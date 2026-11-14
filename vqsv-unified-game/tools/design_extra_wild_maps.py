"""Design four extra wild maps for the VQSV6 pets that no source wild area (petArea.mid) contains.

45 of the 100 VQSV6 pets never appear in a decoded wild area: 26 are evolved forms (obtained by evolution), the other 19 base forms
had no way to be met in the prototype. These maps host them, plus the one unused source area (1-6). Their wild tables live in the map
as `customWildAreas` (same shape as the decoded areas) and are a DESIGN CHOICE of the prototype, not source data: levels follow the
map they branch from, quality ranges start at the species' default quality.

  ryan-pinewind   Rừng Thông Gió        north of Rừng Rậm Phong Lôi  lv 7-10   source 1-6 + weak base forms (Phong, Mộc, Hỏa, Điện)
  ryan-inkmarsh   Đầm Mực Đen           south of Hồ Sương Mù         lv 15-19  water pets on the bridge, ghosts and birds on land
  ryan-stormplain Đồng Bằng Sấm Sét     north of Hẻm Núi Đá          lv 26-30  quality 3 forms (Điện, Mộc, Hỏa)
  ryan-sanctum    Thánh Địa Thần Thú    east of Dải Mây Cao          lv 46-50  the quality 5 legendaries, one per element

Each map also gets a battlefield type (`terrain5`, for the VQSV5 battle background and terrain rule) chosen to suit its theme.
Run after tools/design_wild_maps.py (which rewrites the host maps) and before tools/design_map_chests.py.
"""
import json
import random
from collections import deque
from pathlib import Path

root = Path(__file__).resolve().parents[1]
data = root / "prototype" / "data"
W, H = 24, 18


def pets(*rows):
    return [{"pet_id": pid, "name": name, "terrain": terrain, "quality_range": list(q)} for pid, name, terrain, q in rows]


REGIONS = [
    dict(id="ryan-pinewind", file="wild_pinewind.json", title="Rừng Thông Gió", ground=(25, 2), tint="#7fe0b022", trees=13, rocks=3, decor=[0, 1, 3],
         host="forest_map.json", host_edge="top", entry="bottom", terrain=0, source_areas=["1-6"],
         custom=[{"area": "x-pinewind", "level_range": [8, 10], "pets": pets(
             ("vqsv6-db-105", "Đậu ưng", 0, (1, 2)), ("vqsv6-db-39", "Uy Qua Đệ Đệ", 0, (1, 2)),
             ("vqsv6-db-10", "Nhiên Dực Bức", 0, (1, 2)), ("vqsv6-db-84", "Dị Tiểu Hiệp", 0, (1, 2)))}], seed=21),
    dict(id="ryan-inkmarsh", file="wild_inkmarsh.json", title="Đầm Mực Đen", ground=(25, 2), tint="#3a2a6a38", trees=6, rocks=4, decor=[1, 5],
         host="wild_lakeside.json", host_edge="bottom", entry="top", terrain=4, source_areas=[], water=True,
         custom=[{"area": "x-inkmarsh-land", "level_range": [15, 18], "pets": pets(
             ("vqsv6-db-96", "Hư không hành giả", 0, (1, 2)), ("vqsv6-db-64", "Lục Hành Điểu", 0, (1, 2)))},
                 {"area": "x-inkmarsh-water", "level_range": [16, 19], "pets": pets(
             ("vqsv6-db-66", "Mực Ma", 2, (1, 2)), ("vqsv6-db-68", "Thủy Thượng Phiêu", 2, (2, 3)), ("vqsv6-db-64", "Lục Hành Điểu", 0, (1, 2)))}], seed=22),
    dict(id="ryan-stormplain", file="wild_stormplain.json", title="Đồng Bằng Sấm Sét", ground=(34, 1), tint="#ffe86a26", trees=3, rocks=10, decor=[0, 3],
         host="wild_canyon.json", host_edge="top", entry="bottom", terrain=3, source_areas=[],
         custom=[{"area": "x-stormplain", "level_range": [26, 30], "pets": pets(
             ("vqsv6-db-82", "Chuột Điện", 0, (3, 4)), ("vqsv6-db-40", "Thảo Diệp Cô", 0, (3, 4)),
             ("vqsv6-db-15", "Viêm Ma", 0, (3, 4)), ("vqsv6-db-12", "Nhiên Liệp Sư", 0, (2, 3)))}], seed=23),
    dict(id="ryan-sanctum", file="wild_sanctum.json", title="Thánh Địa Thần Thú", ground=(34, 1), tint="#fff4c03a", trees=4, rocks=8, decor=[5, 3],
         host="wild_skyridge.json", host_edge="right", entry="left", terrain=2, source_areas=[], water=True,
         custom=[{"area": "x-sanctum", "level_range": [46, 50], "pets": pets(
             ("vqsv6-db-25", "Hỏa Phượng Hoàng", 0, (5, 5)), ("vqsv6-db-41", "Linh Quang Lộc", 0, (5, 5)), ("vqsv6-db-57", "Chiến Thần Đà", 0, (5, 5)),
             ("vqsv6-db-85", "Lôi Kỳ Lân", 0, (5, 5)), ("vqsv6-db-97", "Minh vương Long", 0, (5, 5)), ("vqsv6-db-109", "Tinh Vân Hạc", 0, (5, 5)),
             ("vqsv6-db-73", "Tương Quân Giải", 2, (5, 5)))}], seed=24),
]
NEW_IDS = {r["id"] for r in REGIONS}


