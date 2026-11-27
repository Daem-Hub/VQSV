"""Đọc map vẽ bằng Tiled (https://www.mapeditor.org, miễn phí) vào game.

Cách dùng (chạy trong thư mục vqsv-unified-game):
    python3 tools/import_tiled.py            # đọc mọi map trong tiled/maps/ rồi dựng lại prototype/data/bootstrap.js
    python3 tools/import_tiled.py --check    # chỉ đọc và báo lỗi, không ghi file nào
    python3 tools/import_tiled.py a.tmx b.tmj --out-dir /tmp/x --no-build   # đọc file chỉ định, ghi ra chỗ khác

Map Tiled: Orthogonal, không bật Infinite, ô vuông (vd 20 × 20 như VQSV5, hoặc 16, 32...). Lưu dạng .tmx hoặc .tmj/.json.
Mỗi ô Tiled là một ô đi lại của game (game vẽ ô 20 px; ảnh map giữ nguyên độ phân giải gốc và được co giãn cho vừa).

Lớp ô (tile layer):
  - lớp thường            -> vẽ vào ảnh nền (dưới nhân vật)
  - tên bắt đầu "Trên"/"Above"/"Overlay", hoặc thuộc tính above = true -> vẽ đè lên nhân vật (tán cây, mái nhà)
  - tên "Va chạm"/"Collision", hoặc thuộc tính collision = true -> không vẽ; mỗi ô có gạch là ô chặn. Gạch có thuộc tính
    terrain (số) cho biết loại: 1 chặn, 2 nước sâu (chặn, gặp pet nước), 3 nước nông (lội được), 4 chặn, 0 mở lại ô đi được.
  - ở lớp thường, gạch có thuộc tính collides = true (hoặc terrain) cũng chặn ô đó.
  Lớp ẩn (bỏ dấu mắt) không được vẽ; lớp va chạm ẩn vẫn có tác dụng. Lớp nhóm (group) và lớp ảnh (image layer) đều đọc được.

Lớp đối tượng (object layer): loại của đối tượng là ô Class (Tiled 1.9+) hoặc Type, không phân biệt hoa thường hay dấu.
  player / batdau          chỗ Kael đứng khi map được mở lần đầu
  link / loidi             lối sang map khác: target (id map đích), spawnX, spawnY (ô đến ở map đích), label (tên map đích hiện ra, mặc định là title của map đó),
                           kind ("" = lối ở mép map, "door" = cửa vào nhà, "exit" = cửa ra), backX, backY (tùy chọn:
                           thêm luôn lối quay lại vào map đích tại ô đó). Hình chữ nhật phủ nhiều ô = nhiều ô lối đi.
  door / cua, exit         như link với kind door / exit
  npc / nguoi              tên đối tượng là tên NPC; art (elder, herbalist, villager, scout hoặc đường dẫn ảnh trong assets/),
                           dialogue (mỗi dòng một câu), id (tùy chọn)
  chest / ruong            gold (kim tiền) hoặc item (mã vật phẩm VQSV5) + quantity
  wild / hoangda           pet hoang dã riêng của map: pets ("vqsv6-db-33, vqsv6-db-86"), levels ("3-5"), quality ("1-2"),
                           water = true nếu là pet dưới nước
  quest / nhiemvu          tên đối tượng là khóa chỗ làm nhiệm vụ, vd "MQ030:0", "MQ030:npc", "SQ001:2", "ambient:ba-ban-ca"
  block / chan, water / nuocsau, shallow / nuocnong, walk / diduoc   vùng chữ nhật đổi va chạm (1 / 2 / 3 / 0)
  đối tượng gạch (tile object) không có loại -> vẽ vào ảnh như đồ trang trí

Thuộc tính của map: id (bắt buộc nếu khác tên file), title, background (#rrggbb), replace = true (thay map có sẵn cùng id),
  wildAreas ("1-1,1-2": bảng pet hoang dã VQSV6), vqsv5Maps ("17": bảng pet hoang dã VQSV5 của map số đó),
  battleTerrain (loại địa hình trận đấu VQSV5, vd 3), badge = true (lần đầu vào map có pet hoang dã được 1 huy hiệu như các vùng mới).
"""
import argparse
import base64
import gzip
import json
import math
import re
import struct
import subprocess
import sys
import unicodedata
import xml.etree.ElementTree as ET
import zlib
from pathlib import Path

from PIL import Image

ROOT = Path(__file__).resolve().parents[1]
TILED_DIR = ROOT / "tiled" / "maps"
PROTO = ROOT / "prototype"
FLIP_H, FLIP_V, FLIP_D, FLIP_HEX = 0x80000000, 0x40000000, 0x20000000, 0x10000000
GID_MASK = 0x0FFFFFFF
GAME_CELL = 20
NPC_ART = {"elder": "illustrations/elder_hd.png", "herbalist": "characters/villager_74.png",
           "villager": "characters/villager_66.png", "scout": "characters/villager_75.png"}


class TiledError(Exception):
    pass


def norm(text):
    """Lowercase, no Vietnamese accents, letters and digits only: "Va chạm" -> "vacham"."""
    text = unicodedata.normalize("NFD", str(text or "")).replace("đ", "d").replace("Đ", "D")
    return re.sub(r"[^a-z0-9]", "", "".join(c for c in text if unicodedata.category(c) != "Mn").lower())


