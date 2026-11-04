import csv
import json
import os
from pathlib import Path
from PIL import Image, ImageDraw, ImageFont

ROOT = Path(__file__).resolve().parents[1]
index_path = ROOT / "asset_review" / "visual_asset_index.csv"
out_path = ROOT / "asset_review" / "pet_roster_candidates.csv"
BUNDLED_SOURCE = ROOT / "sources" / "game"
SOURCE_ROOT = Path(os.environ.get("VQSV_SOURCE_ROOT", str(BUNDLED_SOURCE if BUNDLED_SOURCE.exists() else Path(r"D:\TOAN BO FILE\Download\game"))))
VQSV5_DATA = SOURCE_ROOT / "vqsv5" / "raw_data" / "data"

with index_path.open(encoding="utf-8-sig", newline="") as f:
    assets = list(csv.DictReader(f))
vqsv5_npc2_ids = {
    int(Path(a["relative_path"].replace("\\", "/")).stem)
    for a in assets
    if a["group"] == "vqsv5_npc" and "/npc2/" in a["relative_path"].replace("\\", "/").lower()
    and Path(a["relative_path"].replace("\\", "/")).stem.isdigit()
}

rows = []
def add(**values):
    rows.append(values)

# monN.d is a count-prefixed table. Each entry value currently matches an existing npc2 image ID.
mon_n = (VQSV5_DATA / "monN.d").read_bytes()
if len(mon_n) >= 2 and mon_n[1] == len(mon_n) - 2:
    for species_index, sprite_id in enumerate(mon_n[2:]):
        if sprite_id not in vqsv5_npc2_ids:
            raise ValueError(f"monN[{species_index}] references missing npc2 image {sprite_id}")
        add(
            catalog_id=f"vqsv5:monN/{species_index:02d}", source_game="VQSV5", source_id=f"monN[{species_index}] -> npc2/{sprite_id}",
            source_path=f"vqsv5/raw_data/data/monN.d[{species_index}] -> vqsv5/images/data/npc2/{sprite_id}.png",
            width="", height="", visual_role="monN table entry mapped to a sprite atlas ID",
            candidate_name="", form_or_evolution="",
            confidence="Table value matches an existing npc2 image; relationship to the data.d species rows is unresolved",
            notes="Multiple table entries can point to the same atlas. Do not treat atlas ID as species ID.",
            next_action="Resolve species name and per-species frame rectangle; cross-reference monType.d"
        )
else:
    raise ValueError("Unexpected monN.d header/record count; catalog was not rebuilt")

# VQSV5 npc2 files are atlases. Keep the source image ID, but do not count each file as one pet.
for a in assets:
    path = a["relative_path"].replace("/", "\\")
    if a["group"] == "vqsv5_npc" and "\\npc2\\" in path.lower():
        add(
            catalog_id=f"vqsv5:npc2/{a['asset_id']}", source_game="VQSV5", source_id=f"npc2/{a['asset_id']}",
            source_path=path, width=a["width"], height=a["height"], visual_role="Pet/monster sprite atlas; may contain multiple poses or effects",
            candidate_name="", form_or_evolution="", confidence="Asset confirmed; species mapping unverified",
            notes="One atlas is not one species. Preserve atlas coordinates when identifying frames.",
            next_action="Inspect atlas and cross-reference monN.d / monType.d / battle data"
        )

VQSV6_SCRIPT = SOURCE_ROOT / "vqsv6" / "raw_data" / "data" / "script"

def read_chs_strings(path):
    data = path.read_bytes()
    pos = 0
    row_count = int.from_bytes(data[pos:pos+2], "big", signed=True); pos += 2
    strings = []
    for row_index in range(row_count):
        count = int.from_bytes(data[pos:pos+2], "big", signed=True); pos += 2
        if count != 1:
            raise ValueError(f"Unexpected chs.mid string count at row {row_index}: {count}")
        length = data[pos]; pos += 1
        if length == 255:
            length = int.from_bytes(data[pos:pos+2], "big", signed=True); pos += 2
        if length < 0 or pos + length * 2 > len(data):
            raise ValueError(f"Invalid chs.mid string length at row {row_index}")
        strings.append("".join(chr(int.from_bytes(data[pos+i*2:pos+i*2+2], "big")) for i in range(length)))
        pos += length * 2
    if pos != len(data):
        raise ValueError(f"Unexpected chs.mid trailing bytes: {len(data)-pos}")
    return strings

