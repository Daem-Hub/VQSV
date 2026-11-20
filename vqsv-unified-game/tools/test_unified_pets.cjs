// VQSV5 + VQSV6 pets together: one element chart for mixed battles and wild pets of both games on the atlas maps.
const fs = require('fs'), vm = require('vm'), assert = require('assert'), path = require('path');
const html = fs.readFileSync(path.join(__dirname, '..', 'prototype', 'index.html'), 'utf8');
const code = [...html.matchAll(/<script>([\s\S]*?)<\/script>/g)].at(-1)[1].replace(/    loadData\(\)\.catch\(err => \{[^\n]+\}\);/, '');
const element = () => ({ textContent: '', hidden: true, addEventListener() {}, setAttribute() {}, getContext: () => new Proxy({}, { get: () => () => ({ addColorStop() {} }) }) });
const ctx = vm.createContext({ document: { getElementById: element }, window: { addEventListener() {}, VQSVBootstrap: require('./bootstrap_for_tests.cjs') }, Element: class {}, Image: class {}, performance: { now: () => 1000 }, console });
vm.runInContext(code, ctx);
const run = js => vm.runInContext(js, ctx);
ctx.maps = require('./bootstrap_for_tests.cjs').maps;
run('assets.maps = maps; assets.map = maps["slice01-border-village"]; reset()');
// The type table: every VQSV5 type lands on a VQSV6 element.
const v5 = type => run(`vqsv5Combatant(STATS5.pets.find(p => p.type_id === ${type}).pet_id, 10)`);
assert.deepEqual([0, 1, 2, 3, 4].map(t => v5(t).unifiedElement), ['Mộc hệ', 'Điện hệ', 'Phong hệ', 'Thổ hệ', 'Thủy hệ']);
assert.equal(v5(null).unifiedElement, 'Quỷ hệ');
// Mixed battles use VQSV6's chart: Hỏa beats Mộc, so a VQSV6 Hỏa pet has the edge over a VQSV5 Thực Vật pet.
const fire6 = run('vqsv6Combatant(STATS.pets.find(p => p.element_id === 0).pet_id, 10)');
ctx.a = fire6; ctx.b = v5(0);
assert.equal(run('elementMultiplier(a, b)'), 1);
assert.equal(run('elementMultiplier(b, a)'), -1);
// Two VQSV5 pets keep only the source rule (+30% in adjustDamage5), not the VQSV6 chart on top.
ctx.c = v5(3);
assert.equal(run('elementMultiplier(c, b)'), 0);
// Every atlas map has wild pets; the mixed pools follow the map's elements and both games appear.
const atlas = Object.values(ctx.maps).filter(m => m.illustration);
assert.equal(atlas.length, 29);
let seen5 = 0, seen6 = 0;
for (const map of atlas) {
  assert(run(`wildAreasOf(${JSON.stringify(map.id)}).length`) > 0, map.id);
  for (let i = 0; i < 40; i++) {
    const wild = run(`rollWild(${JSON.stringify(map.id)}, 0)`);
    assert(wild, map.id);
    const enemy = run(`buildWild(${JSON.stringify(wild)})`);
    assert(map.wildElements.includes(enemy.unifiedElement || enemy.element), `${map.id}: ${enemy.name} ${enemy.element}`);
    assert(wild.level >= map.customWildAreas[0].level_range[0] && wild.level <= map.customWildAreas[0].level_range[1]);
    wild.v5 ? seen5++ : seen6++;
  }
}
assert(seen5 > 100 && seen6 > 100, `both games appear (${seen5} VQSV5, ${seen6} VQSV6)`);
console.log(`Unified pets: VQSV5 types on the VQSV6 chart, mixed battles, ${atlas.length} atlas maps with wild pets of both games passed.`);
