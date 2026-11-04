"""Print the evolution-screen checks in game/h.bh()V from VQSV6 bytecode."""
import struct
import sys
import zipfile
import os
from pathlib import Path

if hasattr(sys.stdout, "reconfigure"):
    sys.stdout.reconfigure(errors="backslashreplace")
PROJECT = Path(__file__).resolve().parents[1]
BUNDLED_SOURCE = PROJECT / "sources" / "game"
SOURCE_ROOT = Path(os.environ.get("VQSV_SOURCE_ROOT", str(BUNDLED_SOURCE if BUNDLED_SOURCE.exists() else Path(r"D:\TOAN BO FILE\Download\game"))))
JAR = SOURCE_ROOT / "vqsv-6.jar"


def u2(data, p):
    return struct.unpack_from(">H", data, p)[0], p + 2


def u4(data, p):
    return struct.unpack_from(">I", data, p)[0], p + 4


def parse_class(data):
    p = 8
    count, p = u2(data, p)
    cp = [None] * count
    i = 1
    while i < count:
        tag = data[p]; p += 1
        if tag == 1:
            n, p = u2(data, p); cp[i] = (tag, data[p:p+n].decode("utf8", "replace")); p += n
        elif tag in (3, 4): cp[i] = (tag, data[p:p+4]); p += 4
        elif tag in (5, 6): cp[i] = (tag, data[p:p+8]); p += 8; i += 1
        elif tag in (7, 8, 16, 19, 20):
            n, p = u2(data, p); cp[i] = (tag, n)
        elif tag in (9, 10, 11, 12, 17, 18):
            a, p = u2(data, p); b, p = u2(data, p); cp[i] = (tag, a, b)
        elif tag == 15:
            a = data[p]; p += 1; b, p = u2(data, p); cp[i] = (tag, a, b)
        else: raise ValueError(f"Unknown constant pool tag {tag}")
        i += 1

    def utf(index):
        return cp[index][1] if index and cp[index] and cp[index][0] == 1 else f"#{index}"

    def ref(index):
        x = cp[index]
        if x[0] in (9, 10, 11):
            owner = utf(cp[x[1]][1])
            nt = cp[x[2]]
            return f"{owner}.{utf(nt[1])}{utf(nt[2])}"
        return utf(x[1]) if x[0] == 8 else str(x)

    _, p = u2(data, p); this, p = u2(data, p); _, p = u2(data, p)
    interfaces, p = u2(data, p); p += interfaces * 2
    fields, p = u2(data, p)
    for _ in range(fields):
        p += 6; attrs, p = u2(data, p)
        for _ in range(attrs):
            _, p = u2(data, p); size, p = u4(data, p); p += size
    methods, p = u2(data, p)
    result = []
    for _ in range(methods):
        _, p = u2(data, p); ni, p = u2(data, p); di, p = u2(data, p); attrs, p = u2(data, p)
        code = b""
        for _ in range(attrs):
            ai, p = u2(data, p); size, p = u4(data, p); end = p + size
            if utf(ai) == "Code":
                _, q = u2(data, p); _, q = u2(data, q); n, q = u4(data, q); code = data[q:q+n]
            p = end
        result.append((utf(ni), utf(di), code))
    return utf(cp[this][1]), cp, result, ref


def instructions(code, cp, ref):
    fixed = {0x10: 2, 0x11: 3, 0x12: 2, 0x13: 3, 0x14: 3, 0x84: 3,
             0x15: 2, 0x16: 2, 0x17: 2, 0x18: 2, 0x19: 2,
             0x36: 2, 0x37: 2, 0x38: 2, 0x39: 2, 0x3a: 2, 0xa9: 2, 0xbc: 2,
             0xb2: 3, 0xb3: 3, 0xb4: 3, 0xb5: 3, 0xb6: 3, 0xb7: 3, 0xb8: 3,
             0xbb: 3, 0xbd: 3, 0xc0: 3, 0xc1: 3, 0xc6: 3, 0xc7: 3,
             0xb9: 5, 0xba: 5, 0xc5: 4, 0xc8: 5, 0xc9: 5}
    result, p = [], 0
    while p < len(code):
        start = p; op = code[p]; p += 1; detail = ""
        if op in (0xaa, 0xab):
            while p % 4: p += 1
            p += 4
            if op == 0xaa:
                low, high = struct.unpack_from(">ii", code, p); p += 8 + max(0, high-low+1) * 4
            else:
                n, = struct.unpack_from(">i", code, p); p += 4 + max(0, n) * 8
        elif op == 0xc4:
            wide_op = code[p]; p += 1; p += 4 if wide_op == 0x84 else 2
        else:
            size = fixed.get(op, 3 if 0x99 <= op <= 0xa8 else 1)
            if op in (0x12, 0x13, 0x14, *range(0xb2, 0xba), 0xbb, 0xbd, 0xc0, 0xc1):
                index = code[p] if op == 0x12 else struct.unpack_from(">H", code, p)[0]
                if index < len(cp) and cp[index]: detail = ref(index)
            elif op == 0x10: detail = str(struct.unpack_from(">b", code, p)[0])
            elif op == 0x11: detail = str(struct.unpack_from(">h", code, p)[0])
            elif 0x99 <= op <= 0xa8 or op in (0xc6, 0xc7):
                detail = str(start + struct.unpack_from(">h", code, p)[0])
            p += size - 1
        result.append((start, op, detail))
    return result


with zipfile.ZipFile(JAR) as jar:
    for item in jar.infolist():
        if item.filename not in {"game/h.class", "aq.class"}:
            continue
        try:
            class_name, cp, methods, ref = parse_class(jar.read(item))
        except Exception:
            continue
        for name, desc, code in methods:
            ins = instructions(code, cp, ref)
            if item.filename == "game/h.class" and name == "bh" and desc == "()V":
                print(f"\n{item.filename} :: {name}{desc} (evolution screen branches)")
                for off, op, value in ins:
                    if 1060 <= off <= 1155 or 1200 <= off <= 1310:
                        print(f"  {off:04d} op={op:02x} {value}")
            elif item.filename == "aq.class" and name == "a" and desc == "(BSB)S":
                print(f"\n{item.filename} :: {name}{desc} (numeric-table lookup helper)")
                for off, op, value in ins:
                    print(f"  {off:04d} op={op:02x} {value}")
