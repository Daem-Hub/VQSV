# VQSV6 map format notes

Source: `D:\TOAN BO FILE\Download\game\vqsv6\raw_data\data\map\map_N.mid`  
Parser: [decode_vqsv6_maps.py](decode_vqsv6_maps.py)  
Decoded outputs: `vqsv6_maps/map_N.json`

## Header and layer records

The map loader in `vqsv-6.jar` (`j.a(I)V`) reads each map as a `DataInputStream`.

| Offset | Field | Evidence / interpretation |
|---|---|---|
| 0 | coordinate mode | `1` selects one-byte x/y coordinates; otherwise the loader reads signed 16-bit coordinates |
| 1 | option byte | Preserved by the loader; meaning not confirmed |
| 2 | width | One byte when mode is `1`, otherwise signed 16-bit |
| 3 | height | Same encoding as width |
| 4 | tile size | One byte; observed value is `16` across the decoded maps |
| 5 | layer count | One byte; observed value is `5` |

Each layer begins with `kind:u8`, `encoding:u8`, `record_count:u16be`. Each record contains `x`, `y`, and `value:u16be`; coordinate width follows the header mode. Grid layers (`kind` 0 or 1) are stored as width-by-height arrays. Other kinds are stored as sparse four-short records; the loader separates the low 12 value bits and the transform bits `((value & 0x7000) >> 12)`.

The bytecode confirms the storage shape and bit handling. The semantic names for kinds 0–4, the meaning of the option byte, and the mapping from tile/object IDs to image assets still need verification. The parser preserves raw values to avoid assigning meanings prematurely.

## Decode coverage

- 94 map files parsed with all declared layers and records consumed.
- IDs span 0–100 with gaps; `map_99.mid` is a 27-byte placeholder with zero records in all layers.
- A single trailing byte remains after the declared records in every file; its role is not yet known.
- VQSV6 tile size is 16 px. VQSV5 decoded maps use 20 px tiles, so the merged runtime should use logical grid coordinates and apply each source's pixel scale separately.

## Candidate start map

`map_0` is a plausible VQSV6 arrival map because it is the lowest numbered regular map and the opening NPC dialogue corpus references village protection and Neil. This link is inferred from ordering and text extraction; the original game start state has not yet been traced to prove `map_0` is Neil.
