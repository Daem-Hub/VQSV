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
  const ids = QUESTS.map(q => q.id);
  // Enemies (TRIAL choices from the VQSV6 roster): Dã Lang Bão Tố = Nhiệt Bạo Phong Tốc Khuyển, the night-raid beasts =
  // Đan nhãn thú, Cuồng Nham Cự Tượng = Nham Sơn Long, the Ma Khống Cầu fight = the story's Tà Vân Khuyển Thần.
  const WOLF = "vqsv6-db-21", RAIDER = "vqsv6-db-89", COLOSSUS = "vqsv6-db-51", POSSESSED = "vqsv6-db-24";

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
  function nearFree(map, x, y, taken = []) {
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
    if (state.mode !== "explore" || !q.started) return null;
    const s = spots(), id = current()?.id;
    if (id === "MQ001" && onMap(VILLAGE) && close(s.get("dummy"), true)) return { label: "Đánh bia gỗ", run: fightDummy };
    if (id === "MQ002" && onMap(VILLAGE) && close(s.get("cellar"), true)) return { label: "Mở căn hầm cũ", run: openCellar };
    if (id === "MQ004" && q.progress < 5 && onMap(MEADOW)) { const i = s.get("grass").findIndex((g, n) => !q.marks.includes(n) && close(g, true)); if (i >= 0) return { label: "Hái Cỏ Dạ Quang", run: () => pickGrass(i) }; }
    if (id === "MQ005" && q.progress < 3 && onMap(STREAM)) { const p = s.get("prints")[q.progress]; if (close(p, true)) return { label: "Xem dấu chân tà khí", run: followPrint }; }
    if (id === "MQ007" && onMap(VILLAGE) && close(s.get("colossus"), true)) return { label: "Đối mặt Nham Cự Tượng", run: fightColossus };
    if (id === "MQ008" && onMap(VILLAGE) && close(assets.map.entities.encounter, true)) return { label: "Phá Ma Khống Cầu", run: fightOrb };
    if (id === "MQ009" && onMap(FOREST) && close(s.get("shrine"), true)) return { label: "Nghe Linh Hồn Cổ Thụ", run: hearSpirit };
    return null;
  }

  // ---- quest battles ----
  function questBattle(quest, enemy, message, extra = {}) {
    const player = state.player;
    player.inputDir = null; player.step = null; player.visualX = player.x; player.visualY = player.y;
    state.battle = newBattle();
    state.battle.enemy = enemy;
    state.battle.quest = { id: quest, ...extra };
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
    questBattle("MQ008", questEnemy(POSSESSED, 8, "Tà Vân Khuyển Thần", { hpScale: 2.5 }),
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
    const quest = current();
    state.battle = newBattle(); state.mode = "explore"; wildSteps = 0;
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
      "Ma Khống Cầu là dấu hiệu phong ấn đang rạn. Hãy cầm lấy bản đồ này và tìm bốn thánh vật."], () => { q.items.push("Bản Đồ Ryan"); complete("Nhận Bản Đồ Ryan. Chương 1 · phần Làng Sương Mù hoàn tất."); });
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
    q.done.push(quest.id); q.index++; q.progress = 0; q.marks = [];
    // Steps that start by themselves; MQ003 and MQ004 start when Kael talks to their giver.
    q.started = ["MQ002", "MQ005", "MQ006", "MQ007", "MQ008", "MQ009"].includes(current()?.id);
    save(); updateWallet();
    statusEl.textContent = `Hoàn thành ${quest.id} · ${quest.title} · ${quest.rewardText}`;
    window.VQSVSound?.play("levelUp");
    if (text) state.battle.message = text;
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
    return out;
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
    if (!isActive("MQ003") || mapId !== FOREST || q.progress >= 3 || Math.random() >= .5) return null;
    return { petId: WOLF, area: "MQ003", level: 3 + Math.floor(Math.random() * 2), quality: 2, questName: "Dã Lang Bão Tố" };
  }
  function wildWon(enemy) {
    if (!isActive("MQ003") || enemy.petId !== WOLF || q.progress >= 3) return;
    q.progress++; save();
    statusEl.textContent += ` · Dã Lang Bão Tố ${q.progress}/3`;
  }

  // ---- what the screen shows ----
  function objective() {
    const quest = current();
    if (!quest) return "Chương 1 · phần Làng Sương Mù đã xong. Các nhiệm vụ từ MQ010 (Đèo Gió Bấc) chưa có trong bản thử.";
    if (!q.intro) return null;
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
    if (!q.started || !assets.maps) return;
    const s = spots(), id = current()?.id, t = performance.now();
    const glow = (x, y, color, r = 7) => {
      ctx.save(); ctx.globalAlpha = .55 + Math.sin(t / 260) * .25; ctx.fillStyle = color;
      ctx.beginPath(); ctx.arc(x * S + 10, y * S + 10, r, 0, Math.PI * 2); ctx.fill(); ctx.restore();
    };
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
    if (intro) intro.textContent = "MQ001–MQ009 theo bảng nhiệm vụ (bản thử). Nhiệm vụ kế tiếp mở khi xong nhiệm vụ trước.";
    QUESTS.forEach((quest, i) => {
      const li = document.createElement("li"), done = q.done.includes(quest.id), now = i === q.index;
      li.className = done ? "done" : now ? "current" : "pending";
      li.textContent = done ? `✓ ${quest.id} · ${quest.title}` : now ? `▶ ${quest.id} · ${quest.title} — ${q.intro ? quest.text : "Nói chuyện với Trưởng làng Elden."} (Thưởng: ${quest.rewardText})` : `○ ${quest.id} · ${quest.title} · ${quest.place}`;
      list.append(li);
    });
    if (q.items.length) { const li = document.createElement("li"); li.className = "done"; li.textContent = `Vật phẩm cốt truyện: ${[...new Set(q.items)].join(", ")}`; list.append(li); }
  }
  function continueFromEnding() {
    if (state.mode !== "ending") return false;
    state.mode = "explore"; state.battle = newBattle(); updateGuidance(); return true;
  }

  window.Quests = {
    list: QUESTS, state: () => q, current, interact, talked, npcs, objective, actionLabel, draw, renderJournal, forceWild, wildWon,
    battleWon, battleLost, continueFromEnding, night, storyFightOpen: () => false, spots,
    save: () => JSON.parse(JSON.stringify(q)),
    load(data) { q = data && Number.isInteger(data.index) ? { ...fresh(), ...data } : fresh(); save(); },
    reset() { q = fresh(); save(); },
    // test helper: jump to a quest with its steps before marked done
    jump(id) { const i = ids.indexOf(id); if (i < 0) return; q = { ...fresh(), index: i, intro: true, started: !["MQ003", "MQ004"].includes(id), done: ids.slice(0, i) }; save(); },
  };
})();
