// Battle skill effects drawn with pixelfx (https://github.com/patch-gg/pixelfx, MIT; vendored in vendor/pixelfx).
// The game calls window.VQSVSkillFx.cast(...) when a move is used and .status(...) when a buff or debuff lands;
// drawFx(ctx, now) paints the live effects over the battle scene. Effects are chosen by element (VQSV6) or type (VQSV5):
// the mapping is a prototype design choice, not source data.
import { createFrame, clearFrame, createFx, composite } from "./vendor/pixelfx/src/core/index.js";
import * as fireball from "./vendor/pixelfx/src/fx/fire/fireball.js";
import * as fireRing from "./vendor/pixelfx/src/fx/fire/fire-ring.js";
import * as hitSpark from "./vendor/pixelfx/src/fx/impacts/hit-spark.js";
import * as critHit from "./vendor/pixelfx/src/fx/impacts/crit-hit.js";
import * as shieldBlock from "./vendor/pixelfx/src/fx/impacts/shield-block.js";
import * as clawSwipe from "./vendor/pixelfx/src/fx/melee/claw-swipe.js";
import * as swordSlash from "./vendor/pixelfx/src/fx/melee/sword-slash.js";
import * as heavyCleave from "./vendor/pixelfx/src/fx/melee/heavy-cleave.js";
import * as doubleSlash from "./vendor/pixelfx/src/fx/melee/double-slash.js";
import * as uppercut from "./vendor/pixelfx/src/fx/melee/uppercut.js";
import * as vineWhip from "./vendor/pixelfx/src/fx/nature/vine-whip.js";
import * as earthSpike from "./vendor/pixelfx/src/fx/nature/earth-spike.js";
import * as tornado from "./vendor/pixelfx/src/fx/nature/tornado.js";
import * as rootSnare from "./vendor/pixelfx/src/fx/nature/root-snare.js";
import * as leafStorm from "./vendor/pixelfx/src/fx/nature/leaf-storm.js";
import * as splash from "./vendor/pixelfx/src/fx/water/splash.js";
import * as waterJet from "./vendor/pixelfx/src/fx/water/water-jet.js";
import * as waveCrash from "./vendor/pixelfx/src/fx/water/wave-crash.js";
import * as zapHit from "./vendor/pixelfx/src/fx/lightning/zap-hit.js";
import * as lightningStrike from "./vendor/pixelfx/src/fx/lightning/lightning-strike.js";
import * as chainLightning from "./vendor/pixelfx/src/fx/lightning/chain-lightning.js";
import * as curse from "./vendor/pixelfx/src/fx/shadow/curse.js";
import * as lifeSteal from "./vendor/pixelfx/src/fx/shadow/life-steal.js";
import * as graspingHands from "./vendor/pixelfx/src/fx/shadow/grasping-hands.js";
import * as energyDisc from "./vendor/pixelfx/src/fx/projectiles/energy-disc.js";
import * as plasmaShot from "./vendor/pixelfx/src/fx/projectiles/plasma-shot.js";
import * as iceLance from "./vendor/pixelfx/src/fx/ice/ice-lance.js";
import * as iceShatter from "./vendor/pixelfx/src/fx/ice/ice-shatter.js";
import * as heal from "./vendor/pixelfx/src/fx/holy/heal.js";
import * as divineShield from "./vendor/pixelfx/src/fx/holy/divine-shield.js";
import * as manaBurst from "./vendor/pixelfx/src/fx/arcane/mana-burst.js";
import * as poisoned from "./vendor/pixelfx/src/fx/status/poisoned.js";
import * as confused from "./vendor/pixelfx/src/fx/status/confused.js";
import * as slowed from "./vendor/pixelfx/src/fx/status/slowed.js";
import * as charmed from "./vendor/pixelfx/src/fx/status/charmed.js";
import * as stunned from "./vendor/pixelfx/src/fx/status/stunned.js";
import * as cursed from "./vendor/pixelfx/src/fx/status/cursed.js";
import * as haste from "./vendor/pixelfx/src/fx/status/haste.js";
import * as rage from "./vendor/pixelfx/src/fx/status/rage.js";
import * as petrified from "./vendor/pixelfx/src/fx/status/petrified.js";
import * as frozen from "./vendor/pixelfx/src/fx/status/frozen.js";
import * as bleeding from "./vendor/pixelfx/src/fx/status/bleeding.js";

