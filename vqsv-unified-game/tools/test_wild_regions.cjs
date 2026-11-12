// The six higher-level wild regions east of Suối Sấm: chain travel, reachability, source pet pools and water pets.
const fs = require('fs'), path = require('path'), vm = require('vm'), assert = require('assert');
const html = fs.readFileSync(path.join(__dirname, '..', 'prototype', 'index.html'), 'utf8');
const code = [...html.matchAll(/<script>([\s\S]*?)<\/script>/g)].at(-1)[1].replace(/    loadData\(\)\.catch\(err => \{[^\n]+\}\);/, '');
const boot = require('./bootstrap_for_tests.cjs');
const maps = boot.maps;
const els = {}, element = id => els[id] ||= { textContent: '', hidden: false, addEventListener() {}, getContext() { return {}; } };
const store = new Map(), localStorage = { getItem: k => store.get(k) ?? null, setItem: (k, v) => store.set(k, v) };
const ctx = vm.createContext({ document: { getElementById: element }, window: { addEventListener() {}, VQSVBootstrap: boot, VQSVPetTeam: { ids: () => [], info: id => ({ id, image: id, sequences: [] }) } }, performance: { now: () => 1000 }, Element: class {}, Image: class {}, console, localStorage, maps });
vm.runInContext(code, ctx);
const run = js => vm.runInContext(js, ctx);
run('assets.maps = maps; assets.map = maps["slice01-border-village"]; reset(); state.spokeToElder = true');

