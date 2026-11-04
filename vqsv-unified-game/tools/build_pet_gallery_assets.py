"""Copy roster portraits into the prototype so the pet book works offline."""
import json
import os
import shutil
from pathlib import Path

project = Path(__file__).resolve().parents[1]
bundled_source = project / "sources" / "game"
source_root = Path(os.environ.get("VQSV_SOURCE_ROOT", str(bundled_source if bundled_source.exists() else Path(r"D:\TOAN BO FILE\Download\game"))))
roster = json.loads((project / "prototype" / "data" / "pet_roster_v1.json").read_text(encoding="utf-8"))
destination = project / "prototype" / "assets" / "pets" / "vqsv6"
destination.mkdir(parents=True, exist_ok=True)
images = {record["source_ids"]["image_id"] for record in roster["records"] if record["source_ids"]["game"] == "VQSV6"}
for image_id in sorted(images):
    source = source_root / "vqsv6" / "images" / "data" / "img" / f"{image_id}.png"
    if not source.is_file():
        raise FileNotFoundError(f"Missing VQSV6 roster image: {source}")
    shutil.copy2(source, destination / source.name)
print(f"Copied {len(images)} VQSV6 portraits to {destination}")
