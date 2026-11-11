// VQSV5 special skills 26-48, statuses and skill books (decoded in tools/decode_vqsv5_pet_stats.py) and their use in the unified battle.
const fs = require('fs'), path = require('path'), vm = require('vm'), assert = require('assert');
const html = fs.readFileSync(path.join(__dirname, '..', 'prototype', 'index.html'), 'utf8');
const code = [...html.matchAll(/<script>([\s\S]*?)<\/script>/g)].at(-1)[1].replace(/    loadData\(\)\.catch\(err => \{[^\n]+\}\);/, '');
const boot = require('./bootstrap_for_tests.cjs');
const S5 = boot.vqsv5Stats;

// ---- Data ----
const sp = id => S5.skills[id].special;
assert.deepEqual(sp(26), { kind: 'damage_loyalty', loyalty_percent: 25, chance: 100, blocked_by_passive: 40, evidence: sp(26).evidence });
assert.equal(sp(27).amount, 70); assert.equal(sp(28).amount, 200);
assert.equal(sp(29).attack_multiplier, 2); assert.equal(sp(30).status, 6);
assert.equal(sp(33).percent, 80); assert.equal(sp(38).chance, 60); assert.equal(sp(38).hp_percent, 50); assert(sp(38).source_quirk);
assert.equal(sp(48).chance, 30); assert.deepEqual([43, 44, 45, 46, 47].map(id => sp(id).type_id), [0, 1, 2, 3, 4]);
assert.equal(S5.skills.slice(26).filter(s => !s.special).length, 0);
assert.equal(S5.skill_books.length, 23);
assert.deepEqual(S5.skill_books.slice(0, 3).map(b => [b.item_id, b.skill_id, b.price_medals, b.trainer_rank]), [[35, 26, 7, 4], [36, 27, 1, 1], [37, 28, 3, 3]].map(r => r));
assert(S5.skill_books.every(b => b.item_id === b.skill_id + 9 && b.passive === (b.skill_id > 30)));
assert.deepEqual(Object.keys(S5.status_rules.statuses), ['0', '2', '3', '4', '5', '6']);
// Type skills carry [power, cost, chance, status]: plant -> 5 (Hủ Thực), metal -> 0, flying -> 4, beast -> 2, sea -> 3.
assert.deepEqual([0, 5, 10, 15, 20].map(id => S5.skills[id].param), [5, 0, 4, 2, 3]);

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
const J = js => { const text = JSON.stringify(run(js)); return text === undefined ? undefined : JSON.parse(text); };
run('assets.map = { id: "ryan-lakeside", width: 4, height: 4, objects: [], entities: { player: { x: 1, y: 1 }, elder: { x: 3, y: 3 }, encounter: { x: 2, y: 2 } } }; reset()');
const setup = (team = ['vqsv5-db-0'], level = 30, extra = '') => {
  teamIds.length = 0; teamIds.push(...team);
  const init = JSON.stringify(Object.fromEntries(team.map(id => [id, { level, exp: 0, traits: [3, 0] }])));
  run(`state.mode = "explore"; wallet = { gold: 5000, badges: 50, rewarded: {} }; for (const id of ITEMS5.keys()) mats5[id] = 0; progress = ${init}; Math.random = () => 0.5; ${extra}`);
};
const battle = (extra = '') => run(`state.battle = newBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 }); state.mode = "battle"; state.battle.enemy.stats.speed = 0; state.battle.enemy.skills = []; state.battle.terrain = null; ${extra}`);

