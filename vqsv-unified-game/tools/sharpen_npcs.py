"""Sharper village NPCs that keep their original look.

The original sprites (assets/illustrations/elder|herbalist|villager|scout.webp, 18-22 x 38) are kept as they are, only
at a higher resolution: each is enlarged 4x (Lanczos), its speckle smoothed (median filter) and its edges sharpened
(unsharp mask), its colours gathered into clean flat areas (36 colours, no dithering), its outline snapped to full
opacity and ringed with a thin dark line. The result is assets/illustrations/<name>_hd.png, which the game draws at the
same size as before (38 pixels tall).
"""
from pathlib import Path
from PIL import Image, ImageFilter

ART = Path(__file__).resolve().parents[1] / "prototype" / "assets" / "illustrations"


def outline(im, f=.38):
    px, out = im.load(), im.copy()
    po, (w, h) = out.load(), im.size
    for y in range(h):
        for x in range(w):
            if px[x, y][3]:
                continue
            near = [px[x + dx, y + dy] for dx, dy in ((1, 0), (-1, 0), (0, 1), (0, -1)) if 0 <= x + dx < w and 0 <= y + dy < h and px[x + dx, y + dy][3]]
            if near:
                c = min(near, key=lambda c: sum(c[:3]))
                po[x, y] = tuple(int(v * f) for v in c[:3]) + (255,)
    return out


def sharpen(src, scale=4, median=5, colours=36):
    big = src.resize((src.width * scale, src.height * scale), Image.LANCZOS)
    rgb = big.convert("RGB").filter(ImageFilter.MedianFilter(median)).filter(ImageFilter.UnsharpMask(2, 160, 2))
    rgb = rgb.quantize(colors=colours, dither=Image.Dither.NONE).convert("RGB")
    alpha = src.getchannel("A").resize(big.size, Image.LANCZOS).filter(ImageFilter.GaussianBlur(1.2)).point(lambda v: 255 if v >= 128 else 0)
    out = rgb.convert("RGBA")
    out.putalpha(alpha)
    px = out.load()
    for y in range(out.height):
        for x in range(out.width):
            if px[x, y][3] == 0:
                px[x, y] = (0, 0, 0, 0)
    return outline(out)


if __name__ == "__main__":
    for name in ("elder", "herbalist", "villager", "scout"):
        sharpen(Image.open(ART / f"{name}.webp").convert("RGBA")).save(ART / f"{name}_hd.png", optimize=True)
    print("sharpened the four village NPCs into", ART)
