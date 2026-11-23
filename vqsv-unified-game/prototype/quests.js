// Chương 1 · Ryan: main quests MQ001–MQ009 from DANH_SACH_NHIEM_VU.md (He_Thong_Nhiem_Vu_VQSV_96_Chinh_47_Phu.xlsx).
// The table is a draft "to review"; where it leaves something open this file picks a default and marks it TRIAL
// (see prototype/README.md, "Nhiệm vụ chính Chương 1"). Loaded after the game script; every hook in index.html is
// guarded by window.Quests, so the engine still runs (and its tests still pass) without this file.
(() => {
  const VILLAGE = "slice01-border-village", FOREST = "ryan-phong-loi-forest", STREAM = "ryan-forest-stream", MEADOW = "ryan-meadow";
  const KEY = "vqsv-unified-main-quests-v1";
  // Rewards: EXP goes to Lôi Miêu; table items are mapped onto the game's existing items (TRIAL mapping).
  const QUESTS = [
    { id: "MQ001", title: "Tia Chớp Phế Phẩm", place: "Làng Sương Mù", giver: "Trưởng làng Elden",
      text: "Làm quen với Lôi Miêu mang vết sẹo dị tật ở tai phải: cùng nó đánh bia gỗ cạnh Trưởng làng.",
      goal: "Đánh bia gỗ cạnh Trưởng làng Elden", reward: { exp: 100, items: { 8: 5 } }, rewardText: "100 EXP, 5 Khôi phục tề (thay cho Bình Thuốc Nhỏ)" },
    { id: "MQ002", title: "Bí Mật Của Người Cha", place: "Căn hầm cũ nhà Kael", giver: "Kỷ vật gia đình",
      text: "Xuống căn hầm cũ phía tây làng tìm kỷ vật của cha: chiếc la bàn đồng rỉ sét và cuốn nhật ký thất lạc.",
      goal: "Mở căn hầm cũ phía tây làng", reward: { exp: 150 }, rewardText: "150 EXP, La Bàn Đồng Cổ, Nhật Ký Thất Lạc" },
    { id: "MQ003", title: "Tiếng Gầm Ngoài Biên Giới", place: "Rừng Phong Lôi", giver: "Thợ săn Bran",
      text: "Thợ săn Bran ở bìa rừng lo bầy Dã Lang Bão Tố hung bạo khác thường. Hạ 3 con trong Rừng Phong Lôi.",
      goal: "Gặp Thợ săn Bran ở bìa Rừng Phong Lôi", count: 3, reward: { exp: 220, items: { 4: 10 } }, rewardText: "220 EXP, 10 Bánh Sandwich (thay cho Bột Thảo Mộc)" },
    { id: "MQ004", title: "Thu Thập Thảo Dược Hồi Phục", place: "Thung Lũng Rêu (Đồng Cỏ)", giver: "Dược sĩ Mae",
      text: "Dược sĩ Mae cần cỏ Tinh Linh để chữa vết sẹo cho Lôi Miêu: hái 5 Ngọn Cỏ Dạ Quang ở thung lũng đồng cỏ.",
      goal: "Nói chuyện với Dược sĩ Mae trong làng", count: 5, reward: { exp: 300, gold: 200 }, rewardText: "300 EXP, 200 kim tiền" },
    { id: "MQ005", title: "Bóng Đêm Thập Thò", place: "Bờ Suối Sấm", giver: "Dấu vết tà khí",
      text: "Một kẻ trùm áo choàng lảng vảng quanh làng. Lần theo 3 dấu chân tà khí dọc Suối Sấm rồi báo Trưởng làng.",
      goal: "Lần theo dấu chân tà khí ở Suối Sấm", count: 3, reward: { exp: 380, items: { 1: 3 } }, rewardText: "380 EXP, 3 Phong ấn cầu" },
    { id: "MQ006", title: "Đêm Trăng Huyết Thứ Bảy", place: "Làng Sương Mù", giver: "Trưởng làng Elden",
      text: "Vết nứt không gian mở trên trời, ma thú tràn vào làng. Cứu 3 dân làng khỏi ngọn lửa hắc ám.",
      goal: "Cứu dân làng đang bị lửa hắc ám vây", count: 3, reward: { exp: 480, relic: 2 }, rewardText: "480 EXP, Quy Xác Toái Phiến (thay cho Giáp Da Tân Thủ)" },
    { id: "MQ007", title: "Cuồng Bạo Nham Cự Tượng", place: "Cổng làng", giver: "Cuồng Nham Cự Tượng",
      text: "Nham Cự Tượng gác làng nổi điên vì Ma Khống Cầu. Cầm cự trước sức mạnh của nó.",
      goal: "Ra cổng đông đối mặt Cuồng Nham Cự Tượng", turns: 6, reward: { exp: 600, gold: 500 }, rewardText: "600 EXP, 500 kim tiền" },
    { id: "MQ008", title: "Lôi Sét Trắng Thức Tỉnh", place: "Tàn tích làng", giver: "Lôi Miêu",
      text: "Ma Khống Cầu điều khiển Tà Vân Khuyển Thần. Lôi Miêu liều mình che chở Kael, dòng máu cổ thức tỉnh: dùng Lôi Kích Trắng phá vỡ Ma Khống Cầu.",
      goal: "Theo dấu tà khí đến chỗ Ma Khống Cầu trong làng", reward: { exp: 750, items: { 3: 1 } }, rewardText: "750 EXP, Đại sư cầu (thay cho Phong Ấn Cầu Nguyên Tố)" },
    { id: "MQ009", title: "Hạt Nhân Khởi Nguyên", place: "Điện Thờ Cổ · Rừng Phong Lôi", giver: "Linh Hồn Cổ Thụ",
      text: "La bàn chỉ về điện thờ cổ trong Rừng Phong Lôi. Nghe Linh Hồn Cổ Thụ kể truyền thuyết 4 thánh vật phong ấn lục địa.",
      goal: "Đến điện thờ cổ trong Rừng Phong Lôi", reward: { exp: 900 }, rewardText: "900 EXP, Bản Đồ Ryan" },
  ];
  let ids = QUESTS.map(q => q.id);
  // Enemies (TRIAL choices from the VQSV6 roster): Dã Lang Bão Tố = Nhiệt Bạo Phong Tốc Khuyển, the night-raid beasts =
  // Đan nhãn thú, Cuồng Nham Cự Tượng = Nham Sơn Long, the Ma Khống Cầu fight = the story's Tà Vân Khuyển Thần.
  const WOLF = "vqsv6-db-21", RAIDER = "vqsv6-db-89", COLOSSUS = "vqsv6-db-51", POSSESSED = "vqsv6-db-24";

  // ---- MQ010–MQ020: the rest of Chương 1, on the atlas maps (Codex's route south of the village). These quests are
  // described as data (giver, task, reward) and run by the small engine below. ----
  const NPC_ART = { caravan: "villager", jax: "scout", veteran: "villager" };
  const PART2 = [
    { id: "MQ010", title: "Lộ Trình Băng Nguyên", place: "Đèo băng", giver: "Đoàn buôn du mục",
      text: "Đoàn buôn du mục cần người dẫn đường qua đèo tuyết tới Hồ Băng Cổ Xưa. Đẩy lùi hai đợt phục kích rồi đưa họ tới đầu đèo bên kia.",
      npc: { id: "caravan", name: "Trưởng đoàn buôn", map: "ice-pass", near: "eternal-volcano",
        offer: ["Bão tuyết sắp đến mà đường đèo đầy thú dữ.", "Cháu dẫn đoàn ta tới Hồ Băng Cổ Xưa được không?"], wait: ["Đi trước đi, đoàn ta theo sau cháu."] },
      task: { ordered: true, spots: [
        { map: "ice-pass", frac: [.4, .45], label: "Đẩy lùi phục kích", battle: { pet: "vqsv6-db-61", level: 11, name: "Chim cánh cụt phục kích" } },
        { map: "ice-pass", frac: [.6, .6], label: "Đẩy lùi phục kích", battle: { pet: "vqsv6-db-61", level: 12, name: "Chim cánh cụt phục kích" } },
        { map: "ice-pass", near: "ancient-ice-lake", label: "Đưa đoàn buôn tới đầu đèo", say: ["Trưởng đoàn buôn", ["Qua được đèo rồi! Hồ Băng ở ngay phía trước.", "Cảm ơn cháu. Cầm lấy áo choàng này mà giữ ấm."]] }] },
      reward: { exp: 1100, relic: 3 }, rewardText: "1.100 EXP, Ô Nha Uế (thay cho Áo Choàng Giữ Nhiệt)" },
    { id: "MQ011", title: "Băng Giá Bất Tận", place: "Hồ Băng Cổ Xưa", giver: "Băng Trụ Trấn Giữ",
      text: "Băng Trụ Trấn Giữ canh mặt hồ. Phá 3 phong ấn băng lam để tìm tung tích Tuyết Y Linh Thú.",
      start: { map: "ancient-ice-lake", near: "ice-pass", label: "Chạm vào Băng Trụ", lines: ["Băng Trụ Trấn Giữ", ["Ai đánh thức ta?… Kẻ mang la bàn.", "Ba phong ấn băng lam giam Tuyết Y dưới đáy hồ. Phá chúng, nếu ngươi đủ sức."]] },
      task: { spots: [
        { map: "ancient-ice-lake", frac: [.25, .35], label: "Phá phong ấn băng lam", battle: { pet: "vqsv6-db-59", level: 13, name: "Hộ vệ phong ấn" } },
        { map: "ancient-ice-lake", frac: [.75, .35], label: "Phá phong ấn băng lam", battle: { pet: "vqsv6-db-59", level: 13, name: "Hộ vệ phong ấn" } },
        { map: "ancient-ice-lake", frac: [.5, .75], label: "Phá phong ấn băng lam", battle: { pet: "vqsv6-db-59", level: 14, name: "Hộ vệ phong ấn" } }] },
      reward: { exp: 1350, stones: { 13: 1 }, story: ["Băng Tinh Thạch"] }, rewardText: "1.350 EXP, Băng Tinh Thạch (thêm 1 Thiên Giới Tinh Thạch)" },
    { id: "MQ012", title: "Khúc Ca Đồng Cảm", place: "Đáy Hồ Băng", giver: "Tuyết Y Linh Thú",
      text: "Tuyết Y Linh Thú bị tà khí làm cuồng. Trấn an nó bằng sự kiên nhẫn: cầm cự 5 lượt mà không hạ gục nó.",
      task: { spots: [{ map: "ancient-ice-lake", frac: [.5, .5], label: "Trấn an Tuyết Y Linh Thú", battle: { pet: "vqsv6-db-60", level: 15, name: "Tuyết Y Linh Thú", hpScale: 3, survive: 5, mercy: true, noFlee: true } }] },
      reward: { exp: 1650, relic: 1 }, rewardText: "1.650 EXP, Hồng Sắc Hải Loa (thay cho Nhẫn Băng Lam)" },
    { id: "MQ013", title: "Vượt Hoang Mạc Xích Diễm", place: "Hẻm núi cát đỏ", giver: "Hướng dẫn viên Jax",
      text: "Jax chỉ đường băng qua sa mạc tới Hỏa Sơn Bất Diệt, nhưng Bọ Cạp Nham Thạch chặn lối. Đánh bại nó.",
      npc: { id: "jax", name: "Hướng dẫn viên Jax", map: "sand-canyon", near: "red-dunes",
        offer: ["Muốn tới Hỏa Sơn à? Phải qua hẻm núi này.", "Có con Bọ Cạp Nham Thạch to bằng cái nhà nằm chắn đường. Hạ nó đi rồi ta dẫn đường."], wait: ["Nó nằm sâu trong hẻm núi, cẩn thận đuôi độc!"] },
      task: { spots: [{ map: "sand-canyon", frac: [.65, .5], label: "Đánh Bọ Cạp Nham Thạch", battle: { pet: "vqsv6-db-54", level: 18, name: "Bọ Cạp Nham Thạch", hpScale: 3, noFlee: true } }] },
      reward: { exp: 2000, items: { 9: 3 } }, rewardText: "2.000 EXP, 3 Siêu năng tề (thay cho Bình Nước Thần)" },
    { id: "MQ014", title: "Nguy Hiểm Miệng Lửa", place: "Chân núi Hỏa Sơn", giver: "Dòng dung nham",
      text: "Có tiếng kêu cứu sau dòng dung nham. Cần một sủng vật hệ Băng (Thủy hệ) trong ba lô để làm nguội đường đi.",
      task: { spots: [{ map: "eternal-volcano", frac: [.45, .45], label: "Làm nguội dòng dung nham", needElement: "Thủy hệ",
        say: ["Dòng dung nham", ["Hơi lạnh từ sủng vật hệ Thủy phủ lên dung nham. Một lối đá nguội hiện ra.", "Tiếng kêu cứu vọng ra từ sâu trong lòng núi…"]] }] },
      reward: { exp: 2400, stones: { 12: 2 } }, rewardText: "2.400 EXP, 2 Tinh Nguyên Thạch (thay cho Đá Cường Hóa Lửa)" },
    { id: "MQ015", title: "Giải Cứu Viêm Long Tí Nị", place: "Lòng núi lửa", giver: "Viêm Long Tí Nị",
      text: "Viêm Long non mắc kẹt giữa dung nham độc. Thu phục nó vào đội (đánh nhẹ tay rồi dùng cầu bắt).",
      task: { spots: [{ map: "eternal-volcano", frac: [.7, .65], label: "Tiếp cận Viêm Long Tí Nị", battle: { pet: "vqsv6-db-16", level: 21, name: "Viêm Long Tí Nị", capture: true } }] },
      reward: { exp: 2900, relic: 4 }, rewardText: "2.900 EXP, Viễn Cổ Long Cốt (thay cho Vòng Cổ Rồng Non)" },
    { id: "MQ016", title: "Bóng Ma Tận Thế Tụ Hội", place: "Đồng Bằng Sấm Sét", giver: "Tàn binh giáo phái",
      text: "Một tàn binh đã bỏ giáo phái báo tin: cứ điểm trinh sát nằm trên Đồng Bằng Sấm Sét. Tiêu diệt 10 Tín Đồ Ma Khống.",
      npc: { id: "veteran", name: "Tàn binh giáo phái", map: "ryan-stormplain", near: "ryan-canyon",
        offer: ["Ta từng là tín đồ… nay chỉ muốn chuộc lỗi.", "Bọn chúng đi theo nhóm hai người khắp đồng bằng này. Diệt đủ mười tên, cứ điểm sẽ tan."], wait: ["Còn tín đồ trên đồng bằng. Cẩn thận."], done: ["Cứ điểm tan rồi. Giáo chủ đang ở Đỉnh Tháp Thiên Không."] },
      task: { wild: { map: "ryan-stormplain", pet: "vqsv6-db-94", level: [22, 24], name: "Nhóm Tín Đồ Ma Khống", perWin: 2, count: 10 }, report: true },
      reward: { exp: 3500, gold: 1200 }, rewardText: "3.500 EXP, 1.200 kim tiền" },
    { id: "MQ017", title: "Đường Lên Tháp Thiên Không", place: "Đường lên Tháp Thiên Không", giver: "Cổng tháp",
      text: "Đại môn Tháp Thiên Không chỉ mở khi 4 trụ đài nhận đủ tinh thể Băng, Hỏa, Lôi và Phong.",
      task: { spots: ["Băng", "Hỏa", "Lôi", "Phong"].map((name, i) => ({ map: "tower-ascent", frac: [[.25, .3], [.75, .3], [.25, .7], [.75, .7]][i], label: `Đặt tinh thể ${name}`,
        say: ["Trụ đài", [`Tinh thể ${name} phát sáng khi chạm vào trụ đài.`]] })) },
      reward: { exp: 4200, story: ["Chìa Khóa Thiên Không"] }, rewardText: "4.200 EXP, Chìa Khóa Thiên Không" },
    { id: "MQ018", title: "Cộng Hưởng Linh Hồn Bộc Phát", place: "Các tầng Tháp Thiên Không", giver: "Lôi Miêu",
      text: "Trong tháp, Lôi Miêu đối mặt ảo ảnh thù hận của chính nó. Vượt qua để hoàn thành nghi thức tiến hóa.",
      task: { spots: [{ map: "tower-halls", frac: [.5, .5], label: "Đối mặt ảo ảnh", battle: { pet: "vqsv6-db-78", level: 24, name: "Ảo ảnh thù hận", hpScale: 2, noFlee: true, ritual: true } }] },
      reward: { exp: 5000, relic: 7 }, rewardText: "5.000 EXP, Linh Trùng Thi Hài (thay cho Dây Cương Sấm Sét)" },
    { id: "MQ019", title: "Tội Ác Kẻ Đồng Đội", place: "Đỉnh Tháp Thiên Không", giver: "Kẻ cầm đầu giáo phái",
      text: "Giáo chủ từng là chiến hữu của cha Kael. Đánh bại 3 đợt hộ vệ của hắn.",
      task: { spots: [{ map: "tower-crown", frac: [.4, .55], label: "Đánh đợt hộ vệ", waves: 3, battle: { pet: "vqsv6-db-95", level: 25, name: "Hộ vệ giáo chủ", noFlee: true } }] },
      reward: { exp: 6000, gold: 2000 }, rewardText: "6.000 EXP, 2.000 kim tiền" },
    { id: "MQ020", title: "Quang Minh Trận Thanh Tẩy", place: "Đỉnh Tháp Thiên Không", giver: "Cổ Long Hắc Ám",
      text: "Kích hoạt Quang Minh Trận từ bốn nguyên tố, đánh bại Cổ Long Hắc Ám và tìm bức thư của cha.",
      task: { spots: [{ map: "tower-crown", frac: [.7, .35], label: "Đối đầu Cổ Long Hắc Ám", battle: { pet: "vqsv6-db-97", level: 26, name: "Cổ Long Hắc Ám", hpScale: 4, noFlee: true } }] },
      reward: { exp: 7500, story: ["Bức thư của cha", "Danh hiệu Anh Hùng Ryan"] }, rewardText: "7.500 EXP, Danh hiệu Anh Hùng Ryan, Bức thư của cha" },
  ];
  const byIdP2 = new Map(PART2.map(def => [def.id, def]));
  const p2 = () => byIdP2.get(current()?.id) || null;
  // Where a spot or NPC stands: next to the exit toward `near` (two cells inward) or at a fraction of the map.
  const placeCache = new Map();
  function place(def, key) {
    if (placeCache.has(key)) return placeCache.get(key);
    const map = assets.maps?.[def.map];
    if (!map) return null;
    let x = map.width * (def.frac?.[0] ?? .5), y = map.height * (def.frac?.[1] ?? .5);
    const link = def.near && (map.links || []).find(l => l.target === def.near);
    if (link) { const cx = map.width / 2, cy = map.height / 2; x = link.x + Math.sign(cx - link.x) * 2; y = link.y + Math.sign(cy - link.y) * 2; }
    const taken = [...placeCache.entries()].filter(([k]) => k.startsWith(def.map + ":")).map(([, v]) => v);
    const at = nearFree(map, x, y, taken);
    placeCache.set(key, at);
    return at;
  }
  const spotKey = (def, i) => `${def.task.spots[i].map}:${def.id}:${i}`;
  function p2Spots(def) { return (def.task.spots || []).map((s, i) => ({ ...s, i, at: place(s, spotKey(def, i)) })); }
  function p2Here() {
    const def = p2();
    if (!def || state.mode !== "explore") return null;
    if (!q.started) {
      if (def.start && onMap(def.start.map)) { const at = place(def.start, `${def.start.map}:${def.id}:start`); if (close(at, true)) return { label: def.start.label, run: () => say(def.start.lines[0], def.start.lines[1], () => { q.started = true; save(); statusEl.textContent = `Nhiệm vụ chính · ${def.id} ${def.title}`; }) }; }
      return null;
    }
    for (const s of p2Spots(def)) {
      if (!onMap(s.map) || !s.at || !close(s.at, true)) continue;
      const doneHere = s.waves ? q.progress >= s.waves : q.marks.includes(s.i);
      if (doneHere) continue;
      if (def.task.ordered && s.i !== q.marks.length) continue;
      const label = s.waves ? `${s.label} (${q.progress + 1}/${s.waves})` : s.label;
      return { label, run: () => runSpot(def, s) };
    }
    return null;
  }
  function teamHas(element) { return buildTeam().some(pet => (pet.unifiedElement || pet.element) === element); }
  function runSpot(def, s) {
    if (s.needElement && !teamHas(s.needElement)) { statusEl.textContent = `Cần một sủng vật ${s.needElement} trong ba lô để làm nguội dung nham.`; return; }
    if (s.battle) { p2Battle(def, s); return; }
    if (s.say) { say(s.say[0], s.say[1], () => markSpot(def, s)); return; }
    markSpot(def, s);
  }
  function markSpot(def, s) {
    if (s.waves) q.progress++; else if (!q.marks.includes(s.i)) q.marks.push(s.i);
    if (!s.waves) q.progress = q.marks.length;
    save();
    const total = s.waves || def.task.spots.length;
    if ((s.waves ? q.progress >= s.waves : q.marks.length >= def.task.spots.length) && !def.task.report) { finishP2(def); return; }
    statusEl.textContent = `${def.id} · ${Math.min(q.progress, total)}/${total}`;
    updateGuidance();
  }
  function p2Battle(def, s) {
    const b = s.battle;
    if (b.capture) {
      const player = state.player;
      player.inputDir = null; player.step = null; player.visualX = player.x; player.visualY = player.y;
      state.battle = newBattle({ petId: b.pet, area: def.id, level: b.level, quality: 3, questName: b.name });
      state.battle.quest = { id: def.id, spot: s.i, capture: true };
      state.battle.message = `${b.name} kêu yếu ớt giữa dung nham. Hãy thu phục nó!`;
      state.mode = "battle"; window.VQSVSound?.play("encounter"); updateGuidance();
      return;
    }
    const enemy = questEnemy(b.pet, b.level, b.name, { hpScale: b.hpScale || 1 });
    questBattle(def.id, enemy, b.survive ? `${b.name} lồng lộn! Kiên nhẫn trấn an nó ${b.survive} lượt, đừng hạ gục nó.` : `${b.name} xuất hiện!`,
      { spot: s.i, ...(b.survive ? { surviveTurns: b.survive } : {}), ...(b.mercy ? { mercy: true } : {}), ...(b.noFlee ? { noFlee: true } : {}), ...(b.ritual ? { ritual: true } : {}) });
  }
  function p2BattleEnd(info, enemyDown) {
    const def = byIdP2.get(info.id);
    const raw = def?.task.spots?.[info.spot], s = raw && { ...raw, i: info.spot };
    if (!def || !s) return false;
    if (info.mercy && enemyDown) { statusEl.textContent = "Tuyết Y Linh Thú gục ngã… Phải trấn an chứ không hạ gục. Thử lại."; return true; }
    if (info.capture) { statusEl.textContent = "Viêm Long Tí Nị ngất đi. Thử lại và dùng cầu bắt khi nó còn máu."; return true; }
    if (info.ritual) ritualEvolve();
    markSpot(def, s);
    return true;
  }
  // MQ018: the ritual evolves Lôi Miêu if its form has a next stage; the ritual itself supplies the material.
  function ritualEvolve() {
    const form = leadForm(), evo = ROSTER.get(form)?.evolution;
    if (!evo) { statusEl.textContent = "Lôi Miêu đã ở dạng cuối. Nghi thức củng cố liên kết giữa Kael và Lôi Miêu."; return; }
    stones[evo.material.table_row] = (stones[evo.material.table_row] || 0) + evo.material.quantity; saveStones();
    const result = window.VQSVEvolution?.evolve("loi-mieu", form);
    statusEl.textContent = result?.ok ? `Nghi thức hoàn tất: ${result.message}` : `Nghi thức hoàn tất, nhưng ${result?.message || "Lôi Miêu chưa thể tiến hóa"}`;
  }
  function finishP2(def) {
    complete();
    if (def.id === "MQ020") { state.mode = "ending"; endingCard = "chapter1"; }
    updateGuidance();
  }
  let endingCard = null;
  function p2Npcs(out) {
    for (const def of PART2) {
      const npc = def.npc;
      if (!npc || current()?.id !== def.id || !onMap(npc.map)) continue;
      const at = place(npc, `${npc.map}:${def.id}:npc`);
      if (assets.chars && !assets.chars[npc.id]) assets.chars[npc.id] = assets.chars[NPC_ART[npc.id]] || assets.chars.scout;
      const lines = !q.started ? npc.offer : def.task.report && q.progress >= (def.task.wild?.count || def.task.spots?.length || 0) ? (npc.done || ["Làm tốt lắm."]) : npc.wait;
      out.push({ id: npc.id, name: npc.name, x: at.x, y: at.y, dialogue: lines, questTalk: npc.id });
    }
    return out;
  }
  function p2Talked(npc) {
    const def = p2();
    if (!def?.npc || npc.questTalk !== def.npc.id) return false;
    if (!q.started) { q.started = true; save(); statusEl.textContent = `Nhiệm vụ chính · ${def.id} ${def.title}`; return true; }
    const need = def.task.wild?.count || def.task.spots?.length || 0;
    if (def.task.report && q.progress >= need) finishP2(def);
    return true;
  }
  function p2ForceWild(mapId) {
    const def = p2(), w = def?.task.wild;
    if (!w || !q.started || mapId !== w.map || q.progress >= w.count || Math.random() >= .7) return null;
    return { petId: w.pet, area: def.id, level: w.level[0] + Math.floor(Math.random() * (w.level[1] - w.level[0] + 1)), quality: 2, questName: w.name };
  }
  function p2WildWon(enemy) {
    const def = p2(), w = def?.task.wild;
    if (!w || !q.started || enemy.petId !== w.pet || q.progress >= w.count) return;
    q.progress = Math.min(w.count, q.progress + (w.perWin || 1)); save();
    statusEl.textContent += ` · ${w.name.replace("Nhóm ", "")} ${q.progress}/${w.count}`;
  }
  function p2Objective(def) {
    if (!q.started) return def.npc ? `${def.id} · Gặp ${def.npc.name} ở ${assets.maps?.[def.npc.map]?.title || def.place}` : def.start ? `${def.id} · Đến ${def.place} tìm ${def.giver}` : `${def.id} · ${def.text}`;
    if (def.task.wild) return q.progress >= def.task.wild.count ? `${def.id} · Về báo ${def.npc.name}` : `${def.id} · Hạ Tín Đồ Ma Khống trên ${assets.maps?.[def.task.wild.map]?.title} (${q.progress}/${def.task.wild.count})`;
    const spotsList = p2Spots(def), s = def.task.ordered ? spotsList[q.marks.length] : spotsList.find(x => x.waves ? q.progress < x.waves : !q.marks.includes(x.i));
    const total = spotsList[0]?.waves || spotsList.length, n = total > 1 ? ` (${Math.min(q.progress, total)}/${total})` : "";
    return `${def.id} · ${s ? s.label : def.title} · ${assets.maps?.[s?.map]?.title || def.place}${n}`;
  }
  function p2Draw(ctx, S, glow) {
    const def = p2();
    if (!def) return;
    if (!q.started && def.start && onMap(def.start.map)) { const at = place(def.start, `${def.start.map}:${def.id}:start`); glow(at.x, at.y, "#9fe8ff", 9); }
    if (!q.started) return;
    for (const s of p2Spots(def)) {
      if (!onMap(s.map) || !s.at) continue;
      const doneHere = s.waves ? q.progress >= s.waves : q.marks.includes(s.i);
      if (doneHere || (def.task.ordered && s.i !== q.marks.length)) continue;
      glow(s.at.x, s.at.y, s.battle ? "#ff6a5a" : "#ffe08a", s.battle?.hpScale ? 10 : 7);
    }
  }
  QUESTS.push(...PART2.map(({ npc, task, start, ...rest }) => ({ ...rest, goal: rest.text })));
  ids = QUESTS.map(q => q.id);
  let q = fresh();
  function fresh() { return { index: 0, started: false, progress: 0, done: [], marks: [], items: [], intro: false }; }
  const current = () => QUESTS[q.index] || null;
  const isActive = id => q.started && current()?.id === id;
  function save() { try { localStorage.setItem(KEY, JSON.stringify(q)); } catch (_) {} }
  try { const raw = JSON.parse(localStorage.getItem(KEY) || "null"); if (raw && Number.isInteger(raw.index)) q = { ...fresh(), ...raw }; } catch (_) {}

  // ---- places, found on the live map data so they follow map edits ----
  function withMap(map, fn) { const keep = assets.map; assets.map = map; try { return fn(); } finally { assets.map = keep; } }
  function free(map, x, y) {
    if (!map || x < 1 || y < 1 || x >= map.width - 1 || y >= map.height - 1) return false;
    if ((map.links || []).some(link => Math.abs(link.x - x) + Math.abs(link.y - y) <= 1)) return false;
    return !withMap(map, () => blocked(x, y));
  }
  // The free cell nearest to (x, y), breadth-first, never on another quest spot.
  let placing = 0;
  function nearFree(map, x, y, taken = []) {
    placing++;
    try { return nearFreeCore(map, x, y, taken); } finally { placing--; }
  }
  function nearFreeCore(map, x, y, taken) {
    const seen = new Set(), queue = [[Math.round(x), Math.round(y)]];
    while (queue.length) {
      const [cx, cy] = queue.shift(), key = cx + "," + cy;
      if (seen.has(key) || cx < 0 || cy < 0 || cx >= map.width || cy >= map.height) continue;
      seen.add(key);
      if (free(map, cx, cy) && !taken.some(t => t.x === cx && t.y === cy)) return { x: cx, y: cy };
      queue.push([cx + 1, cy], [cx - 1, cy], [cx, cy + 1], [cx, cy - 1]);
      if (seen.size > 4000) break;
    }
    return { x: Math.round(x), y: Math.round(y) };
  }
  const spotCache = new Map();
  function spots() {
    if (spotCache.size || !assets.maps) return spotCache;
    const village = assets.maps[VILLAGE], forest = assets.maps[FOREST], stream = assets.maps[STREAM], meadow = assets.maps[MEADOW];
    const elder = village.entities.elder;
    spotCache.set("dummy", nearFree(village, elder.x + 2, elder.y + 1));
    spotCache.set("cellar", nearFree(village, 3, 11, [spotCache.get("dummy")]));
    spotCache.set("colossus", nearFree(village, village.width - 3, 10));
    const fromVillage = forest.arrivals?.[VILLAGE] || [1, Math.floor(forest.height / 2)];
    spotCache.set("bran", nearFree(forest, fromVillage[0] + 2, fromVillage[1] - 1));
    spotCache.set("shrine", nearFree(forest, forest.width * .55, forest.height * .45, [spotCache.get("bran")]));
    const grass = [];
    for (const [fx, fy] of [[.2, .25], [.75, .2], [.5, .5], [.25, .75], [.8, .7]]) grass.push(nearFree(meadow, meadow.width * fx, meadow.height * fy, grass));
    spotCache.set("grass", grass);
    const start = stream.arrivals?.[FOREST] || [1, Math.floor(stream.height / 2)], prints = [];
    for (const k of [.2, .5, .8]) prints.push(nearFree(stream, start[0] + (stream.width / 2 - start[0]) * k * 1.6, start[1] + (stream.height / 2 - start[1]) * k, prints));
    spotCache.set("prints", prints);
    return spotCache;
  }
  const onMap = id => assets.map?.id === id;
  const close = (a, b) => Math.abs(a.x - state.player.x) + Math.abs(a.y - state.player.y) <= 1 && b;

  // What the quest offers at Kael's position: { label, run }.
  function here() {
    if (state.mode !== "explore") return null;
    if (!q.started) return p2Here();
    const s = spots(), id = current()?.id;
    if (id === "MQ001" && onMap(VILLAGE) && close(s.get("dummy"), true)) return { label: "Đánh bia gỗ", run: fightDummy };
    if (id === "MQ002" && onMap(VILLAGE) && close(s.get("cellar"), true)) return { label: "Mở căn hầm cũ", run: openCellar };
    if (id === "MQ004" && q.progress < 5 && onMap(MEADOW)) { const i = s.get("grass").findIndex((g, n) => !q.marks.includes(n) && close(g, true)); if (i >= 0) return { label: "Hái Cỏ Dạ Quang", run: () => pickGrass(i) }; }
    if (id === "MQ005" && q.progress < 3 && onMap(STREAM)) { const p = s.get("prints")[q.progress]; if (close(p, true)) return { label: "Xem dấu chân tà khí", run: followPrint }; }
    if (id === "MQ007" && onMap(VILLAGE) && close(s.get("colossus"), true)) return { label: "Đối mặt Nham Cự Tượng", run: fightColossus };
    if (id === "MQ008" && onMap(VILLAGE) && close(assets.map.entities.encounter, true)) return { label: "Phá Ma Khống Cầu", run: fightOrb };
    if (id === "MQ009" && onMap(FOREST) && close(s.get("shrine"), true)) return { label: "Nghe Linh Hồn Cổ Thụ", run: hearSpirit };
    return p2Here();
  }

  // ---- quest battles ----
  function questBattle(quest, enemy, message, extra = {}) {
    const player = state.player;
    player.inputDir = null; player.step = null; player.visualX = player.x; player.visualY = player.y;
    state.battle = newBattle();
    state.battle.enemy = enemy;
    state.battle.quest = { id: quest, ...extra };
    // Background: the village fights keep the night scene; elsewhere the map's own VQSV5 fields, or the enemy's element.
    state.battle.backdrop6 = onMap(VILLAGE) ? "quy" : state.battle.terrain != null ? null : elementBg6(enemy.element);
    state.battle.message = message;
    if (typeof Image !== "undefined" && enemy.image) cachedImage(enemy.image);
    state.mode = "battle";
    window.VQSVSound?.play("encounter");
    statusEl.textContent = `Nhiệm vụ · ${current().title}`;
    updateGuidance();
  }
  function questEnemy(petId, level, name, { hpScale = 1, image } = {}) {
    const enemy = vqsv6Combatant(petId, level);
    enemy.max = enemy.hp = Math.round(enemy.hp * hpScale);
    return { ...enemy, name, image: image || window.VQSVPetTeam?.info(petId)?.image, subtitle: `NHIỆM VỤ · CẤP ${level}` };
  }
  function fightDummy() {
    const dummy = questEnemy(WOLF, 1, "Bia gỗ", { image: "assets/scenery/stump.png" });
    Object.assign(dummy, { skills: [], skill: null, passive: true, elementId: null, element: "Không hệ" });
    dummy.max = dummy.hp = 40;
    questBattle("MQ001", dummy, "Bia gỗ đứng yên. Ra lệnh cho Lôi Miêu tấn công!");
  }
  function fightRaider(npc) { questBattle("MQ006", questEnemy(RAIDER, 5, "Ma thú lửa hắc ám"), `Ma thú vây quanh ${npc.name}!`, { rescue: npc.id }); }
  function fightColossus() {
    const quest = current();
    questBattle("MQ007", questEnemy(COLOSSUS, 9, "Cuồng Nham Cự Tượng", { hpScale: 6 }),
      `Cuồng Nham Cự Tượng gầm lên! Cầm cự ${quest.turns} lượt.`, { surviveTurns: quest.turns, noFlee: true });
  }
  function fightOrb() {
    questBattle("MQ008", { ...questEnemy(POSSESSED, 8, "Tà Vân Khuyển Thần", { hpScale: 2.5 }), image: null },
      "Ma Khống Cầu toả tà khí! Vết sẹo của Lôi Miêu phát sáng: Lôi Kích Trắng đã sẵn sàng.", { noFlee: true });
    // TRIAL: the awakening gives Lôi Miêu a battle-only skill; power and uses are design values.
    const lead = state.battle.team.find(pet => pet.kind === "lead");
    if (lead) {
      const white = { name: "Lôi Kích Trắng", power: 320, uses: 3, max: 3, description: "Dòng máu cổ thức tỉnh: lôi sét trắng phá tà khí." };
      lead.skills = [white, ...(lead.skills || [])]; lead.skill = white;
    }
    updateGuidance();
  }
  function battleWon(info) {
    const quest = current(), enemyDown = state.battle.enemy.hp <= 0;
    state.battle = newBattle(); state.mode = "explore"; wildSteps = 0;
    if (byIdP2.has(info.id)) { if (quest?.id === info.id) p2BattleEnd(info, enemyDown); updateGuidance(); return; }
    if (!quest || quest.id !== info.id) { updateGuidance(); return; }
    if (quest.id === "MQ001") complete("Lôi Miêu hạ bia gỗ. Trưởng làng gật gù hài lòng.");
    else if (quest.id === "MQ006") {
      q.marks.push(info.rescue); q.progress = q.marks.length; save();
      if (q.progress >= quest.count) complete("Ba dân làng đã an toàn. Cổng đông rung chuyển…");
      else statusEl.textContent = `Đã cứu ${q.progress}/${quest.count} dân làng`;
    } else if (quest.id === "MQ007") complete("Kael và Lôi Miêu trụ vững. Nham Cự Tượng gục xuống, một luồng tà khí bay về giữa làng.");
    else if (quest.id === "MQ008") { complete("Lôi Kích Trắng xé tan Ma Khống Cầu!"); state.mode = "ending"; }
    updateGuidance();
  }
  function battleLost(info) {
    // Quest fights never end the game: the team is carried back to the village to rest and can try again.
    state.battle = newBattle(); state.mode = "explore"; wildSteps = 0;
    statusEl.textContent = `Lôi Miêu kiệt sức · thử lại nhiệm vụ ${current()?.title || ""}`;
    updateGuidance();
  }

  // ---- other steps ----
  function say(name, lines, after) {
    state.dialogue = 0; state.mode = "dialogue";
    state.activeNpc = { id: "quest-" + name, name, dialogue: lines, questAfter: after };
    updateGuidance();
  }
  function openCellar() {
    say("Căn hầm cũ", ["Dưới lớp bụi là chiếc hộp gỗ khắc tên cha Kael.", "Bên trong: la bàn đồng rỉ sét và cuốn nhật ký sờn mép.",
      "Trang cuối viết vội: \"Nếu trăng nhuốm máu, hãy theo kim la bàn.\""], () => { q.items.push("La Bàn Đồng Cổ", "Nhật Ký Thất Lạc"); complete("Đã có La Bàn Đồng Cổ và Nhật Ký Thất Lạc."); });
  }
  function pickGrass(i) { q.marks.push(i); q.progress = q.marks.length; save(); statusEl.textContent = `Cỏ Dạ Quang ${q.progress}/5`; if (q.progress >= 5) statusEl.textContent += " · về gặp Dược sĩ Mae"; updateGuidance(); }
  function followPrint() {
    q.progress++; save();
    statusEl.textContent = q.progress < 3 ? `Dấu chân tà khí ${q.progress}/3 · còn dấu tiếp theo` : "Dấu chân dừng ở cửa rừng · về báo Trưởng làng";
    updateGuidance();
  }
  function hearSpirit() {
    say("Linh Hồn Cổ Thụ", ["Hỡi đứa trẻ mang la bàn… ta đã chờ ngươi.",
      "Thuở trước, bốn thánh vật phong ấn lục địa Ryan: Băng, Hỏa, Lôi và Thiên Không.",
      "Ma Khống Cầu là dấu hiệu phong ấn đang rạn. Hãy cầm lấy bản đồ này và tìm bốn thánh vật."], () => { q.items.push("Bản Đồ Ryan"); complete("Nhận Bản Đồ Ryan. Hướng tiếp theo: Đèo băng, qua hoang mạc phía nam làng."); });
  }
  function complete(text) {
    const quest = current();
    if (!quest) return;
    const r = quest.reward || {};
    if (r.exp) grantLeadExp(r.exp);
    if (r.gold) addGold(r.gold);
    for (const [row, n] of Object.entries(r.items || {})) bag[row] = Math.min(STACK_MAX, (bag[row] || 0) + n);
    if (r.items) saveBag();
    if (r.relic != null) { stones[r.relic] = (stones[r.relic] || 0) + 1; saveStones(); updateStones(); }
    for (const [row, n] of Object.entries(r.stones || {})) { stones[row] = (stones[row] || 0) + n; saveStones(); updateStones(); }
    if (r.story) q.items.push(...r.story);
    q.done.push(quest.id); q.index++; q.progress = 0; q.marks = [];
    // Steps that start by themselves; MQ003 and MQ004 start when Kael talks to their giver.
    const next = current(), nextP2 = next && byIdP2.get(next.id);
    q.started = ["MQ002", "MQ005", "MQ006", "MQ007", "MQ008", "MQ009"].includes(next?.id) || !!(nextP2 && !nextP2.npc && !nextP2.start);
    save(); updateWallet();
    statusEl.textContent = `Hoàn thành ${quest.id} · ${quest.title} · ${quest.rewardText}`;
    window.VQSVSound?.play("levelUp");
    if (text && state.battle) state.battle.message = text;
  }
  function grantLeadExp(amount) {
    const entry = { ...progressOf("loi-mieu", BATTLE_LEVEL) };
    entry.exp += amount;
    while (entry.level < LEVEL_CAP && entry.exp >= expToReach(entry.level + 1)) { entry.exp -= expToReach(entry.level + 1); entry.level++; }
    if (entry.level >= LEVEL_CAP) entry.exp = 0;
    progress["loi-mieu"] = { ...(progress["loi-mieu"] || {}), level: entry.level, exp: entry.exp };
    saveProgress();
  }

  // ---- NPCs: names from the quest table and their quest lines ----
  function npcs(list) {
    if (placing) return list;
    const quest = current()?.id;
    const out = list.map(npc => {
      if (npc.id === "elder") return { ...npc, name: "Trưởng làng Elden", dialogue: elderLines(npc.dialogue) };
      if (npc.id === "herbalist" && (quest === "MQ004")) return { ...npc, name: "Dược sĩ Mae", dialogue: maeLines(), questTalk: "mae" };
      if (npc.id === "herbalist") return { ...npc, name: "Dược sĩ Mae" };
      return npc;
    });
    if (onMap(FOREST) && ["MQ003"].includes(quest) || onMap(FOREST) && q.done.includes("MQ003")) {
      const at = spots().get("bran");
      if (assets.chars && !assets.chars.bran) assets.chars.bran = assets.chars.scout;
      out.push({ id: "bran", name: "Thợ săn Bran", x: at.x, y: at.y, dialogue: branLines(), questTalk: "bran" });
    }
    return p2Npcs(out);
  }
  function elderLines(base) {
    const quest = current()?.id;
    if (!q.intro) return base;
    if (quest === "MQ001") return ["Bia gỗ ngay cạnh ta đấy. Cho ta xem Lôi Miêu ra đòn nào."];
    if (quest === "MQ002") return ["Căn hầm cũ phía tây làng là của cha cháu. Có lẽ đã đến lúc cháu mở nó."];
    if (quest === "MQ005" && q.progress >= 3) return ["Dấu chân tà khí dẫn tới cửa rừng ư?", "Trăng đêm nay đỏ quá… Mau báo mọi người đóng cửa!"];
    if (quest === "MQ005") return ["Có kẻ trùm áo choàng quanh Suối Sấm. Cháu lần theo dấu chân giúp ta."];
    if (quest === "MQ006") return ["Lửa hắc ám! Cứu những người còn mắc kẹt trước đã!"];
    if (quest === "MQ007") return ["Nham Cự Tượng gác cổng đông đang nổi điên. Cầm cự để ta đưa dân làng đi!"];
    if (quest === "MQ008") return ["Tà khí tụ lại giữa làng. Ma Khống Cầu ở đó, Kael!"];
    if (quest === "MQ009") return ["La bàn chỉ về điện thờ cổ trong Rừng Phong Lôi. Linh Hồn Cổ Thụ sẽ cho cháu câu trả lời."];
    return ["Đường đến Đèo Gió Bấc còn xa. Hãy chuẩn bị kỹ trước khi đi, Kael."];
  }
  function maeLines() {
    if (!q.started) return ["Ta là Mae. Vết sẹo trên tai Lôi Miêu cần cỏ Tinh Linh mới lành được.", "Hãy hái giúp ta 5 Ngọn Cỏ Dạ Quang ở thung lũng đồng cỏ."];
    if (q.progress >= 5) return ["Đủ 5 ngọn rồi! Ta sẽ sắc thuốc ngay cho Lôi Miêu."];
    return [`Cháu đã hái ${q.progress}/5 ngọn. Cỏ Dạ Quang phát sáng giữa thung lũng đồng cỏ.`];
  }
  function branLines() {
    if (q.done.includes("MQ003")) return ["Rừng yên hơn rồi. Cảm ơn cháu, Kael."];
    if (!q.started) return ["Ta là Bran, thợ săn ở bìa rừng.", "Bầy Dã Lang Bão Tố hung hãn khác thường. Hạ giúp ta 3 con."];
    if (q.progress >= 3) return ["Ba con rồi à? Giỏi lắm. Có thứ gì đó làm chúng phát cuồng…"];
    return [`Mới ${q.progress}/3 con. Chúng hay xuất hiện trong rừng này.`];
  }
  // Called when a dialogue ends (index.html interact()).
  function talked(npc) {
    const quest = current();
    if (!npc.questAfter && p2Talked(npc)) return;
    if (npc.questAfter) { const after = npc.questAfter; after(); updateGuidance(); return; }
    if (npc.id === "elder" && !q.intro) { q.intro = true; q.started = true; save(); statusEl.textContent = "Nhiệm vụ chính · MQ001 Tia Chớp Phế Phẩm"; return; }
    if (npc.id === "elder" && quest?.id === "MQ005" && q.progress >= 3) { complete(); return; }
    if (npc.questTalk === "bran" && quest?.id === "MQ003") {
      if (!q.started) { q.started = true; save(); statusEl.textContent = "Nhiệm vụ chính · MQ003 Tiếng Gầm Ngoài Biên Giới"; }
      else if (q.progress >= 3) complete();
    }
    if (npc.questTalk === "mae" && quest?.id === "MQ004") {
      if (!q.started) { q.started = true; save(); statusEl.textContent = "Nhiệm vụ chính · MQ004 Thu Thập Thảo Dược Hồi Phục"; }
      else if (q.progress >= 5) complete();
    }
  }
  // MQ006: the three villagers stand in dark fire until rescued; talking to one starts its fight.
  function burning(npc) { return isActive("MQ006") && onMap(VILLAGE) && ["herbalist", "villager", "scout"].includes(npc.id) && !q.marks.includes(npc.id); }
  function interact() {
    const npc = nearbyNpc();
    if (npc && burning(npc)) { fightRaider(npc); return true; }
    const spot = here();
    if (spot) { spot.run(); return true; }
    return false;
  }
  // MQ003: in the forest, half of the wild encounters are the raging wolves.
  function forceWild(mapId) {
    const forced = p2ForceWild(mapId);
    if (forced) return forced;
    if (!isActive("MQ003") || mapId !== FOREST || q.progress >= 3 || Math.random() >= .5) return null;
    return { petId: WOLF, area: "MQ003", level: 3 + Math.floor(Math.random() * 2), quality: 2, questName: "Dã Lang Bão Tố" };
  }
  function wildWon(enemy) {
    p2WildWon(enemy);
    if (!isActive("MQ003") || enemy.petId !== WOLF || q.progress >= 3) return;
    q.progress++; save();
    statusEl.textContent += ` · Dã Lang Bão Tố ${q.progress}/3`;
  }

  // ---- what the screen shows ----
  function objective() {
    const quest = current();
    if (!quest) return "Chương 1 · Ryan đã hoàn thành. Chương 2 (từ MQ021) chưa có trong bản thử.";
    if (!q.intro) return null;
    if (byIdP2.has(quest.id)) return p2Objective(byIdP2.get(quest.id));
    if (!q.started) return `${quest.id} · ${quest.goal}`;
    const n = quest.count ? ` (${Math.min(q.progress, quest.count)}/${quest.count})` : "";
    if (quest.id === "MQ003") return q.progress >= 3 ? "MQ003 · Về báo Thợ săn Bran" : `MQ003 · Hạ Dã Lang Bão Tố trong Rừng Phong Lôi${n}`;
    if (quest.id === "MQ004") return q.progress >= 5 ? "MQ004 · Mang cỏ về cho Dược sĩ Mae" : `MQ004 · Hái Cỏ Dạ Quang ở thung lũng đồng cỏ${n}`;
    if (quest.id === "MQ005") return q.progress >= 3 ? "MQ005 · Về báo Trưởng làng Elden" : `MQ005 · Lần theo dấu chân tà khí ở Suối Sấm${n}`;
    return `${quest.id} · ${quest.goal}${n}`;
  }
  function actionLabel() {
    const npc = nearbyNpc();
    if (npc && burning(npc)) return "Cứu " + npc.name;
    return here()?.label || "";
  }
  const night = () => onMap(VILLAGE) && ["MQ006", "MQ007", "MQ008"].includes(current()?.id) && q.started;
  function draw(ctx, S) {
    if (!assets.maps) return;
    const s = spots(), id = current()?.id, t = performance.now();
    const glow = (x, y, color, r = 7) => {
      ctx.save(); ctx.globalAlpha = .55 + Math.sin(t / 260) * .25; ctx.fillStyle = color;
      ctx.beginPath(); ctx.arc(x * S + 10, y * S + 10, r, 0, Math.PI * 2); ctx.fill(); ctx.restore();
    };
    p2Draw(ctx, S, glow);
    if (!q.started) return;
    if (night()) { ctx.save(); ctx.fillStyle = "rgba(70,10,30,.28)"; ctx.fillRect(0, 0, assets.map.width * S, assets.map.height * S); ctx.restore(); }
    if (id === "MQ001" && onMap(VILLAGE)) { const d = s.get("dummy"); const img = cachedImage("assets/scenery/stump.png"); if (img?.complete) ctx.drawImage(img, d.x * S - 2, d.y * S - 4, 24, 24); glow(d.x, d.y, "#ffe08a", 4); }
    if (id === "MQ002" && onMap(VILLAGE)) { const c = s.get("cellar"); ctx.fillStyle = "#3b2a1c"; ctx.fillRect(c.x * S + 3, c.y * S + 6, 14, 10); ctx.fillStyle = "#8a6a3c"; ctx.fillRect(c.x * S + 3, c.y * S + 6, 14, 3); glow(c.x, c.y, "#ffd36b", 5); }
    if (id === "MQ004" && onMap(MEADOW)) s.get("grass").forEach((g, n) => { if (!q.marks.includes(n)) glow(g.x, g.y, "#bfff8a"); });
    if (id === "MQ005" && onMap(STREAM) && q.progress < 3) { const p = s.get("prints")[q.progress]; glow(p.x, p.y, "#a64cff", 6); ctx.fillStyle = "#2a0d3a"; ctx.fillRect(p.x * S + 6, p.y * S + 8, 3, 5); ctx.fillRect(p.x * S + 11, p.y * S + 5, 3, 5); }
    if (id === "MQ006" && onMap(VILLAGE)) for (const npc of villageNpcs()) if (burning(npc)) flames(ctx, S, npc.x, npc.y, t);
    if (id === "MQ007" && onMap(VILLAGE)) { const c = s.get("colossus"); glow(c.x, c.y, "#ff7a3a", 9); }
    if (id === "MQ008" && onMap(VILLAGE)) { const e = assets.map.entities.encounter; glow(e.x, e.y, "#7a2cff", 10); }
    if (id === "MQ009" && onMap(FOREST)) { const c = s.get("shrine"); glow(c.x, c.y, "#9effd6", 10); }
  }
  function flames(ctx, S, x, y, t) {
    for (let i = 0; i < 4; i++) {
      const fx = x * S + 2 + i * 5, h = 8 + Math.sin(t / 90 + i * 1.7) * 4;
      ctx.fillStyle = "rgba(120,40,200,.8)"; ctx.fillRect(fx, y * S + 18 - h, 4, h);
      ctx.fillStyle = "rgba(230,120,255,.7)"; ctx.fillRect(fx + 1, y * S + 18 - h * .6, 2, h * .6);
    }
  }
  function renderJournal(list) {
    list.replaceChildren();
    const card = list.parentElement;
    const h3 = card?.querySelector?.("h3"), intro = card?.querySelector?.("p");
    if (h3) h3.textContent = "Nhiệm vụ chính · Chương 1 · Ryan";
    if (intro) intro.textContent = "MQ001–MQ020 theo bảng nhiệm vụ (bản thử). Nhiệm vụ kế tiếp mở khi xong nhiệm vụ trước.";
    QUESTS.forEach((quest, i) => {
      const li = document.createElement("li"), done = q.done.includes(quest.id), now = i === q.index;
      li.className = done ? "done" : now ? "current" : "pending";
      li.textContent = done ? `✓ ${quest.id} · ${quest.title}` : now ? `▶ ${quest.id} · ${quest.title} — ${q.intro ? quest.text : "Nói chuyện với Trưởng làng Elden."} (Thưởng: ${quest.rewardText})` : `○ ${quest.id} · ${quest.title} · ${quest.place}`;
      list.append(li);
    });
    if (q.items.length) { const li = document.createElement("li"); li.className = "done"; li.textContent = `Vật phẩm cốt truyện: ${[...new Set(q.items)].join(", ")}`; list.append(li); }
  }
  function wildCaught(enemy) {
    const def = p2(), i = def?.task.spots?.findIndex(x => x.battle?.capture) ?? -1;
    if (i >= 0 && q.started && enemy.petId === def.task.spots[i].battle.pet) markSpot(def, { ...def.task.spots[i], i });
  }
  // The scene shown in "ending" mode: the compass awakening after MQ008, the end of the chapter after MQ020.
  function endingScene() {
    if (endingCard !== "chapter1") return null;
    return { title: "CHƯƠNG 1 · HOÀN THÀNH", heading: "ANH HÙNG RYAN", lines: ["Cổ Long Hắc Ám tan biến trong Quang Minh Trận.", "Dưới chân tháp, Kael tìm thấy bức thư của cha."], hint: "Nhấn E hoặc Tiếp tục. Chương 2 chưa có trong bản thử." };
  }
  function continueFromEnding() {
    if (state.mode !== "ending") return false;
    endingCard = null;
    state.mode = "explore"; state.battle = newBattle(); updateGuidance(); return true;
  }

  window.Quests = {
    list: QUESTS, state: () => q, current, interact, talked, npcs, objective, actionLabel, draw, renderJournal, forceWild, wildWon,
    battleWon, battleLost, continueFromEnding, wildCaught, endingScene, night, storyFightOpen: () => false, spots,
    save: () => JSON.parse(JSON.stringify(q)),
    load(data) { q = data && Number.isInteger(data.index) ? { ...fresh(), ...data } : fresh(); save(); },
    reset() { q = fresh(); save(); },
    // test helper: jump to a quest with its steps before marked done
    jump(id) {
      const i = ids.indexOf(id); if (i < 0) return;
      const def = byIdP2.get(id);
      q = { ...fresh(), index: i, intro: true, started: def ? !def.npc && !def.start : !["MQ003", "MQ004"].includes(id), done: ids.slice(0, i) }; save();
    },
    // where a part-2 quest's NPC, start point and spots stand (tests and the map screen)
    where(id) {
      const def = byIdP2.get(id); if (!def) return null;
      return { npc: def.npc ? { map: def.npc.map, ...place(def.npc, `${def.npc.map}:${def.id}:npc`) } : null,
        start: def.start ? { map: def.start.map, ...place(def.start, `${def.start.map}:${def.id}:start`) } : null,
        spots: p2Spots(def).map(x => ({ map: x.map, x: x.at.x, y: x.at.y, waves: x.waves || 0, capture: !!x.battle?.capture, needElement: x.needElement || null })) };
    },
  };
})();