// Skill books are sold for badges (medals in the source); learning fills the two special slots, a full set asks which to replace.
setup(['vqsv5-db-0'], 30, 'wallet.badges = 20');
assert.equal(J('catalogById.get("mat5-36").price'), 1); assert.equal(J('catalogById.get("mat5-36").currency'), 1);
assert.equal(J('buyItem("mat5-36", 2, false).total'), 2); assert.equal(run('wallet.badges'), 18); assert.equal(run('mats5[36]'), 2);
run('mats5[37] = 1; mats5[35] = 1');
let r = J('window.VQSVSpecials.learn("vqsv5-db-0", "vqsv5-db-0", 27)'); assert.equal(r.ok, true); assert.deepEqual(r.slots, [27, -1]); assert.equal(run('mats5[36]'), 1);
assert.equal(J('window.VQSVSpecials.learn("vqsv5-db-0", "vqsv5-db-0", 27)').ok, false);          // already known
assert.equal(J('window.VQSVSpecials.learn("vqsv5-db-0", "vqsv5-db-0", 28)').ok, true);
r = J('window.VQSVSpecials.learn("vqsv5-db-0", "vqsv5-db-0", 26)'); assert.equal(r.ok, false); assert.equal(r.needsReplace, true); assert.equal(run('mats5[35]'), 1);
r = J('window.VQSVSpecials.learn("vqsv5-db-0", "vqsv5-db-0", 26, 1)'); assert.equal(r.ok, true); assert.deepEqual(r.slots, [27, 26]); assert.match(r.message, /thay Trị liệu chuyên nghiệp/);
assert.deepEqual(J('window.VQSVSpecials.forget("vqsv5-db-0", "vqsv5-db-0", 0).slots'), [-1, 26]);
assert.deepEqual(J('progress["vqsv5-db-0"].passives'), [-1, 26]);
assert.deepEqual(J('passivesOf5("vqsv5-db-0")'), [-1, 26]);
run('mats5[36] = 0');
assert.equal(J('window.VQSVSpecials.learn("vqsv5-db-0", "vqsv5-db-0", 27)').ok, false);          // no book left
run('state.mode = "battle"'); assert.equal(J('window.VQSVSpecials.learn("vqsv5-db-0", "vqsv5-db-0", 27)').ok, false); run('state.mode = "explore"');
// Evolution keeps the special slots.
setup(['vqsv5-db-0'], 15, 'mats5[18] = 3; progress["vqsv5-db-0"].passives = [33, 48]');
assert.equal(J('evolvePet5("vqsv5-db-0", "vqsv5-db-0")').ok, true); assert.deepEqual(J('progress["vqsv5-db-1"].passives'), [33, 48]);

// Skills 26-30 become moves; passives do not.
let c = J('vqsv5Combatant("vqsv5-db-0", 30, [], [27, 29])');
assert.deepEqual(c.skills.slice(-2).map(s => [s.name, s.noDamage, s.max]), [['Trị liệu sơ cấp', true, 12], ['Tử Vong Chi Xúc', true, 1]]);
c = J('vqsv5Combatant("vqsv5-db-0", 30, [], [26, 30])'); assert.deepEqual(c.skills.slice(-2).map(s => [s.name, s.power]), [['Tàn quân', 120], ['Kỹ năng Phong Bế', 53]]);
assert.equal(J('vqsv5Combatant("vqsv5-db-0", 30, [], [33, 48]).skills.length'), 4);