def blocked_of(m):
    cells = set()
    for o in m["objects"]:
        cells |= {tuple(c) for c in o.get("solid", [])}
    for w in m.get("water", []):
        cells |= {(x, y) for x in range(w["x"], w["x"] + w["width"]) for y in range(w["y"], w["y"] + w["height"])}
    cells |= {(c["x"], c["y"]) for c in m.get("chests", [])}
    return cells


def reach(m, start, blocked):
    seen, queue = {start}, deque([start])
    while queue:
        x, y = queue.popleft()
        for p in ((x + 1, y), (x - 1, y), (x, y + 1), (x, y - 1)):
            if 0 <= p[0] < m["width"] and 0 <= p[1] < m["height"] and p not in blocked and p not in seen:
                seen.add(p); queue.append(p)
    return seen


def host_link(spec):
    """Pick a reachable edge tile on the host map (with a free tile inside it) for the branch exit."""
    m = json.loads((data / spec["host"]).read_text(encoding="utf-8"))
    m["links"] = [l for l in m.get("links", []) if l["target"] not in NEW_IDS]
    blocked = blocked_of(m)
    seen = reach(m, (m["entities"]["player"]["x"], m["entities"]["player"]["y"]), blocked)
    edge = spec["host_edge"]
    if edge == "right":
        options = [(W - 1, y, W - 2, y) for y in (10, 9, 11)]
    else:
        y, iy = (0, 1) if edge == "top" else (H - 1, H - 2)
        options = [(x, y, x, iy) for x in sorted(range(1, W - 1), key=lambda x: abs(x - 16))]
    for x, y, ix, iy in options:
        if (x, y) in seen and (ix, iy) in seen and not any(abs(l["x"] - x) + abs(l["y"] - y) < 3 for l in m["links"]):
            return m, (x, y), (ix, iy)
    raise SystemExit(f"no free {edge} edge on {spec['host']}")


