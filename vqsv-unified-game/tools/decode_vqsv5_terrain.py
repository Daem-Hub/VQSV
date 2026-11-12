"""Decode the battlefield type and the wild encounter table of every VQSV5 map (data/map/m<N>.d).

ak.c(int, boolean) reads m<N>.d as: a byte array c, a byte array b, then a matrix. In battle:
  c[0]   field type = pet type id (0 Thực Vật, 1 Kim Loại, 2 Phi Hành, 3 Mãnh Thú, 4 Hải Dương); a pet of another type deals 20% less
         (ak.b(z,al,al): -20 when z.i != its type) unless it knows the matching skill 43 + type (ak.b(z,al) sets z.i = its own type)
  c[1], c[2]  wild level = c[1] + rand(c[2] + 1)
  c[3...]  wild pet ids; ak.b(byte[],int,int) picks c[3 + rand(min(len - 3, 3))] ... or, with 5% and more than 6 entries, entry 6
Maps whose c holds only the type (towns, arenas) have no wild pets. The array b is a separate list (shop/NPC data, not decoded here).

Writes prototype/data/vqsv5_map_terrain.json and asset_review/vqsv5_map_terrain.csv.
"""
import csv
import io
import json
import os
import contextlib
import runpy
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
BUNDLED = ROOT / "sources" / "game"
SOURCE = Path(os.environ.get("VQSV_SOURCE_ROOT", str(BUNDLED if BUNDLED.exists() else Path(r"D:\TOAN BO FILE\Download\game"))))
with contextlib.redirect_stdout(io.StringIO()):
    stats = runpy.run_path(str(Path(__file__).with_name("decode_vqsv5_pet_stats.py")))
PLACES, TYPES, PET_NAMES = stats["place_names"], stats["type_names"], stats["pet_names"]
base = SOURCE / "vqsv5" / "raw_data" / "data" / "map"

maps, rows = [], []
for n in range(68):                                         # m68..m78 are not maps (4-byte stubs and one other table)
    raw = (base / f"m{n}.d").read_bytes()
    if len(raw) <= 4 or int.from_bytes(raw[:2], "big") != len(raw) - 2:
        maps.append({"map": n, "place_guess": PLACES[n] if n < len(PLACES) else None, "empty": True})
        continue
    pos = 2
    c = list(raw[pos + 1:pos + 1 + raw[pos]]); pos += 1 + raw[pos]
    b = list(raw[pos + 1:pos + 1 + raw[pos]]); pos += 1 + raw[pos]
    count = raw[pos]; pos += 1
    for _ in range(count):
        pos += 1 + raw[pos]
    assert pos == len(raw), (n, pos, len(raw))                   # the whole file is accounted for
    entry = {"map": n, "place_guess": PLACES[n] if n < len(PLACES) else None, "terrain_type_id": None, "terrain_type": None, "wild": None}
    if c:
        assert 0 <= c[0] <= 4, (n, c)
        entry["terrain_type_id"], entry["terrain_type"] = c[0], TYPES[c[0]]
    if len(c) > 3:
        pets = c[3:]
        assert all(p < len(PET_NAMES) for p in pets) and c[2] >= 0
        entry["wild"] = {"level_min": c[1], "level_max": c[1] + c[2], "pet_ids": [f"vqsv5-db-{p}" for p in pets], "pet_names": [PET_NAMES[p] for p in pets],
                         "rare_pet_id": f"vqsv5-db-{pets[3]}" if len(pets) > 3 else None}
    maps.append(entry)
    rows.append([n, entry["place_guess"], entry["terrain_type"] or "", "" if not entry["wild"] else f"{entry['wild']['level_min']}-{entry['wild']['level_max']}",
                 "; ".join(entry["wild"]["pet_names"]) if entry["wild"] else ""])

typed = [m for m in maps if m.get("terrain_type_id") is not None]
out = {"source": "vqsv5.jar data/map/m<N>.d, ak.c(int,boolean) and ak.b(z,al,al)", "confidence": "Every file is consumed exactly. The field type and level range are read from the code paths named in the script docstring; place names are guessed from the map index.",
       "summary": {"files": 68, "typed": len(typed), "with_wild_pets": sum(1 for m in maps if m.get("wild")), "by_type": {TYPES[t]: sum(1 for m in typed if m["terrain_type_id"] == t) for t in range(5)}},
       "maps": maps}
(ROOT / "prototype" / "data" / "vqsv5_map_terrain.json").write_text(json.dumps(out, ensure_ascii=False, indent=1), encoding="utf-8")
with (ROOT / "asset_review" / "vqsv5_map_terrain.csv").open("w", encoding="utf-8-sig", newline="") as f:
    writer = csv.writer(f)
    writer.writerow(["map", "place_guess", "terrain_type", "wild_levels", "wild_pets"])
    writer.writerows(rows)
print(f"Terrain: {len(typed)} typed maps, {out['summary']['with_wild_pets']} with wild pets, {out['summary']['by_type']}")
