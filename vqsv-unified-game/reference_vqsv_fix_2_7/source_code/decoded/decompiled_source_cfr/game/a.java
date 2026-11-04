/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
package game;

import a.a.b;
import a.a.g;
import a.b.c;
import a.e;
import game.f;
import game.i;
import game.j;
import game.k;
import game.l;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class a
extends a.a {
    private final byte[] y = new byte[]{2, 4};
    private static a z;
    private static j A;
    private byte B = 0;
    public int k;
    public byte l;
    private byte C;
    public Image m;
    private int[][] D;
    public i[] n;
    private byte[] E;
    public byte[] o;
    public byte[] p;
    private byte[] F;
    public byte q = 0;
    public i r;
    private Vector G;
    public byte s;
    private boolean H;
    private static Vector I;
    public static Vector t;
    private boolean J;
    public boolean u;
    private boolean K;
    private boolean L;
    private boolean M;
    private byte N;
    private byte[] O;
    private byte[] P;
    private byte Q;
    private int R;
    private b S;
    private byte T = 0;
    private byte U = 0;
    private byte V = 0;
    private byte W = 0;
    private boolean X;
    private boolean Y;
    private byte[] Z;
    private int[] aa;
    private boolean ab;
    private byte ac;
    private byte ad;
    private byte ae;
    private byte[] af;
    private byte[][] ag;
    private byte[] ah;
    private byte[] ai;
    private byte[][] aj = new byte[][]{{3, 5, 13}, {0, 1, 2, 3, 8, 9, 10}};
    private g ak;
    private boolean al;
    public static byte v;
    private g[] am;
    private static short[][] an;
    private static short[][][] ao;
    public static byte[][][] w;
    private static byte[][] ap;
    public static short[][] x;
    private static byte[][] aq;
    private static byte[][] ar;
    private static byte[][] as;
    private int at;
    private int au;
    private int av;
    private int aw;
    private int ax;
    private int ay;
    private String az = null;
    private static Image[] aA;
    private static short[][] aB;
    private Vector aC = new Vector();
    private Vector aD = new Vector();
    private int aE;
    private boolean aF;
    private int aG;
    private byte[] aH;
    private byte[] aI;
    private byte[] aJ;

    public static a B() {
        if (z == null) {
            z = new a();
        }
        return z;
    }

    public a() {
        new Vector();
        new Vector();
        this.aE = 0;
        this.aF = false;
        this.aG = 0;
        this.aH = new byte[]{10, 11, 12, 13, 15};
        this.aI = new byte[]{10, 12, 13, 14, 15, 16};
        this.aJ = new byte[]{105, 100, 80, 60, 40, 20, 5};
        if (this.G == null) {
            this.G = new Vector();
        }
        if (I == null) {
            I = new Vector();
        }
        if (t == null) {
            t = new Vector();
        }
    }

    public final void c() {
        int n2;
        this.G.removeAllElements();
        for (n2 = 0; n2 < this.p.length; ++n2) {
            this.n(n2).D();
            this.n(n2).E();
            this.n((int)n2).G = 0;
            this.n(n2).e(false);
            this.n((int)n2).H.removeAllElements();
            this.n((int)n2).I.removeAllElements();
            int n3 = 1;
            i i2 = this.n(n2);
            this.n(n2).u(i2.e[n3]);
        }
        I.removeAllElements();
        t.removeAllElements();
        for (n2 = 0; n2 < this.n.length; ++n2) {
            if (this.n[n2] == null) continue;
            this.n[n2].d();
            this.n[n2] = null;
        }
        for (n2 = 0; n2 < aA.length; ++n2) {
            if (aA[n2] == null) continue;
            game.a.aA[n2] = null;
        }
        aA = null;
        if (this.ak != null) {
            this.ak.a.b();
            this.ak = null;
        }
        if (this.am != null) {
            for (n2 = 0; n2 < this.am.length; ++n2) {
                this.am[n2].a.b();
                this.am[n2] = null;
            }
            this.am = null;
        }
        this.F = null;
        this.s = 0;
        this.R = 0;
        this.r = null;
        this.H = false;
        this.J = false;
        this.S = null;
        this.n = null;
        this.m = null;
        this.E = null;
        this.o = null;
        this.O = null;
        this.P = null;
        this.af = null;
        this.Z = null;
        aq = null;
        ar = null;
        game.l.B = 0;
        an = null;
        ao = null;
        w = null;
        ap = null;
        x = null;
        aB = null;
        aq = null;
        ar = null;
        as = null;
        this.d.ai();
    }

    public final boolean b() {
        int n2;
        int n3;
        this.d();
        this.F = new byte[2];
        A = game.j.p();
        this.p = new byte[game.a.A.B];
        int n4 = 0;
        for (n3 = 0; n3 < game.a.A.B; ++n3) {
            this.p[n3] = (byte)n3;
            if (this.n(n3) != null && this.n(n3).T()) {
                ++n4;
            }
            this.n(n3).j(this.n(n3).A());
        }
        this.n = n4 == 1 && this.k == 1 ? new i[3] : new i[this.y[this.k]];
        an = a.e.a(a.e.a("/data/script/pos.mid"));
        InputStream inputStream = a.e.a("/data/script/cpos.mid");
        ao = new short[3][][];
        for (n3 = 0; n3 < 3; ++n3) {
            game.a.ao[n3] = a.e.a(inputStream);
        }
        inputStream = a.e.a("/data/script/layer.mid");
        w = new byte[15][][];
        for (n3 = 0; n3 < w.length; ++n3) {
            game.a.w[n3] = a.e.b(inputStream);
        }
        ap = a.e.b(a.e.a("/data/script/effect.mid"));
        x = a.e.a(a.e.a("/data/script/speffect.mid"));
        aB = a.e.a(a.e.a("/data/script/blood.mid"));
        inputStream = a.e.a("/data/script/bufDebuf.mid");
        aq = a.e.b(inputStream);
        ar = a.e.b(inputStream);
        as = a.e.b(inputStream);
        this.C = this.k == 0 ? (this.l == 1 ? (byte)2 : (byte)0) : (byte)1;
        this.am = new g[this.n.length + 2];
        for (n3 = 0; n3 < this.am.length; ++n3) {
            this.am[n3] = new g();
            this.am[n3].a(294, false);
            if (n3 == this.n.length + 1) {
                this.am[n3].a((byte)2, (byte)-1, false);
                if (this.k != 0) continue;
                this.am[n3].c();
                continue;
            }
            if (n3 == this.n.length) {
                this.am[n3].a((byte)1, (byte)-1, false);
                this.am[n3].c();
                this.am[n3].c(false);
                continue;
            }
            this.am[n3].a((byte)0, (byte)-1, false);
            this.am[n3].b(ao[this.C][n3][2], (int)ao[this.C][n3][3]);
            this.am[n3].c();
        }
        this.O = new byte[this.n.length];
        this.P = new byte[this.n.length];
        this.r(this.n.length);
        n3 = 0;
        block12: for (n2 = 0; n2 < this.n.length; ++n2) {
            if (this.k == 0) {
                if (n2 > 0) {
                    while (!this.n(this.p[n3]).T()) {
                        ++n3;
                    }
                    this.e(n2, n3);
                    I.addElement(this.n(this.p[n3]));
                    this.f(0, n3);
                    continue;
                }
                switch (this.l) {
                    case 0: 
                    case 1: {
                        this.q(n2);
                        break;
                    }
                    case 2: {
                        this.q(n2);
                    }
                }
                continue;
            }
            if (n2 > 1) {
                while (!this.n(this.p[n3]).T()) {
                    ++n3;
                }
                this.e(n2, n3);
                I.addElement(this.n(this.p[n3]));
                this.f(n2 - 2, n3);
                ++n3;
                continue;
            }
            switch (this.l) {
                case 0: 
                case 1: {
                    this.q(n2);
                    continue block12;
                }
                case 2: {
                    this.q(n2);
                }
            }
        }
        this.Q();
        aA = new Image[3];
        for (n2 = 0; n2 < aA.length; ++n2) {
            game.a.aA[n2] = a.e.b("/data/tex/", "blood_" + n2);
        }
        for (n2 = 0; n2 < game.a.A.B; ++n2) {
            int n5 = 1;
            i i2 = game.a.A.A[n2];
            game.a.A.A[n2].B = i2.e[n5];
        }
        this.a((byte)0);
        game.a.e();
        return true;
    }

    public final void C() {
        int n2;
        int n3 = 0;
        for (int i2 = 0; i2 < game.a.A.B; ++i2) {
            if (this.n(i2) != null && this.n(i2).T()) {
                ++n3;
            }
            this.n(i2).j(this.n(i2).A());
        }
        i[] iArray = this.k == 0 ? new i[1] : new i[2];
        for (n2 = 0; n2 < this.n.length; n2 = (int)((byte)(n2 + 1))) {
            if (this.n[n2].s() != 1) continue;
            iArray[n2] = this.n[n2];
        }
        this.n = n3 == 1 && this.k == 1 ? new i[3] : new i[this.y[this.k]];
        this.am = new g[this.n.length + 2];
        for (n2 = 0; n2 < this.am.length; ++n2) {
            this.am[n2] = new g();
            this.am[n2].a(294, false);
            if (n2 == this.n.length + 1) {
                this.am[n2].a((byte)2, (byte)-1, false);
                if (this.k != 0) continue;
                this.am[n2].c();
                continue;
            }
            if (n2 == this.n.length) {
                this.am[n2].a((byte)1, (byte)-1, false);
                this.am[n2].c();
                this.am[n2].c(false);
                continue;
            }
            this.am[n2].a((byte)0, (byte)-1, false);
            this.am[n2].b(ao[this.C][n2][2], (int)ao[this.C][n2][3]);
            this.am[n2].c();
        }
        this.O = new byte[this.n.length];
        this.P = new byte[this.n.length];
        this.R = 0;
        this.r(this.n.length);
        n2 = 0;
        for (n3 = 0; n3 < this.n.length; ++n3) {
            if (this.k == 0) {
                if (n3 > 0) {
                    while (!this.n(this.p[n2]).T()) {
                        ++n2;
                    }
                    this.e(n3, n2);
                    I.addElement(this.n(this.p[n2]));
                    this.f(0, n2);
                    continue;
                }
                this.n[n3] = iArray[n3];
                continue;
            }
            if (n3 > 1) {
                while (!this.n(this.p[n2]).T()) {
                    ++n2;
                }
                this.e(n3, n2);
                I.addElement(this.n(this.p[n2]));
                this.f(n3 - 2, n2);
                ++n2;
                continue;
            }
            this.n[n3] = iArray[n3];
        }
        this.Q();
    }

    public final void D() {
        if (this.k == 1) {
            int n2 = 0;
            while (((i)this.G.elementAt(n2)).s() != 0 || ((i)this.G.elementAt(n2)).s() == 0 && !((i)this.G.elementAt(n2)).T()) {
                ++n2;
            }
            if (this.n[0].T()) {
                this.d.b(this.n[this.o[n2]], this.n[0]);
                return;
            }
            this.d.b(this.n[this.o[n2]], this.n[1]);
        }
    }

    public final void E() {
        this.d = game.k.a();
        this.d.a(this);
        this.c = c.j.a();
        if (this.k == 0) {
            this.d.a(this.n[1], this.n[0]);
            return;
        }
        this.d.a(this.n[2], this.n[0]);
    }

    public final void e(int n2, int n3) {
        this.n[n2] = this.n(this.p[n3]);
        this.n[n2].e(true);
        this.n[n2].f(0);
        byte by = 0;
        i i2 = this.n[n2];
        this.n[n2].o = by;
        this.n[n2].b(ao[this.C][n2][0], (int)ao[this.C][n2][1]);
        this.n[n2].c();
    }

    private void q(int n2) {
        this.n[n2] = new i();
        this.n[n2].a(this.D[this.F[0]][0], this.D[this.F[0]][1], (short)-1, (byte)2, (short)this.D[this.F[0]][2], (byte)-1);
        this.n[n2].f(1);
        byte by = 1;
        i i2 = this.n[n2];
        this.n[n2].o = by;
        this.n[n2].b(ao[this.C][n2][0], (int)ao[this.C][n2][1]);
        short s = a.b.c.c[0][this.D[this.F[0]][0]][1];
        this.n[n2].g((byte)(s * 10));
        this.n[n2].H();
        this.n[n2].c();
        A.a((byte)this.n[n2].j((byte)1), this.n[n2].r(), (byte)1);
        this.F[0] = (byte)(this.F[0] + 1);
    }

    private void I() {
        this.U = this.T;
        this.Z = ap[this.r.E];
        if (this.Z[this.U * 7 + 1] == 1) {
            short s;
            short s2;
            short s3;
            short s4;
            short s5;
            this.S = new b();
            if (this.Z[this.U * 7] == 0) {
                i i2 = (i)this.r.q;
                s5 = (short)i2.j;
                i2 = (i)this.r.q;
                s4 = (short)i2.k;
                s3 = (short)((i)this.r.q).r();
                s3 = a.b.c.c[0][s3][17];
                s2 = ((i)this.r.q).q();
                s = ((i)this.r.q).o;
            } else {
                i i3 = this.r;
                s5 = (short)i3.j;
                i3 = this.r;
                s4 = (short)i3.k;
                s3 = (short)this.r.r();
                s3 = a.b.c.c[0][s3][17];
                s2 = this.r.q();
                s = this.r.o;
            }
            short[] sArray = x[this.Z[this.U * 7 + 2]];
            short[] sArray2 = new short[sArray.length + 5];
            System.arraycopy(sArray, 1, sArray2, 6, sArray.length - 1);
            short[] sArray3 = new short[]{sArray[0], s5, s4, s3, s2, s};
            System.arraycopy(sArray3, 0, sArray2, 0, sArray3.length);
            this.S.a(sArray2);
            this.S.d(true);
        } else if (this.Z[this.U * 7] == 0) {
            ((i)this.r.q).a((short)this.Z[this.U * 7 + 2], this.Z[this.U * 7 + 3]);
        } else {
            this.r.a((short)this.Z[this.U * 7 + 2], this.Z[this.U * 7 + 3]);
        }
        this.T = (byte)(this.T + 1);
    }

    private boolean a(i i2) {
        while (!this.b(i2)) {
            for (int i3 = 0; i3 < this.aj[this.ah[this.ae << 1]].length; ++i3) {
                if (this.aj[this.ah[this.ae << 1]][i3] != this.ah[(this.ae << 1) + 1]) continue;
                return false;
            }
            if (this.ah[this.ae << 1] == 0) {
                i2.o(this.ah[(this.ae << 1) + 1]);
                i2.d(this.ah[(this.ae << 1) + 1], this.ai[this.ae]);
            } else if (this.ah[this.ae << 1] == 1) {
                i2.q(this.ah[(this.ae << 1) + 1]);
                i2.c(this.ah[(this.ae << 1) + 1], this.ai[this.ae]);
            }
            if (i2.s() == 0) {
                this.d.a(i2, false);
                this.d.a(i2);
            } else {
                this.d.b(i2, this.aF);
                this.d.b(i2);
            }
            this.ae = (byte)(this.ae + 1);
        }
        return true;
    }

    private void J() {
        i i2 = (i)this.G.elementAt(this.s);
        this.ad = this.ac;
        this.af = this.ag[this.ae];
        if (this.af[this.ad << 2] == 1) {
            this.S = new b();
            i i3 = i2;
            short s = (short)i3.j;
            i3 = i2;
            short s2 = (short)i3.k;
            short s3 = (short)i2.r();
            s3 = a.b.c.c[0][s3][17];
            short s4 = i2.q();
            short s5 = i2.o;
            short[] sArray = x[this.af[(this.ad << 2) + 1]];
            short[] sArray2 = new short[sArray.length + 5];
            System.arraycopy(sArray, 1, sArray2, 6, sArray.length - 1);
            short[] sArray3 = new short[]{sArray[0], s, s2, s3, s4, s5};
            System.arraycopy(sArray3, 0, sArray2, 0, sArray3.length);
            this.S.a(sArray2);
            this.S.d(true);
        } else {
            i2.a((short)this.af[(this.ad << 2) + 1], this.af[(this.ad << 2) + 2]);
        }
        this.ac = (byte)(this.ac + 1);
    }

    private void a(i i2, boolean n2) {
        if (!i2.T()) {
            i2.D();
            i2.E();
            this.d.b(i2);
            this.h(i2);
            this.F[1] = (byte)(this.F[1] + 1);
        }
        if (this.F[1] >= this.D.length) {
            game.a.W();
            this.a((byte)8);
            return;
        }
        if (!i2.T()) {
            for (n2 = 0; n2 < this.n.length; ++n2) {
                if (!this.n[n2].m(11) || !this.n[this.n[n2].w[11][1]].equals(i2)) continue;
                this.n[n2].n(11);
            }
            if (this.F[0] < this.D.length) {
                this.q(this.o[this.s]);
                this.q = this.o[this.s];
                this.a((byte)15);
                return;
            }
            this.s = (byte)(this.s + 1);
            this.L();
            return;
        }
        if (n2 != 0) {
            this.a((byte)2);
        }
    }

    private void b(i i2, boolean n2) {
        int n3;
        for (n3 = 0; n3 < this.p.length && !this.n(this.p[n3]).T(); ++n3) {
        }
        if (n3 >= this.p.length) {
            this.a((byte)9);
            return;
        }
        if (!i2.T()) {
            for (n2 = 0; n2 < this.n.length; ++n2) {
                if (!this.n[n2].m(11) || !this.n[this.n[n2].w[11][1]].equals(i2)) continue;
                this.n[n2].n(11);
            }
            i2.D();
            i2.E();
            this.d.a(i2);
            I.removeElement(i2);
            t.removeElement(i2);
            i2.C = 0;
            i2.e(false);
            i2.G = 0;
            if (this.P()) {
                this.q = this.o[this.s];
                this.a((byte)5);
                return;
            }
            this.s = (byte)(this.s + 1);
            this.L();
            return;
        }
        if (n2 != 0) {
            if (i2.p(9)) {
                this.a((byte)2);
                return;
            }
            this.a((byte)20);
        }
    }

    private boolean b(i i2) {
        if (this.ae >= this.ag.length) {
            this.ae = 0;
            this.ac = 0;
            this.ad = 0;
            this.ag = null;
            this.ah = null;
            this.ai = null;
            this.S = null;
            if (this.a == 12) {
                this.a(i2, true);
            } else if (this.a == 13) {
                this.b(i2, true);
            }
            return true;
        }
        return false;
    }

    private void c(i i2) {
        this.ae = (byte)(this.ae + 1);
        if (!this.b(i2)) {
            if (this.a(i2)) {
                return;
            }
            this.ac = 0;
            this.ad = 0;
            this.J();
            if (this.a == 12) {
                if (this.g(i2) == 2) {
                    this.ae = 0;
                    this.ag = null;
                    this.ah = null;
                    this.ai = null;
                }
                this.a(i2, false);
                return;
            }
            if (this.a == 13) {
                if (this.g(i2) == 1) {
                    this.ae = 0;
                    this.ag = null;
                    this.ah = null;
                    this.ai = null;
                }
                this.b(i2, false);
            }
        }
    }

    private void K() {
        a.b.a a2;
        Object object;
        block26: {
            block27: {
                block28: {
                    block29: {
                        object = (i)this.G.elementAt(this.s);
                        if (((i)object).v == null) break block26;
                        if (!((i)object).v.j()) break block27;
                        a2 = ((i)object).v;
                        if (!a2.b.f()) break block28;
                        ((i)object).v.b();
                        ((i)object).v = null;
                        if (this.ac <= this.af.length / 4 - 1) break block29;
                        if (this.S == null) {
                            this.L = true;
                        }
                        break block26;
                    }
                    this.J();
                    if (((i)object).v == null) break block26;
                    break block27;
                }
                if (this.af[(this.ad << 2) + 3] != -1 && ((i)object).v.a((int)this.af[(this.ad << 2) + 3]) && this.ac < this.af.length / 4) {
                    this.J();
                }
                break block26;
            }
            ((i)object).v.a();
        }
        if (this.S != null && !this.S.j()) {
            this.S.a();
            ((a.b.a)object).c(false);
        }
        if (this.S != null && this.S.j() && !this.S.d()) {
            this.S = null;
            ((a.b.a)object).c(true);
            if (this.ac > this.af.length / 4 - 1) {
                this.L = true;
            } else {
                this.J();
            }
        }
        if (this.L) {
            int n2;
            i i2 = object;
            object = this;
            boolean bl = false;
            int n3 = i2.O();
            int n4 = 0;
            if (!((a)object).aF) {
                if (((a)object).ah[((a)object).ae << 1] == 0) {
                    n4 = i2.o(((a)object).ah[(((a)object).ae << 1) + 1]);
                    i2.d(((a)object).ah[(((a)object).ae << 1) + 1], ((a)object).ai[((a)object).ae]);
                } else if (((a)object).ah[((a)object).ae << 1] == 1) {
                    i2.q(((a)object).ah[(((a)object).ae << 1) + 1]);
                    i2.c(((a)object).ah[(((a)object).ae << 1) + 1], ((a)object).ai[((a)object).ae]);
                }
                n2 = 1;
                a2 = i2;
                if (a2.e[n2] < n3) {
                    i i3 = i2;
                    n2 = 1;
                    a2 = i3;
                    i i4 = i2;
                    a2 = i4;
                    a2 = i2;
                    super.a("" + (i3.e[n2] - n3), (byte)0, 0, i2.s(), i4.j, a2.k, 9, 12);
                }
                if (n4 > 0) {
                    i i5 = i2;
                    a2 = i5;
                    a2 = i2;
                    super.a("+" + n4, (byte)0, 2, i2.s(), i5.j, a2.k, 9, 12);
                }
                ((a)object).aF = true;
                ((a)object).d.k = 0;
                if (i2.s() == 0) {
                    ((a)object).d.a(i2);
                } else {
                    ((a)object).d.b(i2);
                }
            }
            n4 = super.S();
            if (i2.s() == 0) {
                n2 = 1;
                a2 = i2;
                if (n3 < a2.e[n2]) {
                    if (((a)object).d.a(i2, true) && n4 != 0) {
                        ((a)object).aF = false;
                        bl = true;
                        super.c(i2);
                    }
                } else if (((a)object).d.a(i2, false) && n4 != 0) {
                    ((a)object).aF = false;
                    bl = true;
                    super.c(i2);
                }
            } else {
                n2 = 1;
                a2 = i2;
                if (n3 < a2.e[n2]) {
                    if (((a)object).d.b(i2, true) && n4 != 0) {
                        ((a)object).aF = false;
                        bl = true;
                        super.c(i2);
                    }
                } else if (((a)object).d.b(i2, false) && n4 != 0) {
                    ((a)object).aF = false;
                    bl = true;
                    super.c(i2);
                }
            }
            if (bl) {
                this.L = false;
            }
        }
    }

    private void r(int n2) {
        this.E = new byte[n2];
        this.o = new byte[n2];
        for (n2 = 0; n2 < this.E.length; ++n2) {
            this.E[n2] = (byte)n2;
        }
    }

    private void e(byte by) {
        switch (by) {
            case 0: {
                this.ak.a(by, (byte)0, true);
                break;
            }
            case 1: {
                this.n[0].c(false);
                short s = a.b.c.c[0][((i)this.r.q).r()][17];
                short[] sArray = new short[20];
                sArray[0] = 8;
                i i2 = (i)this.r.q;
                sArray[1] = (short)i2.j;
                i2 = (i)this.r.q;
                sArray[2] = (short)i2.k;
                sArray[3] = s;
                sArray[4] = 0;
                sArray[5] = ((i)this.r.q).o;
                sArray[6] = 0;
                sArray[7] = 9;
                sArray[8] = 1;
                sArray[9] = 3;
                sArray[10] = 0;
                sArray[11] = 10;
                sArray[12] = 0;
                sArray[13] = 0;
                sArray[14] = 7;
                sArray[15] = 0;
                sArray[16] = -10;
                sArray[17] = 4;
                sArray[18] = 0;
                sArray[19] = -20;
                short[] sArray2 = sArray;
                this.S = new b();
                this.S.a(sArray2);
                this.S.d(true);
                this.S.a();
                this.ak.a(by, (byte)-2, true);
                break;
            }
            case 2: {
                this.ak.a(by, (byte)0, true);
                break;
            }
            case 3: {
                this.ak.a(by, (byte)-2, true);
                break;
            }
            case 4: {
                short s = a.b.c.c[0][((i)this.r.q).r()][17];
                short[] sArray = new short[23];
                sArray[0] = 8;
                i i3 = (i)this.r.q;
                sArray[1] = (short)i3.j;
                i3 = (i)this.r.q;
                sArray[2] = (short)i3.k;
                sArray[3] = s;
                sArray[4] = 0;
                sArray[5] = ((i)this.r.q).o;
                sArray[6] = 0;
                sArray[7] = 8;
                sArray[8] = 1;
                sArray[9] = 4;
                sArray[10] = 1;
                sArray[11] = 4;
                sArray[12] = 0;
                sArray[13] = -20;
                sArray[14] = 6;
                sArray[15] = 0;
                sArray[16] = -12;
                sArray[17] = 8;
                sArray[18] = 0;
                sArray[19] = -4;
                sArray[20] = 10;
                sArray[21] = 0;
                sArray[22] = 0;
                short[] sArray3 = sArray;
                this.S = new b();
                this.S.a(sArray3);
                this.S.d(true);
                this.S.a();
                this.ak.a((byte)1, (byte)-2, true);
            }
        }
        this.B = by;
    }

    private void a(int n2, boolean bl) {
        this.am[this.n.length + 1].c(bl);
        this.am[this.n.length + 1].b(an[this.k][(n2 << 2) + 2], (int)an[this.k][(n2 << 2) + 3]);
    }

    private void b(int n2, boolean bl) {
        this.am[this.n.length].c(bl);
        this.am[this.n.length].b(an[this.k][(n2 << 2) + 2], (int)an[this.k][(n2 << 2) + 3]);
    }

    public final void a(byte by) {
        this.b = this.a;
        this.a = by;
        switch (by) {
            case 0: {
                this.s = 0;
                while (((i)this.G.elementAt(this.s)).s() != 0) {
                    this.s = (byte)(this.s + 1);
                }
                break;
            }
            case 15: {
                this.d.a = 0;
                this.J = true;
                this.R = this.q;
                this.P[this.R] = 0;
                this.G.setElementAt(this.n[this.q], this.E[this.q]);
                this.n[this.q].K = true;
                this.b(this.q, false);
                this.s = (byte)(this.s + 1);
                return;
            }
            case 20: {
                this.r = (i)this.G.elementAt(this.s);
                for (by = 0; by < this.n.length; by = (byte)(by + 1)) {
                    if (this.n[by].s() != 1 || !this.n[by].T()) continue;
                    this.d.b(this.n[by], false);
                    this.d.b(this.n[by]);
                }
                this.b(this.o[this.s], true);
                this.d.c(this.r);
                if (this.n[0].T()) {
                    this.d.b(this.r, this.n[0]);
                    return;
                }
                this.d.b(this.r, this.n[1]);
                return;
            }
            case 1: {
                if (this.s >= this.G.size()) {
                    this.s = 0;
                }
                this.r = (i)this.G.elementAt(this.s);
                while (this.r.K || !this.r.T()) {
                    this.s = (byte)(this.s + 1);
                    if (this.r.K) {
                        this.r.K = false;
                    }
                    if (this.s >= this.G.size()) {
                        this.H = true;
                        this.s = 0;
                        break;
                    }
                    this.r = (i)this.G.elementAt(this.s);
                }
                if (!this.r.p(2) || this.r.s() != 0) break;
                by = 0;
                for (int i2 = 0; i2 < this.r.z.length; ++i2) {
                    if (this.r.z[i2] == 0) continue;
                    by = 1;
                }
                if (by == 0) {
                    this.d.c("Kh\u00f4ng c\u00f2n tinh l\u1ef1c, kh\u00f4ng c\u00e1ch n\u00e0o chi\u1ebfn \u0111\u1ea5u");
                    this.s = (byte)(this.s + 1);
                    if (this.s >= this.G.size()) {
                        this.H = true;
                        this.s = 0;
                        return;
                    }
                }
                return;
            }
            case 12: 
            case 13: {
                int n2;
                if (this.r.s() == 0) {
                    this.d.a(this.r, false);
                    this.d.a(this.r);
                } else {
                    this.d.b(this.r, false);
                    this.d.b(this.r);
                }
                i i3 = (i)this.G.elementAt(this.s);
                if (i3.m(13) || i3.m(14)) {
                    i3.D();
                }
                this.ag = new byte[i3.r(0) + i3.r(1)][];
                this.ah = new byte[this.ag.length << 1];
                this.ai = new byte[this.ag.length];
                int n3 = 0;
                for (n2 = 0; n2 < 3; ++n2) {
                    if (i3.y[0][n2] == -1) continue;
                    this.ai[n3] = (byte)n2;
                    this.ag[n3] = aq[as[0][i3.y[0][n2]]];
                    this.ah[n3 << 1] = 0;
                    this.ah[(n3 << 1) + 1] = i3.y[0][n2];
                    ++n3;
                }
                for (n2 = 0; n2 < 3; ++n2) {
                    if (i3.y[1][n2] == -1) continue;
                    this.ai[n3] = (byte)n2;
                    this.ag[n3] = ar[as[1][i3.y[1][n2]]];
                    this.ah[n3 << 1] = 1;
                    this.ah[(n3 << 1) + 1] = i3.y[1][n2];
                    ++n3;
                }
                this.ac = 0;
                this.ad = 0;
                if (this.a(i3)) break;
                this.J();
                return;
            }
            case 2: {
                return;
            }
            case 7: {
                if (this.r.s() == 0) {
                    this.d.a(this.r, false);
                    this.d.a(this.r);
                } else {
                    this.d.b(this.r, false);
                    this.d.b(this.r);
                }
                if (((i)this.r.q).s() == 1) {
                    this.d.b((i)this.r.q, false);
                    this.d.b((i)this.r.q);
                } else {
                    this.d.a((i)this.r.q, false);
                    this.d.a((i)this.r.q);
                }
                this.K = false;
                this.L = false;
                this.I();
                if (this.O()) {
                    this.aa = this.r.b((i)this.r.q);
                }
                switch (this.r.E) {
                    case 52: 
                    case 58: {
                        if (a.e.a(100) > 30) {
                            this.ab = false;
                            break;
                        }
                    }
                    default: {
                        this.ab = true;
                    }
                }
                if (this.Z[this.U * 7] == 0) {
                    this.r.a((byte)1, true);
                    return;
                }
                this.r.a((byte)0, true);
                return;
            }
            case 3: {
                this.d.e((i)this.G.elementAt(this.s));
                return;
            }
            case 4: {
                this.d.am();
                return;
            }
            case 6: {
                this.N = 0;
                this.d.b((i)this.r.H.elementAt(this.N), false);
                this.d.b(this.r, (i)this.r.H.elementAt(this.N));
                this.d.b((i)this.r.H.elementAt(this.N));
                this.a(Integer.parseInt((String)this.r.I.elementAt(this.N)), true);
                return;
            }
            case 16: {
                this.d.c = 0;
                this.d.l = false;
                this.d.Z();
                return;
            }
            case 5: {
                this.d.c = 0;
                this.d.Z();
                return;
            }
            case 17: {
                this.r.a((a.b.a)this.n[0]);
                this.ak = new g();
                this.ak.a(269, false);
                this.ak.b(this.r.m(), this.r.n());
                this.ak.c();
                this.e((byte)0);
                this.al = false;
                int n4 = this.m(v);
                this.al = a.e.a(100) < n4;
                if (f == 0 && g == 5) {
                    this.al = false;
                }
                this.d.f = 0;
                return;
            }
            case 18: {
                return;
            }
            case 21: {
                this.r.a((a.b.a)this.n[0]);
                this.d.ak();
                return;
            }
            case 10: {
                return;
            }
            case 8: {
                game.l.B().Z.y = 0;
                for (int i4 = 0; i4 < I.size(); ++i4) {
                    by = (byte)(((i)game.a.I.elementAt((int)i4)).B - ((i)I.elementAt(i4)).c((byte)1));
                    by = i ? (byte)(by % 20 / 100) : (byte)(by % 50 / 100);
                    if (by <= 0) continue;
                    ((i)I.elementAt(i4)).l(by);
                    ((i)I.elementAt(i4)).u(((i)I.elementAt(i4)).c((byte)1));
                }
                this.am[0].b(an[0][6], (int)an[0][7]);
                this.d.b((int)an[0][4], an[0][5]);
                return;
            }
            case 22: {
                this.d.aq();
                return;
            }
            case 9: {
                this.N();
                return;
            }
            case 11: {
                this.d.a(4, (byte)0);
                return;
            }
            case 14: {
                return;
            }
            case 23: {
                this.d.as();
                return;
            }
            case 24: {
                this.d.aH();
                return;
            }
            case 102: {
                this.d.aL();
                return;
            }
            case 104: {
                this.d.aK();
                return;
            }
            case 101: {
                this.d.aJ();
            }
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
                this.Q = (byte)(this.Q + 1);
                this.O[this.R] = this.P[this.R];
                this.n[this.R].b(game.a.ao[this.C][this.R][this.P[this.R] << 2], (int)game.a.ao[this.C][this.R][(this.P[this.R] << 2) + 1]);
                this.am[this.R].b(game.a.ao[this.C][this.R][(this.P[this.R] << 2) + 2], (int)game.a.ao[this.C][this.R][(this.P[this.R] << 2) + 3]);
                if (this.k == 1 && this.P[this.R] > game.a.ao[this.C][this.R].length / 4 - 3 && this.R % 2 == 0 && this.P.length > this.R + 1) {
                    v0 = this.R + 1;
                    this.P[v0] = (byte)(this.P[v0] + 1);
                    this.O[this.R + 1] = this.P[this.R + 1];
                    this.n[this.R + 1].b(game.a.ao[this.C][this.R + 1][this.P[this.R + 1] << 2], (int)game.a.ao[this.C][this.R + 1][(this.P[this.R + 1] << 2) + 1]);
                    this.am[this.R + 1].b(game.a.ao[this.C][this.R + 1][(this.P[this.R + 1] << 2) + 2], (int)game.a.ao[this.C][this.R + 1][(this.P[this.R + 1] << 2) + 3]);
                }
                if (this.k == 0) {
                    this.d.a(this.n[1], this.n[0], this.n[this.R], this.P[this.R] + 1, game.a.ao[this.C][this.R].length / 4);
                }
                if (this.Q > 1) {
                    v1 = this.R;
                    this.P[v1] = (byte)(this.P[v1] + 1);
                    this.Q = 0;
                }
                if (this.P[this.R] <= game.a.ao[this.C][this.R].length / 4 - 1) break;
                this.P[this.R] = (byte)(game.a.ao[this.C][this.R].length / 4 - 1);
                this.O[this.R] = this.P[this.R];
                ++this.R;
                if (this.R <= this.n.length - 1) break;
                this.R = this.n.length - 1;
                this.a((byte)20);
                break;
            }
            case 15: {
                if (this.k == 0) {
                    this.d.a(this.n[1], this.n[0], this.P[this.R] + 1, game.a.ao[this.C][this.R].length / 4);
                }
                if (this.Q > 0) {
                    v2 = this.R;
                    this.P[v2] = (byte)(this.P[v2] + 1);
                    this.Q = 0;
                } else {
                    this.Q = (byte)(this.Q + 1);
                    if (this.P[this.R] > game.a.ao[this.C][this.R].length / 4 - 3) {
                        this.O[this.R] = this.P[this.R];
                    }
                    this.n[this.R].b(game.a.ao[this.C][this.R][this.P[this.R] << 2], (int)game.a.ao[this.C][this.R][(this.P[this.R] << 2) + 1]);
                }
                if (this.P[this.R] <= game.a.ao[this.C][this.R].length / 4 - 1) break;
                this.O[this.R] = this.P[this.R] = (byte)(game.a.ao[this.C][this.R].length / 4 - 1);
                var1_1 = 1;
                if (!this.u) ** GOTO lbl62
                while (!(this.s >= this.G.size() || ((i)this.G.elementAt(this.s)).T() && ((i)this.G.elementAt(this.s)).s() == 0)) {
                    this.s = (byte)(this.s + 1);
                }
                if (this.s >= this.G.size()) {
                    this.s = 0;
                    this.a((byte)1);
                } else if (game.a.d((i)this.G.elementAt(this.s))) {
                    this.a((byte)13);
                } else {
                    var1_1 = 0;
                    this.a((byte)20);
                }
                this.u = false;
                ** GOTO lbl129
lbl62:
                // 1 sources

                if (this.k == 0) {
                    this.s = (byte)this.G.size();
                }
                if (this.s < this.G.size()) ** GOTO lbl109
                for (var2_2 = 0; var2_2 < this.n.length; ++var2_2) {
                    this.n[var2_2].K = false;
                }
                if (this.J) {
                    this.Q();
                    this.J = false;
                }
                if (this.b == 12 || this.b == 13) {
                    this.s = 0;
                    while (((i)this.G.elementAt(this.s)).s() != 0 || ((i)this.G.elementAt(this.s)).s() == 0 && !((i)this.G.elementAt(this.s)).T()) {
                        this.s = (byte)(this.s + 1);
                    }
                    if (game.a.d((i)this.G.elementAt(this.s))) {
                        this.a((byte)13);
                    } else {
                        var1_1 = 0;
                        this.a((byte)20);
                    }
                } else {
                    var2_2 = 0;
                    if (this.r.m(12) && this.r.L[12] == 2) {
                        this.r.L[12] = (short)(this.r.L[12] - 1);
                        if (!((i)this.r.q).T()) {
                            var2_2 = 1;
                            this.r.L[12] = (short)(this.r.L[12] - 1);
                        } else {
                            this.s = (byte)(this.s - 1);
                            this.a((byte)2);
                        }
                    } else {
                        var3_6 = a.e.a(100);
                        if ((this.r.E == 63 || this.r.E == 69) && var3_6 <= a.b.c.c[1][this.r.E][8] && ((i)this.r.q).T()) {
                            this.s = (byte)(this.s - 1);
                            this.a((byte)2);
                        } else {
                            var2_2 = 1;
                        }
                    }
                    if (var2_2 != 0) {
                        this.s = 0;
                        while (((i)this.G.elementAt(this.s)).s() != 0 || ((i)this.G.elementAt(this.s)).s() == 0 && !((i)this.G.elementAt(this.s)).T()) {
                            this.s = (byte)(this.s + 1);
                        }
                        if (game.a.d((i)this.G.elementAt(this.s))) {
                            this.a((byte)13);
                        } else {
                            var1_1 = 0;
                            this.a((byte)20);
                        }
                    }
                }
                ** GOTO lbl129
lbl109:
                // 1 sources

                if (this.b != 13 && this.b != 12) ** GOTO lbl112
                this.a((byte)1);
                ** GOTO lbl129
lbl112:
                // 1 sources

                var2_2 = 0;
                if (!this.r.m(12) || this.r.L[12] != 2) ** GOTO lbl121
                this.r.L[12] = (short)(this.r.L[12] - 1);
                if (((i)this.r.q).T()) ** GOTO lbl118
                this.r.L[12] = (short)(this.r.L[12] - 1);
                ** GOTO lbl-1000
lbl118:
                // 1 sources

                this.s = (byte)(this.s - 1);
                this.a((byte)2);
                ** GOTO lbl127
lbl121:
                // 1 sources

                var3_6 = a.e.a(100);
                if ((this.r.E == 63 || this.r.E == 69) && var3_6 <= a.b.c.c[1][this.r.E][8] && ((i)this.r.q).T()) {
                    this.s = (byte)(this.s - 1);
                    this.a((byte)2);
                } else lbl-1000:
                // 2 sources

                {
                    var2_2 = 1;
                }
lbl127:
                // 3 sources

                if (var2_2 != 0) {
                    this.a((byte)1);
                }
lbl129:
                // 9 sources

                if (var1_1 == 0) break;
                this.D();
                if (this.n[this.q].s() == 0) {
                    this.d.a(this.n[this.q], false);
                    this.d.a(this.n[this.q]);
                    break;
                }
                this.d.b(this.n[this.q], false);
                this.d.b(this.n[this.q]);
                break;
            }
            case 1: {
                if (this.d.aB()) {
                    if (this.r.s() == 1 && this.l == 0 && (this.r.r() == 33 || this.r.r() == 59) && this.r.c((byte)1) < this.r.b((byte)1)) {
                        this.a((byte)10);
                        return;
                    }
                    if (this.H) {
                        if (this.J) {
                            this.Q();
                            this.s = 0;
                            this.J = false;
                        }
                        this.s = 0;
                        while (((i)this.G.elementAt(this.s)).s() != 0 || ((i)this.G.elementAt(this.s)).s() == 0 && !((i)this.G.elementAt(this.s)).T()) {
                            this.s = (byte)(this.s + 1);
                        }
                        if (game.a.d((i)this.G.elementAt(this.s))) {
                            this.a((byte)13);
                        } else {
                            this.a((byte)20);
                        }
                        this.H = false;
                    } else if (this.r.s() == 1) {
                        if (game.a.d(this.r)) {
                            this.a((byte)12);
                        } else {
                            this.a((byte)2);
                        }
                    } else {
                        this.a((byte)2);
                    }
                }
                this.d.g();
                this.am[this.n.length].a();
                break;
            }
            case 20: {
                this.am[this.n.length].a();
                this.d.d(this.r);
                break;
            }
            case 12: 
            case 13: {
                this.K();
                break;
            }
            case 2: {
                if (this.r.s() == 1) {
                    var1_1 = (byte)game.a.e(this.r);
                    if (this.r.p(9)) {
                        this.f(this.r);
                    } else {
                        var2_3 = 1;
                        if (game.i.a(var1_1, (byte)9) == 0 && this.r.p(8) && a.e.a(100) > a.b.c.a((byte)1, var1_1, (byte)8)) {
                            this.f(this.r);
                            var2_3 = 0;
                        }
                        if (var2_3 != 0) {
                            this.d(var1_1);
                        }
                    }
                    var2_3 = a.e.a(this.r.H.size());
                    var3_7 = (i)this.r.H.elementAt(var2_3);
                    this.r.J = Byte.parseByte((String)this.r.I.elementAt(var2_3));
                    this.r.a(var1_1, var3_7);
                    this.d.b(this.r, var3_7);
                    this.a((byte)7);
                    break;
                }
                if (this.r.p(9)) {
                    this.f(this.r);
                    var1_1 = a.e.a(this.r.H.size());
                    var2_4 = (i)this.r.H.elementAt(var1_1);
                    this.r.J = Byte.parseByte((String)this.r.I.elementAt(var1_1));
                    var3_8 = (byte)game.a.e(this.r);
                    this.r.a(var3_8, var2_4);
                    this.d.b(this.r, var2_4);
                    this.a((byte)7);
                    break;
                }
                var1_1 = 1;
                if (game.i.a(this.r.I(), (byte)9) == 0 && this.r.p(8) && a.e.a(100) > a.b.c.a((byte)1, this.r.I(), (byte)8)) {
                    this.f(this.r);
                    var1_1 = 0;
                }
                if (var1_1 != 0) {
                    if ((i)this.r.q != null && !((i)this.r.q).T()) {
                        for (var2_5 = 0; var2_5 < this.r.H.size(); ++var2_5) {
                            if (!((i)this.r.H.elementAt(var2_5)).T()) continue;
                            this.r.J = Byte.parseByte((String)this.r.I.elementAt(var2_5));
                            this.r.a(this.r.I(), (i)this.r.H.elementAt(var2_5));
                        }
                    } else {
                        this.r.a(this.r.I(), (i)this.r.q);
                    }
                }
                this.d.b(this.r, (i)this.r.q);
                this.a((byte)7);
                break;
            }
            case 7: {
                if (this.r.v != null) {
                    if (this.r.q() == 0) {
                        if (this.r.v.j()) {
                            if (this.r.v.c()) {
                                this.r.v.b();
                                this.r.v = null;
                                if (this.T > this.Z.length / 7 - 1 || this.T()) {
                                    this.L = true;
                                    this.M = true;
                                } else {
                                    this.I();
                                    if (this.r.v != null) {
                                        this.r.v.a();
                                    }
                                    if (this.S != null) {
                                        this.X = true;
                                    }
                                }
                            } else if (this.Z[this.U * 7 + 4] != -1 && this.r.v.a((int)this.Z[this.U * 7 + 4])) {
                                this.r.v.b();
                                if (this.T < this.Z.length / 7 - 1 || this.T()) {
                                    this.I();
                                    if (this.S != null) {
                                        this.X = true;
                                    }
                                }
                            } else if (this.Z[this.U * 7 + 5] != -1 && this.r.v.a((int)this.Z[this.U * 7 + 5])) {
                                this.r.a(this.Z[this.U * 7 + 6], true);
                            }
                        } else {
                            this.Y = false;
                            this.r.v.a();
                        }
                    } else if (this.r.q() == 1 && this.r.b()) {
                        this.r.a((byte)0, true);
                    }
                } else if (((i)this.r.q).v != null) {
                    if (((i)this.r.q).v.j()) {
                        if (((i)this.r.q).v.c()) {
                            ((i)this.r.q).v.b();
                            if (this.T > this.Z.length / 7 - 1 || this.T()) {
                                ((i)this.r.q).a((byte)2, true);
                            } else if (this.Z[this.T * 7] == 1) {
                                ((i)this.r.q).a((byte)2, true);
                            } else {
                                this.V = 0;
                                ((i)this.r.q).v = null;
                                this.I();
                                if (((i)this.r.q).v != null) {
                                    ((i)this.r.q).v.a();
                                }
                                if (this.S != null) {
                                    this.X = true;
                                }
                            }
                        } else {
                            if (this.Z[this.U * 7 + 5] != -1) {
                                this.V = this.U;
                            }
                            if (this.Z[this.V * 7 + 5] != -1 && ((i)this.r.q).v.a((int)this.Z[this.V * 7 + 5])) {
                                ((i)this.r.q).a(this.Z[this.V * 7 + 6], true);
                                this.V = 0;
                            }
                            if (this.Z[this.U * 7 + 4] != -1 && ((i)this.r.q).v.a((int)this.Z[this.U * 7 + 4])) {
                                this.I();
                                if (this.S != null) {
                                    this.X = true;
                                }
                            }
                        }
                    } else if (this.r.q() == 1 && this.r.b() || this.Y) {
                        this.r.a((byte)0, true);
                        ((i)this.r.q).v.a();
                        this.Y = false;
                    } else if (((i)this.r.q).q() == 2 && ((i)this.r.q).b()) {
                        this.K = true;
                        ((i)this.r.q).v = null;
                        if (this.T > this.Z.length / 7 - 1 || this.T()) {
                            this.L = true;
                        } else {
                            this.I();
                            if (this.S != null) {
                                this.X = true;
                            }
                        }
                    }
                }
                if (this.S != null && !this.S.j() && (this.r.q() == 1 && this.r.b() || this.X || this.r.q() == 0)) {
                    if (this.U == 0) {
                        this.Y = true;
                    }
                    this.r.a((byte)0, true);
                    this.S.a();
                    this.W = this.U;
                    if (this.Z[this.U * 7] == 0) {
                        ((i)this.r.q).c(false);
                    } else {
                        this.r.c(false);
                    }
                }
                if (this.S != null && this.S.j() && !this.S.d()) {
                    this.S = null;
                    this.X = false;
                    if (this.Z[this.W * 7] == 0) {
                        ((i)this.r.q).c(true);
                    } else {
                        this.r.c(true);
                    }
                    if (((i)this.r.q).v == null && this.r.v == null) {
                        if (this.T > this.Z.length / 7 - 1 || this.T()) {
                            if (this.Z[this.U * 7] == 0) {
                                this.K = true;
                            }
                            this.M = true;
                            this.L = true;
                        } else {
                            if (this.Z[this.T * 7] == 1) {
                                this.K = true;
                            }
                            this.I();
                            if (this.S != null) {
                                this.X = true;
                            }
                        }
                    }
                    this.W = 0;
                }
                if (this.K) {
                    this.M = false;
                    if (this.U() && (((i)this.r.q).T() || this.c((i)this.r.q, true))) {
                        this.M = true;
                        this.K = false;
                    }
                }
                if (!this.M || !this.L || !this.M()) break;
                this.M = false;
                this.L = false;
                break;
            }
            case 3: {
                this.d.f((i)this.G.elementAt(this.s));
                break;
            }
            case 4: {
                this.d.an();
                break;
            }
            case 17: {
                if (this.d.f == 0) {
                    if (this.B == 0 && this.ak.b()) {
                        this.e((byte)1);
                    } else if (this.B == 1 && this.ak.b()) {
                        if (!this.S.d()) {
                            this.e((byte)2);
                        }
                    } else if (this.B == 2 && this.ak.b()) {
                        if (this.al) {
                            this.e((byte)3);
                        } else {
                            this.e((byte)4);
                        }
                    } else if (this.B == 3 && this.ak.b()) {
                        var1_1 = game.a.A.z();
                        if (var1_1 == 0) {
                            this.d.f = 1;
                            this.d.b("B\u1eaft th\u00e0nh c\u00f4ng #2" + a.a.c(a.b.c.c[0][((i)this.r.q).r()][0]));
                            game.a.A.a(((i)this.r.q).Q());
                        } else if (var1_1 == 1) {
                            this.d.f = 2;
                            this.d.b("B\u1eaft th\u00e0nh c\u00f4ng #2" + a.a.c(a.b.c.c[0][((i)this.r.q).r()][0]));
                            game.a.A.b(((i)this.r.q).Q());
                        } else {
                            this.d.f = 1;
                            this.d.b("Kh\u00f4ng c\u00f2n kh\u00f4ng gian, s\u1ee7ng v\u1eadt n\u00e0y \u0111\u00e3 ph\u00f3ng sinh");
                        }
                    } else if (this.B == 4 && this.ak.b() && !this.S.d()) {
                        this.S = null;
                        this.n[0].c(true);
                        this.ak.d();
                        this.r.K = true;
                        if (this.al) {
                            this.d.b("Ng\u00e2n h\u00e0ng v\u00e0 Ba l\u00f4 \u0111\u1ec1u \u0111\u00e3 \u0111\u1ea7y");
                            this.d.f = 3;
                        } else {
                            this.s = (byte)(this.s + 1);
                            this.a((byte)1);
                        }
                    }
                    this.ak.a();
                    break;
                }
                if (this.d.aA()) {
                    if (this.d.f == 3) {
                        this.d.f = 0;
                        this.r.K = true;
                        this.s = (byte)(this.s + 1);
                        this.a((byte)1);
                    } else if (this.d.f == 2) {
                        this.d.b("S\u1ee7ng v\u1eadt ba l\u00f4 \u0111\u00e3 \u0111\u1ee7, \u0111\u00e3 \u0111\u1ec3 v\u00e0o ng\u00e2n h\u00e0ng");
                        this.d.f = 4;
                    } else if (this.d.f == 4 || this.d.f == 1) {
                        this.d.f = 0;
                        game.l.B().Z.y = (byte)-1;
                        this.q();
                        game.f.B().a((byte)10);
                    }
                }
                this.d.f();
                break;
            }
            case 16: {
                this.d.ao();
                break;
            }
            case 5: {
                this.d.aa();
                break;
            }
            case 18: {
                break;
            }
            case 19: {
                if (!this.g(786432)) break;
                this.a((byte)18);
                break;
            }
            case 6: {
                if (this.g(4100)) {
                    if (this.k == 1) {
                        this.N = (byte)(this.N - 1);
                        if (this.N <= 0) {
                            this.N = 0;
                        }
                        this.a(Integer.parseInt((String)this.r.I.elementAt(this.N)), true);
                        this.d.b((i)this.r.H.elementAt(this.N), false);
                        this.d.b(this.r, (i)this.r.H.elementAt(this.N));
                        this.d.b((i)this.r.H.elementAt(this.N));
                    }
                } else if (this.g(8448)) {
                    if (this.k == 1) {
                        this.N = (byte)(this.N + 1);
                        if (this.N >= this.r.H.size() - 1) {
                            this.N = (byte)(this.r.H.size() - 1);
                        }
                        this.a(Integer.parseInt((String)this.r.I.elementAt(this.N)), true);
                        this.d.b((i)this.r.H.elementAt(this.N), false);
                        this.d.b(this.r, (i)this.r.H.elementAt(this.N));
                        this.d.b((i)this.r.H.elementAt(this.N));
                    }
                } else if (this.g(16400)) {
                    if (this.k == 1) {
                        this.N = (byte)(this.N - 1);
                        if (this.N <= 0) {
                            this.N = 0;
                        }
                        this.a(Integer.parseInt((String)this.r.I.elementAt(this.N)), true);
                        this.d.b((i)this.r.H.elementAt(this.N), false);
                        this.d.b(this.r, (i)this.r.H.elementAt(this.N));
                        this.d.b((i)this.r.H.elementAt(this.N));
                    }
                } else if (this.g(32832)) {
                    if (this.k == 1) {
                        this.N = (byte)(this.N + 1);
                        if (this.N >= this.r.H.size() - 1) {
                            this.N = (byte)(this.r.H.size() - 1);
                        }
                        this.a(Integer.parseInt((String)this.r.I.elementAt(this.N)), true);
                        this.d.b((i)this.r.H.elementAt(this.N), false);
                        this.d.b(this.r, (i)this.r.H.elementAt(this.N));
                        this.d.b((i)this.r.H.elementAt(this.N));
                    }
                } else if (this.g(196640)) {
                    this.G();
                } else if (this.g(786432)) {
                    this.a(Integer.parseInt((String)this.r.I.elementAt(this.N)), false);
                    this.a((byte)3);
                }
                this.am[this.n.length].a();
                this.am[this.n.length + 1].a();
                break;
            }
            case 21: {
                this.d.al();
                break;
            }
            case 10: {
                if (!this.d.j() && this.c(this.r, false)) {
                    this.d.c(game.a.c(a.b.c.c[0][this.r.r()][0]) + "Ch\u1ea1y tr\u1ed1n");
                }
                if (!this.d.g()) break;
                game.f.B().a((byte)10);
                break;
            }
            case 8: {
                this.d.ap();
                break;
            }
            case 22: {
                this.d.ar();
                break;
            }
            case 9: {
                break;
            }
            case 11: {
                this.d.a((byte)4, (byte)0);
                break;
            }
            case 14: {
                break;
            }
            case 23: {
                this.d.at();
                break;
            }
            case 24: {
                this.d.aI();
                break;
            }
            case 101: 
            case 102: 
            case 104: {
                this.d.aO();
            }
        }
        for (var1_1 = 0; var1_1 < this.n.length; ++var1_1) {
            this.n[var1_1].p();
        }
        this.c.c();
    }

    private void b(Graphics graphics) {
        graphics.setColor(0xFFFFFF);
        for (int i2 = 0; i2 < this.n.length; ++i2) {
            this.n[i2].a(graphics);
        }
    }

    private void a(Graphics graphics, boolean bl, int n2, int n3) {
        for (n2 = 0; n2 < this.n.length; ++n2) {
            this.am[n2].a(graphics, 0, 0);
        }
        if (bl) {
            this.am[this.n.length].a(graphics, 0, 0);
            if (this.k == 1) {
                this.am[this.n.length + 1].a(graphics, 0, 0);
            }
        }
    }

    private void a(String string, byte by, int n2, int n3, int n4, int n5, int n6, int n7) {
        this.az = string;
        this.at = n4;
        this.au = n5;
        this.ax = n2;
        this.ay = n3;
        this.av = n6;
        this.aw = n7;
        this.aD.addElement(this.az);
        this.aC.addElement(new int[]{by, this.ax, this.ay, -1, this.av, this.aw});
    }

    private void c(Graphics graphics) {
        block4: for (int i2 = 0; i2 < this.aC.size(); ++i2) {
            int[] nArray = (int[])this.aC.elementAt(i2);
            String string = (String)this.aD.elementAt(i2);
            switch (nArray[0]) {
                case 0: {
                    if (nArray[2] == 0) {
                        game.a.a(graphics, aA[nArray[1]], string, this.at + aB[nArray[0]][nArray[3] << 1] + 30, this.au + aB[nArray[0]][(nArray[3] << 1) + 1] - 30, nArray[4], nArray[5], 1);
                        continue block4;
                    }
                    game.a.a(graphics, aA[nArray[1]], string, this.at - aB[nArray[0]][nArray[3] << 1] - 30, this.au + aB[nArray[0]][(nArray[3] << 1) + 1] - 30, nArray[4], nArray[5], 1);
                    continue block4;
                }
                case 1: {
                    if (nArray[2] == 0) {
                        a.e.a(graphics, string, 16704699, this.at - 10, this.au + aB[nArray[0]][(nArray[3] << 1) + 1] - 30, 17, 17, this.c.b, 2);
                        continue block4;
                    }
                    a.e.a(graphics, string, 16704699, this.at + 10, this.au + aB[nArray[0]][(nArray[3] << 1) + 1] - 30, 17, 17, this.c.b, 2);
                }
            }
        }
    }

    public final void a(Graphics graphics) {
        if (!this.j) {
            return;
        }
        if (this.m != null) {
            graphics.drawImage(this.m, 0, 0, 20);
        } else {
            graphics.setColor(0);
            graphics.fillRect(0, 0, (int)a.a.g(), (int)a.a.h());
        }
        switch (this.a) {
            case 0: {
                this.a(graphics, false, 0, 0);
                this.b(graphics);
                break;
            }
            case 12: 
            case 13: {
                this.a(graphics, false, 0, 0);
                if (this.S != null) {
                    this.S.a(graphics, 0, 0);
                }
                this.b(graphics);
                this.c(graphics);
                break;
            }
            case 1: 
            case 10: {
                this.a(graphics, false, 0, 0);
                this.b(graphics);
                break;
            }
            case 20: {
                this.a(graphics, true, 0, 0);
                this.b(graphics);
                break;
            }
            case 2: {
                this.a(graphics, false, 0, 0);
                this.b(graphics);
                break;
            }
            case 7: {
                this.a(graphics, false, 0, 0);
                if (this.S != null) {
                    this.S.a(graphics, 0, 0);
                }
                this.b(graphics);
                this.c(graphics);
                break;
            }
            case 15: {
                this.a(graphics, false, 0, 0);
                this.b(graphics);
                break;
            }
            case 3: {
                graphics.setColor(0xFFFFFF);
                break;
            }
            case 4: {
                graphics.setColor(0xFFFFFF);
                break;
            }
            case 5: {
                graphics.setColor(0xFFFFFF);
                graphics.drawString(game.a.c(a.b.c.c[0][this.n(this.p[0]).r()][0]), game.a.g() >> 1, 200, 17);
                break;
            }
            case 17: {
                this.a(graphics, false, 0, 0);
                this.b(graphics);
                if (this.S != null && this.S.a((byte)8)) {
                    this.S.a(graphics, 0, 0);
                    this.ak.a(graphics, 0, 0);
                    break;
                }
                this.ak.a(graphics, 0, 0);
                break;
            }
            case 18: {
                break;
            }
            case 21: {
                break;
            }
            case 6: {
                if (this.k == 1) {
                    this.a(graphics, true, 0, 0);
                } else {
                    this.a(graphics, false, 0, 0);
                }
                this.b(graphics);
                break;
            }
            case 8: {
                this.am[0].a(graphics, 0, 0);
                if (this.d.i >= t.size()) break;
                ((i)t.elementAt(this.d.i)).a(graphics);
            }
        }
        this.c.a(graphics);
    }

    public final void F() {
        this.s = (byte)(this.s + 1);
        if (this.s < this.G.size()) {
            while (((i)this.G.elementAt(this.s)).s() != 0 || ((i)this.G.elementAt(this.s)).s() == 0 && !((i)this.G.elementAt(this.s)).T()) {
                this.s = (byte)(this.s + 1);
                if (this.s < this.G.size()) continue;
            }
        }
        if (this.s >= this.G.size()) {
            this.a((byte)1);
            return;
        }
        if (game.a.d((i)this.G.elementAt(this.s))) {
            this.a((byte)13);
            return;
        }
        this.a((byte)20);
    }

    private void L() {
        if (this.s >= this.G.size()) {
            if (this.J) {
                this.Q();
                this.J = false;
            }
            for (int i2 = 0; i2 < this.G.size(); ++i2) {
                ((i)this.G.elementAt((int)i2)).K = false;
            }
            this.s = 0;
            while (((i)this.G.elementAt(this.s)).s() != 0 || ((i)this.G.elementAt(this.s)).s() == 0 && !((i)this.G.elementAt(this.s)).T()) {
                this.s = (byte)(this.s + 1);
            }
            if (game.a.d((i)this.G.elementAt(this.s))) {
                this.a((byte)13);
                return;
            }
            this.a((byte)20);
            return;
        }
        this.a((byte)1);
    }

    private boolean M() {
        if (this.V()) {
            int n2;
            this.T = 0;
            this.U = 0;
            for (n2 = 0; n2 < this.p.length && !this.n(this.p[n2]).T(); ++n2) {
            }
            if (((i)this.r.q).s() == 1 && !((i)this.r.q).T() || this.r.s() == 1 && !this.r.T()) {
                ((i)this.r.q).D();
                ((i)this.r.q).E();
                this.d.b((i)this.r.q);
                this.h((i)this.r.q);
                this.F[1] = (byte)(this.F[1] + 1);
            } else if (((i)this.r.q).s() == 0 && !((i)this.r.q).T() || this.r.s() == 0 && !this.r.T()) {
                ((i)this.r.q).D();
                ((i)this.r.q).E();
                this.d.a((i)this.r.q);
                I.removeElement((i)this.r.q);
                t.removeElement((i)this.r.q);
                ((i)this.r.q).C = 0;
                ((i)this.r.q).e(false);
                ((i)this.r.q).G = 0;
            }
            int n3 = n2 >= this.p.length ? 2 : (this.F[1] >= this.D.length ? 1 : 0);
            switch (n3) {
                case 0: {
                    int n4;
                    n3 = 0;
                    if (!((i)this.r.q).T() || !this.r.T()) {
                        for (n4 = 0; n4 < this.n.length; ++n4) {
                            if (!this.n[n4].m(11) || !this.n[this.n[n4].w[11][1]].equals((i)this.r.q)) continue;
                            this.n[n4].n(11);
                        }
                        if (((i)this.r.q).s() == 1 && !((i)this.r.q).T() || this.r.s() == 1 && !this.r.T()) {
                            if (this.F[0] < this.D.length) {
                                this.q = this.r.s() == 1 && !this.r.T() ? this.o[this.s] : this.r.J;
                                this.q(this.q);
                                this.a((byte)15);
                            } else {
                                n3 = 1;
                            }
                        } else if (this.P()) {
                            this.q = this.r.s() == 0 && !this.r.T() ? this.o[this.s] : this.r.J;
                            this.a((byte)5);
                        } else {
                            n3 = 1;
                        }
                    } else {
                        n3 = 1;
                    }
                    if (n3 == 0) break;
                    if (this.r.m(12) && this.r.L[12] == 2) {
                        this.r.L[12] = (short)(this.r.L[12] - 1);
                        if (!((i)this.r.q).T()) {
                            this.r.L[12] = (short)(this.r.L[12] - 1);
                            this.s = (byte)(this.s + 1);
                            this.L();
                            break;
                        }
                        this.a((byte)2);
                        break;
                    }
                    n4 = a.e.a(100);
                    if ((this.r.E == 63 || this.r.E == 69) && n4 <= a.b.c.c[1][this.r.E][8]) {
                        if (!((i)this.r.q).T()) {
                            this.r.L[12] = (short)(this.r.L[12] - 1);
                            this.s = (byte)(this.s + 1);
                            this.L();
                            break;
                        }
                        this.a((byte)2);
                        break;
                    }
                    this.s = (byte)(this.s + 1);
                    this.L();
                    break;
                }
                case 1: {
                    game.a.W();
                    this.a((byte)8);
                    break;
                }
                case 2: {
                    this.a((byte)9);
                }
            }
            if (A.b((byte)5, (byte)0) == 2 && A.b((byte)5, (byte)1) == 1) {
                for (n3 = 0; n3 < this.n.length; ++n3) {
                    if (this.n[n2].s() != 0 || !this.n[n2].T()) continue;
                    this.n[n2].z();
                }
            }
            return true;
        }
        return false;
    }

    private void N() {
        if (game.l.B().Z.u) {
            this.a((byte)24);
        } else {
            for (int i2 = 0; i2 < game.a.A.B; ++i2) {
                game.a.A.A[i2].l(1);
                game.a.A.A[i2].u(1);
                game.a.A.A[i2].c();
            }
            game.f.B().a((byte)10);
        }
        game.l.B().Z.y = 1;
        game.l.B().Z.u = true;
    }

    private static boolean d(i i2) {
        return i2.r(0) > 0 || i2.r(1) > 0;
    }

    private boolean O() {
        return this.r.s() != ((i)this.r.q).s() || this.r.p(8);
    }

    private static int e(i i2) {
        byte by = i2.A[0];
        int[] nArray = new int[]{50, 20, 15, 10, 5, 5, 5, 5, 5, 5};
        int n2 = a.e.a(100);
        for (int i3 = 0; i3 < i2.A.length; ++i3) {
            if (i2.A[i3] == -1 || i2.z[i3] <= 0 || n2 >= nArray[i3]) continue;
            by = i2.A[i3];
        }
        return by;
    }

    public final void d(byte by) {
        this.r.H.removeAllElements();
        this.r.I.removeAllElements();
        switch (game.i.a(by, (byte)9)) {
            case 1: {
                for (by = 0; by < this.n.length; by = (byte)(by + 1)) {
                    if (this.n[by].s() != this.r.s() || !this.n[by].T()) continue;
                    this.r.H.addElement(this.n[by]);
                    this.r.I.addElement("" + by);
                }
                return;
            }
            case 0: {
                for (by = 0; by < this.n.length; by = (byte)(by + 1)) {
                    if (this.n[by].s() == this.r.s() || !this.n[by].T()) continue;
                    this.r.H.addElement(this.n[by]);
                    this.r.I.addElement("" + by);
                }
                break;
            }
        }
    }

    private void f(i i2) {
        i2.H.removeAllElements();
        i2.I.removeAllElements();
        for (int i3 = 0; i3 < this.n.length; ++i3) {
            if (!this.n[i3].T() || this.n[i3].equals(i2)) continue;
            i2.H.addElement(this.n[i3]);
            i2.I.addElement("" + i3);
        }
    }

    public final void G() {
        i i2 = (i)this.r.H.elementAt(this.N);
        i i3 = this.r;
        this.r.q = i2;
        this.r.J = Byte.parseByte((String)this.r.I.elementAt(this.N));
        this.r.h(((i)this.G.elementAt((int)this.s)).A[this.d.e]);
        this.a(Integer.parseInt((String)this.r.I.elementAt(this.N)), false);
        this.F();
    }

    private int g(i i2) {
        if (!i2.T()) {
            if (i2.s() == 0) {
                if (this.P()) {
                    return 1;
                }
            } else if (this.F[0] < this.D.length) {
                return 2;
            }
        }
        return 0;
    }

    private boolean P() {
        int n2 = 0;
        for (int i2 = 0; i2 < game.a.A.B; ++i2) {
            if (!this.n(i2).T() || this.n(i2).L()) continue;
            ++n2;
        }
        return n2 > 0;
    }

    private void f(int n2, int n3) {
        byte by = this.p[n3];
        this.p[n3] = this.p[n2];
        this.p[n2] = by;
    }

    public final int l(int n2) {
        if (!this.n(this.p[n2]).T()) {
            return 0;
        }
        if (this.n(this.p[n2]).L()) {
            return 1;
        }
        byte by = this.p[n2];
        --n2;
        while (n2 >= 0) {
            this.p[n2 + 1] = this.p[n2];
            --n2;
        }
        this.p[0] = by;
        for (n2 = 0; n2 < I.size() && !I.elementAt(n2).equals(this.n(this.p[0])); ++n2) {
        }
        if (n2 >= I.size()) {
            I.addElement(this.n(this.p[0]));
        }
        this.n((int)this.p[0]).K = true;
        this.n(this.p[0]).e(true);
        this.r.e(false);
        this.r.G = 0;
        for (n2 = 0; n2 < this.n.length; ++n2) {
            if (!this.n[n2].m(11) || !this.n[this.n[n2].w[11][1]].equals(this.r)) continue;
            this.n[n2].n(11);
        }
        return -1;
    }

    private void Q() {
        int n2;
        int n3;
        this.G.removeAllElements();
        int n4 = -1;
        for (n3 = 0; n3 < this.E.length - 1; ++n3) {
            for (int i2 = n3 + 1; i2 < this.E.length; ++i2) {
                n2 = 4;
                i i3 = this.n[n3];
                short s = i3.d[n2];
                n2 = 4;
                i3 = this.n[i2];
                if (s >= i3.d[n2]) continue;
                byte by = this.E[n3];
                this.E[n3] = this.E[i2];
                this.E[i2] = by;
            }
        }
        for (n3 = 0; n3 < this.E.length; ++n3) {
            if (!this.n[n3].f((byte)7)) continue;
            n4 = n3;
            this.E[n3] = 0;
            break;
        }
        if (n4 != -1) {
            int n5;
            n3 = 1;
            int[] nArray = new int[this.E.length - 1];
            for (n5 = 0; n5 < this.E.length; ++n5) {
                if (n5 == n4) continue;
                nArray[n3 - 1] = n5;
                this.E[n5] = (byte)n3;
                ++n3;
            }
            for (n5 = 0; n5 < nArray.length - 1; ++n5) {
                for (n4 = n5 + 1; n4 < nArray.length; ++n4) {
                    n2 = 4;
                    i i4 = this.n[nArray[n5]];
                    short s = i4.d[n2];
                    n2 = 4;
                    i4 = this.n[nArray[n4]];
                    if (s >= i4.d[n2]) continue;
                    byte by = this.E[nArray[n5]];
                    this.E[nArray[n5]] = this.E[nArray[n4]];
                    this.E[nArray[n4]] = by;
                }
            }
        }
        for (n3 = 0; n3 < this.E.length; ++n3) {
            this.o[this.E[n3]] = (byte)n3;
        }
        for (n3 = 0; n3 < this.o.length; ++n3) {
            this.G.addElement(this.n[this.o[n3]]);
        }
    }

    private void R() {
        i i2;
        int n2;
        if (this.r.f((byte)10)) {
            n2 = 1;
            i2 = (i)this.r.q;
            if (i2.e[n2] <= a.b.c.c[3][10][5]) {
                short s = a.b.c.c[3][10][5];
                n2 = 1;
                i2 = (i)this.r.q;
                i2.e[n2] = s;
            }
        }
        n2 = 1;
        i2 = (i)this.r.q;
        if (i2.e[n2] <= 0) {
            ((i)this.r.q).a((byte)3, true);
            return;
        }
        ((i)this.r.q).a((byte)0, true);
    }

    private boolean c(i i2, boolean bl) {
        if (this.l == 0 && bl) {
            if (i2.M != null && !i2.M.j()) {
                return true;
            }
        } else {
            i i3 = i2;
            int n2 = i3.j;
            this.aE = i2.s() == 0 ? (this.aE -= 10) : (this.aE += 10);
            n2 += this.aE;
            if (Math.abs(this.aE) >= 100) {
                i2.d();
                this.aE = 0;
                return true;
            }
            i i4 = i2;
            i2.b(n2, i4.k);
        }
        return false;
    }

    private boolean S() {
        int n2;
        for (n2 = 0; n2 < this.aC.size(); ++n2) {
            int[] nArray = (int[])this.aC.elementAt(n2);
            int[] nArray2 = nArray;
            nArray[3] = nArray[3] + 1;
            if (nArray2[3] < aB[nArray2[0]].length / 2) continue;
            this.aC.removeElementAt(n2);
            this.aD.removeElementAt(n2);
            --n2;
        }
        return n2 <= 0;
    }

    private boolean T() {
        if (this.aa != null) {
            if (this.aa[2] != -1) {
                return false;
            }
            return this.Z[this.Z.length - 1] == this.T;
        }
        return !this.ab;
    }

    private boolean U() {
        if (a.b.c.c[1][this.r.E][3] == 0) {
            this.d.k = 0;
            if (((i)this.r.q).s() == 0) {
                this.d.a((i)this.r.q);
            } else {
                this.d.b((i)this.r.q);
            }
            ((i)this.r.q).a((byte)0, true);
            return true;
        }
        if (((i)this.r.q).q() == 3) {
            return true;
        }
        if (!this.aF) {
            i i2;
            int n2 = this.r.t();
            if (this.r.s() == 0 && A.b((byte)4, (byte)0) == 2 && A.b((byte)4, (byte)1) == 1) {
                n2 += a.b.c.c[2][4][6];
            }
            n2 = this.r.m(4) ? ((i)this.r.q).t() - (n2 - this.r.x[4][1]) << 1 : ((i)this.r.q).t() - n2 << 1;
            if (this.r.f((byte)9)) {
                n2 = 0;
            }
            if (n2 <= 0) {
                n2 = 0;
            } else if (n2 >= 20) {
                n2 = 20;
            }
            if (a.e.a(100) >= n2) {
                ((i)this.r.q).k(this.aa[0]);
                if (this.r.f((byte)10)) {
                    int n3 = 1;
                    i i3 = (i)this.r.q;
                    if (i3.e[n3] <= a.b.c.c[3][10][5]) {
                        short s = a.b.c.c[3][10][5];
                        n3 = 1;
                        i3 = (i)this.r.q;
                        i3.e[n3] = s;
                    }
                }
                if (this.aa[1] == 1) {
                    i i4 = (i)this.r.q;
                    i2 = i4;
                    i2 = (i)this.r.q;
                    this.a("-" + this.aa[0], (byte)0, 1, ((i)this.r.q).s(), i4.j, i2.k, 15, 19);
                } else {
                    i i5 = (i)this.r.q;
                    i2 = i5;
                    i2 = (i)this.r.q;
                    this.a("-" + this.aa[0], (byte)0, 0, ((i)this.r.q).s(), i5.j, i2.k, 9, 12);
                }
                if (this.aa[2] != -1) {
                    i i6 = (i)this.r.q;
                    i2 = i6;
                    i2 = (i)this.r.q;
                    this.a(a.a.c(a.b.c.c[7][this.aa[2]][0]), (byte)1, 0, ((i)this.r.q).s(), i6.j, i2.k, 9, 12);
                }
            } else {
                i i7 = (i)this.r.q;
                i2 = i7;
                i2 = (i)this.r.q;
                this.a("N\u00e9 tr\u00e1nh", (byte)1, 0, ((i)this.r.q).s(), i7.j, i2.k, 9, 12);
            }
            this.aF = true;
            this.d.k = 0;
            if (((i)this.r.q).s() == 0) {
                this.d.a((i)this.r.q);
            } else {
                this.d.b((i)this.r.q);
            }
        }
        boolean bl = this.S();
        if (((i)this.r.q).s() == 0) {
            if (this.d.a((i)this.r.q, false) && bl) {
                this.R();
                this.aF = false;
                return true;
            }
        } else if (this.d.b((i)this.r.q, false) && bl) {
            this.R();
            this.aF = false;
            return true;
        }
        return false;
    }

    private boolean a(i i2, boolean bl, int n2) {
        if (i2.s() == 0) {
            int n3 = 1;
            i i3 = i2;
            if (n2 < i3.e[n3]) {
                if (this.d.a(i2, true) && bl) {
                    this.aF = false;
                    return true;
                }
            } else if (this.d.a(i2, false) && bl) {
                this.aF = false;
                return true;
            }
        } else {
            int n4 = 1;
            i i4 = i2;
            if (n2 < i4.e[n4]) {
                if (this.d.b(i2, true) && bl) {
                    this.aF = false;
                    return true;
                }
            } else if (this.d.b(i2, false) && bl) {
                this.aF = false;
                return true;
            }
        }
        return false;
    }

    private boolean V() {
        short s;
        if (!this.aF) {
            i i2;
            int n2;
            s = 0;
            this.aG = a.b.c.c[1][this.r.E][9] == 0 ? this.r.O() : ((i)this.r.q).O();
            byte by = this.r.E;
            switch (by) {
                case 11: 
                case 17: {
                    s = (short)(this.r.C() * a.b.c.c[1][by][8] / 100);
                    n2 = 1;
                    i2 = this.r;
                    this.r.u(i2.e[n2]);
                    if (s <= 0) {
                        s = 1;
                    }
                    this.r.l(s);
                    break;
                }
                case 21: 
                case 27: 
                case 42: 
                case 48: 
                case 62: 
                case 68: {
                    this.r.a((byte)a.b.c.c[1][by][7], -1, (int)by);
                    break;
                }
                case 52: 
                case 58: {
                    if (this.ab) {
                        s = (short)(this.aa[0] * a.b.c.c[1][by][8] / 100);
                        n2 = 1;
                        i2 = this.r;
                        this.r.u(i2.e[n2]);
                        this.r.l(this.aa[0] * a.b.c.c[1][by][8] / 100);
                    }
                }
                case 64: {
                    this.r.a((byte)a.b.c.c[1][by][7], (int)this.r.J, (int)by);
                    break;
                }
                default: {
                    if (a.b.c.c[1][by][6] != 1) break;
                    s = (short)((i)this.r.q).a((byte)a.b.c.c[1][by][7], -1, (int)by);
                }
            }
            byte by2 = (byte)a.b.c.c[1][by][6];
            if (a.b.c.c[1][this.r.E][9] == 0) {
                short s2;
                if (this.r.f((byte)8) && a.e.a(100) <= a.b.c.c[3][8][5]) {
                    n2 = 1;
                    i2 = this.r;
                    this.r.u(i2.e[n2]);
                    this.r.l((short)(this.aa[0] * a.b.c.c[3][8][6] / 100));
                }
                if (((i)this.r.q).m(2)) {
                    int n3 = this.aa[0] * ((i)this.r.q).w[2][2] / 100;
                    this.r.k(n3);
                }
                if (((i)this.r.q).m(5) && (s2 = this.r.L[5]) > 0) {
                    this.r.k(s2);
                    this.r.L[5] = 0;
                }
                n2 = 1;
                i2 = this.r;
                if (i2.e[n2] < this.aG) {
                    i i3 = this.r;
                    n2 = 1;
                    i2 = i3;
                    i i4 = this.r;
                    i2 = i4;
                    i2 = this.r;
                    this.a("" + (i3.e[n2] - this.aG), (byte)0, 0, this.r.s(), i4.j, i2.k, 9, 12);
                } else if (s > 0) {
                    i i5 = this.r;
                    i2 = i5;
                    i2 = this.r;
                    this.a("+" + s, (byte)0, 2, this.r.s(), i5.j, i2.k, 9, 12);
                }
            } else if (s > 0) {
                i i6 = (i)this.r.q;
                i2 = i6;
                i2 = (i)this.r.q;
                this.a("+" + s, (byte)0, 2, ((i)this.r.q).s(), i6.j, i2.k, 9, 12);
            }
            if (by2 == 1) {
                short s3 = a.b.c.c[1][by][7];
                switch (by) {
                    case 21: 
                    case 27: 
                    case 42: 
                    case 48: 
                    case 62: 
                    case 64: 
                    case 68: {
                        i i7 = this.r;
                        i i8 = i7;
                        i8 = this.r;
                        this.a(a.a.c(a.b.c.c[6][s3][0]), (byte)1, 2, this.r.s(), i7.j, i8.k, 9, 12);
                        break;
                    }
                    default: {
                        i i9 = (i)this.r.q;
                        i i10 = i9;
                        i10 = (i)this.r.q;
                        this.a(a.a.c(a.b.c.c[6][s3][0]), (byte)1, 2, ((i)this.r.q).s(), i9.j, i10.k, 9, 12);
                    }
                }
            }
            if (a.b.c.c[1][this.r.E][9] == 0) {
                this.d.k = 0;
                if (this.r.s() == 0) {
                    this.d.a(this.r);
                } else {
                    this.d.b(this.r);
                }
            } else {
                this.d.k = 0;
                if (((i)this.r.q).s() == 0) {
                    this.d.a((i)this.r.q);
                } else {
                    this.d.b((i)this.r.q);
                }
            }
            this.aF = true;
        }
        s = this.S();
        if (((i)this.r.q).s() != this.r.s() || this.r.p(9)) {
            return this.a(this.r, s != 0, this.aG);
        }
        return this.a((i)this.r.q, s != 0, this.aG);
    }

    private static void W() {
        int n2;
        for (n2 = 0; n2 < t.size(); ++n2) {
            if (((i)t.elementAt(n2)).T()) {
                ((i)t.elementAt(n2)).g(((i)game.a.t.elementAt((int)n2)).C);
                ((i)game.a.t.elementAt((int)n2)).C = 0;
                ((i)t.elementAt(n2)).e(false);
                continue;
            }
            t.removeElementAt(n2);
            --n2;
        }
        if (A.b((byte)0, (byte)0) == 2 && A.b((byte)0, (byte)1) == 1) {
            for (n2 = 0; n2 < game.a.A.B; ++n2) {
                if (!game.a.A.A[n2].T()) continue;
                int n3 = 1;
                i i2 = game.a.A.A[n2];
                game.a.A.A[n2].u(i2.e[n3] + a.b.c.c[0][game.a.A.A[n2].r()][5] * a.b.c.c[2][0][6] / 100);
                game.a.A.A[n2].l(a.b.c.c[0][game.a.A.A[n2].r()][5] * a.b.c.c[2][0][6] / 100);
            }
        }
    }

    private void h(i i2) {
        int n2;
        int n3;
        int n4 = i2.t();
        int n5 = 0;
        int n6 = ((n4 << 1) * n4 + 50) * this.aH[i2.d[n5] - 1] / 10 + 400;
        n5 = I.size();
        int[] nArray = new int[n5];
        i i3 = null;
        byte by = 0;
        for (n3 = 0; n3 < n5; ++n3) {
            i3 = (i)I.elementAt(n3);
            if (i3.t() - n4 >= 6) {
                by = this.aJ[6];
            } else if (i3.t() - n4 > 0) {
                by = this.aJ[i3.t() - n4];
            } else if (i3.t() == n4) {
                by = this.aJ[1];
            } else if (i3.t() < n4) {
                by = this.aJ[0];
            }
            n2 = n6 / n5 * this.aI[n5 - 1] * by / 1000;
            if (i3.f((byte)5)) {
                n2 = n2 * (a.b.c.c[3][5][5] + 100) / 100;
            }
            i3.C += n2;
            nArray[n3] = n2;
            if (t.contains(i3)) continue;
            t.addElement(i3);
        }
        for (n3 = 0; n3 < game.a.A.B; ++n3) {
            if (!this.n(n3).T() || I.contains(this.n(n3))) continue;
            if (A.b((byte)7, (byte)0) == 2) {
                if (i3.t() - n4 >= 6) {
                    by = this.aJ[6];
                } else if (i3.t() - n4 > 0) {
                    by = this.aJ[i3.t() - n4];
                } else if (i3.t() == n4) {
                    by = this.aJ[1];
                } else if (i3.t() < n4) {
                    by = this.aJ[0];
                }
                n2 = n6 / n5 * this.aI[n5 - 1] * by / 3000;
                this.n((int)n3).C += n2;
                this.n(n3).c();
                if (t.contains(this.n(n3))) continue;
                t.addElement(this.n(n3));
                continue;
            }
            if (!this.n(n3).f((byte)6)) continue;
            if (i3.t() - n4 >= 6) {
                by = this.aJ[6];
            } else if (i3.t() - n4 > 0) {
                by = this.aJ[i3.t() - n4];
            } else if (i3.t() == n4) {
                by = this.aJ[1];
            } else if (i3.t() < n4) {
                by = this.aJ[0];
            }
            n2 = n6 / n5 * this.aI[n5 - 1] * by / 1000;
            this.n((int)n3).C += n2;
            this.n(n3).c();
            if (t.contains(this.n(n3))) continue;
            t.addElement(this.n(n3));
        }
        for (n6 = 0; n6 < I.size(); ++n6) {
            i i4 = (i)I.elementAt(n6);
            if (i4.L()) continue;
            I.removeElement(i4);
        }
        if (A.l(0)) {
            if (game.a.A.J == 0) {
                if (i3.t() >= 30 && ++game.l.A >= 10) {
                    game.l.A = 10;
                    return;
                }
            } else if (i3.t() >= 40 && ++game.l.A >= 30) {
                game.l.A = 30;
            }
        }
    }

    public final int m(int n2) {
        if (n2 == 0) {
            return 100;
        }
        int n3 = 0;
        if (((i)this.r.q).m(1)) {
            n3 = 1;
        }
        if (((i)this.r.q).m(2)) {
            n3 = 2;
        }
        if (((i)this.r.q).m(10)) {
            n3 = 3;
        }
        if (this.r.f((byte)11)) {
            n3 = 4;
        }
        int n4 = 1;
        int n5 = 1;
        i i2 = (i)this.r.q;
        short s = i2.e[n5];
        n5 = 1;
        i2 = (i)this.r.q;
        short s2 = i2.d[n5];
        if (s <= s2 * 15 / 100) {
            n4 = 85;
        } else if (s <= s2 * 50 / 100) {
            n4 = 45;
        } else if (s <= s2) {
            n4 = 20;
        }
        n4 = n4 * a.b.c.c[4][n2][6] / 100;
        int[] nArray = new int[]{110, 100, 95, 80, 70};
        n5 = 0;
        i i3 = (i)this.r.q;
        n4 = n4 * nArray[i3.d[n5] - 1] / 100;
        nArray = new int[]{10, 11, 12, 12, 12};
        n4 = n4 * nArray[n3] / 10;
        if (this.r.f((byte)11)) {
            n4 = n4 * (100 + a.b.c.c[3][11][5]) / 100;
        }
        Object[] objectArray = new int[]{1000, 500, 1, 1000};
        n4 = n4 * objectArray[a.b.c.c[0][((i)this.r.q).r()][22]] / 1000;
        if (((i)this.r.q).t() >= 20 && n4 >= (objectArray = (Object[])new byte[]{0, 15, 35, 65})[n2]) {
            n4 = objectArray[n2];
        }
        if (n4 >= 100) {
            n4 = 100;
        } else if (n4 <= 0) {
            n4 = 1;
        }
        return n4;
    }

    public final i n(int n2) {
        if (n2 > this.p.length - 1) {
            return null;
        }
        return game.a.A.A[n2];
    }

    public final i o(int n2) {
        if (n2 > this.p.length - 1) {
            return null;
        }
        return game.a.A.A[this.p[n2]];
    }

    public final void a(int[][] nArray) {
        this.D = nArray;
    }

    public final int H() {
        return this.D.length;
    }

    public final int p(int n2) {
        return this.D[0][0];
    }

    public final void q() {
        switch (f) {
            case 0: {
                if (g == 0) {
                    if (this.r == null) break;
                    int n2 = 1;
                    i i2 = this.n[0];
                    int n3 = i2.d[n2] * 50 / 100;
                    n2 = 1;
                    i2 = this.n[0];
                    if (i2.e[n2] <= n3) {
                        game.a.b(0, 1);
                        g = (byte)(g + 1);
                        this.d.c("Di L\u1eb7c th\u1ecf th\u1ecf \u0111\u00e3 b\u1ecb th\u01b0\u01a1ng, nhanh s\u1eed d\u1ee5ng #2 phong \u1ea5n c\u1ea7u #1 ti\u1ebfn h\u00e0nh b\u1eaft \u0111\u01b0\u1ee3c a");
                    }
                    return;
                }
                if (g == 1) {
                    if (!this.d.aB()) break;
                    g = (byte)(g + 1);
                    this.d.a = 1;
                    game.a.b(2, 1);
                    game.a.b(1, 1);
                    this.d.aj();
                    this.d.c("H\u00e3y nh\u1ea5n #2n\u00fat 5");
                    return;
                }
                if (g == 3) {
                    g = (byte)(g + 1);
                    game.a.b(2, 0);
                    game.a.b(1, 1);
                    this.d.c("H\u00e3y l\u1ef1a ch\u1ecdn phong \u1ea5n c\u1ea7u");
                    return;
                }
                if (g == 5) {
                    g = (byte)(g + 1);
                    this.d.c("\u0110\u00e1ng ti\u1ebfc \u0111\u00e3 b\u1eaft tr\u01b0\u1ee3t, th\u1eed d\u00f9ng lo\u1ea1i x\u1ecbn #2T\u1ea5t tr\u00fang c\u1ea7u#1 xem sao!");
                    return;
                }
                if (g == 6) {
                    if (!this.d.aB()) break;
                    game.a.b(1, 0);
                    this.d.a = 1;
                    this.d.aj();
                    this.d.b = 0;
                    g = (byte)(g + 1);
                    this.a((byte)21);
                    return;
                }
                if (g != 8) break;
                game.a.b(1, -1);
                game.a.b(0, 0);
                f = (byte)-1;
                g = 0;
                return;
            }
            case 2: {
                if (g == 0) {
                    game.a.b(0, 0);
                    g = (byte)(g + 1);
                    this.d.c("Tranh th\u1ee7 th\u1eddi gian l\u1ef1a ch\u1ecdn #2T\u1ea5t tr\u00fang c\u1ea7u#1 \u0111\u1ec3 b\u1eaft s\u1ee7ng v\u1eadt");
                    return;
                }
                if (g != 2) break;
                if (this.r != null) {
                    int n4 = 1;
                    i i3 = this.n[0];
                    int n5 = i3.d[n4] * 50 / 100 + 2;
                    n4 = 1;
                    i3 = this.n[0];
                    if (i3.e[n4] <= n5) {
                        g = (byte)(g - 1);
                        this.d.c("L\u1ef1a ch\u1ecdn #2T\u1ea5t tr\u00fang c\u1ea7u#1 \u0111\u1ec3 b\u1eaft s\u1ee7ng v\u1eadt");
                    }
                }
                if (A.a((byte)1, 29) != 2) break;
                f = (byte)-1;
                g = 0;
                return;
            }
            case 5: {
                if (g != 0) break;
                this.d.a = 1;
                g = (byte)(g + 1);
                this.d.aj();
                this.d.c("Tranh th\u1ee7 th\u1eddi gian l\u1ef1a ch\u1ecdn #2T\u1ea5t tr\u00fang c\u1ea7u#1 \u0111\u1ec3 b\u1eaft s\u1ee7ng v\u1eadt");
            }
        }
    }

    public final void r() {
        switch (f) {
            case 0: {
                if (g != 2 && g != 4 && g != 7) break;
                g = (byte)(g + 1);
                return;
            }
            case 5: {
                if (g == 1) {
                    g = (byte)(g + 1);
                    game.a.b(1, 0);
                    game.a.b(0, 1);
                    return;
                }
                if (g != 2) break;
                game.a.b(0, 0);
                f = (byte)-1;
                g = 0;
            }
        }
    }

    static {
        v = 0;
    }
}

