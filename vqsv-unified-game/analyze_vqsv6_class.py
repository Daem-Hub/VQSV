import struct
import zipfile
import os
from pathlib import Path

PROJECT = Path(__file__).resolve().parent
BUNDLED_SOURCE = PROJECT / "sources" / "game"
SOURCE_ROOT = Path(os.environ.get("VQSV_SOURCE_ROOT", str(BUNDLED_SOURCE if BUNDLED_SOURCE.exists() else Path(r"D:\TOAN BO FILE\Download\game"))))
JAR = str(SOURCE_ROOT / "vqsv-6.jar")


def read_u2(data, p): return struct.unpack_from(">H", data, p)[0], p + 2
def read_u4(data, p): return struct.unpack_from(">I", data, p)[0], p + 4


def parse(data):
    p = 8
    count, p = read_u2(data, p)
    cp = [None] * count
    i = 1
    while i < count:
        tag = data[p]; p += 1
        if tag == 1:
            n, p = read_u2(data, p); cp[i] = (tag, data[p:p+n].decode("utf8", "replace")); p += n
        elif tag in (3, 4): cp[i] = (tag, data[p:p+4]); p += 4
        elif tag in (5, 6): cp[i] = (tag, data[p:p+8]); p += 8; i += 1
        elif tag in (7, 8, 16, 19, 20):
            n, p = read_u2(data, p); cp[i] = (tag, n)
        elif tag in (9, 10, 11, 12, 17, 18):
            a, p = read_u2(data, p); b, p = read_u2(data, p); cp[i] = (tag, a, b)
        elif tag == 15:
            a = data[p]; p += 1; b, p = read_u2(data, p); cp[i] = (tag, a, b)
        else: raise ValueError((tag, i, p))
        i += 1

    def utf(i): return cp[i][1] if i and cp[i] and cp[i][0] == 1 else f"#{i}"
    def ref(i):
        x = cp[i]
        if x[0] in (9, 10, 11):
            nt = cp[x[2]]
            return f"{utf(cp[x[1]][1])}.{utf(nt[1])}{utf(nt[2])}"
        if x[0] == 8: return repr(utf(x[1]))
        if x[0] == 7: return utf(x[1])
        return str(x)

    _, p = read_u2(data, p); this, p = read_u2(data, p); _, p = read_u2(data, p)
    n, p = read_u2(data, p); p += n * 2
    n, p = read_u2(data, p)
    for _ in range(n):
        p += 6; ac, p = read_u2(data, p)
        for _ in range(ac): _, p = read_u2(data, p); size, p = read_u4(data, p); p += size
    n, p = read_u2(data, p); methods = []
    for _ in range(n):
        _, p = read_u2(data, p); ni, p = read_u2(data, p); di, p = read_u2(data, p); ac, p = read_u2(data, p); code = b""
        for _ in range(ac):
            ai, p = read_u2(data, p); size, p = read_u4(data, p); end = p + size
            if utf(ai) == "Code":
                _, q = read_u2(data, p); _, q = read_u2(data, q); length, q = read_u4(data, q); code = data[q:q+length]
            p = end
        methods.append((utf(ni), utf(di), code))
    return utf(cp[this][1]), cp, methods, ref


