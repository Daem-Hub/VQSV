"""Decode VQSV5 pet combat data from data.d, using the class files of vqsv5.jar as evidence.

The jar was decompiled with CFR 0.152 and disassembled with javap (the obfuscated names collide, e.g. class `al`
has both a byte[] and a short[] called `b`, so every claim below was checked against javap field descriptors).

Layout of data/data.d (ak.j(); ad.b(byte[]) reads one section: matrix = count byte + rows of [length byte + bytes],
strings = count byte + UTF-16BE strings, byte array = length byte + bytes). 40664 of 40664 bytes are consumed:
  matrix  ak.p      skill id by [slot - 1][pet type]
  matrix  ak.d      pet table, 102 rows x 15 bytes   <- the stats
  matrix  ak.n      skill table, 49 rows [power, cost, effect%, param]
  strings ak.m, n, b, j, f, i, g, k, l  (skill labels, skill names, type names, pet names, item texts, ..., trait names/texts)
  matrix  ak.o, e, q  (o = how each pet is found, e = sprite pairs, q = evolution rows: targets and materials)
  strings ak.p     pet descriptions
  bytes   ak.a (trait values), ak.u, ak.e, ak.v
  matrix  at.b, g

Pet instance class `al` (constructor al(ak species, level, mode)):
  byte[]  B: [0] species, [2] level, [3] type (rec[6]), [4] rank (rec[5]), [5] rec[12], [6] loyalty (120 if rank > 3 else 100),
             [8..13] skill slots, [16],[17] trait ids
  short[] S: [0] HP, [1] skill value (SP), [2] max HP, [3] max SP, [5] agility, [6] attack, [7] defense
  S[2] = rec[0] + rec[7] * level / 10          S[3] = rec[1] + rec[8] * level / 10
  S[5] = rec[2] + rec[9] * level / 10          S[6] = rec[3] + rec[10] * level / 10     S[7] = rec[4] + rec[11] * level / 10
  (integer arithmetic; S[0] = S[2] and S[1] = S[3] at creation)

Battle (ak.b(z,z,al,al,int) and ak.c(...)):
  hit chance = 92 + (attacker agility - defender agility) / 2 (+ a small side bonus) percent
  damage = (atk * power * (1000 - def * 5) / 10000 - def / 10 - L / (2 * power) + 6 - (L - 1) / 10)
           * (4 * clamp(attackerLevel - defenderLevel, -10, 10) + 100) / 100, then * (bonus% - 30 if the defender guards + 100) / 100
  bonus% = +20 for a high-rank pet's loyalty (120), -20 if the pet's type differs from its side's type, +30 type advantage
  (ak.b(z,al,al)), crit x1.5 with chance ak.a[10] for trait 10, damage halved by trait 9 chance or defender skill 48.
Skills: slot 0 = skill 25 (basic attack) from level 0; slot k (1..5) unlocks at level 7k with skill p[k-1][type]
  = type * 5 + (k - 1) (ak.a(al)); using a skill costs ak.n[skill][1] of SP (ak.b(al,int)).
HP traits (al.b(ak)): trait 1 -15% HP, trait 2 +15% HP via ak.a[1] / ak.a[2].

Evolution (ak.E(), ak.d(int), ak.b(byte,byte), al.b(ak,int,int)):
  ak.q[species] = [mutation target, normal target, item, qty, item, qty, ...]  (rows with target 0 or itself are placeholders)
  ak.d(): every listed material must be in the bag, else "Tài liệu không đủ"; with a mutation target and one item 34
  ("Dị hoá tài liệu") the mutation replaces the normal evolution.
  ak.b(rank, level): level gate by rank, rank 1 needs level 15, rank 2 needs 25, rank 3 needs 30, higher ranks have none.
  Success consumes the materials (ak.c(item, qty)) and, for a mutation, one item 34; the pet keeps its level, takes the
  target species (stats are recomputed with the same formulas, full HP and SP) and its per-pet evolution counter
  (initially c12 of the species, byte[5]) drops by 1, or to 0 for a mutation. Evolving needs counter > 0 (menu check).
  ak.o[species] = how the pet is found: first byte 254 (-2) "Tiến hóa", 253 (-3) "Dị hóa", 255 (-1) quest, 251 (-5) egg, else place ids.
  Materials come from data/shop.d: [matrix shops -> item ids][short rows (little endian): prices by item id], shop 1 sells
  items 16-25 for 500 or 1000 gold; items 26-30 and 34 are not sold.

Writes prototype/data/vqsv5_pet_stats.json and asset_review/vqsv5_pet_stats.csv.
"""
import csv
import json
import os
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
SHOP_ITEM_RANGE = range(16, 26)
BUNDLED = ROOT / "sources" / "game"
SOURCE = Path(os.environ.get("VQSV_SOURCE_ROOT", str(BUNDLED if BUNDLED.exists() else Path(r"D:\TOAN BO FILE\Download\game"))))
blob = (SOURCE / "vqsv5" / "raw_data" / "data" / "data.d").read_bytes()
if int.from_bytes(blob[:2], "big") != len(blob) - 2:
    raise ValueError("Unexpected data.d length header")
