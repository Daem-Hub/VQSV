// VQSV5 wild pets: encounter tables decoded from data/map/m<N>.d, rolled on the unified maps, fought and caught like any wild pet.
const fs = require('fs'), path = require('path'), vm = require('vm'), assert = require('assert');
const html = fs.readFileSync(path.join(__dirname, '..', 'prototype', 'index.html'), 'utf8');
const code = [...html.matchAll(/<script>([\s\S]*?)<\/script>/g)].at(-1)[1].replace(/    loadData\(\)\.catch\(err => \{[^\n]+\}\);/, '');
const boot = require('./bootstrap_for_tests.cjs');
const S5 = boot.vqsv5Stats, WILD = boot.vqsv5Wild;
const T = require('../prototype/data/vqsv5_map_terrain.json');

// ---- Data ----
assert.equal(Object.keys(WILD).length, 35); assert.equal(Object.keys(WILD).length, T.summary.with_wild_pets);
const known = new Set(S5.pets.map(p => p.pet_id));
for (const [index, table] of Object.entries(WILD)) {
  assert(table.pet_ids.length >= 2 && table.pet_ids.every(id => known.has(id)), index); assert(table.level_min >= 1 && table.level_max >= table.level_min && table.level_max <= 70, index);
  assert.equal(table.terrain_type_id >= 0 && table.terrain_type_id <= 4, true);
}
assert.deepEqual([WILD[12].level_min, WILD[12].level_max, WILD[12].pet_ids], [8, 10, ['vqsv5-db-4', 'vqsv5-db-10', 'vqsv5-db-59']]);
assert(Object.values(WILD).every(t => t.rare_pet_id === null && t.pet_ids.length <= 3));        // no area lists the 4th (rare) entry the 5% branch would pick
const mapIds = ['ryan-phong-loi-forest', 'ryan-forest-stream', 'ryan-lakeside', 'ryan-meadow', 'ryan-highland', 'ryan-canyon', 'ryan-skyridge', 'ryan-ashfield'];
for (const id of mapIds) assert(boot.maps[id].terrain5.source_maps.some(index => WILD[index]), `${id} has a VQSV5 wild source`);

// ---- Game ----
const els = {};
const element = id => els[id] ||= { textContent: '', hidden: false, open: false, listeners: {}, addEventListener(event, fn) { this.listeners[event] = fn; }, getContext() { return {}; }, replaceChildren() {}, append() {}, setAttribute() {} };
const store = new Map(), localStorage = { getItem: k => store.get(k) ?? null, setItem: (k, v) => store.set(k, v) };
const recorded = [];
const VQSVPetTeam = { speciesCaught: () => recorded.length, ids: () => [], replaceSpecies() {}, caughtBest: () => null,
  info: id => ({ id, kind: id.startsWith('vqsv5') ? 'vqsv5' : 'vqsv6', name: id, element: null, sequences: id.startsWith('vqsv5') ? [[{ image: `assets/pets/vqsv5/${id}-0.png`, duration_ticks: 2 }]] : [], image: `assets/pets/${id}.png` }),
  recordCatch: (id, level, quality) => { recorded.push([id, level, quality]); return { addedToTeam: true }; } };
const ctx = vm.createContext({ document: { getElementById: element, createElement: () => ({ append() {}, addEventListener() {}, setAttribute() {}, replaceChildren() {} }) }, window: { addEventListener() {}, VQSVBootstrap: boot, VQSVPetTeam }, Image: class {}, Element: class {}, performance: { now: () => 1000 }, console, localStorage });
vm.runInContext(code, ctx);
const run = js => vm.runInContext(js, ctx);
const J = js => { const text = JSON.stringify(run(js)); return text === undefined ? undefined : JSON.parse(text); };
run('assets.maps = window.VQSVBootstrap.maps; assets.map = assets.maps["ryan-phong-loi-forest"]; reset(); assets.map = assets.maps["ryan-phong-loi-forest"]; progress = {}');
const seq = values => `{ const v = ${JSON.stringify(values)}; let i = 0; Math.random = () => (i < v.length ? v[i++] : 0.5); }`;

// Rolls on the forest: every pet and level comes from one of its source areas.
const sources = boot.maps['ryan-phong-loi-forest'].terrain5.source_maps.filter(i => WILD[i]);
const pets = new Set(sources.flatMap(i => WILD[i].pet_ids)), lo = Math.min(...sources.map(i => WILD[i].level_min)), hi = Math.max(...sources.map(i => WILD[i].level_max));
let v5 = 0, v6 = 0, rare = 0;
const rareIds = new Set(sources.map(i => WILD[i].rare_pet_id).filter(Boolean));
run('Math.random = Math.random.constructor === Function ? (() => { let s = 12345; return () => (s = (s * 1664525 + 1013904223) % 4294967296) / 4294967296; })() : Math.random');
for (let i = 0; i < 600; i++) {
  const roll = J('rollWild("ryan-phong-loi-forest")');
  if (roll.v5) { v5++; assert(pets.has(roll.petId), roll.petId); assert(roll.level >= lo && roll.level <= hi); assert.equal(roll.quality, S5.pets.find(p => p.pet_id === roll.petId).rank); assert(roll.traits.length === 2 && roll.traits.every(t => t >= 0 && t < 13)); if (rareIds.has(roll.petId)) rare++; }
  else v6++;
}
assert(v5 > 200 && v6 > 200, `share ${v5}/${v6}`);                                              // about half each
// Deterministic rolls: the share roll, source area, pet pick, level, then traits.
run(seq([0.9, 0, 0.5, 0, 0, 0, 0]));                                                          // v5; first source; no rare; first pet; lowest level
let w = J('rollWild("ryan-phong-loi-forest")'); assert.equal(w.v5, true); assert.equal(w.petId, WILD[sources[0]].pet_ids[0]); assert.equal(w.level, WILD[sources[0]].level_min);
run(seq([0.9, 0, 0.01, 0, 0, 0, 0])); w = J('rollWild("ryan-phong-loi-forest")');                // the 5% rare roll needs a 4th entry: none exists, so the first pet is picked
assert.equal(w.petId, WILD[sources[0]].pet_ids[0]);
run(seq([0.3, 0, 0])); assert.equal(J('rollWild("ryan-phong-loi-forest")').v5, undefined);   // below the share: a VQSV6 pet
// Villages and bridges (water pets) never roll VQSV5 pets.
run(seq([0.99])); assert.equal(J('rollWild("slice01-border-village")'), null);
run('assets.map = assets.maps["ryan-lakeside"]'); run(seq([0.99, 0, 0, 0, 0, 0])); assert.equal(J('rollWild("ryan-lakeside", 2)').v5, undefined);
run('assets.map = assets.maps["ryan-phong-loi-forest"]');

