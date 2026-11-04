import json
import sys
from pathlib import Path
from PIL import Image, ImageDraw


PROJECT = Path(__file__).resolve().parents[1]
BASE = PROJECT / "prototype"
name = sys.argv[1] if len(sys.argv) > 1 else 'opening_map'
if name not in ('opening_map', 'forest_map', 'forest_stream_map'):
    raise ValueError('Unknown preview map')
DATA = json.loads((BASE / "data" / f"{name}.json").read_text(encoding="utf-8"))
T = DATA["tileSize"]
sets = {}
defs = {}
for tileset in (0, 4):
    folder = BASE / "assets" / "tilesets"
    sets[tileset] = Image.open(folder / f"tileset_{tileset:02}.png").convert("RGBA")
    defs[tileset] = {x["id"]: x for x in json.loads((folder / f"tileset_{tileset:02}.json").read_text(encoding="utf-8"))}

scene = Image.new("RGBA", (DATA["width"] * T, DATA["height"] * T), "#536e3b")


def tile(tileset, tile_id, gx, gy, flip=0):
    d = defs[tileset][tile_id]
    source = sets[tileset]
    crop = source.crop((d["src_x"], d["src_y"], d["src_x"] + d["w"], d["src_y"] + d["h"]))
    if flip & 4:
        crop = crop.transpose(Image.Transpose.FLIP_LEFT_RIGHT)
    scene.alpha_composite(crop, (gx * T, (gy + 1) * T - d["h"]))

def map_object(obj):
    if 'asset' not in obj:
        tile(obj['tileset'], obj['tile'], obj['x'], obj['y'], obj.get('flip', 0))
        return
    sprite = Image.open(BASE / 'assets' / obj['asset']).convert('RGBA')
    if obj.get('scale', 1) != 1:
        sprite = sprite.resize((round(sprite.width*obj['scale']),round(sprite.height*obj['scale'])), Image.Resampling.NEAREST)
    scene.alpha_composite(sprite, (int(obj['x']*T+T-sprite.width/2), (obj['y']+1)*T-sprite.height))


ground_step = DATA["ground"].get("step", 1)
for y in range(0, DATA["height"], ground_step):
    for x in range(0, DATA["width"], ground_step):
        tile(DATA["ground"]["tileset"], DATA["ground"]["tile"], x, y)
for mark in DATA.get("groundVariation", []):
    tile(mark["tileset"], mark["tile"], mark["x"], mark["y"])
for obj in DATA.get("groundDecor", []):
    tile(obj["tileset"], obj["tile"], obj["x"], obj["y"])
for water in DATA.get('water', []):
    draw = ImageDraw.Draw(scene)
    x,y,w,h = (water[k]*T for k in ('x','y','width','height'))
    draw.rectangle((x,y,x+w-1,y+h-1),fill='#357f95')
    draw.rectangle((x+4,y,x+w-5,y+h-1),fill='#55b2bb')
    for yy in range(water['y'],water['y']+water['height']):
        for xx in range(water['x'],water['x']+water['width']):
            if (xx*7+yy*11)%3==0:
                py=yy*T+7+(xx*3+yy)%7
                draw.rectangle((xx*T+4,py,xx*T+10,py+1),fill='#a5e0d8')
for path in DATA["paths"]:
    draw = ImageDraw.Draw(scene)
    x, y, w, h = path['x'] * T, path['y'] * T, path['width'] * T, path['height'] * T
    draw.rounded_rectangle((x, y, x+w-1, y+h-1), radius=8, fill='#aa9a57')
for path in DATA['paths']:
    x, y, w, h = path['x'] * T, path['y'] * T, path['width'] * T, path['height'] * T
    draw.rounded_rectangle((x+2, y+2, x+w-3, y+h-3), radius=6, fill='#d2bd77')
for path in DATA['paths']:
    for y in range(path['y'], path['y'] + path['height']):
        for x in range(path['x'], path['x'] + path['width']):
            h = (x * 31 + y * 47) % 17
            draw.rectangle((x*T+5+h%9, y*T+5+h%7, x*T+6+h%9, y*T+5+h%7), fill='#af9b60')
if DATA.get('bridge'):
    b=DATA['bridge']; x,y,w,h=(b[k]*T for k in ('x','y','width','height'))
    draw.rectangle((x,y,x+w-1,y+h-1),fill='#694a31')
    draw.rectangle((x+2,y+3,x+w-3,y+h-4),fill='#bf8b51')
    for xx in range(x+T,x+w,T): draw.rectangle((xx,y+3,xx+1,y+h-4),fill='#916342')
    draw.rectangle((x,y+8,x+w-1,y+9),fill='#916342')
    draw.rectangle((x,y+h-10,x+w-1,y+h-9),fill='#916342')

player = DATA["entities"]["player"]
objects = sorted(DATA["objects"], key=lambda o: (o["y"], o["x"]))
for obj in objects:
    if obj["y"] < player["y"]:
        map_object(obj)
entities = ([DATA['entities']['elder']] if DATA['entities'].get('elder') else []) + DATA['entities'].get('villagers', []) + [player]
for entity in sorted(entities, key=lambda e: e['y']):
    sprite = Image.open(BASE / "assets" / entity.get("asset", "characters/kael_pixel_v2.png")).convert("RGBA")
    if entity is player:
        sprite = sprite.crop((50, 50, 310, 380)).resize((32, 40), Image.Resampling.NEAREST)
    px = entity["x"] * T + (T - sprite.width) // 2
    py = (entity["y"] + 1) * T - sprite.height
    scene.alpha_composite(sprite, (px, py))
for obj in objects:
    if obj["y"] >= player["y"]:
        map_object(obj)

fog = Image.new("RGBA", scene.size, (210, 232, 238, 13))
scene.alpha_composite(fog)
draw = ImageDraw.Draw(scene, "RGBA")
point = DATA["entities"].get("encounter")
if point:
    draw.ellipse((point["x"] * T + 5, point["y"] * T + 5, point["x"] * T + 15, point["y"] * T + 15), fill=(233, 108, 95, 190))
scene = scene.resize((scene.width * 2, scene.height * 2), Image.Resampling.NEAREST)
output = PROJECT / "asset_review" / ("opening_map_redesign.png" if name == "opening_map" else f"{name}_preview.png")
scene.save(output)
print(f"Wrote {output}")
