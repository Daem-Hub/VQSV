const fs=require('fs'),path=require('path'),vm=require('vm'),assert=require('assert');
const root=path.join(__dirname,'..','prototype');
const html=fs.readFileSync(path.join(root,'index.html'),'utf8');
const code=[...html.matchAll(/<script>([\s\S]*?)<\/script>/g)].at(-1)[1].replace(/    loadData\(\)\.catch\(err => \{[^\n]+\}\);/,'');
class El { constructor(){this.textContent='';this.hidden=false;this.open=false;this.children=[];this.listeners={};} addEventListener(k,v){this.listeners[k]=v;} getContext(){return {};} replaceChildren(){this.children=[];} append(x){this.children.push(x);} showModal(){this.open=true;} close(){this.open=false;} }
const els={};const el=id=>els[id]||=new El();
const storage=new Map(); const localStorage={getItem:k=>storage.get(k)||null,setItem:(k,v)=>storage.set(k,v)};
const map=JSON.parse(fs.readFileSync(path.join(root,'data/opening_map.json'),'utf8'));
const ctx=vm.createContext({document:{getElementById:el,createElement:()=>new El()},window:{addEventListener(){}},performance:{now:()=>1000},Element:class{},console,localStorage,map});
vm.runInContext(code,ctx);vm.runInContext('assets.map=map; reset(); openQuestJournal()',ctx);
assert.equal(el('questJournal').open,true);assert.deepEqual(el('questSteps').children.map(x=>x.className),['current','pending','pending']);
el('questJournal').close();vm.runInContext('state.spokeToElder=true; saveGame(); reset(); loadGame(); openQuestJournal()',ctx);
assert.deepEqual(el('questSteps').children.map(x=>x.className),['done','current','pending']);
el('questJournal').close();vm.runInContext('state.mode="ending"; saveGame(); reset(); loadGame(); openQuestJournal()',ctx);
assert.deepEqual(el('questSteps').children.map(x=>x.className),['done','done','done']);
console.log('Quest journal: initial, saved progress and completed states passed.');
