// VQSV5 pet stats decoded from vqsv5.jar (data.d) and their use in the unified battle.
const fs = require('fs'), path = require('path'), vm = require('vm'), assert = require('assert');
const html = fs.readFileSync(path.join(__dirname, '..', 'prototype', 'index.html'), 'utf8');
const code = [...html.matchAll(/<script>([\s\S]*?)<\/script>/g)].at(-1)[1].replace(/    loadData\(\)\.catch\(err => \{[^\n]+\}\);/, '');
const boot = require('./bootstrap_for_tests.cjs');
const S5 = boot.vqsv5Stats;

// ---- Data invariants ----
assert.equal(S5.pets.length, 102); assert.equal(S5.skills.length, 49); assert.equal(S5.traits.length, 13);
assert.equal(S5.pets.filter(p => p.type_id === null).length, 2);
// Independent check: every typed pet's description names the type it beats ("[khắc chế X hệ]"), which validates both the
// type column (c6) and the type chart.
for (const pet of S5.pets.filter(p => p.type)) {
  const tag = pet.description.match(/khắc chế ([^\]]+?) ?hệ\]/i);
  assert(tag, pet.name);
  assert.equal(tag[1].trim().toLowerCase(), S5.rules.type_chart[pet.type].beats.replace(' hệ', '').toLowerCase(), pet.name);
}
// Stat formulas (al constructor / al.b(byte[])): c0 + c7*L/10 = HP, c1 + c8*L/10 = SP, c2 + c9*L/10 = agility, c3 + c10*L/10 = attack, c4 + c11*L/10 = defense.
const thu = S5.pets[0];
assert.equal(thu.name, 'Thụ quái');
assert.deepEqual(thu.raw_fields.slice(0, 12), [32, 80, 12, 10, 12, 1, 0, 38, 15, 8, 6, 8]);
assert.deepEqual(thu.stats_at['10'], { hp: 70, sp: 95, agility: 20, attack: 16, defense: 20 });
assert.deepEqual(thu.stats_at['1'], { hp: 35, sp: 81, agility: 12, attack: 10, defense: 12 });
assert.equal(thu.type, 'Thực Vật hệ'); assert.equal(thu.rank, 1);
// Skill slots (ak.a(al)): basic skill 25 at level 0, then type*5 + (slot-1) at levels 7/14/21/28/35; typeless pets only have the basic one.
const slots = pet => pet.skill_slots.map(s => [s.level, s.skill_id]);
assert.deepEqual(slots(thu), [[0, 25], [7, 0], [14, 1], [21, 2], [28, 3], [35, 4]]);
const flying = S5.pets.find(p => p.type === 'Phi Hành hệ');
assert.deepEqual(slots(flying), [[0, 25], [7, 10], [14, 11], [21, 12], [28, 13], [35, 14]]);
assert.deepEqual(slots(S5.pets.find(p => p.type_id === null)), [[0, 25]]);
assert.equal(S5.skills[25].name, 'Công Kích thông dụng'); assert.equal(S5.skills[25].power, 8); assert.equal(S5.skills[25].sp_cost, 0);
assert.equal(S5.skills[0].power, 15); assert.equal(S5.skills[0].sp_cost, 10);
for (let type = 0; type < 5; type++) { const powers = [0, 1, 2, 3, 4].map(tier => S5.skills[type * 5 + tier].power); assert.deepEqual(powers, [...powers].sort((a, b) => a - b), `type ${type} skills get stronger`); }
// Traits: HP traits 1 (-15%) and 2 (+15%) as signed bytes.
assert.deepEqual([S5.traits[1].value, S5.traits[2].value], [-15, 15]);

// ---- Game integration ----
const els = {};
const element = id => els[id] ||= { textContent: '', hidden: false, open: false, listeners: {}, addEventListener(event, fn) { this.listeners[event] = fn; }, getContext() { return {}; }, replaceChildren() {}, append() {}, setAttribute() {} };
const store = new Map(), localStorage = { getItem: k => store.get(k) ?? null, setItem: (k, v) => store.set(k, v) };
const teamIds = [];
const VQSVPetTeam = { ids: () => teamIds, replaceSpecies() {}, caughtBest: () => null,
  info: id => ({ id, kind: id.startsWith('vqsv5') ? 'vqsv5' : 'vqsv6', name: id, element: null, sequences: [], image: `assets/pets/${id}.png` }), recordCatch: () => ({ addedToTeam: false }) };
const ctx = vm.createContext({ document: { getElementById: element, createElement: () => ({ append() {}, addEventListener() {}, setAttribute() {}, replaceChildren() {} }) }, window: { addEventListener() {}, VQSVBootstrap: boot, VQSVPetTeam }, Image: class {}, Element: class {}, performance: { now: () => 1000 }, console, localStorage });
vm.runInContext(code, ctx);
const run = js => vm.runInContext(js, ctx);
const J = js => JSON.parse(JSON.stringify(run(js)));
run('assets.map = { width: 4, height: 4, objects: [], entities: { player: { x: 1, y: 1 }, elder: { x: 3, y: 3 }, encounter: { x: 2, y: 2 } } }; reset()');

