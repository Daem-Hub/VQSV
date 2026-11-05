const fs=require('fs'),path=require('path'),vm=require('vm'),assert=require('assert');
const root=path.join(__dirname,'..','prototype'), html=fs.readFileSync(path.join(root,'index.html'),'utf8');
const code=[...html.matchAll(/<script>([\s\S]*?)<\/script>/g)].at(-1)[1].replace(/    loadData\(\)\.catch\(err => \{[^\n]+\}\);/,'');
class El {constructor(){this.hidden=false;this.open=false;this.children=[];this.textContent='';} addEventListener(){} getContext(){return {}} replaceChildren(){this.children=[]} append(x){this.children.push(x)} showModal(){this.open=true} close(){this.open=false}}
const els={}; const el=id=>els[id]||=new El(), storage=new Map();
const localStorage={getItem:k=>storage.get(k)||null,setItem:(k,v)=>storage.set(k,v)};
const map=JSON.parse(fs.readFileSync(path.join(root,'data/opening_map.json'),'utf8'));
const ctx=vm.createContext({document:{getElementById:el,createElement:()=>new El()},window:{addEventListener(){},VQSVBootstrap:require('./bootstrap_for_tests.cjs')},performance:{now:()=>1000},Element:class{},console,localStorage,map});
vm.runInContext(code,ctx);vm.runInContext('assets.map=map; reset()',ctx);
const blocked=new Set(map.objects.flatMap(o=>o.solid||[]).map(([x,y])=>`${x},${y}`));
for(const npc of [map.entities.elder,...map.entities.villagers]) blocked.add(`${npc.x},${npc.y}`);
const queue=[[map.entities.player.x,map.entities.player.y]], reachable=new Set(queue.map(p=>p.join(',')));
for(let i=0;i<queue.length;i++){const [x,y]=queue[i];for(const [nx,ny] of [[x-1,y],[x+1,y],[x,y-1],[x,y+1]]){const key=`${nx},${ny}`;if(nx>=0&&ny>=0&&nx<map.width&&ny<map.height&&!blocked.has(key)&&!reachable.has(key)){reachable.add(key);queue.push([nx,ny]);}}}
for(const [x,y] of [[10,14],[14,13],[18,14]]) assert(reachable.has(`${x},${y}`), `Unreachable herb ${x},${y}`);
// NPC offering the quest only changes its stage after the complete conversation.
vm.runInContext('state.player.x=9;state.player.y=11;interact();interact()',ctx);
assert.equal(vm.runInContext('state.herbQuest.stage',ctx),'unstarted');
vm.runInContext('interact()',ctx);assert.equal(vm.runInContext('state.herbQuest.stage',ctx),'active');
for(const [x,y] of [[10,14],[14,13],[18,14]]) {
  ctx.x=x;ctx.y=y;
  assert.equal(vm.runInContext('blocked(x,y)',ctx),false);
  vm.runInContext('state.player.x=x;state.player.y=y;interact()',ctx);
}
assert.equal(vm.runInContext('state.herbQuest.collected.length',ctx),3);
vm.runInContext('interact()',ctx); assert.equal(vm.runInContext('state.herbQuest.collected.length',ctx),3);
vm.runInContext('saveGame();reset();loadGame()',ctx);
assert.equal(vm.runInContext('state.herbQuest.collected.length',ctx),3);
vm.runInContext('state.player.x=9;state.player.y=11;interact();interact();interact()',ctx);
assert.equal(vm.runInContext('state.herbQuest.stage',ctx),'done');
vm.runInContext('saveGame();reset();loadGame();openQuestJournal()',ctx);
assert.match(el('herbQuestText').textContent,/Đã mang/);
// Old saves have no side-quest field and still load.
let saved=JSON.parse(storage.get('vqsv-unified-story-save-v1'));delete saved.herbQuest;storage.set('vqsv-unified-story-save-v1',JSON.stringify(saved));
vm.runInContext('loadGame()',ctx);assert.equal(vm.runInContext('state.herbQuest.stage',ctx),'unstarted');
console.log('Herb quest: accept, collect uniquely, save, complete and legacy saves passed.');
