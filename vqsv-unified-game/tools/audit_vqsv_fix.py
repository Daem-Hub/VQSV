"""Inventory the supplied rebuild and compare its original resources to VQSV6."""
import collections
import csv
import hashlib
import os
import zipfile
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
SOURCE = Path(os.environ.get("VQSV_SOURCE_ROOT", r"D:\TOAN BO FILE\Download\game"))
ZIP = SOURCE / "VQSV_FIX_2.7.zip"
JAR = SOURCE / "vqsv-6.jar"
PREFIX = "exe/VQSV Liet Hoa Rebuild/app/modules/"

with zipfile.ZipFile(ZIP) as rebuild, zipfile.ZipFile(JAR) as original:
    jar_names = set(original.namelist())
    counts = collections.Counter()
    rows = []
    for item in rebuild.infolist():
        name = item.filename
        if not name.startswith(PREFIX) or item.is_dir():
            continue
        parts = name[len(PREFIX):].split("/")
        group, kind = parts[0], parts[1] if len(parts) > 1 else "manifest"
        counts[(group, kind)] += 1
        if kind != "original":
            continue
        relative = "/".join(parts[2:])
        jar_name = f"data/{group}/{relative}"
        data = rebuild.read(name)
        status = "not_found" if jar_name not in jar_names else "identical" if data == original.read(jar_name) else "different"
        rows.append({"group": group, "rebuild_path": name, "vqsv6_path": jar_name if jar_name in jar_names else "", "status": status, "sha256": hashlib.sha256(data).hexdigest()})

output = ROOT / "asset_review" / "vqsv_fix_2_7_inventory.csv"
with output.open("w", encoding="utf-8-sig", newline="") as f:
    writer = csv.DictWriter(f, fieldnames=["group", "rebuild_path", "vqsv6_path", "status", "sha256"])
    writer.writeheader()
    writer.writerows(rows)
print("Groups:", dict(sorted(counts.items())))
print("Comparison:", dict(sorted(collections.Counter((r["group"], r["status"]) for r in rows).items())))
print("Wrote", output)
