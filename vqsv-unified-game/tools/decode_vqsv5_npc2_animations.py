from __future__ import annotations

import json
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
FRAME_INDEX = PROJECT / "asset_review" / "vqsv5_npc2_frames.csv"
OUT = PROJECT / "asset_review" / "vqsv5_npc2_animations"
OUT.mkdir(parents=True, exist_ok=True)
FRAME_OUT = PROJECT / "prototype" / "assets" / "pets" / "vqsv5"
mon_n = (GAME / "raw_data" / "data" / "monN.d").read_bytes()[2:]
atlas_refs: dict[int, list[int]] = {}
for index, atlas in enumerate(mon_n):
    atlas_refs.setdefault(atlas, []).append(index)


def decode(blob: bytes):
    size = struct.unpack_from(">H", blob)[0]
    data = blob[2:2 + size]
    if len(data) != size:
        raise ValueError("metadata size mismatch")
    wide = data[0] == 0
    p = 0

    def count():
        nonlocal p
        if wide:
            value = struct.unpack_from(">H", data, p)[0]
            p += 2
            return value
        value = data[p]
        p += 1
        return value

    def two_dimensional():
        nonlocal p
        result = []
        for _ in range(count()):
            n = count()
            if wide:
                result.append(list(struct.unpack_from(">" + "H" * n, data, p)))
                p += n * 2
            else:
                result.append(list(data[p:p + n]))
                p += n
        return result

    def three_dimensional():
        return [two_dimensional() for _ in range(count())]

    rectangles = two_dimensional()
    compositions = three_dimensional()
    sequences = three_dimensional()
    if p != len(data):
        raise ValueError(f"unparsed metadata tail: {len(data)-p} bytes")
    return rectangles, compositions, sequences, ("u16 big-endian" if wide else "u8")


def signed(value, bits):
    return value - (1 << bits) if value >= (1 << (bits - 1)) else value


def apply_source_transform(crop, flag):
    # ah.b[[B[0][flag] indexes ah.b[S = [0,5,3,6,2,4,1,7],
    # whose values are MIDP Sprite drawRegion transform constants.
    transforms = {
        0: None,
        1: Image.Transpose.ROTATE_270,
        2: Image.Transpose.ROTATE_180,
        3: Image.Transpose.ROTATE_90,
        4: Image.Transpose.FLIP_LEFT_RIGHT,
        5: (Image.Transpose.FLIP_LEFT_RIGHT, Image.Transpose.ROTATE_90),
        6: Image.Transpose.FLIP_TOP_BOTTOM,
        7: (Image.Transpose.FLIP_LEFT_RIGHT, Image.Transpose.ROTATE_270),
    }
    if flag not in transforms:
        raise ValueError(f"Unimplemented VQSV5 source transform flag: {flag}")
    transform = transforms[flag]
    if transform is None:
        return crop
    for operation in transform if isinstance(transform, tuple) else (transform,):
        crop = crop.transpose(operation)
    return crop


