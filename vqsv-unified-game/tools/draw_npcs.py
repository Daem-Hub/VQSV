"""Hand-built pixel art for the village NPCs, at Kael's field size.

The original NPC sprites (assets/illustrations/elder|herbalist|villager|scout.webp) are only 18-21 x 38 pixels, so they
look coarse next to Kael's 128 x 160 frames. This draws each NPC again as 64 x 80 pixel art in Kael's style (chibi
proportions, dark outlines, 3-tone shading, large eyes with highlights), keeping each one's hair and outfit colours,
and saves it at 2x (128 x 160) as assets/illustrations/<name>_hd.png.

Every part is a layer of flat shapes; each layer gets its own dark outline before it is laid over the layers behind it,
so a front layer (bangs, scarf, vest) keeps a line where it crosses the one behind it.
"""
from pathlib import Path
from PIL import Image, ImageDraw

ART = Path(__file__).resolve().parents[1] / "prototype" / "assets" / "illustrations"
W, H = 64, 80
SKIN, SKIN_SH, SKIN_HL = "#f6d2b2", "#e0ad8a", "#fde6d2"
EYE, WHITE, BLUSH, LINE = "#2a2532", "#ffffff", "#f3a29b", "#2b1c16"


def rgb(h):
    h = h.lstrip("#")
    return tuple(int(h[i:i + 2], 16) for i in (0, 2, 4))


def darker(c, f=.45):
    return tuple(int(v * f) for v in c[:3]) + (255,)


class Sprite:
    def __init__(self):
        self.img = Image.new("RGBA", (W, H), (0, 0, 0, 0))

    def layer(self, paint, outline=True):
        lay = Image.new("RGBA", (W, H), (0, 0, 0, 0))
        paint(ImageDraw.Draw(lay))
        if outline:
            px, out = lay.load(), lay.copy()
            po = out.load()
            for y in range(H):
                for x in range(W):
                    if px[x, y][3]:
                        continue
                    near = [px[x + dx, y + dy] for dx, dy in ((1, 0), (-1, 0), (0, 1), (0, -1)) if 0 <= x + dx < W and 0 <= y + dy < H and px[x + dx, y + dy][3]]
                    if near:
                        po[x, y] = darker(min(near, key=lambda c: sum(c[:3])), .38)
            lay = out
        self.img.alpha_composite(lay)


def ell(d, box, c):
    d.ellipse(box, fill=rgb(c) + (255,))


def poly(d, pts, c):
    d.polygon(pts, fill=rgb(c) + (255,))


def rect(d, box, c):
    d.rectangle(box, fill=(rgb(c) if isinstance(c, str) else tuple(c[:3])) + (255,))


def strands(d, lines, c):
    for pts in lines:
        d.line(pts, fill=rgb(c) + (255,), width=1)


def legs_and_boots(s, pants, pants_sh, boot, boot_sh, gap=2, top=57, knee=None):
    def paint(d):
        for x0 in (25, 33 + gap - 2):
            rect(d, (x0, top, x0 + 6, 69), pants)
            rect(d, (x0 + 4, top, x0 + 6, 69), pants_sh)
            rect(d, (x0 - 1, 68, x0 + 7, 76), boot)
            rect(d, (x0 + 5, 69, x0 + 7, 76), boot_sh)
            rect(d, (x0 - 1, 68, x0 + 7, 69), darker(rgb(boot), .8)[:3] and boot_sh)
    s.layer(paint)


def arms(s, sleeve, sleeve_sh, hand=SKIN, cuff=None):
    def paint(d):
        for x0, sh in ((17, (19, 22)), (41, (44, 46))):
            poly(d, [(x0 + 1, 43), (x0 + 5, 42), (x0 + 6, 55), (x0, 55)], sleeve)
            rect(d, (sh[0], 46, sh[1], 54), sleeve_sh)
            if cuff:
                rect(d, (x0, 53, x0 + 6, 56), cuff)
            ell(d, (x0 + 1, 55, x0 + 5, 59), hand)
    s.layer(paint)


def head(s, eye_iris, mouth="smile"):
    def face(d):
        ell(d, (17, 13, 46, 41), SKIN)
        poly(d, [(40, 22), (45, 26), (44, 34), (39, 39)], SKIN_SH)
        rect(d, (29, 39, 34, 44), SKIN_SH)
    s.layer(face)

    def features(d):
        for x0 in (22, 36):
            rect(d, (x0 - 1, 26, x0 + 5, 26), LINE)                     # upper lash
            rect(d, (x0, 27, x0 + 4, 33), EYE)
            rect(d, (x0, 30, x0 + 4, 33), eye_iris)
            rect(d, (x0 + 1, 31, x0 + 3, 32), darker(rgb(eye_iris), .75)[:3])
            rect(d, (x0 + 1, 28, x0 + 2, 29), WHITE)
            d.point((x0 + 3, 32), fill=rgb(WHITE) + (255,))
            rect(d, (x0 - 1, 35, x0 + 1, 36), BLUSH)
        d.point((32, 34), fill=rgb(SKIN_SH) + (255,))
        if mouth == "smile":
            rect(d, (30, 37, 33, 37), "#b5605a")
            d.point((29, 36), fill=rgb("#b5605a") + (255,)); d.point((34, 36), fill=rgb("#b5605a") + (255,))
        else:
            rect(d, (30, 37, 32, 37), "#b5605a")
    s.layer(features, outline=False)


