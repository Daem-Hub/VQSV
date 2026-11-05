// Wild encounters (petArea.mid) and the VQSV6 capture formula (game/a.java m()).
const fs = require('fs');
const vm = require('vm');
const assert = require('assert');
const path = require('path');
const html = fs.readFileSync(path.join(__dirname, '..', 'prototype', 'index.html'), 'utf8');
const code = [...html.matchAll(/<script>([\s\S]*?)<\/script>/g)].at(-1)[1].replace(/    loadData\(\)\.catch\(err => \{[^\n]+\}\);/, '');
const elements = {};
function element(id) { return elements[id] ||= { textContent: '', hidden: true, open: false, listeners: {}, addEventListener(event, fn) { this.listeners[event] = fn; }, getContext() { return new Proxy({ createLinearGradient: () => ({ addColorStop() {} }), measureText: t => ({ width: t.length * 6 }) }, { get: (t, k) => k in t ? t[k] : () => {}, set: () => true }); }, replaceChildren() { this.children = []; }, append(...c) { (this.children ||= []).push(...c); } }; }
const store = new Map();
const localStorage = { getItem: k => store.get(k) ?? null, setItem: (k, v) => store.set(k, v) };
const catches = [];
const teamIds = [], replaced = [];
const VQSVPetTeam = { ids: () => teamIds, replaceSpecies: (a, b) => { replaced.push([a, b]); const i = teamIds.indexOf(a); if (i >= 0) teamIds[i] = b; }, caughtBest: () => null, info: id => ({ id, kind: "vqsv6", name: id, element: null, sequences: [], image: `assets/pets/vqsv6/${id}.png` }), recordCatch: (id, level, quality) => { catches.push({ id, level, quality }); return { addedToTeam: true }; } };
let clock = 1000;
const ctx = vm.createContext({ document: { getElementById: element, createElement: () => ({ append() {}, addEventListener() {}, setAttribute() {} }) }, window: { addEventListener() {}, VQSVBootstrap: require('./bootstrap_for_tests.cjs'), VQSVPetTeam }, Image: class {}, Element: class {}, performance: { now: () => clock }, console, localStorage });
vm.runInContext(code, ctx);
const run = js => vm.runInContext(js, ctx);
const maps = require('./bootstrap_for_tests.cjs').maps;
ctx.maps = maps;
run('assets.maps = maps; assets.map = maps["ryan-phong-loi-forest"]; reset(); assets.map = maps["ryan-phong-loi-forest"]');

// Every roll on the forest maps comes from source petArea rows 1-1 .. 1-4 (levels 5-7, land pets).
const allowed = new Set(['vqsv6-db-33', 'vqsv6-db-86', 'vqsv6-db-11', 'vqsv6-db-14', 'vqsv6-db-78']);
for (let i = 0; i < 200; i++) {
  const forest = run('rollWild("ryan-phong-loi-forest")'), stream = run('rollWild("ryan-forest-stream")');
  for (const roll of [forest, stream]) {
    assert(allowed.has(roll.petId), roll.petId);
    assert(roll.level >= 5 && roll.level <= 7);
    assert(roll.quality >= 1 && roll.quality <= 2);
  }
  assert.equal(forest.level, 5);
}
assert.equal(run('rollWild("slice01-border-village")'), null);

// No wild battles before the elder opens the route, and none inside the grace steps.
run('state.spokeToElder = false; wildSteps = 0; Math.random = () => 0; for (let i = 0; i < 20; i++) maybeEncounter()');
assert.equal(run('state.mode'), 'explore');
run('state.spokeToElder = true; for (let i = 0; i < 6; i++) maybeEncounter()');
assert.equal(run('state.mode'), 'explore');
run('maybeEncounter()');
assert.equal(run('state.mode'), 'battle');
assert.equal(run('!!state.battle.wild'), true);

