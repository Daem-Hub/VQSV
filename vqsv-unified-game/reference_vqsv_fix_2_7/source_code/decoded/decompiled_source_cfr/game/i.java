/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
package game;

import a.a.b;
import a.b.c;
import a.b.g;
import a.e;
import game.a;
import game.j;
import game.l;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class i
extends a.a.g {
    private static short[] N = new short[]{90, 95, 100, 110, 125};
    public static final byte[] u = new byte[]{12, 30, 5};
    b v;
    short[][] w;
    short[][] x;
    byte[][] y;
    private byte[] O;
    short[] z;
    byte[] A;
    private byte P;
    private short[] Q;
    protected int B = 0;
    private int R;
    private int S = 0;
    private int T = 0;
    protected int C = 0;
    private int U = 0;
    protected short D;
    private byte V = 0;
    private int W = 0;
    private byte X;
    protected byte E;
    private int Y = 0;
    private boolean Z;
    protected short F;
    protected byte G;
    protected Vector H = new Vector();
    protected Vector I = new Vector();
    public byte J = 0;
    protected boolean K;
    protected short[] L;
    protected b M = null;
    private byte aa = 0;

    public i() {
        this.d = new short[23];
        this.e = new short[23];
        this.z = new short[5];
        this.A = new byte[5];
        this.Q = new short[4];
        for (int i2 = 0; i2 < this.A.length; ++i2) {
            this.A[i2] = -1;
        }
        this.L = new short[16];
        this.w = new short[16][5];
        this.x = new short[11][5];
        this.y = new byte[][]{{-1, -1, -1}, {-1, -1, -1}};
        this.O = new byte[2];
        this.c = false;
    }

    public final void a(int n2, int n3, short s, byte by, short s2, byte by2) {
        i i2;
        short s3;
        this.W = n2;
        this.U = n3;
        if (s2 == -1) {
            s3 = (short)a.e.b(a.b.c.c[0][this.W][3], a.b.c.c[0][this.W][3]);
            s2 = 0;
            i2 = this;
            i2.d[s2] = s3;
        } else {
            s3 = s2;
            s2 = 0;
            i2 = this;
            i2.d[s2] = s3;
        }
        s2 = 0;
        i2 = this;
        s3 = (short)((a.b.c.c[0][this.W][5] + a.b.c.c[0][this.W][6] * n3 + a.b.c.c[0][this.W][7]) * N[i2.d[s2] - 1] / 100);
        s2 = 1;
        i2 = this;
        i2.d[s2] = s3;
        s2 = 0;
        i2 = this;
        s3 = (short)((a.b.c.c[0][this.W][8] + a.b.c.c[0][this.W][9] * n3 + a.b.c.c[0][this.W][10]) * N[i2.d[s2] - 1] / 100);
        s2 = (short)2;
        i2 = this;
        i2.d[s2] = s3;
        s2 = 0;
        i2 = this;
        s3 = (short)((a.b.c.c[0][this.W][11] + a.b.c.c[0][this.W][12] * n3 / 10 + a.b.c.c[0][this.W][13]) * N[i2.d[s2] - 1] / 100);
        s2 = (short)3;
        i2 = this;
        i2.d[s2] = s3;
        s2 = 0;
        i2 = this;
        s3 = (short)((a.b.c.c[0][this.W][14] + a.b.c.c[0][this.W][15] * n3 / 10 + a.b.c.c[0][this.W][16]) * N[i2.d[s2] - 1] / 100);
        s2 = (short)4;
        i2 = this;
        i2.d[s2] = s3;
        s3 = s;
        s2 = (short)5;
        i2 = this;
        i2.d[s2] = s3;
        s3 = by;
        s2 = (short)6;
        i2 = this;
        i2.d[s2] = s3;
        this.i(by2);
        this.h();
        this.D = a.b.c.c[0][this.W][17];
        i i3 = this;
        i2 = i3;
        s2 = 1;
        i3.u(i2.e[s2]);
    }

    public final void a(int[] nArray) {
        this.a(nArray[0], nArray[1], (short)nArray[2], (byte)nArray[3], (short)nArray[4], (byte)nArray[5]);
        this.V();
        this.a((short)nArray[6], nArray[7], nArray[8]);
        int[] nArray2 = new int[nArray.length - 9];
        for (int i2 = 0; i2 < nArray2.length; ++i2) {
            nArray2[i2] = nArray[i2 + 9];
        }
        this.b(nArray2);
    }

    public final void a(short s, int n2, int n3) {
        this.V();
        short s2 = s;
        int n4 = 1;
        i i2 = this;
        i2.e[n4] = s2;
        n4 = 1;
        i2 = this;
        this.u(i2.e[n4]);
        this.T = n2;
        this.F = (byte)n3;
    }

    public final void c() {
        super.c();
        if (this.a == null) {
            this.a = new g();
        }
        this.a.a((int)this.D, false);
        this.a((byte)0, true);
    }

    public final void d() {
        super.d();
        if (this.a != null) {
            this.a.b();
            this.a = null;
        }
    }

    public final void a(short s, byte by) {
        byte by2 = this.o;
        this.v = null;
        this.v = new b();
        this.v.a(new short[]{s, by, by2});
        this.v.b(this.j, this.k);
        if (s == 20 && by == 3 || s == 22 && by == 4) {
            int[] nArray = this.a.b(0, by2);
            this.v.b(this.j, this.k - nArray[3]);
        }
        this.v.d(true);
    }

    private void z(int n2) {
        this.M = new b();
        Object object = game.a.x[n2];
        short[] sArray = new short[((short[])object).length + 5];
        System.arraycopy(object, 1, sArray, 6, ((short[])object).length - 1);
        short[] sArray2 = new short[6];
        sArray2[0] = object[0];
        i i2 = this;
        object = i2;
        sArray2[1] = (short)i2.j;
        i i3 = this;
        object = i3;
        sArray2[2] = (short)i3.k;
        sArray2[3] = a.b.c.c[0][this.W][17];
        sArray2[4] = 0;
        sArray2[5] = this.o;
        object = sArray2;
        System.arraycopy(sArray2, 0, sArray, 0, ((short[])object).length);
        this.M.a(sArray);
        this.M.d(true);
    }

    public final void a(byte by, boolean bl) {
        switch (by) {
            case 0: {
                this.a.a(by, (byte)-1, true);
                break;
            }
            case 1: {
                this.a.a(by, (byte)0, true);
                i i2 = this;
                switch (i2.W) {
                    case 0: {
                        this.z(27);
                        break;
                    }
                    case 10: {
                        this.aa = 1;
                        this.z(28);
                        break;
                    }
                    case 67: {
                        this.aa = 1;
                        this.z(30);
                        break;
                    }
                    case 68: {
                        this.aa = 1;
                        this.z(31);
                        break;
                    }
                    case 70: {
                        this.aa = 1;
                        this.z(29);
                        break;
                    }
                    case 71: {
                        this.aa = 1;
                        this.z(32);
                        break;
                    }
                    case 72: {
                        this.aa = 1;
                        this.z(33);
                        break;
                    }
                    case 91: {
                        this.z(26);
                        break;
                    }
                    case 92: {
                        this.z(25);
                        break;
                    }
                    case 97: 
                    case 98: {
                        this.z(23);
                        break;
                    }
                    case 62: {
                        this.z(24);
                        break;
                    }
                    case 60: 
                    case 61: {
                        this.z(22);
                        break;
                    }
                    case 75: {
                        this.z(20);
                        break;
                    }
                    case 87: {
                        this.z(21);
                    }
                }
                break;
            }
            case 2: {
                this.a.a(by, (byte)0, true);
                break;
            }
            case 3: {
                if (game.a.B().l != 0) break;
                this.d();
                i i3 = this;
                Object object = new short[]{16, 0, 0, 4};
                i3.M = new b();
                short[] sArray = new short[((short[])object).length + 5];
                System.arraycopy(object, 1, sArray, 6, ((short[])object).length - 1);
                short[] sArray2 = new short[6];
                sArray2[0] = object[0];
                i i4 = i3;
                object = i4;
                sArray2[1] = (short)i4.j;
                i i5 = i3;
                object = i5;
                sArray2[2] = (short)i5.k;
                sArray2[3] = a.b.c.c[0][i3.W][17];
                sArray2[4] = 0;
                sArray2[5] = i3.o;
                object = sArray2;
                System.arraycopy(sArray2, 0, sArray, 0, ((short[])object).length);
                i3.M.a(sArray);
                i3.M.d(true);
                i3.M.a();
                break;
            }
            case 4: {
                this.a.a(by, (byte)-1, true);
            }
        }
        this.V = by;
    }

    public final void p() {
        this.a();
        if (this.v != null) {
            this.v.d();
        }
        if (this.M != null) {
            this.M.d();
        }
    }

    public final void a(Graphics graphics) {
        i i2;
        if (this.M != null) {
            i2 = this;
            if (i2.V == 1) {
                i2 = this;
                switch (i2.W) {
                    case 0: {
                        if (!this.a.b(5)) break;
                        this.M.a();
                        break;
                    }
                    case 10: {
                        if (!this.a.b(5)) break;
                        this.M.a();
                        break;
                    }
                    case 67: {
                        if (!this.a.b(1)) break;
                        this.M.a();
                        break;
                    }
                    case 68: {
                        if (!this.a.b(3)) break;
                        this.M.a();
                        break;
                    }
                    case 70: {
                        if (!this.a.b(9)) break;
                        this.M.a();
                        break;
                    }
                    case 71: {
                        if (!this.a.b(7)) break;
                        this.M.a();
                        break;
                    }
                    case 72: {
                        if (!this.a.b(4)) break;
                        this.M.a();
                        break;
                    }
                    case 91: {
                        if (!this.a.b(2)) break;
                        this.M.a();
                        break;
                    }
                    case 92: {
                        if (!this.a.b(4)) break;
                        this.M.a();
                        break;
                    }
                    case 97: 
                    case 98: {
                        if (!this.a.b(8)) break;
                        this.M.a();
                        break;
                    }
                    case 62: {
                        if (!this.a.b(8)) break;
                        this.M.a();
                        break;
                    }
                    case 60: 
                    case 61: {
                        if (!this.a.b(3)) break;
                        this.M.a();
                        break;
                    }
                    case 75: {
                        if (!this.a.b(15)) break;
                        this.M.a();
                        break;
                    }
                    case 87: {
                        if (!this.a.b(1)) break;
                        this.M.a();
                    }
                }
            }
        }
        if (this.M != null && this.aa == 0) {
            this.M.a(graphics, 0, 0);
        }
        if (this.v != null && game.a.w[this.v.a - 20][this.v.b.g()][this.v.b.h()] == 0) {
            this.v.a(graphics, 0, 0);
        }
        Graphics graphics2 = graphics;
        i2 = this;
        if (i2.g) {
            i2.a.a(graphics2, i2.j, i2.k, i2.o);
        }
        if (this.M != null && this.aa == 1) {
            this.M.a(graphics, 0, 0);
        }
        if (this.v != null && game.a.w[this.v.a - 20][this.v.b.g()][this.v.b.h()] == 1) {
            this.v.a(graphics, 0, 0);
        }
    }

    public final byte q() {
        return this.V;
    }

    public final int r() {
        return this.W;
    }

    public final void f(int n2) {
        this.Y = n2;
    }

    public final int s() {
        return this.Y;
    }

    public final int t() {
        return this.U;
    }

    public final boolean u() {
        return this.U == 50;
    }

    protected final void g(int n2) {
        if (this.U >= 50) {
            return;
        }
        this.T += n2;
        if (this.T < 0) {
            this.T = 0;
        }
    }

    public final int v() {
        if (this.U >= 50) {
            return game.i.A(50);
        }
        i i2 = this;
        return game.i.A(i2.U + 1);
    }

    public final void w() {
        ++this.U;
        this.g(-game.i.A(this.U));
        this.K();
        for (int i2 = 0; i2 < this.A.length; ++i2) {
            if (this.A[i2] == -1) continue;
            this.z[i2] = a.b.c.c[1][this.A[i2]][5];
        }
        this.W();
    }

    public final void h(int n2) {
        this.U += n2;
        this.K();
        this.W();
    }

    private static int A(int n2) {
        return n2 * 15 * n2 - 200;
    }

    private void V() {
        short s;
        i i2;
        int n2;
        if (game.j.p().b((byte)1, (byte)0) == 2 && game.j.p().b((byte)1, (byte)1) == 1) {
            n2 = 1;
            i2 = this;
            s = (short)(i2.d[n2] * a.b.c.c[2][1][6] / 100);
            n2 = 1;
            i2 = this;
            s = (short)(i2.d[n2] + s);
            n2 = 1;
            i2 = this;
            i2.d[n2] = s;
        }
        if (game.j.p().b((byte)2, (byte)0) == 2 && game.j.p().b((byte)2, (byte)1) == 1) {
            n2 = 3;
            i2 = this;
            s = (short)(i2.d[n2] * a.b.c.c[2][2][6] / 100);
            n2 = 3;
            i2 = this;
            s = (short)(i2.d[n2] + s);
            n2 = 3;
            i2 = this;
            i2.d[n2] = s;
        }
    }

    private void W() {
        int n2 = 0;
        i i2 = this;
        short s = (short)((a.b.c.c[0][this.W][5] + a.b.c.c[0][this.W][6] * this.U + a.b.c.c[0][this.W][7]) * N[i2.d[n2] - 1] / 100);
        n2 = 1;
        i2 = this;
        i2.d[n2] = s;
        n2 = 0;
        i2 = this;
        s = (short)((a.b.c.c[0][this.W][8] + a.b.c.c[0][this.W][9] * this.U + a.b.c.c[0][this.W][10]) * N[i2.d[n2] - 1] / 100);
        n2 = 2;
        i2 = this;
        i2.d[n2] = s;
        n2 = 0;
        i2 = this;
        s = (short)((a.b.c.c[0][this.W][11] + a.b.c.c[0][this.W][12] * this.U / 10 + a.b.c.c[0][this.W][13]) * N[i2.d[n2] - 1] / 100);
        n2 = 3;
        i2 = this;
        i2.d[n2] = s;
        n2 = 0;
        i2 = this;
        s = (short)((a.b.c.c[0][this.W][14] + a.b.c.c[0][this.W][15] * this.U / 10 + a.b.c.c[0][this.W][16]) * N[i2.d[n2] - 1] / 100);
        n2 = 4;
        i2 = this;
        i2.d[n2] = s;
        this.h();
        n2 = 1;
        i2 = this;
        this.u(i2.e[n2]);
    }

    public static short b(int n2, int n3, int n4) {
        return (short)((a.b.c.c[0][n2][5] + a.b.c.c[0][n2][6] * n3 + a.b.c.c[0][n2][7]) * N[n4 - 1] / 100);
    }

    public final void x() {
        for (int i2 = 0; i2 < this.Q.length; ++i2) {
            int n2 = 0;
            i i3 = this;
            this.Q[i2] = game.i.a(this.W, this.U - 5, i3.d[n2], i2 + 1);
        }
    }

    public final void y() {
        for (int i2 = 0; i2 < this.Q.length; ++i2) {
            byte by = (byte)(i2 + 1);
            i i3 = this;
            this.Q[i2] = i3.d[by];
        }
    }

    public final short i(int n2) {
        return this.Q[n2];
    }

    public final void z() {
        if (this.G < 20) {
            this.G = (byte)(this.G + 1);
            int n2 = 2;
            i i2 = this;
            short s = i2.d[n2];
            n2 = 2;
            i2 = this;
            short s2 = (short)(s + i2.d[n2] * this.G / 100);
            n2 = 2;
            i2 = this;
            i2.e[n2] = s2;
            n2 = 3;
            i2 = this;
            short s3 = i2.d[n2];
            n2 = 3;
            i2 = this;
            s2 = (short)(s3 + i2.d[n2] * this.G / 100);
            n2 = 3;
            i2 = this;
            i2.e[n2] = s2;
            n2 = 4;
            i2 = this;
            short s4 = i2.d[n2];
            n2 = 4;
            i2 = this;
            s2 = (short)(s4 + i2.d[n2] * this.G / 100);
            n2 = 4;
            i2 = this;
            i2.e[n2] = s2;
        }
    }

    public final int A() {
        return this.T;
    }

    public final void j(int n2) {
        this.S = n2;
    }

    public final int B() {
        return this.S;
    }

    public final int C() {
        int n2;
        int n3;
        a.b.a a2 = (i)this.q;
        if (a2.Y == 0 && game.j.p().b((byte)4, (byte)0) == 2) {
            n3 = 3;
            a2 = this.q;
            n2 = (short)(a2.d[n3] * (100 + a.b.c.c[2][4][5]) / 100);
            n3 = 3;
            a2 = this.q;
            a2.e[n3] = n2;
        }
        if (((i)this.q).f((byte)2)) {
            n3 = 2;
            a2 = this;
            short s = a2.e[n3];
            n3 = 3;
            a2 = this.q;
            n2 = s - a2.e[n3] * (100 + a.b.c.c[3][2][5]) / 100;
        } else {
            n3 = 2;
            a2 = this;
            short s = a2.e[n3];
            n3 = 3;
            a2 = this.q;
            n2 = s - a2.e[n3];
        }
        if (this.f((byte)0)) {
            n3 = 1;
            a2 = this;
            short s = a2.e[n3];
            n3 = 1;
            a2 = this;
            if (s <= a.b.c.c[3][0][5] * a2.d[n3] / 100) {
                n3 = 2;
                a2 = this;
                int n4 = a2.e[n3] * (100 + a.b.c.c[3][0][6]) / 100;
                n3 = 3;
                a2 = this.q;
                n2 = n4 - a2.e[n3];
            }
        } else if (this.f((byte)1)) {
            n3 = 2;
            a2 = this;
            int n5 = a2.e[n3] * (100 + a.b.c.c[3][1][5]) / 100;
            n3 = 3;
            a2 = this.q;
            n2 = n5 - a2.e[n3];
        }
        return n2;
    }

    public final int a(byte n2) {
        int n3 = n2;
        i i2 = this;
        int n4 = i2.d[n3];
        switch (n2) {
            case 2: {
                if (this.f((byte)0)) {
                    n3 = 1;
                    i2 = this;
                    short s = i2.e[n3];
                    n3 = 1;
                    i2 = this;
                    if (s > a.b.c.c[3][0][5] * i2.d[n3] / 100) break;
                    n3 = 2;
                    i2 = this;
                    n4 = i2.d[n3] * (100 + a.b.c.c[3][0][6]) / 100;
                    break;
                }
                if (!this.f((byte)1)) break;
                n3 = 2;
                i2 = this;
                n4 = i2.d[n3] * (100 + a.b.c.c[3][1][5]) / 100;
                break;
            }
            case 3: {
                if (!this.f((byte)2)) break;
                n3 = 3;
                i2 = this;
                n4 = i2.e[n3] * (100 + a.b.c.c[3][2][5]) / 100;
            }
        }
        return n4;
    }

    public final void k(int n2) {
        int n3 = n2;
        if (n2 <= 0) {
            n3 = 1;
        }
        int n4 = 1;
        i i2 = this;
        this.u(i2.e[n4]);
        n4 = 1;
        i2 = this;
        n3 = (short)(i2.e[n4] - n3);
        n4 = 1;
        i2 = this;
        i2.e[n4] = n3;
        n4 = 1;
        i2 = this;
        if (i2.e[n4] <= 0) {
            n3 = 0;
            n4 = 1;
            i2 = this;
            i2.e[n4] = n3;
        }
    }

    public final void l(int n2) {
        int n3 = 1;
        i i2 = this;
        n2 = (short)(i2.e[n3] + n2);
        n3 = 1;
        i2 = this;
        i2.e[n3] = n2;
        n3 = 1;
        i2 = this;
        short s = i2.e[n3];
        n3 = 1;
        i2 = this;
        if (s >= i2.d[n3]) {
            n3 = 1;
            i2 = this;
            n2 = i2.d[n3];
            n3 = 1;
            i2 = this;
            i2.e[n3] = n2;
        }
    }

    private void B(int n2) {
        for (int i2 = 0; i2 < this.A.length; ++i2) {
            if (this.A[i2] == -1) continue;
            int n3 = i2;
            this.z[n3] = (short)(this.z[n3] + n2);
            if (this.z[i2] < a.b.c.c[1][this.A[i2]][5]) continue;
            this.z[i2] = a.b.c.c[1][this.A[i2]][5];
        }
    }

    public final boolean m(int n2) {
        return this.w[n2][4] == 1;
    }

    public final int a(byte by, int n2, int n3) {
        int n4 = 0;
        if (by == -1) {
            return 0;
        }
        switch (by) {
            case 0: {
                int n5 = 3;
                i i2 = this;
                this.w[by][1] = (short)(i2.d[n5] * a.b.c.c[6][by][3] / 100);
                this.w[by][2] = (short)(a.b.c.c[6][by][4] * this.C() / 100);
                n5 = 3;
                i2 = this;
                n2 = (short)(i2.d[n5] + this.w[by][1]);
                n5 = 3;
                i2 = this;
                i2.e[n5] = n2;
                break;
            }
            case 1: {
                int n6 = 3;
                i i3 = this;
                this.w[by][1] = (short)(i3.d[n6] * a.b.c.c[6][by][3] / 100);
                this.w[by][2] = a.b.c.c[6][by][4];
                n6 = 3;
                i3 = this;
                n2 = (short)(i3.d[n6] - this.w[by][1]);
                n6 = 3;
                i3 = this;
                i3.e[n6] = n2;
                break;
            }
            case 2: {
                int n7 = 3;
                i i4 = this;
                this.w[by][1] = (short)(i4.d[n7] * a.b.c.c[6][by][3] / 100);
                this.w[by][2] = a.b.c.c[6][by][4];
                n7 = 3;
                i4 = this;
                n2 = (short)(i4.d[n7] + this.w[by][1]);
                n7 = 3;
                i4 = this;
                i4.e[n7] = n2;
                break;
            }
            case 3: {
                int n8 = 1;
                i i5 = this;
                this.w[by][1] = (short)(i5.d[n8] * a.b.c.c[6][by][3] / 100);
                n4 = this.w[by][1];
                n8 = 1;
                i5 = this;
                this.u(i5.e[n8]);
                this.l(this.w[by][1]);
                break;
            }
            case 4: {
                this.L[4] = (short)n3;
                int n9 = 3;
                i i6 = this;
                this.w[by][1] = (short)(i6.d[n9] * a.b.c.c[1][n3][8] / 100);
                n9 = 3;
                i6 = this;
                n2 = (short)(i6.d[n9] + this.w[by][1]);
                n9 = 3;
                i6 = this;
                i6.e[n9] = n2;
                break;
            }
            case 5: {
                this.w[by][1] = a.b.c.c[6][by][3];
                break;
            }
            case 6: {
                this.w[by][1] = a.b.c.c[6][by][3];
                this.w[by][2] = a.b.c.c[6][by][4];
                break;
            }
            case 7: {
                this.L[7] = (short)n3;
                int n10 = 4;
                i i7 = this;
                this.w[by][1] = (short)(i7.d[n10] * a.b.c.c[1][n3][8] / 100);
                n10 = 4;
                i7 = this;
                n2 = (short)(i7.d[n10] + this.w[by][1]);
                n10 = 4;
                i7 = this;
                i7.e[n10] = n2;
                break;
            }
            case 8: {
                this.w[by][1] = a.b.c.c[6][by][3];
                break;
            }
            case 9: {
                int n11 = 4;
                i i8 = this;
                this.w[by][1] = (short)(i8.d[n11] * a.b.c.c[6][by][3] / 100);
                n11 = 3;
                i8 = this;
                this.w[by][2] = (short)(i8.d[n11] * a.b.c.c[6][by][4] / 100);
                n11 = 4;
                i8 = this;
                n2 = (short)(i8.d[n11] + this.w[by][1]);
                n11 = 4;
                i8 = this;
                i8.e[n11] = n2;
                n11 = 3;
                i8 = this;
                n2 = (short)(i8.d[n11] - this.w[by][2]);
                n11 = 3;
                i8 = this;
                i8.e[n11] = n2;
                break;
            }
            case 10: {
                int n12 = 2;
                i i9 = this;
                this.w[by][1] = (short)(i9.d[n12] * a.b.c.c[6][by][3] / 100);
                n12 = 2;
                i9 = this;
                n2 = (short)(i9.d[n12] + this.w[by][1]);
                n12 = 2;
                i9 = this;
                i9.e[n12] = n2;
                break;
            }
            case 11: {
                this.w[by][1] = (short)n2;
                i i10 = game.a.B().n[n2];
                int n13 = 0;
                while (true) {
                    int n14 = 0;
                    i i11 = i10;
                    if (n13 >= i11.O[n14]) break;
                    this.a(i10.y[0][n13], (int)i10.w[i10.y[0][n13]][1], (int)game.a.B().n[n2].L[n13]);
                    ++n13;
                }
                i10.E();
                break;
            }
            case 12: {
                this.L[12] = 1;
                break;
            }
            case 13: {
                int n15 = 1;
                i i12 = this;
                this.w[by][1] = (short)(i12.d[n15] * a.b.c.c[6][by][3] / 100);
                n4 = this.w[by][1];
                n15 = 1;
                i12 = this;
                this.u(i12.e[n15]);
                this.l(this.w[by][1]);
                this.D();
                break;
            }
            case 14: {
                this.D();
                break;
            }
            case 15: {
                this.w[by][1] = (short)(n2 * a.b.c.c[6][by][3]);
            }
        }
        this.a(0, by);
        this.w[by][0] = a.b.c.c[6][by][2];
        this.w[by][4] = 1;
        return n4;
    }

    public final void n(int n2) {
        this.w[n2][4] = 0;
        for (n2 = 2; n2 <= 4; n2 = (int)((byte)(n2 + 1))) {
            int n3 = n2;
            i i2 = this;
            short s = i2.d[n3];
            n3 = n2;
            i2 = this;
            i2.e[n3] = s;
        }
    }

    public final int o(int n2) {
        int n3 = 0;
        switch (n2) {
            case 0: {
                break;
            }
            case 1: {
                int n4 = 3;
                i i2 = this;
                short s = (short)(i2.d[n4] - this.w[n2][1]);
                n4 = 3;
                i2 = this;
                i2.e[n4] = s;
                break;
            }
            case 2: {
                int n5 = 3;
                i i3 = this;
                short s = (short)(i3.d[n5] + this.w[n2][1]);
                n5 = 3;
                i3 = this;
                i3.e[n5] = s;
                break;
            }
            case 3: {
                n3 = this.w[n2][1];
                int n6 = 1;
                i i4 = this;
                this.u(i4.e[n6]);
                this.l(this.w[n2][1]);
                break;
            }
            case 4: {
                int n7 = 3;
                i i5 = this;
                short s = (short)(i5.e[n7] + this.w[n2][1]);
                n7 = 3;
                i5 = this;
                i5.e[n7] = s;
                break;
            }
            case 5: {
                break;
            }
            case 6: {
                break;
            }
            case 7: {
                int n8 = 4;
                i i6 = this;
                short s = (short)(i6.d[n8] + this.w[n2][1]);
                n8 = 4;
                i6 = this;
                i6.e[n8] = s;
                break;
            }
            case 8: {
                break;
            }
            case 9: {
                int n9 = 4;
                i i7 = this;
                short s = (short)(i7.d[n9] + this.w[n2][1]);
                n9 = 4;
                i7 = this;
                i7.e[n9] = s;
                n9 = 3;
                i7 = this;
                s = (short)(i7.d[n9] - this.w[n2][2]);
                n9 = 3;
                i7 = this;
                i7.e[n9] = s;
                break;
            }
            case 10: {
                int n10 = 2;
                i i8 = this;
                short s = (short)(i8.d[n10] + this.w[n2][1]);
                n10 = 2;
                i8 = this;
                i8.e[n10] = s;
                break;
            }
            case 11: {
                i i9 = game.a.B().n[this.w[11][1]];
                int n11 = 0;
                while (true) {
                    int n12 = 0;
                    i i10 = i9;
                    if (n11 >= i10.O[n12]) break;
                    this.a(i9.y[0][n11], (int)i9.w[i9.y[0][n11]][1], (int)game.a.B().n[this.w[11][1]].L[n11]);
                    ++n11;
                }
                i9.E();
                break;
            }
            case 12: {
                this.L[12] = 2;
                break;
            }
            case 13: {
                n3 = this.w[n2][1];
                int n13 = 1;
                i i11 = this;
                this.u(i11.e[n13]);
                this.l(this.w[n2][1]);
            }
        }
        return n3;
    }

    public final boolean p(int n2) {
        return this.x[n2][4] == 1;
    }

    public final void D() {
        int n2;
        for (n2 = 0; n2 < 11; ++n2) {
            if (this.x[n2][4] != 1) continue;
            this.C(n2);
        }
        for (n2 = 0; n2 < 3; ++n2) {
            this.e(1, n2);
        }
    }

    public final void E() {
        int n2;
        for (n2 = 0; n2 < 16; ++n2) {
            if (this.w[n2][4] != 1) continue;
            this.n(n2);
        }
        for (n2 = 0; n2 < 3; ++n2) {
            this.e(0, n2);
        }
    }

    private void C(int n2) {
        this.x[n2][4] = 0;
        for (n2 = 2; n2 <= 4; n2 = (int)((byte)(n2 + 1))) {
            int n3 = n2;
            i i2 = this;
            short s = i2.d[n3];
            n3 = n2;
            i2 = this;
            i2.e[n3] = s;
        }
    }

    private void e(int n2, int n3) {
        this.y[n2][n3] = -1;
        if (this.O[n2] > 0) {
            int n4 = n2;
            this.O[n4] = (byte)(this.O[n4] - 1);
        }
    }

    public final void q(int n2) {
        switch (n2) {
            case 0: {
                short s = this.x[0][1];
                n2 = a.b.c.c[1][this.x[0][3]][8];
                this.k(s / n2);
                if (!this.T()) {
                    this.a((byte)3, true);
                }
                return;
            }
            case 1: {
                return;
            }
            case 2: {
                return;
            }
            case 3: {
                if (this.x[n2][0] > 1) break;
                short s = this.x[n2][1];
                n2 = a.b.c.c[1][this.x[n2][3]][8];
                this.k(s * n2 / 100);
                if (!this.T()) {
                    this.a((byte)3, true);
                }
                return;
            }
            case 4: {
                return;
            }
            case 5: {
                int n3 = 4;
                i i2 = this;
                n2 = (short)(i2.d[n3] - this.x[n2][1]);
                n3 = 4;
                i2 = this;
                i2.e[n3] = n2;
                return;
            }
            case 6: {
                return;
            }
            case 7: {
                int n4 = 3;
                i i3 = this;
                n2 = (short)(i3.d[n4] - this.x[n2][1]);
                n4 = 3;
                i3 = this;
                i3.e[n4] = n2;
            }
        }
    }

    public final void c(int n2, int n3) {
        if (this.p(n2)) {
            if (this.x[n2][0] > 0) {
                short[] sArray = this.x[n2];
                sArray[0] = (short)(sArray[0] - 1);
            }
            if (this.x[n2][0] <= 0) {
                this.C(n2);
                this.e(1, n3);
            }
        }
    }

    public final void d(int n2, int n3) {
        if (this.m(n2)) {
            if (this.w[n2][0] > 0) {
                short[] sArray = this.w[n2];
                sArray[0] = (short)(sArray[0] - 1);
            }
            if (this.w[n2][0] <= 0) {
                this.n(n2);
                this.e(0, n3);
            }
        }
    }

    private void a(int n2, byte by) {
        int n3;
        for (n3 = 0; n3 < 3; ++n3) {
            int n4;
            if (this.y[n2][n3] != -1) continue;
            for (n4 = 0; n4 < 3; ++n4) {
                if (this.y[n2][n4] != by) continue;
                return;
            }
            if (n4 < 3) continue;
            this.y[n2][n3] = by;
            if (this.O[n2] >= 3) break;
            int n5 = n2;
            this.O[n5] = (byte)(this.O[n5] + 1);
            break;
        }
        if (n3 >= 3) {
            this.y[n2][0] = by;
        }
    }

    public final byte r(int n2) {
        return this.O[n2];
    }

    public final boolean f(byte by) {
        int n2 = 5;
        i i2 = this;
        return i2.d[n2] == by;
    }

    public final int F() {
        return this.P;
    }

    public final int[] G() {
        int n2;
        int[] nArray = null;
        Vector<String> vector = new Vector<String>();
        short s = a.b.c.c[0][this.W][18];
        short s2 = a.b.c.c[0][this.W][1];
        int n3 = this.X();
        for (n2 = s2 * 10; n2 < s2 * 10 + 10; ++n2) {
            int n4 = 0;
            if (a.b.c.c[1][n2][4] > a.b.c.c[8][s][n3]) continue;
            for (n4 = 0; n4 < this.A.length && n2 != this.A[n4]; ++n4) {
            }
            if (n4 < this.A.length) continue;
            vector.addElement(String.valueOf(n2));
        }
        if (vector.size() > 0) {
            nArray = new int[vector.size()];
            for (n2 = 0; n2 < nArray.length; ++n2) {
                nArray[n2] = Integer.parseInt((String)vector.elementAt(n2));
            }
        }
        return nArray;
    }

    public final void g(byte by) {
        for (int i2 = 0; i2 < this.A.length; ++i2) {
            if (this.A[i2] != -1) continue;
            this.A[i2] = by;
            this.P = (byte)(this.P + 1);
            this.z[i2] = a.b.c.c[1][by][5];
            return;
        }
    }

    public final void H() {
        int n2 = a.b.c.c[0][this.W][1];
        if (this.U <= 5) {
            n2 *= 10;
            boolean bl = true;
            for (int i2 = 0; i2 < this.A.length; ++i2) {
                if (n2 != this.A[i2]) continue;
                bl = false;
                break;
            }
            if (bl) {
                this.g((byte)n2);
            }
            return;
        }
        i i3 = this;
        if (i3.P >= this.X() + 1) {
            return;
        }
        int[] nArray = this.G();
        int n3 = nArray.length;
        for (int i4 = 0; i4 < nArray.length; ++i4) {
            this.g((byte)nArray[n2]);
            i i5 = this;
            i3 = i5;
            i3 = this;
            if (i5.P >= i3.U / 10 + 1) break;
            for (n2 = a.e.a(n3); n2 < n3 - 1; ++n2) {
                nArray[n2] = nArray[n2 + 1];
            }
            --n3;
        }
    }

    public final boolean s(int n2) {
        if (n2 == -1) {
            return false;
        }
        return this.z[n2] > 0;
    }

    public final void a(byte by, i i2) {
        i i3 = i2;
        i2 = this;
        this.q = i3;
        this.E = by;
        for (int i4 = 0; i4 < this.A.length; ++i4) {
            if (this.A[i4] != by) continue;
            int n2 = i4;
            this.z[n2] = (short)(this.z[n2] - 1);
            if (this.m(12) && this.L[12] == 1) {
                int n3 = i4;
                this.z[n3] = (short)(this.z[n3] + 1);
            }
            if (!this.m(8)) continue;
            int n4 = i4;
            this.z[n4] = (short)(this.z[n4] - 1);
        }
    }

    public final void b(int[] nArray) {
        this.P = (byte)nArray[0];
        for (int i2 = 0; i2 < nArray[0]; ++i2) {
            this.A[i2] = (byte)nArray[i2 + 1];
            this.z[i2] = (short)nArray[nArray[0] + 1 + i2];
        }
    }

    public final byte t(int n2) {
        if (n2 > this.A.length - 1 || n2 < 0) {
            return -1;
        }
        return this.A[n2];
    }

    public static short a(byte by, byte by2) {
        return a.b.c.c[1][by][by2];
    }

    private int X() {
        int[] nArray = new int[]{5, 10, 20, 30, 40};
        int n2 = 0;
        for (int i2 = 0; i2 < nArray.length; ++i2) {
            if (this.U < nArray[i2]) continue;
            n2 = i2;
        }
        return n2;
    }

    public final void h(byte by) {
        this.E = by;
    }

    public final byte I() {
        return this.E;
    }

    public final void h() {
        this.V();
        super.h();
        int n2 = 1;
        i i2 = this;
        this.u(i2.d[n2]);
    }

    public final void J() {
        for (int i2 = 0; i2 < this.A.length; ++i2) {
            if (this.A[i2] == -1) continue;
            this.z[i2] = a.b.c.c[1][this.A[i2]][5];
        }
        this.h();
        this.c();
    }

    /*
     * Unable to fully structure code
     */
    public final void K() {
        if (game.l.R == null) {
            game.l.R = new Vector<E>();
        }
        var5_1 = this;
        var1_2 = a.b.c.a((byte)0, (short)var5_1.W, (byte)19);
        if (var1_2 == -1) {
            return;
        }
        var5_1 = this;
        var2_4 = a.b.c.a((byte)0, (short)var5_1.W, (byte)21);
        var5_1 = this;
        var3_5 = a.b.c.a((byte)0, (short)var5_1.W, (byte)20) + 12;
        var4_6 = false;
        if (game.l.U || this.S() <= 0) ** GOTO lbl-1000
        var5_1 = this;
        if (var5_1.U >= game.i.u[a.b.c.a((byte)0, var1_2, (byte)2) - 1] && game.j.p().a(var3_5, (byte)2) >= var2_4) {
            var4_6 = true;
        } else if (this.S() > 0) {
            var5_1 = this;
            if (var5_1.U >= game.i.u[a.b.c.a((byte)0, var1_2, (byte)2) - 1]) {
                var4_6 = true;
            }
        }
        if (var4_6) {
            v0 = new int[2];
            var5_1 = this;
            v0[0] = var5_1.W;
            var5_1 = this;
            v0[1] = a.b.c.c[0][var5_1.W][0];
            var1_3 = v0;
            game.l.R.addElement(var1_3);
            var5_1 = this;
            game.l.V[0] = (byte)var5_1.U;
            var5_1 = this;
            game.l.V[1] = (byte)var5_1.W;
            game.l.S = 0;
        }
    }

    public final void i(byte by) {
        this.X = by;
        switch (by) {
            case 7: {
                int n2 = 2;
                i i2 = this;
                short s = (short)(i2.d[n2] * 90 / 100);
                n2 = 2;
                i2 = this;
                i2.d[n2] = s;
                n2 = 4;
                i2 = this;
                s = (short)(i2.d[n2] + 7);
                n2 = 4;
                i2 = this;
                i2.d[n2] = s;
                n2 = 1;
                i2 = this;
                s = (short)(i2.d[n2] * 80 / 100);
                n2 = 1;
                i2 = this;
                i2.d[n2] = s;
                return;
            }
            case 8: {
                int n3 = 2;
                i i3 = this;
                short s = (short)(i3.d[n3] * 130 / 100);
                n3 = 2;
                i3 = this;
                i3.d[n3] = s;
                n3 = 4;
                i3 = this;
                s = (short)(i3.d[n3] + -2);
                n3 = 4;
                i3 = this;
                i3.d[n3] = s;
                n3 = 1;
                i3 = this;
                s = (short)(i3.d[n3] * 80 / 100);
                n3 = 1;
                i3 = this;
                i3.d[n3] = s;
                return;
            }
            case 9: {
                int n4 = 2;
                i i4 = this;
                short s = (short)(i4.d[n4] * 90 / 100);
                n4 = 2;
                i4 = this;
                i4.d[n4] = s;
                n4 = 4;
                i4 = this;
                s = (short)(i4.d[n4] + -2);
                n4 = 4;
                i4 = this;
                i4.d[n4] = s;
                n4 = 1;
                i4 = this;
                s = (short)(i4.d[n4] * 130 / 100);
                n4 = 1;
                i4 = this;
                i4.d[n4] = s;
            }
        }
    }

    public final boolean L() {
        return this.Z;
    }

    public final void e(boolean bl) {
        this.Z = bl;
    }

    public final int j(byte by) {
        return a.b.c.c[0][this.W][by];
    }

    public static short a(int n2, int n3, int n4, int n5) {
        switch (n5) {
            case 1: {
                return (short)((a.b.c.c[0][n2][5] + a.b.c.c[0][n2][6] * n3 + a.b.c.c[0][n2][7]) * N[n4 - 1] / 100);
            }
            case 2: {
                return (short)((a.b.c.c[0][n2][8] + a.b.c.c[0][n2][9] * n3 + a.b.c.c[0][n2][10]) * N[n4 - 1] / 100);
            }
            case 3: {
                return (short)((a.b.c.c[0][n2][11] + a.b.c.c[0][n2][12] * n3 / 10 + a.b.c.c[0][n2][13]) * N[n4 - 1] / 100);
            }
            case 4: {
                return (short)((a.b.c.c[0][n2][14] + a.b.c.c[0][n2][15] * n3 / 10 + a.b.c.c[0][n2][16]) * N[n4 - 1] / 100);
            }
        }
        return 0;
    }

    public final int M() {
        int n2 = 1;
        i i2 = this;
        int n3 = i2.e[n2] * 100;
        n2 = 1;
        i2 = this;
        return n3 / i2.d[n2];
    }

    public final int N() {
        i i2 = this;
        i i3 = i2;
        int n2 = 1;
        i3 = this;
        return i2.R * 100 / i3.d[n2];
    }

    public final int O() {
        return this.R;
    }

    public final void u(int n2) {
        int n3 = 1;
        i i2 = this;
        if (n2 >= i2.d[n3]) {
            n3 = 1;
            i2 = this;
            this.R = i2.d[n3];
            return;
        }
        this.R = n2;
    }

    public final int P() {
        return this.T * 100 / this.v();
    }

    public final int v(int n2) {
        return n2 * 100 / this.v();
    }

    public static int a(short s, short s2) {
        s2 = s2 >= 50 ? (short)37300 : (short)(s2 * 15 * s2 - 200);
        return s * 100 / s2;
    }

    public final int[] Q() {
        i i2 = this;
        int[] nArray = new int[9 + (i2.P << 1) + 1];
        int[] nArray2 = nArray;
        nArray[0] = this.W;
        nArray2[1] = this.U;
        int n2 = 5;
        i2 = this;
        nArray2[2] = i2.d[n2];
        n2 = 6;
        i2 = this;
        nArray2[3] = i2.e[n2];
        n2 = 0;
        i2 = this;
        nArray2[4] = i2.d[n2];
        nArray2[5] = this.X;
        n2 = 1;
        i2 = this;
        nArray2[6] = i2.e[n2];
        nArray2[7] = this.T;
        nArray2[8] = this.F;
        i2 = this;
        nArray2[9] = i2.P;
        n2 = 0;
        while (true) {
            i2 = this;
            if (n2 >= i2.P) break;
            nArray2[n2 + 10] = this.A[n2];
            nArray2[10 + nArray2[9] + n2] = this.z[n2];
            ++n2;
        }
        return nArray2;
    }

    public final int[] R() {
        i i2 = this;
        int[] nArray = new int[(i2.P << 1) + 1];
        int[] nArray2 = nArray;
        i2 = this;
        nArray[0] = i2.P;
        int n2 = 0;
        while (true) {
            i2 = this;
            if (n2 >= i2.P) break;
            nArray2[n2 + 1] = this.A[n2];
            nArray2[nArray2[0] + n2 + 1] = this.z[n2];
            ++n2;
        }
        return nArray2;
    }

    public final int S() {
        if (this.j((byte)19) == -1) {
            return 0;
        }
        if (a.b.c.c[0][this.j((byte)19)][2] == 1) {
            return 1;
        }
        if (a.b.c.c[0][this.j((byte)19)][2] == 2) {
            return 1;
        }
        if (a.b.c.c[0][this.j((byte)19)][2] == 3) {
            return 2;
        }
        return 0;
    }

    public final void w(int n2) {
        switch (a.b.c.c[4][n2][5]) {
            case 1: {
                int n3 = 1;
                i i2 = this;
                short s = (short)(i2.d[n3] * a.b.c.c[4][n2][6] / 100 + a.b.c.c[4][n2][7]);
                n3 = 1;
                i2 = this;
                this.u(i2.e[n3] + s);
                this.l(s);
                break;
            }
            case 2: {
                short s = a.b.c.c[4][n2][6];
                this.B(s);
                break;
            }
            case 3: {
                int n4 = 1;
                i i3 = this;
                short s = (short)(i3.d[n4] * a.b.c.c[4][n2][6] / 100 + a.b.c.c[4][n2][7]);
                short s2 = a.b.c.c[4][n2][8];
                n4 = 1;
                i3 = this;
                this.u(i3.e[n4] + s);
                this.l(s);
                this.B(s2);
                break;
            }
            case 4: {
                this.c();
                int n5 = 1;
                i i4 = this;
                short s = (short)(i4.d[n5] * a.b.c.c[4][n2][6] / 100 + a.b.c.c[4][n2][7]);
                short s3 = a.b.c.c[4][n2][8];
                this.u(s);
                this.l(s);
                this.B(s3);
                break;
            }
            case 5: {
                this.D();
                break;
            }
            case 6: {
                int n6 = 2;
                int n7 = 6;
                i i5 = this;
                i5.e[n7] = n6;
            }
        }
        game.j.p().d(n2, 1, (byte)0);
    }

    public final int x(int n2) {
        if (!this.T() && a.b.c.c[4][n2][5] != 4) {
            return 8;
        }
        switch (a.b.c.c[4][n2][5]) {
            case 0: {
                return 6;
            }
            case 1: {
                int n3 = 1;
                i i2 = this;
                short s = i2.d[n3];
                n3 = 1;
                i2 = this;
                if (s != i2.e[n3]) break;
                return 2;
            }
            case 2: {
                i i3 = this;
                n2 = i3.P;
                for (int i4 = 0; i4 < n2; ++i4) {
                    if (this.z[i4] >= game.i.a(this.A[i4], (byte)5)) continue;
                    return -1;
                }
                return 3;
            }
            case 3: {
                n2 = -1;
                int n4 = 1;
                i i5 = this;
                short s = i5.d[n4];
                n4 = 1;
                i5 = this;
                if (s == i5.e[n4] || !this.T()) {
                    n2 = 2;
                }
                i5 = this;
                int n5 = i5.P;
                for (n4 = 0; n4 < n5; ++n4) {
                    if (this.z[n4] >= game.i.a(this.A[n4], (byte)5)) continue;
                    return -1;
                }
                if (n2 != 2) break;
                return 7;
            }
            case 4: {
                if (!this.T()) break;
                return 1;
            }
            case 5: {
                for (n2 = 0; n2 < this.x.length; ++n2) {
                    if (!this.p(n2)) continue;
                    return -1;
                }
                return 4;
            }
            case 6: {
                int n6 = 6;
                i i6 = this;
                if (i6.e[n6] < 2) break;
                return 5;
            }
        }
        return -1;
    }

    public final boolean T() {
        int n2 = 1;
        i i2 = this;
        return i2.e[n2] > 0;
    }

    /*
     * Unable to fully structure code
     */
    public final byte a(i var1_1) {
        var2_3 = a.b.c.c[0][this.W][1];
        var3_4 = a.b.c.c[0][var1_1.W][1];
        var4_5 = a.b.c.c[0][this.W][22];
        var1_2 = a.b.c.c[0][var1_1.W][22];
        var5_6 = false;
        var6_7 = false;
        if (var4_5 == 2 && var1_2 == 2) ** GOTO lbl-1000
        if (var4_5 == 2 && var1_2 != 2) {
            var5_6 = true;
        } else if (var4_5 != 2 && var1_2 == 2) {
            var6_7 = true;
        } else lbl-1000:
        // 2 sources

        {
            var5_6 = true;
            var6_7 = true;
        }
        if (var5_6 && (var2_3 == 0 && var3_4 == 1 || var2_3 == 1 && var3_4 == 2 || var2_3 == 2 && var3_4 == 3 || var2_3 == 3 && var3_4 == 0 || var2_3 == 5 && var3_4 == 6 || var2_3 == 6 && var3_4 == 4 || var2_3 == 4 && var3_4 == 5)) {
            return 0;
        }
        if (var6_7 && (var3_4 == 0 && var2_3 == 1 || var3_4 == 1 && var2_3 == 2 || var3_4 == 2 && var2_3 == 3 || var3_4 == 3 && var2_3 == 0 || var3_4 == 5 && var2_3 == 6 || var3_4 == 6 && var2_3 == 4 || var3_4 == 4 && var2_3 == 5)) {
            return 1;
        }
        return -1;
    }

    /*
     * Unable to fully structure code
     */
    public final int[] b(i var1_1) {
        block48: {
            block49: {
                block47: {
                    var2_2 = 0;
                    var3_3 = 5;
                    var4_6 = this.C();
                    var5_7 = a.b.c.c[0][this.W][1];
                    if (this.D == game.j.p().X[var5_7] + game.j.p().Y[var5_7] - 1) {
                        var3_3 = 30;
                    }
                    var9_8 = 4;
                    var8_9 = this;
                    var3_3 += var8_9.e[var9_8] / 2;
                    if (this.f((byte)4)) {
                        var3_3 += a.b.c.c[3][4][5];
                    }
                    if (a.e.a(100) <= var3_3) {
                        var4_6 = var4_6 * 3 / 2;
                        var2_2 = 1;
                    }
                    var5_7 = (byte)a.b.c.c[1][this.E][7];
                    var3_3 = -1;
                    var6_15 = var4_6;
                    switch (this.E) {
                        case 0: 
                        case 6: 
                        case 10: 
                        case 11: 
                        case 12: 
                        case 13: 
                        case 16: 
                        case 17: 
                        case 18: 
                        case 19: 
                        case 20: 
                        case 26: 
                        case 30: 
                        case 31: 
                        case 32: 
                        case 33: 
                        case 36: 
                        case 37: 
                        case 38: 
                        case 39: 
                        case 40: 
                        case 46: 
                        case 50: 
                        case 51: 
                        case 52: 
                        case 54: 
                        case 55: 
                        case 56: 
                        case 57: 
                        case 58: 
                        case 60: 
                        case 61: 
                        case 63: 
                        case 66: 
                        case 68: 
                        case 69: {
                            var4_6 = var4_6 * a.b.c.c[1][this.E][3] / 100;
                            break;
                        }
                        case 1: 
                        case 7: {
                            var4_6 = var4_6 * a.b.c.c[1][this.E][3] / 100 + var4_6 / a.b.c.c[1][this.E][8];
                            break;
                        }
                        case 2: 
                        case 8: 
                        case 22: 
                        case 28: 
                        case 41: 
                        case 47: {
                            var4_6 = var4_6 * a.b.c.c[1][this.E][3] / 100;
                            var3_3 = a.b.c.c[1][this.E][8];
                            break;
                        }
                        case 3: 
                        case 9: {
                            if (var1_1.p(0)) {
                                var4_6 = var4_6 * a.b.c.c[1][this.E][8] / 100;
                                break;
                            }
                            var4_6 = var4_6 * a.b.c.c[1][this.E][3] / 100;
                            break;
                        }
                        case 23: 
                        case 29: {
                            if (var1_1.p(1)) {
                                var4_6 = var4_6 * a.b.c.c[1][this.E][8] / 100;
                                break;
                            }
                            var4_6 = var4_6 * a.b.c.c[1][this.E][3] / 100;
                            break;
                        }
                        case 43: 
                        case 49: {
                            var4_6 = var4_6 * a.b.c.c[1][this.E][3] / 100;
                            var1_1.E();
                            break;
                        }
                        case 53: 
                        case 59: {
                            var9_8 = 1;
                            var8_9 = this;
                            v0 = var8_9.e[var9_8] * 100;
                            var9_8 = 1;
                            var8_9 = this;
                            var7_16 = v0 / var8_9.d[var9_8];
                            var4_6 = var4_6 * (a.b.c.c[1][this.E][8] - var7_16) / 100;
                            break;
                        }
                        default: {
                            var5_7 = -1;
                        }
                    }
                    if (var6_15 <= 0) {
                        var6_15 = 1;
                    }
                    var8_10 = var3_3;
                    var7_16 = this.E;
                    var6_15 = (short)var6_15;
                    var3_4 = var1_1;
                    if (var5_7 != -1) break block47;
                    v1 = -1;
                    break block48;
                }
                if (!var3_4.f((byte)3)) break block49;
                if (a.e.a(100) <= var8_10 * (100 - a.b.c.c[3][3][5]) / 100) ** GOTO lbl-1000
                v1 = -1;
                break block48;
            }
            if (var3_4.m(14)) {
                v1 = -1;
            } else if (var8_10 != -1 && a.e.a(100) > var8_10) {
                v1 = -1;
            } else lbl-1000:
            // 2 sources

            {
                switch (var5_7) {
                    case 0: {
                        var3_4.x[var5_7][1] = var6_15;
                        break;
                    }
                    case 1: {
                        break;
                    }
                    case 2: {
                        break;
                    }
                    case 3: {
                        var3_4.x[var5_7][1] = var6_15;
                        break;
                    }
                    case 4: {
                        var3_4.x[var5_7][1] = a.b.c.c[1][var7_16][8];
                        break;
                    }
                    case 5: {
                        var9_8 = 4;
                        var8_11 = var3_4;
                        var3_4.x[var5_7][1] = (short)(var8_11.d[var9_8] * a.b.c.c[1][var7_16][8] / 100);
                        var9_8 = 4;
                        var8_11 = var3_4;
                        var6_15 = (short)(var8_11.d[var9_8] - var3_4.x[var5_7][1]);
                        var9_8 = 4;
                        var8_11 = var3_4;
                        var8_11.e[var9_8] = var6_15;
                        break;
                    }
                    case 6: {
                        var3_4.x[var5_7][1] = a.b.c.c[1][var7_16][8];
                        break;
                    }
                    case 7: {
                        var9_8 = 3;
                        var8_12 = var3_4;
                        var3_4.x[var5_7][1] = (short)(var8_12.d[var9_8] * a.b.c.c[1][var7_16][8] / 100);
                        var9_8 = 3;
                        var8_12 = var3_4;
                        var6_15 = (short)(var8_12.d[var9_8] - var3_4.x[var5_7][1]);
                        var9_8 = 3;
                        var8_12 = var3_4;
                        var8_12.e[var9_8] = var6_15;
                    }
                }
                var3_4.a(1, (byte)var5_7);
                var8_13 = var3_4;
                var3_4.x[var5_7][0] = var8_13.Y == 0 && game.j.p().b((byte)6, (byte)0) == 2 && game.j.p().b((byte)6, (byte)1) == 1 ? (short)(a.b.c.c[7][var5_7][2] / 2) : a.b.c.c[7][var5_7][2];
                var3_4.x[var5_7][3] = var7_16;
                var3_4.x[var5_7][4] = 1;
                v1 = var7_16 = var5_7;
            }
        }
        if (this.m(0) && this.w[0][0] == 0) {
            var4_6 += this.w[0][2];
        }
        if (this.m(1)) {
            var4_6 += var4_6 * this.w[1][2] / 100;
        }
        if (this.p(6)) {
            var4_6 -= var4_6 * this.x[6][1] / 100;
        }
        if (var1_1.m(6) && a.e.a(100) <= this.w[6][1]) {
            var4_6 = var4_6 * this.w[6][2] / 100;
        }
        if (this.m(8)) {
            var4_6 += var4_6 * this.w[8][1] / 100;
        }
        var8_14 = this;
        if (var8_14.Y == 0 && game.j.p().b((byte)3, (byte)0) == 2 && game.j.p().b((byte)3, (byte)1) == 1 && game.l.ab == 2) {
            var4_6 += var4_6 * a.b.c.c[2][3][5] / 100;
        }
        var8_14 = this;
        if (var8_14.Y == 0 && game.j.p().b((byte)6, (byte)0) == 2) {
            var4_6 += var4_6 * a.b.c.c[2][6][5] / 100;
        }
        if (this.a(var1_1) == 0) {
            var4_6 *= 3;
        } else if (this.a(var1_1) == 1) {
            var4_6 = var4_6 * 60 / 100;
        }
        if (var4_6 <= 0) {
            var4_6 = 1;
        } else {
            var3_5 = a.e.a(100);
            var5_7 = (var4_6 << 1) / 100;
            if (var3_5 > 50) {
                if (var5_7 <= 0) {
                    ++var4_6;
                }
            } else if (var5_7 <= 0) {
                --var4_6;
            }
            if (var4_6 <= 0) {
                var4_6 = 1;
            }
        }
        if (var1_1.m(5) && a.e.a(100) <= var1_1.w[5][1]) {
            this.L[5] = (short)var4_6;
            return new int[]{var4_6, var2_2, var7_16};
        }
        return new int[]{var4_6, var2_2, var7_16};
    }

    public final String U() {
        String[] stringArray = new String[]{"M\u1ed9c h\u1ec7", "Th\u1ed5 h\u1ec7", "Th\u1ee7y h\u1ec7", "H\u1ecfa h\u1ec7", "Qu\u1ef7 h\u1ec7", "Phong h\u1ec7", "\u0110i\u1ec7n h\u1ec7"};
        short s = a.b.c.c[0][this.W][1];
        return stringArray[s];
    }

    public static String y(int n2) {
        String[] stringArray = new String[]{"M\u1ed9c h\u1ec7", "Th\u1ed5 h\u1ec7", "Th\u1ee7y h\u1ec7", "H\u1ecfa h\u1ec7", "Qu\u1ef7 h\u1ec7", "Phong h\u1ec7", "\u0110i\u1ec7n h\u1ec7"};
        n2 = a.b.c.c[0][n2][1];
        return stringArray[n2];
    }
}