// Statuses (all of them need a hit by a VQSV5 skill; status chance = the skill's chance, shifted by traits).
setup(['vqsv5-db-0']);
battle();
run('state.battle.pendingUntil = 0; globalThis.A = vqsv5Combatant("vqsv5-db-0", 30, [], []); globalThis.T = state.battle.enemy; T.hp = T.max = 1000; T.skills = [];');
const tryStatus = (id, chance, r, traits = []) => { run(`Math.random = () => ${r}; T.v5status = null; T.traits = ${JSON.stringify(traits)}; T.item = null; T.hp = 1000`); return J(`status5Lands(T, ${id}, ${chance})`); };
assert.equal(tryStatus(5, 24, 0.23), true); assert.equal(tryStatus(5, 24, 0.25), false);
assert.equal(tryStatus(5, 24, 0.43, [11]), true);      // trait 11: +20
assert.equal(tryStatus(2, 24, 0.05, [12]), false);    // trait 12: -20 -> 4%
assert.equal(tryStatus(0, 24, 0.43, [5]), true); assert.equal(tryStatus(4, 24, 0.43, [8]), false); assert.equal(tryStatus(4, 24, 0.43, [7]), true);
assert.equal(tryStatus(3, 24, 0.43, [7, 11]), false);   // Mị Hoặc is not shifted by any trait
run('T.v5status = { id: 2, turns: 3, h: 0 }; Math.random = () => 0'); assert.equal(J('status5Lands(T, 5, 100)'), false);   // one status at a time
// Turn lengths and the Hủ Thực damage seed h = skill id + 1 (+3 with skill 31).
run('T.v5status = null; setStatus5(A, T, 2, 16)'); assert.equal(run('T.v5status.turns'), 4);
run('A.passives = [32, -1]; T.v5status = null; setStatus5(A, T, 2, 16)'); assert.equal(run('T.v5status.turns'), 5);
run('T.v5status = null; setStatus5(A, T, 0, 5)'); assert.equal(run('T.v5status.turns'), 2);
run('A.passives = []; T.v5status = null; setStatus5(A, T, 5, 2)'); assert.equal(run('T.v5status.h'), 3);
run('A.passives = [31, -1]; T.v5status = null; setStatus5(A, T, 5, 2)'); assert.equal(run('T.v5status.h'), 5);
// Rounds: Hủ Thực loses h*h + 4, Tê Liệt 10% of the current HP, trait 3 adds 20%; the status expires after its turns.
run('T.traits = []; T.hp = 1000; T.v5status = { id: 5, turns: 2, h: 3 }'); assert.match(J('tickStatuses(T, "T").join()'), /mất 13 HP vì Hủ Thực/); assert.equal(run('T.hp'), 987);
assert.match(J('tickStatuses(T, "T").join()'), /hết Hủ Thực/); assert.equal(run('T.v5status'), null);
run('T.hp = 1000; T.v5status = { id: 2, turns: 4, h: 0 }'); J('tickStatuses(T, "T")'); assert.equal(run('T.hp'), 900);
run('T.hp = 1000; T.traits = [3, 0]; T.v5status = { id: 2, turns: 4, h: 0 }'); J('tickStatuses(T, "T")'); assert.equal(run('T.hp'), 880);    // 100 + 20%
// Thạch Hóa: cannot act and takes 30% less; Phong Bế: skills above tier 3 are refused; Giam Cầm / Mị Hoặc stop fleeing and switching.
run('T.traits = []; T.hp = 1000; T.v5status = { id: 0, turns: 2, h: 0 }; globalThis.moved = performMove(T, A, { name: "x", power: 100 }, "T")');
assert.match(run('moved'), /đang Thạch Hóa, không thể hành động/);
run('T.v5status = null; A.passives = []'); const plain = J('adjustDamage5(A, T, 100)'); run('T.v5status = { id: 0, turns: 2, h: 0 }'); assert.equal(J('adjustDamage5(A, T, 100)'), Math.trunc(plain * 70 / 100));
run('T.v5status = { id: 6, turns: 4, h: 0 }; T.v5 = false; T.skills = [{ name: "a", power: 100, uses: 3 }, { name: "b", power: 100, uses: 3 }, { name: "c", power: 100, uses: 3 }, { name: "d", power: 100, uses: 3 }]');
assert.deepEqual(J('T.skills.map(s => skillTier5(T, s))'), [1, 2, 3, 4]);
assert.match(run('performMove(T, A, T.skills[3], "T")'), /bị Phong Bế, không dùng được d/);
assert.equal(run('T.skills[3].uses'), 3);
assert.deepEqual(J('[0, 5, 14, 19, 24, 25].map(id => skillTier5(A, { v5Id: id }))'), [1, 1, 5, 5, 5, 0]);

