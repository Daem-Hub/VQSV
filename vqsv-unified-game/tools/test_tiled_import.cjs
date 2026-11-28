// Công cụ đọc map Tiled (tools/import_tiled.py, tools/tiled_vqsv5.py) và map mẫu tiled/maps/rung_tay.tmj trong game.
// 1. Bộ map thử .tmx/.tsx/.tmj: lớp nhóm, lớp ảnh, lớp "Trên", lớp va chạm ẩn, gạch collides/terrain, mã hóa CSV/base64/zlib,
//    lối đi tự ghép, NPC, rương, pet hoang dã, chỗ nhiệm vụ, vùng chặn, đồ trang trí, các lỗi thường gặp, nối map vào thế giới,
//    mẫu đối tượng .tx/.tj, ô tự chặn (chan) xoay/lật theo gạch, autoBlock = false, NPC đặt bằng hình, căn lề đối tượng gạch.
// 2. Map VQSV5 xuất sang Tiled rồi đọc lại cho đúng ảnh và va chạm của map đi lại đang dùng.
// 3. Map mẫu trong game: đi từ làng sang, suối chặn, chỗ lội, NPC, rương, pet hoang dã, quay về, lưu/tải, ghim chỗ nhiệm vụ.
// 4. Nếu máy có cài Tiled (apt-get install tiled): Tiled lưu map mẫu ra .tmx vẫn đọc ra như cũ, và tmxrasterizer của Tiled
//    vẽ map VQSV5 giống hệt ảnh công cụ tạo ra.
const fs = require('fs'), os = require('os'), path = require('path'), vm = require('vm'), zlib = require('zlib'), assert = require('assert');
const { execFileSync } = require('child_process');
const game = path.join(__dirname, '..'), root = path.join(game, 'prototype');
const tmp = fs.mkdtempSync(path.join(os.tmpdir(), 'tiled-test-'));
const py = code => JSON.parse(execFileSync('python3', ['-c', `import sys, json\nsys.path.insert(0, ${JSON.stringify(path.join(game, 'tools'))})\n${code}`], { cwd: game, encoding: 'utf8' }).trim().split('\n').at(-1));

