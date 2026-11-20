(() => {
  const byId = id => document.getElementById(id);
  const chart = byId("localMap"), pen = chart.getContext("2d");
  let selected = null, tracking = null, fieldPath = [], preview = [], places = [];

  // Both the map and the ground markers follow the same collision rules as Kael.
  const roadCosts = new WeakMap();
  function terrainCosts(map) {
    if (roadCosts.has(map)) return roadCosts.get(map);
    const costs = new Uint8Array(map.width * map.height).fill(map.trails ? 4 : 1);
    for (const trail of map.trails || []) {
      for (let i = 1; i < trail.points.length; i++) {
        const [ax,ay] = trail.points[i-1], [bx,by] = trail.points[i], dx = bx-ax, dy = by-ay;
        for (let y=0; y<map.height; y++) for (let x=0; x<map.width; x++) {
          const t = Math.max(0, Math.min(1, ((x-ax)*dx + (y-ay)*dy) / (dx*dx+dy*dy || 1)));
          if (Math.hypot(x-ax-t*dx, y-ay-t*dy) <= trail.width / 2 + .15) costs[y*map.width+x] = 1;
        }
      }
    }
    roadCosts.set(map,costs); return costs;
  }
  function routeTo(point) {
    const map = assets.map, start = state.player.y * map.width + state.player.x;
    const costs = terrainCosts(map), pending = [{cell:start,cost:0}];
    const parents = new Map(), best = new Map([[start,0]]);
    while (pending.length) {
      pending.sort((a,b) => b.cost-a.cost);
      const {cell,cost} = pending.pop();
      if (cost !== best.get(cell)) continue;
      const x = cell % map.width, y = Math.floor(cell / map.width);
      if (Math.abs(point.x-x) + Math.abs(point.y-y) <= 1) {
        const path = [];
        for (let n=cell; n!==start; n=parents.get(n)) path.push({x:n%map.width,y:Math.floor(n/map.width)});
        return path.reverse();
      }
      for (const [nx,ny] of [[x+1,y],[x-1,y],[x,y+1],[x,y-1]]) {
        if (nx<0 || ny<0 || nx>=map.width || ny>=map.height) continue;
        const next = ny*map.width+nx, nextCost = cost+costs[next];
        if (nextCost >= (best.get(next) ?? Infinity) || blocked(nx,ny)) continue;
        best.set(next,nextCost); parents.set(next,cell); pending.push({cell:next,cost:nextCost});
      }
    }
    return null;
  }
  function destinations(map, target) {
    const entries = (map.links || []).map(link => ({...link, id:link.target, locked:!link.kind && !state.spokeToElder}));
    if (target && !entries.some(place => place.x === target.x && place.y === target.y)) {
      entries.push({...target, id:target.id || "objective", label:target.name || (map.bridge ? "Khảo sát cầu" : "Dấu vết lạ")});
    }
    return entries;
  }
  function box(x,y,w,h,color) { pen.fillStyle = color; pen.fillRect(x,y,w,h); }
  function paintMap(map, player, target, herbs, background) {
    pen.setTransform(1,0,0,1,0,0);
    pen.clearRect(0,0,chart.width,chart.height);
    // Fit the whole map in the chart: real VQSV5 maps are larger or squarer than the 24 × 18 village.
    const t = map.tileSize, mw = map.width*t, mh = map.height*t, k = Math.min(chart.width/mw, chart.height/mh);
    pen.setTransform(k,0,0,k,Math.round((chart.width-mw*k)/2),Math.round((chart.height-mh*k)/2));
    pen.imageSmoothingEnabled = false;
    if (background) pen.drawImage(background,0,0,mw,mh);
    else if (map.interior) window.drawShopRoom(pen,map);
    for (const link of map.links || []) {
      const x=link.x*t+10, y=link.y*t+10;
      box(x-5,y-5,11,11,"#1c3441"); box(x-1,y-4,3,9,"#adf5ff"); box(x-4,y-1,9,3,"#adf5ff");
    }
    for (const herb of herbs) {
      const x=herb.x*t+10, y=herb.y*t+10;
      pen.beginPath(); pen.moveTo(x,y-6); pen.lineTo(x+6,y+5); pen.lineTo(x-6,y+5); pen.closePath();
      pen.fillStyle="#d4ff8d"; pen.fill(); pen.strokeStyle="#316733"; pen.stroke();
    }
    byId("herbLegend").hidden = !herbs.length;
    if (target) {
      pen.save(); pen.translate((target.x+.5)*t,(target.y+.5)*t); pen.rotate(Math.PI/4);
      box(-5,-5,10,10,"#813e30"); box(-3,-3,6,6,"#ffbc69"); pen.restore();
    }
    for (const step of preview || []) {
      box((step.x+.5)*t-3,(step.y+.5)*t-3,6,6,"#264759");
      box((step.x+.5)*t-2,(step.y+.5)*t-2,4,4,"#fff49b");
    }
    if (selected) {
      const x = selected.x*t+2, y = selected.y*t+2;
      pen.strokeStyle = "#193a4a"; pen.lineWidth = 4; pen.strokeRect(x,y,t-4,t-4);
      pen.strokeStyle = "#ffe57b"; pen.lineWidth = 2; pen.strokeRect(x,y,t-4,t-4);
    }
    const x=(player.x+.5)*t, y=(player.y+.5)*t;
    pen.beginPath();pen.arc(x,y,6,0,Math.PI*2);pen.fillStyle="#f9ffff";pen.fill();
    pen.strokeStyle="#163349";pen.lineWidth=3;pen.stroke(); box(x-2,y-2,4,4,"#1784ba");
    byId("mapCaption").textContent = "Kael · " + player.x + ", " + player.y;
    pen.setTransform(1,0,0,1,0,0);
  }
  function describe() {
    for (const row of byId("mapExits").children) row.setAttribute("aria-pressed",String(row.dataset.place === selected?.id));
    const trackingHere = selected && tracking?.mapId === assets.map.id && tracking.point.id === selected.id;
    const button = byId("trackPlace");
    button.hidden = !selected;
    button.disabled = !trackingHere && (selected?.locked || !preview?.length || state.mode !== "explore");
    button.textContent = trackingHere ? "Tắt chỉ đường" : "Chỉ đường";
    byId("routeHint").textContent = !selected ? "" : selected.locked ? "Gặp người gác làng để mở cổng." :
      preview === null ? "Chưa tìm thấy lối đi." : preview.length ? preview.length + " bước đến " + selected.label.toLocaleLowerCase("vi") + "." : "Kael đã ở cạnh điểm này.";
  }
  window.drawFieldMap = (map, player, target, herbs, background) => {
    if (tracking) {
      if (tracking.mapId !== map.id || state.mode !== "explore") { tracking = null; fieldPath = []; }
      else {
        fieldPath = routeTo(tracking.point) || [];
        if (!fieldPath.length) tracking = null;
      }
    }
    if (!byId("worldAtlas").open || byId("localPage").hidden) return;
    preview = selected ? routeTo(selected) : null;
    paintMap(map, player, target, herbs, background); describe();
  };
  window.showLocalMap = preferred => {
    const target = mapTarget();
    places = destinations(assets.map,target);
    selected = places.find(place => place.id === (preferred || tracking?.point.id)) || places.find(p => p.x === target?.x && p.y === target?.y) || places[0];
    const list = byId("mapExits"); list.replaceChildren();
    for (const place of places) {
      const row = document.createElement("button");
      row.className = "v5-choice"; row.type = "button"; row.dataset.place = place.id;
      row.textContent = place.label;
      row.onclick = () => { selected = place; refreshFieldMap(); };
      list.append(row);
    }
    refreshFieldMap();
  };
  byId("trackPlace").onclick = () => {
    if (!selected || selected.locked || state.mode !== "explore") return;
    if (tracking?.mapId === assets.map.id && tracking.point.id === selected.id) {
      tracking = null; fieldPath = []; refreshFieldMap(); return;
    }
    if (!preview?.length) return;
    tracking = {mapId:assets.map.id, point:selected}; fieldPath = preview;
    byId("worldAtlas").close();
    if (byId("gameMenu").open) byId("gameMenu").close();
  };
  chart.addEventListener("click", event => {
    const rect = chart.getBoundingClientRect(), map = assets.map;
    const t = map.tileSize, k = Math.min(chart.width/(map.width*t), chart.height/(map.height*t));
    const cx = (event.clientX-rect.left)/rect.width*chart.width - (chart.width-map.width*t*k)/2;
    const cy = (event.clientY-rect.top)/rect.height*chart.height - (chart.height-map.height*t*k)/2;
    const x = cx/(t*k)-.5, y = cy/(t*k)-.5;
    const nearest = places.reduce((prev,p) => Math.hypot(p.x-x,p.y-y) < Math.hypot(prev.x-x,prev.y-y) ? p : prev, places[0]);
    if (nearest && Math.hypot(nearest.x-x,nearest.y-y) <= 1.75) { selected=nearest; refreshFieldMap(); }
  });
  window.clearTrailGuide = () => { tracking = null; fieldPath = []; };
  window.drawTrailGuide = (context, tile) => {
    for (const step of fieldPath) {
      const x=(step.x+.5)*tile, y=(step.y+.5)*tile;
      context.fillStyle="#334835"; context.fillRect(x-2,y-2,5,5);
      context.fillStyle="#ffe695"; context.fillRect(x-1,y-1,3,3);
    }
  };
})();
