(() => {
  const book = document.getElementById("petBook");
  const search = document.getElementById("petSearch");
  const source = document.getElementById("petSource");
  const element = document.getElementById("petElement");
  const grid = document.getElementById("petGrid");
  const detail = document.getElementById("petDetail");
  const count = document.getElementById("petCount");
  const collectionFilter = document.getElementById("petCollectionFilter");
  const collectionStatus = document.getElementById("petCollectionStatus");
  const materialFilter = document.getElementById("petMaterialFilter");
  const bootstrap = window.VQSVBootstrap;
  const roster = bootstrap?.petRoster?.records || [];
  const atlases = bootstrap?.vqsv5Animations?.atlases || {};
  const tickMs = bootstrap?.vqsv5Animations?.frame_duration_ms_per_tick || 60;
  const byId = new Map(roster.map(pet => [pet.pet_id, pet]));
  const stats5 = bootstrap?.vqsv5Stats || { pets: [], skills: [] };
  const v5ById = new Map(stats5.pets.map(pet => [pet.pet_id, pet]));
  const elementOf = pet => pet.element || v5ById.get(pet.pet_id)?.type || null;
  const stats = bootstrap?.vqsv6Stats || { pets: [], skills: [] };
  const statById = new Map(stats.pets.map(pet => [pet.pet_id, pet]));
  const storageKey = "vqsv-unified-pet-collection-v1";
  let saved = new Set();
  let storageAvailable = true;
  try {
    const data = JSON.parse(localStorage.getItem(storageKey) || "[]");
    if (Array.isArray(data)) saved = new Set(data.filter(id => typeof id === "string" && byId.has(id)));
  } catch (_) { storageAvailable = false; }
  // Reserve team for the trial battle. Lôi Miêu is always Kael's lead pet, so the
  // Điện Miêu reference record cannot be added as a duplicate reserve.
  const teamKey = "vqsv-unified-pet-team-v1";
  const TEAM_MAX = 2;
  // The lead pet's current species: Điện Miêu at first, its evolved form afterwards (index.html owns the lead).
  const LEAD_DEFAULT_ID = "vqsv6-db-78";
  const leadId = () => window.VQSVEvolution?.leadForm?.() || LEAD_DEFAULT_ID;
  let team = [];
  try {
    const data = JSON.parse(localStorage.getItem(teamKey) || "[]");
    if (Array.isArray(data)) team = [...new Set(data.filter(id => typeof id === "string" && byId.has(id)))].slice(0, TEAM_MAX);
  } catch (_) { storageAvailable = false; }
  // Pets caught in wild battles: one record per catch, with the level and quality rolled in battle.
  const caughtKey = "vqsv-unified-caught-pets-v1";
  let caught = [];
  try {
    const data = JSON.parse(localStorage.getItem(caughtKey) || "[]");
    if (Array.isArray(data)) caught = data.filter(item => item && byId.has(item.pet_id) && Number.isInteger(item.level) && Number.isInteger(item.quality));
  } catch (_) { storageAvailable = false; }
  function persistCaught() {
    try { localStorage.setItem(caughtKey, JSON.stringify(caught)); storageAvailable = true; }
    catch (_) { storageAvailable = false; }
  }
  function caughtOf(id) { return caught.filter(item => item.pet_id === id); }
  function persistTeam() {
    try { localStorage.setItem(teamKey, JSON.stringify(team)); storageAvailable = true; }
    catch (_) { storageAvailable = false; }
  }
  function persistCollection() {
    try { localStorage.setItem(storageKey, JSON.stringify([...saved])); storageAvailable = true; }
    catch (_) { storageAvailable = false; }
  }
  const entries = roster.map(pet => {
    const kind = pet.source_ids.game === "VQSV5" ? "vqsv5" : "vqsv6";
    const atlasId = kind === "vqsv5" ? String(pet.source_ids.atlas_id) : null;
    return { kind, key: pet.pet_id, name: pet.display_name, element: elementOf(pet) || "Chưa rõ hệ", atlasId, atlas: atlasId ? atlases[atlasId] : null, pet };
  });
  let selected = "vqsv6-db-78";
  let evolveNote = "";
  let skillNote = "";
  let itemNote = "";
  let skillPick = null;
  let timer = null;

  function normalized(value) {
    return String(value).normalize("NFD").replace(/[\u0300-\u036f]/g, "").replace(/[đĐ]/g, "d").toLowerCase();
  }
  function node(tag, text, className) {
    const el = document.createElement(tag);
    if (text !== undefined) el.textContent = text;
    if (className) el.className = className;
    return el;
  }
  function stopAnimation() {
    if (timer !== null) clearTimeout(timer);
    timer = null;
  }
  function portrait(entry) {
    if (entry.kind === "vqsv6") return `assets/pets/vqsv6/${entry.pet.source_ids.image_id}.png`;
    const firstSequence = entry.pet.animation.sequence_ids[0];
    return entry.atlas?.sequence_previews?.[firstSequence] || entry.atlas?.sequences[firstSequence]?.[0]?.image || "";
  }
  function playSequence(image, atlas, sequenceIndex) {
    stopAnimation();
    const sequence = atlas.sequences[sequenceIndex] || [];
    if (!sequence.length) return;
    let index = 0;
    function frame() {
      const item = sequence[index];
      image.src = item.image;
      index = (index + 1) % sequence.length;
      timer = setTimeout(frame, Math.max(1, item.duration_ticks) * tickMs);
    }
    frame();
  }
  // Skill manager: forget skills and learn or replace skills for each individual of this species you own.
  function appendSkillManager(entry) {
    const skills = window.VQSVSkills;
    const options = skills ? skills.options(entry.key) : [];
    for (const option of options) {
      const box = node("div", undefined, "pet-skills");
      box.append(node("h4", `Kỹ năng · ${option.label}`));
      box.append(node("p", `Cấp ${option.level}: ${option.known.length}/${option.slots} ô.${option.locked ? ` (${option.locked})` : ""}`));
      const picking = skillPick && skillPick.key === option.key ? skillPick.row : null;
      for (const skill of option.known) {
        const row = node("div", undefined, "pet-skill-row");
        row.append(node("span", `${skill.name}${skill.power ? ` · ${skill.power}%` : " · hiệu ứng"}`));
        const act = node("button", picking !== null ? "Thay" : "Quên", "pet-skill-button");
        act.type = "button";
        act.disabled = !!option.locked || (picking === null && option.known.length <= 1);
        act.addEventListener("click", () => {
          const result = picking !== null ? skills.learn(option.key, entry.key, picking, skill.row) : skills.forget(option.key, entry.key, skill.row);
          skillNote = result.message; skillPick = null; render();
        });
        row.append(act);
        box.append(row);
      }
      if (picking !== null) {
        const info = option.learnable.find(skill => skill.row === picking);
        box.append(node("p", `Chọn kỹ năng bị thay bởi ${info ? info.name : "kỹ năng mới"}:`, "pet-skill-hint"));
        const cancel = node("button", "Hủy", "pet-skill-button");
        cancel.type = "button";
        cancel.addEventListener("click", () => { skillPick = null; render(); });
        box.append(cancel);
      } else if (option.learnable.length) {
        box.append(node("p", option.full ? "Có thể học (thay một kỹ năng đang biết):" : "Có thể học:", "pet-skill-hint"));
        for (const skill of option.learnable) {
          const row = node("div", undefined, "pet-skill-row");
          const label = node("span", `${skill.name}${skill.power ? ` · ${skill.power}%` : " · hiệu ứng"}`);
          label.title = skill.description;
          row.append(label);
          const act = node("button", option.full ? "Thay…" : "Học", "pet-skill-button");
          act.type = "button";
          act.disabled = !!option.locked;
          act.addEventListener("click", () => {
            if (option.full) { skillPick = { key: option.key, row: skill.row }; skillNote = ""; render(); return; }
            skillNote = skills.learn(option.key, entry.key, skill.row).message; render();
          });
          row.append(act);
          box.append(row);
        }
      }
      detail.append(box);
    }
    if (options.length && skillNote) detail.append(node("p", skillNote, "pet-evolve-note"));
  }
  // Evolution / mutation buttons for each individual of this species (VQSV6 and VQSV5 share the same API).
  function appendEvolutionOptions(entry) {
    const evolution_ = window.VQSVEvolution;
    if (!evolution_) return;
    detail.append(node("p", `Nguyên liệu đang có: ${evolution_.stoneText()}.`));
    for (const option of evolution_.options(entry.key)) {
      detail.append(node("p", `${option.label} · cấp ${option.level}${option.mode === "mutation" ? " · dị hóa" : ""}: ${option.ok ? "đủ điều kiện" : option.reason}.`));
      const go = node("button", `${option.verb} ${option.label}`, "pet-evolve");
      go.type = "button";
      go.disabled = !option.ok;
      go.addEventListener("click", () => {
        const result = evolution_.evolve(option.key, entry.key, option.mode);
        evolveNote = result.message;
        if (result.ok) selected = result.targetId;
        render();
      });
      detail.append(go);
    }
  }
  // VQSV5 special skills: two slots per pet, filled from skill books (skills 26-30 are moves, 31-48 passive).
  let specialNote = "";
  function appendSpecialSkills(entry, rec5) {
    const api = window.VQSVSpecials;
    if (!api) return;
    const individuals = api.individuals(rec5.pet_id);
    if (!individuals.length) { detail.append(node("p", "Kỹ năng đặc biệt: học từ sách kỹ năng, mỗi pet có 2 ô (cần sở hữu pet này).", "pet-skill-hint")); return; }
    for (const individual of individuals) {
      const box = node("div", undefined, "pet-skills");
      box.append(node("h4", `Kỹ năng đặc biệt · ${individual.label}`));
      const slots = api.slots(individual.key);
      slots.forEach((id, index) => {
        const row = node("div", undefined, "pet-skill-row");
        const info = id >= 0 ? api.describe(id) : null;
        const label = node("span", info ? `${info.name} · ${info.passive ? "bị động" : "chủ động"}` : `Ô ${index + 1}: trống`);
        if (info) label.title = info.label;
        row.append(label);
        if (info) {
          const off = node("button", "Quên", "pet-skill-button");
          off.type = "button";
          off.addEventListener("click", () => { specialNote = api.forget(individual.key, rec5.pet_id, index).message; render(); });
          row.append(off);
          box.append(node("p", info.label, "pet-skill-hint"));
        }
        box.append(row);
      });
      const owned = api.books().filter(book => book.owned > 0);
      if (owned.length) {
        box.append(node("p", "Sách trong túi:", "pet-skill-hint"));
        for (const book of owned) {
          const row = node("div", undefined, "pet-skill-row");
          const label = node("span", `${book.name} ×${book.owned}`);
          label.title = book.description;
          row.append(label);
          const full = slots.every(id => id >= 0);
          for (const target of full ? [0, 1] : [null]) {
            const learn = node("button", full ? `Thay ô ${target + 1}` : "Học", "pet-skill-button");
            learn.type = "button";
            learn.addEventListener("click", () => { specialNote = api.learn(individual.key, rec5.pet_id, book.skill_id, target).message; render(); });
            row.append(learn);
          }
          box.append(row);
        }
      } else box.append(node("p", "Túi không có sách kỹ năng. Mua ở Thương điếm bằng huy hiệu (1-9 mỗi cuốn).", "pet-skill-hint"));
      detail.append(box);
    }
    if (specialNote) detail.append(node("p", specialNote, "pet-evolve-note"));
  }
  // Held items: one per pet; equip from the bag, unequip back to it.
  function appendItemManager(entry) {
    const items = window.VQSVItems;
    const options = items ? items.options(entry.key) : [];
    for (const option of options) {
      const box = node("div", undefined, "pet-skills");
      box.append(node("h4", `Vật phẩm mang theo · ${option.label}`));
      if (option.locked) box.append(node("p", `(${option.locked})`));
      if (option.held) {
        const row = node("div", undefined, "pet-skill-row");
        const label = node("span", `${option.held.name}`);
        label.title = option.held.description;
        row.append(label);
        const off = node("button", "Gỡ", "pet-skill-button");
        off.type = "button"; off.disabled = !!option.locked;
        off.addEventListener("click", () => { itemNote = items.unequip(option.key, entry.key).message; render(); });
        row.append(off);
        box.append(row, node("p", option.held.description, "pet-skill-hint"));
      } else box.append(node("p", "Chưa mang vật phẩm nào.", "pet-skill-hint"));
      if (option.owned.length) {
        box.append(node("p", "Trong túi:", "pet-skill-hint"));
        for (const owned of option.owned) {
          const row = node("div", undefined, "pet-skill-row");
          const label = node("span", `${owned.name} ×${owned.count}`);
          label.title = owned.description;
          row.append(label);
          const on = node("button", option.held ? "Đổi" : "Mang", "pet-skill-button");
          on.type = "button"; on.disabled = !!option.locked || option.held?.row === owned.row;
          on.addEventListener("click", () => { itemNote = items.equip(option.key, entry.key, owned.row).message; render(); });
          row.append(on);
          box.append(row);
        }
      } else box.append(node("p", "Túi không có vật phẩm mang theo. Mua ở Thương điếm (5 huy hiệu mỗi món).", "pet-skill-hint"));
      detail.append(box);
    }
    if (options.length && itemNote) detail.append(node("p", itemNote, "pet-evolve-note"));
  }
  function renderDetail(entry) {
    stopAnimation();
    detail.replaceChildren();
    if (!entry) { detail.append(node("p", "Không có pet phù hợp.")); return; }
    detail.append(node("h3", entry.name));
    if (evolveNote) detail.append(node("p", evolveNote, "pet-evolve-note"));
    detail.append(node("p", entry.kind === "vqsv6" ? `VQSV6 · ${entry.element} · Mã ${entry.pet.source_ids.db_id}` : `VQSV5 · Mã ${entry.pet.source_ids.db_id} · Hình ${entry.atlasId}, dạng ${entry.pet.source_ids.form_index + 1}`));
    const box = node("div", undefined, "pet-preview");
    const image = document.createElement("img");
    image.alt = entry.name;
    image.src = portrait(entry);
    box.append(image);
    detail.append(box);
    const collect = node("button", saved.has(entry.key) ? "Bỏ khỏi bộ sưu tập" : "Gom vào bộ sưu tập", "pet-collect");
    collect.type = "button";
    collect.setAttribute("aria-pressed", String(saved.has(entry.key)));
    collect.addEventListener("click", () => {
      if (saved.has(entry.key)) saved.delete(entry.key); else saved.add(entry.key);
      persistCollection();
      render();
    });
    detail.append(collect);
    const mine = caughtOf(entry.key);
    if (mine.length) {
      const best = mine.reduce((a, b) => (b.level > a.level || (b.level === a.level && b.quality > a.quality) ? b : a));
      detail.append(node("p", `Đã bắt ${mine.length} con · tốt nhất cấp ${best.level}, hạng ${best.quality}. Khi vào đội, pet dùng cấp và hạng này.`));
    }
    const inTeam = team.includes(entry.key);
    const teamButton = node("button", inTeam ? "Rút khỏi đội" : `Đưa vào đội (${team.length}/${TEAM_MAX})`, "pet-team");
    teamButton.type = "button";
    teamButton.setAttribute("aria-pressed", String(inTeam));
    if (entry.key === leadId()) { teamButton.disabled = true; teamButton.textContent = "Hình của Lôi Miêu · luôn trong đội"; }
    else if (!inTeam && team.length >= TEAM_MAX) { teamButton.disabled = true; teamButton.textContent = `Đội đã đủ ${TEAM_MAX} pet dự bị`; }
    teamButton.addEventListener("click", () => {
      if (team.includes(entry.key)) team = team.filter(id => id !== entry.key);
      else if (team.length < TEAM_MAX) team.push(entry.key);
      persistTeam();
      render();
    });
    detail.append(teamButton);
    if (entry.kind === "vqsv6") {
      const stat = statById.get(entry.key);
      if (stat) {
        const lv5 = stat.stats_at_default_quality["5"], lv30 = stat.stats_at_default_quality["30"];
        const table = node("table", undefined, "pet-stats");
        const head = node("tr"); ["", "HP", "Công", "Thủ", "Tốc"].forEach(text => head.append(node("th", text)));
        table.append(head);
        for (const [label, values] of [["Cấp 5", lv5], ["Cấp 30", lv30]]) {
          const row = node("tr"); row.append(node("th", label));
          [values.hp, values.attack, values.defense, values.speed].forEach(value => row.append(node("td", String(value))));
          table.append(row);
        }
        detail.append(node("p", `Hạng mặc định ${stat.default_quality}/${stat.max_quality_stars} sao · chỉ số theo công thức VQSV6 gốc.`));
        detail.append(table);
        const skills = (stat.skills_at_level["30"] || []).map(row => stats.skills[row]?.name).filter(Boolean);
        if (skills.length) detail.append(node("p", `Kỹ năng hệ (tới cấp 30): ${skills.join(", ")}.`));
      }
      if (entry.key === leadId()) detail.append(node("p", entry.key === LEAD_DEFAULT_ID ? "Đang dùng làm hình tham khảo cho Lôi Miêu trong truyện." : "Dạng hiện tại của Lôi Miêu (tiến hóa từ Điện Miêu theo dữ liệu nguồn)."));
      appendSkillManager(entry);
      appendItemManager(entry);
      const evolution = entry.pet.evolution;
      const target = evolution && byId.get(evolution.target_pet_id);
      if (target) {
        const kind = target.raw_fields?.[2];
        const label = kind === 3 ? "Dị hóa" : (kind === 1 || kind === 2 ? "Tiến hóa" : "Biến đổi");
        detail.append(node("p", `${label} → ${target.display_name} (theo game VQSV6 gốc).`));
        if (evolution.minimum_level != null && evolution.material) {
          detail.append(node("p", `Điều kiện gốc: cấp ${evolution.minimum_level}, ${evolution.material.quantity} ${evolution.material.name}. Điều kiện trong game hợp nhất chưa chốt.`));
        }
        if (evolution.material) appendEvolutionOptions(entry);
        const viewTarget = node("button", `Xem ${target.display_name}`, "pet-target");
        viewTarget.type = "button";
        viewTarget.addEventListener("click", () => {
          selected = target.pet_id;
          search.value = ""; source.value = "all"; element.value = "all";
          collectionFilter.value = "all"; materialFilter.value = "all";
          render();
        });
        detail.append(viewTarget);
      } else detail.append(node("p", "Chưa có dạng tiếp theo được nối trong bảng nguồn."));
    } else {
      const rec5 = v5ById.get(entry.key);
      if (rec5) {
        detail.append(node("p", `${rec5.type || "Không thuộc hệ nào"} · hạng ${rec5.rank}/5 (chỉ số gốc VQSV5, chưa quy đổi)`));
        const table = node("table", undefined, "pet-stats");
        const head = node("tr"); ["", "HP", "KN", "Lhoạt", "Công", "Thủ"].forEach(text => head.append(node("th", text)));
        table.append(head);
        for (const level of ["5", "30"]) {
          const values = rec5.stats_at[level], row = node("tr"); row.append(node("th", `Cấp ${level}`));
          [values.hp, values.sp, values.agility, values.attack, values.defense].forEach(value => row.append(node("td", String(value))));
          table.append(row);
        }
        detail.append(table);
        const unlocks = rec5.skill_slots.filter(slot => slot.level > 0).map(slot => `cấp ${slot.level}: ${stats5.skills[slot.skill_id].name}`);
        detail.append(node("p", unlocks.length ? `Kỹ năng tự học — ${unlocks.join("; ")}.` : "Pet không thuộc hệ nên không có kỹ năng hệ."));
        detail.append(node("p", "KN = giá trị kỹ năng (SP), Lhoạt = linh hoạt (quyết định tỉ lệ trúng). Trong trận, chỉ số được quy đổi theo cấp để cân với pet VQSV6."));
        if (stats5.traits && stats5.trait_rules) {
          const pair = stats5.trait_rules.type_pairs[rec5.type] || [];
          const describe = id => `${stats5.traits[id].name} — ${stats5.traits[id].description}${[5, 6].includes(id) ? " (chưa có trạng thái Thạch Hóa của VQSV5 nên chưa áp dụng trong trận)" : ""}`;
          const lines = pair.length ? pair.map(id => `• ${describe(id)}`) : ["• Pet không thuộc hệ nên không có đặc tính hệ."];
          detail.append(node("p", `Đặc tính ngẫu nhiên khi gặp (ô 1, theo hệ): ${lines.join(" ")}. Ô 2: Khí lực suy yếu (HP −15%) / Khí lực cường tráng (HP +15%) / không có.`));
          const mine = window.VQSVTraits?.of?.(rec5.pet_id);
          if (mine) detail.append(node("p", `Đặc tính của cá thể bạn: ${mine.map(id => stats5.traits[id].name).join(" + ")}.`));
        }
        appendSpecialSkills(entry, rec5);
        const evo5 = rec5.evolution;
        if (evo5) {
          const mats = evo5.materials.map(material => `${material.quantity} ${material.name}`).join(", ");
          const gate = stats5.evolution_rules.level_gate_by_rank[String(rec5.rank)];
          detail.append(node("p", `Tiến hóa → ${byId.get(evo5.target_pet_id)?.display_name || evo5.target_pet_id}: ${gate ? `cấp ${gate}` : "không yêu cầu cấp"}, ${mats}; pet mới có ${rec5.evolution_points} lần tiến hóa.`));
          if (evo5.mutation_target_pet_id) detail.append(node("p", `Dị hóa → ${byId.get(evo5.mutation_target_pet_id)?.display_name || evo5.mutation_target_pet_id}: như trên và thêm 1 ${stats5.items.find(item => item.item_id === stats5.evolution_rules?.mutation_item_id)?.name || "vật liệu dị hóa"}.`));
          appendEvolutionOptions(entry);
          const viewTarget5 = node("button", `Xem ${byId.get(evo5.target_pet_id)?.display_name || "dạng tiếp theo"}`, "pet-target");
          viewTarget5.type = "button";
          viewTarget5.addEventListener("click", () => { selected = evo5.target_pet_id; search.value = ""; source.value = "all"; element.value = "all"; collectionFilter.value = "all"; materialFilter.value = "all"; render(); });
          detail.append(viewTarget5);
        } else detail.append(node("p", rec5.found_by === "evolution" || rec5.found_by === "mutation" ? "Đã là dạng cuối của chuỗi tiến hóa." : "Không có dạng tiếp theo."));
      }
      if (!entry.atlas) { detail.append(node("p", "Chưa tải được hình nguồn.")); return; }
      const label = node("label", "Xem hoạt ảnh");
      const select = document.createElement("select");
      select.className = "pet-sequence";
      entry.pet.animation.sequence_ids.forEach((sequenceId, index) => {
        const sequence = entry.atlas.sequences[sequenceId];
        const option = node("option", `Chuỗi ${index + 1} · ${sequence.length} khung`);
        option.value = String(sequenceId);
        select.append(option);
      });
      select.addEventListener("change", () => playSequence(image, entry.atlas, Number(select.value)));
      label.append(select);
      detail.append(label);
      if (entry.pet.description) detail.append(node("p", entry.pet.description));
      detail.append(node("p", "Tốc độ xem hoạt ảnh gần đúng theo nhịp game gốc."));
      playSequence(image, entry.atlas, entry.pet.animation.sequence_ids[0]);
    }
  }
  function render() {
    const query = normalized(search.value.trim());
    const visible = entries.filter(entry => {
      const sourceMatch = source.value === "all" || source.value === entry.kind;
      const elementMatch = element.value === "all" || entry.element === element.value;
      const collectionMatch = collectionFilter.value === "all" || (collectionFilter.value === "caught" ? caughtOf(entry.key).length > 0 : saved.has(entry.key));
      const materialMatch = materialFilter.value === "all" || entry.pet.evolution?.material?.name === materialFilter.value;
      const text = [entry.name, entry.element, entry.key, entry.kind, entry.atlasId || "", entry.pet?.source_ids.db_id || ""].join(" ");
      return sourceMatch && elementMatch && collectionMatch && materialMatch && (!query || normalized(text).includes(query));
    });
    if (!visible.some(entry => entry.key === selected)) selected = visible[0]?.key || null;
    grid.replaceChildren();
    count.textContent = `${visible.length} pet · có thể tìm theo tên, hệ hoặc mã nguồn`;
    const teamNames = team.map(id => byId.get(id)?.display_name).filter(Boolean);
    collectionStatus.textContent = `Đã gom ${saved.size} pet · Đội: Lôi Miêu${teamNames.length ? " + " + teamNames.join(", ") : ""} (${team.length}/${TEAM_MAX} dự bị, đổi được trong trận; thay đổi áp dụng từ trận sau).` + (storageAvailable ? " Lưu trên trình duyệt này." : " Không thể lưu trên trình duyệt này; lựa chọn chỉ giữ trong phiên hiện tại.");
    if (!visible.length) grid.append(node("p", "Không tìm thấy mục phù hợp.", "pet-empty"));
    for (const entry of visible) {
      const button = node("button", undefined, "pet-card");
      button.type = "button";
      button.setAttribute("aria-pressed", String(entry.key === selected));
      const image = document.createElement("img");
      image.src = portrait(entry);
      image.alt = "";
      image.loading = "lazy";
      button.append(image, node("span", entry.name), node("small", `${entry.kind === "vqsv6" ? entry.element : `VQSV5 · #${entry.pet.source_ids.db_id}`}${saved.has(entry.key) ? " · Đã gom" : ""}${team.includes(entry.key) ? " · Trong đội" : ""}${caughtOf(entry.key).length ? " · Đã bắt" : ""}`));
      button.addEventListener("click", () => { selected = entry.key; evolveNote = ""; skillNote = ""; itemNote = ""; skillPick = null; render(); });
      grid.append(button);
    }
    renderDetail(visible.find(entry => entry.key === selected));
  }
  for (const value of [...new Set(entries.map(entry => entry.element))].sort((a, b) => a.localeCompare(b, "vi"))) {
    const option = node("option", value);
    option.value = value;
    element.append(option);
  }
  for (const value of [...new Set(entries.map(entry => entry.pet.evolution?.material?.name).filter(Boolean))].sort((a, b) => a.localeCompare(b, "vi"))) {
    const option = node("option", value);
    option.value = value;
    materialFilter.append(option);
  }
  search.addEventListener("input", render);
  source.addEventListener("change", render);
  element.addEventListener("change", render);
  collectionFilter.addEventListener("change", render);
  materialFilter.addEventListener("change", render);
  document.getElementById("closePetBook").addEventListener("click", () => book.close());
  book.addEventListener("close", stopAnimation);
  // Battle-side access to the reserve team. Only names, elements and source graphics are
  // exposed; combat numbers are assigned by the battle as clearly-marked trial values.
  window.VQSVPetTeam = {
    max: TEAM_MAX,
    ids: () => [...team],
    caughtBest(id) {
      const mine = caughtOf(id);
      return mine.length ? mine.reduce((a, b) => (b.level > a.level || (b.level === a.level && b.quality > a.quality) ? b : a)) : null;
    },
    caughtCount: () => caught.length,
    speciesCaught: () => new Set(caught.map(item => item.pet_id)).size,
    // An evolved reserve pet changes species: swap it in the team, add the new form to the collection and move one catch record.
    replaceSpecies(oldId, newId) {
      if (!byId.has(newId)) return;
      team = [...new Set(team.map(id => id === oldId ? newId : id).filter(id => id !== leadId()))].slice(0, TEAM_MAX);
      saved.add(newId);
      const mine = caughtOf(oldId);
      if (mine.length) {
        const best = mine.reduce((a, b) => (b.level > a.level || (b.level === a.level && b.quality > a.quality) ? b : a));
        caught.splice(caught.indexOf(best), 1);
        caught.push({ ...best, pet_id: newId });
        persistCaught();
      }
      persistTeam(); persistCollection();
    },
    // Record a catch; also adds the species to the collection and to a free team slot.
    recordCatch(id, level, quality) {
      if (!byId.has(id)) return { addedToTeam: false };
      caught.push({ pet_id: id, level, quality, caught_at: new Date().toISOString() });
      saved.add(id);
      let addedToTeam = false;
      if (id !== leadId() && !team.includes(id) && team.length < TEAM_MAX) { team.push(id); addedToTeam = true; persistTeam(); }
      persistCaught(); persistCollection();
      return { addedToTeam };
    },
    info(id) {
      const entry = entries.find(item => item.key === id);
      if (!entry) return null;
      return {
        id, kind: entry.kind, name: entry.name, element: elementOf(entry.pet), portrait: portrait(entry),
        image: entry.kind === "vqsv6" ? `assets/pets/vqsv6/${entry.pet.source_ids.image_id}.png` : null,
        sequences: entry.kind === "vqsv5" && entry.atlas ? entry.pet.animation.sequence_ids.map(sequenceId => entry.atlas.sequences[sequenceId] || []) : []
      };
    }
  };
  window.openPetBook = () => {
    if (!book.open) { book.showModal(); render(); search.focus(); }
  };
})();