ROLES = {
    "player": {"player", "spawn", "start", "batdau", "kael", "nguoichoi"},
    "link": {"link", "warp", "loidi", "loi", "cong", "gate"},
    "door": {"door", "cua", "cuavao"},
    "exit": {"exit", "cuara"},
    "npc": {"npc", "nguoi", "nhanvat", "dan"},
    "chest": {"chest", "ruong"},
    "wild": {"wild", "pet", "hoangda", "pethoangda"},
    "quest": {"quest", "nhiemvu", "questspot"},
    "block": {"block", "wall", "chan", "vatcan"},
    "deep": {"water", "deepwater", "nuoc", "nuocsau"},
    "shallow": {"shallow", "shallowwater", "nuocnong"},
    "walk": {"walk", "open", "diduoc", "mo"},
}
ROLE_OF = {alias: role for role, aliases in ROLES.items() for alias in aliases}
AREA_DIGIT = {"block": "1", "deep": "2", "shallow": "3", "walk": "0"}
TERRAIN_WORDS = {"block": 1, "chan": 1, "wall": 1, "deep": 2, "water": 2, "nuocsau": 2, "shallow": 3, "nuocnong": 3, "walk": 0, "open": 0, "diduoc": 0}


# ---------------------------------------------------------------- reading .tmx / .tsx (XML) into the JSON layout

def _num(value, kind=float):
    return kind(value) if value not in (None, "") else None


def _xml_props(el):
    out = []
    holder = el.find("properties")
    if holder is None:
        return out
    for p in holder.findall("property"):
        kind, value = p.get("type", "string"), p.get("value")
        if value is None:
            value = p.text or ""
        if kind == "int":
            value = int(value)
        elif kind == "float":
            value = float(value)
        elif kind == "bool":
            value = value == "true"
        elif kind == "class":
            value = {q["name"]: q["value"] for q in _xml_props(p)}
        out.append({"name": p.get("name"), "type": kind, "value": value})
    return out


def _xml_image(el, into):
    img = el.find("image")
    if img is not None:
        into["image"] = img.get("source")
        if img.get("width"):
            into["imagewidth"], into["imageheight"] = int(img.get("width")), int(img.get("height"))
        if img.get("trans"):
            into["transparentcolor"] = "#" + img.get("trans").lstrip("#")


def tsx_to_json(root):
    ts = {k: _num(root.get(k), int) for k in ("tilewidth", "tileheight", "tilecount", "columns", "margin", "spacing")}
    ts["name"] = root.get("name", "")
    for key in ("tilerendersize", "fillmode", "objectalignment"):
        if root.get(key):
            ts[key] = root.get(key)
    off = root.find("tileoffset")
    if off is not None:
        ts["tileoffset"] = {"x": int(off.get("x", 0)), "y": int(off.get("y", 0))}
    _xml_image(root, ts)
    ts["properties"] = _xml_props(root)
    tiles = []
    for t in root.findall("tile"):
        tile = {"id": int(t.get("id")), "properties": _xml_props(t)}
        if t.get("type") or t.get("class"):
            tile["type"] = t.get("type") or t.get("class")
        _xml_image(t, tile)
        for key in ("x", "y", "width", "height"):
            if t.get(key):
                tile[key] = int(t.get(key))
        tiles.append(tile)
    ts["tiles"] = tiles
    return ts


def _xml_data(el, layer):
    data = el.find("data")
    if data is None:
        return
    if data.findall("chunk"):
        layer["chunks"] = True
        return
    enc, comp = data.get("encoding"), data.get("compression")
    if enc is None:
        layer["data"] = [int(t.get("gid", 0)) for t in data.findall("tile")]
    else:
        layer["encoding"], layer["data"] = enc, (data.text or "").strip()
        if comp:
            layer["compression"] = comp


def _xml_layer(el):
    tag = el.tag
    layer = {"name": el.get("name", ""), "visible": el.get("visible", "1") != "0", "opacity": float(el.get("opacity", 1)),
             "offsetx": float(el.get("offsetx", 0)), "offsety": float(el.get("offsety", 0)), "properties": _xml_props(el)}
    if el.get("class"):
        layer["class"] = el.get("class")
    if tag == "layer":
        layer.update(type="tilelayer", width=int(el.get("width")), height=int(el.get("height")))
        _xml_data(el, layer)
    elif tag == "objectgroup":
        layer.update(type="objectgroup", draworder=el.get("draworder", "topdown"), objects=[])
        for o in el.findall("object"):
            obj = {"id": int(o.get("id", 0)), "name": o.get("name", ""), "type": o.get("type") or o.get("class") or "",
                   "x": float(o.get("x", 0)), "y": float(o.get("y", 0)), "width": float(o.get("width", 0)),
                   "height": float(o.get("height", 0)), "rotation": float(o.get("rotation", 0)),
                   "visible": o.get("visible", "1") != "0", "properties": _xml_props(o)}
            if o.get("gid"):
                obj["gid"] = int(o.get("gid"))
            if o.find("point") is not None:
                obj["point"] = True
            if o.find("ellipse") is not None:
                obj["ellipse"] = True
            poly = o.find("polygon") if o.find("polygon") is not None else o.find("polyline")
            if poly is not None:
                obj["polygon"] = [{"x": float(a), "y": float(b)} for a, b in (p.split(",") for p in poly.get("points", "").split())]
            layer["objects"].append(obj)
    elif tag == "imagelayer":
        layer.update(type="imagelayer", repeatx=el.get("repeatx") == "1", repeaty=el.get("repeaty") == "1")
        _xml_image(el, layer)
    elif tag == "group":
        layer.update(type="group", layers=[_xml_layer(c) for c in el if c.tag in ("layer", "objectgroup", "imagelayer", "group")])
    return layer


