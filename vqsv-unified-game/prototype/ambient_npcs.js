// Người qua đường: a few NPCs wander around their spot (TRIAL). The village's Dân làng and some extra passers-by in
// the village, Bến cảng Ryan, Atlantis and Lối vào Aethelgard take a step now and then, within a few tiles of home.
// Quest givers and shopkeepers stay put so the quest guide and tests can find them. A wanderer stops while Kael is
// close (so he can talk to it), during dialogues, battles and the MQ006–MQ008 night raid, and only steps onto open
// cells (not exits, not narrow passages) so it never blocks the way.
(() => {
  const Q = window.Quests;
  if (!Q?.kit) return;
  const K = Q.kit;
  const STEP_MS = 520, RADIUS = 3;
  const VILLAGE = "slice01-border-village";
  const P = (id, name, art, map, frac, lines) => ({ id: "amb-" + id, name, art, map, frac, dialogue: lines });
  const PASSERS = [
    P("flower", "Bà bán hoa", "herbalist", VILLAGE, [.68, .34], ["Hoa sương mù chỉ nở vào sáng sớm thôi cháu ạ.", "Mua một bó tặng ai đó không, Kael?"]),
    P("hunter", "Thợ săn trẻ", "scout", VILLAGE, [.32, .72], ["Mình đang tập bắt pet ở Đồng Cỏ Lộng Gió.", "Nghe nói đánh nhẹ tay rồi ném cầu thì dễ bắt hơn."]),
    P("fisher", "Ông lão câu cá", "elder", VILLAGE, [.55, .88], ["Cá dạo này cắn câu ít lắm… chắc tại trăng đỏ.", "Hồi trẻ ta cũng từng ra biển với cha cháu đấy."]),
    P("sailor", "Thủy thủ", "scout", "ryan-port", [.42, .5], ["Gió hôm nay thuận, tàu sắp nhổ neo rồi.", "Ngoài Hải Vực Vô Tận có xoáy nước sấm sét, cẩn thận nhé."]),
    P("fishwife", "Cô bán cá", "herbalist", "ryan-port", [.62, .66], ["Cá tươi đây! À, cháu không ăn cá à?", "Pet của cháu nhìn đói lắm kìa."]),
    P("citizen", "Cư dân Hải Tộc", "herbalist", "atlantis", [.62, .3], ["Chào người trên cạn! Atlantis đẹp chứ?", "Thương Hải Cự Kình là thần hộ mệnh của chúng tôi."]),
    P("guard", "Lính tuần Hải Tộc", "scout", "atlantis", [.3, .42], ["Ta đang đi tuần. Đừng lại gần khu Vane ở vực sâu.", "Có tiếng máy móc vọng lên từ đáy biển…"]),
    P("traveler", "Lữ khách tầng mây", "villager", "aethelgard-gate", [.42, .5], ["Lục Địa Bay nhìn gần còn đẹp hơn trong truyện.", "Gió trên này mạnh, giữ chặt pet của cháu nhé."]),
  ];
  const EXISTING = new Set(["villager"]);          // the village's Dân làng also wanders
  const live = new Map();
  const rand = (a, b) => a + Math.random() * (b - a);
  const dist = (a, b) => Math.abs(a.x - b.x) + Math.abs(a.y - b.y);

  function track(id, x, y, now) {
    let s = live.get(id);
    if (!s) { s = { x, y, fromX: x, fromY: y, homeX: x, homeY: y, startedAt: -1e9, next: now + rand(600, 2600) }; live.set(id, s); }
    return s;
  }
  const paused = () => state.mode !== "explore" || !!Q.night?.() || !assets.map;
  const isExit = (map, x, y) => (map.links || []).some(link => link.x === x && link.y === y);
  // An open cell: at least three of its four neighbours are free, so a wanderer never plugs a corridor.
  const openCell = (x, y) => [[1, 0], [-1, 0], [0, 1], [0, -1]].filter(([dx, dy]) => !blocked(x + dx, y + dy)).length >= 3;

  function step(id, s, now) {
    if (now - s.startedAt < STEP_MS || now < s.next) return;
    const p = state.player;
    if (dist(p, s) <= 3) { s.next = now + rand(700, 1500); return; }         // Kael is near: stand still for a talk
    const dirs = [[1, 0], [-1, 0], [0, 1], [0, -1]].sort(() => Math.random() - .5);
    for (const [dx, dy] of dirs) {
      const x = s.x + dx, y = s.y + dy, to = { x, y };
      if (Math.abs(x - s.homeX) + Math.abs(y - s.homeY) > RADIUS || dist(p, to) < 3) continue;
      if (blocked(x, y) || isExit(assets.map, x, y) || !openCell(x, y)) continue;
      Object.assign(s, { fromX: s.x, fromY: s.y, x, y, startedAt: now, next: now + STEP_MS + rand(1200, 4200) });
      return;
    }
    s.next = now + rand(800, 2000);
  }
  function shown(s, now) {
    const t = Math.max(0, Math.min(1, (now - s.startedAt) / STEP_MS));
    const hop = t < 1 ? Math.sin(t * Math.PI) * .12 : 0;
    return { x: s.x, y: s.y, drawX: s.fromX + (s.x - s.fromX) * t, drawY: s.fromY + (s.y - s.fromY) * t - hop };
  }
  function passersHere() {
    const out = [];
    for (const def of PASSERS) {
      if (!K.onMap(def.map)) continue;
      const at = K.place(def, `${def.map}:ambient:${def.id}`);
      if (at) out.push({ def, at });
    }
    return out;
  }
  // Each frame (from Quests.draw): move the wanderers of this map.
  function tick() {
    if (paused()) return;
    const now = performance.now();
    for (const { def, at } of passersHere()) step(def.id, track(def.id, at.x, at.y, now), now);
    if (K.onMap(VILLAGE)) for (const npc of assets.map.entities?.villagers || []) if (EXISTING.has(npc.id)) step(npc.id, track(npc.id, npc.x, npc.y, now), now);
  }
  function npcs(list) {
    if (K.busy()) return list;
    const now = performance.now();
    const out = list.map(npc => {
      const s = K.onMap(VILLAGE) && EXISTING.has(npc.id) && live.get(npc.id);
      return s ? { ...npc, ...shown(s, now) } : npc;
    });
    for (const { def, at } of passersHere()) {
      if (assets.chars && !assets.chars[def.id]) assets.chars[def.id] = assets.chars[def.art] || assets.chars.scout;
      const s = track(def.id, at.x, at.y, now);
      out.push({ id: def.id, name: def.name, dialogue: def.dialogue, ambient: true, ...shown(s, now) });
    }
    return out;
  }

  const base = { ...Q };
  Object.assign(Q, {
    npcs: list => npcs(base.npcs(list)),
    draw(ctx, S) { tick(); base.draw(ctx, S); },
    ambient: { list: PASSERS, live: () => live, radius: RADIUS, reset: () => live.clear() },
  });
})();
