"""Bake the walking routes and matching field charts at the game's native pixel scale."""
import json
import math
from collections import deque
import random
from pathlib import Path
from PIL import Image, ImageChops, ImageDraw, ImageFilter, ImageOps, ImageEnhance

from pixel_art import pixel_scene

BASE = Path(__file__).resolve().parents[1] / 'prototype'
OUT = BASE / 'assets' / 'trails'
OUT.mkdir(exist_ok=True)


def smooth(points):
    """Cut corners while retaining both endpoints and exact straight bridge spans."""
    for _ in range(3):
        rounded = [points[0]]
        for (x, y), (u, v) in zip(points, points[1:]):
            rounded.extend([(.75*x+.25*u, .75*y+.25*v), (.25*x+.75*u, .25*y+.75*v)])
        points = [*rounded, points[-1]]
    return [(round(x), round(y)) for x, y in points]


def irregular_mask(size, trails, tile, rng):
    mask = Image.new('L', size)
    draw = ImageDraw.Draw(mask)
    for trail in trails:
        points = smooth([((x+.5)*tile, (y+.5)*tile) for x,y in trail['points']])
        width = round(trail['width']*tile)
        draw.line(points, fill=255, width=width, joint='curve')
        radius = width//2
        for x,y in (points[0],points[-1]):
            draw.ellipse((x-radius,y-radius,x+radius,y+radius), fill=255)
    # Tiny cuts in the verge, shared by every branch at a junction.
    edge = ImageChops.subtract(mask, mask.filter(ImageFilter.MinFilter(5)))
    for _ in range(size[0]*size[1]//9):
        x,y = rng.randrange(size[0]),rng.randrange(size[1])
        if edge.getpixel((x,y)):
            draw.rectangle((x,y,x+rng.randrange(1,4),y+1),fill=rng.choice((0,255)))
    return mask


def check_walkable_trails(data, mask):
    """A four-direction walk on the drawn trail must reach each interaction point."""
    tile = data['tileSize']
    entities = data['entities']
    solid = {tuple(cell) for obj in data['objects'] for cell in obj.get('solid', [])}
    npcs = list(entities.get('villagers', []))
    if entities.get('elder'):
        npcs.append(entities['elder'])
    solid.update((npc['x'],npc['y']) for npc in npcs)
    for water in data.get('water', []):
        solid.update((x,y) for x in range(water['x'],water['x']+water['width'])
                     for y in range(water['y'],water['y']+water['height']))
    cells = {(x,y) for x in range(data['width']) for y in range(data['height'])
             if (x,y) not in solid and mask.getpixel((x*tile+tile//2,y*tile+tile//2))}
    spawn = entities['player']['x'], entities['player']['y']
    if spawn not in cells:
        raise ValueError(f"{data['id']}: spawn is off the trail")
    seen, queue = {spawn}, deque([spawn])
    while queue:
        x,y = queue.popleft()
        for point in ((x+1,y),(x-1,y),(x,y+1),(x,y-1)):
            if point in cells and point not in seen:
                seen.add(point)
                queue.append(point)
    for link in data['links']:
        if (link['x'],link['y']) not in seen:
            raise ValueError(f"{data['id']}: disconnected entrance {link['label']}")
    targets = [(npc['x'],npc['y']) for npc in npcs]
    if entities.get('encounter'):
        targets.append((entities['encounter']['x'],entities['encounter']['y']))
    if data.get('bridge'):
        bridge = data['bridge']
        targets.append((bridge['x']+bridge['width']//2,bridge['y']+bridge['height']//2))
    for tx,ty in targets:
        if not any(abs(tx-x)+abs(ty-y)<=1 for x,y in seen):
            raise ValueError(f"{data['id']}: interaction at {(tx,ty)} is off the trail")


DENSITY = 3
ART = BASE.parent / 'assets' / 'landscape'
MATERIALS = Image.open(ART / 'surfaces.webp').convert('RGB')
MEADOW = Image.open(ART / 'meadow.webp').convert('RGB')
WOODLAND = Image.open(ART / 'woodland.webp').convert('RGB')


def material(column, row, side, size):
    """Feather independent samples to keep roots, stone joints and ripples irregular."""
    w, h = MATERIALS.width // 2, MATERIALS.height // 2
    source = MATERIALS.crop((column*w+3, row*h+3, (column+1)*w-3, (row+1)*h-3))
    if (column,row) == (1,0):
        source = source.crop((w//6,h//6,5*w//6,5*h//6))
    rng = random.Random(36+column+row*2)
    radius = round(side*.09)
    feather = Image.new('L',(side,side))
    ImageDraw.Draw(feather).rectangle((radius,radius,side-radius,side-radius),fill=255)
    feather = feather.filter(ImageFilter.GaussianBlur(radius*.48))
    result = Image.new('RGB', size, source.resize((1,1)).getpixel((0,0)))
    step = side-3*radius
    for y in range(-side//2,size[1]+step,step):
        for x in range(-side//2,size[0]+step,step):
            sample = round(min(source.size)*rng.uniform(.64,.94))
            sx,sy = rng.randrange(source.width-sample+1),rng.randrange(source.height-sample+1)
            patch = source.crop((sx,sy,sx+sample,sy+sample)).resize((side,side),Image.Resampling.LANCZOS)
            if rng.random()<.5: patch = ImageOps.mirror(patch)
            result.paste(patch,(x,y),feather)
    return result


def paving_medallion(scene, x, y, r):
    """A flush compass mosaic marks the meeting place without obstructing its paths."""
    d = ImageDraw.Draw(scene)
    for radius, color in [(r,'#786e57'),(r-3,'#c0ae80'),(r-6,'#897c5d'),(r-9,'#c9be9b')]:
        d.ellipse((x-radius,y-radius*.77,x+radius,y+radius*.77),fill=color)
    for i in range(8):
        a = i*math.pi/4
        def point(a,length):
            return (x+math.sin(a)*length,y-math.cos(a)*length*.77)
        tip = point(a,r*(.76 if i%2==0 else .51))
        d.polygon([(x,y),tip,point(a-.42,r*.17)],fill='#7c806e')
        d.polygon([(x,y),tip,point(a+.42,r*.17)],fill='#ad996d')
    d.ellipse((x-3,y-3,x+3,y+3),fill='#e1cf96')


def stream(scene, bridge, t, rng):
    """Keep the painted banks in the two water rectangles and span them at the real crossing."""
    x, y, w, h = [bridge[k]*t for k in ('x','y','width','height')]
    H = scene.height
    left = [(x+round(4*math.sin(yy/45)+3*math.sin(yy/16)),yy) for yy in range(-7,H+8,6)]
    right = [(x+w+round(5*math.sin(yy/54+.6)+2*math.sin(yy/13)),yy) for yy in range(-7,H+8,6)]
    mask = Image.new('L',scene.size)
    ImageDraw.Draw(mask).polygon(left+list(reversed(right)),fill=255)
    scene.paste('#677656',mask=mask.filter(ImageFilter.MaxFilter(13)).filter(ImageFilter.GaussianBlur(2)))
    water = material(0,1,300,scene.size)
    water = Image.blend(water,Image.new('RGB',scene.size,'#258c8f'),.36)
    scene.paste(water,mask=mask.filter(ImageFilter.GaussianBlur(1.5)))
    pen = ImageDraw.Draw(scene)
    for bank,sign in [(left,1),(right,-1)]:
        pen.line([(bx+4*sign,by) for bx,by in bank],fill='#93b69a',width=4)
        for bx,by in bank[::3]:
            if y-6<by<y+h+6: continue
            if rng.random()<.58:
                dx=rng.randint(-4,6);r=rng.randint(3,7)
                pen.ellipse((bx+dx-r,by-r//2,bx+dx+r,by+r//2),fill='#798b73',outline='#596e60')
                pen.line((bx+dx-r+2,by-1,bx+dx+1,by-2),fill='#b8ba92',width=2)
    span = Image.open(ART / 'bridge.png').convert('RGBA')
    # The deck meets the path at both ends; the rope posts rise above its tile footprint.
    span = span.resize((w+40,h+45),Image.Resampling.LANCZOS)
    scene.paste(span,(x-20,y-26),span)


def sprite(obj):
    art = Image.open(BASE / 'assets' / obj['asset']).convert('RGBA')
    return art.resize((art.width*DENSITY, art.height*DENSITY), Image.Resampling.NEAREST)


def place(image, art, obj, t):
    x = math.floor((obj['x'] + obj.get('anchorX',1))*t - art.width/2 + .5)
    y = (obj['y']+1)*t - art.height
    image.paste(art,(x,y),art)


def ground_shadow(scene, obj, t):
    # The silhouette, compressed towards the ground, leaves a contact shadow at the feet.
    art = sprite(obj)
    length = max(6,round(art.height*.26))
    mask = art.getchannel('A').resize((art.width,length), Image.Resampling.LANCZOS)
    slant = round(length*.65); margin = 10
    stamp = Image.new('L',(art.width+slant+margin*2,length+margin*2))
    for row in range(length):
        dx = round(slant*(1-row/length))
        stamp.paste(mask.crop((0,row,art.width,row+1)),(dx+margin,margin+row))
    stamp = stamp.filter(ImageFilter.GaussianBlur(3)).point(lambda alpha:round(alpha*.35))
    x = round((obj['x']+obj.get('anchorX',1))*t-art.width/2)+4-margin
    y = (obj['y']+1)*t-length//2-margin
    scene.paste('#203d38',(x,y,x+stamp.width,y+stamp.height),stamp)


def paint_map(data, seed):
    rng = random.Random(seed)
    native = data['tileSize']; t = native*DENSITY
    size = data['width']*t, data['height']*t
    mask = irregular_mask(size,data['trails'],t,rng)
    check_walkable_trails(data,mask.resize((size[0]//DENSITY,size[1]//DENSITY),Image.Resampling.NEAREST))
    village = bool(data['entities'].get('elder'))
    meadow = MEADOW if village else WOODLAND
    if data.get('bridge'):
        meadow = Image.blend(ImageOps.mirror(WOODLAND), MEADOW.resize(WOODLAND.size), .32)
    scene = meadow.resize(size,Image.Resampling.LANCZOS)
    dust = material(1,0,190,size)
    dust = Image.blend(dust,Image.new('RGB',size,'#beae82'),.33)
    scene.paste(dust,mask=mask.filter(ImageFilter.MaxFilter(9)).filter(ImageFilter.GaussianBlur(4)))
    if village:
        stone = irregular_mask(size,[trail for trail in data['trails'] if trail.get('surface')!='earth'],t,rng)
        paving = ImageEnhance.Color(material(0,0,143,size)).enhance(.72)
        scene.paste(paving,mask=stone.filter(ImageFilter.MinFilter(9)).filter(ImageFilter.GaussianBlur(2)))
        paving_medallion(scene,round(11.5*t),11*t,round(t*.73))
    if data.get('bridge'):
        stream(scene,data['bridge'],t,rng)
    for obj in data['groundDecor']:
        if 'asset' in obj: place(scene,sprite(obj),obj,t)
    for obj in data['objects']:
        if obj.get('asset','').startswith('scenery/'): ground_shadow(scene,obj,t)
    native_size = (data['width']*native, data['height']*native)
    ground = pixel_scene(scene, native_size, colors=96).convert('RGB')
    ground.save(OUT / (data['id']+'.webp'), lossless=True, method=6)
    # Paint props at the same native coordinates and in the same order as the live field.
    for obj in sorted(data['objects'], key=lambda o: (o['y'], o['x'])):
        if 'asset' in obj:
            art = Image.open(BASE / 'assets' / obj['asset']).convert('RGBA')
            place(ground, art, obj, native)
    ground.save(OUT / (data['id']+'-chart.png'), optimize=True)


if __name__ == '__main__':
    for i,name in enumerate(('opening_map','forest_map','forest_stream_map')):
        paint_map(json.loads((BASE/'data'/f'{name}.json').read_text(encoding='utf-8')),310+i)
