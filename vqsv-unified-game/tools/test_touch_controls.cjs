// On-screen controls (village_ui.js): the joystick drives the same movement path as the keyboard,
// the round action button interacts and its label follows what E would do.
const fs = require('fs'), path = require('path'), vm = require('vm'), assert = require('assert');
const root = path.join(__dirname, '..', 'prototype');
const html = fs.readFileSync(path.join(root, 'index.html'), 'utf8');
const code = [...html.matchAll(/<script>([\s\S]*?)<\/script>/g)].at(-1)[1].replace(/    loadData\(\)\.catch\(err => \{[^\n]+\}\);/, '');
for (const id of ['joystick', 'touchAction', 'touchControls', 'openMenu', 'gameMenu']) assert(html.includes(`id="${id}"`), id);
assert(html.indexOf('src="village_ui.js"') > html.lastIndexOf('<script>'), 'village_ui.js loads after the game script');
const els = {};
const element = id => els[id] ||= {
  id, classList:{add() {}, remove() {}}, textContent: '', hidden: false, disabled: false, listeners: {}, style: { setProperty() {}, removeProperty() {} },
  addEventListener(type, fn) { (this.listeners[type] ||= []).push(fn); }, getContext() { return new Proxy({}, { get: () => () => {} }); },
  getBoundingClientRect: () => ({ left: 0, top: 0, width: 100, height: 100 }), setPointerCapture() {}, setAttribute() {}, focus() {},
  querySelectorAll: () => [], showModal() { this.open = true; }, close() { this.open = false; }, click() { (this.listeners.click || []).forEach(fn => fn({})); if (this.onclick) this.onclick({}); }
};
let clock = 1000;
const documentListeners = {};
let hitTarget;
const ctx = vm.createContext({ document: { body: { dataset: {} }, getElementById: element, querySelectorAll: () => [], querySelector: () => null, elementFromPoint: () => hitTarget, addEventListener(type, fn) { documentListeners[type] = fn; }, fullscreenEnabled: false },
  window: { addEventListener() {}, VQSVBootstrap: require('./bootstrap_for_tests.cjs') }, Element: class {}, performance: { now: () => clock }, console });
vm.runInContext(code, ctx);
vm.runInContext(fs.readFileSync(path.join(root, 'village_ui.js'), 'utf8'), ctx);
const run = js => vm.runInContext(js, ctx);
run('assets.map = { id: "test", width: 8, height: 4, objects: [], entities: { player: { x: 3, y: 1 }, elder: { x: 7, y: 3 }, encounter: { x: 7, y: 2 } } }; state.mode = "explore"; reset()');
const fire = (id, type, e = {}) => (els[id].listeners[type] || []).forEach(fn => fn({ pointerId: 1, button: 0, preventDefault() {}, ...e }));
// Dragging the stick to the right starts a step; releasing clears the held direction.
fire('joystick', 'pointerdown', { clientX: 95, clientY: 50 });
assert.deepEqual(JSON.parse(JSON.stringify(run('state.player.inputDir'))), { dx: 1, dy: 0 });
assert.equal(run('state.player.x'), 4);
fire('joystick', 'pointerup');
assert.equal(run('state.player.inputDir'), null);
// A small drag near the centre does nothing; a drag down walks down.
fire('joystick', 'pointerdown', { clientX: 52, clientY: 52 });
assert.equal(run('state.player.inputDir'), null);
fire('joystick', 'pointermove', { clientX: 50, clientY: 98 });
assert.deepEqual(JSON.parse(JSON.stringify(run('state.player.inputDir'))), { dx: 0, dy: 1 });
fire('joystick', 'pointercancel');
assert.equal(run('state.player.inputDir'), null);
// The action button advances dialogue like the E key, and reads "Tiếp tục" while someone is talking.
run('state.player.step = null; state.mode = "dialogue"; state.activeNpc = { id: "x", name: "NPC", dialogue: ["a", "b"] }; state.dialogue = 0; updateGuidance()');
assert.equal(els.touchAction.textContent, 'Tiếp tục');
els.touchAction.click();
assert.equal(run('state.dialogue'), 1);
els.touchAction.click();
assert.equal(run('state.mode'), 'explore');
// Next to the guard the label offers a chat; with nothing nearby the button is disabled.
run('state.player.x = 7; state.player.y = 2; state.spokeToElder = false; updateGuidance()');
assert.equal(els.touchAction.textContent, 'Nói chuyện');
run('state.player.x = 0; state.player.y = 0; updateGuidance()');
assert.equal(els.touchAction.disabled, true);
// No movement while a battle is active.
run('state.mode = "battle"');
fire('joystick', 'pointerdown', { pointerId: 2, clientX: 95, clientY: 50 });
assert.equal(run('state.player.inputDir'), null);
console.log('Touch controls: joystick movement and release, action button and its labels passed.');

// A stationary tap activates once on release. Scrolling, cancellation and releasing
// outside the hit target must never select an item from a scrolling battle list.
let activations = 0, prevented = 0;
const target = {closest:() => target, contains:other => target === other, click:() => activations++};
const touch = (type, x=10) => documentListeners[type]({
  target, cancelable:true, changedTouches:[{identifier:1,clientX:x,clientY:10}],
  preventDefault() { prevented++; }
});
hitTarget = target;
touch('touchstart'); touch('touchend'); touch('touchend');
assert.equal(activations,1); assert.equal(prevented,1);
touch('touchstart'); touch('touchmove',35); touch('touchend',35);
touch('touchstart'); touch('touchcancel'); touch('touchend');
touch('touchstart'); hitTarget = null; touch('touchend');
assert.equal(activations,1);
