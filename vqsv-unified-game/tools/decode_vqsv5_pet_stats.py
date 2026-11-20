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

Traits (al.e(int), the al constructor, ak.a[] values; class al byte[16] and byte[17] are the two trait slots):
  mode -1 (fixed gift): slot0 = 2*type + 4, slot1 = 2
  mode  0 (wild, random): slot0 = 2*type + 3 + rand(2), slot1 = rand(3)
  mode  1 (wild when the lead pet knows skill 39): slot0 = 2*type + 3 + (rand(100) < 70 ? 1 : 0), slot1 = rand(100) < 70 ? 2 : rand(2)
  Evolution does not change them (al.b(ak,int,int) leaves byte[16..17] alone), so they are properties of the individual.
  Effects: 1/2 max HP -15% / +15% (ak.b(al,int)); 4 heals 6% of max HP at the end of the holder's turn (ak.a(z));
  5-8 and 11-12 shift the chance of specific status effects landing on the holder by +20 / -20 (ak.b(al,al) and the skill code);
  9 gives the holder's attacks a 20% chance of halved damage, 10 a boosted-damage chance (the stored value is -20, so as coded it
  can never fire; the description says it should); 3 is described as taking more burn/rot damage (+20%) but the source tests
  al.a(3) (a passive skill) instead of al.e(3), so as coded it never fires either.

Special skills (ids 26-48, held in the two extra slots al.b[14], al.b[15]; ak.n rows, ak.c(), ak.b(z,al,al,int), ak.e(al) and the end-of-round code):
  26-30 are usable moves (the menu lists slots 8-15); 31-48 are passive and only tested with al.a(id). A pet learns one from a skill book
  (bag item = skill id + 9, i.e. items 35-57, ak.h(al)); the books are sold for medals by shop 2 (shop.d price_rows[1], by position in the
  shop list) and each needs a trainer rank ak.u[id - 26]. Pets from scripted teams start with passives 30 and 48.
  Statuses (al.b = id, al.c = turns, 7 = none; one at a time): 0 Thạch Hóa (2 turns, cannot act, damage taken -30%), 2 Tê Liệt (4 turns, 5 with
  skill 32; loses 10% HP each round), 3 Mị Hoặc (4, cannot flee, easier to capture), 4 Giam Cầm (4, cannot flee, switch pets or use items),
  5 Hủ Thực (4, loses h*h + 4 HP each round with h = skill id + 1, or + n[31][0] = 3 with skill 31), 6 Phong Bế (4, cannot use skills above
  tier 3). Type skills carry [power, cost, chance, status] so Thực Vật -> 5, Kim Loại -> 0, Phi Hành -> 4, Mãnh Thú -> 2, Hải Dương -> 3.

Where the skill books come from (ak.c(): items 32 "Bảo rương" and 33 "Cái chìa khóa", ak.f()/win code, data/box.d):
  winning a battle gives one Bảo rương with 50% chance (while fewer than 99 are held). The key (item 33) is sold by the general shop (50 kim).
  Using either with the other in the bag spends one of each and rolls: tier = rand(100) < 70 -> 0, < 85 -> 1, < 95 -> 2, else 3, then rand(100)
  against the cumulative chances of box.d table [tier] ([item, cumulative %] pairs; 255 = empty). Table 2 holds the skill books 35, 41 and 51-55;
  table 3 the evolution materials 26-28, 30, 31; table 1 the mutation item 34 (only if none is held), the box and key; table 0 shop consumables.
  A result already held at 99 is empty. Script instructions can also grant items (opcode 18 of data/event) and gold (opcode 21, amount * 100);
  field chests grant gold * 100, medals or one item (at.java chest handler).

Trainer rank and medals (as.b(boolean), at.g(int) = event opcode 32, ak.g()): every new species obtained adds 1 to the species count k[103] and to
  the counter k[104]; the trainer rank l[3] starts at 1 and becomes 2, 3, 4, 5 once the species count passes 4, 9, 14, 29. Handing in at the
  registry gives k[104] medals (kim "huy chương") and resets it. A skill book needs rank >= ak.u[skill - 26] to be bought or learned.
Script sale (data/event map 43 script 7, see decode_vqsv5_events.py): 1500 kim buys one mutation item (34), at most one held at a time.

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
sold_items = set(shops[1]) | {33}          # item 33 (the chest key) is sold by the general shop, shops[0]
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