v6_names = read_chs_strings(VQSV6_SCRIPT / "chs.mid")
if len(v6_names) != 636:
    raise ValueError(f"Unexpected VQSV6 localized name table length: {len(v6_names)}")

# VQSV6 image IDs in the creature-art band. Some isolated entries may be effects or parts.
for a in assets:
    if a["group"] != "vqsv6_img":
        continue
    aid = a["asset_id"].replace("img_", "")
    if not aid.isdigit() or not 500 <= int(aid) <= 621:
        continue
    role = "Standalone candidate creature image; species mapping unverified"
    status = "Visual candidate only"
    if aid == "516":
        role = "Spark/effect image; not counted as a pet candidate"
        status = "Likely battle effect"
    visual_names = {
        "506": (16, v6_names[16]), "574": (26, v6_names[26]), "520": (61, v6_names[61]),
        "514": (24, v6_names[24]), "538": (76, v6_names[76]), "540": (78, v6_names[78]),
    }
    candidate = visual_names.get(aid)
    name = candidate[1] if candidate else ""
    notes = "Image is a visual candidate only; species name is not assigned from this asset row."
    if candidate:
        record_id, source_name = candidate
        notes = f"chs.mid[{record_id}] names db record {record_id} as {source_name}; DB row -> sprite key -> image mapping is listed in the VQSV6 mapping CSV."
        role = "Named VQSV6 pet database image"
        status = "Localized DB name and record-to-image chain available"
    add(
        catalog_id=f"vqsv6:{a['asset_id']}", source_game="VQSV6", source_id=a["asset_id"],
        source_path=a["relative_path"].replace("/", "\\"), width=a["width"], height=a["height"],
        visual_role=role, candidate_name=name, form_or_evolution="", confidence=status,
        notes=notes, next_action="Match image to pet table, script references, and sprite-frame data"
    )


# VQSV6 db.mid stores 100 pet records. Field 0 is the record ID, field 1 maps to an
# element label at chs.mid[365 + value], field 17 points to sprite.mid, and field 19
# points to a same-element evolution target row. Preserve raw values alongside these.
def read_short_tables(path):
    data = path.read_bytes()
    pos = 0
    tables = []
    while pos < len(data):
        table_start = pos
        if pos + 2 > len(data):
            raise ValueError(f"Truncated table count in {path} at {pos}")
        count = int.from_bytes(data[pos:pos+2], "big", signed=True); pos += 2
        if count < 0:
            raise ValueError(f"Invalid table count in {path} at {table_start}")
        result = []
        for _ in range(count):
            if pos + 2 > len(data):
                raise ValueError(f"Truncated row size in {path} at row {len(result)}")
            width = int.from_bytes(data[pos:pos+2], "big", signed=True); pos += 2
            if width < 0 or pos + width * 2 > len(data):
                raise ValueError(f"Invalid short-row width in {path} at row {len(result)}")
            result.append(list(int.from_bytes(data[pos+i*2:pos+i*2+2], "big", signed=True) for i in range(width)))
            pos += width * 2
        tables.append({"table_index": len(tables), "byte_offset": table_start, "rows": result})
    return tables

db_tables = read_short_tables(VQSV6_SCRIPT / "db.mid")
sprite_tables = read_short_tables(VQSV6_SCRIPT / "sprite.mid")
if len(db_tables) != 9 or len(sprite_tables) != 1:
    raise ValueError(f"Unexpected numeric table counts: db.mid={len(db_tables)}, sprite.mid={len(sprite_tables)}")
