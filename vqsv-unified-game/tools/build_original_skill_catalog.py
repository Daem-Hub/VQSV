"""Extract source skill tables and VQSV5 battle sprite animations without guessing mappings."""
import ast
import json
import os
from pathlib import Path
from PIL import Image
import struct
import zipfile

ROOT = Path(__file__).resolve().parents[1]
bundled = ROOT / 'sources/game'
source = Path(os.environ.get('VQSV_SOURCE_ROOT', str(bundled if bundled.exists() else Path(r'D:\TOAN BO FILE\Download\game'))))
blob = (source / 'vqsv5/raw_data/data/data.d').read_bytes()
if int.from_bytes(blob[:2], 'big') != len(blob) - 2:
    raise ValueError('Unexpected VQSV5 data.d length')
data = memoryview(blob)[2:]; offset = 0
def take(n):
    global offset
    if offset+n > len(data): raise ValueError('Truncated data.d')
    out = data[offset:offset+n]; offset += n; return out
def byte(): return int(take(1)[0])
def matrix(): return [list(take(byte())) for _ in range(byte())]
def strings(): return [bytes(take(byte()*2)).decode('utf-16-be') for _ in range(byte())]
tables = [matrix() for _ in range(3)]
text = [strings() for _ in range(9)]
if not (len(tables[2]) == len(text[0]) == len(text[1]) == 49): raise ValueError('Unexpected VQSV5 skill table')
records = []
for i, (name, category, raw) in enumerate(zip(text[1], text[0], tables[2])):
    records.append({'id':f'vqsv5-skill-{i}', 'game':'VQSV5', 'source_index':i, 'name':name, 'source_label':category, 'raw_fields':raw, 'note':'Số liệu gốc chưa giải nghĩa; chưa nối pet hoặc hiệu ứng.'})

tables6 = json.loads((ROOT / 'asset_review/vqsv6_db_sprite_numeric_tables.json').read_text(encoding='utf8'))
skills6 = tables6['db_mid_tables'][1]['rows']; names = tables6['chs_names']
if len(skills6) != 70: raise ValueError('Unexpected VQSV6 skill table')
for i, row in enumerate(skills6):
    if len(row) < 6 or any(idx < 0 or idx >= len(names) for idx in (row[1], row[2])): raise ValueError(f'Bad VQSV6 skill row {i}')
    records.append({'id':f'vqsv6-skill-{i}', 'game':'VQSV6', 'source_index':i, 'name':names[row[1]], 'description':names[row[2]], 'max_uses':row[5], 'raw_fields':row, 'note':'Tên, mô tả và số lần từ màn chọn kỹ năng gốc; hiệu ứng chưa nối.'})

# Reuse the tested MIDP sprite decoder without executing that module's export loop.
module = ast.parse((ROOT / 'tools/decode_vqsv5_npc2_animations.py').read_text(encoding='utf8'))
functions = [node for node in module.body if isinstance(node, ast.FunctionDef) and node.name in ('decode', 'signed', 'apply_source_transform')]
env = {'struct':struct, 'Image':Image}
exec(compile(ast.Module(body=functions, type_ignores=[]), '<sprite decoder>', 'exec'), env)
effects = []
with zipfile.ZipFile(source / 'vqsv5.jar') as jar:
    for number in range(6):
        rectangles, composites, sequences, encoding = env['decode'](jar.read(f'data/battle/s{number}.data'))
        atlas = Image.open(source / f'vqsv5/images/data/battle/s{number}.png').convert('RGBA')
        bits = 16 if encoding.startswith('u16') else 8
        parts_list = []; bounds = [0, 0, 1, 1]
        for composite in composites:
            parts = []
            for part in composite:
                if len(part) != 4 or part[0] >= len(rectangles): raise ValueError(f'Bad effect component s{number}')
                rid, dx, dy, flag = part; x,y,w,h = rectangles[rid]
                if x+w > atlas.width or y+h > atlas.height or not w or not h: raise ValueError(f'Bad crop s{number}')
                dx=env['signed'](dx,bits); dy=env['signed'](dy,bits)
                crop=env['apply_source_transform'](atlas.crop((x,y,x+w,y+h)),flag)
                bounds[0]=min(bounds[0],dx); bounds[1]=min(bounds[1],dy); bounds[2]=max(bounds[2],dx+crop.width); bounds[3]=max(bounds[3],dy+crop.height)
                parts.append((crop,dx,dy))
            parts_list.append(parts)
        folder = ROOT / f'prototype/assets/effects/vqsv5/s{number}'; folder.mkdir(parents=True, exist_ok=True)
        for i, parts in enumerate(parts_list):
            frame=Image.new('RGBA',(bounds[2]-bounds[0]+6,bounds[3]-bounds[1]+6))
            for crop,dx,dy in parts: frame.alpha_composite(crop,(dx-bounds[0]+3,dy-bounds[1]+3))
            frame.save(folder / f'pose_{i}.png')
        mapped=[]
        for sequence in sequences:
            frames=[]
            for item in sequence:
                if len(item)!=2 or item[0]>=len(composites): raise ValueError(f'Bad effect sequence s{number}')
                frames.append({'image':f'assets/effects/vqsv5/s{number}/pose_{item[0]}.png','duration_ticks':item[1]})
            mapped.append(frames)
        effects.append({'id':f'vqsv5-battle-s{number}', 'source':f'data/battle/s{number}.data + s{number}.png', 'sequences':mapped, 'note':'Hiệu ứng nguồn; chưa xác minh gắn với kỹ năng nào.'})
overlay_path=ROOT/'asset_review/vqsv6_effect_rows.json'
if overlay_path.exists():
    overlay=json.loads(overlay_path.read_text(encoding='utf8'))
    for skill in records:
        if skill['game'] != 'VQSV6': continue
        i=skill['source_index']
        if i >= len(overlay['vqsv6_original']) or i >= len(overlay['fix_2_7']): raise ValueError('Missing effect overlay row')
        skill['effect_source_rows']={'vqsv6_original':overlay['vqsv6_original'][i], 'fix_2_7':overlay['fix_2_7'][i], 'note':'Dòng được bản fallback tra theo mã kỹ năng; ý nghĩa các byte và animation chưa xác minh.'}
out={'records':records,'effects':effects,'frame_duration_ms_per_tick':60,'notes':'Thời gian khung là ước lượng. Chưa gán hiệu ứng hoặc dữ liệu chiến đấu chưa giải nghĩa cho pet.'}
(ROOT/'prototype/data/original_skills.json').write_text(json.dumps(out,ensure_ascii=False,indent=2),encoding='utf8')
print(f'{len(records)} skills ({len(text[1])} VQSV5, {len(skills6)} VQSV6); {len(effects)} source effect atlases, {sum(len(e["sequences"]) for e in effects)} sequences.')
