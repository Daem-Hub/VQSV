"""Rebuild the walking maps from real VQSV5 maps (data/map/N.mid, decoded in assets/vqsv5/maps/).

Each exploration region of the prototype is laid on one real VQSV5 map, chosen to look like the region (PAIRING; the choice is a
prototype decision, the maps themselves are source data). For each one this script:
  - renders the ground layer (layer 1 on the map's background colour) and the over-the-character layer (layer 3) into two PNGs,
    tiles anchored BOTTOM|LEFT on 20 px cells with the MIDP transforms, exactly like assets/vqsv5/maps/giai_ma_map_vqsv5.py;
  - keeps the collision grid (layer 2): on foot, 1, 2 and 4 block (at.java, movement mode 0); 3 is shallow water you can wade;
  - places the region's exits on the side they had before (on a walkable border tile of the main walkable area) and fixes every
    arrival point, places the real chests of that VQSV5 map (raw_data/4), and sets terrain5 to that map's battlefield type;
  - keeps the region's VQSV6 wild tables, titles and links. Branch maps designed for VQSV6 pets get `wild5: false`.
Run after design_wild_maps.py, design_extra_wild_maps.py and design_map_chests.py (it replaces their layout, chests and terrain5).
"""
import json
from collections import deque
from pathlib import Path
from PIL import Image

from vqsv5_map_layers import IMG, T, area, defs, parse, render

ROOT = Path(__file__).resolve().parents[1]
DATA = ROOT / "prototype" / "data"
OUT = ROOT / "prototype" / "assets" / "maps"
PAIRING = {   # unified map id: (file, VQSV5 map index, VQSV5 wild pets allowed)
    "ryan-phong-loi-forest": ("forest_map.json", 16, True),      # Rừng rậm phía tây 1
    "ryan-forest-stream": ("forest_stream_map.json", 18, True),  # Rừng rậm phía tây 3 (river)
    "ryan-lakeside": ("wild_lakeside.json", 31, True),           # Vực Bắc Bắc Hải (islands)
    "ryan-meadow": ("wild_meadow.json", 12, True),               # Cao nguyên phía đông 1
    "ryan-highland": ("wild_highland.json", 14, True),           # Cao nguyên phía đông 3 (cliffs)
    "ryan-canyon": ("wild_canyon.json", 15, True),               # Cao nguyên phía đông 4 (rock walls)
    "ryan-ashfield": ("wild_ashfield.json", 41, True),           # Rừng rậm u ám 4 (dark ground)
    "ryan-skyridge": ("wild_skyridge.json", 24, True),           # Bầu trời phương bắc 1
    "ryan-pinewind": ("wild_pinewind.json", 20, False),          # Rừng rậm phía tây 5
    "ryan-inkmarsh": ("wild_inkmarsh.json", 36, False),          # Khu trường cũ Bắc Hải 2 (water)
    "ryan-stormplain": ("wild_stormplain.json", 11, False),      # Bên ngoài thành phía nam
    "ryan-sanctum": ("wild_sanctum.json", 49, False),            # Thiên Không Thành 1
}
BLOCKING = {1, 2, 4}


def side_of(link, w, h):
    if link["x"] == 0: return "left"
    if link["y"] == 0: return "top"
    if link["y"] == h - 1: return "bottom"
    return "right"