const FX = { fireball, fireRing, hitSpark, critHit, shieldBlock, clawSwipe, swordSlash, heavyCleave, doubleSlash, uppercut,
  vineWhip, earthSpike, tornado, rootSnare, leafStorm, splash, waterJet, waveCrash, zapHit, lightningStrike, chainLightning, curse,
  lifeSteal, graspingHands, energyDisc, plasmaShot, iceLance, iceShatter, heal, divineShield, manaBurst, poisoned, confused, slowed, charmed, stunned, cursed,
  haste, rage, petrified, frozen, bleeding };

// The battle canvas is 480x360; effects are drawn on a 240x180 frame and scaled 2x so their pixels match the sprites.
const SCALE = 2, W = 240, H = 180;
// Where the two combatants stand, in frame pixels (feet) — the enemy on the left, the player's active pet on the right.
const SIDE = { foe: { x: 70, feet: 91 }, hero: { x: 175, feet: 92 } };
const BODY = 28;              // roughly how high a pet's torso sits above its feet

// Seven-step ramps (dark to bright) for the generic hit spark, per element.
const RAMP = {
  fire: ["#4a1712", "#c9281f", "#f2561d", "#ff8a2a", "#ffe26b", "#fff3a6", "#ffffff"],
  leaf: ["#102a14", "#1f5a2a", "#3a9a3a", "#7bd05a", "#c4f08a", "#eaffc0", "#ffffff"],
  earth: ["#2a1d10", "#5a3d1f", "#8a5f2f", "#c08a45", "#e8c070", "#f6e2a8", "#ffffff"],
  water: ["#0b2440", "#14548a", "#1f86c8", "#4cc0f0", "#9ae6ff", "#d4f6ff", "#ffffff"],
  volt: ["#26204a", "#5a4aa8", "#c0a020", "#ffe040", "#fff08a", "#fffbd0", "#ffffff"],
  dark: ["#12081f", "#3a1a5a", "#6a2a9a", "#a050d0", "#d090f0", "#f0d0ff", "#ffffff"],
  wind: ["#10302c", "#1f6a5a", "#3aa890", "#7ee0c0", "#c0fae0", "#e8fff4", "#ffffff"],
  steel: ["#1c2230", "#3c4a60", "#6a7a94", "#a0b0c8", "#d0dcec", "#f0f6ff", "#ffffff"],
  plain: ["#2a2a2a", "#6a5a4a", "#a08a6a", "#d0b890", "#f0e0b8", "#fff6dc", "#ffffff"],
};

// A step is { fx, at (ms after the cast starts), at: "target" | "caster", ms (cut-off), speed, opts, travel }. "travel" steps fly from caster to target.
const spark = (ramp, at = 0) => ({ fx: "hitSpark", on: "target", at, ms: 520, opts: { palette: RAMP[ramp] } });
const PLANS = {
  fire: [{ fx: "fireball", travel: 300, ms: 300, speed: 1 }, { fx: "fireRing", on: "target", at: 250, ms: 1000, speed: 2.6, opts: { reach: 34 } }, spark("fire", 300)],
  leaf: [{ fx: "vineWhip", on: "target", at: 0, ms: 1000, speed: 1.4 }, spark("leaf", 330)],
  earth: [{ fx: "earthSpike", on: "target", at: 0, ms: 1100, speed: 2.4 }, spark("earth", 250)],
  water: [{ fx: "waterJet", travel: 280, ms: 320, speed: 1 }, { fx: "splash", on: "target", at: 280, ms: 900, speed: 2.4 }, spark("water", 300)],
  volt: [{ fx: "chainLightning", on: "target", at: 0, ms: 700, speed: 1.4 }, { fx: "zapHit", on: "target", at: 180, ms: 600, speed: 1.4 }, spark("volt", 200)],
  dark: [{ fx: "curse", on: "target", at: 0, ms: 1100, speed: 2.4 }, { fx: "graspingHands", on: "target", at: 250, ms: 1100, speed: 2.4 }, spark("dark", 500)],
  wind: [{ fx: "tornado", on: "target", at: 0, ms: 900, speed: 1.6 }, { fx: "swordSlash", on: "target", at: 200, ms: 600, speed: 1.4, ox: 36, feet: true, opts: { palette: RAMP.wind } }, spark("wind", 260)],
  steel: [{ fx: "energyDisc", travel: 260, ms: 260, opts: { palette: RAMP.steel } }, { fx: "heavyCleave", on: "target", at: 260, ms: 700, speed: 1.6, ox: 55, feet: true }, spark("steel", 320)],
  beast: [{ fx: "clawSwipe", on: "target", at: 0, ms: 700, speed: 1.3, ox: 34, feet: true }, spark("plain", 150)],
  sea: [{ fx: "waveCrash", on: "target", at: 0, ms: 1000, speed: 2.2 }, spark("water", 400)],
  plain: [{ fx: "swordSlash", on: "target", at: 0, ms: 500, speed: 1.4, ox: 36, feet: true, opts: { palette: RAMP.plain } }, spark("plain", 100)],
};
const ELEMENT_PLAN = { "Hỏa hệ": "fire", "Mộc hệ": "leaf", "Thổ hệ": "earth", "Thủy hệ": "water", "Điện hệ": "volt", "Quỷ hệ": "dark", "Phong hệ": "wind",
  "Thực Vật hệ": "leaf", "Kim Loại hệ": "steel", "Phi Hành hệ": "wind", "Mãnh Thú hệ": "beast", "Hải Dương hệ": "sea" };
