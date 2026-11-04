"""Arrange the playable opening village using verified VQSV5 tile pieces."""
import json
from collections import deque
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
path = ROOT / 'prototype/data/opening_map.json'
m = json.loads(path.read_text(encoding='utf-8'))
m['paths'] = [
    {'x': 1, 'y': 10, 'width': 22, 'height': 1, 'tileset': 4, 'tile': 23},
    {'x': 3, 'y': 8, 'width': 4, 'height': 2, 'tileset': 4, 'tile': 23},
    {'x': 18, 'y': 7, 'width': 1, 'height': 4, 'tileset': 4, 'tile': 23},
    {'x': 10, 'y': 9, 'width': 4, 'height': 3, 'tileset': 4, 'tile': 23},
    {'x': 11, 'y': 11, 'width': 2, 'height': 7, 'tileset': 4, 'tile': 23},
    {'x': 7, 'y': 11, 'width': 5, 'height': 1, 'tileset': 4, 'tile': 23},
    {'x': 13, 'y': 12, 'width': 5, 'height': 1, 'tileset': 4, 'tile': 23},
]
m['objects'] = []
for x, y in ((3, 7), (17, 6)):
    m['objects'].extend([
        {'tileset': 0, 'tile': 2, 'x': x, 'y': y - 4},
        {'tileset': 0, 'tile': 2, 'x': x + 2, 'y': y - 4, 'flip': 4},
        {'tileset': 0, 'tile': 3, 'x': x, 'y': y, 'solid': [[xx, yy] for yy in (y-1, y) for xx in range(x, x + 4)]},
        {'tileset': 0, 'tile': 3, 'x': x + 2, 'y': y, 'flip': 4},
    ])
for x, y in [(0, 2), (3, 2), (7, 2), (9, 2), (12, 2), (15, 2), (20, 2), (22, 3),
             (0, 5), (1, 8), (0, 14), (3, 17), (6, 16), (9, 17),
             (15, 17), (18, 16), (21, 17), (22, 6), (22, 9), (22, 14)]:
    solid = [[xx, yy] for yy in (y - 1, y) for xx in (x, x + 1) if 0 <= xx < m['width'] and 0 <= yy < m['height']]
    if (x, y) in ((3, 17), (15, 17)):
        m['objects'].append({'asset': 'tilesets/village_prop_36.png', 'x': x, 'y': y, 'solid': solid})
    else:
        m['objects'].append({'tileset': 0, 'tile': 11, 'x': x, 'y': y - 2})
        m['objects'].append({'tileset': 0, 'tile': 12, 'x': x, 'y': y, 'solid': solid})
for x, y in [(2, 7), (8, 8), (15, 8), (20, 8), (5, 14), (18, 13)]:
    m['objects'].append({'tileset': 4, 'tile': 2, 'x': x, 'y': y, 'solid': [[x, y]]})
for x, y in [(2, 13), (8, 14), (15, 13), (20, 14)]:
    m['objects'].append({'tileset': 4, 'tile': 4, 'x': x, 'y': y, 'solid': [[x, y]]})
m['objects'].extend([
    {'asset':'tilesets/village_prop_24.png', 'x':8, 'y':7},
    {'asset':'tilesets/village_prop_25.png', 'x':15, 'y':6, 'scale':0.5},
])
m['groundDecor'] = [
    {'tileset': 4, 'tile': tile, 'x': x, 'y': y}
    for tile, x, y in [(0, 2, 4), (1, 3, 5), (3, 8, 4), (1, 9, 5), (5, 14, 4),
                       (0, 15, 6), (1, 20, 4), (3, 21, 6), (0, 3, 8), (5, 9, 9),
                       (1, 14, 9), (3, 20, 9), (5, 2, 12), (1, 6, 12), (0, 9, 14),
                       (3, 14, 15), (5, 19, 12), (1, 21, 12), (0, 6, 15), (5, 18, 15)]
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
