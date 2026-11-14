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
// Every hop is two-way and both arrival cells are walkable (the maps are real VQSV5 maps: exits sit on their walkable edges).
for (let i = 0; i + 1 < chain.length; i++) {
  const east = maps[chain[i]].links.find(l => l.target === chain[i + 1]);
  const west = maps[chain[i + 1]].links.find(l => l.target === chain[i]);
  assert(east && west, `${chain[i]} <-> ${chain[i + 1]}`);
  assert.deepEqual([east.spawnX, east.spawnY], maps[chain[i + 1]].arrivals[chain[i]]);
  run(`assets.map = maps["${chain[i + 1]}"]`); assert.equal(run(`blocked(${east.spawnX}, ${east.spawnY})`), false);
  run(`assets.map = maps["${chain[i]}"]`); assert.equal(run(`blocked(${west.spawnX}, ${west.spawnY})`), false);
}
// Walk the whole chain: from each arrival cell the next exit must be reachable on foot, then E takes you there.
const reachable = (from, to) => JSON.parse(run(`JSON.stringify((() => { const seen = new Set(["${from}"]); const q = [[${from}]]; while (q.length) { const [x, y] = q.shift(); for (const [dx, dy] of [[1,0],[-1,0],[0,1],[0,-1]]) { const nx = x + dx, ny = y + dy, key = nx + "," + ny; if (!seen.has(key) && (key === "${to}" || !blocked(nx, ny))) { seen.add(key); q.push([nx, ny]); } } } return seen.has("${to}"); })())`));
run('assets.map = maps["ryan-forest-stream"]');
for (let i = 1; i < chain.length; i++) {
  const here = maps[chain[i - 1]], exit = here.links.find(l => l.target === chain[i]);
  const start = i === 1 ? [here.entities.player.x, here.entities.player.y] : here.arrivals[chain[i - 2]];
  assert(reachable(start.join(','), `${exit.x},${exit.y}`), `${chain[i - 1]}: exit to ${chain[i]} reachable`);
  run(`state.player.x = ${exit.x}; state.player.y = ${exit.y}; interact()`);
  assert.equal(run('assets.map.id'), chain[i]);
  assert.deepEqual([run('state.player.x'), run('state.player.y')], maps[chain[i]].arrivals[chain[i - 1]]);
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
  assert(maps[id].source5 && maps[id].collision.length === maps[id].height, `${id} is a real VQSV5 map`);
}
// No area is shared between regions.
const used = chain.slice(1).flatMap(id => maps[id].wildAreas);
assert.equal(new Set(used).size, used.length);
// Water pets (terrain 2): only on a bridge, only where the area has them; elsewhere the roll falls back to land.
const waterIds = new Set(wildAreas.filter(a => maps['ryan-lakeside'].wildAreas.includes(a.area)).flatMap(a => a.pets.filter(p => p.terrain === 2).map(p => p.pet_id)));
assert(waterIds.size > 0);
run('assets.map = maps["ryan-lakeside"]');
// on a real VQSV5 map: next to deep water (collision 2) or in shallow water (3) counts as water; inland does not
const lake = maps['ryan-lakeside'];
const shore = (() => { for (let y = 0; y < lake.height; y++) for (let x = 0; x < lake.width; x++) if (lake.collision[y][x] === '0' && [[1,0],[-1,0],[0,1],[0,-1]].some(([dx, dy]) => (lake.collision[y + dy] || '')[x + dx] === '2')) return [x, y]; })();
const inland = (() => { for (let y = 0; y < lake.height; y++) for (let x = 0; x < lake.width; x++) if (lake.collision[y][x] === '0' && ![[1,0],[-1,0],[0,1],[0,-1]].some(([dx, dy]) => ['2', '3'].includes((lake.collision[y + dy] || '')[x + dx]))) return [x, y]; })();
assert.equal(run(`terrainAt(${shore})`), 2); assert.equal(run(`terrainAt(${inland})`), 0);
let sawWater = false;
for (let i = 0; i < 200; i++) { const roll = JSON.parse(JSON.stringify(run('rollWild("ryan-lakeside", 2)'))); if (waterIds.has(roll.petId)) sawWater = true; }
assert(sawWater);
const landOnly = JSON.parse(JSON.stringify(run('Array.from({ length: 300 }, () => rollWild("ryan-lakeside", 0).petId)')));
assert(landOnly.every(id => !waterIds.has(id) || wildAreas.some(a => maps['ryan-lakeside'].wildAreas.includes(a.area) && a.pets.some(p => p.pet_id === id && p.terrain === 0))));
run('assets.map = maps["ryan-meadow"]'); // no water pets there: terrain 2 falls back to land
assert(JSON.parse(JSON.stringify(run('rollWild("ryan-meadow", 2)'))).level >= 13);
// Entering a region reports the wild level range.
{ const l = maps['ryan-highland'].links.find(k => k.target === 'ryan-canyon'); run(`assets.map = maps["ryan-highland"]; state.player.x = ${l.x}; state.player.y = ${l.y}; interact()`); }
assert.match(element('status').textContent, /Hẻm Núi Đá · pet hoang dã cấp 24–32/);
// A high-level wild pet gets level-appropriate skills (slots = level / 10 + 1) from its own element pool.
const skyAreas = maps['ryan-skyridge'].wildAreas;
let sample; do { sample = JSON.parse(JSON.stringify(run('rollWild("ryan-skyridge")'))); } while (sample.v5);   // VQSV6 pets only; VQSV5 wild pets: test_vqsv5_wild.cjs
const battle = JSON.parse(JSON.stringify(run(`(() => { const b = newBattle(${JSON.stringify(sample)}); state.battle = b; return { skills: b.enemy.skills.length, level: b.enemy.level, hp: b.enemy.max, slots: skillSlots(b.enemy.level) }; })()`)));
assert.equal(battle.skills, battle.slots); assert(battle.level >= 35);
console.log('Wild regions: chain travel, reachability, source pools, level order, water pets and real VQSV5 layouts passed.');
