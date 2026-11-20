"""Wild pets for the 29 illustrated atlas maps: VQSV5 and VQSV6 pets mixed, chosen by the region's element.

TRIAL design (not in either source game): each atlas map gets one or more of VQSV6's seven elements from what
its picture shows (volcano = Hỏa, ice and sea = Thủy, ...). VQSV5 pets join through the type-to-element table
UNIFY5 below, the same table the battle engine uses (prototype/index.html UNIFY5). Only base forms appear
(no evolution or mutation targets), VQSV5 pets of rank 1-3 only. Levels rise along the route: map i spans
8 + 2i .. 11 + 2i.
"""

ELEMENTS = ["Hỏa hệ", "Mộc hệ", "Thổ hệ", "Thủy hệ", "Điện hệ", "Quỷ hệ", "Phong hệ"]
# VQSV5 type_id -> VQSV6 element_id: Thực Vật -> Mộc, Kim Loại -> Điện, Phi Hành -> Phong, Mãnh Thú -> Thổ, Hải Dương -> Thủy.
UNIFY5 = {0: 1, 1: 4, 2: 6, 3: 2, 4: 3}
UNIFY5_NONE = 5          # the two untyped VQSV5 pets (Quỷ Vương Địa Phủ) count as Quỷ hệ

MAP_ELEMENTS = {
    "red-dunes": [2, 0], "sand-canyon": [2], "eternal-volcano": [0], "ice-pass": [3, 6], "ice-plain": [3],
    "ancient-ice-lake": [3], "deep-marsh": [1, 3], "ryan-shore": [3, 1], "tower-ascent": [6], "tower-halls": [6, 5],
    "tower-crown": [6, 4], "ryan-port": [3], "endless-sea": [3], "thunder-whirlpool": [4, 3], "atlantis": [3, 5],
    "coral-grotto": [3, 1], "wreck-graveyard": [5, 3], "ocean-abyss": [5, 3], "aethelgard-gate": [2, 6],
    "obsidian-crater": [0, 2], "boran-arena": [0, 2, 4], "cloud-stair": [6], "crystal-palace": [4, 6], "mirror-hall": [5],
    "aethelgard-summit": [6, 4], "sky-monolith": [6, 5], "orbit": [4, 5], "machine-ring": [4], "red-moon": [0, 5],
}


def unified5(pet):
    return UNIFY5.get(pet.get("type_id"), UNIFY5_NONE) if pet.get("type_id") is not None else UNIFY5_NONE


def attach_atlas_wild(atlas_maps, stats6, stats5, roster):
    targets6 = {record["evolution"]["target_pet_id"] for record in roster["records"] if record.get("evolution")}
    targets5 = {t for pet in stats5["pets"] for evo in [pet.get("evolution") or {}]
                for t in (evo.get("target_pet_id"), evo.get("mutation_target_pet_id")) if t}
    for index, atlas_map in enumerate(atlas_maps):
        wanted = MAP_ELEMENTS.get(atlas_map["id"])
        if not wanted:
            continue
        low = 8 + 2 * index
        pets6 = [pet["pet_id"] for pet in stats6["pets"] if pet["element_id"] in wanted and pet["pet_id"] not in targets6]
        pets5 = [pet["pet_id"] for pet in stats5["pets"] if unified5(pet) in wanted and pet["pet_id"] not in targets5 and pet.get("rank", 1) <= 3]
        atlas_map["wildElements"] = [ELEMENTS[e] for e in wanted]
        atlas_map["customWildAreas"] = [{"area": f"atlas-{atlas_map['id']}", "level_range": [low, low + 3],
                                         "pets": [{"pet_id": pet_id, "terrain": 0, "quality_range": [1, 3]} for pet_id in pets6]}]
        atlas_map["wild5Pool"] = {"pet_ids": pets5, "level_min": low, "level_max": low + 3}
    return atlas_maps