with zipfile.ZipFile(JAR) as jar:
  for class_file in ("aq.class", "am.class"):
    name, cp, methods, ref = parse(jar.read(class_file))
    print("CLASS", name)
    constants = [x[1] for x in cp if x and x[0] == 1 and any(k in x[1].lower() for k in ("map", "npc", "event", "data", "layer", "tile"))]
    if constants: print("CONSTANTS", constants)
    for method, desc, code in methods:
        refs = []
        p = 0
        while p < len(code):
            op = code[p]
            if op == 0x12:
                idx = code[p+1]; refs.append((p, idx)); p += 2; continue
            if op in (0x13, 0x14, 0xb2, 0xb3, 0xb4, 0xb5, 0xb6, 0xb7, 0xb8, 0xb9, 0xba, 0xbb, 0xbd, 0xc0, 0xc1):
                idx = struct.unpack_from(">H", code, p+1)[0]; refs.append((p, idx))
            if op in (0x11, 0x13, 0x14, 0x84, *range(0x99,0xa9), 0xb2,0xb3,0xb4,0xb5,0xb6,0xb7,0xb8,0xbb,0xbd,0xc0,0xc1,0xc6,0xc7): p += 3
            elif op in (0x10,0x12,0x15,0x16,0x17,0x18,0x19,0x36,0x37,0x38,0x39,0x3a,0xa9,0xbc): p += 2
            elif op in (0xb9,0xba,0xc8,0xc9): p += 5
            elif op == 0xc5: p += 4
            elif op in (0xaa,0xab,0xc4): break
            else: p += 1
        picked = []
        for off, idx in refs:
            x = cp[idx]
            if x and x[0] in (8,9,10,11):
                value = ref(idx)
                if x[0] != 8 or "map" in value.lower() or "event" in value.lower(): picked.append(f"{off}:{value}")
        calls = [ref(idx) for _, idx in refs if cp[idx] and cp[idx][0] in (10,11)]
        if picked:
            print(f"METHOD {method}{desc} bytes={len(code)}")
            print("  " + " | ".join(picked))
        if method == "a" and desc == "(I)V":
            ops = {0x00:"nop",0x01:"aconst_null",0x02:"iconst_m1",0x03:"iconst_0",0x04:"iconst_1",0x05:"iconst_2",0x06:"iconst_3",0x07:"iconst_4",0x08:"iconst_5",0x10:"bipush",0x11:"sipush",0x12:"ldc",0x13:"ldc_w",0x14:"ldc2_w",0x15:"iload",0x16:"lload",0x17:"fload",0x18:"dload",0x19:"aload",0x36:"istore",0x37:"lstore",0x38:"fstore",0x39:"dstore",0x3a:"astore",0x57:"pop",0x59:"dup",0x60:"iadd",0x64:"isub",0x68:"imul",0x6c:"idiv",0x70:"irem",0x74:"ineg",0x78:"ishl",0x7a:"ishr",0x7e:"iand",0x80:"ior",0x84:"iinc",0x91:"i2b",0x93:"i2s",0x99:"ifeq",0x9a:"ifne",0x9b:"iflt",0x9c:"ifge",0x9d:"ifgt",0x9e:"ifle",0xa7:"goto",0xac:"ireturn",0xb0:"areturn",0xb1:"return",0xb2:"getstatic",0xb3:"putstatic",0xb4:"getfield",0xb5:"putfield",0xb6:"invokevirtual",0xb7:"invokespecial",0xb8:"invokestatic",0xb9:"invokeinterface",0xbb:"new",0xbc:"newarray",0xbd:"anewarray",0xbe:"arraylength",0x2e:"iaload",0x32:"aaload",0x33:"baload",0x34:"caload",0x35:"saload",0x4f:"iastore",0x53:"aastore",0x54:"bastore",0x55:"castore",0x56:"sastore",0xc0:"checkcast",0xc1:"instanceof",0xbf:"athrow",0xc6:"ifnull",0xc7:"ifnonnull"}
            lengths={0x10:2,0x12:2,0x15:2,0x16:2,0x17:2,0x18:2,0x19:2,0x36:2,0x37:2,0x38:2,0x39:2,0x3a:2,0xa9:2,0xbc:2,0x11:3,0x13:3,0x14:3,0x84:3,0xb2:3,0xb3:3,0xb4:3,0xb5:3,0xb6:3,0xb7:3,0xb8:3,0xbb:3,0xbd:3,0xc0:3,0xc1:3,0xc6:3,0xc7:3,0xb9:5}
            q=0
            while q<len(code):
                op=code[q]; size=lengths.get(op,3 if 0x99<=op<=0xa8 else 1); raw=code[q+1:q+size].hex()
                detail=""
                if op in (0x12,): detail=ref(code[q+1])
                elif op in (0x13,0x14,0xb2,0xb3,0xb4,0xb5,0xb6,0xb7,0xb8,0xb9,0xbb,0xbd,0xc0,0xc1): detail=ref(struct.unpack_from('>H',code,q+1)[0])
                elif 0x99<=op<=0xa8 or op in (0xc6,0xc7): detail=str(q+struct.unpack_from('>h',code,q+1)[0])
                elif op==0x10: detail=str(struct.unpack_from('>b',code,q+1)[0])
                elif op==0x11: detail=str(struct.unpack_from('>h',code,q+1)[0])
                elif op in (0x15,0x19,0x36,0x3a,0xbc): detail=str(code[q+1])
                print(f"{q:04d} {ops.get(op,hex(op)):14} {detail} [{raw}]")
                q+=size
