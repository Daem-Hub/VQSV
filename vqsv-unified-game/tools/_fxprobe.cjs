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
    for (let tier = 1; tier <= fx.tiersOf(plan); tier++) {
      fx.clear();
      process.stderr.write(`cast ${plan} ${tier}\n`); fx.cast({ fromEnemy: (plan.length + tier) % 2 === 0, element, startAt: 0, crit: tier === 3, tier });
      assert(fx.busy(), plan);
      const before = drawn.length;
      for (let t = 0; t <= 4000 && fx.busy(); t += 16) fx.draw(ctx, t);
      assert(!fx.busy(), `${plan} tier ${tier} should finish`);
      assert(drawn.length > before, `${plan} tier ${tier} should draw`);
    }
    if (plan !== 'plain') assert.equal(fx.tiersOf(plan), 3, plan);
  }
  assert.equal(ctx.imageSmoothingEnabled, true);       // the canvas smoothing setting is restored
  for (const [kind, table] of [['buff', fx.buffFx], ['debuff', fx.debuffFx]]) for (const id of Object.keys(table)) {
    process.stderr.write(`status ${kind} ${id}\n`); fx.clear(); fx.status({ onEnemy: true, kind, id: Number(id), startAt: 0 });
    for (let t = 0; t <= 3000 && fx.busy(); t += 16) fx.draw(ctx, t);
    assert(!fx.busy(), `${kind} ${id} should finish`);
  }
  fx.clear(); fx.status({ onEnemy: false, kind: 'buff', id: 999, startAt: 0 }); assert(!fx.busy());   // unmapped ids are ignored
  // Every buff of the source table has an effect now (0-14), and every one-off event plays and ends.
  assert.deepEqual(Object.keys(fx.buffFx).map(Number).sort((a, b) => a - b), S.buffs.map((_, i) => i));
  for (const kind of Object.keys(fx.eventFx)) {
    process.stderr.write(`event ${kind}\n`); fx.clear(); fx.event({ kind, onEnemy: kind.startsWith('capture'), startAt: 0, delay: 100 }); assert(fx.busy(), kind);
    for (let t = 0; t <= 4000 && fx.busy(); t += 16) fx.draw(ctx, t);
    assert(!fx.busy(), `${kind} should finish`);
  }
  assert.deepEqual(Object.keys(fx.eventFx).sort(), ['captureFail', 'captureHit', 'captureOk', 'drain', 'healBig', 'healSmall', 'loyalty', 'miss', 'reflect', 'revive', 'sacrifice']);
  console.log('Skill effects: element plans in three tiers, all buffs, statuses, events and headless playback passed.');
})().catch(e => { console.error(e); process.exit(1); });