// ---------------------------------------------------------------- 1. fixture maps
fs.copyFileSync(path.join(game, 'tiled/tilesets/va_cham.png'), path.join(tmp, 'sheet.png'));   // 5 tiles of 20 px: red, blue, cyan, orange, green
const FLIP_H = 0x80000000;
const b64 = (gids, zip) => { const buf = Buffer.alloc(gids.length * 4); gids.forEach((g, i) => buf.writeUInt32LE(g >>> 0, i * 4)); return (zip ? zlib.deflateSync(buf) : buf).toString('base64'); };
const grid = (w, h, cells) => { const g = Array(w * h).fill(0); for (const [x, y, gid] of cells) g[y * w + x] = gid; return g; };
fs.writeFileSync(path.join(tmp, 'set.tsx'), `<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.10" tiledversion="1.11.0" name="fx" tilewidth="20" tileheight="20" tilecount="5" columns="5">
 <image source="sheet.png" width="100" height="20"/>
 <tile id="1"><properties><property name="terrain" type="int" value="2"/></properties></tile>
 <tile id="3"><properties><property name="collides" type="bool" value="true"/></properties></tile>
 <tile id="4"><properties><property name="terrain" value="walk"/></properties></tile>
</tileset>`);
fs.writeFileSync(path.join(tmp, 'a.tmx'), `<?xml version="1.0" encoding="UTF-8"?>
<map version="1.10" orientation="orthogonal" renderorder="right-down" width="6" height="5" tilewidth="20" tileheight="20" infinite="0" backgroundcolor="#ff102030">
 <properties><property name="id" value="fx-a"/><property name="title" value="Thử A"/><property name="wildAreas" value="1-1"/></properties>
 <tileset firstgid="1" source="set.tsx"/>
 <group name="Nhóm">
  <layer name="Nền" width="6" height="5"><data encoding="csv">
2,2,2,2,2,2,
0,${(3 | FLIP_H) >>> 0},0,0,0,0,
0,0,0,0,0,0,
0,0,0,0,0,0,
0,0,0,0,0,0
</data></layer>
 </group>
 <layer name="Mái" width="6" height="5"><properties><property name="above" type="bool" value="true"/></properties>
  <data encoding="base64" compression="zlib">${b64(grid(6, 5, [[2, 2, 4]]), true)}</data></layer>
 <layer name="Va chạm" width="6" height="5" visible="0"><data encoding="base64">${b64(grid(6, 5, [[0, 0, 5], [5, 4, 1]]))}</data></layer>
 <objectgroup name="Đối tượng">
  <object id="1" name="Kael" type="player" x="30" y="70"><point/></object>
  <object id="2" type="link" x="0" y="60" width="20" height="40"><properties><property name="target" value="fx-b"/></properties></object>
  <object id="3" name="Bà Lão" class="NPC" x="90" y="70"><point/><properties><property name="art" value="elder"/><property name="dialogue">Chào cháu.
Đi cẩn thận nhé.</property></properties></object>
 </objectgroup>
</map>`);
const prop = (name, type, value) => ({ name, type, value });
const fixtureB = { type: 'map', orientation: 'orthogonal', renderorder: 'right-down', width: 4, height: 4, tilewidth: 20, tileheight: 20, infinite: false,
  properties: [prop('id', 'string', 'fx-b'), prop('title', 'string', 'Thử B'), prop('vqsv5Maps', 'string', '17'), prop('battleTerrain', 'int', 3)],
  tilesets: [{ firstgid: 1, name: 'fx', tilewidth: 20, tileheight: 20, tilecount: 5, columns: 5, image: 'sheet.png', imagewidth: 100, imageheight: 20, tiles: [] }],
  layers: [
    { id: 1, name: 'Đất', type: 'tilelayer', width: 4, height: 4, x: 0, y: 0, opacity: 1, visible: true, data: Array(16).fill(3) },
    { id: 2, name: 'Ảnh', type: 'imagelayer', image: 'sheet.png', offsetx: 0, offsety: 60, opacity: 1, visible: true },
    { id: 3, name: 'Đối tượng', type: 'objectgroup', opacity: 1, visible: true, objects: [
      { id: 1, name: '', type: 'Lối đi', x: 10, y: 30, width: 0, height: 0, point: true, properties: [prop('target', 'string', 'fx-a')] },
      { id: 2, name: '', type: 'hoang da', x: 0, y: 0, width: 80, height: 80, properties: [prop('pets', 'string', 'vqsv6-db-33, vqsv6-db-86'), prop('levels', 'string', '3-5')] },
      { id: 3, name: 'MQ001:0', type: 'nhiệm vụ', x: 50, y: 50, width: 0, height: 0, point: true },
      { id: 4, name: '', type: 'Rương', x: 70, y: 10, width: 0, height: 0, point: true, properties: [prop('gold', 'int', 50)] },
      { id: 5, name: '', type: 'chan', x: 40, y: 0, width: 20, height: 40 },
      { id: 6, name: '', type: '', gid: 1, x: 0, y: 20, width: 20, height: 20 }] }] };
fs.writeFileSync(path.join(tmp, 'b.tmj'), JSON.stringify(fixtureB));
fs.writeFileSync(path.join(tmp, 'c.tmj'), JSON.stringify({ ...fixtureB, properties: [prop('id', 'string', 'fx-c')], layers: [fixtureB.layers[0],
  { id: 3, name: 'Đối tượng', type: 'objectgroup', objects: [{ id: 1, name: '', type: 'Cửa', x: 50, y: 30, point: true, properties: [prop('target', 'string', 'town'), prop('backX', 'int', 4), prop('backY', 'int', 4)] }] }] }));
fs.writeFileSync(path.join(tmp, 'inf.tmj'), JSON.stringify({ ...fixtureB, infinite: true }));
fs.writeFileSync(path.join(tmp, 'flat.tmj'), JSON.stringify({ ...fixtureB, tileheight: 16 }));
// pieces that block cells by themselves (chan), turned by the tile flips; NPC pictures; object templates
fs.copyFileSync(path.join(game, 'tiled/tilesets/vqsv5_bo04/008.png'), path.join(tmp, 'tall.png'));      // a 20 × 60 VQSV5 piece
const villagerPic = path.relative(tmp, path.join(root, 'assets/illustrations/villager_hd.png')).split(path.sep).join('/');
fs.writeFileSync(path.join(tmp, 'pieces.tsj'), JSON.stringify({ type: 'tileset', name: 'pieces', tilewidth: 20, tileheight: 60, tilecount: 1, columns: 0,
  tiles: [{ id: 0, image: 'tall.png', imagewidth: 20, imageheight: 60, properties: [prop('chan', 'string', '0,0 0,1')] }] }));
