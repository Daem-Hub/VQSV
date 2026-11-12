"""Decode the treasure chests placed on the VQSV5 maps.

The resource "4" (raw_data/4, read by as.b(0) = ad.b("4", 0)) holds one blob per map: at.p() reads, in order, a byte list (header), then
five matrices (each: count, then rows of [length, bytes]) into b[0], b[1], b[2], a and c. Every blob is consumed exactly (68 of 68). The
fourth matrix `a` is the chest table: row [x, y, state, content, quantity] (state 1 = closed, 2 = opened). Opening a chest (at.z()/at.A()):
content - 2 = -2 -> kim tiền (quantity x 100), -1 -> huy chương (quantity), otherwise item id (content - 2) x quantity.

Writes prototype/data/vqsv5_map_chests.json and asset_review/vqsv5_map_chests.csv.
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
ITEM_NAMES, PLACE_NAMES = stats["item_names"], stats["place_names"]

blob = (SOURCE / "vqsv5" / "raw_data" / "4").read_bytes()
pos, entries = 1, []
while pos + 2 <= len(blob):
    size = (blob[pos] << 8) | blob[pos + 1]; pos += 2
    entries.append(blob[pos:pos + size]); pos += size
assert pos == len(blob) and len(entries) == 3
first = entries[0]
assert first[0] == 68
p, maps = 1, []
while p + 2 <= len(first):
    size = (first[p] << 8) | first[p + 1]; p += 2
    maps.append(first[p:p + size]); p += size
assert p == len(first) and len(maps) == 68


def parse(raw):
    at = 0

    def array():
        nonlocal at
        n = raw[at]; at += 1
        out = list(raw[at:at + n]); at += n
        return out

    def matrix():
        nonlocal at
        n = raw[at]; at += 1
        rows = []
        for _ in range(n):
            m = raw[at]; at += 1
            rows.append(list(raw[at:at + m])); at += m
        return rows
    header = array(); groups = [matrix() for _ in range(3)]; chests = matrix(); other = matrix()
    assert at == len(raw), (at, len(raw))                       # every byte of the blob is accounted for
    return header, groups, chests, other


result, rows = [], []
for index, raw in enumerate(maps):
    header, groups, chests, other = parse(raw)
    entry = {"map": index, "place_guess": PLACE_NAMES[index] if index < len(PLACE_NAMES) else None, "chests": []}
    for x, y, state, content, quantity in chests:
        assert state == 1 and len(chests[0]) == 5
        code = content - 2
        if code == -2:
            reward = {"kind": "gold", "kim_tien": quantity * 100}
        elif code == -1:
            reward = {"kind": "medals", "medals": quantity}
        else:
            reward = {"kind": "item", "item_id": code, "name": ITEM_NAMES[code], "quantity": quantity}
        entry["chests"].append({"x": x, "y": y, **reward})
        rows.append([index, entry["place_guess"], x, y, reward["kind"], reward.get("name", ""), reward.get("quantity", reward.get("kim_tien", reward.get("medals")))])
    result.append(entry)

chests_all = [c for e in result for c in e["chests"]]
summary = {"maps": len(result), "maps_with_chests": sum(1 for e in result if e["chests"]), "chests": len(chests_all),
           "gold_chests": sum(1 for c in chests_all if c["kind"] == "gold"), "medal_chests": sum(1 for c in chests_all if c["kind"] == "medals"),
           "item_chests": sum(1 for c in chests_all if c["kind"] == "item"), "total_kim_tien": sum(c.get("kim_tien", 0) for c in chests_all),
           "item_ids": sorted({c["item_id"] for c in chests_all if c["kind"] == "item"})}
out = {"source": "vqsv5.jar raw_data/4 entry 0 (at.p(): header, three matrices, chests, one more matrix)",
       "confidence": "The layout consumes all 68 blobs exactly. Rewards follow at.z(): content - 2 = -2 kim tiền x100, -1 huy chương, else item id. Place names are guessed from the blob index.",
       "summary": summary, "maps": result}
(ROOT / "prototype" / "data" / "vqsv5_map_chests.json").write_text(json.dumps(out, ensure_ascii=False, indent=1), encoding="utf-8")
with (ROOT / "asset_review" / "vqsv5_map_chests.csv").open("w", encoding="utf-8-sig", newline="") as f:
    writer = csv.writer(f)
    writer.writerow(["map", "place_guess", "x", "y", "kind", "item", "quantity_or_amount"])
    writer.writerows(rows)
print(f"Map chests: {summary['chests']} chests on {summary['maps_with_chests']} of {summary['maps']} maps ({summary['gold_chests']} kim, {summary['medal_chests']} medals, {summary['item_chests']} items)")
