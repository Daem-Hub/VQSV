"""Place treasure chests on the unified maps, with the real contents of the VQSV5 chests (vqsv5_map_chests.json).

The unified maps are not the VQSV5 maps, so positions are designed here: each map is paired with the VQSV5 area that looks most like it
(TRIAL choice, see AREAS) and gets up to MAX_CHESTS chests whose rewards are drawn, without repetition, from the chests of those source maps.
Chests stand on free tiles away from the road (rows 8-12), the spawn point and the links, and every chest keeps at least one reachable
neighbour. Run after tools/design_*_map(s).py, which rewrite the map files; this script only adds the `chests` field.
"""
import json
import random
from collections import deque
from pathlib import Path

root = Path(__file__).resolve().parents[1]
data = root / "prototype" / "data"
MAX_CHESTS = 4
# unified map id -> (file, name fragments of the source places whose chests feed it)
AREAS = {
    "ryan-phong-loi-forest": ("forest_map.json", ["Rừng rậm phía tây"]),
    "ryan-forest-stream": ("forest_stream_map.json", ["Rừng rậm u ám"]),
    "ryan-lakeside": ("wild_lakeside.json", ["Bắc Hải"]),
    "ryan-meadow": ("wild_meadow.json", ["Cao nguyên phía đông"]),
    "ryan-highland": ("wild_highland.json", ["Nam Chi Phế Tích"]),
    "ryan-canyon": ("wild_canyon.json", ["Kim Loại Di Tích"]),
    "ryan-skyridge": ("wild_skyridge.json", ["Bầu trời phương bắc", "Thiên Không Thành"]),
    "ryan-ashfield": ("wild_ashfield.json", None),        # no source area of its own: the chests no other region uses
}
source = json.loads((data / "vqsv5_map_chests.json").read_text(encoding="utf-8"))
by_map = {m["map"]: m for m in source["maps"]}


def pool_for(fragments):
    used = {m["map"] for _, (_, frags) in AREAS.items() if frags for m in source["maps"] if m["place_guess"] and any(f in m["place_guess"] for f in frags)}
    if fragments is None:
        return [(m["map"], c) for m in source["maps"] if m["map"] not in used for c in m["chests"]]
    return [(m["map"], c) for m in source["maps"] if m["place_guess"] and any(f in m["place_guess"] for f in fragments) for c in m["chests"]]


def blocked_cells(m):
    cells = set()
    for o in m["objects"]:
        cells.update(tuple(c) for c in o.get("solid", []))
    for w in m.get("water", []):
        cells.update((x, y) for x in range(w["x"], w["x"] + w["width"]) for y in range(w["y"], w["y"] + w["height"]))
    return cells


def reachable(m, blocked, start):
    seen, queue = {start}, deque([start])
    while queue:
        x, y = queue.popleft()
        for dx, dy in ((1, 0), (-1, 0), (0, 1), (0, -1)):
            nx, ny = x + dx, y + dy
            if 0 <= nx < m["width"] and 0 <= ny < m["height"] and (nx, ny) not in blocked and (nx, ny) not in seen:
                seen.add((nx, ny)); queue.append((nx, ny))
    return seen


for map_id, (filename, fragments) in AREAS.items():
    path = data / filename
    m = json.loads(path.read_text(encoding="utf-8"))
    assert m["id"] == map_id
    rng = random.Random(f"chests:{map_id}")
    pool = pool_for(fragments)
    rng.shuffle(pool)
    blocked = blocked_cells(m)
    start = (m["entities"]["player"]["x"], m["entities"]["player"]["y"])
    keep_clear = {start} | {(l["x"] + dx, l["y"] + dy) for l in m.get("links", []) for dx in (-1, 0, 1) for dy in (-1, 0, 1)}
    chests = []
    for index, (source_map, reward) in enumerate(pool[:MAX_CHESTS]):
        for _ in range(500):
            x, y = rng.randrange(1, m["width"] - 1), rng.randrange(2, m["height"] - 1)
            cell = (x, y)
            if cell in blocked or cell in keep_clear or 8 <= y <= 12 or any(abs(x - c["x"]) + abs(y - c["y"]) < 3 for c in chests):
                continue
            trial = blocked | {(c["x"], c["y"]) for c in chests} | {cell}
            area = reachable(m, trial, start)
            if any((x + dx, y + dy) in area for dx, dy in ((1, 0), (-1, 0), (0, 1), (0, -1))) and all(
                    any((c["x"] + dx, c["y"] + dy) in area for dx, dy in ((1, 0), (-1, 0), (0, 1), (0, -1))) for c in chests):
                chests.append({"id": f"{map_id}:{index}", "x": x, "y": y, "source_map": source_map, "reward": {k: v for k, v in reward.items() if k not in ("x", "y")}})
                break
        else:
            raise SystemExit(f"no free tile for chest {index} on {map_id}")
    m["chests"] = chests
    path.write_text(json.dumps(m, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    print(f"{map_id}: {len(chests)} chests, e.g. {chests[0]['reward']}")