all_data = {}
gallery_data = {"source": "VQSV5 npc2 metadata", "frame_duration_ms_per_tick": 60, "timing_note": "Approximation from the 60 ms target in ag.run; exact pacing depends on the original game loop.", "atlases": {}}
with zipfile.ZipFile(JAR) as jar:
    atlas_dir = GAME / "images" / "data" / "npc2"
    atlas_ids = sorted(int(path.stem) for path in atlas_dir.glob("*.png") if path.stem.isdigit())
    for atlas_id in atlas_ids:
        raw = jar.read(f"data/npc2/{atlas_id}.data")
        rectangles, compositions, sequences, encoding = decode(raw)
        atlas = Image.open(GAME / "images" / "data" / "npc2" / f"{atlas_id}.png").convert("RGBA")
        bit_width = 16 if encoding.startswith("u16") else 8
        checked_comps = []
        bounds = [0, 0, 1, 1]
        for comp_id, parts in enumerate(compositions):
            checked_parts = []
            for part in parts:
                if len(part) != 4 or part[0] >= len(rectangles):
                    raise ValueError(f"bad part in npc2/{atlas_id} composition {comp_id}: {part}")
                rect_id = part[0]
                dx, dy = signed(part[1], bit_width), signed(part[2], bit_width)
                flag = part[3]
                x, y, w, h = rectangles[rect_id]
                if x + w > atlas.width or y + h > atlas.height or not w or not h:
                    raise ValueError(f"crop outside npc2/{atlas_id}: {rectangles[rect_id]}")
                bounds[0] = min(bounds[0], dx)
                bounds[1] = min(bounds[1], dy)
                transformed = apply_source_transform(atlas.crop((x, y, x + w, y + h)), flag)
                bounds[2] = max(bounds[2], dx + transformed.width)
                bounds[3] = max(bounds[3], dy + transformed.height)
                checked_parts.append({"rect_id": rect_id, "offset_x": dx, "offset_y": dy, "flag": flag})
            checked_comps.append(checked_parts)
        checked_sequences = []
        for seq_id, sequence in enumerate(sequences):
            clean = []
            for item in sequence:
                if len(item) != 2 or item[0] >= len(compositions):
                    raise ValueError(f"bad animation reference in npc2/{atlas_id} sequence {seq_id}: {item}")
                clean.append({"composition_id": item[0], "duration": item[1]})
            checked_sequences.append(clean)

        margin = 3
        shift_x, shift_y = margin - bounds[0], margin - bounds[1]
        canvas_size = (bounds[2] - bounds[0] + margin * 2, bounds[3] - bounds[1] + margin * 2)
        frames = []
        for parts in checked_comps:
            frame = Image.new("RGBA", canvas_size, (0, 0, 0, 0))
            for part in parts:
                x, y, w, h = rectangles[part["rect_id"]]
                crop = apply_source_transform(atlas.crop((x, y, x + w, y + h)), part["flag"])
                frame.alpha_composite(crop, (shift_x + part["offset_x"], shift_y + part["offset_y"]))
            frames.append(frame)

        frame_dir = FRAME_OUT / f"atlas_{atlas_id}"
        frame_dir.mkdir(parents=True, exist_ok=True)
        for i, frame in enumerate(frames):
            frame.save(frame_dir / f"pose_{i}.png")
        gallery_data["atlases"][str(atlas_id)] = {
            "monN_indices": atlas_refs.get(atlas_id, []),
            "sequences": [[{"image": f"assets/pets/vqsv5/atlas_{atlas_id}/pose_{item['composition_id']}.png", "duration_ticks": item["duration"]} for item in seq] for seq in checked_sequences],
            "sequence_previews": [next((f"assets/pets/vqsv5/atlas_{atlas_id}/pose_{item['composition_id']}.png" for item in seq if frames[item["composition_id"]].getbbox()), "") for seq in checked_sequences]
        }

        cols, cell_w, cell_h, label_h = 5, 120, 102, 20
        sheet = Image.new("RGB", (cols * cell_w, ((len(frames) + cols - 1) // cols) * (cell_h + label_h)), "#25252b")
        draw = ImageDraw.Draw(sheet)
        try:
            font = ImageFont.truetype("arial.ttf", 12)
        except OSError:
            font = ImageFont.load_default()
        for i, frame in enumerate(frames):
            col, row = i % cols, i // cols
            x0, y0 = col * cell_w, row * (cell_h + label_h)
            preview = frame.copy()
            preview.thumbnail((cell_w - 8, cell_h - 8), Image.Resampling.NEAREST)
            tile = Image.new("RGBA", (cell_w - 8, cell_h - 8), "#3a3a42")
            tile.alpha_composite(preview, ((tile.width - preview.width) // 2, (tile.height - preview.height) // 2))
            sheet.paste(tile.convert("RGB"), (x0 + 4, y0 + 4))
            draw.text((x0 + 4, y0 + cell_h), f"{atlas_id}:pose {i}", fill="white", font=font)
        sheet.save(OUT / f"npc2_{atlas_id}_composites.png")

        all_data[str(atlas_id)] = {
            "source": f"vqsv5/data/npc2/{atlas_id}.data",
            "monN_indices": atlas_refs.get(atlas_id, []),
            "number_encoding": encoding,
            "rectangles_xywh": rectangles,
            "compositions": checked_comps,
            "animation_sequences": checked_sequences,
            "preview_flags_applied": True,
            "note": "Composition/frame groups are source metadata. Transform mapping follows ah.class and MIDP drawRegion; species names remain unresolved."
        }

json_path = PROJECT / "asset_review" / "vqsv5_npc2_animation_data.json"
json_path.write_text(json.dumps(all_data, ensure_ascii=False, indent=2), encoding="utf-8")
gallery_path = PROJECT / "prototype" / "data" / "vqsv5_pet_animations.json"
gallery_path.write_text(json.dumps(gallery_data, ensure_ascii=False, indent=2), encoding="utf-8")
print(f"Decoded {sum(len(v['rectangles_xywh']) for v in all_data.values())} crops, {sum(len(v['compositions']) for v in all_data.values())} compositions, and {sum(len(v['animation_sequences']) for v in all_data.values())} animation sequences across {len(all_data)} atlases.")
print(f"JSON: {json_path}")
print(f"Composite previews: {OUT}")
print(f"Gallery animations: {gallery_path}; frames: {FRAME_OUT}")
