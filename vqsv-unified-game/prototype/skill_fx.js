// Battle skill effects drawn with pixelfx (https://github.com/patch-gg/pixelfx, MIT; vendored in vendor/pixelfx).
// The game calls window.VQSVSkillFx.cast(...) when a move is used and .status(...) when a buff or debuff lands;
// drawFx(ctx, now) paints the live effects over the battle scene. Effects are chosen by element (VQSV6) or type (VQSV5):
// the mapping is a prototype design choice, not source data.
import { createFrame, clearFrame, createFx, composite } from "./vendor/pixelfx/src/core/index.js";
import * as fireball from "./vendor/pixelfx/src/fx/fire/fireball.js";
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
import * as stunned from "./vendor/pixelfx/src/fx/status/stunned.js";
import * as cursed from "./vendor/pixelfx/src/fx/status/cursed.js";
import * as haste from "./vendor/pixelfx/src/fx/status/haste.js";
import * as rage from "./vendor/pixelfx/src/fx/status/rage.js";
import * as petrified from "./vendor/pixelfx/src/fx/status/petrified.js";
import * as frozen from "./vendor/pixelfx/src/fx/status/frozen.js";
import * as bleeding from "./vendor/pixelfx/src/fx/status/bleeding.js";
import * as fireRing from "./vendor/pixelfx/src/fx/fire/fire-ring.js";
import * as charmed from "./vendor/pixelfx/src/fx/status/charmed.js";
import * as spellCharge from "./vendor/pixelfx/src/fx/arcane/spell-charge.js";
import * as thorns from "./vendor/pixelfx/src/fx/nature/thorns.js";
import * as blinkOut from "./vendor/pixelfx/src/fx/arcane/blink-out.js";
import * as electrified from "./vendor/pixelfx/src/fx/lightning/electrified.js";
import * as burning from "./vendor/pixelfx/src/fx/status/burning.js";
import * as soulDrain from "./vendor/pixelfx/src/fx/shadow/soul-drain.js";
import * as hexSigil from "./vendor/pixelfx/src/fx/shadow/hex-sigil.js";
import * as implosion from "./vendor/pixelfx/src/fx/explosions/implosion.js";
import * as runeCircle from "./vendor/pixelfx/src/fx/arcane/rune-circle.js";
import * as healAoe from "./vendor/pixelfx/src/fx/holy/heal-aoe.js";
import * as respawnBeam from "./vendor/pixelfx/src/fx/spawn/respawn-beam.js";
import * as poof from "./vendor/pixelfx/src/fx/spawn/poof.js";
import * as collectBurst from "./vendor/pixelfx/src/fx/pickups/collect-burst.js";
import * as featherBurst from "./vendor/pixelfx/src/fx/holy/feather-burst.js";
import * as parry from "./vendor/pixelfx/src/fx/impacts/parry.js";
import * as quake from "./vendor/pixelfx/src/fx/nature/quake.js";
import * as groundSlam from "./vendor/pixelfx/src/fx/impacts/ground-slam.js";
import * as shadowTendrils from "./vendor/pixelfx/src/fx/shadow/shadow-tendrils.js";
import * as hammerSmash from "./vendor/pixelfx/src/fx/melee/hammer-smash.js";
import * as fireTornado from "./vendor/pixelfx/src/fx/fire/fire-tornado.js";
import * as blizzardVortex from "./vendor/pixelfx/src/fx/ice/blizzard-vortex.js";
import * as spinSlash from "./vendor/pixelfx/src/fx/melee/spin-slash.js";

const FX = { fireball, hitSpark, critHit, shieldBlock, clawSwipe, swordSlash, heavyCleave, doubleSlash, uppercut, vineWhip, earthSpike, tornado, rootSnare, leafStorm, splash, waterJet, waveCrash, zapHit, lightningStrike, chainLightning, curse, lifeSteal, graspingHands, energyDisc, plasmaShot, iceLance, iceShatter, heal, divineShield, manaBurst, poisoned, confused, slowed, stunned, cursed, haste, rage, petrified, frozen, bleeding, fireRing, charmed, spellCharge, thorns, blinkOut, electrified, burning, soulDrain, hexSigil, implosion, runeCircle, healAoe, respawnBeam, poof, collectBurst, featherBurst, parry, quake, groundSlam, shadowTendrils, hammerSmash, fireTornado, blizzardVortex, spinSlash };