def save(s, name):
    s.img.resize((W * 2, H * 2), Image.NEAREST).save(ART / f"{name}_hd.png", optimize=True)
    return s.img


def elder():
    """Người gác làng: ash-grey tousled hair, cream shirt, brown vest, maroon trousers, brown boots."""
    hair, hair_sh, hair_hl = "#cfc4b8", "#9f9388", "#ece4da"
    s = Sprite()
    s.layer(lambda d: (ell(d, (13, 8, 50, 38), hair), ell(d, (30, 10, 50, 36), hair_sh)))
    legs_and_boots(s, "#7a3328", "#5a2219", "#6a4428", "#4a2c18")
    def body(d):
        poly(d, [(23, 42), (41, 42), (43, 58), (21, 58)], "#efe2c8")
        poly(d, [(21, 44), (28, 43), (29, 58), (21, 58)], "#7a4a2c")
        poly(d, [(35, 43), (42, 44), (43, 58), (34, 58)], "#7a4a2c")
        rect(d, (38, 45, 43, 58), "#5e3820")
        rect(d, (21, 55, 43, 57), "#4a2e1c")
        rect(d, (30, 55, 33, 57), "#d9a441")
        for y in (46, 50): d.point((27, y), fill=rgb("#d9a441") + (255,))
        poly(d, [(28, 42), (32, 46), (36, 42)], "#d6c6a6")
    s.layer(body)
    arms(s, "#efe2c8", "#d6c6a6", cuff="#d6c6a6")
    head(s, "#6b4a3a")
    def front_hair(d):
        poly(d, [(15, 22), (20, 12), (30, 8), (40, 9), (48, 18), (48, 26), (43, 21), (38, 24), (33, 19), (27, 24), (22, 20), (18, 27)], hair)
        poly(d, [(38, 12), (47, 18), (47, 25), (43, 20)], hair_sh)
        rect(d, (24, 11, 31, 13), hair_hl)
        strands(d, [[(24, 14), (21, 21)], [(31, 12), (29, 20)], [(37, 13), (38, 21)]], hair_sh)
        poly(d, [(15, 22), (18, 27), (17, 34), (14, 29)], hair)
        poly(d, [(48, 22), (49, 30), (46, 34), (46, 25)], hair_sh)
    s.layer(front_hair)
    return save(s, "elder")


def herbalist():
    """Dược sĩ: big pink hair with golden bows, cream blouse, yellow dress with cream ruffles."""
    hair, hair_sh, hair_hl = "#f290ac", "#c8587a", "#ffc4d4"
    s = Sprite()
    s.layer(lambda d: (ell(d, (10, 6, 54, 44), hair), ell(d, (8, 24, 22, 46), hair), ell(d, (42, 24, 56, 46), hair_sh), ell(d, (32, 8, 52, 40), hair_sh)))
    legs_and_boots(s, "#f3dcc4", "#dcbca0", "#8a4a3a", "#6a3328", gap=1, top=62)
    def body(d):
        poly(d, [(23, 42), (41, 42), (42, 52), (22, 52)], "#f8f0e2")
        poly(d, [(26, 42), (32, 47), (38, 42)], "#f0e2c8")
        poly(d, [(22, 50), (42, 50), (47, 64), (17, 64)], "#f2c23a")
        poly(d, [(36, 50), (42, 50), (47, 64), (39, 64)], "#d49a22")
        rect(d, (17, 63, 47, 65), "#fff4dc")
        poly(d, [(29, 51), (35, 51), (36, 61), (28, 61)], "#fffaf0")
        rect(d, (22, 50, 42, 51), "#e08a3a")
        for x in range(18, 47, 3): d.point((x, 65), fill=rgb("#e8d4b0") + (255,))
    s.layer(body)
    arms(s, "#f8f0e2", "#e2d4bc", cuff="#fff4dc")
    head(s, "#c0507a")
    def front_hair(d):
        poly(d, [(14, 24), (18, 12), (28, 7), (38, 7), (47, 13), (50, 24), (45, 22), (42, 26), (37, 20), (32, 25), (27, 20), (21, 26), (17, 30)], hair)
        poly(d, [(39, 10), (48, 15), (50, 23), (44, 20)], hair_sh)
        rect(d, (22, 10, 30, 12), hair_hl)
        strands(d, [[(25, 12), (22, 22)], [(33, 9), (32, 20)], [(40, 11), (42, 20)]], hair_sh)
        ell(d, (11, 30, 19, 40), hair); ell(d, (45, 30, 53, 40), hair_sh)
    s.layer(front_hair)
    def bows(d):
        for cx in (13, 51):
            poly(d, [(cx - 5, 12), (cx, 15), (cx - 5, 18)], "#f0b030")
            poly(d, [(cx + 5, 12), (cx, 15), (cx + 5, 18)], "#d89020")
            rect(d, (cx - 1, 14, cx + 1, 16), "#fff0a0")
        rect(d, (30, 5, 34, 8), "#f0b030")
    s.layer(bows)
    return save(s, "herbalist")


