import json
from pathlib import Path

from village_south import expand_village, read_village_south
from build_atlas_routes import attach_atlas_route
from atlas_expansion import attach_atlas_expansion
from atlas_wild import attach_atlas_wild, attach_frontier_wild
from import_tiled import attach_tiled_maps


PROJECT = Path(__file__).resolve().parents[1]
prototype = PROJECT / "prototype"
opening_map = json.loads((prototype / "data" / "opening_map.json").read_text(encoding="utf-8"))
forest_map = json.loads((prototype / "data" / "forest_map.json").read_text(encoding="utf-8"))
stream_map = json.loads((prototype / "data" / "forest_stream_map.json").read_text(encoding="utf-8"))
arrive = forest_map.get("arrivals", {}).get(opening_map["id"], [1, 10])          # the forest cell next to its exit back to the village
# Doors into the two shops (kind="door") come from design_opening_village.py; the east gate leads to the forest.
opening_map["links"] = [link for link in opening_map.get("links", []) if link.get("kind")] + [
    {"x": 23, "y": 10, "target": forest_map["id"], "spawnX": arrive[0], "spawnY": arrive[1], "label": forest_map["title"]}]
interiors = json.loads((prototype / "data" / "village_interiors.json").read_text(encoding="utf-8"))
# "Khảo sát Suối Sấm" asks Kael to inspect the middle of the stream crossing. The real VQSV5 stream map has no
# separate bridge object, so mark the walkable cell nearest the map centre (collision 0 or 3 = shallow water).
if "bridge" not in stream_map and stream_map.get("collision"):
    w, h, cells = stream_map["width"], stream_map["height"], stream_map["collision"]
    walk = [(x, y) for y in range(h) for x in range(w) if str(cells[y][x]) in ("0", "3")]
    cx, cy = (w - 1) / 2, (h - 1) / 2
    sx, sy = min(walk, key=lambda c: ((c[0] - cx) ** 2 + (c[1] - cy) ** 2, c))
    stream_map["surveyPoint"] = {"x": sx, "y": sy}
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
order = ["ryan-lakeside", "ryan-meadow", "ryan-highland", "ryan-canyon", "ryan-ashfield", "ryan-skyridge",
         "ryan-pinewind", "ryan-inkmarsh", "ryan-stormplain", "ryan-sanctum"]   # branch maps from tools/design_extra_wild_maps.py
wild_maps.sort(key=lambda m: order.index(m["id"]))
village_south = read_village_south(prototype / "data")
expand_village(opening_map, village_south)
atlas_maps = attach_atlas_route(prototype / "data", opening_map, village_south["desertGate"])
atlas_maps = attach_atlas_wild(atlas_maps, vqsv6_stats, vqsv5_stats, pet_roster)
expansion = json.loads((prototype / "data" / "atlas_expansion.json").read_text(encoding="utf-8"))
frontier_maps = attach_atlas_expansion(expansion, [forest_map, *atlas_maps])
frontier_maps = attach_frontier_wild(frontier_maps, [forest_map, *atlas_maps], vqsv6_stats, vqsv5_stats, pet_roster)
all_maps = {m["id"]: m for m in (opening_map, forest_map, stream_map, *interiors, *wild_maps, *atlas_maps, *frontier_maps)}
# Maps drawn in Tiled (tiled/maps/, read by tools/import_tiled.py into data/tiled_maps.json) join last, with their way-back links.
for map_id in attach_tiled_maps(all_maps, prototype / "data"):
    print(f"Tiled map: {map_id}")
payload = json.dumps({"map": opening_map, "maps": all_maps, "atlasRegions": expansion["regions"], "tileDefs": tile_defs, "petRoster": pet_roster, "vqsv5Animations": vqsv5_animations, "originalSkills": original_skills, "vqsv6Effects": vqsv6_effects, "vqsv6Stats": vqsv6_stats, "vqsv5Stats": vqsv5_stats, "vqsv5Wild": vqsv5_wild}, ensure_ascii=False, separators=(",", ":"))
(prototype / "data" / "bootstrap.js").write_text(f"window.VQSVBootstrap = {payload};\n", encoding="utf-8")
print("Wrote prototype/data/bootstrap.js")