// Capture rate: Độc Ba Lợi quality 1 (x110%) at full HP = 20 -> 22; Cao cấp cầu x140% -> 30; at <=15% HP: 85 -> 93 / 100.
run('state.battle = newBattle({ petId: "vqsv6-db-86", level: 5, quality: 1 }); state.mode = "battle"');
assert.equal(run('captureRate(1, state.battle.enemy)'), 22);
assert.equal(run('captureRate(2, state.battle.enemy)'), 30);
run('state.battle.enemy.hp = Math.floor(state.battle.enemy.max / 2)');
assert.equal(run('captureRate(1, state.battle.enemy)'), 49);
run('state.battle.enemy.hp = 5');
assert.equal(run('captureRate(1, state.battle.enemy)'), 93);
assert.equal(run('captureRate(2, state.battle.enemy)'), 100);
assert.equal(run('captureRate(0, state.battle.enemy)'), 100);

// A failed throw costs the ball and a turn (enemy counters); a successful one records the catch.
run('bag[1] = 2; bag[2] = 0; state.battle.enemy.hp = state.battle.enemy.max; Math.random = () => 0.5; attemptCapture(1)');
assert.equal(run('bag[1]'), 1);
assert.equal(run('state.battle.captured'), false);
const hpBefore = run('activePet().hp');
clock += 1200; run('updateBattle(performance.now())');
assert.equal(run('state.mode'), 'battle');
assert(run('activePet().hp') < hpBefore);
run('Math.random = () => 0.1; attemptCapture(1)');
assert.equal(run('bag[1]'), 0);
clock += 1600; run('updateBattle(performance.now())');
assert.equal(run('state.mode'), 'explore');
assert.deepEqual(catches, [{ id: 'vqsv6-db-86', level: 5, quality: 1 }]);
assert.match(element('status').textContent, /Đã bắt Độc Ba Lợi/);
assert.equal(JSON.parse(store.get('vqsv-unified-bag-v1'))[1], 0);