const planKey = element => ELEMENT_PLAN[element] || "plain";

// Buff / debuff ids are the VQSV6 table ids (data/vqsv6_pet_stats.json status_effects).
const BUFF_FX = { 3: { fx: "heal", ms: 1100, speed: 1.6 }, 4: { fx: "divineShield", ms: 900 }, 6: { fx: "divineShield", ms: 900 }, 10: { fx: "rage", ms: 1000 }, 12: { fx: "haste", ms: 900 }, 13: { fx: "heal", ms: 1100, speed: 1.6 }, 7: { fx: "manaBurst", ms: 800, speed: 1.4 } };
const DEBUFF_FX = { 0: { fx: "poisoned", ms: 1000 }, 1: { fx: "confused", ms: 1100 }, 2: { fx: "rootSnare", ms: 1000, speed: 2 }, 3: { fx: "poisoned", ms: 1000 }, 4: { fx: "confused", ms: 1100 },
  5: { fx: "slowed", ms: 1100 }, 6: { fx: "bleeding", ms: 1000 }, 7: { fx: "cursed", ms: 1100 }, 8: { fx: "cursed", ms: 1100 }, 9: { fx: "confused", ms: 1100 }, 10: { fx: "stunned", ms: 1200 } };

// Effects that wrap the character (status auras, heals) read its silhouette: build one from the pet's sprite, drawn the way
// the battle draws it (integer scale, bottom-anchored in a box) and halved to frame pixels.
const actors = new WeakMap();
function actorFromImage(image, box) {
  if (!image?.complete || !image.naturalWidth) return null;
  const key = `${box[0]}x${box[1]}`;
  let byBox = actors.get(image);
  if (!byBox) actors.set(image, (byBox = new Map()));
  if (byBox.has(key)) return byBox.get(key);
  const scale = Math.max(1, Math.floor(Math.min(box[0] / image.naturalWidth, box[1] / image.naturalHeight))) || 1;
  const w = Math.max(8, Math.round(image.naturalWidth * scale / SCALE)), h = Math.max(8, Math.round(image.naturalHeight * scale / SCALE));
  let actor = null;
  try {
    const c = document.createElement("canvas"); c.width = w; c.height = h;
    const g = c.getContext("2d", { willReadFrequently: true }); g.imageSmoothingEnabled = false; g.drawImage(image, 0, 0, w, h);
    const px = g.getImageData(0, 0, w, h).data, mask = new Uint8Array(w * h), pixels = new Uint32Array(w * h);
    for (let i = 0; i < w * h; i++) if (px[i * 4 + 3] > 80) { mask[i] = 1; pixels[i] = (255 << 24) | (px[i * 4 + 2] << 16) | (px[i * 4 + 1] << 8) | px[i * 4]; }
    const x = -Math.floor(w / 2), y = -h;
    actor = { mask: { w, h, data: mask }, pixels, x, y, w, h, k: 2, head: [0, y], chest: [0, y + Math.round(h * .45)], eyes: [[Math.round(w * .45), Math.round(h * .2)], [Math.round(w * .55), Math.round(h * .2)]] };
  } catch { actor = null; }              // a tainted canvas (file://) falls back to the stock stand-in
  byBox.set(key, actor);
  return actor;
}

// VQSV5 statuses (status_rules): Thạch Hóa, Tê Liệt, Mị Hoặc, Giam Cầm, Hủ Thực, Phong Bế.
const V5_FX = { 0: { fx: "petrified", ms: 1300 }, 2: { fx: "stunned", ms: 1200 }, 3: { fx: "charmed", ms: 1200 }, 4: { fx: "rootSnare", ms: 1000, speed: 2 }, 5: { fx: "poisoned", ms: 1000 }, 6: { fx: "cursed", ms: 1100 } };