// The battle canvas is 480x360; effects are drawn on a 240x180 frame and scaled 2x so their pixels match the sprites.
const SCALE = 2, W = 240, H = 180;
// Where the two combatants stand, in frame pixels (feet). VQSV5 layout: the enemy up on the right, the player's active pet down on the left.
const SIDE = { foe: { x: 179, feet: 76 }, hero: { x: 61, feet: 92 } };
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
// Three strength tiers per element (tier 1 weak skills, 2 medium, 3 the strongest): each tier lists its whole timeline.
const ground = (fx, at, ms, speed, opts) => ({ fx, on: "target", at, ms, speed, feet: true, opts });
const slash = (fx, at, ms, speed, ox, opts) => ({ fx, on: "target", at, ms, speed, ox, feet: true, opts });
const PLANS = {
  fire: [
    [{ fx: "fireball", travel: 300, ms: 300 }, spark("fire", 300)],
    [{ fx: "fireball", travel: 300, ms: 300 }, ground("fireRing", 250, 1000, 2.6, { reach: 34 }), spark("fire", 300)],
    [{ fx: "fireball", travel: 280, ms: 280 }, ground("fireTornado", 260, 1200, 1.4), ground("fireRing", 300, 1100, 2.4, { reach: 50 }), spark("fire", 300)],
  ],
  leaf: [
    [ground("vineWhip", 0, 1000, 1.4), spark("leaf", 330)],
    [ground("vineWhip", 0, 1000, 1.4), ground("leafStorm", 200, 900, 1.4), spark("leaf", 330)],
    [ground("thorns", 0, 1000, 1.8), ground("vineWhip", 150, 1000, 1.4), ground("leafStorm", 250, 1000, 1.4), spark("leaf", 400)],
  ],
  earth: [
    [ground("earthSpike", 0, 1000, 2.4), spark("earth", 250)],
    [ground("quake", 0, 1000, 2.2), ground("earthSpike", 150, 1000, 2.4), spark("earth", 300)],
    [ground("groundSlam", 0, 1200, 2.2), ground("earthSpike", 200, 1000, 2.4), ground("quake", 200, 900, 2.2), spark("earth", 350)],
  ],
  water: [
    [{ fx: "waterJet", travel: 280, ms: 320 }, spark("water", 300)],
    [{ fx: "waterJet", travel: 280, ms: 320 }, ground("splash", 280, 900, 2.4), spark("water", 300)],
    [{ fx: "waterJet", travel: 260, ms: 300 }, ground("waveCrash", 200, 1100, 2.2), ground("splash", 400, 900, 2.4), spark("water", 420)],
  ],
  volt: [
    [{ fx: "zapHit", on: "target", at: 0, ms: 600, speed: 1.4 }, spark("volt", 120)],
    [{ fx: "chainLightning", on: "target", at: 0, ms: 700, speed: 1.4 }, { fx: "zapHit", on: "target", at: 180, ms: 600, speed: 1.4 }, spark("volt", 200)],
    [ground("lightningStrike", 0, 1100, 2.0), { fx: "chainLightning", on: "target", at: 250, ms: 700, speed: 1.4 }, { fx: "zapHit", on: "target", at: 350, ms: 600, speed: 1.4 }, spark("volt", 380)],
  ],
  dark: [
    [{ fx: "curse", on: "target", at: 0, ms: 1000, speed: 2.4 }, spark("dark", 300)],
    [{ fx: "curse", on: "target", at: 0, ms: 1100, speed: 2.4 }, ground("graspingHands", 250, 1100, 2.4), spark("dark", 500)],
    [ground("shadowTendrils", 0, 1200, 2.2), ground("graspingHands", 250, 1100, 2.4), { fx: "curse", on: "target", at: 350, ms: 900, speed: 2.4 }, spark("dark", 550)],
  ],
  wind: [
    [slash("swordSlash", 0, 600, 1.4, 36, { palette: RAMP.wind }), spark("wind", 120)],
    [ground("tornado", 0, 900, 1.6), slash("swordSlash", 200, 600, 1.4, 36, { palette: RAMP.wind }), spark("wind", 260)],
    [ground("blizzardVortex", 0, 1100, 1.6, { palette: RAMP.wind }), ground("tornado", 150, 1000, 1.6), slash("spinSlash", 350, 600, 1.3, 0, { palette: RAMP.wind }), spark("wind", 420)],
  ],
  steel: [
    [{ fx: "energyDisc", travel: 260, ms: 260, opts: { palette: RAMP.steel } }, spark("steel", 260)],
    [{ fx: "energyDisc", travel: 260, ms: 260, opts: { palette: RAMP.steel } }, slash("heavyCleave", 260, 700, 1.6, 55), spark("steel", 320)],
    [{ fx: "energyDisc", travel: 240, ms: 240, opts: { palette: RAMP.steel } }, slash("hammerSmash", 240, 900, 1.6, 40), slash("heavyCleave", 380, 700, 1.6, 55), spark("steel", 420)],
  ],
  beast: [
    [slash("clawSwipe", 0, 700, 1.3, 34), spark("plain", 150)],
    [slash("clawSwipe", 0, 700, 1.3, 34), slash("doubleSlash", 220, 600, 1.3, 36, { palette: RAMP.plain }), spark("plain", 260)],
    [slash("clawSwipe", 0, 700, 1.3, 34), slash("doubleSlash", 200, 600, 1.3, 36, { palette: RAMP.plain }), slash("uppercut", 380, 700, 1.3, 30), spark("plain", 420)],
  ],
  sea: [
    [{ fx: "waterJet", travel: 280, ms: 320 }, spark("water", 300)],
    [ground("waveCrash", 0, 1000, 2.2), spark("water", 400)],
    [ground("waveCrash", 0, 1100, 2.0), ground("splash", 300, 900, 2.4), { fx: "waterJet", travel: 260, ms: 300 }, spark("water", 450)],
  ],
  plain: [[slash("swordSlash", 0, 500, 1.4, 36, { palette: RAMP.plain }), spark("plain", 100)]],
};
const planOf = (key, tier) => { const tiers = PLANS[key]; return tiers[Math.max(0, Math.min(tiers.length, tier || 1) - 1)]; };
const ELEMENT_PLAN = { "Hỏa hệ": "fire", "Mộc hệ": "leaf", "Thổ hệ": "earth", "Thủy hệ": "water", "Điện hệ": "volt", "Quỷ hệ": "dark", "Phong hệ": "wind",
  "Thực Vật hệ": "leaf", "Kim Loại hệ": "steel", "Phi Hành hệ": "wind", "Mãnh Thú hệ": "beast", "Hải Dương hệ": "sea" };