db_rows = db_tables[0]["rows"]
sprite_rows = sprite_tables[0]["rows"]
sprite_by_id = {r[0]: r for r in sprite_rows if r}
vqsv6_asset_ids = {
    int(a["asset_id"].replace("img_", "")) for a in assets
    if a["group"] == "vqsv6_img" and a["asset_id"].startswith("img_") and a["asset_id"][4:].isdigit()
}
map_rows = []
for record in db_rows:
    if len(record) <= 17:
        raise ValueError(f"VQSV6 db record {record[0]} lacks sprite reference field 17")
    record_id, sprite_id = record[0], record[17]
    element_id = record[1]
    if not 0 <= element_id <= 6 or 365 + element_id >= len(v6_names):
        raise ValueError(f"VQSV6 db record {record_id} has unknown element ID {element_id}")
    element_name = v6_names[365 + element_id]
    sprite_row = sprite_by_id.get(sprite_id)
    if not sprite_row or len(sprite_row) != 2:
        raise ValueError(f"VQSV6 db record {record_id} has unresolved sprite row {sprite_id}")
    image_id = sprite_row[1]
    if image_id not in vqsv6_asset_ids:
        raise ValueError(f"VQSV6 sprite row {sprite_id} references missing image img_{image_id}")
    if not 0 <= record_id < len(v6_names) or not v6_names[record_id]:
        raise ValueError(f"No localized name in chs.mid for VQSV6 DB record {record_id}")
    db_name = v6_names[record_id]
    evolution_target_row = record[19] if len(record) > 19 else -1
    evolution_target = db_rows[evolution_target_row] if 0 <= evolution_target_row < len(db_rows) else None
    evolution_target_id = evolution_target[0] if evolution_target else ""
    evolution_target_name = v6_names[evolution_target_id] if evolution_target else ""
    evolution_note = f"Tiến hóa → {evolution_target_name} (DB {evolution_target_id})" if evolution_target else ""
    add(
        catalog_id=f"vqsv6:db/{record_id}", source_game="VQSV6", source_id=f"db[{record_id}] -> sprite[{sprite_id}] -> img_{image_id}",
        source_path=f"vqsv6/raw_data/data/script/db.mid[{record_id}] -> sprite.mid[{sprite_id}] -> vqsv6/images/data/img/img_{image_id}.png",
        width="", height="", visual_role=f"VQSV6 pet; element {element_name}; image linked through sprite table",
        candidate_name=db_name, form_or_evolution=evolution_note,
        confidence="DB name aligns by record ID in chs.mid; field 1 maps to element label; field 17 links sprite key; field 19 points to a related same-element pet record when nonnegative",
        notes="Fields 0, 1, 17 and 19 are mapped; evolution target is interpreted as a zero-based DB row index. Other raw field meanings remain open.",
        next_action="Decode the remaining 19 field meanings and the 8 companion tables; check story alias against source name"
    )
    map_rows.append({"db_id": record_id, "db_name": db_name, "element_id": element_id, "element_name": element_name, "sprite_id": sprite_id, "image_id": f"img_{image_id}", "evolution_target_row_index": evolution_target_row if evolution_target else "", "evolution_target_db_id": evolution_target_id, "evolution_target_name": evolution_target_name, "raw_field_20": record[20], "raw_field_21": record[21], "raw_field_22": record[22], "raw_fields": ";".join(map(str, record))})
