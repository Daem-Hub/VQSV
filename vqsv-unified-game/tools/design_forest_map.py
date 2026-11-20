"""Build the first connected exploration area using the existing verified tiles."""
import json
from collections import deque
from pathlib import Path

root = Path(__file__).resolve().parents[1]
path = root / 'prototype/data/forest_map.json'
m = {'id':'ryan-phong-loi-forest', 'title':'Rừng Rậm Phong Lôi', 'width':24, 'height':18, 'tileSize':20,
     'ground':{'tileset':4,'tile':25,'step':2}, 'groundVariation':[], 'groundDecor':[],
     'trails': [{'width': 1.65, 'points': [[-1, 10], [2, 10], [4.5, 10.5], [7, 10.6], [10, 9.3], [13, 8.8], [16, 9.4], [18.5, 10.6], [21, 10.2], [24, 10]]}],
     'objects':[], 'entities':{'player':{'x':1,'y':10}, 'villagers':[]},
     'links':[{'x':0,'y':10,'target':'slice01-border-village','spawnX':22,'spawnY':10,'label':'Làng biên giới'},
              {'x':23,'y':10,'target':'ryan-forest-stream','spawnX':1,'spawnY':10,'label':'Suối Sấm Phong Lôi'}]}
trees = [(1,3),(7,3),(14,2),(20,3), (4,6),(16,6),(22,7),
         (1,14),(7,14),(15,15),(21,14), (4,15),(18,16)]
for x,y in trees:
    cells = [[xx,yy] for yy in (y-1,y) for xx in (x,x+1) if 0 <= xx < 24 and 0 <= yy < 18]
    species = 'oak' if (x + y) % 3 == 1 else 'fir'
    m['objects'].append({'asset': f'scenery/{species}.png', 'x': x, 'y': y, 'solid': cells})
for x,y in [(6,5),(17,4),(8,13),(16,13),(20,12)]:
    m['objects'].append({'asset':'scenery/stones.png','x':x,'y':y,'anchorX':.5,'solid':[[x,y]]})
for i,(x,y) in enumerate([(3,5),(8,4),(15,4),(20,5),(5,12),(9,12),(15,12),(19,13),(7,16),(16,17)]):
    species = 'flowers' if i % 3 == 0 else 'shrub'
    m['groundDecor'].append({'asset':f'scenery/{species}.png','x':x,'y':y,'anchorX':.5})
blocked={tuple(cell) for obj in m['objects'] for cell in obj.get('solid',[])}
seen={(1,10)}; queue=deque(seen)
while queue:
    x,y=queue.popleft()
    for p in [(x+1,y),(x-1,y),(x,y+1),(x,y-1)]:
        if 0<=p[0]<24 and 0<=p[1]<18 and p not in blocked and p not in seen:
            seen.add(p); queue.append(p)
assert (0,10) in seen and (12,4) in seen and (22,10) in seen
path.write_text(json.dumps(m,ensure_ascii=False,indent=2)+'\n',encoding='utf-8')
print(f'Forest created: {len(seen)} reachable cells')