// Story battle refuses capture; returning to the village restocks balls.
run('state.battle = newBattle(); state.mode = "battle"; toggleBallMenu(true)');
assert.match(run('state.battle.message'), /không cho bắt sủng vật/);
assert.equal(run('topUpBag()'), true);
assert.equal(run('bag[1]'), 5);
// Experience (game/a.java h, game/i.java A): Độc Ba Lợi L5 quality 1 -> (50 + 50) * 10 / 10 + 400 = 500.
run('progress = {}');
const reward = (count, level) => run(`expReward({ level: 5, quality: 1 }, ${count}, ${level})`);
assert.equal(reward(1, 5), 500);            // equal level: 100%
assert.equal(reward(1, 3), 525);            // lower than enemy: 105%
assert.equal(reward(1, 7), 400);            // +2 levels: 80%
assert.equal(reward(1, 20), 25);            // +6 or more: 5%
assert.equal(reward(2, 5), 300);            // 500 / 2 * 12 / 10: shared with a participant bonus
assert.equal(run('expToReach(6)'), 340);
// One win at level 5 gives 500 exp: level 6 (340) leaves 160, short of level 7 (535).
run('state.battle = newBattle({ petId: "vqsv6-db-86", level: 5, quality: 1 }); state.mode = "battle"; state.battle.enemy.hp = 0; state.battle.pendingUntil = 1; updateBattle(2)');
assert.equal(run('state.mode'), 'battle');
assert.match(run('state.battle.message'), /Lôi Miêu \+500 KN, lên cấp 6!/);
assert.deepEqual(JSON.parse(store.get('vqsv-unified-pet-progress-v1'))['loi-mieu'], { level: 6, exp: 160 });
clock += 2300; run('updateBattle(performance.now())');
assert.equal(run('state.mode'), 'explore');
// The next battle builds Lôi Miêu at level 6 with stats from the decoded growth formula.
run('state.battle = newBattle()');
assert.equal(run('activePet().level'), 6);
assert.equal(run('activePet().max'), run('statsAt(STAT_BY_ID.get("vqsv6-db-78"), 6).hp'));
// Large gains roll over several levels and stop at the unified level-70 cap (source game: 50).
run('gainExp(activePet(), 10000000)');
assert.equal(run('activePet().level'), 70);
assert.equal(run('activePet().exp'), 0);
assert.equal(run('activePet().max'), run('statsAt(STAT_BY_ID.get("vqsv6-db-78"), 70).hp'));
// Exactly enough exp to go 50 -> 51 still levels up past the source cap.
run('progress = {}; state.battle = newBattle(); progress["loi-mieu"] = { level: 50, exp: 0 }; state.battle = newBattle()');
assert.equal(run('gainExp(activePet(), expToReach(51))'), 1);
assert.equal(run('activePet().level'), 51);
assert.equal(run('gainExp(activePet(), 999)'), 0);
// A catch stores the individual's level/quality as the species' progress unless a stronger one exists.
run('progress = {}; recordWildCatch({ petId: "vqsv6-db-78", level: 7, quality: 2 }); recordWildCatch({ petId: "vqsv6-db-78", level: 6, quality: 2 })');
assert.deepEqual(JSON.parse(JSON.stringify(run('progress["vqsv6-db-78"]'))), { level: 7, exp: 0, quality: 2 });
// Skill learning (game/i.java G()/H(), game/k.java aq()): slots = min(5, level/10 + 1).
assert.deepEqual([5, 9, 10, 19, 20, 40, 70].map(l => run(`skillSlots(${l})`)), [1, 1, 2, 2, 3, 5, 5]);
// Điện Miêu uses skill curve row 2 = [0, 0, 1, 2, 3]; Điện skills are rows 40-49.
const learn = (level, known) => JSON.parse(JSON.stringify(run(`learnableSkills(STAT_BY_ID.get("vqsv6-db-78"), ${level}, ${JSON.stringify(known)})`)));
assert.deepEqual(learn(5, []), [40]);
assert.deepEqual(learn(5, [40]), []);
assert.deepEqual(learn(10, [40]), [41, 42]);
assert.deepEqual(learn(20, [40, 41]), [42, 43, 44, 45]);
assert.deepEqual(learn(30, [40]), [41, 42, 43, 44, 45, 46, 47]);
assert.deepEqual(learn(40, [40]), [41, 42, 43, 44, 45, 46, 47, 48, 49]);
// Wild pets: only the first element skill up to level 9, then random picks up to the slot count.
assert.deepEqual(JSON.parse(JSON.stringify(run('wildSkillRows(STAT_BY_ID.get("vqsv6-db-78"), 7)'))), [40]);
for (let i = 0; i < 30; i++) {
  const rows = JSON.parse(JSON.stringify(run('wildSkillRows(STAT_BY_ID.get("vqsv6-db-78"), 25)')));
  assert.equal(rows.length, 3); assert.equal(rows[0], 40); assert.equal(new Set(rows).size, 3);
  assert(rows.every(r => r >= 40 && r <= 45));
}
// A level-10 Lôi Miêu with one skill learns Lôi thiểm (row 41, 90%) and uses it from slot 2.
run('progress = { "loi-mieu": { level: 10, exp: 0 } }; state.battle = newBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 }); state.mode = "battle"');
assert.equal(run('activePet().skills.length'), 1);
run('learnSkill(activePet(), 41)');
assert.deepEqual(JSON.parse(store.get('vqsv-unified-pet-progress-v1'))['loi-mieu'].skills, [40, 41]);
run('learnSkill(activePet(), 42)'); // no free slot at level 10: ignored
assert.equal(run('activePet().skills.length'), 2);
run('Math.random = () => 0.99; state.battle.enemy.stats.speed = 0; action(false, "skill", 1)');
assert.match(run('state.battle.message'), /Lôi Miêu dùng Lôi thiểm/);
assert.equal(run('activePet().skills[1].uses'), 44);
assert.equal(run('state.battle.skill'), 'skill');
// Saved skills come back in the next battle; the power-0 skill Đoạt mệnh cao áp now applies its buff without damage.
run('progress["loi-mieu"].skills = [40, 44]; state.battle = newBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 }); state.mode = "battle"');
assert.equal(run('activePet().skills[1].name'), 'Đoạt mệnh cao áp');
const enemyFull = run('state.battle.enemy.hp');
run('action(false, "skill", 1)');
assert.match(run('state.battle.message'), /Lôi Miêu dùng Đoạt mệnh cao áp\. Lôi Miêu được Điện áp\./);
assert.equal(run('state.battle.enemy.hp'), enemyFull);

