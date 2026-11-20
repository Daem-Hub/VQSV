#!/usr/bin/env python3
"""Package the prototype as browser files that need no local server.

Outputs (default: vqsv-unified-game/dist_mobile/):
  web/index.html + web/assets_pack_N.js   page body for a hosted page (no doctype; the host adds it)
  vqsv_mobile.html                        one self-contained file with every image inlined

Images are embedded as data: URIs in packs. A small hook placed before every
other script maps any "assets/..." path given to an <img>/Image() to its data URI,
so the game code runs unchanged. skill_fx.js (an ES module importing pixelfx) is
bundled with esbuild into one classic script that starts at DOMContentLoaded,
the same moment the module used to run.

Usage: python3 tools/build_mobile_html.py [--out DIR] [--esbuild PATH] [--no-optimize]
"""
import argparse, base64, glob, json, os, re, shutil, subprocess, sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
PROTO = os.path.join(ROOT, "prototype")
PACK_LIMIT = 11_000_000  # bytes of JS per pack, well under a 16MB file limit

TEXT_SOURCES = ["index.html", "pet_book.js", "skill_book.js", "world_atlas.js", "skill_fx.js", "field_map.js", "village_ui.js", "shop_room.js", "expedition.css", "classic.css", "battle.css",
                "sound_fx.js", "data/bootstrap.js", "pet_book.css", "world_atlas.css"]

HOOK = r"""<script>
(function () {
  var A = window.__VQSV_ASSETS = window.__VQSV_ASSETS || {};
  function key(u) { u = String(u || ""); var i = u.indexOf("assets/"); return i >= 0 ? u.slice(i).split(/[?#]/)[0] : null; }
  function map(v) { var k = key(v); return (k && A[k]) || v; }
  var d = Object.getOwnPropertyDescriptor(HTMLImageElement.prototype, "src");
  Object.defineProperty(HTMLImageElement.prototype, "src", { configurable: true, enumerable: d.enumerable,
    get: function () { return d.get.call(this); }, set: function (v) { d.set.call(this, map(v)); } });
  var sa = Element.prototype.setAttribute;
  Element.prototype.setAttribute = function (n, v) { if (this instanceof HTMLImageElement && String(n).toLowerCase() === "src") v = map(v); return sa.call(this, n, v); };
  document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll("img[data-asset]").forEach(function (img) { img.src = img.getAttribute("data-asset"); });
  });
})();
</script>"""


IMAGES = {}


def read(path):
    with open(os.path.join(PROTO, path), encoding="utf-8") as f:
        return f.read()


def safe_inline(js):
    return js.replace("</script", "<\\/script")


def used_images():
    text = "".join(read(p) for p in TEXT_SOURCES)
    keep, dropped = [], []
    files = [p for ext in ("png", "webp") for p in glob.glob(os.path.join(PROTO, "assets", "**", "*." + ext), recursive=True)]
    for p in sorted(files):
        rel = os.path.relpath(p, PROTO).replace(os.sep, "/")
        base = os.path.basename(rel)
        # Character art is only ever named literally; everything else may be built from ids at runtime.
        if rel.startswith("assets/characters/") and rel not in text and base not in text:
            dropped.append(rel)
        else:
            keep.append(rel)
    return keep, dropped


def encode_images(paths, optimize):
    oxi = None
    if optimize:
        try:
            import oxipng as oxi  # pip install pyoxipng (lossless)
        except ImportError:
            print("pyoxipng not installed; images are embedded unoptimised", file=sys.stderr)
    out = {}
    for rel in paths:
        with open(os.path.join(PROTO, rel), "rb") as f:
            data = f.read()
        if oxi and rel.endswith(".png"):
            try:
                smaller = oxi.optimize_from_memory(data, level=2)
                if len(smaller) < len(data):
                    data = smaller
            except Exception:
                pass
        mime = "image/webp" if rel.endswith(".webp") else "image/png"
        out[rel] = "data:%s;base64,%s" % (mime, base64.b64encode(data).decode("ascii"))
    return out


