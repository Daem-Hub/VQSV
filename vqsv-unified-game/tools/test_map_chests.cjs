// Treasure chests on the maps: decoded VQSV5 chests (data/4), designed placement on the unified maps and opening in the game.
const fs = require('fs'), path = require('path'), vm = require('vm'), assert = require('assert');
const html = fs.readFileSync(path.join(__dirname, '..', 'prototype', 'index.html'), 'utf8');
const code = [...html.matchAll(/<script>([\s\S]*?)<\/script>/g)].at(-1)[1].replace(/    loadData\(\)\.catch\(err => \{[^\n]+\}\);/, '');
const boot = require('./bootstrap_for_tests.cjs');
const source = require('../prototype/data/vqsv5_map_chests.json');

// ---- Decoded data ----
assert.equal(source.maps.length, 68);
const all = source.maps.flatMap(m => m.chests.map(c => ({ ...c, map: m.map })));
assert.deepEqual([source.summary.chests, source.summary.maps_with_chests, source.summary.gold_chests, source.summary.medal_chests, source.summary.item_chests], [143, 48, 28, 1, 114]);
assert.equal(all.length, 143); assert.equal(all.reduce((n, c) => n + (c.kim_tien || 0), 0), 15200);
const first = source.maps[0].chests[0]; assert.deepEqual([first.x, first.y, first.kind, first.item_id, first.quantity], [2, 16, 'item', 1, 2]);
assert.equal(source.maps[1].chests.find(c => c.kind === 'gold').kim_tien, 800);
assert.equal(source.maps[33].chests[0].name, 'Bảo rương'); assert.equal(source.maps[33].chests[0].quantity, 10);
assert(all.every(c => Number.isInteger(c.x) && Number.isInteger(c.y)));
// ---- Designed placement ----
const mapIds = ['ryan-phong-loi-forest', 'ryan-forest-stream', 'ryan-lakeside', 'ryan-meadow', 'ryan-highland', 'ryan-canyon', 'ryan-skyridge', 'ryan-ashfield'];
const realRewards = new Set(all.map(c => JSON.stringify({ kind: c.kind, item_id: c.item_id, kim_tien: c.kim_tien, medals: c.medals, quantity: c.quantity })));
let placed = 0;
for (const id of mapIds) {
  const m = boot.maps[id]; assert(m.chests.length >= 3 && m.chests.length <= 4, id); placed += m.chests.length;
  const solid = new Set(m.objects.flatMap(o => (o.solid || []).map(([x, y]) => `${x},${y}`)));
  const water = (x, y) => (m.water || []).some(w => x >= w.x && x < w.x + w.width && y >= w.y && y < w.y + w.height);
  const seen = new Set();
  for (const c of m.chests) {
    assert(c.x > 0 && c.x < m.width - 1 && c.y > 1 && c.y < m.height - 1, `${id} bounds`);
    assert(!solid.has(`${c.x},${c.y}`) && !water(c.x, c.y), `${id} free tile`); assert(c.y < 8 || c.y > 12, `${id} off the road`);
    assert(!seen.has(`${c.x},${c.y}`)); seen.add(`${c.x},${c.y}`);
    assert(realRewards.has(JSON.stringify({ kind: c.reward.kind, item_id: c.reward.item_id, kim_tien: c.reward.kim_tien, medals: c.reward.medals, quantity: c.reward.quantity })), `${id} real contents`);
    assert.equal(c.id.split(':')[0], id);
  }
  // Every chest has a reachable neighbour from the spawn point with the chests themselves as obstacles.
  const start = m.entities.player, blocked = new Set([...solid, ...seen]);
  const open = (x, y) => x >= 0 && y >= 0 && x < m.width && y < m.height && !blocked.has(`${x},${y}`) && !water(x, y);
  const reach = new Set([`${start.x},${start.y}`]); const queue = [[start.x, start.y]];
  while (queue.length) { const [x, y] = queue.shift(); for (const [dx, dy] of [[1, 0], [-1, 0], [0, 1], [0, -1]]) { const k = `${x + dx},${y + dy}`; if (open(x + dx, y + dy) && !reach.has(k)) { reach.add(k); queue.push([x + dx, y + dy]); } } }
  for (const c of m.chests) assert([[1, 0], [-1, 0], [0, 1], [0, -1]].some(([dx, dy]) => reach.has(`${c.x + dx},${c.y + dy}`)), `${id} reachable`);
}
assert.equal(placed, 31);
assert.equal(boot.maps['slice01-border-village'].chests, undefined);
// ---- In the game ----
const els = {};
const element = id => els[id] ||= { textContent: '', hidden: false, open: false, listeners: {}, addEventListener(event, fn) { this.listeners[event] = fn; }, getContext() { return {}; }, replaceChildren() {}, append() {}, setAttribute() {} };
const store = new Map(), localStorage = { getItem: k => store.get(k) ?? null, setItem: (k, v) => store.set(k, v) };
const VQSVPetTeam = { speciesCaught: () => 0, ids: () => [], replaceSpecies() {}, caughtBest: () => null, info: id => ({ id, kind: 'vqsv6', name: id, element: null, sequences: [], image: '' }), recordCatch: () => ({ addedToTeam: false }) };
const ctx = vm.createContext({ document: { getElementById: element, createElement: () => ({ append() {}, addEventListener() {}, setAttribute() {}, replaceChildren() {} }) }, window: { addEventListener() {}, VQSVBootstrap: boot, VQSVPetTeam }, Image: class {}, Element: class {}, performance: { now: () => 1000 }, console, localStorage });
vm.runInContext(code, ctx);
const run = js => vm.runInContext(js, ctx);
const J = js => { const text = JSON.stringify(run(js)); return text === undefined ? undefined : JSON.parse(text); };
run('assets.map = { width: 4, height: 4, objects: [], entities: { player: { x: 1, y: 1 }, elder: { x: 3, y: 3 }, encounter: { x: 2, y: 2 } } }; reset()');
// Every item that a source chest can hold is either a stored material/book or has a bag/kim tiền equivalent.
for (const id of source.summary.item_ids) assert(run(`ITEMS5.has(${id}) || !!CHEST5_GRANTS[${id}]`), `item ${id}`);
const enter = (mapId, extra = '') => run(`assets.maps = window.VQSVBootstrap.maps; assets.map = assets.maps["${mapId}"]; window.VQSVMapChests.reset(); wallet = { gold: 100, badges: 1, rewarded: {}, medalsClaimed: 0 }; for (const id of ITEMS5.keys()) mats5[id] = 0; state.mode = "explore"; ${extra}`);
enter('ryan-canyon');
const chests = J('window.VQSVMapChests.list()'); assert.equal(chests.length, 4);
// Solid: the player cannot walk onto a chest; the neighbour tile prompts for it.
const c0 = chests[0]; assert.equal(run(`blocked(${c0.x}, ${c0.y})`), true);
run(`state.player = { x: ${c0.x}, y: ${c0.y - 1}, visualX: ${c0.x}, visualY: ${c0.y - 1}, faceX: 0, faceY: 1, step: null, inputDir: null }`);
assert.equal(J('nearbyChest()').id, c0.id);
// Opening gives the content once, stays opened (and persists), a second try does nothing.
const gold = chests.find(c => c.reward.kind === 'gold');
run(`state.player = { x: ${gold.x}, y: ${gold.y - 1}, visualX: ${gold.x}, visualY: ${gold.y - 1}, faceX: 0, faceY: 1, step: null, inputDir: null }`);
let r = J(`window.VQSVMapChests.open(${JSON.stringify(gold.id)})`); assert.equal(r.ok, true); assert.match(r.message, /kim tiền/); assert.equal(run('wallet.gold'), 100 + gold.reward.kim_tien);
assert.equal(J(`window.VQSVMapChests.open(${JSON.stringify(gold.id)})`).ok, false); assert.equal(run('wallet.gold'), 100 + gold.reward.kim_tien);
assert.deepEqual(J('window.VQSVMapChests.opened()'), [gold.id]); assert.deepEqual(JSON.parse(store.get('vqsv-unified-chests-v1')), [gold.id]);
assert.equal(run('nearbyChest()'), undefined);
// Items: materials go to the VQSV5 store, consumables to the closest bag item, with the stack cap.
enter('ryan-forest-stream');
const mat = J('window.VQSVMapChests.list()').find(c => c.reward.item_id === 19); // Chu quả (a VQSV5 evolution material)
assert(mat); r = J(`window.VQSVMapChests.open(${JSON.stringify(mat.id)})`); assert.equal(r.ok, true); assert.match(r.message, /Chu quả ×1/); assert.equal(run('mats5[19]'), 1);
enter('ryan-forest-stream', 'mats5[19] = 99'); r = J(`window.VQSVMapChests.open(${JSON.stringify(mat.id)})`); assert.equal(r.ok, true); assert.match(r.message, /đã đầy/); assert.equal(run('mats5[19]'), 99);   // still opens
enter('ryan-phong-loi-forest');
const potion = J('window.VQSVMapChests.list()').find(c => c.reward.item_id === 6); const bag5 = run('bag[5] || 0');           // Hoàng kim đường -> Chocolate
if (potion) { J(`window.VQSVMapChests.open(${JSON.stringify(potion.id)})`); assert(run('bag[5] || 0') >= bag5 + 1); }
const cheese = J('window.VQSVMapChests.list()').find(c => c.reward.item_id === 13);                                                   // Mật tâm đường -> kim tiền
if (cheese) { run('wallet.gold = 0'); J(`window.VQSVMapChests.open(${JSON.stringify(cheese.id)})`); assert.equal(run('wallet.gold'), 500 * cheese.reward.quantity); }
// No opening in battle.
enter('ryan-canyon', 'state.mode = "battle"'); assert.equal(J(`window.VQSVMapChests.open(${JSON.stringify(c0.id)})`).ok, false);
// The village has none.
enter('slice01-border-village'); assert.equal(J('window.VQSVMapChests.list()').length, 0);
console.log('Map chests: decoded data, designed placement and opening passed.');
