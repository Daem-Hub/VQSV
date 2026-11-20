(() => {
  const byId = id => document.getElementById(id);
  const stick = byId("joystick");
  let pointer = null, direction = null;
  function stopStick() {
    if (direction) releaseMove(...direction);
    direction = null;
    pointer = null;
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
  stick.addEventListener("pointerdown", e => {
    if (pointer !== null || e.button !== 0 || state.mode !== "explore") return;
    e.preventDefault();
    pointer = e.pointerId;
    stick.setPointerCapture(pointer);
    steer(e);
  });
  stick.addEventListener("pointermove", steer);
  for (const event of ["pointerup","pointercancel","lostpointercapture"]) {
    stick.addEventListener(event, e => { if (pointer === e.pointerId) stopStick(); });
  }
  window.addEventListener("blur", stopStick);
  document.addEventListener("visibilitychange", () => { if (document.hidden) stopStick(); });
  byId("touchAction").addEventListener("click", () => {stopStick(); interact();});

  // A tap immediately after steering can lose its compatibility click on touch
  // browsers. Handle the release directly while keeping ordinary keyboard clicks.
  for (const button of document.querySelectorAll("main button")) {
    let finger = null;
    button.addEventListener("pointerdown", e => {
      if (e.pointerType !== "touch" || finger !== null || button.disabled) return;
      finger = e.pointerId;
      button.setPointerCapture(finger);
    });
    button.addEventListener("pointerup", e => {
      if (finger !== e.pointerId) return;
      finger = null;
      const over = document.elementFromPoint(e.clientX,e.clientY);
      if (over && button.contains(over)) button.click();
    });
    button.addEventListener("lostpointercapture", () => { finger = null; });
    button.addEventListener("click", e => {
      if (e.isTrusted && (e.pointerType === "touch" || e.sourceCapabilities?.firesTouchEvents)) {
        e.preventDefault();
        e.stopImmediatePropagation();
      }
    }, true);
  }

  const menu = byId("gameMenu");
  function pauseWalking() {
    stopStick();
    state.player.inputDir = null;
  }
  const grid = byId("menuGrid"), system = byId("systemPage");
  const tiles = [...grid.querySelectorAll("button")];
  let menuIndex = 0;
  function markMenu(index) {
    menuIndex = index;
    tiles.forEach((button, i) => button.setAttribute("aria-current", String(i === index)));
  }
  function systemPage(open) {
    grid.hidden = open;
    system.hidden = !open;
    byId("menuTitle").textContent = open ? "Hệ thống" : "Menu";
    byId("menuChoose").hidden = open;
    (open ? byId("saveGame") : tiles[menuIndex]).focus();
  }
  tiles.forEach((button, i) => {
    button.addEventListener("focus", () => markMenu(i));
    button.addEventListener("click", () => markMenu(i));
  });
  byId("openMenu").onclick = () => {
    if (!assets.map || menu.open) return;
    pauseWalking();
    updateWallet();
    menu.showModal();
    if (!system.hidden) systemPage(false);
    markMenu(menuIndex); tiles[menuIndex].focus();
  };
  byId("menuSystem").onclick = () => systemPage(true);
  byId("menuChoose").onclick = () => { tiles[menuIndex].focus(); tiles[menuIndex].click(); };
  function backMenu() { if (system.hidden) menu.close(); else systemPage(false); }
  byId("menuBack").onclick = backMenu;
  menu.addEventListener("cancel", e => { e.preventDefault(); backMenu(); });
  menu.addEventListener("keydown", e => {
    if (grid.hidden) return;
    const delta = {ArrowLeft:-1, "4":-1, ArrowRight:1, "6":1, ArrowUp:-3, "2":-3, ArrowDown:3, "8":3}[e.key];
    if (delta !== undefined) {
      e.preventDefault(); tiles[(menuIndex + delta + tiles.length) % tiles.length].focus();
    } else if (e.key === "5") { e.preventDefault(); if (!e.repeat) tiles[menuIndex].click(); }
  });
  byId("quickMap").onclick = () => {pauseWalking(); openMap();};
  byId("menuJournal").onclick = () => openQuestJournal();
  byId("menuBag").onclick = () => { menu.close(); openBag(); };
  for (const id of ["openPetBook","openSkillBook","openWorldAtlas","openShop","menuJournal"]) byId(id).addEventListener("click", () => menu.close(), true);
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
