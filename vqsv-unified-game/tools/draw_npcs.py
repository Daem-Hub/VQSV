"""Hand-built pixel art for the village NPCs, at Kael's field size.

The original NPC sprites (assets/illustrations/elder|herbalist|villager|scout.webp) are only 18-21 x 38 pixels, so they
look coarse next to Kael. This draws each NPC again at twice the original size (about 36-44 x 76), following the
original's build, hair shape and outfit region by region, as clean pixel art with dark outlines, shading and eyes with
highlights, and saves it doubled again as assets/illustrations/<name>_redraw.png (4x the original). Not used by the
game: the game keeps the original NPC look, sharpened by tools/sharpen_npcs.py (<name>_hd.png).

Every part is a layer of flat shapes; each layer gets its own dark outline before it is laid over the layers behind it,
so a front layer (bangs, scarf, vest) keeps a line where it crosses the one behind it.
"""
from pathlib import Path
from PIL import Image, ImageDraw

ART = Path(__file__).resolve().parents[1] / "prototype" / "assets" / "illustrations"
W, H = 44, 76
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


def eyes(d, xs, y, iris, h=4):
    for x0 in xs:
        rect(d, (x0 - 1, y - 1, x0 + 2, y - 1), LINE)
        rect(d, (x0, y, x0 + 1, y + h - 1), EYE)
        rect(d, (x0, y + h - 2, x0 + 1, y + h - 1), iris)
        d.point((x0, y), fill=rgb(WHITE) + (255,))
        rect(d, (x0 - 1, y + h + 1, x0, y + h + 1), BLUSH)


def save(s, name):
    s.img.crop(s.img.getbbox()).resize((s.img.getbbox()[2] - s.img.getbbox()[0]) * 0 + 0 or 1, 1) if False else None
    box = s.img.getbbox()
    art = s.img.crop((box[0], 0, box[2], H))
    art.resize((art.width * 2, art.height * 2), Image.NEAREST).save(ART / f"{name}_redraw.png", optimize=True)
    return art


# The four NPCs follow their original 18-21 x 38 sprites: same slim build (head about a third of the height), same
# hair shapes and outfits, drawn again at 2x (each original pixel becomes 2 x 2) as clean pixel art with outlines,
# shading and new eyes. The canvas is 44 x 76; the saved art is cropped to the figure and doubled.

def elder():
    """Người gác làng: a big rounded mop of ash-grey hair over the brow, cream sleeves, brown vest, dark red trousers."""
    hair, hair_sh, hair_hl = "#d6c8ba", "#a8968a", "#eee4da"
    s = Sprite()
    s.layer(lambda d: (ell(d, (4, 0, 38, 27), hair), ell(d, (21, 2, 38, 26), hair_sh), ell(d, (5, 16, 12, 30), hair), ell(d, (31, 16, 38, 30), hair_sh)))
    def legs(d):
        rect(d, (12, 56, 30, 66), "#7a2a28"); rect(d, (21, 56, 30, 66), "#5e1e1c"); rect(d, (20, 60, 21, 66), "#4a1614")
        for x0 in (11, 22):
            rect(d, (x0, 66, x0 + 8, 75), "#7a4a28"); rect(d, (x0 + 5, 67, x0 + 8, 75), "#5a3418")
    s.layer(legs)
    def body(d):
        poly(d, [(11, 31), (31, 31), (32, 57), (10, 57)], "#8a4a2a")
        poly(d, [(23, 31), (31, 31), (32, 57), (24, 57)], "#6a3420")
        poly(d, [(17, 31), (25, 31), (21, 38)], "#f0dcc4")
        for y in (41, 46, 51): d.point((19, y), fill=rgb("#d9a441") + (255,))
        rect(d, (10, 54, 32, 56), "#4a2a18")
    s.layer(body)
    def arms(d):
        for x0, sh in ((4, False), (32, True)):
            poly(d, [(x0 + 1, 32), (x0 + 6, 31), (x0 + 7, 53), (x0, 53)], "#f2e0cc")
            if sh: rect(d, (x0 + 4, 35, x0 + 6, 52), "#d8c0a8")
            ell(d, (x0 + 1, 52, x0 + 6, 57), SKIN)
    s.layer(arms)
    s.layer(lambda d: (ell(d, (11, 12, 31, 31), SKIN), poly(d, [(26, 18), (31, 21), (30, 28), (26, 30)], SKIN_SH), rect(d, (18, 29, 23, 32), SKIN_SH)))
    s.layer(lambda d: (eyes(d, (15, 25), 20, "#6b4a3a"), rect(d, (20, 27, 22, 27), "#b5605a")), outline=False)
    def bangs(d):
        poly(d, [(6, 18), (9, 7), (17, 2), (27, 2), (35, 9), (37, 19), (33, 15), (29, 19), (25, 14), (21, 19), (17, 14), (13, 19), (9, 22)], hair)
        poly(d, [(27, 4), (35, 10), (36, 18), (31, 13)], hair_sh)
        rect(d, (13, 5, 20, 6), hair_hl); strands(d, [[(15, 8), (13, 15)], [(22, 6), (21, 14)], [(28, 8), (30, 14)]], hair_sh)
    s.layer(bangs)
    return save(s, "elder")


