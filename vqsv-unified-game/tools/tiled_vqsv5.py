"""Xuất bộ gạch và map thật của VQSV5 sang Tiled, để vẽ map mới bằng chính gạch của game.

    python3 tools/tiled_vqsv5.py                 # bộ gạch (tiled/tilesets/), file dự án Tiled và 68 map VQSV5 (tiled/vqsv5/)
    python3 tools/tiled_vqsv5.py map 17 --out tiled/maps/x.tmj   # một map VQSV5 ra một file để sửa tiếp

Mỗi bộ gạch VQSV5 (11 bộ, data/map/N.data) thành một tileset "bộ sưu tập ảnh" (mỗi mảnh một ảnh, kích thước 20-60 px), lưới 20 px,
gạch neo ở góc dưới trái như trong game. Phép lật/xoay MIDP của mảnh ghép chuyển thành cờ lật của Tiled.
Map xuất ra có các lớp "Nền N" (lớp 1 của VQSV5, tách nhiều lớp để giữ thứ tự vẽ khi các mảnh đè nhau), "Va chạm" (lớp 2)
và "Trên nhân vật N" (lớp 3). Đọc lại bằng tools/import_tiled.py cho ra đúng ảnh và va chạm như tools/build_vqsv5_walk_maps.py.
"""
import argparse
import json
from pathlib import Path

from PIL import Image, ImageChops, ImageDraw

from import_tiled import FLIP_D, FLIP_H, FLIP_V, apply_flips
from vqsv5_map_layers import IMG, T, TBL, area, defs, parse, transform

ROOT = Path(__file__).resolve().parents[1]
TILED = ROOT / "tiled"
DATA = ROOT / "prototype" / "data"
TILED_VERSION = {"version": "1.10", "tiledversion": "1.11.0"}
# Collision tileset (tiled/tilesets/va_cham.tsj): tile index -> collision digit of the game (index.html BLOCK5, terrainAt)
COLLISION = [("Chặn", 1, (220, 50, 50)), ("Nước sâu", 2, (40, 90, 220)), ("Nước nông", 3, (70, 200, 230)),
             ("Chặn (vách)", 4, (230, 140, 30)), ("Đi được", 0, (60, 200, 90))]


def flag_table():
    """MIDP transform code (vqsv5_map_layers.transform) -> Tiled flip flags, found by comparing pictures."""
    probe = Image.new("RGBA", (3, 5))
    probe.putdata([(i * 13 % 256, i * 41 % 256, i * 7 % 256, 255) for i in range(15)])
    table = {}
    for code in range(8):
        want = transform(probe, code)
        for flags in (0, FLIP_H, FLIP_V, FLIP_H | FLIP_V, FLIP_D, FLIP_D | FLIP_H, FLIP_D | FLIP_V, FLIP_D | FLIP_H | FLIP_V):
            got = apply_flips(probe, flags)
            if got.size == want.size and got.tobytes() == want.tobytes():
                table[code] = flags
                break
        assert code in table, f"no Tiled flips for MIDP transform {code}"
    return table


def tileset_name(ts):
    return f"vqsv5_bo{ts:02d}"


def export_tilesets():
    mapping, sets = area()
    out = TILED / "tilesets"
    out.mkdir(parents=True, exist_ok=True)
    for ts in sorted(set(mapping)):
        sheet = Image.open(IMG / f"{sets[ts][0]}.png").convert("RGBA")
        folder = out / tileset_name(ts)
        folder.mkdir(exist_ok=True)
        tiles = []
        for i, d in enumerate(defs(ts)):
            sx, sy, w, h = d[1:5]
            sheet.crop((sx, sy, sx + w, sy + h)).save(folder / f"{i:03d}.png", optimize=True)
            tiles.append({"id": i, "image": f"{tileset_name(ts)}/{i:03d}.png", "imagewidth": w, "imageheight": h})
        used = sorted(n for n, t in enumerate(mapping) if t == ts)
        tsj = {"type": "tileset", **TILED_VERSION, "name": tileset_name(ts), "tilewidth": max(t["imagewidth"] for t in tiles),
               "tileheight": max(t["imageheight"] for t in tiles), "tilecount": len(tiles), "columns": 0, "margin": 0, "spacing": 0,
               "grid": {"orientation": "orthogonal", "width": T, "height": T}, "tiles": tiles,
               "properties": [{"name": "ghichu", "type": "string", "value": f"Bộ gạch VQSV5 số {ts}, dùng cho các map VQSV5 {', '.join(map(str, used))}"}]}
        (out / f"{tileset_name(ts)}.tsj").write_text(json.dumps(tsj, ensure_ascii=False, indent=1), encoding="utf-8")
    # collision tiles: see-through colours so the map shows underneath in Tiled
    sheet = Image.new("RGBA", (T * len(COLLISION), T), (0, 0, 0, 0))
    pen = ImageDraw.Draw(sheet)
    for i, (_, digit, rgb) in enumerate(COLLISION):
        x = i * T
        pen.rectangle((x, 0, x + T - 1, T - 1), fill=rgb + (110,), outline=rgb + (255,))
        if digit in (1, 4):
            pen.line((x + 4, 4, x + T - 5, T - 5), fill=rgb + (255,), width=2)
            pen.line((x + T - 5, 4, x + 4, T - 5), fill=rgb + (255,), width=2)
        elif digit == 0:
            pen.ellipse((x + 6, 6, x + T - 7, T - 7), outline=rgb + (255,), width=2)
        else:
            for wy in (6, 12):
                pen.line((x + 4, wy, x + 9, wy + 2, x + 15, wy), fill=(255, 255, 255, 220), width=1)
    sheet.save(out / "va_cham.png", optimize=True)
    tsj = {"type": "tileset", **TILED_VERSION, "name": "va_cham", "tilewidth": T, "tileheight": T, "tilecount": len(COLLISION),
           "columns": len(COLLISION), "margin": 0, "spacing": 0, "image": "va_cham.png", "imagewidth": sheet.width, "imageheight": T,
           "tiles": [{"id": i, "type": "collision", "properties": [{"name": "terrain", "type": "int", "value": digit}, {"name": "ten", "type": "string", "value": name}]}
                     for i, (name, digit, _) in enumerate(COLLISION)]}
    (out / "va_cham.tsj").write_text(json.dumps(tsj, ensure_ascii=False, indent=1), encoding="utf-8")