mapping, sets = area()
terrain = {m["map"]: m for m in json.loads((DATA / "vqsv5_map_terrain.json").read_text(encoding="utf-8"))["maps"]}
chest_src = {m["map"]: m for m in json.loads((DATA / "vqsv5_map_chests.json").read_text(encoding="utf-8"))["maps"]}
OUT.mkdir(parents=True, exist_ok=True)
built = {}
for map_id, (filename, source, wild5) in PAIRING.items():
    old = json.loads((DATA / filename).read_text(encoding="utf-8"))
    m = parse(source); ts = mapping[source]
    tile_defs, sheet = defs(ts), Image.open(IMG / f"{sets[ts][0]}.png").convert("RGBA")
    base, over = f"assets/maps/vqsv5_{source:02d}_base.png", f"assets/maps/vqsv5_{source:02d}_overlay.png"
    render(m, tile_defs, sheet, 1, True).convert("RGB").save(ROOT / "prototype" / base)
    has_overlay = bool(m["layers"].get(3))
    if has_overlay: render(m, tile_defs, sheet, 3, False).save(ROOT / "prototype" / over)
    W, H = m["w"], m["h"]
    grid = [[0] * W for _ in range(H)]
    for x, y, v in m["layers"].get(2, []):
        if 0 <= x < W and 0 <= y < H: grid[y][x] = v >> 3
    free = lambda x, y: 0 <= x < W and 0 <= y < H and grid[y][x] not in BLOCKING
    # the main walkable area: the largest connected group of free cells
    seen, best = set(), set()
    for y in range(H):
        for x in range(W):
            if free(x, y) and (x, y) not in seen:
                comp, q = {(x, y)}, deque([(x, y)])
                while q:
                    cx, cy = q.popleft()
                    for p in ((cx + 1, cy), (cx - 1, cy), (cx, cy + 1), (cx, cy - 1)):
                        if free(*p) and p not in comp: comp.add(p); q.append(p)
                seen |= comp
                if len(comp) > len(best): best = comp
    used = set()
    chest_cells = {(c["x"], c["y"]) for c in chest_src[source]["chests"]}
    near_chest = lambda c: any(abs(c[0] - x) + abs(c[1] - y) <= 1 for x, y in chest_cells)   # keep exits clear of the real chests

    def exit_tile(side):
        """A border cell of the main area on that side, nearest the middle; then its inner neighbour as the arrival cell."""
        def key(c):
            x, y = c
            depth = {"left": x, "right": W - 1 - x, "top": y, "bottom": H - 1 - y}[side]
            along = abs(y - H / 2) if side in ("left", "right") else abs(x - W / 2)
            return (depth, along)
        for cell in sorted(best, key=key):
            if cell in used or near_chest(cell): continue
            x, y = cell
            dx, dy = {"left": (1, 0), "right": (-1, 0), "top": (0, 1), "bottom": (0, -1)}[side]
            inner = (x + dx, y + dy)
            if inner not in best:
                inner = next((p for p in ((x + 1, y), (x - 1, y), (x, y + 1), (x, y - 1)) if p in best and p not in used), None)
            if inner and inner not in used:
                used.update({cell, inner}); return cell, inner
        raise SystemExit(f"no exit on {side} for {map_id}")
    links, arrivals = [], {}
    for link in old.get("links", []):
        cell, inner = exit_tile(side_of(link, old["width"], old["height"]))
        links.append({"x": cell[0], "y": cell[1], "target": link["target"], "spawnX": link["spawnX"], "spawnY": link["spawnY"], "label": link["label"]})
        arrivals[link["target"]] = inner
    spawn = arrivals[links[0]["target"]]
    blocked_for_chests = used | {spawn}
    chests = [{"id": f"{map_id}:{i}", "x": c["x"], "y": c["y"], "source_map": source, "reward": {k: v for k, v in c.items() if k not in ("x", "y")}}
              for i, c in enumerate(chest_src[source]["chests"]) if (c["x"], c["y"]) in best and not any(abs(c["x"] - x) + abs(c["y"] - y) <= 1 for x, y in blocked_for_chests)]
    t = terrain[source]
    new = {
        "id": map_id, "title": old["title"], "width": W, "height": H, "tileSize": T,
        "source5": {"map": source, "place": t["place_guess"], "tileset": ts, "background": f"#{m['bg'] & 0xFFFFFF:06x}", "base": base, "overlay": over if has_overlay else None},
        "collision": ["".join(str(v) for v in row) for row in grid],
        "ground": old.get("ground"), "groundVariation": [], "groundDecor": [], "paths": [], "objects": [],
        "entities": {"player": {"x": spawn[0], "y": spawn[1]}, "villagers": []},
        "links": links, "chests": chests,
        "terrain5": {"source_maps": [source], "types": [t["terrain_type_id"]] if t.get("terrain_type_id") is not None else []},
    }
    if "wildAreas" in old: new["wildAreas"] = old["wildAreas"]          # the two forest maps use the game's built-in area list instead
    if old.get("customWildAreas"): new["customWildAreas"] = old["customWildAreas"]
    if not wild5: new["wild5"] = False
    new["arrivals"] = {target: list(cell) for target, cell in arrivals.items()}      # where you stand when you come in from that map
    built[map_id] = (filename, new, arrivals)
# arrival points: a link A -> B lands on B's cell next to B's exit back to A
for map_id, (filename, new, _) in built.items():
    for link in new["links"]:
        if link["target"] in built:
            spot = built[link["target"]][2].get(map_id)
            if spot: link["spawnX"], link["spawnY"] = spot
for map_id, (filename, new, _) in built.items():
    (DATA / filename).write_text(json.dumps(new, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    print(f"{map_id}: VQSV5 map {new['source5']['map']} ({new['source5']['place']}) {new['width']}x{new['height']}, {len(new['links'])} exits, {len(new['chests'])} chests, field {new['terrain5']['types']}")
