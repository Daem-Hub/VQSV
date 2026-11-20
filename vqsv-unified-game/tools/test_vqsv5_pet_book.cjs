// Pet book details for VQSV5 pets: type, rank, source stats, skill unlocks and the evolution / mutation panel.
const fs = require('fs'), vm = require('vm'), assert = require('assert'), path = require('path');
const root = path.join(__dirname, '..', 'prototype');
class Element {
  constructor(tag = 'div') { this.tag = tag; this.children = []; this.listeners = {}; this.value = ''; this.open = false; this.textContent = ''; }
  append(...nodes) { this.children.push(...nodes); }
  replaceChildren(...nodes) { this.children = nodes; }
  setAttribute(key, value) { this[key] = value; }
  addEventListener(event, fn) { this.listeners[event] = fn; }
  showModal() { this.open = true; }
  close() { this.open = false; }
  focus() {}
  trigger(event) { this.listeners[event]?.(); }
}
const ids = ['petBook', 'petSearch', 'petSource', 'petElement', 'petGrid', 'petDetail', 'petCount', 'petCollectionFilter', 'petCollectionStatus', 'petMaterialFilter', 'closePetBook'];
const elements = Object.fromEntries(ids.map(id => [id, new Element()]));
for (const id of ['petSource', 'petElement', 'petCollectionFilter', 'petMaterialFilter']) elements[id].value = 'all';
const store = new Map(), localStorage = { getItem: k => store.get(k) || null, setItem: (k, v) => store.set(k, v) };
const boot = require('./bootstrap_for_tests.cjs');
const calls = [];
const window = { VQSVBootstrap: boot,
  VQSVEvolution: { leadForm: () => 'vqsv6-db-78', stoneText: () => 'Thụ chướng thạch ×3',
    options: speciesId => [{ key: speciesId, label: 'Cỏ thơm', level: 16, ok: true, reason: '', verb: 'Tiến hóa', targetName: 'Hoa ngũ sắc', mode: 'evolution' },
      { key: speciesId, label: 'Cỏ thơm', level: 16, ok: false, reason: 'thiếu Dị hoá tài liệu (0/1)', verb: 'Dị hóa', targetName: 'Hoa ăn thịt người', mode: 'mutation' }],
    evolve: (...args) => { calls.push(args); return { ok: true, message: 'đã tiến hóa', targetId: 'vqsv5-db-5' }; } } };
const document = { getElementById: id => elements[id], createElement: tag => new Element(tag) };
vm.runInNewContext(fs.readFileSync(path.join(root, 'pet_book.js'), 'utf8'), { window, document, localStorage, setTimeout: () => 1, clearTimeout: () => {} });
window.openPetBook();
const texts = () => elements.petDetail.children.map(c => (c.textContent || '')).join('\n');
elements.petSearch.value = 'Cỏ thơm'; elements.petSearch.trigger('input');
const card = elements.petGrid.children.find(c => /Cỏ thơm/.test(c.children.map(x => x.textContent).join(' ')));
assert(card, 'the VQSV5 pet card exists'); card.trigger('click');
const detail = texts();
assert.match(detail, /Thực Vật hệ · hạng 1\/5/);
assert.match(detail, /Kỹ năng tự học — cấp 7: Triền Nhiễu; cấp 14: Phong Lợi Tiêm Thứ/);
assert.match(detail, /Tiến hóa → Hoa ngũ sắc: cấp 15, 3 Thụ chướng thạch; pet mới có 1 lần tiến hóa\./);
assert.match(detail, /Dị hóa → Hoa ăn thịt người: như trên và thêm 1 Dị hoá tài liệu\./);
assert.match(detail, /Nguyên liệu đang có: Thụ chướng thạch ×3\./);
const buttons = elements.petDetail.children.filter(c => c.className === 'pet-evolve');
assert.deepEqual(buttons.map(b => [b.textContent, !!b.disabled]), [['Tiến hóa Cỏ thơm', false], ['Dị hóa Cỏ thơm', true]]);
buttons[0].trigger('click');
assert.deepEqual(calls, [['vqsv5-db-4', 'vqsv5-db-4', 'evolution']]);
assert.match(elements.petDetail.children.find(c => c.className === 'pet-evolve-note').textContent, /đã tiến hóa/);
// A final form has no evolution panel; the element filter now lists the five VQSV5 types.
elements.petSearch.value = 'Cổ Thụ Chi Vương'; elements.petSearch.trigger('input');
elements.petGrid.children.find(c => /Cổ Thụ Chi Vương/.test(c.children.map(x => x.textContent).join(' '))).trigger('click');
assert.match(texts(), /Đã là dạng cuối của chuỗi tiến hóa\./);
assert(!elements.petDetail.children.some(c => c.className === 'pet-evolve'));
assert(elements.petElement.children.some(o => o.textContent === 'Hải Dương hệ'));
console.log('VQSV5 pet book: type, rank, stats, skill unlocks and evolution panel passed.');
