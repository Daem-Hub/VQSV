"""Design the six higher-level wild regions that continue east from the Suối Sấm map.

Each region reuses the verified village/terrain tilesets (0 and 4) with a different ground tile, obstacle mix and a
colour tint. The art is a placeholder: the source worlds those petArea.mid rows belong to are not decoded yet.
`wildAreas` lists the db.mid/petArea.mid areas (map-subarea) whose pets and level ranges feed each region.
"""
import json
import random
from collections import deque
from pathlib import Path

root = Path(__file__).resolve().parents[1]
W, H = 24, 18

# id, title, ground tile (tileset 4), ground step, tint, obstacle recipe, petArea.mid areas
REGIONS = [
    dict(id='ryan-lakeside', title='Hồ Sương Mù', ground=(25, 2), tint='#6fb7ff24', trees=6, rocks=6, decor=[1, 3, 5],
         water=True, areas=['2-0', '2-2', '2-3', '2-4', '2-5'], seed=11),
    dict(id='ryan-meadow', title='Đồng Cỏ Lộng Gió', ground=(34, 1), tint='#fff2a01c', trees=7, rocks=3, decor=[0, 1, 3, 5],
         areas=['2-6', '2-7', '3-0', '3-1', '3-2'], seed=12),
    dict(id='ryan-highland', title='Cao Nguyên Đá', ground=(28, 1), tint='#9fb0c624', trees=5, rocks=14, decor=[3, 5],
         areas=['3-4', '3-5', '3-7', '4-0', '4-2'], seed=13),
    dict(id='ryan-canyon', title='Hẻm Núi Đá', ground=(20, 1), tint='#c98f5a24', trees=0, rocks=26, decor=[3],
         areas=['4-1', '4-3', '4-4', '4-6', '4-7', '4-9'], seed=14),
    dict(id='ryan-ashfield', title='Bãi Tro Núi Lửa', ground=(23, 1), tint='#ff4a2a30', trees=0, rocks=24, decor=[3],
         areas=['4-10', '4-11', '4-12', '5-0', '5-4', '5-6'], seed=15),
    dict(id='ryan-skyridge', title='Dải Mây Cao', ground=(34, 1), tint='#e8f4ff34', trees=2, rocks=18, decor=[5, 3],
         areas=['7-1', '7-3', '7-4', '7-5', '7-6', '7-7', '7-8', '7-9', '7-10', '7-11', '7-12'], seed=16),
]
CHAIN = ['ryan-forest-stream'] + [r['id'] for r in REGIONS]
TITLES = {'ryan-forest-stream': 'Suối Sấm Phong Lôi', **{r['id']: r['title'] for r in REGIONS}}


def build(index, spec):
    rng = random.Random(spec['seed'])
    m = {'id': spec['id'], 'title': spec['title'], 'width': W, 'height': H, 'tileSize': 20,
         'ground': {'tileset': 4, 'tile': spec['ground'][0], 'step': spec['ground'][1]}, 'groundVariation': [], 'groundDecor': [],
         'paths': [{'x': 0, 'y': 9, 'width': W, 'height': 3}], 'objects': [], 'entities': {'player': {'x': 1, 'y': 10}, 'villagers': []},
         'tint': spec['tint'], 'wildAreas': spec['areas'],
         'links': [{'x': 0, 'y': 10, 'target': CHAIN[index], 'spawnX': 22, 'spawnY': 10, 'label': TITLES[CHAIN[index]]}]}
    if index + 1 < len(REGIONS):
        nxt = REGIONS[index + 1]
        m['links'].append({'x': 23, 'y': 10, 'target': nxt['id'], 'spawnX': 1, 'spawnY': 10, 'label': nxt['title']})
    blocked = set()
    if spec.get('water'):
        m['water'] = [{'x': 10, 'y': 0, 'width': 4, 'height': 9}, {'x': 10, 'y': 12, 'width': 4, 'height': 6}]
        m['bridge'] = {'x': 10, 'y': 9, 'width': 4, 'height': 3}
        for w in m['water']:
            blocked.update((x, y) for x in range(w['x'], w['x'] + w['width']) for y in range(w['y'], w['y'] + w['height']))

    def free(cells):
        return all(0 <= x < W and 0 <= y < H and (x, y) not in blocked and not (8 <= y <= 12) and (x, y) not in {(1, 10)} for x, y in cells)

    placed = 0
    attempts = 0
    while placed < spec['trees'] and attempts < 400:
        attempts += 1
        x, y = rng.randrange(1, W - 2), rng.randrange(3, H)
        cells = [(xx, yy) for yy in (y - 1, y) for xx in (x, x + 1)]
        if free(cells) and all((cx, cy) not in blocked for cx, cy in cells):
            m['objects'] += [{'tileset': 0, 'tile': 11, 'x': x, 'y': y - 2}, {'tileset': 0, 'tile': 12, 'x': x, 'y': y, 'solid': [list(c) for c in cells]}]
            blocked.update(cells); placed += 1
    placed = 0
    attempts = 0
    while placed < spec['rocks'] and attempts < 600:
        attempts += 1
        x, y = rng.randrange(0, W), rng.randrange(0, H)
        if free([(x, y)]):
            m['objects'].append({'tileset': 4, 'tile': rng.choice([4, 12]), 'x': x, 'y': y, 'solid': [[x, y]]})
            blocked.add((x, y)); placed += 1
    for n in range(8):
        x, y = rng.randrange(0, W), rng.randrange(0, H)
        if (x, y) not in blocked and not (9 <= y <= 11):
            m['groundDecor'].append({'tileset': 4, 'tile': spec['decor'][n % len(spec['decor'])], 'x': x, 'y': y})
    seen = {(1, 10)}
    queue = deque(seen)
    while queue:
        x, y = queue.popleft()
        for p in ((x + 1, y), (x - 1, y), (x, y + 1), (x, y - 1)):
            if 0 <= p[0] < W and 0 <= p[1] < H and p not in blocked and p not in seen:
                seen.add(p); queue.append(p)
    assert (0, 10) in seen and (23, 10) in seen and (22, 10) in seen, spec['id']
    return m, len(seen)


for i, spec in enumerate(REGIONS):
    m, reachable = build(i, spec)
    (root / f"prototype/data/wild_{spec['id'].split('-', 1)[1]}.json").write_text(json.dumps(m, ensure_ascii=False, indent=2) + '\n', encoding='utf-8')
    print(f"{spec['id']}: {reachable} reachable cells")