fs.writeFileSync(path.join(tmp, 'people.tsj'), JSON.stringify({ type: 'tileset', name: 'people', tilewidth: 72, tileheight: 152, tilecount: 1, columns: 0,
  tiles: [{ id: 0, image: villagerPic, imagewidth: 72, imageheight: 152 }] }));
fs.writeFileSync(path.join(tmp, 'people_b.tsj'), JSON.stringify({ type: 'tileset', name: 'people_b', tilewidth: 72, tileheight: 152, tilecount: 1, columns: 0,
  objectalignment: 'bottom', tiles: [{ id: 0, image: villagerPic, imagewidth: 72, imageheight: 152 }] }));
fs.mkdirSync(path.join(tmp, 'tpl'));
fs.writeFileSync(path.join(tmp, 'tpl/npc.tx'), `<?xml version="1.0" encoding="UTF-8"?>
<template>
 <tileset firstgid="1" source="../people.tsj"/>
 <object name="Dân làng" type="npc" gid="1" width="18" height="38"><properties><property name="dialogue" value="Chào!"/></properties></object>
</template>`);
fs.writeFileSync(path.join(tmp, 'tpl/box.tj'), JSON.stringify({ type: 'template', object: { type: 'chest', name: 'Rương', width: 20, height: 20, properties: [prop('gold', 'int', 70)] } }));
const FLIP_V = 0x40000000, FLIP_D = 0x20000000;
const fixtureD = { type: 'map', orientation: 'orthogonal', renderorder: 'right-down', width: 6, height: 6, tilewidth: 20, tileheight: 20, infinite: false,
  properties: [prop('id', 'string', 'fx-d')], tilesets: [{ firstgid: 1, source: 'pieces.tsj' }, { firstgid: 2, source: 'people_b.tsj' }],
  layers: [
    { id: 1, name: 'Cây', type: 'tilelayer', width: 6, height: 6, opacity: 1, visible: true, data: grid(6, 6, [[1, 3, 1], [3, 3, (1 | FLIP_V) >>> 0], [0, 5, (1 | FLIP_D) >>> 0]]) },
    { id: 2, name: 'Đối tượng', type: 'objectgroup', objects: [
      { id: 1, template: 'tpl/npc.tx', x: 60, y: 40 },
      { id: 2, template: 'tpl/box.tj', x: 80, y: 80, properties: [prop('gold', 'int', 5)] },
      { id: 3, name: 'Bà Tư', type: 'npc', gid: 2, x: 50, y: 60, width: 18, height: 38, properties: [prop('dialogue', 'string', 'Ừ.')] }] }] };
fs.writeFileSync(path.join(tmp, 'd.tmj'), JSON.stringify(fixtureD));
fs.writeFileSync(path.join(tmp, 'e.tmj'), JSON.stringify({ ...fixtureD, properties: [prop('id', 'string', 'fx-e'), prop('autoBlock', 'bool', false)], layers: [fixtureD.layers[0]] }));

