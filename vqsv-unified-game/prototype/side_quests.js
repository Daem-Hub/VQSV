// Nhiệm vụ phụ SQ001–SQ047 (DANH_SACH_NHIEM_VU.md, tuyến A–E). Runs beside the main quests in quests.js:
// several side quests can be open at once, each with its own progress. Each one opens when a main quest is done
// (`after`, chosen from the table's suggested level, TRIAL), is taken from its giver NPC (or starts by itself at an
// object), and uses the same kinds of steps as the main quests: spots to visit (talk, fight, catch, survive),
// wild hunts, or a talk. Rewards the game has no item for are replaced by the nearest one (rewardText says which).
(() => {
  const Q = window.Quests;
  if (!Q?.kit) return;
  const K = Q.kit;
  const KEY = "vqsv-unified-side-quests-v1";
  const LINES = { A: "Tuyến A · Kael và Lyra", B: "Tuyến B · Pet và tiến hóa", C: "Tuyến C · Giải mã và khám phá", D: "Tuyến D · Truy quét và giải cứu", E: "Tuyến E · Huấn luyện sư" };
  const B = (pet, level, name, more = {}) => ({ pet, level, name, ...more });
  const SAY = (who, ...lines) => [who, lines];
  const AT = (map, fracs, label, extra = () => ({})) => fracs.map((frac, i) => ({ map, frac, label: typeof label === "function" ? label(i) : label, ...extra(i) }));
  const NPC = (id, name, map, frac, art, offer, wait, done) => ({ id: "sq-" + id, name, map, frac, art, offer, wait: wait || ["Ta vẫn đang chờ."], done: done || ["Cảm ơn cháu, Kael."] });

  const SIDE = [
    // ---- Tuyến A · Kael và Lyra ----
    { id: "SQ001", line: "A", after: "MQ026", title: "Vỏ Sò Ánh Trăng Tặng Nàng", place: "Vương Quốc Atlantis",
      npc: NPC("lyra-a1", "Lyra", "atlantis", [.3, .7], "herbalist", ["Kael, dưới rạn san hô có những vỏ sò phát sáng như trăng…", "(Kael thầm định nhặt 3 chiếc làm dây chuyền cho Lyra.)"], ["Anh đang tìm gì dưới rạn san hô thế?"], ["Dây chuyền vỏ sò… cho em sao? Cảm ơn anh, Kael!"]),
      task: { report: true, spots: AT("atlantis", [[.15, .25], [.85, .3], [.75, .85]], i => `Nhặt Vỏ Sò Ánh Trăng ${i + 1}`, () => ({ say: SAY("Rạn san hô", "Một vỏ sò ánh trăng lấp lánh trong cát.") })) },
      reward: { aff: 500, story: ["Dây chuyền Vỏ Sò"] }, rewardText: "+500 Hảo cảm, Dây chuyền Vỏ Sò (biểu cảm Hẹn Hò chưa có)" },
    { id: "SQ002", line: "A", after: "MQ029", title: "Món Ăn Quê Hương Của Kael", place: "Làng biên giới sương mù",
      npc: NPC("lyra-a2", "Lyra", "slice01-border-village", [.6, .55], "herbalist", ["Món quê anh là gì vậy, Kael?", "Em muốn nếm thử Bánh Ngô Nướng! Anh tìm giúp nguyên liệu nhé."], ["Ngô ở Đồng Cỏ Lộng Gió, đúng không anh?"], ["Thơm quá! Bánh ngô quê anh ngon thật."]),
      task: { report: true, spots: AT("ryan-meadow", [[.25, .3], [.7, .45], [.4, .8]], i => ["Hái bắp ngô", "Lấy mật ong rừng", "Hái lá thơm"][i], i => ({ say: SAY("Đồng Cỏ Lộng Gió", ["Bắp ngô vàng óng.", "Một tổ mật ong rừng.", "Một nắm lá thơm."][i]) })) },
      reward: { aff: 600, items: { 4: 5 } }, rewardText: "+600 Hảo cảm, 5 Bánh Sandwich (thay cho Công thức Bánh Ngô Hồi Máu)" },
    { id: "SQ003", line: "A", after: "MQ032", title: "Bí Ẩn Tóc Bạc Của Bán Nhân Ngư", place: "Hang Động San Hô Lân Quang",
      npc: NPC("priestess", "Nữ Tế Tư Atlantis", "atlantis", [.7, .5], "elder", ["Tóc của Lyra cần hoa Băng Thủy Nguyệt để chải dưới trăng rằm.", "Hoa mọc trong Hang Động San Hô Lân Quang. Hãy hái 5 bông."], ["Hoa Băng Thủy Nguyệt tỏa ánh sáng xanh nhạt."], ["Đủ 5 bông. Đêm nay trăng rằm, Lyra sẽ rất đẹp."]),
      task: { report: true, spots: AT("coral-grotto", [[.2, .2], [.8, .2], [.5, .5], [.2, .8], [.8, .8]], "Hái Băng Thủy Nguyệt", () => ({ say: SAY("Hang San Hô", "Một bông Băng Thủy Nguyệt lạnh buốt trong tay.") })) },
      reward: { aff: 300, story: ["Trang phục Dạ Hội Biển Sâu"] }, rewardText: "Trang phục Dạ Hội Biển Sâu cho Lyra (vật phẩm cốt truyện), +300 Hảo cảm" },
    { id: "SQ004", line: "A", after: "MQ047", title: "Khiêu Vũ Giữa Tầng Mây", place: "Đài Quan Sát Lục Bảo",
      npc: NPC("lyra-a4", "Lyra", "jade-observatory", [.5, .55], "herbalist", ["Gió ở đây hay quá. Anh dạy em điệu nhảy của tộc gió nhé?"], ["Bước tiếp theo là gì hả anh?"], ["Em nhảy được rồi! Lôi Miêu cũng nhún theo kìa."]),
      task: { ordered: true, report: true, spots: AT("jade-observatory", [[.4, .45], [.6, .45], [.6, .65], [.4, .65]], i => `Bước nhảy ${i + 1}/4`, i => ({ say: SAY("Nhịp gió", ["Bước trái, xoay nửa vòng.", "Bước phải, nắm tay.", "Lùi lại, cúi chào.", "Xoay tròn giữa gió!"][i]) })) },
      reward: { aff: 800, story: ["Nhịp Bước Uyên Ương"] }, rewardText: "Chiêu Nhịp Bước Uyên Ương cho Lôi Miêu, +800 Hảo cảm" },
    { id: "SQ005", line: "A", after: "MQ049", title: "Trái Tim Pha Lê Ấm Áp", place: "Miệng Núi Lửa Hắc Diệu",
      npc: NPC("lyra-a5", "Lyra", "aethelgard-gate", [.5, .6], "herbalist", ["Gió trên cao lạnh quá, tay em tê cóng rồi…", "Nghe nói Tinh Thể Nhiệt Băng ở Miệng Núi Lửa Hắc Diệu giữ ấm rất lâu."], ["Anh cẩn thận dung nham nhé."], ["Ấm quá… Cảm ơn anh."]),
      task: { report: true, spots: AT("obsidian-crater", [[.25, .3], [.75, .4], [.5, .75]], "Khai thác Tinh Thể Nhiệt Băng", () => ({ say: SAY("Miệng núi lửa", "Tinh thể vừa nóng vừa mát, tỏa hơi ấm dịu.") })) },
      reward: { aff: 600, items: { 8: 3 } }, rewardText: "+600 Hảo cảm, 3 Khôi phục tề (thay cho Găng Tay Sưởi Ấm Lyra)" },
    { id: "SQ006", line: "A", after: "MQ056", title: "Nguyện Ước Dưới Mưa Sao Băng", place: "Đường lên Tầng Mây Khởi Nguyên",
      npc: NPC("lyra-a6", "Lyra", "cloud-stair", [.5, .7], "herbalist", ["Đêm nay có mưa sao băng trăm năm mới có một lần!", "Mình ngồi ngắm cùng nhau nhé. Anh chụp lại giúp em."], ["Kia kìa, một ngôi sao nữa!"], ["Em đã ước rồi. Không nói cho anh biết đâu."]),
      task: { report: true, spots: AT("cloud-stair", [[.3, .3], [.7, .35], [.5, .15]], i => `Chụp sao rơi ${i + 1}`, () => ({ say: SAY("Bầu trời", "Một vệt sao băng sáng rực. Tách!") })) },
      reward: { aff: 700, story: ["Danh hiệu Lời Thề Dưới Sao Băng"] }, rewardText: "Danh hiệu Lời Thề Dưới Sao Băng, +700 Hảo cảm" },
    { id: "SQ007", line: "A", after: "MQ065", title: "Bài Hát Ru Chưa Đặt Tên", place: "Đỉnh Aethelgard",
      npc: NPC("lyra-a7", "Lyra", "aethelgard-summit", [.3, .75], "herbalist", ["Mẹ em từng hát một bài ru… em chỉ nhớ từng đoạn.", "Anh giúp em ghép lại ba đoạn nhé."], ["Đoạn tiếp theo là…"], ["Bài hát hoàn chỉnh rồi. Em sẽ đặt tên là \"Biển Khơi\"."]),
      task: { ordered: true, report: true, spots: AT("aethelgard-summit", [[.25, .4], [.5, .3], [.75, .4]], i => `Ghép đoạn hát ${i + 1}/3`, i => ({ say: SAY("Lyra hát", ["\"Ngủ đi con, sóng vỗ về…\"", "\"Trăng soi đáy biển, cá về ngủ say…\"", "\"Mai con lớn, ra khơi xa…\""][i]) })) },
      reward: { aff: 800, story: ["Bản Hát Ru Biển Khơi"] }, rewardText: "Bản Hát Ru Biển Khơi, +800 Hảo cảm" },
    { id: "SQ008", line: "A", after: "MQ068", title: "Cùng Nhau Chăm Sóc Sủng Vật", place: "Rừng Bậc Thang Ngọc Lục",
      npc: NPC("farmer", "Chủ Trang Trại", "jade-terraces", [.5, .5], "villager", ["Đàn sủng vật non cần người tắm rửa và cho ăn.", "Hai đứa giúp ta ba việc nhé."], ["Còn việc chưa xong đấy."], ["Đàn nhỏ quấn quýt hai đứa rồi kìa."]),
      task: { report: true, spots: AT("jade-terraces", [[.4, .4], [.6, .45], [.5, .62]], i => ["Tắm cho sủng vật non", "Cho đàn ăn", "Chải lông"][i], i => ({ say: SAY("Trang trại", ["Lyra té nước, Lôi Miêu hắt xì.", "Đàn nhỏ ăn ngon lành.", "Lông mượt óng ánh."][i]) })) },
      reward: { aff: 500, stones: { 13: 2 } }, rewardText: "+500 Hảo cảm, 2 Thiên Giới Tinh Thạch (thay cho +20% tốc độ tiến hóa)" },
    { id: "SQ009", line: "A", after: "MQ071", title: "Chiếc Nhẫn San Hô Lôi Đình", place: "Phế Tích Thành Đồng",
      npc: NPC("hepha", "Thợ Rèn Hepha", "copper-ruins", [.5, .5], "villager", ["Ta rèn được cặp nhẫn từ Bạc Sấm Sét và San Hô Đỏ.", "Mang cho ta 2 Thỏi Lôi Bạc ở Hẻm Sông Đồng Đỏ và 1 San Hô Đỏ ở Đầm Phá San Hô Hồng."], ["Thiếu nguyên liệu thì ta chịu."], ["Cặp nhẫn đẹp nhất đời ta rèn."]),
      task: { report: true, spots: [...AT("copper-gorge", [[.3, .4], [.7, .6]], "Lấy Thỏi Lôi Bạc", () => ({ battle: B("vqsv6-db-83", 70, "Chuột Lôi giữ bạc", { hpScale: 1.5 }) })),
        { map: "coral-lagoons", frac: [.5, .5], label: "Lấy San Hô Đỏ", say: SAY("Đầm phá", "Một nhánh san hô đỏ thắm.") }] },
      reward: { aff: 1000, story: ["Cặp Nhẫn Định Tình"] }, rewardText: "Cặp Nhẫn Định Tình (vật phẩm cốt truyện; +15% sát thương đôi chưa làm), +1000 Hảo cảm" },
    { id: "SQ010", line: "A", after: "MQ082", title: "Hẹn Ước Sau Ngày Chiến Thắng", place: "Quỹ đạo",
      npc: NPC("lyra-a10", "Lyra", "orbit", [.2, .5], "herbalist", ["Khi mọi chuyện kết thúc… mình xây một ngôi nhà bên bờ biển nhé?", "Có vườn cho Lôi Miêu chạy, có cửa sổ nhìn ra sóng."]),
      task: { talkOnly: true }, reward: { aff: 1000, story: ["Danh hiệu Bạn Đời Tri Kỷ"] }, rewardText: "Danh hiệu Bạn Đời Tri Kỷ, +1000 Hảo cảm" },

    // ---- Tuyến B · Pet và tiến hóa ----
    { id: "SQ011", line: "B", after: "MQ011", title: "Tiếng Sấm Trong Rừng Phong Lôi", place: "Đồng Bằng Sấm Sét",
      npc: NPC("old-hunter", "Thợ Săn Già", "ryan-phong-loi-forest", [.7, .3], "scout", ["Có con Lôi Điêu đột biến lông tích điện ở Đồng Bằng Sấm Sét.", "Bắt hoặc hạ nó giúp ta."], ["Nó lượn trên đồng bằng."], ["Lông vũ tích điện cao cấp! Đá này cho cháu."]),
      task: { report: true, spots: [{ map: "ryan-stormplain", frac: [.6, .5], label: "Săn Lôi Điêu Đột Biến", battle: B("vqsv6-db-105", 16, "Lôi Điêu Đột Biến", { hpScale: 1.5 }) }] },
      reward: { stones: { 12: 1 }, exp: 3000 }, rewardText: "3.000 EXP, Tinh Nguyên Thạch (thay cho Đá Tiến Hóa Hệ Lôi Cấp 1)" },
    { id: "SQ012", line: "B", after: "MQ015", title: "Hạt Giống Cổ Thụ Ngàn Năm", place: "Rừng Rậm Phong Lôi",
      npc: NPC("forest-soul", "Linh Hồn Rừng", "ryan-phong-loi-forest", [.3, .7], "elder", ["Dưới rễ cổ thụ có một sủng vật hóa đá.", "Hãy tưới 3 bình nước từ Suối Sấm."], ["Rễ cây vẫn còn khô."], ["Nó tỉnh rồi! Hãy chăm sóc nó."]),
      task: { ordered: true, report: true, spots: AT("ryan-forest-stream", [[.3, .3], [.6, .5], [.4, .75]], i => `Múc Nước Suối Thần ${i + 1}/3`, () => ({ say: SAY("Suối Sấm", "Nước suối lấp lánh ánh điện.") })) },
      reward: { pet: ["vqsv6-db-33", 20, 3] }, rewardText: "Sủng vật Khốc Tị Mộc Linh (thay cho Trứng Mộc Tinh Thú)" },
    { id: "SQ013", line: "B", after: "MQ022", title: "Băng Cốt Long Say Ngủ", place: "Băng Nguyên Vĩnh Cửu",
      npc: NPC("ice-archeo", "Nhà Khảo Cổ Băng", "ice-pass", [.5, .5], "villager", ["Bọn Người tuyết gặm mất xương rồng băng cổ đại!", "Lấy lại 4 Khúc Băng Cốt trên Băng Nguyên Vĩnh Cửu giúp ta."], ["Còn thiếu xương đấy."], ["Đủ 4 khúc! Rồng con này sẽ theo cháu."]),
      task: { report: true, wild: { map: "ice-plain", pet: "vqsv6-db-59", level: [26, 28], name: "Người tuyết gặm xương", perWin: 1, count: 4 } },
      reward: { pet: ["vqsv5-db-37", 26, 3] }, rewardText: "Sủng vật Tiểu Bạch Long (thay cho mở nhánh tiến hóa Rồng Băng)" },
    { id: "SQ014", line: "B", after: "MQ029", title: "Ngọn Lửa Không Bao Giờ Tắt", place: "Hỏa Sơn Bất Diệt",
      npc: NPC("fire-soul", "Linh Hồn Lửa Cổ", "eternal-volcano", [.3, .3], "elder", ["Hỏa Kỳ Lân canh giữ ngọn đuốc nguyên thủy.", "Thắng nó, ngọn lửa là của ngươi."]),
      task: { spots: [{ map: "eternal-volcano", frac: [.6, .6], label: "Thách đấu Hỏa Kỳ Lân", battle: B("vqsv6-db-23", 36, "Hỏa Kỳ Lân", { hpScale: 2, noFlee: true }) }] },
      reward: { stones: { 14: 1 }, exp: 20000 }, rewardText: "20.000 EXP, Thiên Địa Thần Thạch (thay cho Tinh Hoa Hỏa Diễm Bất Diệt)" },
    { id: "SQ015", line: "B", after: "MQ036", title: "Cá Voi Lân Quang Biển Sâu", place: "Vực Thẳm Tận Cùng",
      npc: NPC("deep-guard", "Hộ Vệ Biển Sâu", "ocean-abyss", [.2, .5], "scout", ["Máy hút của Vane làm bị thương đàn cá voi con.", "Chữa giúp 3 chú cá voi con."], ["Chúng vẫn còn đau."], ["Cá voi mẹ muốn theo cháu để trả ơn."]),
      task: { report: true, spots: AT("ocean-abyss", [[.3, .2], [.7, .3], [.5, .85]], i => `Chữa cá voi con ${i + 1}`, () => ({ say: SAY("Cá voi con", "Lôi Miêu liếm vết thương, ánh lân quang sáng lại.") })) },
      reward: { pet: ["vqsv5-db-69", 40, 3] }, rewardText: "Sủng vật Cá Kình khổng lồ (thay cho Thú Cưỡi Bạch Kình)" },
    { id: "SQ016", line: "B", after: "MQ040", title: "Linh Hồn Sa Ngư Tái Sinh", place: "Nghĩa Địa Chiến Thuyền",
      npc: NPC("ghost-shark", "U Linh Sa Ngư", "wreck-graveyard", [.5, .3], "scout", ["Ta cần 10 Tinh Phách U Uẩn để thức tỉnh thân xác hoàng kim.", "Túi quỷ trong nghĩa địa giữ chúng."], ["Ta cảm thấy… gần rồi."], ["Thân xác hoàng kim! Ta sẽ chiến đấu cho ngươi."]),
      task: { report: true, wild: { map: "wreck-graveyard", pet: "vqsv6-db-92", level: [43, 45], name: "Túi quỷ giữ Tinh Phách", perWin: 2, count: 10 } },
      reward: { pet: ["vqsv5-db-100", 45, 4] }, rewardText: "Sủng vật Sa Vương Cuồng Bạo hạng cao (thay cho U Linh Sa Ngư Hoàng Kim)" },
    { id: "SQ017", line: "B", after: "MQ049", title: "Vuốt Sấm Của Hoàng Hổ", place: "Đồng Bằng Sấm Sét",
      npc: NPC("sage", "Sư Tôn Ẩn Dật", "ryan-stormplain", [.2, .5], "elder", ["Lôi Miêu muốn mạnh hơn thì phải hứng sét không gián đoạn.", "Trụ vững trong lôi đài 8 lượt."]),
      task: { spots: [{ map: "ryan-stormplain", frac: [.75, .5], label: "Vào lôi đài", battle: B("vqsv6-db-85", 55, "Lôi đài sấm sét", { hpScale: 3, survive: 8, noFlee: true }) }] },
      reward: { story: ["Lôi Bạo Cuồng Nộ"] }, rewardText: "Chiêu Lôi Bạo Cuồng Nộ cho Lôi Miêu" },
    { id: "SQ018", line: "B", after: "MQ053", title: "Phượng Hoàng Lửa Tái Sinh", place: "Miệng Núi Lửa Hắc Diệu",
      npc: NPC("fire-temple", "Đền Lửa Aethelgard", "aethelgard-gate", [.75, .3], "elder", ["Quả trứng Phượng Hoàng Tro cần 5 Ngọn Lửa Tinh Khiết.", "Lửa cháy ở Miệng Núi Lửa Hắc Diệu."], ["Trứng vẫn lạnh."], ["Trứng nở rồi! Phượng Hoàng chọn ngươi."]),
      task: { report: true, spots: AT("obsidian-crater", [[.2, .2], [.8, .2], [.5, .45], [.2, .8], [.8, .8]], "Thu Ngọn Lửa Tinh Khiết", () => ({ say: SAY("Ngọn lửa", "Ngọn lửa trắng tinh nhảy vào lồng đèn.") })) },
      reward: { pet: ["vqsv6-db-25", 60, 4] }, rewardText: "Sủng vật Hỏa Phượng Hoàng" },
    { id: "SQ019", line: "B", after: "MQ062", title: "Cánh Bướm Pha Lê Ảo Mộng", place: "Cung điện pha lê",
      npc: NPC("crystal-fairy", "Tiên Hoa Pha Lê", "crystal-palace", [.5, .7], "herbalist", ["Bướm phát sáng trong cung điện mang phấn hoa đột phá.", "Bắt giúp ta 5 Điệp Tinh Thể."], ["Bướm bay khắp cung điện."], ["Phấn hoa quý đây, cho sủng vật của ngươi."]),
      task: { report: true, wild: { map: "crystal-palace", pet: "vqsv6-db-37", level: [64, 66], name: "Điệp Tinh Thể", perWin: 1, count: 5 } },
      reward: { items: { 9: 3 } }, rewardText: "3 Siêu năng tề (thay cho Thuốc Đột Phá Tiềm Năng)" },
    { id: "SQ020", line: "B", after: "MQ066", title: "Thần Thú Cơ Khí Thuần Hóa", place: "Phế Tích Thành Đồng",
      npc: NPC("k9", "Robot Bị Hỏng K-9", "copper-ruins", [.3, .3], "scout", ["Gâu… bíp… Hệ điều hành… lỗi…"], ["Bíp… pin yếu…"], ["Gâu gâu! Khởi động lại thành công!"]),
      task: { ordered: true, report: true, spots: AT("copper-ruins", [[.6, .3], [.7, .7]], i => ["Sửa bo mạch", "Lắp pin năng lượng"][i], i => ({ say: SAY("K-9", ["Bo mạch kêu tách một tiếng, đèn xanh bật sáng.", "Pin năng lượng vào khớp. Đuôi máy vẫy lia lịa."][i]) })) },
      reward: { pet: ["vqsv6-db-22", 66, 3] }, rewardText: "Sủng vật Tuyệt Đối Linh Độ Khuyển (thay cho Khuyển Cơ Giới K-9)" },
    { id: "SQ021", line: "B", after: "MQ070", title: "Lôi Long Giáng Thế", place: "Đỉnh Tháp Thiên Không",
      start: { map: "tower-crown", frac: [.5, .3], label: "Đọc Bia Ký Thần Thú", lines: SAY("Bia Ký Thần Thú", "Kẻ nào hợp nhất được Long Hồn Thượng Cổ sẽ có hình thái mới.") },
      task: { spots: [{ map: "tower-crown", frac: [.5, .55], label: "Thử Thách Long Hồn", battle: B("vqsv6-db-80", 70, "Long Hồn Thượng Cổ", { hpScale: 3, noFlee: true }) }] },
      reward: { stones: { 15: 1, 14: 1 } }, rewardText: "Hồn Tinh Thạch và Thiên Địa Thần Thạch (thay cho Hình Thái Hoàng Kim Lôi Long Thú)" },
    { id: "SQ022", line: "B", after: "MQ088", title: "Thần Thú Tinh Vân Quỹ Đạo", place: "Vành đai cơ khí",
      npc: NPC("star-spirit", "Tinh Linh Tinh Tú", "machine-ring", [.5, .5], "herbalist", ["7 Mảnh Bụi Sao Đỏ trôi khắp Vành đai cơ khí.", "Ghép đủ, một sủng vật vũ trụ sẽ ra đời."], ["Bụi sao còn trôi đâu đó."], ["Tinh Vân Hạc nhỏ đã nở!"]),
      task: { report: true, spots: AT("machine-ring", [[.2, .1], [.8, .15], [.2, .35], [.8, .45], [.3, .6], [.8, .75], [.4, .9]], i => `Mảnh Bụi Sao ${i + 1}/7`, () => ({ say: SAY("Bụi sao", "Một mảnh bụi sao đỏ ấm trong lòng bàn tay.") })) },
      reward: { pet: ["vqsv6-db-109", 70, 4] }, rewardText: "Sủng vật Tinh Vân Hạc (thay cho Tinh Vân Ấu Thú)" },

    // ---- Tuyến C · Giải mã và khám phá ----
    { id: "SQ023", line: "C", after: "MQ013", title: "Bí Ẩn Chữ Viết Cổ Ryan", place: "Làng biên giới sương mù",
      npc: NPC("noel", "Học Giả Noel", "slice01-border-village", [.4, .3], "elder", ["Ba tấm bia đá cổ quanh vùng Ryan chưa ai dịch được.", "Cháu dập bản khắc ở Cao Nguyên Đá, Hẻm Núi Đá và Hồ Sương Mù giúp ta."], ["Còn bia nào chưa dập không?"], ["Đủ cả ba! Cổ tự Ryan… nói về bốn thánh vật."]),
      task: { report: true, spots: [{ map: "ryan-highland", frac: [.5, .4], label: "Dập bia đá", say: SAY("Bia đá", "Ký tự cổ hình tia chớp.") },
        { map: "ryan-canyon", frac: [.5, .5], label: "Dập bia đá", say: SAY("Bia đá", "Ký tự cổ hình giọt nước.") },
        { map: "ryan-lakeside", frac: [.5, .4], label: "Dập bia đá", say: SAY("Bia đá", "Ký tự cổ hình ngọn lửa.") }] },
      reward: { exp: 4000, gold: 800, story: ["Sách Hiểu Biết Cổ Tự"] }, rewardText: "4.000 EXP, 800 kim tiền, Sách Hiểu Biết Cổ Tự" },
    { id: "SQ024", line: "C", after: "MQ017", title: "Mảnh Vỡ La Bàn Đầu Tiên", place: "Hẻm Núi Đá",
      start: { map: "ryan-canyon", frac: [.3, .7], label: "Xem Rương Cổ Bị Khóa", lines: SAY("Rương cổ", "Ba trục bánh răng khóa chặt nắp rương. Phải xoay đúng thứ tự.") },
      task: { ordered: true, spots: AT("ryan-canyon", [[.2, .3], [.5, .2], [.8, .3]], i => `Xoay trục tầng ${i + 1}`, () => ({ say: SAY("Bánh răng", "Cạch. Trục khớp vào rãnh.") })) },
      reward: { gold: 1500, story: ["Mảnh Ghép La Bàn Số 1"] }, rewardText: "1.500 kim tiền, Mảnh Ghép La Bàn Số 1" },
    { id: "SQ025", line: "C", after: "MQ027", title: "Hải Đồ Thất Lạc Của Hải Tộc", place: "Vương Quốc Chìm Atlantis",
      npc: NPC("librarian", "Thủ Thư Biển Sâu", "atlantis", [.5, .25], "elder", ["Tấm hải đồ dòng hải lưu ngầm bị xé thành 5 mảnh.", "Hai mảnh ở Hải Vực Vô Tận, một ở Vòng xoáy lôi đình, hai trong Atlantis."], ["Thiếu mảnh thì hải đồ vô dụng."], ["Hải đồ hoàn chỉnh! Giữ lấy nó."]),
      task: { report: true, spots: [...AT("endless-sea", [[.3, .3], [.7, .7]], "Nhặt mảnh giấy da", () => ({ say: SAY("Mảnh hải đồ", "Mảnh giấy da cuộn trong vỏ ốc.") })),
        { map: "thunder-whirlpool", frac: [.5, .5], label: "Nhặt mảnh giấy da", say: SAY("Mảnh hải đồ", "Mảnh giấy da mắc vào mỏm đá giữa xoáy nước.") },
        ...AT("atlantis", [[.2, .5], [.8, .6]], "Nhặt mảnh giấy da", () => ({ say: SAY("Mảnh hải đồ", "Mảnh giấy da kẹt sau cột đá.") }))] },
      reward: { exp: 12000, story: ["Bản Đồ Bí Mật Hải Vực"] }, rewardText: "12.000 EXP, Bản Đồ Bí Mật Hải Vực" },
    { id: "SQ026", line: "C", after: "MQ033", title: "Chìa Khóa Nước Vĩnh Cửu", place: "Hang Động San Hô Lân Quang",
      start: { map: "atlantis", frac: [.5, .85], label: "Xem Trụ Cột Nguyên Thủy", lines: SAY("Trụ Cột Nguyên Thủy", "Bệ thờ có ba hốc trống. Ba viên ngọc nước nằm trong mê cung san hô.") },
      task: { ordered: true, spots: [...AT("coral-grotto", [[.15, .5], [.5, .15], [.85, .5]], i => `Lấy ngọc nước ${i + 1}/3`, () => ({ say: SAY("Mê cung san hô", "Viên ngọc nước mát lạnh, xanh thẳm.") })),
        { map: "atlantis", frac: [.5, .8], label: "Đặt ngọc vào bệ thờ", say: SAY("Bệ thờ", "Ba viên ngọc sáng lên. Một giọt lệ biển rơi xuống tay Kael.") }] },
      reward: { relic: 1, story: ["Giọt Lệ Biển Ngàn Năm"] }, rewardText: "Giọt Lệ Biển Ngàn Năm, Hồng Sắc Hải Loa" },
    { id: "SQ027", line: "C", after: "MQ044", title: "Nhật Ký Của Kẻ Phản Bội", place: "Vực Thẳm Tận Cùng",
      start: { map: "ocean-abyss", frac: [.8, .8], label: "Lục Tủ Hồ Sơ Bị Cháy", lines: SAY("Tủ hồ sơ", "Nhật ký của một đồng đội cũ của cha Kael, bị xé rời thành 6 trang.") },
      task: { spots: AT("ocean-abyss", [[.15, .15], [.5, .1], [.85, .15], [.15, .55], [.85, .55], [.5, .95]], i => `Trang nhật ký ${i + 1}/6`, i => ({ say: SAY("Trang nhật ký", ["\"Vane hứa cho tôi sức mạnh…\"", "\"Tôi đã đưa bản thiết kế cho hắn.\"", "\"Anh ấy tin tôi. Tôi đã phản bội anh ấy.\"", "\"Lõi Mặt Trăng… họ muốn đánh thức nó.\"", "\"Tôi sẽ chuộc lỗi, nếu còn kịp.\"", "\"Gửi con trai của anh ấy: xin lỗi.\""][i]) })) },
      reward: { relic: 2, exp: 40000 }, rewardText: "40.000 EXP, Quy Xác Toái Phiến (thay cho Áo Choàng Học Giả Cổ)" },
    { id: "SQ028", line: "C", after: "MQ050", title: "Thiên Cung Đồ Aethelgard", place: "Đài Quan Sát Lục Bảo",
      start: { map: "jade-observatory", frac: [.5, .25], label: "Xem Kính Viễn Vọng Cổ", lines: SAY("Kính viễn vọng", "Bốn lăng kính cần hướng về bốn chòm sao hộ mệnh.") },
      task: { ordered: true, spots: AT("jade-observatory", [[.2, .3], [.8, .3], [.8, .8], [.2, .8]], i => `Chỉnh lăng kính: ${["Thanh Long", "Bạch Hổ", "Chu Tước", "Huyền Vũ"][i]}`, () => ({ say: SAY("Lăng kính", "Ánh sao rọi thẳng vào tâm lăng kính.") })) },
      reward: { relic: 0, exp: 60000 }, rewardText: "60.000 EXP, Mạn Đà La Thạch (thay cho Ngọc Tăng Tỉ Lệ Bạo Kích)" },
    { id: "SQ029", line: "C", after: "MQ060", title: "Bia Đá Tiên Tri Về Thực Thể 0", place: "Đường lên Tầng Mây Khởi Nguyên",
      start: { map: "cloud-stair", frac: [.2, .4], label: "Xem Bia Đá Nứt Nẻ", lines: SAY("Bia đá", "Rêu phủ kín mặt bia. Phải dùng bột dạ quang mới đọc được.") },
      task: { spots: [{ map: "cloud-stair", frac: [.25, .5], label: "Chép lại ký tự", say: SAY("Bia đá tiên tri", "\"Khi trăng nhuốm máu lần thứ ba, Thực Thể Thứ 0 sẽ mở mắt.\"", "\"Chỉ máu của người giữ la bàn mới đóng lại được phong ấn.\"") }] },
      reward: { stones: { 14: 1 }, story: ["Mảnh Chìa Khóa Tinh Thạch Số 3"] }, rewardText: "Thiên Địa Thần Thạch, Mảnh Chìa Khóa Tinh Thạch Số 3" },
    { id: "SQ030", line: "C", after: "MQ087", title: "Mật Mã Cửa Hầm Soái Hạm", place: "Quỹ đạo",
      start: { map: "orbit", frac: [.4, .3], label: "Xem Bảng Điều Khiển Mã Hóa", lines: SAY("Bảng điều khiển", "Xác soái hạm còn trôi. Cửa kho cần 4 ký tự nhị phân.") },
      task: { ordered: true, spots: AT("orbit", [[.45, .2], [.55, .2], [.45, .4], [.55, .4]], i => `Nhập ký tự ${i + 1}: ${["1", "0", "1", "1"][i]}`, () => ({ say: SAY("Bảng điều khiển", "Bíp. Ký tự hợp lệ.") })) },
      reward: { gold: 100000 }, rewardText: "100.000 kim tiền từ kho vũ khí tối mật" },
    { id: "SQ031", line: "C", after: "MQ089", title: "Bức Thư Tay Của Người Mẹ", place: "Mặt Trăng Đỏ",
      start: { map: "red-moon", frac: [.2, .5], label: "Mở Hộp Kỷ Vật", lines: SAY("Hộp kỷ vật", "Một lá thư trắng tinh. La bàn trong tay Kael ấm dần lên…") },
      task: { spots: [{ map: "red-moon", frac: [.2, .55], label: "Hơ thư bằng la bàn", say: SAY("Thư của mẹ", "\"Kael yêu dấu, nếu con đọc được thư này, con đã đi rất xa.\"", "\"Mẹ chưa bao giờ hối hận vì đã chọn ở lại đây.\"", "\"Hãy cười nhiều lên, con trai. Mẹ yêu con.\"") }] },
      reward: { story: ["Nụ Cười Của Mẹ"] }, rewardText: "Vật phẩm vĩnh cửu: Nụ Cười Của Mẹ" },

    // ---- Tuyến D · Truy quét và giải cứu ----
    { id: "SQ032", line: "D", after: "MQ010", title: "Tiêu Hủy Trinh Sát Cơ", place: "Đồng Cỏ Lộng Gió",
      npc: NPC("patrol", "Đội Trưởng Tuần Tra", "slice01-border-village", [.5, .2], "scout", ["Có drone trinh sát do thám làng từ Đồng Cỏ Lộng Gió.", "Hạ giúp ta 5 chiếc."], ["Vẫn còn drone bay quanh."], ["Bầu trời sạch rồi. Cầm lấy cầu này."]),
      task: { report: true, wild: { map: "ryan-meadow", pet: "vqsv6-db-98", level: [10, 12], name: "Drone trinh sát", perWin: 1, count: 5 } },
      reward: { items: { 1: 10 } }, rewardText: "10 Phong ấn cầu (thay cho 10 Quả Đạn Sấm Sét)" },
    { id: "SQ033", line: "D", after: "MQ014", title: "Phá Hủy Trạm Khai Thác Cát", place: "Hẻm núi cát đỏ",
      npc: NPC("merchant", "Thương Nhân Sa Mạc", "red-dunes", [.5, .3], "villager", ["Bọn tay sai đào cát trái phép ở Hẻm núi cát đỏ.", "Hạ tên gác rồi cài 3 gói thuốc nổ vào bánh xích máy xúc."], ["Máy xúc vẫn chạy ầm ầm."], ["Bùm! Sa mạc yên tĩnh lại rồi."]),
      task: { ordered: true, report: true, spots: [{ map: "sand-canyon", frac: [.3, .3], label: "Hạ tên gác máy xúc", battle: B("vqsv6-db-19", 20, "Gác máy xúc") },
        ...AT("sand-canyon", [[.6, .5], [.7, .6], [.6, .7]], i => `Cài thuốc nổ ${i + 1}/3`, () => ({ say: SAY("Máy xúc", "Gói thuốc nổ đã gắn chặt vào bánh xích.") }))] },
      reward: { items: { 7: 3 }, gold: 1200 }, rewardText: "3 Tụ Năng Thạch, 1.200 kim tiền (thay cho 500 Quặng Sắt Siêu Bền)" },
    { id: "SQ034", line: "D", after: "MQ022", title: "Giải Cứu Đàn Rùa Biển Bị Gắn Chip", place: "Bờ biển Ryan",
      npc: NPC("volunteer", "Tình Nguyện Viên Biển", "ryan-shore", [.5, .2], "villager", ["6 con rùa biển bị gắn chip kiểm soát.", "Gỡ nhẹ tay thôi, đừng làm chúng đau."], ["Còn rùa mang chip đấy."], ["Đàn rùa đã tự do. Mảnh mai này chúng để lại cho cháu."]),
      task: { report: true, spots: AT("ryan-shore", [[.2, .4], [.5, .45], [.8, .4], [.2, .75], [.5, .8], [.8, .75]], i => `Gỡ chip rùa ${i + 1}`, () => ({ say: SAY("Rùa biển", "Con chip rơi ra. Rùa bò chậm rãi về biển.") })) },
      reward: { relic: 2, exp: 8000 }, rewardText: "8.000 EXP, Quy Xác Toái Phiến (thay cho Khiên Mai Rùa Bọc Thép)" },
    { id: "SQ035", line: "D", after: "MQ034", title: "Tập Kích Kho Nhiên Liệu Ngầm", place: "Nghĩa Địa Chiến Thuyền",
      npc: NPC("resistance", "Biệt Đội Kháng Chiến", "wreck-graveyard", [.3, .7], "scout", ["Kho dầu của xưởng chế tạo quái thú nằm giữa nghĩa địa tàu.", "Phá 4 bồn chứa, nhưng coi chừng lính gác."], ["Còn bồn chưa nổ."], ["Kho dầu cháy rừng rực! Làm tốt lắm."]),
      task: { report: true, spots: AT("wreck-graveyard", [[.2, .2], [.8, .2], [.2, .5], [.8, .5]], i => `Phá bồn nhiên liệu ${i + 1}/4`, i => i === 0 ? { battle: B("vqsv6-db-87", 40, "Lính gác kho dầu", { hpScale: 1.5 }) } : { say: SAY("Bồn nhiên liệu", "Lôi Miêu phóng tia điện. Bồn dầu nổ tung.") }) },
      reward: { stones: { 13: 1 }, story: ["Lõi Nhiệt Hạch Thu Nhỏ"] }, rewardText: "Lõi Nhiệt Hạch Thu Nhỏ, Thiên Giới Tinh Thạch" },
    { id: "SQ036", line: "D", after: "MQ035", title: "Săn Lùng Sát Thủ Người Máy V-1", place: "Vương Quốc Chìm Atlantis",
      npc: NPC("sea-sheriff", "Cảnh Sát Trưởng Biển", "atlantis", [.85, .2], "scout", ["Kẻ Ám Sát V-1 để lại vệt dầu rỉ trong thành.", "Tiêu diệt nó trước khi có thêm nạn nhân."], ["Vệt dầu dẫn xuống khu ổ chuột."], ["V-1 bị hạ rồi. Atlantis nợ cháu."]),
      task: { report: true, spots: [{ map: "atlantis", frac: [.15, .9], label: "Đối đầu Kẻ Ám Sát V-1", battle: B("vqsv6-db-84", 42, "Kẻ Ám Sát V-1", { hpScale: 2.5, noFlee: true }) }] },
      reward: { items: { 9: 2 }, exp: 30000 }, rewardText: "30.000 EXP, 2 Siêu năng tề (thay cho Dao Găm Năng Lượng Laser)" },
    { id: "SQ037", line: "D", after: "MQ048", title: "Phá Lò Rèn Titan Của Boran", place: "Pháo đài và đấu trường Boran",
      npc: NPC("escapee", "Nô Lệ Đào Tẩu", "obsidian-crater", [.5, .9], "villager", ["Boran bắt chúng tôi đúc giáp rồng ngày đêm.", "Đập tan 3 khuôn thép titan trong pháo đài giúp tôi."], ["Lò rèn vẫn còn đỏ lửa."], ["Không còn khuôn nào! Cảm ơn ngài."]),
      task: { report: true, spots: AT("boran-arena", [[.15, .2], [.85, .2], [.5, .9]], i => `Đập khuôn titan ${i + 1}/3`, () => ({ say: SAY("Khuôn thép", "Rắc! Khuôn đúc nứt đôi.") })) },
      reward: { stones: { 14: 2 } }, rewardText: "2 Thiên Địa Thần Thạch (thay cho 2 Thỏi Titan Tinh Chế)" },
    { id: "SQ038", line: "D", after: "MQ051", title: "Chặn Chuyến Xe Tiếp Tế Năng Lượng", place: "Miệng Núi Lửa Hắc Diệu",
      npc: NPC("rebel", "Quân Khởi Nghĩa", "obsidian-crater", [.2, .3], "scout", ["Đoàn tàu bọc thép chở tinh thể dung nham sắp qua.", "Phục kích nó!"], ["Đoàn tàu sắp tới!"], ["Hàng về tay quân khởi nghĩa rồi!"]),
      task: { report: true, spots: [{ map: "obsidian-crater", frac: [.8, .6], label: "Phục kích đoàn tàu", battle: B("vqsv6-db-20", 58, "Đoàn tàu bọc thép", { hpScale: 3 }) }] },
      reward: { items: { 8: 5 } }, rewardText: "5 Khôi phục tề (thay cho 10 Bình Tinh Thể Nhiệt)" },
    { id: "SQ039", line: "D", after: "MQ061", title: "Giải Thoát Đàn Chim Máy Bị Giam Cầm", place: "Lối vào Aethelgard",
      npc: NPC("bird-keeper", "Người Nuôi Thú Trời", "aethelgard-gate", [.2, .2], "villager", ["Chim quý bị gắn cánh cơ học và nhốt trong 5 lồng điện.", "Bẻ khóa giúp ta."], ["Còn chim bị nhốt."], ["Đàn chim bay về trời rồi."]),
      task: { report: true, spots: AT("aethelgard-gate", [[.15, .5], [.35, .8], [.65, .8], [.85, .5], [.5, .4]], i => `Bẻ khóa lồng ${i + 1}/5`, () => ({ say: SAY("Lồng điện", "Khóa bung ra. Chú chim vút lên trời.") })) },
      reward: { items: { 10: 2 } }, rewardText: "2 Vạn năng dược (thay cho Lông Vũ Tốc Độ Tối Thượng)" },
    { id: "SQ040", line: "D", after: "MQ073", title: "Hacker Đột Nhập Mạng Lưới", place: "Cột Mốc Vọng Thiên",
      npc: NPC("rin", "Thiên Tài Lập Trình Rin", "sky-monolith", [.3, .8], "herbalist", ["Tôi sẽ cài virus vào máy chủ Hư Không qua cột mốc này.", "Giữ chân lũ bảo vệ 6 lượt cho tôi!"]),
      task: { spots: [{ map: "sky-monolith", frac: [.7, .75], label: "Bảo vệ Rin", battle: B("vqsv6-db-96", 70, "Bảo vệ mạng Hư Không", { hpScale: 3, survive: 6, noFlee: true }) }] },
      reward: { items: { 11: 2 } }, rewardText: "2 Sinh mệnh thạch (thay cho Phần Mềm Diệt Virus Hư Không)" },
    { id: "SQ041", line: "D", after: "MQ070", title: "Xóa Sổ Căn Cứ Chế Tạo Phân Thân", place: "Điện gương",
      npc: NPC("clone", "Bản Sao Phản Tỉnh", "mirror-hall", [.1, .5], "herbalist", ["Tôi là bản sao của Seraphina… nhưng tôi không muốn thêm ai như tôi.", "Hạ tên gác và kích hoạt ngòi nổ giữa xưởng."]),
      task: { ordered: true, spots: [{ map: "mirror-hall", frac: [.5, .3], label: "Hạ tên gác xưởng", battle: B("vqsv6-db-95", 70, "Gác xưởng phân thân", { hpScale: 2.5 }) },
        { map: "mirror-hall", frac: [.6, .6], label: "Kích hoạt ngòi nổ", say: SAY("Ngòi nổ", "Ba… hai… một… Xưởng phân thân nổ tung!") }] },
      reward: { stones: { 15: 1 }, items: { 3: 3 } }, rewardText: "Hồn Tinh Thạch, 3 Đại sư cầu (thay cho Rương Báu Vũ Khí Hạng Nặng)" },

    // ---- Tuyến E · Huấn luyện sư ----
    { id: "SQ042", line: "E", after: "MQ014", title: "Đấu Trường Tân Thủ Ryan", place: "Cao Nguyên Đá",
      npc: NPC("arena-ryan", "Chủ Đấu Trường Ryan", "ryan-highland", [.3, .3], "villager", ["Giải đấu nghiệp dư đây! Thắng 3 trận liên tiếp sẽ có cúp."], ["Vào sàn đi!"], ["Tân binh vô địch! Cúp này của cháu."]),
      task: { report: true, spots: [{ map: "ryan-highland", frac: [.6, .5], label: "Thi đấu", waves: 3, battle: B("vqsv6-db-13", 20, "Đấu sĩ nghiệp dư") }] },
      reward: { gold: 2000, story: ["Cúp Tân Thủ"] }, rewardText: "2.000 kim tiền, Cúp Tân Thủ (Áo Choàng Đấu Sĩ chưa có)" },
    { id: "SQ043", line: "E", after: "MQ024", title: "Bậc Thầy Bắt Thú Bằng Tay Không", place: "Đồng Cỏ Lộng Gió",
      npc: NPC("catch-master", "Bác Thợ Săn Lão Luyện", "ryan-meadow", [.6, .2], "scout", ["Bắt thú giỏi không cần mạnh tay.", "Thu phục 3 sủng vật bất kỳ cho ta xem."], ["Mới bắt được vài con thôi à?"], ["Bàn tay thép thật sự!"]),
      task: { report: true, catchAny: 3 },
      reward: { items: { 3: 3 }, story: ["Danh hiệu Bàn Tay Thép"] }, rewardText: "3 Đại sư cầu, Danh hiệu Bàn Tay Thép" },
    { id: "SQ044", line: "E", after: "MQ038", title: "Khiêu Chiến Đấu Trường Đại Dương", place: "Vương Quốc Chìm Atlantis",
      npc: NPC("sea-champ", "Quán Quân Tộc Biển", "atlantis", [.5, .55], "scout", ["Hạ 3 Huấn Luyện Sư Biển dưới đáy nước, ngươi sẽ có huy hiệu."], ["Còn đối thủ chờ ngươi."], ["Huy Hiệu Hải Vương thuộc về ngươi."]),
      task: { report: true, spots: [{ map: "atlantis", frac: [.5, .65], label: "Đấu với Huấn Luyện Sư Biển", waves: 3, battle: B("vqsv6-db-63", 44, "Huấn Luyện Sư Biển", { hpScale: 1.5 }) }] },
      reward: { relic: 1, items: { 3: 2 }, story: ["Huy Hiệu Hải Vương"] }, rewardText: "Huy Hiệu Hải Vương, Hồng Sắc Hải Loa, 2 Đại sư cầu (thay cho Khiên Thủy Tinh)" },
    { id: "SQ045", line: "E", after: "MQ058", title: "Giải Đấu Lôi Đài Không Trung", place: "Đường lên Tầng Mây Khởi Nguyên",
      npc: NPC("sky-referee", "Trọng Tài Bầu Trời", "cloud-stair", [.7, .7], "elder", ["Sàn đấu rung lắc giữa tầng mây. Đánh bại Quán Quân Bay nếu dám."]),
      task: { spots: [{ map: "cloud-stair", frac: [.7, .5], label: "Đấu Quán Quân Bay", battle: B("vqsv6-db-106", 65, "Quán Quân Bay", { hpScale: 3, noFlee: true }) }] },
      reward: { stones: { 13: 2 }, story: ["Cúp Thiên Không"] }, rewardText: "Cúp Thiên Không, 2 Thiên Giới Tinh Thạch (thay cho Đôi Cánh Danh Dự)" },
    { id: "SQ046", line: "E", after: "MQ069", title: "Rèn Luyện Ý Chí Kim Cương", place: "Pháo đài và đấu trường Boran",
      npc: NPC("force-master", "Đại Sư Lực Lượng", "boran-arena", [.5, .3], "elder", ["Phòng trọng lực gấp 10 lần. Trụ được 10 lượt mới gọi là ý chí kim cương."]),
      task: { spots: [{ map: "boran-arena", frac: [.5, .6], label: "Vào phòng trọng lực", battle: B("vqsv6-db-57", 70, "Trọng lực gấp 10", { hpScale: 4, survive: 10, noFlee: true }) }] },
      reward: { items: { 11: 3 }, stones: { 15: 1 } }, rewardText: "3 Sinh mệnh thạch, Hồn Tinh Thạch (thay cho +15% Máu & Thủ vĩnh viễn)" },
    { id: "SQ047", line: "E", after: "MQ096", title: "Đỉnh Cao Huấn Luyện Sư Vũ Trụ", place: "Mặt Trăng Đỏ",
      npc: NPC("first-trainer", "Linh Hồn Huấn Luyện Sư Đầu Tiên", "red-moon", [.7, .85], "elder", ["Ta là huấn luyện sư đầu tiên của vùng đất này.", "Hãy cho ta thấy ngươi đã đi xa đến đâu."]),
      task: { spots: [{ map: "red-moon", frac: [.5, .9], label: "Trận solo đỉnh cao", battle: B("vqsv6-db-85", 70, "Bóng ma Huấn Luyện Sư Huyền Thoại", { hpScale: 8, noFlee: true }) }] },
      reward: { story: ["Danh hiệu Đại Tông Sư VQSV Vĩnh Hằng"] }, rewardText: "Danh hiệu Đại Tông Sư VQSV Vĩnh Hằng" },
  ];
  const byId = new Map(SIDE.map(def => [def.id, def]));

  // ---- state: per-quest progress, done list, story items and Lyra's affection (Hảo cảm) ----
  const fresh = () => ({ q: {}, done: [], items: [], aff: 0, caught: 0 });
  let s = fresh();
  try { const raw = JSON.parse(localStorage.getItem(KEY) || "null"); if (raw?.q) s = { ...fresh(), ...raw }; } catch (_) {}
  const store = () => { try { localStorage.setItem(KEY, JSON.stringify(s)); } catch (_) {} };
  const st = id => s.q[id] ||= { started: false, progress: 0, marks: [] };
  const unlocked = def => !def.after || K.done(def.after);
  const isDone = def => s.done.includes(def.id);
  const active = def => unlocked(def) && !isDone(def) && !!s.q[def.id]?.started;
  const open = def => unlocked(def) && !isDone(def);
  const need = def => def.task.wild?.count || def.task.catchAny || def.task.spots?.[0]?.waves || def.task.spots?.length || 0;
  const ready = def => st(def.id).progress >= need(def);
  const mapTitle = id => assets.maps?.[id]?.title || id;
  const spotsOf = def => (def.task.spots || []).map((x, i) => ({ ...x, i, at: K.place(x, `${x.map}:${def.id}:${i}`) }));
  const spotDone = (def, x) => x.waves ? st(def.id).progress >= x.waves : st(def.id).marks.includes(x.i);
  const startAt = def => K.place(def.start, `${def.start.map}:${def.id}:start`);
  const npcAt = def => K.place(def.npc, `${def.npc.map}:${def.id}:npc`);

  function begin(def) { st(def.id).started = true; store(); statusEl.textContent = `Nhiệm vụ phụ · ${def.id} ${def.title}`; updateGuidance(); }
  // Balance (TRIAL): side EXP is about one level of the curve at the quest's map; reward pets join at that map's level.
  const homeMap = def => def.npc?.map || def.start?.map || def.task.spots?.[0]?.map || def.task.wild?.map;
  const homeLevel = def => K.mapRange(homeMap(def))?.[0] || null;
  const expOf = def => def.reward?.exp ? (homeLevel(def) ? K.roundExp(K.levelExp(homeLevel(def) + 1)) : def.reward.exp) : 0;
  const rewardTextOf = def => def.reward?.exp ? def.rewardText.replace(/^[\d.]+ EXP/, `${K.formatExp(expOf(def))} EXP`) : def.rewardText;
  function finish(def) {
    const r = { ...(def.reward || {}), exp: expOf(def) };
    K.grantReward(r);
    if (r.story) s.items.push(...r.story);
    if (r.aff) s.aff += r.aff;
    if (r.pet) window.VQSVPetTeam?.recordCatch(r.pet[0], Math.min(LEVEL_CAP, homeLevel(def) || r.pet[1]), r.pet[2]);
    s.done.push(def.id); delete s.q[def.id]; store();
    statusEl.textContent = `Hoàn thành nhiệm vụ phụ ${def.id} · ${def.title} · ${rewardTextOf(def)}`;
    window.VQSVSound?.play("levelUp");
    updateGuidance();
  }
  function advance(def, x) {
    const p = st(def.id);
    if (x.waves) p.progress++; else if (!p.marks.includes(x.i)) { p.marks.push(x.i); p.progress = p.marks.length; }
    store();
    if (ready(def) && !def.task.report) { finish(def); return; }
    statusEl.textContent = `${def.id} · ${Math.min(p.progress, need(def))}/${need(def)}${ready(def) && def.npc ? ` · về gặp ${def.npc.name}` : ""}`;
    updateGuidance();
  }
  function fight(def, x) {
    const b = x.battle, enemy = K.questEnemy(b.pet, K.foeLevel(x.map, b.level, K.isBoss(b)), b.name, { hpScale: b.hpScale || 1 });
    K.questBattle(def.id, enemy, b.survive ? `${b.name}! Cầm cự ${b.survive} lượt.` : `${b.name} xuất hiện!`,
      { side: true, spot: x.i, ...(b.survive ? { surviveTurns: b.survive } : {}), ...(b.noFlee ? { noFlee: true } : {}) });
    statusEl.textContent = `Nhiệm vụ phụ · ${def.title}`;
  }

  // ---- what Kael can do where he stands ----
  function here() {
    if (state.mode !== "explore") return null;
    for (const def of SIDE) {
      if (!open(def)) continue;
      const p = st(def.id);
      if (!p.started) {
        if (def.start && K.onMap(def.start.map) && K.close(startAt(def), true)) return { label: def.start.label, run: () => K.say(def.start.lines[0], def.start.lines[1], () => begin(def)) };
        continue;
      }
      for (const x of spotsOf(def)) {
        if (!K.onMap(x.map) || !x.at || !K.close(x.at, true) || spotDone(def, x)) continue;
        if (def.task.ordered && x.i !== p.marks.length) continue;
        const label = x.waves ? `${x.label} (${p.progress + 1}/${x.waves})` : x.label;
        return { label, run: () => x.battle ? fight(def, x) : x.say ? K.say(x.say[0], x.say[1], () => advance(def, x)) : advance(def, x) };
      }
    }
    return null;
  }
  function npcs(list) {
    if (K.busy()) return list;
    for (const def of SIDE) {
      if (!def.npc || !open(def) || !K.onMap(def.npc.map)) continue;
      const at = npcAt(def);
      if (!at) continue;
      if (assets.chars && !assets.chars[def.npc.id]) assets.chars[def.npc.id] = assets.chars[def.npc.art] || assets.chars.scout;
      const p = st(def.id), lines = !p.started ? def.npc.offer : ready(def) ? def.npc.done : def.npc.wait;
      list.push({ id: def.npc.id, name: def.npc.name, x: at.x, y: at.y, dialogue: lines, sideTalk: def.id });
    }
    return list;
  }
  function talked(npc) {
    const def = byId.get(npc.sideTalk);
    if (!def || !open(def)) return false;
    if (def.task.talkOnly) { finish(def); return true; }
    if (!st(def.id).started) { begin(def); return true; }
    if (def.task.report && ready(def)) finish(def);
    return true;
  }
  function forceWild(mapId) {
    for (const def of SIDE) {
      const w = def.task.wild;
      if (!w || !active(def) || mapId !== w.map || ready(def) || Math.random() >= .6) continue;
      const [lo, hi] = K.mapRange(w.map) || w.level;
      return { petId: w.pet, area: def.id, level: Math.min(LEVEL_CAP, lo + Math.floor(Math.random() * (hi - lo + 1))), quality: 2, questName: w.name };
    }
    return null;
  }
  function wildWon(enemy) {
    for (const def of SIDE) {
      const w = def.task.wild;
      if (!w || !active(def) || enemy.petId !== w.pet || ready(def)) continue;
      const p = st(def.id); p.progress = Math.min(w.count, p.progress + (w.perWin || 1)); store();
      statusEl.textContent += ` · ${w.name} ${p.progress}/${w.count}`;
      if (ready(def) && !def.task.report) finish(def);
    }
  }
  function caught() {
    for (const def of SIDE) {
      if (!def.task.catchAny || !active(def) || ready(def)) continue;
      const p = st(def.id); p.progress++; store();
      statusEl.textContent += ` · ${def.id} bắt thú ${p.progress}/${def.task.catchAny}`;
    }
  }
  function battleEnd(info, won) {
    const def = byId.get(info.id);
    state.battle = newBattle(); state.mode = "explore"; wildSteps = 0;
    if (def && won && active(def)) { const raw = def.task.spots?.[info.spot]; if (raw) advance(def, { ...raw, i: info.spot }); }
    else if (def && !won) statusEl.textContent = `Lôi Miêu kiệt sức · thử lại nhiệm vụ phụ ${def.title}`;
    updateGuidance();
  }

  // ---- screen: glows for spots and givers, the journal list ----
  function draw(ctx, S) {
    if (!assets.maps) return;
    const t = performance.now();
    const glow = (x, y, color, r = 6) => { ctx.save(); ctx.globalAlpha = .5 + Math.sin(t / 300) * .25; ctx.fillStyle = color; ctx.beginPath(); ctx.arc(x * S + 10, y * S + 10, r, 0, Math.PI * 2); ctx.fill(); ctx.restore(); };
    for (const def of SIDE) {
      if (!open(def)) continue;
      const p = st(def.id);
      if (!p.started) {
        if (def.start && K.onMap(def.start.map)) { const at = startAt(def); if (at) glow(at.x, at.y, "#8dffb0", 7); }
        if (def.npc && K.onMap(def.npc.map)) { const at = npcAt(def); if (at) { ctx.save(); ctx.font = "bold 16px system-ui"; ctx.lineWidth = 3; ctx.strokeStyle = "#12351f"; ctx.fillStyle = "#8dffb0"; const bob = Math.sin(t / 250) * 2; ctx.strokeText("!", at.x * S + 7, at.y * S - 30 + bob); ctx.fillText("!", at.x * S + 7, at.y * S - 30 + bob); ctx.restore(); } }
        continue;
      }
      for (const x of spotsOf(def)) {
        if (!K.onMap(x.map) || !x.at || spotDone(def, x) || (def.task.ordered && x.i !== p.marks.length)) continue;
        glow(x.at.x, x.at.y, x.battle ? "#ff8f6a" : "#8dffb0");
      }
    }
  }
  function objectiveOf(def) {
    const p = st(def.id);
    if (!p.started) return def.npc ? `Gặp ${def.npc.name} ở ${mapTitle(def.npc.map)}` : `${def.start.label} ở ${mapTitle(def.start.map)}`;
    if (ready(def)) return def.npc && def.task.report ? `Về gặp ${def.npc.name} ở ${mapTitle(def.npc.map)}` : "Sắp xong";
    if (def.task.wild) return `Hạ ${def.task.wild.name} ở ${mapTitle(def.task.wild.map)} (${p.progress}/${def.task.wild.count})`;
    if (def.task.catchAny) return `Bắt sủng vật bất kỳ (${p.progress}/${def.task.catchAny})`;
    const list = spotsOf(def), next = def.task.ordered ? list[p.marks.length] : list.find(x => !spotDone(def, x));
    return next ? `${next.label} ở ${mapTitle(next.map)} (${Math.min(p.progress, need(def))}/${need(def)})` : def.title;
  }
  function renderJournal(list) {
    const head = document.createElement("li"); head.className = "pending"; head.textContent = "— Nhiệm vụ phụ (SQ001–SQ047, bản thử) —"; list.append(head);
    const shown = SIDE.filter(def => unlocked(def));
    if (s.aff) { const li = document.createElement("li"); li.className = "done"; li.textContent = `Hảo cảm với Lyra: ${s.aff}`; list.append(li); }
    for (const def of shown) {
      const li = document.createElement("li"), done = isDone(def), on = !!s.q[def.id]?.started;
      li.className = done ? "done" : on ? "current" : "pending";
      li.textContent = done ? `✓ ${def.id} · ${def.title}` : `${on ? "▶" : "○"} ${def.id} · ${def.title} — ${objectiveOf(def)} (Thưởng: ${rewardTextOf(def)})`;
      list.append(li);
    }
    const locked = SIDE.length - shown.length;
    if (locked) { const li = document.createElement("li"); li.className = "pending"; li.textContent = `Còn ${locked} nhiệm vụ phụ mở dần theo nhiệm vụ chính.`; list.append(li); }
    if (s.items.length) { const li = document.createElement("li"); li.className = "done"; li.textContent = `Vật phẩm nhiệm vụ phụ: ${[...new Set(s.items)].join(", ")}`; list.append(li); }
  }
  // SQ004 and SQ017 teach Lôi Miêu a move for good (TRIAL power and uses).
  function bonusSkills(key) {
    if (key !== "loi-mieu") return [];
    const out = [];
    if (s.items.includes("Nhịp Bước Uyên Ương")) out.push({ name: "Nhịp Bước Uyên Ương", power: 220, uses: 10, max: 10, description: "Kael và Lyra nhảy theo nhịp gió, Lôi Miêu lướt theo." });
    if (s.items.includes("Lôi Bạo Cuồng Nộ")) out.push({ name: "Lôi Bạo Cuồng Nộ", power: 300, uses: 6, max: 6, description: "Trăm tia sét dồn vào một cú vuốt." });
    return out;
  }

  // Once the main story leaves nothing to point at, the guide follows the first open side quest.
  function target() {
    for (const def of SIDE) {
      if (!active(def)) continue;
      const p = st(def.id);
      if (ready(def) && def.npc) return { map: def.npc.map, ...npcAt(def), name: def.npc.name };
      if (def.task.wild) return { map: def.task.wild.map, name: def.task.wild.name };
      const list = spotsOf(def), next = def.task.ordered ? list[p.marks.length] : list.find(x => !spotDone(def, x));
      if (next?.at) return { map: next.map, x: next.at.x, y: next.at.y, name: next.label };
    }
    return null;
  }

  // ---- hook into window.Quests: main quests first, then side quests ----
  const base = { ...Q };
  Object.assign(Q, {
    npcs: list => npcs(base.npcs(list)),
    talked(npc) { if (npc?.sideTalk && !npc.questAfter) { talked(npc); updateGuidance(); return; } base.talked(npc); },
    interact() { if (base.interact()) return true; const x = here(); if (x) { x.run(); return true; } return false; },
    actionLabel: () => base.actionLabel() || here()?.label || "",
    draw(ctx, S) {
      base.draw(ctx, S); draw(ctx, S);
      const g = !base.guide() && K.guideTo(target());
      if (g?.link) { ctx.save(); ctx.globalAlpha = .6; ctx.fillStyle = "#7fe9ff"; ctx.beginPath(); ctx.arc(g.x * S + 10, g.y * S + 10, 8, 0, Math.PI * 2); ctx.fill(); ctx.restore(); }
    },
    guide: () => base.guide() || K.guideTo(target()),
    forceWild: mapId => base.forceWild(mapId) || forceWild(mapId),
    wildWon(enemy) { base.wildWon(enemy); wildWon(enemy); },
    wildCaught(enemy) { base.wildCaught(enemy); caught(enemy); },
    battleWon(info) { if (info?.side) { battleEnd(info, state.battle.enemy.hp <= 0 || !!info.surviveTurns); return; } base.battleWon(info); },
    battleLost(info) { if (info?.side) { battleEnd(info, false); return; } base.battleLost(info); },
    renderJournal(list) { base.renderJournal(list); renderJournal(list); },
    bonusSkills: key => [...base.bonusSkills(key), ...bonusSkills(key)],
    save: () => ({ ...base.save(), side: JSON.parse(JSON.stringify(s)) }),
    load(data) { const { side, ...main } = data || {}; base.load(data ? main : data); s = side?.q ? { ...fresh(), ...side } : fresh(); store(); },
    reset() { base.reset(); s = fresh(); store(); },
    side: {
      list: SIDE, state: () => s, open: () => SIDE.filter(open).map(def => def.id), objective: id => objectiveOf(byId.get(id)),
      where(id) {
        const def = byId.get(id); if (!def) return null;
        return { npc: def.npc ? { map: def.npc.map, ...npcAt(def) } : null, start: def.start ? { map: def.start.map, ...startAt(def) } : null,
          wild: def.task.wild ? { ...def.task.wild } : null, catchAny: def.task.catchAny || 0, talkOnly: !!def.task.talkOnly,
          spots: spotsOf(def).map(x => ({ map: x.map, x: x.at.x, y: x.at.y, waves: x.waves || 0, battle: !!x.battle })) };
      },
    },
  });
})();
