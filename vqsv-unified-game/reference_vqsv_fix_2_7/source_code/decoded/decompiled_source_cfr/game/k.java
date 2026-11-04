/*
 * Decompiled with CFR 0.152.
 */
package game;

import a.a.d;
import a.b.c;
import c.h;
import game.a;
import game.b;
import game.e;
import game.f;
import game.g;
import game.i;
import game.j;
import game.l;
import java.util.Vector;

public final class k
implements c.a {
    private static k n;
    private a.a o;
    private c.j p = c.j.a();
    private j q;
    protected int a;
    protected int b;
    protected int c;
    private int r;
    protected int d;
    protected int e;
    private int s;
    protected int f;
    protected boolean g;
    private byte t;
    private byte u;
    private int v;
    protected int h;
    private int w;
    protected int i;
    private int[] x;
    private int[] y;
    public byte j = (byte)-1;
    private static String[] z;
    private static short[] A;
    private Vector B = new Vector();
    private int C = 0;
    private int D = 0;
    private int E = 0;
    private int F = 0;
    public int k = 0;
    boolean l = false;
    private int G;
    private int H;
    private int I = 0;
    private int J = 0;
    private int K = 0;
    private int L = 0;
    private String[] M = new String[]{"/data/ui/option.ui", "/data/ui/answer.ui", "/data/ui/wharf1.ui"};
    public short[] m = new short[]{9, 0, 120, 448, 9, 1, 136, 272, 9, 2, 208, 256, 9, 3, 80, 264, 9, 4, 112, 288, 9, 5, 40, 280, 9, 6, 136, 328, 9, 7, 104, 328};
    private String[] N = new String[]{"\u0110\u1ea1t \u0111\u01b0\u1ee3c 2000 kim ti\u1ec1n", "\u0110\u1ea1t \u0111\u01b0\u1ee3c 5 Phong \u1ea5n c\u1ea7u", "\u0110\u1ea1t \u0111\u01b0\u1ee3c 5 B\u00e1nh Sandwich", "\u0110\u1ea1t \u0111\u01b0\u1ee3c 2 Sinh m\u1ec7nh th\u1ea1ch", "\u0110\u1ea1t \u0111\u01b0\u1ee3c 2 huy hi\u1ec7u"};
    private short[][] O = new short[][]{{621, 622}, {623, 624}, {625, 626}, {627, 628}, {629, 630, 631, 632}};
    private short[][] P = new short[][]{{5, 2, 112, 224, 2, 2, 5, 6, 1, 6, 0, 112, 224, 2, 0, 1, 0, 10}, {4, 0, 48, 176, 2, 2, 3, 6, 3, 6, 0, 112, 224, 2, 2, 1, 0, 10}, {3, 6, 288, 224, 3, 0, 3, 6, 3, 6, 0, 112, 224, 2, 2, 1, 0, 10}, {1, 5, 272, 128, 3, 0, 5, 6, 1, 6, 0, 112, 224, 2, 0, 1, 0, 10}, {1, 5, 272, 128, 3, 2, 0, 0, 0, 3, 6, 288, 224, 3, 0, 0, 0, 0, 4, 0, 48, 176, 2, 0, 0, 0, 0, 5, 2, 112, 224, 2, 2, 0, 0, 0}};
    private byte Q;
    private byte R;
    private String[] S = new String[]{"D\u1eabn th\u01b0\u1edfng", "Ti\u1ebfn h\u00f3a", "D\u1ecb h\u00f3a", "T\u00e0i li\u1ec7u", "C\u00e1ch m\u1edf"};

    public static k a() {
        if (n == null) {
            n = new k();
        }
        return n;
    }

    public k() {
        if (this.q == null) {
            this.q = game.j.p();
        }
    }

    public final void b() {
        n = null;
        this.q = null;
    }

    public final void a(a.a a2) {
        if (this.o != null) {
            this.o = null;
        }
        this.o = a2;
        this.g = true;
    }

    public final void c() {
        this.p.a("/data/ui/world.ui", 257, this);
        this.t = 0;
    }

    public final void d() {
        this.p.a.a(5).a(true);
        this.p.a.a(7).a(true);
    }

    private void aU() {
        if (this.p.b("/data/ui/world.ui")) {
            for (int i2 = 1; i2 <= 7; ++i2) {
                this.p.a.a(i2).a(false);
            }
        }
    }

    public final void e() {
        if (this.t < 2 && !game.e.r && game.l.T && this.p.c("/data/ui/world.ui")) {
            int n2 = 4;
            n2 = 1;
            c.c c2 = this.p.d("/data/ui/world.ui");
            if (((h)c2.a((int)1)).i().m.a().b(4)) {
                ((h)this.p.d((String)"/data/ui/world.ui").a((int)6)).i().a = ((l)this.o).u;
                this.t = 1;
            } else if (this.t == 1) {
                boolean bl = true;
                c.c c3 = this.p.d("/data/ui/world.ui");
                if (((h)c3.a((int)1)).i().m.a().h() >= 5) {
                    ((h)this.p.d((String)"/data/ui/world.ui").a((int)6)).i().a = "";
                    this.t = (byte)2;
                    game.l.T = false;
                }
            }
        }
        this.f();
    }

    public final boolean f() {
        if (this.u < 2 && this.p.b("/data/ui/openbox.ui")) {
            if (this.u == 1) {
                if (this.o.g(196640)) {
                    this.p.a.a((int)2).i().a = "";
                    this.u = (byte)2;
                    this.g = true;
                    this.az();
                    return true;
                }
            } else {
                this.u = 1;
            }
        }
        return this.g();
    }

    public final boolean g() {
        if (this.u < 2) {
            if (this.p.b("/data/ui/taskTip.ui")) {
                if (this.u == 1) {
                    if (this.o.g(196640)) {
                        this.u = (byte)2;
                        this.g = true;
                        k k2 = this;
                        if (k2.p.b("/data/ui/taskTip.ui")) {
                            k2.p.a("/data/ui/taskTip.ui");
                        }
                        return true;
                    }
                } else {
                    this.u = 1;
                }
            }
            this.g = true;
        }
        return false;
    }

    public final void h() {
        this.p.a("/data/ui/transmit.ui", 257, this);
        ((c.b)this.p.a.a((int)0)).a.a = z.length;
        ((c.b)this.p.a.a((int)0)).a.a(1);
        this.aV();
    }

    private void aV() {
        this.v = ((c.b)this.p.a.a((int)0)).a.e;
        this.h = ((c.b)this.p.a.a((int)0)).a.f;
        for (int i2 = 0; i2 < 5; ++i2) {
            this.p.a.a((int)(i2 + 5)).i().a = z[i2 + this.v];
        }
        this.p.a.a(13).b(109 + this.h * 88 / z.length, this.p.a.a());
    }

    public final void i() {
        if (this.o.g(4100)) {
            this.p.a.b(0);
            this.aV();
            return;
        }
        if (this.o.g(8448)) {
            this.p.a.b(1);
            this.aV();
            return;
        }
        if (this.o.g(196640)) {
            game.l.B().p = A[this.h * 5];
            game.l.B().q = A[this.h * 5 + 1];
            game.l.B().r = A[this.h * 5 + 2];
            game.l.B().s = A[this.h * 5 + 3];
            game.l.G = (byte)A[this.h * 5 + 4];
            game.l.B().t = -1;
            game.f.B().a((byte)9);
            return;
        }
        if (this.o.g(262144)) {
            this.o.a((byte)8);
            this.p.a("/data/ui/transmit.ui");
        }
    }

    public final boolean j() {
        return this.p.b("/data/ui/openbox.ui") || this.p.b("/data/ui/taskTip.ui");
    }

    public final void k() {
        String[] stringArray = new String[]{"T\u00f9y th\u00e2n c\u1eeda h\u00e0ng", "S\u1ee7ng v\u1eadt", "L\u01b0ng bao", "\u0110\u1ed3 gi\u00e1m", "Nhi\u1ec7m v\u1ee5", "L\u01b0u d\u1eef li\u1ec7u"};
        this.aU();
        this.p.a("/data/ui/gamemenu.ui", 257, this);
        if (a.a.i) {
            ((c.b)this.p.a.a((int)0)).a.a = 6;
            this.p.a.a((int)14).i().a = a.a.c(605 + this.b);
            this.p.a.a((int)15).i().a = stringArray[0];
            for (int i2 = 0; i2 < 5; ++i2) {
                this.p.a.a((int)(i2 + 5)).i().a = stringArray[i2 + 1];
            }
        } else {
            ((c.b)this.p.a.a((int)0)).a.a = 5;
            this.p.a.a((int)14).i().a = a.a.c(606 + this.b);
            this.p.a.a((int)15).i().a = stringArray[1];
            for (int i3 = 0; i3 < 4; ++i3) {
                this.p.a.a((int)(i3 + 5)).i().a = stringArray[i3 + 2];
            }
            this.p.a.a(9).a(false);
        }
        ((c.b)this.p.a.a((int)0)).a.f = this.b;
        this.p.a.a((int)18).i().a = "" + this.q.G();
        this.p.a.a((int)19).i().a = "" + this.q.F();
        this.f = 0;
    }

    public final void l() {
        this.o.q();
        if (!a.a.a(this.b, 0) && !this.j() && this.o.g(4100)) {
            this.p.a.b(0);
        } else if (!a.a.a(this.b, 0) && !this.j() && this.o.g(8448)) {
            this.p.a.b(1);
        } else if (!this.j() && a.a.s() && this.o.g(196640)) {
            if (a.a.p() && !a.a.a(this.b, 0)) {
                return;
            }
            if (a.a.i) {
                switch (this.b) {
                    case 0: {
                        this.o.a((byte)14);
                        this.p.a("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 1: {
                        this.c = 0;
                        this.o.r();
                        this.o.a((byte)7);
                        this.p.a("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 2: {
                        this.o.r();
                        this.o.a((byte)8);
                        this.p.a("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 3: {
                        this.c = 0;
                        this.o.a((byte)9);
                        this.p.a("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 4: {
                        this.b = 0;
                        this.o.a((byte)10);
                        this.p.a("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 5: {
                        this.p.a.a(11).a(false);
                        this.p.a.a(12).a(false);
                        this.o.a((byte)22);
                    }
                }
            } else {
                switch (this.b) {
                    case 0: {
                        this.c = 0;
                        this.o.r();
                        this.o.a((byte)7);
                        this.p.a("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 1: {
                        this.o.r();
                        this.o.a((byte)8);
                        this.p.a("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 2: {
                        this.c = 0;
                        this.o.a((byte)9);
                        this.p.a("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 3: {
                        this.b = 0;
                        this.o.a((byte)10);
                        this.p.a("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 4: {
                        this.p.a.a(11).a(false);
                        this.p.a.a(12).a(false);
                        this.o.a((byte)22);
                    }
                }
            }
        } else if (a.a.t() && this.o.g(262144)) {
            this.p.a("/data/ui/gamemenu.ui");
            this.o.a((byte)0);
        }
        this.g();
    }

    public final void m() {
        this.aU();
        this.p.a("/data/ui/gamesystem.ui", 257, this);
        ((c.b)this.p.a.a((int)0)).a.f = this.b;
        this.f = 0;
    }

    public final void n() {
        if (this.o.g(4100)) {
            this.p.a.b(0);
            return;
        }
        if (this.o.g(8448)) {
            this.p.a.b(1);
            return;
        }
        if (this.o.g(196640)) {
            switch (this.b) {
                case 0: {
                    this.p.a("/data/ui/gamesystem.ui");
                    this.o.a((byte)0);
                    return;
                }
                case 1: {
                    this.o.a((byte)20);
                    this.p.a("/data/ui/gamesystem.ui");
                    return;
                }
                case 2: {
                    this.o.a((byte)21);
                    this.p.a("/data/ui/gamesystem.ui");
                    return;
                }
                case 3: {
                    if (this.f == 0) {
                        this.p.a("/data/ui/option.ui", 257, this);
                        ((c.b)this.p.a.a((int)0)).a.f = this.c = 1;
                        this.p.a.a((int)12).i().a = "";
                        this.p.a.a((int)13).i().a = "Kh\u00f4ng";
                        this.f = 1;
                        return;
                    }
                    switch (this.c) {
                        case 1: {
                            this.p.a("/data/ui/option.ui");
                            this.f = 0;
                            this.g = true;
                            return;
                        }
                        case 0: {
                            game.f.B().l = 0L;
                            game.f.B().k = 0L;
                            game.j.p().z = false;
                            game.f.B().a((byte)7);
                            this.p.a("/data/ui/gamesystem.ui");
                        }
                    }
                }
            }
            return;
        }
        if (this.o.g(262144)) {
            if (this.f == 0) {
                this.p.a("/data/ui/gamesystem.ui");
                this.o.a((byte)0);
                return;
            }
            if (this.f == 1) {
                this.g = true;
                this.p.a("/data/ui/option.ui");
                this.f = 0;
            }
        }
    }

    public final void o() {
        this.p.a("/data/ui/help1.ui", 257, this);
        this.b = 0;
        this.p.a.a(6).a(true);
        this.p.a.a(7).a(false);
        this.e(this.b);
    }

    private void e(int n2) {
        if (n2 == 0) {
            this.p.a.a((int)5).i().a = "Tr\u1ee3 gi\u00fap";
            this.p.a.a((int)8).i().a = "Nh\u1ea5n n\u00fat 2, 4, 6, 8 \u0111\u1ec3 di chuy\u1ec3n#nN\u00fat 5: c\u00f4ng k\u00edch, \u0111\u1ed1i tho\u1ea1i, x\u00e1c nh\u1eadn#nN\u00fat 1: Xem nhi\u1ec7m v\u1ee5#nN\u00fat 9: l\u1ef1a ch\u1ecdn s\u1ee7ng v\u1eadt c\u01b0\u1ee1i#nN\u00fat 0: Xem b\u1ea3n \u0111\u1ed3#nN\u00fat m\u1ec1m tr\u00e1i: menu h\u1ec7 th\u1ed1ng#nN\u00fat m\u1ec1m ph\u1ea3i: menu tr\u00f2 ch\u01a1i";
            for (int i2 = 0; i2 < 28; ++i2) {
                this.p.a.a(i2 + 9).a(false);
            }
        } else if (n2 > 0) {
            this.p.a.a((int)8).i().a = "";
            for (int i3 = 0; i3 < 14; ++i3) {
                this.p.a.a(9 + (i3 << 1)).a(true);
                this.p.a.a(9 + (i3 << 1) + 1).a(true);
                if ((n2 - 1) * 14 + i3 < 26) {
                    this.p.a.a((int)(9 + (i3 << 1))).i().m = new c.g();
                    this.p.a.a((int)(9 + (i3 << 1))).i().m.a(0);
                    this.p.a.a((int)(9 + (i3 << 1))).i().m.a = (byte)2;
                    this.p.a.a((int)(9 + (i3 << 1))).i().m.a(325, false, (byte)-2);
                    this.p.a.a((int)(9 + (i3 << 1))).i().m.a((n2 - 1) * 14 + i3 + 1);
                    if ((n2 - 1) * 14 + i3 <= 10) {
                        this.p.a.a((int)(9 + (i3 << 1) + 1)).i().a = a.a.c(i3 + 311);
                        continue;
                    }
                    this.p.a.a((int)(9 + (i3 << 1) + 1)).i().a = a.a.c(333 + ((n2 - 1) * 14 + i3 - 11));
                    continue;
                }
                this.p.a.a(9 + (i3 << 1)).a(false);
                this.p.a.a(9 + (i3 << 1) + 1).a(false);
            }
        }
        this.p.a.a((int)39).i().a = n2 + 1 + "/3";
    }

    public final void p() {
        if (this.o.g(16400)) {
            --this.b;
            if (this.b <= 0) {
                this.b = 0;
            }
            this.e(this.b);
            return;
        }
        if (this.o.g(32832)) {
            ++this.b;
            if (this.b >= 2) {
                this.b = 2;
            }
            this.e(this.b);
            return;
        }
        if (this.o.g(262144)) {
            this.o.a((byte)0);
            this.p.a("/data/ui/help1.ui");
        }
    }

    public final void q() {
        this.p.a("/data/ui/help.ui", 257, this);
        this.p.a.a((int)5).i().a = "Quan t\u1ea1i";
        this.p.a.a((int)8).i().a = "T\u00ean tr\u00f2 ch\u01a1i: S\u1ee7ng v\u1eadt V\u01b0\u01a1ng qu\u1ed1c - Li\u1ec7t h\u1ecfa#nVi\u1ec7t h\u00f3a: BIGAME";
        this.p.a.a(6).a(true);
        this.p.a.a(7).a(false);
        for (int i2 = 9; i2 < 13; ++i2) {
            this.p.a.a(i2).a(false);
        }
    }

    public final void r() {
        if (this.o.g(262144)) {
            this.o.a((byte)0);
            this.p.a("/data/ui/help.ui");
        }
    }

    public final void s() {
        this.p.a("/data/ui/help.ui", 257, this);
        this.p.a.a((int)5).i().a = "T\u00f9y ch\u1ecdn";
        this.p.a.a((int)8).i().a = "";
        this.p.a.a(6).a(false);
        this.p.a.a(7).a(true);
        for (int i2 = 9; i2 < 13; ++i2) {
            this.p.a.a(i2).a(true);
        }
        this.aW();
    }

    private void aW() {
        for (int i2 = 1; i2 < 4; ++i2) {
            this.p.a.a((int)(i2 + 9)).i().j = i2 <= game.f.B().r ? -2148 : -8540732;
        }
    }

    public final void t() {
        if (this.o.g(16400)) {
            game.f.B().G();
            this.aW();
            return;
        }
        if (this.o.g(32832)) {
            game.f.B().F();
            this.aW();
            return;
        }
        if (this.o.g(131072)) {
            game.g.B().k = game.f.B().r;
            this.o.a((byte)0);
            this.p.a("/data/ui/help.ui");
        }
    }

    public final void u() {
        this.p.a("/data/ui/menu.ui", 336, this);
    }

    public final void v() {
        this.p.a("/data/ui/menu1.ui", 336, this);
    }

    public final void w() {
        this.p.a("/data/ui/menu1.ui");
    }

    public final void a(int n2, int n3) {
        switch (n2) {
            case 0: {
                this.p.a.a(n3).a(false);
                return;
            }
            case 1: {
                this.p.a.a(8).a(false);
                this.p.a.a(9).a(false);
                return;
            }
            case 2: {
                this.p.a.a(10).a(false);
                this.p.a.a(11).a(false);
                return;
            }
            case 3: {
                this.p.a.a(12).a(false);
                this.p.a.a(13).a(false);
                return;
            }
            case 4: {
                for (n2 = 0; n2 < 2; ++n2) {
                    if (this.p.a.a((int)(n2 + 16)).i().m != null) continue;
                    this.p.a.a((int)(n2 + 16)).i().m = new c.g();
                    this.p.a.a((int)(n2 + 16)).i().m.a = (byte)2;
                    this.p.a.a((int)(n2 + 16)).i().m.a(0);
                    this.p.a.a((int)(n2 + 16)).i().m.a(336, false, (byte)0);
                    if (n2 == 0) {
                        this.p.a.a((int)(n2 + 16)).i().m.a(8);
                        continue;
                    }
                    this.p.a.a((int)(n2 + 16)).i().m.a(10);
                }
                return;
            }
            case 5: {
                this.p.a.a(16).a(false);
                this.p.a.a(17).a(false);
                for (n2 = 0; n2 < 2; ++n2) {
                    this.p.a.a(n2 + 16).l();
                    if (this.p.a.a((int)(n2 + 18)).i().m != null) continue;
                    this.p.a.a((int)(n2 + 18)).i().m = new c.g();
                    this.p.a.a((int)(n2 + 18)).i().m.a = (byte)2;
                    this.p.a.a((int)(n2 + 18)).i().m.a(0);
                    this.p.a.a((int)(n2 + 18)).i().m.a(336, false, (byte)0);
                    if (n2 == 0) {
                        this.p.a.a((int)(n2 + 18)).i().m.a(8);
                        continue;
                    }
                    this.p.a.a((int)(n2 + 18)).i().m.a(11);
                }
                return;
            }
            case 6: {
                this.p.a.a(19).a(false);
                this.p.a.a(19).l();
                if (this.p.a.a((int)20).i().m != null) break;
                this.p.a.a((int)20).i().m = new c.g();
                this.p.a.a((int)20).i().m.a = (byte)2;
                this.p.a.a((int)20).i().m.a(0);
                this.p.a.a((int)20).i().m.a(336, false, (byte)0);
                this.p.a.a((int)20).i().m.a(12);
                return;
            }
            case 7: {
                this.p.a.a(20).a(false);
                this.p.a.a(20).l();
                return;
            }
            case 8: {
                for (n2 = 0; n2 < 2; ++n2) {
                    if (this.p.a.a((int)(n2 + 21)).i().m != null) continue;
                    this.p.a.a((int)(n2 + 21)).i().m = new c.g();
                    this.p.a.a((int)(n2 + 21)).i().m.a = (byte)2;
                    this.p.a.a((int)(n2 + 21)).i().m.a(0);
                    this.p.a.a((int)(n2 + 21)).i().m.a(336, false, (byte)0);
                    if (n2 == 0) {
                        this.p.a.a((int)(n2 + 21)).i().m.a(7);
                        continue;
                    }
                    this.p.a.a((int)(n2 + 21)).i().m.a(13);
                }
                return;
            }
            case 9: {
                this.p.a.a(21).a(false);
                this.p.a.a(22).a(false);
                for (n2 = 0; n2 < 2; ++n2) {
                    this.p.a.a(n2 + 21).l();
                    if (this.p.a.a((int)(n2 + 23)).i().m != null) continue;
                    this.p.a.a((int)(n2 + 23)).i().m = new c.g();
                    this.p.a.a((int)(n2 + 23)).i().m.a = (byte)2;
                    this.p.a.a((int)(n2 + 23)).i().m.a(0);
                    this.p.a.a((int)(n2 + 23)).i().m.a(336, false, (byte)0);
                    if (n2 == 0) {
                        this.p.a.a((int)(n2 + 23)).i().m.a(7);
                        continue;
                    }
                    this.p.a.a((int)(n2 + 23)).i().m.a(14);
                }
                return;
            }
            case 10: {
                this.p.a.a(24).a(false);
                this.p.a.a(24).l();
                if (this.p.a.a((int)25).i().m != null) break;
                this.p.a.a((int)25).i().m = new c.g();
                this.p.a.a((int)25).i().m.a = (byte)2;
                this.p.a.a((int)25).i().m.a(0);
                this.p.a.a((int)25).i().m.a(336, false, (byte)0);
                this.p.a.a((int)25).i().m.a(15);
                return;
            }
            case 11: {
                this.p.a.a(25).a(false);
                this.p.a.a(25).l();
                return;
            }
            case 12: {
                if (this.p.a.a((int)26).i().m != null) break;
                this.p.a.a((int)26).i().m = new c.g();
                this.p.a.a((int)26).i().m.a = (byte)2;
                this.p.a.a((int)26).i().m.a(0);
                this.p.a.a((int)26).i().m.a(336, false, (byte)0);
                this.p.a.a((int)26).i().m.a(5);
                return;
            }
            case 13: {
                this.p.a.a(26).a(false);
                this.p.a.a(26).l();
                if (this.p.a.a((int)27).i().m != null) break;
                this.p.a.a((int)27).i().m = new c.g();
                this.p.a.a((int)27).i().m.a = (byte)2;
                this.p.a.a((int)27).i().m.a(0);
                this.p.a.a((int)27).i().m.a(336, false, (byte)0);
                this.p.a.a((int)27).i().m.a(5);
                return;
            }
            case 14: {
                return;
            }
            case 15: {
                if (this.p.a.a((int)28).i().m != null) break;
                this.p.a.a((int)28).i().m = new c.g();
                this.p.a.a((int)28).i().m.a = (byte)2;
                this.p.a.a((int)28).i().m.a(0);
                this.p.a.a((int)28).i().m.a(336, false, (byte)0);
                this.p.a.a((int)28).i().m.a(6);
                return;
            }
            case 16: {
                this.p.a.a(28).a(false);
                this.p.a.a(28).l();
                if (this.p.a.a((int)29).i().m != null) break;
                this.p.a.a((int)29).i().m = new c.g();
                this.p.a.a((int)29).i().m.a = (byte)2;
                this.p.a.a((int)29).i().m.a(0);
                this.p.a.a((int)29).i().m.a(336, false, (byte)0);
                this.p.a.a((int)29).i().m.a(6);
                return;
            }
            case 17: {
                this.p.a.a(29).a(false);
                this.p.a.a(29).l();
                if (this.p.a.a((int)30).i().m != null) break;
                this.p.a.a((int)30).i().m = new c.g();
                this.p.a.a((int)30).i().m.a = (byte)2;
                this.p.a.a((int)30).i().m.a(0);
                this.p.a.a((int)30).i().m.a(336, false, (byte)0);
                this.p.a.a((int)30).i().m.a(6);
                return;
            }
            case 18: 
            case 19: {
                this.p.a.a(30).a(false);
                this.p.a.a(30).l();
                if (this.p.a.a((int)31).i().m != null) break;
                this.p.a.a((int)31).i().m = new c.g();
                this.p.a.a((int)31).i().m.a = (byte)2;
                this.p.a.a((int)31).i().m.a(0);
                this.p.a.a((int)31).i().m.a(336, false, (byte)0);
                this.p.a.a((int)31).i().m.a(6);
                return;
            }
            case 20: {
                a.a.f.a().c(0xFFFFFF, 2);
                a.a.f.a().g = 85;
                return;
            }
            case 21: 
            case 22: 
            case 23: {
                this.p.a.a(18).a(false);
                this.p.a.a(18).l();
                this.p.a.a(23).a(false);
                this.p.a.a(23).l();
                this.p.a.a(27).a(false);
                this.p.a.a(27).l();
                this.p.a.a(31).a(false);
                this.p.a.a(31).l();
                this.p.a.a(14).a(false);
                this.p.a.a(15).a(false);
                return;
            }
            case 24: {
                a.a.f.a().c(0xFFFFFF, 1);
                a.a.f.a().g = 255;
                if (this.p.a.a((int)32).i().m != null) break;
                this.p.a.a((int)32).i().m = new c.g();
                this.p.a.a((int)32).i().m.a = (byte)2;
                this.p.a.a((int)32).i().m.a(0);
                this.p.a.a((int)32).i().m.a(336, false, (byte)0);
                return;
            }
            case 25: {
                this.p.a.a(32).a(false);
                this.p.a.a(32).l();
                for (n2 = 0; n2 < 5; ++n2) {
                    if (this.p.a.a((int)(n2 + 33)).i().m != null) continue;
                    this.p.a.a((int)(n2 + 33)).i().m = new c.g();
                    this.p.a.a((int)(n2 + 33)).i().m.a = (byte)2;
                    this.p.a.a((int)(n2 + 33)).i().m.a(336, false, (byte)0);
                    if (n2 == 0) {
                        this.p.a.a((int)(n2 + 33)).i().m.a(0);
                        continue;
                    }
                    if (n2 == 1) {
                        this.p.a.a((int)(n2 + 33)).i().m.a(8);
                        continue;
                    }
                    if (n2 == 2) {
                        this.p.a.a((int)(n2 + 33)).i().m.a(5);
                        continue;
                    }
                    if (n2 == 3) {
                        this.p.a.a((int)(n2 + 33)).i().m.a(7);
                        continue;
                    }
                    if (n2 != 4) continue;
                    this.p.a.a((int)(n2 + 33)).i().m.a(6);
                }
                return;
            }
            case 26: {
                game.f.B().q = a.a.d.b(game.f.B().q, n3);
                return;
            }
            case 27: {
                if (n3 > 38) {
                    this.p.a.a(n3 - 1).a(false);
                    this.p.a.a(n3 - 1).l();
                }
                if (this.p.a.a((int)n3).i().m != null) break;
                this.p.a.a((int)n3).i().m = new c.g();
                this.p.a.a((int)n3).i().m.a = (byte)2;
                this.p.a.a((int)n3).i().m.a(4);
                this.p.a.a((int)n3).i().m.a(336, false, (byte)0);
                return;
            }
            case 28: {
                if (n3 > 43) {
                    this.p.a.a(n3 - 1).a(false);
                    this.p.a.a(n3 - 1).l();
                }
                if (this.p.a.a((int)n3).i().m != null) break;
                this.p.a.a((int)n3).i().m = new c.g();
                this.p.a.a((int)n3).i().m.a = (byte)2;
                this.p.a.a((int)n3).i().m.a(1);
                this.p.a.a((int)n3).i().m.a(336, false, (byte)0);
            }
        }
    }

    public final void x() {
        this.p.a("/data/ui/help1.ui", 257, this);
        this.p.a("/data/ui/gamesystem.ui");
        this.r = 0;
        this.p.a.a(6).a(true);
        this.p.a.a(7).a(false);
        this.e(this.r);
    }

    public final void y() {
        if (this.o.g(16400)) {
            --this.r;
            if (this.r <= 0) {
                this.r = 0;
            }
            this.e(this.r);
            return;
        }
        if (this.o.g(32832)) {
            ++this.r;
            if (this.r >= 2) {
                this.r = 2;
            }
            this.e(this.r);
            return;
        }
        if (this.o.g(262144)) {
            this.o.a((byte)13);
            this.p.a("/data/ui/help1.ui");
        }
    }

    public final void z() {
        this.s();
        this.p.a("/data/ui/gamesystem.ui");
    }

    public final void A() {
        if (this.o.g(16400)) {
            this.p.a.b(2);
            game.f.B().G();
            if (game.l.B().W != null) {
                game.l.B().W.b(game.f.B().r);
            }
            this.aW();
            return;
        }
        if (this.o.g(32832)) {
            this.p.a.b(3);
            game.f.B().F();
            if (game.l.B().W != null) {
                game.l.B().W.b(game.f.B().r);
            }
            this.aW();
            return;
        }
        if (this.o.g(131072)) {
            this.o.a((byte)13);
            this.p.a("/data/ui/help.ui");
        }
    }

    public final void B() {
        this.p.a("/data/ui/petstate.ui", 257, this);
        this.f = 0;
        if (this.q.P.size() > 6) {
            ((c.b)this.p.a.a((int)0)).a.a(1);
        } else {
            ((c.b)this.p.a.a((int)0)).a.a(-1);
        }
        this.p.a.a((int)2).i().a = "Ng\u00e2n h\u00e0ng S\u1ee7ng v\u1eadt";
        this.p.a.a(75).a(false);
        this.p.a.a(76).a(false);
        this.aX();
    }

    private void aX() {
        ((c.b)this.p.a.a((int)0)).a.a = this.q.P.size();
        this.v = ((c.b)this.p.a.a((int)0)).a.e;
        this.h = ((c.b)this.p.a.a((int)0)).a.f;
        ((c.b)this.p.a.a((int)0)).a.d = this.q.P.size() >= 6 ? 6 : this.q.P.size();
        if (this.h >= this.q.P.size()) {
            ((c.b)this.p.a.a((int)0)).a.f = this.h = this.q.P.size() - 1;
        }
        if (this.v > 0 && this.h - this.v < 5) {
            --this.v;
            ((c.b)this.p.a.a((int)0)).a.e = this.v;
        }
        for (int i2 = 0; i2 < 6; ++i2) {
            if (this.v + i2 < this.q.P.size()) {
                int[] nArray = (int[])this.q.P.elementAt(this.v + i2);
                if (i2 == 0) {
                    this.p.a.a((int)(14 + i2 * 6)).i().a = "" + (this.v + i2 + 1);
                } else {
                    this.p.a.a((int)(15 + i2 * 6)).i().a = "" + (this.v + i2 + 1);
                }
                this.p.a.a((int)(16 + i2 * 6)).i().a = "#P" + nArray[6] * 100 / game.i.a(nArray[0], nArray[1], nArray[4], 1);
                this.p.a.a((int)(17 + i2 * 6)).i().a = "#P" + game.i.a((short)nArray[7], (short)nArray[1]);
                continue;
            }
            this.p.a.a((int)(16 + i2 * 6)).i().a = "#P0";
            this.p.a.a((int)(17 + i2 * 6)).i().a = "#P0";
        }
        int[] nArray = null;
        if (this.q.P.size() > 0) {
            this.p.a.a(64).a(true);
            nArray = (int[])this.q.P.elementAt(this.h);
        } else {
            this.p.a.a(64).a(false);
        }
        if (nArray != null) {
            if (this.p.a.a((int)48).i().m != null) {
                this.p.a.a((int)48).i().m.c();
            } else {
                this.p.a.a((int)48).i().m = new c.g();
                this.p.a.a((int)48).i().m.a(0);
                this.p.a.a((int)48).i().m.a = (byte)3;
            }
            this.p.a.a((int)48).i().m.a(a.b.c.a((byte)0, (short)nArray[0], (byte)17), false, (byte)-1);
            this.p.a.a((int)51).i().a = a.a.c(a.b.c.a((byte)0, (short)nArray[0], (byte)0));
            this.p.a.a((int)52).i().a = a.a.c(365 + a.b.c.a((byte)0, (short)nArray[0], (byte)1));
            if (a.b.c.a((byte)0, (short)nArray[0], (byte)19) == -1) {
                this.p.a.a((int)62).i().a = "";
            } else if (a.b.c.c[0][a.b.c.a((byte)0, (short)nArray[0], (byte)19)][2] == 1 || a.b.c.c[0][a.b.c.a((byte)0, (short)nArray[0], (byte)19)][2] == 2) {
                this.p.a.a((int)62).i().a = "C\u00f3 th\u1ec3 ti\u1ebfn h\u00f3a";
            } else if (a.b.c.c[0][a.b.c.a((byte)0, (short)nArray[0], (byte)19)][2] == 3) {
                this.p.a.a((int)62).i().a = "C\u00f3 th\u1ec3 d\u1ecb ho\u00e1";
            }
            this.p.a.a((int)61).i().a = game.i.y(nArray[0]);
            if (this.p.a.a((int)59).i().m == null) {
                this.p.a.a((int)59).i().m = new c.g();
                this.p.a.a((int)59).i().m.a(0);
                this.p.a.a((int)59).i().m.a = (byte)2;
                this.p.a.a((int)59).i().m.a(258, false, (byte)-1);
            }
            if (nArray[2] != -1) {
                this.p.a.a((int)59).i().m.a(a.b.c.c[3][nArray[2]][1]);
                this.p.a.a((int)60).i().a = a.a.c(a.b.c.c[3][nArray[2]][0]);
            } else {
                this.p.a.a((int)59).i().m.a(0);
                this.p.a.a((int)60).i().a = "";
            }
            this.p.a.a((int)65).i().a = "" + nArray[1];
            this.p.a.a((int)66).i().a = "" + game.i.a(nArray[0], nArray[1], nArray[4], 2);
            this.p.a.a((int)67).i().a = "" + game.i.a(nArray[0], nArray[1], nArray[4], 3);
            this.p.a.a((int)68).i().a = "" + game.i.a(nArray[0], nArray[1], nArray[4], 4);
            int n2 = nArray[4];
            int n3 = a.b.c.a((byte)0, (short)nArray[0], (byte)4) - 1;
            for (int i3 = 0; i3 < 5; ++i3) {
                this.p.a.a(74 - i3).a(true);
                this.p.a.a((int)(74 - i3)).i().m.a = (byte)3;
                if (i3 > n3) {
                    this.p.a.a(74 - i3).a(false);
                    continue;
                }
                if (n2 > 0) {
                    this.p.a.a((int)(74 - i3)).i().m.a((byte)14, (byte)-1);
                    --n2;
                    continue;
                }
                this.p.a.a((int)(74 - i3)).i().m.a((byte)16, (byte)-1);
            }
            if (this.b == 1) {
                this.p.a.a((int)64).i().a = "L\u1ea5y ra";
                return;
            }
            if (this.b == 2) {
                this.p.a.a((int)64).i().a = "Ph\u00f3ng sinh";
            }
        }
    }

    public final void C() {
        if (this.f == 0 && this.o.g(4100)) {
            this.p.a.b(0);
            this.aX();
        } else if (this.f == 0 && this.o.g(8448)) {
            this.p.a.b(1);
            this.aX();
        }
        if (this.f == 0) {
            if (this.o.g(196640)) {
                if (this.b == 1) {
                    if (this.q.B >= 6) {
                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                        this.a("Ba l\u00f4 S\u1ee7ng v\u1eadt \u0111\u00e3 \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                        this.f = 1;
                    } else {
                        if (this.q.P.size() <= 0) {
                            return;
                        }
                        this.q.r(this.h);
                        if (this.q.P.size() <= 0) {
                            this.o.a((byte)16);
                            this.p.a("/data/ui/petstate.ui");
                        } else {
                            this.aX();
                        }
                    }
                } else if (this.b == 2) {
                    if (this.q.P.size() <= 0) {
                        return;
                    }
                    int[] nArray = (int[])this.q.P.elementAt(this.h);
                    if (a.b.c.a((byte)0, (short)nArray[0], (byte)22) == 2) {
                        this.f = 2;
                        this.H();
                        this.a("Th\u1ea7n th\u00fa kh\u00f4ng th\u1ec3 ph\u00f3ng sinh", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                    } else {
                        this.f = 1;
                        this.p.a("/data/ui/msgconfirm.ui", 257, this);
                        this.b("B\u1ea1n mu\u1ed1n ph\u00f3ng sinh s\u1ee7ng v\u1eadt n\u00e0y?", "X\u00e1c nh\u1eadn");
                    }
                }
            } else if (this.o.g(786432)) {
                this.o.a((byte)16);
                this.p.a("/data/ui/petstate.ui");
            }
        } else if (this.f > 0) {
            if (this.o.g(196640) && this.b == 1 || this.o.g(131072) && this.b == 2 && this.f == 1 || this.o.g(196640) && this.b == 2 && this.f == 2) {
                if (this.b == 1) {
                    this.p.a("/data/ui/msgwarm.ui");
                    this.f = 0;
                } else if (this.b == 2) {
                    if (this.f == 1) {
                        this.p.a("/data/ui/msgconfirm.ui");
                        int[] nArray = (int[])this.q.P.elementAt(this.h);
                        this.q.m(nArray[2]);
                        this.q.q(this.h);
                        this.aX();
                    } else if (this.f == 2) {
                        this.I();
                    }
                    this.f = 0;
                }
            } else if (this.o.g(786432)) {
                if (this.b == 1) {
                    return;
                }
                this.p.a("/data/ui/msgconfirm.ui");
                this.f = 0;
            }
        }
        this.g = true;
    }

    public final void D() {
        this.aU();
        this.p.a("/data/ui/shop.ui", 257, this);
        this.b = 0;
        this.p.a.a((int)5).i().a = "Ng\u00e2n h\u00e0ng S\u1ee7ng v\u1eadt";
        this.p.a.a((int)6).i().a = "G\u1edfi l\u1ea1i";
        this.p.a.a((int)7).i().a = "L\u1ea5y ra";
        this.p.a.a((int)9).i().a = "Ph\u00f3ng sinh";
    }

    public final void E() {
        if (this.o.g(4100)) {
            this.p.a.b(0);
            return;
        }
        if (this.o.g(8448)) {
            this.p.a.b(1);
            return;
        }
        if (this.o.g(196640)) {
            switch (this.b) {
                case 0: {
                    this.c = 0;
                    this.o.a((byte)7);
                    this.p.a("/data/ui/shop.ui");
                    return;
                }
                case 1: {
                    this.o.a((byte)15);
                    this.p.a("/data/ui/shop.ui");
                    return;
                }
                case 2: {
                    this.o.a((byte)15);
                    this.p.a("/data/ui/shop.ui");
                    return;
                }
                case 3: {
                    game.e.q = true;
                    this.p.a("/data/ui/shop.ui");
                    this.o.a((byte)0);
                }
            }
            return;
        }
        if (this.o.g(262144)) {
            game.e.q = true;
            this.p.a("/data/ui/shop.ui");
            this.o.a((byte)0);
        }
    }

    public final void F() {
        this.aU();
        this.p.a("/data/ui/shop.ui", 257, this);
        this.b = 0;
    }

    public final void G() {
        this.o.q();
        if (!a.a.a(this.b, 0) && !this.j() && this.f == 0 && this.o.g(4100) && this.aY()) {
            this.p.a.b(0);
        } else if (!a.a.a(this.b, 0) && !this.j() && this.f == 0 && this.o.g(8448) && this.aY()) {
            this.p.a.b(1);
        } else if (this.aY() && !this.j() && a.a.s() && this.o.g(196640)) {
            if (a.a.p() && !a.a.a(this.b, 0)) {
                return;
            }
            switch (this.b) {
                case 0: {
                    this.o.r();
                    this.o.a((byte)2);
                    this.p.a("/data/ui/shop.ui");
                    break;
                }
                case 1: {
                    this.o.a((byte)3);
                    this.p.a("/data/ui/shop.ui");
                    break;
                }
                case 2: {
                    if (this.f == 0) {
                        if (game.j.p().B() == -1) {
                            this.f = 6;
                            this.H();
                            this.a("To\u00e0n b\u1ed9 tr\u1ea1ng th\u00e1i \u0111\u00e3 \u0111\u1ea7y, kh\u00f4ng c\u1ea7n kh\u00f4i ph\u1ee5c", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                            break;
                        }
                        if (!a.a.i) {
                            this.f = 3;
                            for (int i2 = 0; i2 < this.q.B; ++i2) {
                                this.q.A[i2].J();
                            }
                            this.b("Ba l\u00f4 s\u1ee7ng v\u1eadt tr\u1ea1ng th\u00e1i to\u00e0n b\u1ed9 kh\u00f4i ph\u1ee5c");
                            break;
                        }
                        k k2 = this;
                        k2.p.a("/data/ui/msgRecover.ui", 257, k2);
                        k2.p.a.a((int)4).i().a = "C\u00f3 kh\u00f4i ph\u1ee5c tr\u1ea1ng th\u00e1i ba l\u00f4 s\u1ee7ng v\u1eadt kh\u00f4ng?";
                        k2.p.a.a((int)5).i().a = "C\u1ea7n ti\u1ec1n t\u00e0i: ";
                        k2.p.a.a((int)6).i().a = "" + game.j.p().B();
                        k2.p.a.a((int)8).i().a = "" + game.j.p().F();
                        this.p.a("/data/ui/shop.ui");
                        this.f = 1;
                        break;
                    }
                    if (this.f == 1) {
                        int n2 = game.j.p().B();
                        if (game.j.p().u(n2)) {
                            this.f = 3;
                            game.j.p().s(-n2);
                            for (n2 = 0; n2 < this.q.B; ++n2) {
                                this.q.A[n2].J();
                            }
                            this.b("Ba l\u00f4 s\u1ee7ng v\u1eadt tr\u1ea1ng th\u00e1i to\u00e0n b\u1ed9 kh\u00f4i ph\u1ee5c");
                        } else {
                            this.f = 2;
                            this.H();
                            this.a("Kim ti\u1ec1n ch\u01b0a \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                        }
                        this.p.a("/data/ui/msgRecover.ui");
                        break;
                    }
                    if (this.f == 2 && a.a.i) {
                        this.o.a((byte)102);
                    }
                    this.f = 0;
                    this.I();
                    break;
                }
                case 3: {
                    game.e.q = true;
                    this.p.a("/data/ui/shop.ui");
                    this.o.a((byte)0);
                }
            }
        } else if (!this.j() && this.o.g(262144) && a.a.t() && this.aY()) {
            if (this.f == 1) {
                this.p.a("/data/ui/shop.ui", 257, this);
                this.p.a("/data/ui/msgRecover.ui");
                this.b = 0;
                this.f = 0;
            } else if (this.f == 0) {
                game.e.q = true;
                this.p.a("/data/ui/shop.ui");
                this.o.a((byte)0);
            }
        }
        if (this.f == 3 && this.aA()) {
            this.f = 4;
            this.p.a("/data/ui/shop.ui", 257, this);
            this.K();
            this.a("\u0110ang l\u01b0u...");
            this.M();
        } else if (this.f == 4 && ((l)this.o).I()) {
            this.a("L\u01b0u th\u00e0nh c\u00f4ng");
            this.f = 5;
        } else if (this.f == 5) {
            this.L();
            this.f = 0;
            this.b = 0;
        }
        this.f();
        this.g = true;
    }

    public final void a(int n2, byte by) {
        this.p.a("/data/ui/shopbuy.ui", 257, this);
        this.b = 0;
        this.f = 0;
        ((c.b)this.p.a.a((int)0)).a.a = a.b.c.c[n2].length;
        ((c.b)this.p.a.a((int)0)).a.a(1);
        this.p.a.a((int)5).i().a = "Mua";
        if (this.o instanceof l) {
            this.p.a.a(57).a(true);
            this.p.a.a(58).a(true);
            this.p.a.a((int)57).i().a = "Mua s\u1eafm";
            this.p.a.a((int)58).i().a = "Quay l\u1ea1i";
            this.p.a.a(39).a(false);
            this.p.a.a(40).a(false);
        } else if (this.o instanceof a) {
            this.p.a.a(57).a(false);
            this.p.a.a(58).a(false);
            this.p.a.a(39).a(true);
            this.p.a.a(40).a(true);
            this.p.a.a((int)39).i().a = "Mua s\u1eafm";
            this.p.a.a((int)40).i().a = "Quay l\u1ea1i";
        }
        this.b(n2, by);
    }

    private void b(int n2, byte by) {
        this.v = ((c.b)this.p.a.a((int)0)).a.e;
        this.h = ((c.b)this.p.a.a((int)0)).a.f;
        for (int i2 = 0; i2 < 5; ++i2) {
            if (this.p.a.a((int)(i2 + 51)).i().m == null) {
                this.p.a.a((int)(i2 + 51)).i().m = new c.g();
                this.p.a.a((int)(i2 + 51)).i().m.a(0);
                this.p.a.a((int)(i2 + 51)).i().m.a = (byte)2;
                this.p.a.a((int)(i2 + 51)).i().m.a(258, false, (byte)-1);
            }
            this.p.a.a((int)(i2 + 51)).i().m.a(a.b.c.c[n2][this.v + i2][1]);
            this.p.a.a((int)(14 + i2 * 5)).i().a = a.a.c(a.b.c.c[n2][this.v + i2][0]);
            if (this.o instanceof l) {
                if (this.j == 1 || this.j == 3) {
                    this.p.a.a((int)(15 + i2 * 5)).i().a = "" + a.b.c.c[n2][this.v + i2][3];
                } else if (this.j == 2) {
                    this.p.a.a((int)(15 + i2 * 5)).i().a = a.b.c.c[n2][this.v + i2][4] == 0 ? "" + a.b.c.c[n2][this.v + i2][3] * 3 / 2 : "" + a.b.c.c[n2][this.v + i2][3];
                }
            } else {
                this.p.a.a((int)(15 + i2 * 5)).i().a = by == 0 && n2 == 4 && this.v + i2 == 0 ? "" + a.b.c.c[n2][this.v + i2][3] : "" + (a.b.c.c[n2][this.v + i2][3] << 1);
            }
            if (a.b.c.c[n2][this.v + i2][4] == 0) {
                this.p.a.a((int)(i2 + 45)).i().m.a(84);
                continue;
            }
            if (a.b.c.c[n2][this.v + i2][4] == 1) {
                this.p.a.a((int)(i2 + 45)).i().m.a(83);
                continue;
            }
            if (a.b.c.c[n2][this.v + i2][4] != 2) continue;
            this.p.a.a((int)(i2 + 45)).i().m.a(74);
        }
        this.p.a.a((int)56).i().a = a.a.c(a.b.c.c[n2][this.h][2]);
        this.p.a.a((int)43).i().a = "" + this.q.G();
        this.p.a.a((int)44).i().a = "" + this.q.F();
        this.p.a.a(38).b(102 + this.h * 84 / a.b.c.c[n2].length, this.p.a.a());
    }

    public final void a(byte by, byte by2) {
        this.o.q();
        if (!a.a.a(this.b, 0) && this.f <= 1 && this.o.g(4100) && !this.j()) {
            this.p.a.b(0);
            if (this.f == 0) {
                this.b((int)by, by2);
            }
        } else if (!a.a.a(this.b, 0) && this.f <= 1 && this.o.g(8448) && !this.j()) {
            this.p.a.b(1);
            if (this.f == 0) {
                this.b((int)by, by2);
            }
        } else if (this.f == 1 && this.o.g(16400) && this.c > 0 && !this.j()) {
            --this.c;
            if (this.c <= 0) {
                this.c = 99 - this.q.a(this.h, by2);
            }
            this.a(this.c, this.c * a.b.c.c[by][this.h][3], (int)a.b.c.c[by][this.h][4], (int)by);
        } else if (this.f == 1 && this.o.g(32832) && !this.j()) {
            ++this.c;
            if (this.c > 99 - this.q.a(this.h, by2)) {
                this.c = 1;
            }
            this.a(this.c, this.c * a.b.c.c[by][this.h][3], (int)a.b.c.c[by][this.h][4], (int)by);
        } else if (a.a.s() && this.o.g(196640) && !this.j()) {
            if (a.a.p() && !a.a.a(this.b, 0)) {
                return;
            }
            if (a.b.c.c[by][this.h][4] == 2) {
                if (this.f == 0) {
                    if (a.a.i) {
                        if (!this.q.a(this.h, 1, (byte)0)) {
                            this.f = 3;
                            this.H();
                            this.a("\u0110\u1ea1o c\u1ee5 \u0111\u00e3 \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                        } else {
                            this.o.a((byte)101);
                        }
                    } else {
                        this.f = 3;
                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                        this.a("C\u00f4ng n\u0103ng c\u00f2n ch\u01b0a m\u1edf", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                    }
                } else {
                    this.f = 0;
                    this.p.a("/data/ui/msgwarm.ui");
                }
            } else if (by2 == 2 && this.h < 12) {
                this.c = 1;
                if (this.q.a(this.h, this.c, by2)) {
                    if (this.f == 0) {
                        this.r = 0;
                        this.b(by, by2);
                    } else if (this.f > 0) {
                        if (a.a.i) {
                            if (this.f == 4) {
                                this.o.a((byte)104);
                            } else if (this.f == 3) {
                                this.o.a((byte)102);
                            }
                        }
                        this.f = 0;
                        this.p.a("/data/ui/msgwarm.ui");
                    }
                } else if (this.f == 0) {
                    this.f = 2;
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("\u0110\u1ea1o c\u1ee5 n\u00e0y \u0111\u00e3 \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                } else {
                    this.o.r();
                    this.f = 0;
                    this.p.a("/data/ui/msgwarm.ui");
                    this.b((int)by, by2);
                }
            } else if (this.q.a(this.h, this.c, by2)) {
                if (this.f == 0) {
                    this.f = 1;
                    this.p.a("/data/ui/msgyn.ui", 257, this);
                    this.c = 1;
                    this.r = 0;
                    this.a(this.c, this.c * a.b.c.c[by][this.h][3], (int)a.b.c.c[by][this.h][4], (int)by);
                } else if (this.f == 1) {
                    this.b(by, by2);
                } else if (this.f == 2) {
                    game.l.B().Z.G();
                    this.f = 0;
                    this.c = 0;
                    this.p.a("/data/ui/msgwarm.ui");
                    this.b((int)by, by2);
                } else {
                    if (a.a.i) {
                        if (this.f == 4) {
                            this.p.a("/data/ui/msgyn.ui");
                            this.o.a((byte)104);
                        } else if (this.f == 3) {
                            this.p.a("/data/ui/msgyn.ui");
                            this.o.a((byte)102);
                        }
                    }
                    this.f = 0;
                    this.c = 0;
                    this.p.a("/data/ui/msgwarm.ui");
                }
            } else if (this.f == 0) {
                this.f = 2;
                this.p.a("/data/ui/msgwarm.ui", 257, this);
                this.a("\u0110\u1ea1o c\u1ee5 n\u00e0y \u0111\u00e3 \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
            } else {
                this.f = 0;
                this.p.a("/data/ui/msgwarm.ui");
            }
        } else if (this.o.g(262144) && !this.j() && a.a.t()) {
            if (this.f == 0) {
                if (this.o instanceof l) {
                    if (this.j == 1) {
                        this.o.a((byte)1);
                    } else if (this.j == 2) {
                        this.o.a((byte)14);
                    } else if (this.j == 3) {
                        this.o.a((byte)27);
                    }
                    this.p.a("/data/ui/shopbuy.ui");
                } else {
                    this.p.a("/data/ui/shopbuy.ui");
                    this.o.a((byte)20);
                }
            } else if (this.f == 1) {
                this.f = 0;
                this.c = 0;
                this.p.a("/data/ui/msgyn.ui");
            }
        }
        this.f();
    }

    private void b(byte by, byte by2) {
        block29: {
            block25: {
                block26: {
                    int n2;
                    block28: {
                        block27: {
                            if (this.o instanceof l && (this.j == 1 || this.j == 3) && this.q.b(this.h, this.c * a.b.c.c[by][this.h][3], (int)by) || this.j == 2 && (a.b.c.c[by][this.h][4] == 0 && this.q.b(this.h, this.c * a.b.c.c[by][this.h][3] * 3 / 2, (int)by) || a.b.c.c[by][this.h][4] != 0 && this.q.b(this.h, this.c * a.b.c.c[by][this.h][3], (int)by))) {
                                if (this.r == 0) {
                                    this.q.c(this.h, this.c, by2);
                                    if (a.b.c.c[by][this.h][4] == 0) {
                                        if (this.j == 1 || this.j == 3) {
                                            this.q.s(-this.c * a.b.c.c[by][this.h][3]);
                                        } else if (this.j == 2) {
                                            this.q.s(-this.c * a.b.c.c[by][this.h][3] * 3 / 2);
                                        }
                                    } else if (this.j == 1 || this.j == 3) {
                                        this.q.v(-this.c * a.b.c.c[by][this.h][3]);
                                    } else if (this.j == 2) {
                                        this.q.v(-this.c * a.b.c.c[by][this.h][3]);
                                    }
                                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                                    if (by == 3 && this.h == 17) {
                                        this.a("\u0110\u00e3 th\u00e0nh c\u00f4ng mua s\u1eafm #2" + a.a.c(a.b.c.c[by][this.h][0]) + " * " + 5 * this.c, "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                    } else {
                                        this.a("\u0110\u00e3 th\u00e0nh c\u00f4ng mua s\u1eafm #2" + a.a.c(a.b.c.c[by][this.h][0]) + " * " + this.c, "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                    }
                                    this.f = 2;
                                    this.c = 1;
                                } else {
                                    this.f = 0;
                                }
                                this.p.a("/data/ui/msgyn.ui");
                                return;
                            }
                            if (this.o instanceof a && this.q.b(this.h, this.c * a.b.c.c[by][this.h][3] << 1, (int)by)) {
                                if (this.r == 0) {
                                    this.q.c(this.h, this.c, by2);
                                    if (a.b.c.c[by][this.h][4] == 0) {
                                        this.q.s(-this.c * a.b.c.c[by][this.h][3] << 1);
                                    } else {
                                        this.q.v(-this.c * a.b.c.c[by][this.h][3] << 1);
                                    }
                                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                                    if (by == 3 && this.h == 17) {
                                        this.a("\u0110\u00e3 th\u00e0nh c\u00f4ng mua s\u1eafm #2" + a.a.c(a.b.c.c[by][this.h][0]) + " * " + 5 * this.c, "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                    } else {
                                        this.a("\u0110\u00e3 th\u00e0nh c\u00f4ng mua s\u1eafm #2" + a.a.c(a.b.c.c[by][this.h][0]) + " * " + this.c, "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                    }
                                    this.f = 2;
                                    this.c = 1;
                                } else {
                                    this.f = 0;
                                }
                                this.p.a("/data/ui/msgyn.ui");
                                return;
                            }
                            if (this.r != 0) break block25;
                            this.p.a("/data/ui/msgwarm.ui", 257, this);
                            if (a.b.c.c[by][this.h][4] == 0) {
                                this.f = 3;
                                this.a("Kim ti\u1ec1n ch\u01b0a \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                            } else {
                                this.f = 4;
                                this.a("S\u1ed1 l\u01b0\u1ee3ng Huy hi\u1ec7u ch\u01b0a \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                            }
                            if (!(this.o instanceof l)) break block26;
                            if (this.j != 1 && this.j != 3) break block27;
                            n2 = this.c * a.b.c.c[by][this.h][3];
                            break block28;
                        }
                        if (this.j != 2) break block29;
                        n2 = a.b.c.c[by][this.h][4] == 0 ? this.c * a.b.c.c[by][this.h][3] * 3 / 2 : this.c * a.b.c.c[by][this.h][3];
                    }
                    this.a(new int[]{by, by2, this.h, a.b.c.c[by][this.h][4], n2, this.c});
                    break block29;
                }
                if (this.o instanceof a) {
                    int n3 = this.c * a.b.c.c[by][this.h][3] << 1;
                    this.a(new int[]{by, by2, this.h, a.b.c.c[by][this.h][4], n3, this.c});
                }
                break block29;
            }
            this.f = 0;
        }
        this.p.a("/data/ui/msgyn.ui");
    }

    private void a(int[] nArray) {
        if (this.B == null) {
            this.B = new Vector();
        } else {
            this.B.removeAllElements();
        }
        this.B.addElement(nArray);
    }

    private void a(int n2, int n3, int n4, int n5) {
        this.p.a.a((int)9).i().a = n5 == 3 && this.h == 17 ? "" + n2 * 5 : "" + n2;
        if (this.o instanceof l) {
            if (this.j == 1 || this.j == 3) {
                this.p.a.a((int)11).i().a = "" + n3;
            } else if (this.j == 2) {
                this.p.a.a((int)11).i().a = n4 == 0 ? "" + n3 * 3 / 2 : "" + n3;
            }
        } else {
            this.p.a.a((int)11).i().a = "" + (n3 << 1);
        }
        if (n4 == 0) {
            this.p.a.a((int)12).i().m.a(84);
            return;
        }
        if (n4 == 1) {
            this.p.a.a((int)12).i().m.a(83);
        }
    }

    public final void H() {
        this.p.a("/data/ui/msgwarm.ui", 257, this);
    }

    public final void I() {
        this.p.a("/data/ui/msgwarm.ui");
    }

    public final boolean J() {
        return !this.p.b("/data/ui/msgwarm.ui");
    }

    public final void a(String string, String string2) {
        this.p.a.a((int)6).i().a = string2;
        this.p.a.a((int)7).i().a = string;
    }

    public final void K() {
        this.p.a("/data/ui/msgtip.ui", 257, this);
    }

    public final void L() {
        this.p.a("/data/ui/msgtip.ui");
    }

    private boolean aY() {
        return !this.p.b("/data/ui/msgtip.ui");
    }

    public final void a(String string) {
        this.p.a.a((int)2).i().a = string;
    }

    public final void M() {
        this.p.a.a(3).a(false);
        this.p.a.a(4).a(false);
    }

    public final void N() {
        if (this.f == 0) {
            if (this.o.g(196640)) {
                this.f = 1;
                this.a("\u0110ang l\u01b0u...");
                this.M();
                return;
            }
            if (this.o.g(262144)) {
                this.b = a.a.i ? 5 : 4;
                this.o.a((byte)6);
                this.p.a("/data/ui/msgtip.ui");
                this.f = 0;
                return;
            }
        } else if (this.f == 1) {
            if (((l)this.o).I()) {
                this.a("L\u01b0u th\u00e0nh c\u00f4ng");
                this.f = 2;
                return;
            }
        } else if (this.f == 2) {
            this.p.a("/data/ui/msgtip.ui");
            this.p.a("/data/ui/gamemenu.ui");
            this.o.a((byte)0);
            this.f = 0;
        }
    }

    private void b(String string, String string2) {
        this.p.a.a((int)2).i().a = string2;
        this.p.a.a((int)4).i().a = string;
    }

    public final void O() {
        this.p.a("/data/ui/shopbuy.ui", 257, this);
        this.b = 0;
        this.f = 0;
        this.p.a.a((int)5).i().a = "B\u00e1n ra";
        this.p.a.a((int)39).i().a = "";
        this.p.a.a((int)40).i().a = "";
        this.p.a.a((int)57).i().a = "B\u00e1n \u0111i";
        this.p.a.a((int)58).i().a = "Quay l\u1ea1i";
        this.q.y();
        this.aZ();
    }

    private void aZ() {
        if (this.q.T.size() > 5) {
            ((c.b)this.p.a.a((int)0)).a.a(1);
        } else {
            ((c.b)this.p.a.a((int)0)).a.a(0);
        }
        ((c.b)this.p.a.a((int)0)).a.a = this.q.T.size();
        this.v = ((c.b)this.p.a.a((int)0)).a.e;
        this.h = ((c.b)this.p.a.a((int)0)).a.f;
        if (this.h >= this.q.T.size()) {
            ((c.b)this.p.a.a((int)0)).a.f = this.h = this.q.T.size() - 1;
        }
        if (this.v > 0 && this.h - this.v < 4) {
            --this.v;
            ((c.b)this.p.a.a((int)0)).a.e = this.v;
        }
        for (int i2 = 0; i2 < 5; ++i2) {
            if (this.v + i2 < this.q.T.size()) {
                int n2 = ((int[])this.q.T.elementAt(this.v + i2))[0];
                if (this.p.a.a((int)(i2 + 51)).i().m == null) {
                    this.p.a.a((int)(i2 + 51)).i().m = new c.g();
                    this.p.a.a((int)(i2 + 51)).i().m.a(0);
                    this.p.a.a((int)(i2 + 51)).i().m.a = (byte)2;
                    this.p.a.a((int)(i2 + 51)).i().m.a(258, false, (byte)-1);
                }
                this.p.a.a((int)(i2 + 51)).i().m.a(a.b.c.c[4][n2][1]);
                this.p.a.a((int)(14 + i2 * 5)).i().a = a.a.c(a.b.c.c[4][n2][0]);
                this.p.a.a((int)(15 + i2 * 5)).i().a = "" + a.b.c.c[4][n2][3] / 2;
                if (a.b.c.c[4][n2][4] == 0) {
                    this.p.a.a((int)(i2 + 45)).i().m.a(84);
                    continue;
                }
                if (a.b.c.c[4][n2][4] == 1) {
                    this.p.a.a((int)(i2 + 45)).i().m.a(83);
                    continue;
                }
                if (a.b.c.c[4][n2][4] != 2) continue;
                this.p.a.a((int)(i2 + 45)).i().m.a(74);
                continue;
            }
            if (this.p.a.a((int)(i2 + 51)).i().m != null) {
                this.p.a.a((int)(i2 + 51)).i().m.c();
            }
            this.p.a.a((int)(14 + i2 * 5)).i().a = "";
            this.p.a.a((int)(15 + i2 * 5)).i().a = "";
            this.p.a.a((int)(i2 + 45)).i().m.a(86);
        }
        this.p.a.a((int)56).i().a = this.q.T.size() > 0 ? a.a.c(a.b.c.c[4][((int[])this.q.T.elementAt(this.h))[0]][2]) : "";
        if (this.q.T.size() <= 0) {
            return;
        }
        this.p.a.a((int)43).i().a = "" + this.q.G();
        this.p.a.a((int)44).i().a = "" + this.q.F();
        this.p.a.a(38).b(102 + this.h * 84 / this.q.T.size(), this.p.a.a());
    }

    /*
     * Enabled aggressive block sorting
     */
    public final void P() {
        if (this.o.g(4100)) {
            this.p.a.b(0);
            return;
        }
        if (this.o.g(8448)) {
            this.p.a.b(1);
            return;
        }
        if (this.f == 1 && this.o.g(16400) && this.c > 0) {
            int[] nArray = (int[])this.q.T.elementAt(this.h);
            --this.c;
            if (this.c <= 0) {
                this.c = this.q.a(nArray[0], (byte)0);
            }
            this.a(this.c, this.c * a.b.c.c[4][nArray[0]][3] / 2, (int)a.b.c.c[4][nArray[0]][4], 4);
            return;
        }
        if (this.f == 1 && this.o.g(32832)) {
            int[] nArray = (int[])this.q.T.elementAt(this.h);
            ++this.c;
            if (this.c > this.q.a(nArray[0], (byte)0)) {
                this.c = 1;
            }
            this.a(this.c, this.c * a.b.c.c[4][nArray[0]][3] / 2, (int)a.b.c.c[4][nArray[0]][4], 4);
            return;
        }
        if (this.o.g(196640) && this.q.T.size() > 0) {
            int[] nArray = (int[])this.q.T.elementAt(this.h);
            if (this.f == 0) {
                this.f = 1;
                this.p.a("/data/ui/msgyn.ui", 257, this);
                this.c = 1;
                this.r = 0;
                this.a(this.c, this.c * a.b.c.c[4][nArray[0]][3] / 2, (int)a.b.c.c[4][nArray[0]][4], 4);
                return;
            }
            if (this.r != 0) {
                this.p.a("/data/ui/msgyn.ui");
                this.f = 0;
                return;
            }
            this.q.d(nArray[0], this.c, (byte)0);
            this.q.s(this.c * a.b.c.c[4][nArray[0]][3] / 2);
        } else {
            if (!this.o.g(262144)) return;
            if (this.f == 0) {
                this.o.a((byte)1);
                this.p.a("/data/ui/shopbuy.ui");
                ((c.b)this.p.a.a((int)0)).a.f = this.b = 1;
                return;
            }
        }
        this.f = 0;
        this.p.a("/data/ui/msgyn.ui");
        this.q.y();
        this.aZ();
    }

    public final void Q() {
        this.p.a("/data/ui/record.ui", 257, this);
        this.p.a("/data/ui/gamemenu.ui");
        this.p.a.a((int)14).i().a = "" + (this.q.B + this.q.P.size());
        this.p.a.a((int)17).i().a = "" + this.q.G;
        this.p.a.a((int)20).i().a = "" + this.q.I;
        this.p.a.a((int)26).i().a = "" + this.q.H;
        int n2 = 0;
        for (byte by = 0; by < this.q.C.length; by = (byte)(by + 1)) {
            if (this.q.b(by, (byte)0) != 2) continue;
            ++n2;
        }
        this.p.a.a((int)29).i().a = "" + n2;
        long l2 = game.f.B().n + game.f.B().o - game.f.B().p;
        c.i i2 = this.p.a.a(31).i();
        game.l.B();
        i2.a = game.l.a(l2)[1];
        ((c.b)this.p.a.a((int)0)).b.f = this.c;
        this.b = 0;
        this.f = 0;
        this.g = true;
    }

    public final void R() {
        if (this.o.g(16400)) {
            this.p.a.b(2);
            this.g = true;
        } else if (this.o.g(32832)) {
            this.p.a.b(3);
            this.g = true;
        } else if (this.o.g(196640)) {
            if (this.f == 0) {
                switch (this.c) {
                    case 0: {
                        if (game.j.p().l(5)) {
                            this.o.a((byte)11);
                            break;
                        }
                        this.H();
                        this.a("Kh\u00f4ng \u0111\u1ea1t \u0111\u01b0\u1ee3c s\u1ee7ng v\u1eadt s\u00e1ch tranh \u0111\u1ea1o c\u1ee5", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                        this.f = 1;
                        break;
                    }
                    case 1: {
                        this.o.a((byte)12);
                    }
                }
            } else {
                this.f = 0;
                this.I();
            }
        } else if (this.o.g(262144) && this.f == 0) {
            this.b = a.a.i ? 3 : 2;
            this.o.a((byte)6);
            this.p.a("/data/ui/record.ui");
        }
        this.g = true;
    }

    public final void S() {
        this.p.a("/data/ui/petmap.ui", 257, this);
        this.p.a("/data/ui/record.ui");
        this.b = 0;
        this.c = 0;
        this.f = 0;
        ((c.b)this.p.a.a((int)0)).a.a(1);
        this.bb();
        this.g = true;
    }

    private void ba() {
        ((c.b)this.p.a.a((int)0)).a.e = 0;
        ((c.b)this.p.a.a((int)0)).a.f = 0;
    }

    private void bb() {
        int n2;
        ((c.b)this.p.a.a((int)0)).a.a = this.q.Y[this.b];
        this.v = ((c.b)this.p.a.a((int)0)).a.e;
        this.h = ((c.b)this.p.a.a((int)0)).a.f;
        int n3 = a.b.c.c[0][this.q.X[this.b] + this.h][17];
        if (this.q.a((byte)this.b, this.h + this.q.X[this.b]) > 0) {
            this.p.a.a(21).a(true);
            if (this.p.a.a((int)21).i().m != null) {
                this.p.a.a((int)21).i().m.c();
            } else {
                this.p.a.a((int)21).i().m = new c.g();
                this.p.a.a((int)21).i().m.a(0);
                this.p.a.a((int)21).i().m.a = (byte)3;
            }
            this.p.a.a((int)21).i().m.a(n3, false, (byte)-1);
            this.p.a.a((int)21).i().m.a((byte)1);
        } else {
            this.p.a.a(21).a(false);
        }
        n3 = 0;
        for (n2 = 0; n2 < this.q.Y[this.b]; ++n2) {
            if (this.q.a((byte)this.b, this.q.X[this.b] + n2) != 2) continue;
            ++n3;
        }
        for (n2 = 0; n2 < 5; ++n2) {
            if (this.p.a.a((int)(n2 + 44)).i().m == null) {
                this.p.a.a((int)(n2 + 44)).i().m = new c.g();
                this.p.a.a((int)(n2 + 44)).i().m.a(102);
                this.p.a.a((int)(n2 + 44)).i().m.a = (byte)2;
                this.p.a.a((int)(n2 + 44)).i().m.a(257, false, (byte)-1);
            }
            if (this.q.a((byte)this.b, n2 + this.v + this.q.X[this.b]) == 2) {
                this.p.a.a((int)(n2 + 44)).i().m.a(101);
            } else {
                this.p.a.a((int)(n2 + 44)).i().m.a(102);
            }
            this.p.a.a((int)(24 + (n2 << 2) + 3)).i().a = a.a.c(a.b.c.c[0][this.q.X[this.b] + n2 + this.v][0]);
        }
        this.p.a.a((int)20).i().a = a.a.c(365 + this.b) + n3 + "/" + this.q.Y[this.b];
        this.p.a.a(23).b(99 + (this.h << 6) / this.q.Y[this.b], this.p.a.a());
    }

    public final void T() {
        if (this.o.g(4100)) {
            this.p.a.b(0);
            this.bb();
        } else if (this.o.g(8448)) {
            this.p.a.b(1);
            this.bb();
        } else if (this.o.g(16400)) {
            this.p.a.b(2);
            this.ba();
            this.bb();
        } else if (this.o.g(32832)) {
            this.p.a.b(3);
            this.ba();
            this.bb();
        } else if (!this.o.g(196640) && this.o.g(786432)) {
            if (this.o.b == 8) {
                this.o.a((byte)8);
            } else {
                this.c = 0;
                this.o.a((byte)9);
            }
            this.p.a("/data/ui/petmap.ui");
        }
        this.g = true;
    }

    public final void U() {
        this.p.a("/data/ui/task.ui", 257, this);
        this.p.a("/data/ui/gamemenu.ui");
        ((c.b)this.p.a.a((int)0)).b.f = this.b;
        this.c = 0;
        this.r = 0;
        this.bc();
        this.bd();
    }

    private void bc() {
        switch (this.b) {
            case 0: {
                if (game.e.G >= game.e.E.length / 2 - 1) {
                    ((c.b)this.p.a.a((int)0)).a.a = game.e.E.length / 2;
                    ((c.b)this.p.a.a((int)0)).a.f = game.e.E.length / 2 - 1;
                } else {
                    ((c.b)this.p.a.a((int)0)).a.a = game.e.G + 1;
                    ((c.b)this.p.a.a((int)0)).a.f = game.e.G;
                }
                this.p.a.a((int)36).i().a = "";
                this.h = game.e.G;
                this.v = game.e.G - 4;
                if (this.h <= 0) {
                    this.h = 0;
                }
                if (this.v <= 0) {
                    this.v = 0;
                }
                ((c.b)this.p.a.a((int)0)).a.e = this.v;
                this.p.a.a((int)37).i().a = "\u0110\u1ea7u m\u1ed1i ch\u00ednh ho\u00e0n th\u00e0nh \u0111\u1ed9: ";
                this.p.a.a((int)38).i().a = game.e.G >= game.e.E.length / 2 ? game.e.E[game.e.E.length - 1] : game.e.E[game.e.E.length / 2 + game.e.G];
                int n2 = game.e.G * 1000 / (game.e.E.length / 2);
                if (!a.a.i) {
                    int n3 = n2 % 10;
                    if (n3 == 0) {
                        n3 = 1;
                    }
                    this.p.a.a((int)38).i().a = n2 / 50 + "." + n3 + "%";
                } else {
                    this.p.a.a((int)38).i().a = n2 / 10 + "." + n2 % 10 + "%";
                }
                if (game.e.G > 4) {
                    ((c.b)this.p.a.a((int)0)).a.a(1);
                } else {
                    ((c.b)this.p.a.a((int)0)).a.a(0);
                }
                this.p.a.a((int)8).i().g = 11290624;
                break;
            }
            case 1: {
                int n4;
                ((c.b)this.p.a.a((int)0)).a.a = game.e.H;
                ((c.b)this.p.a.a((int)0)).a.f = 0;
                ((c.b)this.p.a.a((int)0)).a.e = 0;
                this.p.a.a((int)36).i().a = "";
                this.p.a.a((int)37).i().a = "Chi nh\u00e1nh ho\u00e0n th\u00e0nh \u0111\u1ed9: ";
                int n5 = 0;
                for (n4 = 0; n4 < game.e.F.length; ++n4) {
                    if (game.e.F[n4][1] != 3) continue;
                    ++n5;
                }
                n4 = n5 * 1000 / (game.e.D.length / 2);
                this.p.a.a((int)38).i().a = n4 / 10 + "." + n4 % 10 + "%";
                if (game.e.H > 5) {
                    ((c.b)this.p.a.a((int)0)).a.a(1);
                } else {
                    ((c.b)this.p.a.a((int)0)).a.a(0);
                }
                this.p.a.a((int)9).i().g = 11290624;
            }
        }
        this.bd();
    }

    private void bd() {
        this.v = ((c.b)this.p.a.a((int)0)).a.e;
        this.h = ((c.b)this.p.a.a((int)0)).a.f;
        for (int i2 = 0; i2 < 5; ++i2) {
            if (this.b == 0) {
                if (game.e.G > 0) {
                    if (this.v + i2 < game.e.G) {
                        this.p.a.a((int)(10 + i2 * 5 + 2)).i().a = "" + (i2 + this.v + 1);
                        this.p.a.a((int)(10 + i2 * 5 + 3)).i().a = game.e.E[this.v + i2];
                        this.p.a.a((int)(10 + i2 * 5 + 4)).i().a = "Ho\u00e0n th\u00e0nh";
                        continue;
                    }
                    if (this.v + i2 == game.e.G && this.v + i2 <= game.e.E.length / 2 - 1) {
                        this.p.a.a((int)(10 + i2 * 5 + 2)).i().a = "" + (i2 + this.v + 1);
                        this.p.a.a((int)(10 + i2 * 5 + 3)).i().a = game.e.E[this.v + i2];
                        this.p.a.a((int)(10 + i2 * 5 + 4)).i().a = "";
                        continue;
                    }
                    this.p.a.a((int)(10 + i2 * 5 + 2)).i().a = "";
                    this.p.a.a((int)(10 + i2 * 5 + 3)).i().a = "";
                    this.p.a.a((int)(10 + i2 * 5 + 4)).i().a = "";
                    this.p.a.a((int)36).i().a = "";
                    continue;
                }
                this.p.a.a((int)12).i().a = "1";
                this.p.a.a((int)13).i().a = game.e.E[0];
                this.p.a.a((int)14).i().a = "";
                continue;
            }
            if (this.b != 1) continue;
            if (this.v + i2 < game.e.H) {
                this.p.a.a((int)(10 + i2 * 5 + 2)).i().a = "" + (i2 + this.v + 1);
                this.p.a.a((int)(10 + i2 * 5 + 3)).i().a = game.e.D[game.e.F[this.v + i2][0]];
                if (game.e.F[this.v + i2][1] == 3) {
                    this.p.a.a((int)(10 + i2 * 5 + 4)).i().a = "Ho\u00e0n th\u00e0nh";
                    continue;
                }
                this.p.a.a((int)(10 + i2 * 5 + 4)).i().a = "";
                continue;
            }
            this.p.a.a((int)(10 + i2 * 5 + 2)).i().a = "";
            this.p.a.a((int)(10 + i2 * 5 + 3)).i().a = "";
            this.p.a.a((int)(10 + i2 * 5 + 4)).i().a = "";
        }
        switch (this.b) {
            case 0: {
                this.p.a.a((int)36).i().a = game.e.E[game.e.E.length / 2 + this.h];
                break;
            }
            case 1: {
                if (game.e.H <= 0) break;
                this.p.a.a((int)36).i().a = game.e.D[game.e.D.length / 2 + game.e.F[this.h][0]];
            }
        }
        if (((c.b)this.p.a.a((int)0)).a.a > 0) {
            this.p.a.a(40).b(104 + (this.h << 6) / ((c.b)this.p.a.a((int)0)).a.a, this.p.a.a());
        }
    }

    public final void V() {
        if (this.o.g(4100)) {
            this.p.a.b(0);
            this.bd();
            return;
        }
        if (this.o.g(8448)) {
            this.p.a.b(1);
            this.bd();
            return;
        }
        if (this.o.g(16400)) {
            this.p.a.b(2);
            this.bc();
            return;
        }
        if (this.o.g(32832)) {
            this.p.a.b(3);
            this.bc();
            return;
        }
        if (this.o.g(983072)) {
            this.b = a.a.i ? 4 : 3;
            this.p.a("/data/ui/task.ui");
            if (this.o.b == 0) {
                this.b = 0;
                this.o.a((byte)0);
                return;
            }
            if (this.o.b == 33) {
                this.o.a((byte)33);
                return;
            }
            this.o.a((byte)6);
            return;
        }
        if (this.o.g(10)) {
            this.p.a("/data/ui/task.ui");
            this.o.a((byte)0);
        }
    }

    public final void W() {
        this.p.a("/data/ui/badge.ui", 257, this);
        this.p.a("/data/ui/record.ui");
        this.b = 0;
        this.f = 0;
        for (int i2 = 0; i2 < 8; ++i2) {
            if (this.q.C[i2][0] == 0) continue;
            this.p.a.a((int)(i2 + 25)).i().m.a(i2 + 46);
        }
        this.be();
    }

    private void be() {
        this.p.a.a((int)13).i().a = a.a.c(a.b.c.c[2][this.b][0]);
        this.p.a.a((int)14).i().a = a.a.c(a.b.c.c[2][this.b][2 + this.q.b((byte)this.b, (byte)1)]);
        if (this.q.b((byte)this.b, (byte)0) == 0) {
            this.p.a.a((int)16).i().a = "Ch\u01b0a \u0111\u1ea1t";
            return;
        }
        this.p.a.a((int)16).i().a = "\u0110\u00e3 \u0111\u1ea1t \u0111\u01b0\u1ee3c";
        this.q.b((byte)this.b, (byte)1);
        this.p.a.a((int)33).i().a = "";
    }

    public final void X() {
        if (this.o.g(4100)) {
            this.p.a.b(0);
            this.be();
            return;
        }
        if (this.o.g(8448)) {
            this.p.a.b(1);
            this.be();
            return;
        }
        if (this.o.g(16400)) {
            this.p.a.b(2);
            this.be();
            return;
        }
        if (this.o.g(32832)) {
            this.p.a.b(3);
            this.be();
            return;
        }
        if (this.o.g(786432)) {
            if (this.o.b == 8) {
                this.o.a((byte)8);
            } else {
                this.c = 1;
                this.o.a((byte)9);
            }
            this.p.a("/data/ui/badge.ui");
        }
    }

    public final void a(int n2) {
        this.p.a("/data/ui/smsTip.ui", 257, this);
        if (this.p.a.a((int)6).i().m == null) {
            this.p.a.a((int)6).i().m = new c.g();
            this.p.a.a((int)6).i().m.a = (byte)2;
            this.p.a.a((int)6).i().m.a(-1);
            this.p.a.a((int)6).i().m.a(257, false, (byte)-1);
            this.p.a.a((int)6).i().m.a(n2 + 46);
        }
        this.p.a.a((int)7).i().a = a.a.c(n2 + 187) + ":" + a.a.c(n2 + 195);
        this.p.a.a((int)8).i().a = a.a.c(377);
    }

    public final void Y() {
        this.p.a("/data/ui/smsTip.ui");
    }

    public final void Z() {
        this.b = 0;
        this.f(this.c);
    }

    private void f(int n2) {
        int n3 = n2;
        i[] iArray = this.q.A;
        k k2 = this;
        k2.p.a("/data/ui/petstate.ui", 257, k2);
        k2.g(n3);
        k2.f = 0;
        if (k2.o instanceof l) {
            for (int i2 = 0; i2 < 6; ++i2) {
                if (iArray[i2] != null) {
                    k2.p.a.a((int)(16 + i2 * 6)).i().a = "#P" + iArray[i2].M();
                    k2.p.a.a((int)(17 + i2 * 6)).i().a = "#P" + iArray[i2].P();
                    continue;
                }
                k2.p.a.a((int)(16 + i2 * 6)).i().a = "#P0";
                k2.p.a.a((int)(17 + i2 * 6)).i().a = "#P0";
            }
            if (k2.o.b == 16) {
                k2.p.a.a((int)64).i().a = "G\u1edfi l\u1ea1i";
            }
            k2.p.a.a(75).a(false);
            k2.p.a.a(76).a(false);
        } else if (k2.o instanceof a) {
            for (int i3 = 0; i3 < 6; ++i3) {
                if (i3 < ((a)k2.o).p.length && iArray[((a)k2.o).p[i3]] != null) {
                    k2.p.a.a((int)(16 + i3 * 6)).i().a = "#P" + iArray[((a)k2.o).p[i3]].M();
                    k2.p.a.a((int)(17 + i3 * 6)).i().a = "#P" + iArray[((a)k2.o).p[i3]].P();
                    continue;
                }
                k2.p.a.a((int)(16 + i3 * 6)).i().a = "#P0";
                k2.p.a.a((int)(17 + i3 * 6)).i().a = "#P0";
            }
            k2.p.a.a(63).a(false);
            k2.p.a.a(64).a(false);
            if (k2.o.b == 4) {
                k2.p.a.a((int)75).i().a = "S\u1eed d\u1ee5ng";
            } else if (k2.o.a == 5) {
                k2.p.a.a((int)75).i().a = "Xu\u1ea5t chi\u1ebfn";
            }
        }
        ((c.b)k2.p.a.a((int)0)).a.a = k2.q.B;
        ((c.b)k2.p.a.a((int)0)).a.d = k2.q.B;
        ((c.b)k2.p.a.a((int)0)).a.f = n3;
        k2.g = true;
    }

    private void a(i[] iArray, int n2) {
        if (iArray[n2] != null) {
            if (this.p.a.a((int)48).i().m != null) {
                this.p.a.a((int)48).i().m.c();
            } else {
                this.p.a.a((int)48).i().m = new c.g();
                this.p.a.a((int)48).i().m.a(0);
                this.p.a.a((int)48).i().m.a = (byte)3;
            }
            this.p.a.a((int)48).i().m.a(iArray[n2].D, false, (byte)-1);
            this.p.a.a((int)51).i().a = a.a.c(iArray[n2].j((byte)0));
            this.p.a.a((int)52).i().a = a.a.c(365 + iArray[n2].j((byte)1));
            if (iArray[n2].j((byte)19) == -1) {
                this.p.a.a((int)62).i().a = "";
            } else if (a.b.c.c[0][iArray[n2].j((byte)19)][2] == 1 || a.b.c.c[0][iArray[n2].j((byte)19)][2] == 2) {
                this.p.a.a((int)62).i().a = "C\u00f3 th\u1ec3 ti\u1ebfn h\u00f3a";
            } else if (a.b.c.c[0][iArray[n2].j((byte)19)][2] == 3) {
                this.p.a.a((int)62).i().a = "C\u00f3 th\u1ec3 d\u1ecb ho\u00e1";
            }
            this.p.a.a((int)61).i().a = iArray[n2].U();
            if (this.o instanceof a) {
                this.p.a.a((int)64).i().a = "Xu\u1ea5t chi\u1ebfn";
            } else if (this.o instanceof l) {
                this.p.a.a((int)64).i().a = "X\u00e1c nh\u1eadn";
            }
            if (this.p.a.a((int)59).i().m == null) {
                this.p.a.a((int)59).i().m = new c.g();
                this.p.a.a((int)59).i().m.a(0);
                this.p.a.a((int)59).i().m.a = (byte)2;
                this.p.a.a((int)59).i().m.a(258, false, (byte)-1);
            }
            int n3 = 5;
            i i2 = iArray[n2];
            if (i2.d[n3] != -1) {
                n3 = 5;
                i2 = iArray[n2];
                this.p.a.a((int)59).i().m.a(a.b.c.c[3][i2.d[n3]][1]);
                n3 = 5;
                i2 = iArray[n2];
                this.p.a.a((int)60).i().a = a.a.c(a.b.c.c[3][i2.d[n3]][0]);
            } else {
                this.p.a.a((int)59).i().m.a(0);
                this.p.a.a((int)60).i().a = "";
            }
            this.p.a.a((int)65).i().a = "" + iArray[n2].t();
            this.p.a.a((int)66).i().a = "" + iArray[n2].a((byte)2);
            this.p.a.a((int)67).i().a = "" + iArray[n2].a((byte)3);
            this.p.a.a((int)68).i().a = "" + iArray[n2].a((byte)4);
            n3 = 0;
            i2 = iArray[n2];
            int n4 = i2.e[n3];
            int n5 = a.b.c.a((byte)0, (short)iArray[n2].r(), (byte)4) - 1;
            for (n2 = 0; n2 < 5; ++n2) {
                this.p.a.a(74 - n2).a(true);
                this.p.a.a((int)(74 - n2)).i().m.a(257, false, (byte)-1);
                this.p.a.a((int)(74 - n2)).i().m.a = (byte)3;
                if (n2 > n5) {
                    this.p.a.a(74 - n2).a(false);
                    continue;
                }
                if (n4 > 0) {
                    this.p.a.a((int)(74 - n2)).i().m.a((byte)14, (byte)-1);
                    --n4;
                    continue;
                }
                this.p.a.a((int)(74 - n2)).i().m.a((byte)16, (byte)-1);
            }
        }
    }

    private void g(int n2) {
        if (this.o instanceof l) {
            this.a(this.q.A, n2);
            return;
        }
        if (this.o instanceof a) {
            this.a(this.q.A, (int)((a)this.o).p[n2]);
        }
    }

    public final void aa() {
        block89: {
            block92: {
                block91: {
                    block90: {
                        block88: {
                            if (this.f != 0) break block88;
                            if (!a.a.a(this.b, 0) && !this.j() && this.o.g(4100)) {
                                this.p.a.b(0);
                            } else if (!a.a.a(this.b, 0) && !this.j() && this.o.g(8448)) {
                                this.p.a.b(1);
                            } else if (a.a.s() && !this.j() && this.o.g(196640)) {
                                if (a.a.p() && !a.a.a(this.b, 0)) {
                                    return;
                                }
                                if (this.o instanceof a) {
                                    int n2 = ((a)this.o).l(this.b);
                                    if (n2 == 0) {
                                        this.f = 2;
                                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                                        this.a("S\u1ee7ng v\u1eadt n\u00e0y kh\u00f4ng th\u1ec3 tham chi\u1ebfn", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                        this.p.a("/data/ui/petsetting.ui");
                                    } else if (n2 == 1) {
                                        this.f = 2;
                                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                                        this.a("S\u1ee7ng v\u1eadt n\u00e0y \u0111\u00e3 \u0111\u1eb7t \u1edf v\u1ecb tr\u00ed chi\u1ebfn \u0111\u1ea5u", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                        this.p.a("/data/ui/petsetting.ui");
                                    } else if (n2 == -1) {
                                        ((a)this.o).e(((a)this.o).q, 0);
                                        this.a = 0;
                                        this.o.a((byte)15);
                                        this.p.a("/data/ui/petsetting.ui");
                                        this.p.a("/data/ui/petstate.ui");
                                    }
                                } else if (this.o instanceof l) {
                                    if (this.o.b == 16) {
                                        if (this.q.A()) {
                                            if (this.q.i(this.b)) {
                                                this.q.m(this.q.A[this.b].b((byte)5));
                                                this.q.A[this.b].a((byte)5, (short)-1);
                                                this.q.b(this.q.A[this.b].Q());
                                                this.q.n(this.b);
                                                if (this.b >= this.q.B) {
                                                    --this.b;
                                                }
                                                this.f(this.b);
                                            } else {
                                                this.f = 1;
                                                this.p.a("/data/ui/msgwarm.ui", 257, this);
                                                this.a("Ba l\u00f4 ph\u1ea3i l\u01b0u \u00edt nh\u1ea5t 1 s\u1ee7ng v\u1eadt", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                            }
                                        } else {
                                            this.f = 1;
                                            this.p.a("/data/ui/msgwarm.ui", 257, this);
                                            this.a("Ng\u00e2n h\u00e0ng \u0111\u00e3 \u0111\u1ea7y, kh\u00f4ng th\u1ec3 g\u1edfi l\u1ea1i", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                        }
                                    } else if (this.o.b == 6 || this.o.b == 0) {
                                        this.c = 0;
                                        this.o.r();
                                        this.f = 1;
                                        this.p.a("/data/ui/petsetting.ui", 257, this);
                                        ((c.b)this.p.a.a((int)0)).a.f = this.c;
                                        if (this.q.A[this.b].S() == 2) {
                                            this.p.a.a((int)9).i().a = "D\u1ecb ho\u00e1";
                                            ((c.b)this.p.a.a((int)0)).a.a = 6;
                                            ((c.b)this.p.a.a((int)0)).a.d = 6;
                                        } else if (this.q.A[this.b].S() == 1) {
                                            this.p.a.a((int)9).i().a = "Ti\u1ebfn h\u00f3a";
                                            ((c.b)this.p.a.a((int)0)).a.a = 6;
                                            ((c.b)this.p.a.a((int)0)).a.d = 6;
                                        } else {
                                            this.p.a.a((int)9).i().a = "";
                                            ((c.b)this.p.a.a((int)0)).a.a = 5;
                                            ((c.b)this.p.a.a((int)0)).a.d = 5;
                                        }
                                    } else if (this.o.b == 27) {
                                        if (this.d == 1 && this.q.A[this.b].S() == 1 || this.d == 2 && this.q.A[this.b].S() == 2) {
                                            this.bl();
                                        } else {
                                            this.f = 4;
                                            this.H();
                                            if (this.d == 1) {
                                                this.a("S\u1ee7ng v\u1eadt n\u00e0y kh\u00f4ng th\u1ec3 ti\u1ebfn h\u00f3a", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                            } else if (this.d == 2) {
                                                this.a("S\u1ee7ng v\u1eadt n\u00e0y kh\u00f4ng th\u1ec3 d\u1ecb ho\u00e1", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                            } else {
                                                this.a("Kh\u00f4ng th\u1ec3 v\u00e0o h\u00f3a c\u00f9ng d\u1ecb ho\u00e1", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                            }
                                        }
                                    }
                                }
                            } else if (game.e.t() && !this.j() && this.o.g(262144)) {
                                if (this.o instanceof l) {
                                    if (this.o.b == 16) {
                                        this.o.a((byte)16);
                                    } else if (this.o.b == 6) {
                                        this.b = a.a.i ? 1 : 0;
                                        this.o.a((byte)6);
                                    } else if (this.o.b == 27) {
                                        this.o.a((byte)27);
                                    } else if (this.o.b == 0) {
                                        this.o.a((byte)23);
                                    }
                                    this.p.a("/data/ui/petstate.ui");
                                } else if (this.o instanceof a) {
                                    if (((a)this.o).b == 7 || ((a)this.o).b == 13) {
                                        return;
                                    }
                                    this.p.a("/data/ui/petstate.ui");
                                    game.a.B().u = false;
                                    this.a = 0;
                                    this.o.a((byte)20);
                                }
                            }
                            break block89;
                        }
                        if (this.f != 1) break block90;
                        if (!a.a.a(this.c, 0) && !this.j() && this.o.g(4100)) {
                            this.p.a.b(0);
                        } else if (!a.a.a(this.c, 0) && !this.j() && this.o.g(8448)) {
                            this.p.a.b(1);
                        } else if (a.a.s() && !this.j() && this.o.g(196640)) {
                            if (a.a.p() && !a.a.a(this.c, 0)) {
                                return;
                            }
                            if (this.o.b == 16) {
                                this.o.a((byte)16);
                                this.p.a("/data/ui/msgwarm.ui");
                                this.p.a("/data/ui/petstate.ui");
                                this.f = 0;
                            } else if (this.o.b == 6 || this.o.b == 0) {
                                switch (this.c) {
                                    case 0: {
                                        this.bh();
                                        break;
                                    }
                                    case 1: {
                                        if (!this.q.A[this.b].T()) {
                                            this.f = 2;
                                            this.p.a("/data/ui/msgwarm.ui", 257, this);
                                            this.a("S\u1ee7ng v\u1eadt n\u00e0y kh\u00f4ng th\u1ec3 tham chi\u1ebfn", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                            this.p.a("/data/ui/petsetting.ui");
                                            this.b = 0;
                                            break;
                                        }
                                        if (this.b == 0) {
                                            this.f = 2;
                                            this.b = 0;
                                            this.p.a("/data/ui/msgwarm.ui", 257, this);
                                            this.a("S\u1ee7ng v\u1eadt n\u00e0y \u0111\u00e3 xu\u1ea5t chi\u1ebfn", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                            this.p.a("/data/ui/petsetting.ui");
                                            break;
                                        }
                                        this.q.p(this.b);
                                        this.f = 0;
                                        this.b = 0;
                                        this.f(this.b);
                                        this.p.a("/data/ui/petsetting.ui");
                                        ((c.b)this.p.a.a((int)0)).a.f = 0;
                                        ((c.b)this.p.a.a((int)0)).a.e = 0;
                                        break;
                                    }
                                    case 2: {
                                        this.o.r();
                                        this.bf();
                                        break;
                                    }
                                    case 3: {
                                        if (a.b.c.a((byte)0, (short)this.q.A[this.b].r(), (byte)22) == 2) {
                                            this.f = 3;
                                            this.H();
                                            this.p.a("/data/ui/petsetting.ui");
                                            this.a("Th\u1ea7n th\u00fa kh\u00f4ng th\u1ec3 ph\u00f3ng sinh", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                            break;
                                        }
                                        this.f = 2;
                                        this.p.a("/data/ui/msgconfirm.ui", 257, this);
                                        this.p.a("/data/ui/petsetting.ui");
                                        this.b("B\u1ea1n mu\u1ed1n ph\u00f3ng sinh s\u1ee7ng v\u1eadt n\u00e0y?", "X\u00e1c nh\u1eadn");
                                        break;
                                    }
                                    case 4: {
                                        this.bj();
                                        break;
                                    }
                                    case 5: {
                                        this.o.r();
                                        this.bl();
                                    }
                                    default: {
                                        break;
                                    }
                                }
                            }
                        } else if (game.e.t() && !this.j() && this.o.g(262144)) {
                            if (this.o.b == 16) {
                                return;
                            }
                            this.f = 0;
                            this.p.a("/data/ui/petsetting.ui");
                        }
                        break block89;
                    }
                    if (this.f < 2) break block89;
                    if (!(this.o instanceof a)) break block91;
                    if (this.o.g(196640)) {
                        this.f = 0;
                        this.p.a("/data/ui/msgwarm.ui");
                    }
                    break block89;
                }
                if (this.o.b != 6 && this.o.b != 0) break block92;
                switch (this.c) {
                    case 1: {
                        if (this.o.g(196640)) {
                            this.f = 0;
                            this.p.a("/data/ui/msgwarm.ui");
                            break;
                        }
                        break block89;
                    }
                    case 2: {
                        this.bm();
                        break;
                    }
                    case 0: {
                        this.bn();
                        break;
                    }
                    case 4: {
                        this.bo();
                        break;
                    }
                    case 3: {
                        if (this.o.g(131072) && this.f == 2 || this.o.g(196640) && this.f == 3) {
                            if (this.f == 2) {
                                if (this.q.i(this.b)) {
                                    this.q.m(this.q.A[this.b].b((byte)5));
                                    this.q.A[this.b].a((byte)5, (short)-1);
                                    this.q.n(this.b);
                                    if (this.b >= this.q.B) {
                                        --this.b;
                                    }
                                    this.f(this.b);
                                    ((l)this.o).Z.G();
                                    this.p.a("/data/ui/msgconfirm.ui");
                                    this.f = 0;
                                    break;
                                }
                                this.f = 3;
                                this.p.a("/data/ui/msgwarm.ui", 257, this);
                                this.a("Ba l\u00f4 ph\u1ea3i l\u01b0u \u00edt nh\u1ea5t 1 s\u1ee7ng v\u1eadt", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                this.p.a("/data/ui/msgconfirm.ui");
                                break;
                            }
                            this.f = 0;
                            this.p.a("/data/ui/msgwarm.ui");
                            break;
                        }
                        if (this.o.g(786432) && this.f <= 2) {
                            this.f = 0;
                            this.p.a("/data/ui/msgconfirm.ui");
                            break;
                        }
                        break block89;
                    }
                    case 5: {
                        this.bp();
                    }
                }
                break block89;
            }
            if (this.f <= 3) {
                this.bp();
            } else if (this.f == 4 && this.o.g(196640)) {
                this.f = 0;
                this.p.a("/data/ui/msgwarm.ui");
            }
        }
        this.g = true;
        this.g();
    }

    private void bf() {
        this.f = 2;
        this.r = 0;
        this.p.a("/data/ui/choice.ui", 257, this);
        this.p.a("/data/ui/petsetting.ui");
        this.p.a("/data/ui/petstate.ui");
        this.p.a.a((int)8).i().a = "V\u1eadt ph\u1ea9m trang s\u1ee9c";
        this.p.a.a((int)9).i().a = "Tr\u1ea1ng th\u00e1i";
        if (this.o instanceof l) {
            this.p.a.a(5).a(false);
            this.p.a.a(6).a(false);
            this.p.a.a(59).a(true);
            this.p.a.a(60).a(true);
            this.p.a.a((int)59).i().a = "Mang theo";
        } else {
            this.p.a.a(5).a(true);
            this.p.a.a(6).a(true);
            this.p.a.a(59).a(false);
            this.p.a.a(60).a(false);
            this.p.a.a((int)5).i().a = "Mang theo";
        }
        this.bg();
        this.g = true;
    }

    private void bg() {
        if (this.q.M.size() > 5) {
            ((c.b)this.p.a.a((int)0)).a.a(1);
        } else {
            ((c.b)this.p.a.a((int)0)).a.a(0);
        }
        ((c.b)this.p.a.a((int)0)).a.a = this.q.M.size();
        this.v = ((c.b)this.p.a.a((int)0)).a.e;
        this.h = ((c.b)this.p.a.a((int)0)).a.f;
        if (this.h >= this.q.M.size()) {
            ((c.b)this.p.a.a((int)0)).a.f = this.h = this.q.M.size() - 1;
        }
        if (this.v > 0 && this.h - this.v < 4) {
            --this.v;
            ((c.b)this.p.a.a((int)0)).a.e = this.v;
        }
        if (this.q.M.size() <= 0) {
            return;
        }
        int n2 = 5;
        i i2 = this.q.A[this.b];
        if (i2.d[n2] == ((int[])this.q.M.elementAt(this.h))[0]) {
            if (this.o instanceof l) {
                this.p.a.a((int)59).i().a = "D\u1ee1 xu\u1ed1ng";
            } else {
                this.p.a.a((int)5).i().a = "D\u1ee1 xu\u1ed1ng";
            }
        } else if (this.o instanceof l) {
            this.p.a.a((int)59).i().a = "Mang theo";
        } else {
            this.p.a.a((int)5).i().a = "Mang theo";
        }
        for (int i3 = 0; i3 < 5; ++i3) {
            if (this.v + i3 < this.q.M.size()) {
                int[] nArray = (int[])this.q.M.elementAt(this.v + i3);
                if (this.p.a.a((int)(i3 + 54)).i().m == null) {
                    this.p.a.a((int)(i3 + 54)).i().m = new c.g();
                    this.p.a.a((int)(i3 + 54)).i().m.a(0);
                    this.p.a.a((int)(i3 + 54)).i().m.a = (byte)2;
                    this.p.a.a((int)(i3 + 54)).i().m.a(258, false, (byte)-1);
                }
                this.p.a.a((int)(i3 + 54)).i().m.a(a.b.c.c[3][nArray[0]][1]);
                this.p.a.a((int)(13 + i3 * 5)).i().a = a.a.c(a.b.c.c[3][nArray[0]][0]);
                n2 = 5;
                i2 = game.j.p().A[this.b];
                if (i2.d[n2] == nArray[0]) {
                    this.p.a.a((int)(14 + i3 * 5)).i().a = "\u0110\u00e3 mang theo";
                    continue;
                }
                if (nArray[1] == 1) {
                    this.p.a.a((int)(14 + i3 * 5)).i().a = "B\u1ecb mang theo";
                    continue;
                }
                this.p.a.a((int)(14 + i3 * 5)).i().a = "";
                continue;
            }
            if (this.p.a.a((int)(i3 + 54)).i().m != null) {
                this.p.a.a((int)(i3 + 54)).i().m.c();
            }
            this.p.a.a((int)(13 + i3 * 5)).i().a = "";
            this.p.a.a((int)(14 + i3 * 5)).i().a = "";
        }
        this.p.a.a((int)53).i().a = this.q.M.size() > 0 ? a.a.c(a.b.c.c[3][((int[])this.q.M.elementAt(this.h))[0]][2]) : "";
        if (this.q.M.size() > 0) {
            this.p.a.a(51).b(98 + this.h * 62 / this.q.M.size(), this.p.a.a());
            return;
        }
        this.p.a.a(51).b(98, this.p.a.a());
    }

    private void bh() {
        this.f = 2;
        this.r = 0;
        this.p.a("/data/ui/choice.ui", 257, this);
        this.p.a("/data/ui/petsetting.ui");
        this.p.a("/data/ui/petstate.ui");
        this.p.a.a((int)8).i().a = "\u0110\u1ea1o c\u1ee5";
        this.p.a.a((int)9).i().a = "S\u1ed1 l\u01b0\u1ee3ng";
        if (this.o instanceof l) {
            this.p.a.a(5).a(false);
            this.p.a.a(6).a(false);
            this.p.a.a(59).a(true);
            this.p.a.a(60).a(true);
            this.p.a.a((int)59).i().a = "S\u1eed d\u1ee5ng";
        } else {
            this.p.a.a(5).a(true);
            this.p.a.a(6).a(true);
            this.p.a.a(59).a(false);
            this.p.a.a(60).a(false);
            this.p.a.a((int)5).i().a = "S\u1eed d\u1ee5ng";
        }
        this.bi();
        this.g = true;
    }

    private void bi() {
        if (this.q.K.size() > 5) {
            ((c.b)this.p.a.a((int)0)).a.a(1);
        } else {
            ((c.b)this.p.a.a((int)0)).a.a(0);
        }
        ((c.b)this.p.a.a((int)0)).a.a = this.q.K.size();
        this.v = ((c.b)this.p.a.a((int)0)).a.e;
        this.h = ((c.b)this.p.a.a((int)0)).a.f;
        if (this.h >= this.q.K.size()) {
            ((c.b)this.p.a.a((int)0)).a.f = this.h = this.q.K.size() - 1;
        }
        if (this.v > 0 && this.h - this.v < 4) {
            --this.v;
            ((c.b)this.p.a.a((int)0)).a.e = this.v;
        }
        for (int i2 = 0; i2 < 5; ++i2) {
            if (this.v + i2 < this.q.K.size()) {
                int[] nArray = (int[])this.q.K.elementAt(this.v + i2);
                if (this.p.a.a((int)(i2 + 54)).i().m == null) {
                    this.p.a.a((int)(i2 + 54)).i().m = new c.g();
                    this.p.a.a((int)(i2 + 54)).i().m.a(0);
                    this.p.a.a((int)(i2 + 54)).i().m.a = (byte)2;
                    this.p.a.a((int)(i2 + 54)).i().m.a(258, false, (byte)-1);
                }
                this.p.a.a((int)(i2 + 54)).i().m.a(a.b.c.c[4][nArray[0]][1]);
                this.p.a.a((int)(13 + i2 * 5)).i().a = a.a.c(a.b.c.c[4][nArray[0]][0]);
                this.p.a.a((int)(14 + i2 * 5)).i().a = "" + nArray[1];
                continue;
            }
            if (this.p.a.a((int)(i2 + 54)).i().m != null) {
                this.p.a.a((int)(i2 + 54)).i().m.c();
            }
            this.p.a.a((int)(13 + i2 * 5)).i().a = "";
            this.p.a.a((int)(14 + i2 * 5)).i().a = "";
        }
        this.p.a.a((int)53).i().a = this.q.K.size() > 0 ? a.a.c(a.b.c.c[4][((int[])this.q.K.elementAt(this.h))[0]][2]) : "";
        if (this.q.K.size() > 0) {
            this.p.a.a(51).b(98 + this.h * 80 / this.q.K.size(), this.p.a.a());
            return;
        }
        this.p.a.a(51).b(98, this.p.a.a());
    }

    private void bj() {
        this.f = 2;
        this.r = 0;
        this.p.a("/data/ui/skill.ui", 257, this);
        this.p.a("/data/ui/petsetting.ui");
        this.p.a("/data/ui/petstate.ui");
        this.p.a.a((int)12).i().a = a.a.c(this.q.A[this.b].j((byte)0));
        this.p.a.a((int)14).i().a = "" + this.q.A[this.b].t();
        if (this.p.a.a((int)16).i().m != null) {
            this.p.a.a((int)16).i().m.c();
        } else {
            this.p.a.a((int)16).i().m = new c.g();
            this.p.a.a((int)16).i().m.a(0);
            this.p.a.a((int)16).i().m.a = (byte)3;
        }
        this.p.a.a((int)16).i().m.a(this.q.A[this.b].D, false, (byte)-1);
        int n2 = this.q.A[this.b].F();
        for (int i2 = 0; i2 < n2; ++i2) {
            this.p.a.a((int)(i2 + 18)).i().a = a.a.c(a.b.c.c[1][this.q.A[this.b].t(i2)][1]);
        }
        this.bk();
        this.g = true;
    }

    private void bk() {
        if (this.q.A[this.b].t(this.r) != -1) {
            String[] stringArray = new String[]{"Nh\u1ea5t \u0111\u1ecbnh", "Nh\u1ea5t \u0111\u1ecbnh"};
            this.p.a.a((int)9).i().a = a.a.a((int)a.b.c.c[1][this.q.A[this.b].t(this.r)][2], stringArray);
            return;
        }
        this.p.a.a((int)9).i().a = "";
    }

    private void bl() {
        this.f = 2;
        this.r = 0;
        this.p.a("/data/ui/evolve.ui", 257, this);
        this.p.a("/data/ui/petsetting.ui");
        this.p.a("/data/ui/petstate.ui");
        if (this.p.a.a((int)10).i().m == null) {
            this.p.a.a((int)10).i().m = new c.g();
            this.p.a.a((int)10).i().m.a(0);
            this.p.a.a((int)10).i().m.a = (byte)3;
        }
        this.p.a.a((int)10).i().m.a(this.q.A[this.b].D, false, (byte)-1);
        int n2 = a.b.c.a((byte)0, (byte)this.q.A[this.b].r(), (byte)20) + 12;
        short s = a.b.c.a((byte)0, (byte)this.q.A[this.b].r(), (byte)21);
        this.p.a.a((int)38).i().a = a.a.c(a.b.c.a((byte)0, (byte)this.q.A[this.b].r(), (byte)0));
        this.p.a.a((int)40).i().a = "" + this.q.A[this.b].t();
        this.p.a.a((int)45).i().a = a.a.c(a.b.c.a((byte)3, (short)n2, (byte)0));
        this.p.a.a((int)46).i().a = this.q.a(n2, (byte)2) + "/" + s;
        n2 = a.b.c.a((byte)0, (byte)this.q.A[this.b].r(), (byte)19);
        i i2 = new i();
        i2.a(n2, (byte)this.q.A[this.b].t(), (short)-1, (byte)-1, (short)-1, (byte)-1);
        for (n2 = 0; n2 < 4; ++n2) {
            byte by = (byte)(n2 + 1);
            i i3 = this.q.A[this.b];
            this.p.a.a((int)(n2 + 19)).i().a = "" + i3.d[by];
            by = (byte)(n2 + 1);
            i3 = i2;
            this.p.a.a((int)(n2 + 31)).i().a = "" + i3.d[by];
        }
        this.g = true;
    }

    private void bm() {
        if (!a.a.a(this.b, 0) && !this.j() && this.f == 2 && this.o.g(4100)) {
            this.p.a.b(0);
            this.bg();
            return;
        }
        if (!a.a.a(this.b, 0) && !this.j() && this.f == 2 && this.o.g(8448)) {
            this.p.a.b(1);
            this.bg();
            return;
        }
        if (a.a.s() && !this.j() && this.o.g(196640) && this.q.M.size() > 0) {
            if (a.a.p() && !a.a.a(this.b, 0)) {
                return;
            }
            if (this.f == 2) {
                int[] nArray = (int[])this.q.M.elementAt(this.h);
                int n2 = 5;
                i i2 = this.q.A[this.b];
                if (i2.d[n2] == nArray[0]) {
                    n2 = 5;
                    i2 = this.q.A[this.b];
                    this.q.m(i2.d[n2]);
                    int n3 = -1;
                    n2 = 5;
                    i2 = this.q.A[this.b];
                    i2.d[n2] = n3;
                    this.bg();
                    this.H();
                    this.a("Th\u00e0nh c\u00f4ng d\u1ee1 xu\u1ed1ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                } else {
                    this.q.f(nArray[0], this.b);
                    this.bg();
                    this.H();
                    this.a("Th\u00e0nh c\u00f4ng mang theo", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                }
                this.f = 3;
                return;
            }
            this.f = 2;
            this.o.r();
            this.f(this.b);
            this.I();
            this.p.a("/data/ui/choice.ui");
            return;
        }
        if (game.e.t() && !this.j() && this.f == 2 && this.o.g(262144)) {
            this.f(this.b);
            this.p.a("/data/ui/choice.ui");
        }
    }

    private void bn() {
        if (this.f == 2 && this.o.g(4100)) {
            this.p.a.b(0);
            return;
        }
        if (this.f == 2 && this.o.g(8448)) {
            this.p.a.b(1);
            return;
        }
        if (this.o.g(196640)) {
            if (this.q.K.size() <= 0) {
                return;
            }
            if (this.f == 2) {
                this.f = 3;
                int[] nArray = (int[])this.q.K.elementAt(this.r);
                switch (nArray[0]) {
                    case 13: 
                    case 14: {
                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                        this.a("\u0110\u1ea1o c\u1ee5 n\u00e0y kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                        return;
                    }
                }
                switch (this.q.A[this.b].x(nArray[0])) {
                    case 0: {
                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                        this.a("S\u1ee7ng v\u1eadt n\u00e0y \u0111\u00e3 t\u1eed vong, kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                        return;
                    }
                    case 1: {
                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                        this.a("S\u1ee7ng v\u1eadt n\u00e0y kh\u00f4ng c\u00f3, kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                        return;
                    }
                    case 2: {
                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                        this.a("M\u00e1u \u0111\u1ea7y, kh\u00f4ng c\u1ea7n s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                        return;
                    }
                    case 3: {
                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                        this.a("K\u1ef9 n\u0103ng gi\u00e1 tr\u1ecb \u0111\u00e3 \u0111\u1ea7y, kh\u00f4ng c\u1ea7n s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                        return;
                    }
                    case 4: {
                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                        this.a("Tr\u00ean ng\u01b0\u1eddi \u0111\u1ec1u b\u1ecb l\u1ee3i hi\u1ec7u qu\u1ea3", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                        return;
                    }
                    case 5: {
                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                        this.a("Trong h\u01b0ng ph\u1ea5n, kh\u00f4ng th\u1ec3 d\u00f9ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                        return;
                    }
                    case 7: {
                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                        this.a("M\u00e1u v\u00e0 tinh kh\u00ed \u0111\u1ec1u \u0111\u00e3 \u0111\u1ea7y, kh\u00f4ng c\u1ea7n s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                        return;
                    }
                    case 8: {
                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                        this.a("S\u1ee7ng v\u1eadt \u0111\u00e3 ch\u1ebft, kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                        return;
                    }
                }
                this.q.A[this.b].w(nArray[0]);
                this.f(this.b);
                this.f = 4;
                this.p.a("/data/ui/msgwarm.ui", 257, this);
                this.a("Th\u00e0nh c\u00f4ng s\u1eed d\u1ee5ng \u0111\u1ea1o c\u1ee5", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                this.p.a("/data/ui/choice.ui");
                return;
            }
            if (this.f == 3) {
                this.f = 2;
                this.p.a("/data/ui/msgwarm.ui");
                return;
            }
            if (this.f == 4) {
                this.f = 0;
                this.p.a("/data/ui/msgwarm.ui");
                return;
            }
        } else if (this.f == 2 && this.o.g(262144)) {
            this.f(this.b);
            this.p.a("/data/ui/choice.ui");
        }
    }

    private void bo() {
        if (this.o.g(4100)) {
            this.p.a.b(0);
            this.bk();
            return;
        }
        if (this.o.g(8448)) {
            this.p.a.b(1);
            this.bk();
            return;
        }
        if (this.o.g(16400)) {
            this.p.a.b(2);
            this.bk();
            return;
        }
        if (this.o.g(32832)) {
            this.p.a.b(3);
            this.bk();
            return;
        }
        if (this.o.g(262144)) {
            this.f(this.b);
            this.p.a("/data/ui/skill.ui");
        }
    }

    private void bp() {
        if (game.l.x != null) {
            if (!game.l.x.j()) {
                short s = a.b.c.a((byte)0, (byte)this.q.A[this.b].r(), (byte)19);
                String string = a.a.c(a.b.c.a((byte)0, s, (byte)0));
                k k2 = this;
                short s2 = a.b.c.a((byte)0, (byte)k2.q.A[k2.b].r(), (byte)19);
                short s3 = a.b.c.a((byte)0, s2, (byte)17);
                a.b.c.a((byte)0, (byte)k2.q.A[k2.b].r(), (byte)21);
                k2.p.a.a(10).a(true);
                k2.p.a.a((int)10).i().m.a(s3, false, (byte)-1);
                k2.p.a.a((int)38).i().a = a.a.c(a.b.c.a((byte)0, s2, (byte)0));
                i i2 = new i();
                short s4 = a.b.c.a((byte)0, s2, (byte)3);
                int n2 = -1;
                int n3 = 0;
                i i3 = k2.q.A[k2.b];
                if (i3.d[n3] >= s4) {
                    n3 = 0;
                    i3 = k2.q.A[k2.b];
                    n2 = (byte)i3.d[n3];
                }
                n3 = 5;
                i3 = k2.q.A[k2.b];
                short s5 = i3.d[n3];
                n3 = 6;
                i3 = k2.q.A[k2.b];
                i2.a(s2, k2.q.A[k2.b].t(), s5, (byte)i3.e[n3], (short)n2, (byte)-1);
                n3 = 1;
                i3 = i2;
                i2.a(i3.d[n3], k2.q.A[k2.b].A(), (int)k2.q.A[k2.b].F);
                i2.b(k2.q.A[k2.b].R());
                k2.q.a((byte)k2.q.A[k2.b].j((byte)1), (int)s2, (byte)2);
                k2.q.A[k2.b].a(i2.Q());
                s4 = (short)(a.b.c.a((byte)0, (byte)k2.q.A[k2.b].r(), (byte)20) + 12);
                short s6 = a.b.c.a((byte)0, (byte)k2.q.A[k2.b].r(), (byte)21);
                s2 = a.b.c.a((byte)0, (byte)k2.q.A[k2.b].r(), (byte)19);
                n2 = k2.q.a((int)s4, (byte)2);
                if (s2 == -1) {
                    k2.p.a.a((int)42).i().a = "";
                    k2.p.a.a((int)45).i().a = "";
                    k2.p.a.a((int)46).i().a = "";
                } else {
                    k2.p.a.a((int)45).i().a = a.a.c(a.b.c.a((byte)3, s4, (byte)0));
                    k2.p.a.a((int)46).i().a = n2 + "/" + s6;
                }
                if (this.q.A[this.b].S() == 2) {
                    this.f = 3;
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("D\u1ecb ho\u00e1 th\u00e0nh #2" + string, "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                } else {
                    this.f = 3;
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("Ti\u1ebfn h\u00f3a th\u00e0nh #2" + string, "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                }
                game.l.x = null;
            }
            return;
        }
        if (a.a.s() && !this.j() && this.o.g(196640)) {
            if (this.f == 2) {
                short s = (short)(a.b.c.a((byte)0, (byte)this.q.A[this.b].r(), (byte)20) + 12);
                short s7 = a.b.c.a((byte)0, (byte)this.q.A[this.b].r(), (byte)21);
                short s8 = a.b.c.a((byte)0, (byte)this.q.A[this.b].r(), (byte)19);
                if (s8 == -1) {
                    this.f = 3;
                    this.H();
                    this.a("Kh\u00f4ng th\u1ec3 l\u1ea1i ti\u1ebfn h\u00f3a ho\u1eb7c d\u1ecb ho\u00e1", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                    return;
                }
                short s9 = a.b.c.a((byte)0, s8, (byte)17);
                if (this.q.A[this.b].t() >= game.i.u[a.b.c.a((byte)0, s8, (byte)2) - 1]) {
                    if (this.q.a((int)s, (byte)2) >= s7) {
                        this.p.a.a(10).a(false);
                        game.l.x = new a.a.b();
                        short[] sArray = new short[]{0, 0, 10, 116, 164, this.q.A[this.b].D, 0, 0, s9, 0, 0};
                        game.l.x.a(sArray);
                        game.l.x.d(true);
                        game.l.x.a();
                        this.q.d(s, s7, (byte)2);
                        return;
                    }
                    this.f = 3;
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    if (this.q.A[this.b].S() == 2) {
                        this.a("T\u00e0i li\u1ec7u ch\u01b0a \u0111\u1ee7, kh\u00f4ng th\u1ec3 d\u1ecb ho\u00e1", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                        return;
                    }
                    this.a("T\u00e0i li\u1ec7u ch\u01b0a \u0111\u1ee7, kh\u00f4ng th\u1ec3 ti\u1ebfn h\u00f3a", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                    return;
                }
                this.f = 3;
                this.p.a("/data/ui/msgwarm.ui", 257, this);
                this.a("C\u00f2n ch\u01b0a t\u1edbi" + game.i.u[a.b.c.a((byte)0, s8, (byte)2) - 1] + " c\u1ea5p, kh\u00f4ng th\u1ec3 v\u00e0o h\u00f3a", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                return;
            }
            if (this.f == 3) {
                if (this.o.b == 6 || this.o.b == 0) {
                    this.f = 2;
                    this.p.a("/data/ui/msgwarm.ui");
                    this.o.r();
                    return;
                }
                if (this.o.b == 27) {
                    this.f(this.b);
                    this.f = 0;
                    this.c = 0;
                    this.p.a("/data/ui/msgwarm.ui");
                    this.p.a("/data/ui/evolve.ui");
                    return;
                }
            }
        } else if (this.f < 3 && this.o.g(262144) && !this.j() && a.a.t()) {
            this.f = 0;
            this.f(this.b);
            this.p.a("/data/ui/evolve.ui");
        }
    }

    public final void ab() {
        this.p.a("/data/ui/bag.ui", 257, this);
        this.b = 0;
        this.bq();
        this.p.a.b(5);
        this.p.a.a((int)14).i().a = "V\u1eadt ph\u1ea9m";
        this.b = 0;
    }

    private void bq() {
        ((c.b)this.p.a.a((int)(8 + this.b * 39))).a.e = 0;
        ((c.b)this.p.a.a((int)(8 + this.b * 39))).a.f = 0;
        this.br();
    }

    private void br() {
        block0 : switch (this.b) {
            case 0: {
                this.bs();
                break;
            }
            case 1: {
                k k2 = this;
                if (k2.q.M.size() > 5) {
                    ((c.b)k2.p.a.a((int)47)).a.a(1);
                } else {
                    ((c.b)k2.p.a.a((int)47)).a.a(0);
                }
                ((c.b)k2.p.a.a((int)47)).a.a = k2.q.M.size();
                k2.v = ((c.b)k2.p.a.a((int)47)).a.e;
                k2.h = ((c.b)k2.p.a.a((int)47)).a.f;
                k2.p.a.a(7).a(false);
                for (int i2 = 0; i2 < 5; ++i2) {
                    if (k2.v + i2 < k2.q.M.size()) {
                        int[] nArray = (int[])k2.q.M.elementAt(k2.v + i2);
                        if (k2.p.a.a((int)(59 + i2 * 5)).i().m == null) {
                            k2.p.a.a((int)(59 + i2 * 5)).i().m = new c.g();
                            k2.p.a.a((int)(59 + i2 * 5)).i().m.a(0);
                            k2.p.a.a((int)(59 + i2 * 5)).i().m.a = (byte)2;
                            k2.p.a.a((int)(59 + i2 * 5)).i().m.a(258, false, (byte)-1);
                        }
                        if (k2.p.a.a((int)(59 + i2 * 5)).i().i == null) {
                            k2.p.a.a((int)(59 + i2 * 5)).i().i = new c.g();
                            k2.p.a.a((int)(59 + i2 * 5)).i().i.a(0);
                            k2.p.a.a((int)(59 + i2 * 5)).i().i.a = (byte)2;
                            k2.p.a.a((int)(59 + i2 * 5)).i().i.a(258, false, (byte)-1);
                        }
                        k2.p.a.a((int)(59 + i2 * 5)).i().m.a(a.b.c.c[3][nArray[0]][1]);
                        k2.p.a.a((int)(59 + i2 * 5)).i().i.a(a.b.c.c[3][nArray[0]][1]);
                        k2.p.a.a((int)(60 + i2 * 5)).i().a = a.a.c(a.b.c.c[3][nArray[0]][0]);
                        if (nArray[1] == 1) {
                            k2.p.a.a((int)(61 + i2 * 5)).i().a = "\u0110\u00e3 mang theo";
                            continue;
                        }
                        k2.p.a.a((int)(61 + i2 * 5)).i().a = "";
                        continue;
                    }
                    if (k2.p.a.a((int)(59 + i2 * 5)).i().m != null) {
                        k2.p.a.a((int)(59 + i2 * 5)).i().m.c();
                    }
                    k2.p.a.a((int)(60 + i2 * 5)).i().a = "";
                    k2.p.a.a((int)(61 + i2 * 5)).i().a = "";
                }
                k2.p.a.a((int)85).i().a = k2.q.M.size() > 0 ? a.a.c(a.b.c.c[3][((int[])k2.q.M.elementAt(k2.h))[0]][2]) : "";
                if (k2.q.M.size() > 0) {
                    k2.p.a.a(84).b(127 + k2.h * 72 / k2.q.M.size(), k2.p.a.a());
                    break;
                }
                k2.p.a.a(84).b(127, k2.p.a.a());
                break;
            }
            case 2: {
                this.bt();
                break;
            }
            case 3: {
                this.bu();
                if (this.h < 0 || this.q.O.size() <= 0) {
                    return;
                }
                int[] nArray = (int[])this.q.O.elementAt(this.h);
                this.p.a.a(164).a(false);
                this.p.a.a(165).a(false);
                switch (nArray[0]) {
                    case 0: {
                        if (this.q.l(nArray[0])) {
                            this.p.a.a(7).a(true);
                            this.p.a.a((int)7).i().a = "\u1ea4p tr\u1ee9ng";
                            this.p.a.a(164).a(true);
                            this.p.a.a(165).a(true);
                            if (this.q.J == 0) {
                                this.p.a.a((int)164).i().a = "#P" + game.l.A * 100 / 10;
                                this.p.a.a((int)165).i().a = game.l.A + "/10";
                                break block0;
                            }
                            this.p.a.a((int)164).i().a = "#P" + game.l.A * 100 / 30;
                            this.p.a.a((int)165).i().a = game.l.A + "/30";
                            break block0;
                        }
                        this.p.a.a(7).a(false);
                        break block0;
                    }
                    case 1: 
                    case 2: 
                    case 3: 
                    case 4: {
                        this.p.a.a(7).a(false);
                        break block0;
                    }
                    case 5: 
                    case 6: 
                    case 10: {
                        this.p.a.a((int)7).i().a = "M\u1edf ra";
                        break block0;
                    }
                    case 7: 
                    case 8: 
                    case 9: {
                        this.p.a.a((int)7).i().a = "S\u1eed d\u1ee5ng";
                    }
                }
            }
        }
        this.g = true;
    }

    private void bs() {
        int n2 = this.q.L.size() + this.q.K.size();
        if (n2 > 5) {
            ((c.b)this.p.a.a((int)8)).a.a(1);
        } else {
            ((c.b)this.p.a.a((int)8)).a.a(0);
        }
        ((c.b)this.p.a.a((int)8)).a.a = n2;
        this.v = ((c.b)this.p.a.a((int)8)).a.e;
        this.h = ((c.b)this.p.a.a((int)8)).a.f;
        this.p.a.a(7).a(true);
        this.p.a.a((int)7).i().a = "S\u1eed d\u1ee5ng";
        for (int i2 = 0; i2 < 5; ++i2) {
            if (this.v + i2 < n2) {
                int[] nArray = this.v + i2 < this.q.L.size() ? (int[])this.q.L.elementAt(this.v + i2) : (int[])this.q.K.elementAt(this.v + i2 - this.q.L.size());
                if (this.p.a.a((int)(18 + i2 * 5)).i().m == null) {
                    this.p.a.a((int)(18 + i2 * 5)).i().m = new c.g();
                    this.p.a.a((int)(18 + i2 * 5)).i().m.a(0);
                    this.p.a.a((int)(18 + i2 * 5)).i().m.a = (byte)2;
                    this.p.a.a((int)(18 + i2 * 5)).i().m.a(258, false, (byte)-1);
                }
                if (this.p.a.a((int)(18 + i2 * 5)).i().i == null) {
                    this.p.a.a((int)(18 + i2 * 5)).i().i = new c.g();
                    this.p.a.a((int)(18 + i2 * 5)).i().i.a(0);
                    this.p.a.a((int)(18 + i2 * 5)).i().i.a = (byte)2;
                    this.p.a.a((int)(18 + i2 * 5)).i().i.a(258, false, (byte)-1);
                }
                this.p.a.a((int)(18 + i2 * 5)).i().m.a(a.b.c.c[4][nArray[0]][1]);
                this.p.a.a((int)(18 + i2 * 5)).i().i.a(a.b.c.c[4][nArray[0]][1]);
                this.p.a.a((int)(19 + i2 * 5)).i().a = a.a.c(a.b.c.c[4][nArray[0]][0]);
                this.p.a.a((int)(20 + i2 * 5)).i().a = "" + nArray[1];
                continue;
            }
            if (this.p.a.a((int)(18 + i2 * 5)).i().m != null) {
                this.p.a.a((int)(18 + i2 * 5)).i().m.c();
            }
            this.p.a.a((int)(19 + i2 * 5)).i().a = "";
            this.p.a.a((int)(20 + i2 * 5)).i().a = "";
        }
        this.p.a.a((int)46).i().a = n2 > 0 ? (this.h < this.q.L.size() ? a.a.c(a.b.c.c[4][((int[])this.q.L.elementAt(this.h))[0]][2]) : a.a.c(a.b.c.c[4][((int[])this.q.K.elementAt(this.h - this.q.L.size()))[0]][2])) : "";
        if (n2 > 0) {
            this.p.a.a(43).b(127 + this.h * 72 / n2, this.p.a.a());
            return;
        }
        this.p.a.a(43).b(127, this.p.a.a());
    }

    private void bt() {
        if (this.q.N.size() > 5) {
            ((c.b)this.p.a.a((int)86)).a.a(1);
        } else {
            ((c.b)this.p.a.a((int)86)).a.a(0);
        }
        ((c.b)this.p.a.a((int)86)).a.a = this.q.N.size();
        this.v = ((c.b)this.p.a.a((int)86)).a.e;
        this.h = ((c.b)this.p.a.a((int)86)).a.f;
        this.p.a.a(7).a(false);
        for (int i2 = 0; i2 < 5; ++i2) {
            if (this.v + i2 < this.q.N.size()) {
                int[] nArray = (int[])this.q.N.elementAt(this.v + i2);
                if (this.p.a.a((int)(98 + i2 * 5)).i().m == null) {
                    this.p.a.a((int)(98 + i2 * 5)).i().m = new c.g();
                    this.p.a.a((int)(98 + i2 * 5)).i().m.a(0);
                    this.p.a.a((int)(98 + i2 * 5)).i().m.a = (byte)2;
                    this.p.a.a((int)(98 + i2 * 5)).i().m.a(258, false, (byte)-1);
                }
                if (this.p.a.a((int)(98 + i2 * 5)).i().i == null) {
                    this.p.a.a((int)(98 + i2 * 5)).i().i = new c.g();
                    this.p.a.a((int)(98 + i2 * 5)).i().i.a(0);
                    this.p.a.a((int)(98 + i2 * 5)).i().i.a = (byte)2;
                    this.p.a.a((int)(98 + i2 * 5)).i().i.a(258, false, (byte)-1);
                }
                this.p.a.a((int)(98 + i2 * 5)).i().m.a(a.b.c.c[3][nArray[0]][1]);
                this.p.a.a((int)(98 + i2 * 5)).i().i.a(a.b.c.c[3][nArray[0]][1]);
                this.p.a.a((int)(99 + i2 * 5)).i().a = nArray[0] == 17 ? "Ch\u00eca kh\u00f3a v\u00e0ng" : a.a.c(a.b.c.c[3][nArray[0]][0]);
                this.p.a.a((int)(100 + i2 * 5)).i().a = "" + nArray[1];
                continue;
            }
            if (this.p.a.a((int)(98 + i2 * 5)).i().m != null) {
                this.p.a.a((int)(98 + i2 * 5)).i().m.c();
            }
            this.p.a.a((int)(99 + i2 * 5)).i().a = "";
            this.p.a.a((int)(100 + i2 * 5)).i().a = "";
        }
        this.p.a.a((int)124).i().a = this.q.N.size() > 0 ? a.a.c(a.b.c.c[3][((int[])this.q.N.elementAt(this.h))[0]][2]) : "";
        if (this.q.N.size() > 0) {
            this.p.a.a(123).b(127 + this.h * 72 / this.q.N.size(), this.p.a.a());
            return;
        }
        this.p.a.a(123).b(127, this.p.a.a());
    }

    private void bu() {
        int n2;
        if (this.q.O.size() > 5) {
            ((c.b)this.p.a.a((int)125)).a.a(1);
        } else {
            ((c.b)this.p.a.a((int)125)).a.a(0);
        }
        ((c.b)this.p.a.a((int)125)).a.a = this.q.O.size();
        this.v = ((c.b)this.p.a.a((int)125)).a.e;
        this.h = ((c.b)this.p.a.a((int)125)).a.f;
        for (n2 = 0; n2 < 5; ++n2) {
            if (this.v + n2 < this.q.O.size()) {
                int[] nArray = (int[])this.q.O.elementAt(this.v + n2);
                if (this.p.a.a((int)(137 + n2 * 5)).i().m == null) {
                    this.p.a.a((int)(137 + n2 * 5)).i().m = new c.g();
                    this.p.a.a((int)(137 + n2 * 5)).i().m.a(0);
                    this.p.a.a((int)(137 + n2 * 5)).i().m.a = (byte)2;
                    this.p.a.a((int)(137 + n2 * 5)).i().m.a(258, false, (byte)-1);
                }
                if (this.p.a.a((int)(137 + n2 * 5)).i().i == null) {
                    this.p.a.a((int)(137 + n2 * 5)).i().i = new c.g();
                    this.p.a.a((int)(137 + n2 * 5)).i().i.a(0);
                    this.p.a.a((int)(137 + n2 * 5)).i().i.a = (byte)2;
                    this.p.a.a((int)(137 + n2 * 5)).i().i.a(258, false, (byte)-1);
                }
                this.p.a.a((int)(137 + n2 * 5)).i().m.a(a.b.c.c[5][nArray[0]][1]);
                this.p.a.a((int)(137 + n2 * 5)).i().i.a(a.b.c.c[5][nArray[0]][1]);
                this.p.a.a((int)(138 + n2 * 5)).i().a = a.a.c(a.b.c.c[5][nArray[0]][0]);
                switch (nArray[0]) {
                    case 0: {
                        if (this.q.l(nArray[0])) {
                            this.p.a.a((int)163).i().a = a.a.c(a.b.c.c[5][nArray[0]][2]);
                            if (game.l.B().O()) {
                                this.p.a.a((int)(139 + n2 * 5)).i().a = "Ho\u00e0n th\u00e0nh";
                                break;
                            }
                            this.p.a.a((int)(139 + n2 * 5)).i().a = "1 c\u00e1i";
                            break;
                        }
                        this.p.a.a((int)163).i().a = a.a.c(634);
                        this.p.a.a((int)(139 + n2 * 5)).i().a = "0 c\u00e1i";
                        break;
                    }
                    default: {
                        this.p.a.a((int)(139 + n2 * 5)).i().a = "";
                        break;
                    }
                }
                continue;
            }
            if (this.p.a.a((int)(137 + n2 * 5)).i().m != null) {
                this.p.a.a((int)(137 + n2 * 5)).i().m.c();
            }
            this.p.a.a((int)(138 + n2 * 5)).i().a = "";
            this.p.a.a((int)(139 + n2 * 5)).i().a = "";
        }
        if (this.q.O.size() > 0) {
            n2 = ((int[])this.q.O.elementAt(this.h))[0];
            if (n2 != 0) {
                this.p.a.a((int)163).i().a = a.a.c(a.b.c.c[5][n2][2]);
                this.p.a.a(7).a(true);
            }
            this.p.a.a((int)7).i().a = n2 == 0 ? (((int[])this.q.O.elementAt(this.h))[1] == 1 ? "\u0110\u00f3ng c\u1eeda" : "M\u1edf ra") : (n2 > 0 || n2 <= 4 ? (this.q.u == n2 - 1 ? "Tri\u1ec7u h\u1ed3i" : "Tri\u1ec7u ho\u00e1n") : (n2 == 10 ? "Gia t\u1ed1c" : "S\u1eed d\u1ee5ng"));
        } else {
            this.p.a.a((int)163).i().a = "";
            this.p.a.a(7).a(false);
        }
        if (this.q.O.size() > 0) {
            this.p.a.a(162).b(127 + this.h * 72 / this.q.O.size(), this.p.a.a());
            return;
        }
        this.p.a.a(162).b(127, this.p.a.a());
    }

    public final void ac() {
        if (this.f == 0 && this.o.g(4100)) {
            this.p.a.b(0);
            this.g(this.c);
            return;
        }
        if (this.f == 0 && this.o.g(8448)) {
            this.p.a.b(1);
            this.g(this.c);
            return;
        }
        if (this.o.g(196640)) {
            this.bx();
            return;
        }
        if (this.f == 0 && this.o.g(262144)) {
            this.o.a((byte)8);
            this.p.a("/data/ui/petstate.ui");
        }
    }

    public final void ad() {
        if (this.o.g(4100)) {
            this.p.a.b(0);
            this.g(this.b);
            return;
        }
        if (this.o.g(8448)) {
            this.p.a.b(1);
            this.g(this.b);
            return;
        }
        if (this.o.g(196640)) {
            this.q.f(this.s, this.b);
            this.o.a((byte)8);
            return;
        }
        if (this.o.g(262144)) {
            this.o.a((byte)8);
            this.p.a("/data/ui/petstate.ui");
        }
    }

    public final void ae() {
        if (this.o.g(4100)) {
            this.p.a.b(0);
            this.g(this.b);
            return;
        }
        if (this.o.g(8448)) {
            this.p.a.b(1);
            this.g(this.b);
            return;
        }
        if (this.o.g(196640)) {
            if (this.f == 0) {
                if (this.q.A[this.b].t() < 50) {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("Ch\u1ec9 c\u00f3 th\u1ec3 cho 50 c\u1ea5p s\u1ee7ng v\u1eadt s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                    this.f = 2;
                    return;
                }
                if (this.q.e(this.s, this.b)) {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("S\u1eed d\u1ee5ng th\u00e0nh c\u00f4ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                    this.f = 1;
                    return;
                }
            } else {
                if (this.f == 1) {
                    this.f = 0;
                    this.o.a((byte)8);
                    this.p.a("/data/ui/msgwarm.ui");
                    this.p.a("/data/ui/petstate.ui");
                    return;
                }
                if (this.f == 2) {
                    this.f = 0;
                    this.p.a("/data/ui/msgwarm.ui");
                    return;
                }
            }
        } else if (this.o.g(262144) && this.f == 0) {
            this.o.a((byte)8);
            this.p.a("/data/ui/petstate.ui");
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public final void af() {
        block46: {
            block50: {
                block54: {
                    block52: {
                        block44: {
                            int n2;
                            block53: {
                                block51: {
                                    block49: {
                                        block48: {
                                            block47: {
                                                block45: {
                                                    this.o.q();
                                                    if (this.f != 0 || !this.o.g(16400) || this.j() || a.a.a(this.b, 1)) break block45;
                                                    this.p.a.b(7);
                                                    this.p.a.b(2);
                                                    this.p.a.b(5);
                                                    this.bq();
                                                    this.o.r();
                                                    break block46;
                                                }
                                                if (this.f != 0 || !this.o.g(32832) || this.j() || a.a.a(this.b, 1)) break block47;
                                                this.p.a.b(7);
                                                this.p.a.b(3);
                                                this.p.a.b(5);
                                                this.bq();
                                                this.o.r();
                                                break block46;
                                            }
                                            if (this.f != 0 || !this.o.g(4100) || this.j() || a.a.a(this.h, 0)) break block48;
                                            this.p.a.b(0);
                                            break block46;
                                        }
                                        if (this.f != 0 || !this.o.g(8448) || this.j() || a.a.a(this.h, 0)) break block49;
                                        this.p.a.b(1);
                                        break block46;
                                    }
                                    if (!this.o.g(196640) || this.j() || !a.a.s()) break block50;
                                    if (this.f != 0) break block51;
                                    if (a.a.p() && !a.a.a(this.h, 0)) {
                                        return;
                                    }
                                    block0 : switch (this.b) {
                                        case 0: {
                                            int[] nArray;
                                            if (this.h >= this.q.L.size()) {
                                                if (this.q.K.size() <= 0) {
                                                    return;
                                                }
                                                nArray = (int[])this.q.K.elementAt(this.h - this.q.L.size());
                                            } else {
                                                nArray = (int[])this.q.L.elementAt(this.h);
                                            }
                                            switch (nArray[0]) {
                                                case 0: 
                                                case 1: 
                                                case 2: 
                                                case 3: {
                                                    if (this.f == 0) {
                                                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                                                        this.a("Kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                                        this.f = 1;
                                                        break;
                                                    }
                                                    this.p.a("/data/ui/msgwarm.ui");
                                                    this.f = 0;
                                                    break;
                                                }
                                                case 14: {
                                                    if (this.f != 0) break;
                                                    if (this.q.l(0) && (this.q.J == 0 && game.l.A < 10 || this.q.J > 0 && game.l.A < 30)) {
                                                        if (!this.q.b(nArray[0], 1, (byte)0)) break;
                                                        game.l.A = this.q.J == 0 ? 10 : 30;
                                                        this.q.d(nArray[0], 1, (byte)0);
                                                        int n3 = this.q.L.size() + this.q.K.size();
                                                        if (this.h >= n3) {
                                                            ((c.b)this.p.a.a((int)8)).a.f = this.h = n3 - 1;
                                                        }
                                                        if (this.v > 0 && this.h - this.v < 4) {
                                                            --this.v;
                                                            ((c.b)this.p.a.a((int)8)).a.e = this.v;
                                                        }
                                                        this.bs();
                                                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                                                        this.a("Th\u00e0nh c\u00f4ng s\u1eed d\u1ee5ng, tranh th\u1ee7 th\u1eddi gian \u0111i \u1ea5p tr\u1ee9ng tr\u01b0\u0301ng su\u0309ng v\u00e2\u0323t a!", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                                        this.f = 1;
                                                        break;
                                                    }
                                                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                                                    this.a("Kh\u00f4ng c\u00f3 tr\u1ee9ng c\u00f3 th\u1ec3 \u1ea5p tr\u1ee9ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                                    this.f = 1;
                                                    break;
                                                }
                                                case 13: {
                                                    if (this.f != 0) break;
                                                    if (this.q.y <= 0) {
                                                        if (game.l.B().p == 3 && game.l.B().q == 7) {
                                                            this.H();
                                                            this.a("N\u01a1i n\u00e0y kh\u00f4ng c\u00e1ch n\u00e0o s\u1eed d\u1ee5ng tr\u00e1nh qu\u00e1i ho\u00e0n", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                                            this.f = 1;
                                                            break;
                                                        }
                                                        if (!this.q.b(nArray[0], 1, (byte)0)) break;
                                                        this.q.d(nArray[0], 1, (byte)0);
                                                        this.q.y = a.b.c.c[4][nArray[0]][6];
                                                        this.q.x = 0;
                                                        int n4 = this.q.L.size() + this.q.K.size();
                                                        if (this.h >= n4) {
                                                            ((c.b)this.p.a.a((int)8)).a.f = this.h = n4 - 1;
                                                        }
                                                        if (this.v > 0 && this.h - this.v < 4) {
                                                            --this.v;
                                                            ((c.b)this.p.a.a((int)8)).a.e = this.v;
                                                        }
                                                        this.bs();
                                                        this.H();
                                                        this.q.b(1);
                                                        this.a("Th\u00e0nh c\u00f4ng s\u1eed d\u1ee5ng \u0111\u1ea1o c\u1ee5, c\u0169ng c\u00f3 th\u1eddi gian ng\u1eafn tr\u00e1nh qu\u00e1i hi\u1ec7u qu\u1ea3", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                                        this.f = 1;
                                                        break;
                                                    }
                                                    this.H();
                                                    this.a("\u0110\u00e3 c\u00f3 \u0111\u01b0\u1ee3c th\u1eddi gian ng\u1eafn tr\u00e1nh qu\u00e1i hi\u1ec7u qu\u1ea3", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                                    this.f = 1;
                                                    break;
                                                }
                                                default: {
                                                    this.s = nArray[0];
                                                    this.o.a((byte)17);
                                                    this.p.a("/data/ui/bag.ui");
                                                    break;
                                                }
                                            }
                                            break;
                                        }
                                        case 3: {
                                            int[] nArray = (int[])this.q.O.elementAt(this.h);
                                            switch (nArray[0]) {
                                                case 0: {
                                                    if (!this.q.l(nArray[0])) break block0;
                                                    if (game.l.B().O()) {
                                                        if (this.q.z() == 2) {
                                                            this.H();
                                                            this.a("Kh\u00f4ng gian kh\u00f4ng \u0111\u1ee7, h\u00e3y thanh l\u00fd l\u1ea1i kh\u00f4ng gian \u1ea5p tr\u1ee9ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                                            this.f = 1;
                                                            break block0;
                                                        }
                                                        game.l.A = 0;
                                                        if (game.l.B().Z.n[game.l.e(4, 5)] != null) {
                                                            game.l.B().Z.n[game.l.e((int)4, (int)5)][15] = 4;
                                                            if (game.l.B().p == 4 && game.l.B().q == 5) {
                                                                game.l.B().Z.l[15].a((byte)4);
                                                            }
                                                        }
                                                        this.q.k(nArray[0]);
                                                        this.bu();
                                                        this.H();
                                                        this.a("\u1ea4p tr\u1ee9ng th\u00e0nh c\u00f4ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                                        this.f = 2;
                                                        break block0;
                                                    }
                                                    this.H();
                                                    this.a("V\u1eabn ch\u01b0a th\u1ec3 \u1ea5p tr\u1ee9ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                                    this.f = 1;
                                                    break block0;
                                                }
                                                case 5: {
                                                    this.o.a((byte)11);
                                                    this.p.a("/data/ui/bag.ui");
                                                    break block0;
                                                }
                                                case 10: {
                                                    this.o.a((byte)24);
                                                    this.p.a("/data/ui/bag.ui");
                                                    break block0;
                                                }
                                                case 6: {
                                                    this.o.a((byte)12);
                                                    this.p.a("/data/ui/bag.ui");
                                                    break block0;
                                                }
                                                case 7: 
                                                case 8: 
                                                case 9: {
                                                    this.s = nArray[0];
                                                    this.o.a((byte)19);
                                                    this.p.a("/data/ui/bag.ui");
                                                    break block0;
                                                }
                                            }
                                        }
                                    }
                                    break block46;
                                }
                                if (this.f != 1 && this.f != 2) break block46;
                                if (this.f != 2) break block52;
                                if (this.q.J != 0) break block53;
                                byte by = this.h(58);
                                this.q.a((short)58);
                                if (by == 0) {
                                    this.c("\u1ea4p tr\u1ee9ng t\u00ecm \u0111\u01b0\u1ee3c #2" + a.a.c(a.b.c.c[0][58][0]) + "#0 \u0111\u1ec3 v\u00e0o ba l\u00f4");
                                    break block44;
                                } else if (by == 1) {
                                    this.c("\u1ea4p tr\u1ee9ng t\u00ecm \u0111\u01b0\u1ee3c #2" + a.a.c(a.b.c.c[0][58][0]) + "#0 \u0111\u1ec3 v\u00e0o ng\u00e2n h\u00e0ng");
                                    break block44;
                                } else {
                                    this.c("Kh\u00f4ng c\u00f3 kh\u00f4ng gian, \u0111\u00e3 ph\u00f3ng sinh");
                                }
                                break block44;
                            }
                            int n5 = a.e.a(new int[]{76, 52, 28, 4, 0}, a.e.a(100));
                            short[] sArray = new short[]{0, 56, 58, 95, 72};
                            byte by = this.h(sArray[n5]);
                            for (n2 = 0; n2 < this.q.J && this.q.S[n2] != sArray[n5]; ++n2) {
                            }
                            if (n2 >= this.q.J) {
                                this.q.a(sArray[n5]);
                            }
                            if (by == 0) {
                                this.c("\u1ea4p tr\u1ee9ng t\u00ecm \u0111\u01b0\u1ee3c #2" + a.a.c(a.b.c.c[0][sArray[n5]][0]) + "#0 \u0111\u1ec3 v\u00e0o ba l\u00f4");
                            } else if (by == 1) {
                                this.c("\u1ea4p tr\u1ee9ng t\u00ecm \u0111\u01b0\u1ee3c #2" + a.a.c(a.b.c.c[0][sArray[n5]][0]) + "#0 \u0111\u1ec3 v\u00e0o ng\u00e2n h\u00e0ng");
                            } else {
                                this.c("Kh\u00f4ng c\u00f3 kh\u00f4ng gian, \u0111\u00e3 ph\u00f3ng sinh");
                            }
                        }
                        this.f = 3;
                        break block54;
                    }
                    this.o.r();
                    this.f = 0;
                }
                this.I();
                break block46;
            }
            if (this.f == 0 && this.o.g(262144) && !this.j() && a.a.t()) {
                this.b = a.a.i ? 2 : 1;
                this.o.a((byte)6);
                this.p.a("/data/ui/bag.ui");
            }
        }
        if (this.f == 3 && !this.j()) {
            this.o.r();
            this.br();
            this.f = 0;
        }
        this.f();
        this.g = true;
    }

    private byte h(int n2) {
        int[][] nArrayArray = new int[][]{{60, 20, 0}, {75, 50, 20, 0}};
        int n3 = -1;
        int n4 = 0;
        if (a.b.c.c[0][n2][4] == 5) {
            if (a.b.c.c[0][n2][3] == 2) {
                n3 = 1;
                n4 = 2;
            } else if (a.b.c.c[0][n2][3] == 3) {
                n3 = 0;
                n4 = 3;
            }
        }
        int n5 = a.b.c.c[0][n2][1] * 10;
        int n6 = a.b.c.c[1][n5][5];
        byte by = this.q.z();
        if (n3 == -1) {
            if (by == 0) {
                this.q.a(n2, 5, (short)-1, (byte)2, (short)-1, (byte)-1, new int[]{1, n5, n6});
            } else if (by == 1) {
                int n7 = a.e.b(a.b.c.c[0][n2][3], a.b.c.c[0][n2][3]);
                this.q.a(n2, 5, (short)-1, (byte)2, (byte)n7, (byte)-1, game.i.b(n2, 5, n7), 0, -1, new int[]{1, n5, n6});
            }
        } else {
            n4 = (byte)(n4 + (byte)a.e.a(nArrayArray[n3], a.e.a(100)));
            if (by == 0) {
                this.q.a(n2, 5, (short)-1, (byte)2, (short)n4, (byte)-1, new int[]{1, n5, n6});
            } else if (by == 1) {
                this.q.a(n2, 5, (short)-1, (byte)2, (short)n4, (byte)-1, game.i.b(n2, 5, n4), 0, -1, new int[]{1, n5, n6});
            }
        }
        return by;
    }

    public final void ag() {
        this.aU();
        this.p.a("/data/ui/ride.ui", 257, this);
        this.b = 0;
        this.bv();
    }

    private void bv() {
        for (int i2 = 0; i2 < 4; ++i2) {
            if (this.p.a.a((int)(i2 + 4)).i().m == null) {
                this.p.a.a((int)(i2 + 4)).i().m = new c.g();
                this.p.a.a((int)(i2 + 4)).i().m.a(0);
                this.p.a.a((int)(i2 + 4)).i().m.a = (byte)3;
                this.p.a.a((int)(i2 + 4)).i().m.a(260, false, (byte)-1);
            }
            if (this.p.a.a((int)(i2 + 16)).i().m == null) {
                this.p.a.a((int)(i2 + 16)).i().m = new c.g();
                this.p.a.a((int)(i2 + 16)).i().m.a(131);
                this.p.a.a((int)(i2 + 16)).i().m.a = (byte)2;
                this.p.a.a((int)(i2 + 16)).i().m.a(257, false, (byte)0);
            }
            if (this.q.f(i2)) {
                if (this.b == i2) {
                    this.p.a.a((int)(i2 + 4)).i().m.a((byte)i2, (byte)-1);
                    if (this.b == 0) {
                        this.p.a.a((int)(i2 + 8)).i().a = "L\u1ee5c \u0111i \u0111i\u1ec3u";
                    } else if (this.b == 1) {
                        this.p.a.a((int)(i2 + 8)).i().a = "H\u01b0 kh\u00f4ng h\u00e0nh gi\u1ea3";
                    } else if (this.b == 2) {
                        this.p.a.a((int)(i2 + 8)).i().a = "H\u1ea3i \u00e2u";
                    } else if (this.b == 3) {
                        this.p.a.a((int)(i2 + 8)).i().a = "Nham s\u01a1n long";
                    }
                } else {
                    this.p.a.a((int)(i2 + 4)).i().m.a((byte)(i2 + 8), (byte)-1);
                    this.p.a.a((int)(i2 + 8)).i().a = "";
                }
                if (!this.q.g(i2)) {
                    this.p.a.a(i2 + 16).a(true);
                    continue;
                }
                this.p.a.a(i2 + 16).a(false);
                continue;
            }
            this.p.a.a(i2 + 16).a(false);
            this.p.a.a((int)(i2 + 4)).i().m.a((byte)(i2 + 4), (byte)-1);
            this.p.a.a((int)(i2 + 8)).i().a = "";
        }
    }

    public final void ah() {
        if (!this.j() && this.o.g(16400)) {
            this.p.a.b(2);
        } else if (!this.j() && this.o.g(32832)) {
            this.p.a.b(3);
        } else if (!this.j() && this.o.g(512)) {
            this.p.a("/data/ui/ride.ui");
            this.o.a((byte)0);
        } else if (!this.j() && this.o.g(196640)) {
            if (this.q.f(this.b)) {
                if (this.q.g(this.b)) {
                    this.q.h(this.b);
                    this.p.a("/data/ui/ride.ui");
                    this.o.a((byte)0);
                } else {
                    this.b("N\u01a1i n\u00e0y kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng s\u1ee7ng v\u1eadt c\u01b0\u1ee1i");
                }
            } else {
                this.b("Ch\u01b0a c\u00f3 s\u1ee7ng v\u1eadt c\u01b0\u1ee1i n\u00e0y");
            }
        } else if (!this.j() && this.o.g(262144)) {
            this.p.a("/data/ui/ride.ui");
            this.o.a((byte)0);
        }
        this.f();
        this.g = true;
    }

    public final void a(i object, i i2) {
        this.p.a("/data/ui/battle.ui", 257, this);
        this.a = 0;
        this.e = 0;
        this.a((i)object, false);
        this.b(i2, false);
        object = this;
        this.p.a.a((int)59).i().a = "100%";
        ((k)object).p.a.a((int)58).i().a = "100%";
        ((a)((k)object).o).D();
        this.p.a("/data/ui/world.ui");
    }

    public final void b(i i2, i i3) {
        if (i2.a(i3) == 0) {
            if (i2.s() == 0) {
                this.p.a.a((int)59).i().a = "300%";
                this.p.a.a((int)58).i().a = "60%";
                return;
            }
            this.p.a.a((int)59).i().a = "60%";
            this.p.a.a((int)58).i().a = "300%";
            return;
        }
        if (i2.a(i3) == 1) {
            if (i2.s() == 0) {
                this.p.a.a((int)59).i().a = "60%";
                this.p.a.a((int)58).i().a = "300%";
                return;
            }
            this.p.a.a((int)59).i().a = "300%";
            this.p.a.a((int)58).i().a = "60%";
            return;
        }
        this.p.a.a((int)59).i().a = "100%";
        this.p.a.a((int)58).i().a = "100%";
    }

    public final void a(i i2, i i3, i i4, int n2, int n3) {
        if (i2.a(i3) == 0) {
            if (i4.s() == 0) {
                if ((n2 *= 200 / n3) == n3 && n2 != 200) {
                    n2 = 200;
                }
                this.p.a.a((int)59).i().a = n2 + 100 + "%";
                return;
            }
            if (i4.s() == 1) {
                if ((n2 *= 40 / n3) == n3 && n2 != 40) {
                    n2 = 40;
                }
                this.p.a.a((int)58).i().a = 100 - n2 + "%";
                return;
            }
        } else if (i2.a(i3) == 1) {
            if (i4.s() == 0) {
                if ((n2 *= 40 / n3) == n3 && n2 != 40) {
                    n2 = 40;
                }
                this.p.a.a((int)59).i().a = 100 - n2 + "%";
                return;
            }
            if (i4.s() == 1) {
                if ((n2 *= 200 / n3) == n3 && n2 != 200) {
                    n2 = 200;
                }
                this.p.a.a((int)58).i().a = n2 + 100 + "%";
                return;
            }
        } else {
            this.p.a.a((int)59).i().a = "100%";
            this.p.a.a((int)58).i().a = "100%";
        }
    }

    public final void a(i i2, i i3, int n2, int n3) {
        this.D = 0;
        this.C = 0;
        if (i2.a(i3) == 0) {
            this.C += n2 * (200 / n3);
            if (this.C == n3 && this.C != 200) {
                this.C = 200;
            }
            this.p.a.a((int)59).i().a = 100 + this.C + "%";
            this.D += n2 * (40 / n3);
            if (this.D == n3 && this.D != 40) {
                this.D = 40;
            }
            this.p.a.a((int)58).i().a = 100 - this.D + "%";
            return;
        }
        if (i2.a(i3) == 1) {
            this.C += n2 * (40 / n3);
            if (this.C == n3 && this.C != 40) {
                this.C = 40;
            }
            this.p.a.a((int)59).i().a = 100 - this.C + "%";
            this.D += n2 * (200 / n3);
            if (this.D == n3 && this.D != 200) {
                this.D = 200;
            }
            this.p.a.a((int)58).i().a = 100 + this.D + "%";
            return;
        }
        this.p.a.a((int)59).i().a = "100%";
        this.p.a.a((int)58).i().a = "100%";
    }

    public final boolean a(i i2, boolean bl) {
        i i3;
        int n2;
        int n3 = 0;
        if (this.E == 0) {
            n2 = 1;
            i3 = i2;
            n3 = Math.abs(i2.O() - i3.e[n2]) / 11;
            if (n3 <= 1) {
                n3 = 1;
            }
        }
        int n4 = i2.O();
        n2 = 1;
        i3 = i2;
        int n5 = i3.e[n2];
        if (n4 != n5) {
            ++this.F;
            if (this.F < 4) {
                if (bl) {
                    this.p.a.a((int)55).i().a = "#P" + i2.M();
                    this.p.a.a((int)11).i().a = "#P" + i2.N();
                } else {
                    this.p.a.a((int)55).i().a = "#P" + i2.N();
                    this.p.a.a((int)11).i().a = "#P" + i2.M();
                }
                return false;
            }
        }
        this.E += n3;
        if (bl) {
            if ((n4 += this.E) >= n5) {
                n4 = n5;
            }
            i2.u(n4);
            this.p.a.a((int)41).i().a = "#P" + i2.M();
            this.p.a.a((int)11).i().a = "#P" + i2.N();
            this.p.a.a((int)55).i().a = "#P" + i2.N();
        } else {
            if ((n4 -= this.E) <= n5) {
                n4 = n5;
            }
            i2.u(n4);
            this.p.a.a((int)41).i().a = "#P" + i2.N();
            this.p.a.a((int)55).i().a = "#P" + i2.M();
            this.p.a.a((int)11).i().a = "#P" + i2.M();
        }
        n2 = 1;
        i3 = i2;
        this.p.a.a((int)38).i().a = i2.O() + "/" + i3.d[n2];
        this.p.a.a((int)9).i().a = "#P" + i2.P();
        this.p.a.a((int)40).i().a = i2.A() + "/" + i2.v();
        this.p.a.a((int)12).i().a = a.a.c(i2.j((byte)0));
        this.p.a.a((int)13).i().a = "lv" + i2.t();
        this.p.a.a((int)17).i().m.a(94 + i2.j((byte)1));
        if (n4 == n5) {
            this.E = 0;
            this.F = 0;
            this.k = 0;
            return true;
        }
        return false;
    }

    public final void a(i i2) {
        int n2;
        for (n2 = 0; n2 < 6; ++n2) {
            if (this.p.a.a((int)(n2 + 26)).i().m == null) {
                this.p.a.a((int)(n2 + 26)).i().m = new c.g();
                this.p.a.a((int)(n2 + 26)).i().m.a = (byte)2;
                this.p.a.a((int)(n2 + 26)).i().m.a(0);
                this.p.a.a((int)(n2 + 26)).i().m.a(325, false, (byte)0);
            }
            if (this.p.a.a((int)(n2 + 43)).i().m == null) {
                this.p.a.a((int)(n2 + 43)).i().m = new c.g();
                this.p.a.a((int)(n2 + 43)).i().m.a = (byte)2;
                this.p.a.a((int)(n2 + 43)).i().m.a(145);
                this.p.a.a((int)(n2 + 43)).i().m.a(257, false, (byte)0);
            }
            this.p.a.a((int)(n2 + 43)).i().m.a(145);
            this.p.a.a((int)(n2 + 26)).i().m.a(0);
        }
        for (n2 = 0; n2 < 3; ++n2) {
            if (i2.y[0][n2] != -1 && i2.w[i2.y[0][n2]][0] > 0) {
                this.p.a.a((int)(43 + this.k)).i().m.a(134 + i2.w[i2.y[0][n2]][0]);
                this.p.a.a((int)(26 + this.k)).i().m.a(i2.y[0][n2] + 12);
                ++this.k;
            }
            if (i2.y[1][n2] == -1 || i2.x[i2.y[1][n2]][0] <= 0) continue;
            this.p.a.a((int)(43 + this.k)).i().m.a(134 + i2.x[i2.y[1][n2]][0]);
            this.p.a.a((int)(26 + this.k)).i().m.a(i2.y[1][n2] + 1);
            ++this.k;
        }
    }

    private void g(i i2) {
        this.p.a.a((int)11).i().a = "#P" + i2.M();
        int n2 = 1;
        i i3 = i2;
        this.p.a.a((int)38).i().a = i2.O() + "/" + i3.d[n2];
        this.p.a.a((int)16).i().a = "lv" + i2.t();
    }

    public final boolean b(i i2, boolean bl) {
        i i3;
        int n2;
        int n3 = 0;
        if (this.E == 0) {
            n2 = 1;
            i3 = i2;
            n3 = Math.abs(i2.O() - i3.e[n2]) / 11;
            if (n3 <= 1) {
                n3 = 1;
            }
        }
        int n4 = i2.O();
        n2 = 1;
        i3 = i2;
        int n5 = i3.e[n2];
        if (n4 != n5) {
            ++this.F;
            if (this.F < 4) {
                if (bl) {
                    this.p.a.a((int)56).i().a = "#P" + i2.M();
                    this.p.a.a((int)14).i().a = "#P" + i2.N();
                } else {
                    this.p.a.a((int)56).i().a = "#P" + i2.N();
                    this.p.a.a((int)14).i().a = "#P" + i2.M();
                }
                return false;
            }
        }
        this.E += n3;
        if (bl) {
            if ((n4 += this.E) >= n5) {
                n4 = n5;
            }
            i2.u(n4);
            this.p.a.a((int)42).i().a = "#P" + i2.M();
            this.p.a.a((int)14).i().a = "#P" + i2.N();
            this.p.a.a((int)56).i().a = "#P" + i2.N();
        } else {
            if ((n4 -= this.E) <= n5) {
                n4 = n5;
            }
            i2.u(n4);
            this.p.a.a((int)42).i().a = "#P" + i2.N();
            this.p.a.a((int)14).i().a = "#P" + i2.M();
            this.p.a.a((int)56).i().a = "#P" + i2.M();
        }
        n2 = 1;
        i3 = i2;
        this.p.a.a((int)39).i().a = i2.O() + "/" + i3.d[n2];
        if (this.q.a((byte)i2.j((byte)1), i2.r()) == 2) {
            this.p.a.a((int)19).i().m.a(101);
        } else {
            this.p.a.a((int)19).i().m.a(102);
        }
        this.p.a.a((int)15).i().a = a.a.c(i2.j((byte)0));
        this.p.a.a((int)16).i().a = "lv" + i2.t();
        this.p.a.a((int)18).i().m.a(94 + i2.j((byte)1));
        if (n4 == n5) {
            this.E = 0;
            this.F = 0;
            this.k = 0;
            return true;
        }
        return false;
    }

    public final void b(i i2) {
        int n2;
        for (n2 = 0; n2 < 6; ++n2) {
            if (this.p.a.a((int)(n2 + 32)).i().m == null) {
                this.p.a.a((int)(n2 + 32)).i().m = new c.g();
                this.p.a.a((int)(n2 + 32)).i().m.a = (byte)2;
                this.p.a.a((int)(n2 + 32)).i().m.a(0);
                this.p.a.a((int)(n2 + 32)).i().m.a(325, false, (byte)0);
            }
            if (this.p.a.a((int)(n2 + 49)).i().m == null) {
                this.p.a.a((int)(n2 + 49)).i().m = new c.g();
                this.p.a.a((int)(n2 + 49)).i().m.a = (byte)2;
                this.p.a.a((int)(n2 + 49)).i().m.a(145);
                this.p.a.a((int)(n2 + 49)).i().m.a(257, false, (byte)0);
            }
            this.p.a.a((int)(n2 + 49)).i().m.a(145);
            this.p.a.a((int)(n2 + 32)).i().m.a(0);
        }
        for (n2 = 0; n2 < 3; ++n2) {
            if (i2.y[0][n2] != -1 && i2.w[i2.y[0][n2]][0] > 0) {
                this.p.a.a((int)(49 + this.k)).i().m.a(134 + i2.w[i2.y[0][n2]][0]);
                this.p.a.a((int)(32 + this.k)).i().m.a(i2.y[0][n2] + 12);
                ++this.k;
            }
            if (i2.y[1][n2] == -1 || i2.x[i2.y[1][n2]][0] <= 0) continue;
            this.p.a.a((int)(49 + this.k)).i().m.a(134 + i2.x[i2.y[1][n2]][0]);
            this.p.a.a((int)(32 + this.k)).i().m.a(i2.y[1][n2] + 1);
            ++this.k;
        }
    }

    public final void ai() {
        this.a = 0;
        this.x = null;
        this.p.a("/data/ui/battle.ui");
    }

    public final void aj() {
        ((c.b)this.p.a.a((int)0)).b.f = this.a;
        this.p.a.a(20 + this.a).a(true);
    }

    public final void c(i i2) {
        this.f = 0;
        this.a(i2, false);
        this.aj();
    }

    public final void d(i i2) {
        ((a)this.o).q();
        if (!a.a.a(this.a, 1) && this.f == 0 && !this.j() && this.o.g(16400)) {
            this.p.a.b(2);
        } else if (!a.a.a(this.a, 1) && this.f == 0 && !this.j() && this.o.g(32832)) {
            this.p.a.b(3);
        } else if (!this.j() && this.o.g(196640)) {
            switch (this.a) {
                case 0: {
                    this.p.a.a(20 + this.a).a(false);
                    this.o.a((byte)3);
                    break;
                }
                case 2: {
                    if (this.f == 0) {
                        if (i2.p(2)) {
                            this.p.a("/data/ui/msgwarm.ui", 257, this);
                            this.a("Tr\u1ea1ng th\u00e1i b\u1ecb qu\u1ea5n, kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng \u0111\u1ea1o c\u1ee5", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                            this.f = 1;
                            break;
                        }
                        this.p.a.a(20 + this.a).a(false);
                        this.o.a((byte)4);
                        break;
                    }
                    this.p.a("/data/ui/msgwarm.ui");
                    this.f = 0;
                    break;
                }
                case 3: {
                    if (this.f == 0) {
                        if (i2.p(2)) {
                            this.p.a("/data/ui/msgwarm.ui", 257, this);
                            this.a("Tr\u1ea1ng th\u00e1i b\u1ecb qu\u1ea5n, kh\u00f4ng th\u1ec3 \u0111\u1ed5i s\u1ee7ng v\u1eadt", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                            this.f = 1;
                            break;
                        }
                        this.p.a.a(20 + this.a).a(false);
                        ((a)this.o).q = ((a)this.o).o[((a)this.o).s];
                        game.a.B().u = true;
                        this.o.a((byte)5);
                        break;
                    }
                    this.p.a("/data/ui/msgwarm.ui");
                    this.f = 0;
                    break;
                }
                case 1: {
                    if (((a)this.o).l == 2) {
                        this.b("Tr\u1eadn chi\u1ebfn n\u00e0y kh\u00f4ng cho b\u1eaft s\u1ee7ng v\u1eadt");
                        break;
                    }
                    if (this.q.z() == 2) {
                        this.b("Kh\u00f4ng gian kh\u00f4ng \u0111\u1ee7, kh\u00f4ng c\u00e1ch n\u00e0o b\u1eaft \u0111\u01b0\u1ee3c");
                        break;
                    }
                    this.b = 0;
                    this.p.a.a(20 + this.a).a(false);
                    ((a)this.o).r();
                    this.o.a((byte)21);
                    break;
                }
                case 4: {
                    this.p.a.a(20 + this.a).a(false);
                    this.o.a((byte)11);
                    break;
                }
                case 5: {
                    if (this.f == 0) {
                        if (i2.p(2)) {
                            this.p.a("/data/ui/msgwarm.ui", 257, this);
                            this.a("Tr\u1ea1ng th\u00e1i b\u1ecb qu\u1ea5n, kh\u00f4ng th\u1ec3 ch\u1ea1y tr\u1ed1n", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                            this.f = 1;
                            break;
                        }
                        if (((a)this.o).l > 0 || !game.e.v) {
                            this.p.a.a(20 + this.a).a(false);
                            this.f = 3;
                            this.b("Tr\u1eadn chi\u1ebfn n\u00e0y kh\u00f4ng th\u1ec3 tr\u1ed1n ch\u1ea1y");
                            break;
                        }
                        boolean bl = false;
                        if (((a)this.o).r.t() > ((a)this.o).n[0].t()) {
                            bl = true;
                        } else if (((a)this.o).r.t() == ((a)this.o).n[0].t()) {
                            if (a.e.a(100) <= 95) {
                                bl = true;
                            }
                        } else {
                            int n2 = ((a)this.o).n[0].t() - ((a)this.o).r.t();
                            if ((n2 = 95 - n2 * 10) <= 15) {
                                n2 = 15;
                            }
                            if (a.e.a(100) < n2) {
                                bl = true;
                            }
                        }
                        if (bl) {
                            this.p.a.a(20 + this.a).a(false);
                            game.f.B().a((byte)10);
                            break;
                        }
                        this.f = 2;
                        this.b("Ch\u1ea1y tr\u1ed1n th\u1ea5t b\u1ea1i");
                        break;
                    }
                    this.p.a("/data/ui/msgwarm.ui");
                    this.f = 0;
                }
            }
        }
        this.f();
        if (this.f >= 2 && this.aA()) {
            if (this.f == 2) {
                ((a)this.o).r.K = true;
                ((a)this.o).s = (byte)(((a)this.o).s + 1);
                this.o.a((byte)1);
            } else {
                this.p.a.a(20 + this.a).a(true);
            }
            this.f = 0;
        }
    }

    public final void e(i i2) {
        this.p.a("/data/ui/choiceskill.ui", 257, this);
        ((c.b)this.p.a.a((int)0)).a.a = i2.F();
        if (this.e >= i2.F()) {
            this.e = i2.F() - 1;
        }
        if (i2.F() > 5) {
            ((c.b)this.p.a.a((int)0)).a.a(1);
        } else {
            ((c.b)this.p.a.a((int)0)).a.a(-1);
        }
        this.p.a.a((int)5).i().a = "S\u1eed d\u1ee5ng";
        ((c.b)this.p.a.a((int)0)).a.f = this.e;
        this.h(i2);
        this.f = 0;
    }

    private void h(i i2) {
        this.v = ((c.b)this.p.a.a((int)0)).a.e;
        this.h = ((c.b)this.p.a.a((int)0)).a.f;
        int n2 = i2.F();
        for (int i3 = 0; i3 < 5; ++i3) {
            if (i3 >= n2) {
                this.p.a.a((int)(13 + i3 * 5)).i().a = "";
                this.p.a.a((int)(14 + i3 * 5)).i().a = "";
                continue;
            }
            this.p.a.a((int)(13 + i3 * 5)).i().a = a.a.c(a.b.c.c[1][i2.t(this.v + i3)][1]);
            this.p.a.a((int)(14 + i3 * 5)).i().a = i2.z[this.v + i3] + "/" + a.b.c.c[1][i2.t(this.v + i3)][5];
        }
        this.i(i2.A[this.e]);
        this.p.a.a(51).b(98 + this.h * 80 / n2, this.p.a.a());
    }

    private void i(int n2) {
        this.p.a.a((int)53).i().a = a.a.c(a.b.c.c[1][n2][2]);
    }

    public final void f(i i2) {
        if (this.aB()) {
            if (this.f == 0 && this.o.g(4100)) {
                this.p.a.b(0);
                this.h(i2);
            } else if (this.f == 0 && this.o.g(8448)) {
                this.p.a.b(1);
                this.h(i2);
            } else if (this.o.g(196640)) {
                if (this.f == 0) {
                    if (i2.s(this.e)) {
                        this.p.a("/data/ui/choiceskill.ui");
                        ((a)this.o).d(i2.A[this.e]);
                        int n2 = ((a)this.o).k;
                        ((a)this.o).getClass();
                        if (n2 == 0) {
                            ((a)this.o).G();
                        } else {
                            this.o.a((byte)6);
                        }
                    } else {
                        this.f = 1;
                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                        this.a("K\u1ef9 n\u0103ng gi\u00e1 tr\u1ecb ch\u01b0a \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                    }
                } else {
                    this.f = 0;
                    this.p.a("/data/ui/msgwarm.ui");
                    if (i2.p(2) && i2.s() == 0) {
                        boolean bl = false;
                        for (int i3 = 0; i3 < i2.z.length; ++i3) {
                            if (i2.z[i3] == 0) continue;
                            bl = true;
                        }
                        if (!bl) {
                            this.p.a("/data/ui/choiceskill.ui");
                            this.c("Kh\u00f4ng c\u00f3 k\u1ef9 n\u0103ng gi\u00e1 tr\u1ecb, kh\u00f4ng c\u00e1ch n\u00e0o chi\u1ebfn \u0111\u1ea5u");
                            ((a)this.o).F();
                        }
                    }
                }
            } else if (this.o.g(262144) && this.f == 0) {
                this.p.a("/data/ui/choiceskill.ui");
                this.o.a((byte)20);
            }
        }
        this.g();
    }

    public final void ak() {
        this.f = 0;
        this.p.a("/data/ui/choice.ui", 257, this);
        this.p.a.a((int)8).i().a = "Pokemon ball";
        this.p.a.a((int)9).i().a = "T\u1ec9 l\u1ec7 b\u1eaft";
        this.p.a.a((int)5).i().a = "S\u1eed d\u1ee5ng";
        ((c.b)this.p.a.a((int)0)).a.f = this.b;
        ((c.b)this.p.a.a((int)0)).a.a(0);
        ((c.b)this.p.a.a((int)0)).a.a = this.q.L.size();
        for (int i2 = 0; i2 < this.q.L.size(); ++i2) {
            int[] nArray = (int[])this.q.L.elementAt(i2);
            if (this.p.a.a((int)(i2 + 54)).i().m == null) {
                this.p.a.a((int)(i2 + 54)).i().m = new c.g();
                this.p.a.a((int)(i2 + 54)).i().m.a(0);
                this.p.a.a((int)(i2 + 54)).i().m.a = (byte)2;
                this.p.a.a((int)(i2 + 54)).i().m.a(258, false, (byte)-1);
            }
            this.p.a.a((int)(i2 + 54)).i().m.a(a.b.c.c[4][nArray[0]][1]);
            this.p.a.a((int)(13 + i2 * 5)).i().a = a.a.c(a.b.c.c[4][nArray[0]][0]);
            this.p.a.a((int)(14 + i2 * 5)).i().a = ((a)this.o).m(nArray[0]) + "%";
        }
        this.p.a.a(59).a(false);
        this.p.a.a(60).a(false);
        this.bw();
    }

    private void bw() {
        int[] nArray = (int[])this.q.L.elementAt(this.b);
        this.p.a.a((int)53).i().a = "S\u1ed1 l\u01b0\u1ee3ng: " + nArray[1] + " c\u00e1i ";
    }

    public final void al() {
        this.o.q();
        if (!a.a.a(this.b, 0) && this.f == 0 && this.o.g(4100) && !this.j()) {
            this.p.a.b(0);
            this.bw();
        } else if (!a.a.a(this.b, 0) && this.f == 0 && this.o.g(8448) && !this.j()) {
            this.p.a.b(1);
            this.bw();
        } else if (this.o.g(196640) && !this.j() && a.a.s()) {
            if (a.a.p() && !a.a.a(this.b, 0)) {
                return;
            }
            game.e.w = true;
            if (this.f == 0) {
                int[] nArray = (int[])this.q.L.elementAt(this.b);
                if (!this.q.b(nArray[0], 1, (byte)0)) {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("S\u1ed1 l\u01b0\u1ee3ng Pokemon ball kh\u00f4ng \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                    this.f = 1;
                } else {
                    this.f = 0;
                    game.a.v = (byte)nArray[0];
                    this.o.r();
                    this.q.d(nArray[0], 1, (byte)0);
                    this.o.a((byte)17);
                    this.p.a("/data/ui/choice.ui");
                }
            } else if (this.f == 1) {
                if (a.a.i && ((int[])this.q.L.elementAt(this.b))[0] == 0) {
                    this.p.a("/data/ui/choice.ui");
                    this.o.a((byte)101);
                }
                this.f = 0;
                this.p.a("/data/ui/msgwarm.ui");
            }
        } else if (game.e.t() && this.f == 0 && this.o.g(262144) && !this.j()) {
            this.p.a("/data/ui/choice.ui");
            this.o.a((byte)20);
        }
        this.g();
    }

    public final void am() {
        this.s = 0;
        this.f = 0;
        this.b = 0;
        this.p.a("/data/ui/choice.ui", 257, this);
        this.p.a.a((int)8).i().a = "\u0110\u1ea1o c\u1ee5";
        this.p.a.a((int)9).i().a = "S\u1ed1 l\u01b0\u1ee3ng";
        this.p.a.a((int)5).i().a = "S\u1eed d\u1ee5ng";
        this.p.a.a(59).a(false);
        this.p.a.a(60).a(false);
        this.bi();
    }

    public final void an() {
        if (this.f == 0 && this.o.g(4100)) {
            this.p.a.b(0);
            return;
        }
        if (this.f == 0 && this.o.g(8448)) {
            this.p.a.b(1);
            return;
        }
        if (this.o.g(196640)) {
            if (this.q.K.size() <= 0) {
                return;
            }
            this.s = ((int[])this.q.K.elementAt(this.h))[0];
            if (this.f == 0) {
                switch (a.b.c.c[4][this.s][5]) {
                    case 7: 
                    case 8: 
                    case 9: 
                    case 10: {
                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                        this.a("Trong chi\u1ebfn \u0111\u1ea5u kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                        this.f = 1;
                        return;
                    }
                }
                this.o.a((byte)16);
                this.p.a("/data/ui/choice.ui");
                return;
            }
            if (this.f == 1) {
                this.p.a("/data/ui/msgwarm.ui");
                this.f = 0;
                return;
            }
        } else if (this.f == 0 && this.o.g(262144)) {
            this.p.a("/data/ui/choice.ui");
            this.o.a((byte)20);
        }
    }

    private void bx() {
        if (this.f == 0) {
            this.f = 1;
            int n2 = this.o instanceof l ? this.q.A[this.c].x(this.s) : this.q.A[((a)this.o).p[this.c]].x(this.s);
            switch (n2) {
                case 0: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("S\u1ee7ng v\u1eadt n\u00e0y \u0111\u00e3 t\u1eed vong, kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                    return;
                }
                case 1: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("S\u1ee7ng v\u1eadt n\u00e0y kh\u00f4ng c\u00f3, kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                    return;
                }
                case 2: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("M\u00e1u \u0111\u1ea7y, kh\u00f4ng c\u1ea7n s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                    return;
                }
                case 3: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("K\u1ef9 n\u0103ng gi\u00e1 tr\u1ecb \u0111\u00e3 \u0111\u1ea7y, kh\u00f4ng c\u1ea7n s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                    return;
                }
                case 4: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("Tr\u00ean ng\u01b0\u1eddi \u0111\u1ec1u b\u1ecb l\u1ee3i hi\u1ec7u qu\u1ea3", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                    return;
                }
                case 5: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("Trong h\u01b0ng ph\u1ea5n, kh\u00f4ng th\u1ec3 d\u00f9ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                    return;
                }
                case 7: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("M\u00e1u v\u00e0 tinh kh\u00ed \u0111\u1ec1u \u0111\u00e3 \u0111\u1ea7y, kh\u00f4ng c\u1ea7n s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                    return;
                }
                case 8: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("S\u1ee7ng v\u1eadt \u0111\u00e3 ch\u1ebft, kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                    return;
                }
            }
            if (this.q.b(this.s, 1, (byte)0)) {
                if (this.o instanceof l) {
                    this.q.A[this.c].w(this.s);
                } else {
                    ((a)this.o).r.K = true;
                    this.q.A[((a)this.o).p[this.c]].w(this.s);
                }
                this.f(this.c);
                this.f = 1;
                this.l = true;
                this.p.a("/data/ui/msgwarm.ui", 257, this);
                this.a("Th\u00e0nh c\u00f4ng s\u1eed d\u1ee5ng \u0111\u1ea1o c\u1ee5", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                return;
            }
            this.f = 2;
            this.H();
            this.a("Kh\u00f4ng c\u00f3 \u0111\u1ea1o n\u00e0y c\u1ee5, h\u00e3y mua s\u1eafm", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
            return;
        }
        if (this.f == 1) {
            this.f = 0;
            this.p.a("/data/ui/msgwarm.ui");
            return;
        }
        if (this.f == 2) {
            this.f = 0;
            this.p.a("/data/ui/msgwarm.ui");
            this.p.a("/data/ui/petstate.ui");
            if (this.o instanceof l) {
                this.o.a((byte)8);
                return;
            }
            if (game.a.B().r.equals(((a)this.o).n(this.c))) {
                this.g(((a)this.o).o(this.c));
            }
            if (((a)this.o).r.K) {
                ((a)this.o).s = (byte)(((a)this.o).s + 1);
                ((a)this.o).a((byte)1);
                return;
            }
            ((a)this.o).a((byte)4);
        }
    }

    public final void ao() {
        if (this.f == 0 && this.o.g(4100)) {
            this.p.a.b(0);
            return;
        }
        if (this.f == 0 && this.o.g(8448)) {
            this.p.a.b(1);
            return;
        }
        if (this.o.g(196640)) {
            this.bx();
            return;
        }
        if (this.f == 0 && this.o.g(262144)) {
            if (this.l) {
                if (game.a.B().r.equals(((a)this.o).n(this.c))) {
                    this.g(((a)this.o).o(this.c));
                }
                if (((a)this.o).r.K) {
                    ((a)this.o).s = (byte)(((a)this.o).s + 1);
                    ((a)this.o).a((byte)1);
                } else {
                    ((a)this.o).a((byte)4);
                }
                this.p.a("/data/ui/petstate.ui");
                return;
            }
            ((a)this.o).a((byte)4);
            this.p.a("/data/ui/petstate.ui");
        }
    }

    public final void b(int n2, int n3) {
        i i2;
        if (this.i >= game.a.t.size()) {
            this.i = 0;
            game.f.B().a((byte)10);
            return;
        }
        block0: while (true) {
            i2 = (i)game.a.t.elementAt(this.i);
            while (this.i < game.a.t.size() && i2.u()) {
                ++this.i;
                if (this.i >= game.a.t.size()) continue;
                continue block0;
            }
            break;
        }
        this.G = n2;
        this.H = n3;
        i2.c();
        i2.b(n2, n3);
        this.w = 0;
    }

    public final void ap() {
        block20: {
            block19: {
                int n2;
                int n3;
                i i2;
                block18: {
                    if (this.i >= game.a.t.size()) {
                        this.i = 0;
                        game.f.B().a((byte)10);
                        return;
                    }
                    if (this.w <= 0) {
                        this.I += 8;
                    }
                    i2 = (i)game.a.t.elementAt(this.i);
                    n3 = i2.B() + this.I;
                    int n4 = i2.v();
                    n2 = i2.A();
                    if (n3 >= n4) {
                        n3 = n4;
                    } else if (n3 >= n2) {
                        n3 = n2;
                    }
                    if (this.o.g(196640)) {
                        if (n2 >= n4) {
                            n3 = n4;
                            this.p.a.a((int)40).i().a = n3 + "/" + n3;
                            this.p.a.a((int)9).i().a = "#P" + i2.v(n3);
                            i2.j(0);
                            this.w = 0;
                            ((a)this.o).a((byte)22);
                            return;
                        }
                        if (n3 >= n2) {
                            this.p.a.a((int)40).i().a = n2 + "/" + i2.v();
                            this.p.a.a((int)9).i().a = "#P" + i2.v(n2);
                            i2.j(n3);
                            ++this.i;
                            while (this.i < game.a.t.size() && ((i)game.a.t.elementAt(this.i)).u()) {
                                ++this.i;
                            }
                            if (this.i >= game.a.t.size()) {
                                this.i = 0;
                                game.f.B().a((byte)10);
                            } else {
                                ((i)game.a.t.elementAt(this.i)).b(this.G, this.H);
                            }
                            this.w = 0;
                            this.I = 0;
                            return;
                        }
                        this.I = 0;
                        n3 = n2;
                        i2.j(n3);
                        this.p.a.a((int)40).i().a = n3 + "/" + i2.v();
                        this.p.a.a((int)9).i().a = "#P" + i2.v(n3);
                        return;
                    }
                    this.p.a.a((int)40).i().a = n3 + "/" + i2.v();
                    this.p.a.a((int)9).i().a = "#P" + i2.v(n3);
                    i i3 = i2;
                    k k2 = this;
                    this.p.a.a((int)12).i().a = a.a.c(i3.j((byte)0));
                    k2.p.a.a((int)13).i().a = "lv" + i3.t();
                    k2.p.a.a((int)17).i().m.a(94 + i3.j((byte)1));
                    if (n3 < n4) break block18;
                    i2.j(0);
                    ((a)this.o).a((byte)22);
                    break block19;
                }
                if (n3 < n2) break block20;
                ++this.w;
                i2.j(n3);
                if (this.w >= 10) {
                    ++this.i;
                    while (this.i < game.a.t.size() && ((i)game.a.t.elementAt(this.i)).u()) {
                        ++this.i;
                    }
                    if (this.i >= game.a.t.size()) {
                        this.i = 0;
                        game.f.B().a((byte)10);
                    } else {
                        ((i)game.a.t.elementAt(this.i)).b(this.G, this.H);
                    }
                    this.w = 0;
                }
            }
            this.I = 0;
        }
    }

    public final void aq() {
        i i2;
        byte by;
        int n2;
        i i3 = (i)game.a.t.elementAt(this.i);
        String[] stringArray = new String[4];
        for (n2 = 0; n2 < 4; ++n2) {
            by = (byte)(n2 + 1);
            i2 = i3;
            stringArray[n2] = "" + i2.d[by];
        }
        i3.w();
        this.g(i3);
        this.p.a("/data/ui/levelUp.ui", 257, this);
        for (n2 = 0; n2 < 4; ++n2) {
            this.p.a.a((int)(n2 + 19)).i().a = stringArray[n2];
        }
        if (i3.F() < 5 && i3.F() < i3.t() / 10 + 1) {
            this.x = i3.G();
            this.p.a.a((int)51).i().a = "C\u00f3 th\u1ec3 h\u1ecdc t\u1eadp k\u1ef9 n\u0103ng m\u1edbi";
        } else {
            this.p.a.a((int)51).i().a = "";
        }
        this.p.a.a((int)38).i().a = a.a.c(a.b.c.c[0][i3.r()][0]);
        this.p.a.a((int)40).i().a = "" + i3.t();
        if (this.p.a.a((int)10).i().m == null) {
            this.p.a.a((int)10).i().m = new c.g();
            this.p.a.a((int)10).i().m.a = (byte)3;
            this.p.a.a((int)10).i().m.a(0);
            this.p.a.a((int)10).i().m.a(i3.D, false, (byte)-1);
        }
        for (n2 = 0; n2 < 4; ++n2) {
            by = (byte)(n2 + 1);
            i2 = i3;
            this.p.a.a((int)(n2 + 31)).i().a = "" + i2.d[by];
        }
    }

    public final void ar() {
        ++this.J;
        if (this.J > 40) {
            this.J = 0;
            if (this.x != null) {
                ((a)this.o).a((byte)23);
            } else if (this.i + 1 >= game.a.t.size()) {
                if (((i)game.a.t.elementAt(this.i)).A() > 0) {
                    this.o.a((byte)8);
                } else {
                    this.i = 0;
                    game.f.B().a((byte)10);
                }
                this.p.a("/data/ui/levelUp.ui");
            } else {
                this.o.a((byte)8);
                this.p.a("/data/ui/levelUp.ui");
            }
        }
        if (this.o.g(196640)) {
            this.J = 0;
            if (this.x != null) {
                this.o.a((byte)23);
                return;
            }
            if (this.i + 1 >= game.a.t.size()) {
                if (((i)game.a.t.elementAt(this.i)).A() > 0) {
                    this.o.a((byte)8);
                } else {
                    this.i = 0;
                    game.f.B().a((byte)10);
                }
                this.p.a("/data/ui/levelUp.ui");
                return;
            }
            this.o.a((byte)8);
            this.p.a("/data/ui/levelUp.ui");
        }
    }

    public final void as() {
        this.p.a("/data/ui/choiceskill.ui", 257, this);
        this.p.a("/data/ui/levelUp.ui");
        this.b = 0;
        this.f = 0;
        ((c.b)this.p.a.a((int)0)).a.a = this.x.length;
        if (this.x.length > 5) {
            ((c.b)this.p.a.a((int)0)).a.a(1);
        } else {
            ((c.b)this.p.a.a((int)0)).a.a(-1);
        }
        if (this.p.a.a((int)5).i().m == null) {
            this.p.a.a((int)5).i().m = new c.g();
            this.p.a.a((int)5).i().m.a = (byte)3;
            this.p.a.a((int)5).i().m.a(0);
            this.p.a.a((int)5).i().m.a(257, false, (byte)-1);
        }
        this.p.a.a((int)5).i().m.a((byte)11, (byte)-1);
        this.p.a.a(6).a(false);
        this.by();
        if (!game.l.N) {
            this.b("C\u00f3 th\u1ec3 nh\u1ea5n #1n\u00fat m\u1ec1m tr\u00e1i#0 \u0111\u1ec3 h\u1ecdc t\u1eadp k\u1ef9 n\u0103ng");
            game.l.N = true;
        }
    }

    private void by() {
        this.v = ((c.b)this.p.a.a((int)0)).a.e;
        this.h = ((c.b)this.p.a.a((int)0)).a.f;
        for (int i2 = 0; i2 < 5; ++i2) {
            if (i2 >= this.x.length) {
                this.p.a.a((int)(13 + i2 * 5)).i().a = "";
                this.p.a.a((int)(14 + i2 * 5)).i().a = "";
                continue;
            }
            this.p.a.a((int)(13 + i2 * 5)).i().a = a.a.c(a.b.c.c[1][this.x[this.v + i2]][1]);
            this.p.a.a((int)(14 + i2 * 5)).i().a = "" + a.b.c.c[1][this.x[this.v + i2]][5];
        }
        this.i(this.x[this.h]);
        this.p.a.a(51).b(98 + this.h * 62 / this.x.length, this.p.a.a());
    }

    public final void at() {
        if (!this.j() && this.o.g(4100) && this.f == 0) {
            this.p.a.b(0);
            this.by();
        } else if (!this.j() && this.o.g(8448) && this.f == 0) {
            this.p.a.b(1);
            this.by();
        } else if (!this.j() && this.f == 0 && (this.o.g(131072) || this.o.a(40, 228, 45, 20)) || this.f == 1 && this.o.g(196640)) {
            if (this.f == 0) {
                this.f = 1;
                this.p.a("/data/ui/msgwarm.ui", 257, this);
                this.a("H\u1ecdc t\u1eadp" + a.a.c(a.b.c.c[1][this.x[this.b]][1]), "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
            } else if (this.f == 1) {
                i i2 = (i)game.a.t.elementAt(this.i);
                i2.g((byte)this.x[this.h]);
                this.x = null;
                if (this.i + 1 >= game.a.t.size() && i2.A() <= 0) {
                    this.i = 0;
                    game.f.B().a((byte)10);
                } else {
                    this.o.a((byte)8);
                }
                this.p.a("/data/ui/msgwarm.ui");
                this.p.a("/data/ui/choiceskill.ui");
            }
        }
        this.f();
    }

    public final void au() {
        this.f = 0;
        this.b("Ba l\u00f4 s\u1ee7ng v\u1eadt \u0111\u1ec1u th\u0103ng 5 c\u1ea5p");
    }

    /*
     * Enabled aggressive block sorting
     */
    public final void av() {
        block14: {
            k k2;
            block17: {
                block22: {
                    block21: {
                        block19: {
                            block20: {
                                block18: {
                                    block16: {
                                        block15: {
                                            if (this.f != 0) break block15;
                                            if (this.aA()) {
                                                this.f = 1;
                                                if (game.l.O.size() <= 0) {
                                                    this.o.a((byte)14);
                                                }
                                            }
                                            break block14;
                                        }
                                        if (this.f != 1) break block16;
                                        this.p.a("/data/ui/bodyShop.ui");
                                        this.bz();
                                        this.g = true;
                                        break block14;
                                    }
                                    k2 = this;
                                    if (k2.f < 3) break block17;
                                    if (k2.f != 5) break block18;
                                    k2.f = 6;
                                    k2.K();
                                    k2.a("\u0110ang l\u01b0u...");
                                    k2.M();
                                    break block19;
                                }
                                if (k2.f != 6) break block20;
                                game.l.Q = (byte)2;
                                a.a cfr_ignored_0 = k2.o;
                                game.l.F();
                                if (((l)k2.o).H()) {
                                    k2.a("L\u01b0u th\u00e0nh c\u00f4ng");
                                    k2.f = 7;
                                }
                                break block19;
                            }
                            if (k2.f != 7) break block19;
                            k2.p.a("/data/ui/msgtip.ui");
                            k2.f = 0;
                            if (k2.o.b == 14) {
                                k2.o.a((byte)14);
                                break block14;
                            } else {
                                k2.o.a((byte)0);
                            }
                            break block14;
                        }
                        if (k2.j() || !k2.o.g(4100) || k2.f != 3) break block21;
                        k2.p.a.b(0);
                        k2.by();
                        break block14;
                    }
                    if (k2.j() || !k2.o.g(8448) || k2.f != 3) break block22;
                    k2.p.a.b(1);
                    k2.by();
                    break block14;
                }
                if ((k2.j() || k2.f != 3 || !k2.o.g(131072) && !k2.o.a(40, 228, 45, 20)) && (k2.f != 4 || !k2.o.g(196640))) break block14;
                if (k2.f == 3) {
                    k2.f = 4;
                    k2.p.a("/data/ui/msgwarm.ui", 257, k2);
                    k2.a("H\u1ecdc t\u1eadp" + a.a.c(a.b.c.c[1][k2.x[k2.h]][1]), "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                    break block14;
                } else if (k2.f == 4) {
                    ((i)game.l.O.elementAt(k2.i)).g((byte)k2.x[k2.h]);
                    k2.x = null;
                    ++k2.i;
                    if (k2.i >= game.l.O.size()) {
                        k2.i = 0;
                        k2.f = 5;
                    } else {
                        k2.bz();
                    }
                    k2.p.a("/data/ui/msgwarm.ui");
                    k2.p.a("/data/ui/choiceskill.ui");
                }
                break block14;
            }
            if (k2.o.g(196640)) {
                k k3 = k2;
                k3.p.a("/data/ui/choiceskill.ui", 257, k3);
                k3.p.a("/data/ui/levelUp.ui");
                k3.b = 0;
                k3.f = 3;
                ((c.b)k3.p.a.a((int)0)).a.a = k3.x.length;
                if (k3.x.length > 5) {
                    ((c.b)k3.p.a.a((int)0)).a.a(1);
                } else {
                    ((c.b)k3.p.a.a((int)0)).a.a(-1);
                }
                if (k3.p.a.a((int)5).i().m == null) {
                    k3.p.a.a((int)5).i().m = new c.g();
                    k3.p.a.a((int)5).i().m.a = (byte)3;
                    k3.p.a.a((int)5).i().m.a(0);
                    k3.p.a.a((int)5).i().m.a(257, false, (byte)-1);
                }
                k3.p.a.a((int)5).i().m.a((byte)11, (byte)-1);
                k3.p.a.a(6).a(false);
                k3.by();
                k2.g = true;
            }
        }
        this.f();
    }

    private void bz() {
        int n2;
        this.f = 2;
        i i2 = (i)game.l.O.elementAt(this.i);
        this.p.a("/data/ui/levelUp.ui", 257, this);
        for (n2 = 0; n2 < 4; ++n2) {
            this.p.a.a((int)(n2 + 19)).i().a = "" + i2.i((int)((byte)(n2 + 1 - 1)));
        }
        if (i2.F() < 5 && i2.F() < i2.t() / 10 + 1) {
            this.x = i2.G();
            this.p.a.a((int)51).i().a = "Nh\u1ea5n n\u00fat 5 h\u1ecdc t\u1eadp k\u1ef9 n\u0103ng m\u1edbi";
        } else {
            this.p.a.a((int)51).i().a = "";
        }
        this.p.a.a((int)38).i().a = a.a.c(a.b.c.c[0][i2.r()][0]);
        this.p.a.a((int)40).i().a = "" + i2.t();
        if (this.p.a.a((int)10).i().m == null) {
            this.p.a.a((int)10).i().m = new c.g();
            this.p.a.a((int)10).i().m.a = (byte)3;
            this.p.a.a((int)10).i().m.a(0);
            this.p.a.a((int)10).i().m.a(i2.D, false, (byte)-1);
        }
        for (n2 = 0; n2 < 4; ++n2) {
            byte by = (byte)(n2 + 1);
            i i3 = i2;
            this.p.a.a((int)(n2 + 31)).i().a = "" + i3.d[by];
        }
    }

    public final void aw() {
        this.p.a("/data/ui/npcEnemy.ui", 296, this);
        if (this.p.a.a((int)1).i().m == null) {
            this.p.a.a((int)1).i().m = new c.g();
            this.p.a.a((int)1).i().m.a = (byte)2;
            this.p.a.a((int)1).i().m.a(296, false, (byte)0);
            this.p.a.a((int)1).i().m.a(0);
        }
        this.p.a.a(36).a(false);
    }

    private void a(int n2, int n3, int n4) {
        if (n4 != -1 && this.p.a.a((int)n4).i().m != null) {
            this.p.a.a(n4).a(false);
        }
        if (this.p.a.a((int)n2).i().m == null) {
            this.p.a.a((int)n2).i().m = new c.g();
            this.p.a.a((int)n2).i().m.a = (byte)2;
            this.p.a.a((int)n2).i().m.a(296, false, (byte)0);
            this.p.a.a((int)n2).i().m.a(0);
        }
        this.p.a.a((int)n2).i().m.a(n3);
    }

    public final void c(int n2, int n3) {
        switch (n2) {
            case 0: {
                this.p.a.a((int)1).i().m.a(n3);
                return;
            }
            case 1: {
                for (n2 = 2; n2 < 4; ++n2) {
                    if (this.p.a.a((int)n2).i().m == null) {
                        this.p.a.a((int)n2).i().m = new c.g();
                        this.p.a.a((int)n2).i().m.a = (byte)2;
                        this.p.a.a((int)n2).i().m.a(0);
                    }
                    if (n2 % 2 == 1) {
                        this.p.a.a((int)n2).i().m.a(0, false, (byte)-1);
                    } else if (game.l.E == -1) {
                        if (game.l.F == -1) {
                            this.p.a.a((int)n2).i().m.a(game.l.B().n[8].a.a, false, (byte)-1);
                        } else {
                            this.p.a.a((int)n2).i().m.a(game.l.B().n[game.l.F].a.a, false, (byte)-1);
                        }
                    } else {
                        this.p.a.a((int)n2).i().m.a(game.l.B().n[game.l.E].a.a, false, (byte)-1);
                    }
                    this.p.a.a((int)n2).i().m.a(1);
                }
                this.p.a.a((int)1).i().m.a(n3);
                return;
            }
            case 2: {
                for (n2 = 2; n2 < 4; ++n2) {
                    if (this.p.a.a((int)n2).i().m != null) {
                        this.p.a.a(n2).a(false);
                    }
                    if (this.p.a.a((int)(n2 + 32)).i().m == null) {
                        this.p.a.a((int)(n2 + 32)).i().m = new c.g();
                        this.p.a.a((int)(n2 + 32)).i().m.a = (byte)2;
                        this.p.a.a((int)(n2 + 32)).i().m.a(0);
                    }
                    if (n2 % 2 == 1) {
                        this.p.a.a((int)(n2 + 32)).i().m.a(0, false, (byte)-1);
                    } else if (game.l.E == -1) {
                        if (game.l.F == -1) {
                            this.p.a.a((int)(n2 + 32)).i().m.a(game.l.B().n[8].a.a, false, (byte)-1);
                        } else {
                            this.p.a.a((int)(n2 + 32)).i().m.a(game.l.B().n[game.l.F].a.a, false, (byte)-1);
                        }
                    } else {
                        this.p.a.a((int)(n2 + 32)).i().m.a(game.l.B().n[game.l.E].a.a, false, (byte)-1);
                    }
                    this.p.a.a((int)(n2 + 32)).i().m.a(1);
                }
                this.p.a.a((int)1).i().m.a(n3);
                return;
            }
            case 3: {
                for (n2 = 2; n2 < 4; ++n2) {
                    if (this.p.a.a((int)(n2 + 32)).i().m != null) {
                        this.p.a.a(n2 + 32).a(false);
                    }
                    if (this.p.a.a((int)(n2 + 2)).i().m == null) {
                        this.p.a.a((int)(n2 + 2)).i().m = new c.g();
                        this.p.a.a((int)(n2 + 2)).i().m.a = (byte)2;
                        this.p.a.a((int)(n2 + 2)).i().m.a(0);
                    }
                    if (n2 % 2 == 1) {
                        this.p.a.a((int)(n2 + 2)).i().m.a(0, false, (byte)-1);
                    } else if (game.l.E == -1) {
                        if (game.l.F == -1) {
                            this.p.a.a((int)(n2 + 2)).i().m.a(game.l.B().n[8].a.a, false, (byte)-1);
                        } else {
                            this.p.a.a((int)(n2 + 2)).i().m.a(game.l.B().n[game.l.F].a.a, false, (byte)-1);
                        }
                    } else {
                        this.p.a.a((int)(n2 + 2)).i().m.a(game.l.B().n[game.l.E].a.a, false, (byte)-1);
                    }
                    this.p.a.a((int)(n2 + 2)).i().m.a(1);
                }
                this.p.a.a((int)1).i().m.a(n3);
                this.K = game.a.B().H();
                this.L = this.q.B;
                if (n3 - 3 < this.K) {
                    this.a(6, 6, -1);
                }
                if (n3 - 3 >= this.L) break;
                this.a(18, 6, -1);
                return;
            }
            case 4: {
                if (n3 - 3 < this.K) {
                    this.a(6 + (n3 - 3 << 1), 6, 6 + (n3 - 4 << 1));
                } else {
                    this.a(6 + (n3 - 3 << 1), 5, 6 + (n3 - 4 << 1));
                }
                if (n3 - 4 < this.K) {
                    this.a(7 + (n3 - 4 << 1), 6, 6 + (n3 - 4 << 1));
                } else {
                    this.a(7 + (n3 - 4 << 1), 5, 6 + (n3 - 4 << 1));
                }
                if (n3 - 4 < this.L) {
                    this.a(19 + (n3 - 4 << 1), 6, 18 + (n3 - 4 << 1));
                } else {
                    this.a(19 + (n3 - 4 << 1), 5, 18 + (n3 - 4 << 1));
                }
                if (n3 - 3 < this.L) {
                    this.a(18 + (n3 - 3 << 1), 6, 18 + (n3 - 4 << 1));
                    return;
                }
                this.a(18 + (n3 - 3 << 1), 5, 18 + (n3 - 4 << 1));
                return;
            }
            case 5: {
                if (n3 - 4 < this.K) {
                    this.a(7 + (n3 - 4 << 1), 6, 6 + (n3 - 4 << 1));
                } else {
                    this.a(7 + (n3 - 4 << 1), 5, 6 + (n3 - 4 << 1));
                }
                if (n3 - 4 < this.L) {
                    this.a(19 + (n3 - 4 << 1), 6, 18 + (n3 - 4 << 1));
                    return;
                }
                this.a(19 + (n3 - 4 << 1), 5, 18 + (n3 - 4 << 1));
                return;
            }
            case 6: {
                this.a(30, 8, -1);
                this.a(31, 7, -1);
                return;
            }
            case 7: {
                this.a(32, 8, 30);
                this.a(33, 7, 31);
                return;
            }
            case 8: {
                this.p.a.a(36).a(true);
                return;
            }
            case 9: {
                this.p.a.a(36).a(false);
                return;
            }
            case 10: {
                this.a(1, 4, 32);
                this.a(1, 4, 33);
                for (n2 = 4; n2 < 6; ++n2) {
                    this.p.a.a(n2).a(false);
                }
                for (n2 = 7; n2 < 19; n2 += 2) {
                    this.p.a.a(n2).a(172 + 17 * (n2 - 7) / 2, this.p.a.a());
                    this.p.a.a(n2 + 12).a(-30 + 17 * (n2 - 7) / 2, this.p.a.a());
                }
                return;
            }
            case 11: {
                for (n2 = 4; n2 < 6; ++n2) {
                    this.p.a.a(n2).a(false);
                }
                for (n2 = 7; n2 < 19; n2 += 2) {
                    this.p.a.a(n2).a(false);
                    this.p.a.a(n2 + 12).a(false);
                }
                this.a(1, 0, -1);
            }
        }
    }

    private void e(String string) {
        if (this.p.a.a((int)1).i().m == null) {
            this.p.a.a((int)1).i().m = new c.g();
            this.p.a.a((int)1).i().m.a(0);
            this.p.a.a((int)1).i().m.a = (byte)3;
            this.p.a.a((int)1).i().m.a(257, false, (byte)-2);
        }
        this.p.a.a((int)1).i().m.a((byte)9, (byte)-2);
        this.p.a.a((int)2).i().a = string;
        this.u = 0;
    }

    public final void ax() {
        this.p.a("/data/ui/openbox.ui", 257, this);
        this.e("Kh\u00f4ng c\u00f3 c\u00e1i ch\u00eca kh\u00f3a, c\u00f3 th\u1ec3 \u0111\u1ebfn t\u00e0i li\u1ec7u c\u1eeda h\u00e0ng mua s\u1eafm");
    }

    public final void ay() {
        this.p.a("/data/ui/openbox.ui", 257, this);
        this.e("\u0110\u1ea1o c\u1ee5 \u0111\u00e3 \u0111\u1ee7");
    }

    public final void a(String string, int n2) {
        this.p.a("/data/ui/openbox.ui", 257, this);
        this.e(string + " x " + n2);
    }

    public final void b(String string) {
        this.p.a("/data/ui/openbox.ui", 257, this);
        this.e(string);
    }

    public final void az() {
        if (this.p.b("/data/ui/openbox.ui")) {
            this.p.a("/data/ui/openbox.ui");
        }
    }

    public final boolean aA() {
        return !this.p.b("/data/ui/openbox.ui");
    }

    public final void c(String object) {
        this.p.a("/data/ui/taskTip.ui", 257, this);
        String string = object;
        object = this;
        if (((k)object).p.a.a((int)1).i().m == null) {
            ((k)object).p.a.a((int)1).i().m = new c.g();
            ((k)object).p.a.a((int)1).i().m.a(0);
            ((k)object).p.a.a((int)1).i().m.a = (byte)3;
            ((k)object).p.a.a((int)1).i().m.a(257, false, (byte)-2);
        }
        ((k)object).p.a.a((int)1).i().m.a((byte)10, (byte)-2);
        ((k)object).p.a.a((int)2).i().a = string;
        ((k)object).u = 0;
    }

    public final boolean aB() {
        return !this.p.b("/data/ui/taskTip.ui");
    }

    public final void aC() {
        this.c = 0;
        this.f = 0;
        this.p.a("/data/ui/bodyShop.ui", 257, this);
        this.bA();
    }

    private void bA() {
        Object object = "";
        switch (this.c) {
            case 0: {
                object = "T\u00f9y th\u1eddi mua s\u1eafm c\u00e1c lo\u1ea1i \u0111\u1ea1o c\u1ee5, gi\u00e0 tr\u1ebb kh\u00f4ng g\u1ea1t.";
                break;
            }
            case 1: {
                object = new int[]{2, 1, 2};
                object = a.a.c(602) + a.a.a(604, (int[])object);
                break;
            }
            case 2: {
                object = new int[]{2, 1, 2};
                object = a.a.c(603) + a.a.a(604, (int[])object);
                break;
            }
            case 3: {
                object = new int[]{2, 1, 2};
                object = a.a.c(601) + a.a.a(604, (int[])object);
            }
        }
        this.p.a.a((int)11).i().a = (String)object;
        if (this.c > 0) {
            this.o.c((byte)0);
            this.bB();
        }
    }

    private void bB() {
        switch (this.c) {
            case 1: {
                this.o.b((byte)3);
                return;
            }
            case 2: {
                this.o.b((byte)4);
                return;
            }
            case 3: {
                this.o.b((byte)2);
            }
        }
    }

    public final void aD() {
        block0 : switch (this.c) {
            case 0: {
                if (this.o.g(4100) && this.f == 0) {
                    this.p.a.b(0);
                    this.bA();
                    return;
                }
                if (this.o.g(8448) && this.f == 0) {
                    this.p.a.b(1);
                    this.bA();
                    return;
                }
                if (this.o.g(196640)) {
                    this.o.a((byte)26);
                    this.p.a("/data/ui/bodyShop.ui");
                    return;
                }
                if (!this.o.g(786432)) break;
                this.b = 0;
                this.o.a((byte)6);
                this.p.a("/data/ui/bodyShop.ui");
                return;
            }
            default: {
                switch (this.o.x()) {
                    case 0: {
                        if (this.o.g(4100) && this.f == 0) {
                            this.p.a.b(0);
                            this.bA();
                            return;
                        }
                        if (this.o.g(8448) && this.f == 0) {
                            this.p.a.b(1);
                            this.bA();
                            return;
                        }
                        if (this.f == 0 && this.o.g(131072) || this.f == 1 && this.o.g(65568)) {
                            if (this.f == 0) {
                                this.bB();
                                if (this.o.v() == 3) {
                                    int n2;
                                    if (game.l.R != null) {
                                        game.l.R.removeAllElements();
                                    }
                                    for (n2 = 0; n2 < game.j.p().B && game.j.p().A[n2].t() >= 50; ++n2) {
                                    }
                                    if (n2 >= game.j.p().B) {
                                        this.f = 1;
                                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                                        this.a("Trong ba l\u00f4 s\u1ee7ng v\u1eadt \u0111\u1ec1u \u0111\u00e3 max level", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                                        return;
                                    }
                                }
                                if (this.o.y() > 1) {
                                    this.o.c((byte)1);
                                    return;
                                }
                                if (!this.p.a.a(11).i().b()) break block0;
                                this.o.e(1);
                                return;
                            }
                            this.f = 0;
                            this.p.a("/data/ui/msgwarm.ui");
                            return;
                        }
                        if (!this.o.g(786432) || this.f != 0) break block0;
                        this.b = 0;
                        this.o.a((byte)6);
                        this.p.a("/data/ui/bodyShop.ui");
                        return;
                    }
                    case 1: {
                        if (this.o.g(131072)) {
                            this.o.f(1);
                            return;
                        }
                        if (!this.o.g(262144)) break block0;
                        this.o.f(2);
                        return;
                    }
                    case 2: {
                        if (!this.bC() || !this.o.g(917504)) break block0;
                        if (this.o.w()) {
                            if (this.o.v() == 3) {
                                this.o.a((byte)25);
                            }
                            this.o.c((byte)5);
                        } else {
                            this.o.c((byte)1);
                        }
                        this.f = 0;
                        return;
                    }
                    case 3: {
                        if (!this.o.g(393216)) break block0;
                        this.o.f(1);
                    }
                }
            }
        }
    }

    private boolean bC() {
        if (this.f == 0) {
            this.f = 1;
            this.K();
            this.a("\u0110ang l\u01b0u...");
            this.M();
        } else if (this.f == 1) {
            if (this.o.v() == 3) {
                if (game.l.B().I()) {
                    this.a("L\u01b0u th\u00e0nh c\u00f4ng");
                    this.f = 2;
                }
            } else if (game.l.B().J()) {
                this.a("L\u01b0u th\u00e0nh c\u00f4ng");
                this.f = 2;
            }
        } else if (this.f == 2) {
            this.p.a("/data/ui/msgtip.ui");
            this.f = 3;
        } else if (this.f == 3) {
            return true;
        }
        return false;
    }

    public final void aE() {
        this.p.a("/data/ui/dialog.ui", 257, this);
        this.p.a.a(12).a(false);
        this.p.a.a(13).a(false);
    }

    public final void a(String string, String string2, int n2, int n3) {
        this.p.a("/data/ui/dialog.ui", 257, this);
        a.e.a(string2, a.a.o(), this.p.a.a(14).e(), a.a.m(), true, (byte)-1, this.o.c.b);
        a.e.d(this.p.a.a(14).f());
        this.p.a.a((int)14).i().a = a.e.e(1);
        game.l.D = (byte)n2;
        game.l.C = (byte)n3;
        this.p.a.a(8).a(false);
        this.p.a.a(11).a(false);
        this.p.a.a(12).a(true);
        this.p.a.a(13).a(true);
        if (n2 == -1) {
            this.p.a.a(12).a(false);
            this.p.a.a(13).a(false);
        }
        switch (n2) {
            case 0: {
                if (n3 != -1) {
                    if (this.p.a.a((int)11).i().m == null) {
                        this.p.a.a((int)11).i().m = new c.g();
                        this.p.a.a((int)11).i().m.a = (byte)3;
                        this.p.a.a((int)11).i().m.a(0);
                        this.p.a.a((int)11).i().m.a(323, false, (byte)-2);
                    }
                    this.p.a.a(11).a(true);
                    this.p.a.a((int)11).i().m.a((byte)(n2 + (n3 << 1)), (byte)-2);
                }
                this.p.a.a(13).a(false);
                this.p.a.a((int)12).i().a = string;
                return;
            }
            case 1: {
                if (n3 != -1) {
                    if (this.p.a.a((int)8).i().m == null) {
                        this.p.a.a((int)8).i().m = new c.g();
                        this.p.a.a((int)8).i().m.a = (byte)3;
                        this.p.a.a((int)8).i().m.a(0);
                        this.p.a.a((int)8).i().m.a(323, false, (byte)-2);
                    }
                    this.p.a.a(8).a(true);
                    this.p.a.a((int)8).i().m.a((byte)(n2 + (n3 << 1)), (byte)-2);
                }
                this.p.a.a(12).a(false);
                this.p.a.a((int)13).i().a = string;
            }
        }
    }

    public final void b(int n2) {
        this.p.a.a((int)14).i().a = a.e.e(n2);
    }

    public final void aF() {
        this.p.a("/data/ui/dialog.ui");
    }

    public final boolean d(int n2, int n3) {
        if (n3 == -1) {
            return true;
        }
        switch (n2) {
            case 0: {
                if (!this.p.a.a((int)11).i().m.a().f()) break;
                return true;
            }
            case 1: {
                if (!this.p.a.a((int)8).i().m.a().f()) break;
                return true;
            }
        }
        this.g = true;
        return false;
    }

    public final void a(int n2, int n3, String[] stringArray, String string) {
        this.b = 0;
        this.p.a(this.M[n2], 257, this);
        ((c.b)this.p.a.a((int)0)).a.a = n3;
        switch (n2) {
            case 0: {
                for (n2 = 0; n2 < stringArray.length; ++n2) {
                    this.p.a.a((int)(n2 + 12)).i().a = stringArray[n2];
                }
                return;
            }
            case 1: {
                this.p.a.a((int)5).i().a = string;
                for (n2 = 0; n2 < stringArray.length; ++n2) {
                    this.p.a.a((int)(9 + (n2 << 2))).i().a = stringArray[n2];
                }
                return;
            }
            case 2: {
                this.p.a.a(10).a(false);
                this.p.a.a((int)8).i().a = "Tr\u00f2 ch\u01a1i";
                this.p.a.a((int)9).i().a = "X\u00e1c nh\u1eadn";
                for (n2 = 0; n2 < stringArray.length; ++n2) {
                    this.p.a.a((int)(n2 + 5)).i().a = stringArray[n2];
                }
                break;
            }
        }
    }

    public final int c(int n2) {
        if (this.o.g(4100)) {
            this.p.a.b(0);
            this.b = this.y[0];
        } else if (this.o.g(8448)) {
            this.p.a.b(1);
            this.b = this.y[0];
        } else if (this.o.g(196640)) {
            int n3 = n2;
            k k2 = this;
            k2.p.a(k2.M[n3]);
            return this.b;
        }
        return -1;
    }

    public final void a(int[] nArray, int[] nArray2, String[] stringArray, String[] stringArray2) {
        int n2;
        this.b = 0;
        this.p.a("/data/ui/taskOption.ui", 257, this);
        for (n2 = 0; n2 < stringArray2.length; ++n2) {
            this.p.a.a((int)(n2 + 17)).i().a = stringArray2[n2];
        }
        block10: for (n2 = 0; n2 < nArray.length; ++n2) {
            if (this.p.a.a((int)((n2 << 1) + 13)).i().m == null) {
                this.p.a.a((int)((n2 << 1) + 13)).i().m = new c.g();
                this.p.a.a((int)((n2 << 1) + 13)).i().m.a = (byte)2;
                if (nArray[n2] < 3 || nArray[n2] >= 5) {
                    this.p.a.a((int)((n2 << 1) + 13)).i().m.a(0);
                    this.p.a.a((int)((n2 << 1) + 13)).i().m.a(258, false, (byte)0);
                } else {
                    this.p.a.a((int)((n2 << 1) + 13)).i().m.a(-1);
                    this.p.a.a((int)((n2 << 1) + 13)).i().m.a(257, false, (byte)0);
                }
            }
            switch (nArray[n2]) {
                case 0: {
                    this.p.a.a((int)((n2 << 1) + 13)).i().m.a(a.b.c.c[4][nArray2[n2]][1]);
                    this.p.a.a((int)((n2 << 1) + 14)).i().a = stringArray[n2];
                    continue block10;
                }
                case 1: {
                    this.p.a.a((int)((n2 << 1) + 13)).i().m.a(a.b.c.c[3][nArray2[n2]][1]);
                    this.p.a.a((int)((n2 << 1) + 14)).i().a = stringArray[n2];
                    continue block10;
                }
                case 2: {
                    this.p.a.a((int)((n2 << 1) + 13)).i().m.a(a.b.c.c[5][nArray2[n2]][1]);
                    this.p.a.a((int)((n2 << 1) + 14)).i().a = stringArray[n2];
                    continue block10;
                }
                case 3: {
                    this.p.a.a((int)((n2 << 1) + 13)).i().m.a(84);
                    this.p.a.a((int)((n2 << 1) + 14)).i().a = stringArray[n2];
                    continue block10;
                }
                case 4: {
                    this.p.a.a((int)((n2 << 1) + 13)).i().m.a(83);
                    this.p.a.a((int)((n2 << 1) + 14)).i().a = stringArray[n2];
                    continue block10;
                }
                case 5: {
                    continue block10;
                }
                case 6: {
                    this.p.a.a((int)21).i().a = "#2" + a.a.c(a.b.c.a((byte)0, (short)nArray2[n2], (byte)0)) + " #0" + stringArray[n2];
                }
            }
        }
    }

    public final int aG() {
        if (this.o.g(4100)) {
            this.p.a.b(0);
            this.b = this.y[0];
        } else if (this.o.g(8448)) {
            this.p.a.b(1);
            this.b = this.y[0];
        } else {
            if (this.o.g(196640)) {
                this.p.a("/data/ui/taskOption.ui");
                return this.b;
            }
            if (this.o.g(262144)) {
                this.p.a("/data/ui/taskOption.ui");
                return 1;
            }
        }
        return -1;
    }

    public final void aH() {
        this.bE();
        this.c("C\u00f3 d\u00f9ng 10000 kim ti\u1ec1n \u0111\u1ec3 kh\u00f4i ph\u1ee5c tr\u1ea1ng th\u00e1i c\u1ee7a t\u1ea5t c\u1ea3 s\u1ee7ng v\u1eadt trong ba l\u00f4 kh\u00f4ng?", "T\u1ea1i ch\u1ed7 s\u1ed1ng l\u1ea1i");
    }

    private void bD() {
        int n2;
        int n3 = -1;
        if (game.l.B().p == 9) {
            n3 = (byte)game.l.B().q;
        }
        if (n3 == -1) {
            game.l.B();
            if (game.l.G()) {
                game.l.B().c();
                this.q.z = false;
                game.f.B().a((byte)9);
                return;
            }
            game.f.B().a((byte)7);
            return;
        }
        for (n2 = 0; n2 < this.q.B; ++n2) {
            this.q.A[n2].l(1);
            this.q.A[n2].u(1);
            this.q.A[n2].c();
        }
        game.l.E = (short)-1;
        if (game.l.B().q == 0) {
            short[] sArray = new short[]{15, 194, 433, 16, 142, 357, 17, 97, 268, 18, 183, 224};
            for (int i2 = 0; i2 < game.l.B().n.length; ++i2) {
                for (int i3 = 0; i3 < sArray.length / 3; ++i3) {
                    if (game.l.B().n[i2].J != sArray[i3 * 3]) continue;
                    game.l.B().n[i2].b(sArray[i3 * 3 + 1], (int)sArray[i3 * 3 + 2]);
                }
            }
        }
        game.l.B().p = this.m[n3 << 2];
        game.l.B().q = this.m[(n3 << 2) + 1];
        game.j.p().b(this.m[(n3 << 2) + 2], (int)this.m[(n3 << 2) + 3]);
        game.j.p().b.b(this.m[(n3 << 2) + 2], (int)this.m[(n3 << 2) + 3]);
        n2 = 2;
        j j2 = game.j.p();
        game.j.p().o = (byte)n2;
        game.f.B().a((byte)10);
    }

    public final void aI() {
        if (this.o.g(196640)) {
            if (this.f == 0) {
                if (this.q.u(10000)) {
                    this.q.s(-10000);
                    for (int i2 = 0; i2 < this.q.B; ++i2) {
                        this.q.A[i2].J();
                        int n2 = 1;
                        i i3 = this.q.A[i2];
                        this.q.A[i2].u(i3.e[n2]);
                    }
                    game.a.B().C();
                    this.o.a((byte)0);
                    this.bF();
                    return;
                }
                this.H();
                this.a("Kim ti\u1ec1n ch\u01b0a \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                this.f = 1;
                return;
            }
            for (int i4 = 0; i4 < this.q.B; ++i4) {
                this.q.A[i4].l(1);
                this.q.A[i4].u(1);
                this.q.A[i4].c();
            }
            if (a.a.i) {
                this.o.a((byte)102);
            } else {
                this.bD();
            }
            this.I();
            return;
        }
        if (this.f == 0 && this.o.g(786432)) {
            this.bD();
            this.bF();
        }
    }

    public final void d(int n2) {
        this.o.b((byte)0);
        this.o.c((byte)0);
        this.bE();
        Object object = new int[]{4, 1, 4};
        object = a.a.c(599) + a.a.a(604, object);
        this.c((String)object, "K\u00edch ho\u1ea1t tr\u00f2 ch\u01a1i");
    }

    public final void aJ() {
        this.o.b((byte)1);
        this.o.c((byte)0);
        this.bE();
        Object object = new int[]{2, 1, 2};
        object = a.a.c(600) + a.a.a(604, object);
        this.c((String)object, "Mua s\u1eafm t\u1ea5t tr\u00fang c\u1ea7u");
    }

    public final void aK() {
        this.f = 0;
        this.o.b((byte)4);
        this.o.c((byte)0);
        this.bE();
        Object object = new int[]{2, 1, 2};
        object = a.a.c(603) + a.a.a(604, object);
        this.c((String)object, "Mua huy hi\u1ec7u");
    }

    public final void aL() {
        this.f = 0;
        this.o.b((byte)2);
        this.o.c((byte)0);
        this.bE();
        Object object = new int[]{2, 1, 2};
        object = a.a.c(601) + a.a.a(604, object);
        this.c((String)object, "Mua kim ti\u1ec1n");
    }

    private void bE() {
        this.p.a("/data/ui/smsInfo.ui", 257, this);
        if (this.o instanceof l) {
            this.p.a.a(6).a(true);
            this.p.a.a(7).a(true);
            this.p.a.a(10).a(false);
            this.p.a.a(11).a(false);
            return;
        }
        this.p.a.a(6).a(false);
        this.p.a.a(7).a(false);
        this.p.a.a(10).a(true);
        this.p.a.a(11).a(true);
        this.p.a.a((int)10).i().a = "X\u00e1c nh\u1eadn";
        this.p.a.a((int)11).i().a = "Quay l\u1ea1i";
    }

    private void c(String string, String string2) {
        this.p.a.a((int)8).i().a = string;
        this.p.a.a((int)5).i().a = string2;
    }

    private void bF() {
        this.p.a("/data/ui/smsInfo.ui");
    }

    public final void aM() {
        if (!this.p.c("/data/ui/smsTip.ui")) {
            this.p.a("/data/ui/smsTip.ui", 257, this);
        }
        for (int i2 = 0; i2 < 3; ++i2) {
            this.p.a.a(i2 + 6).a(false);
        }
        this.g = true;
    }

    public final void d(String string) {
        this.g = true;
        this.p.a.a((int)5).i().a = string;
    }

    public final void aN() {
        this.p.a("/data/ui/smsTip.ui");
    }

    public final void aO() {
        switch (this.o.x()) {
            case 0: {
                if (this.o.g(16400) || this.o.g(32832)) break;
                if (this.o.g(131072)) {
                    if (this.o.y() > 1) {
                        this.o.c((byte)1);
                        return;
                    }
                    this.o.e(1);
                    return;
                }
                if (!this.o.g(786432)) break;
                this.bF();
                this.o.c((byte)5);
                this.o.a(this.o.b);
                return;
            }
            case 1: {
                if (this.o.g(131072)) {
                    this.o.f(1);
                    return;
                }
                if (!this.o.g(262144)) break;
                this.o.f(2);
                return;
            }
            case 2: {
                boolean bl = false;
                if (this.o.a == 100) {
                    if (this.w >= this.N.length && this.aA()) {
                        bl = true;
                    } else if (this.aA()) {
                        this.b(this.N[this.w]);
                        ++this.w;
                    }
                    this.f();
                } else {
                    bl = true;
                }
                if (!bl || !this.bC() || !this.o.g(917504)) break;
                this.w = 0;
                if (this.o.w()) {
                    this.bF();
                    this.aN();
                    this.o.a(this.o.b);
                } else {
                    this.o.c((byte)5);
                }
                this.f = 0;
                return;
            }
            case 3: {
                if (!this.o.g(393216)) break;
                this.o.f(1);
            }
        }
    }

    public final void a(byte by, int n2, int n3) {
        this.c = 0;
        this.Q = by;
        this.R = (byte)n2;
        switch (n2) {
            case 0: {
                this.p.a("/data/ui/wharf1.ui", 257, this);
                this.p.a.a((int)8).i().a = a.a.c(n3);
                for (n2 = 0; n2 < this.O[by].length; ++n2) {
                    this.p.a.a((int)(n2 + 5)).i().a = a.a.c(this.O[by][n2]);
                }
                break;
            }
            case 1: {
                this.p.a("/data/ui/wharf2.ui", 257, this);
                this.p.a.a((int)10).i().a = a.a.c(n3);
                for (n2 = 0; n2 < this.O[by].length; ++n2) {
                    this.p.a.a((int)(n2 + 5)).i().a = a.a.c(this.O[by][n2]);
                }
                break;
            }
        }
        this.p.a.a((int)(5 + this.O[by].length)).i().a = "Kh\u00f4ng ra h\u00e0ng";
    }

    public final void aP() {
        if (this.o.g(4100) && !this.j()) {
            this.p.a.b(0);
        } else if (this.o.g(8448) && !this.j()) {
            this.p.a.b(1);
        } else if (this.o.g(196640) && !this.j()) {
            if (this.c == this.P[this.Q].length / 9) {
                switch (this.R) {
                    case 0: {
                        this.p.a("/data/ui/wharf1.ui");
                        break;
                    }
                    case 1: {
                        this.p.a("/data/ui/wharf2.ui");
                    }
                }
                if (game.l.E != -1 && game.l.B().n[game.l.E].v() == 0) {
                    game.l.B().a((byte)13, game.l.B().n[game.l.E].j, game.l.B().n[game.l.E].k - 40, game.l.B().n[game.l.E]);
                }
                this.o.a((byte)0);
            } else if (game.l.B().Z.n[game.l.e(this.P[this.Q][this.c * 9 + 6], this.P[this.Q][this.c * 9 + 7])] != null && game.l.B().Z.n[game.l.e(this.P[this.Q][this.c * 9 + 6], this.P[this.Q][this.c * 9 + 7])][this.P[this.Q][this.c * 9 + 8]] == 3) {
                game.l.B().p = this.P[this.Q][this.c * 9];
                game.l.B().q = this.P[this.Q][this.c * 9 + 1];
                game.l.B().r = this.P[this.Q][this.c * 9 + 2];
                game.l.B().s = this.P[this.Q][this.c * 9 + 3];
                game.l.G = (byte)this.P[this.Q][this.c * 9 + 4];
                game.l.B().t = -1;
                game.b.B().d((byte)this.P[this.Q][this.c * 9 + 5]);
                this.o.a((byte)29);
                switch (this.R) {
                    case 0: {
                        this.p.a("/data/ui/wharf1.ui");
                        break;
                    }
                    case 1: {
                        this.p.a("/data/ui/wharf2.ui");
                    }
                }
            } else {
                this.b("\u0110\u01b0\u1eddng th\u1ee7y ch\u01b0a m\u1edf");
            }
        } else if (this.o.g(262144) && !this.j()) {
            if (game.l.E != -1 && game.l.B().n[game.l.E].v() == 0) {
                game.l.B().a((byte)13, game.l.B().n[game.l.E].j, game.l.B().n[game.l.E].k - 40, game.l.B().n[game.l.E]);
            }
            switch (this.R) {
                case 0: {
                    this.p.a("/data/ui/wharf1.ui");
                    break;
                }
                case 1: {
                    this.p.a("/data/ui/wharf2.ui");
                }
            }
            this.o.a((byte)0);
        }
        this.f();
    }

    public final void aQ() {
        this.b = 0;
        this.p.a("/data/ui/shopbuy.ui", 257, this);
        this.b = 0;
        this.f = 0;
        ((c.b)this.p.a.a((int)0)).a.a = 1;
        ((c.b)this.p.a.a((int)0)).a.a(0);
        this.p.a.a(41).a(false);
        this.p.a.a(43).a(false);
        this.p.a.a((int)5).i().a = "Mua";
        this.p.a.a(57).a(true);
        this.p.a.a(58).a(true);
        this.p.a.a((int)57).i().a = "Mua s\u1eafm";
        this.p.a.a((int)58).i().a = "Quay l\u1ea1i";
        this.p.a.a(39).a(false);
        this.p.a.a(40).a(false);
        k k2 = this;
        this.v = ((c.b)k2.p.a.a((int)0)).a.e;
        k2.h = ((c.b)k2.p.a.a((int)0)).a.f;
        if (k2.p.a.a((int)51).i().m == null) {
            k2.p.a.a((int)51).i().m = new c.g();
            k2.p.a.a((int)51).i().m.a(0);
            k2.p.a.a((int)51).i().m.a = (byte)2;
            k2.p.a.a((int)51).i().m.a(258, false, (byte)-1);
        }
        k2.p.a.a((int)51).i().m.a(a.b.c.c[5][0][1]);
        k2.p.a.a((int)14).i().a = a.a.c(a.b.c.c[5][0][0]);
        k2.p.a.a((int)15).i().a = "5000";
        k2.p.a.a((int)45).i().m.a(84);
        k2.p.a.a((int)56).i().a = "\u1ea4p tr\u1ee9ng ra s\u1ee7ng v\u1eadt";
        k2.p.a.a((int)44).i().a = "" + k2.q.F();
        k2.p.a.a(38).b(102 + k2.h * 84 / a.b.c.c[5].length, k2.p.a.a());
    }

    private void bG() {
        this.p.a("/data/ui/shopbuy.ui");
    }

    public final int aR() {
        if (this.o.g(196640)) {
            if (this.f == 0) {
                if (this.q.u(5000)) {
                    if (this.q.l(0)) {
                        this.H();
                        this.a("\u0110\u00e3 c\u00f3 tr\u01b0\u0301ng su\u0309ng v\u00e2\u0323t, kh\u00f4ng c\u1ea7n mua s\u1eafm", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                        this.f = 2;
                    } else {
                        this.q.e(0, -1);
                        this.H();
                        this.a("\u0110\u00e3 th\u00e0nh c\u00f4ng mua s\u1eafm #2 tr\u01b0\u0301ng su\u0309ng v\u00e2\u0323t", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                        this.f = 2;
                    }
                } else {
                    this.H();
                    this.a("Kim ti\u1ec1n ch\u01b0a \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                    this.f = 1;
                }
            } else if (this.f > 0) {
                this.I();
                if (this.f == 1) {
                    this.o.a((byte)102);
                } else if (this.f == 2) {
                    game.e.z = 0;
                    this.bG();
                    this.o.a((byte)0);
                }
            }
        } else if (this.o.g(262144) && this.f == 0) {
            game.e.z = 1;
            this.bG();
            this.o.a((byte)0);
        }
        return -1;
    }

    public final void aS() {
        this.p.a("/data/ui/wharf2.ui", 257, this);
        ((c.b)this.p.a.a((int)0)).a.f = this.d;
        this.f = 0;
        this.p.a.a((int)10).i().a = "Ti\u1ec7n l\u1ee3i \u0111i\u1ebfm";
        this.p.a.a((int)12).i().a = "Ti\u1ebfn v\u00e0o";
        for (int i2 = 0; i2 < this.S.length; ++i2) {
            this.p.a.a((int)(i2 + 5)).i().a = this.S[i2];
        }
    }

    public final void aT() {
        if (this.o.g(4100) && !this.j()) {
            this.p.a.b(0);
            return;
        }
        if (this.o.g(8448) && !this.j()) {
            this.p.a.b(1);
            return;
        }
        if (this.o.g(196640) && !this.j()) {
            if (this.f == 0) {
                switch (this.d) {
                    case 0: {
                        this.p.a("/data/ui/wharf2.ui");
                        this.o.a((byte)31);
                        return;
                    }
                    case 1: 
                    case 2: {
                        game.l.B();
                        if (game.l.U) {
                            this.p.a("/data/ui/wharf2.ui");
                            this.c = 0;
                            this.o.a((byte)7);
                            return;
                        }
                        this.H();
                        this.f = 1;
                        this.a("C\u00f4ng n\u0103ng theo \u0111\u1ea1o h\u1ecdc sau m\u1edf ra", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                        return;
                    }
                    case 3: {
                        this.p.a("/data/ui/wharf2.ui");
                        this.o.a((byte)32);
                        return;
                    }
                    case 4: {
                        this.p.a("/data/ui/wharf2.ui");
                        this.o.a((byte)0);
                    }
                }
                return;
            }
            this.f = 0;
            this.I();
            this.g = true;
            return;
        }
        if (this.o.g(262144) && this.f == 0 && !this.j()) {
            this.p.a("/data/ui/wharf2.ui");
            this.o.a((byte)0);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final void a(int[] nArray, int[] object) {
        this.y = nArray;
        if (this.o instanceof l) {
            switch (((l)this.o).a) {
                case 0: {
                    return;
                }
                case 1: {
                    int[] nArray2 = nArray;
                    object = this;
                    this.b = nArray2[0];
                    return;
                }
                case 2: 
                case 26: 
                case 32: {
                    this.c(nArray);
                    return;
                }
                case 3: {
                    int[] nArray3 = nArray;
                    object = this;
                    if (((k)object).f == 0) {
                        ((k)object).b = nArray3[0];
                        super.aZ();
                        return;
                    }
                    ((k)object).r = nArray3[0];
                    return;
                }
                case 4: {
                    return;
                }
                case 5: {
                    int[] nArray4 = nArray;
                    object = this;
                    this.b = nArray4[1];
                    super.bv();
                    return;
                }
                case 6: {
                    int[] nArray5 = nArray;
                    object = this;
                    this.b = nArray5[0];
                    if (a.a.i) {
                        ((k)object).p.a.a((int)14).i().a = a.a.c(605 + ((k)object).b);
                        return;
                    }
                    ((k)object).p.a.a((int)14).i().a = a.a.c(606 + ((k)object).b);
                    return;
                }
                case 7: {
                    this.b(nArray);
                    return;
                }
                case 8: {
                    int[] nArray6 = nArray;
                    object = this;
                    if (nArray6[0] >= 0) {
                        ((k)object).c = nArray6[0];
                    }
                    if (nArray6[1] >= 0) {
                        ((k)object).b = nArray6[1];
                    }
                    super.br();
                    return;
                }
                case 9: {
                    int[] nArray7 = nArray;
                    object = this;
                    this.c = nArray7[1];
                    return;
                }
                case 10: {
                    int[] nArray8 = nArray;
                    object = this;
                    this.b = nArray8[1];
                    switch (((k)object).b) {
                        case 0: {
                            ((k)object).c = nArray8[0];
                            return;
                        }
                        case 1: {
                            ((k)object).r = nArray8[0];
                        }
                        default: {
                            return;
                        }
                    }
                }
                case 11: {
                    int[] nArray9 = nArray;
                    object = this;
                    this.c = nArray9[0];
                    ((k)object).b = nArray9[1];
                    return;
                }
                case 12: {
                    int[] nArray10 = nArray;
                    object = this;
                    this.b = nArray10[1];
                    return;
                }
                case 13: {
                    if (this.f == 0) {
                        this.b = nArray[0];
                        return;
                    }
                    this.c = nArray[0];
                    return;
                }
                case 14: {
                    int[] nArray11 = nArray;
                    object = this;
                    this.c = nArray11[0];
                    return;
                }
                case 15: {
                    int[] nArray12 = nArray;
                    object = this;
                    this.c = nArray12[0];
                    return;
                }
                case 16: {
                    int[] nArray13 = nArray;
                    object = this;
                    this.b = nArray13[0];
                    return;
                }
                case 17: 
                case 18: 
                case 19: {
                    int[] nArray14 = nArray;
                    object = this;
                    this.c = nArray14[0];
                    return;
                }
                case 20: {
                    this.c = nArray[1];
                    return;
                }
                case 24: {
                    int[] nArray15 = nArray;
                    object = this;
                    this.c = nArray15[0];
                    return;
                }
                case 28: {
                    int[] nArray16 = nArray;
                    object = this;
                    this.c = nArray16[0];
                    return;
                }
                case 27: {
                    this.d = nArray[0];
                }
                default: {
                    return;
                }
            }
        }
        if (!(this.o instanceof a)) return;
        switch (((a)this.o).a) {
            case 2: {
                return;
            }
            case 3: {
                int[] nArray17 = nArray;
                object = this;
                this.e = nArray17[0];
                return;
            }
            case 4: {
                int[] nArray18 = nArray;
                object = this;
                this.b = nArray18[0];
                super.bi();
                return;
            }
            case 5: {
                this.b(nArray);
                return;
            }
            case 6: {
                return;
            }
            case 7: {
                return;
            }
            case 8: {
                return;
            }
            case 9: {
                return;
            }
            case 10: {
                return;
            }
            case 11: {
                this.c(nArray);
                return;
            }
            case 12: {
                return;
            }
            case 13: {
                return;
            }
            case 14: {
                return;
            }
            case 15: {
                return;
            }
            case 16: {
                int[] nArray19 = nArray;
                object = this;
                this.c = nArray19[0];
                super.g(((k)object).c);
                return;
            }
            case 17: {
                return;
            }
            case 18: {
                return;
            }
            case 19: {
                return;
            }
            case 20: {
                int[] nArray20 = nArray;
                object = this;
                this.a = nArray20[1];
                ((k)object).p.a.a(20 + ((k)object).a).a(true);
                return;
            }
            case 21: {
                int[] nArray21 = nArray;
                object = this;
                this.b = nArray21[0];
            }
            case 23: {
                int[] nArray21 = nArray;
                object = this;
                this.b = nArray21[0];
            }
        }
    }

    private void b(int[] nArray) {
        if (this.f == 0) {
            this.b = nArray[0];
            this.g(this.b);
            return;
        }
        if (this.f == 1) {
            this.c = nArray[0];
            return;
        }
        if (this.f == 2) {
            this.r = nArray[0];
            switch (this.c) {
                case 0: {
                    this.bi();
                }
            }
        }
    }

    private void c(int[] nArray) {
        if (this.f == 0) {
            this.b = nArray[0];
            return;
        }
        this.r = nArray[0];
    }

    static {
        z = new String[]{"Th\u1ee7y Kimura", "B\u00edch Th\u1ee7y th\u00e0nh", "Nguy\u00ean M\u1ed9c Th\u00e0nh", "Ni\u00eam Th\u1ed5 Th\u00e0nh", "H\u1eafc Th\u1ea1ch th\u00e0nh", "Thi\u00ean kh\u00f4ng", "Xa c\u1ed5"};
        A = new short[]{1, 0, 196, 208, 0, 2, 1, 196, 208, 0, 3, 3, 196, 208, 0, 4, 5, 320, 352, 0, 5, 3, 320, 196, 0, 7, 2, 288, 112, 0, 8, 0, 160, 144, 0};
    }
}

