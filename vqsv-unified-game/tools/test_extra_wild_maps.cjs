// Branch maps for the VQSV6 pets no source wild area contains (tools/design_extra_wild_maps.py).
const fs = require('fs'), path = require('path'), vm = require('vm'), assert = require('assert');
const html = fs.readFileSync(path.join(__dirname, '..', 'prototype', 'index.html'), 'utf8');
const code = [...html.matchAll(/<script>([\s\S]*?)<\/script>/g)].at(-1)[1].replace(/    loadData\(\)\.catch\(err => \{[^\n]+\}\);/, '');
const boot = require('./bootstrap_for_tests.cjs');
const maps = boot.maps, S6 = boot.vqsv6Stats;
const NEW = { 'ryan-pinewind': 'ryan-phong-loi-forest', 'ryan-inkmarsh': 'ryan-lakeside', 'ryan-stormplain': 'ryan-canyon', 'ryan-sanctum': 'ryan-skyridge' };
const pets6 = new Map(S6.pets.map(p => [p.pet_id, p]));

// Every VQSV6 base form can now be met in the wild (evolved forms come from evolution).
const roster = new Map(boot.petRoster.records.map(r => [r.pet_id, r]));
const evolved = new Set(boot.petRoster.records.map(r => r.evolution?.target_pet_id).filter(Boolean));
const met = new Set([...S6.wild_areas.flatMap(a => a.pets.map(p => p.pet_id)), ...Object.values(maps).flatMap(m => (m.customWildAreas || []).flatMap(a => a.pets.map(p => p.pet_id)))]);
const missing = S6.pets.map(p => p.pet_id).filter(id => !met.has(id) && !evolved.has(id));
assert.deepEqual(missing, [], `base forms still without a wild area: ${missing}`);
// Custom tables only use known pets, quality at least their default, and are not source areas.
for (const [id, host] of Object.entries(NEW)) {
  const m = maps[id]; assert(m, id);
  for (const area of m.customWildAreas) {
    assert(area.area.startsWith('x-') && !S6.wild_areas.some(a => a.area === area.area));
    assert(area.level_range[0] <= area.level_range[1]);
    for (const p of area.pets) { const rec = pets6.get(p.pet_id); assert(rec, p.pet_id); assert.equal(p.name, rec.name); assert(p.quality_range[0] >= rec.default_quality && p.quality_range[1] <= 5, p.pet_id); assert([0, 2].includes(p.terrain)); }
  }
  // Two-way links: the host has an exit to the new map, the new map one back; both arrival tiles are free.
  const out = maps[host].links.find(l => l.target === id), back = m.links.find(l => l.target === host);
  assert(out && back, id); assert.equal(m.terrain5.types.length, 1); assert.deepEqual(m.terrain5.source_maps, [m.source5.map]);
  assert.equal(m.wild5, false, `${id} keeps VQSV5 wild pets out`);
  assert.deepEqual([out.spawnX, out.spawnY], [m.entities.player.x, m.entities.player.y]);
}
// ---- In the game ----
const els = {}, element = id => els[id] ||= { textContent: '', hidden: false, addEventListener() {}, getContext() { return {}; }, replaceChildren() {}, append() {} };
const store = new Map(), localStorage = { getItem: k => store.get(k) ?? null, setItem: (k, v) => store.set(k, v) };
const ctx = vm.createContext({ document: { getElementById: element, createElement: () => ({ append() {}, addEventListener() {}, setAttribute() {}, replaceChildren() {} }) }, window: { addEventListener() {}, VQSVBootstrap: boot }, performance: { now: () => 1000 }, Element: class {}, Image: class {}, console, localStorage });
vm.runInContext(code, ctx);
const run = js => vm.runInContext(js, ctx), J = js => JSON.parse(JSON.stringify(run(js)) ?? 'null');
run('assets.maps = window.VQSVBootstrap.maps; assets.map = assets.maps["slice01-border-village"]; reset(); state.spokeToElder = true');
for (const [id, host] of Object.entries(NEW)) {
  // Travel there and back with E next to the exit.
  run(`assets.map = assets.maps["${host}"]; state.mode = "explore"; { const l = assets.map.links.find(l => l.target === "${id}"); state.player = { x: l.x, y: l.y, visualX: l.x, visualY: l.y, faceX: 0, faceY: 0, step: null, inputDir: null }; } interact()`);
  assert.equal(run('assets.map.id'), id); assert.equal(run('blocked(state.player.x, state.player.y)'), false);
  const range = J(`wildLevelRange("${id}")`); assert(range, id);
  assert.match(element('status').textContent, new RegExp(`pet hoang dã cấp ${range[0]}–${range[1]}`));
  // Land rolls come from its tables (VQSV6 only: no VQSV5 source areas), water pets only on the bridge.
  const allowed = new Set(J(`wildAreasOf("${id}")`).flatMap(a => a.pets.map(p => p.pet_id)));
  for (let i = 0; i < 120; i++) { const r = J(`rollWild("${id}")`); assert(!r.v5 && allowed.has(r.petId), `${id} ${r.petId}`); assert(r.level >= range[0] && r.level <= range[1]); }
  if (maps[id].bridge) {
    const water = new Set(maps[id].customWildAreas.flatMap(a => a.pets.filter(p => p.terrain === 2).map(p => p.pet_id)));
    let sawWater = false; for (let i = 0; i < 120; i++) if (water.has(J(`rollWild("${id}", 2)`).petId)) sawWater = true; assert(sawWater, `${id} water pets`);
    for (let i = 0; i < 120; i++) assert(!water.has(J(`rollWild("${id}", 0)`).petId) || maps[id].customWildAreas.some(a => a.pets.some(p => p.pet_id === J(`rollWild("${id}", 0)`).petId && p.terrain === 0)));
  }
  // A battle there: VQSV6 stats and skills, the map's battle background type.
  run(`{ let w; do { w = rollWild("${id}"); } while (w.v5); state.battle = newBattle(w); state.mode = "battle"; }`);
  assert.equal(J('battleFields5().enemy'), maps[id].terrain5.types[0]);
  assert.equal(J('state.battle.enemy.skills.length'), J('skillSlots(state.battle.enemy.level)'));
  run('state.mode = "explore"');
  { const l = maps[id].links.find(x => x.target === host); run(`state.player = { x: ${l.x}, y: ${l.y}, visualX: 0, visualY: 0, faceX: 0, faceY: 0, step: null, inputDir: null }; interact()`); }
  assert.equal(run('assets.map.id'), host); assert.equal(run('blocked(state.player.x, state.player.y)'), false);
}
// The sanctum only holds quality 5 legendaries at level 46-50.
for (let i = 0; i < 60; i++) { const r = J('rollWild("ryan-sanctum")'); assert.equal(r.quality, 5); assert(r.level >= 46); }
// ---- VQSV6 battle backgrounds: one new picture per element, chosen by region ----
for (const key of ['hoa', 'moc', 'tho', 'thuy', 'dien', 'quy', 'phong']) assert(fs.existsSync(path.join(__dirname, '..', 'prototype', 'assets', 'battle', `vqsv6_${key}.png`)), key);
const expectBg = { 'ryan-phong-loi-forest': 'moc', 'ryan-forest-stream': 'dien', 'ryan-lakeside': 'thuy', 'ryan-meadow': 'moc', 'ryan-highland': 'tho', 'ryan-canyon': 'tho',
  'ryan-ashfield': 'hoa', 'ryan-skyridge': 'phong', 'ryan-pinewind': 'phong', 'ryan-inkmarsh': 'quy', 'ryan-stormplain': 'dien' };
