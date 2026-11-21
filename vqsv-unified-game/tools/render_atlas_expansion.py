"""Paint the original frontier fields at the game's native 20 px tile scale."""
import json
from pathlib import Path
from random import Random
from PIL import Image, ImageDraw
from atlas_expansion import field_cells

ROOT = Path(__file__).resolve().parents[1] / "prototype"
T = 20
# TRIAL palettes and terrain. Gameplay geometry lives in atlas_expansion.json.
PALETTE = {
    "jade": dict(sea="#244c53", shallow="#408477", bank="#315c48", floor="#5b874e", light="#92b960", shade="#203f37",
                 path="#c1aa73", edge="#856d4f", speck="#e5c88c", roof="#427344", foliage="#67934f", gleam="#cfda81"),
    "coral": dict(sea="#185574", shallow="#39959a", bank="#62b5ae", floor="#6ea569", light="#98c77e", shade="#285c62",
                  path="#e3c593", edge="#a5835f", speck="#fae0a7", roof="#ab5979", foliage="#578d66", gleam="#f09aa8"),
    "copper": dict(sea="#6d3e43", shallow="#98594b", bank="#a7634a", floor="#bd8154", light="#d89a60", shade="#61383c",
                   path="#e3b880", edge="#9e684e", speck="#ffdb9d", roof="#a56247", foliage="#738553", gleam="#f4bb72"),
}