def villager():
    """Dân làng: messy green hair with a cowlick, maroon jacket over a cream shirt, brown trousers."""
    hair, hair_sh, hair_hl = "#79a43c", "#4e7426", "#a6cc5e"
    s = Sprite()
    s.layer(lambda d: (ell(d, (13, 7, 50, 38), hair), ell(d, (32, 9, 50, 36), hair_sh)))
    legs_and_boots(s, "#4e3a2c", "#36281e", "#6a4428", "#4a2c18")
    def body(d):
        poly(d, [(23, 42), (41, 42), (43, 58), (21, 58)], "#7c3a33")
        poly(d, [(28, 42), (36, 42), (34, 58), (30, 58)], "#efe2c8")
        poly(d, [(36, 43), (42, 44), (43, 58), (35, 58)], "#5e2a24")
        rect(d, (21, 55, 43, 57), "#3d2a1c")
        for y in (46, 50): d.point((27, y), fill=rgb("#d9a441") + (255,))
    s.layer(body)
    arms(s, "#7c3a33", "#5e2a24", cuff="#5e2a24")
    head(s, "#3f7a3a")
    def front_hair(d):
        poly(d, [(14, 24), (18, 13), (27, 7), (37, 7), (46, 13), (49, 24), (44, 21), (40, 27), (35, 20), (30, 26), (25, 20), (20, 27), (17, 31)], hair)
        poly(d, [(31, 8), (34, 1), (37, 2), (35, 8)], hair)
        poly(d, [(38, 10), (47, 15), (48, 23), (43, 19)], hair_sh)
        rect(d, (22, 10, 29, 12), hair_hl)
        strands(d, [[(24, 12), (21, 22)], [(31, 10), (30, 20)], [(38, 11), (40, 20)]], hair_sh)
    s.layer(front_hair)
    return save(s, "villager")


def scout():
    """Người gác: brown hair under a blue bandana with a tail, orange scarf, blue tunic, orange sash, brown gloves."""
    band, band_sh, band_hl = "#3d5eb0", "#2a4282", "#6a8ad8"
    hair, hair_sh = "#8a5030", "#643820"
    s = Sprite()
    s.layer(lambda d: (ell(d, (14, 10, 49, 40), hair), ell(d, (32, 12, 49, 38), hair_sh), poly(d, [(14, 18), (4, 14), (2, 22), (12, 24)], band), poly(d, [(12, 22), (3, 26), (6, 32), (14, 27)], band_sh)))
    legs_and_boots(s, "#5a3a2a", "#3e281c", "#6a4428", "#4a2c18")
    def body(d):
        poly(d, [(23, 42), (41, 42), (43, 58), (21, 58)], "#3e5e9a")
        poly(d, [(36, 43), (42, 44), (43, 58), (35, 58)], "#2c4472")
        poly(d, [(21, 52), (43, 52), (45, 60), (19, 60)], "#d8762a")
        poly(d, [(36, 52), (43, 52), (45, 60), (37, 60)], "#b45a1c")
    s.layer(body)
    arms(s, "#3e5e9a", "#2c4472", hand="#6a4428")
    head(s, "#6a3a20")
    def front(d):
        poly(d, [(15, 22), (18, 12), (26, 7), (38, 7), (46, 12), (49, 21), (15, 21)], band)
        poly(d, [(38, 8), (46, 12), (49, 21), (40, 21)], band_sh)
        rect(d, (22, 10, 30, 12), band_hl)
        rect(d, (15, 20, 49, 23), band_sh)
        poly(d, [(16, 23), (22, 23), (19, 30)], hair); poly(d, [(42, 23), (48, 23), (46, 31)], hair_sh)
        poly(d, [(25, 23), (31, 23), (28, 27)], hair)
    s.layer(front)
    def scarf(d):
        ell(d, (20, 39, 44, 47), "#e8862e")
        poly(d, [(36, 43), (44, 41), (46, 52), (40, 52)], "#c86a1e")
        rect(d, (24, 42, 30, 44), "#f6a85a")
    s.layer(scarf)
    return save(s, "scout")


if __name__ == "__main__":
    frames = [elder(), herbalist(), villager(), scout()]
    sheet = Image.new("RGBA", (W * 4 + 30, H), (0, 0, 0, 0))
    for i, f in enumerate(frames):
        sheet.alpha_composite(f, (i * (W + 10), 0))
    print("drew", len(frames), "NPCs into", ART)
