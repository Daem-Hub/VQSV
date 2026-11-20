(() => {
  const byId = id => document.getElementById(id);
  const stick = byId("joystick");
  let pointer = null, direction = null;
  function stopStick() {
    if (direction) releaseMove(...direction);
    direction = null;
    pointer = null;
    stick.classList.remove("floating");
    stick.style.removeProperty("--dx");
    stick.style.removeProperty("--dy");
  }
  function steer(e) {
    if (e.pointerId !== pointer) return;
    if (document.querySelector("dialog[open]")) { stopStick(); return; }
    const box = stick.getBoundingClientRect();
    const limit = box.width * .32;
    const dx = e.clientX - box.left - box.width / 2;
    const dy = e.clientY - box.top - box.height / 2;
    const distance = Math.hypot(dx, dy);
    const scale = Math.min(1, limit / (distance || 1));
    stick.style.setProperty("--dx", dx * scale + "px");
    stick.style.setProperty("--dy", dy * scale + "px");
    const next = distance < limit * .25 ? null : Math.abs(dx) > Math.abs(dy) ? [Math.sign(dx),0] : [0,Math.sign(dy)];
    if (next?.toString() === direction?.toString()) return;
    if (direction) releaseMove(...direction);
    direction = next;
    if (next) setMove(...next);
  }
  function startStick(e) {
    if (pointer !== null || e.button !== 0 || state.mode !== "explore") return;
    e.preventDefault();
    if (e.currentTarget === byId("stickZone")) {
      const box = byId("touchControls").getBoundingClientRect();
      stick.style.setProperty("--stick-x", (e.clientX-box.left)+"px");
      stick.style.setProperty("--stick-y", (e.clientY-box.top)+"px");
      stick.classList.add("floating");
    }
    pointer = e.pointerId;
    stick.setPointerCapture(pointer);
    steer(e);
  }
  stick.addEventListener("pointerdown", startStick);
  byId("stickZone").addEventListener("pointerdown", startStick);
  stick.addEventListener("pointermove", steer);
  for (const event of ["pointerup","pointercancel","lostpointercapture"]) {
    stick.addEventListener(event, e => { if (pointer === e.pointerId) stopStick(); });
  }
  window.addEventListener("blur", stopStick);
  document.addEventListener("visibilitychange", () => { if (document.hidden) stopStick(); });
  byId("touchAction").addEventListener("click", () => {stopStick(); interact();});

  // A browser can suppress the compatibility click immediately after a drag.
  // Confirm a stationary touch on release and cancel its compatibility click.
  // Delegate so dynamically created battle items follow the same rule.
  const taps = new Map();
  document.addEventListener("touchstart", e => {
    const button = e.target.closest?.("main button:enabled");
    if (!button) return;
    for (const touch of e.changedTouches) {
      taps.set(touch.identifier,{button,x:touch.clientX,y:touch.clientY});
    }
  }, {passive:true});
  document.addEventListener("touchmove", e => {
    for (const touch of e.changedTouches) {
      const tap = taps.get(touch.identifier);
      if (tap && Math.hypot(touch.clientX-tap.x,touch.clientY-tap.y)>12) taps.delete(touch.identifier);
    }
  }, {passive:true});
  document.addEventListener("touchcancel", e => {
    for (const touch of e.changedTouches) taps.delete(touch.identifier);
  });
  document.addEventListener("touchend", e => {
    for (const touch of e.changedTouches) {
      const tap = taps.get(touch.identifier);
      taps.delete(touch.identifier);
      if (!tap || !e.cancelable || tap.button.disabled) continue;
      if (!tap.button.contains(document.elementFromPoint(touch.clientX,touch.clientY))) continue;
      e.preventDefault();
      tap.button.click();
    }
  }, {passive:false});

  const menu = byId("gameMenu");
  function pauseWalking() {
    stopStick();
    state.player.inputDir = null;
  }
  const home = byId("mainMenuPage"), system = byId("systemPage"), record = byId("recordPage");
  let page = home, selected;
  function choices() { return [...page.querySelectorAll("button:not(:disabled),input,summary")]; }
  function mark(control) {
    selected?.removeAttribute("aria-current");
    selected = control;
    selected?.setAttribute("aria-current","true");
  }
  function showPage(next, initial) {
    page = next;
    for (const part of [home,system,record]) part.hidden = part !== next;
    byId("menuTitle").textContent = next === home ? "Menu trò chơi" : next === system ? "Hệ thống" : "Lưu dữ liệu";
    const first = initial || choices()[0];
    mark(first); first?.focus();
  }
  menu.addEventListener("focusin", e => { if (choices().includes(e.target)) mark(e.target); });
  byId("openMenu").onclick = () => {
    if (!assets.map || menu.open) return;
    pauseWalking(); updateWallet(); menu.showModal(); showPage(home);
  };
  byId("menuSystem").onclick = () => showPage(system);
  byId("menuRecord").onclick = () => showPage(record);
  function choose() { if (selected?.type === "range") selected.focus(); else selected?.click(); }
  byId("menuChoose").onclick = choose;
  function backMenu() { if (page === home) menu.close(); else showPage(home, byId(page === system ? "menuSystem" : "menuRecord")); }
  byId("menuBack").onclick = backMenu;
  menu.addEventListener("cancel", e => { e.preventDefault(); backMenu(); });
  menu.addEventListener("keydown", e => {
    if (e.target.type === "range" && ["ArrowLeft","ArrowRight"].includes(e.key)) return;
    const delta = {ArrowLeft:-1,"4":-1,ArrowRight:1,"6":1,ArrowUp:-1,"2":-1,ArrowDown:1,"8":1}[e.key];
    if (delta !== undefined) {
      e.preventDefault(); e.stopPropagation(); const options = choices();
      options[(options.indexOf(selected)+delta+options.length)%options.length].focus();
    } else if (e.key === "5" || (e.key === "Enter" && !e.target.closest(".softkeys"))) {
      e.preventDefault(); e.stopPropagation(); if (!e.repeat) choose();
    }
  });
  byId("menuDex").onclick = () => { menu.close(); window.openPetBook("caught"); };
  byId("quickMap").onclick = () => {pauseWalking(); openMap();};
  byId("menuJournal").onclick = () => openQuestJournal();
  byId("menuBag").onclick = () => { menu.close(); openBag(); };
  for (const id of ["openPetRoster","openSkillBook","openWorldAtlas","openShop","menuJournal"]) byId(id).addEventListener("click", () => menu.close(), true);
  byId("playAgain").onclick = () => reset();
  byId("restart").addEventListener("click", () => menu.close());
  const full = byId("fullscreen");
  full.hidden = !document.fullscreenEnabled;
  full.onclick = async () => {
    try {
      if (document.fullscreenElement) await document.exitFullscreen();
      else await document.documentElement.requestFullscreen();
    } catch { byId("status").textContent = "Trình duyệt chưa cho phép bật toàn màn hình."; }
  };
  document.addEventListener("fullscreenchange", () => {
    const label = document.fullscreenElement ? "Thoát toàn màn hình" : "Toàn màn hình";
    full.setAttribute("aria-label",label); full.title = label;
  });

  window.syncScene = () => {
    byId("menuLocation").textContent = assets.map.title;
    document.body.dataset.mode = state.mode;
    byId("playAgain").hidden = !["ending","defeat"].includes(state.mode);
  };
  // CSS crops the 4:3 canvas to the viewport. Pan that crop toward Kael's position on screen
  // (the engine's camera already scrolls maps larger than the canvas).
  let lastPosition;
  window.followKael = () => {
    if (!["dialogue","explore"].includes(state.mode)) return;
    const p = state.player, cam = cameraOf(assets.map);
    const position = Math.max(0, Math.min(100, ((p.visualX+.5)*S - cam.x) / 480 * 100)).toFixed(2) + "% " +
      Math.max(0, Math.min(100, ((p.visualY+.25)*S - cam.y) / 360 * 100)).toFixed(2) + "%";
    if (position !== lastPosition) { canvas.style.objectPosition = position; lastPosition = position; }
  };
})();
