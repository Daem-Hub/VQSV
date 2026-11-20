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
const replaced = [];
const VQSVPetTeam = { ids: () => teamIds, replaceSpecies: (a, b) => { replaced.push([a, b]); const i = teamIds.indexOf(a); if (i >= 0) teamIds[i] = b; }, caughtBest: () => null,
  info: id => ({ id, kind: id.startsWith('vqsv5') ? 'vqsv5' : 'vqsv6', name: id, element: null, sequences: [], image: `assets/pets/${id}.png` }), recordCatch: () => ({ addedToTeam: false }) };
const ctx = vm.createContext({ document: { getElementById: element, createElement: () => ({ append() {}, addEventListener() {}, setAttribute() {}, replaceChildren() {} }) }, window: { addEventListener() {}, VQSVBootstrap: boot, VQSVPetTeam }, Image: class {}, Element: class {}, performance: { now: () => 1000 }, console, localStorage });
vm.runInContext(code, ctx);
const run = js => vm.runInContext(js, ctx);
const J = js => { const text = JSON.stringify(run(js)); return text === undefined ? undefined : JSON.parse(text); };
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
run('progress = { "vqsv5-db-0": { level: 14, exp: 0, traits: [3, 0] } }; Math.random = () => 0.99; state.battle = newBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 }); state.mode = "battle"; state.battle.enemy.stats.speed = 0; state.battle.enemy.skills = []');
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

// ---- Evolution (ak.E(), ak.d(int), ak.b(byte,byte)) ----
// Data: 46 evolution rows, 4 mutations; the decoder already asserts the cross-checks (targets == pets found by evolution/mutation,
// same type, rank + 1, initial counter == chain length), repeated here against the exported file.
const evoPets = S5.pets.filter(p => p.evolution);
assert.equal(evoPets.length, 46); assert.equal(evoPets.filter(p => p.evolution.mutation_target_pet_id).length, 4);
assert.deepEqual(new Set(evoPets.map(p => p.evolution.target_pet_id)), new Set(S5.pets.filter(p => p.found_by === 'evolution').map(p => p.pet_id)));
assert.deepEqual(new Set(evoPets.filter(p => p.evolution.mutation_target_pet_id).map(p => p.evolution.mutation_target_pet_id)), new Set(S5.pets.filter(p => p.found_by === 'mutation').map(p => p.pet_id)));
const byId = new Map(S5.pets.map(p => [p.pet_id, p]));
for (const pet of evoPets) { const target = byId.get(pet.evolution.target_pet_id); assert.equal(target.type_id, pet.type_id); assert.equal(target.rank, pet.rank + 1); }
assert.deepEqual(S5.evolution_rules.level_gate_by_rank, { 1: 15, 2: 25, 3: 30 });
assert.deepEqual(S5.items.filter(i => i.sold).map(i => [i.item_id, i.price]), [[16, 500], [17, 1000], [18, 500], [19, 1000], [20, 500], [21, 1000], [22, 500], [23, 1000], [24, 500], [25, 1000], [33, 50]]);
assert.deepEqual(S5.items.filter(i => !i.sold).map(i => i.item_id), [26, 27, 28, 29, 30, 31, 32, 34]);
// Thụ quái (rank 1) -> Bụi gai needs 3 Thụ chướng thạch (item 18) and level 15; the second step needs rank-2 level 25 and 2 + 2 materials.
assert.deepEqual(byId.get('vqsv5-db-0').evolution.materials.map(m => [m.item_id, m.quantity]), [[18, 3]]);
assert.equal(byId.get('vqsv5-db-0').evolution.min_level, 15); assert.equal(byId.get('vqsv5-db-1').evolution.min_level, 25); assert.equal(byId.get('vqsv5-db-2').evolution.min_level, 30);
assert.equal(byId.get('vqsv5-db-3').evolution, null);
// Placeholder rows (target itself or 0) are not evolutions.
for (const id of ['vqsv5-db-14', 'vqsv5-db-23', 'vqsv5-db-78']) assert.equal(byId.get(id).evolution, null);

