/*
 * Decompiled with CFR 0.152.
 */
package a.b;

import a.b.h;
import a.e;

public final class f {
    private static h[] a;

    public static void a(int n2) {
        a = new h[1000];
    }

    public static h b(int n2) {
        if (a[n2] == null) {
            f.a[n2] = new h();
            byte[] byArray = new byte[20000];
            int[] nArray = new int[]{0};
            e.a(byArray, "/data/spr/spr_" + n2 + "_all(r)", 0);
            short[] cfr_ignored_0 = f.a[n2].b;
            f.a[n2].b = e.a(byArray, nArray);
            short[][] cfr_ignored_1 = f.a[n2].e;
            f.a[n2].e = e.b(byArray, nArray);
            short[][] cfr_ignored_2 = f.a[n2].f;
            f.a[n2].f = e.b(byArray, nArray);
            f.a[n2].d = f.a(e.a(byArray, nArray), f.a[n2].e.length);
            f.a[n2].c = f.a(e.a(byArray, nArray), f.a[n2].e.length);
        }
        ++f.a[n2].a;
        return a[n2];
    }

    private static short[][] a(short[] sArray, int n2) {
        if (sArray == null) {
            return null;
        }
        short[][] sArrayArray = new short[n2][];
        for (int i2 = 0; i2 < sArray.length / 5; ++i2) {
            sArrayArray[sArray[i2 * 5]] = e.a(sArrayArray[sArray[i2 * 5]], new short[]{sArray[i2 * 5 + 1], sArray[i2 * 5 + 2], sArray[i2 * 5 + 3], sArray[i2 * 5 + 4]});
        }
        return sArrayArray;
    }

    public static void c(int n2) {
        --f.a[n2].a;
        if (f.a[n2].a <= 0) {
            f.a[n2].a = 0;
        }
    }

    public static boolean d(int n2) {
        if (f.a[n2].a <= 0) {
            f.a[n2].a = 0;
            f.a[n2] = null;
            return true;
        }
        return false;
    }
}