const planKey = element => ELEMENT_PLAN[element] || "plain";

// Buff / debuff ids are the VQSV6 table ids (data/vqsv6_pet_stats.json status_effects).
const BUFF_FX = {
  0: { fx: "spellCharge", ms: 1000 },                                              // Súc Lực: gathering strength
  1: { fx: "collectBurst", ms: 900, opts: { palette: RAMP.fire } },                 // Phá Phủ: power-up burst
  2: { fx: "divineShield", ms: 900, opts: { palette: RAMP.leaf } },               // Kinh Cức: a thorn-green guard that hurts attackers
  3: { fx: "heal", ms: 1100, speed: 1.6 }, 4: { fx: "divineShield", ms: 900 },
  5: { fx: "blinkOut", ms: 900, speed: 1.4 },                                        // Vô hình: fades out
  6: { fx: "divineShield", ms: 900 }, 7: { fx: "manaBurst", ms: 800, speed: 1.4 },
  8: { fx: "electrified", ms: 1000 },                                                // Điện áp
  9: { fx: "burning", ms: 1000 },                                                    // Hỏa Thạch
  10: { fx: "rage", ms: 1000 },
  11: { fx: "soulDrain", ms: 1000, speed: 1.4, faceFoe: true },                     // Thâu Thủ: pulls the opponent's buffs over
  12: { fx: "haste", ms: 900 }, 13: { fx: "heal", ms: 1100, speed: 1.6 },
  14: { fx: "petrified", ms: 1100 },                                                 // Thạch Phu: stone skin
};
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
const V5_FX = { 0: { fx: "petrified", ms: 1300 }, 2: { fx: "stunned", ms: 1200 }, 3: { fx: "charmed", ms: 1200 }, 4: { fx: "rootSnare", ms: 1000, speed: 2 }, 5: { fx: "poisoned", ms: 1000 }, 6: { fx: "runeCircle", ms: 1100 } };

// One-off events: capture, miss, reflection, drain, revive and the VQSV5 special moves.
const EVENT_FX = {
  miss: { fx: "poof", ms: 700, speed: 1.6, opts: { palette: RAMP.plain } },
  reflect: { fx: "parry", ms: 700, speed: 1.2, body: true },
  drain: { fx: "heal", ms: 900, speed: 1.6, opts: { palette: ["#2a0a10", "#6a1020", "#b02030", "#e04050", "#ff7a80", "#ffb0b0", "#ffe0e0", "#ffffff"] } },
  revive: { fx: "respawnBeam", ms: 1400, speed: 1.4 },
  captureHit: { fx: "collectBurst", ms: 700, speed: 1.4, opts: { palette: RAMP.fire } },
  captureOk: { fx: "featherBurst", ms: 1300, speed: 1.4 },
  captureFail: { fx: "poof", ms: 900, speed: 1.4 },
  healSmall: { fx: "heal", ms: 1100, speed: 1.6 },
  healBig: { fx: "healAoe", ms: 1300, speed: 1.6 },
  loyalty: { fx: "hexSigil", ms: 1200, speed: 2.4 },
  sacrifice: { fx: "implosion", ms: 1100, speed: 1.2 },
};

const frame = createFrame(W, H);
let canvas = null, image = null;
const live = [];
const sidePos = (side, atFeet = false) => ({ x: SIDE[side].x, y: SIDE[side].feet - (atFeet ? 0 : BODY) });