def project_file():
    """tiled/vqsv.tiled-project: open it in Tiled (File > Open File or Project) to get the object classes with their fields."""
    def member(name, kind, value):
        return {"name": name, "type": kind, "value": value}
    classes = [
        ("player", "#ffe6c84a", []),
        ("link", "#ff3cb4e6", [member("target", "string", ""), member("spawnX", "int", -1), member("spawnY", "int", -1), member("label", "string", ""),
                               member("kind", "string", ""), member("backX", "int", -1), member("backY", "int", -1)]),
        ("door", "#ff3c78e6", [member("target", "string", ""), member("spawnX", "int", -1), member("spawnY", "int", -1), member("label", "string", ""),
                               member("backX", "int", -1), member("backY", "int", -1)]),
        ("npc", "#ffe678c8", [member("art", "string", "villager"), member("dialogue", "string", ""), member("id", "string", "")]),
        ("chest", "#ffc8a03c", [member("gold", "int", 0), member("item", "int", -1), member("quantity", "int", 1)]),
        ("wild", "#ff78c83c", [member("pets", "string", ""), member("levels", "string", "3-5"), member("quality", "string", "1-2"), member("water", "bool", False)]),
        ("quest", "#ffb43cdc", []),
        ("block", "#ffdc3232", []), ("water", "#ff2850dc", []), ("shallow", "#ff46c8e6", []), ("walk", "#ff3cc85a", []),
    ]
    types = [{"id": i + 1, "name": name, "type": "class", "useAs": ["object"], "color": color, "drawFill": True, "members": members}
             for i, (name, color, members) in enumerate(classes)]
    types.append({"id": len(types) + 1, "name": "collision", "type": "class", "useAs": ["layer", "tile"], "color": "#ffdc3232", "drawFill": True, "members": []})
    types.append({"id": len(types) + 1, "name": "above", "type": "class", "useAs": ["layer"], "color": "#ff9696ff", "drawFill": True, "members": []})
    project = {"automappingRulesFile": "", "commands": [], "compatibilityVersion": 1100, "extensionsPath": "extensions",
               "folders": ["."], "properties": [], "propertyTypes": types}
    (TILED / "vqsv.tiled-project").write_text(json.dumps(project, ensure_ascii=False, indent=1), encoding="utf-8")