for (const [id, key] of Object.entries(expectBg)) {
  run(`assets.map = assets.maps["${id}"]; { let w; do { w = rollWild("${id}"); } while (w.v5); state.battle = newBattle(w); }`);
  assert.equal(run('state.battle.backdrop6'), key, id);
}
// The sanctum shows the legendary's own element; the story fight the ghost marsh; a VQSV5 wild pet keeps the VQSV5 field pictures.
const bgOf = { 'Hỏa hệ': 'hoa', 'Mộc hệ': 'moc', 'Thổ hệ': 'tho', 'Thủy hệ': 'thuy', 'Điện hệ': 'dien', 'Quỷ hệ': 'quy', 'Phong hệ': 'phong' };
run('assets.map = assets.maps["ryan-sanctum"]');
for (let i = 0; i < 20; i++) { run('state.battle = newBattle(rollWild("ryan-sanctum"))'); assert.equal(run('state.battle.backdrop6'), bgOf[run('state.battle.enemy.element')]); }
run('assets.map = assets.maps["slice01-border-village"]; state.battle = newBattle()'); assert.equal(run('state.battle.backdrop6'), 'quy');
run('assets.map = assets.maps["ryan-meadow"]; state.battle = newBattle({ v5: true, petId: "vqsv5-db-0", level: 10, quality: 1, traits: [3, 0] })'); assert.equal(run('state.battle.backdrop6'), null);
assert.equal(run('backdrop6For("unknown-map", { petId: "vqsv6-db-33" }, { element: "Hỏa hệ" })'), null);
// The marsh's water pets are met beside its water (the map is a real VQSV5 map with deep water).
{ const m = maps['ryan-inkmarsh'], water = new Set(m.customWildAreas.flatMap(a => a.pets.filter(p => p.terrain === 2).map(p => p.pet_id)));
  const shore = (() => { for (let y = 0; y < m.height; y++) for (let x = 0; x < m.width; x++) if (m.collision[y][x] === '0' && [[1,0],[-1,0],[0,1],[0,-1]].some(([dx, dy]) => (m.collision[y + dy] || '')[x + dx] === '2')) return [x, y]; })();
  run('assets.map = assets.maps["ryan-inkmarsh"]'); assert.equal(run(`terrainAt(${shore})`), 2);
  let saw = false; for (let i = 0; i < 100; i++) if (water.has(J(`rollWild("ryan-inkmarsh", terrainAt(${shore}))`).petId)) saw = true; assert(saw); }
// Every pet of the branch tables can actually be met: water pets only on maps with water.
for (const id of Object.keys(NEW)) { const m = maps[id]; if (m.customWildAreas.some(a => a.pets.some(p => p.terrain === 2))) assert(m.collision.some(row => /[23]/.test(row)), `${id} has water for its water pets`); }
console.log('Extra wild maps: every VQSV6 base form reachable, two-way travel, tables, water pets, battles and VQSV6 backgrounds passed.');