const frame = createFrame(W, H);
let canvas = null, image = null;
const live = [];
const sidePos = (side, atFeet = false) => ({ x: SIDE[side].x, y: SIDE[side].feet - (atFeet ? 0 : BODY) });

function add(step, from, to, base, speedUp = 1) {
  const target = step.on === "caster" ? from : to;
  const mod = FX[step.fx];
  if (!mod) return;
  const flip = to.x < from.x;                         // the foe is on the left: effects that face right are mirrored for the hero
  const entry = { mod, start: base + (step.at || 0), ms: step.ms || 800, speed: (step.speed || 1) * speedUp, flip, step, from, to, fx: null, last: null };
  const pos = step.travel ? from : target;
  // "ox": slide the origin toward the attacker so a melee mark lands on the target; "feet": stand on the target's feet.
  const offset = step.ox ? (flip ? 1 : -1) * step.ox : 0, feetY = step.feet ? (step.on === "caster" ? from : to).y + BODY : pos.y;
  entry.opts = { x: pos.x + offset, y: step.feet ? feetY : pos.y, flip, seed: Math.floor(Math.random() * 1e6), ...(step.opts || {}) };
  if (step.fx === "hitSpark" || step.fx === "critHit") entry.opts.height = BODY - 8;
  live.push(entry);
}

function cast({ fromEnemy, element, startAt, crit = false }) {
  const from = sidePos(fromEnemy ? "foe" : "hero"), to = sidePos(fromEnemy ? "hero" : "foe");
  const feetTo = sidePos(fromEnemy ? "hero" : "foe", true);
  // Ground effects stand on the target's feet; spark and strike effects hit its body.
  for (const step of PLANS[planKey(element)]) {
    const ground = ["earthSpike", "vineWhip", "splash", "waveCrash", "tornado", "rootSnare", "fireRing", "graspingHands"].includes(step.fx);
    add(step, from, ground && step.on === "target" ? feetTo : to, startAt);
  }
  if (crit) add({ fx: "critHit", on: "target", at: (PLANS[planKey(element)].find(step => step.travel)?.travel || 120) - 20, ms: 700 }, from, to, startAt);
  if (!live.length) return;
}
function basic({ fromEnemy, startAt, element }) { cast({ fromEnemy, element: element || null, startAt }); }
function status({ onEnemy, kind, id, startAt, image = null, box = [114, 112] }) {
  const row = (kind === "buff" ? BUFF_FX : kind === "v5" ? V5_FX : DEBUFF_FX)[id];
  if (!row) return;
  const at = sidePos(onEnemy ? "foe" : "hero", true);
  const actor = actorFromImage(image, box);
  add({ ...row, on: "target", opts: actor ? { actor } : undefined }, at, at, startAt);
}

function draw(ctx, now) {
  if (!live.length) return;
  clearFrame(frame);
  let drew = false;
  for (let i = live.length - 1; i >= 0; i--) {
    const e = live[i];
    if (now < e.start) continue;
    if (!e.fx) { e.fx = createFx(e.mod, e.opts); e.last = e.start; }
    const dt = Math.min(0.05, (now - e.last) / 1000) * e.speed; e.last = now;
    if (e.step.travel) {
      const k = Math.min(1, (now - e.start) / e.step.travel);
      e.fx.options.x = e.from.x + (e.to.x - e.from.x) * k; e.fx.options.y = e.from.y + (e.to.y - e.from.y) * k;
      if (k >= 1) e.fx.stop();
    }
    if (now - e.start > e.ms) e.fx.stop();
    e.fx.update(dt);
    if (e.fx.done()) { live.splice(i, 1); continue; }
    e.fx.draw(frame, "back"); e.fx.draw(frame, "front"); drew = true;
  }
  if (!drew) return;
  if (!canvas) { canvas = document.createElement("canvas"); canvas.width = W; canvas.height = H; image = new ImageData(W, H); }
  composite(frame, image.data, null);
  canvas.getContext("2d").putImageData(image, 0, 0);
  const smoothing = ctx.imageSmoothingEnabled;
  ctx.imageSmoothingEnabled = false;
  ctx.drawImage(canvas, 0, 0, W * SCALE, H * SCALE);
  ctx.imageSmoothingEnabled = smoothing;
}

window.VQSVSkillFx = { cast, basic, status, draw, clear: () => { live.length = 0; }, busy: () => live.length > 0, plans: Object.keys(PLANS), planOf: planKey, elements: ELEMENT_PLAN, buffFx: BUFF_FX, debuffFx: DEBUFF_FX, v5Fx: V5_FX };
window.dispatchEvent?.(new Event("vqsv-skillfx-ready"));