def herbalist():
    """Dược sĩ: a big pink puff with curls at the sides and gold clips, orange bodice with a yellow panel, yellow apron."""
    hair, hair_sh, hair_hl = "#f290ac", "#c8587a", "#ffc4d4"
    s = Sprite()
    s.layer(lambda d: (ell(d, (3, 1, 41, 33), hair), ell(d, (24, 3, 41, 31), hair_sh), ell(d, (1, 18, 10, 36), hair), ell(d, (34, 18, 43, 36), hair_sh), ell(d, (18, 0, 25, 5), hair)))
    def legs(d):
        for x0 in (14, 24):
            rect(d, (x0, 62, x0 + 6, 72), "#8a8a6c"); rect(d, (x0 + 4, 62, x0 + 6, 72), "#6c6c52")
            rect(d, (x0 - 1, 71, x0 + 7, 75), "#d8b890")
    s.layer(legs)
    def body(d):
        poly(d, [(12, 33), (32, 33), (33, 46), (11, 46)], "#b86a3a")
        rect(d, (16, 36, 28, 44), "#f2c23a"); rect(d, (24, 36, 28, 44), "#d49a22")
        poly(d, [(17, 32), (27, 32), (22, 36)], "#fff4e4")
        poly(d, [(10, 45), (34, 45), (38, 62), (6, 62)], "#c47a40")
        poly(d, [(14, 46), (30, 46), (31, 60), (13, 60)], "#f2c23a")
        poly(d, [(24, 46), (30, 46), (31, 60), (25, 60)], "#d49a22")
        rect(d, (6, 60, 38, 63), "#f4e2c0")
        for x in range(7, 38, 3): d.point((x, 63), fill=rgb("#c8a878") + (255,))
        for x, y in ((9, 50), (34, 52), (11, 56)): rect(d, (x, y, x + 1, y + 1), "#f4a2b8")
    s.layer(body)
    def arms(d):
        for x0, sh in ((4, False), (34, True)):
            poly(d, [(x0 + 1, 34), (x0 + 6, 33), (x0 + 7, 49), (x0, 49)], "#f6ead8")
            rect(d, (x0, 47, x0 + 7, 50), "#f4a2b8")
            ell(d, (x0 + 1, 50, x0 + 6, 55), SKIN)
    s.layer(arms)
    s.layer(lambda d: (ell(d, (12, 14, 32, 33), SKIN), poly(d, [(27, 20), (32, 23), (31, 30), (27, 32)], SKIN_SH)))
    s.layer(lambda d: (eyes(d, (16, 26), 22, "#c0507a"), rect(d, (21, 29, 23, 29), "#b5605a")), outline=False)
    def bangs(d):
        poly(d, [(6, 20), (9, 8), (17, 3), (28, 3), (36, 9), (39, 20), (34, 17), (31, 22), (27, 16), (22, 22), (17, 16), (13, 22), (9, 24)], hair)
        poly(d, [(29, 5), (36, 10), (38, 19), (33, 14)], hair_sh)
        rect(d, (13, 6, 21, 7), hair_hl); strands(d, [[(15, 9), (13, 17)], [(23, 7), (22, 16)], [(30, 9), (32, 16)]], hair_sh)
        ell(d, (2, 26, 9, 37), hair); ell(d, (35, 26, 42, 37), hair_sh)
    s.layer(bangs)
    def clips(d):
        for cx in (5, 39):
            poly(d, [(cx - 3, 8), (cx + 3, 8), (cx + 2, 13), (cx - 2, 13)], "#f0b030")
            rect(d, (cx - 1, 9, cx + 1, 11), "#fff0a0")
            rect(d, (cx - 3, 13, cx - 1, 15), "#e07a30")
    s.layer(clips)
    return save(s, "herbalist")


