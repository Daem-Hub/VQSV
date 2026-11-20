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

  const menu = byId("gameMenu"), trade = byId("trade");
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
    byId("menuCoins").textContent = state.bag.coins + " xu";
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
  byId("menuBag").onclick = () => window.showGoods();
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

  const goods = {
    apothecary: {name:"Thuốc hồi phục", price:8, icon:"potion", text:"Hồi tối đa 30 HP cho Lôi Miêu khi dùng Đạo cụ trong trận."},
    charms: {name:"Bùa lá rừng", price:24, icon:"charm", text:"Tự trang bị cho Lôi Miêu, giảm 2 sát thương mỗi lần trúng đòn."}
  };
  const battleShop = {name:"Tiếp tế trong trận"};
  let seller, selected, selling, ordering, quantity;
  const owned = key => key === "charms" ? Number(state.bag.charm) : state.bag.tonics;
  const unitPrice = (key = selected) => goods[key].price / (selling ? 2 : 1);
  function limit() {
    const bag = state.bag, price = unitPrice(), count = owned(selected);
    return selling ? Math.min(count, Math.floor((Number.MAX_SAFE_INTEGER - bag.coins) / price))
      : Math.min(Math.floor(bag.coins / price), (selected === "charms" ? 1 : Number.MAX_SAFE_INTEGER) - count);
  }
  function updateTrade() {
    const item = goods[selected], available = limit();
    byId("funds").textContent = state.bag.coins + " xu";
    byId("tradeTabs").hidden = !seller || seller === battleShop || ordering;
    byId("tradeBuy").setAttribute("aria-pressed", String(!selling));
    byId("tradeSell").setAttribute("aria-pressed", String(selling));
    for (const row of byId("stock").children) row.setAttribute("aria-current", String(row.dataset.key === selected));
    const desc = document.createElement("p"), count = document.createElement("small");
    desc.textContent = item.text;
    count.textContent = "Trong túi: " + owned(selected) + (selected === "charms" && state.bag.charm ? " · Đang trang bị" : "");
    byId("itemDetail").replaceChildren(desc, count);
    byId("quantityBox").hidden = !ordering;
    byId("quantity").textContent = quantity;
    byId("lessGoods").disabled = quantity <= 1;
    byId("moreGoods").disabled = quantity >= available;
    byId("tradeTotal").hidden = !ordering;
    byId("tradeTotal").textContent = (selling ? "Nhận được: " : "Cần trả: ") + (quantity * unitPrice()) + " xu";
    const confirm = byId("tradeConfirm");
    confirm.hidden = !seller;
    confirm.disabled = available <= 0;
    confirm.querySelector("span").textContent = available <= 0
      ? (selling ? "Hết hàng để bán" : selected === "charms" && state.bag.charm ? "Đã trang bị" : "Không đủ xu")
      : ordering ? (selling ? "Xác nhận bán" : "Xác nhận mua") : "Chọn";
  }
  function beginOrder() {
    if (!seller || !limit() || ordering) return;
    quantity = 1; ordering = true;
    byId("receipt").textContent = "";
    updateTrade(); byId("tradeConfirm").focus();
  }
  function listGoods() {
    const stock = byId("stock");
    stock.replaceChildren();
    for (const [key,item] of Object.entries(goods)) {
      if (seller?.vendor && key !== seller.vendor) continue;
      const row = document.createElement("button"), icon = document.createElement("i"), price = document.createElement("small");
      row.type = "button"; row.dataset.key = key;
      icon.className = "v5-icon " + item.icon; icon.ariaHidden = "true";
      price.textContent = seller ? unitPrice(key) + " xu" : "× " + owned(key);
      row.append(icon, document.createTextNode(item.name), price);
      row.onclick = () => { selected = key; updateTrade(); if (seller) beginOrder(); };
      stock.append(row);
    }
    updateTrade();
  }
  function tradeMode(sell) {
    selling = sell; ordering = false; quantity = 1;
    byId("receipt").textContent = "";
    listGoods();
  }
  byId("tradeBuy").onclick = () => tradeMode(false);
  byId("tradeSell").onclick = () => tradeMode(true);
  function adjust(delta) {
    quantity = Math.max(1, Math.min(limit(), quantity + delta));
    updateTrade();
  }
  byId("lessGoods").onclick = () => adjust(-1);
  byId("moreGoods").onclick = () => adjust(1);
  byId("tradeConfirm").onclick = () => {
    if (!ordering) { beginOrder(); return; }
    const canTrade = seller === battleShop ? state.mode === "battle" && !state.battle.pendingUntil && !selling : nearbyNpc()?.vendor === selected;
    if (!trade.open || !canTrade || quantity < 1 || quantity > limit()) return;
    const amount = quantity * (selling ? -1 : 1);
    state.bag.coins -= amount * unitPrice();
    if (selected === "apothecary") state.bag.tonics += amount;
    else state.bag.charm = !selling;
    byId("receipt").textContent = (selling ? "Đã bán " : "Đã mua ") + quantity + " " + goods[selected].name.toLocaleLowerCase("vi") + ".";
    ordering = false; quantity = 1;
    byId("menuCoins").textContent = state.bag.coins + " xu";
    listGoods(); byId("stock").firstElementChild.focus();
  };
  function backTrade() {
    if (!ordering) { trade.close(); return; }
    ordering = false; quantity = 1; updateTrade(); byId("stock").firstElementChild.focus();
  }
  byId("tradeBack").onclick = backTrade;
  trade.addEventListener("cancel", e => { e.preventDefault(); backTrade(); });
  trade.addEventListener("keydown", e => {
    if (ordering && ["4","6","ArrowLeft","ArrowRight"].includes(e.key)) {
      e.preventDefault(); adjust(["4","ArrowLeft"].includes(e.key) ? -1 : 1);
    } else if (e.key === "5" && seller) {
      e.preventDefault(); if (!e.repeat) byId("tradeConfirm").click();
    }
  });
  window.showGoods = npc => {
    if (!assets.map || trade.open) return;
    pauseWalking(); seller = npc || null;
    selected = seller?.vendor || "apothecary";
    byId("tradePerson").textContent = seller ? seller.name : "Kael";
    byId("tradeTitle").textContent = seller === battleShop ? "Cửa hàng" : seller ? assets.map.title : "Hành trang";
    tradeMode(false); trade.showModal(); byId("stock").firstElementChild.focus();
  };
  byId("openBag").onclick = () => window.showGoods();
  byId("shopButton").onclick = () => {
    if (state.mode === "battle" && !state.battle.pendingUntil) window.showGoods(battleShop);
  };

  window.syncScene = () => {
    byId("menuLocation").textContent = assets.map.title;
    byId("menuCoins").textContent = state.bag.coins + " xu";
    document.body.dataset.mode = state.mode;
    const speaking = state.mode === "dialogue";
    byId("speech").hidden = !speaking;
    if (speaking) {
      byId("speaker").textContent = state.activeNpc.name;
      byId("line").textContent = state.activeNpc.dialogue[state.dialogue];
    }
    byId("playAgain").hidden = !["ending","defeat"].includes(state.mode);
  };
  // CSS crops the 4:3 map to the viewport. Pan that crop with the player.
  let lastPosition;
  window.followKael = () => {
    if (!["dialogue","explore"].includes(state.mode)) return;
    const p = state.player;
    const position = ((p.visualX+.5)/assets.map.width*100).toFixed(2) + "% " +
      ((p.visualY+.25)/assets.map.height*100).toFixed(2) + "%";
    if (position !== lastPosition) { canvas.style.objectPosition = position; lastPosition = position; }
  };
})();
