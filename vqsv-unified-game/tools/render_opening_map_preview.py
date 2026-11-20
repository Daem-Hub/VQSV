"""Render a field preview from the same background and depth anchors used by the client."""
import json
import math
from pathlib import Path
import sys
from PIL import Image

PROJECT = Path(__file__).resolve().parents[1]
BASE = PROJECT / 'prototype'
name = sys.argv[1] if len(sys.argv) > 1 else 'opening_map'
if name not in ('opening_map', 'forest_map', 'forest_stream_map'):
    raise ValueError('Unknown preview map')
area = json.loads((BASE / 'data' / f'{name}.json').read_text(encoding='utf-8'))
scene = Image.open(BASE / 'assets' / 'trails' / (area['id']+'.webp')).convert('RGBA')
tile = scene.width // area['width']
art = BASE / 'assets'
entities = area['entities']
faces = {'elder':'elder', 'villager_74':'herbalist', 'villager_66':'villager', 'villager_75':'scout'}
layers = []
for obj in area['objects']:
    if 'asset' not in obj:
        continue
    sprite = Image.open(art / obj['asset']).convert('RGBA')
    layers.append((obj['y'],1,obj['x']+obj.get('anchorX',1),sprite))
actors = ([dict(entities['elder'],id='elder')] if entities.get('elder') else []) + entities['villagers']
for npc in actors:
    key = 'elder' if npc['id']=='elder' else Path(npc['asset']).stem
    sprite = Image.open(art / 'illustrations' / (faces[key]+'.webp')).convert('RGBA')
    layers.append((npc['y'],0,npc['x']+.5,sprite))
player = entities['player']
kael = Image.open(art / 'characters/kael_field.png').crop((0,80,32,120))
layers.append((player['y'],0,player['x']+.6,kael))
pet = Image.open(art / 'illustrations/loi-mieu-field.webp')
layers.append((player['y']-.01,0,player['x']-.22,pet))
if 'encounter' in entities:
    goal = entities['encounter']
    clue = Image.open(art / 'illustrations/beast-track.png')
    scene.alpha_composite(clue,(goal['x']*tile-1,goal['y']*tile+2))
for y,_,x,sprite in sorted(layers,key=lambda layer:layer[:2]):
    scene.alpha_composite(sprite,(math.floor(x*tile-sprite.width/2+.5),round((y+1)*tile-sprite.height)))
output = PROJECT / 'asset_review' / ('opening_map_redesign.png' if name=='opening_map' else f'{name}_preview.png')
scene.resize((960,720), Image.Resampling.NEAREST).save(output)
print(output)