const chk = (id, mode = 'evolution') => J(`evolutionCheck5(${JSON.stringify(id)}, ${JSON.stringify(id)}, ${JSON.stringify(mode)})`);
const reset5 = (level, extra = '', team = ['vqsv5-db-0']) => {
  teamIds.length = 0; teamIds.push(...team); replaced.length = 0;
  const progressInit = JSON.stringify(Object.fromEntries(team.map(id => [id, { level, exp: 12 }])));
  run(`state.mode = "explore"; wallet = { gold: 5000, badges: 0, rewarded: {} }; for (const id of ITEMS5.keys()) mats5[id] = 0; progress = ${progressInit}; ${extra}`);
};
// Gate by rank: level 14 is too low for a rank-1 pet; materials missing.
reset5(14);
let c = chk('vqsv5-db-0');
assert.equal(c.ok, false); assert.match(c.reason, /cần cấp 15 \(đang cấp 14\)/); assert.match(c.reason, /thiếu Thụ chướng thạch \(0\/3\)/);
// The materials shop sells them for gold (item 18 = 500 each, labelled VQSV5); battle prices are doubled.
assert.equal(J('catalogById.get("mat5-18").price'), 500); assert.match(J('catalogById.get("mat5-18").name'), /Thụ chướng thạch \(VQSV5\)/);
assert.equal(J('buyItem("mat5-18", 3, false).total'), 1500); assert.equal(run('mats5[18]'), 3); assert.equal(run('wallet.gold'), 3500);
assert.equal(J('buyItem("mat5-18", 1, true).total'), 1000);
assert.equal(J('catalogById.get("mat5-27")'), undefined); // not sold
// Evolve at level 15: materials consumed, level and exp kept, counter 3 -> 2, species swapped in the team, full recomputed stats.
reset5(15, 'mats5[18] = 4');
c = chk('vqsv5-db-0'); assert.equal(c.ok, true);
assert.equal(J('window.VQSVEvolution.options("vqsv5-db-0")').map(o => o.mode).join(), 'evolution');
let r = J('window.VQSVEvolution.evolve("vqsv5-db-0", "vqsv5-db-0")');
assert.equal(r.ok, true); assert.match(r.message, /Thụ quái tiến hóa thành Bụi gai! HP \d+→\d+.*Còn 2 lần tiến hóa\./);
assert.equal(run('mats5[18]'), 1);
assert.equal(run('progress["vqsv5-db-0"]'), undefined);
assert.deepEqual(J('progress["vqsv5-db-1"]'), { level: 15, exp: 12, evoLeft: 2 });
assert.deepEqual(replaced, [['vqsv5-db-0', 'vqsv5-db-1']]); assert.deepEqual(teamIds, ['vqsv5-db-1']);
assert(r.after.hp > r.before.hp);
// The next battle uses the evolved species.
run('state.battle = newBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 }); state.mode = "battle"');
assert.equal(run('state.battle.team[1].petId'), 'vqsv5-db-1'); assert.equal(run('state.battle.team[1].level'), 15);
run('state.mode = "explore"');
// Next step (rank 2): level 25 and 2 Thụ chướng thạch + 2 Chu quả; the per-pet counter keeps counting down to 0 at the end of the chain.
c = chk('vqsv5-db-1'); assert.match(c.reason, /cần cấp 25/); assert.match(c.reason, /thiếu Chu quả \(0\/2\)/); assert.equal(c.left, 2);
run('progress["vqsv5-db-1"].level = 25; mats5[18] = 2; mats5[19] = 2'); r = J('evolvePet("vqsv5-db-1", "vqsv5-db-1")');
assert.equal(r.ok, true); assert.equal(r.left, 1); { const { traits, ...rest } = J('progress["vqsv5-db-2"]'); assert.deepEqual(rest, { level: 25, exp: 12, evoLeft: 1 }); }
c = chk('vqsv5-db-2'); assert.match(c.reason, /cần cấp 30/);
run('progress["vqsv5-db-2"].level = 30; mats5[19] = 3; mats5[27] = 1'); r = J('evolvePet("vqsv5-db-2", "vqsv5-db-2")');
assert.equal(r.ok, true); assert.equal(r.left, 0); assert.match(r.message, /thành Cổ Thụ Chi Vương/);
c = chk('vqsv5-db-3'); assert.equal(c.ok, false); assert.match(c.reason, /Đã là dạng cuối/);
// A species with a counter of 0 cannot evolve even when everything else is present (a wild pet found at its last form).
reset5(30, 'mats5[17] = 3; mats5[30] = 1', ['vqsv5-db-54']);
assert.equal(J('evolutionCheck5("vqsv5-db-54", "vqsv5-db-54")').left, 0);
assert.match(J('evolutionCheck5("vqsv5-db-54", "vqsv5-db-54")').reason, /đã hết lượt tiến hóa/);
// ...but the same species reached by evolving still has its remaining counter (Hổ Dũng Sĩ after Hổ Thú -> Công phu hổ -> Hổ Dũng Sĩ).
run('progress["vqsv5-db-54"].evoLeft = 1'); assert.equal(J('evolutionCheck5("vqsv5-db-54", "vqsv5-db-54")').left, 1);
// Mutation: Cỏ thơm (id 4) -> Hoa ăn thịt người (id 6) needs the same materials plus 1 item 34 and replaces the normal evolution.
reset5(15, 'mats5[18] = 3', ['vqsv5-db-4']);
const modes = J('window.VQSVEvolution.options("vqsv5-db-4")').map(o => [o.mode, o.ok]);
assert.deepEqual(modes, [['evolution', true], ['mutation', false]]);
assert.match(chk('vqsv5-db-4', 'mutation').reason, /thiếu Dị hoá tài liệu \(0\/1\)/);
run('mats5[34] = 1'); r = J('evolvePet("vqsv5-db-4", "vqsv5-db-4", "mutation")');
assert.equal(r.ok, true); assert.match(r.message, /Cỏ thơm dị hóa thành Hoa ăn thịt người/);
assert.equal(run('mats5[34]'), 0); assert.equal(run('mats5[18]'), 0); assert.equal(r.left, 0);
assert.deepEqual(J('progress["vqsv5-db-6"]'), { level: 15, exp: 12, evoLeft: 0 });
// No evolution in battle; a target already owned blocks it.
reset5(15, 'mats5[18] = 3; state.mode = "battle"'); assert.match(chk('vqsv5-db-0').reason, /không thể trong trận/);
reset5(15, 'mats5[18] = 3; progress["vqsv5-db-1"] = { level: 3, exp: 0 }'); assert.match(chk('vqsv5-db-0').reason, /đã có Bụi gai/);
// Materials persist and show in the bag text; high-level wins can drop the unsold ones (TRIAL table).
reset5(15, 'mats5[18] = 2; mats5[27] = 1; saveMats5()');
assert.deepEqual(JSON.parse(store.get('vqsv-unified-mats5-v1'))['18'], 2);
assert.match(run('stoneText()'), /Thụ chướng thạch ×2/); assert.match(run('stoneText()'), /Cổ thụ chi hạch ×1/);
run('Math.random = () => 0'); const drops = J('rollStoneDrops(30)'); assert(drops.includes('Cổ kình chi tâm') && !drops.includes('Cổ thụ chi hạch') && !drops.includes('Dị hoá tài liệu'));        // 26-28, 30, 34 now come from treasure boxes
run('Math.random = () => 0.999'); assert.deepEqual(J('rollStoneDrops(30)'), []);

