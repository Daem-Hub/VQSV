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

// Story battle refuses capture.
run('state.battle = newBattle(); state.mode = "battle"; toggleBallMenu(true)');
assert.match(run('state.battle.message'), /không cho bắt sủng vật/);
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
fresh(); run('activePet().debuffs[2] = { turns: 3, value: 0 }; activePet().hp = 10; bag[5] = 1; useBattleItem(5, 0)');
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
assert.equal(run('rollStoneDrops(45).length'), 6); // 5 VQSV6 stones + the one VQSV5 material that no treasure box holds (29)
run('Math.random = () => 0.999');
assert.equal(run('rollStoneDrops(45).length'), 0);
assert.equal(JSON.parse(store.get('vqsv-unified-stones-v1'))[12], 3);
// A win over a wild pet reports drops in the status text.
run('Math.random = () => 0; state.battle = newBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 }); state.mode = "battle"; finishWild("win")');
assert.match(element('status').textContent, /nhặt được Tinh Nguyên Thạch, Hồn Tinh Thạch/);
assert.match(element('stoneStatus').textContent, /Tinh Nguyên Thạch ×\d+/);
// ---- Shop and items (game/k.java a()/b(), game/j.java b(), game/i.java w()/x()) ----
const reset3 = () => { teamIds.length = 0; run('bag = { 1: 5, 2: 2, 4: 5, 11: 2 }; stones = { 12: 0, 13: 0, 14: 0, 15: 0, 16: 0 }; wallet = { gold: 2000, badges: 2, rewarded: {} }; progress = {}; repelSteps = 0; state.mode = "explore"; state.battle = newBattle()'); };
reset3();
// Source starter gifts: 2000 kim tiền, 2 huy hiệu, 5 Phong ấn cầu, 5 Bánh Sandwich, 2 Sinh mệnh thạch.
assert.equal(run('wallet.gold'), 2000); assert.equal(run('wallet.badges'), 2);
assert.equal(run('bag[1] + "," + bag[4] + "," + bag[11]'), '5,5,2');
// Buying: Tinh Nguyên Thạch = 1000 kim tiền; the second one is unaffordable.
let buy = JSON.parse(JSON.stringify(run('buyItem("stone-12", 1)')));
assert.equal(buy.ok, true); assert.equal(buy.total, 1000);
assert.equal(run('wallet.gold'), 1000); assert.equal(run('stones[12]'), 1);
buy = JSON.parse(JSON.stringify(run('buyItem("stone-12", 2)')));
assert.equal(buy.ok, false); assert.match(buy.message, /Kim tiền chưa đủ \(cần 2000, đang có 1000\)/);
assert.equal(run('stones[12]'), 1);
// Badge currency: Hồn Tinh Thạch = 2 huy hiệu.
assert.equal(JSON.parse(JSON.stringify(run('buyItem("stone-15", 1)'))).ok, true);
assert.equal(run('wallet.badges'), 0); assert.equal(run('stones[15]'), 1);
assert.match(run('buyItem("stone-13", 1).message'), /Huy hiệu chưa đủ/);
// Battle prices are doubled (Bánh Sandwich 100 -> 200); the village price is unchanged.
run('wallet.gold = 1000');
assert.equal(run('buyItem("item-4", 1, true).total'), 200);
assert.equal(run('buyItem("item-4", 1, false).total'), 100);
assert.equal(run('wallet.gold'), 700);
assert.equal(run('bag[4]'), 7);
// Stack limit and bad input.
run('bag[4] = 98; wallet.gold = 10000');
assert.match(run('buyItem("item-4", 2).message'), /tối đa 99/);
assert.equal(run('buyItem("item-999", 1).ok'), false);
assert.equal(run('buyItem("item-4", 0).ok'), false);
// Selling returns half the price in the item's currency, village only, table 4 items only.
reset3();
assert.equal(run('sellItem("item-4", 2).total'), 100);
assert.equal(run('wallet.gold'), 2100); assert.equal(run('bag[4]'), 3);
assert.equal(run('sellItem("item-4", 9).ok'), false);
run('stones[12] = 1'); assert.equal(run('sellItem("stone-12", 1).ok'), false);
run('state.mode = "battle"'); assert.match(run('sellItem("item-4", 1).message'), /Chỉ bán được đồ ở làng/);
// Wallet and bag are persisted.
assert.equal(JSON.parse(store.get('vqsv-unified-wallet-v1')).gold, 2100);
assert.equal(JSON.parse(store.get('vqsv-unified-bag-v1'))[4], 3);
// Battle items: Chocolate (full heal), Năng lượng Thạch (+25 uses), Khôi phục tề (HP and uses), Vạn năng dược, Sinh mệnh thạch (revive).
teamIds.push('vqsv6-db-61');
const duel = () => { reset3(); teamIds.push('vqsv6-db-61'); run('state.battle = newBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 }); state.mode = "battle"; state.battle.enemy.stats.speed = 0; state.battle.enemy.skills = []; Math.random = () => 0.99'); };
duel(); run('bag[5] = 1; activePet().hp = 10; useBattleItem(5, 0)');
assert.match(run('state.battle.message'), /Kael dùng Chocolate: Lôi Miêu hồi 105 HP\./);
assert.equal(run('bag[5]'), 0);
assert.equal(run('state.battle.turn'), 1);
duel(); run('bag[6] = 1; activePet().skills[0].uses = 30; useBattleItem(6, 0)');
assert.equal(run('activePet().skills[0].uses'), 45);
assert.match(run('state.battle.message'), /hồi 25 lần mỗi kỹ năng/);
duel(); run('bag[8] = 1; activePet().hp = 20; activePet().skills[0].uses = 10; useBattleItem(8, 0)');
assert.equal(run('activePet().skills[0].uses'), 30);
assert.match(run('state.battle.message'), /Khôi phục tề: Lôi Miêu hồi 95 HP và 20 lần mỗi kỹ năng/);
duel(); run('bag[10] = 1; useBattleItem(10, 0)');
assert.match(run('state.battle.message'), /Vạn năng dược: Lôi Miêu không có trạng thái bất lợi\./);
assert.equal(run('bag[10]'), 1);
run('activePet().debuffs[5] = { turns: 3, value: 10 }; useBattleItem(10, 0)');
assert.match(run('state.battle.message'), /được giải trừ trạng thái bất lợi/);
assert.equal(run('Object.keys(activePet().debuffs).length'), 0);
// Revive: Sinh mệnh thạch (row 11) = 50% max HP + 50 and +20 uses, only for fainted pets.
duel(); run('useBattleItem(11, 1)');
assert.match(run('state.battle.message'), /Sinh mệnh thạch: vqsv6-db-61 vẫn còn sức chiến đấu/);
assert.equal(run('bag[11]'), 2);
run('state.battle.team[1].hp = 0; state.battle.team[1].skills[0].uses = 0; useBattleItem(11, 1)');
assert.equal(run('state.battle.team[1].hp'), Math.trunc(run('state.battle.team[1].max') * 50 / 100) + 50);
assert.equal(run('state.battle.team[1].skills[0].uses'), 20);
assert.equal(run('bag[11]'), 1);
// A healing item never lands without effect on an unaffected target; a team of one cannot pick a missing target.
duel(); assert.equal(run('useBattleItem(4, 5)'), false);
assert.equal(run('bag[4]'), 5);
// Master ball (Tất Trung Cầu) always catches; Đại sư cầu only shows once owned.
duel(); run('bag[0] = 1; state.battle.enemy.hp = state.battle.enemy.max; attemptCapture(0)');
assert.equal(run('state.battle.captured'), true);
assert.equal(run('bag[0]'), 0);
// Repel (Tị quái hàn, row 13): 500 steps without wild encounters, only outside battle.
reset3(); run('bag[13] = 1; state.spokeToElder = true; assets.map = maps["ryan-phong-loi-forest"]; wildSteps = 100; Math.random = () => 0');
run('state.mode = "battle"'); assert.equal(run('useRepel()'), false); run('state.mode = "explore"');
assert.equal(run('useRepel()'), true);
assert.equal(run('repelSteps'), 500); assert.equal(run('bag[13]'), 0);
for (let i = 0; i < 10; i++) run('maybeEncounter()');
assert.equal(run('state.mode'), 'explore'); assert.equal(run('repelSteps'), 490);
run('repelSteps = 1; maybeEncounter()'); assert.equal(run('repelSteps'), 0);
assert.match(element('status').textContent, /Tị quái hàn đã hết tác dụng/);
run('maybeEncounter()'); assert.equal(run('state.mode'), 'battle');
// Battle rewards: gold on wild wins/catches (TRIAL 15 per level + up to 5 per level), one-time quest rewards.
reset3(); run('Math.random = () => 0; state.battle = newBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 }); state.mode = "battle"; finishWild("win")');
assert.equal(run('wallet.gold'), 2075);
assert.match(element('status').textContent, /\+75 kim tiền/);
assert.equal(run('grantOnce("herb", () => { wallet.gold += 300; })'), true);
assert.equal(run('grantOnce("herb", () => { wallet.gold += 300; })'), false);
assert.equal(run('wallet.gold'), 2375);
// ---- Skill management (design: the source game never removes skills) ----
const J = js => JSON.parse(JSON.stringify(run(js)));
reset3();
run('progress["loi-mieu"] = { level: 30, exp: 0, skills: [40, 41, 42, 43] }');
let opts = J('window.VQSVSkills.options("vqsv6-db-78")');
assert.equal(opts.length, 1);
assert.equal(opts[0].slots, 4); assert.equal(opts[0].full, true);
assert.deepEqual(opts[0].known.map(k => k.row), [40, 41, 42, 43]);
assert.deepEqual(opts[0].learnable.map(k => k.row), [44, 45, 46, 47]);
// Unowned species have nothing to manage.
assert.deepEqual(J('window.VQSVSkills.options("vqsv6-db-33")'), []);
// Full slots: learning needs a skill to replace, and the replacement keeps its slot.
let r = J('window.VQSVSkills.learn("loi-mieu", "vqsv6-db-78", 44)');
assert.equal(r.ok, false); assert.match(r.message, /Các ô kỹ năng đã đầy/);
r = J('window.VQSVSkills.learn("loi-mieu", "vqsv6-db-78", 44, 42)');
assert.equal(r.ok, true); assert.equal(r.message, 'Đã quên Nạp điện và học Đoạt mệnh cao áp.');
assert.deepEqual(JSON.parse(store.get('vqsv-unified-pet-progress-v1'))['loi-mieu'].skills, [40, 41, 44, 43]);
// Forgetting frees a slot; the forgotten skill can be learned again; the next learn uses the free slot.
r = J('window.VQSVSkills.forget("loi-mieu", "vqsv6-db-78", 41)');
assert.equal(r.ok, true); assert.equal(r.message, 'Đã quên Lôi thiểm.');
opts = J('window.VQSVSkills.options("vqsv6-db-78")');
assert.equal(opts[0].full, false);
assert.deepEqual(opts[0].learnable.map(k => k.row), [41, 42, 45, 46, 47]);
r = J('window.VQSVSkills.learn("loi-mieu", "vqsv6-db-78", 45)');
assert.equal(r.ok, true); assert.equal(r.message, 'Đã học Điện năng chuyển đổi.');
assert.deepEqual(JSON.parse(store.get('vqsv-unified-pet-progress-v1'))['loi-mieu'].skills, [40, 44, 43, 45]);
// The next battle uses the new skill set, with fresh use counts.
run('state.battle = newBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 })');
assert.deepEqual(J('activePet().skills.map(skill => skill.name)'), ['Điện giật', 'Đoạt mệnh cao áp', 'Sóng điện từ', 'Điện năng chuyển đổi']);
// Rules: only skills the pet could learn at its level, never the last skill, never unknown skills, never in battle.
r = J('window.VQSVSkills.learn("loi-mieu", "vqsv6-db-78", 49)');
assert.equal(r.ok, false); assert.match(r.message, /chưa thể học kỹ năng này/);
r = J('window.VQSVSkills.forget("loi-mieu", "vqsv6-db-78", 42)');
assert.equal(r.ok, false); assert.match(r.message, /không biết kỹ năng này/);
run('progress["loi-mieu"].skills = [40]');
r = J('window.VQSVSkills.forget("loi-mieu", "vqsv6-db-78", 40)');
assert.equal(r.ok, false); assert.match(r.message, /ít nhất một kỹ năng/);
run('progress["loi-mieu"].skills = [40, 41]; state.mode = "battle"');
r = J('window.VQSVSkills.forget("loi-mieu", "vqsv6-db-78", 41)');
assert.equal(r.ok, false); assert.match(r.message, /không thể đổi kỹ năng trong trận/);
assert.equal(J('window.VQSVSkills.options("vqsv6-db-78")')[0].locked, 'không thể đổi kỹ năng trong trận');
run('state.mode = "explore"');
// Reserve pets work the same way (Thủy thủ chim cánh cụt: Thủy skills are rows 30-39) and the lead is independent of them.
teamIds.push('vqsv6-db-61');
run('progress["vqsv6-db-61"] = { level: 20, exp: 0, skills: [30, 31, 32] }');
opts = J('window.VQSVSkills.options("vqsv6-db-61")');
assert.equal(opts.length, 1); assert.equal(opts[0].label.length > 0, true); assert.equal(opts[0].slots, 3);
r = J('window.VQSVSkills.forget("vqsv6-db-61", "vqsv6-db-61", 31)');
assert.equal(r.ok, true);
assert.deepEqual(JSON.parse(store.get('vqsv-unified-pet-progress-v1'))['vqsv6-db-61'].skills, [30, 32]);
assert.deepEqual(JSON.parse(store.get('vqsv-unified-pet-progress-v1'))['loi-mieu'].skills, [40, 41]);
// Skills of another element are rejected as a stored selection and fall back to the first skill of the element.
run('progress["vqsv6-db-61"].skills = [40, 41]');
assert.deepEqual(J('skillState("vqsv6-db-61", "vqsv6-db-61").known'), [30]);
teamIds.length = 0;
// ---- Held items (db.mid table 3 rows 0-11; game/i.java f(byte), game/a.java) ----
const heldBattle = (item, extra = '') => { reset3(); run(`progress["loi-mieu"] = { level: 5, exp: 0${item === null ? '' : `, item: ${item}`} }; state.battle = newBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 }); state.mode = "battle"; state.battle.enemy.stats.speed = 0; state.battle.enemy.skills = []; Math.random = () => 0.99; ${extra}`); };
// Shop: relics cost 5 huy hiệu each (the starter wallet has 2).
reset3();
assert.equal(J('SHOP.relics.length'), 12);
assert.match(run('buyItem("stone-1", 1).message'), /Huy hiệu chưa đủ \(cần 5, đang có 2\)/);
run('wallet.badges = 11');
assert.equal(run('buyItem("stone-1", 2).ok'), true);
assert.equal(run('wallet.badges'), 1); assert.equal(run('stones[1]'), 2);
// Equip and unequip: the item moves between the bag and the pet, one per pet, never in battle.
reset3(); run('stones[1] = 1; stones[2] = 1');
let eq = J('window.VQSVItems.equip("loi-mieu", "vqsv6-db-78", 1)');
assert.equal(eq.ok, true); assert.match(eq.message, /Đã đeo Hồng Sắc Hải Loa\./);
assert.equal(run('stones[1]'), 0); assert.equal(run('progress["loi-mieu"].item'), 1);
eq = J('window.VQSVItems.equip("loi-mieu", "vqsv6-db-78", 2)');
assert.equal(eq.ok, true); assert.match(eq.message, /trả Hồng Sắc Hải Loa về túi/);
assert.equal(run('stones[1]'), 1); assert.equal(run('stones[2]'), 0); assert.equal(run('progress["loi-mieu"].item'), 2);
assert.equal(J('window.VQSVItems.options("vqsv6-db-78")')[0].held.name, 'Quy Xác Toái Phiến');
assert.equal(J('window.VQSVItems.equip("loi-mieu", "vqsv6-db-78", 5)').ok, false); // none owned
run('state.mode = "battle"');
assert.match(J('window.VQSVItems.unequip("loi-mieu", "vqsv6-db-78")').message, /không thể trong trận/);
run('state.mode = "explore"');
assert.equal(J('window.VQSVItems.unequip("loi-mieu", "vqsv6-db-78")').ok, true);
assert.equal(run('stones[2]'), 1); assert.equal(run('progress["loi-mieu"].item'), undefined);
assert.equal(J('window.VQSVItems.unequip("loi-mieu", "vqsv6-db-78")').ok, false);
assert.equal(JSON.parse(store.get('vqsv-unified-stones-v1'))[2], 1);
// The item shows in the next battle and survives an evolution.
reset3(); run('stones[3] = 1; progress["loi-mieu"] = { level: 12, exp: 0 }; window.VQSVItems.equip("loi-mieu", "vqsv6-db-78", 3); stones[12] = 1; evolvePet("loi-mieu", "vqsv6-db-78"); state.battle = newBattle()');
assert.equal(run('activePet().petId'), 'vqsv6-db-79'); assert.equal(run('activePet().item'), 3);
// Stat items: 1 attack +10%, 0 attack +100% at HP <= 30%, 2 defense +15%.
heldBattle(null); const plainAttack = run('effStats(activePet()).attack'), plainDefense = run('effStats(activePet()).defense');
heldBattle(1); assert.equal(run('effStats(activePet()).attack'), plainAttack + Math.trunc(run('activePet().stats.attack') / 10));
heldBattle(2); assert.equal(run('effStats(activePet()).defense'), plainDefense + Math.trunc(run('activePet().stats.defense') * 15 / 100));
heldBattle(0); assert.equal(run('effStats(activePet()).attack'), plainAttack);
run('activePet().hp = Math.trunc(activePet().max * 30 / 100)');
assert.equal(run('effStats(activePet()).attack'), plainAttack + run('activePet().stats.attack'));
run('activePet().hp = Math.trunc(activePet().max * 30 / 100) + 1'); assert.equal(run('effStats(activePet()).attack'), plainAttack);
// Crit +10 points (Viễn Cổ Long Cốt): speed 5 gives 7.5%, with the item 17.5%.
heldBattle(null, 'Math.random = () => 0.12'); assert.equal(run('rollDamage(activePet(), state.battle.enemy, 100).crit'), false);
heldBattle(4, 'Math.random = () => 0.12'); assert.equal(run('rollDamage(activePet(), state.battle.enemy, 100).crit'), true);
// Miss rule: clamp(2 * level gap, 0, 20)%; item 9 removes it; Mục lowers the attacker level by its parameter.
heldBattle(null, 'state.battle.enemy.level = 20; Math.random = () => 0.1; action(false, "skill", 0)');
assert.match(run('state.battle.message'), /Lôi Miêu dùng Điện giật nhưng đánh trượt/);
assert.equal(run('state.battle.enemy.hp'), run('state.battle.enemy.max'));
heldBattle(9, 'state.battle.enemy.level = 20; Math.random = () => 0.1; action(false, "skill", 0)');
assert.equal(run('state.battle.enemy.hp') < run('state.battle.enemy.max'), true);
heldBattle(null, 'state.battle.enemy.level = 8; Math.random = () => 0.05; action(false, "skill", 0)'); // gap 3 -> 6% miss
assert.match(run('state.battle.message'), /nhưng đánh trượt/);
heldBattle(null, 'state.battle.enemy.level = 8; Math.random = () => 0.07; action(false, "skill", 0)');
assert.doesNotMatch(run('state.battle.message'), /nhưng đánh trượt/);
heldBattle(null, 'activePet().debuffs[4] = { turns: 3, value: 2 }; Math.random = () => 0.03; action(false, "skill", 0)'); // equal level + Mục 2 -> 4%
assert.match(run('state.battle.message'), /nhưng đánh trượt/);
heldBattle(null, 'activePet().debuffs[4] = { turns: 3, value: 2 }; Math.random = () => 0.05; action(false, "skill", 0)');
assert.doesNotMatch(run('state.battle.message'), /nhưng đánh trượt/);
// Item 8 (Hấp Huyết Đằng Mạn): 10% chance to heal 20% of the damage dealt.
heldBattle(8, 'activePet().hp = 50; Math.random = () => 0.05; action(false, "skill", 0)');
assert.match(run('state.battle.message'), /hút máu \+\d+ HP/);
heldBattle(8, 'activePet().hp = 50; Math.random = () => 0.5; action(false, "skill", 0)');
assert.doesNotMatch(run('state.battle.message'), /hút máu/);
// Item 10 (Cảm Lãm Chi Diệp): the target is never taken below 10 HP by the holder.
heldBattle(10, 'state.battle.enemy.hp = 12; action(false, "skill", 0)');
assert.equal(run('state.battle.enemy.hp'), 10);
heldBattle(null, 'state.battle.enemy.hp = 12; action(false, "skill", 0)');
assert.equal(run('state.battle.enemy.hp'), 0);
// Item 3 (Ô Nha Uế): debuffs land with chance x 80%; 85 rolls miss, 50 rolls land; without it always-on debuffs always land.
heldBattle(3); run('Math.random = () => 0.85'); assert.equal(run('debuffLands(activePet(), -1)'), false); assert.equal(run('debuffLands(activePet(), 10)'), false);
run('Math.random = () => 0.5'); assert.equal(run('debuffLands(activePet(), -1)'), true);
run('Math.random = () => 0.09'); assert.equal(run('debuffLands(activePet(), 10)'), false); // 9 >= 10 * 0.8
run('Math.random = () => 0.05'); assert.equal(run('debuffLands(activePet(), 10)'), true);
heldBattle(null); run('Math.random = () => 0.99'); assert.equal(run('debuffLands(activePet(), -1)'), true);
// Item 7 (Linh Trùng Thi Hài): the holder acts first even against a faster enemy.
heldBattle(null, 'state.battle.enemy.stats.speed = 99; state.battle.enemy.skills = []; action(false, "skill", 0)');
assert.match(run("state.battle.message"), /^Khốc Tị Mộc Linh dùng/);
heldBattle(7, 'state.battle.enemy.stats.speed = 99; state.battle.enemy.skills = []; action(false, "skill", 0)');
assert.match(run('state.battle.message'), /^Lôi Miêu dùng/);
// Item 11 (Sủng vật lôi đạt): capture rate +20% for the pet in battle.
heldBattle(null, 'state.battle.enemy.hp = state.battle.enemy.max'); const noRelic = run('captureRate(1, state.battle.enemy)');
heldBattle(11, 'state.battle.enemy.hp = state.battle.enemy.max'); assert.equal(run('captureRate(1, state.battle.enemy)'), Math.trunc(noRelic * 120 / 100));
// Items 5 and 6 (experience): holder +20%; a living holder that did not fight still gets a full share.
const win = (extra = '') => { run(`state.battle.enemy.hp = 0; state.battle.pendingUntil = 1; ${extra} updateBattle(2)`); return run('state.battle.message'); };
heldBattle(null); assert.match(win(), /Lôi Miêu \+500 KN/);
heldBattle(5); assert.match(win(), /Lôi Miêu \+600 KN/);
reset3(); teamIds.push('vqsv6-db-61');
run('progress["vqsv6-db-61"] = { level: 5, exp: 0, item: 6 }; progress["loi-mieu"] = { level: 5, exp: 0 }; state.battle = newBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 }); state.mode = "battle"; Math.random = () => 0.99');
assert.equal(run('state.battle.team[1].item'), 6);
assert.match(win(), /Lôi Miêu \+500 KN.*\(Ký Cư Giải Xác\) \+500 KN/);
assert.equal(run('progress["vqsv6-db-61"].exp'), 160);
teamIds.length = 0;
// TRIAL badge source: the first visit to each higher-level region gives 1 huy hiệu, once.
reset3(); run('state.spokeToElder = true; assets.maps = maps; assets.map = maps["ryan-forest-stream"]; state.player.x = 22; state.player.y = 10; interact()');
assert.equal(run('assets.map.id'), 'ryan-lakeside'); assert.equal(run('wallet.badges'), 3);
assert.match(element('status').textContent, /\+1 huy hiệu/);
run('state.player.x = 1; state.player.y = 10; interact(); state.player.x = 22; state.player.y = 10; interact()');
assert.equal(run('assets.map.id'), 'ryan-lakeside'); assert.equal(run('wallet.badges'), 3);
console.log('Wild capture: source encounter pools, grace steps, capture formula, ball use, story restriction, experience, skill learning, status effects, evolution, shop, items, skill management and held items passed.');