tables_json = {
    "source": "vqsv6/raw_data/data/script/db.mid and sprite.mid",
    "table_notes": "Raw signed 16-bit values. Table 0 has 100 pet records (IDs 10-109), each 23 values wide. Decoded: field 0 is DB ID; field 1 is element ID and resolves to chs.mid[365 + value]; field 17 is sprite.mid key; field 19 is a zero-based row index to a same-element evolution target when nonnegative. Evolution-screen bytecode also reads table 0 column 2 and transforms the returned value through game/b.t; its exact row-index mapping and zero sentinel remain under review. Other fields and the eight auxiliary tables remain undecoded.",
    "db_mid_tables": db_tables,
    "sprite_mid_tables": sprite_tables,
    "chs_names": v6_names,
    "name_alignment_note": "For DB IDs 10-109, chs.mid at the same index contains the localized pet name; image join uses DB field 17 -> sprite.mid key -> image ID.",
    "field_schema_findings": {
        "table_0": {
            "field_0": {"name": "db_id", "confidence": "direct; value is the record ID used for the chs.mid name lookup"},
            "field_1": {"name": "element_id", "confidence": "strong; values 0-6 resolve to the seven consecutive element labels in chs.mid[365:372]", "label_lookup": "chs.mid[365 + value]"},
            "field_17": {"name": "sprite_key", "confidence": "direct; matches the first column of a sprite.mid row"},
            "field_19": {"name": "evolution_target_row_index", "confidence": "strong; 38 nonnegative values point to related records of the same element; -1 means no target in this field", "target_lookup": "db.mid table 0 at row index value"},
            "field_2_usage": {"name": "unresolved raw value", "confidence": "bytecode evidence; evolution-screen code calls aq.a(0, local_value, 2), subtracts 1, and indexes game/b.t; nearby UI text describes a minimum-level restriction, but local_value mapping and the zero sentinel are not confirmed", "source": "game/h.bh()V -> aq.a(BSB)S -> game/b.t"},
            "unresolved_fields": [*range(2, 17), 18, 20, 21, 22]
        }
    },
    "element_id_labels": {str(i): v6_names[365 + i] for i in range(7)},
}
tables_path = ROOT / "asset_review" / "vqsv6_db_sprite_numeric_tables.json"
tables_path.write_text(json.dumps(tables_json, ensure_ascii=False, indent=2), encoding="utf-8")
map_csv = ROOT / "asset_review" / "vqsv6_pet_db_sprite_map.csv"
with map_csv.open("w", encoding="utf-8-sig", newline="") as f:
    writer = csv.DictWriter(f, fieldnames=["db_id", "db_name", "element_id", "element_name", "sprite_id", "image_id", "evolution_target_row_index", "evolution_target_db_id", "evolution_target_name", "raw_field_20", "raw_field_21", "raw_field_22", "raw_fields"])
    writer.writeheader(); writer.writerows(map_rows)
if len(db_rows) != 100 or len({r["image_id"] for r in map_rows}) != 100:
    raise ValueError("Unexpected VQSV6 pet database or non-unique image mapping")

evolution_rows = [r for r in map_rows if r["evolution_target_db_id"] != ""]
if len(evolution_rows) != 38 or any(db_rows[r["evolution_target_row_index"]][1] != r["element_id"] for r in evolution_rows):
    raise ValueError("VQSV6 evolution targets do not match the expected same-element links")
evolution_csv = ROOT / "asset_review" / "vqsv6_pet_evolution_links.csv"
with evolution_csv.open("w", encoding="utf-8-sig", newline="") as f:
    fields = ["db_id", "db_name", "element_name", "image_id", "target_row_index", "target_db_id", "target_name", "target_image_id", "field_20", "field_21", "field_22"]
    writer = csv.DictWriter(f, fieldnames=fields)
    writer.writeheader()
    for r in evolution_rows:
        target_record = db_rows[r["evolution_target_row_index"]]
        target_sprite_id = target_record[17]
        target_image_id = f"img_{sprite_by_id[target_sprite_id][1]}"
        writer.writerow({"db_id": r["db_id"], "db_name": r["db_name"], "element_name": r["element_name"], "image_id": r["image_id"], "target_row_index": r["evolution_target_row_index"], "target_db_id": r["evolution_target_db_id"], "target_name": r["evolution_target_name"], "target_image_id": target_image_id, "field_20": r["raw_field_20"], "field_21": r["raw_field_21"], "field_22": r["raw_field_22"]})

