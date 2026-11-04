/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
package game;

import a.a;
import game.e;
import game.f;
import game.j;
import game.k;
import game.l;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class g
extends a {
    private static g l;
    private static int[] m;
    private static int n;
    private byte o = 0;
    private byte p = 0;
    private Image q;
    int k = 0;
    private byte r = (byte)10;
    private int[][] s = new int[this.r][5];
    private int[] t = new int[]{28, 3, 21, 22, 50, 5, 17, 17};
    private byte u = (byte)30;
    private byte v = (byte)30;
    private boolean w = false;
    private int x = 0;
    private int[] y = new int[this.r];
    private int[] z = new int[]{3958719, 3958719, 3958719, 7248110, 7248110, 9943031};

    public static g B() {
        if (l == null) {
            l = new g();
        }
        return l;
    }

    public final boolean b() {
        this.d = game.k.a();
        this.c = c.j.a();
        this.d.a(this);
        n = 0;
        m = h ? new int[]{504, 503, 505, 506, 507, 508} : new int[]{503, 505, 506, 507, 508};
        if (this.q == null) {
            this.q = a.e.b("/data/img/", "img_833");
        }
        this.C();
        this.a((byte)0);
        this.k = 0;
        if (game.l.B().W != null) {
            this.k = game.f.B().r;
            game.f.B().l(0);
            game.l.B().W.a((byte)1);
            game.l.B().W.a();
            game.l.B().W = null;
        }
        return true;
    }

    private void C() {
        for (int i2 = 0; i2 < this.r; ++i2) {
            this.s[i2][0] = -a.e.a(this.u);
            this.s[i2][1] = game.g.h() + a.e.a(this.v);
            this.s[i2][2] = a.e.a(2);
            this.s[i2][3] = a.e.b(1, 5);
            this.s[i2][4] = a.e.b(3, 5);
        }
    }

    private void D() {
        game.l.H = false;
        game.l.N = false;
        game.l.Q = 0;
        game.e.r = true;
        game.l.B().p = 0;
        game.l.B().q = 0;
        if (game.l.B().Z != null) {
            game.l.B().Z.D();
        }
        if (game.l.B().m != null) {
            game.l.B().m.q();
        }
        if (this.d != null) {
            this.d.b();
        }
        this.o = 0;
        game.j.p().z = false;
        h = false;
        game.f.B().a((byte)9);
        if (this.k > 0 && game.f.B().r <= 0) {
            game.f.B().l(this.k);
        }
    }

    /*
     * Unable to fully structure code
     */
    public final void a() {
        if (!this.j) {
            return;
        }
        this.A();
        switch (this.a) {
            case 0: {
                var1_1 = this;
                if (var1_1.o != 0 || !var1_1.g(16400)) ** GOTO lbl11
                if (--game.g.n < 0) {
                    game.g.n = game.g.m.length - 1;
                }
                ** GOTO lbl90
lbl11:
                // 1 sources

                if (var1_1.o != 0 || !var1_1.g(32832)) ** GOTO lbl15
                if (++game.g.n > game.g.m.length - 1) {
                    game.g.n = 0;
                }
                ** GOTO lbl90
lbl15:
                // 1 sources

                if (!var1_1.g(196640)) ** GOTO lbl90
                if (!game.g.h) ** GOTO lbl66
                switch (game.g.n) {
                    case 0: {
                        if (var1_1.o == 0) {
                            a.e.a();
                            var1_1.E();
                            var2_2 = var1_1;
                            if (game.l.B().Z != null) {
                                game.l.B().Z.D();
                            }
                            if (game.l.B().m != null) {
                                game.l.B().m.q();
                            }
                            if (var2_2.d != null) {
                                var2_2.d.b();
                            }
                            var2_2.o = 0;
                            game.j.p().z = false;
                            game.f.B().a((byte)9);
                            game.f.B().a((byte)9);
                            if (var1_1.k > 0 && game.f.B().r <= 0) {
                                game.f.B().l(var1_1.k);
                                break;
                            }
                        } else if (var1_1.o == 1) {
                            var1_1.o = 0;
                            var1_1.d.az();
                            break;
                        }
                        ** GOTO lbl90
                    }
                    case 1: {
                        if (var1_1.o == 0) {
                            a.e.a();
                            var1_1.a((byte)5);
                            break;
                        }
                        if (var1_1.o == 1) {
                            var1_1.o = 0;
                            var1_1.d.az();
                            break;
                        }
                        ** GOTO lbl90
                    }
                    case 2: {
                        var1_1.a((byte)1);
                        break;
                    }
                    case 3: {
                        var1_1.a((byte)2);
                        break;
                    }
                    case 4: {
                        var1_1.a((byte)3);
                        break;
                    }
                    case 5: {
                        var1_1.a((byte)4);
                    }
                }
                ** GOTO lbl90
lbl66:
                // 1 sources

                switch (game.g.n) {
                    case 0: {
                        if (var1_1.o == 0) {
                            a.e.a();
                            var1_1.E();
                            var1_1.D();
                            game.f.B().a((byte)9);
                            break;
                        }
                        if (var1_1.o != 1) break;
                        var1_1.o = 0;
                        var1_1.d.az();
                        break;
                    }
                    case 1: {
                        var1_1.a((byte)1);
                        break;
                    }
                    case 2: {
                        var1_1.a((byte)2);
                        break;
                    }
                    case 3: {
                        var1_1.a((byte)3);
                        break;
                    }
                    case 4: {
                        var1_1.a((byte)4);
                    }
                }
lbl90:
                // 15 sources

                if (var1_1.d.f()) {
                    var1_1.o = 0;
                }
                var1_1 = this;
                if (!var1_1.w) break;
                ++var1_1.x;
                if (var1_1.x < 100) break;
                for (var2_3 = 0; var2_3 < var1_1.y.length; ++var2_3) {
                    var1_1.y[var2_3] = 0;
                }
                var1_1.x = 0;
                var1_1.C();
                var1_1.w = false;
                break;
            }
            case 1: {
                this.d.t();
                break;
            }
            case 2: {
                this.d.p();
                break;
            }
            case 3: {
                this.d.r();
                break;
            }
            case 4: {
                if (this.g(131072)) {
                    game.f.B().a((byte)1);
                    break;
                }
                if (!this.g(262144)) break;
                this.a((byte)0);
                break;
            }
            case 5: {
                if (this.g(131104)) {
                    game.l.B();
                    game.l.K();
                    this.D();
                    break;
                }
                if (!this.g(262144)) break;
                this.a((byte)0);
                this.c.a("/data/ui/msgtip.ui");
            }
        }
        this.c.c();
    }

    public final void a(Graphics graphics) {
        switch (this.a) {
            case 1: 
            case 2: 
            case 3: {
                Graphics graphics2 = graphics;
                for (int i2 = 0; i2 < game.g.h() / 20; ++i2) {
                    if (i2 % 2 == 0) {
                        graphics2.setColor(10440998);
                    } else {
                        graphics2.setColor(12082732);
                    }
                    graphics2.fillRect(0, i2 * 20, (int)game.g.g(), 20);
                }
                break;
            }
        }
        this.c.a(graphics);
        if (this.a == 0) {
            int n2 = 36;
            int n3 = game.g.h() - 20;
            int n4 = (game.g.g() - game.g.m().stringWidth(game.g.c(m[n]))) / 2 + 4;
            String string = game.g.c(m[n]);
            Graphics graphics3 = graphics;
            g g2 = this;
            graphics3.setColor(g2.z[g2.p]);
            graphics3.drawString(string, n4, n3 - 1, 36);
            graphics3.drawString(string, n4, n3 + 1, 36);
            graphics3.drawString(string, n4 - 1, n3, 36);
            graphics3.drawString(string, n4 + 1, n3, 36);
            graphics3.setColor(0xFFFFFF);
            graphics3.drawString(string, n4, n3, 36);
            g2.p = (byte)(g2.p + 1);
            if (g2.p >= 6) {
                g2.p = 0;
            }
            graphics3 = graphics;
            g2 = this;
            if (!g2.w) {
                int n5;
                for (n5 = 0; n5 < g2.r; ++n5) {
                    graphics3.drawRegion(g2.q, g2.t[g2.s[n5][2] << 2], g2.t[(g2.s[n5][2] << 2) + 1], g2.t[(g2.s[n5][2] << 2) + 2], g2.t[(g2.s[n5][2] << 2) + 3], 0, g2.s[n5][0], g2.s[n5][1], 20);
                    int[] nArray = g2.s[n5];
                    nArray[0] = nArray[0] + g2.s[n5][3];
                    int[] nArray2 = g2.s[n5];
                    nArray2[1] = nArray2[1] - g2.s[n5][4];
                    if (g2.s[n5][0] <= game.g.g() && g2.s[n5][1] >= 0) continue;
                    int n6 = n5;
                    g2.y[n6] = g2.y[n6] + 1;
                }
                for (n5 = 0; n5 < g2.y.length && g2.y[n5] > 0; ++n5) {
                }
                if (n5 >= g2.y.length) {
                    g2.w = true;
                }
            }
            return;
        }
        if (this.a == 4) {
            graphics.setColor(0);
            graphics.fillRect(0, 0, (int)game.g.g(), (int)game.g.h());
            graphics.setColor(0xFFFFFF);
            graphics.drawString("B\u1ea1n c\u00f3 mu\u1ed1n tho\u00e1t kh\u00f4ng?", (int)game.g.i(), game.g.j() - 10, 17);
            graphics.drawString("", 2, (int)game.g.h(), 36);
            graphics.drawString("Kh\u00f4ng", game.g.g() - 2, (int)game.g.h(), 40);
        }
    }

    public final void c() {
        this.q = null;
        this.c.b();
    }

    private void E() {
        this.c.a("/data/ui/menu.ui");
    }

    public final void a(byte by) {
        this.a = by;
        switch (by) {
            case 2: {
                this.d.o();
                this.E();
                return;
            }
            case 3: {
                this.d.q();
                this.E();
                return;
            }
            case 1: {
                if (this.k > 0) {
                    game.f.B().r = (byte)this.k;
                }
                this.d.s();
                this.E();
                return;
            }
            case 0: {
                this.d.u();
                this.d.w();
                return;
            }
            case 5: {
                this.d.K();
                this.d.a("C\u00f3 ch\u1eafc ch\u1eafn x\u00f3a d\u1eef li\u1ec7u c\u0169 \u0111\u1ec3 ch\u01a1i m\u1edbi kh\u00f4ng?");
            }
        }
    }

    static {
        m = null;
    }
}

