from pathlib import Path
import csv
import os
from PIL import Image, ImageDraw, ImageFont


PROJECT = Path(__file__).resolve().parents[1]
BUNDLED_SOURCE = PROJECT / "sources" / "game"
ROOT = Path(os.environ.get("VQSV_SOURCE_ROOT", str(BUNDLED_SOURCE if BUNDLED_SOURCE.exists() else Path(r"D:\TOAN BO FILE\Download\game"))))
OUT = Path(__file__).resolve().parents[1] / "asset_review"
OUT.mkdir(parents=True, exist_ok=True)


def sheet(items, destination, columns=10, cell=(112, 124), label_height=22):
    rows = (len(items) + columns - 1) // columns
    canvas = Image.new("RGB", (columns * cell[0], rows * (cell[1] + label_height)), "#25252b")
    draw = ImageDraw.Draw(canvas)
    try:
        font = ImageFont.truetype("arial.ttf", 12)
    except OSError:
        font = ImageFont.load_default()
    for index, (label, path) in enumerate(items):
        x = (index % columns) * cell[0]
        y = (index // columns) * (cell[1] + label_height)
        try:
            with Image.open(path) as source:
                source = source.convert("RGBA")
                source.thumbnail((cell[0] - 8, cell[1] - 8), Image.Resampling.NEAREST)
                tile = Image.new("RGBA", (cell[0] - 8, cell[1] - 8), "#3a3a42")
                px = (tile.width - source.width) // 2
                py = (tile.height - source.height) // 2
                tile.alpha_composite(source, (px, py))
                canvas.paste(tile.convert("RGB"), (x + 4, y + 4))
            draw.text((x + 4, y + cell[1]), label, fill="white", font=font)
        except Exception as exc:
            draw.text((x + 4, y + 4), f"ERROR {label}: {exc}", fill="#ff7777", font=font)
    canvas.save(destination)


v5 = ROOT / "vqsv5" / "images" / "data"
npcs = []
for group in ("npc0", "npc1", "npc2"):
    for path in sorted((v5 / group).glob("*.png"), key=lambda p: (not p.stem.isdigit(), int(p.stem) if p.stem.isdigit() else p.stem)):
        npcs.append((f"{group}/{path.stem}", path))
sheet(npcs, OUT / "vqsv5_npc_contact.png", columns=12, cell=(108, 112))

battles = [(path.stem, path) for path in sorted((v5 / "battle").glob("*.png"), key=lambda p: p.name)]
sheet(battles, OUT / "vqsv5_battle_contact.png", columns=6, cell=(160, 150))

v6_images = ROOT / "vqsv6" / "images" / "data" / "img"
images = [(path.stem, path) for path in sorted(v6_images.glob("*.png"), key=lambda p: (not p.stem.startswith("img_"), int(p.stem.split("_")[-1]) if p.stem.split("_")[-1].isdigit() else p.stem))]
sheet(images, OUT / "vqsv6_img_contact.png", columns=12, cell=(108, 112))

maps = Path(__file__).resolve().parents[1] / "assets" / "vqsv5" / "maps" / "png"
map_items = [(f"map_{i:02d}", maps / f"map_{i:02d}.png") for i in range(11, 21) if (maps / f"map_{i:02d}.png").exists()]
sheet(map_items, OUT / "vqsv5_maps_11_20_contact.png", columns=5, cell=(240, 190))

tileset_dir = Path(__file__).resolve().parents[1] / "assets" / "vqsv5" / "maps" / "tilesets"
tilesets = [(path.stem, path) for path in sorted(tileset_dir.glob("*.png"), key=lambda p: p.name)]
sheet(tilesets, OUT / "vqsv5_tilesets_contact.png", columns=4, cell=(240, 220))

records = []
for group, paths in (
    ("vqsv5_npc", [path for _, path in npcs]),
    ("vqsv5_battle", [path for _, path in battles]),
    ("vqsv6_img", [path for _, path in images]),
    ("vqsv5_map", [path for _, path in map_items]),
    ("vqsv5_tileset", [path for _, path in tilesets]),
):
    for path in paths:
        with Image.open(path) as im:
            records.append({"group": group, "asset_id": path.stem, "relative_path": str(path.relative_to(ROOT if ROOT in path.parents else Path(__file__).resolve().parents[1])), "width": im.width, "height": im.height})
with (OUT / "visual_asset_index.csv").open("w", newline="", encoding="utf-8-sig") as handle:
    writer = csv.DictWriter(handle, fieldnames=("group", "asset_id", "relative_path", "width", "height"))
    writer.writeheader()
    writer.writerows(records)

print(f"Created {len(npcs)} VQSV5 NPC thumbnails, {len(battles)} battle images, {len(images)} VQSV6 images, {len(map_items)} map previews, and {len(tilesets)} tileset previews in {OUT}")
