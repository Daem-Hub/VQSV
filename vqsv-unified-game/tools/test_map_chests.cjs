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
// ---- Placement: each region stands on one real VQSV5 map (tools/build_vqsv5_walk_maps.py), so its chests are that map's own
// chests at their own cells (only the ones next to an exit or arrival cell are left out). ----
const mapIds = Object.keys(boot.maps).filter(id => boot.maps[id].source5 && boot.maps[id].atlasRegion == null);
assert.equal(mapIds.length, 12);
let placed = 0;
for (const id of mapIds) {
  const m = boot.maps[id], src = source.maps[m.source5.map].chests;
  placed += m.chests.length;
  for (const c of m.chests) {
    const same = src.find(s => s.x === c.x && s.y === c.y);
    assert(same, `${id} chest at a source cell`); assert.equal(c.reward.kind, same.kind); assert.equal(c.reward.quantity, same.quantity);
    assert.equal(m.collision[c.y][c.x], '0', `${id} chest on a walkable cell`);
    assert(m.links.every(l => Math.abs(l.x - c.x) + Math.abs(l.y - c.y) > 1), `${id} chest clear of exits`);
    assert.equal(c.id.split(':')[0], id);
  }
  // Every chest has a walkable neighbour reachable from the arrival cell.
  const start = m.entities.player, cells = new Set(m.chests.map(c => `${c.x},${c.y}`));
  const open = (x, y) => x >= 0 && y >= 0 && x < m.width && y < m.height && !['1', '2', '4'].includes(m.collision[y][x]) && !cells.has(`${x},${y}`);
  const reach = new Set([`${start.x},${start.y}`]); const queue = [[start.x, start.y]];
  while (queue.length) { const [x, y] = queue.shift(); for (const [dx, dy] of [[1, 0], [-1, 0], [0, 1], [0, -1]]) { const k = `${x + dx},${y + dy}`; if (open(x + dx, y + dy) && !reach.has(k)) { reach.add(k); queue.push([x + dx, y + dy]); } } }
  for (const c of m.chests) assert([[1, 0], [-1, 0], [0, 1], [0, -1]].some(([dx, dy]) => reach.has(`${c.x + dx},${c.y + dy}`)), `${id} reachable`);
}
assert(placed >= 25, `placed ${placed}`);
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
const goldMap = mapIds.find(id => boot.maps[id].chests.some(c => c.reward.kind === 'gold'));
enter(goldMap);
const chests = J('window.VQSVMapChests.list()'); assert(chests.length >= 1);
// Solid: the player cannot walk onto a chest; the neighbour tile prompts for it.
const c0 = chests[0]; assert.equal(run(`blocked(${c0.x}, ${c0.y})`), true);
{ const [nx, ny] = [[1, 0], [-1, 0], [0, 1], [0, -1]].map(([dx, dy]) => [c0.x + dx, c0.y + dy]).find(([x, y]) => !run(`blocked(${x}, ${y})`)); run(`state.player = { x: ${nx}, y: ${ny}, visualX: ${nx}, visualY: ${ny}, faceX: 0, faceY: 1, step: null, inputDir: null }`); }
assert.equal(J('nearbyChest()').id, c0.id);
// Opening gives the content once, stays opened (and persists), a second try does nothing.
const gold = chests.find(c => c.reward.kind === 'gold');
{ const [nx, ny] = [[1, 0], [-1, 0], [0, 1], [0, -1]].map(([dx, dy]) => [gold.x + dx, gold.y + dy]).find(([x, y]) => !run(`blocked(${x}, ${y})`)); run(`state.player = { x: ${nx}, y: ${ny}, visualX: ${nx}, visualY: ${ny}, faceX: 0, faceY: 1, step: null, inputDir: null }`); }
let r = J(`window.VQSVMapChests.open(${JSON.stringify(gold.id)})`); assert.equal(r.ok, true); assert.match(r.message, /kim tiền/); assert.equal(run('wallet.gold'), 100 + gold.reward.kim_tien);
assert.equal(J(`window.VQSVMapChests.open(${JSON.stringify(gold.id)})`).ok, false); assert.equal(run('wallet.gold'), 100 + gold.reward.kim_tien);
assert.deepEqual(J('window.VQSVMapChests.opened()'), [gold.id]); assert.deepEqual(JSON.parse(store.get('vqsv-unified-chests-v1')), [gold.id]);
assert.equal(run('nearbyChest()'), undefined);
// Items: materials go to the VQSV5 store, consumables to the closest bag item, with the stack cap.
const matMap = mapIds.find(id => boot.maps[id].chests.some(c => c.reward.kind === 'item' && c.reward.item_id >= 16 && c.reward.item_id <= 34));
assert(matMap, 'some region holds an evolution material');
enter(matMap);
const mat = J('window.VQSVMapChests.list()').find(c => c.reward.kind === 'item' && c.reward.item_id >= 16 && c.reward.item_id <= 34), mid = mat.reward.item_id;
r = J(`window.VQSVMapChests.open(${JSON.stringify(mat.id)})`); assert.equal(r.ok, true); assert.match(r.message, new RegExp(`${mat.reward.name} ×${mat.reward.quantity}`)); assert.equal(run(`mats5[${mid}]`), mat.reward.quantity);
enter(matMap, `mats5[${mid}] = 99`); r = J(`window.VQSVMapChests.open(${JSON.stringify(mat.id)})`); assert.equal(r.ok, true); assert.match(r.message, /đã đầy/); assert.equal(run(`mats5[${mid}]`), 99);   // still opens
enter('ryan-phong-loi-forest');
const potion = J('window.VQSVMapChests.list()').find(c => c.reward.item_id === 6); const bag5 = run('bag[5] || 0');           // Hoàng kim đường -> Chocolate
if (potion) { J(`window.VQSVMapChests.open(${JSON.stringify(potion.id)})`); assert(run('bag[5] || 0') >= bag5 + 1); }
const cheese = J('window.VQSVMapChests.list()').find(c => c.reward.item_id === 13);                                                   // Mật tâm đường -> kim tiền
if (cheese) { run('wallet.gold = 0'); J(`window.VQSVMapChests.open(${JSON.stringify(cheese.id)})`); assert.equal(run('wallet.gold'), 500 * cheese.reward.quantity); }
// No opening in battle.
enter(goldMap, 'state.mode = "battle"'); assert.equal(J(`window.VQSVMapChests.open(${JSON.stringify(c0.id)})`).ok, false);
// The village has none.
enter('slice01-border-village'); assert.equal(J('window.VQSVMapChests.list()').length, 0);
console.log('Map chests: decoded data, placement on the real VQSV5 maps and opening passed.');
