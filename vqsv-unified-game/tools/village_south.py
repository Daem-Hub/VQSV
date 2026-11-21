"""Geometry shared by the walking map and illustrated southern village."""
import json


def read_village_south(data):
    return json.loads((data / "village_south.json").read_text(encoding="utf-8"))


def expand_village(village, south):
    village["height"] = south["height"]
    village["trails"].extend(south["trails"])
    village["water"] = [*village.get("water", []), *south["water"]]
    village["sceneArt"] = "assets/trails/village-south.webp"
    village["chartArt"] = "assets/trails/village-south-chart.webp"
    for kind, x, y in south["props"]:
        asset = "stones" if kind == "stone" else kind
        solid = [] if kind in ("flowers", "shrub") else [[x, y]]
        village["objects"].append({"asset": f"scenery/{asset}.png", "x": x, "y": y, "anchorX": 0.5, "solid": solid})
