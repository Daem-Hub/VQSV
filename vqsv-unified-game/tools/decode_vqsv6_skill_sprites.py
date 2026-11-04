"""Reconstruct VQSV6 FIX 2.7 actor-effect sprite sequences referenced by skill rows."""
import ast
import json
import os
import struct
import zipfile
from pathlib import Path
from PIL import Image

ROOT=Path(__file__).resolve().parents[1]
source=Path(os.environ.get('VQSV_SOURCE_ROOT',r'D:\TOAN BO FILE\Download\game'))
program=(ROOT/'tools/decode_vqsv5_npc2_animations.py').read_text(encoding='utf8')
nodes=[n for n in ast.parse(program).body if isinstance(n,ast.FunctionDef) and n.name=='apply_source_transform']
env={'Image':Image}; exec(compile(ast.Module(body=nodes,type_ignores=[]),'<transform>','exec'),env)
sprite_ids=[262,263,264,265,266,267,268,299,300,301,304,306,307,308,309]

def parse(blob):
    pos=0
    def short():
        nonlocal pos
        if pos+2>len(blob): raise ValueError('truncated sprite')
        v=struct.unpack_from('>h',blob,pos)[0]; pos+=2; return v
    def flat():
        n,cols=short(),short()
        if n<0 or cols<0 or n*cols>20000: raise ValueError('invalid table size')
        return [short() for _ in range(n*cols)]
    def matrix():
        n,cols=short(),short()
        if n<0 or cols<0 or n>1000: raise ValueError('invalid matrix size')
        out=[]
        for _ in range(n):
            m=short()
            if m<0 or m*cols>20000: raise ValueError('invalid row size')
            out.append([short() for _ in range(m*cols)])
        return out
    raw=flat()
    if len(raw)%5: raise ValueError('invalid frame table')
    frames=[raw[i:i+5] for i in range(0,len(raw),5)]
    cells=matrix(); animations=matrix(); hit=flat(); collision=flat()
    if pos!=len(blob): raise ValueError(f'unread sprite bytes {len(blob)-pos}')
    return frames,cells,animations

manifest={'source':'VQSV_FIX_2.7 sprite.mid, spr_N_all(r) and decoded images', 'tick_ms':60, 'tick_note':'Ước lượng khi xem; bản mẫu còn có điều kiện và pha hiệu ứng.', 'sprites':{},'errors':{}}
with zipfile.ZipFile(source/'VQSV_FIX_2.7.zip') as z:
    names=z.namelist(); prefix='exe/VQSV Liet Hoa Rebuild/app/modules/'
    sprite_table=json.loads(z.read(prefix+'script/decoded/data__script__sprite.mid.json'))['rows']
    sprite_images={row[0]:row[1:] for row in sprite_table if row and row[0]>=0}
    for sid in sprite_ids:
        try:
            frames,cells,animations=parse(z.read(prefix+f'spr/original/spr_{sid}_all(r)'))
            image_ids=sprite_images[sid]
            images=[Image.open(z.open(prefix+f'img/decoded/data__img__img_{i}.mid.png')).convert('RGBA') for i in image_ids]
            prepared=[]; bounds=[0,0,1,1]
            for row in cells:
                if len(row)%4: raise ValueError('invalid cell row')
                parts=[]
                for i in range(0,len(row),4):
                    fid,dx,dy,flag=row[i:i+4]
                    if fid<0 or fid>=len(frames): raise ValueError('invalid frame ID')
                    slot,x,y,w,h=frames[fid]
                    if slot<0 or slot>=len(images) or x<0 or y<0 or w<=0 or h<=0 or x+w>images[slot].width or y+h>images[slot].height: raise ValueError(f'invalid image crop {frames[fid]}')
                    crop=env['apply_source_transform'](images[slot].crop((x,y,x+w,y+h)),flag & 7)
                    bounds[0]=min(bounds[0],dx); bounds[1]=min(bounds[1],dy); bounds[2]=max(bounds[2],dx+crop.width); bounds[3]=max(bounds[3],dy+crop.height)
                    parts.append((crop,dx,dy))
                prepared.append(parts)
            if bounds[2]-bounds[0]>500 or bounds[3]-bounds[1]>500: raise ValueError('unreasonably large sprite bounds')
            folder=ROOT/f'prototype/assets/effects/vqsv6/sprite_{sid}'; folder.mkdir(parents=True,exist_ok=True)
            for i,parts in enumerate(prepared):
                im=Image.new('RGBA',(bounds[2]-bounds[0]+6,bounds[3]-bounds[1]+6))
                for crop,x,y in parts: im.alpha_composite(crop,(x-bounds[0]+3,y-bounds[1]+3))
                im.save(folder/f'cell_{i}.png')
            states=[]
            for row in animations:
                if len(row)%2: raise ValueError('invalid animation row')
                state=[]
                for i in range(0,len(row),2):
                    ticks,cell=row[i:i+2]
                    if cell<0 or cell>=len(cells): raise ValueError('invalid animation cell')
                    state.append({'image':f'assets/effects/vqsv6/sprite_{sid}/cell_{cell}.png','duration_ticks':max(1,ticks)})
                states.append(state)
            # Crop each animation as a group, preserving alignment across all frames.
            for state_index,state in enumerate(states):
                if not state: continue
                images_for_state=[Image.open(ROOT/'prototype'/entry['image']).convert('RGBA') for entry in state]
                boxes=[im.getbbox() for im in images_for_state if im.getbbox()]
                if not boxes: continue
                box=(min(b[0] for b in boxes),min(b[1] for b in boxes),max(b[2] for b in boxes),max(b[3] for b in boxes))
                for frame_index,(entry,im) in enumerate(zip(state,images_for_state)):
                    target=folder/f'state_{state_index}_frame_{frame_index}.png'
                    im.crop(box).save(target)
                    entry['image']=target.relative_to(ROOT/'prototype').as_posix()
            manifest['sprites'][str(sid)]={'image_ids':image_ids,'states':states,'cell_count':len(cells)}
        except (KeyError,ValueError,IndexError) as exc:
            manifest['errors'][str(sid)]=str(exc)
(ROOT/'prototype/data/vqsv6_effect_sprites.json').write_text(json.dumps(manifest,ensure_ascii=False,indent=2),encoding='utf8')
print(f"Decoded {len(manifest['sprites'])}/{len(sprite_ids)} sprites; errors: {manifest['errors']}")
