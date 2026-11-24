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
  // ---- Chương 2 · Hải Vực và Atlantis (MQ021–MQ042), on the sea maps of the atlas route. ----
  const B = (pet, level, name, more = {}) => ({ pet, level, name, ...more });
  const SAY = (who, ...lines) => [who, lines];
  PART2.push(
    { id: "MQ021", chapter: 2, title: "Lời Cảnh Báo Ngoài Đại Dương", place: "Bến cảng Ryan", giver: "Thuyền trưởng Drake",
      text: "Thuyền trưởng Drake cần vật liệu để chiến thuyền vượt Hải Vực Vô Tận: 10 Gỗ Lim Thần (5 đống gỗ) và 5 Cánh Buồm Bão (kho buồm).",
      npc: { id: "drake", name: "Thuyền trưởng Drake", map: "ryan-port", near: "tower-crown",
        offer: ["Muốn ra khơi à? Hải Vực Vô Tận nuốt chửng thuyền thường đấy.", "Gom cho ta Gỗ Lim Thần ở năm đống gỗ quanh bến và Cánh Buồm Bão trong kho."], wait: ["Thiếu gỗ thì thân tàu không chịu nổi sóng."], done: ["Đủ cả rồi! Chiến Thuyền Viễn Chinh sẵn sàng."] },
      task: { report: true, spots: [[.2, .3], [.8, .3], [.3, .6], [.7, .6], [.5, .8]].map(frac => ({ map: "ryan-port", frac, label: "Nhặt 2 Gỗ Lim Thần" }))
        .concat([{ map: "ryan-port", frac: [.5, .45], label: "Lấy 5 Cánh Buồm Bão trong kho" }]) },
      reward: { exp: 8200, story: ["Chiến Thuyền Viễn Chinh"] }, rewardText: "8.200 EXP, Chiến Thuyền Viễn Chinh" },
    { id: "MQ022", chapter: 2, title: "Làn Nước Tím Ma Quái", place: "Hải Vực Vô Tận", giver: "Thủy thủ đoàn",
      text: "Nước biển đổi màu huỳnh quang. Lấy 3 mẫu nước nhiễm Hư Không Tinh Thể.",
      npc: { id: "sailor", name: "Thủy thủ đoàn", map: "endless-sea", near: "ryan-port", offer: ["Thuyền trưởng ơi, nước đang phát sáng tím!", "Lấy mẫu ở ba chỗ nước sáng nhất giúp bọn tôi."], wait: ["Cẩn thận, nước đó lạnh buốt."] },
      task: { spots: [[.3, .3], [.7, .45], [.4, .7]].map(frac => ({ map: "endless-sea", frac, label: "Lấy mẫu nước tím" })) },
      reward: { exp: 9000, relic: 9 }, rewardText: "9.000 EXP, Cá Thờn Bơn (thay cho Kính Thủy Tinh Lọc)" },
    { id: "MQ023", chapter: 2, title: "Cơn Thịnh Nộ Thủy Quái Cơ Khí", place: "Vùng Biển Chết", giver: "Cá mập cơ khí đột biến",
      text: "Quái vật nửa sống nửa máy tấn công chiến thuyền. Đánh chìm 2 Thủy Quái Bọc Thép.",
      task: { spots: [[.25, .55], [.75, .75]].map(frac => ({ map: "endless-sea", frac, label: "Đánh Thủy Quái Bọc Thép", battle: B("vqsv6-db-71", 28, "Thủy Quái Bọc Thép", { hpScale: 2 }) })) },
      reward: { exp: 10000, stones: { 13: 1 }, story: ["Mảnh Hợp Kim Biển"] }, rewardText: "10.000 EXP, Mảnh Hợp Kim Biển (thêm 1 Thiên Giới Tinh Thạch)" },
    { id: "MQ024", chapter: 2, title: "Lốc Xoáy Lôi Đình Cuốn Phăng", place: "Vòng xoáy lôi đình", giver: "Bão tố biển sâu",
      text: "Lốc xoáy lôi đình cuốn tàu. Lái thuyền né 3 cột sóng xoáy theo đúng thứ tự.",
      task: { ordered: true, spots: [[.3, .35], [.6, .55], [.4, .8]].map((frac, i) => ({ map: "thunder-whirlpool", frac, label: `Né cột sóng xoáy ${i + 1}`, say: SAY("Bánh lái", ["Bẻ lái sang trái!", "Giữ chặt buồm!", "Lao qua khe sóng!"][i]) })) },
      reward: { exp: 11200, gold: 2500 }, rewardText: "11.200 EXP, 2.500 kim tiền" },
    { id: "MQ025", chapter: 2, title: "Vương Quốc Chìm Atlantis", place: "Cổng San Hô Atlantis", giver: "Lính gác Hải Tộc",
      text: "Tỉnh dậy dưới đáy biển. Thuyết phục Hải Tộc rằng Kael không phải kẻ xâm lăng: đặt lại vũ khí trước cổng rồi nói chuyện với lính gác.",
      npc: { id: "seaguard", name: "Lính gác Hải Tộc", map: "atlantis", near: "thunder-whirlpool", offer: ["Dừng lại, kẻ trên cạn!", "Muốn vào Atlantis thì đặt vũ khí ở cột đá kia."], wait: ["Vũ khí của ngươi đâu?"], done: ["Ngươi không mang ý xâm lăng… Được, vào đi."] },
      task: { report: true, spots: [{ map: "atlantis", frac: [.4, .3], label: "Đặt vũ khí ở cột đá", say: SAY("Cột đá", "Kael đặt vũ khí xuống. Lôi Miêu ngồi yên bên cạnh.") }] },
      reward: { exp: 12500, items: { 10: 3 } }, rewardText: "12.500 EXP, 3 Vạn năng dược (thay cho Thuốc Thở Dưới Nước)" },
    { id: "MQ026", chapter: 2, title: "Gặp Gỡ Nàng Lyra", place: "Cung điện San Hô", giver: "Lyra (Bán Nhân Ngư)",
      text: "Gặp Lyra, nữ hộ vệ của quả trứng Thương Hải Thần Cự Kình, và nhận lời ủy thác của nàng.",
      npc: { id: "lyra", name: "Lyra", map: "atlantis", frac: [.6, .5], offer: ["Ta là Lyra, hộ vệ của quả trứng Thần Cự Kình.", "Quân đoàn cơ giới đang săn nó. Ngươi… sẽ giúp ta bảo vệ nó chứ?"] },
      task: { talkOnly: true }, reward: { exp: 14000, relic: 8 }, rewardText: "14.000 EXP, Hấp Huyết Đằng Mạn (thay cho Vòng Tay San Hô)" },
    { id: "MQ027", chapter: 2, title: "Tập Kích Của Quân Đoàn Cơ Giới", place: "Vườn ươm Cự Kình", giver: "Tiểu đội thiết giáp",
      text: "Quân đoàn cơ giới đột kích để cướp quả trứng. Bảo vệ Lyra và quả trứng: cầm cự 6 lượt.",
      task: { spots: [{ map: "atlantis", frac: [.55, .75], label: "Bảo vệ quả trứng", battle: B("vqsv6-db-83", 32, "Tiểu đội thiết giáp", { hpScale: 3, survive: 6, noFlee: true }) }] },
      reward: { exp: 15800, relic: 2 }, rewardText: "15.800 EXP, Quy Xác Toái Phiến (thay cho Khiên Nước Atlantis)" },
    { id: "MQ028", chapter: 2, title: "Sự Thật Về Người Cha Hiến Tế", place: "Đền tưởng niệm biển", giver: "Lyra",
      text: "Lyra kể rằng cha Kael đã hiến mình làm vật tế phong ấn mười năm trước. Tìm phiến đá ký ức của cha.",
      task: { spots: [{ map: "atlantis", frac: [.25, .7], label: "Chạm phiến đá ký ức", say: SAY("Phiến đá ký ức", "Giọng cha vang lên: \"Kael… nếu con nghe được, hãy chăm sóc Lôi Miêu.\"", "\"Cha phải ở lại giữ phong ấn. Đừng trách mẹ con.\"") }] },
      reward: { exp: 17800, story: ["Mảnh Ký Ức Lam"] }, rewardText: "17.800 EXP, Mảnh Ký Ức Lam" },
    { id: "MQ029", chapter: 2, title: "Âm Mưu Của Tiến Sĩ Vane", place: "Phòng thí nghiệm đáy biển", giver: "Băng ghi âm cổ",
      text: "Băng ghi âm lộ dã tâm cấy linh hồn sủng vật vào cỗ máy. Vượt qua 3 bẫy điện tử theo thứ tự.",
      task: { ordered: true, spots: [[.3, .3], [.6, .45], [.8, .7]].map(frac => ({ map: "coral-grotto", frac, label: "Phá bẫy điện tử", battle: B("vqsv6-db-82", 34, "Bẫy điện tử") })) },
      reward: { exp: 20000, story: ["Lõi Vi Mạch Cổ"] }, rewardText: "20.000 EXP, Lõi Vi Mạch Cổ" },
    { id: "MQ030", chapter: 2, title: "Thử Thách San Hô Lân Quang", place: "Hang San Hô sâu", giver: "Ảo cảnh nỗi sợ",
      text: "Ảo ảnh ngày làng cháy hiện về. Vượt qua nó mà không dùng bạo lực: cầm cự 5 lượt, không hạ gục ảo ảnh.",
      task: { spots: [{ map: "coral-grotto", frac: [.5, .8], label: "Đối mặt ảo cảnh", battle: B("vqsv6-db-96", 35, "Ảo cảnh nỗi sợ", { hpScale: 3, survive: 5, mercy: true, noFlee: true }) }] },
      reward: { exp: 22500, story: ["Ngọc Lân Quang"] }, rewardText: "22.500 EXP, Ngọc Lân Quang" },
    { id: "MQ031", chapter: 2, title: "Vết Sẹo Là Sức Mạnh", place: "Đài Tinh Thần Lôi Đình", giver: "Lôi Miêu",
      text: "Lôi Miêu chấp nhận vết sẹo của mình. Đưa nó lên đài lôi đình để đánh thức đôi cánh sấm sét.",
      task: { spots: [{ map: "thunder-whirlpool", frac: [.7, .25], label: "Bước lên đài lôi đình", say: SAY("Lôi Miêu", "Sét giáng xuống đài. Vết sẹo trên tai Lôi Miêu sáng rực.", "Đôi cánh sấm sét kim sắc bung ra!") }] },
      reward: { exp: 25500, story: ["Lôi Dực Khiêu Vũ"] }, rewardText: "25.500 EXP, kỹ năng Lôi Dực Khiêu Vũ cho Lôi Miêu" },
    { id: "MQ032", chapter: 2, title: "Lạc Vào Nghĩa Địa Chiến Thuyền", place: "Nghĩa Địa Chiến Thuyền", giver: "Xác tàu cổ",
      text: "Tìm lối xuống Vực Thẳm: đánh lui oan hồn canh xác tàu rồi tìm la bàn áp suất trong buồng hạm trưởng.",
      task: { ordered: true, spots: [
        { map: "wreck-graveyard", frac: [.4, .4], label: "Đánh lui oan hồn", battle: B("vqsv6-db-93", 37, "Oan hồn xác tàu") },
        { map: "wreck-graveyard", frac: [.65, .7], label: "Lục buồng hạm trưởng", say: SAY("Buồng hạm trưởng", "Dưới tấm hải đồ mục là chiếc la bàn áp suất vẫn còn chạy.") }] },
      reward: { exp: 29000, story: ["La Bàn Áp Suất"] }, rewardText: "29.000 EXP, La Bàn Áp Suất" },
    { id: "MQ033", chapter: 2, title: "U Linh Sa Ngư Lạc Lối", place: "Vùng tối thủy triều", giver: "U Linh Sa Ngư",
      text: "Một linh thể từng bị thí nghiệm tàn nhẫn đang lạc lối. Thuần hóa U Linh Sa Ngư: đánh nhẹ tay rồi dùng cầu bắt.",
      task: { spots: [{ map: "wreck-graveyard", frac: [.25, .75], label: "Tiếp cận U Linh Sa Ngư", battle: B("vqsv6-db-70", 38, "U Linh Sa Ngư", { capture: true }) }] },
      reward: { exp: 33000 }, rewardText: "33.000 EXP, U Linh Sa Ngư gia nhập đội" },
    { id: "MQ034", chapter: 2, title: "Bẻ Khóa Trạm Quan Sát Ngầm", place: "Cửa Vực Sâu Vane", giver: "Cơ chế khóa sinh học",
      text: "Các tháp phát sóng làm nhiễu tâm trí sủng vật. Vô hiệu hóa 3 trạm phát sóng cơ giới.",
      task: { spots: [[.2, .25], [.5, .2], [.8, .25]].map(frac => ({ map: "ocean-abyss", frac, label: "Phá trạm phát sóng", battle: B("vqsv6-db-84", 39, "Trạm phát sóng cơ giới", { hpScale: 2 }) })) },
      reward: { exp: 37500, story: ["Thẻ Truy Cập Hư Vô"] }, rewardText: "37.500 EXP, Thẻ Truy Cập Hư Vô" },
    { id: "MQ035", chapter: 2, title: "Đường Xuống Vực Thẳm Tận Cùng", place: "Vực áp suất cực hạn", giver: "Lyra",
      text: "Lyra dùng Thương Hải Chi Lệ tạo màng bong bóng chịu áp suất. Dẫn cả nhóm vượt 3 khe đá nứt theo thứ tự.",
      task: { ordered: true, spots: [[.3, .4], [.55, .5], [.35, .6]].map((frac, i) => ({ map: "ocean-abyss", frac, label: `Vượt khe đá nứt ${i + 1}`, say: SAY("Lyra", ["Giữ sát nhau, bong bóng mỏng lắm!", "Áp suất tăng… cố lên!", "Qua rồi! Phía dưới là căn cứ của Vane."][i]) })) },
      reward: { exp: 42500, relic: 2 }, rewardText: "42.500 EXP, Quy Xác Toái Phiến (thay cho Áo Giáp Lặn Thần)" },
    { id: "MQ036", chapter: 2, title: "Hình Bóng Trong Lồng Kính", place: "Trung tâm thí nghiệm Vane", giver: "Cha Kael (bị giam cầm)",
      text: "Cha Kael bị gắn vào cỗ máy hình nhện rút năng lượng. Đánh bại hộ vệ người máy canh phòng giam.",
      task: { spots: [{ map: "ocean-abyss", frac: [.7, .45], label: "Đánh hộ vệ người máy", battle: B("vqsv6-db-85", 41, "Hộ vệ người máy", { hpScale: 3, noFlee: true }) }] },
      reward: { exp: 48000, story: ["Chìa Khóa Lồng Kính"] }, rewardText: "48.000 EXP, Chìa Khóa Lồng Kính" },
    { id: "MQ037", chapter: 2, title: "Kỷ Nguyên Thần Thú Cơ Khí?", place: "Sàn đấu lõi Vực Thẳm", giver: "Tiến sĩ Vane",
      text: "Tiến sĩ Vane tuyên bố về sự bất tử của máy móc. Đập tan 2 cánh tay robot khổng lồ bảo vệ bục.",
      task: { spots: [[.2, .7], [.8, .7]].map(frac => ({ map: "ocean-abyss", frac, label: "Đập cánh tay robot", battle: B("vqsv6-db-81", 42, "Cánh tay robot", { hpScale: 2, noFlee: true }) })) },
      reward: { exp: 54000, stones: { 14: 1 } }, rewardText: "54.000 EXP, Thiên Địa Thần Thạch (thay cho Mảnh Thép Titan)" },
    { id: "MQ038", chapter: 2, title: "Cơ Giới Thần Long Xuất Thế", place: "Lõi Vực Thẳm", giver: "Cơ Giới Thần Long Hư Không",
      text: "Cơ Giới Thần Long hấp thụ mọi nguyên tố. Phá hủy 4 bộ hấp thụ năng lượng ở bốn góc.",
      task: { spots: [[.12, .12], [.88, .12], [.12, .88], [.88, .88]].map(frac => ({ map: "ocean-abyss", frac, label: "Phá bộ hấp thụ năng lượng", battle: B("vqsv6-db-80", 43, "Bộ hấp thụ năng lượng") })) },
      reward: { exp: 61000, relic: 3 }, rewardText: "61.000 EXP, Ô Nha Uế (thay cho Tinh Thể Kháng Nguyên Tố)" },
    { id: "MQ039", chapter: 2, title: "Giai Điệu Ru Thơ Ấu", place: "Lõi cỗ máy", giver: "Chiếc la bàn đồng",
      text: "Kael nghe tiếng cha hát ru. Kích hoạt khúc nhạc tuổi thơ qua la bàn cổ để đánh thức linh hồn rồng.",
      task: { spots: [{ map: "ocean-abyss", frac: [.5, .35], label: "Mở khúc nhạc qua la bàn", say: SAY("La bàn đồng", "Kim la bàn quay tròn, phát ra khúc hát ru cha từng hát.", "Sâu trong lớp vỏ sắt, một linh hồn rồng khẽ cựa mình.") }] },
      reward: { exp: 69000, story: ["Kỷ Vật Gia Đình Cấp 2"] }, rewardText: "69.000 EXP, Kỷ Vật Gia Đình Cấp 2" },
    { id: "MQ040", chapter: 2, title: "Xé Toạc Lớp Vỏ Sắt", place: "Lưng Cự Long", giver: "Lõi sinh học Thần Long",
      text: "Buông vũ khí, dùng năng lượng thanh tẩy giải thoát linh hồn rồng: cầm cự 6 lượt mà không hạ gục nó.",
      task: { spots: [{ map: "ocean-abyss", frac: [.5, .6], label: "Thanh tẩy linh hồn rồng", battle: B("vqsv6-db-108", 44, "Cơ Giới Thần Long", { hpScale: 4, survive: 6, mercy: true, noFlee: true }) }] },
      reward: { exp: 78000, story: ["Long Lân Bạc"] }, rewardText: "78.000 EXP, Long Lân Bạc" },
    { id: "MQ041", chapter: 2, title: "Sụp Đổ Dưới Đáy Biển", place: "Căn cứ sụp đổ", giver: "Tiến sĩ Vane & Vực Sâu",
      text: "Căn cứ nổ tung. Dìu cha Kael chạy qua 3 hành lang sụp đổ theo thứ tự.",
      task: { ordered: true, spots: [[.5, .45], [.35, .3], [.2, .15]].map((frac, i) => ({ map: "ocean-abyss", frac, label: `Chạy qua hành lang ${i + 1}`, say: SAY("Cha Kael", ["Đừng bỏ ta lại… à không, đi đi Kael!", "Trần sắp sập rồi!", "Ánh sáng phía trên kìa!"][i]) })) },
      reward: { exp: 88000, relic: 10 }, rewardText: "88.000 EXP, Cảm Lãm Chi Diệp (thay cho Nhẫn Bảo Vệ Biển Sâu)" },
    { id: "MQ042", chapter: 2, title: "Thương Hải Cự Kình Tái Sinh", place: "Mặt nước đại dương", giver: "Thần Cự Kình (mới nở)",
      text: "Trứng cự kình nở, đưa cả nhóm trồi lên mặt biển. Xem cảnh cha con đoàn tụ.",
      task: { spots: [{ map: "atlantis", frac: [.55, .75], label: "Đến bên quả trứng", say: SAY("Thần Cự Kình", "Vỏ trứng nứt, một chú cá voi ánh bạc cất tiếng ca.", "Nó cõng cả nhóm vút lên mặt biển. Cha ôm chặt Kael; Lyra mỉm cười.") }] },
      reward: { exp: 100000, story: ["Tín Vật Tình Bạn Lyra"] }, rewardText: "100.000 EXP, Tín Vật Tình Bạn Lyra" },
  );
  NPC_ART.drake = "scout"; NPC_ART.sailor = "villager"; NPC_ART.seaguard = "villager"; NPC_ART.lyra = "herbalist";
  // ---- Chương 3 · Aethelgard và Boran (MQ043–MQ060), on the floating-continent maps of the atlas route. ----
  const RANGE3 = (map, list, label, extra = () => ({})) => list.map((frac, i) => ({ map, frac, label: typeof label === "function" ? label(i) : label, ...extra(i) }));
  PART2.push(
    { id: "MQ043", chapter: 3, title: "Lời Dặn Của Người Cha Bệnh Tật", place: "Doanh trại bờ biển", giver: "Cha Kael",
      text: "Cha dưỡng thương ở doanh trại, dặn Kael tiến lên Lục Địa Bay và trao Thánh Vật Khí Cầu Cổ.",
      npc: { id: "father", name: "Cha Kael", map: "aethelgard-gate", near: "ocean-abyss", offer: ["Ta còn yếu, chưa đi cùng con được.", "Cầm lấy Khí Cầu Cổ. Lục Địa Bay Aethelgard đang chờ, và Boran đang làm điều tồi tệ ở đó."] },
      task: { talkOnly: true }, reward: { exp: 110000, story: ["Khí Cầu Cổ"] }, rewardText: "110.000 EXP, Thánh Vật Khí Cầu Cổ" },
    { id: "MQ044", chapter: 3, title: "Hẹn Ước Dưới Ánh Trăng", place: "Mũi tàu bay", giver: "Lyra",
      text: "Dưới ánh trăng, Lyra thề sát cánh bên Kael dù hiểm nguy nào.",
      npc: { id: "lyra3", name: "Lyra", map: "aethelgard-gate", frac: [.3, .35], offer: ["Kael… dù phía trước là gì, ta sẽ ở cạnh cậu.", "Đây là lời hứa của Hải Tộc. Không bao giờ thất hứa."] },
      task: { talkOnly: true }, reward: { exp: 122000, relic: 5 }, rewardText: "122.000 EXP, Mật Phong Sào (thay cho Nhẫn Đôi Tương Tư)" },
    { id: "MQ045", chapter: 3, title: "Xuyên Qua Vùng Dông Bão Khí Quyển", place: "Tầng mây nghịch lưu", giver: "Bão từ trên không",
      text: "Từ trường Aethelgard làm nhiễu tàu bay. Giữ bánh lái qua 3 tâm bão theo thứ tự.",
      task: { ordered: true, spots: RANGE3("aethelgard-gate", [[.55, .3], [.7, .5], [.55, .75]], i => `Giữ bánh lái qua tâm bão ${i + 1}`, i => ({ say: SAY("Bánh lái", ["Kim la bàn quay loạn!", "Gió xoáy hất ngược mũi tàu!", "Ra khỏi tâm bão rồi!"][i]) })) },
      reward: { exp: 135000, stones: { 13: 1 } }, rewardText: "135.000 EXP, Thiên Giới Tinh Thạch (thay cho Đá Từ Tính Bay)" },
    { id: "MQ046", chapter: 3, title: "Đặt Chân Lên Lục Địa Bay", place: "Cảng trời Aethelgard", giver: "Cư dân Thiên Không",
      text: "Một cư dân Thiên Không chỉ đường đến trạm dịch chuyển cổ. Kích hoạt nó.",
      npc: { id: "skyfolk", name: "Cư dân Thiên Không", map: "aethelgard-gate", frac: [.8, .7], offer: ["Người từ mặt đất? Đã lâu lắm rồi…", "Trạm dịch chuyển cổ ở phía bắc cảng. Kích hoạt được thì đi đâu cũng nhanh."], wait: ["Trạm ở phía bắc cảng trời."] },
      task: { spots: [{ map: "aethelgard-gate", frac: [.85, .2], label: "Kích hoạt trạm dịch chuyển", say: SAY("Trạm dịch chuyển", "Các vòng đá sáng lên lần lượt. Trạm Thiên Không Cổ đã hoạt động.") }] },
      reward: { exp: 150000, items: { 7: 3 } }, rewardText: "150.000 EXP, 3 Tụ Năng Thạch (thay cho Giày Lướt Gió)" },
    { id: "MQ047", chapter: 3, title: "Bản Đồ Miệng Núi Lửa Hắc Diệu", place: "Đài thiên văn trời", giver: "Bản đồ cổ tự",
      text: "Giải mã tọa độ ngọn núi lửa hắc diệu, cứ điểm của Thống Lĩnh Boran.",
      task: { spots: [{ map: "aethelgard-gate", frac: [.2, .2], label: "Giải mã bản đồ cổ tự", say: SAY("Bản đồ cổ tự", "Các chòm sao trên bản đồ khớp với bầu trời đêm nay.", "Tọa độ chỉ về Miệng Núi Lửa Hắc Diệu.") }] },
      reward: { exp: 166000, story: ["Kính Đo Tọa Độ"] }, rewardText: "166.000 EXP, Kính Đo Tọa Độ" },
    { id: "MQ048", chapter: 3, title: "Vượt Vùng Đất Chết Dung Nham", place: "Miệng Núi Lửa Hắc Diệu", giver: "Quái vật dung nham",
      text: "Sủng vật bị Boran cấy chip cuồng nộ lang thang khắp miệng núi lửa. Hạ 8 Quái Nham Thạch Cơ Giới.",
      task: { wild: { map: "obsidian-crater", pet: "vqsv6-db-55", level: [52, 54], name: "Quái Nham Thạch Cơ Giới", perWin: 1, count: 8 } },
      reward: { exp: 184000, relic: 2 }, rewardText: "184.000 EXP, Quy Xác Toái Phiến (thay cho Giáp Kháng Lửa)" },
    { id: "MQ049", chapter: 3, title: "Pháo Đài Sắt Boran", place: "Cửa pháo đài Hắc Diệu", giver: "Lính gác Titan",
      text: "Đột nhập pháo đài khai thác địa nhiệt: phá 2 cổng sắt do lính gác Titan canh.",
      task: { spots: RANGE3("obsidian-crater", [[.3, .8], [.7, .8]], "Phá cổng sắt", () => ({ battle: B("vqsv6-db-57", 54, "Lính gác Titan", { hpScale: 2 }) })) },
      reward: { exp: 204000, gold: 5000 }, rewardText: "204.000 EXP, 5.000 kim tiền" },
    { id: "MQ050", chapter: 3, title: "Tiếng Rên Xiết Dưới Lòng Đất", place: "Hầm khoan địa nhiệt", giver: "Cổ Thú Nham Thạch",
      text: "Cổ Thú Nham Thạch bị xích sắt cấy lò phản ứng. Thu thập dữ liệu để giải phóng dây xích ở 3 trụ xích.",
      task: { spots: RANGE3("obsidian-crater", [[.25, .3], [.5, .5], [.75, .3]], "Ghi dữ liệu trụ xích", () => ({ say: SAY("Trụ xích", "Lôi Miêu đánh hơi lớp kim loại nóng rực, ghi nhớ nhịp đập của lò.") })) },
      reward: { exp: 226000, story: ["Kìm Bẻ Xích Cổ"] }, rewardText: "226.000 EXP, Kìm Bẻ Xích Cổ" },
    { id: "MQ051", chapter: 3, title: "Đối Đầu Thống Lĩnh Boran", place: "Phòng lò phản ứng", giver: "Thống Lĩnh Boran",
      text: "Boran khoe khoang Nham Bạo Long bọc titan. Đánh bại 3 đợt thiết giáp tiên phong.",
      task: { spots: [{ map: "boran-arena", frac: [.5, .7], label: "Đánh đợt thiết giáp", waves: 3, battle: B("vqsv6-db-56", 58, "Thiết giáp tiên phong", { noFlee: true }) }] },
      reward: { exp: 250000, relic: 2 }, rewardText: "250.000 EXP, Quy Xác Toái Phiến (thay cho Khiên Hợp Kim)" },
    { id: "MQ052", chapter: 3, title: "Trùm 1 - Khởi Động: Pháo Đài Kim Loại", place: "Miệng núi lửa", giver: "Nham Bạo Long (giai đoạn 1)",
      text: "Lyra dùng Thủy Luân kích nổ mạch nước. Phá lớp Nhiệt Giáp của Nham Bạo Long.",
      task: { spots: [{ map: "boran-arena", frac: [.5, .35], label: "Phá Nhiệt Giáp", battle: B("vqsv6-db-18", 60, "Nhiệt Giáp Nham Bạo Long", { hpScale: 3, noFlee: true }) }] },
      reward: { exp: 280000, items: { 9: 3, 11: 2 } }, rewardText: "280.000 EXP, 3 Siêu năng tề và 2 Sinh mệnh thạch (thay cho Rương Báu Titan)" },
    { id: "MQ053", chapter: 3, title: "Trùm 1 - Nguy Hiểm: Lồng Sắt Từ Tính", place: "Miệng núi lửa", giver: "Nham Bạo Long (giai đoạn 2)",
      text: "Kael bị nhốt trong lồng từ tính. Cầm cự 5 lượt để Lyra phá lồng.",
      task: { spots: [{ map: "boran-arena", frac: [.5, .35], label: "Cầm cự trong lồng sắt", battle: B("vqsv6-db-18", 61, "Nham Bạo Long", { hpScale: 5, survive: 5, noFlee: true }) }] },
      reward: { exp: 315000, relic: 3 }, rewardText: "315.000 EXP, Ô Nha Uế (thay cho Ngọc Chống Từ)" },
    { id: "MQ054", chapter: 3, title: "Trùm 1 - Sinh Tử: Lôi Kích Băng Hàn", place: "Miệng núi lửa", giver: "Nham Bạo Long (giai đoạn 3)",
      text: "Sàn đấu ngập nham thạch. Kết liễu Nham Bạo Long bằng combo Lôi Kích Băng Hàn.",
      task: { spots: [{ map: "boran-arena", frac: [.5, .35], label: "Kết liễu Nham Bạo Long", battle: B("vqsv6-db-18", 62, "Nham Bạo Long", { hpScale: 3, noFlee: true,
        grantSkill: { name: "Lôi Kích Băng Hàn", power: 380, uses: 3, max: 3, description: "Lôi Miêu và Lyra phối hợp: sét và băng cùng giáng xuống." } }) }] },
      reward: { exp: 355000, relic: 0 }, rewardText: "355.000 EXP, Mạn Đà La Thạch (thay cho Thương Lôi Thạch)" },
    { id: "MQ055", chapter: 3, title: "Cái Giá Của Sự Cuồng Bạo", place: "Xác Nham Bạo Long", giver: "Boran thất trận",
      text: "Boran sụp đổ, lò phản ứng vỡ nát. Nói chuyện với linh hồn Cổ Thú Nham Thạch.",
      task: { spots: [{ map: "boran-arena", frac: [.3, .5], label: "Nói chuyện với linh hồn Cổ Thú", say: SAY("Linh hồn Cổ Thú Nham Thạch", "Xiềng xích đã gãy… cảm ơn đứa trẻ mang sấm sét.", "Hãy cẩn thận với kẻ trên tầng mây. Nàng ta nhìn thấy mọi thứ qua gương.") }] },
      reward: { exp: 400000, story: ["Lõi Dung Nham Tinh Khiết"] }, rewardText: "400.000 EXP, Lõi Dung Nham Tinh Khiết" },
    { id: "MQ056", chapter: 3, title: "Băng Bó Giữa Tàn Tro", place: "Doanh trại tạm núi lửa", giver: "Lyra",
      text: "Kael băng bó vết bỏng cho Lyra giữa tàn tro.",
      npc: { id: "lyra4", name: "Lyra", map: "boran-arena", frac: [.75, .5], offer: ["Ui… nhẹ tay thôi, Kael.", "…Cảm ơn cậu. Lúc ở trong lồng sắt, tớ đã rất sợ mất cậu."] },
      task: { talkOnly: true }, reward: { exp: 450000, items: { 10: 3 } }, rewardText: "450.000 EXP, 3 Vạn năng dược (thay cho Khăn Lụa Biển Xanh)" },
    { id: "MQ057", chapter: 3, title: "Cột Khói Tím Trên Đỉnh Trời", place: "Ranh giới tầng mây", giver: "Dấu vết Seraphina",
      text: "Lần theo 3 cột khói tím lên Cung Điện Kính, rồi chế Khiên Kháng Gương từ tro núi lửa.",
      task: { ordered: true, spots: RANGE3("cloud-stair", [[.3, .7], [.6, .5], [.4, .25], [.7, .2]], i => i < 3 ? `Lần theo cột khói tím ${i + 1}` : "Chế Khiên Kháng Gương",
        i => ({ say: i < 3 ? SAY("Cột khói tím", "Mùi hương lạ và tiếng cười văng vẳng từ phía trên.") : SAY("Lyra", "Tro núi lửa trộn nước biển, phủ lên mặt khiên…", "Xong! Gương của Seraphina sẽ không phản chiếu được chúng ta nữa.") })) },
      reward: { exp: 505000, story: ["Khiên Kháng Gương"] }, rewardText: "505.000 EXP, Khiên Kháng Gương" },
    { id: "MQ058", chapter: 3, title: "Chiến Hạm Gió Đột Kích", place: "Cầu vồng pha lê", giver: "Phi đội Kính Giới",
      text: "Phi đội ảo ảnh phục kích trên Cung điện pha lê. Tiêu diệt 12 Ảo Ảnh Phi Hành Cơ (mỗi trận một tốp 3).",
      task: { wild: { map: "crystal-palace", pet: "vqsv6-db-107", level: [62, 64], name: "Tốp Ảo Ảnh Phi Hành Cơ", perWin: 3, count: 12 } },
      reward: { exp: 565000, stones: { 14: 1 } }, rewardText: "565.000 EXP, Thiên Địa Thần Thạch (thay cho Mảnh Pha Lê Tím)" },
    { id: "MQ059", chapter: 3, title: "Trước Thềm Tầng Mây Khởi Nguyên", place: "Bậc thang pha lê", giver: "Cổng Khởi Nguyên",
      text: "Chuẩn bị cho trận chiến tối thượng: ba lô phải có ít nhất 3 sủng vật trước khi bước lên bậc thang pha lê.",
      task: { spots: [{ map: "crystal-palace", frac: [.5, .7], label: "Kiểm tra đội hình", needTeam: 3, say: SAY("Cổng Khởi Nguyên", "Ba linh thú đứng cạnh Kael. Cổng pha lê ngân lên một tiếng dài.") }] },
      reward: { exp: 630000, stones: { 12: 3 } }, rewardText: "630.000 EXP, 3 Tinh Nguyên Thạch (thay cho Đá Tẩy Tủy)" },
    { id: "MQ060", chapter: 3, title: "Lời Nguyền Của Nữ Hoàng Hư Không", place: "Cung điện Khởi Nguyên", giver: "Giọng nói Seraphina",
      text: "Seraphina tuyên bố sẽ tẩy não cả hai người. Bước qua cánh cửa gương khổng lồ.",
      task: { spots: [{ map: "crystal-palace", frac: [.5, .2], label: "Bước qua cửa gương", say: SAY("Giọng nói Seraphina", "Hahaha… cứ vào đi, những đứa trẻ ngây thơ.", "Trong Kính Giới, chính ký ức của các ngươi sẽ giết các ngươi.") }] },
      reward: { exp: 700000, relic: 3, story: ["Hộ Phù Ý Chí"] }, rewardText: "700.000 EXP, Hộ Phù Ý Chí" },
  );
  NPC_ART.father = "elder"; NPC_ART.lyra3 = "herbalist"; NPC_ART.lyra4 = "herbalist"; NPC_ART.skyfolk = "villager";
  // ---- Chương 4 · Kính Giới và hành trình lên trời (MQ061–MQ080). The table marks chapters 4–5 as expanded content
  // still to be reviewed; it is played as written. Enemy levels above the game's cap (70) are held at 70. ----
  PART2.push(
    { id: "MQ061", chapter: 4, title: "Bước Vào Kính Giới Vô Cực", place: "Điện gương", giver: "Seraphina",
      text: "Cung điện phản chiếu ngàn hình bóng. Tìm lối đi thật qua mê cung gương: chạm 4 tấm gương thật theo thứ tự.",
      task: { ordered: true, spots: RANGE3("mirror-hall", [[.15, .5], [.35, .25], [.6, .7], [.85, .4]], i => `Chạm tấm gương thật ${i + 1}`, i => ({ say: SAY("Gương", ["Mặt gương lạnh và thật. Lối đi mở tiếp.", "Hình bóng Kael trong gương không cử động theo… gương giả ở bên cạnh.", "Lôi Miêu gầm lên, chỉ đúng tấm gương thật.", "Cuối mê cung, một hành lang ký ức hiện ra."][i]) })) },
      reward: { exp: 780000, relic: 9 }, rewardText: "780.000 EXP, Cá Thờn Bơn (thay cho Kính Phù Thủy)" },
    { id: "MQ062", chapter: 4, title: "Huyễn Ảnh Quá Khứ", place: "Hành lang ký ức", giver: "Gương ký ức",
      text: "Ảo ảnh tái hiện nỗi đau và mất mát. Phá hủy 3 tấm gương tạo ảo giác.",
      task: { spots: RANGE3("mirror-hall", [[.25, .7], [.5, .3], [.75, .75]], "Phá gương ảo giác", () => ({ battle: B("vqsv6-db-94", 67, "Gương ảo giác", { hpScale: 2 }) })) },
      reward: { exp: 865000, items: { 10: 3 } }, rewardText: "865.000 EXP, 3 Vạn năng dược (thay cho Thuốc Bình Tâm)" },
    { id: "MQ063", chapter: 4, title: "Lời Thì Thầm Của Hư Không", place: "Điện gương thứ hai", giver: "Bóng ma tâm linh",
      text: "Sóng âm của Seraphina thao túng tâm trí. Giữ vững ý chí: cầm cự 6 lượt trước Bóng ma tâm linh.",
      task: { spots: [{ map: "mirror-hall", frac: [.9, .75], label: "Chống lại lời thì thầm", battle: B("vqsv6-db-96", 68, "Bóng ma tâm linh", { hpScale: 4, survive: 6, noFlee: true }) }] },
      reward: { exp: 955000, relic: 3 }, rewardText: "955.000 EXP, Ô Nha Uế (thay cho Dây Chuyền Bạc)" },
    { id: "MQ064", chapter: 4, title: "Khúc Hát Đại Dương Của Lyra", place: "Đài vọng kính", giver: "Lyra",
      text: "Lyra hát bản hùng ca biển khơi thanh tẩy cung điện. Đẩy lùi 3 đợt bóng gương quấy nhiễu theo thứ tự để nàng hát trọn.",
      task: { ordered: true, spots: RANGE3("mirror-hall", [[.4, .5], [.55, .55], [.7, .5]], i => `Bảo vệ khúc hát (đoạn ${i + 1})`, () => ({ battle: B("vqsv6-db-95", 69, "Bóng gương quấy nhiễu") })) },
      reward: { exp: 1050000, story: ["Bản Nhạc Cổ Biển"] }, rewardText: "1.050.000 EXP, Bản Nhạc Cổ Biển" },
    { id: "MQ065", chapter: 4, title: "Kích Hoạt Trận Đồ Pha Lê", place: "Trung tâm Kính Giới", giver: "Cột trụ pha lê",
      text: "Hội tụ ánh sáng: kích hoạt 4 trụ pha lê theo đúng thứ tự Bắc, Đông, Nam, Tây.",
      task: { ordered: true, spots: RANGE3("aethelgard-summit", [[.5, .15], [.85, .5], [.5, .85], [.15, .5]], i => `Kích hoạt trụ ${["Bắc", "Đông", "Nam", "Tây"][i]}`, i => ({ say: SAY("Trụ pha lê", i < 3 ? "Một tia sáng bắn về tâm trận đồ." : "Bốn tia sáng hội tụ. Trận Đồ Pha Lê bừng sáng!") })) },
      reward: { exp: 1150000, story: ["Tinh Cầu Pha Lê"] }, rewardText: "1.150.000 EXP, Tinh Cầu Pha Lê" },
    { id: "MQ066", chapter: 4, title: "Đao Kiếm Phản Chiếu", place: "Quảng trường gương", giver: "Binh đoàn tinh thể",
      text: "Đội cận vệ tinh thể tuần tra khắp Đỉnh Aethelgard. Tiêu diệt 15 Cận Vệ Gương (mỗi trận một tốp 3).",
      task: { wild: { map: "aethelgard-summit", pet: "vqsv6-db-95", level: [68, 70], name: "Tốp Cận Vệ Gương", perWin: 3, count: 15 } },
      reward: { exp: 1260000, relic: 0 }, rewardText: "1.260.000 EXP, Mạn Đà La Thạch (thay cho Kiếm Pha Lê Tinh Khiết)" },
    { id: "MQ067", chapter: 4, title: "Phá Vỡ Phong Ấn Thời Gian", place: "Đồng hồ cát pha lê", giver: "Đồng Hồ Vô Tận",
      text: "Seraphina tua ngược thời gian để chữa lành quái vật. Đập vỡ đồng hồ cát hư không.",
      task: { spots: [{ map: "aethelgard-summit", frac: [.3, .3], label: "Đập đồng hồ cát", battle: B("vqsv6-db-109", 70, "Đồng Hồ Vô Tận", { hpScale: 3, noFlee: true }) }] },
      reward: { exp: 1380000, story: ["Bụi Thời Gian"] }, rewardText: "1.380.000 EXP, Bụi Thời Gian" },
    { id: "MQ068", chapter: 4, title: "Trùm 2 - Khởi Động: Gương Đa Chiều", place: "Điện pha lê chính", giver: "Seraphina (giai đoạn 1)",
      text: "Không đánh trực diện: phá 4 tấm gương phản đòn ở bốn góc điện.",
      task: { spots: RANGE3("aethelgard-summit", [[.12, .12], [.88, .12], [.12, .88], [.88, .88]], "Phá gương phản đòn", () => ({ battle: B("vqsv6-db-94", 70, "Gương phản đòn", { hpScale: 2 }) })) },
      reward: { exp: 1520000, relic: 3 }, rewardText: "1.520.000 EXP, Ô Nha Uế (thay cho Áo Choàng Quang Học)" },
    { id: "MQ069", chapter: 4, title: "Trùm 2 - Đối Đầu: Ảo Ảnh Song Trùng", place: "Điện pha lê chính", giver: "Bản sao Kael & Lyra",
      text: "Hai bản sao mang dáng hình Kael và Lyra. Đánh bại cả hai.",
      task: { spots: [{ map: "aethelgard-summit", frac: [.4, .55], label: "Đánh bản sao Lôi Miêu", battle: B("vqsv6-db-79", 70, "Bản sao Lôi Miêu", { hpScale: 2, noFlee: true }) },
        { map: "aethelgard-summit", frac: [.6, .55], label: "Đánh bản sao của Lyra", battle: B("vqsv6-db-63", 70, "Bản sao của Lyra", { hpScale: 2, noFlee: true }) }] },
      reward: { exp: 1680000, story: ["Gương Song Sinh"] }, rewardText: "1.680.000 EXP, Gương Song Sinh" },
    { id: "MQ070", chapter: 4, title: "Bẫy Phân Rã Tâm Trí", place: "Không gian vô định", giver: "Seraphina",
      text: "Kael bị kéo vào hố đen ý thức. Cầm cự 5 lượt cho tới khi nắm được bàn tay ánh sáng của Lyra.",
      task: { spots: [{ map: "aethelgard-summit", frac: [.5, .3], label: "Chống chọi hố đen ý thức", battle: B("vqsv6-db-96", 70, "Hố đen ý thức", { hpScale: 5, survive: 5, noFlee: true }) }] },
      reward: { exp: 1850000, story: ["Trái Tim Dũng Cảm"] }, rewardText: "1.850.000 EXP, Trái Tim Dũng Cảm" },
    { id: "MQ071", chapter: 4, title: "Trùm 2 - Sinh Tử: Thủy Lôi Thần Hợp Nhất", place: "Không gian vỡ vụn", giver: "Kính Giới Thiên Sứ",
      text: "Sàn kính vỡ vụn, Lyra rải màng nước dẫn sét. Kết liễu Kính Giới Thiên Sứ.",
      task: { spots: [{ map: "aethelgard-summit", frac: [.5, .5], label: "Đối đầu Kính Giới Thiên Sứ", battle: B("vqsv6-db-102", 70, "Kính Giới Thiên Sứ", { hpScale: 5, noFlee: true,
        grantSkill: { name: "Thủy Lôi Thần Hợp Nhất", power: 420, uses: 3, max: 3, description: "Màng nước của Lyra dẫn sét của Lôi Miêu ra khắp sàn kính." } }) }] },
      reward: { exp: 2050000, relic: 4 }, rewardText: "2.050.000 EXP, Viễn Cổ Long Cốt (pháp bảo thay thế)" },
    { id: "MQ072", chapter: 4, title: "Tro Tàn Của Kẻ Kiến Trúc", place: "Sàn đấu đổ nát", giver: "Xác pha lê Seraphina",
      text: "Seraphina tan biến, để lại lời tiên tri về Mặt Trăng. Nhặt Khối Lập Phương Hư Vô.",
      task: { spots: [{ map: "aethelgard-summit", frac: [.55, .45], label: "Nhặt Khối Lập Phương Hư Vô", say: SAY("Xác pha lê Seraphina", "\"Các ngươi chỉ làm chậm điều tất yếu… Mặt Trăng Đỏ đã thức giấc.\"", "Giữa đống pha lê vỡ là một khối lập phương đen tuyền, lạnh buốt.") }] },
      reward: { exp: 2280000, story: ["Khối Lập Phương Hư Vô"] }, rewardText: "2.280.000 EXP, Khối Lập Phương Hư Vô" },
    { id: "MQ073", chapter: 4, title: "Cú Nhảy Vực Mây", place: "Mép lục địa bay", giver: "Lôi Miêu",
      text: "Cung điện sụp đổ. Kael ôm Lyra nhảy lên lưng Lôi Miêu, lượn qua 3 mảnh vỡ rơi theo thứ tự.",
      task: { ordered: true, spots: RANGE3("sky-monolith", [[.3, .2], [.7, .4], [.35, .6]], i => `Lượn qua mảnh vỡ ${i + 1}`, i => ({ say: SAY("Lôi Miêu", ["Nghiêng cánh trái!", "Lao xuống dưới khối đá!", "Vút lên, đáp xuống Cột Mốc Vọng Thiên!"][i]) })) },
      reward: { exp: 2520000, relic: 7 }, rewardText: "2.520.000 EXP, Linh Trùng Thi Hài (thay cho Yên Cương Hoàng Kim)" },
    { id: "MQ074", chapter: 4, title: "Bản Đồ Chiếu Lên Đêm Đen", place: "Đài tinh tú Aethelgard", giver: "La bàn cổ & khối lập phương",
      text: "Khối lập phương cộng hưởng với la bàn, chiếu bản đồ lên trời đêm.",
      task: { spots: [{ map: "sky-monolith", frac: [.5, .3], label: "Ghép la bàn và khối lập phương", say: SAY("La bàn cổ", "Một cột sáng chiếu lên trời, vẽ ra vành đai cơ khí quanh Mặt Trăng Đỏ.") }] },
      reward: { exp: 2800000, story: ["La Bàn Vũ Trụ"] }, rewardText: "2.800.000 EXP, La Bàn Vũ Trụ" },
    { id: "MQ075", chapter: 4, title: "Bí Mật Về Mẹ Của Kael", place: "Tàn tích đài tinh tú", giver: "Bản ghi âm của cha",
      text: "Nghe trọn bản ghi âm của cha về Thực Thể Thứ 0 và lý do ông tìm kiếm năng lượng.",
      task: { spots: [{ map: "sky-monolith", frac: [.25, .45], label: "Mở bản ghi âm của cha", say: SAY("Giọng cha Kael", "\"Kael, mẹ con không chết. Bà ấy bị Thực Thể Thứ 0 mang lên Mặt Trăng.\"", "\"Mọi năng lượng ta tìm kiếm… đều là để đưa bà ấy về.\"") }] },
      reward: { exp: 3100000, story: ["Bức Ảnh Mẹ Kael"] }, rewardText: "3.100.000 EXP, Bức Ảnh Mẹ Kael" },
    { id: "MQ076", chapter: 4, title: "Cột Mốc Vọng Thiên Kích Hoạt", place: "Đỉnh tháp Vọng Thiên", giver: "Cột năng lượng đỏ",
      text: "Khởi động bàn điều khiển Cột Mốc Vọng Thiên.",
      task: { spots: [{ map: "sky-monolith", frac: [.5, .15], label: "Khởi động bàn điều khiển", say: SAY("Cột Mốc Vọng Thiên", "Tia sáng đỏ bắn thẳng lên quỹ đạo Mặt Trăng.") }] },
      reward: { exp: 3450000, story: ["Chìa Khóa Vọng Thiên"] }, rewardText: "3.450.000 EXP, Chìa Khóa Vọng Thiên" },
    { id: "MQ077", chapter: 4, title: "Đại Hạm Đội Trồi Lên", place: "Bầu trời Aethelgard", giver: "Hạm đội Hư Không",
      text: "Tàu bay bọc thép trồi lên từ tầng mây. Bắn hạ 5 khinh khí cầu trinh sát quanh Cột Mốc Vọng Thiên.",
      task: { wild: { map: "sky-monolith", pet: "vqsv6-db-104", level: [68, 70], name: "Khinh khí cầu trinh sát", perWin: 1, count: 5 } },
      reward: { exp: 3850000, relic: 4 }, rewardText: "3.850.000 EXP, Viễn Cổ Long Cốt (thay cho Pháo Bắn Nhanh)" },
    { id: "MQ078", chapter: 4, title: "Tiếng Sáo Xé Tầng Mây", place: "Đài quan sát trời", giver: "Kael & Lyra",
      text: "Kael huýt sáo hiệu triệu đồng minh. Tập hợp đủ 6 sủng vật trong ba lô cho chiến dịch cuối.",
      task: { spots: [{ map: "sky-monolith", frac: [.75, .7], label: "Huýt sáo hiệu triệu", needTeam: 6, say: SAY("Tiếng sáo", "Tiếng sáo vang qua tầng mây. Sáu linh thú đáp lời, đứng quanh Kael.") }] },
      reward: { exp: 4300000, story: ["Tù Và Hiệu Triệu"] }, rewardText: "4.300.000 EXP, Tù Và Hiệu Triệu" },
    { id: "MQ079", chapter: 4, title: "Nụ Hôn Dưới Ánh Đỏ", place: "Mũi tàu chiến thần", giver: "Lyra",
      text: "Trước giờ xuất trận, Kael và Lyra trao trọn niềm tin.",
      npc: { id: "lyra5", name: "Lyra", map: "sky-monolith", frac: [.3, .8], offer: ["Dù trên kia là gì… tớ không sợ nữa.", "Vì cậu ở đây, Kael."] },
      task: { talkOnly: true }, reward: { exp: 4800000, story: ["Danh hiệu Tình Yêu Vượt Thiên Cương"] }, rewardText: "4.800.000 EXP, Danh hiệu Tình Yêu Vượt Thiên Cương" },
    { id: "MQ080", chapter: 4, title: "Mở Đường Lên Quỹ Đạo", place: "Bệ phóng cột mốc", giver: "Toàn quân liên minh",
      text: "Toàn quân theo cột sáng tiến vào không gian. Phá 3 khẩu pháo phòng không trên Quỹ đạo theo thứ tự.",
      task: { ordered: true, spots: RANGE3("orbit", [[.2, .5], [.5, .3], [.8, .5]], "Phá pháo phòng không", () => ({ battle: B("vqsv6-db-85", 70, "Pháo phòng không Hư Không", { hpScale: 2, noFlee: true }) })) },
      reward: { exp: 5500000, relic: 2 }, rewardText: "5.500.000 EXP, Quy Xác Toái Phiến (thay cho Bộ Giáp Phi Hành)" },
  );
  NPC_ART.lyra5 = "herbalist";
  // ---- Chương 5 · Mặt Trăng (MQ081–MQ096). The table marks this chapter as expanded content to be reviewed (the mother
  // being alive, the end of Thực Thể Thứ 0); it is played as written so the whole route can be tried. ----
  PART2.push(
    { id: "MQ081", chapter: 5, title: "Xé Rách Tầng Điện Ly", place: "Cột Mốc Vọng Thiên", giver: "Tầng thượng khí quyển",
      text: "Lôi Miêu tung cánh sấm sét đưa Kael và Lyra thoát ly trọng lực. Giữ thanh Lôi Đình qua 3 tầng khí quyển.",
      task: { ordered: true, spots: RANGE3("sky-monolith", [[.5, .85], [.5, .55], [.5, .25]], i => `Giữ thanh Lôi Đình (tầng ${i + 1})`, i => ({ say: SAY("Lôi Miêu", ["Sấm sét bao quanh đôi cánh!", "Gió trời rít lên, trọng lực níu lại!", "Thoát ly! Bầu trời đen thẫm đầy sao."][i]) })) },
      reward: { exp: 6200000, relic: 7 }, rewardText: "6.200.000 EXP, Linh Trùng Thi Hài (thay cho Lông Vũ Thần Tốc)" },
    { id: "MQ082", chapter: 5, title: "Vành Đai Rác Cơ Khí", place: "Quỹ đạo tầm thấp", giver: "Trận địa mìn vũ trụ",
      text: "Mìn cơ khí trôi khắp Quỹ đạo. Phá hủy 8 quả mìn từ tính hư không (mỗi trận một cụm 2 quả).",
      task: { wild: { map: "orbit", pet: "vqsv6-db-11", level: [69, 70], name: "Cụm Mìn từ tính", perWin: 2, count: 8 } },
      reward: { exp: 7000000, stones: { 14: 1 } }, rewardText: "7.000.000 EXP, Thiên Địa Thần Thạch (thay cho Kim Loại Vũ Trụ)" },
    { id: "MQ083", chapter: 5, title: "Tập Kích Tuần Dương Hạm", place: "Bên ngoài soái hạm", giver: "Hạm đội bảo hộ",
      text: "Đột nhập soái hạm Hư Không: đánh lui tàu hộ tống rồi đổ bộ lên boong chỉ huy.",
      task: { ordered: true, spots: [{ map: "orbit", frac: [.65, .3], label: "Đánh lui tàu hộ tống", battle: B("vqsv6-db-106", 70, "Tàu hộ tống Hư Không", { hpScale: 2 }) },
        { map: "orbit", frac: [.75, .6], label: "Đổ bộ lên boong", say: SAY("Lyra", "Bám chắc vào, Kael! Lên boong rồi!") }] },
      reward: { exp: 7900000, relic: 2 }, rewardText: "7.900.000 EXP, Quy Xác Toái Phiến (thay cho Khiên Lực Từ Trường)" },
    { id: "MQ084", chapter: 5, title: "Chiến Đấu Không Trọng Lực", place: "Boong tàu chiến hạm", giver: "Vệ binh trọng lực",
      text: "Không trọng lực trên boong tàu. Hạ 10 Robot Kháng Trọng Lực (mỗi trận một tốp 2).",
      task: { wild: { map: "orbit", pet: "vqsv6-db-84", level: [69, 70], name: "Tốp Robot Kháng Trọng Lực", perWin: 2, count: 10 } },
      reward: { exp: 8900000, items: { 7: 5 } }, rewardText: "8.900.000 EXP, 5 Tụ Năng Thạch (thay cho Giày Nam Châm)" },
    { id: "MQ085", chapter: 5, title: "Vô Hiệu Hóa Động Cơ Hư Vô", place: "Phòng động cơ tàu mẹ", giver: "Lõi nhiệt hạch tàu",
      text: "Lyra dùng nước biển đông kết động cơ warp. Sau đó kích nổ buồng đốt chính.",
      task: { ordered: true, spots: [{ map: "orbit", frac: [.4, .7], label: "Giúp Lyra đông kết động cơ", say: SAY("Lyra", "Nước biển kết băng quanh động cơ warp. Nó khựng lại rồi!") },
        { map: "orbit", frac: [.55, .8], label: "Kích nổ buồng đốt", battle: B("vqsv6-db-15", 70, "Lõi nhiệt hạch", { hpScale: 3, noFlee: true }) }] },
      reward: { exp: 10000000, story: ["Lõi Động Cơ Tinh Vân"] }, rewardText: "10.000.000 EXP, Lõi Động Cơ Tinh Vân" },
    { id: "MQ086", chapter: 5, title: "Chạm Trán Tổng Chỉ Huy Hư Không", place: "Đài chỉ huy soái hạm", giver: "Đại Tướng Cơ Giới",
      text: "Đánh bại Đại Tướng Cơ Giới trước khi soái hạm rơi.",
      task: { spots: [{ map: "orbit", frac: [.85, .45], label: "Đối đầu Đại Tướng Cơ Giới", battle: B("vqsv6-db-57", 70, "Đại Tướng Cơ Giới", { hpScale: 5, noFlee: true }) }] },
      reward: { exp: 11200000, relic: 0 }, rewardText: "11.200.000 EXP, Mạn Đà La Thạch (thay cho Kiếm Quang Học Bậc S)" },
    { id: "MQ087", chapter: 5, title: "Con Tàu Bốc Cháy", place: "Khoang thoát hiểm", giver: "Lôi Miêu",
      text: "Tàu mẹ nổ tung. Lôi Miêu chở Kael và Lyra né 3 đợt mảnh vỡ theo thứ tự, phóng về Mặt Trăng.",
      task: { ordered: true, spots: RANGE3("orbit", [[.3, .35], [.15, .6], [.1, .85]], i => `Né mảnh vỡ ${i + 1}`, i => ({ say: SAY("Lôi Miêu", ["Lửa ở khắp nơi!", "Xoay người tránh khối thép!", "Mặt Trăng Đỏ ngay trước mặt!"][i]) })) },
      reward: { exp: 12500000, relic: 10 }, rewardText: "12.500.000 EXP, Cảm Lãm Chi Diệp (thay cho Nhẫn Tinh Vân)" },
    { id: "MQ088", chapter: 5, title: "Bề Mặt Mặt Trăng Đỏ", place: "Bình nguyên bụi máu", giver: "Bề mặt Mặt Trăng",
      text: "Đặt chân lên cát đỏ, cảm nhận nhịp đập của Thực Thể Thứ 0. Tìm cửa hầm dẫn vào lõi.",
      task: { spots: [{ map: "red-moon", frac: [.5, .85], label: "Tìm cửa hầm", say: SAY("Mặt Trăng Đỏ", "Dưới lớp bụi đỏ là một nắp hầm kim loại, đập theo một nhịp đều như tim.") }] },
      reward: { exp: 14000000, items: { 11: 3 } }, rewardText: "14.000.000 EXP, 3 Sinh mệnh thạch (thay cho Bình Dưỡng Khí Vũ Trụ)" },
    { id: "MQ089", chapter: 5, title: "Dấu Vết Của Người Mẹ", place: "Trạm nghiên cứu số 1", giver: "Hình chiếu của mẹ",
      text: "Tìm phòng ngủ đông của mẹ Kael và mở khóa bằng la bàn gia tộc.",
      task: { spots: [{ map: "red-moon", frac: [.3, .65], label: "Mở buồng ngủ đông bằng la bàn", say: SAY("Hình chiếu của mẹ", "Một hình chiếu mờ hiện ra: \"Kael… con đã lớn thế này rồi.\"", "Kim la bàn khớp vào ổ khóa. Buồng ngủ đông mở hé.") }] },
      reward: { exp: 15800000, story: ["Vòng Tay Người Mẹ"] }, rewardText: "15.800.000 EXP, Vòng Tay Người Mẹ" },
    { id: "MQ090", chapter: 5, title: "Sự Thật Về Giấc Ngủ Đông", place: "Hệ thống duy trì sự sống", giver: "Máy chủ cổ đại",
      text: "Mẹ Kael tự nguyện ngủ đông để duy trì phong ấn. Bảo vệ hệ thống duy trì sự sống: cầm cự 6 lượt.",
      task: { spots: [{ map: "red-moon", frac: [.7, .6], label: "Bảo vệ hệ thống sự sống", battle: B("vqsv6-db-93", 70, "Kẻ phá hoại hệ thống", { hpScale: 4, survive: 6, noFlee: true }) }] },
      reward: { exp: 17800000, story: ["Tinh Thể Sự Sống"] }, rewardText: "17.800.000 EXP, Tinh Thể Sự Sống" },
    { id: "MQ091", chapter: 5, title: "Đánh Thức Vành Đai Cơ Khí", place: "Vành đai cơ khí", giver: "Bộ xương cơ khí khổng lồ",
      text: "Hệ thống phòng thủ của Thực Thể Thứ 0 thức giấc. Phá 4 tháp phát tia plasma.",
      task: { spots: RANGE3("machine-ring", [[.25, .2], [.75, .35], [.25, .65], [.75, .8]], "Phá tháp plasma", () => ({ battle: B("vqsv6-db-80", 70, "Tháp plasma quỹ đạo", { hpScale: 2 }) })) },
      reward: { exp: 20000000, relic: 2 }, rewardText: "20.000.000 EXP, Quy Xác Toái Phiến (thay cho Giáp Trụ Mặt Trăng)" },
    { id: "MQ092", chapter: 5, title: "Nhịp Đập Của Trái Tim Đen", place: "Đại sảnh lõi Mặt Trăng", giver: "Lõi Hư Không đỏ",
      text: "Phôi thai hư không bò khắp Mặt Trăng Đỏ. Dọn sạch 9 con (mỗi trận một ổ 3 con).",
      task: { wild: { map: "red-moon", pet: "vqsv6-db-92", level: [69, 70], name: "Ổ Phôi thai hư không", perWin: 3, count: 9 } },
      reward: { exp: 22500000, story: ["Thạch Hư Không"] }, rewardText: "22.500.000 EXP, Thạch Hư Không" },
    { id: "MQ093", chapter: 5, title: "Nguyện Ước Chung Đôi", place: "Trước kén thần", giver: "Lyra",
      text: "Kael và Lyra hợp sức mọi thánh vật, kích hoạt Kết Giới Uyên Ương.",
      npc: { id: "lyra6", name: "Lyra", map: "red-moon", frac: [.5, .45], offer: ["Đưa tay đây, Kael. Thương Hải Chi Lệ, la bàn, khối lập phương… tất cả.", "Cùng nhau, chúng ta là một kết giới."] },
      task: { talkOnly: true }, reward: { exp: 25500000, story: ["Kỹ Năng Đôi Tối Thượng"] }, rewardText: "25.500.000 EXP, Kỹ Năng Đôi Tối Thượng (chiêu Uyên Ương Lôi Hải cho Lôi Miêu)" },
    { id: "MQ094", chapter: 5, title: "Cắt Đứt Ống Dẫn Sinh Mệnh", place: "Lõi kén Thực Thể Thứ 0", giver: "Hệ thống dẫn máu máy",
      text: "Cắt đứt 6 ống dẫn năng lượng titan đang truyền tà khí vào kén.",
      task: { spots: RANGE3("red-moon", [[.2, .2], [.5, .15], [.8, .2], [.2, .35], [.5, .3], [.8, .35]], "Cắt ống dẫn titan", () => ({ say: SAY("Ống dẫn titan", "Lôi Miêu cắn đứt ống dẫn. Dòng tà khí phụt ra rồi tắt dần.") })) },
      reward: { exp: 29000000, stones: { 12: 3 } }, rewardText: "29.000.000 EXP, 3 Tinh Nguyên Thạch (thay cho Thần Dược Hồi Sinh)" },
    { id: "MQ095", chapter: 5, title: "Mắt Thần Hé Mở: Trận Chiến Mở Đầu", place: "Lõi kén thức giác", giver: "Thực Thể Thứ 0 (phân thân)",
      text: "Giao chiến với phân thân thức tỉnh đầu tiên của Thực Thể Thứ 0.",
      task: { spots: [{ map: "red-moon", frac: [.5, .1], label: "Đối đầu Thực Thể Thứ 0", battle: B("vqsv6-db-97", 70, "Thực Thể Thứ 0 (phân thân)", { hpScale: 6, noFlee: true }) }] },
      reward: { exp: 33000000, relic: 4 }, rewardText: "33.000.000 EXP, Viễn Cổ Long Cốt (thay cho Vũ Khí Thần Thoại)" },
    { id: "MQ096", chapter: 5, title: "Khúc Khải Hoàn Bất Tận", place: "Bệ phóng niềm tin", giver: "Kael, Lyra và gia đình",
      text: "Tái lập phong ấn, đưa mẹ trở về nhân gian và mở ra kỷ nguyên mới.",
      task: { spots: [{ map: "red-moon", frac: [.5, .7], label: "Tái lập phong ấn", say: SAY("Mẹ Kael", "Phong ấn khép lại. Mẹ Kael mở mắt, nắm lấy tay con trai.", "\"Về nhà thôi, Kael. Cả nhà mình.\"") }] },
      reward: { exp: 50000000, story: ["Danh hiệu Chúa Tể VQSV"] }, rewardText: "50.000.000 EXP, Danh hiệu Chúa Tể VQSV" },
  );
  NPC_ART.lyra6 = "herbalist";
  const byIdP2 = new Map(PART2.map(def => [def.id, def]));

  // ---- Balance (TRIAL). The table's levels run 1–100 and its EXP grows ~1.15× per quest, so Lôi Miêu reached the
  // level cap (70) at MQ049. Quest foes now follow the level of the map they stand on (its wild pool, built along the
  // route by tools/atlas_wild.py), bosses 2 above; each quest's EXP covers about half of the curve (expToReach) from
  // its map's level to the next quest's, so wild fights still matter and the lead keeps growing to the last quest. ----
  const mapRange = id => assets.maps?.[id]?.customWildAreas?.[0]?.level_range || null;
  const isBoss = b => !!b.noFlee || (b.hpScale || 1) >= 3;
  function foeLevel(map, raw, boss) { const r = mapRange(map); return Math.min(LEVEL_CAP, r ? r[1] + (boss ? 2 : 0) : raw); }
  const levelExp = level => Math.max(0, expToReach(level));
  const curveExp = level => { let sum = 0; for (let l = 2; l <= level; l++) sum += levelExp(l); return sum; };
  const roundExp = n => { if (n < 100) return Math.round(n); const k = 10 ** (Math.floor(Math.log10(n)) - 1); return Math.round(n / k) * k; };
  const homeMap = def => def.npc?.map || def.start?.map || def.task.spots?.[0]?.map || def.task.wild?.map;
  let expPlan = null;
  function planExp() {
    if (expPlan || !assets.maps) return expPlan;
    const lv = []; let top = 0;
    for (const def of PART2) { const r = mapRange(homeMap(def)); if (r) top = Math.max(top, r[0]); lv.push(top || null); }
    expPlan = new Map(PART2.map((def, i) => {
      if (!lv[i]) return [def.id, def.reward.exp];
      const next = i + 1 < PART2.length ? lv[i + 1] : LEVEL_CAP;
      return [def.id, roundExp(Math.max(.35 * levelExp(lv[i] + 1), .5 * (curveExp(next) - curveExp(lv[i]))))];
    }));
    return expPlan;
  }
  const expOf = quest => planExp()?.get(quest.id) ?? quest.reward?.exp ?? 0;
  const formatExp = n => n.toLocaleString("vi-VN");
  const rewardTextOf = quest => byIdP2.has(quest.id) && planExp() ? quest.rewardText.replace(/^[\d.]+ EXP/, `${formatExp(expOf(quest))} EXP`) : quest.rewardText;
  const p2 = () => byIdP2.get(current()?.id) || null;
  // Where a spot or NPC stands: next to the exit toward `near` (two cells inward) or at a fraction of the map.
  const placeCache = new Map();
  function place(def, key) {
    if (placeCache.has(key)) return placeCache.get(key);
    const map = assets.maps?.[def.map];
    if (!map) return null;
    let x = map.width * (def.frac?.[0] ?? .5), y = map.height * (def.frac?.[1] ?? .5);
    const link = def.near && !def.frac && (map.links || []).find(l => l.target === def.near);
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
    if (s.needTeam && buildTeam().length < s.needTeam) { statusEl.textContent = `Cần ít nhất ${s.needTeam} sủng vật trong ba lô (đang có ${buildTeam().length}).`; return; }
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
      state.battle = newBattle({ petId: b.pet, area: def.id, level: foeLevel(s.map, b.level, false), quality: 3, questName: b.name });
      state.battle.quest = { id: def.id, spot: s.i, capture: true };
      state.battle.message = `${b.name} xuất hiện. Đánh nhẹ tay rồi dùng cầu bắt để thu phục nó!`;
      state.mode = "battle"; window.VQSVSound?.play("encounter"); updateGuidance();
      return;
    }
    const enemy = questEnemy(b.pet, foeLevel(s.map, b.level, isBoss(b)), b.name, { hpScale: b.hpScale || 1 });
    questBattle(def.id, enemy, b.survive ? (b.mercy ? `${b.name} lồng lộn! Kiên nhẫn trấn an nó ${b.survive} lượt, đừng hạ gục nó.` : `${b.name} xuất hiện! Cầm cự ${b.survive} lượt.`) : `${b.name} xuất hiện!`,
      { spot: s.i, ...(b.survive ? { surviveTurns: b.survive } : {}), ...(b.mercy ? { mercy: true } : {}), ...(b.noFlee ? { noFlee: true } : {}), ...(b.ritual ? { ritual: true } : {}) });
    if (b.grantSkill) { grantBattleSkill(b.grantSkill); state.battle.message += ` Lôi Miêu có ${b.grantSkill.name} cho trận này!`; }
  }
  function grantBattleSkill(skill) {
    const lead = state.battle.team.find(pet => pet.kind === "lead");
    if (lead) { const move = { ...skill }; lead.skills = [move, ...(lead.skills || [])]; lead.skill = move; updateGuidance(); }
  }
  function p2BattleEnd(info, enemyDown) {
    const def = byIdP2.get(info.id);
    const raw = def?.task.spots?.[info.spot], s = raw && { ...raw, i: info.spot };
    if (!def || !s) return false;
    if (info.mercy && enemyDown) { statusEl.textContent = `${s.battle.name} gục ngã… Phải trấn an chứ không hạ gục. Thử lại.`; return true; }
    if (info.capture) { statusEl.textContent = `${s.battle.name} ngất đi. Thử lại và dùng cầu bắt khi nó còn máu.`; return true; }
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
    if (def.id === "MQ042") { state.mode = "ending"; endingCard = "chapter2"; }
    if (def.id === "MQ060") { state.mode = "ending"; endingCard = "chapter3"; }
    if (def.id === "MQ080") { state.mode = "ending"; endingCard = "chapter4"; }
    if (def.id === "MQ096") { state.mode = "ending"; endingCard = "finale"; }
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
    if (def.task.talkOnly) { finishP2(def); return true; }
    if (!q.started) { q.started = true; save(); statusEl.textContent = `Nhiệm vụ chính · ${def.id} ${def.title}`; return true; }
    const need = def.task.wild?.count || def.task.spots?.length || 0;
    if (def.task.report && q.progress >= need) finishP2(def);
    return true;
  }
  function p2ForceWild(mapId) {
    const def = p2(), w = def?.task.wild;
    if (!w || !q.started || mapId !== w.map || q.progress >= w.count || Math.random() >= .7) return null;
    const [lo, hi] = mapRange(w.map) || w.level;
    return { petId: w.pet, area: def.id, level: Math.min(LEVEL_CAP, lo + Math.floor(Math.random() * (hi - lo + 1))), quality: 2, questName: w.name };
  }
  function p2WildWon(enemy) {
    const def = p2(), w = def?.task.wild;
    if (!w || !q.started || enemy.petId !== w.pet || q.progress >= w.count) return;
    q.progress = Math.min(w.count, q.progress + (w.perWin || 1)); save();
    statusEl.textContent += ` · ${w.name.replace(/^(Nhóm|Tốp) /, "")} ${q.progress}/${w.count}`;
    if (q.progress >= w.count && !def.task.report) finishP2(def);
  }
  function p2Objective(def) {
    if (!q.started) return def.npc ? `${def.id} · Gặp ${def.npc.name} ở ${assets.maps?.[def.npc.map]?.title || def.place}` : def.start ? `${def.id} · Đến ${def.place} tìm ${def.giver}` : `${def.id} · ${def.text}`;
    if (def.task.wild) return q.progress >= def.task.wild.count && def.npc ? `${def.id} · Về báo ${def.npc.name}` : `${def.id} · Hạ ${def.task.wild.name.replace(/^(Nhóm|Tốp) /, "")} trên ${assets.maps?.[def.task.wild.map]?.title} (${q.progress}/${def.task.wild.count})`;
    if (def.task.report && q.progress >= (def.task.spots?.length || 0)) return `${def.id} · Về báo ${def.npc.name}`;
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
  // Cells Kael can walk to from the map's exits (flood fill), so no spot ends up on an island or behind a wall.
  const reachCache = new Map();
  function reachable(map) {
    if (reachCache.has(map.id)) return reachCache.get(map.id);
    const open = (x, y) => x >= 0 && y >= 0 && x < map.width && y < map.height && !withMap(map, () => blocked(x, y));
    const seen = new Set(), queue = [];
    for (const link of map.links || []) for (const [dx, dy] of [[0, 0], [1, 0], [-1, 0], [0, 1], [0, -1]]) if (open(link.x + dx, link.y + dy)) queue.push([link.x + dx, link.y + dy]);
    if (map.entities?.player) queue.push([map.entities.player.x, map.entities.player.y]);
    while (queue.length) {
      const [x, y] = queue.pop(), key = x + "," + y;
      if (seen.has(key) || !open(x, y)) continue;
      seen.add(key);
      queue.push([x + 1, y], [x - 1, y], [x, y + 1], [x, y - 1]);
    }
    reachCache.set(map.id, seen);
    return seen;
  }
  function nearFreeCore(map, x, y, taken) {
    const reach = reachable(map);
    // a spot must be reachable and leave a reachable neighbour cell to stand on
    const usable = (cx, cy) => reach.has(cx + "," + cy) && [[1, 0], [-1, 0], [0, 1], [0, -1]].some(([dx, dy]) => reach.has((cx + dx) + "," + (cy + dy)) && !taken.some(t => t.x === cx + dx && t.y === cy + dy));
    const seen = new Set(), queue = [[Math.round(x), Math.round(y)]];
    while (queue.length) {
      const [cx, cy] = queue.shift(), key = cx + "," + cy;
      if (seen.has(key) || cx < 0 || cy < 0 || cx >= map.width || cy >= map.height) continue;
      seen.add(key);
      if (free(map, cx, cy) && (!reach.size || usable(cx, cy)) && !taken.some(t => t.x === cx && t.y === cy)) return { x: cx, y: cy };
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
    statusEl.textContent = `Nhiệm vụ · ${current()?.title || ""}`;
    updateGuidance();
  }
  function questEnemy(petId, level, name, { hpScale = 1, image } = {}) {
    level = Math.min(level, LEVEL_CAP);
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
    grantReward({ ...(quest.reward || {}), exp: expOf(quest) });
    if (quest.reward?.story) q.items.push(...quest.reward.story);
    q.done.push(quest.id); q.index++; q.progress = 0; q.marks = [];
    // Steps that start by themselves; MQ003 and MQ004 start when Kael talks to their giver.
    const next = current(), nextP2 = next && byIdP2.get(next.id);
    q.started = ["MQ002", "MQ005", "MQ006", "MQ007", "MQ008", "MQ009"].includes(next?.id) || !!(nextP2 && !nextP2.npc && !nextP2.start);
    save(); updateWallet();
    statusEl.textContent = `Hoàn thành ${quest.id} · ${quest.title} · ${rewardTextOf(quest)}`;
    window.VQSVSound?.play("levelUp");
    if (text && state.battle) state.battle.message = text;
  }
  // EXP, kim tiền, bag items, relics and stones (story items are kept by the caller).
  function grantReward(r) {
    if (r.exp) grantLeadExp(r.exp);
    if (r.gold) addGold(r.gold);
    for (const [row, n] of Object.entries(r.items || {})) bag[row] = Math.min(STACK_MAX, (bag[row] || 0) + n);
    if (r.items) saveBag();
    if (r.relic != null) { stones[r.relic] = (stones[r.relic] || 0) + 1; saveStones(); updateStones(); }
    for (const [row, n] of Object.entries(r.stones || {})) { stones[row] = (stones[row] || 0) + n; saveStones(); updateStones(); }
    updateWallet();
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
    if (!quest) return "Đã hoàn thành toàn bộ 96 nhiệm vụ chính. Chế độ tự do: đi khắp các vùng, bắt và nuôi pet.";
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
    if (h3) h3.textContent = "Nhiệm vụ chính";
    if (intro) intro.textContent = "MQ001–MQ096 theo bảng nhiệm vụ (bản thử). Nhiệm vụ kế tiếp mở khi xong nhiệm vụ trước.";
    QUESTS.forEach((quest, i) => {
      if (i === 0 || (quest.chapter || 1) !== (QUESTS[i - 1].chapter || 1)) {
        const head = document.createElement("li"); head.className = "pending";
        head.textContent = ["", "— Chương 1 · Ryan —", "— Chương 2 · Hải Vực và Atlantis —", "— Chương 3 · Aethelgard và Boran —", "— Chương 4 · Kính Giới và hành trình lên trời —", "— Chương 5 · Mặt Trăng —"][quest.chapter || 1];
        list.append(head);
      }
      const li = document.createElement("li"), done = q.done.includes(quest.id), now = i === q.index;
      li.className = done ? "done" : now ? "current" : "pending";
      li.textContent = done ? `✓ ${quest.id} · ${quest.title}` : now ? `▶ ${quest.id} · ${quest.title} — ${q.intro ? quest.text : "Nói chuyện với Trưởng làng Elden."} (Thưởng: ${rewardTextOf(quest)})` : `○ ${quest.id} · ${quest.title} · ${quest.place}`;
      list.append(li);
      if (now) setTimeout(() => li.scrollIntoView?.({ block: "center" }), 0);
    });
    if (q.items.length) { const li = document.createElement("li"); li.className = "done"; li.textContent = `Vật phẩm cốt truyện: ${[...new Set(q.items)].join(", ")}`; list.append(li); }
  }
  function wildCaught(enemy) {
    const def = p2(), i = def?.task.spots?.findIndex(x => x.battle?.capture) ?? -1;
    if (i >= 0 && q.started && enemy.petId === def.task.spots[i].battle.pet) markSpot(def, { ...def.task.spots[i], i });
  }
  // The scene shown in "ending" mode: the compass awakening after MQ008, the end of the chapter after MQ020.
  function endingScene() {
    if (endingCard === "finale") return { title: "ĐẠI KẾT CỤC", heading: "KỶ NGUYÊN MỚI", lines: ["Phong ấn đã được tái lập. Mẹ Kael trở về nhân gian.", "Kael, Lyra, Lôi Miêu và cả gia đình cùng về nhà."], hint: "Nhấn E hoặc Tiếp tục để chơi tự do." };
    if (endingCard === "chapter4") return { title: "CHƯƠNG 4 · HOÀN THÀNH", heading: "LÊN QUỸ ĐẠO", lines: ["Kính Giới đã vỡ, Seraphina tan biến.", "Theo cột sáng Vọng Thiên, cả đoàn bay về phía Mặt Trăng Đỏ."], hint: "Nhấn E hoặc Tiếp tục sang Chương 5." };
    if (endingCard === "chapter3") return { title: "CHƯƠNG 3 · HOÀN THÀNH", heading: "CÁNH CỬA KÍNH GIỚI", lines: ["Boran đã gục ngã, Cổ Thú Nham Thạch được siêu thoát.", "Sau cánh cửa gương, Seraphina đang chờ."], hint: "Nhấn E hoặc Tiếp tục sang Chương 4." };
    if (endingCard === "chapter2") return { title: "CHƯƠNG 2 · HOÀN THÀNH", heading: "THƯƠNG HẢI CỰ KÌNH TÁI SINH", lines: ["Cha Kael đã trở về. Atlantis gửi lời cảm ơn.", "Phía chân trời, Lục Địa Bay Aethelgard lơ lửng chờ đợi."], hint: "Nhấn E hoặc Tiếp tục sang Chương 3." };
    if (endingCard !== "chapter1") return null;
    return { title: "CHƯƠNG 1 · HOÀN THÀNH", heading: "ANH HÙNG RYAN", lines: ["Cổ Long Hắc Ám tan biến trong Quang Minh Trận.", "Dưới chân tháp, Kael tìm thấy bức thư của cha."], hint: "Nhấn E hoặc Tiếp tục sang Chương 2." };
  }
  function continueFromEnding() {
    if (state.mode !== "ending") return false;
    endingCard = null;
    state.mode = "explore"; state.battle = newBattle(); updateGuidance(); return true;
  }

  window.Quests = {
    list: QUESTS, state: () => q, expOf, rewardTextOf, current, interact, talked, npcs, objective, actionLabel, draw, renderJournal, forceWild, wildWon,
    battleWon, battleLost, continueFromEnding, wildCaught, endingScene,
    // MQ031 wakes Lôi Miêu's thunder wings: a permanent extra move (TRIAL power and uses)
    bonusSkills(key) {
      if (key !== "loi-mieu") return [];
      const out = [];
      if (q.items.includes("Lôi Dực Khiêu Vũ")) out.push({ name: "Lôi Dực Khiêu Vũ", power: 260, uses: 12, max: 12, description: "Đôi cánh sấm sét kim sắc: nhảy múa giữa lôi đình." });
      // MQ093: the couple's ultimate move (TRIAL power and uses)
      if (q.items.includes("Kỹ Năng Đôi Tối Thượng")) out.push({ name: "Uyên Ương Lôi Hải", power: 340, uses: 8, max: 8, description: "Sấm của Lôi Miêu và biển của Lyra hòa làm một." });
      return out;
    }, night, storyFightOpen: () => false, spots,
    // shared pieces for side_quests.js
    kit: { busy: () => placing > 0, foeLevel, isBoss, mapRange, levelExp, roundExp, formatExp, place, say, questEnemy, questBattle, grantReward, onMap, close, NPC_ART, done: id => q.done.includes(id) },
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
        wild: def.task.wild ? { ...def.task.wild } : null,
        spots: p2Spots(def).map(x => ({ map: x.map, x: x.at.x, y: x.at.y, waves: x.waves || 0, capture: !!x.battle?.capture, needElement: x.needElement || null })) };
    },
  };
})();