def paint(scene):
    width, height = scene["size"]
    rng = Random(scene["id"])
    c = PALETTE[scene["theme"]]
    ground = field_cells(scene)
    image = Image.new("RGB", (width*T, height*T), c["sea"])
    draw = ImageDraw.Draw(image)
    dry = {(x,y) for (x,y), kind in ground.items() if kind != "bridge"}
    radius = 2 if scene["theme"] == "coral" else 4
    shore = {(x+dx,y+dy) for x,y in dry for dy in range(-radius,radius+1)
             for dx in range(-radius,radius+1) if dx*dx+dy*dy <= radius*radius}
    fringe = {(x+dx,y+dy) for x,y in shore for dx,dy in [(0,0),(0,1),(0,-1),(1,0),(-1,0)]}
    ornaments = []
    for y in range(height):
        for x in range(width):
            left, top = x*T, y*T
            cell = x,y
            on_land = cell in shore
            terrain = ground.get(cell)
            base = c["floor"] if on_land else c["shallow"] if cell in fringe else c["sea"]
            draw.rectangle((left,top,left+19,top+19), fill=base)
            if on_land and (x,y+1) not in shore:
                draw.rectangle((left,top+11,left+19,top+19), fill=c["shade"])
                draw.line((left,top+11,left+19,top+11), fill=c["light"], width=2)
                for a in (2,9,15):
                    draw.line((left+a,top+14,left+a-1,top+18),fill=c["bank"])
            if terrain in ("road","plaza"):
                draw.rectangle((left,top,left+19,top+19),fill=c["path"])
                for dx,dy,edge in [(1,0,(19,0,19,19)),(-1,0,(0,0,0,19)),(0,1,(0,19,19,19)),(0,-1,(0,0,19,0))]:
                    if (x+dx,y+dy) not in ground:
                        draw.line(tuple(v + (left if i % 2 == 0 else top) for i,v in enumerate(edge)),fill=c["edge"])
            elif terrain == "bridge":
                draw.rectangle((left,top,left+19,top+19),fill="#9b6656")
                for offset in range(1,20,5):
                    draw.line((left,top+offset,left+19,top+offset),fill="#d3a575")
                for dx,dy,line in [(1,0,(19,0,19,19)),(-1,0,(0,0,0,19)),(0,1,(0,19,19,19)),(0,-1,(0,0,19,0))]:
                    if (x+dx,y+dy) not in ground:
                        draw.line(tuple(v + (left if i % 2 == 0 else top) for i,v in enumerate(line)),fill="#644451",width=2)
                continue
            highlight = c["speck"] if terrain else c["light"] if on_land else c["bank"]
            for _ in range(3):
                px, py = left+rng.randrange(1,16), top+rng.randrange(2,18)
                draw.line((px,py,px+rng.randrange(1,4),py),fill=highlight)
            if not terrain and on_land and all(p not in ground for p in [(x,y+1),(x-1,y),(x+1,y),(x,y-1)]) and rng.random() < .6:
                ornaments.append((left+10,top+13))
            elif scene["theme"] == "coral" and not terrain and not on_land and cell in fringe and rng.random() < .25:
                draw.line((left+5,top+13,left+10,top+8,left+14,top+12),fill=c["gleam"],width=2)

    for x,y in ornaments:
        draw.ellipse((x-8,y-3,x+10,y+4),fill=c["shade"])
        if scene["theme"] == "copper":
            draw.polygon([(x-8,y),(x-7,y-9),(x-3,y-15),(x+4,y-15),(x+9,y-8),(x+9,y)],fill=c["roof"])
            draw.polygon([(x-7,y-9),(x-3,y-15),(x+4,y-15),(x+7,y-11),(x+1,y-7)],fill=c["light"])
            draw.line((x-6,y-4,x+7,y-4),fill=c["shade"],width=2)
        else:
            draw.rectangle((x-2,y-10,x+2,y+1),fill=c["edge"])
            for offset,spread in [(0,9),(-5,7),(-10,5)]:
                py = y-6+offset
                draw.polygon([(x-spread,py),(x,py-10),(x+spread,py),(x+3,py+3),(x-4,py+3)],fill=c["roof"])
                draw.line((x-spread+1,py,x,py-9,x+4,py-5),fill=c["foliage"],width=2)

    for cx,cy,radius in scene["plazas"]:
        x, y = cx*T+10, cy*T+10
        r = min(28, radius*8)
        draw.ellipse((x-r,y-r,x+r,y+r),outline=c["edge"],width=2)
        draw.ellipse((x-r+4,y-r+4,x+r-4,y+r-4),outline=c["speck"],width=2)
        draw.polygon([(x,y-10),(x+10,y),(x,y+10),(x-10,y)],fill=c["floor"],outline=c["edge"])
        draw.rectangle((x-2,y-2,x+2,y+2),fill=c["gleam"])
    kind, cx, cy = scene["landmark"]
    x, y = cx*T+10, cy*T+17
    draw.ellipse((x-33,y-12,x+34,y+6),fill=c["edge"])
    draw.rectangle((x-28,y-34,x+28,y-3),fill=c["bank"],outline=c["shade"],width=2)
    draw.line((x-27,y-4,x+27,y-4),fill=c["speck"],width=2)

    if kind in ("grove","observatory"):
        draw.polygon([(x-25,y-33),(x-16,y-47),(x+16,y-47),(x+25,y-33)],fill=c["roof"],outline=c["shade"],width=2)
        draw.ellipse((x-19,y-61,x+19,y-30),fill=c["foliage"],outline=c["shade"],width=2)
        draw.arc((x-15,y-58,x+14,y-31),186,330,fill=c["gleam"],width=3)
        if kind == "observatory":
            draw.ellipse((x-9,y-64,x+9,y-46),fill="#b9a850",outline=c["shade"],width=2)
            draw.line((x-12,y-46,x+12,y-65),fill="#fff0ad",width=3)
        else:
            draw.ellipse((x-8,y-65,x+8,y-49),fill="#73a94f",outline=c["shade"],width=2)
            draw.rectangle((x-3,y-60,x+3,y-54),fill=c["gleam"])
    elif kind in ("pavilion","warehouse"):
        draw.polygon([(x-33,y-30),(x-24,y-51),(x+18,y-51),(x+32,y-31)],fill=c["roof"],outline=c["shade"],width=2)
        for offset in (0,7,15):
            draw.line((x-23-offset//3,y-47+offset,x+19+offset//3,y-47+offset),fill=c["gleam"])
        draw.line((x-31,y-31,x+30,y-31),fill=c["speck"],width=2)
    else:
        draw.rectangle((x-31,y-46,x+31,y-31),fill=c["roof"],outline=c["shade"],width=2)
        draw.line((x-30,y-46,x+30,y-46),fill=c["light"],width=3)
        for column in (x-23,x+18):
            draw.rectangle((column,y-51,column+6,y-5),fill=c["speck"],outline=c["edge"],width=1)
        if kind == "ruin":
            for step in range(3):
                draw.rectangle((x-17+step*4,y-47-step*6,x+17-step*4,y-43-step*6),fill=c["light"],outline=c["edge"])
    draw.rectangle((x-7,y-23,x+7,y-5),fill=c["shade"])
    for offset in (-19,16):
        draw.rectangle((x+offset,y-23,x+offset+4,y-17),fill=c["gleam"])
    return image


if __name__ == "__main__":
    out = ROOT / "assets/maps/frontier"
    out.mkdir(exist_ok=True)
    for scene in json.loads((ROOT / "data/atlas_expansion.json").read_text())["scenes"]:
        image = paint(scene)
        image.save(out / f"{scene['id']}.webp", lossless=True, method=5)
        image.thumbnail((448,336),Image.Resampling.BOX)
        image.save(out / f"{scene['id']}-preview.webp", quality=88, method=5)
