(() => {
  const book = document.getElementById('worldAtlas');
  const byId = id => document.getElementById(id);
  const list = byId('worldPins'), selector = byId('regionSelect');
  const detail = document.getElementById('worldRegionDetail');
  const regions = [
    {maps:['slice01-border-village','village-apothecary','village-charms'],name:'Làng biên giới sương mù',area:'Lục địa Ryan',status:'Có thể khám phá',description:'Nơi Kael và Lôi Miêu bắt đầu hành trình, gặp người gác làng và lần theo dấu vết.',places:['Làng biên giới','Tiệm thuốc','Tiệm bùa']},
    {maps:['ryan-phong-loi-forest','ryan-forest-stream'],name:'Rừng Rậm Phong Lôi',area:'Lục địa Ryan',status:'Có thể khám phá',description:'Từ cổng đông của làng, đi qua đường rừng đến cầu gỗ Suối Sấm. Người đi rừng trong làng phụ trách chuyến khảo sát.',places:['Rừng Phong Lôi','Suối Sấm (địa điểm thiết kế thử)']},
    {name:'Hoang Mạc Xích Diễm',area:'Lục địa Ryan',status:'Chưa mở',description:'Vùng cát đỏ dẫn tới Hỏa Sơn Bất Diệt.',places:['Hoang mạc','Hẻm núi cát đỏ','Hỏa Sơn Bất Diệt']},
    {name:'Băng Nguyên Vĩnh Cửu',area:'Lục địa Ryan',status:'Chưa mở',description:'Vùng băng tuyết nơi Kael tìm và đánh thức Tuyết Y Linh Thú.',places:['Đèo băng','Băng nguyên','Hồ Băng Cổ Xưa']},
    {name:'Biển Đầm Thẳm Sâu',area:'Lục địa Ryan',status:'Chưa mở',description:'Vùng nước và đầm lầy thuộc bốn vùng đất của Ryan.',places:['Đầm lầy','Bờ biển Ryan']},
    {name:'Tháp Thiên Không',area:'Lục địa Ryan',status:'Chưa mở',description:'Nơi đối đầu kẻ cầm đầu và Cổ Long Hắc Ám, kích hoạt Quang Minh Trận.',places:['Đường lên tháp','Các tầng tháp','Đỉnh tháp']},
    {name:'Hải Vực Vô Tận',area:'Đại dương',status:'Chưa mở',description:'Hải trình qua vùng nước biến dị và cơn lốc xoáy lôi đình.',places:['Bến cảng Ryan','Hải vực','Vòng xoáy']},
    {name:'Vương Quốc Chìm Atlantis',area:'Đại dương',status:'Chưa mở',description:'Vương quốc dưới biển, nơi Kael gặp Lyra và quả trứng Thương Hải Thần Cự Kình.',places:['Atlantis','Hang Động San Hô Lân Quang','Nghĩa Địa Chiến Thuyền','Vực Thẳm Tận Cùng']},
    {name:'Miệng Núi Lửa Hắc Diệu',area:'Lục địa bay Aethelgard',status:'Chưa mở',description:'Cứ điểm của Boran và chiến trường Nham Bạo Long.',places:['Lối vào Aethelgard','Miệng núi lửa','Pháo đài và đấu trường Boran']},
    {name:'Tầng Mây Khởi Nguyên',area:'Lục địa bay Aethelgard',status:'Chưa mở',description:'Cung điện pha lê và trận chiến với Seraphina, Kính Giới Thiên Sứ.',places:['Đường lên tầng mây','Cung điện pha lê','Điện gương']},
    {name:'Cột Mốc Vọng Thiên',area:'Lục địa bay Aethelgard',status:'Chưa mở',description:'Điểm nối từ Aethelgard lên quỹ đạo trong đoạn cuối kịch bản.',places:['Đỉnh Aethelgard','Cột Mốc Vọng Thiên']},
    {name:'Mặt Trăng Đỏ',area:'Không gian',status:'Chưa mở',description:'Điểm đến được hé lộ ở cuối kịch bản; nội dung sau khi đến cần được duyệt.',places:['Quỹ đạo','Vành đai cơ khí','Mặt Trăng Đỏ']}
  ];
  // Marker positions are proportions of the illustrated world, independent of screen size.
  const positions = [[18,45],[21,21],[28,66],[41,19],[43,69],[42,39],[71,55],[77,81],[13,65],[78,17],[87,31],[94,8]];
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
    detail.append(el('h3',region.name),el('small',here ? 'Kael đang ở đây' : region.area + ' · ' + region.status),el('p',region.description));
    if (region.maps) {
      const action = el('button',here ? 'Xem khu vực hiện tại' : 'Xem lối đi');
      action.type = 'button';
      action.onclick = () => switchPage(false, here ? undefined : firstLinkTo(region.maps[0]));
      detail.append(action);
    } else detail.append(el('small',region.places.join(' · ')));
  }
  regions.forEach((region,i) => {
    const option = el('option',region.name);
    option.value = String(i); selector.append(option);
    const pin = document.createElement('button');
    pin.type = 'button'; pin.className = 'world-pin'; pin.title = region.name;
    pin.setAttribute('aria-label',region.name);
    pin.style.left = positions[i][0] + '%'; pin.style.top = positions[i][1] + '%';
    pin.addEventListener('click',() => select(i)); list.append(pin);
  });
  selector.addEventListener('change',() => select(Number(selector.value)));
  list.addEventListener('keydown',e => {
    const d = {ArrowLeft:-1, ArrowUp:-1, ArrowRight:1, ArrowDown:1}[e.key];
    if (!d) return;
    e.preventDefault(); select((index + d + regions.length) % regions.length); list.children[index].focus();
  });
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
