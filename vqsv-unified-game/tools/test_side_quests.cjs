// Nhiệm vụ phụ SQ001–SQ047 (side_quests.js): unlocks follow the main quests, then every side quest is played through the engine.
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
vm.runInContext(fs.readFileSync(path.join(root, 'side_quests.js'), 'utf8'), ctx);
assert(html.includes('src="side_quests.js"'));
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

const where = id => run(`JSON.parse(JSON.stringify(window.Quests.side.where(${JSON.stringify(id)})))`);
const stand = p => { at(p.map, p.x, p.y); const ok = [[0, 1], [0, -1], [1, 0], [-1, 0]].find(([dx, dy]) => !run(`blocked(${p.x + dx}, ${p.y + dy})`)); at(p.map, p.x + ok[0], p.y + ok[1]); };
const survive = () => { run('state.battle.turn = state.battle.quest.surviveTurns - 1; state.battle.pendingUntil = 1; state.battle.enemyActed = true; activePet().hp = activePet().max'); clock += 2000; run('updateBattle(performance.now())'); };
const open = () => run('window.Quests.side.open()');
const S = () => run('window.Quests.side.state()');
const ids = run('window.Quests.list.map(q => q.id)');
assert.equal(run('window.Quests.side.list.length'), 47);
// Locked until the main quest named in `after` is done.
assert.deepEqual(open(), []);
run('window.Quests.jump("MQ011")');
assert(open().includes('SQ032') && !open().includes('SQ011'), 'SQ032 opens after MQ010, SQ011 waits for MQ011');
// The side quest giver stands on its map; talking starts the quest; main quest save keeps side progress.
let w = where('SQ032'); stand(w.npc); run('interact()'); talkThrough();
assert(S().q.SQ032.started);
let forced = 0; for (let i = 0; i < 40; i++) if (run(`rollWild("ryan-meadow", 0)`).questName === 'Drone trinh sát') forced++;
assert(forced > 10, 'drones appear on the meadow');
const kept = run('JSON.stringify(window.Quests.save())');
assert(JSON.parse(kept).side.q.SQ032.started);
run('window.Quests.reset()'); assert(!S().q.SQ032); run(`window.Quests.load(${kept})`); assert(S().q.SQ032.started);
// Unlock everything (main story finished), then play all 47.
run(`window.Quests.load({ ...window.Quests.save(), index: ${ids.length}, done: ${JSON.stringify(ids)}, intro: true, started: false })`);
assert.equal(open().length, 47);
run('progress["loi-mieu"] = { ...(progress["loi-mieu"] || {}), level: 70, exp: 0 }; saveProgress()');
const played = [];
for (const id of run('window.Quests.side.list.map(d => d.id)')) {
  const info = where(id); played.push(id);
  if (info.npc) { stand(info.npc); run('interact()'); talkThrough(); }
  if (info.talkOnly) { assert(S().done.includes(id), `${id} ends on the talk`); continue; }
  if (info.start) { stand(info.start); assert(run('window.Quests.actionLabel()'), `${id}: start label`); run('interact()'); talkThrough(); }
  assert(S().q[id]?.started, `${id} started`);
  if (info.wild) {
    for (let i = 0; i < 20 && !S().done.includes(id) && S().q[id].progress < info.wild.count; i++) {
      run(`startWildBattle(${JSON.stringify({ petId: info.wild.pet, area: id, level: info.wild.level[0], quality: 2, questName: info.wild.name })})`); run('finishWild("win")');
    }
  }
  for (let i = 0; i < info.catchAny; i++) { run(`startWildBattle(${JSON.stringify({ petId: 'vqsv6-db-99', area: id, level: 20, quality: 2 })})`); run('finishWild("caught")'); }
  for (const sp of info.spots) {
    for (let wave = 0; wave < (sp.waves || 1); wave++) {
      stand(sp); const label = run('window.Quests.actionLabel()'); assert(label, `${id}: spot on ${sp.map} at ${sp.x},${sp.y} has an action`);
      run('interact()');
      if (run('state.mode') === 'battle') { if (run('!!state.battle.quest?.surviveTurns')) survive(); else winBattle(); }
      else talkThrough();
    }
  }
  if (!S().done.includes(id) && info.npc) { stand(info.npc); run('interact()'); talkThrough(); }
  assert(S().done.includes(id), `${id} completes`);
  assert.equal(run('state.mode'), 'explore');
}
assert.equal(played.length, 47);
assert(S().aff > 0, 'Lyra affection grows');
const team = run('buildTeam()[0].skills.map(s => s.name)');
assert(team.includes('Nhịp Bước Uyên Ương') && team.includes('Lôi Bạo Cuồng Nộ'), 'SQ004 and SQ017 teach moves');
assert(run('window.VQSVPetTeam.owned()').includes('vqsv6-db-25'), 'SQ018 gives Hỏa Phượng Hoàng');
// A lost side battle never ends the game and leaves the quest open.
run('window.Quests.load({ ...window.Quests.save(), side: { q: {}, done: [], items: [], aff: 0, caught: 0 } })');
w = where('SQ014'); stand(w.npc); run('interact()'); talkThrough(); stand(w.spots[0]); run('interact()');
assert.equal(run('state.mode'), 'battle'); run('window.Quests.battleLost(state.battle.quest)');
assert.equal(run('state.mode'), 'explore'); assert(!S().done.includes('SQ014'));
const journal = run('(() => { const ul = document.getElementById("questSteps"); window.Quests.renderJournal(ul); return ul.children.map(li => li.textContent).join("\\n"); })()');
assert.match(journal, /Nhiệm vụ phụ/); assert.match(journal, /SQ014/);
console.log('Side quests: 47 unlocked by the main story and played through (givers, objects, spots, hunts, catches, fights, survives), rewards, save and loss passed.');
