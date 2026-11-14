// Sound effects (prototype/sound_fx.js): every sound builds Web Audio nodes in all tiers, settings persist, and the game and the
// effect module call the right sounds at the right moments.
const fs = require('fs'), path = require('path'), vm = require('vm'), assert = require('assert');
const { pathToFileURL } = require('url');
const root = path.join(__dirname, '..', 'prototype');

// ---- a fake Web Audio context that records what is scheduled ----
function fakeAudio(log) {
  const param = () => ({ value: 0, setValueAtTime() {}, exponentialRampToValueAtTime() {}, linearRampToValueAtTime() {} });
  const node = kind => ({ kind, connect() {}, frequency: param(), detune: param(), gain: param(), Q: param(), type: '', start(t) { log.push([kind, t]); }, stop() {}, buffer: null, loop: false });
  return class { constructor() { this.currentTime = 10; this.sampleRate = 8000; this.state = 'running'; this.destination = {}; }
    createGain() { return node('gain'); } createOscillator() { return node('osc'); } createBiquadFilter() { return node('filter'); } createBufferSource() { return node('noise'); }
    createBuffer(c, n) { return { getChannelData: () => new Float32Array(n) }; } resume() { return Promise.resolve(); } };
}
const log = [], store = new Map();
const win = { AudioContext: fakeAudio(log), addEventListener() {} };
const sandbox = { window: win, localStorage: { getItem: k => store.get(k) ?? null, setItem: (k, v) => store.set(k, v) }, console, Math, JSON, Number };
vm.runInNewContext(fs.readFileSync(path.join(root, 'sound_fx.js'), 'utf8'), sandbox);
const S = win.VQSVSound;
assert(S.supported);
const expected = ['fire', 'leaf', 'earth', 'water', 'volt', 'dark', 'wind', 'steel', 'beast', 'sea', 'plain', 'crit', 'buff', 'debuff', 'heal', 'revive', 'miss', 'reflect', 'drain', 'loyalty', 'sacrifice',
  'throw', 'ballHit', 'caught', 'escape', 'encounter', 'victory', 'levelUp', 'faint', 'chest', 'coin', 'flee', 'select'];
assert.deepEqual([...S.names].sort(), [...expected].sort());
for (const name of expected) {
  const counts = [1, 2, 3].map(tier => { log.length = 0; assert.equal(S.play(name, { tier }), true, name); return log.length; });
  assert(counts.every(n => n > 0), name);
}
// Stronger moves layer more sounds.
for (const el of ['fire', 'leaf', 'earth', 'water', 'volt', 'beast']) { const n = [1, 3].map(tier => { log.length = 0; S.play(el, { tier }); return log.length; }); assert(n[1] > n[0], el); }
// Delays schedule ahead of the context clock.
log.length = 0; S.play('select', { delay: 500 }); assert(log[0][1] >= 10.5);
assert.equal(S.play('nope'), false);
// Off / volume persist; nothing plays while off.
S.setOn(false); log.length = 0; assert.equal(S.play('fire'), false); assert.equal(log.length, 0);
assert.equal(JSON.parse(store.get('vqsv-unified-sound-v1')).on, false);
S.setVolume(0.25); assert.equal(S.settings().volume, 0.25); S.setOn(true); assert.equal(S.play('coin'), true);
S.setVolume(0); assert.equal(S.play('coin'), false); S.setVolume(0.6);
// Without Web Audio the module still loads and stays silent.
const quiet = { window: { addEventListener() {} }, localStorage: sandbox.localStorage, console, Math, JSON, Number };
vm.runInNewContext(fs.readFileSync(path.join(root, 'sound_fx.js'), 'utf8'), quiet);
assert.equal(quiet.window.VQSVSound.supported, false); assert.equal(quiet.window.VQSVSound.play('fire'), false);