// A VQSV5 wild battle: stats, skills, traits, subtitle and sprite frames.
const wild = { v5: true, petId: 'vqsv5-db-0', level: 20, quality: 1, area: 'vqsv5-16', traits: [3, 2] };
run(`globalThis.wild = ${JSON.stringify(wild)}; state.spokeToElder = true; progress = {}; state.battle = newBattle(wild); state.mode = "battle"`);
const e = J('state.battle.enemy');
assert.equal(e.v5, true); assert.equal(e.name, 'Thụ quái'); assert.equal(e.level, 20); assert.deepEqual(e.traits, [3, 2]); assert.match(e.subtitle, /HOANG DÃ · CẤP 20 · THỰC VẬT HỆ · VQSV5 · HẠNG 1/);
assert.equal(e.max, J('vqsv5Combatant("vqsv5-db-0", 20, [3, 2], []).max')); assert.equal(e.skills.length, 2); assert.equal(e.image, 'assets/pets/vqsv5/vqsv5-db-0-0.png'); assert.equal(e.sequences.length, 1);
assert.match(run('state.battle.message'), /Thụ quái hoang dã xuất hiện/);
// The wild pet fights back with its skills (and their statuses land on the player's pet).
run('Math.random = () => 0; state.battle.pendingUntil = 0; state.battle.enemy.stats.attack = 400; globalThis.moved = performMove(state.battle.enemy, activePet(), state.battle.enemy.skills[0], "Thụ quái")');
assert.match(run('moved'), /Thụ quái dùng Triền Nhiễu/); assert(run('activePet().hp') < run('activePet().max'));
// Type advantage between VQSV5 pets: +30% for the beater, none the other way; a typeless attacker always gets it.
run(`globalThis.A = vqsv5Combatant("vqsv5-db-0", 20, [], []); globalThis.B = vqsv5Combatant("${S5.pets.find(p => p.type_id === 2).pet_id}", 20, [], []); A.loyalty = 100; B.loyalty = 100; state.battle.terrain = null`);
assert.equal(J('[A.typeId, B.typeId]').join(), '0,2');                                           // Thực Vật beats Phi Hành
assert.equal(J('adjustDamage5(A, B, 100)'), 130); assert.equal(J('adjustDamage5(B, A, 100)'), 100);
run('globalThis.C = vqsv5Combatant("vqsv5-db-85", 20, [], []); C.loyalty = 100'); assert.equal(J('adjustDamage5(C, A, 100)'), 130);
assert.equal(J('adjustDamage5(A, activePet(), 100)'), 100);                                       // against a VQSV6 pet: unchanged
// Catching it: the individual keeps its traits, the species counts as caught.
run('Math.random = () => 0; state.battle.enemy.hp = 1; state.battle.captured = true; finishWild("caught")');
assert.deepEqual(recorded.at(-1), ['vqsv5-db-0', 20, 1]); assert.deepEqual(J('progress["vqsv5-db-0"]'), { level: 20, exp: 0, traits: [3, 2] });
assert.equal(J('window.VQSVTrainer.species()'), 1); assert.equal(run('state.mode'), 'explore');
// A stronger catch replaces the stored one (traits follow), a weaker one does not; passives are kept.
run('progress["vqsv5-db-0"].passives = [33, -1]; state.battle = newBattle({ v5: true, petId: "vqsv5-db-0", level: 25, quality: 1, traits: [4, 1] }); state.mode = "battle"; finishWild("caught")');
assert.deepEqual(J('progress["vqsv5-db-0"]'), { level: 25, exp: 0, traits: [4, 1], passives: [33, -1] });
run('state.battle = newBattle({ v5: true, petId: "vqsv5-db-0", level: 10, quality: 1, traits: [3, 0] }); state.mode = "battle"; finishWild("caught")'); assert.deepEqual(J('progress["vqsv5-db-0"].traits'), [4, 1]);
// Skill 39 on a team pet: wild pets get lucky traits.
run('window.VQSVPetTeam.ids = () => ["vqsv5-db-0"]; progress["vqsv5-db-0"].passives = [39, -1]; ' + seq([0.9, 0, 0.5, 0, 0, 0.5, 0.5, 0.5, 0.5]));
assert.equal(run('teamLucky5()'), true); assert.equal(J('rollWild("ryan-phong-loi-forest")').traits.length, 2);
console.log('VQSV5 wild: decoded tables, rolls, battles, type advantage and catching passed.');
