"""Xuất bộ gạch và map thật của VQSV5 sang Tiled, để vẽ map mới bằng chính gạch của game.

    python3 tools/tiled_vqsv5.py                 # bộ gạch (tiled/tilesets/), file dự án Tiled và 68 map VQSV5 (tiled/vqsv5/)
    python3 tools/tiled_vqsv5.py map 17 --out tiled/maps/x.tmj   # một map VQSV5 ra một file để sửa tiếp

Mỗi bộ gạch VQSV5 (11 bộ, data/map/N.data) thành một tileset "bộ sưu tập ảnh" (mỗi mảnh một ảnh, kích thước 20-60 px), lưới 20 px,
gạch neo ở góc dưới trái như trong game. Phép lật/xoay MIDP của mảnh ghép chuyển thành cờ lật của Tiled.
Map xuất ra có các lớp "Nền N" (lớp 1 của VQSV5, tách nhiều lớp để giữ thứ tự vẽ khi các mảnh đè nhau), "Va chạm" (lớp 2)
và "Trên nhân vật N" (lớp 3). Đọc lại bằng tools/import_tiled.py cho ra đúng ảnh và va chạm như tools/build_vqsv5_walk_maps.py.

Thêm: thuộc tính chan cho những mảnh gạch hầu như lúc nào cũng bị chặn trong 68 map gốc (TRIAL, learn_footprints), để vẽ cây,
đá, vách là tự thành vật cản; bộ hình NPC tiled/tilesets/nhan_vat.tsj (hình NPC của game) và mẫu đối tượng tiled/templates/*.tj.
"""
import argparse
import json
import os
from collections import Counter, defaultdict
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
# NPC pictures of the game (tiled/tilesets/nhan_vat.tsj, templates npc_*.tj): path under prototype/assets, default name
NPCS = [("illustrations/elder_hd.png", "Trưởng làng"), ("illustrations/herbalist_hd.png", "Dược sĩ"),
        ("illustrations/villager_hd.png", "Dân làng"), ("illustrations/scout_hd.png", "Người gác")]


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


