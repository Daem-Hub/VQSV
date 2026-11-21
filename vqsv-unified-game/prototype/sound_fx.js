// Sound effects, synthesised with the Web Audio API (no audio files). The source games only ship MIDI music (vqsv5/music/*.mid,
// vqsv6/music/data/sound/0.mid) and no effect sounds, so every sound here is new prototype design in a retro chiptune style.
// window.VQSVSound.play(name, { tier, delay }) plays one sound; names cover the skill elements, statuses, battle events and UI.
(function () {
  const KEY = "vqsv-unified-sound-v1";
  let settings = { on: true, volume: 0.6 };
  try { const raw = JSON.parse(localStorage.getItem(KEY) || "null"); if (raw && typeof raw === "object") settings = { on: raw.on !== false, volume: Math.max(0, Math.min(1, Number(raw.volume) || 0)) }; } catch (_) {}
  const save = () => { try { localStorage.setItem(KEY, JSON.stringify(settings)); } catch (_) {} };
  const AudioCtx = typeof window !== "undefined" && (window.AudioContext || window.webkitAudioContext);
  let ctx = null, master = null, noiseBuffer = null;

  function audio() {
    if (!AudioCtx) return null;
    if (!ctx) {
      ctx = new AudioCtx();
      master = ctx.createGain(); master.gain.value = settings.volume * 0.5; master.connect(ctx.destination);
      noiseBuffer = ctx.createBuffer(1, ctx.sampleRate, ctx.sampleRate);
      const data = noiseBuffer.getChannelData(0);
      for (let i = 0; i < data.length; i++) data[i] = Math.random() * 2 - 1;
    }
    if (ctx.state === "suspended") ctx.resume().catch(() => {});
    return ctx;
  }
  // Browsers only allow sound after a user gesture: unlock on the first key or tap.
  for (const type of ["pointerdown", "keydown", "touchstart"]) window.addEventListener?.(type, () => { if (settings.on) audio(); }, { once: true, passive: true });

  // ---- building blocks: a tone with a pitch sweep and an envelope, a filtered noise burst ----
  function tone(t, { type = "square", from = 440, to = from, dur = 0.12, vol = 0.3, attack = 0.005, curve = "exp", detune = 0 }) {
    const o = ctx.createOscillator(), g = ctx.createGain();
    o.type = type; o.detune.value = detune;
    o.frequency.setValueAtTime(Math.max(20, from), t);
    if (to !== from) curve === "exp" ? o.frequency.exponentialRampToValueAtTime(Math.max(20, to), t + dur) : o.frequency.linearRampToValueAtTime(Math.max(20, to), t + dur);
    g.gain.setValueAtTime(0.0001, t); g.gain.exponentialRampToValueAtTime(vol, t + attack); g.gain.exponentialRampToValueAtTime(0.0001, t + dur);
    o.connect(g); g.connect(master); o.start(t); o.stop(t + dur + 0.02);
  }
  function noise(t, { dur = 0.2, vol = 0.3, filter = "lowpass", from = 2000, to = from, q = 1, attack = 0.005 }) {
    const src = ctx.createBufferSource(), f = ctx.createBiquadFilter(), g = ctx.createGain();
    src.buffer = noiseBuffer; src.loop = true;
    f.type = filter; f.Q.value = q; f.frequency.setValueAtTime(from, t); if (to !== from) f.frequency.exponentialRampToValueAtTime(Math.max(30, to), t + dur);
    g.gain.setValueAtTime(0.0001, t); g.gain.exponentialRampToValueAtTime(vol, t + attack); g.gain.exponentialRampToValueAtTime(0.0001, t + dur);
    src.connect(f); f.connect(g); g.connect(master); src.start(t, Math.random() * 0.5); src.stop(t + dur + 0.02);
  }
  const arp = (t, notes, { type = "square", step = 0.07, dur = 0.1, vol = 0.18 } = {}) => notes.forEach((f, i) => tone(t + i * step, { type, from: f, dur, vol }));

  // ---- the sounds; `k` is the tier (1-3): stronger moves are longer, lower and layered ----
  const SOUNDS = {
    // elements of a damaging move
    fire: (t, k) => { noise(t, { dur: 0.25 + 0.12 * k, vol: 0.32, filter: "lowpass", from: 900, to: 180 }); tone(t, { type: "sawtooth", from: 180, to: 60, dur: 0.25 + 0.1 * k, vol: 0.12 }); if (k > 1) noise(t + 0.18, { dur: 0.4, vol: 0.22, filter: "bandpass", from: 400, to: 120, q: 2 }); if (k > 2) tone(t + 0.25, { type: "triangle", from: 90, to: 40, dur: 0.5, vol: 0.3 }); },
    leaf: (t, k) => { noise(t, { dur: 0.22, vol: 0.2, filter: "bandpass", from: 1200, to: 3000, q: 3 }); tone(t + 0.05, { type: "triangle", from: 520, to: 880, dur: 0.12, vol: 0.14 }); if (k > 1) for (let i = 0; i < 4; i++) noise(t + 0.15 + i * 0.06, { dur: 0.07, vol: 0.12, filter: "highpass", from: 3000 }); if (k > 2) tone(t + 0.3, { type: "square", from: 140, to: 70, dur: 0.3, vol: 0.14 }); },
    earth: (t, k) => { tone(t, { type: "triangle", from: 120, to: 40, dur: 0.3 + 0.1 * k, vol: 0.4 }); noise(t, { dur: 0.3, vol: 0.25, filter: "lowpass", from: 500, to: 80 }); if (k > 1) noise(t + 0.2, { dur: 0.5, vol: 0.18, filter: "lowpass", from: 300, to: 60 }); if (k > 2) tone(t + 0.3, { type: "square", from: 70, to: 35, dur: 0.5, vol: 0.18 }); },
    water: (t, k) => { noise(t, { dur: 0.25, vol: 0.18, filter: "bandpass", from: 1800, to: 700, q: 4 }); for (let i = 0; i < 2 + k; i++) tone(t + 0.05 + i * 0.05, { type: "sine", from: 300 + i * 120, to: 900 + i * 150, dur: 0.07, vol: 0.14 }); if (k > 2) noise(t + 0.2, { dur: 0.6, vol: 0.22, filter: "lowpass", from: 1400, to: 200 }); },
    volt: (t, k) => { for (let i = 0; i < 3 + 2 * k; i++) tone(t + i * 0.03, { type: "sawtooth", from: 1800 - i * 90 + Math.random() * 400, to: 300, dur: 0.05, vol: 0.12 }); noise(t, { dur: 0.15 + 0.08 * k, vol: 0.18, filter: "highpass", from: 2500 }); if (k > 2) { noise(t + 0.15, { dur: 0.5, vol: 0.35, filter: "lowpass", from: 2500, to: 100 }); tone(t + 0.15, { type: "square", from: 80, to: 40, dur: 0.4, vol: 0.2 }); } },
    dark: (t, k) => { tone(t, { type: "sawtooth", from: 220, to: 80, dur: 0.35 + 0.1 * k, vol: 0.14, detune: -15 }); tone(t, { type: "sawtooth", from: 225, to: 78, dur: 0.35 + 0.1 * k, vol: 0.12, detune: 15 }); noise(t, { dur: 0.3, vol: 0.12, filter: "bandpass", from: 500, to: 250, q: 6 }); if (k > 2) arp(t + 0.3, [233, 220, 175, 146], { type: "triangle", step: 0.08, vol: 0.14 }); },
    wind: (t, k) => { noise(t, { dur: 0.3 + 0.12 * k, vol: 0.22, filter: "bandpass", from: 600, to: 3200, q: 5 }); if (k > 1) noise(t + 0.15, { dur: 0.35, vol: 0.16, filter: "bandpass", from: 3000, to: 700, q: 5 }); tone(t + 0.1, { type: "triangle", from: 700, to: 1200, dur: 0.1, vol: 0.08 }); },
    steel: (t, k) => { tone(t, { type: "square", from: 1500, to: 1450, dur: 0.18 + 0.05 * k, vol: 0.1 }); tone(t, { type: "triangle", from: 2300, to: 2250, dur: 0.3, vol: 0.08 }); noise(t, { dur: 0.06, vol: 0.25, filter: "highpass", from: 3000 }); if (k > 1) tone(t + 0.15, { type: "triangle", from: 110, to: 55, dur: 0.25, vol: 0.3 }); },
    beast: (t, k) => { for (let i = 0; i < k + 1; i++) noise(t + i * 0.09, { dur: 0.08, vol: 0.25, filter: "bandpass", from: 2500, to: 900, q: 2 }); tone(t, { type: "sawtooth", from: 160, to: 110, dur: 0.15, vol: 0.12 }); },
    sea: (t, k) => SOUNDS.water(t, k),
    plain: (t) => { noise(t, { dur: 0.1, vol: 0.2, filter: "bandpass", from: 1500, to: 3500, q: 2 }); tone(t + 0.05, { type: "triangle", from: 150, to: 70, dur: 0.12, vol: 0.3 }); },
    // the two halves of every move: leaving the caster, then the body blow on the target
    swing: (t, k) => noise(t, { dur: 0.12 + 0.03 * k, vol: 0.13, filter: "bandpass", from: 700, to: 2600, q: 2.5 }),
    charge: (t, k) => { tone(t, { type: "sine", from: 260, to: 620 + 120 * k, dur: 0.16 + 0.04 * k, vol: 0.09 }); tone(t + 0.02, { type: "triangle", from: 520, to: 1240, dur: 0.12, vol: 0.05 }); },
    hit: (t, k) => { tone(t, { type: "triangle", from: 160, to: 55, dur: 0.12 + 0.04 * k, vol: 0.22 + 0.04 * k }); noise(t, { dur: 0.06, vol: 0.12 + 0.03 * k, filter: "lowpass", from: 1800, to: 300 }); },
    crit: (t) => { tone(t, { type: "square", from: 1320, to: 1760, dur: 0.08, vol: 0.14 }); tone(t + 0.07, { type: "square", from: 1760, to: 2640, dur: 0.12, vol: 0.12 }); },
    // statuses and events
    buff: (t) => arp(t, [523, 659, 784, 1047], { type: "triangle", step: 0.06, vol: 0.14 }),
    debuff: (t) => arp(t, [494, 415, 349, 294], { type: "square", step: 0.07, vol: 0.1 }),
    heal: (t, k) => arp(t, k > 1 ? [523, 659, 784, 1047, 1319] : [659, 784, 1047], { type: "triangle", step: 0.07, dur: 0.16, vol: 0.14 }),
    revive: (t) => { arp(t, [392, 523, 659, 784, 1047, 1319], { type: "triangle", step: 0.08, dur: 0.2, vol: 0.15 }); noise(t, { dur: 0.6, vol: 0.06, filter: "highpass", from: 4000 }); },
    miss: (t) => noise(t, { dur: 0.18, vol: 0.14, filter: "bandpass", from: 800, to: 2400, q: 3 }),
    reflect: (t) => { tone(t, { type: "square", from: 1800, to: 1600, dur: 0.12, vol: 0.12 }); tone(t + 0.02, { type: "triangle", from: 2700, to: 2600, dur: 0.25, vol: 0.08 }); },
    drain: (t) => { tone(t, { type: "sine", from: 900, to: 300, dur: 0.35, vol: 0.14 }); tone(t + 0.2, { type: "triangle", from: 400, to: 800, dur: 0.2, vol: 0.1 }); },
    loyalty: (t) => SOUNDS.dark(t, 1),
    sacrifice: (t) => { noise(t, { dur: 0.6, vol: 0.2, filter: "lowpass", from: 200, to: 3000 }); tone(t, { type: "sawtooth", from: 60, to: 600, dur: 0.6, vol: 0.12 }); },
    throw: (t) => noise(t, { dur: 0.35, vol: 0.12, filter: "bandpass", from: 500, to: 2000, q: 4 }),
    ballHit: (t) => { tone(t, { type: "square", from: 900, to: 500, dur: 0.06, vol: 0.16 }); for (let i = 0; i < 2; i++) tone(t + 0.18 + i * 0.22, { type: "triangle", from: 300, to: 260, dur: 0.06, vol: 0.18 }); },
    caught: (t) => arp(t, [523, 659, 784, 1047, 784, 1047], { type: "square", step: 0.09, dur: 0.12, vol: 0.14 }),
    escape: (t) => { tone(t, { type: "square", from: 600, to: 150, dur: 0.18, vol: 0.14 }); noise(t, { dur: 0.15, vol: 0.18, filter: "highpass", from: 1500 }); },
    // battle flow and UI
    encounter: (t) => { arp(t, [196, 262, 330, 392], { type: "square", step: 0.05, dur: 0.07, vol: 0.12 }); tone(t + 0.22, { type: "square", from: 523, dur: 0.22, vol: 0.12 }); },
    victory: (t) => arp(t, [523, 523, 523, 659, 784, 659, 784, 1047], { type: "square", step: 0.1, dur: 0.12, vol: 0.12 }),
    levelUp: (t) => arp(t, [392, 494, 587, 784, 988], { type: "square", step: 0.07, dur: 0.12, vol: 0.12 }),
    faint: (t) => tone(t, { type: "triangle", from: 440, to: 110, dur: 0.6, vol: 0.2, curve: "lin" }),
    chest: (t) => { tone(t, { type: "square", from: 220, to: 330, dur: 0.08, vol: 0.12 }); arp(t + 0.1, [784, 988, 1175, 1568], { type: "triangle", step: 0.06, vol: 0.14 }); },
    coin: (t) => { tone(t, { type: "square", from: 988, dur: 0.06, vol: 0.12 }); tone(t + 0.06, { type: "square", from: 1319, dur: 0.14, vol: 0.12 }); },
    flee: (t) => noise(t, { dur: 0.3, vol: 0.12, filter: "bandpass", from: 2000, to: 600, q: 2 }),
    select: (t) => tone(t, { type: "square", from: 880, dur: 0.04, vol: 0.08 }),
  };

  function play(name, { tier = 1, delay = 0 } = {}) {
    if (!settings.on || settings.volume <= 0 || !SOUNDS[name] || !audio()) return false;
    try { SOUNDS[name](ctx.currentTime + Math.max(0, delay) / 1000 + 0.01, Math.max(1, Math.min(3, tier))); } catch (_) { return false; }
    return true;
  }
  function setOn(on) { settings.on = !!on; save(); if (on) audio(); if (master) master.gain.value = settings.on ? settings.volume * 0.5 : 0; }
  function setVolume(v) { settings.volume = Math.max(0, Math.min(1, Number(v) || 0)); save(); if (master) master.gain.value = settings.on ? settings.volume * 0.5 : 0; }
  window.VQSVSound = { play, setOn, setVolume, settings: () => ({ ...settings }), names: Object.keys(SOUNDS), supported: !!AudioCtx };
})();
