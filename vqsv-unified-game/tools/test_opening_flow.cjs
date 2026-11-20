const fs = require('fs');
const vm = require('vm');
const assert = require('assert');
const path = require('path');
const html = fs.readFileSync(path.join(__dirname, '..', 'prototype', 'index.html'), 'utf8');
const scripts = [...html.matchAll(/<script>([\s\S]*?)<\/script>/g)];
const code = scripts.at(-1)[1].replace(/    loadData\(\)\.catch\(err => \{[^\n]+\}\);/, '');
const elements = {};
const drawnImages = [];
function element(id) {
  if (elements[id]) return elements[id];
  const attributes = new Map();
  const el = {
    id, textContent:'', hidden:false, open:false, children:[], listeners:{}, width:240, height:108,
    style:{setProperty() {}}, addEventListener(event, fn) { this.listeners[event] = fn; },
    getAttribute: name => attributes.get(name), setAttribute: (name, value) => attributes.set(name, value),
    removeAttribute: name => attributes.delete(name), toggleAttribute() {}, focus() {},
    append(child) { this.children.push(child); }, replaceChildren() { this.children = []; },
    querySelector() { return this.children.find(child => !child.disabled); }
  };
  el.getContext = () => new Proxy({canvas:el, drawImage:image => drawnImages.push(image)}, {
    get: (target, key) => key in target ? target[key] : () => {}
  });
  elements[id] = el;
  return el;
}
const listeners = {};
let petBookOpened = 0;
let clock = 1000;
const context = vm.createContext({
  document:{getElementById:element, querySelector:() => null, createElement:() => ({style:{setProperty() {}}})},
  window:{addEventListener:(event, fn) => { listeners[event] = fn; }, openPetBook:() => { petBookOpened++; },
    matchMedia:() => ({matches:false})},
  ResizeObserver:class { observe() {} }, Element:class {}, performance:{now:() => clock}, console
});
element('fightMenu').children = ['attackButton','basicButton','guardButton'].map(element);
element('itemMenu').children = ['potionButton','captureButton'].map(element);
element('duelCommands').children = ['fightButton','itemButton','teamButton','shopButton','fleeButton'].map(element);
vm.runInContext(fs.readFileSync(path.join(__dirname,'../prototype/battle_ui.js'),'utf8'), context);
vm.runInContext(code, context);
const drawBattle = () => vm.runInContext('window.drawDuelSprites(assets,state.battle,performance.now())',context);
const finishAction = () => { clock += 651; vm.runInContext('updateBattle(performance.now())', context); };
vm.runInContext('assets.map = {entities:{player:{x:1,y:1},elder:{x:1,y:2},encounter:{x:3,y:3}}}; state.mode="explore"; reset()', context);
assert.match(element('objective').textContent, /người gác làng/);
vm.runInContext('interact(); interact(); interact(); interact(); interact()', context);
assert.match(element('objective').textContent, /dấu đỏ/);
vm.runInContext('interact(); interact(); interact(); interact(); interact()', context);
assert.match(element('objective').textContent, /dấu đỏ/);
vm.runInContext('state.player.x=3; state.player.y=3; interact()', context);
assert.equal(element('battleControls').hidden, false);
assert.equal(element('fightMenu').hidden, true);
element('fightButton').listeners.click();
assert.equal(element('fightMenu').hidden, false);
vm.runInContext('assets.pets={hero:{width:94,height:82},enemy:{width:104,height:92}}', context);
drawBattle();
assert.equal(element('duelTurn').textContent,'Lượt 1');
assert.equal(element('foeHpText').textContent,'48 / 48');
assert.equal(element('heroHpText').textContent,'60 / 60');
const key = (key, repeat) => listeners.keydown({ key, repeat, target: {}, preventDefault() {} });
key('1', true);
assert.equal(vm.runInContext('state.battle.enemyHp', context), 48);
element('attackButton').listeners.click();
assert.equal(element('fightMenu').hidden, true);
assert.equal(vm.runInContext('state.battle.sparkUses', context), 44);
element('attackButton').listeners.click();
assert.equal(vm.runInContext('state.battle.sparkUses', context), 44);
vm.runInContext('assets.skillEffects = [{image:{width:20,height:20,marker:"source-vfx"}, duration_ticks:2}]', context);
clock += 100; drawBattle();
assert(drawnImages.some(image => image.marker === 'source-vfx'));
finishAction();
element('attackButton').listeners.click();
finishAction();
element('attackButton').listeners.click();
assert.equal(vm.runInContext('state.mode', context), 'battle');
finishAction();
assert.equal(vm.runInContext('state.mode', context), 'ending');
assert.equal(element('battleControls').hidden, true);
assert.match(element('objective').textContent, /thức tỉnh/);
element('restart').listeners.click();
assert.equal(vm.runInContext('state.player.x', context), 1);
assert.match(element('objective').textContent, /người gác làng/);
vm.runInContext('state.spokeToElder=true; state.player.x=3; state.player.y=3; interact()', context);
element('itemButton').listeners.click();
assert.equal(element('itemMenu').hidden, false);
element('captureButton').listeners.click();
assert.equal(element('itemMenu').hidden, true);
assert.match(vm.runInContext('state.battle.message', context), /Chưa thể bắt/);
assert.equal(vm.runInContext('state.battle.turn', context), 0);
element('teamButton').listeners.click();
assert.equal(petBookOpened, 1);
element('itemButton').listeners.click();
element('potionButton').listeners.click();
assert.equal(vm.runInContext('state.battle.potions', context), 2);
element('attackButton').listeners.click();
finishAction();
assert.equal(vm.runInContext('state.battle.heroHp', context), 48);
element('itemButton').listeners.click();
element('potionButton').listeners.click();
assert.equal(vm.runInContext('state.battle.potions', context), 1);
assert.equal(vm.runInContext('state.battle.heroHp', context), 48);
assert.equal(vm.runInContext('state.battle.turn', context), 2);
element('fleeButton').listeners.click();
assert.equal(vm.runInContext('state.mode', context), 'explore');
assert.equal(vm.runInContext('state.battle.potions', context), 2);
vm.runInContext('interact()', context);
element('basicButton').listeners.click();
assert.equal(vm.runInContext('state.battle.enemyHp', context), 38);
assert.equal(vm.runInContext('state.battle.skill', context), 'basic');
clock += 100; drawBattle();
finishAction();
element('fleeButton').listeners.click();
vm.runInContext('interact()', context);
for (let i = 0; i < 5; i++) { element('guardButton').listeners.click(); finishAction(); }
assert.equal(vm.runInContext('state.mode', context), 'battle');
for (let i = 0; i < 7; i++) { element('guardButton').listeners.click(); finishAction(); }
assert.equal(vm.runInContext('state.mode', context), 'defeat');
assert.equal(element('battleControls').hidden, true);
console.log('Opening flow: dialogue, replay, objective, battle buttons and held-key handling passed.');
