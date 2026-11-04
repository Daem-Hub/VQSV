const fs = require('fs');
const vm = require('vm');
const assert = require('assert');
const path = require('path');
const root = path.join(__dirname, '..', 'prototype');
class Element {
  constructor(tag = 'div') { this.tag = tag; this.children = []; this.listeners = {}; this.value = ''; this.open = false; }
  append(...nodes) { this.children.push(...nodes); }
  replaceChildren(...nodes) { this.children = nodes; }
  setAttribute(key, value) { this[key] = value; }
  addEventListener(event, fn) { this.listeners[event] = fn; }
  showModal() { this.open = true; }
  close() { this.open = false; this.listeners.close?.(); }
  focus() {}
  trigger(event) { this.listeners[event]?.(); }
}
const ids = ['petBook', 'petSearch', 'petSource', 'petElement', 'petGrid', 'petDetail', 'petCount', 'petCollectionFilter', 'petCollectionStatus', 'petMaterialFilter', 'closePetBook'];
const elements = Object.fromEntries(ids.map(id => [id, new Element()]));
for (const id of ['petSource', 'petElement', 'petCollectionFilter', 'petMaterialFilter']) elements[id].value = 'all';
const store = new Map();
const storage = { getItem: key => store.get(key) || null, setItem: (key, value) => store.set(key, value) };
const bootstrapContext = { window: {} };
vm.runInNewContext(fs.readFileSync(path.join(root, 'data/bootstrap.js'), 'utf8'), bootstrapContext);
function start(localStorage = storage) {
  const window = { VQSVBootstrap: bootstrapContext.window.VQSVBootstrap };
  const document = { getElementById: id => elements[id], createElement: tag => new Element(tag) };
  vm.runInNewContext(fs.readFileSync(path.join(root, 'pet_book.js'), 'utf8'), { window, document, localStorage, setTimeout: () => 1, clearTimeout: () => {} });
  window.openPetBook();
}
start();
assert.match(elements.petCount.textContent, /^202 pet/);
elements.petMaterialFilter.value = 'Tinh Nguyên Thạch';
elements.petMaterialFilter.trigger('change');
assert.match(elements.petCount.textContent, /^18 pet/);
assert(elements.petDetail.children.find(e => e.className === 'pet-target'));
elements.petDetail.children.find(e => e.className === 'pet-target').trigger('click');
assert.equal(elements.petMaterialFilter.value, 'all');
assert.match(elements.petCount.textContent, /^202 pet/);
elements.petDetail.children.find(e => e.className === 'pet-collect').trigger('click');
assert.match(elements.petCollectionStatus.textContent, /Đã gom 1 pet/);
elements.petCollectionFilter.value = 'saved';
elements.petCollectionFilter.trigger('change');
assert.match(elements.petCount.textContent, /^1 pet/);
elements.petBook.close();
start();
assert.match(elements.petCount.textContent, /^1 pet/);
elements.petDetail.children.find(e => e.className === 'pet-collect').trigger('click');
assert.match(elements.petCount.textContent, /^0 pet/);
elements.petBook.close();
elements.petCollectionFilter.value = 'all';
start({ getItem() { throw Error('blocked'); }, setItem() { throw Error('blocked'); } });
elements.petDetail.children.find(e => e.className === 'pet-collect').trigger('click');
assert.match(elements.petCollectionStatus.textContent, /chỉ giữ trong phiên hiện tại/);
console.log('Pet collection: add, filter, reload, remove and storage failure passed.');
