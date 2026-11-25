// Shops: the menu's portable shop opens anywhere Kael walks; Tiệm thuốc and Tiệm bùa each stock their own trade.
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

const names = () => run('shopEntries().map(e => e.name)');
// Portable shop on a far map (Quỹ đạo): the whole catalogue, titled Tùy thân cửa hàng.
run('state.spokeToElder = true; assets.map = maps.orbit; state.mode = "explore"; openShop()');
assert.equal(run('shopDialog.open'), true, 'the portable shop opens outside the village');
assert.equal(run('document.getElementById("shopTitle").textContent'), 'Tùy thân cửa hàng');
const all = run('CATALOG.length'); assert.equal(names().length, all);
run('shopDialog.close()');
// Tiệm thuốc: food, potions, revive and repel only.
const vendor = id => run(`(() => { assets.map = maps[${JSON.stringify(id)}]; return assets.map.entities.villagers.find(n => n.vendor).vendor; })()`);
ctx.maps['village-apothecary'] = run('assets.maps["village-apothecary"]'); ctx.maps['village-charms'] = run('assets.maps["village-charms"]');
run(`openShop(${JSON.stringify(vendor('village-apothecary'))})`);
let list = names();
assert(list.includes('Khôi phục tề') && list.includes('Bánh Sandwich') && list.includes('Tị quái hàn'));
assert(!list.includes('Phong ấn cầu') && !list.some(n => n.startsWith('Sách ')), 'no balls or books at the apothecary');
run('shopDialog.close()');
// Tiệm bùa: capture balls, held charms, evolution stones, VQSV5 books and items.
run(`openShop(${JSON.stringify(vendor('village-charms'))})`);
list = names();
assert(list.includes('Phong ấn cầu') && list.includes('Mạn Đà La Thạch') && list.includes('Thiên Giới Tinh Thạch') && list.some(n => n.startsWith('Sách ')));
assert(!list.includes('Khôi phục tề'), 'no potions at the charm shop');
assert.equal(names().length + 10, all, 'the two shops together sell the whole catalogue');
// Buying still works from a split shop, and selling is not restricted.
run('wallet.gold = 1000; saveWallet()');
const before = run('bag[1] || 0'); run('buyItem("item-1", 2)'); assert.equal(run('bag[1]'), before + 2);
run('shopMode = "sell"'); assert(run('shopEntries().some(e => e.id === "item-1")'));
run('shopDialog.close(); shopMode = "buy"');
// In battle the shop keeps the whole catalogue at double price.
run('state.mode = "battle"; openShop("apothecary")'); assert.equal(names().length, all); run('shopDialog.close(); state.mode = "explore"');
console.log('Shops: portable shop anywhere, Tiệm thuốc / Tiệm bùa stock split, buying, selling and battle shop passed.');
