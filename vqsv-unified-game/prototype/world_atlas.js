(() => {
  const book = document.getElementById('worldAtlas');
  const list = document.getElementById('worldRegions');
  const detail = document.getElementById('worldRegionDetail');
  const regions = [
    {name:'Làng biên giới sương mù',area:'Lục địa Ryan',status:'Đã có map chơi thử',description:'Nơi Kael và Lôi Miêu bắt đầu hành trình, gặp người gác làng và lần theo dấu vết.',places:['Làng biên giới','Bìa rừng và dấu vết Trăng Huyết']},
    {name:'Rừng Rậm Phong Lôi',area:'Lục địa Ryan',status:'Đã có map khám phá thử',description:'Hai khu thử nối với làng: đường rừng và suối có cầu; nhiệm vụ còn đang phát triển.',places:['Rừng Phong Lôi','Suối Sấm (địa điểm thiết kế thử)']},
    {name:'Hoang Mạc Xích Diễm',area:'Lục địa Ryan',status:'Chưa có map chơi được',description:'Vùng cát đỏ dẫn tới Hỏa Sơn Bất Diệt.',places:['Hoang mạc','Hẻm núi cát đỏ','Hỏa Sơn Bất Diệt']},
    {name:'Băng Nguyên Vĩnh Cửu',area:'Lục địa Ryan',status:'Chưa có map chơi được',description:'Vùng băng tuyết nơi Kael tìm và đánh thức Tuyết Y Linh Thú.',places:['Đèo băng','Băng nguyên','Hồ Băng Cổ Xưa']},
    {name:'Biển Đầm Thẳm Sâu',area:'Lục địa Ryan',status:'Chưa có map chơi được',description:'Vùng nước và đầm lầy thuộc bốn vùng đất của Ryan.',places:['Đầm lầy','Bờ biển Ryan']},
    {name:'Tháp Thiên Không',area:'Lục địa Ryan',status:'Chưa có map chơi được',description:'Nơi đối đầu kẻ cầm đầu và Cổ Long Hắc Ám, kích hoạt Quang Minh Trận.',places:['Đường lên tháp','Các tầng tháp','Đỉnh tháp']},
    {name:'Hải Vực Vô Tận',area:'Đại dương',status:'Chưa có map chơi được',description:'Hải trình qua vùng nước biến dị và cơn lốc xoáy lôi đình.',places:['Bến cảng Ryan','Hải vực','Vòng xoáy']},
    {name:'Vương Quốc Chìm Atlantis',area:'Đại dương',status:'Chưa có map chơi được',description:'Vương quốc dưới biển, nơi Kael gặp Lyra và quả trứng Thương Hải Thần Cự Kình.',places:['Atlantis','Hang Động San Hô Lân Quang','Nghĩa Địa Chiến Thuyền','Vực Thẳm Tận Cùng']},
    {name:'Miệng Núi Lửa Hắc Diệu',area:'Lục địa bay Aethelgard',status:'Chưa có map chơi được',description:'Cứ điểm của Boran và chiến trường Nham Bạo Long.',places:['Lối vào Aethelgard','Miệng núi lửa','Pháo đài và đấu trường Boran']},
    {name:'Tầng Mây Khởi Nguyên',area:'Lục địa bay Aethelgard',status:'Chưa có map chơi được',description:'Cung điện pha lê và trận chiến với Seraphina, Kính Giới Thiên Sứ.',places:['Đường lên tầng mây','Cung điện pha lê','Điện gương']},
    {name:'Cột Mốc Vọng Thiên',area:'Lục địa bay Aethelgard',status:'Chưa có map chơi được',description:'Điểm nối từ Aethelgard lên quỹ đạo trong đoạn cuối kịch bản.',places:['Đỉnh Aethelgard','Cột Mốc Vọng Thiên']},
    {name:'Mặt Trăng Đỏ',area:'Không gian',status:'Chưa có map chơi được',description:'Điểm đến được hé lộ ở cuối kịch bản; nội dung sau khi đến cần được duyệt.',places:['Quỹ đạo','Vành đai cơ khí','Mặt Trăng Đỏ']}
  ];
  function el(tag,text,cls){const node=document.createElement(tag);if(text!==undefined)node.textContent=text;if(cls)node.className=cls;return node;}
  function select(index){
    for(const [i,button] of [...list.children].entries()) button.setAttribute('aria-pressed',String(i===index));
    const region=regions[index];detail.replaceChildren();
    detail.append(el('h3',region.name),el('p',`${region.area} · ${region.status}`,'world-status'),el('p',region.description));
    const heading=el('strong','Các địa điểm dự kiến');detail.append(heading);
    const ul=el('ul');for(const place of region.places)ul.append(el('li',place));detail.append(ul);
  }
  regions.forEach((region,i)=>{const button=el('button',undefined,'world-region');button.type='button';button.append(el('span',region.name),el('small',region.status));button.addEventListener('click',()=>select(i));list.append(button);});
  document.getElementById('closeWorldAtlas').addEventListener('click',()=>book.close());
  window.openWorldAtlas=()=>{if(!book.open){book.showModal();select(0);}};
})();
