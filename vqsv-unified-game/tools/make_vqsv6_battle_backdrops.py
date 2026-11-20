"""Draw seven battle backgrounds, one per VQSV6 pet element, in the format of the VQSV5 ones (240x108 pixel art, shown at 2x).

VQSV6 itself has no battle pictures: data/script/backPic.mid only gives one flat RGB colour per map area ([world, area, 1, r, g, b]).
These pictures are NEW prototype art, drawn procedurally (layered gradients with ordered dithering, ridge silhouettes from seeded noise,
clouds, trees and element details) so they sit next to the five VQSV5 backgrounds. Writes prototype/assets/battle/vqsv6_<element>.png.
"""
import math
import random
from pathlib import Path
from PIL import Image, ImageDraw

ROOT = Path(__file__).resolve().parents[1]
OUT = ROOT / "prototype" / "assets" / "battle"
W, H = 240, 108
BAYER = [[0, 8, 2, 10], [12, 4, 14, 6], [3, 11, 1, 9], [15, 7, 13, 5]]


def hexc(h):
    h = h.lstrip("#"); return tuple(int(h[i:i + 2], 16) for i in (0, 2, 4))


def lerp(a, b, t):
    return tuple(round(a[i] + (b[i] - a[i]) * t) for i in range(3))


def gradient(img, stops, y0=0, y1=H, x0=0, x1=W, steps=6):
    """Vertical gradient quantised into `steps` bands with Bayer dithering between them (pixel-art look)."""
    px = img.load()
    for y in range(y0, y1):
        t = (y - y0) / max(1, y1 - y0 - 1) * (len(stops) - 1)
        i = min(int(t), len(stops) - 2)
        local = (t - i) * steps
        band = int(local)
        for x in range(x0, x1):
            frac = local - band
            b = band + (1 if frac * 16 > BAYER[y % 4][x % 4] else 0)
            px[x, y] = lerp(stops[i], stops[i + 1], min(1, b / steps))


def noise1d(seed, n, rough=0.55, scale=1.0):
    rng = random.Random(seed)
    size = 1
    while size < n: size *= 2
    pts = [0.0] * (size + 1)
    pts[0], pts[size] = rng.uniform(-1, 1), rng.uniform(-1, 1)
    step, amp = size, 1.0
    while step > 1:
        half = step // 2
        for i in range(half, size, step):
            pts[i] = (pts[i - half] + pts[i + half]) / 2 + rng.uniform(-amp, amp)
        step, amp = half, amp * rough
    return [pts[i] * scale for i in range(n)]


def ridge(img, seed, base, height, color, rough=0.55, shade=None, peaks=False):
    """A silhouette ridge: top edge from noise; optional lighter rim on the top pixel row and peaked (triangular) shapes."""
    d = ImageDraw.Draw(img)
    n = noise1d(seed, W, rough)
    if peaks:
        rng = random.Random(seed + 7)
        tops = []
        x = -20
        while x < W + 20:
            w = rng.randint(28, 60); tops.append((x + w / 2, rng.uniform(0.5, 1.0), w)); x += int(w * 0.6)
    for x in range(W):
        if peaks:
            h = max(max(0, (1 - abs(x - cx) / (w / 2)) * hh) for cx, hh, w in tops) * height + n[x] * 3
        else:
            h = (n[x] * 0.5 + 0.5) * height
        top = int(base - h)
        d.line([(x, top), (x, H)], fill=color)
        if shade:
            d.point((x, top), fill=shade)


