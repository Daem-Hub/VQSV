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
  // a level-up may offer a new skill first (the battle waits for that dialog); skip it
  for (let i = 0; i < 8 && run('state.mode') === 'battle'; i++) { clock += 2000; while (run('learnDialog.open')) run('skipLearn()'); run('state.battle.pendingUntil = state.battle.pendingUntil || 1; updateBattle(performance.now())'); }
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
assert.equal(Q(), 'MQ010'); assert(run('window.Quests.state().items').includes('Bản Đồ Ryan'));
assert.match(run('objectiveEl.textContent'), /MQ010/);
// ---- MQ010–MQ020 on the atlas maps ----
const where = id => run(`JSON.parse(JSON.stringify(window.Quests.where(${JSON.stringify(id)})))`);
const stand = p => { at(p.map, p.x, p.y); const ok = [[0, 1], [0, -1], [1, 0], [-1, 0]].find(([dx, dy]) => !run(`blocked(${p.x + dx}, ${p.y + dy})`)); at(p.map, p.x + ok[0], p.y + ok[1]); };
const survive = () => { run('state.battle.turn = state.battle.quest.surviveTurns - 1; state.battle.pendingUntil = 1; state.battle.enemyActed = true; activePet().hp = activePet().max'); clock += 2000; run('updateBattle(performance.now())'); };
// MQ010: the caravan leader, two ambushes in order, then the far end of the pass.
let w = where('MQ010');
stand(w.npc); run('interact()'); talkThrough();
assert.match(run('objectiveEl.textContent'), /MQ010 · Đẩy lùi phục kích/);
stand(w.spots[1]); assert.equal(run('window.Quests.actionLabel()'), '');            // ordered: the second ambush waits
for (const sp of w.spots) { stand(sp); run('interact()'); if (run('state.mode') === 'battle') winBattle(); else talkThrough(); }
assert.equal(Q(), 'MQ011');
// MQ011: touch the ice pillar, break three seals.
w = where('MQ011'); stand(w.start); run('interact()'); talkThrough();
for (const sp of w.spots) { stand(sp); run('interact()'); winBattle(); }
assert.equal(Q(), 'MQ012'); assert(run('window.Quests.state().items').includes('Băng Tinh Thạch'));
// MQ012: calm the guardian; knocking it out fails, holding out five turns succeeds.
w = where('MQ012'); stand(w.spots[0]); run('interact()'); winBattle();
assert.equal(Q(), 'MQ012'); assert.match(run('statusEl.textContent'), /trấn an/);
stand(w.spots[0]); run('interact()'); survive();
assert.equal(Q(), 'MQ013');
// MQ013: Jax, then the rock scorpion.
w = where('MQ013'); stand(w.npc); run('interact()'); talkThrough();
stand(w.spots[0]); run('interact()'); assert.equal(run('state.battle.enemy.name'), 'Bọ Cạp Nham Thạch'); winBattle();
assert.equal(Q(), 'MQ014');
// MQ014: the lava needs a Thủy pet in the backpack.
w = where('MQ014'); run('window.VQSVPetTeam.setTeam([])'); stand(w.spots[0]); run('interact()');
assert.equal(Q(), 'MQ014'); assert.match(run('statusEl.textContent'), /Thủy hệ/);
run('window.VQSVPetTeam.recordCatch("vqsv6-db-58", 12, 2)'); run('interact()'); talkThrough();
assert.equal(Q(), 'MQ015');
// MQ015: knocking the dragon out fails; catching it completes.
w = where('MQ015'); stand(w.spots[0]); run('interact()');
assert.equal(run('state.battle.enemy.name'), 'Viêm Long Tí Nị'); assert(run('!!state.battle.wild'));
winBattle(); assert.equal(Q(), 'MQ015');
stand(w.spots[0]); run('interact()'); run('state.battle.captured = true; state.battle.pendingUntil = 1'); clock += 2000; run('updateBattle(performance.now())');
assert.equal(Q(), 'MQ016');
// MQ016: the veteran; cultist groups count two each; report back.
w = where('MQ016'); stand(w.npc); run('interact()'); talkThrough();
let cult = 0; for (let i = 0; i < 40; i++) if (run('rollWild("ryan-stormplain", 0)').questName === 'Nhóm Tín Đồ Ma Khống') cult++;
assert(cult > 15, 'cultists appear on the storm plain');
for (let i = 0; i < 5; i++) { run('startWildBattle({ petId: "vqsv6-db-94", area: "MQ016", level: 22, quality: 2, questName: "Nhóm Tín Đồ Ma Khống" })'); run('finishWild("win")'); }
assert.equal(run('window.Quests.state().progress'), 10);
stand(w.npc); run('interact()'); talkThrough();
assert.equal(Q(), 'MQ017');
// MQ017: four crystals on four pillars.
for (const sp of where('MQ017').spots) { stand(sp); run('interact()'); talkThrough(); }
assert.equal(Q(), 'MQ018'); assert(run('window.Quests.state().items').includes('Chìa Khóa Thiên Không'));
// MQ018: beat the phantom; the ritual evolves Lôi Miêu when its form has a next stage.
run('progress["loi-mieu"] = { ...(progress["loi-mieu"] || {}), level: 24, exp: 0 }; saveProgress()');
const form0 = run('leadForm()');
w = where('MQ018'); stand(w.spots[0]); run('interact()'); winBattle();
assert.equal(Q(), 'MQ019'); assert.notEqual(run('leadForm()'), form0, 'Lôi Miêu evolved in the ritual');
// MQ019: three waves at one spot.
w = where('MQ019'); for (let i = 0; i < 3; i++) { stand(w.spots[0]); run('interact()'); winBattle(); }
assert.equal(Q(), 'MQ020');
// MQ020: the dark dragon; the chapter ends on its own scene.
w = where('MQ020'); stand(w.spots[0]); run('interact()'); run('fleeBattle()'); assert.equal(run('state.mode'), 'battle'); winBattle();
assert.equal(run('state.mode'), 'ending'); assert.equal(run('window.Quests.endingScene().heading'), 'ANH HÙNG RYAN');
run('interact()'); assert.equal(run('state.mode'), 'explore');
assert.equal(Q(), 'MQ021'); assert(run('window.Quests.state().items').includes('Bức thư của cha'));
// ---- Chương 2 · Hải Vực và Atlantis (MQ021–MQ042): every quest played with the same generic steps ----
run('progress["loi-mieu"] = { ...(progress["loi-mieu"] || {}), level: 40, exp: 0 }; saveProgress()');
const played = [];
for (let guard = 0; guard < 40 && Q() && Q() !== null; guard++) {
  const id = Q(), info = where(id);
  played.push(id);
  if (info.npc) { stand(info.npc); run('interact()'); talkThrough(); }
  if (Q() !== id) continue;                                         // talk-only quests end on the first talk
  if (info.start) { stand(info.start); run('interact()'); talkThrough(); }
  for (const sp of info.spots) {
    for (let wave = 0; wave < (sp.waves || 1); wave++) {
      stand(sp); run('interact()');
      if (run('state.mode') === 'battle') {
        if (sp.capture) { run('state.battle.captured = true; state.battle.pendingUntil = 1'); clock += 2000; run('updateBattle(performance.now())'); }
        else if (run('!!state.battle.quest?.surviveTurns')) survive();
        else winBattle();
      } else talkThrough();
    }
  }
  if (Q() === id && info.npc) { stand(info.npc); run('interact()'); talkThrough(); }
  if (run('state.mode') === 'ending') run('interact()');
  assert.notEqual(Q(), id, `${id} completes`);
}
assert.equal(played[0], 'MQ021'); assert.equal(played.at(-1), 'MQ042'); assert.equal(played.length, 22);
assert.equal(Q(), null);
assert(run('window.Quests.state().items').includes('Tín Vật Tình Bạn Lyra'));
assert(run('buildTeam()[0].skills.some(s => s.name === "Lôi Dực Khiêu Vũ")'), 'MQ031 gives Lôi Miêu its wing move');
assert.match(run('objectiveEl.textContent'), /Chương 2 · Hải Vực và Atlantis đã hoàn thành/);
// Saves carry the quest state; a reset starts over.
at('slice01-border-village', elder.x, elder.y + 1); run('saveGame(); reset()');
assert.equal(Q(), 'MQ001'); run('loadGame()'); assert.equal(Q(), null);
console.log('Chapters 1–2: MQ001–MQ042 played through (dummy, cellar, wolves, herbs, footprints, night raid, colossus, Ma Khống Cầu, shrine), rewards and save passed.');
