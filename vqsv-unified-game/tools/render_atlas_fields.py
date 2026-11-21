"""Render native pixel layers with the atlas region's trial palette."""
import json
from PIL import Image, ImageOps
from vqsv5_map_layers import ROOT, IMG, area, defs, parse, render

# TRIAL region palettes, in atlas order from the desert to the Red Moon.
palettes = [
    ("#321821","#da8747","#ffdf94"), ("#173154","#8cc3da","#f7ffff"),
    ("#112b32","#498776","#ede49f"), ("#22233b","#8879aa","#fbe4b4"),
    ("#112c50","#51abbb","#fbebbd"), ("#151e41","#508a9f","#9ff3e3"),
    ("#290f24","#ae5450","#ffd078"), ("#302c5d","#b8abde","#fff4d6"),
    ("#243245","#849e9a","#f3dea1"), ("#21192e","#a55254","#f7c0a1"),
]
mapping, sets = area()
out = ROOT / "prototype/assets/maps/atlas-field"
out.mkdir(exist_ok=True)
for scene in json.loads((ROOT / "prototype/data/atlas_routes.json").read_text()):
    source = scene["sourceMap"]
    native = parse(source)
    ts = mapping[source]
    with Image.open(IMG / f"{sets[ts][0]}.png") as sheet:
        for layer, suffix in [(1, ""), (3, "-over")]:
            if not native["layers"].get(layer):
                continue
            im = render(native, defs(ts), sheet, layer, layer == 1)
            dark, mid, light = palettes[scene["region"] - 2]
            color = ImageOps.colorize(im.convert("L"), dark, light, mid)
            field = Image.blend(im.convert("RGB"), color, .76)
            if layer == 3:
                field.putalpha(im.getchannel("A"))
            field.save(out / (scene["id"] + suffix + ".webp"), lossless=True)
