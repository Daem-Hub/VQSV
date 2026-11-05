"""Decode VQSV6 pet combat stats, skill table and battle rules from db.mid.

Evidence comes from the decompiled VQSV_FIX_2.7 reference (whose db.mid is byte-identical
to vqsv-6.jar). Paths below are relative to
reference_vqsv_fix_2_7/source_code/decoded/decompiled_source_cfr/:

- game/i.java  a(int,int,short,byte,short,byte) and W(): stats are
  stat[1..4] = (base + growth * level [/10] + bonus) * QUALITY_PCT[quality - 1] / 100
  with integer (Java) arithmetic; quality defaults to table-0 column 3.
- game/a.java  Q(): turn order sorts by stat[4] (speed), highest first.
- game/i.java  a(byte) / C(): attack damage = attacker stat[2] - defender stat[3].
- game/i.java  b(i): crit chance 5% + speed/2 (x1.5 damage); skill power column 3 in percent;
  element advantage x3 / disadvantage x0.6; final damage at least 1.
- game/i.java  a(i): element cycle and table-0 column 22 flag.
- game/i.java  H()/G()/X(): skills come from rows element*10 .. element*10+9 of table 1;
  at level <= 5 a pet knows only row element*10; later rows unlock when
  table1[row][4] <= table8[column18][tier], tier from level thresholds [5,10,20,30,40].
- game/k.java  status screen: column 4 is the number of quality stars shown (max quality).

- game/l.java  petArea.mid: row = [map, sub-area, tuple count (-1 = no wild pets), level min, level max,
  then tuples (pet row, terrain 0 land / 2 water, quality min, quality max)]; encounter level and quality
  are uniform random in those ranges.
- game/a.java  m(ball): capture rate. Tất Trung Cầu = 100; otherwise HP <= 15% -> 85, <= 50% -> 45, else 20;
  * ball table4 col6 / 100; * [110,100,95,80,70][quality-1] / 100; * status factor [10,11,12,12,12] / 10;
  * [1000,500,1,1000][pet col22] / 1000; level >= 20 caps at [0,15,35,65][ball]; clamp 1..100;
  success when random(100) < rate.

Writes prototype/data/vqsv6_pet_stats.json and asset_review/vqsv6_pet_stats.csv.
"""
import csv
import json
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
tables = json.loads((ROOT / "asset_review" / "vqsv6_db_sprite_numeric_tables.json").read_text(encoding="utf-8"))
skills_src = json.loads((ROOT / "prototype" / "data" / "original_skills.json").read_text(encoding="utf-8"))
db = [t["rows"] for t in tables["db_mid_tables"]]
names = tables["chs_names"]
elements = {int(k): v for k, v in tables["element_id_labels"].items()}
skill_names = {s["source_index"]: s for s in skills_src["records"] if s["game"] == "VQSV6"}

QUALITY_PCT = [90, 95, 100, 110, 125]   # game/i.java N
LEVEL_TIERS = [5, 10, 20, 30, 40]       # game/i.java X()
# Attacker element -> element it deals x3 to (game/i.java a(i)); reverse direction is x0.6.
BEATS = {0: 1, 1: 2, 2: 3, 3: 0, 5: 6, 6: 4, 4: 5}


def jdiv(a, b):
    """Java integer division truncates toward zero."""
    q = abs(a) // abs(b)
    return q if (a >= 0) == (b >= 0) else -q


def stats_at(row, level, quality):
    pct = QUALITY_PCT[quality - 1]
    return {
        "hp": jdiv((row[5] + row[6] * level + row[7]) * pct, 100),
        "attack": jdiv((row[8] + row[9] * level + row[10]) * pct, 100),
        "defense": jdiv((row[11] + jdiv(row[12] * level, 10) + row[13]) * pct, 100),
        "speed": jdiv((row[14] + jdiv(row[15] * level, 10) + row[16]) * pct, 100),
    }


def tier(level):
    t = 0
    for i, threshold in enumerate(LEVEL_TIERS):
        if level >= threshold:
            t = i
    return t


def skills_known(row, level):
    """Skill rows a pet can know at a level (game/i.java H()/G())."""
    base = row[1] * 10
    if level <= 5:
        return [base]
    curve = db[8][row[18]]
    return [r for r in range(base, base + 10) if db[1][r][4] <= curve[tier(level)]]


skill_records = []
for index, r in enumerate(db[1]):
    src = skill_names.get(index, {})
    skill_records.append({
        "skill_id": f"vqsv6-skill-{index}", "source_row": index, "name": src.get("name"), "description": src.get("description"),
        "element": elements.get(r[0]), "element_id": r[0], "power_percent": r[3], "unlock_tier": r[4], "max_uses": r[5],
        "raw_fields": r,
        "field_notes": {"0": "element id", "3": "damage percent of (attack - defense); 0 = non-damage skill",
                        "4": "unlock value compared to table8[pet col18][level tier]", "5": "uses (PP)",
                        "6-9": "status/effect fields; partially used by effect code, not decoded"},
    })

