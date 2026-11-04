/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
package game;

import a.a;
import game.e;
import game.l;
import game.n;
import javax.microedition.lcdui.Graphics;

public final class d {
    private static int c = 0;
    private char[] d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int[] i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    public static boolean a = false;
    private int r;
    public static boolean b = false;
    private byte s;
    private static d t;
    private boolean u;
    private int v;
    private int[][] w;
    private int x;
    private char[] y;
    private char[] z;
    private String[] A;
    private int B;
    private long C;
    private int D;

    public d() {
        a.a.o();
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = new int[10];
        this.j = 0;
        this.k = 0;
        this.l = 2;
        this.m = 0;
        this.p = 0;
        this.q = 2;
        this.r = 0;
        this.s = 0;
        this.v = 0;
        this.w = null;
        this.x = 0;
        this.y = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        this.z = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        this.A = new String[]{"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
    }

    public static d a() {
        if (t == null) {
            t = new d();
        }
        return t;
    }

    /*
     * Enabled aggressive block sorting
     */
    public final void b() {
        switch (c) {
            case 0: {
                if (this.g < this.d.length) {
                    if (this.g - this.f >= this.q * this.e + this.h) {
                        this.f = this.g;
                        this.h = 0;
                        this.i[0] = this.i[this.k];
                        a = false;
                        return;
                    }
                    while (this.g - this.f < this.q * this.e + this.h && this.g < this.d.length) {
                        if (this.d[this.g] == '#') {
                            this.h += 7;
                            this.g += 7;
                            continue;
                        }
                        ++this.g;
                    }
                }
                game.e.B = null;
                this.i[0] = this.i[this.k];
                b = false;
                this.f = 0;
                this.g = 0;
                this.h = 0;
                a = false;
                return;
            }
            case 1: {
                b = false;
                this.f = 0;
                this.g = 0;
                this.h = 0;
                return;
            }
            case 2: {
                return;
            }
            case 3: {
                if (this.r >= this.q * (this.x + 1)) return;
                if (this.q * (this.x + 1) > this.w.length) {
                    this.r = this.w.length;
                    return;
                }
                this.r = this.q * (this.x + 1);
                return;
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    public final void a(Graphics var1_1) {
        block21: {
            var2_2 = var1_1;
            var1_1 = this;
            if (!game.d.b) break block21;
            block0 : switch (game.d.c) {
                case 0: {
                    switch (var1_1.s) {
                        case 0: {
                            var2_2.setColor(0xFFFFFF);
                            super.a(var2_2, var1_1.n, var1_1.o);
                            break;
                        }
                        case 1: {
                            super.a(var2_2, var1_1.n, var1_1.o - (a.a.o() >> 1));
                        }
                    }
                    break;
                }
                case 1: 
                case 2: {
                    var4_3 = game.d.c == 1 ? 0 : var1_1.m;
                    switch (var1_1.s) {
                        case 0: {
                            var3_5 = 0;
                            while (var3_5 < a.e.a.length) {
                                game.n.a(var2_2, a.e.a[var3_5], var1_1.n, var1_1.o + var3_5 * (game.n.a + 1) + var4_3, 0xFFFFFF);
                                ++var3_5;
                            }
                            break block0;
                        }
                        case 1: {
                            var3_6 = 0;
                            while (var3_6 < a.e.a.length) {
                                var2_2.setColor(0xFFFFFF);
                                game.n.b(var2_2, a.e.a[var3_6], var1_1.n, var1_1.o + (game.n.a + 1) * (a.e.a.length >> var3_6 + 1) + var4_3, 17);
                                ++var3_6;
                            }
                            break block9;
                        }
                    }
                    break;
                }
                case 3: {
                    var6_8 = var1_1.o;
                    var5_9 = var1_1.n;
                    var4_4 = var2_2;
                    var3_7 = var1_1;
                    var7_10 = var3_7.d.length;
                    System.out.println("drawDialogRow: startLine=" + var3_7.x + ", m=" + var3_7.q + ", n=" + var3_7.r);
                    var8_11 = var3_7.x * var3_7.q;
                    while (var8_11 < var3_7.r) {
                        var9_12 = game.n.a * (var8_11 - var3_7.x * var3_7.q) + var6_8;
                        var10_13 = var5_9;
                        var11_14 = var3_7.w[var8_11][0];
                        ** GOTO lbl65
                        {
                            var12_15 = "";
                            var13_17 = 0;
                            while (var13_17 < 7) {
                                var12_15 = String.valueOf(var12_15) + var3_7.d[var11_14 + var13_17];
                                ++var13_17;
                            }
                            var3_7.j = ++var3_7.j >= var3_7.i.length ? 0 : var3_7.j;
                            var3_7.i[var3_7.j] = super.a(var12_15);
                            var4_4.setColor(var3_7.i[var3_7.j]);
                            var11_14 += 7;
                            do {
                                if (var11_14 < var7_10 && var3_7.d[var11_14] == '#') continue block16;
                                var4_4.setColor(var3_7.i[var3_7.j]);
                                if (var11_14 < var7_10) {
                                    var12_16 = var3_7.d[var11_14];
                                    game.n.a(var4_4, var12_16, var10_13, var9_12);
                                    var10_13 += game.n.a(var12_16);
                                }
                                ++var11_14;
lbl65:
                                // 2 sources

                            } while (var11_14 < var3_7.w[var8_11][1]);
                        }
                        ++var8_11;
                    }
                    var3_7.k = var3_7.j;
                    var3_7.j = 0;
                }
            }
            if (var1_1.u && game.d.a && var1_1.r % 10 < 5) {
                var2_2.setColor(0xFFFFFF);
                var2_2.drawString("Nh\u1ea5n 0 \u0111\u1ec3 ti\u1ebfp t\u1ee5c", a.a.g() >> 1, a.a.h() - 8, 33);
            }
        }
    }

    public final void c() {
        this.d = null;
        a.e.a = null;
        this.r = 0;
    }

    public final void a(byte by, String string, int n2) {
        this.d = string.toCharArray();
        c = by;
        switch (by) {
            case 0: {
                d d2 = this;
                int n3 = d2.d.length;
                int n4 = 0;
                int n5 = 0;
                int n6 = d2.D - 10;
                int n7 = n6 - game.n.b;
                StringBuffer stringBuffer = new StringBuffer();
                while (d2.g < n3) {
                    char c2 = d2.d[d2.g];
                    if (c2 == '#') {
                        d2.h += 7;
                        d2.g += 7;
                        continue;
                    }
                    ++d2.g;
                    int n8 = game.n.a(c2);
                    int n9 = n5 + n8;
                    stringBuffer.append(c2);
                    if (n5 > n6 || c2 == ' ' && n5 > n7) {
                        n9 = 0;
                        if (c2 != ' ') {
                            n9 = n8 + 0;
                        }
                        ++n4;
                        stringBuffer = new StringBuffer();
                    }
                    n5 = n9;
                }
                if (n5 > 0) {
                    ++n4;
                }
                this.q = n4;
                break;
            }
            case 1: {
                break;
            }
            case 2: {
                break;
            }
            case 3: {
                this.x = 0;
                this.f();
                this.a(0, 80);
            }
        }
        this.f = 0;
        this.g = 0;
        this.h = 0;
        b = true;
        a = false;
        boolean bl = false;
        d d3 = this;
        this.u = bl;
        this.s = (byte)n2;
    }

    public final void a(int n2, int n3) {
        this.n = n2;
        this.o = n3;
        this.D = a.a.g() - 2 * this.n;
    }

    private void f() {
        c = 3;
        int n2 = 0;
        char[] cArray = this.d;
        int n3 = this.d.length;
        int[][] nArray = new int[50][2];
        int n4 = 0;
        int n5 = this.D - 10;
        int n6 = n5 - game.n.b;
        int n7 = 0;
        int n8 = 0;
        while (n8 < n3) {
            char c2 = cArray[n8];
            if (c2 == '#') {
                n8 += 7;
                continue;
            }
            int n9 = game.n.a(c2);
            int n10 = n4 + n9;
            if (n4 > n5 || c2 == ' ' && n4 > n6) {
                n10 = 0;
                if (c2 != ' ') {
                    n10 = n9 + 0;
                }
                nArray[n2][0] = n7;
                nArray[n2][1] = n8;
                n7 = n8;
                ++n2;
            }
            n4 = n10;
            ++n8;
        }
        if (n4 > 0) {
            nArray[n2][0] = n7;
            nArray[n2][1] = this.m;
            ++n2;
        }
        this.w = new int[n2][2];
        n8 = 0;
        while (n8 < n2) {
            System.arraycopy(nArray[n8], 0, this.w[n8], 0, 2);
            ++n8;
        }
    }

    public final void b(int n2, int n3) {
        this.D = n2;
        int n4 = game.n.a('w');
        this.e = n2 / n4;
        this.q = n3 / (game.n.a + 1);
    }

    /*
     * Unable to fully structure code
     */
    private void a(Graphics var1_1, int var2_2, int var3_3) {
        this.p = this.f;
        var4_4 = this.d;
        var5_5 = this.d.length;
        var6_6 = var2_2;
        var7_7 = this.D + var2_2 - 10;
        var8_8 = var7_7 - game.n.b;
        var9_9 = this.f;
        ** GOTO lbl36
        {
            var10_10 = "";
            var11_12 = 0;
            while (var11_12 < 7) {
                var10_10 = String.valueOf(var10_10) + var4_4[var9_9 + var11_12];
                ++var11_12;
            }
            this.j = ++this.j >= this.i.length ? 0 : this.j;
            this.i[this.j] = this.a(var10_10);
            var1_1.setColor(this.i[this.j]);
            var9_9 += 7;
            do {
                if (var9_9 < var5_5 && var4_4[var9_9] == '#') continue block0;
                var1_1.setColor(this.i[this.j]);
                if (var9_9 < var5_5) {
                    var10_11 = var4_4[var9_9];
                    var11_12 = game.n.a(var10_11);
                    var12_13 = var6_6 + var11_12;
                    if (var12_13 > var7_7 || var10_11 == ' ' && var12_13 > var8_8) {
                        var6_6 = var12_13 = var2_2;
                        if (var10_11 != ' ') {
                            var12_13 = var6_6 + var11_12;
                        }
                        var3_3 += game.n.a + 1;
                    }
                    game.n.a(var1_1, var10_11, var6_6, var3_3);
                    var6_6 = var12_13;
                }
                ++this.p;
                ++var9_9;
lbl36:
                // 2 sources

            } while (var9_9 < this.g);
        }
        this.k = this.j;
        this.j = 0;
    }

    private int a(String object) {
        object = ((String)object).toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        int n2 = 0;
        while (n2 < ((Object)object).length) {
            stringBuffer.append(this.a((char)object[n2]));
            ++n2;
        }
        object = stringBuffer.toString().toCharArray();
        n2 = 0;
        int n3 = 0;
        while (n3 < ((Object)object).length) {
            if (object[n3] == 49) {
                n2 += 1 << ((Object)object).length - n3 - 1;
            }
            ++n3;
        }
        return n2;
    }

    private String a(char c2) {
        int n2 = 0;
        while (n2 < this.y.length) {
            if (c2 == this.y[n2] || c2 == this.z[n2]) {
                return this.A[n2];
            }
            ++n2;
        }
        return "0000";
    }

    public final void d() {
        d d2 = this;
        char[] cArray = d2.d;
        if (b) {
            switch (c) {
                case 0: {
                    int n2 = cArray.length;
                    int n3 = d2.D - 10;
                    int n4 = n3 - game.n.b;
                    int n5 = 1;
                    int n6 = 0;
                    while (n6 < 2) {
                        if (d2.g < n2) {
                            char c2;
                            d2.C = 0L;
                            if (d2.g == 0) {
                                d2.B = 0;
                            }
                            if ((c2 = cArray[d2.g]) == '#') {
                                d2.h += 7;
                                d2.g += 7;
                            } else {
                                int n7 = game.n.a(c2);
                                int n8 = d2.B + n7;
                                if (n8 > n3 || c2 == ' ' && n8 > n4) {
                                    n8 = 0;
                                    d2.B = 0;
                                    if (c2 != ' ') {
                                        n8 = n7 + 0;
                                    }
                                    if (++n5 > d2.q) {
                                        n5 = 0;
                                        d2.f = d2.g;
                                    }
                                }
                                d2.B = n8;
                                ++d2.g;
                            }
                        } else if (d2.C == 0L) {
                            d2.C = System.currentTimeMillis() + 2500L;
                        } else if (System.currentTimeMillis() > d2.C) {
                            d d3 = d2;
                            if (!d3.u) {
                                d2.c();
                                b = false;
                                if (game.l.B().c.b("/data/ui/dialog.ui")) {
                                    game.l.B().d.aF();
                                }
                            }
                            a = true;
                            ++d2.r;
                        }
                        ++n6;
                    }
                    return;
                }
                case 1: {
                    a = true;
                    ++d2.r;
                    return;
                }
                case 2: {
                    d2.m += d2.l;
                    if (d2.m <= a.e.a.length * (a.a.h() + a.a.h() / 2)) break;
                    d2.m = 0;
                    a = true;
                    b = false;
                    ++d2.r;
                    return;
                }
                case 3: {
                    if (d2.r >= d2.w.length) break;
                    ++d2.v;
                    if (d2.v < 20) break;
                    ++d2.r;
                    if (d2.r > d2.q * (d2.x + 1)) {
                        ++d2.x;
                        d2.i[0] = d2.i[d2.k];
                    }
                    d2.v = 0;
                }
            }
        }
    }

    public final void a(boolean bl) {
        this.u = bl;
    }

    public final boolean e() {
        return this.u;
    }
}

