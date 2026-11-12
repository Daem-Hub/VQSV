"""Decode the VQSV5 event scripts (data/event) and list what they give: items, kim tiền, pets, medals, boss fights, quest updates.

data/event is a resource of 68 blobs (one per map, ad.b(String,int) skips `index` entries of [short length][bytes]). A blob is
[script count][ [short length][bytes] ... ]. Scripts are run by at.u() (class at, the big switch over d[n2][c[n2]], 53 opcodes).
Instruction lengths come from the pc advances in the bytecode (javap of at.u()) plus the variable-length forms below; a few forms were
fixed empirically because the decompiled offsets are shifted by one (28, 51) or depend on a mode byte (13). The result parses 385 of the 386
scripts exactly to their end with every opcode known; the remaining script is reported and skipped.

Opcode layout (op, then operands; "d1" is the byte after the opcode):
  fixed length: 1:4 2:5 3:4 4:4 5:5 6:7 8:5 11:4 15:3 16:4 17:4 18:4 21:3 22:6 23:4 24:3 25:3 29:5 30:7 31:4 32:3 33:3 35:4 36:4 37:8 38:5
                39:4 40:4 41:5 42:4 43:4 44:3 45:3 46:3 47:3 48:3 49:5 50:3 52:3 53:3
  13  quest update: 4 bytes when d1 == 2 ([13,2,id,mode], mode 0 = main quest, 2 = side quest), else 2
  12  choice menu: [12, count, count x (length byte + length UTF-16 chars), count x 2-byte offsets]
  19  boss battle: 2 + d1 bytes; pairs (boss row of data/battle/boss.d, level) from d2
  26, 27  2 + d1 bytes: 26 gives a pet [26, n, species, level, mode...] (ak.b(species, level, mode, -1)); 27 is a pet check
  28  dialogue: [28, 3, speaker, count hi, count lo, 2 x count bytes of UTF-16BE text] = 5 + 2 x count
  51  centered text: [51, mode, ?, count, 2 x count bytes]: 4 + 2 x count
  18  items: [18, d1, item, quantity]; a negative quantity removes (checks) the item (at.f()); 21  kim tiền: [21, d1, amount] x 100
  other ops are flag / movement / camera / state instructions that do not give anything.

Writes prototype/data/vqsv5_event_rewards.json and asset_review/vqsv5_event_rewards.csv.
"""
import csv
import json
import os
import runpy
import io
import contextlib
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
BUNDLED = ROOT / "sources" / "game"
SOURCE = Path(os.environ.get("VQSV_SOURCE_ROOT", str(BUNDLED if BUNDLED.exists() else Path(r"D:\TOAN BO FILE\Download\game"))))
DATA = SOURCE / "vqsv5" / "raw_data" / "data"
with contextlib.redirect_stdout(io.StringIO()):
    stats = runpy.run_path(str(Path(__file__).with_name("decode_vqsv5_pet_stats.py")))
ITEM_NAMES, PET_NAMES, PLACE_NAMES, SKILL_NAMES = stats["item_names"], stats["pet_names"], stats["place_names"], stats["skill_names"]

blob = (DATA / "event").read_bytes()
blobs, pos = [], 1
while pos + 2 <= len(blob):
    size = (blob[pos] << 8) | blob[pos + 1]; pos += 2
    blobs.append(blob[pos:pos + size]); pos += size
assert pos == len(blob) and len(blobs) == 68

FIXED = {1: 4, 2: 5, 3: 4, 4: 4, 5: 5, 6: 7, 8: 5, 11: 4, 15: 3, 16: 4, 17: 4, 18: 4, 21: 3, 22: 6, 23: 4, 24: 3, 25: 3, 29: 5, 30: 7, 31: 4, 32: 3,
         33: 3, 35: 4, 36: 4, 37: 8, 38: 5, 39: 4, 40: 4, 41: 5, 42: 4, 43: 4, 44: 3, 45: 3, 46: 3, 47: 3, 48: 3, 49: 5, 50: 3, 52: 3, 53: 3}


def length(sc, pc):
    op = sc[pc]
    if op in FIXED:
        return FIXED[op]
    if op == 13:
        return 4 if sc[pc + 1] == 2 else 2
    if op == 12:
        count, p = sc[pc + 1], pc + 2
        for _ in range(count):
            p += sc[p] * 2 + 1
        return p - pc + 2 * count
    if op in (26, 27):
        return sc[pc + 1] + 2
    if op == 28:
        return 5 + 2 * ((sc[pc + 3] << 8) | sc[pc + 4])
    if op == 51:
        return 4 + 2 * sc[pc + 3]
    if op == 19:
        return 2 + sc[pc + 1]
    return None


def signed(v):
    return v - 256 if v > 127 else v


def text_of(sc, pc, op):
    start, count = (pc + 5, (sc[pc + 3] << 8) | sc[pc + 4]) if op == 28 else (pc + 4, sc[pc + 3])
    raw = bytes(sc[start:start + 2 * count])
    return raw.decode("utf-16-be", errors="replace").replace("\ufffd", "?")


