// VQSV6 skills with their own damage rule (game/i.java b(i), switch on the skill row).
const fs = require('fs'), vm = require('vm'), assert = require('assert'), path = require('path');
const html = fs.readFileSync(path.join(__dirname, '..', 'prototype', 'index.html'), 'utf8');
const code = [...html.matchAll(/<script>([\s\S]*?)<\/script>/g)].at(-1)[1].replace(/    loadData\(\)\.catch\(err => \{[^\n]+\}\);/, '');
const element = () => ({ textContent: '', hidden: true, addEventListener() {}, setAttribute() {}, getContext: () => new Proxy({}, { get: () => () => ({ addColorStop() {} }) }) });
const ctx = vm.createContext({ document: { getElementById: element }, window: { addEventListener() {}, VQSVBootstrap: require('./bootstrap_for_tests.cjs') }, Element: class {}, Image: class {}, performance: { now: () => 1000 }, console });
vm.runInContext(code, ctx);
const run = js => vm.runInContext(js, ctx);
const dmg = (row, defender = '{}', attacker = '{ hp: 100, max: 100 }') => run(`skillDamage(sourceSkill(${row}), ${attacker}, ${defender}, 120)`);
// Plain skill: damage x power %.
assert.equal(dmg(0), Math.trunc(120 * run('STATS.skills[0].power_percent') / 100));
// 1 / 7: power % plus damage / parameter (4 and 3).
assert.equal(dmg(1), 60 + 30);
assert.equal(dmg(7), 90 + 40);
// 3 / 9: parameter % (120 / 250) against a target carrying debuff 0, otherwise power % (100 / 200).
assert.equal(dmg(3), 120); assert.equal(dmg(3, '{ debuffs: { 0: {} } }'), 144);
assert.equal(dmg(9), 240); assert.equal(dmg(9, '{ debuffs: { 0: {} } }'), 300);
// 23 / 29: parameter % (250 / 300) against a target in Mê Muội (debuff 1).
assert.equal(dmg(23), 120); assert.equal(dmg(23, '{ debuffs: { 1: {} } }'), 300);
assert.equal(dmg(29, '{ debuffs: { 1: {} } }'), 360);
// 43 / 49: power % and the target's buffs are removed.
run('globalThis.t43 = { buffs: { 4: { turns: 2 } } }');
assert.equal(run('skillDamage(sourceSkill(43), {}, t43, 120)'), 120);
assert.deepEqual(JSON.parse(run('JSON.stringify(t43.buffs)')), {});
// 53 / 59: (parameter - the user's HP %) %: weak at full HP, strong when the user is low.
assert.equal(dmg(53), 120);                                        // 200 - 100 = 100 %
assert.equal(dmg(53, '{}', '{ hp: 10, max: 100 }'), 228);           // 200 - 10 = 190 %
assert.equal(dmg(59, '{}', '{ hp: 50, max: 100 }'), 240);           // 250 - 50 = 200 %
// In a whole roll the rule applies after the critical hit and before buffs and the element chart.
run('Math.random = () => 0.99');
const a = run('vqsv6Combatant(STATS.pets[0].pet_id, 20)'), d = run('vqsv6Combatant(STATS.pets[20].pet_id, 5)');
ctx.a = a; ctx.d = { ...d, debuffs: { 1: {} } };
const plain = run('rollDamage(a, d, 100).damage'), special = run('rollDamage(a, d, sourceSkill(23).power, sourceSkill(23)).damage');
assert(special > plain * 2, `Nham băng on a Mê Muội target: ${special} vs ${plain}`);
console.log('Skill damage rules: rows 1/7, 3/9, 23/29, 43/49, 53/59 and their place in the damage roll passed.');

// The estimate in the skill list follows the same steps without the random parts.
run('Math.random = () => 0.99');
ctx.a = a; ctx.d = d;
const est = run('estimateDamage(a, d, sourceSkill(0))');
const rolled = run('rollDamage(a, d, sourceSkill(0).power, sourceSkill(0)).damage');
assert(Math.abs(est.damage - rolled) <= 1, `estimate ${est.damage} vs roll ${rolled}`);
assert(est.crit >= est.damage);
assert.equal(run('estimateDamage(a, d, { noDamage: true })'), null);
console.log('Damage estimate: matches a non-critical roll within the ±1 jitter.');