def build(spec, host_id, host_title, back_spawn):
    rng = random.Random(spec["seed"])
    entry = spec["entry"]
    m = {"id": spec["id"], "title": spec["title"], "width": W, "height": H, "tileSize": 20,
         "ground": {"tileset": 4, "tile": spec["ground"][0], "step": spec["ground"][1]}, "groundVariation": [], "groundDecor": [],
         "paths": [{"x": 0, "y": 9, "width": W, "height": 3}], "objects": [], "tint": spec["tint"], "wildAreas": spec["source_areas"],
         "customWildAreas": spec["custom"], "links": []}
    road = {(x, y) for x in range(W) for y in range(9, 12)}
    if entry == "bottom":
        link, spawn = (12, H - 1), (12, H - 2)
        m["paths"].append({"x": 11, "y": 12, "width": 3, "height": H - 12}); road |= {(x, y) for x in range(11, 14) for y in range(12, H)}
    elif entry == "top":
        link, spawn = (16, 0), (16, 1)
        m["paths"].append({"x": 15, "y": 0, "width": 3, "height": 9}); road |= {(x, y) for x in range(15, 18) for y in range(0, 9)}
    else:
        link, spawn = (0, 10), (1, 10)
    m["entities"] = {"player": {"x": spawn[0], "y": spawn[1]}, "villagers": []}
    m["links"].append({"x": link[0], "y": link[1], "target": host_id, "spawnX": back_spawn[0], "spawnY": back_spawn[1], "label": host_title})
    blocked = set()
    if spec.get("water"):
        if entry == "top":                     # keep the north path dry: the marsh water lies west, crossed by the road on a bridge
            m["water"] = [{"x": 3, "y": 0, "width": 5, "height": 9}, {"x": 3, "y": 12, "width": 5, "height": 6}]
            m["bridge"] = {"x": 3, "y": 9, "width": 5, "height": 3}
        else:
            m["water"] = [{"x": 10, "y": 0, "width": 4, "height": 9}, {"x": 10, "y": 12, "width": 4, "height": 6}]
            m["bridge"] = {"x": 10, "y": 9, "width": 4, "height": 3}
        for w in m["water"]:
            blocked |= {(x, y) for x in range(w["x"], w["x"] + w["width"]) for y in range(w["y"], w["y"] + w["height"])}
    keep = road | {spawn, link} | {(link[0] + dx, link[1] + dy) for dx in (-1, 0, 1) for dy in (-1, 0, 1)}

    def free(cells):
        return all(0 <= x < W and 0 <= y < H and (x, y) not in blocked and (x, y) not in keep for x, y in cells)
    placed = attempts = 0
    while placed < spec["trees"] and attempts < 500:
        attempts += 1
        x, y = rng.randrange(1, W - 2), rng.randrange(3, H)
        cells = [(xx, yy) for yy in (y - 1, y) for xx in (x, x + 1)]
        if free(cells):
            m["objects"] += [{"tileset": 0, "tile": 11, "x": x, "y": y - 2}, {"tileset": 0, "tile": 12, "x": x, "y": y, "solid": [list(c) for c in cells]}]
            blocked |= set(cells); placed += 1
    placed = attempts = 0
    while placed < spec["rocks"] and attempts < 600:
        attempts += 1
        x, y = rng.randrange(0, W), rng.randrange(0, H)
        if free([(x, y)]):
            m["objects"].append({"tileset": 4, "tile": rng.choice([4, 12]), "x": x, "y": y, "solid": [[x, y]]})
            blocked.add((x, y)); placed += 1
    for n in range(8):
        x, y = rng.randrange(0, W), rng.randrange(0, H)
        if (x, y) not in blocked and (x, y) not in road:
            m["groundDecor"].append({"tileset": 4, "tile": spec["decor"][n % len(spec["decor"])], "x": x, "y": y})
    m["terrain5"] = {"source_maps": [], "types": [spec["terrain"]], "note": "prototype design: no VQSV5 area; the type sets the battle background and the terrain rule"}
    seen = reach(m, spawn, blocked)
    assert link in seen and (0, 10) in seen and (W - 1, 10) in seen, spec["id"]
    return m, len(seen)


for spec in REGIONS:
    host, tile, inner = host_link(spec)
    m, cells = build(spec, host["id"], host["title"], inner)
    spawn = (m["entities"]["player"]["x"], m["entities"]["player"]["y"])
    host["links"].append({"x": tile[0], "y": tile[1], "target": spec["id"], "spawnX": spawn[0], "spawnY": spawn[1], "label": spec["title"]})
    if spec["host_edge"] in ("top", "bottom"):         # a short dirt track from the road to the new exit
        y0, y1 = (0, 9) if spec["host_edge"] == "top" else (12, H)
        host["paths"] = [p for p in host.get("paths", []) if not (p.get("branch") == spec["id"])] + [{"x": tile[0], "y": y0, "width": 1, "height": y1 - y0, "branch": spec["id"]}]
    (data / spec["host"]).write_text(json.dumps(host, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    (data / spec["file"]).write_text(json.dumps(m, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    print(f"{spec['id']}: {cells} reachable cells, exit on {host['id']} at {tile}")
