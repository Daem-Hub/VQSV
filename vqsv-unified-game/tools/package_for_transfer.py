"""Create a verified project snapshot with original game sources for another PC."""
import argparse
import hashlib
import json
import os
from datetime import datetime, timezone
from pathlib import Path
from zipfile import ZIP_DEFLATED, ZipFile


project = Path(__file__).resolve().parents[1]
parser = argparse.ArgumentParser()
parser.add_argument("--source-root", type=Path, default=Path(os.environ.get("VQSV_SOURCE_ROOT", str(project / "sources" / "game"))))
parser.add_argument("--output", required=True, type=Path)
args = parser.parse_args()
source = args.source_root.resolve()
output = args.output.resolve()
if not source.is_dir():
    raise SystemExit(f"Missing source folder: {source}")
if output.is_relative_to(project) or output.is_relative_to(source):
    raise SystemExit("Output must be outside the input folders")
if output.exists():
    raise SystemExit("Refusing to overwrite an existing snapshot")
output.parent.mkdir(parents=True, exist_ok=True)
excluded = {".git", ".venv", "node_modules", "__pycache__", ".codex"}
inputs = {}
for path in sorted(project.rglob("*")):
    relative = path.relative_to(project)
    if path.is_file() and not any(part in excluded for part in relative.parts) and not (len(relative.parts) >= 2 and relative.parts[:2] == ("sources", "game")):
        inputs[relative.as_posix()] = path
for path in sorted(source.rglob("*")):
    if path.is_file():
        inputs["sources/game/" + path.relative_to(source).as_posix()] = path
manifest = {"created_utc": datetime.now(timezone.utc).isoformat(), "project": "vqsv-unified-game", "files": []}
with ZipFile(output, "w", compression=ZIP_DEFLATED, compresslevel=6) as archive:
    for relative, path in inputs.items():
        payload = path.read_bytes()
        manifest["files"].append({"path": relative, "size": len(payload), "sha256": hashlib.sha256(payload).hexdigest()})
        archive.writestr("vqsv-unified-game/" + relative, payload)
    archive.writestr("vqsv-unified-game/PACKAGE_MANIFEST.json", json.dumps(manifest, ensure_ascii=False, indent=2).encode("utf-8"))
with ZipFile(output) as archive:
    for record in manifest["files"]:
        data = archive.read("vqsv-unified-game/" + record["path"])
        if hashlib.sha256(data).hexdigest() != record["sha256"]:
            raise RuntimeError(f"Archive verification failed: {record['path']}")
digest = hashlib.sha256(output.read_bytes()).hexdigest()
md5 = hashlib.md5(output.read_bytes()).hexdigest()
checksum_path = output.with_suffix(".sha256.txt")
checksum_path.write_text(f"{digest}  {output.name}\n", encoding="utf-8")
print(json.dumps({"archive": str(output), "files": len(inputs), "size_bytes": output.stat().st_size, "sha256": digest, "md5": md5, "verification": "all archived file hashes matched", "checksum_file": str(checksum_path)}, ensure_ascii=False))