# Export a stable, namespaced roster contract for the unified game's runtime/data layer.
# VQSV5 data.d names and atlas/form pairs are decoded by decode_vqsv5_pet_tables.py.
pet_roster = {
    "schema_version": 1,
    "status": "source roster; VQSV5 and VQSV6 names and source graphics are linked; combat fields remain undecoded",
    "id_policy": "pet_id is namespaced by source game and source DB ID; never merge records by numeric ID or visual similarity alone",
    "starter_pet_ids": ["vqsv6-db-16", "vqsv6-db-26", "vqsv6-db-61"],
    "story_links": [{
        "story_name": "Lôi Miêu",
        "pet_id": "vqsv6-db-78",
        "status": "prototype candidate; not a confirmed source-game identity",
        "reason": "VQSV6 record is Điện Miêu and its element is Điện; the story name is similar, but no canonical alias field exists in the source tables."
    }],
    "records": [],
    "unresolved_source_references": [],
    "unresolved_source_tables": []
}
for row in map_rows:
    raw = db_rows[next(i for i, source_row in enumerate(db_rows) if source_row[0] == row["db_id"])]
    target_pet_id = f"vqsv6-db-{row['evolution_target_db_id']}" if row["evolution_target_db_id"] != "" else None
    evolution = None
    if target_pet_id:
        target = db_rows[row["evolution_target_row_index"]]
        category = target[2]
        material_index = raw[20] + 12
        materials = db_tables[3]["rows"]
        if category not in (1, 2, 3) or not 0 <= material_index < len(materials):
            raise ValueError(f"Unresolved VQSV6 evolution requirements for {row['db_id']}")
        material_name_id = materials[material_index][0]
        if not 0 <= material_name_id < len(v6_names):
            raise ValueError(f"Unresolved material name for {row['db_id']}")
        evolution = {"target_pet_id": target_pet_id, "confidence": "source game evolution screen", "kind": "mutation" if category == 3 else "evolution", "minimum_level": (12, 30, 5)[category - 1], "material": {"table_row": material_index, "name": v6_names[material_name_id], "quantity": raw[21]}, "requirements_source": "VQSV_FIX_2.7 game/k.java bp; game/i.java u"}
    pet_roster["records"].append({
        "pet_id": f"vqsv6-db-{row['db_id']}",
        "display_name": row["db_name"],
        "element": row["element_name"],
        "source_ids": {"game": "VQSV6", "db_id": row["db_id"], "sprite_key": row["sprite_id"], "image_id": row["image_id"]},
        "source_asset": f"vqsv6/images/data/img/{row['image_id']}.png",
        "evolution": evolution,
        "field_2_level_lookup": {"raw_value": raw[2], "meaning": "unresolved; referenced by evolution-screen code and transformed through game/b.t"},
        "raw_fields": raw,
        "combat_stats": None,
        "verification": {"name_element_sprite": "source-table join", "evolution_target": "source game evolution screen; unified game balance not determined"}
    })
vqsv5_catalog = json.loads((ROOT / "prototype" / "data" / "vqsv5_pet_catalog.json").read_text(encoding="utf-8"))
vqsv5_animations = json.loads((ROOT / "prototype" / "data" / "vqsv5_pet_animations.json").read_text(encoding="utf-8"))
for record in vqsv5_catalog["records"]:
    atlas_id = str(record["animation"]["atlas_id"])
    atlas = vqsv5_animations["atlases"].get(atlas_id)
    if not atlas or any(seq < 0 or seq >= len(atlas["sequences"]) for seq in record["animation"]["sequence_ids"]):
        raise ValueError(f"Unresolved VQSV5 atlas/form sequences for {record['pet_id']}")
    pet_roster["records"].append(record)
    ids = record["source_ids"]
    add(catalog_id=record["pet_id"], source_game="VQSV5", source_id=f"data.d pet[{ids['db_id']}] -> npc2/{ids['atlas_id']} form {ids['form_index']}", source_path=record["source_asset"], width="", height="", visual_role="Pet database row with name and source animation form", candidate_name=record["display_name"], form_or_evolution="", confidence="Name and atlas/form from data.d; t.class uses the same source row to select atlas and animation group", notes="Stats, element and evolution remain undecoded; source rows are preserved independently.", next_action="Decode battle stats, element and evolution from the original tables")