const out = path.join(tmp, 'out');
const r = py(`
from pathlib import Path
from PIL import Image
from import_tiled import import_maps, attach_tiled_maps, TiledError
T = Path(${JSON.stringify(tmp)}); O = T / "out"
maps, warns = import_maps([T / "a.tmx", T / "b.tmj", T / "c.tmj", T / "d.tmj", T / "e.tmj"], O)
px = lambda name, x, y: list(Image.open(O / "maps" / name).convert("RGBA").getpixel((x, y)))
res = {"maps": {m["id"]: m for m in maps}, "warns": warns,
       "a_bg": px("fx-a.png", 70, 70), "a_row0": px("fx-a.png", 110, 10), "a_hidden": px("fx-a.png", 110, 90), "a_roof_base": px("fx-a.png", 50, 50),
       "a_roof_over": px("fx-a_over.png", 50, 50), "b_tile": px("fx-b.png", 30, 50), "b_image": px("fx-b.png", 10, 70), "b_deco": px("fx-b.png", 10, 10),
       "b_over": (O / "maps" / "fx-b_over.png").exists(), "d_turned": px("fx-d.png", 50, 110), "d_bg": px("fx-d.png", 110, 110)}
errors = {}
for name in ("inf.tmj", "flat.tmj"):
    try: import_maps([T / name], check=True)
    except TiledError as e: errors[name] = str(e)
world = {"town": {"id": "town", "title": "Phố", "width": 8, "height": 8, "links": []}}
res["attached"] = attach_tiled_maps(world, O, log=lambda *a: None)
res["attached_maps"] = {k: world[k] for k in res["attached"]}
res["town"] = world["town"]; res["world_c"] = world["fx-c"]; res["world_a"] = world["fx-a"]
for label, w in (("dup", {"town": world["town"], "fx-a": {"id": "fx-a"}}), ("missing", {})):
    try: attach_tiled_maps(w, O, log=lambda *a: None)
    except TiledError as e: errors[label] = str(e)
res["errors"] = errors
print(json.dumps(res, ensure_ascii=False))
`);
const A = r.maps['fx-a'], B = r.maps['fx-b'], C = r.maps['fx-c'];
// collision: blue tiles (terrain 2) in a drawn layer; the hidden collision layer reopens (0,0) with a "walk" tile and blocks (5,4);
// a "collides" tile in the roof layer blocks (2,2); a flipped tile without properties stays open
assert.deepEqual(A.collision, ['022222', '000000', '001000', '000000', '000001']);
assert.deepEqual(A.entities.player, { x: 1, y: 3 });
assert.deepEqual(A.links.map(l => [l.x, l.y, l.target]), [[0, 3, 'fx-b'], [0, 4, 'fx-b']]);        // a rectangle = one exit per cell
assert.deepEqual([A.links[0].spawnX, A.links[0].spawnY], [1, 1]);                                    // paired with fx-b's exit: arrive next to it
assert.deepEqual([B.links[0].spawnX, B.links[0].spawnY], [1, 4]);
assert.deepEqual(A.entities.villagers[0], { id: 'fx-a-npc-3', name: 'Bà Lão', x: 4, y: 3, asset: 'illustrations/elder_hd.png', dialogue: ['Chào cháu.', 'Đi cẩn thận nhé.'] });
assert.deepEqual(A.wildAreas, ['1-1']);
assert.equal(A.tiled.background, '#102030');
assert.deepEqual(r.a_bg, [16, 32, 48, 255]);                       // #AARRGGBB background without its alpha
assert(r.a_row0[2] > r.a_row0[0], 'row 0 painted with the blue tile');
assert.deepEqual(r.a_hidden, r.a_bg, 'a hidden collision layer is not drawn');
assert.deepEqual(r.a_roof_base, r.a_bg, 'an "above" layer is not in the ground picture');
assert(r.a_roof_over[3] > 0 && A.tiled.overlay === 'assets/maps/tiled/fx-a_over.png', 'an "above" layer goes to the overlay');
assert.deepEqual(B.collision, ['0010', '0010', '0000', '0000']);    // "chan" rectangle; the chest blocks in the game, not in the grid
assert.deepEqual(B.questSpots, { 'MQ001:0': { x: 2, y: 2 } });
assert.deepEqual(B.chests, [{ id: 'fx-b:tiled-4', x: 3, y: 0, reward: { kind: 'gold', kim_tien: 50 } }]);
assert.deepEqual(B.customWildAreas[0].level_range, [3, 5]);
assert.deepEqual(B.customWildAreas[0].pets.map(p => p.pet_id), ['vqsv6-db-33', 'vqsv6-db-86']);
assert.deepEqual(B.terrain5, { source_maps: [17], types: [3] });
assert.notDeepEqual(r.b_image, r.b_tile, 'image layer drawn on row 3');
assert(r.b_deco[0] > r.b_deco[2], 'tile object without a class drawn as decoration (red tile)');
assert.equal(r.b_over, false, 'no overlay file when nothing is drawn above Kael');
assert.match(r.errors['inf.tmj'], /Infinite/);
assert.match(r.errors['flat.tmj'], /vuông/);
// joining the world: a door with backX/backY adds the way out in the target map, both arrival cells are worked out
assert.deepEqual(r.attached, ['fx-a', 'fx-b', 'fx-c', 'fx-d', 'fx-e']);
assert.deepEqual(r.town.links, [{ x: 4, y: 4, target: 'fx-c', spawnX: 2, spawnY: 2, label: 'fx-c', kind: 'exit' }]);
assert.deepEqual(r.world_c.links, [{ x: 2, y: 1, target: 'town', spawnX: 4, spawnY: 5, label: 'Phố', kind: 'door' }]);
assert.deepEqual(r.town.arrivals, { 'fx-c': [4, 5] });
assert.equal(r.world_a.links[0].label, 'Thử B');
assert.match(r.errors.dup, /trùng id/);
assert.match(r.errors.missing, /không có/);
// chan: a 20 × 60 piece blocking its two lower cells; flipped vertically it blocks the two upper ones, turned (diagonal flip) it lies
// along the row and blocks its 2nd and 3rd cells. With autoBlock = false nothing is blocked.
const D = r.maps['fx-d'];
assert.deepEqual(D.collision, ['000000', '000100', '010100', '010000', '000000', '011000']);
assert.deepEqual(r.maps['fx-e'].collision, ['000000', '000000', '000000', '000000', '000000', '000000']);
assert.notDeepEqual(r.d_turned, r.d_bg, 'the turned piece is drawn along row 5');
// a template from another folder with its own tileset; a JSON template with a changed property; an NPC placed as its picture
// (its picture is the NPC art) and a tileset that aligns objects on their bottom middle
assert.deepEqual(D.entities.villagers, [
  { id: 'fx-d-npc-1', name: 'Dân làng', x: 3, y: 1, asset: 'illustrations/villager_hd.png', dialogue: ['Chào!'] },
  { id: 'fx-d-npc-3', name: 'Bà Tư', x: 2, y: 2, asset: 'illustrations/villager_hd.png', dialogue: ['Ừ.'] }]);