data = memoryview(blob)[2:]
pos = 0


def take(size):
    global pos
    if pos + size > len(data):
        raise ValueError("Truncated data.d")
    value = data[pos:pos + size]
    pos += size
    return value


def byte():
    return int(take(1)[0])


def matrix():
    return [list(take(byte())) for _ in range(byte())]


def strings():
    return [bytes(take(byte() * 2)).decode("utf-16-be") for _ in range(byte())]


def bytes_array():
    return list(take(byte()))


skill_by_slot = matrix()
pets_raw = matrix()
skills_raw = matrix()
skill_labels, skill_names, type_names, pet_names, item_effects, item_names, place_names, trait_names, trait_texts = (strings() for _ in range(9))
# The nine string tables above are, in file order: ak.m, n, b, j, f, i, g, k, l. Their content (skill labels, skill names,
# type names, pet names, item effect texts, item names, places, traits...) is identified from the strings themselves.
(skill_labels, skill_names, type_names, pet_names, item_effects, item_names, place_names, trait_names, trait_texts) = (
    skill_labels, skill_names, type_names, pet_names, item_effects, item_names, place_names, trait_names, trait_texts)
evolutions, sprite_pairs, extra_skills = matrix(), matrix(), matrix()
descriptions = strings()
trait_values_raw, ak_u, ak_e, ak_v = bytes_array(), bytes_array(), bytes_array(), bytes_array()
at_b, at_g = matrix(), matrix()
if pos != len(data):
    raise ValueError(f"data.d not fully consumed: {pos} of {len(data)}")
assert len(pets_raw) == len(pet_names) == len(sprite_pairs) == len(descriptions) == 102 and all(len(row) == 15 for row in pets_raw)
assert len(skills_raw) == len(skill_names) == len(skill_labels) == 49 and len(type_names) == 5

signed = lambda value: value - 256 if value > 127 else value

# ---- data/shop.d (ak.f: shops -> item ids; ak.c: little-endian short rows, c[0] = price by item id) ----
shop_blob = (SOURCE / "vqsv5" / "raw_data" / "data" / "shop.d").read_bytes()
if int.from_bytes(shop_blob[:2], "big") != len(shop_blob) - 2:
    raise ValueError("Unexpected shop.d length header")
shop = shop_blob[2:]
spos = 0
def sbyte():
    global spos
    spos += 1
    return shop[spos - 1]
shops = [[sbyte() for _ in range(sbyte())] for _ in range(sbyte())]
def sshort():
    global spos
    value = shop[spos] | (shop[spos + 1] << 8)
    spos += 2
    return value - 65536 if value > 32767 else value
price_rows = []
for _ in range(signed(sbyte())):
    price_rows.append([sshort() for _ in range(signed(sbyte()))])
if spos != len(shop):
    raise ValueError(f"shop.d not fully consumed: {spos} of {len(shop)}")
sold_items = set(shops[1])
LEVEL_UNLOCKS = [0, 7, 14, 21, 28, 35]   # ak.a(al): slot thresholds
BASIC_SKILL = 25                         # slot 0
TYPE_CHART = [[2, 3], [3, 4], [4, 0], [0, 1], [1, 2]]  # ak constructor: m[type] = [type it beats (+30%), second listed type]


def stats_at(row, level):
    div = lambda a, b: int(a / b)  # Java integer division (all operands here are non-negative)
    return {
        "hp": row[0] + div(row[7] * level, 10),
        "sp": row[1] + div(row[8] * level, 10),
        "agility": row[2] + div(row[9] * level, 10),
        "attack": row[3] + div(row[10] * level, 10),
        "defense": row[4] + div(row[11] * level, 10),
    }