// ---- Random traits ----
assert.deepEqual(S5.traits.map(t => t.value), [0, -15, 15, 20, 6, 20, -20, 20, -20, 20, -20, 20, -20]);
assert.deepEqual(S5.trait_rules.type_pairs['Hải Dương hệ'], [11, 12]);
assert.deepEqual(S5.traits.map(t => t.effect?.kind || null), [null, 'hp_percent', 'hp_percent', 'dot_taken_percent', 'regen_percent', 'status_chance_delta', 'status_chance_delta', 'status_chance_delta', 'status_chance_delta', 'damage_halved_chance', 'damage_boost_chance', 'status_chance_delta', 'status_chance_delta']);
const rollAt = (id, mode, r) => { run(`Math.random = () => ${r}`); return J(`rollTraits5(STAT5_BY_ID.get(${JSON.stringify(id)}), ${JSON.stringify(mode)})`); };
assert.deepEqual(rollAt('vqsv5-db-0', 'fixed', 0), [4, 2]);
assert.deepEqual(rollAt('vqsv5-db-0', 'random', 0), [3, 0]);
assert.deepEqual(rollAt('vqsv5-db-0', 'random', 0.99), [4, 2]);
assert.deepEqual(rollAt(flying.pet_id, 'random', 0.99), [8, 2]);
assert.deepEqual(rollAt('vqsv5-db-0', 'lucky', 0.5), [4, 2]);
assert.deepEqual(rollAt('vqsv5-db-0', 'lucky', 0.8), [3, 1]);
const typeless = S5.pets.find(p => p.type_id === null);
assert.deepEqual(rollAt(typeless.pet_id, 'random', 0.99), [0, 2]);
// Traits are rolled once, saved with the individual, and carried through evolution.
reset5(15, 'mats5[18] = 3; Math.random = () => 0.99');
assert.deepEqual(J('ensureTraits5("vqsv5-db-0", "vqsv5-db-0")'), [4, 2]);
run('Math.random = () => 0'); assert.deepEqual(J('ensureTraits5("vqsv5-db-0", "vqsv5-db-0")'), [4, 2]);
assert.deepEqual(J('progress["vqsv5-db-0"].traits'), [4, 2]);
assert.equal(J('window.VQSVEvolution.evolve("vqsv5-db-0", "vqsv5-db-0")').ok, true);
assert.deepEqual(J('progress["vqsv5-db-1"].traits'), [4, 2]);
run('progress["vqsv5-db-0"] = { level: 5, exp: 0, traits: [99, 1] }; Math.random = () => 0'); assert.deepEqual(J('ensureTraits5("vqsv5-db-0", "vqsv5-db-0")'), [3, 0]);
// HP traits: +15% / -15% of the unified max HP, truncated.
const baseHp = J('unifiedStats5(STAT5_BY_ID.get("vqsv5-db-0"), 20).hp');
assert.equal(J('vqsv5Combatant("vqsv5-db-0", 20, [3, 2]).max'), baseHp + Math.trunc(baseHp * 15 / 100));
assert.equal(J('vqsv5Combatant("vqsv5-db-0", 20, [3, 1]).max'), baseHp + Math.trunc(baseHp * -15 / 100));
assert.equal(J('vqsv5Combatant("vqsv5-db-0", 20, [3, 0]).max'), baseHp);
// Battle effects.
const fighter = traits => `Object.assign(vqsv5Combatant("vqsv5-db-0", 20, ${JSON.stringify(traits)}), { hp: 1 })`;
run(`globalThis.t1 = ${fighter([4, 0])}`);
assert.match(J('tickStatuses(t1, "A").join()'), /hồi \d+ HP nhờ/);
assert.equal(J('t1.hp'), 1 + Math.max(1, Math.trunc(J('t1.max') * 6 / 100)));
run(`globalThis.t2 = ${fighter([3, 0])}; t2.hp = t2.max; t2.debuffs = { 0: { turns: 3, value: 100, skillRow: 8 } }`);
const noTrait = J('(() => { const c = vqsv5Combatant("vqsv5-db-0", 20, []); c.debuffs = { 0: { turns: 3, value: 100, skillRow: 8 } }; const before = c.hp; tickStatuses(c, "B"); return before - c.hp; })()');
const withTrait = J('(() => { const before = t2.hp; tickStatuses(t2, "A"); return before - t2.hp; })()');
assert.equal(withTrait, noTrait + Math.trunc(noTrait * 20 / 100));
// Status chances: traits 11/12 shift paralysis (+-20), 7/8 shift confinement; clamped 0..100; unchanged without traits.
const lands = (traits, debuffId, chance, r) => { run(`Math.random = () => ${r}; globalThis.tg = Object.assign(vqsv5Combatant("vqsv5-db-0", 20, ${JSON.stringify(traits)}), { item: null })`); return J(`debuffLands(tg, ${chance}, ${debuffId})`); };
assert.equal(lands([11, 0], 10, 50, 0.65), true);    // 65 < 70
assert.equal(lands([12, 0], 10, 50, 0.35), false);   // 35 >= 30
assert.equal(lands([0, 0], 10, 50, 0.45), true);     // unchanged: 45 <= 50
assert.equal(lands([7, 0], 2, -1, 0.999), true);     // always (100%) stays clamped at 100
assert.equal(lands([8, 0], 2, 90, 0.75), false);     // 90 - 20 = 70, 75 >= 70
assert.equal(lands([8, 0], 2, 90, 0.65), true);
assert.equal(lands([8, 0], 10, 90, 0.75), true);     // trait 8 only guards confinement (debuff 2)
console.log('VQSV5 stats: formulas, types, skill slots, calibration, battle use and evolution passed.');
