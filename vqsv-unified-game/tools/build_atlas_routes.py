"""Assemble atlas destinations using the native VQSV5 collision grids and authored gates."""
import json


def attach_atlas_route(data, village, desert_gate):
    scenes = json.loads((data / "atlas_routes.json").read_text(encoding="utf-8"))
    decoded = data.parents[1] / "assets" / "vqsv5" / "maps" / "json"
    maps = {village["id"]: village}
    gates = {village["id"]: {scenes[0]["id"]: desert_gate}}
    arrivals = {village["id"]: {scenes[0]["id"]: [desert_gate[0], desert_gate[1] - 1]}}
    for scene in scenes:
        name = scene["id"]
        native = json.loads((decoded / f"map_{scene['sourceMap']:02}.json").read_text())
        width, height = native["width"], native["height"]
        grid = native["collision_grid"]
        gates[name] = scene["gates"]
        arrivals[name] = {}
        for target, (x, y) in gates[name].items():
            adjacent = [(x+1,y), (x-1,y), (x,y+1), (x,y-1)]
            walkable = [(a,b) for a,b in adjacent if 0 <= a < width and 0 <= b < height and grid[b][a] in (0,3)]
            arrivals[name][target] = min(walkable, key=lambda p: abs(2*p[0]-width+1) + abs(2*p[1]-height+1))
        spawn_x, spawn_y = next(iter(arrivals[name].values()))
        prefix = f"assets/maps/atlas-field/{name}"
        maps[name] = {
            "id": name, "title": scene["title"], "atlasRegion": scene["region"],
            "illustration": f"assets/maps/atlas/{name}.webp",
            "source5": {"map": scene["sourceMap"], "base": prefix+".webp",
                        "overlay": prefix+"-over.webp" if native["overlay_layer"] else None,
                        "background": native["background"]},
            "width": width, "height": height, "tileSize": native["tile"],
            "collision": ["".join(map(str,row)) for row in grid], "objects": [],
            "entities": {"player": {"x": spawn_x, "y": spawn_y}, "villagers": []},
            "links": [],
        }
    for name, exits in gates.items():
        for target, (x, y) in exits.items():
            sx, sy = arrivals[target][name]
            maps[name]["links"].append({"x": x, "y": y, "target": target,
                                        "spawnX": sx, "spawnY": sy, "label": maps[target]["title"]})
    return [maps[scene["id"]] for scene in scenes]
