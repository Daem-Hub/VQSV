"""Extract VQSV5 pet names and their source atlas/form indices from data.d."""
import csv
import json
import os
from pathlib import Path

project = Path(__file__).resolve().parents[1]
bundled = project / "sources" / "game"
source = Path(os.environ.get("VQSV_SOURCE_ROOT", str(bundled if bundled.exists() else Path(r"D:\TOAN BO FILE\Download\game"))))
blob = (source / "vqsv5" / "raw_data" / "data" / "data.d").read_bytes()
if len(blob) < 2 or int.from_bytes(blob[:2], "big") != len(blob) - 2:
    raise ValueError("Unexpected VQSV5 data.d length header")
data = memoryview(blob)[2:]
offset = 0

def take(size):
    global offset
    if offset + size > len(data):
        raise ValueError("Truncated VQSV5 data.d")
    value = data[offset:offset + size]
    offset += size
    return value

def byte():
    return int(take(1)[0])

def matrix():
    rows = []
    for _ in range(byte()):
        size = byte()
        rows.append(list(take(size)))
    return rows

def strings():
    values = []
    for _ in range(byte()):
        length = byte()
        values.append(bytes(take(length * 2)).decode("utf-16-be"))
    return values

# ak.j() reads these sections in order; ak.j[] holds the pet names and
# ak.e[][] the [atlas ID, form index] pairs used by t.class to draw a pet.
initial_tables = [matrix() for _ in range(3)]
string_tables = [strings() for _ in range(9)]
pet_relations, sprite_pairs, pet_unknown = matrix(), matrix(), matrix()
descriptions = strings()
names = string_tables[3]
raw_attributes = initial_tables[1]
if not (len(names) == len(sprite_pairs) == len(descriptions) == len(raw_attributes) == 102):
    raise ValueError("VQSV5 pet tables do not have the expected aligned record count")

records = []
for species_id, (name, pair, attributes, description) in enumerate(zip(names, sprite_pairs, raw_attributes, descriptions)):
    if len(pair) != 2:
        raise ValueError(f"Unexpected sprite pair for VQSV5 pet {species_id}: {pair}")
    atlas_id, form_index = pair
    records.append({
        "pet_id": f"vqsv5-db-{species_id}",
        "display_name": name,
        "element": None,
        "source_ids": {"game": "VQSV5", "db_id": species_id, "atlas_id": atlas_id, "form_index": form_index},
        "source_asset": f"vqsv5/images/data/npc2/{atlas_id}.png",
        "animation": {"atlas_id": atlas_id, "sequence_ids": [form_index * 3 + i for i in range(3)]},
        "description": description,
        "raw_fields": attributes,
        "combat_stats": None,
        "verification": {"name_sprite": "data.d ak.j and ak.e row index; t.class loads atlas and uses form index times three", "element_and_stats": "unresolved"}
    })

output = project / "prototype" / "data" / "vqsv5_pet_catalog.json"
output.write_text(json.dumps({"source": "vqsv5/raw_data/data/data.d", "records": records, "notes": "Distinct source rows remain separate even if names and sprite pairs repeat."}, ensure_ascii=False, indent=2), encoding="utf-8")
csv_path = project / "asset_review" / "vqsv5_pet_db_sprite_map.csv"
with csv_path.open("w", encoding="utf-8-sig", newline="") as f:
    writer = csv.DictWriter(f, fieldnames=["db_id", "name", "atlas_id", "form_index", "animation_sequences", "description", "raw_fields"])
    writer.writeheader()
    for record in records:
        ids = record["source_ids"]
        writer.writerow({"db_id": ids["db_id"], "name": record["display_name"], "atlas_id": ids["atlas_id"], "form_index": ids["form_index"], "animation_sequences": ";".join(map(str, record["animation"]["sequence_ids"])), "description": record["description"], "raw_fields": ";".join(map(str, record["raw_fields"]))})
print(f"Extracted {len(records)} VQSV5 pet records to {output}")
print(f"VQSV5 pet mapping: {csv_path}")
