"""Create a visual index of all VQSV5 source pet records."""
import json
from pathlib import Path
from PIL import Image, ImageDraw, ImageFont

project = Path(__file__).resolve().parents[1]
prototype = project / "prototype"
catalog = json.loads((prototype / "data" / "vqsv5_pet_catalog.json").read_text(encoding="utf-8"))["records"]
atlases = json.loads((prototype / "data" / "vqsv5_pet_animations.json").read_text(encoding="utf-8"))["atlases"]
columns, cell_w, cell_h = 8, 140, 145
sheet = Image.new("RGB", (columns * cell_w, ((len(catalog) + columns - 1) // columns) * cell_h), "#1d2522")
draw = ImageDraw.Draw(sheet)
try:
    font = ImageFont.truetype("arial.ttf", 12)
except OSError:
    font = ImageFont.load_default()

def fit_lines(text, width):
    lines, current = [], ""
    for word in text.split():
        candidate = (current + " " + word).strip()
        if current and draw.textlength(candidate, font=font) > width:
            lines.append(current)
            current = word
        else:
            current = candidate
    if current:
        lines.append(current)
    if len(lines) > 2:
        last = lines[1]
        while last and draw.textlength(last + "…", font=font) > width:
            last = last[:-1]
        lines = [lines[0], last + "…"]
    return lines

for index, record in enumerate(catalog):
    x, y = index % columns * cell_w, index // columns * cell_h
    draw.rounded_rectangle((x + 3, y + 3, x + cell_w - 4, y + cell_h - 4), radius=6, fill="#304336")
    atlas = atlases[str(record["animation"]["atlas_id"])]
    preview_path = atlas["sequence_previews"][record["animation"]["sequence_ids"][0]]
    image = Image.open(prototype / preview_path).convert("RGBA")
    bbox = image.getbbox()
    if bbox:
        image = image.crop(bbox)
        scale = min(116 / image.width, 91 / image.height)
        image = image.resize((max(1, round(image.width * scale)), max(1, round(image.height * scale))), Image.Resampling.NEAREST)
        sheet.paste(image, (x + (cell_w - image.width) // 2, y + 5 + (91 - image.height) // 2), image)
    label = f"#{record['source_ids']['db_id']} {record['display_name']}"
    for line_index, line in enumerate(fit_lines(label, cell_w - 12)):
        draw.text((x + 6, y + 99 + line_index * 15), line, fill="#f4f7e8", font=font)

output = project / "asset_review" / "vqsv5_pet_102_contact.png"
sheet.save(output)
print(f"Wrote overview for {len(catalog)} VQSV5 pet records: {output}")
