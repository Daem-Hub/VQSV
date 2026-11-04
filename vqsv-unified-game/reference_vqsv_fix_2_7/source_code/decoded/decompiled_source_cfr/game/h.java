/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
package game;

import a.a.g;
import a.b.a;
import a.b.d;
import game.e;
import game.f;
import game.j;
import game.l;
import javax.microedition.lcdui.Graphics;

public final class h
extends g {
    public byte u;
    public boolean v;
    public byte w;
    public byte x;
    public short y;
    public short z;
    private int K;
    private int L;
    private int M;
    private int N;
    public byte A;
    public int B;
    public int C;
    public byte D;
    private short O;
    private short P;
    private short Q;
    public short E;
    public short F;
    public short G;
    private boolean R;
    private byte[] S = new byte[]{2, 3, 0, 1};
    private byte T = 0;
    public g H;
    public g I;
    public short J = (short)-1;
    private short[] U = new short[]{8, 9, 2, 96, 320, 0};

    public final void a(short[] sArray, int n2) {
        g g2;
        this.J = (short)n2;
        this.u = (byte)sArray[0];
        this.a.a((int)sArray[1], false);
        this.a.c();
        this.w = (byte)sArray[6];
        if (this.u == 0 && (this.w == 1 || this.w == 18)) {
            byte by = (byte)(sArray[2] % 3);
            g2 = this;
            this.o = by;
        }
        this.a((byte)sArray[2]);
        this.j = sArray[3];
        this.k = sArray[4];
        if (sArray[5] == 1) {
            this.c(true);
        } else {
            this.c(false);
        }
        switch (this.u) {
            case 0: {
                this.t = (byte)sArray[7];
                this.x = (byte)sArray[8];
                h h2 = this;
                if (h2.x != 0 && h2.H == null && h2.k()) {
                    h2.H = new g();
                    h2.H.a(259, false);
                    g2 = h2.H;
                    g2.a.c();
                    h2.H.a(h2.x, (byte)-1, true);
                    h2.H.b(h2.j, h2.k - 40);
                    h h3 = h2;
                    g2 = h2.H;
                    h2.H.q = h3;
                }
                h2.B();
                this.w();
                this.v = sArray[9] != 0;
                this.y = sArray[11];
                this.z = sArray[12];
                this.K = 0;
                this.M = a.e.b(20, 40);
                this.A = 0;
                if (this.w == 12) {
                    this.o = 0;
                } else if (this.w == 13) {
                    this.o = 1;
                }
                if (this.w == 3) {
                    if (this.i == 4) {
                        this.o = 1;
                    }
                } else if (this.w == 2) {
                    if (this.i == 5) {
                        this.o = (byte)2;
                    } else if (this.i == 3) {
                        this.o = 0;
                    }
                }
                if ((this.w != 1 || this.a.a == 226) && this.w != 2 && this.w != 3 && this.w != 17) break;
                if (this.b == null) {
                    this.b = new g();
                    this.b.a(337, false);
                }
                this.b.b(this.j, this.k);
                if (this.a.a == 4) {
                    this.b.a((byte)0, (byte)0, this.R);
                } else {
                    this.b.a((byte)1, (byte)0, this.R);
                }
                this.b.c();
                break;
            }
            case 1: {
                this.t = sArray[1] == 320 ? 2 : 1;
                if (this.k() && sArray[0] > 0 && sArray[0] <= 3) {
                    game.l.B().o.addElement(this);
                }
                if (this.w == 3) {
                    this.v = true;
                }
                this.D = (byte)sArray[7];
                this.O = sArray[8];
                this.P = sArray[9];
                this.Q = sArray[10];
                break;
            }
            case 2: {
                if (sArray[7] == 0) {
                    this.R = false;
                    break;
                }
                this.R = true;
                break;
            }
            case 3: {
                this.t = 1;
                this.E = sArray[7];
                this.F = sArray[8];
                switch (this.F) {
                    case 9: {
                        this.F = 1;
                        break;
                    }
                    case 10: {
                        this.F = 0;
                        break;
                    }
                    case 11: {
                        this.F = (short)2;
                        break;
                    }
                    case 12: {
                        this.F = (short)3;
                    }
                }
                this.G = sArray[9];
                this.v = true;
            }
        }
        this.d = new short[3];
        this.e = new short[3];
    }

    public final void p() {
        if (this.a != null) {
            this.a.d();
        }
    }

    public final void a(byte by) {
        switch (this.u) {
            case 0: {
                if (this.w == 8) {
                    this.a((byte)0, (byte)-1, false);
                    this.K = 0;
                    this.i = by;
                    return;
                }
                if (this.w == 1 || this.w == 18) {
                    this.i = (byte)(by / 3);
                    if (this.i == 0) {
                        if (this.o == 3) {
                            this.a((byte)1, (byte)-1, false);
                            return;
                        }
                        this.a(this.o, (byte)-1, false);
                        return;
                    }
                    if (this.i != 1) break;
                    if (this.o == 3) {
                        this.a((byte)(this.i * 3 + 1), (byte)-1, false);
                        return;
                    }
                    this.a((byte)(this.i * 3 + this.o), (byte)-1, false);
                    return;
                }
                this.a(by, (byte)-1, false);
                this.i = by;
                game.l.B().a((int)this.J, 0, this.i, true);
                return;
            }
            case 1: {
                if (this.w == 0) {
                    switch (by) {
                        case 0: {
                            this.a(by, (byte)-1, false);
                            break;
                        }
                        case 1: {
                            this.a(by, (byte)-2, false);
                            break;
                        }
                        case 2: {
                            this.a(by, (byte)-1, false);
                            break;
                        }
                        case 3: {
                            this.a(by, (byte)-2, false);
                        }
                    }
                } else {
                    this.a(by, (byte)-1, false);
                    if (this.w == 3) {
                        game.l.B().a((int)this.J, 0, by, true);
                    }
                }
                this.i = by;
                return;
            }
            case 2: {
                this.i = by;
                return;
            }
            case 3: {
                this.a(by, (byte)-2, false);
                this.i = by;
                game.l.B().a((int)this.J, 0, this.i, true);
            }
        }
    }

    public final void q() {
        switch (this.u) {
            case 0: {
                this.z();
                break;
            }
            case 1: {
                if (this.w == 0) {
                    if (this.i == 0 && a.e.a(game.j.p().j, game.j.p().k, this.j, this.k, game.j.p().a.k(), this.a.k())) {
                        this.a((byte)1);
                    } else if (this.i == 2 && !a.e.a(game.j.p().j, game.j.p().k, this.j, this.k, game.j.p().a.k(), this.a.k())) {
                        this.a((byte)3);
                    } else if (this.i == 1 && this.a.f()) {
                        this.a((byte)2);
                    } else if (this.i == 3 && this.a.f()) {
                        this.a((byte)0);
                    }
                }
                if (this.w == 0 && this.a.f() || this.w == 1 || this.w == 3 && this.i() == 2) {
                    if (this.a.a == 320 && !this.k()) {
                        return;
                    }
                    if ((game.j.p().p != this.S[this.D] || this.a.a == 320 || this.a.a == 310) && this.a.a != 320 && this.a.a != 310 || !a.e.a(game.j.p().j, game.j.p().k, this.j, this.k, game.j.p().a.k(), this.a.j())) break;
                    game.l.B().p = this.O;
                    game.l.B().q = this.P;
                    game.l.B().t = this.Q;
                    game.f.B().a((byte)9);
                    break;
                }
                if (this.w == 2) {
                    if ((game.j.p().p != this.S[this.D] || this.a.a == 320) && this.a.a != 320 || !a.e.a(game.j.p().j, game.j.p().k, this.j, this.k, game.j.p().a.k(), this.a.j())) break;
                    for (int i2 = 0; i2 < this.U.length / 6; ++i2) {
                        if (this.U[i2 * 6] != this.J || this.U[i2 * 6 + 1] != game.l.B().p || this.U[i2 * 6 + 2] != game.l.B().q) continue;
                        game.l.B().r = this.U[i2 * 6 + 3];
                        game.l.B().s = this.U[i2 * 6 + 4];
                        game.l.G = (byte)this.U[i2 * 6 + 5];
                        break;
                    }
                    game.l.B().p = this.O;
                    game.l.B().q = this.P;
                    game.l.B().t = -1;
                    game.f.B().a((byte)9);
                    break;
                }
                if (this.w != 4 || game.j.p().i() == 9 || game.j.p().i() == 10 || !a.e.a(game.j.p().j, game.j.p().k, this.j, this.k, game.j.p().a.k(), this.a.j())) break;
                game.j.p().b(this.j, this.k);
                game.j.p().b.b(this.j, this.k);
                game.j.p().a((byte)9, this.o);
                game.l.B().t = this.Q;
            }
        }
        this.f();
    }

    private void z() {
        switch (this.w) {
            case 2: {
                if (this.A()) {
                    Object object = new byte[]{0, 1, 2, 3, 5};
                    this.a(object[a.e.a(5)]);
                    if (this.i == 3 || this.i == 0) {
                        byte by = 0;
                        object = this;
                        this.o = by;
                    } else if (this.i == 5 || this.i == 2) {
                        int n2 = 2;
                        object = this;
                        this.o = (byte)n2;
                    } else if (a.e.a(2) == 0) {
                        int n3 = 3;
                        object = this;
                        this.o = (byte)n3;
                    } else {
                        byte by = 1;
                        object = this;
                        this.o = by;
                    }
                }
                if (this.i == 3) {
                    if (this.K >= 64) {
                        this.a((byte)0);
                        return;
                    }
                    this.a(4);
                    this.K += 4;
                    return;
                }
                if (this.i != 5) break;
                if (this.K <= 0) {
                    this.a((byte)2);
                    return;
                }
                this.a(4);
                this.K -= 4;
                return;
            }
            case 3: {
                if (this.A()) {
                    Object object = new byte[]{0, 1, 2, 4};
                    this.a(object[a.e.a(4)]);
                    if (this.i == 0) {
                        byte by = 0;
                        object = this;
                        this.o = by;
                    } else if (this.i == 2) {
                        int n4 = 2;
                        object = this;
                        this.o = (byte)n4;
                    } else if (a.e.a(2) == 0) {
                        int n5 = 3;
                        object = this;
                        this.o = (byte)n5;
                    } else {
                        byte by = 1;
                        object = this;
                        this.o = by;
                    }
                }
                if (this.i != 4) break;
                if (this.o == 1) {
                    if (this.K >= 64) {
                        this.a((byte)0);
                        return;
                    }
                    this.a(4);
                    this.K += 4;
                    return;
                }
                if (this.o != 3) break;
                if (this.K <= 0) {
                    this.a((byte)2);
                    return;
                }
                this.a(4);
                this.K -= 4;
                return;
            }
            case 11: {
                return;
            }
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 15: {
                int n6;
                if (this.I != null && this.k() && this.I.q.equals(this) && (this.i != 0 || !game.j.p().a(this, game.j.p().a.k(), this.a.k()))) {
                    this.y();
                }
                if (this.i != 1 || !this.a.f()) break;
                this.a((byte)2);
                if (this.w == 6 && this.w == 7) {
                    game.l.B().a((int)this.J, 0, this.i, false);
                } else {
                    game.l.B().a((int)this.J, 0, this.i, true);
                }
                if ((this.w == 7 || this.w == 6) && (n6 = a.e.a(2)) > 0) {
                    game.j.p().s(n6);
                    int[] nArray = new int[4];
                    nArray[0] = n6;
                    Object object = this;
                    nArray[1] = ((a)object).j;
                    object = this;
                    nArray[2] = ((a)object).k - 20;
                    nArray[3] = 0;
                    object = nArray;
                    game.j.p().W.addElement(object);
                }
                game.e.t = false;
                return;
            }
            case 8: {
                if (this.i != 1) break;
                if (this.K < 2 && this.a(this.o, 8, (byte)0)) {
                    ++this.K;
                    this.a(8);
                    this.t();
                    return;
                }
                this.a((byte)0);
                return;
            }
            case 9: 
            case 10: {
                if (this.A != 1) break;
                if ((this.a.a == 302 || this.a.a == 298) && this.a(this.o, 4, (byte)1)) {
                    this.a(4);
                    ((g)this.q).a(4);
                    return;
                }
                if (this.a(this.o, 4, (byte)2)) {
                    this.a(4);
                    ((g)this.q).a(4);
                    return;
                }
                this.t();
                this.A = (byte)2;
                this.a((byte)0);
                return;
            }
            case 12: {
                if (!this.k()) break;
                if (a.e.a(game.j.p().j, game.j.p().k, this.j, this.k, this.a.k())) {
                    if (game.j.p().i() == 8) break;
                    game.j.p().a((byte)8, this.o);
                    return;
                }
                if (this.a(this.o, 4, (byte)0)) {
                    this.a(4);
                    return;
                }
                if (this.o == 2) {
                    this.o = 0;
                    return;
                }
                this.o = (byte)2;
                return;
            }
            case 13: {
                if (!this.k()) break;
                if (a.e.a(game.j.p().j, game.j.p().k, this.j, this.k, this.a.k())) {
                    if (game.j.p().i() == 8) break;
                    game.j.p().a((byte)8, this.o);
                    return;
                }
                if (this.a(this.o, 4, (byte)0)) {
                    this.a(4);
                    return;
                }
                if (this.o == 3) {
                    this.o = 1;
                    return;
                }
                this.o = (byte)3;
                return;
            }
            case 16: {
                if (!a.e.a(game.j.p().j, game.j.p().k, this.j, this.k, this.a.k()) || game.j.p().i() == 5) break;
                game.j.p().a((byte)5, game.j.p().o);
                return;
            }
            case 14: {
                this.s();
                if (this.N < 4) {
                    ++this.N;
                    return;
                }
                this.N = 0;
                return;
            }
            case 1: {
                h h2;
                if (this.i == 1) {
                    int n7 = 0;
                    h2 = this;
                    this.a(h2.e[n7]);
                    if (game.l.B().z != null && game.l.B().z.q.equals(this) && !game.j.p().a(this, game.j.p().a.k(), this.a.k())) {
                        game.l.B().D();
                    }
                }
                if (!(game.l.B().z == null || !game.l.B().z.q.equals(this) || this.k() && game.j.p().a(this, game.j.p().a.k(), this.a.k()))) {
                    game.l.E = (short)-1;
                    game.l.B().D();
                }
                if (this.H != null && this.k() && this.H.q.equals(this) && !game.j.p().a(this, game.j.p().a.k(), this.a.k())) {
                    this.B();
                }
                if (game.e.p == null || game.e.p.size() <= 0) break;
                h2 = this;
                if (h2.T != 1 || game.j.p().a(this, game.j.p().a.k(), this.a.k())) break;
                for (int i2 = 0; i2 < game.e.p.size(); ++i2) {
                    if (!((g)game.e.p.elementAt((int)i2)).q.equals(this)) continue;
                    ((g)game.e.p.elementAt(i2)).c();
                    return;
                }
                break;
            }
        }
    }

    public final void r() {
        if (this.w == 9) {
            this.a((byte)1);
            if ((this.a.a == 302 || this.a.a == 298) && this.a((byte)1, 4, (byte)1)) {
                byte by = 1;
                h h2 = this;
                this.o = by;
            } else if (this.a((byte)1, 4, (byte)2)) {
                byte by = 1;
                h h3 = this;
                this.o = by;
            } else {
                int n2 = 3;
                h h4 = this;
                this.o = (byte)n2;
            }
        } else if ((this.a.a == 302 || this.a.a == 298) && this.a((byte)2, 4, (byte)1)) {
            this.a((byte)2);
            int n3 = 2;
            h h5 = this;
            this.o = (byte)n3;
        } else if (this.a((byte)2, 4, (byte)2)) {
            this.a((byte)2);
            int n4 = 2;
            h h6 = this;
            this.o = (byte)n4;
        } else {
            this.a((byte)1);
            byte by = 0;
            h h7 = this;
            this.o = by;
        }
        this.A = 1;
    }

    /*
     * Enabled aggressive block sorting
     */
    public final void s() {
        this.B = 0;
        while (true) {
            h h2 = this;
            boolean bl = false;
            int n2 = 16 * (this.B + 1);
            int n3 = h2.a.g();
            h2 = this;
            byte by = 0;
            switch (n3) {
                case 2: {
                    by = a.b.d.a().a(0, h2.j, h2.k - n2);
                    for (n3 = 0; n3 < game.l.B().n.length; ++n3) {
                        if (game.l.B().n[n3].w == h2.w || game.l.B().n[n3].a.k() == null || !a.e.a(h2.j, h2.k - n2, game.l.B().n[n3].j, game.l.B().n[n3].k, game.l.B().n[n3].a.k())) continue;
                        h h3 = h2;
                        h2 = game.l.B().n[n3];
                        game.l.B().n[n3].q = h3;
                        return;
                    }
                    break;
                }
                case 0: {
                    by = a.b.d.a().a(0, h2.j, h2.k + n2);
                    for (n3 = 0; n3 < game.l.B().n.length; ++n3) {
                        if (game.l.B().n[n3].w == h2.w || game.l.B().n[n3].a.k() == null || !a.e.a(h2.j, h2.k + n2, game.l.B().n[n3].j, game.l.B().n[n3].k, game.l.B().n[n3].a.k())) continue;
                        h h4 = h2;
                        h2 = game.l.B().n[n3];
                        game.l.B().n[n3].q = h4;
                        return;
                    }
                    break;
                }
                case 3: {
                    by = a.b.d.a().a(0, h2.j - n2, h2.k);
                    for (n3 = 0; n3 < game.l.B().n.length; ++n3) {
                        if (game.l.B().n[n3].w == h2.w || game.l.B().n[n3].a.k() == null || !a.e.a(h2.j - n2, h2.k, game.l.B().n[n3].j, game.l.B().n[n3].k, game.l.B().n[n3].a.k())) continue;
                        h h5 = h2;
                        h2 = game.l.B().n[n3];
                        game.l.B().n[n3].q = h5;
                        return;
                    }
                    break;
                }
                case 1: {
                    by = a.b.d.a().a(0, h2.j + n2, h2.k);
                    for (n3 = 0; n3 < game.l.B().n.length; ++n3) {
                        if (game.l.B().n[n3].w == h2.w || game.l.B().n[n3].a.k() == null || !a.e.a(h2.j + n2, h2.k, game.l.B().n[n3].j, game.l.B().n[n3].k, game.l.B().n[n3].a.k())) continue;
                        h h6 = h2;
                        h2 = game.l.B().n[n3];
                        game.l.B().n[n3].q = h6;
                        return;
                    }
                }
            }
            if (by != 0) {
                return;
            }
            boolean bl2 = true;
            if (!bl2) {
                return;
            }
            ++this.B;
        }
    }

    private boolean A() {
        ++this.L;
        if (this.L >= this.M) {
            this.L = 0;
            this.M = a.e.b(20, 40);
            return true;
        }
        return false;
    }

    private boolean a(byte by, int n2, byte by2) {
        byte by3 = 0;
        switch (by) {
            case 2: {
                by3 = a.b.d.a().a(0, this.j, this.k - this.a.k()[3] - n2);
                break;
            }
            case 0: {
                by3 = a.b.d.a().a(0, this.j, this.k + n2);
                break;
            }
            case 3: {
                by3 = a.b.d.a().a(0, this.j - n2 - this.a.k()[2] / 2, this.k);
                break;
            }
            case 1: {
                by3 = a.b.d.a().a(0, this.j + n2 + this.a.k()[2] / 2, this.k);
            }
        }
        return by3 == by2;
    }

    public final void c(Graphics graphics, int n2, int n3) {
        h h2 = this;
        switch (h2.a.g()) {
            case 2: {
                graphics.setColor(65280);
                graphics.fillRect(this.j - n2 - (this.N + 5) / 2, this.k - this.a.j()[3] - n3 - (this.B << 4) + 8, this.N + 5, this.B << 4);
                graphics.setColor(0xFFFFFF);
                graphics.fillRect(this.j - n2 - (this.N + 3) / 2, this.k - this.a.j()[3] - n3 - (this.B << 4) + 8, this.N + 3, this.B << 4);
                return;
            }
            case 0: {
                graphics.setColor(65280);
                graphics.fillRect(this.j - n2 - (this.N + 5) / 2, this.k - this.a.j()[3] - n3 + 20, this.N + 5, this.B + 1 << 4);
                graphics.setColor(0xFFFFFF);
                graphics.fillRect(this.j - n2 - (this.N + 3) / 2, this.k - this.a.j()[3] - n3 + 20, this.N + 3, this.B + 1 << 4);
                return;
            }
            case 3: {
                graphics.setColor(65280);
                graphics.fillRect(this.j - n2 - 8 - (this.B << 4), this.k - this.a.j()[3] - n3 - (this.N + 5) / 2 + 13, this.B << 4, this.N + 5);
                graphics.setColor(0xFFFFFF);
                graphics.fillRect(this.j - n2 - 8 - (this.B << 4), this.k - this.a.j()[3] - n3 - (this.N + 3) / 2 + 13, this.B << 4, this.N + 3);
                return;
            }
            case 1: {
                graphics.setColor(65280);
                graphics.fillRect(this.j - n2 + 7, this.k - this.a.j()[3] - n3 - (this.N + 5) / 2 + 13, this.B << 4, this.N + 5);
                graphics.setColor(0xFFFFFF);
                graphics.fillRect(this.j - n2 + 7, this.k - this.a.j()[3] - n3 - (this.N + 3) / 2 + 13, this.B << 4, this.N + 3);
            }
        }
    }

    public final void t() {
        game.l.B().a((int)this.J, 0, this.j);
        game.l.B().a((int)this.J, 1, this.k);
    }

    public final void u() {
        byte by = 0;
        if (this.k()) {
            by = 1;
        }
        game.l.B().a((int)this.J, 1, by, true);
        game.l.B().a((int)this.J, 0, this.i, true);
        game.l.B().a((int)this.J, 2, this.o, true);
    }

    public final void a(int n2) {
        super.a(n2);
    }

    public final void f(byte by) {
        this.T = 1;
    }

    public final byte v() {
        return this.T;
    }

    public final void f(int n2) {
        if (this.I == null && this.k()) {
            this.I = new g();
            this.I.a(259, false);
            g g2 = this.I;
            g2.a.c();
            this.I.a((byte)7, (byte)-1, true);
            this.I.b(this.j, this.k - n2);
            h h2 = this;
            g2 = this.I;
            this.I.q = h2;
        }
        h h3 = this;
        if (h3.I != null) {
            h3.I.c();
        }
    }

    public final void w() {
        if (game.l.B().n(this.J)) {
            this.x();
        }
    }

    private void B() {
        if (this.H != null) {
            this.H.c();
        }
    }

    public final void d() {
        super.d();
        this.x();
        this.e();
        this.y();
    }

    public final void x() {
        if (this.H != null) {
            this.H.d();
        }
    }

    public final void y() {
        if (this.I != null) {
            this.I.d();
        }
    }

    public final void b(int n2, int n3) {
        super.b(n2, n3);
        if (this.b != null && this.b.k()) {
            this.b.b(n2, n3);
        }
    }
}