# ---- special skills 26-48 (see docstring) ----
SPECIAL = {
    26: {"kind": "damage_loyalty", "loyalty_percent": skills_raw[26][3], "chance": skills_raw[26][2], "blocked_by_passive": 40,
         "evidence": "ak.c(): after the hit, rand(100) < n[26][2] lowers the target's loyalty by n[26][3]% of it unless it knows skill 40"},
    27: {"kind": "heal", "amount": skills_raw[27][0] * 10, "evidence": "ak.c(): heals n[27][0] * 10 HP, no hit roll"},
    28: {"kind": "heal", "amount": skills_raw[28][0] * 10, "evidence": "ak.c(): heals n[28][0] * 10 HP, no hit roll"},
    29: {"kind": "sacrifice", "attack_multiplier": skills_raw[29][0], "uses_all_sp": True,
         "evidence": "ak.c(): damage = attack * n[29][0] + current SP, then the user's SP drops to 0"},
    30: {"kind": "seal", "status": 6, "chance": skills_raw[30][2], "blocks_tier_above": 3,
         "evidence": "ak.b(z,al,al,int) status 6; ak.c(): a sealed pet cannot choose a skill of tier above 3"},
    31: {"kind": "rot_boost", "skill_offset": skills_raw[31][0], "default_offset": 1, "evidence": "z.h = skill id + (knows 31 ? n[31][0] : 1); rot loses h*h + 4 per round"},
    32: {"kind": "paralysis_turns", "turns": 5, "default_turns": 4, "evidence": "ak.b(z,al,al,int): status 2 lasts 5 turns when the attacker knows 32"},
    33: {"kind": "defense_percent", "percent": skills_raw[33][0], "type": "Kim Loại hệ", "evidence": "damage method: defender type 1 and knows 33 -> damage * n[33][0] / 100"},
    34: {"kind": "sp_drain", "percent": skills_raw[34][0], "type": "Phi Hành hệ", "source_quirk": "n[34][0] is 0, so the drain coded in ak.c() (target max SP * n[34][0] / 100) is 0",
         "evidence": "ak.c(): attacker type 2 and knows 34 -> target SP -= max SP * n[34][0] / 100"},
    35: {"kind": "sp_cost_discount", "type": "Hải Dương hệ", "evidence": "ak.b(al,int): cost = cost * n[skill][0] / 100 when the pet is type 4 and knows 35 (n[skill][0] is the skill's power)"},
    36: {"kind": "exp_bonus", "percent": 100, "evidence": "exp * (100 + 100) / 100 per skill, 36 and 37 add up"},
    37: {"kind": "exp_bonus", "percent": 200, "evidence": "exp * (100 + 200) / 100 per skill, 36 and 37 add up"},
    38: {"kind": "revive", "hp_percent": skills_raw[38][0], "chance": skills_raw[38][1], "label_chance": 70,
         "source_quirk": "the label promises 70% but the code tests rand(100) < n[38][1] = 60",
         "evidence": "when a pet falls: rand(100) < n[38][1] -> back with n[38][0]% of max HP"},
    39: {"kind": "lucky_encounters", "evidence": "ak.au(): wild pets are created with trait mode 1 when the lead pet knows 39"},
    40: {"kind": "loyalty_lock", "evidence": "loyalty is not lost on defeat or when sent out, and skill 26 cannot lower it"},
    41: {"kind": "capture_no_flee", "evidence": "a failed capture does not make the target flee (ak: knows 41 -> keep fighting)"},
    42: {"kind": "flee_always", "evidence": "fleeing always succeeds even with status 3 or 4"},
    **{43 + t: {"kind": "terrain", "type_id": t, "evidence": "the pet's side counts as standing on its own type's terrain (ak.b(z,al): z.i = type)"} for t in range(5)},
    48: {"kind": "damage_halved_chance", "chance": skills_raw[48][0], "evidence": "damage method: defender knows 48 and rand(100) < n[48][0] -> damage >> 1"},
}
for sid, special in SPECIAL.items():
    skills[sid]["special"] = special