def villager():
    """Dân làng: a wide, curly mop of green hair parted over the face, green eyes, maroon jacket with a tan front."""
    hair, hair_sh, hair_hl = "#7aa43c", "#4c7024", "#a4cc5e"
    s = Sprite()
    def back(d):
        ell(d, (2, 3, 40, 30), hair); ell(d, (22, 5, 40, 29), hair_sh); ell(d, (18, 0, 24, 6), hair_sh)
        for x, y in ((1, 12), (38, 14), (4, 22), (36, 24)): ell(d, (x, y, x + 6, y + 7), hair if x < 20 else hair_sh)
    s.layer(back)
    def legs(d):
        rect(d, (13, 58, 29, 68), "#3e2a20"); rect(d, (21, 58, 29, 68), "#2c1e16")
        for x0 in (12, 22):
            rect(d, (x0, 66, x0 + 8, 75), "#4a2c20"); rect(d, (x0 + 5, 67, x0 + 8, 75), "#341e16")
    s.layer(legs)
    def body(d):
        poly(d, [(10, 32), (32, 32), (33, 62), (9, 62)], "#7c3a3a")
        poly(d, [(23, 32), (32, 32), (33, 62), (24, 62)], "#5e2a2a")
        poly(d, [(16, 31), (26, 31), (24, 45), (18, 45)], "#d8b080")
        poly(d, [(15, 32), (19, 32), (20, 44), (17, 46)], "#9a4a48")
        rect(d, (9, 52, 33, 54), "#3d2a1c"); rect(d, (19, 52, 22, 54), "#d9a441")
    s.layer(body)
    def arms(d):
        for x0, sh in ((3, False), (33, True)):
            poly(d, [(x0 + 1, 33), (x0 + 6, 32), (x0 + 7, 57), (x0, 57)], "#7c3a3a")
            if sh: rect(d, (x0 + 4, 36, x0 + 6, 56), "#5e2a2a")
            ell(d, (x0 + 1, 56, x0 + 6, 61), "#e8c090")
    s.layer(arms)
    s.layer(lambda d: (ell(d, (12, 12, 30, 31), SKIN), poly(d, [(25, 18), (30, 21), (29, 28), (25, 30)], SKIN_SH), rect(d, (18, 29, 23, 32), SKIN_SH)))
    s.layer(lambda d: (eyes(d, (15, 25), 20, "#4a8a3a"), rect(d, (20, 27, 22, 27), "#b5605a")), outline=False)
    def bangs(d):
        poly(d, [(5, 20), (8, 8), (16, 3), (21, 6), (19, 13), (15, 20), (12, 16), (9, 24)], hair)
        poly(d, [(21, 6), (27, 3), (35, 8), (38, 20), (34, 24), (31, 16), (27, 20), (24, 13)], hair_sh)
        rect(d, (11, 7, 17, 8), hair_hl); strands(d, [[(13, 10), (11, 17)], [(29, 8), (32, 15)]], "#3c5a1c")
    s.layer(bangs)
    return save(s, "villager")