def export_map(n, out_path, tilesets_rel="../tilesets"):
    mapping, sets = area()
    ts = mapping[n]
    d, m, flags = defs(ts), parse(n), flag_table()
    sheet = Image.open(IMG / f"{sets[ts][0]}.png").convert("RGBA")
    terrain = {t["map"]: t for t in json.loads((DATA / "vqsv5_map_terrain.json").read_text(encoding="utf-8"))["maps"]}
    W, H = m["w"], m["h"]
    collision_first = 1 + len(d)

    masks = {}

    def piece(v):
        """A piece as it is drawn (after its MIDP transform)."""
        if v not in masks:
            sx, sy, w, h = d[v >> 3][1:5]
            masks[v] = transform(sheet.crop((sx, sy, sx + w, sy + h)), TBL[v & 7])
        return masks[v]

    def clash(a, b):
        """True when two same-size crops both paint a pixel with different colours (then drawing order matters)."""
        both = ImageChops.multiply(a.getchannel("A"), b.getchannel("A")).point(lambda v: 255 if v else 0)
        diff = ImageChops.difference(a, b).split()
        differ = diff[0]
        for band in diff[1:]:
            differ = ImageChops.lighter(differ, band)
        return ImageChops.multiply(both, differ.point(lambda v: 255 if v else 0)).getbbox() is not None

    def spread(items):
        """Put the pieces of one VQSV5 layer on as few Tiled layers as possible: two pieces that paint the same pixel in different
        colours keep their VQSV5 drawing order (the later one on a higher layer); the others can share a layer."""
        placed, grids = [], []
        for x, y, v in items:
            if v < 0 or not (0 <= x < W and 0 <= y < H):
                continue
            a = piece(v)
            rect = (x * T, (y + 1) * T - a.height, x * T + a.width, (y + 1) * T)
            level = 0
            for lv, r, b in placed:
                if lv + 1 <= level or not (rect[0] < r[2] and r[0] < rect[2] and rect[1] < r[3] and r[1] < rect[3]):
                    continue
                box = (max(rect[0], r[0]), max(rect[1], r[1]), min(rect[2], r[2]), min(rect[3], r[3]))
                mine = a.crop((box[0] - rect[0], box[1] - rect[1], box[2] - rect[0], box[3] - rect[1]))
                theirs = b.crop((box[0] - r[0], box[1] - r[1], box[2] - r[0], box[3] - r[1]))
                if clash(mine, theirs):
                    level = lv + 1
            while level < len(grids) and grids[level][y * W + x]:   # one tile per cell and layer: go up
                level += 1
            while len(grids) <= level:
                grids.append([0] * (W * H))
            grids[level][y * W + x] = (1 + (v >> 3)) | flags[TBL[v & 7]]
            placed.append((level, rect, a))
        return grids

    layers, next_id = [], 1

    def tile_layer(name, data, extra=None):
        nonlocal next_id
        layer = {"id": next_id, "name": name, "type": "tilelayer", "x": 0, "y": 0, "width": W, "height": H, "opacity": 1, "visible": True, "data": data}
        if extra:
            layer["properties"] = extra
        layers.append(layer)
        next_id += 1

    for i, grid in enumerate(spread(m["layers"].get(1, []))):
        tile_layer(f"Nền {i + 1}", grid)
    cells = [0] * (W * H)
    for x, y, v in m["layers"].get(2, []):
        digit = v >> 3
        if 0 <= x < W and 0 <= y < H and digit:
            cells[y * W + x] = collision_first + [c[1] for c in COLLISION].index(digit)
    tile_layer("Va chạm", cells)
    for i, grid in enumerate(spread(m["layers"].get(3, []))):
        tile_layer(f"Trên nhân vật {i + 1}", grid, [{"name": "above", "type": "bool", "value": True}])
    layers.append({"id": next_id, "name": "Đối tượng", "type": "objectgroup", "draworder": "topdown", "x": 0, "y": 0, "opacity": 1, "visible": True, "objects": []})
    place = terrain.get(n, {}).get("place_guess", f"Map VQSV5 {n}")
    tmj = {"type": "map", **TILED_VERSION, "orientation": "orthogonal", "renderorder": "right-down", "width": W, "height": H,
           "tilewidth": T, "tileheight": T, "infinite": False, "backgroundcolor": f"#{m['bg'] & 0xFFFFFF:06x}",
           "nextlayerid": next_id + 1, "nextobjectid": 1, "compressionlevel": -1,
           "properties": [{"name": "id", "type": "string", "value": f"vqsv5-{n:02d}"}, {"name": "title", "type": "string", "value": place},
                          {"name": "vqsv5", "type": "int", "value": n}],
           "tilesets": [{"firstgid": 1, "source": f"{tilesets_rel}/{tileset_name(ts)}.tsj"}, {"firstgid": collision_first, "source": f"{tilesets_rel}/va_cham.tsj"}],
           "layers": layers}
    out_path = Path(out_path)
    out_path.parent.mkdir(parents=True, exist_ok=True)
    text = json.dumps(tmj, ensure_ascii=False, separators=(",", ":"))
    out_path.write_text(text.replace(',"layers":[', ',\n"layers":[\n').replace('},{"id"', '},\n{"id"'), encoding="utf-8")
    return out_path


def main():
    ap = argparse.ArgumentParser(description="Xuất gạch và map VQSV5 sang Tiled")
    ap.add_argument("what", nargs="?", default="all", choices=["all", "tilesets", "map"])
    ap.add_argument("number", nargs="?", type=int, help="số map VQSV5 (0-67) khi xuất một map")
    ap.add_argument("--out", help="file .tmj ghi ra (mặc định tiled/vqsv5/vqsv5_NN.tmj)")
    args = ap.parse_args()
    if args.what in ("all", "tilesets"):
        export_tilesets()
        project_file()
        print("Đã xuất bộ gạch vào tiled/tilesets/ và tiled/vqsv.tiled-project")
    if args.what == "all":
        mapping, _ = area()
        for n in range(len(mapping)):
            export_map(n, TILED / "vqsv5" / f"vqsv5_{n:02d}.tmj")
        print(f"Đã xuất {len(mapping)} map VQSV5 vào tiled/vqsv5/")
    if args.what == "map":
        if args.number is None:
            ap.error("cần số map, vd: map 17")
        out = Path(args.out) if args.out else TILED / "vqsv5" / f"vqsv5_{args.number:02d}.tmj"
        rel = Path("../tilesets") if not args.out else Path(__import__("os").path.relpath(TILED / "tilesets", out.resolve().parent))
        print("Đã xuất", export_map(args.number, out, rel.as_posix()))


if __name__ == "__main__":
    main()