pets = []
for row_index, r in enumerate(db[0]):
    default_q = r[3]
    pets.append({
        "pet_id": f"vqsv6-db-{r[0]}", "row_index": row_index, "name": names[r[0]], "element": elements.get(r[1]), "element_id": r[1],
        "default_quality": default_q, "max_quality_stars": r[4],
        "growth": {"hp": [r[5], r[6], r[7]], "attack": [r[8], r[9], r[10]], "defense": [r[11], r[12], r[13]], "speed": [r[14], r[15], r[16]]},
        "growth_note": "hp/attack: (a + b*L + c); defense/speed: (a + b*L/10 + c); then * quality% / 100, integer math",
        "skill_curve_row": r[18], "advantage_flag_col22": r[22],
        "stats_at_default_quality": {str(L): stats_at(r, L, default_q) for L in (1, 5, 10, 20, 30)},
        "skills_at_level": {str(L): skills_known(r, L) for L in (5, 10, 20, 30, 40)},
    })

wild_areas = []
for r in tables["pet_area_mid_tables"][0]["rows"]:
    if r[2] == -1:
        continue
    tuples = [r[5 + i * 4: 9 + i * 4] for i in range((len(r) - 5) // 4)]
    wild_areas.append({"area": f"{r[0]}-{r[1]}", "level_range": [r[3], r[4]], "pets": [
        {"pet_id": f"vqsv6-db-{db[0][row][0]}", "name": names[db[0][row][0]], "terrain": terrain, "quality_range": [qmin, qmax]}
        for row, terrain, qmin, qmax in tuples]})

balls = []
for index, r in enumerate(db[4][:4]):
    balls.append({"ball_index": index, "name": names[r[0]], "description": names[r[2]], "price": r[3], "rate_percent": r[6]})

out = {
    "source": "vqsv6 data/script/db.mid tables 0, 1, 8; formulas from decompiled VQSV_FIX_2.7 game/i.java and game/a.java",
    "confidence": "Stat formulas, turn order, damage, crit, element multipliers and skill pools are read directly from decompiled code. "
                  "Skill status effects (cols 6-9), column 22 semantics beyond the element check, and table 2-7 buffs are not decoded.",
    "rules": {
        "quality_percent": QUALITY_PCT, "level_tiers": LEVEL_TIERS,
        "damage": "max(1, attacker.attack - defender.defense) * skill.power_percent / 100; crit x1.5; element x3 or x0.6; then +-1 jitter, minimum 1",
        "crit_chance_percent": "5 + attacker.speed / 2 (30 base in a special sprite case)",
        "turn_order": "higher speed acts first",
        "element_beats": {elements[a]: elements[b] for a, b in BEATS.items()},
        "column22_rule": "if exactly one side has col22 == 2, only that side's favourable multiplier applies",
    },
    "capture": {
        "balls": balls,
        "hp_band_rate": [[15, 85], [50, 45], [100, 20]],
        "quality_factor_percent": [110, 100, 95, 80, 70],
        "col22_factor_permille": [1000, 500, 1, 1000],
        "level20_cap_by_ball": [0, 15, 35, 65],
        "note": "Ball 0 (Tất Trung Cầu) always 100. Status factor needs decoded status effects; prototype uses 1.0.",
    },
    "wild_areas": wild_areas,
    "skills": skill_records,
    "pets": pets,
}
(ROOT / "prototype" / "data" / "vqsv6_pet_stats.json").write_text(json.dumps(out, ensure_ascii=False, indent=1), encoding="utf-8")

with (ROOT / "asset_review" / "vqsv6_pet_stats.csv").open("w", encoding="utf-8-sig", newline="") as f:
    w = csv.writer(f)
    w.writerow(["pet_id", "name", "element", "default_quality", "max_stars", "L5_hp", "L5_atk", "L5_def", "L5_spd", "L30_hp", "L30_atk", "L30_def", "L30_spd", "skills_L5", "skills_L30"])
    for p in pets:
        a, b = p["stats_at_default_quality"]["5"], p["stats_at_default_quality"]["30"]
        sk = lambda ids: " / ".join(skill_records[i]["name"] or str(i) for i in ids)
        w.writerow([p["pet_id"], p["name"], p["element"], p["default_quality"], p["max_quality_stars"], a["hp"], a["attack"], a["defense"], a["speed"],
                    b["hp"], b["attack"], b["defense"], b["speed"], sk(p["skills_at_level"]["5"]), sk(p["skills_at_level"]["30"])])
print(f"Wrote {len(pets)} pets and {len(skill_records)} skills")
