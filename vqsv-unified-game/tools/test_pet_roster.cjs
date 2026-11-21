// Sủng vật screen (pet_roster.js): VQSV6 pet backpack (6) and bank (100), Xuất chiến, Gởi lại, Lấy ra, Phóng sinh.
const fs = require('fs'), vm = require('vm'), assert = require('assert'), path = require('path');
const root = path.join(__dirname, '..', 'prototype');
const html = fs.readFileSync(path.join(root, 'index.html'), 'utf8');
const code = [...html.matchAll(/<script>([\s\S]*?)<\/script>/g)].at(-1)[1].replace(/    loadData\(\)\.catch\(err => \{[^\n]+\}\);/, '');
assert(html.includes('src="pet_roster.js"') && html.includes('id="openPetRoster"'), 'roster is wired into the page');
class Element {
  constructor(tag = 'div') { this.tag = tag; this.children = []; this.listeners = {}; this.attrs = {}; this.dataset = {}; this.style = { setProperty() {}, removeProperty() {} }; this.value = 'all'; this.textContent = ''; this.hidden = false; this.disabled = false; this.open = false; }
  append(...n) { this.children.push(...n.filter(x => typeof x === 'object')); }
  replaceChildren(...n) { this.children = []; this.append(...n); }
  setAttribute(k, v) { this.attrs[k] = String(v); } getAttribute(k) { return this.attrs[k]; } removeAttribute(k) { delete this.attrs[k]; } toggleAttribute() {}
  addEventListener(e, fn) { (this.listeners[e] ||= []).push(fn); }
  click() { if (this.disabled) return; (this.listeners.click || []).forEach(fn => fn({})); this.onclick?.({}); }
  showModal() { this.open = true; } close() { this.open = false; (this.listeners.close || []).forEach(fn => fn()); }
  focus() {} querySelector() { return null; } querySelectorAll() { return []; } getContext() { return new Proxy({}, { get: () => () => ({ addColorStop() {} }) }); }
  getBoundingClientRect() { return { left: 0, top: 0, width: 100, height: 100 }; }
}
const all = node => [node, ...node.children.flatMap(all)];
const els = {}, element = id => els[id] ||= new Element();
const store = new Map(), localStorage = { getItem: k => store.get(k) ?? null, setItem: (k, v) => store.set(k, String(v)) };
const boot = { window: {} }; vm.runInNewContext(fs.readFileSync(path.join(root, 'data/bootstrap.js'), 'utf8'), boot);
const window = { addEventListener() {}, VQSVBootstrap: boot.window.VQSVBootstrap, matchMedia: () => ({ matches: false }) };
const ctx = vm.createContext({ window, document: { getElementById: element, createElement: t => new Element(t), querySelectorAll: () => [], querySelector: () => null, addEventListener() {}, body: { dataset: {} } },
  localStorage, Element, Image: class {}, performance: { now: () => 1000 }, setTimeout: () => 1, clearTimeout() {}, console });
for (const file of ['pet_book.js']) vm.runInContext(fs.readFileSync(path.join(root, file), 'utf8'), ctx);
vm.runInContext(code, ctx);
vm.runInContext(fs.readFileSync(path.join(root, 'pet_roster.js'), 'utf8'), ctx);
const run = js => vm.runInContext(js, ctx);
ctx.maps = boot.window.VQSVBootstrap.maps;
run('assets.maps = maps; assets.map = maps["slice01-border-village"]; reset()');
// Seven catches: five fill the backpack next to Lôi Miêu (6 slots), the other two go to the bank.
const ids = run('STATS.pets.slice(10, 17).map(pet => pet.pet_id)');
ids.forEach((id, i) => run(`window.VQSVPetTeam.recordCatch(${JSON.stringify(id)}, ${3 + i}, 2)`));
run('window.openPetRoster()');
assert.equal(els.petRoster.open, true);
assert.equal(els.rosterBagTab.textContent, 'Ba lô 6/6');
assert.equal(els.rosterBankTab.textContent, 'Ngân hàng 2/100');
const buttons = () => all(els.rosterDetail).filter(n => n.tag === 'button');
const press = label => { const b = buttons().find(n => n.textContent === label); assert(b, `button ${label}`); assert(!b.disabled, `${label} enabled`); b.click(); };
const pick = id => all(els.rosterList).find(n => n.dataset.id === id).click();
// Lôi Miêu: cannot be deposited or released.
assert(buttons().find(n => n.textContent === 'Gởi lại').disabled && buttons().find(n => n.textContent === 'Phóng sinh').disabled);
// Xuất chiến puts the chosen pet first in the next battle.
pick(ids[1]); press('Xuất chiến');
assert.equal(run('buildTeam()[0].id'), ids[1]);
assert.equal(run('newBattleCore().team[0].id'), ids[1]);
// The bank cannot hand out a pet while the backpack is full.
els.rosterBankTab.click();
assert(buttons().find(n => n.textContent === 'Lấy ra').disabled);
// Gởi lại moves the pet to the bank and hands the opening slot back to Lôi Miêu.
els.rosterBagTab.click(); pick(ids[1]); press('Gởi lại');
assert.equal(els.rosterBagTab.textContent, 'Ba lô 5/6');
assert.equal(els.rosterBankTab.textContent, 'Ngân hàng 3/100');
assert.equal(run('battleFirst()'), 'loi-mieu');
assert(!run('buildTeam()').some(pet => pet.id === ids[1]));
// Lấy ra brings a bank pet back while there is room.
els.rosterBankTab.click(); pick(ids[6]); press('Lấy ra');
assert.equal(els.rosterBagTab.textContent, 'Ba lô 6/6');
assert(run('buildTeam()').some(pet => pet.id === ids[6]));
// Phóng sinh asks for confirmation, then removes the pet, its catch records and its training.
els.rosterBankTab.click(); pick(ids[1]);
press('Phóng sinh');
assert.equal(run(`window.VQSVPetTeam.owned().includes(${JSON.stringify(ids[1])})`), true);
press('Xác nhận phóng sinh');
assert.equal(run(`window.VQSVPetTeam.owned().includes(${JSON.stringify(ids[1])})`), false);
assert.equal(run(`progress[${JSON.stringify(ids[1])}]`), undefined);
assert.equal(els.rosterBankTab.textContent, 'Ngân hàng 1/100');
// Not available during a battle (pets are switched there with the Sủng vật command).
els.petRoster.close(); run('state.mode = "battle"; window.openPetRoster()');
assert.equal(els.petRoster.open, false);
console.log('Pet roster: backpack 6 / bank 100, Xuất chiến order, Gởi lại, Lấy ra, Phóng sinh with confirmation, battle lock passed.');

