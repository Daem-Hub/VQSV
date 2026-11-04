"""Read evidence from the supplied fallback and compare VQSV6 animation tables."""
import base64
import hashlib
import json
import os
import struct
import zipfile
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
source = Path(os.environ.get('VQSV_SOURCE_ROOT', r'D:\TOAN BO FILE\Download\game'))
fallback = source / 'tn moi/vqsv-libgdx-fallback.js'
save = source / 'tn moi/YWN0b3IuMD03LDI2LDAsMQphY3Rvci4xPTU.txt'
js = fallback.read_text(encoding='utf8')
checks = ['vbd_VqsvBattleAnimationTables_effectRow', '$this.$p7EffectRow = vbd_VqsvBattleAnimationTables_effectRow', 'effect.mid', 'speffect.mid', 'bufDebuf.mid']
if any(term not in js for term in checks): raise ValueError('Fallback lacks expected battle reference')

def byte_rows(blob):
    cursor = 0
    def short():
        nonlocal cursor
        if cursor + 2 > len(blob): raise ValueError('Truncated row header')
        value = struct.unpack_from('>h', blob, cursor)[0]; cursor += 2; return value
    count = short()
    if count < 0: raise ValueError('Negative row count')
    rows=[]
    for _ in range(count):
        size=short()
        if size < 0 or cursor+size > len(blob): raise ValueError('Invalid row size')
        rows.append(list(struct.unpack_from(f'>{size}b', blob, cursor))); cursor += size
    if cursor != len(blob): raise ValueError(f'Unparsed effect data: {len(blob)-cursor} bytes')
    return rows

original = byte_rows((source/'vqsv6/raw_data/data/script/effect.mid').read_bytes())
zip_path = 'exe/VQSV Liet Hoa Rebuild/app/modules/script/original/effect.mid'
with zipfile.ZipFile(source/'VQSV_FIX_2.7.zip') as z: rebuilt = byte_rows(z.read(zip_path))
overlay_path = ROOT/'asset_review/vqsv6_effect_rows.json'
overlay_path.write_text(json.dumps({'vqsv6_original':original, 'fix_2_7':rebuilt},ensure_ascii=False,indent=2),encoding='utf8')
catalog_path = ROOT/'prototype/data/original_skills.json'
catalog = json.loads(catalog_path.read_text(encoding='utf8'))
skills = [x for x in catalog['records'] if x['game'] == 'VQSV6']
if len(original) < len(skills) or len(rebuilt) < len(skills): raise ValueError('Missing effect rows')
for skill in skills:
    i = skill['source_index']
    skill['effect_source_rows'] = {'vqsv6_original':original[i], 'fix_2_7':rebuilt[i], 'note':'Dòng được bản fallback tra theo mã kỹ năng; ý nghĩa các byte và animation chưa xác minh.'}
catalog_path.write_text(json.dumps(catalog,ensure_ascii=False,indent=2),encoding='utf8')
decoded=base64.b64decode(save.read_bytes(), validate=True)
lines=decoded.decode('utf8').splitlines()
actor_count=next((int(x.split('=',1)[1]) for x in lines if x.startswith('actor.count=')),None)
actor_rows=sum(x.startswith('actor.') and not x.startswith('actor.count=') for x in lines)
report={'fallback_sha256':hashlib.sha256(fallback.read_bytes()).hexdigest(), 'evidence_symbols':checks,
        'effect_rows_original':len(original),'effect_rows_fix':len(rebuilt),'skill_rows_linked':len(skills),
        'different_skill_rows':sum(original[i]!=rebuilt[i] for i in range(len(skills))),
        'save_bytes_base64':save.stat().st_size,'save_bytes_decoded':len(decoded),'save_ends_with_newline':decoded.endswith(b'\n'),
        'save_declared_actors':actor_count,'save_actor_rows_present':actor_rows,
        'save_note':'Input ends mid-line and actor count does not match; do not import as a complete save.'}
out=ROOT/'asset_review/fallback_reference_analysis.json'
out.write_text(json.dumps(report,ensure_ascii=False,indent=2),encoding='utf8')
print(json.dumps(report,ensure_ascii=False))