const chain = ['ryan-forest-stream', 'ryan-lakeside', 'ryan-meadow', 'ryan-highland', 'ryan-canyon', 'ryan-ashfield', 'ryan-skyridge'];
for (const id of chain) assert(maps[id], id);
// Every hop is two-way and both spawn cells are walkable.
for (let i = 0; i + 1 < chain.length; i++) {
  const east = maps[chain[i]].links.find(l => l.target === chain[i + 1]);
  const west = maps[chain[i + 1]].links.find(l => l.target === chain[i]);
  assert(east && west, `${chain[i]} <-> ${chain[i + 1]}`);
  assert.equal(east.x, 23); assert.equal(west.x, 0);
  run(`assets.map = maps["${chain[i + 1]}"]`); assert.equal(run(`blocked(${east.spawnX}, ${east.spawnY})`), false);
  run(`assets.map = maps["${chain[i]}"]`); assert.equal(run(`blocked(${west.spawnX}, ${west.spawnY})`), false);
}
// Walk the whole chain by interacting at each exit (the exit cell must be reachable from the entry cell).
run('assets.map = maps["ryan-forest-stream"]; state.player.x = 22; state.player.y = 10');
for (let i = 1; i < chain.length; i++) {
  run('interact()'); // east exit
  assert.equal(run('assets.map.id'), chain[i]);
  assert.equal(run('state.player.x'), 1);
  // Reachability from the west entry to the east exit on this map.
  const reach = JSON.parse(run(`JSON.stringify((() => { const seen = new Set(["1,10"]); const q = [[1, 10]]; while (q.length) { const [x, y] = q.shift(); for (const [dx, dy] of [[1,0],[-1,0],[0,1],[0,-1]]) { const nx = x + dx, ny = y + dy, key = nx + "," + ny; if (!seen.has(key) && !blocked(nx, ny)) { seen.add(key); q.push([nx, ny]); } } } return seen.has("22,10") && seen.has("23,10") && seen.has("0,10"); })())`));
  assert.equal(reach, true, chain[i]);
  run('state.player.x = 22; state.player.y = 10');
}
// Regions have increasing, source-backed level ranges and only their own areas feed the rolls.
const ranges = chain.slice(1).map(id => JSON.parse(JSON.stringify(run(`wildLevelRange("${id}")`))));
for (let i = 1; i < ranges.length; i++) { assert(ranges[i][0] >= ranges[i - 1][0], `min level order ${i}`); assert(ranges[i][1] > ranges[i - 1][1], `max level order ${i}`); }
assert.deepEqual(ranges[0], [8, 14]); assert.deepEqual(ranges.at(-1), [35, 46]);
const wildAreas = boot.vqsv6Stats.wild_areas;
for (const id of chain.slice(1)) {
  const keys = maps[id].wildAreas;
  assert(keys.length >= 5, id);
  for (const key of keys) assert(wildAreas.some(area => area.area === key), `${id} references ${key}`);
  const allowed = new Set(wildAreas.filter(a => keys.includes(a.area)).flatMap(a => a.pets.map(p => p.pet_id)));
  const [lo, hi] = JSON.parse(JSON.stringify(run(`wildLevelRange("${id}")`)));
  for (let i = 0; i < 150; i++) {
    const roll = JSON.parse(JSON.stringify(run(`rollWild("${id}")`)));
    if (roll.v5) continue;                                  // VQSV5 wild pets are covered by test_vqsv5_wild.cjs
    assert(allowed.has(roll.petId), `${id}: ${roll.petId}`);
    assert(roll.level >= lo && roll.level <= hi && roll.quality >= 1 && roll.quality <= 5);
  }
  assert(maps[id].tint, `${id} tint`);
}
// No area is shared between regions.
const used = chain.slice(1).flatMap(id => maps[id].wildAreas);
assert.equal(new Set(used).size, used.length);
// Water pets (terrain 2): only on a bridge, only where the area has them; elsewhere the roll falls back to land.
const waterIds = new Set(wildAreas.filter(a => maps['ryan-lakeside'].wildAreas.includes(a.area)).flatMap(a => a.pets.filter(p => p.terrain === 2).map(p => p.pet_id)));
assert(waterIds.size > 0);
run('assets.map = maps["ryan-lakeside"]');
assert.equal(run('terrainAt(11, 10)'), 2); assert.equal(run('terrainAt(2, 10)'), 0);
let sawWater = false;
for (let i = 0; i < 200; i++) { const roll = JSON.parse(JSON.stringify(run('rollWild("ryan-lakeside", 2)'))); if (waterIds.has(roll.petId)) sawWater = true; }
assert(sawWater);
const landOnly = JSON.parse(JSON.stringify(run('Array.from({ length: 300 }, () => rollWild("ryan-lakeside", 0).petId)')));
assert(landOnly.every(id => !waterIds.has(id) || wildAreas.some(a => maps['ryan-lakeside'].wildAreas.includes(a.area) && a.pets.some(p => p.pet_id === id && p.terrain === 0))));
run('assets.map = maps["ryan-meadow"]'); // no bridge/water pets there: terrain 2 falls back to land
assert.equal(run('terrainAt(11, 10)'), 0);
assert(JSON.parse(JSON.stringify(run('rollWild("ryan-meadow", 2)'))).level >= 13);
// Entering a region reports the wild level range.
run('assets.map = maps["ryan-canyon"]; assets.map = maps["ryan-highland"]; state.player.x = 22; state.player.y = 10; interact()');
assert.match(element('status').textContent, /Hẻm Núi Đá · pet hoang dã cấp 24–32/);
// A high-level wild pet gets level-appropriate skills (slots = level / 10 + 1) from its own element pool.
const skyAreas = maps['ryan-skyridge'].wildAreas;
const sample = JSON.parse(JSON.stringify(run('rollWild("ryan-skyridge")')));
const battle = JSON.parse(JSON.stringify(run(`(() => { const b = newBattle(${JSON.stringify(sample)}); state.battle = b; return { skills: b.enemy.skills.length, level: b.enemy.level, hp: b.enemy.max, slots: skillSlots(b.enemy.level) }; })()`)));
assert.equal(battle.skills, battle.slots); assert(battle.level >= 35);
console.log('Wild regions: chain travel, reachability, source pools, level order, water pets and tints passed.');