def tmx_to_json(root):
    tm = {k: root.get(k) for k in ("orientation", "renderorder", "backgroundcolor")}
    for k in ("width", "height", "tilewidth", "tileheight"):
        tm[k] = int(root.get(k))
    tm["infinite"] = root.get("infinite") == "1"
    tm["properties"] = _xml_props(root)
    tm["tilesets"] = []
    for ts in root.findall("tileset"):
        ref = {"firstgid": int(ts.get("firstgid"))}
        if ts.get("source"):
            ref["source"] = ts.get("source")
        else:
            ref.update(tsx_to_json(ts))
        tm["tilesets"].append(ref)
    tm["layers"] = [_xml_layer(c) for c in root if c.tag in ("layer", "objectgroup", "imagelayer", "group")]
    return tm


def load_json_or_xml(path):
    path = Path(path)
    if path.suffix.lower() in (".tmx", ".tsx"):
        root = ET.parse(path).getroot()
        return tmx_to_json(root) if root.tag == "map" else tsx_to_json(root)
    return json.loads(path.read_text(encoding="utf-8"))


# ---------------------------------------------------------------- properties, tiles, pictures

def props(thing):
    raw = thing.get("properties") or []
    if isinstance(raw, dict):                                   # very old Tiled JSON
        return dict(raw)
    return {p["name"]: p.get("value") for p in raw}


def kind_of(thing):
    return norm(thing.get("type") or thing.get("class") or "")


def truthy(value):
    return value is True or norm(value) in ("true", "1", "yes", "co")


def parse_color(text, default=None):
    text = (text or "").strip().lstrip("#")
    if len(text) == 8:                                          # Tiled writes #AARRGGBB
        text = text[2:]
    if len(text) != 6:
        return default
    return "#" + text.lower()


def apply_flips(img, flags):
    """Tiled order: the diagonal flip first, then horizontal, then vertical."""
    if flags & FLIP_D:
        img = img.transpose(Image.Transpose.TRANSPOSE)
    if flags & FLIP_H:
        img = img.transpose(Image.Transpose.FLIP_LEFT_RIGHT)
    if flags & FLIP_V:
        img = img.transpose(Image.Transpose.FLIP_TOP_BOTTOM)
    return img


def fade(img, opacity):
    if opacity >= 1:
        return img
    alpha = img.getchannel("A").point(lambda a: round(a * opacity))
    out = img.copy()
    out.putalpha(alpha)
    return out


def paste_over(canvas, img, x, y):
    """alpha_composite that also accepts pictures hanging over the left or top edge."""
    x, y = int(round(x)), int(round(y))
    left, top = max(0, -x), max(0, -y)
    if left >= img.width or top >= img.height or x >= canvas.width or y >= canvas.height:
        return
    if left or top:
        img = img.crop((left, top, img.width, img.height))
    canvas.alpha_composite(img, (x + left, y + top))