boss_rows = None
boss_blob = (DATA / "battle" / "boss.d").read_bytes()
bp = 3
boss_rows = []
for _ in range(boss_blob[2]):
    n = boss_blob[bp]; boss_rows.append(list(boss_blob[bp + 1:bp + 1 + n])); bp += 1 + n

scripts, failed = [], []
for map_index, bl in enumerate(blobs):
    if len(bl) <= 1:
        continue
    count, p = bl[0], 1
    for script_index in range(count):
        size = (bl[p] << 8) | bl[p + 1]; p += 2
        sc = bl[p:p + size]; p += size
        pc, ops, ok = 0, [], True
        while pc < len(sc):
            n = length(sc, pc) if pc + 4 < len(sc) + 4 else None
            try:
                n = length(sc, pc)
            except IndexError:
                n = None
            if n is None or pc + n > len(sc):
                ok = False
                break
            ops.append((pc, n)); pc += n
        if not ok or pc != len(sc):
            failed.append({"map": map_index, "script": script_index, "length": len(sc), "stopped_at": pc})
            continue
        entry = {"map": map_index, "script": script_index, "place_guess": PLACE_NAMES[map_index] if map_index < len(PLACE_NAMES) else None,
                 "items": [], "kim_tien": 0, "pets": [], "bosses": [], "quests": [], "text_lines": 0, "first_text": None}
        for pc, n in ops:
            op = sc[pc]
            if op == 18:
                item, qty = sc[pc + 2], signed(sc[pc + 3])
                if item < len(ITEM_NAMES):
                    entry["items"].append({"item_id": item, "name": ITEM_NAMES[item], "quantity": qty})
            elif op == 21:
                entry["kim_tien"] += signed(sc[pc + 2]) * 100
            elif op == 26 and n >= 5:
                species = sc[pc + 2]
                if species < len(PET_NAMES):
                    entry["pets"].append({"pet_id": f"vqsv5-db-{species}", "name": PET_NAMES[species], "level": sc[pc + 3]})
            elif op == 19:
                entry["bosses"].append([{"boss_row": sc[pc + 2 + 2 * q], "level": sc[pc + 3 + 2 * q]} for q in range(sc[pc + 1] // 2)])
            elif op == 13 and sc[pc + 1] == 2:
                entry["quests"].append({"id": sc[pc + 2], "kind": "main" if sc[pc + 3] == 0 else "side" if sc[pc + 3] == 2 else f"mode {sc[pc + 3]}"})
            elif op in (28, 51):
                entry["text_lines"] += 1
                if entry["first_text"] is None:
                    entry["first_text"] = text_of(sc, pc, op)[:60]
        scripts.append(entry)

exchange = next(s for s in scripts if (s["map"], s["script"]) == (43, 7))
assert exchange["kim_tien"] == -1500 and [i["item_id"] for i in exchange["items"]] == [34], exchange     # the 1500 kim -> mutation item exchange
total = sum(len(bl) > 1 and bl[0] for bl in blobs)
assert total == 386 and len(failed) <= 1, (total, failed)
rewards = [s for s in scripts if s["items"] or s["kim_tien"] or s["pets"]]
summary = {
    "scripts": total, "parsed": len(scripts), "failed": failed,
    "items_given": sum(1 for s in scripts for i in s["items"] if i["quantity"] > 0),
    "skill_books_given": [{"map": s["map"], "script": s["script"], **i} for s in scripts for i in s["items"] if i["quantity"] > 0 and 35 <= i["item_id"] <= 57],
}
out = {"source": "vqsv5.jar data/event, instruction set of class at (u())",
       "confidence": "Lengths from bytecode plus empirical fixes (see decoder docstring); 385 of 386 scripts parse exactly. Item/gold/pet operands are read from the opcodes that call at.f() / ak.e() / ak.b(); the speaker/place names are guesses.",
       "summary": summary, "boss_rows": boss_rows, "scripts": scripts}
(ROOT / "prototype" / "data" / "vqsv5_event_rewards.json").write_text(json.dumps(out, ensure_ascii=False, indent=1), encoding="utf-8")
with (ROOT / "asset_review" / "vqsv5_event_rewards.csv").open("w", encoding="utf-8-sig", newline="") as f:
    writer = csv.writer(f)
    writer.writerow(["map", "script", "place_guess", "items", "kim_tien", "pets", "bosses", "quests", "first_text"])
    for s in rewards + [x for x in scripts if x["bosses"] and x not in rewards]:
        writer.writerow([s["map"], s["script"], s["place_guess"], "; ".join(f"{i['name']} x{i['quantity']}" for i in s["items"]), s["kim_tien"],
                         "; ".join(f"{p['name']} L{p['level']}" for p in s["pets"]), len(s["bosses"]), len(s["quests"]), s["first_text"]])
print(f"Event scripts: {len(scripts)}/{total} parsed, {len(rewards)} with rewards, {len(summary['skill_books_given'])} skill-book grants, failed {failed}")