def learn_footprints(min_blocked=3, share=0.9):
    """Cells each VQSV5 piece blocks by itself (TRIAL, a guess from the data): over the 68 maps, a cell of a piece drawn on the
    ground layer counts when the collision layer blocks it at least `share` of the times (and at least `min_blocked` times).
    Returns {tileset: {piece: "dx,dy[:digit] ..."}}, cells counted from the bottom-left cell of the unturned piece, y upwards."""
    mapping, _ = area()
    stats, uses = defaultdict(lambda: defaultdict(Counter)), Counter()
    for n in range(len(mapping)):
        m, ts = parse(n), mapping[n]
        d, W, H = defs(ts), m["w"], m["h"]
        grid = [[0] * W for _ in range(H)]
        for x, y, v in m["layers"].get(2, []):
            if 0 <= x < W and 0 <= y < H:
                grid[y][x] = v >> 3
        for x, y, v in m["layers"].get(1, []):
            if v < 0 or not (0 <= x < W and 0 <= y < H):
                continue
            w, h = d[v >> 3][3:5]
            cw, ch = max(1, -(-w // T)), max(1, -(-h // T))
            cells = Image.new("L", (cw, ch))
            cells.putdata([k + 1 for k in range(cw * ch)])            # cell numbers, row by row from the top
            drawn = transform(cells, TBL[v & 7])                         # where each cell lands once the piece is turned
            uses[(ts, v >> 3)] += 1
            for j in range(drawn.height):
                for i in range(drawn.width):
                    k, cx, cy = drawn.getpixel((i, j)) - 1, x + i, y - (drawn.height - 1 - j)
                    if 0 <= cx < W and 0 <= cy < H:
                        stats[(ts, v >> 3)][(k % cw, ch - 1 - k // cw)][grid[cy][cx]] += 1
    out = defaultdict(dict)
    for (ts, piece), cells in stats.items():
        parts = []
        for (dx, dy), c in sorted(cells.items(), key=lambda kv: (kv[0][1], kv[0][0])):
            blocked = c[1] + c[2] + c[4]
            if blocked >= min_blocked and blocked / sum(c.values()) >= share:
                digit = max((1, 2, 4), key=lambda k: c[k])
                parts.append(f"{dx},{dy}" + ("" if digit == 1 else f":{digit}"))
        if parts:
            out[ts][piece] = " ".join(parts)
    return out


def export_tilesets():
    mapping, sets = area()
    out = TILED / "tilesets"
    out.mkdir(parents=True, exist_ok=True)
    blocks = learn_footprints()
    for ts in sorted(set(mapping)):
        sheet = Image.open(IMG / f"{sets[ts][0]}.png").convert("RGBA")
        folder = out / tileset_name(ts)
        folder.mkdir(exist_ok=True)
        tiles = []
        for i, d in enumerate(defs(ts)):
            sx, sy, w, h = d[1:5]
            sheet.crop((sx, sy, sx + w, sy + h)).save(folder / f"{i:03d}.png", optimize=True)
            tile = {"id": i, "image": f"{tileset_name(ts)}/{i:03d}.png", "imagewidth": w, "imageheight": h}
            if i in blocks[ts]:
                tile["properties"] = [{"name": "chan", "type": "string", "value": blocks[ts][i]}]
            tiles.append(tile)
        used = sorted(n for n, t in enumerate(mapping) if t == ts)
        tsj = {"type": "tileset", **TILED_VERSION, "name": tileset_name(ts), "tilewidth": max(t["imagewidth"] for t in tiles),
               "tileheight": max(t["imageheight"] for t in tiles), "tilecount": len(tiles), "columns": 0, "margin": 0, "spacing": 0,
               "grid": {"orientation": "orthogonal", "width": T, "height": T}, "tiles": tiles,
               "properties": [{"name": "ghichu", "type": "string", "value": f"Bộ gạch VQSV5 số {ts}, dùng cho các map VQSV5 {', '.join(map(str, used))}. "
                                                                         "Thuộc tính chan của gạch: các ô gạch đó tự chặn (TRIAL, học từ 68 map gốc)"}]}
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
    # the village NPC pictures of the game itself, to place NPCs as pictures (tile objects)
    tiles = []
    for i, (art, name) in enumerate(NPCS):
        path = ROOT / "prototype" / "assets" / art
        w, h = Image.open(path).size
        tiles.append({"id": i, "image": Path(os.path.relpath(path, out)).as_posix(), "imagewidth": w, "imageheight": h,
                      "properties": [{"name": "ten", "type": "string", "value": name}]})
    tsj = {"type": "tileset", **TILED_VERSION, "name": "nhan_vat", "tilewidth": max(t["imagewidth"] for t in tiles),
           "tileheight": max(t["imageheight"] for t in tiles), "tilecount": len(tiles), "columns": 0, "margin": 0, "spacing": 0,
           "grid": {"orientation": "orthogonal", "width": T, "height": T}, "tiles": tiles}
    (out / "nhan_vat.tsj").write_text(json.dumps(tsj, ensure_ascii=False, indent=1), encoding="utf-8")


def templates():
    """tiled/templates/*.tj: objects ready to drop on a map (Tiled: View > Views and Toolbars > Templates)."""
    def prop(name, kind, value):
        return {"name": name, "type": kind, "value": value}
    way = [prop("target", "string", ""), prop("spawnX", "int", -1), prop("spawnY", "int", -1), prop("backX", "int", -1), prop("backY", "int", -1)]
    cell = {"width": T, "height": T}
    area_ = {"width": 2 * T, "height": 2 * T}
    objects = {
        "kael": {"type": "player", "name": "Kael", **cell},
        "loi_di": {"type": "link", "name": "Lối đi", **cell, "properties": way},
        "cua_vao_nha": {"type": "door", "name": "Cửa", **cell, "properties": way},
        "ruong_kim_tien": {"type": "chest", "name": "Rương", **cell, "properties": [prop("gold", "int", 100)]},
        "ruong_vat_pham": {"type": "chest", "name": "Rương", **cell, "properties": [prop("item", "int", 1), prop("quantity", "int", 1)]},
        "pet_hoang_da": {"type": "wild", "name": "Pet hoang dã", "width": 3 * T, "height": 3 * T,
                         "properties": [prop("pets", "string", ""), prop("levels", "string", "3-5")]},
        "nhiem_vu": {"type": "quest", "name": "MQ001:0", **cell},
        "vung_chan": {"type": "block", "name": "", **area_}, "nuoc_sau": {"type": "water", "name": "", **area_},
        "nuoc_nong": {"type": "shallow", "name": "", **area_}, "di_duoc": {"type": "walk", "name": "", **area_},
    }
    out = TILED / "templates"
    out.mkdir(parents=True, exist_ok=True)
    for name, obj in objects.items():
        (out / f"{name}.tj").write_text(json.dumps({"type": "template", "object": {"id": 1, "rotation": 0, "visible": True, **obj}},
                                                   ensure_ascii=False, indent=1), encoding="utf-8")
    for i, (art, name) in enumerate(NPCS):                         # an NPC as its picture, 38 px tall like in the game
        w, h = Image.open(ROOT / "prototype" / "assets" / art).size
        obj = {"id": 1, "type": "npc", "name": name, "gid": 1 + i, "width": round(38 * w / h), "height": 38, "rotation": 0, "visible": True,
               "properties": [prop("dialogue", "string", "Chào cháu!")]}
        tpl = {"type": "template", "tileset": {"firstgid": 1, "source": "../tilesets/nhan_vat.tsj"}, "object": obj}
        (out / f"npc_{Path(art).stem.replace('_hd', '')}.tj").write_text(json.dumps(tpl, ensure_ascii=False, indent=1), encoding="utf-8")


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
                          {"name": "vqsv5", "type": "int", "value": n},
                          {"name": "autoBlock", "type": "bool", "value": False}],     # the real collision layer is complete
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
        templates()
        project_file()
        print("Đã xuất bộ gạch vào tiled/tilesets/, mẫu đối tượng vào tiled/templates/ và tiled/vqsv.tiled-project")
    if args.what == "all":
        mapping, _ = area()
        for n in range(len(mapping)):
            export_map(n, TILED / "vqsv5" / f"vqsv5_{n:02d}.tmj")
        print(f"Đã xuất {len(mapping)} map VQSV5 vào tiled/vqsv5/")
    if args.what == "map":
        if args.number is None:
            ap.error("cần số map, vd: map 17")
        out = Path(args.out) if args.out else TILED / "vqsv5" / f"vqsv5_{args.number:02d}.tmj"
        rel = Path("../tilesets") if not args.out else Path(os.path.relpath(TILED / "tilesets", out.resolve().parent))
        print("Đã xuất", export_map(args.number, out, rel.as_posix()))


if __name__ == "__main__":
    main()