// ---- Status effects (tables 6/7; game/i.java a(byte,int,int), b(i), q(int)) ----
const fresh = () => run('progress = {}; state.battle = newBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 }); state.mode = "battle"; state.battle.enemy.stats.speed = 0; state.battle.enemy.skills = []');
const tick = () => { clock += 1200; run('updateBattle(performance.now())'); };
// Điện áp (buff 8): +30% damage and double skill-use cost for 4 rounds.
// Random 0.99: no crit, -1 jitter. Lôi Miêu L5 (atk 26) vs Khốc Tị Mộc Linh: plain = (26 - def) - 1; with Điện áp the
// pre-jitter damage gains 30% (game/i.java: damage += damage * w[8][1] / 100).
fresh(); run('Math.random = () => 0.99; action(false, "skill", 0)');
const plain = run('state.battle.enemy.max - state.battle.enemy.hp');
const raw = plain + 1;
fresh(); run('Math.random = () => 0.99; activePet().buffs[8] = { turns: 4, value: 30 }; action(false, "skill", 0)');
assert.equal(run('state.battle.enemy.max - state.battle.enemy.hp'), raw + Math.trunc(raw * 30 / 100) - 1);
fresh(); run('Math.random = () => 0.99; activePet().buffs[8] = { turns: 4, value: 30 }; action(false, "skill", 0)');
assert.equal(run('activePet().skills[0].uses'), 43);
// Lôi thiểm (row 41): 10% chance to apply Tê Liệt; random 0.05 applies it, 0.5 does not.
fresh(); run('activePet().skills.push(sourceSkill(41)); Math.random = () => 0.05; action(false, "skill", 1)');
assert.equal(run('!!state.battle.enemy.debuffs[10]'), true);
assert.equal(run('state.battle.enemy.debuffs[10].turns'), 4);
fresh(); run('activePet().skills.push(sourceSkill(41)); Math.random = () => 0.5; action(false, "skill", 1)');
assert.equal(run('!!state.battle.enemy.debuffs[10]'), false);
// Dương viêm (row 1, burn = Gieo Hạt): always applies; each round costs base damage / col8 (4), for 3 rounds.
fresh(); run('Math.random = () => 0.99; activePet().skills.push(sourceSkill(1)); action(false, "skill", 1)');
const burn = run('state.battle.enemy.debuffs[0]');
assert.equal(burn.turns, 3);
const hpAfterHit = run('state.battle.enemy.hp');
tick();
assert.equal(run('state.battle.enemy.hp'), hpAfterHit - Math.max(1, Math.trunc(burn.value / 4)));
assert.equal(run('state.battle.enemy.debuffs[0].turns'), 2);
assert.match(run('state.battle.message'), /mất \d+ HP vì Gieo Hạt/);
// Statuses expire after their duration.
for (let i = 0; i < 2; i++) { run('state.battle.pendingUntil = 1'); tick(); }
assert.equal(run('!!state.battle.enemy.debuffs[0]'), false);
// Chú oán (row 51): defense -20% while active.
fresh(); const def = run('effStats(state.battle.enemy).defense');
run('applyDebuff(state.battle.enemy, 7, 20, 0, 51)');
assert.equal(run('effStats(state.battle.enemy).defense'), def - Math.trunc(run('state.battle.enemy.stats.defense') * 20 / 100));
// Mê Muội (TRIAL: skip the turn); Quấn Quanh blocks items, switching and fleeing.
fresh(); run('activePet().debuffs[1] = { turns: 2, value: 0 }; action(false, "skill", 0)');
assert.match(run('state.battle.message'), /không thể hành động/);
assert.equal(run('state.battle.enemy.hp'), run('state.battle.enemy.max'));
fresh(); run('activePet().debuffs[2] = { turns: 3, value: 0 }; activePet().hp = 10; usePotion()');
assert.match(run('state.battle.message'), /không thể dùng đạo cụ/);
run('fleeBattle()'); assert.equal(run('state.mode'), 'battle');
// At most 3 statuses per side; Thạch Phu (buff 14) clears debuffs and blocks new ones.
fresh(); run('for (const id of [0, 5, 6]) applyDebuff(state.battle.enemy, id, 10, 10, 1); applyDebuff(state.battle.enemy, 7, 20, 0, 51)');
assert.equal(run('Object.keys(state.battle.enemy.debuffs).length'), 3);
run('applyBuff(state.battle.enemy, 14, -1); applyDebuff(state.battle.enemy, 7, 20, 0, 51)');
assert.equal(run('Object.keys(state.battle.enemy.debuffs).length'), 0);
// Capture status factor (game/a.java m()): a target with Man Lực (buff 10) is x1.2 easier.
run('state.battle.enemy.hp = state.battle.enemy.max');
const base1 = run('captureRate(1, state.battle.enemy)');
run('state.battle.enemy.buffs[10] = { turns: 2, value: 5 }');
assert.equal(run('captureRate(1, state.battle.enemy)'), Math.trunc(base1 * 12 / 10));
// ---- Evolution (game/k.java bp(), game/i.java K()): level gate + material, keeps level/exp/skills, quality rule ----
const evo = (key, id) => JSON.parse(JSON.stringify(run(`evolutionCheck(${JSON.stringify(key)}, ${JSON.stringify(id)})`)));
const fresh2 = () => run('progress = {}; stones = { 12: 1, 13: 0, 14: 0, 15: 1, 16: 0 }; state.mode = "explore"; state.battle = newBattle()');
// Lôi Miêu (Điện Miêu db-78 -> Thiểm Điện Miêu db-79): level 12 and 1 Tinh Nguyên Thạch.
fresh2();
let check = evo('loi-mieu', 'vqsv6-db-78');
assert.equal(check.ok, false);
assert.match(check.reason, /cần cấp 12 \(đang cấp 5\)/);
run('progress["loi-mieu"] = { level: 12, exp: 77, skills: [40, 41] }; stones[12] = 0');
check = evo('loi-mieu', 'vqsv6-db-78');
assert.match(check.reason, /thiếu Tinh Nguyên Thạch \(0\/1\)/);
run('stones[12] = 1; state.mode = "battle"');
assert.match(evo('loi-mieu', 'vqsv6-db-78').reason, /không thể trong trận/);
run('state.mode = "explore"');
let result = JSON.parse(JSON.stringify(run('evolvePet("loi-mieu", "vqsv6-db-78")')));
assert.equal(result.ok, true);
assert.match(result.message, /^Lôi Miêu tiến hóa thành Thiểm Điện Miêu! HP \d+→\d+/);
assert.equal(run('stones[12]'), 0);
assert.equal(run('leadForm()'), 'vqsv6-db-79');
let saved = JSON.parse(store.get('vqsv-unified-pet-progress-v1'))['loi-mieu'];
assert.equal(saved.form, 'vqsv6-db-79'); assert.equal(saved.level, 12); assert.equal(saved.exp, 77);
assert.deepEqual(saved.skills, [40, 41]);
// Quality: Điện Miêu default 2; the target's default quality applies only when it is higher.
assert.equal(saved.quality, Math.max(2, run('STAT_BY_ID.get("vqsv6-db-79").default_quality')));
// The next battle uses the evolved record's stats, keeps the name and carries skills.
run('state.battle = newBattle()');
assert.equal(run('activePet().name'), 'Lôi Miêu');
assert.equal(run('activePet().petId'), 'vqsv6-db-79');
assert.equal(run('activePet().max'), run('statsAt(STAT_BY_ID.get("vqsv6-db-79"), 12, activePet().quality).hp'));
assert.deepEqual(JSON.parse(JSON.stringify(run('activePet().skills.map(skill => skill.row)'))), [40, 41]);
assert.match(run('activePet().subtitle'), /^CẤP 12 · /);
assert.equal(run('activePet().image'), 'assets/pets/vqsv6/' + run('window.VQSVPetTeam.info("vqsv6-db-79").image').split('/').pop());
// A second evolution is checked against the new form's own data (Thiểm Điện Miêu has a further form needing level 30).
check = evo('loi-mieu', 'vqsv6-db-79');
assert.equal(check.ok === false, true);
// Reserve pet: Long bảo bối (db-16) -> Phệ Hỏa Thú (db-17) moves progress and the team slot.
fresh2(); teamIds.length = 0; teamIds.push('vqsv6-db-16'); replaced.length = 0;
run('progress["vqsv6-db-16"] = { level: 14, exp: 5, quality: 1, skills: [0, 1] }');
assert.equal(JSON.parse(JSON.stringify(run('window.VQSVEvolution.options("vqsv6-db-16")')))[0].ok, true);
result = JSON.parse(JSON.stringify(run('window.VQSVEvolution.evolve("vqsv6-db-16", "vqsv6-db-16")')));
assert.equal(result.ok, true);
assert.equal(run('progress["vqsv6-db-16"]'), undefined);
assert.equal(run('progress["vqsv6-db-17"].level'), 14);
assert.deepEqual(JSON.parse(JSON.stringify(run('progress["vqsv6-db-17"].skills'))), [0, 1]);
assert.deepEqual(replaced, [['vqsv6-db-16', 'vqsv6-db-17']]);
assert.deepEqual(teamIds, ['vqsv6-db-17']);
// Same species already owned blocks the evolution instead of overwriting a pet.
fresh2(); teamIds.length = 0; teamIds.push('vqsv6-db-16');
run('progress["vqsv6-db-16"] = { level: 14, exp: 0 }; progress["vqsv6-db-17"] = { level: 3, exp: 0 }');
assert.match(evo('vqsv6-db-16', 'vqsv6-db-16').reason, /đã có Phệ Hỏa Thú/);
// Mutation (dị hóa): level 5 gate with Hồn Tinh Thạch (Nhiệt Bạo Phong Tốc Khuyển db-21 -> db-22).
fresh2(); teamIds.length = 0; teamIds.push('vqsv6-db-21');
run('progress["vqsv6-db-21"] = { level: 5, exp: 0 }');
result = JSON.parse(JSON.stringify(run('evolvePet("vqsv6-db-21", "vqsv6-db-21")')));
assert.equal(result.ok, true); assert.match(result.message, /dị hóa thành/);
assert.equal(run('stones[15]'), 0);
// A pet without a source evolution reports it.
fresh2(); assert.match(evo('vqsv6-db-33', 'vqsv6-db-33').reason, /Chưa có dạng tiếp theo/);
// Stone drops (TRIAL table): Math.random = 0 grants every stone whose level gate is met.
fresh2(); run('Math.random = () => 0');
assert.deepEqual(JSON.parse(JSON.stringify(run('rollStoneDrops(5)'))), ['Tinh Nguyên Thạch', 'Hồn Tinh Thạch']);
assert.equal(run('stones[12]'), 2);
assert.equal(run('rollStoneDrops(45).length'), 5);
run('Math.random = () => 0.999');
assert.equal(run('rollStoneDrops(45).length'), 0);
assert.equal(JSON.parse(store.get('vqsv-unified-stones-v1'))[12], 3);
// A win over a wild pet reports drops in the status text.
run('Math.random = () => 0; state.battle = newBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 }); state.mode = "battle"; finishWild("win")');
assert.match(element('status').textContent, /nhặt được Tinh Nguyên Thạch, Hồn Tinh Thạch/);
assert.match(element('stoneStatus').textContent, /Tinh Nguyên Thạch ×\d+/);
console.log('Wild capture: source encounter pools, grace steps, capture formula, ball use, story restriction, experience, skill learning, status effects and evolution passed.');
