import json
from pathlib import Path


PROJECT = Path(__file__).resolve().parents[1]
prototype = PROJECT / "prototype"
opening_map = json.loads((prototype / "data" / "opening_map.json").read_text(encoding="utf-8"))
forest_map = json.loads((prototype / "data" / "forest_map.json").read_text(encoding="utf-8"))
stream_map = json.loads((prototype / "data" / "forest_stream_map.json").read_text(encoding="utf-8"))
opening_map["links"] = [{"x": 23, "y": 10, "target": forest_map["id"], "spawnX": 1, "spawnY": 10, "label": forest_map["title"]}]
tile_defs = {
    str(index): json.loads((prototype / "assets" / "tilesets" / f"tileset_{index:02}.json").read_text(encoding="utf-8"))
    for index in (0, 4)
}
pet_roster = json.loads((prototype / "data" / "pet_roster_v1.json").read_text(encoding="utf-8"))
vqsv5_animations = json.loads((prototype / "data" / "vqsv5_pet_animations.json").read_text(encoding="utf-8"))
original_skills = json.loads((prototype / "data" / "original_skills.json").read_text(encoding="utf-8"))
vqsv6_effects = json.loads((prototype / "data" / "vqsv6_effect_sprites.json").read_text(encoding="utf-8"))
vqsv6_stats = json.loads((prototype / "data" / "vqsv6_pet_stats.json").read_text(encoding="utf-8"))
vqsv5_stats = json.loads((prototype / "data" / "vqsv5_pet_stats.json").read_text(encoding="utf-8"))
vqsv5_terrain = json.loads((prototype / "data" / "vqsv5_map_terrain.json").read_text(encoding="utf-8"))
vqsv5_wild = {str(m["map"]): {"place": m["place_guess"], "terrain_type_id": m["terrain_type_id"], **m["wild"]} for m in vqsv5_terrain["maps"] if m.get("wild")}
wild_maps = [json.loads(path.read_text(encoding="utf-8")) for path in sorted((prototype / "data").glob("wild_*.json"))]
# Travel order east of the stream: lakeside -> meadow -> highland -> canyon -> ashfield -> skyridge.
order = ["ryan-lakeside", "ryan-meadow", "ryan-highland", "ryan-canyon", "ryan-ashfield", "ryan-skyridge"]
wild_maps.sort(key=lambda m: order.index(m["id"]))
payload = json.dumps({"map": opening_map, "maps": {m["id"]: m for m in (opening_map, forest_map, stream_map, *wild_maps)}, "tileDefs": tile_defs, "petRoster": pet_roster, "vqsv5Animations": vqsv5_animations, "originalSkills": original_skills, "vqsv6Effects": vqsv6_effects, "vqsv6Stats": vqsv6_stats, "vqsv5Stats": vqsv5_stats, "vqsv5Wild": vqsv5_wild}, ensure_ascii=False, separators=(",", ":"))
(prototype / "data" / "bootstrap.js").write_text(f"window.VQSVBootstrap = {payload};\n", encoding="utf-8")
print("Wrote prototype/data/bootstrap.js")