(async () => {
  // ---- skill_fx.js calls the sounds: element by tier on impact, crit, statuses and events ----
  const calls = [];
  global.window = { dispatchEvent() {}, VQSVSound: { play: (name, opts = {}) => { calls.push([name, opts.tier || 1, Math.round(opts.delay || 0)]); return true; } } };
  global.Event = class {}; global.ImageData = class { constructor(w, h) { this.data = new Uint8ClampedArray(w * h * 4); } };
  global.document = { createElement: () => ({ getContext: () => ({ putImageData() {} }) }) };
  global.performance = { now: () => 0 };
  await import(pathToFileURL(path.join(root, 'skill_fx.js')).href);
  const fx = window.VQSVSkillFx;
  fx.cast({ fromEnemy: false, element: 'Hỏa hệ', startAt: 0, tier: 3, crit: true });
  assert.deepEqual(calls.slice(-2), [['fire', 3, 280], ['crit', 1, 360]]);                     // tier-3 fireball lands at 280 ms
  fx.cast({ fromEnemy: true, element: 'Kim Loại hệ', startAt: 100, tier: 2 }); assert.deepEqual(calls.at(-1), ['steel', 2, 360]);
  fx.cast({ fromEnemy: true, element: null, startAt: 0 }); assert.deepEqual(calls.at(-1), ['plain', 1, 60]);
  fx.status({ onEnemy: true, kind: 'buff', id: 3, startAt: 0 }); assert.equal(calls.at(-1)[0], 'heal');
  fx.status({ onEnemy: true, kind: 'buff', id: 12, startAt: 0 }); assert.equal(calls.at(-1)[0], 'buff');
  fx.status({ onEnemy: true, kind: 'debuff', id: 10, startAt: 0 }); assert.equal(calls.at(-1)[0], 'debuff');
  fx.status({ onEnemy: true, kind: 'v5', id: 0, startAt: 0 }); assert.equal(calls.at(-1)[0], 'debuff');
  for (const [kind, sound] of [['miss', 'miss'], ['reflect', 'reflect'], ['drain', 'drain'], ['revive', 'revive'], ['captureHit', 'ballHit'], ['captureOk', 'caught'], ['captureFail', 'escape'], ['healBig', 'heal'], ['loyalty', 'loyalty'], ['sacrifice', 'sacrifice']]) {
    fx.event({ kind, onEnemy: true, startAt: 0, delay: 200 }); assert.deepEqual(calls.at(-1).slice(0, 1), [sound], kind); assert.equal(calls.at(-1)[2], 200);
  }
  assert.equal(calls.at(-3)[1], 2);                                                              // healBig plays the bigger heal

  // ---- the game calls the battle-flow and UI sounds ----
  const html = fs.readFileSync(path.join(root, 'index.html'), 'utf8');
  assert(html.includes('<script src="sound_fx.js"></script>'));
  const code = [...html.matchAll(/<script>([\s\S]*?)<\/script>/g)].at(-1)[1].replace(/    loadData\(\)\.catch\(err => \{[^\n]+\}\);/, '');
  const boot = require('./bootstrap_for_tests.cjs');
  const heard = [];
  const els = {}, element = id => els[id] ||= { textContent: '', hidden: false, value: '', addEventListener() {}, getContext() { return {}; }, replaceChildren() {}, append() {} };
  const st2 = new Map();
  const gctx = vm.createContext({ document: { getElementById: element, createElement: () => ({ append() {}, addEventListener() {}, setAttribute() {}, replaceChildren() {} }) },
    window: { addEventListener() {}, VQSVBootstrap: boot, VQSVSound: { play: name => { heard.push(name); return true; }, settings: () => ({ on: true, volume: 0.6 }), setOn() {}, setVolume() {}, supported: true },
      VQSVPetTeam: { ids: () => [], speciesCaught: () => 3, caughtBest: () => null, replaceSpecies() {}, info: id => ({ id, kind: 'vqsv6', name: id, sequences: [], image: '' }), recordCatch: () => ({ addedToTeam: false }) } },
    performance: { now: () => 1000 }, Element: class {}, Image: class {}, console, localStorage: { getItem: k => st2.get(k) ?? null, setItem: (k, v) => st2.set(k, v) } });
  vm.runInContext(code, gctx);
  const run = js => vm.runInContext(js, gctx);
  run('assets.maps = window.VQSVBootstrap.maps; assets.map = assets.maps["slice01-border-village"]; reset(); state.spokeToElder = true; assets.map = assets.maps["ryan-meadow"]');
  run('Math.random = () => 0.3; startWildBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 })'); assert(heard.includes('encounter'));
  run('bag[1] = 3; state.battle.pendingUntil = 0; attemptCapture(1)'); assert(heard.includes('throw'));
  run('state.battle = newBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 }); state.mode = "battle"; state.battle.enemy.hp = 0; awardExperience(1000)'); assert(heard.includes('victory'));
  run('state.battle = newBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 }); state.mode = "battle"; state.battle.pendingUntil = 0; fleeBattle()'); assert(heard.includes('flee'));
  run('state.battle = newBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 }); state.mode = "battle"; activePet().hp = 0; handleFaint()'); assert(heard.includes('faint'));
  run('state.mode = "explore"; wallet.gold = 1000; buyItem("item-1", 1, false)'); assert(heard.includes('coin'));
  run('window.VQSVMapChests.reset(); window.VQSVMapChests.open(window.VQSVMapChests.list()[0].id)'); assert(heard.includes('chest'));
  heard.length = 0; run('state.battle = newBattle({ petId: "vqsv6-db-33", level: 5, quality: 1 }); state.battle.team[0].leveledUp = true; awardExperience(1000)'); assert(heard.includes('levelUp'));
  console.log('Sound effects: synthesis for every sound and tier, settings, the effect hooks and the game hooks passed.');
})().catch(e => { console.error(e); process.exit(1); });
