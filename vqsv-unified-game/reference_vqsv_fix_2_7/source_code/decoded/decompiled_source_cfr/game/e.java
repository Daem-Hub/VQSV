/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
package game;

import a.a.g;
import a.b.b;
import a.b.c;
import game.a;
import game.d;
import game.f;
import game.h;
import game.i;
import game.j;
import game.l;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class e
extends a.a {
    public static byte k;
    private static e I;
    private l J;
    private j K;
    private a.a L;
    public b.a[] l;
    private Vector M;
    private byte N = (byte)-1;
    public byte m = (byte)-1;
    public byte[][] n;
    private int O = 0;
    public int o = -1;
    private static Vector P;
    public static Vector p;
    private d Q = game.d.a();
    public static boolean q;
    public static boolean r;
    public static boolean s;
    public static boolean t;
    public boolean u = true;
    public static boolean v;
    public static boolean w;
    public boolean x = true;
    public byte y = 0;
    public static byte z;
    private short[] R;
    private byte[] S;
    private short[] T;
    private short[] U;
    private short[][] V;
    private short[][] W;
    private short[] X;
    private short[] Y;
    public byte A = 0;
    public static Image B;
    private String[] Z = new String[]{"ikon_1", "ikon_2", "ikon_3", "ikon_4", "ikon_5"};
    private int aa;
    private int ab;
    private int ac;
    private int ad = 0;
    private int ae = 0;
    private byte[] af;
    private String[] ag;
    private int[] ah;
    private int[] ai;
    private String[] aj = null;
    private g ak = null;
    private int al = -1;
    private Calendar am = null;
    private int[] an;
    public byte C = 0;
    public static String[] D;
    public static String[] E;
    private int ao = 0;
    public static short[][] F;
    public static byte G;
    public static byte H;
    private static byte[][] ap;
    private static byte[][] aq;

    public e() {
        int n2;
        if (this.J == null) {
            this.J = game.l.B();
        }
        if (this.K == null) {
            this.K = game.j.p();
        }
        if (this.n == null) {
            this.n = new byte[127][];
        }
        if (F == null) {
            F = new short[200][2];
        }
        if (this.am == null) {
            this.am = Calendar.getInstance(TimeZone.getDefault());
        }
        Object object = a.e.c(a.e.a("/data/script/bTask.mid"));
        D = new String[((String[][])object).length];
        for (n2 = 0; n2 < ((String[][])object).length; ++n2) {
            System.arraycopy(object[n2], 0, D, n2, object[n2].length);
        }
        object = a.e.c(a.e.a("/data/script/mTask.mid"));
        E = new String[((String[][])object).length];
        for (n2 = 0; n2 < ((String[][])object).length; ++n2) {
            System.arraycopy(object[n2], 0, E, n2, object[n2].length);
        }
        object = a.e.a("/data/script/bqTask.mid");
        ap = a.e.b((InputStream)object);
        aq = a.e.b((InputStream)object);
    }

    public static e B() {
        if (I == null) {
            I = new e();
        }
        return I;
    }

    public final void a(a.a a2) {
        if (this.L != null) {
            this.L = null;
        }
        this.L = a2;
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    public final void a() {
        if (this.l == null) {
            return;
        }
        a.a.f.a().d();
        this.Q.d();
        var1_1 = this;
        for (var2_2 = 0; var2_2 < var1_1.l.length; ++var2_2) {
            if (var1_1.l[var2_2].a() != 0 && var1_1.l[var2_2].a() != 4) continue;
            var3_3 = var1_1.l[var2_2].d();
            var4_5 = false;
            switch (var3_3.a()) {
                case 43: {
                    if (game.l.e(var3_3.b()[2], var3_3.b()[3]) != game.l.e(var1_1.J.p, var1_1.J.q) || var3_3.b()[4] != game.l.E || !var1_1.a((b.b)var3_3)) break;
                    game.e.s = true;
                    if (!game.e.t || var1_1.l(-1) != -1) break;
                    var6_13 = var2_2;
                    var5_6 = var1_1;
                    for (var7_18 = 0; var7_18 < var5_6.l.length; ++var7_18) {
                        if (var5_6.l[var7_18].a() == 3 || var6_13 == var7_18 || (var8_19 = var5_6.l[var7_18].d()).a() != 43 || game.l.e(var8_19.b()[2], var8_19.b()[3]) != game.l.e(var5_6.J.p, var5_6.J.q) || var8_19.b()[4] != game.l.E || !var5_6.a(var8_19)) continue;
                        v0 = (byte)var7_18;
                        ** GOTO lbl22
                    }
                    v0 = -1;
lbl22:
                    // 2 sources

                    if (var2_2 < v0) break;
                    var4_5 = true;
                    game.e.s = false;
                    var1_1.K.a((byte)0, var1_1.K.o);
                    if (var3_3.b()[1] != 1) break;
                    game.e.F[game.e.H][0] = var3_3.b()[0];
                    break;
                }
                case 44: {
                    if (game.l.e(var3_3.b()[2], var3_3.b()[3]) != game.l.e(var1_1.J.p, var1_1.J.q) || var3_3.b()[4] != game.l.E || !var1_1.b((b.b)var3_3)) break;
                    game.e.s = true;
                    if (!game.e.t || var2_2 < var1_1.l(var2_2)) break;
                    game.e.s = false;
                    var4_5 = true;
                    var1_1.K.a((byte)0, var1_1.K.o);
                    break;
                }
                case 13: {
                    if (!a.e.a(var3_3.b()[0], (int)var3_3.b()[1], (int)var3_3.b()[2], (int)var3_3.b()[3], var1_1.K.j, var1_1.K.k, var1_1.K.a.k())) break;
                    var1_1.K.a((byte)0, var1_1.K.o);
                    ** GOTO lbl113
                }
                case 15: {
                    if (var1_1.n[game.l.e(var3_3.b()[0], var3_3.b()[1])] == null || var1_1.n[game.l.e(var3_3.b()[0], var3_3.b()[1])][var3_3.b()[2]] != 3 && var1_1.n[game.l.e(var3_3.b()[0], var3_3.b()[1])][var3_3.b()[2]] != 4) break;
                    var4_5 = true;
                    break;
                }
                case 16: {
                    if (var3_3.b()[0] != game.l.E) break;
                    game.e.s = true;
                    if (!game.e.t) break;
                    var1_1.K.a((byte)0, var1_1.K.o);
                    game.e.t = false;
                    ** GOTO lbl113
                }
                case 69: {
                    if (var3_3.b()[0] != game.l.E) break;
                    ** GOTO lbl113
                }
                case 57: {
                    if (var1_1.K.q == null || ((h)var1_1.K.q).u != 0 || ((h)var1_1.K.q).w != 11 || ((h)var1_1.K.q).J != var3_3.b()[3] || !game.e.t) break;
                    var5_7 = var1_1.J.n[var3_3.b()[0]];
                    if (var5_7.j != var3_3.b()[1]) ** GOTO lbl-1000
                    var5_7 = var1_1.J.n[var3_3.b()[0]];
                    if (var5_7.k == var3_3.b()[2]) {
                        ((h)var1_1.K.q).a((byte)0);
                    } else lbl-1000:
                    // 2 sources

                    {
                        var4_5 = true;
                    }
                    game.e.t = false;
                    break;
                }
                case 59: {
                    var5_8 = new int[a.e.a(var3_3.c()[0], ',').length];
                    for (var6_14 = 0; var6_14 < var5_8.length; ++var6_14) {
                        var5_8[var6_14] = a.e.b(a.e.a(var3_3.c()[0], ',')[var6_14]);
                        if (var1_1.J.n[var5_8[var6_14]].i() == 0) break;
                    }
                    if (var6_14 < var5_8.length) break;
                    var4_5 = true;
                    break;
                }
                case 61: {
                    var5_9 = new int[a.e.a(var3_3.c()[0], ',').length];
                    for (var6_15 = 0; var6_15 < var5_9.length; ++var6_15) {
                        var5_9[var6_15] = a.e.b(a.e.a(var3_3.c()[0], ',')[var6_15]);
                        if (var1_1.J.n[var5_9[var6_15]].i() == 0) break;
                    }
                    if (var6_15 < var5_9.length) break;
                    var1_1.K.a((byte)0, var1_1.K.o);
                    var4_5 = true;
                    break;
                }
                case 73: {
                    var5_10 = a.e.a(var3_3.c()[1], ',');
                    var6_16 = a.e.a(var3_3.c()[0], ',');
                    for (var3_4 = 0; var3_4 < var5_10.length && var1_1.K.a(a.e.d(var6_16[var3_4]), (int)a.e.d(var5_10[var3_4])) >= 2; ++var3_4) {
                    }
                    if (var3_4 < var5_10.length) break;
                    var4_5 = true;
                    break;
                }
                case 75: {
                    if (var1_1.K.M.size() <= 0) break;
                    ** GOTO lbl113
                }
                case 78: {
                    var5_11 = a.e.e(var3_3.c()[0]);
                    var6_17 = a.e.e(var3_3.c()[1]);
                    var3_3 = a.e.e(var3_3.c()[2]);
                    for (var7_18 = 0; var7_18 < ((Object)var3_3).length && var1_1.n[game.l.e(var5_11[var7_18], var6_17[var7_18])] != null && (var1_1.n[game.l.e(var5_11[var7_18], var6_17[var7_18])][var3_3[var7_18]] == 3 || var1_1.n[game.l.e(var5_11[var7_18], var6_17[var7_18])][var3_3[var7_18]] == 4); ++var7_18) {
                    }
                    if (var7_18 < ((Object)var3_3).length) break;
                    var4_5 = true;
                    break;
                }
                case 79: {
                    if (var1_1.n[game.l.e(var3_3.b()[0], var3_3.b()[1])] == null || var1_1.n[game.l.e(var3_3.b()[0], var3_3.b()[1])][var3_3.b()[2]] != 3 || var1_1.K.l(0) || game.l.E != var3_3.b()[3]) break;
                    game.e.s = true;
                    if (!game.e.t) break;
                    game.e.t = false;
                    ** GOTO lbl113
                }
                case 86: {
                    if (var1_1.n[game.l.e(var3_3.b()[0], var3_3.b()[1])] == null || var1_1.n[game.l.e(var3_3.b()[0], var3_3.b()[1])][var3_3.b()[2]] != 3) break;
                }
lbl113:
                // 7 sources

                default: {
                    var4_5 = true;
                }
            }
            if (!var4_5) continue;
            var1_1.N = (byte)var2_2;
            var1_1.l[var2_2].b((byte)0);
            var1_1.M.addElement(var1_1.l[var2_2]);
            var1_1.J;
            var5_12 = game.l.a(var1_1.N, (byte)0);
            if (var5_12 != -1) {
                var1_1.m = var1_1.N;
                var1_1.J.W.a(var5_12, 1);
                game.l.Y = true;
            }
            var1_1.l[var2_2].a((byte)1);
        }
        this.J();
    }

    public static void b(Graphics graphics) {
        int n2;
        if (P != null) {
            for (n2 = 0; n2 < P.size(); ++n2) {
                g g2 = (g)P.elementAt(n2);
                a.b.a a2 = g2.q;
                a.b.a a3 = a2;
                a3 = g2.q;
                g2.b(a2.j, a3.k - 40);
                g2.a(graphics, a.b.d.a().a, a.b.d.a().b);
            }
        }
        if (p != null) {
            for (n2 = 0; n2 < p.size(); ++n2) {
                ((g)p.elementAt(n2)).a(graphics, a.b.d.a().a, a.b.d.a().b);
            }
        }
    }

    public final void C() {
        int n2;
        if (P != null) {
            for (n2 = 0; n2 < P.size(); ++n2) {
                g g2 = (g)P.elementAt(n2);
                g2.a();
                if (!g2.a.f()) continue;
                g2.d();
                P.removeElementAt(n2);
                --n2;
            }
        }
        if (p != null) {
            for (n2 = 0; n2 < p.size(); ++n2) {
                ((g)p.elementAt(n2)).a();
            }
        }
        if (this.ak != null) {
            this.ak.a();
        }
    }

    public final void a(Graphics graphics) {
        a.a.f.a().a(graphics);
        Graphics graphics2 = graphics;
        e e2 = this;
        if (B != null) {
            graphics2.setColor(0);
            graphics2.fillRect(0, 0, (int)game.e.g(), (int)game.e.h());
            graphics2.drawImage(B, e2.aa, e2.ab, 20);
        }
        if (e2.ak != null && e2.al == game.l.e(e2.J.p, e2.J.q)) {
            e2.ak.a(graphics2, a.b.d.a().a, a.b.d.a().b);
        }
        this.Q.a(graphics);
    }

    public final boolean b() {
        return true;
    }

    public final boolean a(DataInputStream dataInputStream, int n2, int n3, int n4, String[] stringArray) {
        try {
            this.l = new b.a[n4];
            this.M = new Vector();
            P = new Vector();
            int n5 = n2 << 8 | n3;
            if (this.n[game.l.v[n2] + n3] == null) {
                this.n[game.l.v[n2] + n3] = new byte[n4];
            }
            this.N = (byte)-1;
            this.m = (byte)-1;
            for (byte by = 0; by < n4; by = (byte)(by + 1)) {
                this.l[by] = new b.a();
                this.l[by].a(dataInputStream, by, n5, stringArray);
                this.l[by].a(this.n[game.l.v[n2] + n3][by]);
            }
        }
        catch (IOException iOException) {
            System.out.println(" ex = " + iOException.toString() + " event init ");
        }
        this.G();
        return false;
    }

    public final void D() {
        this.K = null;
        this.J = null;
        this.n = null;
        F = null;
        this.am = null;
        I = null;
    }

    public final void c() {
        if (P != null) {
            P.removeAllElements();
            P = null;
        }
        if (p != null) {
            p.removeAllElements();
            p = null;
        }
        this.l = null;
        this.R = null;
        this.S = null;
        this.T = null;
        this.U = null;
        this.V = null;
        this.W = null;
        this.X = null;
        this.Y = null;
        B = null;
        this.af = null;
        this.ag = null;
    }

    public final void a(byte by) {
    }

    private byte l(int n2) {
        for (int i2 = 0; i2 < this.l.length; ++i2) {
            b.b b2;
            if (this.l[i2].a() == 3 || n2 == i2 || (b2 = this.l[i2].d()).a() != 44 || game.l.e(b2.b()[2], b2.b()[3]) != game.l.e(this.J.p, this.J.q) || b2.b()[4] != game.l.E || !this.b(b2)) continue;
            return (byte)i2;
        }
        return -1;
    }

    private static void m(int n2) {
        for (int i2 = 0; i2 < H; ++i2) {
            if (F[i2][0] != n2) continue;
            game.e.F[i2][1] = 3;
            return;
        }
    }

    private static int n(int n2) {
        for (int i2 = 0; i2 < H; ++i2) {
            if (F[i2][0] != n2) continue;
            return i2;
        }
        return -1;
    }

    /*
     * Unable to fully structure code
     */
    private void J() {
        ++this.ao;
        var1_1 = 0;
        while (var1_1 < this.M.size()) {
            var2_2 = (b.a)this.M.elementAt(var1_1);
            var3_3 = var2_2.c();
            switch (var3_3.a()) {
                case 0: {
                    break;
                }
                case 1: {
                    if (var2_2.a() != 5) {
                        a.a.f.a().c(0, 9);
                        this.Q.a(var3_3.b()[1], var3_3.b()[2]);
                        this.Q.a((byte)(var3_3.b()[0] / 10 - 1), var3_3.c()[0], var3_3.b()[0] % 10);
                        this.Q.a(true);
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!game.d.a || !this.L.g(65537) && !this.L.a(80, 294, 80, 18)) break;
                    this.Q.b();
                    if (game.d.b) break;
                    a.a.f.a().a = -1;
                    this.Q.c();
                    ** GOTO lbl1048
                }
                case 2: {
                    if (var3_3.b()[0] == -1) {
                        this.K.c();
                        this.K.a((byte)0, a.e.d(a.e.a(var3_3.c()[1], ',')[0]));
                        break;
                    }
                    for (var4_4 = 0; var4_4 < var3_3.b()[0]; ++var4_4) {
                        this.J.n[a.e.c(a.e.a(var3_3.c()[0], ',')[var4_4])].d(a.e.d(a.e.a(var3_3.c()[1], ',')[var4_4]));
                        if (this.J.n[a.e.c((String)a.e.a((String)var3_3.c()[0], (char)',')[var4_4])].w == 1) {
                            this.J.n[a.e.c(a.e.a(var3_3.c()[0], ',')[var4_4])].a((byte)0);
                        }
                        this.J.n[a.e.c(a.e.a(var3_3.c()[0], ',')[var4_4])].c();
                    }
                    break;
                }
                case 3: {
                    if (var3_3.b()[0] == -1) {
                        this.K.d();
                        break;
                    }
                    for (var5_29 = 0; var5_29 < var3_3.b()[0]; ++var5_29) {
                        var4_5 = a.e.c(a.e.a(var3_3.c()[0], ',')[var5_29]);
                        this.J.n[var4_5].d();
                    }
                    break;
                }
                case 4: {
                    if (var2_2.a() != 5) {
                        this.K.a((byte)0, this.K.o);
                        this.J.d.a(var3_3.c()[0], var3_3.c()[1], (int)var3_3.b()[1], (int)var3_3.b()[0]);
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.J.d.d(var3_3.b()[1], var3_3.b()[0]) || !this.L.g(196640)) break;
                    game.l.B().D();
                    if (a.e.b < a.e.b()) {
                        a.e.c();
                        this.J.d.b(a.e.b);
                        break;
                    }
                    if (game.l.E != -1 && this.J.n[game.l.E].a.a <= 85 && this.J.n[game.l.E].v() == 0) {
                        game.l.B().a((byte)13, game.l.B().n[game.l.E].j, game.l.B().n[game.l.E].k - 40, game.l.B().n[game.l.E]);
                    }
                    game.e.s = false;
                    game.e.t = false;
                    this.J.d.aF();
                    var2_2.a((byte)1);
                    break;
                }
                case 5: {
                    var4_6 = new g();
                    var4_6.a(259, false);
                    var4_6.g();
                    var4_6.a((byte)var3_3.b()[2], (byte)-1, true);
                    if (var3_3.b()[0] == 0) {
                        var4_6.b(this.K.m(), this.K.n() - this.K.a.b(this.K.i(), this.K.o)[3]);
                        var4_6.a(this.K);
                    } else if (var3_3.b()[0] == 1) {
                        if (var3_3.b()[3] != 0 || var3_3.b()[4] != 0) {
                            var4_6.b(var3_3.b()[3], (int)var3_3.b()[4]);
                        } else {
                            var4_6.b(this.J.n[var3_3.b()[1]].m(), this.J.n[var3_3.b()[1]].n());
                            var4_6.a(this.J.n[var3_3.b()[1]]);
                        }
                    }
                    var4_6.c();
                    game.e.P.addElement(var4_6);
                    break;
                }
                case 6: {
                    this.n[game.l.v[this.J.p] + this.J.q][var2_2.b()] = 3;
                    game.l.B().p = var3_3.b()[0];
                    game.l.B().q = var3_3.b()[1];
                    this.J.t = var3_3.b()[3] == 1 ? var3_3.b()[2] : -1;
                    game.f.B().a((byte)22);
                    break;
                }
                case 7: {
                    if (var2_2.a() != 5) {
                        this.R = new short[var3_3.b()[0]];
                        for (var4_7 = 0; var4_7 < this.R.length; ++var4_7) {
                            this.R[var4_7] = a.e.c(a.e.a(var3_3.c()[0], ',')[var4_7]);
                            var5_30 = a.e.d(a.e.a(var3_3.c()[2], ',')[var4_7]);
                            if (this.R[var4_7] == -1) {
                                this.K.a(a.e.d(a.e.a(var3_3.c()[1], ',')[0]), var5_30);
                                continue;
                            }
                            this.J.n[this.R[var4_7]].d(var5_30);
                            this.J.n[this.R[var4_7]].a(a.e.d(a.e.a(var3_3.c()[1], ',')[var4_7]));
                        }
                        this.O = 0;
                        var2_2.a((byte)5);
                        break;
                    }
                    for (var4_8 = 0; var4_8 < this.R.length; ++var4_8) {
                        if (this.R[var4_8] == -1) {
                            if (!this.K.b()) continue;
                            this.K.a((byte)0, this.K.o);
                            ++this.O;
                            continue;
                        }
                        if (!this.J.n[this.R[var4_8]].b()) continue;
                        this.J.n[this.R[var4_8]].a((byte)0);
                        ++this.O;
                    }
                    if (this.O < this.R.length) break;
                    this.O = 0;
                    ** GOTO lbl1048
                }
                case 8: {
                    this.K.c();
                    game.l.E = (short)-1;
                    this.K.b(var3_3.b()[0], (int)var3_3.b()[1]);
                    this.K.b.b(var3_3.b()[0], (int)var3_3.b()[1]);
                    this.K.a((byte)0, this.K.o);
                    break;
                }
                case 9: {
                    if (var2_2.a() != 5) {
                        var4_9 = false;
                        if (var3_3.b()[0] == 12 || var3_3.b()[0] == 13) {
                            a.a.f.a().c(0, var3_3.b()[0]);
                            a.a.f.a().a(var3_3.b()[1], var3_3.b()[2], var3_3.b()[3], var3_3.b()[4], var3_3.b()[5]);
                        } else if (var3_3.b()[0] == 10) {
                            a.a.f.a().c(0, var3_3.b()[0]);
                            a.a.f.a().d(var3_3.b()[1], var3_3.b()[2]);
                        } else if (var3_3.b()[0] == 15 || var3_3.b()[0] == 14) {
                            a.a.f.a().c(0, var3_3.b()[0]);
                            a.a.f.a().a(this.Z[var3_3.b()[1]], (int)var3_3.b()[2], (int)var3_3.b()[3], (int)var3_3.b()[4]);
                        } else if (var3_3.b()[0] == 16) {
                            if (var3_3.b()[1] == 0) {
                                var5_31 = new String[]{"star0", "star1", "star2", "star3"};
                                a.a.f.a().a(16, 0, (byte)var3_3.b()[2], (byte)7, game.l.w, var5_31);
                            } else if (var3_3.b()[1] == 1) {
                                var5_32 = new String[]{"fire0", "fire1", "fire2"};
                                a.a.f.a().a(16, 0, (byte)var3_3.b()[2], (byte)0, null, var5_32);
                            } else if (var3_3.b()[1] == 2) {
                                var5_33 = new String[]{"fire0", "fire1", "fire2"};
                                a.a.f.a().a(17, 0, (byte)var3_3.b()[2], (byte)0, null, var5_33);
                            } else {
                                var4_9 = true;
                                a.a.f.a().a(-1, 0, (byte)var3_3.b()[2], (byte)0, null, null);
                                var2_2.a((byte)1);
                            }
                        } else if (var3_3.b()[0] == 17) {
                            a.a.f.a().c(var3_3.b()[1], var3_3.b()[0]);
                            a.a.f.a().a(var3_3.b()[2], (int)var3_3.b()[3], (int)var3_3.b()[4], (int)var3_3.b()[5]);
                        } else {
                            var5_34 = var3_3.b()[1] << 24 | var3_3.b()[2] << 16 | var3_3.b()[3] << 8 | var3_3.b()[4];
                            a.a.f.a().c(var5_34, var3_3.b()[0]);
                        }
                        if (var4_9) break;
                        var2_2.a((byte)5);
                        break;
                    }
                    if (a.a.f.a().e && (var3_3.b()[0] == 12 || var3_3.b()[0] == 13)) {
                        var2_2.a((byte)1);
                        break;
                    }
                    if (var3_3.b()[0] != 16 && !a.a.f.a().d) break;
                    ** GOTO lbl1048
                }
                case 10: {
                    if (var2_2.a() != 5) {
                        if (var3_3.b()[0] == -1) {
                            this.S = new byte[1];
                            this.K.d(a.e.d(a.e.a(var3_3.c()[1], ',')[0]));
                            this.K.a((byte)0, this.K.o);
                            this.K.b((byte)0, (short)a.e.d(a.e.a(var3_3.c()[2], ',')[0]));
                            this.S[0] = a.e.d(a.e.a(var3_3.c()[3], ',')[0]);
                        } else {
                            this.R = new short[var3_3.b()[0]];
                            this.S = new byte[var3_3.b()[0]];
                            for (var4_10 = 0; var4_10 < this.R.length; ++var4_10) {
                                this.R[var4_10] = a.e.c(a.e.a(var3_3.c()[0], ',')[var4_10]);
                                if (this.R[var4_10] != -1) {
                                    this.J.n[this.R[var4_10]].d(a.e.d(a.e.a(var3_3.c()[1], ',')[var4_10]));
                                    this.J.n[this.R[var4_10]].b((byte)0, a.e.d(a.e.a(var3_3.c()[2], ',')[var4_10]));
                                    this.J.n[this.R[var4_10]].a((byte)0);
                                } else {
                                    this.K.d(a.e.d(a.e.a(var3_3.c()[1], ',')[var4_10]));
                                    this.K.b((byte)0, (short)a.e.d(a.e.a(var3_3.c()[2], ',')[var4_10]));
                                    this.K.a((byte)0, this.K.o);
                                }
                                this.S[var4_10] = a.e.d(a.e.a(var3_3.c()[3], ',')[var4_10]);
                            }
                        }
                        this.O = 0;
                        var2_2.a((byte)5);
                        break;
                    }
                    if (var3_3.b()[0] == -1) {
                        if (this.K.i() == 0) {
                            this.K.a((byte)1, this.K.o);
                            break;
                        }
                        this.S[0] = (byte)(this.S[0] - 1);
                        if (this.S[0] > 0) break;
                        this.K.a((byte)0, this.K.o);
                        if (this.K.Q[0] == 2 || this.K.Q[1] == 2) {
                            this.K.b((byte)0, (short)8);
                        } else {
                            this.K.b((byte)0, (short)4);
                        }
                        var2_2.a((byte)1);
                        break;
                    }
                    for (var4_11 = 0; var4_11 < this.R.length; ++var4_11) {
                        if (this.R[var4_11] != -1 && this.J.n[this.R[var4_11]].i() == 0 || this.R[var4_11] == -1 && this.K.i() == 0) {
                            if (this.S[var4_11] <= 0) continue;
                            if (this.R[var4_11] != -1) {
                                this.J.n[this.R[var4_11]].a((byte)3);
                                continue;
                            }
                            this.K.a((byte)1, this.K.o);
                            continue;
                        }
                        v0 = var4_11;
                        this.S[v0] = (byte)(this.S[v0] - 1);
                        if (this.S[var4_11] > 0) continue;
                        ++this.O;
                        this.S[var4_11] = 0;
                        if (this.R[var4_11] != -1) {
                            this.J.n[this.R[var4_11]].a((byte)0);
                            this.J.n[this.R[var4_11]].b((byte)0, (short)4);
                            continue;
                        }
                        this.K.a((byte)0, this.K.o);
                        if (this.K.Q[0] == 2 || this.K.Q[1] == 2) {
                            this.K.b((byte)0, (short)8);
                            continue;
                        }
                        this.K.b((byte)0, (short)4);
                    }
                    if (this.O < this.R.length) break;
                    ** GOTO lbl1048
                }
                case 11: {
                    if (var2_2.a() != 5) {
                        var4_12 = false;
                        if (var3_3.b()[6] == 0) {
                            var4_12 = true;
                        }
                        a.b.b.a().a(var3_3.b()[7]);
                        if (var3_3.b()[2] == 1) {
                            a.b.b.a().a(var3_3.b()[4], var3_3.b()[5], var3_3.b()[0], var3_3.b()[1], var4_12);
                        } else if (var3_3.b()[2] == 0) {
                            if (var3_3.b()[3] == -1) {
                                a.b.b.a().a(this.K, var3_3.b()[0], var3_3.b()[1], var4_12);
                            } else {
                                a.b.b.a().a(this.J.n[var3_3.b()[3]], var3_3.b()[0], var3_3.b()[1], var4_12);
                            }
                        }
                        this.K.a((byte)0, this.K.o);
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!a.b.b.a().b()) break;
                    ** GOTO lbl1048
                }
                case 12: {
                    ++this.O;
                    if (var2_2.a() != 5) {
                        var2_2.a((byte)5);
                        break;
                    }
                    if (this.O < var3_3.b()[0]) break;
                    this.O = 0;
                    ** GOTO lbl1048
                }
                case 13: {
                    if (a.e.a(var3_3.b()[0], (int)var3_3.b()[1], (int)var3_3.b()[2], (int)var3_3.b()[3], this.K.j, this.K.k, this.K.a.k())) {
                        var2_2.a((byte)1);
                        this.K.a((byte)0, this.K.o);
                        break;
                    }
                    var2_2.a((byte)6);
                    break;
                }
                case 14: {
                    var2_2.a((byte)3);
                    break;
                }
                case 16: {
                    if (var3_3.b()[0] == game.l.E) {
                        game.e.s = true;
                        if (!game.e.t) break;
                        game.e.t = false;
                        var2_2.a((byte)2);
                        break;
                    }
                    var2_2.a((byte)6);
                    break;
                }
                case 17: {
                    if (var2_2.a() != 5) {
                        if (var3_3.b()[0] == 0) {
                            if (this.K.a((int)var3_3.b()[1], (int)var3_3.b()[2], (byte)0)) {
                                var4_13 = a.b.c.c[4][var3_3.b()[1]][0];
                                this.L.d.a("\u0110\u1ea1t \u0111\u01b0\u1ee3c: " + a.a.c(var4_13), (int)var3_3.b()[2]);
                                this.K.c(var3_3.b()[1], var3_3.b()[2], (byte)0);
                            } else {
                                this.L.d.b("Ba l\u00f4 \u0111\u00e3 \u0111\u1ee7 \u0111\u1ea1o c\u1ee5 n\u00e0y");
                            }
                        } else if (this.K.b((int)var3_3.b()[1], (int)var3_3.b()[2], (byte)0)) {
                            var4_14 = a.b.c.c[4][var3_3.b()[1]][0];
                            this.L.d.a("M\u1ea5t: " + a.a.c(var4_14), (int)var3_3.b()[2]);
                            this.K.d(var3_3.b()[1], var3_3.b()[2], (byte)0);
                        }
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.L.d.aA()) break;
                    ** GOTO lbl1048
                }
                case 18: {
                    if (var2_2.a() != 5) {
                        if (var3_3.b()[0] == 0) {
                            if (this.K.a((int)var3_3.b()[1], (int)var3_3.b()[2], (byte)2)) {
                                var4_15 = a.b.c.c[3][var3_3.b()[1]][0];
                                this.L.d.a("\u0110\u1ea1t \u0111\u01b0\u1ee3c: " + a.a.c(var4_15), (int)var3_3.b()[2]);
                                this.K.c(var3_3.b()[1], var3_3.b()[2], (byte)2);
                            } else {
                                this.L.d.b("Ba l\u00f4 \u0111\u00e3 \u0111\u1ee7 \u0111\u1ea1o c\u1ee5 n\u00e0y");
                            }
                        } else if (var3_3.b()[0] == 1) {
                            var4_16 = a.b.c.c[3][var3_3.b()[1]][0];
                            this.L.d.a("M\u1ea5t: " + a.a.c(var4_16), (int)var3_3.b()[2]);
                            this.K.d(var3_3.b()[1], var3_3.b()[2], (byte)2);
                        }
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.L.d.aA()) break;
                    ** GOTO lbl1048
                }
                case 19: {
                    if (var2_2.a() != 5) {
                        var4_17 = a.b.c.c[5][var3_3.b()[0]][0];
                        this.L.d.a("\u0110\u1ea1t \u0111\u01b0\u1ee3c: " + a.a.c(var4_17), (int)var3_3.b()[1]);
                        var5_35 = this.K.d(var3_3.b()[0], var3_3.b()[1]);
                        if (var5_35 != -1) {
                            if (var5_35 == 1) {
                                this.L.d.b("Ba l\u00f4 \u0111\u00e3 \u0111\u1ee7 lo\u1ea1i \u0111\u1ea1o c\u1ee5 n\u00e0y");
                            } else {
                                this.K.c(var3_3.b()[0], var3_3.b()[1]);
                            }
                        } else if (var3_3.b()[0] == 0) {
                            this.K.e(var3_3.b()[0], -1);
                        } else {
                            this.K.j(var3_3.b()[0]);
                        }
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.L.d.aA()) break;
                    ** GOTO lbl1048
                }
                case 20: {
                    if (var2_2.a() != 5) {
                        if (var3_3.b()[0] == 1) {
                            this.L.d.b("M\u1ea5t: " + var3_3.c()[0]);
                            this.K.U[var3_3.b()[1]] = false;
                        } else {
                            this.L.d.b("\u0110\u1ea1t \u0111\u01b0\u1ee3c: " + var3_3.c()[0]);
                            this.K.U[var3_3.b()[1]] = true;
                        }
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.L.d.aA()) break;
                    ** GOTO lbl1048
                }
                case 21: {
                    game.l.H = false;
                    game.l.I = var3_3.b()[2];
                    if (var3_3.b()[1] != 1) break;
                    game.l.J = var3_3.b()[3];
                    game.l.K = var3_3.b()[4];
                    game.l.L = var3_3.b()[5];
                    game.l.M = var3_3.b()[6];
                    break;
                }
                case 22: {
                    game.l.H = true;
                    game.l.G = (byte)var3_3.b()[1];
                    game.l.B().r = var3_3.b()[2];
                    game.l.B().s = var3_3.b()[3];
                    game.l.L = var3_3.b()[4];
                    game.l.M = var3_3.b()[5];
                    game.l.B().t = -1;
                    break;
                }
                case 23: {
                    this.n[game.l.e((int)var3_3.b()[0], (int)var3_3.b()[1])][var3_3.b()[2]] = 3;
                    if (var3_3.b()[0] != this.J.p || var3_3.b()[1] != this.J.q) break;
                    this.l[var3_3.b()[2]].a((byte)3);
                    if (this.M.size() <= 0) break;
                    this.M.removeElement(this.l[var3_3.b()[2]]);
                    --var1_1;
                    break;
                }
                case 24: {
                    if (var2_2.a() != 5) {
                        a.a.f.a().c(0, 11);
                        a.a.f.a().a(var3_3.b()[0], (int)var3_3.b()[1], (int)var3_3.b()[2]);
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!a.a.f.a().d) break;
                    ** GOTO lbl1048
                }
                case 25: {
                    game.e.r = var3_3.b()[0] == 0;
                    break;
                }
                case 29: {
                    if (var2_2.a() != 5) {
                        var4_18 = var3_3.b()[0];
                        if (var3_3.b()[0] == -1) {
                            var4_18 = 1;
                        }
                        this.R = new short[var4_18];
                        this.X = new short[var4_18];
                        this.Y = new short[var4_18];
                        this.T = new short[var4_18];
                        this.U = new short[var4_18];
                        for (var5_36 = 0; var5_36 < this.R.length; ++var5_36) {
                            this.R[var5_36] = a.e.c(a.e.a(var3_3.c()[0], ',')[var5_36]);
                            this.X[var5_36] = a.e.c(a.e.a(var3_3.c()[1], ',')[var5_36]);
                            this.Y[var5_36] = a.e.c(a.e.a(var3_3.c()[2], ',')[var5_36]);
                            this.T[var5_36] = a.e.c(a.e.a(var3_3.c()[3], ',')[var5_36]);
                            this.U[var5_36] = a.e.c(a.e.a(var3_3.c()[4], ',')[var5_36]);
                        }
                        var2_2.a((byte)5);
                        break;
                    }
                    var4_19 = true;
                    for (var7_61 = 0; var7_61 < this.R.length; ++var7_61) {
                        if (this.T[var7_61] <= 0 && this.U[var7_61] <= 0) continue;
                        var4_19 = false;
                        v1 = var7_61;
                        this.T[v1] = (short)(this.T[v1] - 1);
                        v2 = var7_61;
                        this.U[v2] = (short)(this.U[v2] - 1);
                        if (var3_3.b()[0] == -1) {
                            var5_37 = this.K.m() + this.X[var7_61];
                            var6_46 = this.K.n() + this.Y[var7_61];
                            this.K.b(var5_37, var6_46);
                            if (this.K.b == null) continue;
                            this.K.b.b(var5_37, var6_46);
                            continue;
                        }
                        var5_37 = this.J.n[this.R[var7_61]].m() + this.X[var7_61];
                        var6_46 = this.J.n[this.R[var7_61]].n() + this.Y[var7_61];
                        this.J.n[this.R[var7_61]].b(var5_37, var6_46);
                        if (this.J.n[this.R[var7_61]].b == null) continue;
                        this.J.n[this.R[var7_61]].b.b(var5_37, var6_46);
                    }
                    if (!var4_19) break;
                    var2_2.a((byte)1);
                    break;
                }
                case 30: {
                    if (var2_2.a() != 5) {
                        this.R = new short[var3_3.b()[0]];
                        var4_20 = a.e.a(var3_3.c()[2], ',');
                        for (var5_38 = 0; var5_38 < this.R.length; ++var5_38) {
                            this.R[var5_38] = a.e.c(var4_20[var5_38]);
                        }
                        var5_39 = new String[this.R.length][];
                        var6_47 = new String[this.R.length][];
                        for (var7_62 = 0; var7_62 < var6_47.length; ++var7_62) {
                            var5_39[var7_62] = a.e.a(a.e.a(var3_3.c()[0], '#')[var7_62], ',');
                            var6_47[var7_62] = a.e.a(a.e.a(var3_3.c()[1], '#')[var7_62], ',');
                        }
                        this.V = new short[this.R.length][];
                        this.W = new short[this.R.length][];
                        for (var7_62 = 0; var7_62 < this.R.length; ++var7_62) {
                            this.V[var7_62] = new short[var5_39[var7_62].length];
                            this.W[var7_62] = new short[var6_47[var7_62].length];
                            for (var8_70 = 0; var8_70 < this.V[var7_62].length; ++var8_70) {
                                this.V[var7_62][var8_70] = a.e.c(var5_39[var7_62][var8_70]);
                                this.W[var7_62][var8_70] = a.e.c(var6_47[var7_62][var8_70]);
                            }
                        }
                        this.O = 0;
                        var2_2.a((byte)5);
                        break;
                    }
                    for (var4_21 = 0; var4_21 < this.R.length; ++var4_21) {
                        this.J.n[this.R[var4_21]].b(this.V[var4_21][this.O], (int)this.W[var4_21][this.O]);
                    }
                    ++this.O;
                    if (this.O < this.V[0].length) break;
                    ** GOTO lbl1048
                }
                case 31: {
                    if (var2_2.a() != 5) {
                        if (var3_3.b()[0] == 0) {
                            if (var3_3.b()[1] == 0) {
                                this.K.s(var3_3.b()[2]);
                                this.L.d.b("\u0110\u1ea1t \u0111\u01b0\u1ee3c: " + var3_3.b()[2] + " kim ti\u1ec1n");
                            } else if (var3_3.b()[1] == 1) {
                                this.K.v(var3_3.b()[2]);
                                this.L.d.b("\u0110\u1ea1t \u0111\u01b0\u1ee3c: " + var3_3.b()[2] + "Huy hi\u1ec7u");
                            }
                        } else if (var3_3.b()[0] == 1) {
                            if (var3_3.b()[1] == 0) {
                                this.K.s(-var3_3.b()[2]);
                                this.L.d.b("M\u1ea5t: " + var3_3.b()[2] + " kim ti\u1ec1n");
                            } else if (var3_3.b()[1] == 1) {
                                this.K.v(-var3_3.b()[2]);
                                this.L.d.b("M\u1ea5t: " + var3_3.b()[2] + " huy hi\u1ec7u");
                            }
                        }
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.J.d.aA()) break;
                    ** GOTO lbl1048
                }
                case 32: {
                    this.J.D();
                    game.a.B().k = var3_3.b()[0];
                    game.a.B().l = (byte)var3_3.b()[1];
                    this.J.N();
                    this.K.a((byte)0, this.K.o);
                    var2_2.a((byte)1);
                    var4_22 = game.l.a(var2_2.b(), (byte)1);
                    if (var4_22 != -1) {
                        this.J.W.a(var4_22, 1);
                    } else {
                        this.J.W.a(4, 1);
                    }
                    game.f.B().a((byte)12);
                    break;
                }
                case 33: {
                    game.e.k = (byte)var3_3.b()[0];
                    if (game.e.k == 2) {
                        game.e.k = 1;
                        a.b.d.a().c();
                        for (var5_40 = 0; var5_40 < game.l.B().n.length; ++var5_40) {
                            if (!this.J.n[var5_40].k()) continue;
                            this.J.n[var5_40].p();
                        }
                    } else {
                        if (game.e.k != 3) break;
                        game.e.k = 0;
                        a.b.d.a().c();
                        for (var5_41 = 0; var5_41 < game.l.B().n.length; ++var5_41) {
                            if (!this.J.n[var5_41].k()) continue;
                            game.l.B().n[var5_41].p();
                        }
                    }
                    break;
                }
                case 34: {
                    if (var2_2.a() != 5) {
                        game.e.B = a.e.b("/data/tex/", this.Z[var3_3.b()[0]]);
                        this.aa = var3_3.b()[1];
                        this.ab = var3_3.b()[2];
                        this.ac = var3_3.b()[3];
                        this.O = var3_3.b()[4];
                        var2_2.a((byte)5);
                        break;
                    }
                    --this.O;
                    this.ab -= this.ac;
                    if (this.O > 0) break;
                    this.O = 0;
                    ** GOTO lbl1048
                }
                case 35: {
                    if (var2_2.a() != 5) {
                        this.ad = var3_3.b()[0];
                        this.ae = var3_3.b()[1];
                        this.ag = a.e.a(var3_3.c()[0], ',');
                        this.af = new byte[a.e.a(var3_3.c()[1], ',').length];
                        var5_42 = var3_3.c()[2];
                        for (var6_48 = 0; var6_48 < this.af.length; ++var6_48) {
                            this.af[var6_48] = a.e.d(a.e.a(var3_3.c()[1], ',')[var6_48]);
                        }
                        this.L.d.a(this.ae, this.ad, this.ag, var5_42);
                        var2_2.a((byte)5);
                        break;
                    }
                    var5_43 = this.L.d.c(this.ae);
                    if (var5_43 == -1) break;
                    var2_2.b((byte)(this.af[var5_43] - 2));
                    var2_2.a((byte)1);
                    break;
                }
                case 36: {
                    if (var2_2.a() != 5) {
                        var5_44 = this.K.z();
                        if (var3_3.b()[0] == 0) {
                            if (var5_44 == 0) {
                                this.K.a(var3_3.b()[1], var3_3.b()[2], (short)-1, (byte)var3_3.b()[4], (byte)var3_3.b()[3], (byte)-1, new int[]{1, var3_3.b()[5], var3_3.b()[6]});
                            } else if (var5_44 == 1) {
                                this.L.d.b("Ba l\u00f4 \u0111\u00e3 \u0111\u1ee7, \u0111\u00e3 \u0111\u1ec3 v\u00e0o ng\u00e2n h\u00e0ng");
                                var6_49 = game.i.b(var3_3.b()[1], var3_3.b()[2], var3_3.b()[3]);
                                this.K.a(var3_3.b()[1], var3_3.b()[2], (short)-1, (byte)var3_3.b()[4], (byte)var3_3.b()[3], (byte)-1, var6_49, 0, 0, new int[]{1, var3_3.b()[5], var3_3.b()[6]});
                            } else {
                                this.L.d.b("Kh\u00f4ng c\u00f3 kh\u00f4ng gian, \u0111\u00e3 ph\u00f3ng sinh");
                            }
                        } else if (var3_3.b()[0] == 1) {
                            this.K.o(var3_3.b()[1]);
                        }
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.L.d.aA()) break;
                    ** GOTO lbl1048
                }
                case 37: {
                    game.a.B().a(new int[][]{{var3_3.b()[0], var3_3.b()[1], var3_3.b()[2]}});
                    break;
                }
                case 38: {
                    game.e.s = false;
                    for (var6_50 = 0; var6_50 < a.e.a(var3_3.c()[0], ',').length; ++var6_50) {
                        if (a.e.d(a.e.a(var3_3.c()[0], ',')[var6_50]) != game.l.E) continue;
                        game.e.s = true;
                        if (!game.e.t) break;
                        game.l.E = (short)-1;
                        var5_45 = a.e.d(a.e.a(var3_3.c()[1], ',')[var6_50]);
                        var2_2.b((byte)(var5_45 - 1));
                        game.l.B().D();
                        game.e.s = false;
                        game.e.t = false;
                        break;
                    }
                    var2_2.a((byte)6);
                    break;
                }
                case 39: {
                    for (var6_51 = 0; var6_51 < this.K.B; ++var6_51) {
                        this.K.A[var6_51].J();
                    }
                    break;
                }
                case 40: {
                    if (var2_2.a() != 5) {
                        this.L.d.c(var3_3.c()[0]);
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.L.d.aB()) break;
                    ** GOTO lbl1048
                }
                case 41: {
                    var2_2.b((byte)(var3_3.b()[0] - 2));
                    break;
                }
                case 42: {
                    var2_2.a((byte)4);
                    break;
                }
                case 45: {
                    if (var2_2.a() != 5) {
                        this.L.d.c(var3_3.c()[0]);
                        game.e.G = (byte)var3_3.b()[0];
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.L.d.aB()) break;
                    ** GOTO lbl1048
                }
                case 46: {
                    if (var2_2.a() != 5) {
                        this.L.d.K();
                        this.L.d.a(var3_3.c()[0]);
                        var2_2.a((byte)5);
                        break;
                    }
                    if (this.L.d.f == 0) {
                        if (this.L.g(196640)) {
                            this.L.d.f = 1;
                            this.L.d.a("\u0110ang l\u01b0u...");
                            this.L.d.M();
                            break;
                        }
                        if (!this.L.g(262144)) break;
                        var2_2.a((byte)1);
                        this.L.d.L();
                        this.L.d.f = 0;
                        break;
                    }
                    if (this.L.d.f == 1) {
                        this.n[game.l.e((int)this.J.p, (int)this.J.q)][var2_2.b()] = 3;
                        if (!((l)this.L).I()) break;
                        this.L.d.a("L\u01b0u th\u00e0nh c\u00f4ng");
                        this.L.d.f = 2;
                        break;
                    }
                    if (this.L.d.f != 2) break;
                    this.L.d.L();
                    this.L.d.f = 0;
                    ** GOTO lbl1048
                }
                case 47: {
                    if (this.y == -1) break;
                    var2_2.b((byte)(var3_3.b()[this.y] - 2));
                    break;
                }
                case 48: {
                    if (var2_2.a() != 5) {
                        this.Q.a(var3_3.b()[1], var3_3.b()[2]);
                        this.Q.a((byte)(var3_3.b()[0] / 10 - 1), var3_3.c()[0], var3_3.b()[0] % 10);
                        if (var3_3.b()[5] == 1) {
                            this.Q.a(true);
                        }
                        this.Q.b(var3_3.b()[3], var3_3.b()[4]);
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.Q.e()) ** GOTO lbl666
                    if (!game.d.a || !this.L.g(1) && !this.L.a(80, 294, 80, 18)) break;
                    this.Q.b();
                    if (game.d.b) break;
                    a.a.f.a().a = -1;
                    this.Q.c();
                    ** GOTO lbl1048
lbl666:
                    // 1 sources

                    var2_2.a((byte)1);
                    break;
                }
                case 49: {
                    if (var2_2.a() != 5) {
                        this.ah = new int[2];
                        this.ai = new int[2];
                        this.aj = new String[2];
                        this.ag = new String[2];
                        for (var6_52 = 0; var6_52 < 2; ++var6_52) {
                            this.ah[var6_52] = var3_3.b()[var6_52 << 1];
                            this.ai[var6_52] = var3_3.b()[(var6_52 << 1) + 1];
                            this.aj[var6_52] = var3_3.c()[var6_52];
                        }
                        this.af = new byte[a.e.a(var3_3.c()[2], ',').length];
                        for (var6_52 = 0; var6_52 < this.af.length; ++var6_52) {
                            this.af[var6_52] = a.e.d(a.e.a(var3_3.c()[2], ',')[var6_52]);
                            this.ag[var6_52] = a.e.a(var3_3.c()[3], ',')[var6_52];
                        }
                        this.L.d.a(this.ah, this.ai, this.aj, this.ag);
                        var2_2.a((byte)5);
                        break;
                    }
                    var6_53 = this.L.d.aG();
                    if (var6_53 == -1) break;
                    if (var6_53 == 0 && var2_2.d().b()[1] == 1) {
                        game.e.F[game.e.H][1] = 1;
                        game.e.H = (byte)(game.e.H + 1);
                    }
                    var2_2.b((byte)(this.af[var6_53] - 2));
                    var2_2.a((byte)1);
                    break;
                }
                case 50: {
                    if (var3_3.b()[0] == 0) {
                        this.K.v();
                        break;
                    }
                    this.K.u();
                    break;
                }
                case 51: {
                    this.J.d.aE();
                    this.Q.a(var3_3.b()[1], var3_3.b()[2]);
                    this.Q.a((byte)(var3_3.b()[0] / 10 - 1), var3_3.c()[0], var3_3.b()[0] % 10);
                    this.Q.b(var3_3.b()[3], var3_3.b()[4]);
                    break;
                }
                case 52: {
                    this.u = var3_3.b()[0] == 0;
                    if (var3_3.b()[1] == 0) {
                        game.e.v = true;
                        break;
                    }
                    game.e.v = false;
                    break;
                }
                case 53: {
                    if (var2_2.a() != 5) {
                        if (var3_3.b()[1] == 0) {
                            this.K.b((byte)var3_3.b()[0], (byte)var3_3.b()[1], (byte)2);
                            for (var6_54 = 0; var6_54 < game.l.B().n.length; ++var6_54) {
                                if (game.l.B().n[var6_54].u != 0 || game.l.B().n[var6_54].w != 1) continue;
                                game.l.B().n[var6_54].w();
                            }
                        } else if (var3_3.b()[1] == 1) {
                            this.K.b((byte)var3_3.b()[0], (byte)var3_3.b()[1], (byte)1);
                        }
                        this.L.d.a(var3_3.b()[0]);
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.J.g(65537) && !this.L.a(94, 200, 60, 18)) break;
                    this.L.d.Y();
                    ** GOTO lbl1048
                }
                case 54: {
                    var6_55 = var3_3.b()[0];
                    var7_63 = new int[var6_55][3];
                    for (var8_71 = 0; var8_71 < var6_55; ++var8_71) {
                        var7_63[var8_71][0] = a.e.b(a.e.a(var3_3.c()[0], ',')[var8_71]);
                        var7_63[var8_71][1] = a.e.b(a.e.a(var3_3.c()[1], ',')[var8_71]);
                        var7_63[var8_71][2] = a.e.b(a.e.a(var3_3.c()[2], ',')[var8_71]);
                    }
                    game.a.B().a(var7_63);
                    break;
                }
                case 55: {
                    if (var3_3.b()[0] == 0) {
                        if (this.ak == null) {
                            this.ak = new g();
                            this.ak.a(340, false);
                            this.ak.c();
                            this.al = game.l.e(var3_3.b()[3], var3_3.b()[4]);
                        }
                        this.ak.b(var3_3.b()[1], (int)var3_3.b()[2]);
                        break;
                    }
                    if (var3_3.b()[0] != 1 || this.ak == null) break;
                    this.ak.d();
                    this.ak = null;
                    this.al = -1;
                    break;
                }
                case 56: {
                    var6_56 = var3_3.b()[1];
                    if (var3_3.b()[0] == 0) {
                        for (var7_64 = 0; var7_64 < var6_56; ++var7_64) {
                            var8_72 = a.e.c(a.e.a(var3_3.c()[0], ',')[var7_64]);
                            var4_23 = a.e.d(a.e.a(var3_3.c()[1], ',')[var7_64]);
                            this.J.n[var8_72].d(var4_23);
                            if (this.J.n[var8_72].w == 1) {
                                this.J.n[var8_72].a((byte)0);
                            }
                            this.J.n[var8_72].c();
                            this.J.a((int)var8_72, 1, (byte)1, true);
                            this.J.a((int)var8_72, 2, var4_23, true);
                            this.J.n[var8_72].t();
                        }
                    } else {
                        if (var3_3.b()[0] != 1) break;
                        for (var7_65 = 0; var7_65 < var6_56; ++var7_65) {
                            var8_73 = a.e.c(a.e.a(var3_3.c()[0], ',')[var7_65]);
                            if (this.J.n[var8_73].w == 1) {
                                this.J.n[var8_73].a((byte)0);
                            }
                            this.J.n[var8_73].d();
                            this.J.a((int)var8_73, 1, (byte)0, true);
                            this.J.n[var8_73].t();
                        }
                    }
                    break;
                }
                case 58: {
                    if (((h)this.K.q).i() != 1 || !((h)this.K.q).a.f()) break;
                    ((h)this.K.q).a((byte)0);
                    this.J.n[var3_3.b()[0]].b(var3_3.b()[1], (int)var3_3.b()[2]);
                    if ((h)this.J.n[var3_3.b()[0]].q == null) break;
                    ((h)this.J.n[var3_3.b()[0]].q).s();
                    this.J.n[var3_3.b()[0]].a(null);
                    break;
                }
                case 60: {
                    if (var2_2.a() != 5) {
                        this.R = new short[var3_3.b()[0]];
                        for (var6_57 = 0; var6_57 < this.R.length; ++var6_57) {
                            this.R[var6_57] = a.e.c(a.e.a(var3_3.c()[0], ',')[var6_57]);
                            this.J.n[this.R[var6_57]].a(a.e.d(a.e.a(var3_3.c()[1], ',')[var6_57]));
                            if (this.J.n[this.R[var6_57]].u != 0 || this.J.n[this.R[var6_57]].w != 6 || this.J.n[this.R[var6_57]].i() != 2) continue;
                            game.l.B().l.a(game.l.B().n[this.R[var6_57]], 2);
                        }
                        this.O = 0;
                        var2_2.a((byte)5);
                        break;
                    }
                    for (var6_58 = 0; var6_58 < this.R.length; ++var6_58) {
                        if (!this.J.n[this.R[var6_58]].b()) continue;
                        ++this.O;
                    }
                    if (this.O < this.R.length) break;
                    this.O = 0;
                    ** GOTO lbl1048
                }
                case 62: {
                    var6_59 = new int[a.e.a(var3_3.c()[0], ',').length];
                    var8_74 = -1;
                    var2_2.a((byte)6);
                    for (var7_66 = 0; var7_66 < var6_59.length; ++var7_66) {
                        var6_59[var7_66] = a.e.b(a.e.a(var3_3.c()[0], ',')[var7_66]);
                        if (this.J.n[var6_59[var7_66]].i() != 2) continue;
                        var8_74 = var6_59[var7_66];
                        break;
                    }
                    if (var8_74 < 0) break;
                    if (var8_74 == var3_3.b()[0]) ** GOTO lbl821
                    var2_2.b((byte)(var3_3.b()[2] - 2));
                    ** GOTO lbl1048
lbl821:
                    // 1 sources

                    var2_2.b((byte)(var3_3.b()[1] - 2));
                    var2_2.a((byte)1);
                    break;
                }
                case 63: {
                    if (var3_3.b()[0] == 0) {
                        this.K.h(var3_3.b()[1]);
                    } else {
                        this.K.t();
                    }
                    this.x = var3_3.b()[2] != 0;
                    break;
                }
                case 64: {
                    if (var3_3.b()[0] == 0) {
                        this.J.l(var3_3.b()[1]);
                        if (var3_3.b()[2] == -1) {
                            this.J.a((g)this.K);
                            break;
                        }
                        this.J.a(this.J.n[var3_3.b()[2]]);
                        break;
                    }
                    this.J.E();
                    break;
                }
                case 65: {
                    if (var2_2.a() != 5 && !game.e.i) {
                        this.L.a((byte)100);
                        var2_2.a((byte)5);
                        break;
                    }
                    if (game.e.i) {
                        var2_2.b((byte)(var3_3.b()[0] - 2));
                    } else {
                        var2_2.b((byte)(var3_3.b()[1] - 2));
                    }
                    ** GOTO lbl1048
                }
                case 66: {
                    a.a.f = (byte)var3_3.b()[0];
                    a.a.b(0, 3);
                    break;
                }
                case 67: {
                    game.l.F = var3_3.b()[0];
                    break;
                }
                case 70: {
                    if (var2_2.a() != 5) {
                        game.e.q = false;
                        this.o = var3_3.b()[0];
                        switch (var3_3.b()[0]) {
                            case 0: 
                            case 1: {
                                this.J.a((byte)1);
                                break;
                            }
                            case 2: {
                                this.J.a((byte)16);
                            }
                        }
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!game.e.q) break;
                    this.o = -1;
                    ** GOTO lbl1048
                }
                case 71: {
                    if (this.K.G >= var3_3.b()[0]) {
                        var2_2.b((byte)(var3_3.b()[1] - 2));
                        break;
                    }
                    var2_2.b((byte)(var3_3.b()[2] - 2));
                    break;
                }
                case 72: {
                    var6_60 = a.e.a(var3_3.c()[0], ',');
                    var7_67 = a.e.a(var3_3.c()[1], ',');
                    var8_75 = new g[var6_60.length];
                    for (var4_24 = 0; var4_24 < var6_60.length; ++var4_24) {
                        var8_75[var4_24] = new g();
                        var8_75[var4_24].a(259, false);
                        var8_75[var4_24].g();
                        if (a.e.b(var6_60[var4_24]) == -1) {
                            var8_75[var4_24].a(a.e.d(var7_67[var4_24]), (byte)-1, true);
                            var8_75[var4_24].c();
                            var8_75[var4_24].b(this.K.m(), this.K.n() - 40);
                            var8_75[var4_24].a(this.K);
                        } else {
                            var8_75[var4_24].a(a.e.d(var7_67[var4_24]), (byte)-1, true);
                            var8_75[var4_24].c();
                            var8_75[var4_24].b(this.J.n[a.e.b(var6_60[var4_24])].m(), this.J.n[a.e.b(var6_60[var4_24])].n() - 40);
                            var8_75[var4_24].a(this.J.n[a.e.b(var6_60[var4_24])]);
                        }
                        game.e.P.addElement(var8_75[var4_24]);
                    }
                    break;
                }
                case 74: {
                    if (((int[])this.K.L.elementAt(0))[1] > 0) {
                        var2_2.b((byte)(var3_3.b()[0] - 2));
                        break;
                    }
                    var2_2.b((byte)(var3_3.b()[1] - 2));
                    break;
                }
                case 76: {
                    this.n[game.l.v[this.J.p] + this.J.q][var2_2.b()] = 3;
                    game.l.B().p = var3_3.b()[0];
                    game.l.B().q = var3_3.b()[1];
                    game.l.B().t = -1;
                    this.J.a((byte)29);
                    break;
                }
                case 77: {
                    this.n[game.l.e((int)var3_3.b()[0], (int)var3_3.b()[1])][var3_3.b()[2]] = 4;
                    if (var3_3.b()[0] != this.J.p || var3_3.b()[1] != this.J.q) break;
                    this.l[var3_3.b()[2]].a((byte)4);
                    break;
                }
                case 80: {
                    if (var2_2.a() != 5) {
                        if (var3_3.b()[0] == 0) {
                            this.O = 0;
                            this.A = (byte)4;
                        } else if (var3_3.b()[0] == 1) {
                            game.f.B().m = game.f.B().l;
                            var7_68 = game.f.B().m - game.f.B().k;
                            if (a.e.a(var7_68)[2] <= 60L) {
                                var4_25 = this.K.z();
                                if (var4_25 == 0) {
                                    this.L.d.b("\u0110\u1ea1t \u0111\u01b0\u1ee3c #2L\u1ee5c h\u00e0nh \u0111i\u1ec3u");
                                    this.K.a(54, 5, (short)-1, (byte)2, (short)-1, (byte)-1, new int[]{1, 30, 45});
                                } else if (var4_25 == 1) {
                                    this.L.d.b("\u0110\u1ea1t \u0111\u01b0\u1ee3c #2L\u1ee5c h\u00e0nh \u0111i\u1ec3u#0 ba l\u00f4 \u0111\u00e3 \u0111\u1ee7, \u0111\u00e3 \u0111\u1ec3 v\u00e0o ng\u00e2n h\u00e0ng");
                                    var4_25 = a.e.b(a.b.c.c[0][54][3], a.b.c.c[0][54][3]);
                                    this.K.a(54, 5, (short)-1, (byte)2, (byte)var4_25, (byte)-1, 0, 0, 0, new int[]{1, 30, 45});
                                } else {
                                    this.L.d.b("Kh\u00f4ng c\u00f3 kh\u00f4ng gian, \u0111\u00e3 ph\u00f3ng sinh");
                                }
                            } else if (a.e.a(var7_68)[2] <= 65L) {
                                this.K.s(1000);
                                this.L.d.b("Th\u01b0\u1edfng 1000 kim");
                            } else if (a.e.a(var7_68)[2] <= 130L) {
                                this.K.s(750);
                                this.L.d.b("Th\u01b0\u1edfng 750 kim");
                            } else if (a.e.a(var7_68)[2] <= 200L) {
                                this.K.s(600);
                                this.L.d.b("Th\u01b0\u1edfng 600 kim");
                            }
                            game.f.B().l = 0L;
                            game.f.B().k = 0L;
                        }
                        var2_2.a((byte)5);
                        break;
                    }
                    if (var3_3.b()[0] == 0) {
                        ++this.O;
                        if (this.A > 0) {
                            if (this.O / 10 == 0 || this.O % 10 != 0) break;
                            this.A = (byte)(this.A - 1);
                            break;
                        }
                        this.A = 0;
                        game.f.B().l = game.f.B().k = System.currentTimeMillis();
                        game.f.B().m = 0L;
                    } else {
                        if (var3_3.b()[0] != 1 || !this.L.d.aA()) break;
                        if (this.C == 0) {
                            this.an = this.H();
                        }
                        this.C = (byte)(this.C + 1);
                    }
                    ** GOTO lbl1048
                }
                case 81: {
                    if (var3_3.b()[0] == 0) {
                        if (this.K.u(var3_3.b()[1])) {
                            var2_2.b((byte)(var3_3.b()[2] - 2));
                            break;
                        }
                        var2_2.b((byte)(var3_3.b()[3] - 2));
                        break;
                    }
                    if (var3_3.b()[0] != 1) break;
                    if (this.K.x(var3_3.b()[1])) {
                        var2_2.b((byte)(var3_3.b()[2] - 2));
                        break;
                    }
                    var2_2.b((byte)(var3_3.b()[3] - 2));
                    break;
                }
                case 82: {
                    var7_69 = var3_3.b()[0];
                    var8_76 = a.e.e(var3_3.c()[0]);
                    for (var4_26 = 0; var4_26 < var7_69; ++var4_26) {
                        this.J.n[var8_76[var4_26]].t();
                        this.J.n[var8_76[var4_26]].u();
                    }
                    break;
                }
                case 83: {
                    if (var2_2.a() != 5) {
                        this.L.a((byte)30);
                        var2_2.a((byte)5);
                        break;
                    }
                    var2_2.b((byte)(var3_3.b()[game.e.z] - 2));
                    ** GOTO lbl1048
                }
                case 84: {
                    if (var2_2.a() != 5) {
                        var4_27 = null;
                        if (var3_3.b()[2] == 1) {
                            var4_27 = new int[]{this.C, 5 - this.C};
                        } else if (var3_3.b()[2] == 0) {
                            var4_27 = new int[]{this.K.J, this.K.S.length - this.K.J};
                        }
                        var4_27 = game.e.a(var3_3.c()[1], var4_27);
                        this.J.d.a(var3_3.c()[0], (String)var4_27, (int)var3_3.b()[1], (int)var3_3.b()[0]);
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.J.d.d(var3_3.b()[1], var3_3.b()[0]) || !this.L.g(196640)) break;
                    game.l.B().D();
                    if (a.e.b < a.e.b()) {
                        a.e.c();
                        this.J.d.b(a.e.b);
                        break;
                    }
                    if (game.l.E != -1 && this.J.n[game.l.E].a.a <= 85 && this.J.n[game.l.E].v() == 0) {
                        game.l.B().a((byte)13, game.l.B().n[game.l.E].j, game.l.B().n[game.l.E].k - 40, game.l.B().n[game.l.E]);
                    }
                    game.e.s = false;
                    game.e.t = false;
                    this.J.d.aF();
                    var2_2.a((byte)1);
                    break;
                }
                case 85: {
                    if (this.C >= 0 && this.C < 5) {
                        var2_2.b((byte)(var3_3.b()[0] - 2));
                        break;
                    }
                    var2_2.b((byte)(var3_3.b()[1] - 2));
                    break;
                }
                case 87: {
                    if (var2_2.a() != 5) {
                        if (var3_3.b()[0] == 0) {
                            this.K.a(var3_3.b()[7], var3_3.b()[1], var3_3.b()[2], (short)-1, (byte)var3_3.b()[4], (byte)var3_3.b()[3], (byte)-1, new int[]{1, var3_3.b()[5], var3_3.b()[6]});
                        } else if (var3_3.b()[0] == 1) {
                            this.K.o(var3_3.b()[1]);
                        }
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.L.d.aA()) break;
                    ** GOTO lbl1048
                }
                case 88: {
                    if (this.K.z() == 2) {
                        var2_2.b((byte)(var3_3.b()[0] - 2));
                    } else {
                        var2_2.b((byte)(var3_3.b()[1] - 2));
                    }
lbl1048:
                    // 31 sources

                    var2_2.a((byte)1);
                }
            }
            if (var2_2.a() != 5 && var2_2.a() != 6) {
                var2_2.e();
            }
            if (var2_2.a() == 3 || var2_2.a() == 4) {
                game.e.t = false;
                this.M.removeElement(var2_2);
                var4_28 = game.l.e(var2_2.f()[0], var2_2.f()[1]);
                if (this.n[var4_28] != null) {
                    this.n[var4_28][var2_2.b()] = var2_2.a();
                }
                if (var2_2.a() == 3 && var2_2.d().a() == 44 && var2_2.d().b()[1] == 1) {
                    game.e.m(var2_2.d().b()[0]);
                }
                this.G();
                if (game.l.Y) {
                    this.J.W.a(game.l.X, 1);
                }
                game.l.Y = false;
                continue;
            }
            ++var1_1;
        }
    }

    public final byte E() {
        return this.N;
    }

    public final boolean F() {
        if (this.l == null) {
            return false;
        }
        for (int i2 = 0; i2 < this.M.size(); ++i2) {
            b.a a2 = (b.a)this.M.elementAt(i2);
            if (a2.a() == 2 || a2.a() == 6) continue;
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean a(b.b b2) {
        boolean bl = false;
        if (b2.b()[7] != -1) {
            if (b2.b()[7] == -1) return bl;
            if (this.n[game.l.e(b2.b()[5], b2.b()[6])] == null) return bl;
            if (this.n[game.l.e(b2.b()[5], b2.b()[6])][b2.b()[7]] != 3) return bl;
        }
        switch (b2.b()[8]) {
            case 0: {
                if (!this.K.U[b2.b()[9]]) return bl;
                break;
            }
            case 1: {
                return true;
            }
            case 2: {
                int n2;
                if (this.K.P.size() + this.K.B < b2.b()[9]) return bl;
                for (n2 = 0; n2 < this.K.B; ++n2) {
                    if (this.K.A[n2].t() != b2.b()[10]) continue;
                    bl = true;
                    break;
                }
                if (bl) return bl;
                n2 = 0;
                while (n2 < this.K.P.size()) {
                    if (((int[])this.K.P.elementAt(n2))[1] == b2.b()[10]) return true;
                    ++n2;
                }
                return bl;
            }
            case 3: {
                if (this.K.G < b2.b()[9]) return bl;
                break;
            }
            case 4: {
                if (this.K.a((byte)b2.b()[9], (int)b2.b()[10]) != 2) return bl;
                break;
            }
            case 5: {
                if (G <= b2.b()[9]) return bl;
                break;
            }
            case 6: {
                if (G != b2.b()[9]) return bl;
            }
        }
        return true;
    }

    private boolean b(b.b object) {
        boolean bl = false;
        if (((b.b)object).b()[7] == -1 || ((b.b)object).b()[7] != -1 && this.n[game.l.e(((b.b)object).b()[5], ((b.b)object).b()[6])] != null && this.n[game.l.e(((b.b)object).b()[5], ((b.b)object).b()[6])][((b.b)object).b()[7]] == 3) {
            switch (((b.b)object).b()[8]) {
                case 0: {
                    if (this.K.a((byte)((b.b)object).b()[9], (int)((b.b)object).b()[10]) != 2) break;
                    bl = true;
                    break;
                }
                case 1: {
                    if (!this.K.U[((b.b)object).b()[9]]) break;
                    bl = true;
                    break;
                }
                case 2: 
                case 4: {
                    if (this.n[game.l.e(((b.b)object).b()[5], ((b.b)object).b()[6])] == null || this.n[game.l.e(((b.b)object).b()[5], ((b.b)object).b()[6])][((b.b)object).b()[7]] != 3) break;
                    bl = true;
                    break;
                }
                case 3: {
                    if (!this.K.b((int)((b.b)object).b()[9], (int)((b.b)object).b()[10], (byte)0)) break;
                    bl = true;
                    break;
                }
                case 5: {
                    if (this.K.G < ((b.b)object).b()[9]) break;
                    bl = true;
                    break;
                }
                case 6: {
                    int n2;
                    object = new byte[]{0, 1, 2, 3};
                    block8: for (n2 = 0; n2 < this.K.B; ++n2) {
                        for (int i2 = 0; i2 < ((Object)object).length; ++i2) {
                            if (object[i2] == -1 || object[i2] != a.b.c.a((byte)0, (short)this.K.A[n2].r(), (byte)1)) continue;
                            object[i2] = -1;
                            continue block8;
                        }
                    }
                    for (n2 = 0; n2 < ((Object)object).length && object[n2] == -1; ++n2) {
                    }
                    if (n2 < ((Object)object).length) break;
                    bl = true;
                }
            }
        }
        return bl;
    }

    public final void G() {
        h h2;
        g g2;
        g g3;
        b.b b2;
        int n2;
        if (p == null) {
            p = new Vector();
        }
        p.removeAllElements();
        Vector<String> vector = new Vector<String>();
        for (n2 = 0; n2 < aq.length; ++n2) {
            if (game.l.e(aq[n2][0], aq[n2][1]) != game.l.e(game.l.B().p, game.l.B().q) || this.l[aq[n2][2]].a() != 0 && this.l[aq[n2][2]].a() != 4 || vector.contains("" + (b2 = this.l[aq[n2][2]].d()).b()[4])) continue;
            if (this.b(b2)) {
                g3 = new g();
                g3.a(259, false);
                g3.a((byte)1, (byte)-1, true);
                h h3 = this.J.n[b2.b()[4]];
                g2 = h3;
                g2 = this.J.n[b2.b()[4]];
                g3.b(h3.j, g2.k - 40);
                this.J.n[b2.b()[4]].f((byte)1);
                h2 = this.J.n[b2.b()[4]];
                g2 = g3;
                g3.q = h2;
                g3.c();
                p.addElement(g3);
                vector.addElement("" + b2.b()[4]);
                continue;
            }
            int n3 = game.e.n(b2.b()[0]);
            if ((b2.b()[1] != 0 || this.n[game.l.e(ap[n2][0], ap[n2][1])][ap[n2][2]] != 3 || this.n[game.l.e(aq[n2][0], aq[n2][1])][aq[n2][2]] == 3) && (b2.b()[1] != 1 || n3 == -1 || F[n3][1] != 1)) continue;
            g g4 = new g();
            g4.a(259, false);
            g4.a((byte)15, (byte)-1, true);
            h h4 = this.J.n[b2.b()[4]];
            g2 = h4;
            g2 = this.J.n[b2.b()[4]];
            g4.b(h4.j, g2.k - 40);
            this.J.n[b2.b()[4]].f((byte)1);
            h2 = this.J.n[b2.b()[4]];
            g2 = g4;
            g4.q = h2;
            g4.c();
            p.addElement(g4);
            vector.addElement("" + b2.b()[4]);
        }
        for (n2 = 0; n2 < ap.length; ++n2) {
            if (game.l.e(ap[n2][0], ap[n2][1]) != game.l.e(game.l.B().p, game.l.B().q) || this.l[ap[n2][2]].a() != 0 && this.l[ap[n2][2]].a() != 4 || vector.contains("" + (b2 = this.l[ap[n2][2]].d()).b()[4]) || !this.a(b2)) continue;
            g3 = new g();
            g3.a(259, false);
            g3.a((byte)7, (byte)-1, true);
            h h5 = this.J.n[b2.b()[4]];
            g2 = h5;
            g2 = this.J.n[b2.b()[4]];
            g3.b(h5.j, g2.k - 40);
            this.J.n[b2.b()[4]].f((byte)1);
            h2 = this.J.n[b2.b()[4]];
            g2 = g3;
            g3.q = h2;
            g3.c();
            p.addElement(g3);
        }
    }

    public final int[] H() {
        int[] nArray = new int[4];
        int[] nArray2 = nArray;
        nArray[0] = this.am.get(1);
        nArray2[1] = this.am.get(2);
        nArray2[2] = this.am.get(5);
        nArray2[3] = this.am.get(11);
        return this.an;
    }

    public final int[] I() {
        return this.an;
    }

    public final void a(int[] nArray) {
        this.an = nArray;
    }

    static {
        I = null;
        q = false;
        r = true;
        s = false;
        t = false;
        v = true;
        z = (byte)-1;
        F = null;
        G = 0;
        H = 0;
    }
}

