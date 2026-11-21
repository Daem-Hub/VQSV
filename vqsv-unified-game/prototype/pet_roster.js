// Sủng vật (VQSV6 petstate.ui / petsetting.ui, game/k.java): the pet backpack ("Ba lô Sủng vật", 6 slots
// in game/j.java A[6]) and the pet bank ("Ngân hàng Sủng vật", j.java A(): fewer than 100). Actions follow the
// source: Xuất chiến (lead the next battle), Gởi lại (to the bank; the backpack keeps at least one pet),
// Lấy ra (to the backpack while it has room), Phóng sinh (release, with a confirmation step) and Tiến hóa / Dị hóa.
// Lôi Miêu is Kael's story companion: like the source's "Thần thú" it cannot be released, and it stays in the backpack.
(() => {
  const byId = id => document.getElementById(id);
  const dialog = byId("petRoster"), list = byId("rosterList"), detail = byId("rosterDetail"), message = byId("rosterMessage");
  const BAG_MAX = 6, BANK_MAX = 100;
  let tab = "bag", selected = "loi-mieu", confirmRelease = null, fuseFrom = null;   // fuseFrom: main pet while picking a partner

  const team = () => window.VQSVPetTeam?.ids() || [];
  function bagPets() { return buildTeam().sort((a, b) => order(a.id) - order(b.id)); }
  function order(id) { return id === "loi-mieu" ? -1 : team().indexOf(id); }
  function bankIds() {
    const carried = new Set(team()), trained = Object.keys(progress).filter(key => key !== "loi-mieu");
    return window.VQSVPetTeam.owned(trained.filter(id => window.VQSVPetTeam.info(id))).filter(id => !carried.has(id)).slice(0, BANK_MAX);
  }
  function allPets() { return [...bagPets(), ...bankIds().map(reserveCombatant).filter(Boolean)]; }
  function fuseMain() { return fuseFrom ? allPets().find(pet => pet.id === fuseFrom) : null; }
  // Partner picking lists every pet from the other game, from the backpack and the bank.
  function current() {
    const main = fuseMain();
    if (main) return allPets().filter(pet => pet.id !== main.id && pet.id !== "loi-mieu" && !!pet.v5 !== !!main.v5);
    return tab === "bag" ? bagPets() : bankIds().map(reserveCombatant).filter(Boolean);
  }

  function bar(className, value, max) {
    const meter = document.createElement("span"), fill = document.createElement("i");
    meter.className = "roster-meter " + className;
    fill.style.width = (max > 0 ? Math.max(0, Math.min(100, 100 * value / max)) : 0) + "%";
    meter.append(fill); return meter;
  }
  function expOf(pet) {
    const id = pet.id === "loi-mieu" ? "loi-mieu" : pet.id, entry = progress[id];
    const level = pet.level, need = level >= LEVEL_CAP ? 0 : expToReach(level + 1);
    return { exp: entry?.exp || 0, need };
  }
  function portrait(pet) {
    const img = document.createElement("img");
    img.alt = ""; img.src = pet.portrait || pet.image || pet.sequences?.[0]?.[0]?.image || "assets/pets/electric_cat_candidate.png";
    return img;
  }
  function evolution(pet) {
    const speciesId = pet.id === "loi-mieu" ? leadForm() : pet.id;
    return (window.VQSVEvolution?.options(speciesId) || []).filter(option => option.key === (pet.id === "loi-mieu" ? "loi-mieu" : pet.id));
  }

  function renderList(pets) {
    list.replaceChildren();
    for (const pet of pets) {
      const row = document.createElement("button"), text = document.createElement("span"), name = document.createElement("strong"), sub = document.createElement("small");
      row.type = "button"; row.dataset.id = pet.id; row.setAttribute("role", "option");
      row.setAttribute("aria-selected", String(pet.id === selected));
      name.textContent = pet.name;
      const first = tab === "bag" && pet.id === battleFirst();
      sub.textContent = `Cấp ${pet.level} · ${pet.v5 ? `${pet.element} (${pet.unifiedElement})` : pet.element || "Chưa rõ hệ"} · ${pet.v5 ? "VQSV5" : "VQSV6"}${first ? " · Xuất chiến" : ""}`;
      const { exp, need } = expOf(pet);
      text.append(name, sub, bar("hp", pet.hp, pet.max), bar("exp", exp, need || 1));
      row.append(portrait(pet), text);
      row.onclick = () => { selected = pet.id; confirmRelease = null; message.textContent = ""; render(); };
      list.append(row);
    }
    if (!pets.length) { const empty = document.createElement("p"); empty.className = "roster-empty"; empty.textContent = tab === "bank" ? "Ngân hàng chưa có sủng vật. Bắt pet hoang dã khi ba lô đã đủ 6 con, hoặc gởi lại từ ba lô." : "Ba lô trống."; list.append(empty); }
  }

  function action(label, handler, disabled = false, kind = "") {
    const button = document.createElement("button");
    button.type = "button"; button.textContent = label; button.disabled = disabled; if (kind) button.className = kind;
    button.onclick = handler; return button;
  }
  function renderFusion(main, partner) {
    const head = document.createElement("p"); head.className = "roster-evo";
    head.textContent = `Ghép vào ${main.name} (cấp ${main.level}, ${main.v5 ? "VQSV5" : "VQSV6"}). Chọn một pet ${main.v5 ? "VQSV6" : "VQSV5"} cấp ${FUSE_MIN_LEVEL}+ làm pet ghép; pet ghép sẽ mất đi.`;
    detail.append(head);
    const actions = document.createElement("div"); actions.className = "roster-actions";
    if (partner) {
      const check = fusionCheck(main, partner), preview = document.createElement("p"); preview.className = "roster-skills";
      preview.textContent = check.ok
        ? `${partner.name} (cấp ${partner.level}, ${elementOfPet(partner)}) → ${main.name} nhận ${check.exp} EXP${check.sameElement ? (main.v5 ? ", gieo lại đặc tính (cùng hệ)" : `, phẩm chất ${main.quality} → ${check.quality} (cùng hệ)`) : ""}. Phí ${check.cost} kim tiền.`
        : check.reason;
      detail.append(preview);
      actions.append(action("Xác nhận ghép", () => { const result = fusePets(main, partner); fuseFrom = null; tab = "bag"; selected = main.id; say(result.message); }, !check.ok, "danger"));
    }
    actions.append(action("Hủy ghép", () => { fuseFrom = null; selected = main.id; message.textContent = ""; render(); }));
    detail.append(actions);
  }
  function renderDetail(pet) {
    detail.replaceChildren();
    const main = fuseMain();
    if (main) { renderFusion(main, pet); return; }
    if (!pet) return;
    const head = document.createElement("div"), title = document.createElement("h3"), sub = document.createElement("p");
    head.className = "roster-head";
    title.textContent = pet.name;
    const cap = !pet.v5 ? STAT_BY_ID.get(pet.petId)?.max_quality_stars || pet.quality : null;
    const quality = pet.v5 ? `Hạng ${pet.quality}` : `Phẩm chất ${"★".repeat(pet.quality)}${"☆".repeat(Math.max(0, cap - pet.quality))} (${pet.quality}/${cap})`;
    sub.textContent = `${pet.kind === "lead" ? "Pet của Kael" : (pet.kind || "").toUpperCase()} · ${pet.v5 ? `${pet.element} → ${pet.unifiedElement}` : pet.element || "Chưa rõ hệ"} · ${quality}`;
    const info = document.createElement("div"); info.append(title, sub);
    head.append(portrait(pet), info);
    const stats = document.createElement("dl"); stats.className = "roster-stats";
    const { exp, need } = expOf(pet);
    for (const [k, v] of [["Cấp", pet.level], ["HP", pet.max], ["Công", pet.stats?.attack], ["Thủ", pet.stats?.defense], ["Tốc", pet.stats?.speed], ["EXP", need ? `${exp} / ${need}` : "Tối đa"]]) {
      const dt = document.createElement("dt"), dd = document.createElement("dd"); dt.textContent = k; dd.textContent = v ?? "—"; stats.append(dt, dd);
    }
    const skills = document.createElement("p"); skills.className = "roster-skills";
    skills.textContent = "Kỹ năng: " + ((pet.skills || []).map(skill => skill.name).filter(Boolean).join(", ") || "đòn thường");
    const item = document.createElement("p"); item.className = "roster-skills";
    item.textContent = pet.item ? `Trang bị: ${RELIC(pet.item)?.name || "vật phẩm"}` : "";
    const growth = document.createElement("p"); growth.className = "roster-skills";
    const next = pet.level < LEVEL_CAP ? statsPreview(pet, pet.level + 1) : null, now = statsPreview(pet, pet.level);
    if (next && now) growth.textContent = `Lên cấp ${pet.level + 1}: HP +${next.hp - now.hp}, Công +${next.attack - now.attack}, Thủ +${next.defense - now.defense}, Tốc +${next.speed - now.speed}`;
    detail.append(head, stats, skills, item, growth);

    const evo = evolution(pet), ready = evo.find(option => option.ok);
    const evoNote = document.createElement("p"); evoNote.className = "roster-evo";
    evoNote.textContent = ready ? `Có thể ${ready.verb.toLowerCase()}${ready.targetName ? " thành " + ready.targetName : ""}` : evo[0]?.reason ? `Tiến hóa: ${evo[0].reason}` : "";
    detail.append(evoNote);
    // Before / after at the current level for every form this pet can turn into.
    for (const option of evo.filter(o => o.targetId)) {
      const after = statsPreview(pet, pet.level, option.targetId), before = now;
      if (!after || !before) continue;
      const diff = (k, label) => `${label} ${after[k]}${after[k] !== before[k] ? ` (${after[k] > before[k] ? "+" : ""}${after[k] - before[k]})` : ""}`;
      const line = document.createElement("p"); line.className = "roster-compare";
      line.textContent = `${option.verb} → ${option.targetName}: ${diff("hp", "HP")}, ${diff("attack", "Công")}, ${diff("defense", "Thủ")}, ${diff("speed", "Tốc")}`;
      detail.append(line);
    }

    const actions = document.createElement("div"); actions.className = "roster-actions";
    const story = pet.id === "loi-mieu";
    if (tab === "bag") {
      const first = battleFirst() === pet.id;
      actions.append(action(first ? "Đang xuất chiến" : "Xuất chiến", () => { setBattleFirst(pet.id); say(`${pet.name} sẽ ra trận đầu tiên.`); }, first));
      actions.append(action("Gởi lại", () => deposit(pet), story));
    } else {
      actions.append(action("Lấy ra", () => withdraw(pet), team().length + 1 >= BAG_MAX));
    }
    for (const option of evo.filter(o => o.ok)) actions.append(action(option.verb, () => evolve(pet, option)));
    actions.append(action("Ghép", () => { fuseFrom = pet.id; selected = null; confirmRelease = null; message.textContent = ""; render(); }));
    if (confirmRelease === pet.id) {
      actions.append(action("Xác nhận phóng sinh", () => release(pet), false, "danger"), action("Hủy", () => { confirmRelease = null; render(); }));
    } else actions.append(action("Phóng sinh", () => { confirmRelease = pet.id; say("Bạn muốn phóng sinh sủng vật này? Bấm Xác nhận phóng sinh."); render(); }, story, "danger"));
    if (story) { const note = document.createElement("p"); note.className = "roster-note"; note.textContent = "Lôi Miêu là bạn đồng hành của Kael: luôn ở trong ba lô và không thể phóng sinh."; detail.append(actions, note); }
    else detail.append(actions);
  }

  function say(text) { message.textContent = text; render(); }
  function deposit(pet) {
    if (bankIds().length >= BANK_MAX) { say("Ngân hàng đã đầy, không thể gởi lại."); return; }
    // The backpack always keeps Lôi Miêu, so it never drops below one pet (source: "Ba lô phải lưu ít nhất 1 sủng vật").
    window.VQSVPetTeam.setTeam(team().filter(id => id !== pet.id));
    if (battleFirst() === pet.id) setBattleFirst("loi-mieu");
    selected = "loi-mieu"; say(`Đã gởi ${pet.name} vào ngân hàng.`);
  }
  function withdraw(pet) {
    if (team().length + 1 >= BAG_MAX) { say("Ba lô Sủng vật đã đủ."); return; }
    window.VQSVPetTeam.setTeam([...team(), pet.id]);
    tab = "bag"; selected = pet.id; say(`Đã lấy ${pet.name} ra ba lô.`);
  }
  function release(pet) {
    window.VQSVPetTeam.release(pet.id);
    delete progress[pet.id]; saveProgress();
    if (battleFirst() === pet.id) setBattleFirst("loi-mieu");
    confirmRelease = null; selected = tab === "bag" ? "loi-mieu" : null; say(`Đã phóng sinh ${pet.name}.`);
  }
  function evolve(pet, option) {
    const result = window.VQSVEvolution.evolve(option.key, pet.id === "loi-mieu" ? leadForm() : pet.id, option.mode);
    if (result?.ok && pet.id !== "loi-mieu") selected = result.newId || result.targetId || selected;
    say(result?.message || "Đã tiến hóa.");
  }

  function render() {
    const bag = bagPets(), bank = bankIds();
    byId("rosterBagTab").setAttribute("aria-pressed", String(tab === "bag"));
    byId("rosterBankTab").setAttribute("aria-pressed", String(tab === "bank"));
    byId("rosterBagTab").textContent = `Ba lô ${bag.length}/${BAG_MAX}`;
    byId("rosterBankTab").textContent = `Ngân hàng ${bank.length}/${BANK_MAX}`;
    byId("rosterCount").textContent = tab === "bag" ? `${bag.length}/${BAG_MAX}` : `${bank.length}/${BANK_MAX}`;
    byId("rosterTitle").textContent = fuseFrom ? "Ghép sủng vật" : "Sủng vật";
    const pets = current();
    if (!pets.some(pet => pet.id === selected)) selected = pets[0]?.id ?? null;
    renderList(pets);
    renderDetail(pets.find(pet => pet.id === selected));
  }
  function move(delta) {
    const pets = current(), index = pets.findIndex(pet => pet.id === selected);
    if (!pets.length) return;
    selected = pets[(index + delta + pets.length) % pets.length].id; confirmRelease = null; render();
    list.querySelector('[aria-selected="true"]')?.focus();
  }

  window.openPetRoster = () => {
    if (!assets.map || state.mode === "loading" || dialog.open) return;
    if (state.mode === "battle") { say("Đổi sủng vật trong trận ở lệnh Sủng vật."); return; }
    state.player.inputDir = null;
    tab = "bag"; selected = battleFirst(); confirmRelease = null; fuseFrom = null; message.textContent = "";
    dialog.showModal(); render();
    list.querySelector('[aria-selected="true"]')?.focus();
  };
  byId("openPetRoster").addEventListener("click", () => window.openPetRoster());
  byId("rosterBagTab").onclick = () => { fuseFrom = null; tab = "bag"; selected = null; confirmRelease = null; message.textContent = ""; render(); };
  byId("rosterBankTab").onclick = () => { fuseFrom = null; tab = "bank"; selected = null; confirmRelease = null; message.textContent = ""; render(); };
  byId("rosterBack").onclick = () => { if (fuseFrom) { selected = fuseFrom; fuseFrom = null; render(); } else dialog.close(); };
  byId("rosterChoose").onclick = () => detail.querySelector(".roster-actions button:enabled")?.click();
  dialog.addEventListener("close", () => updateGuidance());
  dialog.addEventListener("keydown", e => {
    const delta = { ArrowUp: -1, "2": -1, ArrowDown: 1, "8": 1 }[e.key];
    if (delta && !e.target.closest?.(".roster-actions")) { e.preventDefault(); move(delta); }
    else if (["ArrowLeft", "4", "ArrowRight", "6"].includes(e.key)) { e.preventDefault(); byId(tab === "bag" ? "rosterBankTab" : "rosterBagTab").click(); }
    else if (e.key === "5") { e.preventDefault(); byId("rosterChoose").click(); }
  });
})();