function add(step, from, to, base, speedUp = 1) {
  const target = step.on === "caster" ? from : to;
  const mod = FX[step.fx];
  if (!mod) return;
  const flip = to.x < from.x;                         // effects drawn facing right are mirrored when the target is to the left
  const entry = { mod, start: base + (step.at || 0), ms: step.ms || 800, speed: (step.speed || 1) * speedUp, flip, step, from, to, fx: null, last: null };
  const pos = step.travel ? from : target;
  // "ox": slide the origin toward the attacker so a melee mark lands on the target; "feet": stand on the target's feet.
  const offset = step.ox ? (flip ? 1 : -1) * step.ox : 0, feetY = step.feet ? (step.on === "caster" ? from : to).y + BODY : pos.y;
  entry.opts = { x: pos.x + offset, y: step.feet ? feetY : pos.y, flip, seed: Math.floor(Math.random() * 1e6), ...(step.opts || {}) };
  if (step.fx === "hitSpark" || step.fx === "critHit") entry.opts.height = BODY - 8;
  live.push(entry);
}

function cast({ fromEnemy, element, startAt, crit = false, tier = 1 }) {
  const from = sidePos(fromEnemy ? "foe" : "hero"), to = sidePos(fromEnemy ? "hero" : "foe");
  const feetTo = sidePos(fromEnemy ? "hero" : "foe", true);
  // Ground effects stand on the target's feet; spark and strike effects hit its body.
  const plan = planOf(planKey(element), tier);
  // sound on impact (sound_fx.js): after the projectile flight when the move throws something
  const impact = Math.max(0, startAt - performance.now()) + (plan.find(step => step.travel)?.travel || 60);
  window.VQSVSound?.play(planKey(element), { tier, delay: impact });
  if (crit) window.VQSVSound?.play("crit", { delay: impact + 80 });
  for (const step of plan) {
    add(step, from, to, startAt);
  }
  if (crit) add({ fx: "critHit", on: "target", at: (plan.find(step => step.travel)?.travel || 120) - 20, ms: 700 }, from, to, startAt);
  if (!live.length) return;
}
// A one-off event effect standing on one side's feet (EVENT_FX), e.g. a capture burst on the enemy or a parry on the attacker.
const EVENT_SOUND = { miss: ["miss"], reflect: ["reflect"], drain: ["drain"], revive: ["revive"], captureHit: ["ballHit"], captureOk: ["caught"], captureFail: ["escape"],
  healSmall: ["heal", 1], healBig: ["heal", 2], loyalty: ["loyalty"], sacrifice: ["sacrifice"] };
function event({ kind, onEnemy, startAt, delay = 0, image = null, box = [114, 112] }) {
  const row = EVENT_FX[kind];
  if (!row) return;
  const sound = EVENT_SOUND[kind];
  if (sound) window.VQSVSound?.play(sound[0], { tier: sound[1] || 1, delay: Math.max(0, startAt - performance.now()) + delay });
  const at = sidePos(onEnemy ? "foe" : "hero", true), actor = actorFromImage(image, box);
  add({ ...row, on: "target", at: delay, opts: { ...(row.opts || {}), ...(actor ? { actor } : {}) } }, at, at, startAt);
}
function basic({ fromEnemy, startAt, element }) { cast({ fromEnemy, element: element || null, startAt }); }
function status({ onEnemy, kind, id, startAt, image = null, box = [114, 112] }) {
  const row = (kind === "buff" ? BUFF_FX : kind === "v5" ? V5_FX : DEBUFF_FX)[id];
  if (!row) return;
  window.VQSVSound?.play(kind === "buff" ? (row.fx === "heal" ? "heal" : "buff") : "debuff", { delay: Math.max(0, startAt - performance.now()) });
  const at = sidePos(onEnemy ? "foe" : "hero", true);
  const actor = actorFromImage(image, box), opts = { ...(row.opts || {}), ...(actor ? { actor } : {}) };
  // effects that point somewhere (faceFoe) are anchored on the holder and turned toward the opponent
  if (row.faceFoe) add({ ...row, on: "caster", opts }, at, sidePos(onEnemy ? "hero" : "foe", true), startAt);
  else add({ ...row, on: "target", opts }, at, at, startAt);
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

window.VQSVSkillFx = { cast, basic, status, event, draw, eventFx: EVENT_FX, tiersOf: key => PLANS[key].length, clear: () => { live.length = 0; }, busy: () => live.length > 0, plans: Object.keys(PLANS), planOf: planKey, elements: ELEMENT_PLAN, buffFx: BUFF_FX, debuffFx: DEBUFF_FX, v5Fx: V5_FX };
window.dispatchEvent?.(new Event("vqsv-skillfx-ready"));