assert.deepEqual(D.chests, [{ id: 'fx-d:tiled-2', x: 4, y: 4, reward: { kind: 'gold', kim_tien: 5 } }]);
console.log('Tiled import: layers, encodings, collision, self-blocking pieces, templates, objects, pictures, errors and joining the world passed.');

// ---------------------------------------------------------------- 2. VQSV5 maps through Tiled and back
const rt = py(`
from pathlib import Path
from PIL import Image, ImageChops
from tiled_vqsv5 import export_map
from import_tiled import import_maps
T = Path(${JSON.stringify(tmp)}) / "rt"
res = {}
for n, name in ((18, "forest_stream_map.json"), (24, "wild_skyridge.json")):   # 18 has quarter-turned pieces, 24 an overlay layer
    src = export_map(n, T / f"v{n}.tmj", str(Path("tiled/tilesets").resolve()))
    m = import_maps([src], T / "out")[0][0]
    old = json.loads(Path("prototype/data", name).read_text(encoding="utf-8"))
    same = lambda a, b: ImageChops.difference(Image.open(a).convert("RGBA"), Image.open(b).convert("RGBA")).getbbox() is None
    res[n] = {"base": same(T / "out" / "maps" / f"{m['id']}.png", Path("prototype") / old["source5"]["base"]),
              "overlay": same(T / "out" / "maps" / f"{m['id']}_over.png", Path("prototype") / old["source5"]["overlay"]) if old["source5"].get("overlay") else None,
              "collision": m["collision"] == old["collision"]}
print(json.dumps(res))
`);
assert.deepEqual(rt, { 18: { base: true, overlay: null, collision: true }, 24: { base: true, overlay: true, collision: true } });
console.log('VQSV5 maps exported to Tiled and read back give the same pictures and collision.');