STATUS_RULES = {
    "single_status": "a combatant can carry one status; a new one only lands while none is active (ak.b(z,al,al,int): al3.b != 7 -> no)",
    "statuses": {
        "0": {"name": "Thạch Hóa", "turns": 2, "effect": "cannot act, damage taken -30%", "type": "Kim Loại hệ", "trait_delta": [5, 6]},
        "2": {"name": "Tê Liệt", "turns": 4, "effect": "loses 10% of current HP each round", "type": "Mãnh Thú hệ", "trait_delta": [11, 12]},
        "3": {"name": "Mị Hoặc", "turns": 4, "effect": "cannot flee, easier to capture", "type": "Hải Dương hệ"},
        "4": {"name": "Giam Cầm", "turns": 4, "effect": "cannot flee, switch pets or use items", "type": "Phi Hành hệ", "trait_delta": [7, 8]},
        "5": {"name": "Hủ Thực", "turns": 4, "effect": "loses h*h + 4 HP each round, h = skill id + 1 (+3 with skill 31)", "type": "Thực Vật hệ", "trait_delta": [11, 12]},
        "6": {"name": "Phong Bế", "turns": 4, "effect": "cannot use skills of tier above 3", "type": "skill 30"},
    },
    "damage_each_round_boost_trait": 3,
}
SHOP2_BOOKS = {item: price_rows[1][i] for i, item in enumerate(shops[2]) if 35 <= item <= 57}
assert set(SHOP2_BOOKS) == set(range(35, 58))
skill_books = [{"item_id": sid + 9, "skill_id": sid, "name": item_names[sid + 9], "description": item_effects[sid + 9], "price_medals": SHOP2_BOOKS[sid + 9],
                "trainer_rank": ak_u[sid - 26], "passive": sid > 30} for sid in range(26, 49)]
assert all(book["name"] and book["trainer_rank"] > 0 for book in skill_books)

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
material_ids = sorted({row[k] for row in evo_rows.values() for k in range(2, len(row) - 1, 2)} | {MUTATION_ITEM, 31, 32, 33})
assert all(item < len(item_names) for item in material_ids)
items = [{"item_id": item, "name": item_names[item], "description": item_effects[item] if item < len(item_effects) else "",
          "price": price_rows[0][item] if item < len(price_rows[0]) else 0, "sold": item in sold_items} for item in material_ids]

# ---- data/box.d: treasure box tables (see docstring) ----
box_blob = (SOURCE / "vqsv5" / "raw_data" / "data" / "box.d").read_bytes()
if int.from_bytes(box_blob[:2], "big") != len(box_blob) - 2:
    raise ValueError("Unexpected box.d length header")
box = box_blob[2:]
box_pos = 1
box_tables = []
for _ in range(box[0]):
    length = box[box_pos]; row = list(box[box_pos + 1:box_pos + 1 + length]); box_pos += 1 + length
    pairs = [(row[i], row[i + 1]) for i in range(0, len(row), 2)]
    assert pairs[-1] == (255, 100) and [p[1] for p in pairs] == sorted(p[1] for p in pairs)
    previous = 0; entries = []
    for item, upto in pairs:
        entries.append({"item_id": None if item == 255 else item, "chance": upto - previous, "cumulative": upto}); previous = upto
    box_tables.append(entries)
assert box_pos == len(box) and len(box_tables) == 4
table_books = {e["item_id"] for e in box_tables[2] if e["item_id"] is not None}
assert table_books <= set(range(35, 58)) and len(table_books) == 7
chest_rules = {
    "chest_item": 32, "key_item": 33, "key_price": price_rows[0][33], "win_drop_chance": 50, "stack_max": 99,
    "tier_thresholds": [70, 85, 95], "tiers": box_tables,
    "mutation_item": MUTATION_ITEM, "mutation_item_only_if_none_held": True,
    "evidence": "ak.c() items 32/33; the victory code rolls rand(100) < 50 for item 32; box.d is read as pairs [item, cumulative %]",
    "other_sources": ["data/event opcode 18 (item), opcode 21 (gold x100)", "field chests (gold x100, medals or one item)"],
}

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