def make_packs(images):
    packs, cur, size = [], {}, 0
    for k, v in images.items():
        if cur and size + len(v) > PACK_LIMIT:
            packs.append(cur); cur, size = {}, 0
        cur[k] = v; size += len(v) + len(k) + 8
    if cur:
        packs.append(cur)
    return ["Object.assign(window.__VQSV_ASSETS = window.__VQSV_ASSETS || {}, %s);\n" % json.dumps(p, separators=(",", ":")) for p in packs]


def bundle_skill_fx(esbuild):
    cmd = (esbuild.split() if esbuild else ["npx", "--yes", "esbuild@0.24.0"]) + [
        os.path.join(PROTO, "skill_fx.js"), "--bundle", "--format=iife", "--minify", "--log-level=warning"]
    js = subprocess.run(cmd, check=True, capture_output=True, text=True).stdout
    return "document.addEventListener(\"DOMContentLoaded\", function () {\n" + js + "\n});"


def build_parts(esbuild):
    html = read("index.html")
    head = re.search(r"<head>(.*?)</head>", html, re.S).group(1)
    body = re.search(r"<body[^>]*>(.*)</body>", html, re.S).group(1)
    inline_css = lambda m: "<style>\n" + re.sub(r'url\("(assets/[^"]+)"\)', lambda u: 'url("%s")' % IMAGES.get(u.group(1), u.group(1)), read(m.group(1))) + "\n</style>"
    head = re.sub(r'<link rel="stylesheet" href="([^"]+)">', inline_css, head)

    def inline_script(m):
        attrs, src = m.group(1), m.group(2)
        if 'type="module"' in attrs:
            return "<script>\n" + safe_inline(bundle_skill_fx(esbuild)) + "\n</script>"
        return "<script>\n" + safe_inline(read(src)) + "\n</script>"
    body = re.sub(r'<script([^>]*?)src="([^"]+)"[^>]*></script>', inline_script, body)
    body = re.sub(r'<img([^>]*?) src="(assets/[^"]+)"', r'<img\1 data-asset="\2"', body)
    return head, body


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--out", default=os.path.join(ROOT, "dist_mobile"))
    ap.add_argument("--esbuild", default=os.environ.get("ESBUILD"))
    ap.add_argument("--no-optimize", action="store_true")
    a = ap.parse_args()

    keep, dropped = used_images()
    images = encode_images(keep, not a.no_optimize)
    packs = make_packs(images)
    IMAGES.update(images)
    head, body = build_parts(a.esbuild)

    web = os.path.join(a.out, "web")
    shutil.rmtree(a.out, ignore_errors=True)
    os.makedirs(web)
    pack_names = []
    for i, js in enumerate(packs, 1):
        name = "assets_pack_%d.js" % i
        pack_names.append(name)
        with open(os.path.join(web, name), "w", encoding="utf-8") as f:
            f.write(js)

    # Hosted page: the host wraps it in <!doctype><html><head>…<body>, so it carries no wrappers.
    head_web = re.sub(r"<title>.*?</title>", "<title>VQSV Unified</title>", head)
    head_web = re.sub(r'\s*<meta (charset|name="viewport")[^>]*>', "", head_web)
    page = (head_web.strip() + "\n<style>html, body { background: #11151b; color: #f1f4f8; }</style>\n" + HOOK + "\n"
            + "".join('<script src="%s"></script>\n' % n for n in pack_names) + body.strip() + "\n")
    with open(os.path.join(web, "index.html"), "w", encoding="utf-8") as f:
        f.write(page)

    single = ("<!doctype html>\n<html lang=\"vi\">\n<head>\n" + head.strip() + "\n" + HOOK + "\n"
              + "".join("<script>\n" + js + "</script>\n" for js in packs)
              + "</head>\n<body>\n" + body.strip() + "\n</body>\n</html>\n")
    with open(os.path.join(a.out, "vqsv_mobile.html"), "w", encoding="utf-8") as f:
        f.write(single)

    mb = lambda n: "%.1f MB" % (n / 1e6)
    print("images:", len(keep), "embedded,", len(dropped), "unused dropped", dropped)
    print("web/index.html:", mb(len(page.encode())), "| packs:", ", ".join(mb(len(p.encode())) for p in packs))
    print("vqsv_mobile.html:", mb(len(single.encode())))


if __name__ == "__main__":
    main()
