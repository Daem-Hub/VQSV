import struct
import os
import zipfile
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
BUNDLED_SOURCE = ROOT / "sources" / "game"
GAME = Path(os.environ.get("VQSV_SOURCE_ROOT", str(BUNDLED_SOURCE if BUNDLED_SOURCE.exists() else Path(r"D:\TOAN BO FILE\Download\game"))))
TERMS = ("monn", "montype", "npc2", "img_", "pet", "monster", "mon.", "mon_", "gamee", "gamed", "gamec", "gameb", "/data/")

def class_strings(data):
    if data[:4] != b"\xca\xfe\xba\xbe":
        return []
    p = 8
    count = struct.unpack_from(">H", data, p)[0]
    p += 2
    strings = []
    i = 1
    while i < count:
        tag = data[p]
        p += 1
        if tag == 1:
            n = struct.unpack_from(">H", data, p)[0]
            p += 2
            s = data[p:p+n].decode("utf-8", "replace")
            p += n
            strings.append(s)
        elif tag in (3, 4): p += 4
        elif tag in (5, 6): p += 8; i += 1
        elif tag in (7, 8, 16, 19, 20): p += 2
        elif tag in (9, 10, 11, 12, 17, 18): p += 4
        elif tag == 15: p += 3
        else: raise ValueError(f"unknown constant pool tag {tag} at {p-1}")
        i += 1
    return strings

for jar_path in (GAME / "vqsv5.jar", GAME / "vqsv-6.jar"):
    print(f"\n=== {jar_path.name} ===")
    with zipfile.ZipFile(jar_path) as jar:
        matches = []
        for item in jar.infolist():
            if not item.filename.endswith(".class"):
                continue
            vals = class_strings(jar.read(item))
            picked = [v for v in vals if any(term in v.lower() for term in TERMS)]
            if picked:
                matches.append((item.filename, picked))
        for name, vals in matches[:80]:
            print(name, " | ".join(vals[:14]))
        print(f"classes with pet/data references: {len(matches)}")
