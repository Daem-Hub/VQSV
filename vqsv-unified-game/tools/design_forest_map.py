"""Build the first connected exploration area using the existing verified tiles."""
import json
from collections import deque
from pathlib import Path

root = Path(__file__).resolve().parents[1]
path = root / 'prototype/data/forest_map.json'
m = {'id':'ryan-phong-loi-forest', 'title':'Rừng Rậm Phong Lôi', 'width':24, 'height':18, 'tileSize':20,
     'ground':{'tileset':4,'tile':25,'step':2}, 'groundVariation':[], 'groundDecor':[],
     'paths':[{'x':0,'y':9,'width':24,'height':3}, {'x':11,'y':3,'width':3,'height':9}, {'x':11,'y':11,'width':3,'height':5}],
     'objects':[], 'entities':{'player':{'x':1,'y':10}, 'villagers':[]},
     'links':[{'x':0,'y':10,'target':'slice01-border-village','spawnX':22,'spawnY':10,'label':'Làng biên giới'},
              {'x':23,'y':10,'target':'ryan-forest-stream','spawnX':1,'spawnY':10,'label':'Suối Sấm Phong Lôi'}]}
trees = [(1,3),(4,2),(7,3),(10,2),(14,2),(17,2),(20,3),(22,4),
         (1,7),(4,6),(7,7),(16,6),(19,7),(22,7),
         (1,14),(4,15),(7,14),(9,16),(15,15),(18,16),(21,14),(22,16)]
for x,y in trees:
    cells = [[xx,yy] for yy in (y-1,y) for xx in (x,x+1) if 0 <= xx < 24 and 0 <= yy < 18]
    m['objects'].append({'tileset':0,'tile':11,'x':x,'y':y-2})
    m['objects'].append({'tileset':0,'tile':12,'x':x,'y':y,'solid':cells})
for x,y in [(6,5),(17,4),(8,13),(16,13),(20,12)]:
    m['objects'].append({'tileset':4,'tile':4,'x':x,'y':y,'solid':[[x,y]]})
for i,(x,y) in enumerate([(3,5),(8,4),(15,4),(20,5),(5,12),(9,12),(15,12),(19,13),(7,16),(16,17)]):
    m['groundDecor'].append({'tileset':4,'tile':[0,1,3,5][i%4],'x':x,'y':y})
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
