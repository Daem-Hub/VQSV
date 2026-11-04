/*
 * Decompiled with CFR 0.152.
 */
public final class aa {
    private static o[] a;
    private static short[] b;
    private static short[][] c;

    public static void a() {
        a = new o[1000];
    }

    public static o a(int n2) {
        if (a[n2] == null) {
            aa.a[n2] = new o();
            byte[] byArray = new byte[20000];
            int[] nArray = new int[]{0};
            short[][] sArray = new short[5][4];
            ae.a(byArray, "/data/spr/spr_" + n2 + "_all(r)");
            aa.a[n2].b = ae.a(byArray, nArray);
            if (n2 >= 86 && n2 <= 185) {
                aa.a[n2].e = ae.b(byArray, nArray);
                for (int i2 = 0; i2 < sArray.length; ++i2) {
                    for (int i3 = 0; i3 < 4; ++i3) {
                        sArray[i2][i3] = i3 == 1 ? (short)(aa.a[n2].e[0][i3] + b[i2]) : aa.a[n2].e[0][i3];
                    }
                }
                aa.a[n2].e = sArray;
                aa.a[n2].f = ae.b(byArray, nArray);
                aa.a[n2].f = c;
            } else {
                aa.a[n2].e = ae.b(byArray, nArray);
                aa.a[n2].f = ae.b(byArray, nArray);
            }
            aa.a[n2].d = aa.a(ae.a(byArray, nArray), aa.a[n2].e.length);
            aa.a[n2].c = aa.a(ae.a(byArray, nArray), aa.a[n2].e.length);
        }
        ++aa.a[n2].a;
        return a[n2];
    }

    private static short[][] a(short[] sArray, int n2) {
        if (sArray == null) {
            return null;
        }
        short[][] sArrayArray = new short[n2][];
        for (int i2 = 0; i2 < sArray.length / 5; ++i2) {
            sArrayArray[sArray[i2 * 5]] = ae.a(sArrayArray[sArray[i2 * 5]], new short[]{sArray[i2 * 5 + 1], sArray[i2 * 5 + 2], sArray[i2 * 5 + 3], sArray[i2 * 5 + 4]});
        }
        return sArrayArray;
    }

    public static void b(int n2) {
        --aa.a[n2].a;
        if (aa.a[n2].a <= 0) {
            aa.a[n2].a = 0;
            aa.c(n2);
        }
    }

    public static boolean c(int n2) {
        if (a[n2] != null) {
            aa.a[n2].a = 0;
            aa.a[n2] = null;
            return true;
        }
        return false;
    }

    static {
        b = new short[]{0, 10, 3, 7, -10};
        c = new short[][]{{2, 0}, {1, 0, 1, 1, 1, 2, 1, 3, 1, 2}, {5, 0, 5, 4}};
    }
}