def scout():
    """Người gác: blue bandana with a long tail flying left, long brown hair, orange scarf, blue-and-brown tunic, orange wrap."""
    band, band_sh, band_hl = "#4a6cb8", "#2c4886", "#7a96d8"
    hair, hair_sh = "#8a4a2a", "#643020"
    s = Sprite()
    def back(d):
        ell(d, (9, 4, 39, 32), hair); ell(d, (25, 6, 39, 32), hair_sh)
        rect(d, (10, 16, 15, 34), hair); rect(d, (34, 16, 39, 34), hair_sh)
        poly(d, [(10, 12), (2, 18), (0, 26), (4, 30), (8, 22), (12, 18)], band_sh)
        poly(d, [(4, 26), (0, 34), (3, 36), (7, 28)], band)
    s.layer(back)
    def legs(d):
        rect(d, (15, 58, 31, 66), "#5a3420"); rect(d, (23, 58, 31, 66), "#3e2416")
        for x0 in (14, 24):
            rect(d, (x0, 64, x0 + 8, 75), "#6a4428"); rect(d, (x0 + 5, 65, x0 + 8, 75), "#4a2c18")
            rect(d, (x0, 64, x0 + 8, 65), "#e07a30")
    s.layer(legs)
    def body(d):
        poly(d, [(12, 35), (34, 35), (35, 60), (11, 60)], "#7a4a2c")
        poly(d, [(14, 36), (24, 36), (23, 54), (13, 54)], "#3e5e9a")
        rect(d, (26, 44, 32, 52), "#3e5e9a")
        rect(d, (11, 50, 35, 52), "#3a2418")
        poly(d, [(10, 52), (24, 52), (22, 64), (8, 62)], "#d8762a")
        poly(d, [(18, 52), (24, 52), (22, 64), (17, 63)], "#b45a1c")
    s.layer(body)
    def arms(d):
        for x0, sh in ((5, False), (35, True)):
            poly(d, [(x0 + 1, 36), (x0 + 6, 35), (x0 + 7, 55), (x0, 55)], "#7a4a2c")
            if sh: rect(d, (x0 + 4, 38, x0 + 6, 54), "#5a3420")
            rect(d, (x0, 46, x0 + 7, 49), "#3e5e9a")
            ell(d, (x0 + 1, 54, x0 + 6, 59), "#6a4428")
    s.layer(arms)
    s.layer(lambda d: (ell(d, (14, 10, 34, 30), SKIN), poly(d, [(29, 16), (34, 19), (33, 26), (29, 28)], SKIN_SH)))
    s.layer(lambda d: (eyes(d, (18, 27), 18, "#7a4a2a"), rect(d, (22, 25, 24, 25), "#b5605a")), outline=False)
    def front(d):
        poly(d, [(10, 12), (13, 4), (21, 0), (31, 0), (38, 5), (40, 12)], band)
        poly(d, [(30, 1), (38, 5), (40, 12), (32, 12)], band_sh)
        rect(d, (17, 2, 25, 3), band_hl)
        rect(d, (10, 11, 40, 13), band_sh)
        poly(d, [(14, 13), (21, 13), (16, 20)], hair); poly(d, [(29, 13), (36, 13), (35, 22)], hair_sh)
        rect(d, (25, 6, 26, 8), "#e07a30")
    s.layer(front)
    def scarf(d):
        ell(d, (12, 28, 36, 38), "#e8862e")
        poly(d, [(28, 33), (34, 32), (36, 44), (31, 44)], "#c86a1e")
        rect(d, (16, 30, 22, 31), "#f6a85a")
    s.layer(scarf)
    return save(s, "scout")


if __name__ == "__main__":
    frames = [elder(), herbalist(), villager(), scout()]
    print("drew", len(frames), "NPCs into", ART, [f.size for f in frames])
