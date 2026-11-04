"""Preserve source resources for future battle-effect reverse engineering."""
import hashlib
import json
import os
from pathlib import Path, PurePosixPath
import zipfile

ROOT = Path(__file__).resolve().parents[1]
source = Path(os.environ.get('VQSV_SOURCE_ROOT', r'D:\TOAN BO FILE\Download\game'))
dest = ROOT / 'reference_vqsv_fix_2_7/effect_sources'
prefix = 'exe/VQSV Liet Hoa Rebuild/app/modules/'
manifest = []
def save(group, rel, payload, original):
    p = PurePosixPath(rel)
    if p.is_absolute() or '..' in p.parts: raise ValueError('Unsafe ZIP path')
    target = dest / group / Path(*p.parts)
    target.parent.mkdir(parents=True, exist_ok=True)
    target.write_bytes(payload)
    manifest.append({'group':group, 'source':original, 'path':target.relative_to(ROOT).as_posix(), 'sha256':hashlib.sha256(payload).hexdigest()})
with zipfile.ZipFile(source/'VQSV_FIX_2.7.zip') as z:
    for item in z.infolist():
        if item.is_dir(): continue
        for prefix_part, group in ((prefix+'spr/original/', 'vqsv6_sprites'), (prefix+'img/decoded/', 'vqsv6_images')):
            if item.filename.startswith(prefix_part): save(group, item.filename[len(prefix_part):], z.read(item), item.filename)
with zipfile.ZipFile(source/'vqsv5.jar') as z:
    for item in z.infolist():
        if not item.is_dir() and item.filename.startswith('data/battle/'):
            save('vqsv5_battle', item.filename[len('data/battle/'):], z.read(item), item.filename)
(dest/'manifest.json').write_text(json.dumps(manifest, ensure_ascii=False, indent=2),encoding='utf8')
print(f'Preserved {len(manifest)} candidate source files and SHA-256 manifest; mappings remain unverified.')