vqsv5_mon_n = list(mon_n[2:])
for mon_index, atlas_id in enumerate(vqsv5_mon_n):
    pet_roster["unresolved_source_references"].append({
        "source_reference_id": f"vqsv5-monn-{mon_index:02d}",
        "source_game": "VQSV5",
        "source_table": "monN.d",
        "source_index": mon_index,
        "atlas_id": atlas_id,
        "source_asset": f"vqsv5/images/data/npc2/{atlas_id}.png",
        "species_name": None,
        "frame_mapping": None,
        "status": "atlas link confirmed; species identity and frame mapping unresolved; do not use as a selectable pet"
    })
mon_type_blob = (VQSV5_DATA / "monType.d").read_bytes()
mon_type_count = int.from_bytes(mon_type_blob[:2], "big")
mon_type_values = list(mon_type_blob[2:])
if len(mon_type_values) != mon_type_count:
    raise ValueError(f"monType.d declares {mon_type_count} bytes but contains {len(mon_type_values)}")
unique_mon_n_atlases = list(dict.fromkeys(vqsv5_mon_n))
pet_roster["unresolved_source_tables"].append({
    "source_game": "VQSV5",
    "source_table": "monType.d",
    "declared_value_count": mon_type_count,
    "raw_values": mon_type_values,
    "observed_shape": {
        "distinct_monN_atlases": len(unique_mon_n_atlases),
        "count_equals_five_times_distinct_atlases": mon_type_count == 5 * len(unique_mon_n_atlases),
        "interpretation": "shape coincidence only; no five-field-per-atlas grouping or gameplay meaning is confirmed"
    },
    "lookup_evidence": "No direct monType.d string reference was found in the scanned VQSV5 JAR class constants; loader and semantics remain unresolved."
})
roster_path = ROOT / "prototype" / "data" / "pet_roster_v1.json"
roster_path.write_text(json.dumps(pet_roster, ensure_ascii=False, indent=2), encoding="utf-8")

# petArea.mid rows contain a five-value header followed by repeated four-value pet references.
# The first value in each four-value block is a zero-based row index into db.mid table 0.
pet_area_tables = read_short_tables(VQSV6_SCRIPT / "petArea.mid")
if len(pet_area_tables) != 1:
    raise ValueError(f"Unexpected petArea.mid table count: {len(pet_area_tables)}")
pet_area_rows = []
for area_row_index, area_row in enumerate(pet_area_tables[0]["rows"]):
    if len(area_row) == 3 and area_row[-1] == -1:
        continue
    if len(area_row) < 5 or (len(area_row) - 5) % 4:
        raise ValueError(f"Unexpected petArea.mid row shape at row {area_row_index}: {len(area_row)} values")
    header = area_row[:5]
    for tuple_index, offset in enumerate(range(5, len(area_row), 4)):
        pet_row_index, aux0, aux1, aux2 = area_row[offset:offset+4]
        if not 0 <= pet_row_index < len(db_rows):
            raise ValueError(f"petArea row {area_row_index} references invalid pet DB row index {pet_row_index}")
        pet_record = db_rows[pet_row_index]
        sprite_id = pet_record[17]
        sprite_row = sprite_by_id.get(sprite_id)
        if not sprite_row:
            raise ValueError(f"petArea row {area_row_index} pet row {pet_row_index} has no sprite mapping")
        element_id = pet_record[1]
        pet_area_rows.append({
            "pet_area_row": area_row_index,
            "tuple_index": tuple_index,
            **{f"header_{i}": value for i, value in enumerate(header)},
            "pet_db_row_index": pet_row_index,
            "db_id": pet_record[0],
            "db_name": v6_names[pet_record[0]],
            "element_id": element_id,
            "element_name": v6_names[365 + element_id],
            "sprite_id": sprite_id,
            "image_id": f"img_{sprite_row[1]}",
            "tuple_aux_0": aux0,
            "tuple_aux_1": aux1,
            "tuple_aux_2": aux2,
        })
