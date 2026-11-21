const fs = require('fs'), path = require('path'), vm = require('vm'), assert = require('assert');
const root = path.join(__dirname, '..', 'prototype');
const html = fs.readFileSync(path.join(root, 'index.html'), 'utf8');
const code = [...html.matchAll(/<script>([\s\S]*?)<\/script>/g)].at(-1)[1].replace(/    loadData\(\)\.catch\(err => \{[^\n]+\}\);/, '');
const bootCtx = {window:{}}; vm.runInNewContext(fs.readFileSync(path.join(root,'data/bootstrap.js'),'utf8'), bootCtx);
const maps = bootCtx.window.VQSVBootstrap.maps;
const els = {}, element = id => els[id] ||= {textContent:'',hidden:false,addEventListener(){},getContext(){return {}}};
const storage = new Map(), localStorage = {getItem:k=>storage.get(k)||null,setItem:(k,v)=>storage.set(k,v)};
const ctx = vm.createContext({document:{getElementById:element},window:{addEventListener(){},VQSVBootstrap:require('./bootstrap_for_tests.cjs')},performance:{now:()=>1000},Element:class{},Image:class{},console,localStorage,maps});
vm.runInContext(code,ctx); vm.runInContext('assets.maps=maps; assets.map=maps["slice01-border-village"]; reset()',ctx);
const run = js => vm.runInContext(js, ctx);
run('globalThis.travelTo = target => { const l = assets.map.links.find(k => k.target === target); state.player.x = l.x; state.player.y = l.y; interact(); }');
run('state.player.x=22; state.player.y=10; interact()');
assert.equal(run('assets.map.id'),'slice01-border-village');            // the guard must be met first
run('state.spokeToElder=true; interact()');
assert.equal(run('assets.map.id'),'ryan-phong-loi-forest');
assert.equal(run('blocked(state.player.x,state.player.y)'),false);
assert.deepEqual([run('state.player.x'), run('state.player.y')], maps['ryan-phong-loi-forest'].arrivals['slice01-border-village']);
run('travelTo("ryan-forest-stream")');
assert.equal(run('assets.map.id'),'ryan-forest-stream');
assert.equal(run('blocked(state.player.x,state.player.y)'),false);
// collision of the real VQSV5 map: 1 blocks, 0 is free
const stream = maps['ryan-forest-stream'];
const wall = (() => { for (let y = 0; y < stream.height; y++) { const x = stream.collision[y].indexOf('1'); if (x >= 0) return [x, y]; } })();
assert.equal(run(`blocked(${wall[0]},${wall[1]})`), true);
assert.equal(run(`blocked(${stream.entities.player.x},${stream.entities.player.y})`), false);
const spot = stream.arrivals['ryan-phong-loi-forest'];
run(`state.player.x=${spot[0]}; state.player.y=${spot[1]}; saveGame(); reset(); loadGame()`);
assert.equal(run('assets.map.id'),'ryan-forest-stream');
run('travelTo("ryan-phong-loi-forest")');
assert.equal(run('assets.map.id'),'ryan-phong-loi-forest');
assert.deepEqual([run('state.player.x'), run('state.player.y')], maps['ryan-phong-loi-forest'].arrivals['ryan-forest-stream']);
run('saveGame(); reset(); loadGame()');
assert.equal(run('assets.map.id'),'ryan-phong-loi-forest');
assert.deepEqual([run('state.player.x'), run('state.player.y')], maps['ryan-phong-loi-forest'].arrivals['ryan-forest-stream']);
run('travelTo("slice01-border-village")');
assert.equal(run('assets.map.id'),'slice01-border-village');
assert.equal(run('blocked(state.player.x,state.player.y)'),false);
console.log('Map connections: story gate, two-way travel, unblocked spawn, save and restore passed.');

// "Khảo sát Suối Sấm": gated dialogue, the survey spot on the real VQSV5 stream map, the return trip and old saves.
function talkToScout() {
  run('{ const scout = assets.map.entities.villagers.find(npc => npc.id === "scout"); state.player.x = scout.x; state.player.y = scout.y + 1; interact(); }');
  assert.equal(run('state.activeNpc.id'), 'scout');
  const lines = run('state.activeNpc.dialogue.length');
  for (let i = 0; i < lines; i++) run('interact()');
}
run('reset()');
talkToScout();
assert.equal(run('state.survey'), 'unstarted');                 // before the guard, the scout only chats
run('state.spokeToElder = true');
talkToScout();
assert.equal(run('state.survey'), 'active');
run('travelTo("ryan-phong-loi-forest"); travelTo("ryan-forest-stream")');
const point = maps['ryan-forest-stream'].surveyPoint;
assert(point && !run(`blocked(${point.x},${point.y})`), 'survey point is walkable');
run(`state.player.x=${point.x}; state.player.y=${point.y}; interact()`);
assert.equal(run('state.survey'), 'return');
run('saveGame(); reset(); loadGame()');
assert.equal(run('state.survey'), 'return');
assert.equal(run('assets.map.id'), 'ryan-forest-stream');
run('travelTo("ryan-phong-loi-forest"); travelTo("slice01-border-village")');
talkToScout();
assert.equal(run('state.survey'), 'done');
const slot = run('SAVE_KEY');
const legacySave = JSON.parse(storage.get(slot));
delete legacySave.survey;
storage.set(slot, JSON.stringify(legacySave));
run('loadGame()');
assert.equal(run('state.survey'), 'unstarted');
console.log('Survey quest: gated dialogue, VQSV5 stream survey spot, return trip, save/restore and legacy save passed.');

