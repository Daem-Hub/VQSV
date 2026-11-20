"""Arrange the playable opening village using verified VQSV5 tile pieces."""
import json
from collections import deque
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
path = ROOT / 'prototype/data/opening_map.json'
m = json.loads(path.read_text(encoding='utf-8'))
# Coordinates follow walkable tile centres, from the shop steps to the east gate.
m.pop('paths', None)
m['trails'] = [
    {'width': 1.65, 'points': [[5,8], [5,9], [6.5,10], [9,10.4], [11,10.6], [14,10.3], [17,10.7], [20,10], [22,10], [24,10]]},
    {'width': 1.15, 'points': [[19,7], [18.9,8.2], [18.4,9.5], [17.5,10.6]]},
    {'width': 1.15, 'points': [[11,8.9], [11,10.6], [11,11.8], [10.4,13.2], [10,14]]},
    {'width': 1.03, 'surface': 'earth', 'points': [[6.2,11.8], [7.5,12.8], [9.6,13.9], [11.2,14.1], [13.1,13.6], [14.4,12.6], [16.2,12], [17,12]]},
    {'width': 1.0, 'points': [[7.3,10.2], [7.1,11], [6,12]]},
    {'width': 1.0, 'points': [[17,10.7], [16.6,11.5], [17,12]]},
]
m['objects'] = []
for x, y, sprite in [(3, 7, 'apothecary'), (17, 6, 'charm-shop')]:
    solid = [[xx, yy] for yy in (y-1, y) for xx in range(x, x+4)]
    m['objects'].append({'asset': f'scenery/{sprite}.png', 'x': x+2, 'y': y, 'anchorX': .5, 'solid': solid})
# Keep complete crowns clear of roofs, doors, and the eastbound road.
for x, y in [(0, 5), (7, 2), (12, 2), (22, 3), (22, 9),
             (0, 14), (3, 17), (9, 17), (15, 17), (21, 17)]:
    solid = [[xx, yy] for yy in (y - 1, y) for xx in (x, x + 1) if 0 <= xx < m['width'] and 0 <= yy < m['height']]
    tree = 'fir' if (x, y) in [(7, 2), (22, 3), (0, 14), (15, 17)] else 'oak'
    m['objects'].append({'asset': f'scenery/{tree}.png', 'x': x, 'y': y, 'solid': solid})
for x, y in [(2, 7), (8, 8), (15, 8), (20, 8), (5, 14), (18, 13)]:
    prop = 'stump' if (x, y) in [(5,14), (18,13)] else 'shrub'
    m['objects'].append({'asset': f'scenery/{prop}.png', 'x': x, 'y': y, 'anchorX': .5, 'solid': [[x,y]]})
for x, y in [(2,13), (8,14), (15,13), (20,14)]:
    m['objects'].append({'asset': 'scenery/stones.png', 'x': x, 'y': y, 'anchorX': .5, 'solid': [[x,y]]})
m['objects'].append({'asset':'scenery/noticeboard.png', 'x':8, 'y':7})
m['groundDecor'] = [
    {'asset': f'scenery/{name}.png', 'x': x, 'y': y, 'anchorX': .5}
    for name, x, y in [('flowers',3,8), ('flowers',4,8), ('shrub',9,5), ('flowers',14,9),
                       ('flowers',18,8), ('flowers',21,7), ('shrub',15,6), ('flowers',2,12),
                       ('flowers',9,9), ('shrub',6,15), ('flowers',14,15), ('flowers',19,12)]
]
m['links'] = [
    {'x': 23, 'y': 10, 'target': 'ryan-phong-loi-forest', 'spawnX': 1, 'spawnY': 10, 'label': 'Rừng Rậm Phong Lôi'},
    {'kind': 'door', 'x': 5, 'y': 8, 'target': 'village-apothecary', 'spawnX': 12, 'spawnY': 13, 'label': 'Tiệm thuốc'},
    {'kind': 'door', 'x': 19, 'y': 7, 'target': 'village-charms', 'spawnX': 12, 'spawnY': 13, 'label': 'Tiệm bùa'},
]
m['groundVariation'] = []
blocked = {tuple(cell) for obj in m['objects'] for cell in obj.get('solid', [])}
elder = m['entities']['elder']; blocked.add((elder['x'], elder['y']))
for npc in m['entities'].get('villagers', []):
    pos = (npc['x'], npc['y'])
    if pos in blocked: raise ValueError(f"NPC {npc['id']} overlaps an obstacle")
    blocked.add(pos)
start = tuple(m['entities']['player'][k] for k in ('x', 'y'))
if start in blocked: raise ValueError('Player start is blocked')
seen = {start}; todo = deque([start])
while todo:
    x, y = todo.popleft()
    for nxt in ((x+1,y),(x-1,y),(x,y+1),(x,y-1)):
        if 0 <= nxt[0] < m['width'] and 0 <= nxt[1] < m['height'] and nxt not in blocked and nxt not in seen:
            seen.add(nxt); todo.append(nxt)
for name in ('elder', 'encounter'):
    entity = m['entities'][name]
    if not any(abs(x-entity['x']) + abs(y-entity['y']) <= 1 for x, y in seen):
        raise ValueError(f'{name} is unreachable')
for npc in m['entities'].get('villagers', []):
    if not any(abs(x-npc['x']) + abs(y-npc['y']) == 1 for x,y in seen):
        raise ValueError(f"NPC {npc['id']} is unreachable")
path.write_text(json.dumps(m, ensure_ascii=False, indent=2) + '\n', encoding='utf-8')
print(f'Village map written; {len(seen)} accessible cells, NPC and encounter reachable.')
