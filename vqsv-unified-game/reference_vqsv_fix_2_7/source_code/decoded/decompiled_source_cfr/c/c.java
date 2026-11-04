/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
package c;

import c.a;
import c.b;
import c.d;
import c.e;
import c.f;
import c.g;
import c.h;
import c.k;
import c.l;
import c.m;
import javax.microedition.lcdui.Graphics;

public final class c {
    private f[] a = new f[200];
    private int b = 0;
    private int c = -1;
    private b d;
    private int[] e = new int[]{0, 1, 2, 3, 5, 6, 7, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    private int[] f = null;
    private int[] g = null;
    private d h;
    private a i;

    public c(a a2) {
        this.i = a2;
        this.d();
    }

    private void d() {
        this.c = -1;
        this.d = new b();
        this.d.a(0);
        this.d.c(-1);
        this.f = a.e.c(100);
        this.f();
    }

    public final b a() {
        return this.d;
    }

    public final void a(d d2) {
        this.h = d2;
    }

    public final void a(String object, int n2, boolean bl) {
        this.d();
        byte[] byArray = new byte[20000];
        a.e.b(byArray, (String)object, 0);
        object = new int[]{0};
        this.c = a.e.d(byArray, (int[])object);
        this.c = 0;
        a.e.d(byArray, (int[])object);
        short s = a.e.c(byArray, (int[])object);
        this.d.b(s);
        s = a.e.d(byArray, (int[])object);
        this.d.a(s);
        s = a.e.d(byArray, (int[])object);
        c c2 = this;
        this.d.a(s, c2.d);
        short s2 = a.e.d(byArray, (int[])object);
        c c3 = this;
        this.d.b(s2, c3.d);
        short s3 = a.e.d(byArray, (int[])object);
        c c4 = this;
        this.d.c(s3, c4.d);
        short s4 = a.e.d(byArray, (int[])object);
        c c5 = this;
        this.d.d(s4, c5.d);
        this.a[this.b] = this.d;
        this.b = 1;
        this.a(byArray, (int[])object, this.d, n2, false);
        object = this;
        int[] cfr_ignored_0 = ((c)object).g;
        ((c)object).f = a.e.c(50);
        super.a(((c)object).d, -1);
    }

    private void a(byte[] byArray, int[] nArray, b b2, int n2, boolean bl) {
        int n3;
        byte by;
        int n4;
        int n5 = a.e.c(byArray, nArray);
        if (n5 > 0) {
            byte[][] byArray2 = new byte[n5][4];
            for (n4 = 0; n4 < n5; ++n4) {
                byArray2[n4][0] = a.e.c(byArray, nArray);
                byArray2[n4][1] = a.e.c(byArray, nArray);
                byArray2[n4][2] = a.e.c(byArray, nArray);
                byArray2[n4][3] = a.e.c(byArray, nArray);
            }
            b2.a(byArray2);
        }
        short s = a.e.c(byArray, nArray);
        for (n4 = 0; n4 < s; ++n4) {
            int n6;
            int n7;
            n5 = a.e.c(byArray, nArray);
            l l2 = new l(n5);
            new l(n5).g = a.e.c(byArray, nArray) != 0;
            l2.d = a.e.d(byArray, nArray);
            l2.a = a.e.d(byArray, nArray);
            l2.h = a.e.c(byArray, nArray);
            l2.i = a.e.c(byArray, nArray);
            for (n7 = 0; n7 < l2.a; ++n7) {
                by = a.e.d(byArray, nArray);
                l2.b[n7] = by;
                n6 = a.e.d(byArray, nArray);
                byte[] byArray3 = new byte[n6];
                for (int i2 = 0; i2 < n6; ++i2) {
                    byArray3[i2] = a.e.c(byArray, nArray);
                }
                l2.o.addElement(a.e.a(byArray3));
            }
            l2.c = new int[l2.d + this.e.length][][];
            for (n7 = 0; n7 < l2.c.length; ++n7) {
                l2.c[n7] = new int[0][];
            }
            n7 = a.e.d(byArray, nArray);
            for (by = 0; by < n7; ++by) {
                n6 = a.e.d(byArray, nArray);
                int n8 = a.e.d(byArray, nArray);
                int[][] nArrayArray = new int[n8][];
                for (n3 = 0; n3 < n8; ++n3) {
                    nArrayArray[n3] = new int[5];
                    nArrayArray[n3][0] = a.e.d(byArray, nArray);
                    nArrayArray[n3][1] = a.e.d(byArray, nArray);
                    nArrayArray[n3][2] = a.e.d(byArray, nArray);
                    nArrayArray[n3][3] = a.e.d(byArray, nArray);
                    nArrayArray[n3][4] = a.e.d(byArray, nArray);
                }
                l2.c[n6] = nArrayArray;
            }
            if (n5 == 0) {
                b2.a = l2;
                continue;
            }
            b2.b = l2;
        }
        n4 = a.e.d(byArray, nArray);
        for (n5 = 0; n5 < n4; ++n5) {
            int n9;
            byte by2;
            f f2;
            int n10 = a.e.c(byArray, nArray);
            if (n10 == 0) {
                f2 = new b();
                ((b)f2).b(n10);
                ((b)f2).a(a.e.d(byArray, nArray));
                ((b)f2).a(a.e.d(byArray, nArray), this.a());
                ((b)f2).b(a.e.d(byArray, nArray), this.a());
                ((b)f2).c(a.e.d(byArray, nArray), this.a());
                ((b)f2).d(a.e.d(byArray, nArray), this.a());
                ((b)f2).c(b2.b());
                if (b2.a != null) {
                    for (by = 0; by < b2.a.b.length; ++by) {
                        if (b2.a.b[by] != ((b)f2).b()) continue;
                        ((b)f2).a(b2.a);
                        break;
                    }
                }
                if (b2.b != null) {
                    for (by = 0; by < b2.b.b.length; ++by) {
                        if (b2.b.b[by] != ((b)f2).b()) continue;
                        ((b)f2).a(b2.b);
                        break;
                    }
                }
                this.a[this.b] = f2;
                ++this.b;
                b2.h()[n5] = f2;
                this.a(byArray, nArray, (b)b2.h()[n5], n2, bl);
                continue;
            }
            if (n10 == 1) {
                f2 = new h();
                ((h)f2).b(n10);
                ((h)f2).a(a.e.d(byArray, nArray));
                ((h)f2).a(a.e.d(byArray, nArray), this.a());
                ((h)f2).b(a.e.d(byArray, nArray), this.a());
                ((h)f2).c(a.e.d(byArray, nArray), this.a());
                ((h)f2).d(a.e.d(byArray, nArray), this.a());
                ((h)f2).a();
                ((h)f2).c = this.h;
                by = a.e.d(byArray, nArray);
                byte[] byArray4 = new byte[by];
                for (by2 = 0; by2 < by; ++by2) {
                    byArray4[by2] = a.e.c(byArray, nArray);
                }
                ((h)f2).i().a = a.e.a(byArray4);
                ((h)f2).i().b = a.e.c(byArray, nArray);
                ((h)f2).i().c = a.e.c(byArray, nArray);
                ((h)f2).i().d = a.e.c(byArray, nArray) != 0;
                ((h)f2).i().e = a.e.e(byArray, nArray);
                ((h)f2).i().f = a.e.e(byArray, nArray);
                ((h)f2).i().g = a.e.e(byArray, nArray);
                by2 = (byte)a.e.d(byArray, nArray);
                n9 = a.e.c(byArray, nArray);
                if (by2 < 0) {
                    ((h)f2).i().i = null;
                } else {
                    ((h)f2).i().i = new g();
                    ((h)f2).i().i.a = n9;
                    ((h)f2).i().i.a((int)by2);
                }
                ((h)f2).i().j = a.e.e(byArray, nArray);
                ((h)f2).i().k = a.e.e(byArray, nArray);
                ((h)f2).i().l = a.e.e(byArray, nArray);
                n3 = a.e.d(byArray, nArray);
                s = a.e.c(byArray, nArray);
                if (n3 < 0) {
                    ((h)f2).i().m = null;
                } else {
                    ((h)f2).i().m = new g();
                    ((h)f2).i().m.a(n3);
                    ((h)f2).i().m.a = s;
                }
                ((h)f2).i().h = a.e.c(byArray, nArray);
                if (((h)f2).i().i != null) {
                    ((h)f2).i().i.a(n2, bl, ((h)f2).i().h);
                }
                if (((h)f2).i().m != null) {
                    ((h)f2).i().m.a(n2, bl, ((h)f2).i().h);
                }
                ((h)f2).c(b2.b());
                if (b2.a != null) {
                    for (n10 = 0; n10 < b2.a.b.length; ++n10) {
                        if (b2.a.b[n10] != ((h)f2).b()) continue;
                        ((h)f2).a(b2.a);
                        break;
                    }
                }
                if (b2.b != null) {
                    for (n10 = 0; n10 < b2.b.b.length; ++n10) {
                        if (b2.b.b[n10] != ((h)f2).b()) continue;
                        ((h)f2).a(b2.b);
                        break;
                    }
                }
                this.a[this.b] = f2;
                ++this.b;
                b2.h()[n5] = f2;
                ((h)f2).a = a.e.c(byArray, nArray);
                ((h)f2).b = a.e.c(byArray, nArray);
                continue;
            }
            if (n10 != 2) continue;
            f2 = new m();
            ((m)f2).q(a.e.d(byArray, nArray));
            ((m)f2).a(a.e.d(byArray, nArray), this.a());
            ((m)f2).b(a.e.d(byArray, nArray), this.a());
            ((m)f2).a((int)a.e.c(byArray, nArray));
            ((m)f2).b(a.e.c(byArray, nArray));
            ((m)f2).c(a.e.c(byArray, nArray));
            ((m)f2).d(a.e.c(byArray, nArray));
            ((m)f2).e(a.e.c(byArray, nArray));
            ((m)f2).f(a.e.c(byArray, nArray));
            ((m)f2).g(a.e.c(byArray, nArray));
            ((m)f2).h(a.e.c(byArray, nArray));
            ((m)f2).k(a.e.c(byArray, nArray));
            ((m)f2).l(a.e.c(byArray, nArray));
            ((m)f2).m(a.e.c(byArray, nArray));
            ((m)f2).n(a.e.c(byArray, nArray));
            ((m)f2).o(a.e.c(byArray, nArray));
            ((m)f2).p(a.e.c(byArray, nArray));
            ((m)f2).i(a.e.c(byArray, nArray));
            ((m)f2).j(a.e.c(byArray, nArray));
            ((m)f2).a = a.e.e(byArray, nArray);
            by = (byte)a.e.d(byArray, nArray);
            short s2 = a.e.c(byArray, nArray);
            if (by < 0) {
                ((m)f2).b = null;
            } else {
                ((m)f2).b = new g();
                ((m)f2).b.a((int)by);
                ((m)f2).b.a = s2;
                ((m)f2).b.a(n2, bl, (byte)s2);
            }
            by = (byte)a.e.d(byArray, nArray);
            s2 = a.e.c(byArray, nArray);
            if (by < 0) {
                ((m)f2).c = null;
            } else {
                ((m)f2).c = new g();
                ((m)f2).c.a((int)by);
                ((m)f2).c.a = s2;
                ((m)f2).c.a(n2, bl, (byte)s2);
            }
            ((m)f2).r(a.e.d(byArray, nArray));
            byte by3 = a.e.c(byArray, nArray);
            by2 = by3;
            if (by3 == 0) {
                ((m)f2).e = ((m)f2).n();
            } else if (by2 == 1) {
                ((m)f2).e = m.a(((m)f2).a(), ((m)f2).m());
                n9 = a.e.d(byArray, nArray);
                for (n3 = 0; n3 < n9; ++n3) {
                    s = a.e.d(byArray, nArray);
                    n10 = a.e.d(byArray, nArray);
                    by = a.e.c(byArray, nArray);
                    s2 = a.e.d(byArray, nArray);
                    by2 = (byte)a.e.d(byArray, nArray);
                    short s3 = a.e.d(byArray, nArray);
                    short s4 = a.e.d(byArray, nArray);
                    ((m)f2).e[s] = new e(n10, by, s2, by2, s3, s4);
                }
            }
            ((m)f2).s(b2.b());
            if (b2.a != null) {
                for (n9 = 0; n9 < b2.a.b.length; ++n9) {
                    if (b2.a.b[n9] != ((m)f2).b()) continue;
                    l cfr_ignored_0 = b2.a;
                    break;
                }
            }
            if (b2.b != null) {
                for (n9 = 0; n9 < b2.b.b.length; ++n9) {
                    if (b2.b.b[n9] != ((m)f2).b()) continue;
                    l cfr_ignored_1 = b2.b;
                    break;
                }
            }
            this.a[this.b] = f2;
            ++this.b;
            b2.h()[n5] = f2;
        }
    }

    private k a(f f2) {
        if (f2.j() != 0) {
            return new k(f2.c(), f2.d(), f2.e(), f2.f());
        }
        b b2 = (b)f2;
        if (b2.h() == null || b2.h()[0] == null) {
            return new k(f2.c(), f2.d(), f2.e(), f2.f());
        }
        int n2 = this.a((f)b2.h()[0]).a;
        int n3 = this.a((f)b2.h()[0]).a + this.a((f)b2.h()[0]).c;
        int n4 = this.a((f)b2.h()[0]).b;
        int n5 = this.a((f)b2.h()[0]).b + this.a((f)b2.h()[0]).d;
        for (int i2 = 0; i2 < b2.h().length && b2.h()[i2] != null; ++i2) {
            k k2 = this.a(b2.h()[i2]);
            if (n2 > k2.a) {
                n2 = k2.a;
            }
            if (n3 < k2.a + k2.c) {
                n3 = k2.a + k2.c;
            }
            if (n4 > k2.b) {
                n4 = k2.b;
            }
            if (n5 >= k2.b + k2.d) continue;
            n5 = k2.b + k2.d;
        }
        return new k(n2, n4, n3 - n2, n5 - n4);
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    public final void a(Graphics var1_1) {
        var3_2 = this.d;
        var2_3 = var1_1 /* !! */ ;
        var1_1 /* !! */  = this;
        var6_4 = var3_2;
        var5_6 = var2_3;
        var4_8 = var1_1 /* !! */ ;
        var7_10 = var6_4;
        if (var7_10.a == null && var7_10.b == null) ** GOTO lbl-1000
        if (var7_10.a == null && var7_10.b != null) {
            v0 = var7_10.b;
        } else if (var7_10.a != null && var7_10.b == null) {
            v0 = var7_10.a;
        } else lbl-1000:
        // 2 sources

        {
            v0 = var6_4 = null;
        }
        if (v0 != null && var6_4.d < var6_4.a) {
            var6_4.m;
            v1 = var8_11 = var4_8;
            var4_8 = var6_4;
            var7_10 = v1;
            var9_12 = 0;
            var10_13 = 0;
            var11_14 = 0;
            var12_15 = 0;
            if (var4_8.b[0] != -1) {
                var13_16 = super.a(var8_11.a(var4_8.b[0]));
                var9_12 = var13_16.a;
                var10_13 = var13_16.a + var13_16.c;
                var11_14 = var13_16.b;
                var12_15 = var13_16.b + var13_16.d;
                for (var14_17 = 1; var14_17 != var4_8.d; ++var14_17) {
                    var13_16 = super.a(var8_11.a(var4_8.b[var14_17]));
                    if (var9_12 > var13_16.a) {
                        var9_12 = var13_16.a;
                    }
                    if (var10_13 < var13_16.a + var13_16.c) {
                        var10_13 = var13_16.a + var13_16.c;
                    }
                    if (var11_14 > var13_16.b) {
                        var11_14 = var13_16.b;
                    }
                    if (var12_15 >= var13_16.b + var13_16.d) continue;
                    var12_15 = var13_16.b + var13_16.d;
                }
            }
            new k(var9_12, var11_14, var10_13 - var9_12, var12_15 - var11_14);
            var4_8 = new k();
            var7_10 = new k();
            if (var6_4.j != 0) {
                var6_4.j;
            }
            var5_6.setColor(255, 255, 255);
            var5_6.fillRect(var4_8.a, var4_8.b, var4_8.c, var4_8.d);
            var5_6.setColor(245, 222, 179);
            var5_6.drawRect(var4_8.a, var4_8.b, var4_8.c, var4_8.d);
            var5_6.setColor(95, 158, 160);
            var5_6.fillRect(var7_10.a, var7_10.b, var7_10.c, var7_10.d);
        }
        for (var4_9 = 0; var4_9 < var3_2.h().length && var3_2.h()[var4_9] != null; ++var4_9) {
            if (var3_2.h()[var4_9].g() != null) {
                var5_7 = false;
                var6_5 = a.e.a(var1_1 /* !! */ .g);
                if (var6_5 > 0 && var1_1 /* !! */ .g[var6_5 - 1] == var3_2.b()) {
                    var5_7 = true;
                }
                var3_2.h()[var4_9].g().a(var2_3, var3_2.h()[var4_9].b(), var5_7, var1_1 /* !! */ .g, true, var1_1 /* !! */ .d);
                continue;
            }
            var3_2.h()[var4_9].a(var2_3, false, true, var1_1 /* !! */ .d, var1_1 /* !! */ .g);
        }
    }

    public final void b() {
        boolean bl = true;
        b b2 = this.d;
        c c2 = this;
        for (int i2 = 0; i2 < b2.h().length && b2.h()[i2] != null; ++i2) {
            if (b2.h()[i2].g() != null) {
                if (a.e.a(c2.g) > 0) {
                    int[] cfr_ignored_0 = c2.g;
                    b2.b();
                }
                b2.h()[i2].g().a(b2.h()[i2].b(), c2.g, true, c2.d);
                continue;
            }
            b2.h()[i2].a(false, true, c2.d, c2.g);
        }
    }

    public final f a(int n2) {
        return a.e.a(this.d, n2);
    }

    public final boolean b(int n2) {
        boolean bl;
        f f2 = this.a((f)this.d, this.f, 0);
        if (f2.j() == 2) {
            m m2 = (m)f2;
            int n3 = n2;
            c c2 = this;
            boolean bl2 = false;
            switch (n3) {
                case 0: {
                    bl2 = m2.a((byte)0);
                    int[] cfr_ignored_0 = c2.g;
                    c2.i.a(new int[]{-1, -1, 0}, new int[]{-1, -1, -1, -1});
                    break;
                }
                case 1: {
                    bl2 = m2.a((byte)1);
                    int[] cfr_ignored_1 = c2.g;
                    c2.i.a(new int[]{-1, -1, 1}, new int[]{-1, -1, -1, -1});
                    break;
                }
                case 2: {
                    bl2 = m2.a((byte)2);
                    int[] cfr_ignored_2 = c2.g;
                    c2.i.a(new int[]{-1, -1, 2}, new int[]{-1, -1, -1, -1});
                    break;
                }
                case 3: {
                    bl2 = m2.a((byte)3);
                    int[] cfr_ignored_3 = c2.g;
                    c2.i.a(new int[]{-1, -1, 3}, new int[]{-1, -1, -1, -1});
                    break;
                }
                case 5: {
                    int[] cfr_ignored_4 = c2.g;
                    c2.i.a(new int[]{-1, -1, 4}, new int[]{-1, -1, -1, -1});
                    bl2 = true;
                    break;
                }
                case 7: {
                    bl2 = c2.e();
                    if (bl2) {
                        m2.d = false;
                        int[] cfr_ignored_5 = c2.g;
                        c2.i.a(new int[]{-1, -1, 7}, new int[]{-1, -1, -1, -1});
                        break;
                    }
                    int[] cfr_ignored_6 = c2.g;
                    c2.i.a(new int[]{-1, -1, 5}, new int[]{-1, -1, -1, -1});
                }
            }
            bl = bl2;
        } else {
            b b2 = (b)f2;
            int n4 = n2;
            c c3 = this;
            boolean bl3 = false;
            boolean bl4 = false;
            Object object = b2.a() != null ? b2.a() : (Object)new byte[][]{{0, 0, 1, -1}, {1, 1, 1, -1}, {2, 2, 1, -1}, {3, 3, 1, -1}, {5, 4, -1, -1}, {7, 5, -1, -1}};
            int[] nArray = new int[3];
            nArray[1] = b2.b != null ? b2.b.f : -1;
            nArray[0] = b2.a != null ? b2.a.f : -1;
            for (int i2 = 0; i2 < ((byte[][])object).length; ++i2) {
                if (object[i2][0] != n4) continue;
                bl4 = true;
                bl3 = false;
                switch (object[i2][1]) {
                    case 0: {
                        nArray[2] = 0;
                        if (b2.a != null) {
                            if (object[i2][3] != -1 && b2.a.f % (object[i2][3] + 1) == 0) {
                                b2.a.a(object[i2][3], c3.d);
                            } else {
                                b2.a.b(object[i2][2], c3.d);
                            }
                            bl3 = true;
                            nArray[0] = b2.a.f;
                            int[] cfr_ignored_7 = c3.g;
                            c3.i.a(nArray, new int[]{-1, -1, -1, -1});
                            break;
                        }
                        int[] cfr_ignored_8 = c3.g;
                        c3.i.a(nArray, new int[]{-1, -1, -1, -1});
                        break;
                    }
                    case 1: {
                        nArray[2] = 1;
                        if (b2.a != null) {
                            if (object[i2][3] != -1 && (b2.a.f + 1) % (object[i2][3] + 1) == 0) {
                                b2.a.b(object[i2][3], c3.d);
                            } else {
                                b2.a.a(object[i2][2], c3.d);
                            }
                            bl3 = true;
                            nArray[0] = b2.a.f;
                            int[] cfr_ignored_9 = c3.g;
                            c3.i.a(nArray, new int[]{-1, -1, -1, -1});
                            break;
                        }
                        int[] cfr_ignored_10 = c3.g;
                        c3.i.a(nArray, new int[]{-1, -1, -1, -1});
                        break;
                    }
                    case 2: {
                        nArray[2] = 2;
                        if (b2.b != null) {
                            if (object[i2][3] != -1 && b2.b.f % (object[i2][3] + 1) == 0) {
                                b2.b.a(object[i2][3], c3.d);
                            } else {
                                b2.b.b(object[i2][2], c3.d);
                            }
                            bl3 = true;
                            nArray[1] = b2.b.f;
                            int[] cfr_ignored_11 = c3.g;
                            c3.i.a(nArray, new int[]{-1, -1, -1, -1});
                            break;
                        }
                        int[] cfr_ignored_12 = c3.g;
                        c3.i.a(nArray, new int[]{-1, -1, -1, -1});
                        break;
                    }
                    case 3: {
                        nArray[2] = 3;
                        if (b2.b != null) {
                            if (object[i2][3] != -1 && (b2.b.f + 1) % (object[i2][3] + 1) == 0) {
                                b2.b.b(object[i2][3], c3.d);
                            } else {
                                b2.b.a(object[i2][2], c3.d);
                            }
                            bl3 = true;
                            nArray[1] = b2.b.f;
                            int[] cfr_ignored_13 = c3.g;
                            c3.i.a(nArray, new int[]{-1, -1, -1, -1});
                            break;
                        }
                        int[] cfr_ignored_14 = c3.g;
                        c3.i.a(nArray, new int[]{-1, -1, -1, -1});
                        break;
                    }
                    case 4: {
                        if (b2.b != null) {
                            bl3 = b2.b.f >= a.e.a(b2.b.b) ? c3.a(b2, b2.b.b[b2.b.f - b2.b.e]) : c3.a(b2, b2.b.b[b2.b.f]);
                        }
                        if (!bl3 && b2.a != null) {
                            bl3 = b2.a.f >= a.e.a(b2.a.b) ? c3.a(b2, b2.a.b[b2.a.f - b2.a.e]) : c3.a(b2, b2.a.b[b2.a.f]);
                        }
                        if (bl3) {
                            nArray[2] = 6;
                            int[] cfr_ignored_15 = c3.g;
                            c3.i.a(nArray, new int[]{-1, -1, -1, -1});
                            break;
                        }
                        nArray[2] = 4;
                        int[] cfr_ignored_16 = c3.g;
                        c3.i.a(nArray, new int[]{-1, -1, -1, -1});
                        break;
                    }
                    case 5: {
                        bl3 = c3.e();
                        if (bl3) {
                            nArray[2] = 7;
                            int[] cfr_ignored_17 = c3.g;
                            c3.i.a(nArray, new int[]{-1, -1, -1, -1});
                            break;
                        }
                        nArray[2] = 5;
                        int[] cfr_ignored_18 = c3.g;
                        c3.i.a(nArray, new int[]{-1, -1, -1, -1});
                    }
                }
                if (bl3) break;
            }
            if (!bl4) {
                switch (n4) {
                    case 14: 
                    case 15: 
                    case 16: 
                    case 17: 
                    case 18: 
                    case 19: 
                    case 20: 
                    case 21: 
                    case 22: 
                    case 23: {
                        nArray[2] = n4 - 6;
                        int[] cfr_ignored_19 = c3.g;
                        c3.i.a(nArray, new int[]{-1, -1, -1, -1});
                    }
                }
            }
            bl = bl3;
        }
        return bl;
    }

    private boolean e() {
        boolean bl = false;
        int n2 = -1;
        for (int i2 = a.e.a(this.g) - 2; i2 >= 0; --i2) {
            f f2 = a.e.a(this.d, this.g[i2]);
            if (((b)f2).a == null && ((b)f2).b == null) continue;
            bl = true;
            n2 = f2.b();
            break;
        }
        if (bl) {
            this.f = this.c(n2);
            if (this.f == null) {
                this.f = a.e.c(50);
            }
            this.f();
        }
        return bl;
    }

    private boolean a(f f2, int n2) {
        if (f2.j() == 1) {
            return false;
        }
        int n3 = this.a(f2, n2, true);
        if (n3 == -1) {
            return false;
        }
        this.f = this.c(n3);
        if (this.f == null) {
            this.f = a.e.c(50);
        }
        this.f();
        return true;
    }

    private void f() {
        this.g = a.e.c(50);
        f f2 = this.d;
        int n2 = 0;
        ++n2;
        this.g[0] = f2.b();
        for (int i2 = 0; i2 < this.f.length && this.f[i2] != -1; ++i2) {
            f2 = f2.h()[this.f[i2]];
            this.g[n2++] = f2.b();
        }
    }

    private int a(f f2, int n2, boolean bl) {
        if (f2.j() == 2 && n2 == -1) {
            if (bl) {
                ((m)f2).d = true;
            }
            return f2.b();
        }
        if ((((b)f2).a != null || ((b)f2).b != null) && n2 == -1) {
            return f2.b();
        }
        for (int i2 = 0; i2 < f2.h().length && f2.h()[i2] != null; ++i2) {
            int n3;
            if (f2.h()[i2].j() == 1 || n2 != -1 && f2.h()[i2].b() != n2 || (n3 = this.a(f2.h()[i2], -1, bl)) == -1) continue;
            return n3;
        }
        return -1;
    }

    private int[] c(int n2) {
        int[] nArray = a.e.c(50);
        f f2 = a.e.a(this.d, n2);
        while (f2.k() != -1) {
            f f3 = a.e.a(this.d, f2.k());
            for (int i2 = 0; i2 < f3.h().length && f3.h()[i2] != null; ++i2) {
                if (f3.h()[i2].b() != f2.b()) continue;
                a.e.a(nArray, 0, i2);
                break;
            }
            f2 = a.e.a(this.d, f2.k());
        }
        return nArray;
    }

    private f a(f f2, int[] nArray, int n2) {
        int n3;
        while ((n3 = a.e.a(nArray)) != 0) {
            if (n2 == n3 - 1) {
                return f2.h()[nArray[n2]];
            }
            f f3 = f2.h()[nArray[n2]];
            ++n2;
            f2 = f3;
        }
        return f2;
    }

    public final void c() {
        this.d.l();
        this.e = null;
        this.f = null;
        this.g = null;
        this.i = null;
        this.a = null;
    }
}

