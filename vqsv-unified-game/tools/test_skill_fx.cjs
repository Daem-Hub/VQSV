// Battle skill effects (prototype/skill_fx.js, pixelfx): every element has a plan, effects run and draw headless, statuses are mapped.
const path = require('path'), assert = require('assert'), { pathToFileURL } = require('url');
(async () => {
  const drawn = [];
  global.window = { dispatchEvent() {} }; global.Event = class {};
  global.ImageData = class { constructor(w, h) { this.width = w; this.height = h; this.data = new Uint8ClampedArray(w * h * 4); } };
  global.document = { createElement: () => ({ width: 0, height: 0, getContext: () => ({ putImageData() {} }) }) };
  await import(pathToFileURL(path.join(__dirname, '..', 'prototype', 'skill_fx.js')).href);
  const fx = window.VQSVSkillFx;
  const boot = require('./bootstrap_for_tests.cjs');
  // Every element and type name used by the data has its own plan.
  const names = new Set([...boot.vqsv6Stats.pets.map(p => p.element), ...boot.vqsv5Stats.type_names]);
  for (const name of names) assert.notEqual(fx.planOf(name), 'plain', name);
  assert.equal(fx.planOf(null), 'plain');
  // Every status id with a mapped effect exists in the status tables.
  const S = boot.vqsv6Stats.status_effects;
  for (const id of Object.keys(fx.buffFx)) assert(S.buffs[id], `buff ${id}`);
  for (const id of Object.keys(fx.debuffFx)) assert(S.debuffs[id], `debuff ${id}`);
  const ctx = { imageSmoothingEnabled: true, drawImage: (...a) => drawn.push(a) };
  // Each plan plays to the end without throwing; something is drawn on the way, and the pool empties.
  for (const plan of fx.plans) {
    fx.clear();
    const element = Object.entries(fx.elements).find(([, v]) => v === plan)?.[0] ?? null;
    fx.cast({ fromEnemy: plan.length % 2 === 0, element, startAt: 0, crit: true });
    assert(fx.busy(), plan);
    const before = drawn.length;
    for (let t = 0; t <= 4000 && fx.busy(); t += 16) fx.draw(ctx, t);
    assert(!fx.busy(), `${plan} should finish`);
    assert(drawn.length > before, `${plan} should draw`);
  }
  assert.equal(ctx.imageSmoothingEnabled, true);       // the canvas smoothing setting is restored
  for (const [kind, table] of [['buff', fx.buffFx], ['debuff', fx.debuffFx]]) for (const id of Object.keys(table)) {
    fx.clear(); fx.status({ onEnemy: true, kind, id: Number(id), startAt: 0 });
    for (let t = 0; t <= 3000 && fx.busy(); t += 16) fx.draw(ctx, t);
    assert(!fx.busy(), `${kind} ${id} should finish`);
  }
  fx.clear(); fx.status({ onEnemy: false, kind: 'buff', id: 999, startAt: 0 }); assert(!fx.busy());   // unmapped ids are ignored
  console.log('Skill effects: element plans, statuses and headless playback passed.');
})().catch(e => { console.error(e); process.exit(1); });
