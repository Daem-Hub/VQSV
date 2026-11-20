// VQSV5's original combat screen anchors the opponents at (177,97) and (46,206).
(() => {
  const byId = id => document.getElementById(id);
  const screen = byId("battleControls"), commands = byId("duelCommands");
  const skills = byId("fightMenu"), items = byId("itemMenu");
  const enemy = byId("foeCanvas").getContext("2d"), hero = byId("heroCanvas").getContext("2d");
  const reducedMotion = window.matchMedia("(prefers-reduced-motion: reduce)");
  let group = null, active = null, rootChoice = null;
  const hints = {
    attackButton:"Điện giật gây 16 sát thương.", basicButton:"Vồ nhanh gây 10 sát thương.",
    guardButton:"Giảm sát thương từ đòn phản công kế tiếp.",
    potionButton:"Dùng một liều thuốc để hồi HP cho Lôi Miêu.", captureButton:"Thử thu phục sủng vật đối diện."
  };

  const resize = new ResizeObserver(entries => {
    for (const {target, contentRect:rect} of entries) {
      if (!rect.width || !rect.height) continue;
      const width = Math.min(480, Math.round(rect.width));
      target.width = width;
      target.height = Math.max(1, Math.round(rect.height * width / rect.width));
      target.getContext("2d").imageSmoothingEnabled = false;
    }
  });
  resize.observe(enemy.canvas);
  resize.observe(hero.canvas);

  function paintPet(ctx, sprite, fraction, direction, lunge, shake, bob) {
    const {width, height} = ctx.canvas;
    ctx.clearRect(0,0,width,height);
    const w = Math.round(Math.min(width*.34, height*.73 * sprite.width/sprite.height));
    const h = Math.round(w * sprite.height/sprite.width);
    const x = Math.round(width*fraction + direction*w*.22*lunge + shake);
    const y = Math.round(height*.80);
    ctx.fillStyle = "#26381e65";
    ctx.beginPath(); ctx.ellipse(x,y,w*.31,Math.max(2,h*.055),0,0,Math.PI*2); ctx.fill();
    ctx.save();
    ctx.translate(x, Math.round(y + bob - lunge*h*.06));
    ctx.scale(-1,1);
    ctx.drawImage(sprite, -Math.round(w/2), -h, w, h);
    ctx.restore();
    return {x,y,w,h};
  }

  window.drawDuelSprites = (art, battle, now) => {
    const attack = now - battle.attackAt, counter = now - battle.enemyAt;
    const hit = !reducedMotion.matches && attack >= 0 && attack < 360 ? Math.sin(Math.PI*attack/360) : 0;
    const reply = !reducedMotion.matches && counter >= 0 && counter < 300 ? Math.sin(Math.PI*counter/300) : 0;
    const idle = reducedMotion.matches ? 0 : Math.sin(now/265)*1.2;
    const foe = paintPet(enemy,art.pets.enemy,177/240,-1,reply,Math.sin(attack/24)*hit*3,idle);
    const cat = paintPet(hero,art.pets.hero,46/240,1,hit,Math.sin(counter/24)*reply*3,-idle);
    if (attack >= 0 && attack < 480 && battle.skill === "spark" && art.skillEffects.length) {
      let remaining = attack, frame = art.skillEffects[art.skillEffects.length-1];
      for (const candidate of art.skillEffects) {
        const duration = candidate.duration_ticks*60;
        if (remaining < duration) { frame = candidate; break; }
        remaining -= duration;
      }
      const image = frame.image, scale = foe.h / Math.max(image.width,image.height);
      const w = Math.round(image.width*scale), h = Math.round(image.height*scale);
      enemy.drawImage(image, Math.round(foe.x-w/2), Math.round(foe.y-foe.h*.48-h/2), w, h);
    } else if (battle.skill === "basic" && attack >= 0 && attack < 280) {
      enemy.fillStyle = "#fff1a9";
      for (let i=0;i<3;i++) {
        const x = Math.round(foe.x + (i-1)*foe.w*.12), y = Math.round(foe.y-foe.h*.61);
        for (let step=0;step<5;step++) enemy.fillRect(x+step*2, y+step*3, 3, 5);
      }
    }
    const guard = now-battle.guardAt;
    if (guard >= 0 && guard < 500) {
      hero.strokeStyle = "#9efaff"; hero.lineWidth = 2;
      hero.beginPath(); hero.ellipse(cat.x,cat.y-cat.h*.49,cat.w*.53,cat.h*.54,0,0,Math.PI*2); hero.stroke();
    }
  };

  function vital(side, hp, max) {
    const percent = Math.round(100*hp/max), value = byId(side+"Percent");
    byId(side+"HpBar").style.width = percent+"%";
    byId(side+"HpText").textContent = hp+" / "+max;
    if (value.getAttribute("aria-label") === percent+"%") return;
    value.setAttribute("role","img"); value.setAttribute("aria-label",percent+"%");
    value.replaceChildren();
    for (const char of String(percent)+"%") {
      const digit = document.createElement("i"); digit.className = "duel-digit";
      digit.style.setProperty("--digit",char === "%" ? 10 : Number(char));
      value.append(digit);
    }
  }

  function message() {
    const text = group !== commands && active ? hints[active.id] : state.battle.message;
    if (byId("duelMessage").textContent !== text) byId("duelMessage").textContent = text;
  }
  function select(button, focus) {
    if (!button) return;
    active?.removeAttribute("data-selected");
    active = button;
    button.setAttribute("data-selected","");
    if (group === commands) rootChoice = button;
    if (focus) button.focus({preventScroll:true});
    message();
  }
  for (const list of [commands,skills,items]) {
    list.addEventListener("focusin", event => {
      if (list === group && event.target.matches("button")) select(event.target,false);
    });
  }
  window.syncBattleScreen = () => {
    if (state.mode !== "battle") {
      if (group) resizeGame();
      active?.removeAttribute("data-selected");
      active = rootChoice = group = null;
      return;
    }
    const b = state.battle, next = !skills.hidden ? skills : !items.hidden ? items : commands;
    const pending = !!b.pendingUntil;
    vital("hero",b.heroHp,60); vital("foe",b.enemyHp,48);
    byId("heroPpBar").style.width = (100*b.sparkUses/SKILLS.spark.uses)+"%";
    byId("duelTurn").textContent = "Lượt "+(b.turn+1);
    byId("duelPotionCount").textContent = "× "+(state.bag.tonics+b.potions);
    byId("duelBackLabel").textContent = next === commands ? "Menu" : "Trở lại";
    byId("fightButton").setAttribute("aria-expanded",String(next === skills));
    byId("itemButton").setAttribute("aria-expanded",String(next === items));
    screen.toggleAttribute("data-picker", next !== commands);
    for (const button of commands.children) button.disabled = pending;
    byId("duelChoose").disabled = pending;
    byId("duelBack").disabled = pending;
    byId("duelFull").hidden = !document.fullscreenEnabled;
    if (group !== next || !active || active.disabled && !pending) {
      group = next;
      const choice = next === commands ? rootChoice || byId("fightButton") : next.querySelector("button:enabled");
      select(choice,!pending && !document.querySelector("dialog[open]"));
    }
    message();
  };

  function back() {
    if (state.battle.pendingUntil) return;
    if (group === commands) { byId("openMenu").click(); return; }
    closeBattleLists(); window.syncBattleScreen();
  }
  byId("duelChoose").onclick = () => active?.click();
  byId("duelBack").onclick = back;
  byId("duelFull").onclick = () => byId("fullscreen").click();
  window.navigateDuel = e => {
    const key = e.key.toLowerCase();
    if (!group || e.altKey || e.ctrlKey || e.metaKey) return false;
    if (key === "escape") { e.preventDefault(); if (!e.repeat) back(); return true; }
    const backwards = ["arrowup","arrowleft","4"].includes(key);
    if (backwards || ["arrowdown","arrowright","6","8"].includes(key)) {
      e.preventDefault();
      const choices = [...group.querySelectorAll("button:enabled")];
      if (choices.length) select(choices[(choices.indexOf(active)+(backwards?-1:1)+choices.length)%choices.length],true);
      return true;
    }
    if (!["5","enter"," "].includes(key)) return false;
    if ((key === "enter" || key === " ") && e.target.closest?.(".duel-softkeys")) return false;
    e.preventDefault(); if (!e.repeat && !state.battle.pendingUntil) active?.click(); return true;
  };
})();
