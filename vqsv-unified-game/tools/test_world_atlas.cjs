const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const assert = require('node:assert/strict');
const root = path.join(__dirname, '..', 'prototype');
class Element {
  children = []; listeners = {}; style = {}; classList = {toggle() {}};
  append(...items) { this.children.push(...items); }
  replaceChildren() { this.children = []; }
  setAttribute(name,value) { this[name] = value; }
  addEventListener(name,fn) { this.listeners[name] = fn; }
  querySelector() { return this.label ||= new Element(); }
  showModal() { this.open = true; }
  close() { this.open = false; }
  focus() {}
}
const els = {}, document = {
  getElementById(id) { return els[id] ||= new Element(); },
  createElement() { return new Element(); }
};
let preferred;
const window = {showLocalMap(id) { preferred = id; }};
vm.runInNewContext(fs.readFileSync(path.join(root,'data/bootstrap.js'),'utf8'), {window});
vm.runInNewContext(fs.readFileSync(path.join(root,'world_atlas.js'),'utf8'), {window,document});
assert(fs.existsSync(path.join(root,'assets/world/world_map_concept.png')));
assert.equal(els.worldPins.children.length,12);
assert.equal(els.regionSelect.children.length,12);
window.openWorldAtlas('village-apothecary');
assert(els.worldAtlas.open && !els.localPage.hidden);
els.worldTab.onclick();
assert(!els.worldPage.hidden);
assert.match(els.worldRegionDetail.children[1].textContent,/Kael đang ở đây/);
els.worldPins.children[1].listeners.click();
els.worldRegionDetail.children.at(-1).onclick();
assert.equal(preferred,'slice01-border-village');
els.worldTab.onclick();
els.regionSelect.value = '11';
els.regionSelect.listeners.change();
assert.equal(els.worldRegionDetail.children[0].textContent,'Mặt Trăng Đỏ');
assert.match(els.worldRegionDetail.children[1].textContent,/Chưa mở/);
els.closeWorldAtlas.listeners.click();
assert(!els.worldAtlas.open);
console.log('World atlas: both views, 12 selectable pins, current interiors, first connecting exit and planned regions passed.');
