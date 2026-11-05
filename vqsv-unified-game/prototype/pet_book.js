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
  const LEAD_REFERENCE_ID = "vqsv6-db-78";
  let team = [];
  try {
    const data = JSON.parse(localStorage.getItem(teamKey) || "[]");
    if (Array.isArray(data)) team = [...new Set(data.filter(id => typeof id === "string" && byId.has(id) && id !== LEAD_REFERENCE_ID))].slice(0, TEAM_MAX);
  } catch (_) { storageAvailable = false; }
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
    return { kind, key: pet.pet_id, name: pet.display_name, element: pet.element || "Chưa rõ hệ", atlasId, atlas: atlasId ? atlases[atlasId] : null, pet };
  });
  let selected = "vqsv6-db-78";
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
  function renderDetail(entry) {
    stopAnimation();
    detail.replaceChildren();
    if (!entry) { detail.append(node("p", "Không có pet phù hợp.")); return; }
    detail.append(node("h3", entry.name));
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
    const inTeam = team.includes(entry.key);
    const teamButton = node("button", inTeam ? "Rút khỏi đội" : `Đưa vào đội (${team.length}/${TEAM_MAX})`, "pet-team");
    teamButton.type = "button";
    teamButton.setAttribute("aria-pressed", String(inTeam));
    if (entry.key === LEAD_REFERENCE_ID) { teamButton.disabled = true; teamButton.textContent = "Hình của Lôi Miêu · luôn trong đội"; }
    else if (!inTeam && team.length >= TEAM_MAX) { teamButton.disabled = true; teamButton.textContent = `Đội đã đủ ${TEAM_MAX} pet dự bị`; }
    teamButton.addEventListener("click", () => {
      if (team.includes(entry.key)) team = team.filter(id => id !== entry.key);
      else if (team.length < TEAM_MAX) team.push(entry.key);
      persistTeam();
      render();
    });
    detail.append(teamButton);
    if (entry.kind === "vqsv6") {
      if (entry.key === "vqsv6-db-78") detail.append(node("p", "Đang dùng làm hình tham khảo cho Lôi Miêu trong truyện."));
      const evolution = entry.pet.evolution;
      const target = evolution && byId.get(evolution.target_pet_id);
      if (target) {
        const kind = target.raw_fields?.[2];
        const label = kind === 3 ? "Dị hóa" : (kind === 1 || kind === 2 ? "Tiến hóa" : "Biến đổi");
        detail.append(node("p", `${label} → ${target.display_name} (theo game VQSV6 gốc).`));
        if (evolution.minimum_level != null && evolution.material) {
          detail.append(node("p", `Điều kiện gốc: cấp ${evolution.minimum_level}, ${evolution.material.quantity} ${evolution.material.name}. Điều kiện trong game hợp nhất chưa chốt.`));
        }
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
      const collectionMatch = collectionFilter.value === "all" || saved.has(entry.key);
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
      button.append(image, node("span", entry.name), node("small", `${entry.kind === "vqsv6" ? entry.element : `VQSV5 · #${entry.pet.source_ids.db_id}`}${saved.has(entry.key) ? " · Đã gom" : ""}${team.includes(entry.key) ? " · Trong đội" : ""}`));
      button.addEventListener("click", () => { selected = entry.key; render(); });
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
    info(id) {
      const entry = entries.find(item => item.key === id);
      if (!entry) return null;
      return {
        id, kind: entry.kind, name: entry.name, element: entry.pet.element || null, portrait: portrait(entry),
        image: entry.kind === "vqsv6" ? `assets/pets/vqsv6/${entry.pet.source_ids.image_id}.png` : null,
        sequences: entry.kind === "vqsv5" && entry.atlas ? entry.pet.animation.sequence_ids.map(sequenceId => entry.atlas.sequences[sequenceId] || []) : []
      };
    }
  };
  window.openPetBook = () => {
    if (!book.open) { book.showModal(); render(); search.focus(); }
  };
})();
