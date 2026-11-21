// Chương 1 main quests MQ001–MQ009 (quests.js): the whole chain played through the engine's own interact() and battles.
const fs = require('fs'), vm = require('vm'), assert = require('assert'), path = require('path');
const root = path.join(__dirname, '..', 'prototype');
const html = fs.readFileSync(path.join(root, 'index.html'), 'utf8');
const code = [...html.matchAll(/<script>([\s\S]*?)<\/script>/g)].at(-1)[1].replace(/    loadData\(\)\.catch\(err => \{[^\n]+\}\);/, '');
assert(html.includes('src="quests.js"'));
class Element {
  constructor() { this.children = []; this.listeners = {}; this.dataset = {}; this.style = { setProperty() {}, removeProperty() {} }; this.textContent = ''; this.hidden = false; this.value = 'all'; }
  append(...n) { this.children.push(...n); } replaceChildren(...n) { this.children = [...n]; }
  setAttribute() {} getAttribute() {} removeAttribute() {} toggleAttribute() {} addEventListener(e, f) { (this.listeners[e] ||= []).push(f); }
  showModal() { this.open = true; } close() { this.open = false; } focus() {} querySelector() { return null; } querySelectorAll() { return []; }
  getContext() { return new Proxy({}, { get: () => () => ({ addColorStop() {} }) }); }
}
const els = {}, element = id => els[id] ||= new Element();
const store = new Map(), localStorage = { getItem: k => store.get(k) ?? null, setItem: (k, v) => store.set(k, String(v)) };
const boot = { window: {} }; vm.runInNewContext(fs.readFileSync(path.join(root, 'data/bootstrap.js'), 'utf8'), boot);
let clock = 1000;
const window = { addEventListener() {}, VQSVBootstrap: boot.window.VQSVBootstrap, matchMedia: () => ({ matches: false }) };
const ctx = vm.createContext({ window, document: { getElementById: element, createElement: () => new Element(), querySelectorAll: () => [], querySelector: () => null, addEventListener() {}, body: { dataset: {} } },
  localStorage, Element, Image: class {}, performance: { now: () => clock }, setTimeout: () => 1, clearTimeout() {}, console });
