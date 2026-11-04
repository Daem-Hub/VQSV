"""Decode the sparse layer structure used by VQSV6 data/map/map_N.mid."""
import json
import pathlib
import struct
import os

PROJECT = pathlib.Path(__file__).resolve().parent
BUNDLED_SOURCE = PROJECT / "sources" / "game"
SOURCE_ROOT = pathlib.Path(os.environ.get("VQSV_SOURCE_ROOT", str(BUNDLED_SOURCE if BUNDLED_SOURCE.exists() else pathlib.Path(r"D:\TOAN BO FILE\Download\game"))))
SOURCE = SOURCE_ROOT / "vqsv6" / "raw_data" / "data" / "map"
OUT = pathlib.Path(__file__).parent / "vqsv6_maps"
OUT.mkdir(exist_ok=True)


def decode(path):
    data = path.read_bytes()
    if len(data) < 6:
        return {"source": path.name, "error": "short header", "size": len(data)}
    pos = 0
    mode, option, width, height, tile, layer_count = data[:6]
    pos = 6
    layers = []
    for layer_index in range(layer_count):
        if pos + 4 > len(data):
            raise ValueError(f"{path.name}: truncated layer {layer_index} header at {pos}")
        kind = struct.unpack_from(">b", data, pos)[0]; pos += 1
        encoding = data[pos]; pos += 1
        count = struct.unpack_from(">H", data, pos)[0]; pos += 2
        records = []
        for _ in range(count):
            coord_size = 1 if mode == 1 else 2
            if pos + coord_size * 2 + 2 > len(data):
                raise ValueError(f"{path.name}: truncated record at {pos}")
            if coord_size == 1:
                x, y = data[pos], data[pos + 1]
                pos += 2
            else:
                x, y = struct.unpack_from(">hh", data, pos)
                pos += 4
            raw = struct.unpack_from(">H", data, pos)[0]
            pos += 2
            records.append({
                "x": x, "y": y, "raw": raw,
                "tile_or_id": raw & 0x0fff,
                "variant": (raw & 0x7000) >> 12,
                "signed": raw - 65536 if raw >= 32768 else raw,
            })
        layers.append({"index": layer_index, "kind": kind, "encoding": encoding, "count": count, "records": records})
    return {
        "source": path.name,
        "map_id": int(path.stem.split("_")[-1]),
        "mode": mode,
        "option": option,
        "width": width,
        "height": height,
        "tile_size": tile,
        "layer_count": layer_count,
        "layers": layers,
        "bytes_read": pos,
        "trailing_bytes": len(data) - pos,
        "file_size": len(data),
    }


summaries = []
for path in sorted(SOURCE.glob("map_*.mid"), key=lambda p: int(p.stem.split("_")[-1])):
    result = decode(path)
    target = OUT / f"{path.stem}.json"
    target.write_text(json.dumps(result, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    summaries.append(result)

valid = [m for m in summaries if "error" not in m]
print(f"decoded={len(valid)} errors={len(summaries)-len(valid)} output={OUT}")
print("map,width,height,tile,layers,layer_types,records,trailing")
for m in valid:
    print(f"{m['map_id']},{m['width']},{m['height']},{m['tile_size']},{m['layer_count']},"
          f"{','.join(str(l['kind']) for l in m['layers'])},"
          f"{sum(l['count'] for l in m['layers'])},{m['trailing_bytes']}")