def slots_for(type_id):
    if type_id is None:
        return [{"level": 0, "skill_id": BASIC_SKILL}]
    return [{"level": LEVEL_UNLOCKS[0], "skill_id": BASIC_SKILL}] + [
        {"level": LEVEL_UNLOCKS[k], "skill_id": skill_by_slot[k - 1][type_id]} for k in range(1, 6)]


skills = []
for index, (name, label, row) in enumerate(zip(skill_names, skill_labels, skills_raw)):
    skills.append({"skill_id": index, "name": name, "label": label, "raw_fields": row,
                   "power": row[0], "sp_cost": row[1] if len(row) > 1 else None, "effect_chance": row[2] if len(row) > 2 else None,
                   "param": row[3] if len(row) > 3 else None,
                   "note": "power/cost used by the damage and SP rules; chance/param belong to the skill's special effect (index 26-48 are special skills)"})

# ---- evolution rows (ak.q) ----
RANK_LEVEL_GATE = {1: 15, 2: 25, 3: 30}
MUTATION_ITEM = 34
def valid_row(species, row):
    return bool(row) and row[1] not in (0, species) and len(row) >= 4 and len(row) % 2 == 0
evo_rows = {species: row for species, row in enumerate(extra_skills) if valid_row(species, row)}
normal_targets = {species: row[1] for species, row in evo_rows.items()}
mutation_targets = {species: row[0] for species, row in evo_rows.items() if row[0] > 0}
# Cross-checks: the origin table (ak.o) must agree with the targets, and the evolution counters with the chain lengths.
origin_evolution = {i for i, row in enumerate(evolutions) if row and row[0] == 254}
origin_mutation = {i for i, row in enumerate(evolutions) if row and row[0] == 253}
assert set(normal_targets.values()) == origin_evolution and len(set(normal_targets.values())) == len(normal_targets)
assert set(mutation_targets.values()) == origin_mutation
assert all(pets_raw[t][6] == pets_raw[s][6] and pets_raw[t][5] == pets_raw[s][5] + 1 for s, t in normal_targets.items())
def chain_length(species):
    count = 0
    while species in normal_targets:
        species = normal_targets[species]; count += 1
    return count
base_forms = [i for i in range(len(pets_raw)) if i not in set(normal_targets.values()) | set(mutation_targets.values())]
assert all(pets_raw[i][12] == chain_length(i) for i in base_forms), "evolution counters must equal chain lengths"
material_ids = sorted({row[k] for row in evo_rows.values() for k in range(2, len(row) - 1, 2)} | {MUTATION_ITEM})
assert all(item < len(item_names) for item in material_ids)
items = [{"item_id": item, "name": item_names[item], "description": item_effects[item] if item < len(item_effects) else "",
          "price": price_rows[0][item] if item < len(price_rows[0]) else 0, "sold": item in sold_items} for item in material_ids]

pets = []
for pet_id, (name, row, pair, description) in enumerate(zip(pet_names, pets_raw, sprite_pairs, descriptions)):
    type_id = row[6] if row[6] < len(type_names) else None
    pets.append({
        "pet_id": f"vqsv5-db-{pet_id}", "db_id": pet_id, "name": name,
        "type_id": type_id, "type": type_names[type_id] if type_id is not None else None,
        "rank": row[5], "loyalty_base": 120 if row[5] > 3 else 100,
        "growth": {"hp": [row[0], row[7]], "sp": [row[1], row[8]], "agility": [row[2], row[9]], "attack": [row[3], row[10]], "defense": [row[4], row[11]]},
        "growth_note": "stat = base + growth * level / 10 (integer division)",
        "stats_at": {str(level): stats_at(row, level) for level in (1, 5, 10, 20, 30, 50)},
        "skill_slots": slots_for(type_id),
        "evolution_points": row[12],
        "evolution": ({
            "target_pet_id": f"vqsv5-db-{normal_targets[pet_id]}",
            "mutation_target_pet_id": f"vqsv5-db-{mutation_targets[pet_id]}" if pet_id in mutation_targets else None,
            "materials": [{"item_id": evo_rows[pet_id][k], "name": item_names[evo_rows[pet_id][k]], "quantity": evo_rows[pet_id][k + 1]} for k in range(2, len(evo_rows[pet_id]) - 1, 2)],
            "min_level": RANK_LEVEL_GATE.get(row[5]), "kind": "evolution",
        } if pet_id in evo_rows else None),
        "found_by": {254: "evolution", 253: "mutation", 255: "quest", 252: "arena", 251: "egg"}.get(evolutions[pet_id][0] if evolutions[pet_id] else None, "wild"),
        "unresolved_fields": {"13": row[13], "14": row[14]},
        "raw_fields": row, "atlas_id": pair[0], "form_index": pair[1], "origin_raw": evolutions[pet_id], "evolution_row_raw": extra_skills[pet_id],
        "description": description,
    })