vm.runInContext(fs.readFileSync(path.join(root, 'pet_book.js'), 'utf8'), ctx);
vm.runInContext(code, ctx);
vm.runInContext(fs.readFileSync(path.join(root, 'quests.js'), 'utf8'), ctx);
const run = js => vm.runInContext(js, ctx);
ctx.maps = boot.window.VQSVBootstrap.maps;
run('assets.maps = maps; assets.map = maps["slice01-border-village"]; reset()');
const Q = () => run('window.Quests.current()?.id ?? null');
const at = (mapId, x, y) => run(`assets.map = maps[${JSON.stringify(mapId)}]; state.mode = "explore"; Object.assign(state.player, { x: ${x}, y: ${y}, visualX: ${x}, visualY: ${y}, step: null })`);
const nextTo = (mapId, spot) => at(mapId, spot.x, spot.y + 1 < ctx.maps[mapId].height && !run(`(() => { assets.map = maps[${JSON.stringify(mapId)}]; return blocked(${spot.x}, ${spot.y + 1}); })()`) ? spot.y + 1 : spot.y - 1);
const talkThrough = () => { for (let i = 0; i < 12 && run('state.mode') === 'dialogue'; i++) run('interact()'); };
const winBattle = () => {
  assert.equal(run('state.mode'), 'battle');
  run('state.battle.enemy.hp = 0; state.battle.pendingUntil = 1');
  for (let i = 0; i < 6 && run('state.mode') === 'battle'; i++) { clock += 2000; run('state.battle.pendingUntil = state.battle.pendingUntil || 1; updateBattle(performance.now())'); }
};
const spot = name => run(`(() => { const s = window.Quests.spots().get(${JSON.stringify(name)}); return JSON.parse(JSON.stringify(s)); })()`);
// MQ001: Elden's talk starts it; the training dummy is a quest battle that never strikes back.
const elder = ctx.maps['slice01-border-village'].entities.elder;
at('slice01-border-village', elder.x, elder.y + 1); run('interact()');
assert.equal(run('state.activeNpc.name'), 'Trưởng làng Elden');
talkThrough();
assert.equal(Q(), 'MQ001'); assert.equal(run('state.spokeToElder'), true);
assert.match(run('objectiveEl.textContent'), /MQ001/);
const exp0 = run('progressOf("loi-mieu", BATTLE_LEVEL).exp + 1000 * progressOf("loi-mieu", BATTLE_LEVEL).level');
nextTo('slice01-border-village', spot('dummy')); run('interact()');
assert.equal(run('state.battle.enemy.name'), 'Bia gỗ'); assert.equal(run('state.battle.enemy.passive'), true);
run('enemyStrike(performance.now())'); assert.match(run('state.battle.message'), /đứng yên/);
const bag8 = run('bag[8] || 0');
winBattle();
assert.equal(Q(), 'MQ002'); assert.equal(run('bag[8]'), bag8 + 5);
assert(run('progressOf("loi-mieu", BATTLE_LEVEL).exp + 1000 * progressOf("loi-mieu", BATTLE_LEVEL).level') > exp0, 'MQ001 gives Lôi Miêu EXP');
// MQ002: the cellar.
nextTo('slice01-border-village', spot('cellar')); run('interact()'); talkThrough();
assert.equal(Q(), 'MQ003'); assert(run('window.Quests.state().items').includes('La Bàn Đồng Cổ'));
// MQ003: Bran in the forest; three Dã Lang Bão Tố (the forest forces the wolf half of the time).
const bran = spot('bran');
at('ryan-phong-loi-forest', bran.x, bran.y + 1);
assert(run('villageNpcs().some(n => n.id === "bran")'));
run(`Object.assign(state.player, { x: ${bran.x}, y: ${bran.y} + (blocked(${bran.x}, ${bran.y + 1}) ? -1 : 1) }); interact()`); talkThrough();
assert.match(run('objectiveEl.textContent'), /Dã Lang Bão Tố/);
let forced = 0; for (let i = 0; i < 60; i++) if (run('rollWild("ryan-phong-loi-forest", 0)').questName === 'Dã Lang Bão Tố') forced++;
assert(forced > 10, 'wolves appear while MQ003 runs');
for (let i = 0; i < 3; i++) { run('startWildBattle(window.Quests.forceWild("ryan-phong-loi-forest") || { petId: "vqsv6-db-21", area: "MQ003", level: 3, quality: 2, questName: "Dã Lang Bão Tố" })'); run('finishWild("win")'); }
run(`Object.assign(state.player, { x: ${bran.x}, y: ${bran.y} + (blocked(${bran.x}, ${bran.y + 1}) ? -1 : 1) }); interact()`); talkThrough();
assert.equal(Q(), 'MQ004');
// MQ004: Mae (the herbalist) and five Cỏ Dạ Quang in the meadow valley.
const mae = ctx.maps['slice01-border-village'].entities.villagers.find(n => n.id === 'herbalist');
at('slice01-border-village', mae.x, mae.y + 1); run('interact()');
assert.equal(run('state.activeNpc.name'), 'Dược sĩ Mae'); talkThrough();
const gold0 = run('wallet.gold');
for (const g of spot('grass')) { nextTo('ryan-meadow', g); run('interact()'); }
assert.equal(run('window.Quests.state().progress'), 5);
at('slice01-border-village', mae.x, mae.y + 1); run('interact()'); talkThrough();
assert.equal(Q(), 'MQ005'); assert.equal(run('wallet.gold'), gold0 + 200);
// MQ005: three dark footprints in order along the stream, then report to Elden.
for (const p of spot('prints')) { nextTo('ryan-forest-stream', p); run('interact()'); }
assert.match(run('objectiveEl.textContent'), /Trưởng làng/);
at('slice01-border-village', elder.x, elder.y + 1); run('interact()'); talkThrough();
assert.equal(Q(), 'MQ006'); assert.equal(run('window.Quests.night()'), true);
// MQ006: rescue the three burning villagers, one fight each.
for (const id of ['herbalist', 'villager', 'scout']) {
  const v = ctx.maps['slice01-border-village'].entities.villagers.find(n => n.id === id);
  at('slice01-border-village', v.x, v.y + 1); run('interact()'); winBattle();
}
assert.equal(Q(), 'MQ007');
// MQ007: the colossus cannot be fled from and is won by holding out its turns.
nextTo('slice01-border-village', spot('colossus')); run('interact()');
assert.equal(run('state.battle.enemy.name'), 'Cuồng Nham Cự Tượng');
run('fleeBattle()'); assert.equal(run('state.mode'), 'battle');
run('state.battle.turn = state.battle.quest.surviveTurns - 1; state.battle.pendingUntil = 1; state.battle.enemyActed = true; activePet().hp = activePet().max'); clock += 2000; run('updateBattle(performance.now())');
assert.equal(Q(), 'MQ008');
// MQ008: Ma Khống Cầu; Lôi Miêu gets Lôi Kích Trắng for this fight; winning shows the compass scene, E continues.
const enc = ctx.maps['slice01-border-village'].entities.encounter;
at('slice01-border-village', enc.x - 1, enc.y); run('interact()');
assert.equal(run('state.battle.team.find(p => p.kind === "lead").skill.name'), 'Lôi Kích Trắng');
winBattle();
assert.equal(run('state.mode'), 'ending'); assert.equal(Q(), 'MQ009');
run('interact()'); assert.equal(run('state.mode'), 'explore');
// MQ009: the old shrine in the forest; the chain ends with the map of Ryan.
nextTo('ryan-phong-loi-forest', spot('shrine')); run('interact()'); talkThrough();
assert.equal(Q(), null); assert(run('window.Quests.state().items').includes('Bản Đồ Ryan'));
assert.match(run('objectiveEl.textContent'), /MQ010/);
// Saves carry the quest state; a reset starts over.
at('slice01-border-village', elder.x, elder.y + 1); run('saveGame(); reset()');
assert.equal(Q(), 'MQ001'); run('loadGame()'); assert.equal(Q(), null);
console.log('Chapter 1: MQ001–MQ009 played through (dummy, cellar, wolves, herbs, footprints, night raid, colossus, Ma Khống Cầu, shrine), rewards and save passed.');
