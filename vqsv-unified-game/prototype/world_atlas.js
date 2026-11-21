(() => {
  const book = document.getElementById('worldAtlas');
  const byId = id => document.getElementById(id);
  const list = byId('worldPins'), selector = byId('regionSelect');
  const detail = document.getElementById('worldRegionDetail');
  const regions = [
    {maps:['slice01-border-village','village-apothecary','village-charms'],name:'Làng biên giới sương mù',area:'Lục địa Ryan',description:'Nơi Kael và Lôi Miêu bắt đầu hành trình, gặp người gác làng và lần theo dấu vết.'},
    {maps:['ryan-phong-loi-forest','ryan-forest-stream'],name:'Rừng Rậm Phong Lôi',area:'Lục địa Ryan',description:'Từ cổng đông của làng, đi qua đường rừng đến cầu gỗ Suối Sấm. Người đi rừng trong làng phụ trách chuyến khảo sát.'},
    {name:'Hoang Mạc Xích Diễm',area:'Lục địa Ryan',description:'Vùng cát đỏ dẫn tới Hỏa Sơn Bất Diệt.'},
    {name:'Băng Nguyên Vĩnh Cửu',area:'Lục địa Ryan',description:'Qua đèo tuyết và băng nguyên, con đường dẫn tới vòng đá cổ giữa hồ băng.'},
    {name:'Biển Đầm Thẳm Sâu',area:'Lục địa Ryan',description:'Vùng nước và đầm lầy thuộc bốn vùng đất của Ryan.'},
    {name:'Tháp Thiên Không',area:'Lục địa Ryan',description:'Từ chân tháp, theo bậc đá lên các tầng máy cổ và khoảng sân giữa mây.'},
    {name:'Hải Vực Vô Tận',area:'Đại dương',description:'Hải trình qua vùng nước biến dị và cơn lốc xoáy lôi đình.'},
    {name:'Vương Quốc Chìm Atlantis',area:'Đại dương',description:'Từ thành Atlantis, con đường qua hang san hô phát sáng và nghĩa địa chiến thuyền rồi tới vực sâu.'},
    {name:'Miệng Núi Lửa Hắc Diệu',area:'Lục địa bay Aethelgard',description:'Qua cổng Aethelgard và miệng núi lửa để đến đấu trường trong pháo đài Boran.'},
    {name:'Tầng Mây Khởi Nguyên',area:'Lục địa bay Aethelgard',description:'Lối đá qua các đảo mây nối cung điện pha lê với điện gương.'},
    {name:'Cột Mốc Vọng Thiên',area:'Lục địa bay Aethelgard',description:'Điểm nối từ Aethelgard lên quỹ đạo trong đoạn cuối kịch bản.'},
    {name:'Mặt Trăng Đỏ',area:'Không gian',description:'Từ quỹ đạo, băng qua vành đai bánh răng để đặt chân lên bề mặt Mặt Trăng Đỏ.'},
    ...(window.VQSVBootstrap.atlasRegions || [])
  ];
  for (const map of Object.values(window.VQSVBootstrap.maps)) {
    if (map.atlasRegion == null) continue;
    const region = regions[map.atlasRegion];
    (region.maps ||= []).push(map.id);
  }
  window.atlasArea = regionIndex => regions[regionIndex]?.area;
  // Marker positions are proportions of the illustrated world, independent of screen size.
  const positions = [[32,36],[34,23],[40,49],[53,21],[60,35],[49,33],[70,44],[66,68],[71,17],[76,18],[81,24],[82,8]];
  let currentId, index = 0;
  function el(tag, text) {
    const node = document.createElement(tag);
    node.textContent = text;
    return node;
  }
  function firstLinkTo(mapId) {
    const maps = window.VQSVBootstrap.maps;
    const queue = [{id:currentId, first:null}], seen = new Set([currentId]);
    for (let i=0; i<queue.length; i++) {
      const {id,first} = queue[i];
      if (id === mapId) return first;
      for (const link of maps[id]?.links || []) {
        if (seen.has(link.target)) continue;
        seen.add(link.target); queue.push({id:link.target, first:first || link.target});
      }
    }
    return null;
  }
  function switchPage(world,preferred) {
    byId('worldPage').hidden = !world; byId('localPage').hidden = world;
    byId('worldTab').setAttribute('aria-pressed',String(world));
    byId('localTab').setAttribute('aria-pressed',String(!world));
    byId('atlasSwitch').querySelector('span').textContent = world ? 'Khu vực' : 'Thế giới';
    if (!world) window.showLocalMap(preferred);
  }
  function select(i) {
    index = i; selector.value = String(i);
    const region = regions[i], here = region.maps?.includes(currentId);
    [...list.children].forEach((button,n) => {
      button.setAttribute('aria-pressed',String(i === n));
      button.classList.toggle('here', Boolean(regions[n].maps?.includes(currentId)));
    });
    detail.replaceChildren();
    detail.append(el('h3',region.name),el('small',here ? 'Kael đang ở đây' : region.area + ' · Có thể khám phá'),el('p',region.description));
    if (region.maps) {
      let destination = here ? currentId : region.maps[0];
      const action = el('button',here ? 'Xem khu vực hiện tại' : 'Xem lối đi');
      if (window.VQSVBootstrap.maps[destination]?.illustration) {
        const picker = el('select',''), preview = el('img','');
        picker.setAttribute('aria-label','Xem cảnh trong vùng');
        for (const id of region.maps) {
          const option = el('option',window.VQSVBootstrap.maps[id].title);
          option.value = id; picker.append(option);
        }
        picker.value = destination;
        function showScene() {
          destination = picker.value;
          const map = window.VQSVBootstrap.maps[destination];
          preview.src = map.illustration; preview.alt = map.title;
          action.textContent = currentId === destination ? 'Xem khu vực hiện tại' : 'Xem lối đi';
        }
        picker.addEventListener('change',showScene); showScene(); detail.append(picker,preview);
      }
      action.type = 'button';
      action.onclick = () => switchPage(false, currentId === destination ? undefined : firstLinkTo(destination));
      detail.append(action);
    }
  }
  regions.forEach((region,i) => {
    const option = el('option',region.name);
    option.value = String(i); selector.append(option);
    const [x,y] = region.position || positions[i];
    const pin = document.createElement('button');
    pin.type = 'button'; pin.className = 'world-pin'; pin.title = region.name;
    pin.setAttribute('aria-label',region.name);
    pin.style.left = x + '%'; pin.style.top = y + '%';
    pin.addEventListener('click',() => select(i)); list.append(pin);
  });
  selector.addEventListener('change',() => select(Number(selector.value)));
  list.addEventListener('keydown',e => {
    const d = {ArrowLeft:-1, ArrowUp:-1, ArrowRight:1, ArrowDown:1}[e.key];
    if (!d) return;
    e.preventDefault(); select((index + d + regions.length) % regions.length); list.children[index].focus();
  });
  const zoom = byId('worldZoom'), chart = byId('worldChart');
  zoom.onclick = () => {
    const expanded = zoom.getAttribute('aria-pressed') !== 'true';
    zoom.setAttribute('aria-pressed', String(expanded));
    zoom.textContent = expanded ? 'Thu nhỏ bản đồ' : 'Phóng to bản đồ';
    chart.classList.toggle('zoomed', expanded);
    if (expanded) list.children[index].focus();
  };
  byId('localTab').onclick = () => switchPage(false);
  byId('worldTab').onclick = () => switchPage(true);
  byId('atlasSwitch').onclick = () => switchPage(byId('worldPage').hidden);
  byId('closeWorldAtlas').addEventListener('click',() => book.close());
  window.openWorldAtlas = mapId => {
    if (book.open) return;
    currentId = mapId || window.VQSVBootstrap.map.id;
    byId('atlasLocation').textContent = window.VQSVBootstrap.maps[currentId].title;
    book.showModal();
    select(Math.max(0,regions.findIndex(region => region.maps?.includes(currentId))));
    switchPage(false); byId('localTab').focus();
  };
})();
