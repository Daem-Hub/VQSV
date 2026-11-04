const fs=require('fs'),path=require('path'),vm=require('vm'),assert=require('assert');
const root=path.join(__dirname,'..','prototype');
class El {constructor(){this.children=[];this.listeners={};this.open=false;} append(...x){this.children.push(...x)} replaceChildren(...x){this.children=x} setAttribute(k,v){this[k]=v} addEventListener(k,v){this.listeners[k]=v} showModal(){this.open=true} close(){this.open=false}}
const ids=['worldAtlas','worldRegions','worldRegionDetail','closeWorldAtlas'], els=Object.fromEntries(ids.map(x=>[x,new El()]));
const window={},document={getElementById:id=>els[id],createElement:()=>new El()};
vm.runInNewContext(fs.readFileSync(path.join(root,'world_atlas.js'),'utf8'),{window,document});
assert(fs.existsSync(path.join(root,'assets/world/world_map_concept.png')));
assert.equal(els.worldRegions.children.length,12);
window.openWorldAtlas(); assert(els.worldAtlas.open); assert.match(els.worldRegionDetail.children[1].textContent,/Đã có map chơi thử/);
els.worldRegions.children[11].listeners.click(); assert.equal(els.worldRegionDetail.children[0].textContent,'Mặt Trăng Đỏ'); assert.match(els.worldRegionDetail.children[1].textContent,/Chưa có map/);
els.closeWorldAtlas.listeners.click(); assert(!els.worldAtlas.open);
console.log('World atlas: 12 regions, selection and accurate playable status passed.');
