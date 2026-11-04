/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
package a.a;

import a.a;
import a.a.b;
import a.a.c;
import a.a.d;
import a.e;
import c.j;
import game.k;
import game.l;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class f {
    private static f i;
    public int a = -1;
    private int j = -1;
    public int b = -1;
    public int c = -1;
    public boolean d;
    public boolean e;
    private int k;
    public int f;
    private int l = 0;
    private int m = 20;
    public int g = 5;
    private int n = -2013265920;
    private int[] o = null;
    private int[] p = null;
    private int[] q = null;
    private int r = 16;
    private int s = 4;
    private int t = 0;
    private int[][] u;
    private int[] v;
    private int w = 20;
    private int x;
    private static j y;
    private static k z;
    private static short[][] A;
    private short[][] B = new short[][]{{-20, 20, 20, -20, -15, 15, -15, 15, -5, 5, -5, 5}, {-5, 5, 5, -5}, {-5, 10, -5}};
    private int C = 0;
    private int D = 0;
    private int E = 0;
    private int F = 0;
    private int G = 0;
    private int H = 0;
    private int I;
    private int J;
    private int K;
    private int L = 0;
    private int M = 0;
    private Image N = null;
    private c O = null;
    private int P;
    private int Q;
    private int R = 0;
    private int S = 0;
    private int T = 0;
    private int U = 0;
    private int[] V = new int[]{0xFFFFFF, 9115396};
    private static int W;
    private static int[] X;
    private b Y;
    private int Z = 0;
    private int aa = 0;
    public byte h = (byte)-1;
    private int ab;
    private int ac;
    private int ad;
    private int ae;
    private int af;
    private int ag;
    private static final int[][] ah;
    private byte ai = (byte)50;
    private Image[] aj;
    private Image ak = null;
    private int[][] al;
    private int am = 0;
    private int an = 0;
    private byte ao = 0;
    private boolean ap = false;
    private boolean aq = false;

    public static f a() {
        if (i == null) {
            i = new f();
        }
        y = c.j.a();
        z = game.k.a();
        return i;
    }

    public static void b() {
        A = null;
        A = a.e.a(a.e.a("/data/script/battleNpc.mid"));
    }

    public static void c() {
        A = null;
    }

    public final void a(Graphics graphics) {
        if (this.a == -1 && this.b == -1 && this.c == -1) {
            return;
        }
        switch (this.c) {
            case 18: {
                y.a(graphics);
                if (this.k < A.length) break;
                this.k = 0;
                this.c = -1;
                this.d = true;
                A = null;
                y.a("/data/ui/menu1.ui");
                return;
            }
        }
        switch (this.a) {
            case 0: 
            case 1: 
            case 2: {
                boolean bl;
                Graphics graphics2 = graphics;
                f f2 = this;
                int n2 = f2.n & 0xFFFFFF;
                boolean bl2 = false;
                int n3 = 0;
                if (f2.a == 0) {
                    n3 = f2.n;
                } else if (f2.a == 1) {
                    if (255 - f2.l * f2.g < 0) {
                        --f2.l;
                        f2.a = -1;
                        bl2 = true;
                    }
                    n3 = 255 - f2.l * f2.g << 24;
                    n3 = n2 | n3;
                } else if (f2.a == 2) {
                    if (f2.l * f2.g > 255) {
                        --f2.l;
                        bl2 = true;
                    }
                    n3 = f2.l * f2.g << 24;
                    n3 = n2 | n3;
                }
                n2 = f2.w;
                int n4 = a.a.h() / n2 + 1;
                int n5 = a.a.g() * n4;
                if (f2.o == null || f2.o.length != n5) {
                    f2.o = new int[n5];
                }
                if (f2.o[0] != n3) {
                    for (n5 = 0; n5 < f2.o.length; ++n5) {
                        f2.o[n5] = n3;
                    }
                }
                for (n5 = 0; n5 < n2; ++n5) {
                    graphics2.drawRGB(f2.o, 0, (int)a.a.g(), 0, n5 * n4, (int)a.a.g(), n4, true);
                }
                if (bl2) {
                    f2.f();
                    bl = true;
                } else {
                    ++f2.l;
                    bl = false;
                }
                this.d = bl;
                break;
            }
            case 3: {
                boolean bl;
                int n6;
                Graphics graphics3 = graphics;
                f f3 = this;
                int n7 = a.a.g();
                int n8 = a.a.h();
                int n9 = f3.n & 0xFFFFFF;
                int n10 = f3.m;
                int n11 = 255 / n10 / 2;
                int n12 = n7 / 2;
                n8 /= 2;
                int n13 = n12 * 200 / 120;
                int n14 = n13 * n13;
                boolean bl3 = false;
                if (f3.q == null) {
                    f3.q = new int[n13];
                }
                int n15 = n13 + n10 - (f3.l << 1);
                if (f3.l <= 0) {
                    bl3 = true;
                    n15 = n13 + n10 - (--f3.l << 1);
                }
                if (f3.o == null || f3.o.length != n12 * n8) {
                    f3.o = new int[n12 * n8];
                }
                if (f3.p == null || f3.p.length != n12 * n8) {
                    f3.p = new int[n12 * n8];
                }
                for (n6 = 0; n6 < f3.q.length; ++n6) {
                    n7 = n6 - n15;
                    n7 = n7 > n10 ? 255 : (n7 < -n10 ? 0 : 127 + n7 * n11);
                    f3.q[n6] = n9 | n7 << 24;
                }
                n6 = n13 - 1;
                n7 = n15 + n10;
                n9 = n15 - n10;
                if (n9 < 0) {
                    n9 = 0;
                }
                n7 = n7 * n14 / n6;
                n9 = n9 * n14 / n6;
                for (n10 = 0; n10 < n8; ++n10) {
                    n13 = n10 * n10;
                    n15 = n10 * n12;
                    for (int i2 = 0; i2 < n12; ++i2) {
                        n11 = n13 + i2 * i2;
                        f3.o[n15 + i2] = n11 > n7 ? -16777216 : (n11 < n9 ? 0 : f3.q[n6 * n11 / n14]);
                    }
                }
                if (f3.o != null) {
                    graphics3.drawRGB(f3.o, 0, n12, n12, n8, n12, n8, true);
                    graphics3.drawRGB(a.a.f.a(f3.o, f3.p, n12, n8, (byte)2), 0, n12, 0, n8, n12, n8, true);
                    graphics3.drawRGB(a.a.f.a(f3.o, f3.p, n12, n8, (byte)3), 0, n12, 0, 0, n12, n8, true);
                    graphics3.drawRGB(a.a.f.a(f3.o, f3.p, n12, n8, (byte)1), 0, n12, n12, 0, n12, n8, true);
                }
                if (bl3) {
                    f3.f();
                    bl = true;
                } else {
                    f3.l -= 10;
                    bl = false;
                }
                this.d = bl;
                break;
            }
            case 4: {
                if (this.d) {
                    this.a = -1;
                    graphics.fillRect(0, 0, (int)a.a.g(), (int)a.a.h());
                    break;
                }
                this.d = this.e(graphics);
                break;
            }
            case 5: {
                if (this.d) break;
                this.d = this.e(graphics);
                break;
            }
            case 6: {
                if (this.d) {
                    this.a = -1;
                    graphics.fillRect(0, 0, (int)a.a.g(), (int)a.a.h());
                    break;
                }
                this.d = this.d(graphics);
                break;
            }
            case 7: {
                if (this.k < a.a.h()) {
                    graphics.drawRGB(this.o, 0, (int)a.a.g(), 0, 0, (int)a.a.g(), this.k, true);
                    graphics.drawRGB(this.p, 0, (int)a.a.g(), 0, a.a.h() - this.k, (int)a.a.g(), this.k, true);
                    this.k += 20;
                } else {
                    graphics.drawRGB(this.o, 0, (int)a.a.g(), 0, 0, (int)a.a.g(), (int)a.a.h(), true);
                    graphics.drawRGB(this.p, 0, (int)a.a.g(), 0, 0, (int)a.a.g(), (int)a.a.h(), true);
                    if (this.Y == null) {
                        this.a(0);
                    }
                }
                if (this.Y == null) break;
                this.Y.a(graphics, 0, 0);
                break;
            }
            case 8: {
                if (this.k >= 5) {
                    graphics.drawImage(game.a.B().m, 0, 0, 20);
                }
                y.a(graphics);
                if (this.k < A.length) break;
                this.k = 0;
                this.a = -1;
                this.d = true;
                y.a("/data/ui/npcEnemy.ui");
                return;
            }
            case 9: {
                graphics.setColor(this.n);
                graphics.fillRect(0, 0, (int)a.a.g(), (int)a.a.h());
                break;
            }
            case 10: {
                if (this.k > this.L) break;
                if (this.k % 3 / (this.M + 1) == 0) {
                    graphics.setColor(0xFFFFFF);
                    graphics.fillRect(0, 0, (int)a.a.g(), (int)a.a.h());
                } else if (this.k % 3 / (this.M + 1) == 1) {
                    graphics.setColor(0);
                    graphics.fillRect(0, 0, (int)a.a.g(), (int)a.a.h());
                }
                ++this.k;
                break;
            }
            case 14: 
            case 15: {
                boolean bl;
                Graphics graphics4 = graphics;
                f f4 = this;
                boolean bl4 = false;
                int n16 = 0;
                if (f4.a == 15) {
                    n16 = f4.l;
                    if (n16 >= 255) {
                        n16 = 255;
                        f4.l = 255;
                        bl4 = true;
                    }
                } else if (f4.a == 14 && (n16 = 255 - f4.l) <= 0) {
                    n16 = 0;
                    f4.a = -1;
                    bl4 = true;
                }
                if (f4.O != null) {
                    f4.O = a.a.d.b(f4.O, n16);
                    graphics4.drawRGB(f4.O.a, 0, f4.O.b, f4.P - f4.O.b / 2, f4.Q - f4.O.c / 2, f4.O.b, f4.O.c, true);
                }
                if (bl4) {
                    f4.a = -1;
                    f4.f();
                    bl = true;
                } else {
                    f4.l += f4.g;
                    bl = false;
                }
                this.d = bl;
                break;
            }
            case 17: {
                graphics.setColor(this.V[this.n]);
                int n17 = this.R;
                int n18 = this.U;
                int n19 = this.T;
                Graphics graphics5 = graphics;
                graphics5.fillArc(n19 - n17, n18 - n17, n17 << 1, n17 << 1, 0, 360);
                break;
            }
            case 19: 
            case 20: {
                this.d = this.f(graphics);
            }
        }
        switch (this.b) {
            case 12: {
                graphics.setColor(0);
                graphics.fillRect(0, 0, this.I, this.J - this.f * this.J / this.G);
                graphics.fillRect(0, a.a.h() - this.K + this.f * this.K / this.G, this.I, this.K - this.f * this.K / this.G);
                return;
            }
            case 13: {
                graphics.setColor(0);
                graphics.fillRect(0, 0, this.I, this.f * this.J / this.G);
                graphics.fillRect(0, a.a.h() - this.f * this.K / this.G, this.I, this.f * this.K / this.G);
            }
        }
    }

    private void a(int n2) {
        this.aa = n2;
        switch (this.a) {
            case 7: {
                n2 = a.b.c.c[0][game.a.B().p(0)][17];
                switch (this.aa) {
                    case 0: {
                        short[] sArray = new short[]{8, 118, 160, n2, 0, 1, 0, 4, 0, 2, 1, 8, 0, -16, 10, 0, 0};
                        this.Y = new b();
                        this.Y.a(sArray);
                        this.Y.d(true);
                        this.Y.a();
                        return;
                    }
                    case 1: {
                        short[] sArray = new short[]{17, 118, 160, n2, 0, 1, 100, 255, 255, 255, 12, 0, 1, 1, 9};
                        this.Y.a(sArray);
                        this.Y.a();
                        return;
                    }
                    case 2: {
                        short[] sArray = new short[]{17, 118, 160, n2, 0, 1, 255, 255, 255, 255, 15, 0, 1, 1, 13};
                        this.Y.a(sArray);
                        this.Y.a();
                        return;
                    }
                    case 3: {
                        short[] sArray = new short[]{9, 118, 160, n2, 0, 1, 160, 255, 255, 255, 0, 4, 1};
                        this.Y.a(sArray);
                        this.Y.a();
                    }
                }
                return;
            }
        }
        this.Z = A[this.k].length;
    }

    public final void d() {
        if (this.a == -1 && this.b == -1 && this.c == -1) {
            return;
        }
        switch (this.a) {
            case 8: {
                if (this.aa < this.Z) {
                    z.c(this.k, A[this.k][this.aa]);
                    ++this.aa;
                } else {
                    ++this.k;
                    if (this.k < A.length) {
                        this.a(0);
                    }
                }
                y.c();
                break;
            }
            case 7: {
                if (this.Y == null || this.Y.d()) break;
                ++this.aa;
                if (this.aa >= 4) {
                    this.d = true;
                    this.a = -1;
                    this.k = 0;
                    this.Y = null;
                    return;
                }
                this.a(this.aa);
                break;
            }
            case 11: {
                if (this.C == 0) {
                    if (this.F >= this.B[this.D].length * this.E) {
                        this.a = -1;
                        this.d = true;
                        this.F = 0;
                        a.b.b.a().a = 1;
                        return;
                    }
                    a.b.b.a().d(this.B[this.D][this.F % this.B[this.D].length]);
                    ++this.F;
                    break;
                }
                if (this.F >= this.B[this.D].length * this.E) {
                    this.a = -1;
                    this.d = true;
                    this.F = 0;
                    a.b.b.a().a = 1;
                    return;
                }
                a.b.b.a().e(this.B[this.D][this.F % this.B[this.D].length]);
                ++this.F;
                break;
            }
            case 10: {
                if (this.k <= this.L) break;
                this.d = true;
                this.a = -1;
                this.k = 0;
                break;
            }
            case 17: {
                ++this.k;
                if (this.S == 0) {
                    if ((a.a.g() - this.T) * (a.a.g() - this.T) + (a.a.h() - this.U) * (a.a.h() - this.U) < this.R * this.R) {
                        this.k = 0;
                        this.d = true;
                    }
                    this.R += 10;
                    break;
                }
                if (this.S == 1) {
                    this.R -= 10;
                    if (this.R > 0) break;
                    this.k = 0;
                    this.a = -1;
                    this.d = true;
                    break;
                }
                if (this.k <= 10) {
                    this.R += 10;
                    break;
                }
                if (this.k > 10 && this.k <= 20) {
                    this.R -= 10;
                    break;
                }
                this.k = 0;
                this.d = true;
                this.a = -1;
            }
        }
        switch (this.b) {
            case 13: {
                this.f += this.H;
                if (this.f <= this.G) break;
                this.f = this.G;
                this.e = true;
                break;
            }
            case 12: {
                this.f += this.H;
                if (this.f <= this.G) break;
                this.f = 0;
                this.e = true;
                this.b = -1;
            }
        }
        switch (this.c) {
            case 18: {
                if (this.aa < this.Z) {
                    z.a(this.k, (int)A[this.k][this.aa]);
                    ++this.aa;
                } else {
                    ++this.k;
                    if (this.k < A.length) {
                        this.a(0);
                    }
                }
                y.c();
            }
        }
    }

    private boolean d(Graphics graphics) {
        if (this.k < 10) {
            if (this.k % 3 == 1) {
                graphics.setColor(0xFFFFFF);
                graphics.fillRect(0, 0, (int)a.a.g(), (int)a.a.h());
            } else {
                game.l.B();
                game.l.b(graphics);
                game.l.B().l.a(graphics);
            }
            ++this.k;
        } else {
            if (this.k >= a.a.g()) {
                graphics.setColor(0);
                graphics.fillRect(0, 0, (int)a.a.g(), (int)a.a.h());
                return true;
            }
            switch (this.x) {
                case 0: {
                    int n2;
                    graphics.setColor(0);
                    graphics.fillRect(0, 0, this.k, (int)a.a.j());
                    for (n2 = 1; n2 < 6; ++n2) {
                        graphics.fillRect(this.k + n2 * 15, 0, 15 - n2 * 3, (int)a.a.j());
                    }
                    graphics.fillRect(a.a.g() - this.k, (int)a.a.j(), this.k, (int)a.a.j());
                    for (n2 = 1; n2 < 6; ++n2) {
                        graphics.fillRect(a.a.g() - this.k - n2 * 15, (int)a.a.j(), 15 - n2 * 3, (int)a.a.j());
                    }
                    this.k += 15;
                    break;
                }
                case 1: {
                    graphics.setColor(0);
                    boolean bl = false;
                    for (int i2 = 0; i2 < a.a.h(); i2 += 10) {
                        if (bl) {
                            graphics.fillRect(0, i2, this.k, 10);
                            bl = false;
                            continue;
                        }
                        graphics.fillRect(a.a.g() - this.k, i2, this.k, 10);
                        bl = true;
                    }
                    this.k += 15;
                    break;
                }
                case 2: {
                    graphics.setColor(0);
                    boolean bl = false;
                    for (int i3 = 0; i3 < a.a.g(); i3 += 10) {
                        if (bl) {
                            graphics.fillRect(i3, 0, 10, this.k);
                            bl = false;
                            continue;
                        }
                        graphics.fillRect(i3, a.a.h() - this.k, 10, this.k);
                        bl = true;
                    }
                    this.k += 15;
                }
            }
        }
        return false;
    }

    private boolean e(Graphics graphics) {
        int n2;
        int n3;
        int n4;
        int n5;
        int n6;
        int n7 = this.n & 0xFFFFFF;
        int n8 = 0;
        int n9 = this.s;
        int n10 = 255 / ((n9 << 1) + 1);
        int n11 = this.r / 2;
        int n12 = n11 * 200 / 120;
        int n13 = n12 * n12;
        int n14 = this.t / this.r + 1;
        int n15 = a.a.g() / this.r;
        int n16 = a.a.h() / this.r;
        int n17 = n15 / 2;
        int n18 = n16 / 2;
        int[] nArray = new int[n12];
        if (this.u == null) {
            this.u = new int[a.e.a(n17 * n17 + n18 * n18, 0)][];
        }
        if (this.v == null) {
            this.v = new int[this.u.length];
        }
        if (n14 > this.u.length) {
            n14 = this.u.length;
        }
        for (n6 = 0; n6 < n14; ++n6) {
            if (this.u[n6] == null) {
                this.u[n6] = new int[this.r * this.r];
            }
            if (this.v[n6] >= n12 + n9) continue;
            n5 = -n9 + this.v[n6];
            for (n4 = 0; n4 < nArray.length; ++n4) {
                n3 = n4 - n5;
                if (n3 > n9) {
                    n8 = this.a == 4 ? 0 : 255;
                } else if (n3 < -n9) {
                    n8 = this.a == 4 ? 255 : 0;
                } else if (this.a == 4) {
                    n8 = 127 - n3 * n10;
                } else if (this.a == 5) {
                    n8 = 127 + n3 * n10;
                }
                nArray[n4] = n7 | n8 << 24;
            }
            n4 = n12 - 1;
            n3 = n5 + n9;
            if ((n5 -= n9) < 0) {
                n5 = 0;
            }
            int n19 = n3 * n13 / n4;
            n2 = n5 * n13 / n4;
            for (n5 = 0; n5 < this.r; ++n5) {
                n3 = (n5 - n11) * (n5 - n11);
                int n20 = n5 * this.r;
                for (int i2 = 0; i2 < this.r; ++i2) {
                    n4 = n3 + (i2 - n11) * (i2 - n11);
                    int n21 = a.e.a(n4, 1);
                    this.u[n6][n20 + i2] = n4 > n19 ? (this.a == 4 ? 0 : -16777216) : (n4 < n2 ? (this.a == 4 ? -16777216 : 0) : nArray[n21]);
                }
            }
            int n22 = n6;
            this.v[n22] = this.v[n22] + 1;
        }
        n6 = 1;
        graphics.setColor(n7);
        for (n4 = 0; n4 < n16; ++n4) {
            n3 = (n4 - n18) * (n4 - n18);
            for (n2 = 0; n2 < n15; ++n2) {
                n5 = a.e.a(n3 + (n2 - n17) * (n2 - n17), 1);
                if (this.u[n5] == null) {
                    if (this.a != 5) continue;
                    graphics.fillRect(n2 * this.r, n4 * this.r, this.r, this.r);
                    continue;
                }
                if (this.v[n5] >= n12 + n9) {
                    if (this.a != 4) continue;
                    graphics.fillRect(n2 * this.r, n4 * this.r, this.r, this.r);
                    continue;
                }
                n6 = 0;
                graphics.drawRGB(this.u[n5], 0, this.r, n2 * this.r, n4 * this.r, this.r, this.r, true);
            }
        }
        if (n6 != 0) {
            this.f();
            return true;
        }
        this.t += 15;
        return false;
    }

    private static int[] a(int[] nArray, int[] nArray2, int n2, int n3, byte by) {
        block11: {
            block14: {
                block13: {
                    block12: {
                        block10: {
                            if (by != 5) break block10;
                            for (by = 0; by < n3; by = (byte)(by + 1)) {
                                int n4 = by * n2;
                                int n5 = -1 - by;
                                int n6 = 0;
                                int n7 = 1;
                                while (n6 < n2) {
                                    nArray2[n7 * n3 + n5] = nArray[n4 + n6];
                                    ++n6;
                                    ++n7;
                                }
                            }
                            break block11;
                        }
                        if (by != 3) break block12;
                        by = (byte)(n2 * n3 - 1);
                        for (int i2 = 0; i2 < n3; ++i2) {
                            int n8 = i2 * n2;
                            int n9 = by - i2 * n2;
                            for (int i3 = 0; i3 < n2; ++i3) {
                                nArray2[n9 - i3] = nArray[n8 + i3];
                            }
                        }
                        break block11;
                    }
                    if (by != 6) break block13;
                    by = (byte)(n2 - 1);
                    for (int i4 = 0; i4 < n3; ++i4) {
                        int n10 = by * n3 + i4;
                        int n11 = i4 * n2;
                        for (int i5 = 0; i5 < n2; ++i5) {
                            nArray2[n10 - i5 * n3] = nArray[n11 + i5];
                        }
                    }
                    break block11;
                }
                if (by == 0 || by == 7) break block11;
                if (by != 1) break block14;
                by = (byte)(n3 - 1);
                for (int i6 = 0; i6 < n3; ++i6) {
                    int n12 = i6 * n2;
                    int n13 = (by - i6) * n2;
                    for (int i7 = 0; i7 < n2; ++i7) {
                        nArray2[n13 + i7] = nArray[n12 + i7];
                    }
                }
                break block11;
            }
            if (by == 4 || by != 2) break block11;
            by = (byte)(n2 - 1);
            for (int i8 = 0; i8 < n3; ++i8) {
                int n14 = i8 * n2;
                int n15 = n14 + by;
                for (int i9 = 0; i9 < n2; ++i9) {
                    nArray2[n15 - i9] = nArray[n14 + i9];
                }
            }
        }
        return nArray2;
    }

    public final void a(byte by) {
        this.h = (byte)-1;
        this.O = null;
    }

    public final void a(byte by, int n2, int n3, int n4, int n5, int n6, int n7) {
        this.h = 0;
        this.b(n2, n3);
        this.ad = n4;
        this.ae = n5;
        this.af = n6;
        this.ag = n7;
    }

    public final void a(int n2, int n3) {
        this.af = n2;
        this.ag = n3;
    }

    public final void b(int n2, int n3) {
        this.ab = n2;
        this.ac = n3;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        graphics.setColor(0);
        graphics.fillRect(0, 0, this.ad, this.ac - this.ag);
        graphics.fillRect(0, this.ac - this.ag, this.ab - this.af, this.ag << 1);
        graphics.fillRect(0, this.ac + this.ag, this.ad, this.ae - (this.ac + this.ag));
        graphics.fillRect(this.ab + this.af, this.ac - this.ag, this.ad - (this.ab + this.af), this.ag << 1);
    }

    public final void c(int n2, int n3) {
        this.l = 0;
        this.n = n2;
        if (n3 == 12 || n3 == 13) {
            this.b = n3;
            this.e = false;
        } else if (n3 == 18) {
            this.c = n3;
        } else {
            this.a = n3;
        }
        this.d = false;
        switch (this.a) {
            case 1: 
            case 2: {
                this.g = 17;
                return;
            }
            case 3: {
                this.l = a.a.i();
                this.m = 20;
                return;
            }
            case 4: 
            case 5: {
                this.t = 0;
                return;
            }
            case 6: {
                this.k = 0;
                this.x = a.e.a(2);
                return;
            }
            case 7: {
                this.k = 0;
                this.o = null;
                this.p = null;
                this.o = new int[a.a.g() * a.a.h()];
                this.p = new int[this.o.length];
                for (n2 = 0; n2 < this.o.length; ++n2) {
                    if (n2 % a.a.g() / 10 % 2 == 0) {
                        this.o[n2] = 0x88000000 | this.o[n2] & 0xFFFFFF;
                        int n4 = n2;
                        this.p[n4] = this.p[n4] & 0xFFFFFF;
                        continue;
                    }
                    this.p[n2] = 0x88000000 | this.p[n2] & 0xFFFFFF;
                    int n5 = n2;
                    this.o[n5] = this.o[n5] & 0xFFFFFF;
                }
                return;
            }
            case 8: {
                this.k = 0;
                z.aw();
                this.a(0);
                return;
            }
            case 10: 
            case 17: {
                this.k = 0;
                return;
            }
            case 19: {
                this.c(-1);
                return;
            }
            case 20: {
                this.c(1);
            }
        }
    }

    public final void a(int n2, int n3, int n4) {
        a.b.b.a().a = (byte)3;
        this.C = n2;
        this.D = n3;
        this.E = n4;
    }

    public final void a(int n2, int n3, int n4, int n5, int n6) {
        this.f = 0;
        this.G = n2;
        this.H = n3;
        this.I = n4;
        this.J = n5;
        this.K = n6;
    }

    public final void d(int n2, int n3) {
        this.L = n2;
        this.M = n3;
    }

    public final void a(int n2, int n3, int n4, int n5) {
        this.S = n2;
        this.T = n3;
        this.U = n4;
        this.R = n5;
    }

    public final void a(String string, int n2, int n3, int n4) {
        if (game.e.k == 1) {
            this.N = a.e.b("/data/tex/", string);
            this.N = a.a.d.a(this.N);
        } else {
            this.N = a.e.b("/data/tex/", string);
        }
        this.O = new c();
        this.O = a.a.d.a(this.N, this.O);
        this.P = n2;
        this.Q = n3;
        this.g = n4;
    }

    public final void e() {
        this.k = 0;
        A = new short[][]{{7}, {8}, {10}, {12}, {16}, {18}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {200, 200, 100, 100, 50, 50, 0, 0}, {38, 38, 39, 39, 40, 40, 41, 41, 42, 42}, {43, 43, 44, 44, 45, 45, 46, 46, 47, 47, 48}};
        z.v();
        this.a(0);
    }

    private void f() {
        this.o = null;
        this.p = null;
        this.q = null;
        this.u = null;
        this.v = null;
        this.N = null;
    }

    public final void a(int n2, int n3, byte by, byte by2, Image image, String[] stringArray) {
        this.j = n2;
        if (this.a == 17 && this.S == 0) {
            return;
        }
        this.a = -1;
        if (this.j < 16) {
            return;
        }
        this.ai = by;
        this.am = n2;
        this.an = 0;
        this.ao = by2;
        this.ak = image;
        this.aj = null;
        this.aj = new Image[stringArray.length];
        for (n2 = 0; n2 < stringArray.length; ++n2) {
            this.aj[n2] = a.e.b("/data/tex/", stringArray[n2]);
        }
        this.al = new int[by][5];
        for (n2 = 0; n2 < by; ++n2) {
            this.b(n2);
        }
    }

    private void b(int n2) {
        int n3 = a.e.a(100);
        this.al[n2][0] = n3 < 3 ? this.aj.length - 1 : (n3 < 15 ? this.aj.length - 2 : (n3 < 50 ? this.aj.length - 3 : 0));
        this.al[n2][1] = a.e.a(a.a.g());
        this.al[n2][2] = a.e.a(a.a.h());
        this.al[n2][3] = a.e.a(ah[this.al[n2][0]][1] - ah[this.al[n2][0]][0]) + ah[this.al[n2][0]][0];
        this.al[n2][4] = a.e.b(2);
    }

    public final void b(Graphics graphics) {
        if (this.j < 16) {
            return;
        }
        if (this.ak == null) {
            graphics.setColor(0);
            graphics.fillRect(0, 0, (int)a.a.g(), (int)a.a.h());
        } else {
            int n2 = this.ak.getWidth();
            for (int i2 = 0; i2 < a.a.g() / n2; ++i2) {
                graphics.drawImage(this.ak, i2 * n2, 0, 20);
            }
        }
        this.aq = false;
    }

    public final void c(Graphics graphics) {
        if (this.j < 16 && !this.aq) {
            return;
        }
        for (int i2 = 0; i2 < this.ai; ++i2) {
            if (this.al[i2][1] < a.a.g() && this.al[i2][2] < a.a.h()) {
                graphics.drawImage(this.aj[this.al[i2][0]], this.al[i2][1], this.al[i2][2], 20);
            }
            switch (this.ao) {
                case 0: {
                    int[] nArray = this.al[i2];
                    nArray[2] = nArray[2] - this.al[i2][3];
                    break;
                }
                case 1: {
                    int[] nArray = this.al[i2];
                    nArray[1] = nArray[1] + this.al[i2][3];
                    int[] nArray2 = this.al[i2];
                    nArray2[2] = nArray2[2] - this.al[i2][3];
                    break;
                }
                case 2: {
                    int[] nArray = this.al[i2];
                    nArray[1] = nArray[1] + this.al[i2][3];
                    break;
                }
                case 3: {
                    int[] nArray = this.al[i2];
                    nArray[1] = nArray[1] + this.al[i2][3];
                    int[] nArray3 = this.al[i2];
                    nArray3[2] = nArray3[2] + this.al[i2][3];
                    break;
                }
                case 4: {
                    int[] nArray = this.al[i2];
                    nArray[2] = nArray[2] + this.al[i2][3];
                    break;
                }
                case 5: {
                    int[] nArray = this.al[i2];
                    nArray[1] = nArray[1] - this.al[i2][3];
                    int[] nArray4 = this.al[i2];
                    nArray4[2] = nArray4[2] + this.al[i2][3];
                    break;
                }
                case 6: {
                    int[] nArray = this.al[i2];
                    nArray[1] = nArray[1] - this.al[i2][3];
                    break;
                }
                case 7: {
                    int[] nArray = this.al[i2];
                    nArray[1] = nArray[1] - this.al[i2][3];
                    int[] nArray5 = this.al[i2];
                    nArray5[2] = nArray5[2] - this.al[i2][3];
                }
            }
            if (this.al[i2][1] >= this.am - this.aj[this.al[i2][0]].getWidth() && this.al[i2][2] >= this.an - this.aj[this.al[i2][0]].getHeight()) continue;
            this.b(i2);
        }
    }

    private void c(int n2) {
        a.a.f.X[0] = 20;
        W = a.a.g() / X[0];
        a.a.f.X[1] = (a.a.h() - 1) / X[0] + 1;
        a.a.f.X[2] = n2;
        a.a.f.X[3] = a.e.b(0, 7);
        a.a.f.X[4] = 0;
        this.d = false;
    }

    private boolean f(Graphics graphics) {
        graphics.setColor(this.n);
        int n2 = 0;
        for (int i2 = 0; i2 < W; ++i2) {
            for (int i3 = 0; i3 < X[1]; ++i3) {
                switch (X[2]) {
                    case 1: {
                        n2 = X[0];
                        break;
                    }
                    case -1: {
                        n2 = 0;
                    }
                }
                switch (X[3]) {
                    case 0: {
                        n2 = (n2 += (i3 - X[4]) * X[2]) < 0 ? 0 : (n2 > X[0] ? X[0] : n2);
                        break;
                    }
                    case 1: {
                        n2 = (n2 += (X[1] - i3 - X[4]) * X[2]) < 0 ? 0 : (n2 > X[0] ? X[0] : n2);
                        break;
                    }
                    case 2: {
                        n2 = (n2 += (i2 - X[4]) * X[2]) < 0 ? 0 : (n2 > X[0] ? X[0] : n2);
                        break;
                    }
                    case 3: {
                        n2 = (n2 += (8 - i2 - X[4]) * X[2]) < 0 ? 0 : (n2 > X[0] ? X[0] : n2);
                        break;
                    }
                    case 4: {
                        n2 = (n2 += ((i2 + i3 >> 1) - X[4]) * X[2]) < 0 ? 0 : (n2 > X[0] ? X[0] : n2);
                        break;
                    }
                    case 5: {
                        n2 = (n2 += ((8 - i2 + i3 >> 1) - X[4]) * X[2]) < 0 ? 0 : (n2 > X[0] ? X[0] : n2);
                        break;
                    }
                    case 6: {
                        n2 = (n2 += ((i2 + X[1] - i3 >> 1) - X[4]) * X[2]) < 0 ? 0 : (n2 > X[0] ? X[0] : n2);
                        break;
                    }
                    case 7: {
                        n2 = (n2 += ((8 - i2 + X[1] - i3 >> 1) - X[4]) * X[2]) < 0 ? 0 : (n2 > X[0] ? X[0] : n2);
                    }
                }
                graphics.fillRect(X[0] * i2 + (X[0] - n2 >> 1), X[0] * i3 + (X[0] - n2 >> 1), n2, n2);
            }
        }
        X[4] = X[4] + 2;
        if (X[4] > 40) {
            if (this.a == 20) {
                this.a = -1;
            }
            if (this.a == 19) {
                graphics.fillRect(0, 0, (int)a.a.g(), (int)a.a.h());
            }
            return true;
        }
        return false;
    }

    static {
        A = null;
        W = 0;
        X = new int[5];
        byte[] byArray = new byte[]{0, 5, 3, 6, 2, 7, 1, 4};
        ah = new int[][]{{1, 3}, {1, 4}, {2, 5}, {2, 6}};
    }
}