// ---------------------------------------------------------------- 3. the sample map in the game
const html = fs.readFileSync(path.join(root, 'index.html'), 'utf8');
const code = [...html.matchAll(/<script>([\s\S]*?)<\/script>/g)].at(-1)[1].replace(/    loadData\(\)\.catch\(err => \{[^\n]+\}\);/, '');
class Element {
  constructor() { this.children = []; this.listeners = {}; this.dataset = {}; this.style = { setProperty() {}, removeProperty() {} }; this.textContent = ''; this.hidden = false; this.value = 'all'; }
  append(...n) { this.children.push(...n); } replaceChildren(...n) { this.children = [...n]; }
  setAttribute() {} getAttribute() {} removeAttribute() {} toggleAttribute() {} addEventListener(e, f) { (this.listeners[e] ||= []).push(f); }
  showModal() { this.open = true; } close() { this.open = false; } focus() {} querySelector() { return null; } querySelectorAll() { return []; }
  getContext() { return new Proxy({}, { get: () => () => ({ addColorStop() {} }) }); }
}
const els = {}, element = id => els[id] ||= new Element();
const store = new Map(), localStorage = { getItem: k => store.get(k) ?? null, setItem: (k, v) => store.set(k, String(v)) };
const boot = { window: {} }; vm.runInNewContext(fs.readFileSync(path.join(root, 'data/bootstrap.js'), 'utf8'), boot);
const window = { addEventListener() {}, VQSVBootstrap: boot.window.VQSVBootstrap, matchMedia: () => ({ matches: false }) };
const ctx = vm.createContext({ window, document: { getElementById: element, createElement: () => new Element(), querySelectorAll: () => [], querySelector: () => null, addEventListener() {}, body: { dataset: {} } },
  localStorage, Element, Image: class {}, performance: { now: () => 1000 }, setTimeout: () => 1, clearTimeout() {}, console });
vm.runInContext(fs.readFileSync(path.join(root, 'pet_book.js'), 'utf8'), ctx);
vm.runInContext(code, ctx);
vm.runInContext(fs.readFileSync(path.join(root, 'quests.js'), 'utf8'), ctx);
const run = js => vm.runInContext(js, ctx);
const maps = ctx.maps = boot.window.VQSVBootstrap.maps;
const ID = 'tiled-west-forest', forest = maps[ID];
assert(forest?.tiled, 'the sample Tiled map is in bootstrap.js');
assert.equal(forest.tiled.source, 'tiled/maps/rung_tay.tmj');
assert(fs.existsSync(path.join(root, forest.tiled.base)), 'its picture is in prototype/assets');
const gate = maps['slice01-border-village'].links.find(l => l.target === ID);
assert.deepEqual([gate.x, gate.y], [0, 10]);                         // backX/backY of the map's exit
run('assets.maps = maps; assets.map = maps["slice01-border-village"]; reset(); state.spokeToElder = true');
const at = (x, y) => run(`state.mode = "explore"; Object.assign(state.player, { x: ${x}, y: ${y}, visualX: ${x}, visualY: ${y}, step: null })`);
// walking off the west edge of the village
at(0, 10); run('state.player.inputDir = { dx: -1, dy: 0 }; startStep(1000)');
assert.equal(run('assets.map.id'), ID);
assert.deepEqual([run('state.player.x'), run('state.player.y')], [gate.spawnX, gate.spawnY]);
assert.equal(run('blocked(state.player.x, state.player.y)'), false);
assert.match(run('statusEl.textContent'), /Rừng Rậm Phía Tây · pet hoang dã cấp/);
assert.doesNotMatch(run('statusEl.textContent'), /huy hiệu/);         // Tiled maps give the region badge only with badge = true
// the stream painted as deep water in the collision layer, with two shallow fords
assert.equal(run('blocked(12, 8)'), true);
assert.equal(run('blocked(4, 14)'), true);
assert.equal(run('blocked(12, 11)'), false);
assert.equal(run('blocked(6, 14)'), false);
assert.equal(run('terrainAt(12, 11)'), 2);                           // wading: water pets
assert.equal(run('terrainAt(20, 18)'), 0);
assert(run(`wildAreasOf("${ID}").length`) > 0);
// NPC from the object layer
at(16, 13); run('state.player.faceX = 0; state.player.faceY = -1; interact()');
assert.equal(run('state.activeNpc.name'), 'Bác Tiều Phu');
for (let i = 0; i < 4 && run('state.mode') === 'dialogue'; i++) run('interact()');
assert.equal(run('state.mode'), 'explore');
// chest from the object layer
at(2, 16);
assert.equal(run('nearbyChest()?.id'), `${ID}:tiled-5`);
assert.equal(run('openMapChest(nearbyChest()).ok'), true);
// save and load on the Tiled map, then back to the village through the exit
at(20, 18); run('saveGame(); reset(); loadGame()');
assert.equal(run('assets.map.id'), ID);
assert.deepEqual([run('state.player.x'), run('state.player.y')], [20, 18]);
run('state.spokeToElder = true'); at(23, 12); run('interact()');
assert.equal(run('assets.map.id'), 'slice01-border-village');
assert.equal(run('blocked(state.player.x, state.player.y)'), false);
// a "quest" object pins a quest spot: place() starts from the pin instead of the map fraction
forest.questSpots = { 'TEST:0': { x: 19, y: 17 } };
assert.deepEqual(run(`JSON.parse(JSON.stringify(window.Quests.kit.place({ map: "${ID}", frac: [.1, .1] }, "${ID}:TEST:0")))`), { x: 19, y: 17 });
assert.notDeepEqual(run(`JSON.parse(JSON.stringify(window.Quests.kit.place({ map: "${ID}", frac: [.1, .1] }, "${ID}:TEST:1")))`), { x: 19, y: 17 });
delete forest.questSpots;
console.log('Sample Tiled map: the way in from the village, stream and fords, NPC, chest, wild pets, save/load, way back and quest pin passed.');

