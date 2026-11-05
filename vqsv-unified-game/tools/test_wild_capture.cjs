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
const VQSVPetTeam = { ids: () => [], caughtBest: () => null, info: id => ({ id, image: `assets/pets/vqsv6/${id}.png` }), recordCatch: (id, level, quality) => { catches.push({ id, level, quality }); return { addedToTeam: true }; } };
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
console.log('Wild capture: source encounter pools, grace steps, capture formula, ball use, story restriction and experience passed.');
