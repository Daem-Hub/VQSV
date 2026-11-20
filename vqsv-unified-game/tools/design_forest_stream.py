"""Design an explorable forest stream as a new, non-canonical prototype area."""
import json
from collections import deque
from pathlib import Path

root=Path(__file__).resolve().parents[1]
m={'id':'ryan-forest-stream','title':'Suối Sấm Phong Lôi','width':24,'height':18,'tileSize':20,
   'ground':{'tileset':4,'tile':25,'step':2},'groundVariation':[], 'groundDecor':[],
   'water':[{'x':9,'y':0,'width':5,'height':9},{'x':9,'y':12,'width':5,'height':6}],
   'bridge':{'x':9,'y':9,'width':5,'height':3},
   'paths':[{'x':0,'y':9,'width':24,'height':3},{'x':4,'y':7,'width':3,'height':3}, {'x':17,'y':11,'width':3,'height':4}],
   'objects':[],'entities':{'player':{'x':1,'y':10},'villagers':[]},
   'links':[{'x':0,'y':10,'target':'ryan-phong-loi-forest','spawnX':22,'spawnY':10,'label':'Rừng Rậm Phong Lôi'},
            {'x':23,'y':10,'target':'ryan-lakeside','spawnX':1,'spawnY':10,'label':'Hồ Sương Mù'}]}
for x,y in [(0,3),(3,2),(6,3),(15,2),(18,3),(21,2),(0,7),(6,7),(16,7),(21,7),
            (0,14),(3,16),(6,14),(15,16),(19,15),(22,16)]:
    cells=[[xx,yy] for yy in (y-1,y) for xx in (x,x+1) if 0<=xx<24 and 0<=yy<18]
    m['objects'] += [{'tileset':0,'tile':11,'x':x,'y':y-2}, {'tileset':0,'tile':12,'x':x,'y':y,'solid':cells}]
for x,y in [(7,5),(15,5),(7,13),(15,13),(20,12)]:
    m['objects'].append({'tileset':4,'tile':4,'x':x,'y':y,'solid':[[x,y]]})
for i,(x,y) in enumerate([(2,5),(5,5),(16,4),(20,5),(3,12),(7,16),(16,12),(20,13)]):
    m['groundDecor'].append({'tileset':4,'tile':[1,3,5,0][i%4],'x':x,'y':y})
blocked={tuple(cell) for obj in m['objects'] for cell in obj.get('solid',[])}
for water in m['water']:
    blocked.update((x,y) for x in range(water['x'],water['x']+water['width']) for y in range(water['y'],water['y']+water['height']))
seen={(1,10)}; queue=deque(seen)
while queue:
    x,y=queue.popleft()
    for p in ((x+1,y),(x-1,y),(x,y+1),(x,y-1)):
        if 0<=p[0]<24 and 0<=p[1]<18 and p not in blocked and p not in seen: seen.add(p); queue.append(p)
assert (0,10) in seen and (22,10) in seen and (23,10) in seen and (18,14) in seen
(root/'prototype/data/forest_stream_map.json').write_text(json.dumps(m,ensure_ascii=False,indent=2)+'\n',encoding='utf-8')
print(f'Forest stream created: {len(seen)} reachable cells')
