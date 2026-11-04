/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
package game;

import a.b.d;
import a.e;
import game.a;
import game.b;
import game.c;
import game.g;
import game.j;
import game.k;
import game.l;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class f
extends a.a {
    private static f s = null;
    private byte t;
    private byte u;
    private int v;
    private Image w;
    private a.a x;
    private static int y = 0;
    private static int z = 10;
    private String[] A = new String[]{"H\u1ecfa h\u1ec7 kh\u1eafc m\u1ed9c h\u1ec7", "M\u1ed9c h\u1ec7 kh\u1eafc th\u1ed5 h\u1ec7", "Th\u1ed5 h\u1ec7 kh\u1eafc th\u1ee7y h\u1ec7", "Th\u1ee7y h\u1ec7 kh\u1eafc h\u1ecfa h\u1ec7", "Qu\u1ef7 h\u1ec7 kh\u1eafc phong h\u1ec7", "Phong h\u1ec7 kh\u1eafc \u0111i\u1ec7n h\u1ec7", "\u0110i\u1ec7n h\u1ec7 kh\u1eafc qu\u1ef7 h\u1ec7"};
    private b B = game.b.B();
    public long k = 0L;
    public long l = 0L;
    public long m = 0L;
    public long n = 0L;
    public long o = 0L;
    public long p = 0L;
    a.a.c q = null;
    private String C = "";
    public byte r = 0;

    public static f B() {
        if (s == null) {
            s = new f();
        }
        return s;
    }

    public final void d(boolean bl) {
        if (bl) {
            this.C();
            return;
        }
        this.H();
    }

    public final void C() {
        this.p = System.currentTimeMillis();
        this.a((byte)3);
        super.d(true);
    }

    private void H() {
        this.a((byte)1);
        super.d(false);
    }

    public final void a(byte by) {
        if (by >= 24) {
            return;
        }
        this.u = this.t;
        switch (this.t) {
            case 2: {
                break;
            }
            case 4: {
                break;
            }
            case 16: {
                break;
            }
            case 15: {
                break;
            }
            case 6: {
                break;
            }
            case 7: 
            case 9: 
            case 12: 
            case 22: 
            case 23: {
                y = 0;
                break;
            }
            case 10: {
                break;
            }
            case 8: 
            case 11: 
            case 13: {
                break;
            }
            case 3: {
                a.a.f.a().a = -1;
            }
        }
        this.t = by;
        switch (by) {
            case 15: {
                this.w = null;
                this.w = a.e.a("/data/logo/", "0");
                break;
            }
            case 6: {
                this.w = null;
                this.w = a.e.b("/data/img/", "img_831");
                break;
            }
            case 21: {
                a.a.f.a().c(0, 18);
                a.a.f.a().e();
                break;
            }
            case 3: {
                y = 0;
                a.a.f.a().c(0, 19);
                break;
            }
            case 12: 
            case 22: {
                game.f.a(false);
                y = 0;
                break;
            }
            case 11: {
                game.l.B().d.g = true;
                break;
            }
            case 23: {
                game.f.a(false);
                y = 0;
                this.B.a((byte)1);
                break;
            }
            case 9: {
                game.f.a(false);
                by = (byte)a.e.a(this.A.length);
                this.C = this.A[by];
                break;
            }
            case 2: {
                if (game.l.B().m == null) break;
                game.l.B().m.I();
            }
        }
        this.v = 0;
    }

    public final byte D() {
        return this.t;
    }

    public final synchronized void E() {
        if (game.l.B().W != null) {
            game.l.B().W.b();
        }
        if (this.t != 2) {
            this.a((byte)2);
            this.z();
        }
    }

    private void I() {
        this.a(this.u);
        if (game.l.B().W != null) {
            game.l.B().W.c();
        }
        this.z();
    }

    public final boolean b() {
        this.d();
        this.d = game.k.a();
        this.c = c.j.a();
        this.d.a(this);
        a.b.c.a(50000);
        a.b.f.a(1000);
        a.b.c.a();
        game.f.b(0);
        game.f.m();
        this.B.b();
        Image image = a.e.b("/data/img/", "img_22");
        int[] nArray = a.e.a(image);
        this.q = new a.a.c();
        this.q.a(nArray, image.getWidth(), image.getHeight());
        game.l.B();
        game.l.G();
        game.f.e();
        return true;
    }

    public final void c() {
        if (this.x != null) {
            this.x.c();
            this.x = null;
        }
    }

    public final void a() {
        if (!this.j) {
            return;
        }
        this.A();
        switch (this.t) {
            case 3: {
                if (!game.f.f()) {
                    this.b();
                }
                if (!a.a.f.a().d || !game.f.f()) break;
                this.a((byte)15);
                break;
            }
            case 2: {
                if (!this.g(262144)) break;
                this.I();
                break;
            }
            case 4: {
                if (this.g(131072)) {
                    this.H();
                    break;
                }
                if (!this.g(262144)) break;
                this.I();
                break;
            }
            case 16: {
                ++this.v;
                if (this.v < 10) break;
                this.a((byte)6);
                break;
            }
            case 15: {
                ++this.v;
                if (this.v < 10) break;
                this.v = 0;
                this.w = null;
                this.w = a.e.a("/data/logo/", "cwalogo");
                this.a((byte)16);
                break;
            }
            case 6: {
                if (this.g(131072)) {
                    boolean bl = true;
                    f f2 = this;
                    this.r = (byte)(bl ? 1 : 0);
                    this.a((byte)21);
                    break;
                }
                if (!this.g(262144)) break;
                boolean bl = false;
                f f3 = this;
                this.r = (byte)(bl ? 1 : 0);
                this.a((byte)21);
                break;
            }
            case 21: {
                a.a.f.a().d();
                if (a.a.f.a().c != -1 && (!this.g(65568) || !game.g.h)) break;
                a.a.f.a().c = -1;
                a.a.f.a().b = -1;
                a.a.f.a().f = 0;
                a.a.f.a().a = -1;
                this.w = null;
                a.a.c c2 = this.q;
                this.q.a = null;
                this.q = null;
                this.a((byte)7);
                break;
            }
            case 9: 
            case 22: {
                this.c();
                this.x = game.l.B();
                this.x.b();
                this.a(this.x);
                if (this.t == 9 || this.t == 22) {
                    this.a((byte)11);
                }
                game.j.V = false;
                break;
            }
            case 23: {
                a.a.f.a().d();
                if (this.B.a == 1 && a.a.f.a().e) {
                    this.B.a((byte)2);
                    break;
                }
                if (this.B.a == 2) {
                    if (!this.B.k) break;
                    this.c();
                    this.x = game.l.B();
                    this.x.b();
                    this.a(this.x);
                    this.B.a((byte)3);
                    break;
                }
                if (this.B.a != 3 || !a.a.f.a().e) break;
                a.a.f.a().b = -1;
                this.B.C();
                this.a((byte)11);
                break;
            }
            case 10: {
                this.c();
                this.x = game.l.B();
                ((l)this.x).L();
                this.a(this.x);
                this.a((byte)11);
                break;
            }
            case 7: {
                this.c();
                this.x = game.g.B();
                this.x.b();
                this.a(this.x);
                this.a((byte)8);
                break;
            }
            case 12: {
                if (!game.f.f()) {
                    this.x = null;
                    this.x = game.a.B();
                    this.x.b();
                    this.a(this.x);
                    if (((a)this.x).l == 0) {
                        a.a.f.a().c(-2013265920, 6);
                    } else if (((a)this.x).l == 2) {
                        a.a.f.a().c(-2013265920, 8);
                    } else if (((a)this.x).l == 1) {
                        a.a.f.a().c(-2013265920, 7);
                    }
                }
                if (game.f.f()) {
                    a.a.f.a().d();
                    game.j.V = false;
                }
                if (!a.a.f.a().d) break;
                ((a)this.x).E();
                this.a((byte)13);
                break;
            }
            case 19: {
                byte by = this.x instanceof l ? (byte)1 : 2;
                this.x = game.c.B();
                this.x.b();
                this.a(this.x);
                this.x.a(by);
                this.a((byte)20);
                break;
            }
            case 8: 
            case 11: 
            case 13: 
            case 20: {
                if (this.x == null) break;
                this.x.a();
            }
        }
        if (this.a != 2) {
            if (game.l.B().p == 3 && game.l.B().q == 7 && this.m == 0L && this.k != 0L) {
                this.l = System.currentTimeMillis();
            }
            this.o = System.currentTimeMillis();
        }
    }

    public final void a(Graphics object) {
        if (!this.j) {
            return;
        }
        object.setFont(game.f.m());
        switch (this.t) {
            case 3: {
                object.setColor(0xFFFFFF);
                object.fillRect(0, 0, (int)game.f.g(), (int)game.f.h());
                a.a.f.a().a((Graphics)object);
                return;
            }
            case 2: {
                object.setColor(0);
                object.fillRect(0, 0, (int)game.f.g(), (int)game.f.h());
                object.setColor(0xFFFFFF);
                object.drawString("Tr\u00f2 ch\u01a1i t\u1ea1m d\u1eebng", game.f.g() >> 1, (int)game.f.j(), 33);
                object.drawString("Quay l\u1ea1i", game.f.g() - 2, game.f.h() - 2, 40);
                return;
            }
            case 4: {
                return;
            }
            case 15: {
                object.setColor(0xFFFFFF);
                object.fillRect(0, 0, (int)game.f.g(), (int)game.f.h());
                object.setColor(0xFFFFFF);
                object.setFont(game.f.m());
                if (this.w == null) break;
                object.drawImage(this.w, (int)game.f.i(), (int)game.f.j(), 3);
                return;
            }
            case 16: {
                object.setColor(game.f.l());
                object.fillRect(0, 0, (int)game.f.g(), (int)game.f.h());
                if (this.w == null) break;
                object.drawImage(this.w, (game.f.g() - this.w.getWidth()) / 2, (game.f.h() - this.w.getHeight()) / 2, 20);
                return;
            }
            case 6: {
                object.setColor(0);
                object.fillRect(0, 0, (int)game.f.g(), (int)game.f.h());
                object.setFont(game.f.m());
                object.setColor(0xFFFFFF);
                object.drawString(game.f.c(8), game.f.g() >> 1, 144, 17);
                object.drawString(game.f.c(4), 2, game.f.h() - 2, 36);
                object.drawString(game.f.c(5), game.f.g() - 2, game.f.h() - 2, 40);
                object.setColor(16739328);
                object.drawString(game.f.c(9), game.f.g() >> 1, 166, 17);
                return;
            }
            case 21: {
                object.drawImage(this.w, 0, 0, 20);
                for (int i2 = 0; i2 < game.f.g() / 10; ++i2) {
                    object.drawRGB(this.q.a, 0, this.q.b, i2 * 10, 0, this.q.b, this.q.c, true);
                }
                a.a.f.a().a((Graphics)object);
                return;
            }
            case 7: {
                return;
            }
            case 12: {
                game.l.B().l.a((Graphics)object);
                if (!game.f.f()) break;
                a.a.f.a().a((Graphics)object);
                game.j.V = false;
                return;
            }
            case 9: {
                Graphics graphics = object;
                object = this;
                if (game.j.V) {
                    graphics.setColor(0);
                    graphics.fillRect(0, 0, (int)game.f.g(), (int)game.f.h());
                    if (y % 4 == 3) {
                        game.j.p().a((byte)1, (byte)-1, false);
                    } else {
                        game.j.p().a((byte)(y % 4), (byte)-1, false);
                    }
                    byte by = (byte)(y % 4);
                    j j2 = game.j.p();
                    game.j.p().o = by;
                    game.j.p().a(graphics, a.b.d.a().a, a.b.d.a().b - y);
                } else {
                    graphics.setColor(0);
                    graphics.fillRect(0, 0, (int)game.f.g(), (int)game.f.h());
                }
                if (y < 148) {
                    y += z;
                }
                if (y > 148) {
                    y = 148;
                }
                if (!game.j.V) {
                    graphics.setColor(0);
                    graphics.fillRect(45, game.f.h() - 48, 150, 5);
                    graphics.setColor(7877410);
                    graphics.fillRect(46, game.f.h() - 47, 148, 3);
                    graphics.setColor(16707204);
                    graphics.fillRect(46, game.f.h() - 47, y, 3);
                    graphics.setColor(0xFFFFFF);
                    graphics.drawString(object.C, game.f.g() >> 1, game.f.h() - 70, 17);
                }
                return;
            }
            case 23: {
                this.B.a();
                this.B.a((Graphics)object);
                a.a.f.a().a((Graphics)object);
                return;
            }
            case 10: {
                return;
            }
            case 8: 
            case 11: 
            case 13: 
            case 20: {
                if (this.x == null) break;
                this.x.a((Graphics)object);
            }
        }
    }

    public final void l(int n2) {
        this.r = (byte)n2;
    }

    public final void F() {
        this.r = (byte)(this.r + 1);
        if (this.r > 3) {
            this.r = (byte)3;
        }
    }

    public final void G() {
        this.r = (byte)(this.r - 1);
        if (this.r < 0) {
            this.r = 0;
        }
    }
}