// Shop doors are always open and lead into their interiors; the exit returns to the door.
run('reset()');
const door = maps['slice01-border-village'].links.find(link => link.kind === 'door');
run(`state.player.x=${door.x}; state.player.y=${door.y + 1}; interact()`);
assert.equal(run('assets.map.id'), door.target);
assert.equal(run('blocked(state.player.x,state.player.y)'), false);
run('{ const exit = assets.map.links.find(link => link.kind === "exit"); state.player.x = exit.x; state.player.y = exit.y - 1; interact(); }');
assert.equal(run('assets.map.id'), 'slice01-border-village');
assert.deepEqual([run('state.player.x'), run('state.player.y')], [door.x, door.y]);

// Holding a direction through an unlocked border carries it to the next map.
run('reset(); state.player.x=23; state.player.y=10; setMove(1,0)');
assert.equal(run('assets.map.id'), 'slice01-border-village');
run('state.spokeToElder=true; state.player.inputDir=null; setMove(1,0)');
assert.equal(run('assets.map.id'), 'ryan-phong-loi-forest');
assert.equal(run('state.player.inputDir.dx'), 1);
run('releaseMove(1,0)');
assert.equal(run('state.player.inputDir'), null);
console.log('Doors, interiors and walking through open borders passed.');

// Walkable cells must connect every painted entrance to its exit and allow a return trip.
const atlas = Object.values(maps).filter(map => map.illustration && map.source5);
assert.equal(atlas.length, 29);
function canReach(point) {
  const [x,y] = run('[state.player.x,state.player.y]');
  const queue = [[x,y]], seen = new Set([x+','+y]);
  for (const [cx,cy] of queue) {
    if (cx === point.x && cy === point.y) return true;
    for (const [nx,ny] of [[cx+1,cy],[cx-1,cy],[cx,cy+1],[cx,cy-1]]) {
      const key = nx+','+ny;
      if (seen.has(key) || run(`blocked(${nx},${ny})`)) continue;
      seen.add(key); queue.push([nx,ny]);
    }
  }
  return false;
}
run('reset(); state.spokeToElder=true');
const journey = [...atlas, ...atlas.slice(0,-1).reverse(), maps['slice01-border-village']];
for (const next of journey) {
  const gate = maps[run('assets.map.id')].links.find(link => link.target === next.id);
  assert(gate && canReach(gate), 'walking to ' + next.id);
  run(`travelTo(${JSON.stringify(next.id)})`);
  assert.equal(run('assets.map.id'), next.id);
  assert(!run('blocked(state.player.x,state.player.y)'));
  if (next === atlas.at(-1)) {
    run('saveGame(); reset(); loadGame()');
    assert.equal(run('assets.map.id'), next.id);
  }
}
console.log('All 29 atlas scenes reachable on foot in both directions, including save and restore on the Red Moon.');

assert.equal(new Set(atlas.map(m => m.source5.map)).size, atlas.length);
let gateCount = 0;
for (const map of atlas) {
  run(`assets.map=maps[${JSON.stringify(map.id)}]; Object.assign(state.player,assets.map.entities.player)`);
  for (const gate of map.links) {
    assert(canReach(gate), map.id + ' to ' + gate.target);
    assert(maps[gate.target].links.some(link => link.target === map.id));
    gateCount++;
  }
  for(const picture of [map.source5.base,map.source5.overlay].filter(Boolean)) assert(fs.existsSync(path.join(root,picture)));
}
assert.equal(gateCount, 57 + 2 * 8 + 5);

const frontier = Object.values(maps).filter(map => map.fieldArt);
assert.equal(frontier.length, 6);
for (const map of frontier) {
  assert(map.width >= 48 && map.height >= 38);
  assert(fs.existsSync(path.join(root,map.fieldArt)));
  for (const gate of map.links) {
    const previous = maps[gate.target], entrance = previous.links.find(link => link.target === map.id);
    assert(entrance, map.id + ' has a reciprocal gate to ' + previous.id);
    run(`assets.map=maps[${JSON.stringify(previous.id)}]; Object.assign(state.player,assets.map.entities.player)`);
    assert(canReach(entrance), previous.id + ' can reach ' + map.id);
    run(`travelTo(${JSON.stringify(map.id)})`);
    assert.equal(run('assets.map.id'),map.id);
    assert(!run('blocked(state.player.x,state.player.y)'));
    for (const exit of map.links) assert(canReach(exit), map.id + ' can reach ' + exit.target);
    run(`travelTo(${JSON.stringify(previous.id)})`);
    assert.equal(run('assets.map.id'),previous.id);
    assert(!run('blocked(state.player.x,state.player.y)'));
  }
}