// Calibration: an average VQSV5 pet matches an average VQSV6 pet at the same level (hp, attack, defense, speed), preserving relative strengths.
for (const level of [5, 20, 40]) {
  const mean = values => values.reduce((a, b) => a + b, 0) / values.length;
  const five = J(`STATS5.pets.map(pet => unifiedStats5(pet, ${level}))`), six = J(`STATS.pets.map(pet => statsAt(pet, ${level}))`);
  for (const key of ['hp', 'attack', 'defense', 'speed']) {
    const a = mean(five.map(x => x[key])), b = mean(six.map(x => x[key]));
    assert(Math.abs(a - b) / b < 0.02 + 1.5 / b, `${key} at ${level}: ${a} vs ${b}`);
  }
}
const stronger = J('STATS5.pets.filter(p => p.rank === 5).map(p => unifiedStats5(p, 30).hp)'), weaker = J('STATS5.pets.filter(p => p.rank === 1).map(p => unifiedStats5(p, 30).hp)');
assert(stronger.reduce((a, b) => a + b) / stronger.length > weaker.reduce((a, b) => a + b) / weaker.length);

// Battle: a VQSV5 reserve pet has decoded stats and the type skills it has unlocked.
teamIds.push('vqsv5-db-0');
run('progress = { "vqsv5-db-0": { level: 14, exp: 0 } }; Math.random = () => 0.99; state.battle = newBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 }); state.mode = "battle"; state.battle.enemy.stats.speed = 0; state.battle.enemy.skills = []');
let pet = J('state.battle.team[1]');
assert.equal(pet.v5, true); assert.equal(pet.progressKey, 'vqsv5-db-0'); assert.equal(pet.element, 'Thực Vật hệ');
assert.deepEqual(pet.stats, J('unifiedStats5(STAT5_BY_ID.get("vqsv5-db-0"), 14)'));
assert.deepEqual(pet.skills.map(s => s.name), ['Triền Nhiễu', 'Phong Lợi Tiêm Thứ']);
// powers: skill power / 15; uses: SP / cost (SP at level 14 = 80 + 15*14/10 = 101): 101/10 = 10 and 101/13 = 7.
assert.deepEqual(pet.skills.map(s => s.power), [100, 120]); assert.deepEqual(pet.skills.map(s => s.max), [10, 7]);
assert.equal(pet.basicPower, 53);
// Fighting: skills deal damage and spend uses; the basic move uses the source basic-attack power.
run('switchPet(1); state.battle.pendingUntil = 0; state.battle.enemy.hp = state.battle.enemy.max; action(false, "skill", 0)');
assert.match(run('state.battle.message'), /vqsv5-db-0 dùng Triền Nhiễu, \d+ sát thương/);
assert.equal(run('activePet().skills[0].uses'), 9);
assert(run('state.battle.enemy.hp') < run('state.battle.enemy.max'));
run('state.battle.pendingUntil = 0; state.battle.enemyActed = false; action(false, "basic")');
assert.match(run('state.battle.message'), /Đòn thường/);
// Below level 7 there are no type skills yet; level-ups recompute stats and unlock them automatically.
teamIds.length = 0; teamIds.push('vqsv5-db-0');
run('progress = { "vqsv5-db-0": { level: 6, exp: 0 } }; state.battle = newBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 }); state.mode = "battle"');
assert.equal(run('state.battle.team[1].skills.length'), 0);
const hpBefore = run('state.battle.team[1].max');
run('gainExp(state.battle.team[1], 300000)'); // levels 7..35 cost 216485 exp in total
assert(run('state.battle.team[1].level') >= 35);
assert.equal(run('state.battle.team[1].skills.length'), 5);
assert(run('state.battle.team[1].max') > hpBefore);
assert.equal(run('state.battle.team[1].hp'), run('state.battle.team[1].max'));
// A typeless boss-like pet has the basic move only and no element.
teamIds.length = 0; teamIds.push('vqsv5-db-85');
run('progress = { "vqsv5-db-85": { level: 40, exp: 0 } }; state.battle = newBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 }); state.mode = "battle"');
assert.equal(run('state.battle.team[1].skills.length'), 0); assert.equal(run('state.battle.team[1].element'), null);
// A story/wild win gives the VQSV5 pet experience through the shared formula (it no longer says "chưa nhận kinh nghiệm").
teamIds.length = 0; teamIds.push('vqsv5-db-0');
run('progress = { "loi-mieu": { level: 5, exp: 0 }, "vqsv5-db-0": { level: 5, exp: 0 } }; state.battle = newBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 }); state.mode = "battle"; switchPet(1); state.battle.pendingUntil = 0; state.battle.enemy.hp = 0; state.battle.pendingUntil = 1; updateBattle(2)');
assert.match(run('state.battle.message'), /vqsv5-db-0 \+\d+ KN/);
assert.doesNotMatch(run('state.battle.message'), /VQSV5\) chưa nhận/);
console.log('VQSV5 stats: formulas, types, skill slots, calibration and battle use passed.');
