const fs = require('fs');
const path = require('path');
const vm = require('vm');
const assert = require('assert');
const root = path.join(__dirname, '..', 'prototype');
const data = JSON.parse(fs.readFileSync(path.join(root, 'data/original_skills.json'), 'utf8'));
const effects6 = JSON.parse(fs.readFileSync(path.join(root, 'data/vqsv6_effect_sprites.json'), 'utf8'));
assert.equal(Object.keys(effects6.sprites).length, 15);
assert.deepEqual(effects6.errors, {});
for (const sprite of Object.values(effects6.sprites)) for (const state of sprite.states) for (const frame of state) assert(fs.existsSync(path.join(root, frame.image)), frame.image);
assert.equal(effects6.sprites['266'].states[0].length, 4);
assert.equal(data.records.filter(x => x.game === 'VQSV5').length, 49);
assert.equal(data.records.filter(x => x.game === 'VQSV6').length, 70);
const linked = data.records.filter(x => x.game === 'VQSV6' && x.effect_source_rows);
assert.equal(linked.length, 70);
assert.equal(linked.filter(x => JSON.stringify(x.effect_source_rows.vqsv6_original) !== JSON.stringify(x.effect_source_rows.fix_2_7)).length, 61);
assert.equal(data.effects.reduce((n, x) => n + x.sequences.length, 0), 32);
for (const item of data.effects.flatMap(x => x.sequences).flat()) {
  assert(fs.existsSync(path.join(root, item.image)), item.image);
  assert(item.duration_ticks >= 0);
}
class El {
  constructor() { this.children = []; this.listeners = {}; this.value = ''; this.open = false; }
  append(...children) { this.children.push(...children); }
  replaceChildren(...children) { this.children = children; }
  addEventListener(event, fn) { this.listeners[event] = fn; }
  setAttribute(k, v) { this[k] = v; }
  removeAttribute(k) { delete this[k]; }
  showModal() { this.open = true; }
  close() { this.open = false; this.listeners.close?.(); }
  focus() {}
}
const ids = ['skillBook','skillSearch','skillSource','skillList','skillDetail','skillCount','effectAtlas','effectSequence','effectPreview','closeSkillBook'];
const e = Object.fromEntries(ids.map(id => [id, new El()])); e.skillSource.value = 'all';
let cleared = false;
const window = { VQSVBootstrap: { originalSkills: data } };
const document = { getElementById: id => e[id], createElement: () => new El() };
vm.runInNewContext(fs.readFileSync(path.join(root,'skill_book.js'),'utf8'), { window, document, setTimeout: () => 7, clearTimeout: () => { cleared = true; } });
window.openSkillBook();
assert.equal(e.skillCount.textContent, '119 kỹ năng');
assert(e.effectPreview.src.startsWith('assets/effects/vqsv5/'));
e.skillSource.value = 'VQSV5'; e.skillSource.listeners.change();
assert.equal(e.skillCount.textContent, '49 kỹ năng');
e.skillSearch.value = 'Triền Nhiễu'; e.skillSearch.listeners.input();
assert.equal(e.skillCount.textContent, '1 kỹ năng');
e.skillBook.close(); assert(cleared);
console.log('Skill catalog: counts, referenced frames, search, filter and timer cleanup passed.');