TRAIT_EFFECTS = {
    1: {"kind": "hp_percent", "value": -15, "evidence": "ak.b(al,int) adds n * a[1] / 100 to max HP"},
    2: {"kind": "hp_percent", "value": 15, "evidence": "ak.b(al,int) adds n * a[2] / 100 to max HP"},
    3: {"kind": "dot_taken_percent", "value": 20, "applies": "burn and rot damage over time", "source_quirk": "tests al.a(3) instead of al.e(3), never fires as coded"},
    4: {"kind": "regen_percent", "value": 6, "evidence": "ak.a(z): heal max HP * a[4] / 100 at the end of the holder's turn"},
    5: {"kind": "status_chance_delta", "value": 20, "status": "stone", "evidence": "ak.b(al,al): status 0 chance + a[5]"},
    6: {"kind": "status_chance_delta", "value": -20, "status": "stone", "evidence": "ak.b(al,al): status 0 chance + a[6]"},
    7: {"kind": "status_chance_delta", "value": 20, "status": "confinement", "evidence": "ak.b(al,al): status 4 chance + a[7]"},
    8: {"kind": "status_chance_delta", "value": -20, "status": "confinement", "evidence": "ak.b(al,al): status 4 chance + a[8]"},
    9: {"kind": "damage_halved_chance", "value": 20, "evidence": "damage method: e(9) and rand(100) < a[9] -> damage >>= 1"},
    10: {"kind": "damage_boost_chance", "value": 20, "source_value": -20, "evidence": "damage method: e(10) and rand(100) < a[10] -> damage * 15 / 10", "source_quirk": "a[10] is -20, so as coded the test can never pass"},
    11: {"kind": "status_chance_delta", "value": 20, "status": "paralysis and rot", "evidence": "ak.b(al,al): status 2 or 5 chance + a[11]"},
    12: {"kind": "status_chance_delta", "value": -20, "status": "paralysis and rot", "evidence": "ak.b(al,al): status 2 or 5 chance + a[12]"},
}
traits = [{"trait_id": i, "name": trait_names[i], "description": trait_texts[i], "value": signed(trait_values_raw[i]), "effect": TRAIT_EFFECTS.get(i)} for i in range(len(trait_names))]
# Every pair of type traits must be [positive-chance, negative-chance] style values and every type has two traits (2t+3, 2t+4).
assert all(2 * t + 4 < len(trait_names) for t in range(5)) and len(trait_names) == 13
out = {
    "source": "vqsv5.jar data/data.d (ak.j()), formulas from class al and class ak",
    "confidence": {
        "direct": "Stat formulas, type ids, ranks, skill unlock slots, SP costs and the damage/hit formulas are read from bytecode.",
        "unresolved": "Pet columns 13-14 and the byte tables ak.e/ak.v are exported raw or ignored; the special skills 26-48 and statuses are read from ak.c(), ak.b() and the end-of-round code (see special and status_rules). Column 12 is the initial evolution counter.",
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
    "trait_rules": {
        "slots": 2,
        "modes": {"fixed": "slot0 = 2*type + 4, slot1 = 2", "random": "slot0 = 2*type + 3 + rand(2), slot1 = rand(3)",
                  "lucky": "slot0 = 2*type + 3 + (rand(100) < 70 ? 1 : 0), slot1 = rand(100) < 70 ? 2 : rand(2)"},
        "wild_mode": "random; lucky when the lead pet knows skill 39 (Hạnh Vận Chi Tinh)",
        "type_pairs": {type_names[t]: [2 * t + 3, 2 * t + 4] for t in range(5)},
        "slot1_traits": {"0": "none", "1": "weak constitution (max HP -15%)", "2": "strong constitution (max HP +15%)"},
        "halve_chance": 20, "boost_chance": 20, "dot_boost_percent": 20, "regen_percent": 6, "status_delta": 20, "hp_percent": 15,
        "inherited_on_evolution": True,
    },
    "chest_rules": chest_rules,
    "trainer_rules": {"rank_species_thresholds": [5, 10, 15, 30], "meaning": "rank 2/3/4/5 once species obtained >= 5/10/15/30 (k[103] > 4, 9, 14, 29)",
                      "medals": "one medal per new species since the last hand-in (k[104]); handed in at the registry NPC",
                      "book_gate": "a skill book needs trainer rank >= skill_books[].trainer_rank to be bought or learned"},
    "script_sales": [{"item_id": MUTATION_ITEM, "price": 1500, "limit": 1, "currency": "kim tiền",
                      "evidence": "data/event map 43 script 7: choice, 1500 kim check (opcode 21 -15), item 34 given (opcode 18), 'only one at a time'"}],
    "status_rules": STATUS_RULES,
    "skill_books": skill_books,
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