// Hits by type skills apply their status; skill 30 seals; skill 26 lowers loyalty unless the target knows 40.
setup(['vqsv5-db-0'], 35);
battle('state.battle.enemy.stats.speed = 0');
run('state.battle.pendingUntil = 0; globalThis.A = vqsv5Combatant("vqsv5-db-0", 35, [], [26, 30]); globalThis.T = state.battle.enemy; T.hp = T.max = 100000; T.v5status = null; T.loyalty = undefined; Math.random = () => 0');
run('globalThis.moved = performMove(A, T, A.skills[0], "A")');           // Triền Nhiễu: Hủ Thực, chance 4%, random 0 -> lands
assert.match(run('moved'), /Hủ Thực/); assert.equal(run('T.v5status.id'), 5); assert.equal(run('T.v5status.h'), 1);
run('T.v5status = null; globalThis.moved = performMove(A, T, A.skills.find(s => s.v5Id === 30), "A")');
assert.equal(run('T.v5status.id'), 6);
run('T.v5status = null; T.loyalty = 100; globalThis.moved = performMove(A, T, A.skills.find(s => s.v5Id === 26), "A")');
assert.equal(run('T.loyalty'), 75); assert.match(run('moved'), /Độ trung thành của .* giảm 25/);
run('T.passives = [40, -1]; performMove(A, T, A.skills.find(s => s.v5Id === 26), "A")'); assert.equal(run('T.loyalty'), 75);   // skill 40: loyalty cannot be lowered
// Loyalty: +20% damage at 120, -20% at 70, -40% at 50; below 41 the pet disobeys 25% of the time.
assert.deepEqual(J('[120, 100, 99, 80, 70, 50, 20].map(loyaltyBonus)'), [20, 0, 0, 0, -20, -40, -60]);
run('Math.random = () => 0.1; T.loyalty = 30; globalThis.moved = performMove(T, A, { name: "x", power: 100 }, "T")'); assert.match(run('moved'), /không nghe lệnh/);
run('Math.random = () => 0.9; globalThis.moved = performMove(T, A, { name: "x", power: 100 }, "T")'); assert.doesNotMatch(run('moved'), /không nghe lệnh/);

