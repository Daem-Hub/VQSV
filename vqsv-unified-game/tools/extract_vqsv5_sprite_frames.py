from __future__ import annotations

import csv
import os
import struct
import zipfile
from pathlib import Path
from PIL import Image, ImageDraw, ImageFont

PROJECT = Path(__file__).resolve().parents[1]
BUNDLED_SOURCE = PROJECT / "sources" / "game"
SOURCE_ROOT = Path(os.environ.get("VQSV_SOURCE_ROOT", str(BUNDLED_SOURCE if BUNDLED_SOURCE.exists() else Path(r"D:\TOAN BO FILE\Download\game"))))
GAME = SOURCE_ROOT / "vqsv5"
JAR = SOURCE_ROOT / "vqsv5.jar"
OUT = PROJECT / "asset_review" / "vqsv5_npc2_frames"
OUT.mkdir(parents=True, exist_ok=True)

mon_n = (GAME / "raw_data" / "data" / "monN.d").read_bytes()[2:]
mon_refs: dict[int, list[int]] = {}
for index, atlas in enumerate(mon_n):
    mon_refs.setdefault(atlas, []).append(index)


def extract_rectangles(blob: bytes):
    if len(blob) < 3:
        raise ValueError("metadata too short")
    declared = struct.unpack_from(">H", blob)[0]
    data = blob[2:2 + declared]
    if len(data) != declared:
        raise ValueError("metadata length header mismatch")
    wide = data[0] == 0
    pos = 0

    def read_count():
        nonlocal pos
        if wide:
            if pos + 2 > len(data):
                raise ValueError("truncated short count")
            value = struct.unpack_from(">H", data, pos)[0]
            pos += 2
            return value
        if pos >= len(data):
            raise ValueError("truncated byte count")
        value = data[pos]
        pos += 1
        return value

    count = read_count()
    rects = []
    for _ in range(count):
        size = read_count()
        if wide:
            if size != 4 or pos + 8 > len(data):
                raise ValueError("unexpected wide rectangle record")
            rect = struct.unpack_from(">HHHH", data, pos)
            pos += 8
        else:
            if size != 4 or pos + 4 > len(data):
                raise ValueError("unexpected byte rectangle record")
            rect = tuple(data[pos:pos + 4])
            pos += 4
        rects.append(rect)
    return rects, ("u16 big-endian" if wide else "u8"), declared


rows = []
with zipfile.ZipFile(JAR) as jar:
    atlas_ids = sorted(int(path.stem) for path in (GAME / "images" / "data" / "npc2").glob("*.png") if path.stem.isdigit())
    for atlas in atlas_ids:
        image_path = GAME / "images" / "data" / "npc2" / f"{atlas}.png"
        meta_name = f"data/npc2/{atlas}.data"
        image = Image.open(image_path).convert("RGBA")
        rects, mode, payload_bytes = extract_rectangles(jar.read(meta_name))
        cells = []
        for frame_index, (x, y, w, h) in enumerate(rects):
            valid = w > 0 and h > 0 and x + w <= image.width and y + h <= image.height
            if not valid:
                raise ValueError(f"npc2/{atlas} frame {frame_index} exceeds atlas bounds: {(x,y,w,h)}")
            crop = image.crop((x, y, x + w, y + h))
            rows.append({
                "atlas_id": atlas, "frame_index": frame_index, "monN_indices": ",".join(map(str, mon_refs.get(atlas, []))),
                "x": x, "y": y, "width": w, "height": h, "rect_encoding": mode,
                "meaning": "Atlas crop rectangle; species-to-frame link not established"
            })
            cells.append((frame_index, crop))
        columns, cell_w, cell_h, label_h = 8, 104, 92, 20
        sheet = Image.new("RGB", (columns * cell_w, ((len(cells)+columns-1)//columns)*(cell_h+label_h)), "#25252b")
        draw = ImageDraw.Draw(sheet)
        try:
            font = ImageFont.truetype("arial.ttf", 12)
        except OSError:
            font = ImageFont.load_default()
        for i, (frame_index, crop) in enumerate(cells):
            col, row = i % columns, i // columns
            x0, y0 = col * cell_w, row * (cell_h + label_h)
            crop.thumbnail((cell_w - 8, cell_h - 8), Image.Resampling.NEAREST)
            tile = Image.new("RGBA", (cell_w - 8, cell_h - 8), "#3a3a42")
            tile.alpha_composite(crop, ((tile.width - crop.width)//2, (tile.height - crop.height)//2))
            sheet.paste(tile.convert("RGB"), (x0 + 4, y0 + 4))
            draw.text((x0 + 4, y0 + cell_h), f"{atlas}:{frame_index}", fill="white", font=font)
        sheet.save(OUT / f"npc2_{atlas}_frames.png")

csv_path = PROJECT / "asset_review" / "vqsv5_npc2_frames.csv"
with csv_path.open("w", encoding="utf-8-sig", newline="") as f:
    writer = csv.DictWriter(f, fieldnames=list(rows[0]))
    writer.writeheader()
    writer.writerows(rows)
print(f"Decoded {len(rows)} crop rectangles across {len(atlas_ids)} numeric atlases.")
print(f"Contact sheets: {OUT}")
print(f"Frame index: {csv_path}")
