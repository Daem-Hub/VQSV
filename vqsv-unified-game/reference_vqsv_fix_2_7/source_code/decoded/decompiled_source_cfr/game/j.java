/*
 * Decompiled with CFR 0.152.
 */
package game;

import a.a.g;
import a.b.a;
import a.b.b;
import a.b.c;
import a.b.d;
import game.e;
import game.f;
import game.h;
import game.i;
import game.l;
import java.util.Vector;

public final class j
extends g {
    private static j Z;
    public int u;
    private int aa;
    private int ab;
    public byte v;
    private int ac;
    private boolean ad;
    public int w;
    private int ae;
    private int af;
    public int x;
    public int y;
    public boolean z;
    public i[] A;
    public int B;
    public byte[][] C;
    public byte[][] D;
    public byte[][] E;
    public byte[] F;
    public byte G;
    public byte H;
    public byte I;
    public byte J;
    public Vector K;
    public Vector L;
    public Vector M;
    public Vector N;
    public Vector O;
    public Vector P;
    public byte[] Q;
    public byte[] R;
    public short[] S;
    public Vector T;
    public boolean[] U;
    public static boolean V;
    public Vector W;
    private int ag;
    private int ah;
    public int[] X = new int[]{0, 16, 32, 48, 64, 76, 88};
    public int[] Y = new int[]{16, 16, 16, 16, 12, 12, 12};
    private static byte[][] ai;
    private static short[][] aj;
    private Vector ak = null;

    public static j p() {
        if (Z == null) {
            Z = new j();
        }
        return Z;
    }

    public final void q() {
        this.B = 0;
        Z = null;
    }

    public j() {
        int n2;
        this.d = new short[3];
        this.e = new short[3];
        this.A = new i[6];
        this.C = new byte[8][2];
        this.U = new boolean[21];
        this.K = new Vector();
        this.L = new Vector();
        int[] nArray = new int[]{0, 0, 1};
        this.L.addElement(nArray);
        this.M = new Vector();
        this.N = new Vector();
        this.O = new Vector();
        this.P = new Vector();
        this.W = new Vector();
        this.D = new byte[7][];
        this.F = new byte[7];
        this.E = new byte[7][];
        this.S = new short[]{-1, -1, -1, -1, -1};
        for (n2 = 0; n2 < this.E.length; ++n2) {
            this.E[n2] = new byte[this.Y[n2]];
            for (int i2 = 0; i2 < this.E[n2].length; ++i2) {
                this.E[n2][i2] = -1;
            }
        }
        this.D[0] = new byte[16];
        this.D[1] = new byte[16];
        this.D[2] = new byte[16];
        this.D[3] = new byte[16];
        this.D[4] = new byte[12];
        this.D[5] = new byte[12];
        this.D[6] = new byte[12];
        this.Q = new byte[4];
        this.R = new byte[4];
        this.v = 0;
        for (n2 = 0; n2 < 8; ++n2) {
            this.C[n2][0] = 0;
            this.C[n2][1] = 0;
        }
        for (n2 = 0; n2 < 4; ++n2) {
            this.Q[n2] = 0;
        }
        this.ag = 1000;
        this.ah = 0;
        this.u = -1;
        this.z = false;
    }

    public final void a(short[] sArray) {
        if (this.u == -1) {
            this.a(0, false);
        }
        this.j = sArray[0];
        this.k = sArray[1];
        this.h(this.u);
        j j2 = this;
        j2.a.c();
        this.a((byte)0, (byte)sArray[2]);
        short s = sArray[3];
        int n2 = 0;
        j2 = this;
        j2.d[n2] = s;
        s = sArray[4];
        n2 = 1;
        j2 = this;
        j2.d[n2] = s;
        s = sArray[5];
        n2 = 2;
        j2 = this;
        j2.d[n2] = s;
        if (this.u == -1) {
            this.h();
        }
        this.t = 1;
        this.ae = sArray[6];
        this.af = sArray[7];
        this.w = this.D();
        if (this.v == 1) {
            this.a(0, 107, true);
        }
        if (this.b == null) {
            this.b = new g();
            this.b.a(337, false);
        }
        this.b.b(this.j, this.k);
        if (this.a.a == 4) {
            this.b.a((byte)0, (byte)0, false);
        } else {
            this.b.a((byte)1, (byte)0, false);
        }
        this.b.c();
        Object var3_5 = null;
        j2 = this;
        this.q = var3_5;
        this.z = true;
    }

    public final void r() {
        if (this.o() && this.q.i() != 0) {
            this.a(((g)this.q).a, this.a);
            return;
        }
        switch (this.i) {
            case 0: {
                if (this.Q[2] == 2 || !this.N()) break;
                this.a((byte)3, this.o);
                return;
            }
            case 1: {
                if (this.Q[2] == 2) {
                    boolean bl;
                    int n2 = this.k;
                    int n3 = this.j;
                    j j2 = this;
                    switch (j2.o) {
                        case 2: {
                            bl = a.b.d.a().b(n3, n2 - 25 - j2.aa);
                            break;
                        }
                        case 0: {
                            bl = a.b.d.a().b(n3, n2 - 25 + j2.aa);
                            break;
                        }
                        case 3: {
                            bl = a.b.d.a().b(n3 - j2.aa, n2 - 25);
                            break;
                        }
                        case 1: {
                            bl = a.b.d.a().b(n3 + j2.aa, n2 - 25);
                            break;
                        }
                        default: {
                            bl = false;
                        }
                    }
                    if (bl) break;
                    game.l.E = this.J();
                    Object var2_3 = null;
                    j2 = this;
                    this.q = var2_3;
                    for (int i2 = 0; i2 < game.l.B().n.length; ++i2) {
                        this.y(i2);
                    }
                    int n4 = 0;
                    j j3 = this;
                    this.a((int)j3.e[n4]);
                    if (this.ab < 8) {
                        n4 = 0;
                        j3 = this;
                        this.ab += j3.e[n4];
                    } else {
                        this.ab = 4;
                    }
                    this.O();
                    return;
                }
                game.l.E = this.J();
                if (this.K() && this.L()) {
                    this.a(this.aa);
                    this.ab = this.ab < 8 ? (this.ab += this.aa) : 4;
                    this.O();
                    return;
                }
                this.ab = 0;
                return;
            }
            case 2: {
                if (this.K() && this.L()) {
                    this.a(this.aa);
                    this.ab = this.ab < 8 ? (this.ab += this.aa) : 4;
                    this.O();
                    return;
                }
                this.ab = 0;
                return;
            }
            case 4: {
                return;
            }
            case 5: {
                int n5;
                if (this.ac < 16) {
                    switch (this.n) {
                        case 1: 
                        case 3: {
                            g g2;
                            if (this.n == 3) {
                                g2 = (h)this.q;
                                if (this.j > g2.j) {
                                    this.a((int)this.n, 4);
                                }
                            } else {
                                g2 = (h)this.q;
                                if (this.j < g2.j) {
                                    this.a((int)this.n, 4);
                                }
                            }
                            g2 = (h)this.q;
                            if (this.m > g2.k - 16) {
                                g2 = (h)this.q;
                                if (this.k <= g2.k - 16) {
                                    g2 = (h)this.q;
                                    int n6 = g2.k - 16;
                                    g2 = this;
                                    this.k = n6;
                                    break;
                                }
                                this.a(2, 4);
                                break;
                            }
                            g2 = (h)this.q;
                            if (this.m >= g2.k - 16) break;
                            g2 = (h)this.q;
                            if (this.k >= g2.k - 16) {
                                g2 = (h)this.q;
                                int n7 = g2.k - 16;
                                g2 = this;
                                this.k = n7;
                                break;
                            }
                            this.a(0, 4);
                            break;
                        }
                        case 0: 
                        case 2: {
                            if (this.n == 2) {
                                if (this.k > ((h)this.q).n() - 16) {
                                    this.a((int)this.n, 4);
                                }
                            } else if (this.k < ((h)this.q).n() - 16) {
                                this.a((int)this.n, 4);
                            }
                            if (this.l > ((h)this.q).m()) {
                                if (this.j <= ((h)this.q).m()) {
                                    this.c(((h)this.q).m());
                                    break;
                                }
                                this.a(3, 4);
                                break;
                            }
                            if (this.l >= ((h)this.q).m()) break;
                            if (this.j >= ((h)this.q).m()) {
                                this.c(((h)this.q).m());
                                break;
                            }
                            this.a(1, 4);
                        }
                    }
                    if (this.ac % 4 == 3) {
                        this.a((byte)1, (byte)-1, false);
                    } else {
                        this.a((byte)(this.ac % 4), (byte)-1, false);
                    }
                    this.d((byte)(this.ac % 4));
                    ++this.ac;
                    return;
                }
                int n8 = 0;
                for (n5 = 0; n5 < ai.length; ++n5) {
                    if (game.l.B().p != ai[n5][0] || game.l.B().q != ai[n5][1]) continue;
                    game.l.B().p = ai[n5][2];
                    game.l.B().q = ai[n5][3];
                    n8 = ai[n5][4];
                    break;
                }
                for (n5 = 0; n5 < aj[n8].length / 4; ++n5) {
                    if (((h)this.q).J < aj[n8][n5 << 2] || ((h)this.q).J > aj[n8][(n5 << 2) + 1]) continue;
                    game.l.B().r = aj[n8][(n5 << 2) + 2];
                    game.l.B().s = aj[n8][(n5 << 2) + 3];
                    break;
                }
                V = true;
                game.l.B().t = -1;
                game.f.B().a((byte)9);
                return;
            }
            case 6: {
                if (this.M()) {
                    this.a((int)this.b((byte)2));
                    return;
                }
                this.a((int)this.c((byte)1));
                this.a((byte)0, this.o);
                return;
            }
            case 7: {
                if (((h)this.q).A == 0) {
                    if (this.ac < 7) {
                        this.a(4);
                        ++this.ac;
                        return;
                    }
                    if (this.ac != 7) break;
                    if (this.o == 3) {
                        this.a((byte)1, (byte)-1, false);
                    } else {
                        this.a(this.o, (byte)-1, false);
                    }
                    this.d(this.o);
                    ((h)this.q).r();
                    ++this.ac;
                    return;
                }
                if (((h)this.q).A != 2) break;
                if (this.ac < 8 && this.ac > 0) {
                    this.a(4);
                    --this.ac;
                    return;
                }
                if (this.ac == 8) {
                    this.a((byte)7, this.o);
                    --this.ac;
                    return;
                }
                ((h)this.q).A = 0;
                ((h)this.q).a(null);
                this.a((a)null);
                this.a((byte)0, this.o);
                return;
            }
            case 8: {
                if (this.ac < 16) {
                    if (this.ac % 4 == 3) {
                        this.a((byte)1, (byte)-1, false);
                    } else {
                        this.a((byte)(this.ac % 4), (byte)-1, false);
                    }
                    this.d((byte)(this.ac % 4));
                    ++this.ac;
                    return;
                }
                int n9 = game.l.B().n[game.l.B().t].j - game.l.B().n[game.l.B().t].j % this.b((byte)2);
                int n10 = game.l.B().n[game.l.B().t].k - game.l.B().n[game.l.B().t].k % this.b((byte)2);
                this.b(n9, n10);
                this.b.b(n9, n10);
                this.a((byte)0, game.l.B().n[game.l.B().t].D);
                this.a(32);
                a.b.b.a().a(8);
                a.b.b.a().a(false);
                return;
            }
            case 9: {
                if (this.ac < 16) {
                    if (this.ac % 4 == 3) {
                        this.a((byte)1, (byte)-1, false);
                    } else {
                        this.a((byte)(this.ac % 4), (byte)-1, false);
                    }
                    this.d((byte)(this.ac % 4));
                    ++this.ac;
                    return;
                }
                int n11 = game.l.B().n[game.l.B().t].j - game.l.B().n[game.l.B().t].j % this.b((byte)2);
                int n12 = game.l.B().n[game.l.B().t].k - game.l.B().n[game.l.B().t].k % this.b((byte)2);
                this.b(n11, n12);
                this.b.b(n11, n12);
                this.a((byte)10, this.o);
                a.b.b.a().a(8);
                a.b.b.a().a(false);
                return;
            }
            case 10: {
                if (this.ac > 0) {
                    if (this.ac % 4 == 3) {
                        this.a((byte)1, (byte)-1, false);
                    } else {
                        this.a((byte)(this.ac % 4), (byte)-1, false);
                    }
                    this.d((byte)(this.ac % 4));
                    --this.ac;
                    return;
                }
                this.a((byte)0, game.l.B().n[game.l.B().t].D);
                this.a(32);
            }
        }
    }

    public final void a(byte by, byte n2) {
        block11: while (true) {
            switch (by) {
                case 0: {
                    if (this.Q[2] != 2 && this.N()) {
                        by = (byte)3;
                        continue block11;
                    }
                    if (n2 == 3) {
                        this.a((byte)1, (byte)-1, false);
                    } else {
                        this.a((byte)n2, (byte)-1, false);
                    }
                    j j2 = this;
                    this.o = (byte)n2;
                    break block11;
                }
                case 1: {
                    if (this.Q[2] != 2 && this.N()) {
                        by = (byte)2;
                        continue block11;
                    }
                    j j3 = this;
                    if (j3.a.g() < 6) {
                        if (n2 == 3) {
                            this.a((byte)4, (byte)-1, (byte)n2);
                            break block11;
                        }
                        this.a((byte)(n2 + 3), (byte)-1, (byte)n2);
                        break block11;
                    }
                    if (n2 == 3) {
                        this.a((byte)(by * 3 + 1), (byte)-1, false);
                    } else {
                        this.a((byte)(by * 3 + n2), (byte)-1, false);
                    }
                    j3 = this;
                    this.o = (byte)n2;
                    break block11;
                }
                case 2: {
                    j j4;
                    if (this.N()) {
                        j4 = this;
                        if (j4.a.g() < 9) {
                            if (n2 == 3) {
                                this.a((byte)7, (byte)-1, (byte)n2);
                            } else {
                                this.a((byte)(n2 + 6), (byte)-1, (byte)n2);
                            }
                        } else if (n2 == 3) {
                            this.a((byte)(by * 3 + 1), (byte)-1, false);
                        } else {
                            this.a((byte)(by * 3 + n2), (byte)-1, false);
                        }
                    } else if (n2 == 3) {
                        this.a((byte)(this.i * 3 + 1), (byte)-1, false);
                    } else {
                        this.a((byte)(this.i * 3 + n2), (byte)-1, false);
                    }
                    j4 = this;
                    this.o = (byte)n2;
                    break block11;
                }
                case 3: {
                    if (n2 == 3) {
                        this.a((byte)(by * 3 + 1), (byte)-1, false);
                    } else {
                        this.a((byte)(by * 3 + n2), (byte)-1, false);
                    }
                    j j5 = this;
                    this.o = (byte)n2;
                    break block11;
                }
                case 4: {
                    this.a((byte)(by * 3), (byte)-2, false);
                    j j6 = this;
                    this.o = n2;
                    this.ab = 0;
                    break block11;
                }
                case 5: {
                    this.ac = 0;
                    int n3 = n2;
                    j j7 = this;
                    this.n = n3;
                    int n4 = this.k;
                    n3 = this.j;
                    j7 = this;
                    this.l = n3;
                    j7.m = n4;
                    if (n2 == 3) {
                        this.a((byte)1, (byte)-1, false);
                    } else {
                        this.a((byte)n2, (byte)-1, false);
                    }
                    j7 = this;
                    this.o = (byte)n2;
                    break block11;
                }
                case 6: {
                    if (n2 == 3) {
                        this.a((byte)1, (byte)-1, false);
                    } else {
                        this.a((byte)n2, (byte)-1, false);
                    }
                    j j8 = this;
                    this.o = n2;
                    break block11;
                }
                case 7: {
                    if (n2 == 3) {
                        this.a((byte)4, (byte)-1, false);
                    } else {
                        this.a((byte)(n2 + 3), (byte)-1, false);
                    }
                    j j9 = this;
                    this.o = (byte)n2;
                    break block11;
                }
                case 8: 
                case 9: {
                    this.ac = 0;
                    j j10 = this;
                    this.o = n2;
                }
            }
            break;
        }
        this.i = by;
        if (this.i == 0 || this.i == 1) {
            if (game.l.B().y != null) {
                game.l.B().y.b(true);
                return;
            }
        } else if (game.l.B().y != null) {
            game.l.B().y.b(false);
        }
    }

    public final boolean f(int n2) {
        return this.Q[n2] != 0;
    }

    public final boolean g(int n2) {
        return this.R[n2] != 1;
    }

    public final boolean s() {
        return this.u != 2 || a.b.d.a().a(0, this.j + 7, this.k + 7) == 0 && a.b.d.a().a(0, this.j - 8, this.k - 8) == 0;
    }

    public final void h(int n2) {
        int n3;
        if (n2 == -1) {
            return;
        }
        this.Q[n2] = 2;
        this.a.b();
        this.a(n2 + 1, false);
        j j2 = this;
        j2.a.c();
        this.a((byte)0, this.o);
        if (this.v == 1) {
            this.a(1, 107, true);
        }
        if (this.Q[n2] == 2 && n2 == 0 || this.Q[n2] == 2 && n2 == 1) {
            int n4 = 8;
            n3 = 0;
            j2 = this;
            j2.e[n3] = n4;
        } else {
            int n5 = 4;
            n3 = 0;
            j2 = this;
            j2.e[n3] = n5;
        }
        if (this.Q[2] == 2 && game.l.B().y != null) {
            game.l.B().y.d();
        }
        this.u = n2;
        n3 = 0;
        j2 = this;
        this.aa = j2.e[n3];
    }

    public final void t() {
        this.a.b();
        this.a(0, false);
        j j2 = this;
        j2.a.c();
        for (int i2 = 0; i2 < 4; ++i2) {
            if (this.Q[i2] != 2) continue;
            this.Q[i2] = 1;
        }
        if (this.v == 1) {
            this.a(0, 107, true);
        }
        if (game.l.B().y != null) {
            game.l.B().y.c();
        }
        int n2 = 0;
        j j3 = this;
        short s = j3.d[n2];
        n2 = 0;
        j3 = this;
        j3.e[n2] = s;
        this.u = -1;
    }

    public final void u() {
        this.v = 1;
        boolean bl = false;
        for (int i2 = 0; i2 < 4; ++i2) {
            if (this.Q[i2] != 2) continue;
            bl = true;
            break;
        }
        if (bl) {
            this.a(1, 107, true);
            return;
        }
        this.a(0, 107, true);
    }

    public final void v() {
        this.v = 0;
        boolean bl = false;
        for (int i2 = 0; i2 < 4; ++i2) {
            if (this.Q[i2] != 2) continue;
            bl = true;
            break;
        }
        if (bl) {
            this.a(1, 100, true);
            return;
        }
        this.a(0, 100, true);
    }

    private short J() {
        for (short s = 0; s < game.l.B().n.length; s = (short)((short)(s + 1))) {
            if (!game.l.B().n[s].k()) continue;
            if (!(game.l.B().n[s].a.a > 85 && game.l.B().n[s].a.a != 226 && game.l.B().n[s].a.a != 92 && game.l.B().n[s].a.a != 102 && game.l.B().n[s].a.a != 137 || game.l.B().n[s].u != 0 || game.l.B().n[s].w != 1 && game.l.B().n[s].w != 18 || !this.a(game.l.B().n[s], this.a.k(), game.l.B().n[s].a.k()))) {
                if (game.l.B().n[s].v() == 0) {
                    game.l.B().a((byte)13, game.l.B().n[s].j, game.l.B().n[s].k - 40, game.l.B().n[s]);
                    if (game.l.B().n[s].H != null) {
                        game.l.B().n[s].H.d();
                    }
                } else if (game.l.B().n[s].v() == 1) {
                    game.l.B().a((byte)13, game.l.B().n[s].j, game.l.B().n[s].k - 40, game.l.B().n[s]);
                    if (game.e.p != null && game.e.p.size() > 0) {
                        for (int i2 = 0; i2 < game.e.p.size(); ++i2) {
                            if (!((g)game.e.p.elementAt((int)i2)).q.equals(game.l.B().n[s])) continue;
                            ((g)game.e.p.elementAt(i2)).d();
                            break;
                        }
                    }
                } else {
                    game.l.B().a((byte)13, game.l.B().n[s].j, game.l.B().n[s].k - 40, game.l.B().n[s]);
                    if (game.l.B().n[s].x != 0) {
                        game.l.B().n[s].x();
                    }
                }
                return s;
            }
            if (game.l.B().n[s].u != 2 || !this.a(game.l.B().n[s], this.a.k(), game.l.B().n[s].a.k())) continue;
            game.l.B().m(s);
        }
        game.l.B().D();
        game.e.s = false;
        return -1;
    }

    private boolean y(int n2) {
        switch (game.l.B().n[n2].u) {
            case 3: {
                short[] sArray = game.l.B().n[n2].a.k();
                short s = sArray[0];
                short s2 = sArray[1];
                short s3 = (short)(sArray[2] + 16);
                short s4 = (short)(sArray[3] + 16);
                if (!game.l.B().n[n2].v || !this.a(game.l.B().n[n2], this.a.k(), new short[]{s, s2, s3, s4})) break;
                h h2 = game.l.B().n[n2];
                j j2 = this;
                this.q = h2;
            }
        }
        return true;
    }

    private boolean K() {
        g g2 = null;
        g g3 = this;
        this.q = g2;
        int n2 = 1;
        if (this.ak != null) {
            this.ak.removeAllElements();
        }
        block24: for (int i2 = 0; i2 < game.l.B().n.length; ++i2) {
            this.y(i2);
            if (!game.l.B().n[i2].v || !this.a(game.l.B().n[i2], this.a.k(), game.l.B().n[i2].a.k())) continue;
            switch (game.l.B().n[i2].u) {
                case 0: {
                    switch (game.l.B().n[i2].w) {
                        case 0: {
                            return false;
                        }
                        case 4: 
                        case 11: {
                            if (game.l.B().n[i2].i() == 2 || !game.l.B().n[i2].k()) break;
                            g2 = game.l.B().n[i2];
                            g3 = this;
                            this.q = g2;
                            return false;
                        }
                        case 5: {
                            if (game.l.B().n[i2].i() != 2) {
                                if (this.C[5][0] == 2) {
                                    g2 = game.l.B().n[i2];
                                    g3 = this;
                                    this.q = g2;
                                }
                                return false;
                            }
                        }
                        case 6: {
                            if (game.l.B().n[i2].i() == 2) break;
                            if (this.Q[3] != 2) {
                                if (this.C[2][0] == 2) {
                                    g2 = game.l.B().n[i2];
                                    g3 = this;
                                    this.q = g2;
                                    if (this.ak == null) {
                                        this.ak = new Vector();
                                    }
                                    this.ak.addElement(game.l.B().n[i2]);
                                    game.l.B().n[i2].f(20);
                                }
                                n2 = 0;
                                break;
                            }
                            game.l.B().n[i2].a((byte)1);
                            game.l.B().l.a(game.l.B().n[i2], 2);
                            break;
                        }
                        case 15: {
                            if (game.l.B().n[i2].i() == 2) break;
                            if (this.U[6]) {
                                g2 = game.l.B().n[i2];
                                g3 = this;
                                this.q = g2;
                                return false;
                            }
                            game.l.B().n[i2].a((byte)1);
                            game.l.B().l.a(game.l.B().n[i2], 2);
                            break;
                        }
                        case 7: {
                            if (game.l.B().n[i2].i() == 2) break;
                            if (this.Q[3] != 2) {
                                if (this.C[1][0] == 2) {
                                    g2 = game.l.B().n[i2];
                                    g3 = this;
                                    this.q = g2;
                                    if (this.ak == null) {
                                        this.ak = new Vector();
                                    }
                                    this.ak.addElement(game.l.B().n[i2]);
                                    game.l.B().n[i2].f(30);
                                }
                                n2 = 0;
                                break;
                            }
                            game.l.B().n[i2].a((byte)1);
                            game.l.B().l.a(game.l.B().n[i2], 2);
                            break;
                        }
                        case 8: {
                            if (!game.l.B().n[i2].k()) break;
                            if ((h)game.l.B().n[i2].q != null && ((h)game.l.B().n[i2].q).C > ((h)game.l.B().n[i2].q).B) {
                                return false;
                            }
                            block25: for (n2 = 0; n2 < game.l.B().n.length; ++n2) {
                                if (!game.l.B().n[n2].v || game.l.B().n[n2].equals(game.l.B().n[i2]) || game.l.B().n[n2].u != 0 || game.l.B().n[n2].w != 8 && game.l.B().n[n2].w != 11) continue;
                                switch (this.o) {
                                    case 2: {
                                        h h2;
                                        h h3;
                                        h h4;
                                        g3 = game.l.B().n[i2];
                                        g3 = game.l.B().n[i2];
                                        g3 = game.l.B().n[n2];
                                        g3 = game.l.B().n[n2];
                                        if (!a.e.a(h4.j, h3.k - 8, h2.j, g3.k, game.l.B().n[i2].a.k(), game.l.B().n[n2].a.k())) continue block25;
                                        return false;
                                    }
                                    case 0: {
                                        h h5;
                                        h h6;
                                        h h7;
                                        g3 = game.l.B().n[i2];
                                        g3 = game.l.B().n[i2];
                                        g3 = game.l.B().n[n2];
                                        g3 = game.l.B().n[n2];
                                        if (!a.e.a(h7.j, h6.k + 8, h5.j, g3.k, game.l.B().n[i2].a.k(), game.l.B().n[n2].a.k())) continue block25;
                                        return false;
                                    }
                                    case 3: {
                                        h h8;
                                        h h9;
                                        h h10;
                                        g3 = game.l.B().n[i2];
                                        g3 = game.l.B().n[i2];
                                        g3 = game.l.B().n[n2];
                                        g3 = game.l.B().n[n2];
                                        if (!a.e.a(h10.j - 8, h9.k, h8.j, g3.k, game.l.B().n[i2].a.k(), game.l.B().n[n2].a.k())) continue block25;
                                        return false;
                                    }
                                    case 1: {
                                        h h11;
                                        h h12;
                                        h h13;
                                        g3 = game.l.B().n[i2];
                                        g3 = game.l.B().n[i2];
                                        g3 = game.l.B().n[n2];
                                        g3 = game.l.B().n[n2];
                                        if (!a.e.a(h13.j + 8, h12.k, h11.j, g3.k, game.l.B().n[i2].a.k(), game.l.B().n[n2].a.k())) continue block25;
                                        return false;
                                    }
                                }
                            }
                            game.l.B().n[i2].a((byte)1);
                            byte by = this.o;
                            g3 = game.l.B().n[i2];
                            game.l.B().n[i2].o = by;
                            return false;
                        }
                        case 9: {
                            if (a.b.d.a().a(0, this.j, this.k) == 2 || a.b.d.a().a(0, this.j, this.k) == 1) {
                                return false;
                            }
                            if (this.o != 3 && this.o != 1) break;
                            this.ac = 0;
                            this.a((byte)7, this.o);
                            g2 = this;
                            g3 = game.l.B().n[i2];
                            game.l.B().n[i2].q = g2;
                            g2 = game.l.B().n[i2];
                            g3 = this;
                            this.q = g2;
                            return false;
                        }
                        case 10: {
                            if (a.b.d.a().a(0, this.j, this.k) == 2 || a.b.d.a().a(0, this.j, this.k) == 1) {
                                return false;
                            }
                            if (this.o != 0 && this.o != 2) break;
                            this.ac = 0;
                            this.a((byte)7, this.o);
                            g2 = this;
                            g3 = game.l.B().n[i2];
                            game.l.B().n[i2].q = g2;
                            g2 = game.l.B().n[i2];
                            g3 = this;
                            this.q = g2;
                            return false;
                        }
                        case 14: {
                            return false;
                        }
                        case 16: {
                            g2 = game.l.B().n[i2];
                            g3 = this;
                            this.q = g2;
                        }
                    }
                    continue block24;
                }
                case 3: {
                    return false;
                }
                case 1: {
                    if (game.l.B().n[i2].w != 3) continue block24;
                    return false;
                }
            }
        }
        return n2 != 0;
    }

    private boolean L() {
        int n2 = 0;
        j j2 = this;
        this.aa = j2.e[n2];
        int n3 = this.j - 8;
        int n4 = this.k - 8;
        int n5 = this.j + 7;
        int n6 = this.k + 7;
        byte[] byArray = new byte[]{-1, -1, -1, -1, -1};
        switch (this.o) {
            case 2: {
                if (a.b.d.a().b(this.j, n4 - this.aa)) {
                    return false;
                }
                byArray[0] = a.b.d.a().a(0, n3, n4 - this.aa);
                byArray[1] = a.b.d.a().a(0, n5, n4 - this.aa);
                byArray[2] = a.b.d.a().a(0, this.j, n4 - this.aa);
                if (!this.a(byArray[0]) && !this.a(byArray[1])) {
                    if (a.b.d.a().a(0, this.j, this.k) == 3) {
                        return false;
                    }
                    byArray[0] = a.b.d.a().a(0, this.j - 16, n4 - this.aa);
                    byArray[1] = a.b.d.a().a(0, this.j + 16, n4 - this.aa);
                    byArray[3] = a.b.d.a().a(0, this.j - 16, this.k);
                    byArray[4] = a.b.d.a().a(0, this.j + 16, this.k);
                    n2 = 1;
                    j2 = this;
                    this.aa = j2.e[n2];
                    if (!this.a(byArray[0])) {
                        return this.c(byArray[1], (byte)1);
                    }
                    if (!this.a(byArray[1])) {
                        return this.c(byArray[0], (byte)3);
                    }
                    if (this.a(byArray[4])) {
                        return this.c(byArray[1], (byte)1);
                    }
                    if (this.a(byArray[3])) {
                        return this.c(byArray[0], (byte)3);
                    }
                } else {
                    if (!this.a(byArray[0])) {
                        n2 = 1;
                        j2 = this;
                        this.aa = j2.e[n2];
                        return this.c(byArray[1], (byte)1);
                    }
                    if (!this.a(byArray[1])) {
                        n2 = 1;
                        j2 = this;
                        this.aa = j2.e[n2];
                        return this.c(byArray[0], (byte)3);
                    }
                    return this.c(byArray[2], (byte)2);
                }
            }
            case 0: {
                if (a.b.d.a().b(this.j, n6 + this.aa)) {
                    return false;
                }
                byArray[0] = a.b.d.a().a(0, n3, n6 + this.aa);
                byArray[1] = a.b.d.a().a(0, n5, n6 + this.aa);
                byArray[2] = a.b.d.a().a(0, this.j, n6 + this.aa);
                if (!this.a(byArray[0]) && !this.a(byArray[1])) {
                    if (a.b.d.a().a(0, this.j, this.k) == 3) {
                        return false;
                    }
                    byArray[0] = a.b.d.a().a(0, n3 - 16, n6 + this.aa);
                    byArray[1] = a.b.d.a().a(0, n5 + 16, n6 + this.aa);
                    byArray[3] = a.b.d.a().a(0, this.j - 16, this.k);
                    byArray[4] = a.b.d.a().a(0, this.j + 16, this.k);
                    n2 = 1;
                    j2 = this;
                    this.aa = j2.e[n2];
                    if (!this.a(byArray[0])) {
                        return this.c(byArray[1], (byte)1);
                    }
                    if (!this.a(byArray[1])) {
                        return this.c(byArray[0], (byte)3);
                    }
                    if (this.a(byArray[4])) {
                        return this.c(byArray[1], (byte)1);
                    }
                    if (this.a(byArray[3])) {
                        return this.c(byArray[0], (byte)3);
                    }
                } else {
                    if (!this.a(byArray[0])) {
                        n2 = 1;
                        j2 = this;
                        this.aa = j2.e[n2];
                        return this.c(byArray[1], (byte)1);
                    }
                    if (!this.a(byArray[1])) {
                        n2 = 1;
                        j2 = this;
                        this.aa = j2.e[n2];
                        return this.c(byArray[0], (byte)3);
                    }
                    return this.c(byArray[2], (byte)0);
                }
            }
            case 3: {
                if (a.b.d.a().b(n3 - this.aa, this.k)) {
                    return false;
                }
                byArray[0] = a.b.d.a().a(0, n3 - this.aa, n4);
                byArray[1] = a.b.d.a().a(0, n3 - this.aa, n6);
                byArray[2] = a.b.d.a().a(0, n3 - this.aa, this.k);
                if (!this.a(byArray[0]) && !this.a(byArray[1])) {
                    if (a.b.d.a().a(0, this.j, this.k) == 3) {
                        return false;
                    }
                    byArray[0] = a.b.d.a().a(0, n3 - this.aa, n4 - 16);
                    byArray[1] = a.b.d.a().a(0, n3 - this.aa, n6 + 16);
                    byArray[3] = a.b.d.a().a(0, this.j, this.k - 16);
                    byArray[4] = a.b.d.a().a(0, this.j, this.k + 16);
                    n2 = 1;
                    j2 = this;
                    this.aa = j2.e[n2];
                    if (!this.a(byArray[0])) {
                        return this.c(byArray[1], (byte)0);
                    }
                    if (!this.a(byArray[1])) {
                        return this.c(byArray[0], (byte)2);
                    }
                    if (this.a(byArray[4])) {
                        return this.c(byArray[1], (byte)0);
                    }
                    if (this.a(byArray[3])) {
                        return this.c(byArray[0], (byte)2);
                    }
                } else {
                    if (!this.a(byArray[0])) {
                        n2 = 1;
                        j2 = this;
                        this.aa = j2.e[n2];
                        return this.c(byArray[1], (byte)0);
                    }
                    if (!this.a(byArray[1])) {
                        n2 = 1;
                        j2 = this;
                        this.aa = j2.e[n2];
                        return this.c(byArray[0], (byte)2);
                    }
                    return this.c(byArray[2], (byte)3);
                }
            }
            case 1: {
                if (a.b.d.a().b(n5 + this.aa, this.k)) {
                    return false;
                }
                byArray[0] = a.b.d.a().a(0, n5 + this.aa, n4);
                byArray[1] = a.b.d.a().a(0, n5 + this.aa, n6);
                byArray[2] = a.b.d.a().a(0, n5 + this.aa, this.k);
                if (!this.a(byArray[0]) && !this.a(byArray[1])) {
                    if (a.b.d.a().a(0, this.j, this.k) == 3) {
                        return false;
                    }
                    byArray[0] = a.b.d.a().a(0, n5 + this.aa, n4 - 16);
                    byArray[1] = a.b.d.a().a(0, n5 + this.aa, n6 + 16);
                    byArray[3] = a.b.d.a().a(0, this.j, this.k - 16);
                    byArray[4] = a.b.d.a().a(0, this.j, this.k + 16);
                    n2 = 1;
                    j2 = this;
                    this.aa = j2.e[n2];
                    if (!this.a(byArray[0])) {
                        return this.c(byArray[1], (byte)0);
                    }
                    if (!this.a(byArray[1])) {
                        return this.c(byArray[0], (byte)2);
                    }
                    if (this.a(byArray[4])) {
                        return this.c(byArray[1], (byte)0);
                    }
                    if (!this.a(byArray[3])) break;
                    return this.c(byArray[0], (byte)2);
                }
                if (!this.a(byArray[0])) {
                    n2 = 1;
                    j2 = this;
                    this.aa = j2.e[n2];
                    return this.c(byArray[1], (byte)0);
                }
                if (!this.a(byArray[1])) {
                    n2 = 1;
                    j2 = this;
                    this.aa = j2.e[n2];
                    return this.c(byArray[0], (byte)2);
                }
                return this.c(byArray[2], (byte)1);
            }
        }
        return true;
    }

    private boolean a(byte by) {
        switch (by) {
            case 1: {
                return false;
            }
            case 2: {
                return this.C[3][0] == 2;
            }
        }
        return true;
    }

    public final boolean a(h h2, short[] sArray, short[] sArray2) {
        if (sArray2 == null) {
            return false;
        }
        switch (this.o) {
            case 3: {
                if (h2.w == 14) {
                    int n2 = 0;
                    j j2 = this;
                    return game.j.a(h2, sArray2, sArray, this.j - j2.e[n2], this.k);
                }
                int n3 = 0;
                j j3 = this;
                if (!a.e.a(this.j - j3.e[n3], this.k, h2.j, h2.k, sArray, sArray2)) break;
                return true;
            }
            case 1: {
                if (h2.w == 14) {
                    int n4 = 0;
                    j j4 = this;
                    return game.j.a(h2, sArray2, sArray, this.j + j4.e[n4], this.k);
                }
                int n5 = 0;
                j j5 = this;
                if (!a.e.a(this.j + j5.e[n5], this.k, h2.j, h2.k, sArray, sArray2)) break;
                return true;
            }
            case 2: {
                if (h2.w == 14) {
                    int n6 = 0;
                    j j6 = this;
                    return game.j.a(h2, sArray2, sArray, this.j, this.k - j6.e[n6]);
                }
                int n7 = 0;
                j j7 = this;
                if (!a.e.a(this.j, this.k - j7.e[n7], h2.j, h2.k, sArray, sArray2)) break;
                return true;
            }
            case 0: {
                if (h2.w == 14) {
                    int n8 = 0;
                    j j8 = this;
                    return game.j.a(h2, sArray2, sArray, this.j, this.k + j8.e[n8]);
                }
                int n9 = 0;
                j j9 = this;
                if (!a.e.a(this.j, this.k + j9.e[n9], h2.j, h2.k, sArray, sArray2)) break;
                return true;
            }
        }
        return false;
    }

    private static boolean a(h h2, short[] sArray, short[] sArray2, int n2, int n3) {
        h h3 = h2;
        switch (h3.a.g()) {
            case 1: {
                if (!a.e.a(h2.j + sArray[0], h2.k + sArray[1], sArray[2] + (h2.B << 4), (int)sArray[3], n2, n3, sArray2)) break;
                return true;
            }
            case 3: {
                if (!a.e.a(h2.j + sArray[0] - (h2.B << 4), h2.k + sArray[1], sArray[2] + (h2.B << 4), (int)sArray[3], n2, n3, sArray2)) break;
                return true;
            }
            case 2: {
                if (!a.e.a(h2.j + sArray[0], h2.k + sArray[1] - (h2.B << 4), (int)sArray[2], sArray[3] + (h2.B << 4), n2, n3, sArray2)) break;
                return true;
            }
            case 0: {
                if (!a.e.a(h2.j + sArray[0], h2.k + sArray[1], (int)sArray[2], sArray[3] + (h2.B << 4), n2, n3, sArray2)) break;
                return true;
            }
        }
        return false;
    }

    private boolean M() {
        boolean bl = true;
        switch (this.o) {
            case 3: {
                bl = this.a(a.b.d.a().a(0, this.j - 16, this.k));
                break;
            }
            case 1: {
                bl = this.a(a.b.d.a().a(0, this.j + 16, this.k));
                break;
            }
            case 2: {
                bl = this.a(a.b.d.a().a(0, this.j, this.k - 16));
                break;
            }
            case 0: {
                bl = this.a(a.b.d.a().a(0, this.j, this.k + 16));
            }
        }
        if (bl && a.b.d.a().a(0, this.j, this.k) != 3) {
            return false;
        }
        return bl;
    }

    private boolean N() {
        boolean bl = true;
        switch (this.o) {
            case 3: {
                int n2 = 0;
                j j2 = this;
                bl = this.a(a.b.d.a().a(0, this.j - j2.e[n2], this.k));
                break;
            }
            case 1: {
                int n3 = 0;
                j j3 = this;
                bl = this.a(a.b.d.a().a(0, this.j + j3.e[n3], this.k));
                break;
            }
            case 2: {
                int n4 = 0;
                j j4 = this;
                bl = this.a(a.b.d.a().a(0, this.j, this.k - j4.e[n4]));
                break;
            }
            case 0: {
                int n5 = 0;
                j j5 = this;
                bl = this.a(a.b.d.a().a(0, this.j, this.k + j5.e[n5]));
            }
        }
        if (bl && a.b.d.a().a(0, this.j, this.k) != 2) {
            return false;
        }
        return bl;
    }

    private boolean c(byte by, byte by2) {
        switch (by) {
            case -1: 
            case 0: {
                this.a((byte)1, by2);
                break;
            }
            case 1: {
                if (a.b.d.a().a(0, this.j, this.k) == 2) {
                    this.a((byte)2, by2);
                }
                return false;
            }
            case 2: {
                if (this.a((byte)2)) {
                    this.a((byte)2, by2);
                    break;
                }
                return false;
            }
            case 3: {
                this.a((byte)6, by2);
            }
        }
        return true;
    }

    public final void w() {
        if (((h)this.q).i() == 1) {
            game.l.B().d.az();
            game.l.B().d.b("B\u1ea3o r\u01b0\u01a1ng n\u00e0y \u0111\u00e3 tr\u1ed1ng");
            return;
        }
        if (((h)this.q).w == 0) {
            ((h)this.q).a((byte)1);
            if (this.a((int)((h)this.q).G, (int)((h)this.q).E, (byte)((h)this.q).F)) {
                this.c(((h)this.q).G, ((h)this.q).E, (byte)((h)this.q).F);
                String string = null;
                if (((h)this.q).F == 0) {
                    string = a.a.c(a.b.c.c[4][((h)this.q).G][0]);
                } else if (((h)this.q).F == 2) {
                    string = a.a.c(a.b.c.c[3][((h)this.q).G][0]);
                }
                game.l.B().d.a("\u0110\u1ea1t \u0111\u01b0\u1ee3c: " + string, (int)((h)this.q).E);
            } else {
                game.l.B().d.ay();
            }
            this.a((byte)0, this.o);
            return;
        }
        if (((h)this.q).w == 1) {
            if (this.b(17, 1, (byte)2)) {
                ((h)this.q).a((byte)1);
                this.d(17, 1, (byte)2);
                if (this.a((int)((h)this.q).G, (int)((h)this.q).E, (byte)((h)this.q).F)) {
                    this.c(((h)this.q).G, ((h)this.q).E, (byte)((h)this.q).F);
                    String string = null;
                    if (((h)this.q).F == 0) {
                        string = a.a.c(a.b.c.c[4][((h)this.q).G][0]);
                    } else if (((h)this.q).F == 2) {
                        string = a.a.c(a.b.c.c[3][((h)this.q).G][0]);
                    }
                    game.l.B().d.a("\u0110\u1ea1t \u0111\u01b0\u1ee3c: " + string, (int)((h)this.q).E);
                } else {
                    game.l.B().d.ay();
                }
            } else {
                game.l.B().d.ax();
            }
            this.a((byte)0, this.o);
        }
    }

    public final boolean x() {
        if (this.q == null) {
            return false;
        }
        if (((h)this.q).i() == 0) {
            game.e.t = true;
            if (((h)this.q).w == 7 || ((h)this.q).w == 6) {
                for (int i2 = 0; i2 < this.ak.size(); ++i2) {
                    h h2 = (h)this.ak.elementAt(i2);
                    h2.a((byte)1);
                    h2.y();
                }
                this.ak.removeAllElements();
            } else if (((h)this.q).w != 16) {
                ((h)this.q).a((byte)1);
            }
            return true;
        }
        return false;
    }

    private static boolean a(int n2, int n3, Vector vector) {
        for (int i2 = 0; i2 < vector.size(); ++i2) {
            int[] nArray = (int[])vector.elementAt(i2);
            if (nArray[0] != n2) continue;
            return nArray[1] < 99;
        }
        return n3 <= 99;
    }

    private static boolean b(int n2, int n3, Vector vector) {
        for (int i2 = 0; i2 < vector.size(); ++i2) {
            int[] nArray = (int[])vector.elementAt(i2);
            if (nArray[0] != n2) continue;
            return nArray[1] - n3 >= 0;
        }
        return false;
    }

    private static boolean c(int n2, int n3, Vector vector) {
        int[] nArray;
        for (int i2 = 0; i2 < vector.size(); ++i2) {
            nArray = (int[])vector.elementAt(i2);
            if (nArray[0] != n2) continue;
            nArray[1] = nArray[1] + n3;
            if (nArray[1] >= 99) {
                nArray[1] = 99;
            }
            return true;
        }
        nArray = new int[]{n2, n3, 0};
        vector.addElement(nArray);
        return false;
    }

    private static boolean d(int n2, int n3, Vector vector) {
        for (int i2 = 0; i2 < vector.size(); ++i2) {
            int[] nArray = (int[])vector.elementAt(i2);
            if (nArray[0] != n2) continue;
            nArray[1] = nArray[1] - n3;
            if (nArray[1] <= 0 && nArray[2] == 0) {
                vector.removeElementAt(i2);
            }
            return true;
        }
        return false;
    }

    public final boolean i(int n2) {
        int n3 = 0;
        for (int i2 = 0; i2 < this.B; ++i2) {
            if (i2 == n2 || !this.A[i2].T()) continue;
            ++n3;
        }
        return n3 > 0;
    }

    public final boolean a(int n2, int n3, byte by) {
        switch (by) {
            case 0: {
                if (a.b.c.c[4][n2][5] == 0) {
                    return game.j.a(n2, n3, this.L);
                }
                return game.j.a(n2, n3, this.K);
            }
            case 2: {
                if (n2 >= 12) {
                    return game.j.a(n2, n3, this.N);
                }
                n3 = n2;
                j j2 = this;
                for (by = 0; by < j2.M.size(); by = (byte)(by + 1)) {
                    if (((int[])j2.M.elementAt(by))[0] != n3) continue;
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public final boolean b(int n2, int n3, byte by) {
        switch (by) {
            case 0: {
                if (a.b.c.c[4][n2][5] == 0) {
                    return game.j.b(n2, n3, this.L);
                }
                return game.j.b(n2, n3, this.K);
            }
            case 2: {
                return game.j.b(n2, n3, this.N);
            }
        }
        return false;
    }

    public final boolean c(int n2, int n3, byte by) {
        switch (by) {
            case 0: {
                if (a.b.c.c[4][n2][5] == 0) {
                    return game.j.c(n2, n3, this.L);
                }
                return game.j.c(n2, n3, this.K);
            }
            case 2: {
                if (n2 >= 12) {
                    if (n2 == 17) {
                        return game.j.c(n2, n3 * 5, this.N);
                    }
                    return game.j.c(n2, n3, this.N);
                }
                n3 = n2;
                j j2 = this;
                int[] nArray = new int[]{n3, 0, 0};
                j2.M.addElement(nArray);
                return true;
            }
        }
        return false;
    }

    public final boolean d(int n2, int n3, byte by) {
        switch (by) {
            case 0: {
                if (a.b.c.c[4][n2][5] == 0) {
                    return game.j.d(n2, n3, this.L);
                }
                return game.j.d(n2, n3, this.K);
            }
            case 2: {
                return game.j.d(n2, n3, this.N);
            }
        }
        return false;
    }

    public final int a(int n2, byte by) {
        switch (by) {
            case 0: {
                if (a.b.c.c[4][n2][5] == 0) {
                    for (int i2 = 0; i2 < this.L.size(); ++i2) {
                        int[] nArray = (int[])this.L.elementAt(i2);
                        if (nArray[0] != n2) continue;
                        return nArray[1];
                    }
                } else {
                    for (int i3 = 0; i3 < this.K.size(); ++i3) {
                        int[] nArray = (int[])this.K.elementAt(i3);
                        if (nArray[0] != n2) continue;
                        return nArray[1];
                    }
                }
                break;
            }
            case 2: {
                for (int i4 = 0; i4 < this.N.size(); ++i4) {
                    int[] nArray = (int[])this.N.elementAt(i4);
                    if (nArray[0] != n2) continue;
                    return nArray[1];
                }
                break;
            }
        }
        return 0;
    }

    public final void y() {
        int[] nArray;
        int n2;
        if (this.T == null) {
            this.T = new Vector();
        } else {
            this.T.removeAllElements();
        }
        for (n2 = 0; n2 < this.L.size(); ++n2) {
            nArray = (int[])this.L.elementAt(n2);
            if (a.b.c.c[4][nArray[0]][4] != 0) continue;
            this.T.addElement(nArray);
        }
        for (n2 = 0; n2 < this.K.size(); ++n2) {
            nArray = (int[])this.K.elementAt(n2);
            if (a.b.c.c[4][nArray[0]][4] != 0) continue;
            this.T.addElement(nArray);
        }
    }

    public final boolean j(int n2) {
        int[] nArray;
        if (n2 == 0) {
            nArray = new int[]{n2, 0, 0};
        } else {
            nArray = new int[]{n2, 1, 0};
            if (n2 == 1 || n2 == 2 || n2 == 3 || n2 == 4) {
                this.Q[n2 - 1] = 1;
            }
        }
        this.O.addElement(nArray);
        return true;
    }

    public final void c(int n2, int n3) {
        int[] nArray;
        for (int i2 = 0; i2 < this.O.size(); ++i2) {
            nArray = (int[])this.O.elementAt(i2);
            if (nArray[0] != n2) continue;
            nArray[2] = nArray[2] + n3;
            if (nArray[2] >= 99) {
                nArray[2] = 99;
            }
            return;
        }
        nArray = new int[]{n2, 0, n3};
        this.O.addElement(nArray);
    }

    public final int d(int n2, int n3) {
        if (0 < this.O.size()) {
            int[] nArray = (int[])this.O.elementAt(0);
            if (nArray[0] == n2 && (n2 == 7 || n2 == 9 || n2 == 8)) {
                if (nArray[1] + n3 <= 99) {
                    return 0;
                }
                return 1;
            }
            return -1;
        }
        if (n3 > 99) {
            return 1;
        }
        return -1;
    }

    public final boolean e(int n2, int n3) {
        for (int i2 = 0; i2 < this.O.size(); ++i2) {
            int[] nArray = (int[])this.O.elementAt(i2);
            if (nArray[0] == n2 && (n2 == 7 || n2 == 9 || n2 == 8)) {
                this.A[n3].i((byte)n2);
                if (nArray[2] > 0) {
                    nArray[2] = nArray[2] - 1;
                    this.O.setElementAt(new int[]{nArray[0], 0, nArray[2]}, i2);
                } else if (nArray[2] <= 0) {
                    this.O.removeElementAt(i2);
                }
                return true;
            }
            if (nArray[0] != n2 || nArray[1] != 0) continue;
            this.O.setElementAt(new int[]{nArray[0], 1, nArray[2]}, i2);
            break;
        }
        return false;
    }

    public final void k(int n2) {
        for (int i2 = 0; i2 < this.O.size(); ++i2) {
            int[] nArray = (int[])this.O.elementAt(i2);
            if (nArray[0] != n2 || nArray[1] != 1) continue;
            this.O.setElementAt(new int[]{nArray[0], 0, nArray[2]}, i2);
            return;
        }
    }

    public final boolean l(int n2) {
        for (int i2 = 0; i2 < this.O.size(); ++i2) {
            int[] nArray = (int[])this.O.elementAt(i2);
            if (nArray[0] != n2 || nArray[1] != 1) continue;
            return true;
        }
        return false;
    }

    public final boolean m(int n2) {
        if (n2 == -1) {
            return false;
        }
        for (int i2 = 0; i2 < this.M.size(); ++i2) {
            int[] nArray = (int[])this.M.elementAt(i2);
            if (nArray[0] != n2) continue;
            nArray[1] = 0;
            return true;
        }
        return false;
    }

    public final void f(int n2, int n3) {
        block8: {
            boolean bl;
            boolean bl2;
            int n4;
            block7: {
                int n5 = 5;
                i i2 = this.A[n3];
                if (i2.d[n5] >= 0) {
                    n5 = 5;
                    i2 = this.A[n3];
                    this.m(i2.d[n5]);
                    int n6 = -1;
                    n5 = 5;
                    i2 = this.A[n3];
                    i2.d[n5] = n6;
                }
                n4 = n2;
                j j2 = this;
                for (int i3 = 0; i3 < j2.M.size(); ++i3) {
                    int[] nArray = (int[])j2.M.elementAt(i3);
                    if (nArray[0] != n4 || nArray[1] != 1) continue;
                    bl2 = true;
                    break block7;
                }
                bl2 = false;
            }
            if (bl2) {
                this.m(n2);
                int n7 = 0;
                for (n4 = 0; n4 < this.B; ++n4) {
                    int n8 = 5;
                    i i4 = this.A[n4];
                    if (i4.d[n8] != n2) continue;
                    n7 = -1;
                    n8 = 5;
                    i4 = this.A[n4];
                    i4.d[n8] = n7;
                    n7 = 1;
                    break;
                }
                if (n7 == 0) {
                    for (n4 = 0; n4 < this.P.size(); ++n4) {
                        int[] nArray = (int[])this.P.elementAt(n3);
                        if (nArray[2] != n2) continue;
                        nArray[2] = -1;
                        break;
                    }
                }
            }
            n4 = n2;
            j j3 = this;
            for (int i5 = 0; i5 < j3.M.size(); ++i5) {
                int[] nArray = (int[])j3.M.elementAt(i5);
                if (nArray[0] != n4) continue;
                nArray[1] = 1;
                bl = true;
                break block8;
            }
            bl = false;
        }
        short s = (short)n2;
        int n9 = 5;
        i i6 = this.A[n3];
        i6.d[n9] = s;
    }

    public final byte z() {
        if (this.B < 6) {
            return 0;
        }
        if (this.P.size() < 100) {
            return 1;
        }
        return 2;
    }

    public final boolean A() {
        return this.P.size() < 100;
    }

    public final void a(int n2, int n3, short s, byte by, short s2, byte by2, int[] nArray) {
        this.A[this.B] = new i();
        this.A[this.B].a(n2, n3, (short)-1, by, s2, (byte)-1);
        this.A[this.B].b(nArray);
        this.a((byte)this.A[this.B].j((byte)1), n2, (byte)2);
        ++this.B;
    }

    public final void a(int n2, int n3, int n4, short s, byte by, short s2, byte by2, int[] nArray) {
        this.A[this.B] = new i();
        System.arraycopy(this.A, n2, this.A, n2 + 1, this.B - n2);
        this.A[n2] = null;
        this.A[n2] = new i();
        this.A[n2].a(n3, n4, (short)-1, by, s2, (byte)-1);
        this.A[n2].b(nArray);
        ++this.B;
    }

    public final void a(int[] nArray) {
        this.A[this.B] = new i();
        this.A[this.B].a(nArray[0], nArray[1], (short)nArray[2], (byte)nArray[3], (short)nArray[4], (byte)nArray[5]);
        this.A[this.B].a((short)nArray[6], nArray[7], nArray[8]);
        int[] nArray2 = new int[nArray.length - 9];
        for (int i2 = 0; i2 < nArray2.length; ++i2) {
            nArray2[i2] = nArray[i2 + 9];
        }
        this.A[this.B].b(nArray2);
        this.a((byte)this.A[this.B].j((byte)1), nArray[0], (byte)2);
        ++this.B;
    }

    public final void n(int n2) {
        this.A[n2] = null;
        while (n2 < this.B - 1) {
            this.A[n2] = this.A[n2 + 1];
            this.A[n2 + 1] = null;
            ++n2;
        }
        --this.B;
    }

    public final void o(int n2) {
        for (int i2 = 0; i2 < this.B; ++i2) {
            if (this.A[i2].r() != n2) continue;
            this.n(i2);
            return;
        }
    }

    public final int B() {
        int n2;
        int[] nArray = new int[this.B];
        for (n2 = 0; n2 < this.B; ++n2) {
            int n3 = 1;
            i i2 = this.A[n2];
            short s = i2.d[n3];
            n3 = 1;
            i2 = this.A[n2];
            nArray[n2] = s - i2.e[n3];
        }
        n2 = nArray[0];
        for (int i3 = 1; i3 < nArray.length; ++i3) {
            if (n2 >= nArray[i3]) continue;
            n2 = nArray[i3];
        }
        if (n2 == 0) {
            return -1;
        }
        return n2;
    }

    public final void p(int n2) {
        i i2 = this.A[n2];
        while (n2 > 0) {
            this.A[n2] = this.A[n2 - 1];
            --n2;
        }
        this.A[0] = i2;
    }

    public final void a(int n2, int n3, short s, byte by, short s2, byte by2, int n4, int n5, int n6, int[] nArray) {
        int[] nArray2 = new int[9 + nArray.length];
        int[] nArray3 = nArray2;
        nArray2[0] = n2;
        nArray3[1] = n3;
        nArray3[2] = -1;
        nArray3[3] = by;
        nArray3[4] = s2;
        nArray3[5] = -1;
        nArray3[6] = n4;
        nArray3[7] = 0;
        nArray3[8] = n6;
        System.arraycopy(nArray, 0, nArray3, 9, nArray.length);
        this.P.addElement(nArray3);
        this.a((byte)a.b.c.c[0][n2][1], n2, (byte)2);
    }

    public final void b(int[] nArray) {
        this.P.addElement(nArray);
        this.a((byte)a.b.c.c[0][nArray[0]][1], nArray[0], (byte)2);
    }

    public final void q(int n2) {
        this.P.removeElementAt(n2);
    }

    public final void r(int n2) {
        int n3 = n2;
        Object object = this;
        i i2 = new i();
        int[] nArray = (int[])((j)object).P.elementAt(n3);
        i2.a(nArray[0], nArray[1], (short)nArray[2], (byte)nArray[3], (short)nArray[4], (byte)nArray[5]);
        i2.a((short)nArray[6], nArray[7], nArray[8]);
        object = new int[nArray.length - 9];
        for (int i3 = 0; i3 < ((Object)object).length; ++i3) {
            object[i3] = nArray[i3 + 9];
        }
        i2.b((int[])object);
        this.A[this.B] = i2;
        ++this.B;
        this.q(n2);
    }

    public final void a(short s) {
        this.S[this.J] = s;
        this.J = (byte)(this.J + 1);
    }

    public final void b(byte by, byte by2, byte by3) {
        this.C[by][by2] = by3;
        if (this.C[0][0] == 2) {
            a.a.f.a().a(a.b.c.a((byte)2, (short)0, (byte)5) / 2, a.b.c.a((byte)2, (short)0, (byte)5) / 2);
        }
    }

    public final byte b(byte by, byte by2) {
        return this.C[by][by2];
    }

    public final void a(byte by, int n2, byte by2) {
        boolean bl;
        block11: {
            int n3 = n2;
            byte by3 = by;
            j j2 = this;
            for (int i2 = 0; i2 < j2.F[by3]; ++i2) {
                if (j2.E[by3][i2] != n3) continue;
                bl = false;
                break block11;
            }
            bl = true;
        }
        if (bl) {
            this.E[by][this.F[by]] = (byte)n2;
            byte by4 = by;
            this.F[by4] = (byte)(this.F[by4] + 1);
            if (by2 == 2) {
                this.G = (byte)(this.G + 1);
                if (a.b.c.c[0][n2][22] == 2) {
                    this.H = (byte)(this.H + 1);
                } else if (a.b.c.c[0][n2][22] == 1) {
                    this.I = (byte)(this.I + 1);
                }
            }
            this.D[by][n2 - this.X[by]] = by2;
            return;
        }
        if (this.a(by, n2) <= 1) {
            if (by2 == 2) {
                this.G = (byte)(this.G + 1);
                if (a.b.c.c[0][n2][22] == 2) {
                    this.H = (byte)(this.H + 1);
                } else if (a.b.c.c[0][n2][22] == 1) {
                    this.I = (byte)(this.I + 1);
                }
            }
            this.D[by][n2 - this.X[by]] = by2;
        }
    }

    public final byte a(byte by, int n2) {
        return this.D[by][n2 - this.X[by]];
    }

    public final void C() {
        this.ad = true;
    }

    public final int D() {
        if (game.l.B().p == 4 && game.l.B().q == 1) {
            return a.e.b(4, 8);
        }
        return a.e.b(this.ae, this.af);
    }

    private void O() {
        if (game.l.B().Z.F() || game.e.G == 0) {
            return;
        }
        --this.x;
        if (this.x <= 0) {
            game.l.B().e(false);
            this.x = 0;
        }
        --this.y;
        if (this.y == 0) {
            this.a.a(0);
            this.y = -1;
        }
        if (!game.l.B().C() || this.y > 0) {
            return;
        }
        if (this.ad && this.w > 0 && this.Q[1] != 2 && this.Q[3] != 2) {
            --this.w;
        }
    }

    public final boolean E() {
        return this.w <= 0;
    }

    public final int F() {
        return this.ag;
    }

    public final void s(int n2) {
        this.ag += n2;
    }

    public final void t(int n2) {
        this.ag = 0;
    }

    public final boolean u(int n2) {
        return this.ag >= n2;
    }

    public final int G() {
        return this.ah;
    }

    public final void v(int n2) {
        this.ah += n2;
    }

    public final void w(int n2) {
        this.ah = 0;
    }

    public final boolean x(int n2) {
        return this.ah >= n2;
    }

    public final boolean b(int n2, int n3, int n4) {
        if (a.b.c.c[n4][n2][4] == 0) {
            return this.u(n3);
        }
        return this.x(n3);
    }

    public final void H() {
        this.a(68, 7, (short)-1, (byte)2, (short)2, (byte)-1, new int[]{1, 40, 45});
        this.j(0);
    }

    public final boolean a(int n2, boolean bl) {
        super.a(n2, bl);
        if (this.y > 0) {
            this.a.a(1);
        }
        return true;
    }

    public final void I() {
        this.a((byte)0, this.o);
    }

    static {
        ai = new byte[][]{{9, 2, 9, 3, 0}};
        aj = new short[][]{{0, 3, 112, 256}};
    }
}