pet_area_csv = ROOT / "asset_review" / "vqsv6_pet_area_roster.csv"
with pet_area_csv.open("w", encoding="utf-8-sig", newline="") as f:
    fields = ["pet_area_row", "tuple_index", *[f"header_{i}" for i in range(5)], "pet_db_row_index", "db_id", "db_name", "element_id", "element_name", "sprite_id", "image_id", "tuple_aux_0", "tuple_aux_1", "tuple_aux_2"]
    writer = csv.DictWriter(f, fieldnames=fields)
    writer.writeheader(); writer.writerows(pet_area_rows)
tables_json["pet_area_mid_tables"] = pet_area_tables
tables_json["pet_area_structure_note"] = "petArea.mid has 119 rows. Empty rows have width 3 and end in -1; other rows have five unlabeled header values followed by repeated four-value tuples. The tuple's first value is treated as a zero-based row index into db.mid table 0: all 111 values fall within 0-99, while interpreting them as DB IDs would fail for values such as 1, 4, and 9. The remaining three tuple values and header semantics remain undecoded; confirm the lookup in gameplay code before assigning final field names. Flattened joins are in vqsv6_pet_area_roster.csv."
tables_path.write_text(json.dumps(tables_json, ensure_ascii=False, indent=2), encoding="utf-8")

