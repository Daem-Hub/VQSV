"""Build the two-way walking route from the illustrated atlas scenes."""
import json


def attach_atlas_route(data, village):
    scenes = json.loads((data / "atlas_routes.json").read_text(encoding="utf-8"))
    maps = []
    columns = (0, 4, 8, 12, 16, 20, 23)
    previous = village
    exit_x, exit_y = return_x, return_y = 10, 14
    for scene in scenes:
        rows = scene["road"]
        centers = []
        for i, (left, right) in enumerate(zip(columns, columns[1:])):
            for x in range(left, right):
                t = (x - left) / (right - left)
                centers.append(round(rows[i] + t * (rows[i+1] - rows[i])))
        centers.append(rows[-1])

        # Keep a shared walkable row between columns even on a narrow, curved bridge.
        collision = [["1"] * 24 for _ in range(18)]
        for x, center in enumerate(centers):
            last = centers[max(0, x - 1)]
            low = min(last, center - scene["halfWidth"])
            high = max(last, center + scene["halfWidth"])
            for y in range(low, high + 1):
                collision[y][x] = "0"
        result = {
            "id": scene["id"], "title": scene["title"], "atlasRegion": scene["region"],
            "illustration": f"assets/maps/atlas/{scene['id']}.webp",
            "width": 24, "height": 18, "tileSize": 20,
            "collision": ["".join(row) for row in collision], "objects": [],
            "entities": {"player": {"x": 1, "y": centers[1]}, "villagers": []},
            "links": [{"x": 0, "y": centers[0], "target": previous["id"],
                       "spawnX": return_x, "spawnY": return_y, "label": previous["title"]}],
        }
        previous["links"].append({"x": exit_x, "y": exit_y, "target": result["id"],
                                  "spawnX": 1, "spawnY": centers[1], "label": result["title"]})
        maps.append(result)
        previous = result
        exit_x, exit_y = 23, centers[-1]
        return_x, return_y = 22, centers[-2]
    return maps