traits = [{"trait_id": i, "name": trait_names[i], "description": trait_texts[i], "value": signed(trait_values_raw[i])} for i in range(len(trait_names))]
out = {
    "source": "vqsv5.jar data/data.d (ak.j()), formulas from class al and class ak",
    "confidence": {
        "direct": "Stat formulas, type ids, ranks, skill unlock slots, SP costs and the damage/hit formulas are read from bytecode.",
        "unresolved": "Pet columns 13-14, the byte tables ak.u/ak.e/ak.v and the special-skill effects (ids 26-48) are exported raw or ignored. Column 12 is the initial evolution counter.",
    },
    "rules": {
        "stats": "hp = c0 + c7*L/10; sp = c1 + c8*L/10; agility = c2 + c9*L/10; attack = c3 + c10*L/10; defense = c4 + c11*L/10",
        "hit_chance_percent": "92 + (attacker.agility - defender.agility) / 2",
        "damage": "(atk*power*(1000 - def*5)/10000 - def/10 - L/(2*power) + 6 - (L-1)/10) * (4*clamp(La - Ld, -10, 10) + 100)/100, then * (bonus - 30 if guarding + 100)/100, min 1",
        "level_unlocks": LEVEL_UNLOCKS, "basic_skill_id": BASIC_SKILL, "skill_for_slot": "type*5 + (slot-1)",
        "type_chart": {type_names[t]: {"beats": type_names[TYPE_CHART[t][0]], "listed_second": type_names[TYPE_CHART[t][1]]} for t in range(5)},
        "type_advantage_percent": 30,
    },
    "evolution_rules": {
        "level_gate_by_rank": {str(k): v for k, v in RANK_LEVEL_GATE.items()}, "mutation_item_id": MUTATION_ITEM,
        "counter": "per-pet counter starts at c12 of the species, -1 per evolution, 0 after a mutation; evolving needs counter > 0",
        "consumes": "all listed materials; a mutation also consumes one mutation item (id 34) and replaces the normal evolution",
        "on_success": "level kept, target species stats recomputed with the same formulas, full HP and SP",
        "validated": {"valid_rows": len(evo_rows), "mutations": len(mutation_targets), "base_forms": len(base_forms),
                      "checks": ["targets == pets found by evolution (ak.o 254)", "mutation targets == pets found by mutation (ak.o 253)", "same type, rank + 1",
                                 "initial counter == evolution chain length for every base form"]},
    },
    "items": items,
    "type_names": type_names, "traits": traits, "skills": skills, "pets": pets,
}
(ROOT / "prototype" / "data" / "vqsv5_pet_stats.json").write_text(json.dumps(out, ensure_ascii=False, indent=1), encoding="utf-8")
with (ROOT / "asset_review" / "vqsv5_pet_stats.csv").open("w", encoding="utf-8-sig", newline="") as f:
    writer = csv.writer(f)
    writer.writerow(["pet_id", "name", "type", "rank", "L1_hp", "L1_sp", "L1_agi", "L1_atk", "L1_def", "L30_hp", "L30_sp", "L30_agi", "L30_atk", "L30_def", "raw_fields"])
    for pet in pets:
        a, b = pet["stats_at"]["1"], pet["stats_at"]["30"]
        writer.writerow([pet["pet_id"], pet["name"], pet["type"] or "", pet["rank"], a["hp"], a["sp"], a["agility"], a["attack"], a["defense"],
                         b["hp"], b["sp"], b["agility"], b["attack"], b["defense"], ";".join(map(str, pet["raw_fields"]))])
print(f"Decoded {len(pets)} pets, {len(skills)} skills, {len(traits)} traits; data.d fully consumed ({pos} bytes), shop.d ({spos} bytes)")
print(f"Evolution: {len(evo_rows)} rows ({len(mutation_targets)} mutations), {len(items)} material items; cross-checks passed")
