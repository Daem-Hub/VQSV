"""Build original exploration fields and connect them to the main atlas graph."""
import json
from math import hypot


def field_cells(scene):
    width, height = scene["size"]
    ground = {}
    for x, y, radius in scene["plazas"]:
        for cy in range(max(0, y-radius), min(height, y+radius+1)):
            for cx in range(max(0, x-radius), min(width, x+radius+1)):
                if hypot(cx-x, cy-y) <= radius:
                    ground[cx, cy] = "plaza"
    for path in scene["paths"]:
        for (ax, ay), (bx, by) in zip(path["points"], path["points"][1:]):
            dx, dy = bx-ax, by-ay
            radius = path["width"] / 2
            for y in range(max(0, int(min(ay,by)-radius)), min(height, int(max(ay,by)+radius)+1)):
                for x in range(max(0, int(min(ax,bx)-radius)), min(width, int(max(ax,bx)+radius)+1)):
                    t = max(0, min(1, ((x-ax)*dx + (y-ay)*dy)/(dx*dx + dy*dy)))
                    if hypot(x-ax-t*dx, y-ay-t*dy) <= radius:
                        ground.setdefault((x,y), path["kind"])
    return ground


def attach_atlas_expansion(expansion, main_maps):
    maps = {map_["id"]: map_ for map_ in main_maps}
    gates = {map_["id"]: {} for map_ in main_maps}
    added = []
    for scene in expansion["scenes"]:
        name = scene["id"]
        width, height = scene["size"]
        ground = field_cells(scene)
        _, lx, ly = scene["landmark"]
        for x in range(lx-1, lx+2):
            for y in (ly-1, ly):
                ground.pop((x,y), None)
        gates[name] = scene["gates"]
        art = f"assets/maps/frontier/{name}"
        map_ = {
            "id": name, "title": scene["title"], "atlasRegion": scene["region"],
            "width": width, "height": height, "tileSize": 20, "objects": [],
            "fieldArt": art+".webp", "illustration": art+"-preview.webp",
            "collision": ["".join("0" if (x,y) in ground else "1" for x in range(width)) for y in range(height)],
            "entities": {"villagers": []}, "links": [],
        }
        added.append(map_)
        maps[name] = map_
    for origin, gate, target in expansion["connections"]:
        gates[origin][target] = gate

    def landing(name, gate):
        x, y = gate
        map_ = maps[name]
        occupied = {(link["x"], link["y"]) for link in map_["links"]}
        occupied.update(tuple(cell) for cell in gates[name].values())
        occupied.update((c["x"], c["y"]) for c in map_.get("chests", []))
        occupied.update((npc["x"], npc["y"]) for npc in map_["entities"].get("villagers", []))
        if map_["collision"][y][x] not in "03":
            raise ValueError(f"{name}: blocked gate at {gate}")
        for a, b in ((x+1,y), (x-1,y), (x,y+1), (x,y-1)):
            if 0 <= a < map_["width"] and 0 <= b < map_["height"] and map_["collision"][b][a] in "03" and (a,b) not in occupied:
                return a, b
        raise ValueError(f"{name}: no walkable arrival at {gate}")

    arrivals = {name: {target: landing(name, gate) for target, gate in exits.items()} for name, exits in gates.items()}
    for name, exits in gates.items():
        for target, (x,y) in exits.items():
            sx, sy = arrivals[target][name]
            maps[name]["links"].append({"x":x, "y":y, "target":target, "spawnX":sx, "spawnY":sy, "label":maps[target]["title"]})
    for map_ in added:
        x, y = next(iter(arrivals[map_["id"]].values()))
        map_["entities"]["player"] = {"x":x, "y":y}
    return added
