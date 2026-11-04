import json
from pathlib import Path
from PIL import Image, ImageDraw, ImageFont


PROJECT = Path(__file__).resolve().parents[1]
TILESETS = PROJECT / "assets" / "vqsv5" / "maps" / "tilesets"
OUT = PROJECT / "asset_review"
OUT.mkdir(parents=True, exist_ok=True)

for tileset_id in (0, 4):
    metadata = json.loads((TILESETS / f"tileset_{tileset_id:02}.json").read_text(encoding="utf-8"))
    atlas = Image.open(TILESETS / f"tileset_{tileset_id:02}.png").convert("RGBA")
    cols, cell_w, cell_h, label_h = 6, 132, 112, 24
    rows = (len(metadata) + cols - 1) // cols
    sheet = Image.new("RGB", (cols * cell_w, rows * (cell_h + label_h)), "#25252b")
    draw = ImageDraw.Draw(sheet)
    try:
        font = ImageFont.truetype("arial.ttf", 14)
    except OSError:
        font = ImageFont.load_default()
    for i, tile in enumerate(metadata):
        x, y = (i % cols) * cell_w, (i // cols) * (cell_h + label_h)
        crop = atlas.crop((tile["src_x"], tile["src_y"], tile["src_x"] + tile["w"], tile["src_y"] + tile["h"]))
        crop.thumbnail((cell_w - 8, cell_h - 8), Image.Resampling.NEAREST)
        tile_bg = Image.new("RGBA", (cell_w - 8, cell_h - 8), "#3a3a42")
        tile_bg.alpha_composite(crop, ((tile_bg.width - crop.width) // 2, (tile_bg.height - crop.height) // 2))
        sheet.paste(tile_bg.convert("RGB"), (x + 4, y + 4))
        draw.text((x + 4, y + cell_h), f"id {tile['id']} ({tile['w']}x{tile['h']})", fill="white", font=font)
    sheet.save(OUT / f"vqsv5_tileset_{tileset_id:02}_tile_ids.png")
    print(f"tileset {tileset_id}: {len(metadata)} crops")