class Tiles:
    """gid -> tileset entry and picture (with the flip flags applied)."""

    def __init__(self, tm, map_dir):
        self.sets, self.images, self.cache = [], {}, {}
        for ref in tm.get("tilesets", []):
            if "source" in ref:
                path = (map_dir / ref["source"]).resolve()
                if not path.exists():
                    raise TiledError(f"không thấy tileset {ref['source']}")
                ts, base = load_json_or_xml(path), path.parent
            else:
                ts, base = ref, map_dir
            by_id = {t["id"]: t for t in ts.get("tiles", [])}
            self.sets.append((ref["firstgid"], ts, base, by_id))
        self.sets.sort(key=lambda s: s[0])

    def find(self, gid):
        gid &= GID_MASK
        hit = None
        for entry in self.sets:
            if entry[0] <= gid:
                hit = entry
        if hit is None:
            raise TiledError(f"gạch số {gid} không thuộc tileset nào")
        first, ts, base, by_id = hit
        return ts, gid - first, base, by_id.get(gid - first, {})

    def meta(self, gid):
        return self.find(gid)[3]

    def open(self, path, transparent=None):
        key = (str(path), transparent)
        if key not in self.images:
            if not Path(path).exists():
                raise TiledError(f"không thấy ảnh {path}")
            img = Image.open(path).convert("RGBA")
            if transparent:
                r, g, b = (int(transparent[i:i + 2], 16) for i in (1, 3, 5))
                img.putdata([(0, 0, 0, 0) if p[:3] == (r, g, b) else p for p in img.getdata()])
            self.images[key] = img
        return self.images[key]

    def picture(self, gid, grid=None):
        """(image, offset x, offset y) for a gid with its flips."""
        if gid in self.cache:
            return self.cache[gid]
        ts, local, base, tile = self.find(gid)
        trans = parse_color(ts.get("transparentcolor"))
        if tile.get("image"):                                    # collection of images
            img = self.open(base / tile["image"], trans)
            if "width" in tile:
                x, y = tile.get("x", 0), tile.get("y", 0)
                img = img.crop((x, y, x + tile["width"], y + tile["height"]))
        else:
            if not ts.get("image"):
                raise TiledError(f"tileset {ts.get('name')} không có ảnh cho gạch {local}")
            sheet = self.open(base / ts["image"], trans)
            tw, th, margin, spacing = ts["tilewidth"], ts["tileheight"], ts.get("margin", 0) or 0, ts.get("spacing", 0) or 0
            columns = ts.get("columns") or max(1, (sheet.width - 2 * margin + spacing) // (tw + spacing))
            x, y = margin + (local % columns) * (tw + spacing), margin + (local // columns) * (th + spacing)
            img = sheet.crop((x, y, x + tw, y + th))
        if ts.get("tilerendersize") == "grid" and grid:
            img = img.resize(grid, Image.Resampling.NEAREST)
        img = apply_flips(img, gid)
        off = ts.get("tileoffset") or {}
        self.cache[gid] = (img, off.get("x", 0), off.get("y", 0))
        return self.cache[gid]


def layer_gids(layer, width, height):
    if layer.get("chunks"):
        raise TiledError("map đang bật Infinite (vô hạn). Hãy vào Map > Map Properties, bỏ chọn Infinite rồi lưu lại")
    data, enc = layer.get("data"), layer.get("encoding", "csv")
    if isinstance(data, list):
        gids = [int(g) for g in data]
    elif enc == "base64":
        raw, comp = base64.b64decode(data), layer.get("compression") or ""
        if comp == "zlib":
            raw = zlib.decompress(raw)
        elif comp == "gzip":
            raw = gzip.decompress(raw)
        elif comp:
            raise TiledError(f"lớp '{layer.get('name')}' nén kiểu {comp}; hãy chọn nén zlib, gzip hoặc CSV trong Map Properties")
        gids = list(struct.unpack(f"<{len(raw) // 4}I", raw))
    else:
        gids = [int(v) for v in str(data or "").replace("\n", "").split(",") if v.strip()]
    if len(gids) != width * height:
        raise TiledError(f"lớp '{layer.get('name')}' có {len(gids)} ô, cần {width * height}")
    return gids


# ---------------------------------------------------------------- one map

def auto(value):
    """A cell number from a property; empty or -1 (the default in tiled/vqsv.tiled-project) means "work it out"."""
    if value in (None, "") or str(value).strip() in ("", "-1"):
        return None
    return int(value)


def tile_terrain(meta):
    """Collision digit given by a tile's properties, or None."""
    p = props(meta)
    if "terrain" in p:
        value = p["terrain"]
        if isinstance(value, str) and not value.strip().isdigit():
            value = TERRAIN_WORDS.get(norm(value), 1)
        return str(int(value))
    if truthy(p.get("collides")) or truthy(p.get("chan")) or kind_of(meta) in ("collision", "block", "chan"):
        return "1"
    return None


def layer_role(layer, inherited):
    p, name, cls = props(layer), norm(layer.get("name")), norm(layer.get("class"))
    if inherited == "collision" or truthy(p.get("collision")) or cls == "collision" or name in ("collision", "vacham", "chan", "vatcan") or name.startswith("vacham") or name.startswith("collision"):
        return "collision"
    if inherited == "above" or truthy(p.get("above")) or cls == "above" or any(name.startswith(w) for w in ("above", "overlay", "tren", "phutren")):
        return "above"
    return "below"


class MapReader:
    def __init__(self, path):
        self.path = Path(path)
        self.tm = load_json_or_xml(self.path)
        tm = self.tm
        if (tm.get("orientation") or "orthogonal") != "orthogonal":
            raise TiledError("chỉ đọc map Orthogonal (ô vuông nhìn từ trên xuống)")
        if tm.get("infinite"):
            raise TiledError("map đang bật Infinite (vô hạn). Hãy vào Map > Map Properties, bỏ chọn Infinite rồi lưu lại")
        self.W, self.H, self.tw, self.th = tm["width"], tm["height"], tm["tilewidth"], tm["tileheight"]
        if self.tw != self.th:
            raise TiledError(f"ô của map phải vuông (đang {self.tw} × {self.th})")
        self.tiles = Tiles(tm, self.path.parent)
        self.p = props(tm)
        self.id = str(self.p.get("id") or self.path.stem).strip()
        if not re.fullmatch(r"[a-z0-9][a-z0-9_-]*", self.id):
            raise TiledError(f"id map '{self.id}' chỉ được dùng chữ thường không dấu, số, '-' và '_' (đặt thuộc tính id của map)")
        self.title = str(self.p.get("title") or self.p.get("ten") or self.id)
        self.background = parse_color(self.p.get("background")) or parse_color(tm.get("backgroundcolor")) or "#1c2e22"
        size = (self.W * self.tw, self.H * self.th)
        self.base = Image.new("RGBA", size, self.background)
        self.over = Image.new("RGBA", size, (0, 0, 0, 0))
        self.tile_cells = [["0"] * self.W for _ in range(self.H)]      # from collides/terrain tiles in drawn layers
        self.layer_cells = [[None] * self.W for _ in range(self.H)]    # from collision layers
        self.area_cells = [[None] * self.W for _ in range(self.H)]     # from block/water objects
        self.objects = []
        self.warnings = []

    # -- layers
    def walk(self, layers, off=(0.0, 0.0), opacity=1.0, visible=True, role=None):
        for layer in layers:
            here = (off[0] + float(layer.get("offsetx", 0) or 0), off[1] + float(layer.get("offsety", 0) or 0))
            alpha = opacity * float(layer.get("opacity", 1) if layer.get("opacity") is not None else 1)
            shown = visible and layer.get("visible", True) is not False
            r = layer_role(layer, role)
            kind = layer.get("type")
            if kind == "group":
                self.walk(layer.get("layers", []), here, alpha, shown, r if r != "below" else role)
            elif kind == "tilelayer":
                self.tile_layer(layer, here, alpha, shown, r)
            elif kind == "imagelayer" and shown and r != "collision" and layer.get("image"):
                self.image_layer(layer, here, alpha, r)
            elif kind == "objectgroup":
                for obj in layer.get("objects", []):
                    self.objects.append((obj, r, shown, alpha, here))

    def tile_layer(self, layer, off, alpha, shown, role):
        gids = layer_gids(layer, self.W, self.H)
        if role == "collision":
            for i, gid in enumerate(gids):
                if gid & GID_MASK:
                    digit = tile_terrain(self.tiles.meta(gid))
                    self.layer_cells[i // self.W][i % self.W] = digit if digit is not None else "1"
            return
        for i, gid in enumerate(gids):
            if gid & GID_MASK:
                digit = tile_terrain(self.tiles.meta(gid))
                if digit is not None:
                    self.tile_cells[i // self.W][i % self.W] = digit
        if not shown:
            return
        canvas = self.over if role == "above" else self.base
        order = self.tm.get("renderorder") or "right-down"
        xs = range(self.W) if order.startswith("right") else range(self.W - 1, -1, -1)
        ys = range(self.H) if order.endswith("down") else range(self.H - 1, -1, -1)
        for y in ys:
            for x in xs:
                gid = gids[y * self.W + x]
                if not gid & GID_MASK:
                    continue
                img, ox, oy = self.tiles.picture(gid, (self.tw, self.th))
                paste_over(canvas, fade(img, alpha), x * self.tw + off[0] + ox, (y + 1) * self.th - img.height + off[1] + oy)

    def image_layer(self, layer, off, alpha, role):
        img = fade(self.tiles.open(self.path.parent / layer["image"], parse_color(layer.get("transparentcolor"))), alpha)
        canvas = self.over if role == "above" else self.base
        xs = range(int(off[0]) % img.width - img.width, canvas.width, img.width) if layer.get("repeatx") else [off[0]]
        ys = range(int(off[1]) % img.height - img.height, canvas.height, img.height) if layer.get("repeaty") else [off[1]]
        for y in ys:
            for x in xs:
                paste_over(canvas, img, x, y)

    # -- objects
    def cell_of(self, obj):
        t = self.tw
        x, y, w, h = obj.get("x", 0), obj.get("y", 0), obj.get("width", 0) or 0, obj.get("height", 0) or 0
        if obj.get("gid"):                                    # tile objects stand on their bottom edge
            cx, cy = (x + w / 2) / t, (y - 0.01) / t
        elif obj.get("point") or (w == 0 and h == 0 and not obj.get("polygon")):
            cx, cy = x / t, y / t
        else:
            x0, y0, x1, y1 = self.box(obj)
            cx, cy = (x0 + x1) / 2 / t, (y0 + y1) / 2 / t
        return min(self.W - 1, max(0, math.floor(cx))), min(self.H - 1, max(0, math.floor(cy)))

    def box(self, obj):
        x, y, w, h = obj.get("x", 0), obj.get("y", 0), obj.get("width", 0) or 0, obj.get("height", 0) or 0
        if obj.get("polygon"):
            xs, ys = [x + p["x"] for p in obj["polygon"]], [y + p["y"] for p in obj["polygon"]]
            return min(xs), min(ys), max(xs), max(ys)
        if obj.get("gid"):
            return x, y - h, x + w, y
        return x, y, x + w, y + h

    def cells_of(self, obj):
        x0, y0, x1, y1 = self.box(obj)
        if x1 - x0 < 1 and y1 - y0 < 1:
            return [self.cell_of(obj)]
        t = self.tw
        cols = range(max(0, math.floor(x0 / t)), min(self.W, max(math.floor(x0 / t) + 1, math.ceil(x1 / t))))
        rows = range(max(0, math.floor(y0 / t)), min(self.H, max(math.floor(y0 / t) + 1, math.ceil(y1 / t))))
        return [(cx, cy) for cy in rows for cx in cols]

    def draw_tile_object(self, obj, role, alpha, off):
        img, ox, oy = self.tiles.picture(obj["gid"])
        w, h = round(obj.get("width") or img.width), round(obj.get("height") or img.height)
        if (w, h) != img.size:
            img = img.resize((max(1, w), max(1, h)), Image.Resampling.NEAREST)
        if obj.get("rotation"):
            self.warnings.append(f"đối tượng gạch #{obj.get('id')} bị xoay; ảnh được vẽ không xoay")
        paste_over(self.over if role == "above" else self.base, fade(img, alpha), obj["x"] + off[0] + ox, obj["y"] - h + off[1] + oy)

    def inward(self, x, y, grid):
        """The open cell next to an exit, toward the inside of the map."""
        if x == 0:
            order = [(1, 0)]
        elif x == self.W - 1:
            order = [(-1, 0)]
        elif y == 0:
            order = [(0, 1)]
        elif y == self.H - 1:
            order = [(0, -1)]
        else:
            order = [(0, 1)]
        order += [d for d in ((0, 1), (1, 0), (-1, 0), (0, -1)) if d not in order]
        for dx, dy in order:
            nx, ny = x + dx, y + dy
            if 0 <= nx < self.W and 0 <= ny < self.H and grid[ny][nx] not in "124":
                return [nx, ny]
        return [x + order[0][0], y + order[0][1]] if 0 <= x + order[0][0] < self.W and 0 <= y + order[0][1] < self.H else [x, y]

    def read(self):
        self.walk(self.tm.get("layers", []))
        for obj, role, shown, alpha, off in self.objects:
            r = ROLE_OF.get(kind_of(obj))
            if r in AREA_DIGIT:
                for cx, cy in self.cells_of(obj):
                    self.area_cells[cy][cx] = AREA_DIGIT[r]
        grid = [[self.area_cells[y][x] or self.layer_cells[y][x] or self.tile_cells[y][x] for x in range(self.W)] for y in range(self.H)]
        out = {"id": self.id, "title": self.title, "width": self.W, "height": self.H, "tileSize": GAME_CELL,
               "tiled": {"source": self.path.relative_to(ROOT).as_posix() if self.path.is_relative_to(ROOT) else self.path.name,
                         "background": self.background, "cell": self.tw},
               "collision": ["".join(row) for row in grid],
               "ground": {"tileset": 4, "tile": 25, "step": 2}, "groundVariation": [], "groundDecor": [], "paths": [], "objects": [],
               "entities": {"villagers": []}, "links": [], "chests": [], "arrivals": {}}
        out["regionBadge"] = truthy(self.p.get("badge"))
        if truthy(self.p.get("replace")):
            out["replace"] = True
        if self.p.get("wildAreas"):
            out["wildAreas"] = [a.strip() for a in str(self.p["wildAreas"]).split(",") if a.strip()]
        sources = [int(a) for a in re.findall(r"\d+", str(self.p.get("vqsv5Maps") or ""))]
        terrain = self.p.get("battleTerrain")
        if sources or terrain not in (None, ""):
            out["terrain5"] = {"source_maps": sources, "types": [int(terrain)] if terrain not in (None, "") else []}
        if sources and not out.get("wildAreas"):
            out["wildAreas"] = []
        wild, spots = [], {}
        for obj, role, shown, alpha, off in self.objects:
            kind, p, name = kind_of(obj), props(obj), (obj.get("name") or "").strip()
            r = ROLE_OF.get(kind)
            where = f"đối tượng '{name or kind}' (#{obj.get('id')})"
            if r is None:
                if obj.get("gid") and shown and obj.get("visible", True) is not False and role != "collision":
                    self.draw_tile_object(obj, role, alpha, off)
                elif kind:
                    self.warnings.append(f"{where}: không hiểu loại '{obj.get('type') or obj.get('class')}', bỏ qua")
                continue
            x, y = self.cell_of(obj)
            if r == "player":
                out["entities"]["player"] = {"x": x, "y": y}
            elif r in ("link", "door", "exit"):
                target = str(p.get("target") or p.get("den") or "").strip()
                if not target:
                    raise TiledError(f"{where}: lối đi thiếu thuộc tính target (id map đích)")
                link_kind = str(p.get("kind") or ("" if r == "link" else r)).strip()
                if link_kind not in ("", "door", "exit"):
                    raise TiledError(f"{where}: kind chỉ được là trống, door hoặc exit")
                cells = self.cells_of(obj) if not link_kind else [(x, y)]
                for i, (cx, cy) in enumerate(cells):
                    link = {"x": cx, "y": cy, "target": target, "spawnX": auto(p.get("spawnX")), "spawnY": auto(p.get("spawnY")), "label": str(p.get("label") or "")}
                    if link_kind:
                        link["kind"] = link_kind
                    if i == 0 and auto(p.get("backX")) is not None and auto(p.get("backY")) is not None:
                        link["back"] = [int(p["backX"]), int(p["backY"])]
                    out["links"].append(link)
                out["arrivals"].setdefault(target, self.inward(x, y, grid))
            elif r == "npc":
                lines = [line.strip() for line in str(p.get("dialogue") or p.get("loithoai") or "").splitlines() if line.strip()]
                art = str(p.get("art") or p.get("hinh") or "villager").strip()
                npc = {"id": str(p.get("id") or f"{self.id}-npc-{obj.get('id')}"), "name": name or "Người dân", "x": x, "y": y,
                       "asset": NPC_ART.get(norm(art), art), "dialogue": lines or ["..."]}
                if not (PROTO / "assets" / npc["asset"]).exists():
                    raise TiledError(f"{where}: không thấy hình NPC assets/{npc['asset']} (art: elder, herbalist, villager, scout hoặc đường dẫn trong assets/)")
                out["entities"]["villagers"].append(npc)
            elif r == "chest":
                reward = {"kind": "gold", "kim_tien": int(p["gold"])} if p.get("gold") not in (None, "", 0) else None
                if reward is None:
                    if p.get("item") in (None, "", -1):
                        raise TiledError(f"{where}: rương cần gold hoặc item")
                    reward = {"kind": "item", "item_id": int(p["item"]), "name": str(p.get("name") or ""), "quantity": int(p.get("quantity") or 1)}
                out["chests"].append({"id": f"{self.id}:tiled-{obj.get('id')}", "x": x, "y": y, "reward": reward})
            elif r == "wild":
                pets = [s.strip() for s in re.split(r"[,;\s]+", str(p.get("pets") or "")) if s.strip()]
                levels = [int(v) for v in re.findall(r"\d+", str(p.get("levels") or p.get("level") or "1-3"))] or [1, 3]
                quality = [int(v) for v in re.findall(r"\d+", str(p.get("quality") or "1-2"))] or [1, 2]
                if not pets:
                    raise TiledError(f"{where}: vùng pet hoang dã cần thuộc tính pets")
                wild.append({"area": f"tiled-{self.id}-{obj.get('id')}", "level_range": [min(levels), max(levels)],
                             "pets": [{"pet_id": pet, "terrain": 2 if truthy(p.get("water")) else 0, "quality_range": [min(quality), max(quality)]} for pet in pets]})
            elif r == "quest":
                if not name:
                    raise TiledError(f"{where}: chỗ nhiệm vụ cần tên, vd MQ030:0")
                spots[name] = {"x": x, "y": y}
        if wild:
            out["customWildAreas"] = wild
        if spots:
            out["questSpots"] = spots
        if "player" not in out["entities"]:
            first = out["links"][0] if out["links"] else None
            spot = out["arrivals"][first["target"]] if first else [self.W // 2, self.H // 2]
            out["entities"]["player"] = {"x": spot[0], "y": spot[1]}
            self.warnings.append("chưa có đối tượng player; Kael sẽ đứng cạnh lối đi đầu tiên")
        if not out["links"]:
            self.warnings.append("map chưa có lối đi (link) nào nên chưa vào được từ map khác")
        return out


def write_pictures(reader, out, img_dir, rel_dir):
    img_dir.mkdir(parents=True, exist_ok=True)
    base_rel = f"{rel_dir}/{out['id']}.png"
    reader.base.convert("RGB").save(img_dir / f"{out['id']}.png", optimize=True)
    out["tiled"]["base"] = base_rel
    over_path = img_dir / f"{out['id']}_over.png"
    if reader.over.getbbox():
        reader.over.save(over_path, optimize=True)
        out["tiled"]["overlay"] = f"{rel_dir}/{out['id']}_over.png"
    elif over_path.exists():
        over_path.unlink()


def import_maps(files, out_dir=None, check=False):
    """Read the Tiled maps; returns (maps, warnings). Writes tiled_maps.json and the pictures unless check."""
    maps, warnings, seen = [], [], {}
    for path in files:
        try:
            reader = MapReader(path)
            out = reader.read()
        except TiledError as err:
            raise TiledError(f"{Path(path).name}: {err}") from None
        if out["id"] in seen:
            raise TiledError(f"{Path(path).name}: id '{out['id']}' trùng với {seen[out['id']]}")
        seen[out["id"]] = Path(path).name
        warnings += [f"{Path(path).name}: {w}" for w in reader.warnings]
        if not check:
            data_dir = Path(out_dir) if out_dir else PROTO / "data"
            img_dir = Path(out_dir) / "maps" if out_dir else PROTO / "assets" / "maps" / "tiled"
            write_pictures(reader, out, img_dir, "assets/maps/tiled")
        maps.append(out)
    # links between two Tiled maps without spawnX/spawnY: arrive next to the partner link
    by_id = {m["id"]: m for m in maps}
    for m in maps:
        for link in m["links"]:
            other = by_id.get(link["target"])
            if (link["spawnX"] is None or link["spawnY"] is None) and other and m["id"] in other["arrivals"]:
                link["spawnX"], link["spawnY"] = other["arrivals"][m["id"]]
    if not check:
        data_dir = Path(out_dir) if out_dir else PROTO / "data"
        data_dir.mkdir(parents=True, exist_ok=True)
        (data_dir / "tiled_maps.json").write_text(json.dumps({"source": "tools/import_tiled.py (map vẽ bằng Tiled trong tiled/maps/)", "maps": maps},
                                                             ensure_ascii=False, indent=1), encoding="utf-8")
    return maps, warnings


# ---------------------------------------------------------------- joining the imported maps to the world (build_prototype_bootstrap.py)

def _open(m, x, y):
    rows = m.get("collision")
    return 0 <= x < m["width"] and 0 <= y < m["height"] and (not rows or rows[y][x] not in "124")


def _inward(m, x, y):
    w, h = m["width"], m["height"]
    first = (1, 0) if x == 0 else (-1, 0) if x == w - 1 else (0, 1) if y == 0 else (0, -1) if y == h - 1 else (0, 1)
    for dx, dy in [first] + [d for d in ((0, 1), (1, 0), (-1, 0), (0, -1)) if d != first]:
        if _open(m, x + dx, y + dy):
            return [x + dx, y + dy]
    return [min(w - 1, max(0, x + first[0])), min(h - 1, max(0, y + first[1]))]


def attach_tiled_maps(maps, data_dir=PROTO / "data", log=print):
    """Add the maps of tiled_maps.json to `maps` (id -> map), add their way-back links, check every link. Returns their ids."""
    path = Path(data_dir) / "tiled_maps.json"
    if not path.exists():
        return []
    imported = json.loads(path.read_text(encoding="utf-8"))["maps"]
    item_names = {c["reward"]["item_id"]: c["reward"]["name"] for m in maps.values() for c in m.get("chests", []) if c["reward"].get("item_id") is not None}
    for m in imported:
        if m["id"] in maps and not m.get("replace"):
            raise TiledError(f"map Tiled '{m['id']}' trùng id với map có sẵn; đặt thuộc tính replace = true nếu muốn thay map đó")
        if m.pop("replace", False) and m["id"] not in maps:
            log(f"  chú ý: '{m['id']}' có replace = true nhưng chưa có map nào cùng id")
        maps[m["id"]] = m
    for m in imported:
        for link in m["links"]:
            target = maps.get(link["target"])
            if target is None:
                raise TiledError(f"{m['id']}: lối đi ({link['x']},{link['y']}) dẫn tới map '{link['target']}' không có")
            back = link.pop("back", None)
            if back:
                bx, by = back
                if not (0 <= bx < target["width"] and 0 <= by < target["height"]):
                    raise TiledError(f"{m['id']}: backX/backY ({bx},{by}) nằm ngoài map '{target['id']}'")
                if any(l["x"] == bx and l["y"] == by for l in target.get("links", [])):
                    raise TiledError(f"{m['id']}: ô ({bx},{by}) của '{target['id']}' đã có lối đi khác")
                arrive = m["arrivals"].get(target["id"]) or _inward(m, link["x"], link["y"])
                rev = {"x": bx, "y": by, "target": m["id"], "spawnX": arrive[0], "spawnY": arrive[1], "label": m["title"]}
                if link.get("kind"):
                    rev["kind"] = "exit" if link["kind"] == "door" else "door"
                target.setdefault("links", []).append(rev)
                if link["spawnX"] is None or link["spawnY"] is None:
                    link["spawnX"], link["spawnY"] = _inward(target, bx, by)
                target.setdefault("arrivals", {})[m["id"]] = [link["spawnX"], link["spawnY"]]
            if link["spawnX"] is None or link["spawnY"] is None:
                partner = next((l for l in target.get("links", []) if l["target"] == m["id"]), None)
                if not partner:
                    raise TiledError(f"{m['id']}: lối đi ({link['x']},{link['y']}) tới '{target['id']}' thiếu spawnX/spawnY (hoặc backX/backY)")
                link["spawnX"], link["spawnY"] = _inward(target, partner["x"], partner["y"])
            link["spawnX"], link["spawnY"] = int(link["spawnX"]), int(link["spawnY"])
            if not (0 <= link["spawnX"] < target["width"] and 0 <= link["spawnY"] < target["height"]):
                raise TiledError(f"{m['id']}: spawnX/spawnY ({link['spawnX']},{link['spawnY']}) nằm ngoài map '{target['id']}'")
            if not _open(target, link["spawnX"], link["spawnY"]):
                log(f"  chú ý: {m['id']} -> {target['id']}: ô đến ({link['spawnX']},{link['spawnY']}) bị chặn")
            if not link["label"]:
                link["label"] = target.get("title", target["id"])
        for chest in m.get("chests", []):
            reward = chest["reward"]
            if reward["kind"] == "item" and not reward["name"]:
                reward["name"] = item_names.get(reward["item_id"], f"Vật phẩm {reward['item_id']}")
    # links from older maps into a replaced map must still land inside it
    ids = {m["id"] for m in imported}
    for other in maps.values():
        for link in other.get("links", []):
            if link["target"] in ids and other["id"] not in ids and not _open(maps[link["target"]], link["spawnX"], link["spawnY"]):
                log(f"  chú ý: {other['id']} -> {link['target']}: ô đến ({link['spawnX']},{link['spawnY']}) bị chặn hoặc nằm ngoài map")
    return [m["id"] for m in imported]


def main():
    ap = argparse.ArgumentParser(description="Đọc map Tiled vào game VQSV")
    ap.add_argument("files", nargs="*", help="file .tmx/.tmj/.json (mặc định: mọi map trong tiled/maps/)")
    ap.add_argument("--check", action="store_true", help="chỉ kiểm tra, không ghi file")
    ap.add_argument("--out-dir", help="ghi tiled_maps.json và ảnh vào thư mục này (để thử)")
    ap.add_argument("--no-build", action="store_true", help="không chạy build_prototype_bootstrap.py sau khi đọc")
    args = ap.parse_args()
    files = [Path(f) for f in args.files] or sorted(p for p in TILED_DIR.glob("*") if p.suffix.lower() in (".tmx", ".tmj", ".json"))
    try:
        maps, warnings = import_maps(files, args.out_dir, args.check)
    except TiledError as err:
        print(f"LỖI: {err}", file=sys.stderr)
        sys.exit(1)
    for m in maps:
        print(f"  {m['id']}: {m['title']} ({m['width']} × {m['height']} ô, {len(m['links'])} ô lối đi, "
              f"{len(m['entities']['villagers'])} NPC, {len(m['chests'])} rương)")
    for w in warnings:
        print(f"  chú ý: {w}")
    if args.check:
        print(f"Đã kiểm tra {len(maps)} map Tiled, không ghi file.")
        return
    print(f"Đã đọc {len(maps)} map Tiled.")
    if not args.out_dir and not args.no_build:
        subprocess.run([sys.executable, str(ROOT / "tools" / "build_prototype_bootstrap.py")], check=True, cwd=ROOT)


if __name__ == "__main__":
    main()
