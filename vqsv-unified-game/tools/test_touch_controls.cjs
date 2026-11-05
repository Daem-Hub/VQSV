// On-screen controls: the d-pad drives the same movement path as the keyboard, and the action button interacts.
const fs = require('fs'), path = require('path'), vm = require('vm'), assert = require('assert');
const html = fs.readFileSync(path.join(__dirname, '..', 'prototype', 'index.html'), 'utf8');
const code = [...html.matchAll(/<script>([\s\S]*?)<\/script>/g)].at(-1)[1].replace(/    loadData\(\)\.catch\(err => \{[^\n]+\}\);/, '');
for (const id of ['touchPad', 'touchUp', 'touchDown', 'touchLeft', 'touchRight', 'touchAction']) assert(html.includes(`id="${id}"`), id);
assert.match(html, /@media \(pointer: coarse\), \(max-width: 850px\)/);
const els = {};
const element = id => els[id] ||= { textContent: '', hidden: false, listeners: {}, addEventListener(event, fn) { (this.listeners[event] ||= []).push(fn); }, getContext() { return {}; } };
let clock = 1000;
const ctx = vm.createContext({ document: { getElementById: element }, window: { addEventListener() {}, VQSVBootstrap: require('./bootstrap_for_tests.cjs') }, Element: class {}, performance: { now: () => clock }, console });
vm.runInContext(code, ctx);
const run = js => vm.runInContext(js, ctx);
run('assets.map = { width: 8, height: 4, objects: [], entities: { player: { x: 3, y: 1 }, elder: { x: 7, y: 3 }, encounter: { x: 7, y: 2 } } }; state.mode = "explore"; reset()');
const fire = (id, type) => (els[id].listeners[type] || []).forEach(fn => fn({ preventDefault() {} }));
// Pressing a direction starts a step, releasing clears the held direction.
fire('touchRight', 'pointerdown');
assert.deepEqual(JSON.parse(JSON.stringify(run('state.player.inputDir'))), { dx: 1, dy: 0 });
assert.equal(run('state.player.x'), 4);
fire('touchRight', 'pointerup');
assert.equal(run('state.player.inputDir'), null);
// Leaving the button or a cancelled touch also releases; only the matching direction releases.
fire('touchDown', 'pointerdown'); fire('touchUp', 'pointerup');
assert.deepEqual(JSON.parse(JSON.stringify(run('state.player.inputDir'))), { dx: 0, dy: 1 });
fire('touchDown', 'pointerleave'); assert.equal(run('state.player.inputDir'), null);
fire('touchLeft', 'pointerdown'); fire('touchLeft', 'pointercancel'); assert.equal(run('state.player.inputDir'), null);
// The action button advances dialogue like the E key.
run('state.player.step = null; state.mode = "dialogue"; state.activeNpc = { id: "x", name: "NPC", dialogue: ["a", "b"] }; state.dialogue = 0');
fire('touchAction', 'pointerdown');
assert.equal(run('state.dialogue'), 1);
fire('touchAction', 'pointerdown');
assert.equal(run('state.mode'), 'explore');
// No movement while a battle or dialogue is active.
run('state.mode = "battle"'); fire('touchRight', 'pointerdown'); assert.equal(run('state.player.inputDir'), null);
console.log('Touch controls: d-pad movement, release paths and the action button passed.');