# Contact sheet uses the same verified db -> sprite -> image mapping as the CSV.
columns, cell_w, cell_h, label_h = 10, 104, 86, 20
contact = Image.new("RGB", (columns * cell_w, ((len(map_rows)+columns-1)//columns)*(cell_h+label_h)), "#25252b")
draw = ImageDraw.Draw(contact)
try:
    font = ImageFont.truetype("arial.ttf", 11)
except OSError:
    font = ImageFont.load_default()
for i, row in enumerate(map_rows):
    db_id, image_id = row["db_id"], int(row["image_id"].removeprefix("img_"))
    image = Image.open(VQSV6_SCRIPT.parent.parent.parent / "images" / "data" / "img" / f"img_{image_id}.png").convert("RGBA")
    image.thumbnail((cell_w-8, cell_h-8), Image.Resampling.NEAREST)
    tile = Image.new("RGBA", (cell_w-8, cell_h-8), "#3a3a42")
    tile.alpha_composite(image, ((tile.width-image.width)//2, (tile.height-image.height)//2))
    x, y = (i % columns)*cell_w, (i // columns)*(cell_h+label_h)
    contact.paste(tile.convert("RGB"), (x+4,y+4))
    draw.text((x+4,y+cell_h), f"db {db_id} | img {image_id}", fill="white", font=font)
contact_path = ROOT / "asset_review" / "vqsv6_pet_db_contact.png"
contact.save(contact_path)

story = [
    ("story:kael_thunder_cat", "Lôi Miêu", "Juvenile companion; scarred malformed right ear", "Cực Lôi Hoàng Hổ", "Story canon; source-game image not mapped"),
    ("story:flame_dragon", "Viêm Long Tí Nị", "Rescue and recruit at Hỏa Sơn Bất Diệt", "Not stated", "Story canon; source-game image not mapped"),
    ("story:snow_spirit", "Tuyết Y Linh Thú", "Awaken and cleanse at Hồ Băng Cổ Xưa", "Not stated", "Story canon; recruitability not specified"),
    ("story:tide_whale", "Thương Hải Thần Cự Kình", "Guardian of the final egg in Atlantis", "Not stated", "Story canon; ally status distinct from party pet"),
    ("story:obsidian_dragon", "Nham Bạo Long", "Boran's engineered boss at Miệng Núi Lửa Hắc Diệu", "Not stated", "Story canon; boss, not presumed catchable"),
    ("story:mirror_angel", "Kính Giới Thiên Sứ", "Seraphina's mirror boss at Tầng Mây Khởi Nguyên", "Not stated", "Story canon; boss, not presumed catchable"),
    ("story:dark_ancient_dragon", "Cổ Long Hắc Ám", "Chapter 1 tower boss", "Not stated", "Story canon; boss, not presumed catchable"),
]
for cid, name, role, evolution, confidence in story:
    add(
        catalog_id=cid, source_game="NEW STORY", source_id=cid, source_path="", width="", height="",
        visual_role=role, candidate_name=name, form_or_evolution=evolution, confidence=confidence,
        notes="Defined by the approved story draft; gameplay values and art mapping are still open.",
        next_action="Keep as story records; map to existing art only after evidence"
    )

v6_starters = [
    ("vqsv6:starter_penguin", "Thủy thủ cánh cụt PenGuin", "Starter pet; Water element", "vqsv6/text/vqsv6__data__event__scene_1.mid.txt:42-46", "db[61] chs name Thủy thủ chim cánh cụt -> sprite[137] -> img_520; direct name match"),
    ("vqsv6:starter_frog", "Ô Lá Ếch Frog", "Starter pet; Wood element", "vqsv6/text/vqsv6__data__event__scene_1.mid.txt:48-49", "db[26] chs name Diệp Tán Oa -> sprite[102] -> img_574; alias matches leaf-frog description"),
    ("vqsv6:starter_dragon", "Rồng Bảo Bối", "Starter pet; Fire element", "vqsv6/text/vqsv6__data__event__scene_1.mid.txt:51-53", "db[16] chs name Long bảo bối -> sprite[92] -> img_506; direct name match"),
]
for cid, name, role, source_path, note in v6_starters:
    add(
        catalog_id=cid, source_game="VQSV6 TEXT", source_id=cid, source_path=source_path, width="", height="",
        visual_role=role, candidate_name=name, form_or_evolution="", confidence="Starter name and element from event text; matching localized name at same DB ID plus sprite/image mapping",
        notes=note, next_action="Keep starter name alias and element as story metadata; decode DB field meanings before stat normalization"
    )

fields = ["catalog_id", "source_game", "source_id", "source_path", "width", "height", "visual_role", "candidate_name", "form_or_evolution", "confidence", "notes", "next_action"]
with out_path.open("w", encoding="utf-8-sig", newline="") as f:
    writer = csv.DictWriter(f, fieldnames=fields)
    writer.writeheader()
    writer.writerows(rows)

print(f"Wrote {out_path} with {len(rows)} records")
print(f"VQSV5 table entries: {sum(r['catalog_id'].startswith('vqsv5:monN/') for r in rows)}")
print(f"VQSV5 atlas files: {sum(r['catalog_id'].startswith('vqsv5:npc2/') for r in rows)}")
print(f"VQSV6 source image assets: {sum(r['catalog_id'].startswith('vqsv6:img_') for r in rows)}")
print(f"VQSV6 pet DB rows linked to images: {sum(r['catalog_id'].startswith('vqsv6:db/') for r in rows)}")
print(f"Decoded numeric tables: db.mid={len(db_tables)}, sprite.mid={len(sprite_tables)}")
print(f"VQSV6 petArea associations (row-index join): {len(pet_area_rows)} rows, {len({r['pet_db_row_index'] for r in pet_area_rows})} unique pets")
print(f"VQSV6 petArea CSV: {pet_area_csv}")
print(f"VQSV6 linked evolution targets: {len(evolution_rows)} rows; {evolution_csv}")
print(f"Unified-game pet roster: {len(map_rows)} VQSV6 and {len(vqsv5_catalog['records'])} VQSV5 namespaced records; {roster_path}")
print(f"VQSV6 DB contact sheet: {contact_path}")
print(f"Raw VQSV6 numeric tables: {tables_path}")
print(f"Story-defined records: {sum(r['source_game']=='NEW STORY' for r in rows)}")