def cloud(img, cx, cy, w, color, shade, seed):
    d = ImageDraw.Draw(img)
    rng = random.Random(seed)
    for k in range(7):
        r = rng.randint(w // 6, w // 3)
        x = cx + rng.randint(-w // 2, w // 2); y = cy + rng.randint(-r // 3, r // 4)
        d.ellipse([x - r, y - r * 0.6, x + r, y + r * 0.6], fill=color)
    d.rectangle([cx - w // 2 - 4, cy + 2, cx + w // 2 + 4, cy + 5], fill=shade)


def pine(d, x, base, h, dark, light):
    for i in range(h):
        half = int((i / h) * h * 0.38) + 1
        y = base - h + i
        d.line([(x - half, y), (x + half, y)], fill=dark)
        if i % 4 == 0: d.point((x - half + 1, y), fill=light)
    d.line([(x, base), (x, base + 3)], fill=(40, 28, 20))


def save(img, name):
    OUT.mkdir(parents=True, exist_ok=True)
    img.save(OUT / f"vqsv6_{name}.png")


# ---- Hỏa: volcano under a red sky, lava river and embers ----
def fire():
    img = Image.new("RGB", (W, H)); gradient(img, [hexc("2a0b14"), hexc("7a1f1a"), hexc("e0562a"), hexc("ffb347")], 0, 70)
    gradient(img, [hexc("3a1410"), hexc("1c0a0a")], 70, H)
    d = ImageDraw.Draw(img)
    d.ellipse([170, 8, 196, 34], fill=hexc("ffd27a")); d.ellipse([174, 12, 192, 30], fill=hexc("fff1c0"))
    ridge(img, 3, 70, 26, hexc("5a1e1a"), shade=hexc("8a3a24"))
    # the volcano: a cone with a glowing crater and lava streaks
    d.polygon([(70, 74), (112, 22), (128, 22), (176, 74)], fill=hexc("3b1612"))
    d.polygon([(104, 32), (112, 22), (128, 22), (136, 32)], fill=hexc("5c2018"))
    d.rectangle([112, 20, 128, 23], fill=hexc("ff7a2a")); d.line([(114, 19), (126, 19)], fill=hexc("ffd27a"))
    for x0, x1 in ((116, 98), (122, 132), (126, 150)):
        pts = [(x0 + (x1 - x0) * t / 10 + math.sin(t) * 2, 23 + t * 5) for t in range(11)]
        d.line(pts, fill=hexc("ff5a1a"), width=2); d.line(pts, fill=hexc("ffb347"), width=1)
    for k, (x, y) in enumerate(((118, 10), (124, 4), (110, 2), (130, 12))):
        d.ellipse([x - 6, y - 4, x + 6, y + 4], fill=hexc("4a2a2a") if k % 2 else hexc("5a3434"))
    ridge(img, 9, 86, 14, hexc("23100e"), shade=hexc("4a1c14"))
    # lava river across the foreground
    for y in range(92, 100):
        for x in range(W):
            if (y - 92) < 3 + math.sin(x / 9) * 2:
                ImageDraw.Draw(img).point((x, y), fill=hexc("ff6a1a") if (x + y) % 5 else hexc("ffc04a"))
    rng = random.Random(5)
    for _ in range(40):
        x, y = rng.randrange(W), rng.randrange(30, 95); d.point((x, y), fill=rng.choice([hexc("ffb347"), hexc("ff7a2a"), hexc("ffe08a")]))
    save(img, "hoa")


# ---- Mộc: deep forest clearing, layered pines, sun shafts and leaves ----
def wood():
    img = Image.new("RGB", (W, H)); gradient(img, [hexc("9fe0c8"), hexc("dff7c8"), hexc("bfe8a0")], 0, 60)
    gradient(img, [hexc("4f9a3a"), hexc("2c5e28")], 60, H)
    d = ImageDraw.Draw(img)
    for layer, (base, dark, light, n, hmin, hmax) in enumerate(((58, hexc("7fbf8a"), hexc("a6d8a8"), 18, 18, 28), (66, hexc("4f8f5a"), hexc("6fb07a"), 14, 26, 38), (78, hexc("2c5a34"), hexc("3f7a46"), 10, 36, 52))):
        rng = random.Random(30 + layer)
        for i in range(n):
            pine(d, rng.randrange(-10, W + 10), base, rng.randint(hmin, hmax), dark, light)
    px = img.load()
    for x0 in (40, 120, 190):                       # soft sun shafts: lighten every other pixel inside a slanted band
        for y in range(0, 84):
            for k in range(10):
                x = x0 + y // 3 + k
                if 0 <= x < W and (x + y) % 2 == 0:
                    px[x, y] = lerp(px[x, y], hexc("f6ffe0"), 0.35)
    d.rectangle([0, 84, W, H], fill=hexc("3d7a2e"))
    for x in range(-6, W, 22):                        # foreground bushes
        d.ellipse([x, 80, x + 26, 92], fill=hexc("2f6a2a")); d.ellipse([x + 4, 81, x + 18, 87], fill=hexc("4a8f3a"))
    rng = random.Random(8)
    for _ in range(70):
        x, y = rng.randrange(W), rng.randrange(84, H); d.point((x, y), fill=rng.choice([hexc("5fa040"), hexc("2c5e28"), hexc("8fd060")]))
    for x, c in ((30, "e05a6a"), (60, "f2d25a"), (190, "e05a6a"), (214, "fff2a0")):
        d.ellipse([x, 96, x + 4, 100], fill=hexc(c))
    save(img, "moc")


# ---- Thổ: desert canyon with mesas and dust ----
def earth():
    img = Image.new("RGB", (W, H)); gradient(img, [hexc("f6c27a"), hexc("ffe2b0"), hexc("f2d2a0")], 0, 58)
    d = ImageDraw.Draw(img)
    ridge(img, 21, 60, 18, hexc("c98a5a"), shade=hexc("e0a878"))
    def mesa(x0, w, top, body, light, dark, seed):
        rng = random.Random(seed)
        slope = 6
        poly = [(x0 - slope, 74), (x0, top + 3)]
        for x in range(x0, x0 + w + 1, 4):
            poly.append((x, top + rng.randint(0, 2)))
        poly += [(x0 + w, top + 3), (x0 + w + slope, 74)]
        d.polygon(poly, fill=body)
        d.polygon([(x0 + w - 8, top + 2), (x0 + w, top + 3), (x0 + w + slope, 74), (x0 + w - 4, 74)], fill=dark)
        d.line([(x0, top + 1), (x0 + w - 8, top + 1)], fill=light)
        for y in range(top + 7, 74, 6):
            d.line([(x0 - (y - top) * slope // (74 - top), y), (x0 + w - 8, y)], fill=dark)
    for x0, w, top in ((150, 70, 34), (20, 50, 40)):                              # far mesas
        mesa(x0, w, top, hexc("d08a5a"), hexc("ecb084"), hexc("b8764a"), x0)
    for x0, w, top in ((6, 40, 24), (88, 26, 36), (176, 52, 20)):                # near mesas
        mesa(x0, w, top, hexc("a85a34"), hexc("d8905a"), hexc("7c3e24"), x0 + 1)
    for x in (70, 150, 226):                                                      # cacti
        d.rectangle([x, 62, x + 2, 76], fill=hexc("4f7a3a")); d.rectangle([x - 3, 66, x - 2, 70], fill=hexc("4f7a3a")); d.rectangle([x + 4, 64, x + 5, 69], fill=hexc("4f7a3a"))
    gradient(img, [hexc("d89a5a"), hexc("b8743e"), hexc("9a5e30")], 74, H)
    rng = random.Random(4)
    for _ in range(14):
        x, y = rng.randrange(W), rng.randrange(80, H - 4); d.ellipse([x, y, x + rng.randint(3, 7), y + 3], fill=hexc("7c4a2a"))
    for _ in range(50):
        x, y = rng.randrange(W), rng.randrange(40, 90); d.point((x, y), fill=hexc("fff0d0"))
    save(img, "tho")


# ---- Thủy: misty lake, far shore, reflections and lily pads ----
def water():
    img = Image.new("RGB", (W, H)); gradient(img, [hexc("bfe6ff"), hexc("e8f6ff"), hexc("d0eef8")], 0, 52)
    d = ImageDraw.Draw(img)
    ridge(img, 41, 50, 18, hexc("8fb8cc"), shade=hexc("b0d4e0"), peaks=True)
    ridge(img, 43, 56, 8, hexc("5e8fa8"))
    gradient(img, [hexc("6cc6e8"), hexc("3a8fc8"), hexc("1f5f9a")], 56, H)
    rng = random.Random(6)
    for y in range(58, H, 3):
        for _ in range(4):
            x = rng.randrange(W); d.line([(x, y), (x + rng.randint(4, 14), y)], fill=hexc("bfeaff"))
    for x, y in ((30, 90), (60, 98), (190, 92), (220, 100)):
        d.ellipse([x, y, x + 12, y + 5], fill=hexc("3f9a4a")); d.point((x + 6, y + 1), fill=hexc("ff9ac0"))
    for k in range(3):
        cloud(img, 40 + k * 80, 16 + (k % 2) * 6, 34, hexc("ffffff"), hexc("d8eef8"), 50 + k)
    d.rectangle([0, 54, W, 57], fill=hexc("e8f6ff"))      # mist line
    save(img, "thuy")


# ---- Điện: storm plain, dark clouds and a lightning strike ----
def thunder():
    img = Image.new("RGB", (W, H)); gradient(img, [hexc("1a1838"), hexc("3a3a6a"), hexc("6a6aa0")], 0, 64)
    d = ImageDraw.Draw(img)
    for k in range(6):
        cloud(img, 20 + k * 44, 10 + (k % 3) * 5, 50, hexc("2a2850"), hexc("1e1c40"), 60 + k)
    bolt = [(150, 14), (142, 34), (152, 36), (138, 62)]
    d.line(bolt, fill=hexc("fff6a0"), width=3); d.line(bolt, fill=hexc("ffffff"), width=1)
    d.line([(142, 34), (130, 44)], fill=hexc("fff6a0"), width=1)
    ridge(img, 61, 66, 14, hexc("3a4a5a"), shade=hexc("6a7a9a"))
    gradient(img, [hexc("4a6a3a"), hexc("2e4a28"), hexc("1e3220")], 66, H)
    rng = random.Random(7)
    for _ in range(90):
        x, y = rng.randrange(W), rng.randrange(68, H); d.line([(x, y), (x + 1, y - 3)], fill=rng.choice([hexc("5a8a40"), hexc("3a6a30")]))
    for _ in range(40):                                  # rain
        x, y = rng.randrange(W), rng.randrange(0, 80); d.line([(x, y), (x - 2, y + 5)], fill=hexc("8a9ac8"))
    for x in (60, 196):                                  # sparks on the grass
        for k in range(5): d.point((x + rng.randint(-4, 4), 90 + rng.randint(-3, 3)), fill=hexc("fff6a0"))
    save(img, "dien")


# ---- Quỷ: haunted marsh under a purple moon, dead trees, wisps and graves ----
def ghost():
    img = Image.new("RGB", (W, H)); gradient(img, [hexc("120a24"), hexc("2e1a4a"), hexc("5a3a7a")], 0, 66)
    d = ImageDraw.Draw(img)
    d.ellipse([40, 10, 70, 40], fill=hexc("e8d8ff")); d.ellipse([48, 10, 74, 36], fill=hexc("2e1a4a"))   # crescent moon
    ridge(img, 71, 64, 16, hexc("24163a"), shade=hexc("3a2858"))
    for x, h in ((24, 30), (170, 38), (210, 26)):        # dead trees
        d.line([(x, 70), (x, 70 - h)], fill=hexc("140c1e"), width=3)
        for k in range(3):
            y = 70 - h + 6 + k * 7; s = 1 if k % 2 else -1
            d.line([(x, y), (x + s * 10, y - 6)], fill=hexc("140c1e"), width=1)
    gradient(img, [hexc("2a1a3a"), hexc("1a1028")], 70, H)
    d.rectangle([0, 82, W, 86], fill=hexc("6a4a8a"))     # fog
    for x in range(0, W, 2): d.point((x, 81 + (x // 2) % 2), fill=hexc("8a6aaa"))
    for x in (90, 120, 146):                             # graves
        d.rectangle([x, 74, x + 10, 88], fill=hexc("4a405a")); d.ellipse([x, 70, x + 10, 78], fill=hexc("4a405a")); d.line([(x + 5, 76), (x + 5, 84)], fill=hexc("2a2438"))
    rng = random.Random(9)
    for _ in range(9):                                   # wisps
        x, y = rng.randrange(10, W - 10), rng.randrange(40, 100)
        d.ellipse([x - 2, y - 2, x + 2, y + 2], fill=hexc("9affd0")); d.point((x, y - 3), fill=hexc("d0ffe8"))
    save(img, "quy")


# ---- Phong: windy peaks above the clouds, wind streaks and birds ----
def wind():
    img = Image.new("RGB", (W, H)); gradient(img, [hexc("5ab0f0"), hexc("a8dcff"), hexc("e8f8ff")], 0, 80)
    d = ImageDraw.Draw(img)
    ridge(img, 81, 70, 46, hexc("8aa0c0"), shade=hexc("f0f8ff"), peaks=True)
    ridge(img, 83, 80, 26, hexc("6a88b0"), shade=hexc("d8e8ff"), peaks=True)
    for k in range(5):
        cloud(img, 10 + k * 56, 78 + (k % 2) * 6, 60, hexc("ffffff"), hexc("ffffff"), 90 + k)
    d.rectangle([0, 88, W, H], fill=hexc("ffffff"))
    for y in range(90, H, 4):
        for x in range(0, W, 9): d.point((x + (y % 8), y), fill=hexc("e2f0ff"))
    rng = random.Random(11)
    for _ in range(9):                                   # wind streaks
        x, y = rng.randrange(W), rng.randrange(10, 70); L = rng.randint(14, 30)
        d.line([(x, y), (x + L, y)], fill=hexc("ffffff")); d.line([(x + L, y), (x + L + 3, y - 2)], fill=hexc("ffffff"))
    for x, y in ((60, 24), (70, 28), (180, 18)):
        d.line([(x - 3, y), (x, y + 2), (x + 3, y)], fill=hexc("3a4a6a"))
    save(img, "phong")


for draw in (fire, wood, earth, water, thunder, ghost, wind):
    draw()
print("Wrote", sorted(p.name for p in OUT.glob("vqsv6_*.png")))
