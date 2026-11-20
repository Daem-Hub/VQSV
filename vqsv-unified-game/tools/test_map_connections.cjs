const fs = require('fs'), path = require('path'), vm = require('vm'), assert = require('assert');
const root = path.join(__dirname, '..', 'prototype');
const html = fs.readFileSync(path.join(root, 'index.html'), 'utf8');
const code = [...html.matchAll(/<script>([\s\S]*?)<\/script>/g)].at(-1)[1].replace(/    loadData\(\)\.catch\(err => \{[^\n]+\}\);/, '');
const bootCtx = {window:{}}; vm.runInNewContext(fs.readFileSync(path.join(root,'data/bootstrap.js'),'utf8'), bootCtx);
const maps = bootCtx.window.VQSVBootstrap.maps;
const els = {}, element = id => els[id] ||= {textContent:'',hidden:false,addEventListener(){},getContext(){return {}}};
const storage = new Map(), localStorage = {getItem:k=>storage.get(k)||null,setItem:(k,v)=>storage.set(k,v)};
const ctx = vm.createContext({document:{getElementById:element},window:{addEventListener(){}},performance:{now:()=>1000},Element:class{},console,localStorage,maps});
vm.runInContext(code,ctx); vm.runInContext('assets.maps=maps; assets.map=maps["slice01-border-village"]; reset()',ctx);
vm.runInContext('state.player.x=22; state.player.y=10; interact()',ctx);
assert.equal(vm.runInContext('assets.map.id',ctx),'slice01-border-village');
vm.runInContext('state.spokeToElder=true; interact()',ctx);
assert.equal(vm.runInContext('assets.map.id',ctx),'ryan-phong-loi-forest');
assert.equal(vm.runInContext('blocked(state.player.x,state.player.y)',ctx),false);
vm.runInContext('state.player.x=22; state.player.y=10; interact()',ctx);
assert.equal(vm.runInContext('assets.map.id',ctx),'ryan-forest-stream');
assert.equal(vm.runInContext('blocked(10,5)',ctx),true);
assert.equal(vm.runInContext('blocked(10,10)',ctx),false);
vm.runInContext('state.player.x=12; state.player.y=10; saveGame(); reset(); loadGame()',ctx);
assert.equal(vm.runInContext('assets.map.id',ctx),'ryan-forest-stream');
vm.runInContext('state.player.x=1; state.player.y=10; interact()',ctx);
assert.equal(vm.runInContext('assets.map.id',ctx),'ryan-phong-loi-forest');
vm.runInContext('state.player.x=12; state.player.y=10; saveGame(); reset(); loadGame()',ctx);
assert.equal(vm.runInContext('assets.map.id',ctx),'ryan-phong-loi-forest');
assert.equal(vm.runInContext('state.player.x',ctx),12);
vm.runInContext('state.player.x=1; state.player.y=10; interact()',ctx);
assert.equal(vm.runInContext('assets.map.id',ctx),'slice01-border-village');
assert.equal(vm.runInContext('blocked(state.player.x,state.player.y)',ctx),false);
console.log('Map connections: story gate, two-way travel, unblocked spawn, save and restore passed.');

// Exercise the quest over the same map links that the player uses.
const run = input => vm.runInContext(input, ctx);
function talkToScout() {
  run(`{
    const scout = assets.map.entities.villagers.find(npc => npc.id === "scout");
    state.player.x = scout.x;
    state.player.y = scout.y + 1;
    interact();
  }`);
  assert.equal(run('state.activeNpc.id'), 'scout');
  const lines = run('state.activeNpc.dialogue.length');
  for (let i = 0; i < lines; i++) run('interact()');
}
run('reset()');
talkToScout();
assert.equal(run('state.survey'), 'unstarted');
run('state.spokeToElder = true');
talkToScout();
assert.equal(run('state.survey'), 'active');
run('state.player.x=22; state.player.y=10; interact()');
run('state.player.x=22; state.player.y=10; interact()');
run('state.player.x=5; state.player.y=10; interact()');
assert.equal(run('state.survey'), 'active');
run('state.player.x=11; state.player.y=10; interact(); saveGame(); reset(); loadGame()');
assert.equal(run('state.survey'), 'return');
assert.equal(run('assets.map.id'), 'ryan-forest-stream');
run('state.player.x=1; state.player.y=10; interact()');
run('state.player.x=1; state.player.y=10; interact()');
talkToScout();
assert.equal(run('state.survey'), 'done');
const slot = run('SAVE_KEY');
const legacySave = JSON.parse(storage.get(slot));
delete legacySave.survey;
storage.set(slot, JSON.stringify(legacySave));
run('loadGame()');
assert.equal(run('state.survey'), 'unstarted');
assert.equal(run('assets.map.id'), 'ryan-forest-stream');
console.log('Survey quest: gated dialogue, bridge proximity, return trip, save/restore and legacy save passed.');

// Holding the stick through an unlocked border carries direction to its safe arrival tile.
run('reset(); state.player.x=23; state.player.y=10; setMove(1,0)');
assert.equal(run('assets.map.id'), 'slice01-border-village');
run('state.spokeToElder=true; setMove(1,0)');
assert.equal(run('assets.map.id'), 'ryan-phong-loi-forest');
assert.equal(run('state.player.inputDir.dx'), 1);
run('releaseMove(1,0)');
assert.equal(run('state.player.inputDir'), null);