// Move 27/28 heal (scaled by the calibration), 29 spends all energy, passive 33/48 reduce damage, 38 revives, 36/37 add experience.
setup(['vqsv5-db-0'], 30);
battle('state.battle.pendingUntil = 0');
run('globalThis.A = vqsv5Combatant("vqsv5-db-0", 30, [], [27, 29]); A.hp = 1; globalThis.T = state.battle.enemy; T.hp = T.max = 100000; T.v5status = null');
run('globalThis.moved = performMove(A, T, A.skills.find(s => s.v5Id === 27), "A")');
assert.equal(run('A.hp'), Math.min(run('A.max'), 1 + Math.max(1, Math.round(70 * J('calibration(30)').hp))));
const attack = J('effStats(A).attack'), spLeft = J('A.skills.filter(s => s.v5Id !== 29).reduce((n, s) => n + s.uses * STATS5.skills[s.v5Id].sp_cost, 0)');
run('globalThis.moved = performMove(A, T, A.skills.find(s => s.v5Id === 29), "A")');
assert.equal(100000 - run('T.hp'), Math.trunc(attack * 2 + spLeft * J('calibration(30)').attack)); assert(J('A.skills.every(s => s.uses === 0)')); assert.match(run('moved'), /tiêu hao toàn bộ năng lượng/);
run('A.passives = []; T.passives = []; T.typeId = null; Math.random = () => 0.99'); const base = J('adjustDamage5(A, T, 100)');
run('T.passives = [33, -1]; T.typeId = 1'); assert.equal(J('adjustDamage5(A, T, 100)'), Math.trunc(base * 80 / 100));
run('T.passives = [48, -1]; T.typeId = null; Math.random = () => 0.1'); assert.equal(J('adjustDamage5(A, T, 100)'), Math.trunc(base / 2));
run('Math.random = () => 0.9'); assert.equal(J('adjustDamage5(A, T, 100)'), base);
// Terrain: a typed VQSV5 pet off its field deals 20% less unless it knows 43 + its type; maps without a field type have no penalty.
run('state.battle.terrain = 2; A.passives = []; T.passives = []; T.typeId = null; Math.random = () => 0.9');
assert.equal(J('adjustDamage5(A, T, 100)'), Math.trunc(base * 80 / 100));
run('A.passives = [43, -1]'); assert.equal(J('adjustDamage5(A, T, 100)'), base);
run('state.battle.terrain = null; A.passives = []'); assert.equal(J('adjustDamage5(A, T, 100)'), base);
assert.equal(J('currentTerrain5()'), 3 + 1);   // ryan-lakeside is a Hải Dương field
// Revive: 60% (code value, not the 70% on the label), once per battle, 50% of max HP.
run('A.passives = [38, -1]; A.hp = 0; A.revived = false; Math.random = () => 0.59'); assert.match(run('reviveCheck5(A)'), /hồi sinh/); assert.equal(run('A.hp'), Math.trunc(run('A.max') / 2));
run('A.hp = 0; Math.random = () => 0'); assert.equal(run('reviveCheck5(A)'), ''); assert.equal(run('A.hp'), 0);        // already used
run('A.hp = 0; A.revived = false; Math.random = () => 0.6'); assert.equal(run('reviveCheck5(A)'), ''); assert.equal(run('A.hp'), 0);
// Experience: 36 -> x2, 37 -> x3, both -> x4.
const exp = passives => { setup(['vqsv5-db-0'], 30, `progress["vqsv5-db-0"].passives = ${JSON.stringify(passives)}`); battle('state.battle.pendingUntil = 0; switchPet(1); state.battle.enemy.hp = 0'); run('awardExperience(1000)'); return Number(run('state.battle.message').match(/vqsv5-db-0 \+(\d+) KN/)[1]); };
const e0 = exp([-1, -1]);
assert.equal(exp([36, -1]), e0 * 2); assert.equal(exp([37, -1]), e0 * 3); assert.equal(exp([36, 37]), e0 * 4);
// Fleeing, switching and items under Giam Cầm / Mị Hoặc, with skill 42 overriding fleeing.
setup(['vqsv5-db-0', 'vqsv5-db-4'], 30);
battle('switchPet(1); state.battle.pendingUntil = 0');
run('activePet().v5status = { id: 4, turns: 3, h: 0 }; state.battle.message = ""; fleeBattle()'); assert.match(run('state.battle.message'), /đang bị Giam Cầm, không thể chạy trốn/); assert.equal(run('state.mode'), 'battle');
run('state.battle.message = ""; switchPet(0)'); assert.match(run('state.battle.message'), /Giam Cầm, không thể đổi pet/);
run('activePet().v5status = { id: 3, turns: 3, h: 0 }; state.battle.message = ""; fleeBattle()'); assert.match(run('state.battle.message'), /Mị Hoặc, không thể chạy trốn/);
run('activePet().passives = [42, -1]; state.battle.message = ""; fleeBattle()'); assert.notEqual(run('state.mode'), 'battle');
// Mị Hoặc makes a catch easier (same factor as the VQSV6 status that does so).
setup(['vqsv5-db-0']); battle(); run('globalThis.E = state.battle.enemy; E.hp = E.max');
const rate = J('captureRate(1, E)'); run('E.v5status = { id: 3, turns: 4, h: 0 }'); assert(J('captureRate(1, E)') >= rate);
assert.match(run('statusLabels(E).join()'), /▼Mị Hoặc 4/);
// Skill 39 rolls traits in the lucky mode.
setup(['vqsv5-db-0'], 20, 'progress["vqsv5-db-0"].passives = [39, -1]; delete progress["vqsv5-db-0"].traits; Math.random = () => 0.5'); assert.equal(run('teamLucky5()'), true);
battle(); assert.deepEqual(J('progress["vqsv5-db-0"].traits'), [4, 2]);       // rand(100) = 50 < 70 for both slots
console.log('VQSV5 specials: decoded skills, books, statuses, loyalty, passives and battle rules passed.');