// ---------------------------------------------------------------- 4. against Tiled itself, when it is installed
const has = cmd => { try { execFileSync('sh', ['-c', `command -v ${cmd}`], { stdio: 'ignore' }); return true; } catch (_) { return false; } };
if (has('tiled') && has('tmxrasterizer')) {
  const real = path.join(tmp, 'real'), env = { ...process.env, QT_QPA_PLATFORM: 'offscreen', XDG_RUNTIME_DIR: path.join(tmp, 'xdg') };
  fs.mkdirSync(env.XDG_RUNTIME_DIR, { mode: 0o700 });
  fs.cpSync(path.join(game, 'tiled'), path.join(real, 'tiled'), { recursive: true });
  const tiled = (...args) => execFileSync('tiled', args, { env, stdio: 'ignore' });
  tiled('--export-map', 'tmx', path.join(real, 'tiled/maps/rung_tay.tmj'), path.join(real, 'tiled/maps/rung_tay.tmx'));
  for (const n of ['18', '24']) execFileSync('tmxrasterizer', ['--no-smoothing', '--hide-layer', 'Va chạm', '--hide-layer', 'Đối tượng',
    path.join(real, `tiled/vqsv5/vqsv5_${n}.tmj`), path.join(real, `r${n}.png`)], { env, stdio: 'ignore' });
  const same = py(`
from pathlib import Path
from PIL import Image, ImageChops
from import_tiled import import_maps
R = Path(${JSON.stringify(real)})
a = import_maps([R / "tiled/maps/rung_tay.tmj"], R / "a")[0][0]
b = import_maps([R / "tiled/maps/rung_tay.tmx"], R / "b")[0][0]
a["tiled"].pop("source"); b["tiled"].pop("source")
res = {"tmx": a == b and not ImageChops.difference(Image.open(R / "a/maps" / f"{a['id']}.png"), Image.open(R / "b/maps" / f"{b['id']}.png")).getbbox()}
for n in ("18", "24"):
    m = import_maps([R / f"tiled/vqsv5/vqsv5_{n}.tmj"], R / f"v{n}")[0][0]
    want = Image.open(R / f"v{n}/maps/{m['id']}.png").convert("RGBA")
    over = R / f"v{n}/maps/{m['id']}_over.png"
    if over.exists(): want.alpha_composite(Image.open(over).convert("RGBA"))
    got = Image.new("RGBA", want.size, m["tiled"]["background"]); got.alpha_composite(Image.open(R / f"r{n}.png").convert("RGBA"))
    res[n] = not ImageChops.difference(got.convert("RGB"), want.convert("RGB")).getbbox()
print(json.dumps(res))
`);
  assert.deepEqual(same, { tmx: true, 18: true, 24: true });
  console.log('Tiled itself: the sample map saved as .tmx reads the same, and tmxrasterizer draws VQSV5 maps exactly like the importer.');
} else console.log('Tiled is not installed: skipped the checks against Tiled itself (apt-get install tiled).');
fs.rmSync(tmp, { recursive: true, force: true });
