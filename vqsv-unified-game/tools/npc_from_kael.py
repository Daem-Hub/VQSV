"""Village NPCs in the main character's style, made from Kael's own walk sheet.

The original NPC sprites (assets/illustrations/elder|herbalist|villager|scout.webp) are only 18-22 x 38 pixels and in
a different style from Kael. This makes each NPC from Kael's 4-direction walk sheet (kael_field_hd.webp, 4 rows down /
left / right / up x 4 frames of 128 x 160), so line work, shading, proportions and walk frames are exactly Kael's:

1. every pixel is sorted into a part of Kael (hair, skin, scarf, tunic, cream wraps, leather, compass, trousers, boots,
   outline, eyes) by its colour and where it sits in the frame;
2. each part is recoloured to the NPC's colour from the original sprite, keeping the pixel's own light and shade
   (the brightness relative to that part's usual brightness on Kael);
3. a few touches give each NPC its own look: the scout's bandana (top of the hair turned blue, with a tail), the
   herbalist's gold hair clips, and the compass hidden on everyone but Kael.

Outputs assets/characters/npc_<name>_sheet.webp (the whole 4 x 4 sheet) and assets/illustrations/<name>_kael.png (the
front idle frame). Not used by the game yet: the game still draws the NPCs from tools/draw_npcs.py (<name>_hd.png);
this is an alternative set kept for review.
"""
import colorsys
from pathlib import Path
from PIL import Image

ROOT = Path(__file__).resolve().parents[1] / "prototype" / "assets"
FW, FH = 128, 160

# Each part's usual brightness on Kael (HSV value), so a recoloured pixel keeps its shade relative to it.
REF = {"hair": .22, "scarf": .55, "tunic": .27, "cream": .82, "leather": .3, "compass": .7, "pants": .16, "boots": .3, "skin": .9}


def hsv(c):
    return colorsys.rgb_to_hsv(c[0] / 255, c[1] / 255, c[2] / 255)


def part_of(c, x, y):
    """Which part of Kael a pixel belongs to (frame coordinates)."""
    h, s, v = hsv(c)
    hd = h * 360
    if v < .09:
        return "line"
    if 12 <= hd <= 40 and .18 <= s <= .55 and v > .62:
        return "skin"
    if (hd < 14 or hd > 340) and s > .55:
        return "scarf"
    if 38 <= hd <= 62 and s > .4 and v > .45:
        return "compass"
    if 150 <= hd <= 230 and s > .18:
        return "tunic"
    if s < .3 and v > .6:
        return "cream"
    if y < 72 and s < .45 and v < .55:
        return "hair"
    if 8 <= hd <= 45 and s > .45:
        return "boots" if y >= 122 else "leather"
    if y >= 112 and v < .4:
        return "pants"
    return None


def recolour(c, target, ref, gamma=.75, floor=.0):
    """Paint `target` with the shade of `c`: value follows c's brightness relative to the part's usual brightness."""
    _, _, v = hsv(c)
    th, ts, tv = hsv(target)
    k = (v / ref) ** gamma if ref else 1
    nv = max(floor, min(1, tv * k))
    ns = max(0, min(1, ts * (1.08 if k < 1 else .92)))
    r, g, b = colorsys.hsv_to_rgb(th, ns, nv)
    return (int(r * 255), int(g * 255), int(b * 255), c[3])


def hexc(h):
    h = h.lstrip("#")
    return tuple(int(h[i:i + 2], 16) for i in (0, 2, 4))


def eye_rows(px, x0, y0):
    """Face pixels between the leftmost and rightmost skin pixel of each row keep their colour (eyes, mouth)."""
    keep = set()
    for y in range(40, 72):
        xs = [x for x in range(FW) if px[x0 + x, y0 + y][3] > 0 and part_of(px[x0 + x, y0 + y], x, y) == "skin"]
        if len(xs) >= 10:
            for x in range(min(xs), max(xs) + 1):
                keep.add((x, y))
    return keep


