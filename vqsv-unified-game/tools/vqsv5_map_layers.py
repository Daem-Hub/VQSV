"""Read and render the ground and foreground layers of native VQSV5 field maps."""
import struct
from pathlib import Path
from PIL import Image

ROOT = Path(__file__).resolve().parents[1]
RAW = ROOT / "sources/game/vqsv5/raw_data/data/map"
IMG = ROOT / "sources/game/vqsv5/images/data/map"
T = 20
TBL = [0, 5, 3, 6, 2, 4, 1, 7]


def payload(path):
    b = path.read_bytes(); return b[2:2 + ((b[0] << 8) | b[1])]


def s8(b): return b - 256 if b > 127 else b
def s16(lo, hi): v = lo | (hi << 8); return v - 65536 if v > 32767 else v


def area():
    p = payload(RAW / "area.dat"); i = 0
    n = p[i]; i += 1; mp = list(p[i:i + n]); i += n
    k = p[i]; i += 1; sets = []
    for _ in range(k):
        ln = p[i]; i += 1; sets.append(list(p[i:i + ln])); i += ln
    return mp, sets


def defs(ts):
    p = payload(RAW / f"{ts}.data"); i = 0
    cnt = s8(p[i]) + 100; i += 1; out = []
    for _ in range(cnt):
        ln = s8(p[i]) + 100; i += 1; v = []
        for _ in range(ln): v.append(s16(p[i], p[i + 1])); i += 2
        out.append(v)
    return out


def parse(mid):
    b = (RAW / f"{mid}.mid").read_bytes(); i = 2
    bg = struct.unpack_from("<I", b, i)[0]; i += 4
    n = b[i]; i += 1; i += n
    w = b[i]; i += n; h = b[i]; i += n
    layers = {}
    for _ in range(3):
        if i >= len(b): break
        t = b[i]; i += 1
        if t == 0xFF: continue
        cnt = struct.unpack_from("<I", b, i)[0]; i += 4
        items = []
        for _ in range(cnt):
            x = b[i]; y = h - 1 - b[i + 1]; v = s16(b[i + 2], b[i + 3]); i += 4
            items.append((x, y, v))
        layers[t] = items
    return dict(bg=bg, w=w, h=h, layers=layers)


def transform(im, code):
    F = Image
    return {0: lambda: im, 1: lambda: im.transpose(F.FLIP_TOP_BOTTOM), 2: lambda: im.transpose(F.FLIP_LEFT_RIGHT), 3: lambda: im.transpose(F.ROTATE_180),
            5: lambda: im.transpose(F.ROTATE_270), 6: lambda: im.transpose(F.ROTATE_90), 7: lambda: im.transpose(F.FLIP_LEFT_RIGHT).transpose(F.ROTATE_270),
            4: lambda: im.transpose(F.FLIP_LEFT_RIGHT).transpose(F.ROTATE_90)}[code]()


def render(m, tile_defs, sheet, layer, background):
    W, H = m["w"] * T, m["h"] * T
    r, g, b = (m["bg"] >> 16) & 255, (m["bg"] >> 8) & 255, m["bg"] & 255
    img = Image.new("RGBA", (W, H), (r, g, b, 255) if background else (0, 0, 0, 0))
    for x, y, v in m["layers"].get(layer, []):
        if v < 0: continue
        d = tile_defs[v >> 3]; _, sx, sy, w, h = d[:5]
        tile = transform(sheet.crop((sx, sy, sx + w, sy + h)).convert("RGBA"), TBL[v & 7])
        img.paste(tile, (x * T, (y + 1) * T - tile.height), tile)
    return img
