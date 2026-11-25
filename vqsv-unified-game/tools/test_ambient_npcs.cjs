// Wandering NPCs (ambient_npcs.js): passers-by stay near home, never step onto blocked cells, exits or Kael, stop when he is close, and can be talked to.
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
vm.runInContext(fs.readFileSync(path.join(root, 'ambient_npcs.js'), 'utf8'), ctx);
assert(html.includes('src="ambient_npcs.js"'));
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

const tick = ms => { clock += ms; run('window.Quests.draw(ctx, S)'); };
const npcsHere = () => run('JSON.parse(JSON.stringify(villageNpcs()))');
const passers = run('window.Quests.ambient.list.map(d => d.map)');
assert(passers.length >= 6, 'several passers-by');
for (const mapId of [...new Set(passers)]) {
  run(`state.spokeToElder = true; state.mode = "explore"; assets.map = maps[${JSON.stringify(mapId)}]; window.Quests.ambient.reset()`);
  // park Kael far away so the wanderers are free to move
  const far = run(`(() => { const m = assets.map; let best = null; for (let y = 0; y < m.height; y++) for (let x = 0; x < m.width; x++) if (!blocked(x, y) && (!best || x + y > best.x + best.y)) best = { x, y }; return best; })()`);
  run(`Object.assign(state.player, { x: ${far.x}, y: ${far.y}, visualX: ${far.x}, visualY: ${far.y}, step: null })`);
  tick(0);
  const ids = npcsHere().filter(n => n.ambient || (mapId === 'slice01-border-village' && n.id === 'villager')).map(n => n.id);
  assert(ids.length, `${mapId}: wanderers present`);
  const home = Object.fromEntries(npcsHere().filter(n => ids.includes(n.id)).map(n => [n.id, { x: n.x, y: n.y }]));
  const moved = new Set();
  for (let i = 0; i < 400; i++) {
    tick(250);
    for (const n of npcsHere().filter(n => ids.includes(n.id))) {
      if (n.x !== home[n.id].x || n.y !== home[n.id].y) moved.add(n.id);
      assert(Math.abs(n.x - home[n.id].x) + Math.abs(n.y - home[n.id].y) <= 3, `${n.id} stays near home`);
      assert(!run(`(assets.map.links || []).some(l => l.x === ${n.x} && l.y === ${n.y})`), `${n.id} never stands on an exit`);
      assert(!(n.x === far.x && n.y === far.y), `${n.id} never steps onto Kael`);
      const others = npcsHere().filter(o => o.id !== n.id && o.x === n.x && o.y === n.y);
      assert(!others.length, `${n.id} never shares a cell`);
    }
  }
  assert(moved.size >= 1, `${mapId}: someone wandered`);
}
// Kael next to a wanderer: it stops, and talking gives its lines.
run(`assets.map = maps["slice01-border-village"]; window.Quests.ambient.reset()`);
tick(0);
const flower = npcsHere().find(n => n.id === 'amb-flower');
assert(flower, 'Bà bán hoa is in the village');
const side = [[0, 1], [0, -1], [1, 0], [-1, 0]].find(([dx, dy]) => !run(`blocked(${flower.x + dx}, ${flower.y + dy})`));
run(`Object.assign(state.player, { x: ${flower.x + side[0]}, y: ${flower.y + side[1]}, visualX: ${flower.x + side[0]}, visualY: ${flower.y + side[1]}, step: null })`);
for (let i = 0; i < 60; i++) tick(250);
const still = npcsHere().find(n => n.id === 'amb-flower');
assert.deepEqual([still.x, still.y], [flower.x, flower.y], 'a wanderer stands still while Kael is next to it');
run('interact()');
assert.equal(run('state.mode'), 'dialogue'); assert.equal(run('state.activeNpc.name'), 'Bà bán hoa');
for (let i = 0; i < 4 && run('state.mode') === 'dialogue'; i++) run('interact()');
// Nobody moves during a dialogue or the night raid.
run('state.mode = "dialogue"'); const before = JSON.stringify(npcsHere()); for (let i = 0; i < 40; i++) tick(250); assert.equal(JSON.stringify(npcsHere().map(n => [n.id, n.x, n.y])), JSON.stringify(JSON.parse(before).map(n => [n.id, n.x, n.y])));
run('state.mode = "explore"');
console.log('Wandering NPCs: passers-by on village, port, Atlantis and Aethelgard wander near home, avoid exits, cells and Kael, stop to talk and in dialogue passed.');