def make(name, colours, touch=None):
    sheet = Image.open(ROOT / "characters" / "kael_field_hd.webp").convert("RGBA")
    out = sheet.copy()
    src, dst = sheet.load(), out.load()
    for row in range(4):
        for col in range(4):
            x0, y0 = col * FW, row * FH
            face = eye_rows(src, x0, y0) if row != 3 else set()
            parts = {}
            for y in range(FH):
                for x in range(FW):
                    c = src[x0 + x, y0 + y]
                    if c[3] == 0:
                        continue
                    p = part_of(c, x, y)
                    if p == "hair" and (x, y) in face:
                        p = None                          # eyes and brows inside the face keep Kael's dark colour
                    parts[(x, y)] = p
                    target = colours.get(p)
                    if target:
                        dst[x0 + x, y0 + y] = recolour(c, hexc(target), REF[p], floor=.06 if p == "hair" else 0)
            if touch:
                touch(src, dst, x0, y0, row, parts)
    out.save(ROOT / "characters" / f"npc_{name}_sheet.webp", lossless=True, method=6)
    out.crop((0, 0, FW, FH)).save(ROOT / "illustrations" / f"{name}_kael.png", optimize=True)
    return out


def bandana(src, dst, x0, y0, row, parts):
    """The scout: the top of the hair becomes a blue bandana, with its tail hanging off the left side."""
    hair = [(x, y) for (x, y), p in parts.items() if p == "hair"]
    if not hair:
        return
    top = min(y for _, y in hair)
    for x, y in hair:
        if y < top + 17:
            dst[x0 + x, y0 + y] = recolour(src[x0 + x, y0 + y], hexc("#4a6cb8"), REF["hair"], floor=.12)
    if row in (0, 1, 3):                       # tail on the left (front, left and back views)
        left = min(x for x, y in hair if y < top + 17)
        for i in range(14):
            for w in range(4):
                x, y = left - 2 - i // 3 + w, top + 14 + i
                if 0 <= x < FW and 0 <= y < FH:
                    dst[x0 + x, y0 + y] = (40, 62, 130, 255) if w in (1, 2) else (18, 26, 52, 255)


def clips(src, dst, x0, y0, row, parts):
    """The herbalist: gold clips on both sides of the hair."""
    hair = [(x, y) for (x, y), p in parts.items() if p == "hair"]
    if not hair or row == 3:
        return
    top = min(y for _, y in hair)
    y = top + 20
    edge = [x for x, yy in hair if abs(yy - y) <= 1]
    if not edge:
        return
    for cx in ((min(edge) + 5, max(edge) - 5) if row == 0 else ((max(edge) - 6,) if row == 1 else (min(edge) + 6,))):
        for dy in range(-4, 5):
            for dx in range(-5, 6):
                if abs(dx) + abs(dy) <= 5:
                    edge_px = abs(dx) + abs(dy) == 5
                    dst[x0 + cx + dx, y0 + y + dy] = (90, 50, 10, 255) if edge_px else ((255, 222, 120, 255) if dy < 0 else (232, 168, 48, 255))


NO_COMPASS = {"compass": None}
NPCS = {
    # Người gác làng: ash-grey hair, cream collar, brown vest, dark red trousers
    "elder": ({"hair": "#c9beb2", "scarf": "#e6d6bc", "tunic": "#7a4a2c", "cream": "#efe2cc", "leather": "#5a3a24", "compass": "#7a4a2c", "pants": "#7a2a28", "boots": "#6a4428"}, None),
    # Dược sĩ: pink hair, white frilled collar, yellow dress, orange belt, grey stockings, gold clips
    "herbalist": ({"hair": "#ef7fa2", "scarf": "#fbe9ef", "tunic": "#e9b62c", "cream": "#fff6ea", "leather": "#d0762c", "compass": "#ff9ec0", "pants": "#8a8a72", "boots": "#8a4a32"}, clips),
    # Dân làng: green hair, tan collar, maroon jacket, dark brown trousers
    "villager": ({"hair": "#6f9a36", "scarf": "#d8b080", "tunic": "#7c3a36", "cream": "#e8d2ae", "leather": "#4a2e1e", "compass": "#7c3a36", "pants": "#3e2a20", "boots": "#4a2c20"}, None),
    # Người gác: brown hair under a blue bandana, orange scarf, blue tunic, orange belt
    "scout": ({"hair": "#8a4a2a", "scarf": "#e2802c", "tunic": "#3e5e9a", "cream": "#9ab2dc", "leather": "#b8622a", "compass": "#3e5e9a", "pants": "#5a3420", "boots": "#6a4428"}, bandana),
}

if __name__ == "__main__":
    for name, (colours, touch) in NPCS.items():
        make(name, colours, touch)
    print("made", ", ".join(NPCS), "from Kael's sheet")