// Ghép sủng vật: a VQSV6 pet absorbs a VQSV5 partner (both level 10+), for 100 kim tiền per partner level.
run('state.mode = "explore"; wallet.gold = 5000');
const v6 = run('STATS.pets.find(p => p.element_id === 1 && !buildTeam().some(t => t.id === p.pet_id)).pet_id');
const v5 = run('STATS5.pets.find(p => p.type_id === 0).pet_id');          // Thực Vật -> Mộc: same element as v6
run(`window.VQSVPetTeam.setTeam([]); window.VQSVPetTeam.recordCatch(${JSON.stringify(v6)}, 12, 2); window.VQSVPetTeam.recordCatch(${JSON.stringify(v5)}, 15, 2)`);
run(`progress[${JSON.stringify(v6)}] = { level: 12, exp: 0, quality: 2 }; progress[${JSON.stringify(v5)}] = { level: 15, exp: 0 }; saveProgress()`);
run('window.openPetRoster()');
pick(v6); press('Ghép');
assert.equal(els.rosterTitle.textContent, 'Ghép sủng vật');
assert(all(els.rosterList).filter(n => n.dataset.id).every(n => run(`!!STAT5_BY_ID.get(${JSON.stringify(n.dataset.id)})`)), 'only VQSV5 partners are offered');
pick(v5); press('Xác nhận ghép');
assert.equal(run(`progress[${JSON.stringify(v6)}].quality`), 3);                     // same element: quality 2 -> 3
assert(run(`progress[${JSON.stringify(v6)}].level`) >= 12);
assert.equal(run(`window.VQSVPetTeam.owned().includes(${JSON.stringify(v5)})`), false);   // the partner is used up
assert.equal(run('wallet.gold'), 5000 - 100 * 15);
assert.match(els.rosterMessage.textContent, /Ghép thành công/);
// Same game, or under level 10: refused.
ctx.p1 = run(`reserveCombatant(${JSON.stringify(v6)})`); ctx.p2 = run('buildTeam()[0]');
assert.equal(run('fusionCheck(p1, p2).ok'), false);
console.log('Pet fusion: VQSV5 partner into a VQSV6 pet, same-element quality step, cost and partner removal passed.');

// Stats screen: next-level growth, before/after stats of the evolved form, and the species' quality cap.
run('state.mode = "explore"; window.openPetRoster()');
const texts = () => all(els.rosterDetail).map(n => n.textContent).filter(Boolean);
assert(texts().some(t => /^Lên cấp \d+: HP \+\d+, Công \+\d+, Thủ \+\d+, Tốc \+\d+$/.test(t)), 'next-level growth line');
assert(texts().some(t => /^Tiến hóa → .+: HP \d+/.test(t)), 'evolution preview for Lôi Miêu (Điện Miêu evolves)');
assert(texts().some(t => /Phẩm chất ★+☆*\s\(\d\/\d\)/.test(t)), 'quality stars with the species cap');
ctx.lead = run('buildTeam()[0]');
const capped = run('(() => { const cap = STAT_BY_ID.get(lead.petId).max_quality_stars; const pet = { ...lead, quality: cap, level: 20 }; const partner = vqsv5Combatant(STATS5.pets.find(p => p.type_id === 1).pet_id, 20); partner.id = partner.petId; return [cap, fusionCheck(pet, partner).quality]; })()');
assert.equal(capped[1], capped[0], 'fusion never lifts quality above the species cap');
console.log('Pet stats: growth preview, evolution before/after, quality cap passed.');
