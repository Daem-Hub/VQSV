/*
 * Decompiled with CFR 0.152.
 */
package a.b;

import a.b.g;
import a.e;

public class a {
    public short[] d;
    public short[] e;
    protected boolean f;
    protected boolean g;
    protected boolean h;
    protected byte i;
    public int j;
    public int k;
    public int l;
    public int m;
    public byte n;
    public byte o;
    public byte p;
    public a q;
    public int r;
    public int s;
    public int t;
    private short a = (short)10;
    private int[][] b;
    private boolean c = false;

    public final void a(byte by, short s) {
        this.d[by] = s;
    }

    public final void b(byte by, short s) {
        this.e[by] = s;
    }

    public final short b(byte by) {
        return this.d[by];
    }

    public final short c(byte by) {
        return this.e[by];
    }

    public void h() {
        for (int n2 = 0; n2 < this.d.length; n2 = (int)((byte)(n2 + 1))) {
            int n3 = n2;
            a a2 = this;
            short s = a2.d[n3];
            n3 = n2;
            a2 = this;
            a2.e[n3] = s;
        }
    }

    public final byte i() {
        return this.i;
    }

    public final void b(boolean bl) {
        this.f = bl;
    }

    public final boolean j() {
        return this.f;
    }

    public final void c(boolean bl) {
        this.g = bl;
    }

    public final boolean k() {
        return this.g;
    }

    public final void d(boolean bl) {
        this.h = bl;
    }

    public final boolean l() {
        return this.h;
    }

    public final void c(int n2) {
        this.j = n2;
    }

    public void b(int n2, int n3) {
        this.j = n2;
        this.k = n3;
    }

    public final void d(byte by) {
        this.o = by;
    }

    public final int m() {
        return this.j;
    }

    public final int n() {
        return this.k;
    }

    public final void d(int n2) {
        this.j += n2;
    }

    public final void e(int n2) {
        this.k += n2;
    }

    public void a(int n2, int n3) {
        this.j += n2;
        this.k += 4;
    }

    public final boolean a(int n2, int n3, int n4) {
        if (this.j == n3 && this.k == n4) {
            return true;
        }
        int n5 = a.e.a(this.j, this.k, n3, n4);
        if (n5 < n2) {
            this.j = n3;
            this.k = n4;
        } else {
            this.d((n3 - this.j) * n2 / n5);
            this.e((n4 - this.k) * n2 / n5);
        }
        return false;
    }

    public final void a(g g2, g g3) {
        if (!this.c) {
            return;
        }
        if (this.q.i == 0) {
            return;
        }
        this.b[0][0] = this.q.j;
        this.b[0][1] = this.q.k;
        this.b[0][2] = g2.b;
        this.b[0][3] = this.q.o;
        for (int i2 = this.a; i2 > 0; --i2) {
            this.b[i2][0] = this.b[i2 - 1][0];
            this.b[i2][1] = this.b[i2 - 1][1];
            this.b[i2][2] = this.b[i2 - 1][2];
            this.b[i2][3] = this.b[i2 - 1][3];
            if (i2 % this.a != 0) continue;
            this.b(this.b[i2][0], this.b[i2][1]);
            if (this.b[i2][3] == 3) {
                g3.a((byte)this.b[i2][2], (byte)1, false);
            } else {
                g3.a((byte)this.b[i2][2], (byte)this.b[i2][3], false);
            }
            byte by = (byte)this.b[i2][3];
            a a2 = this;
            this.o = by;
        }
    }

    public final void e(byte by) {
        int n2;
        this.c = true;
        this.b = new int[this.a + 1][4];
        for (n2 = 0; n2 < this.a + 1; ++n2) {
            this.b[n2][0] = this.q.j;
            this.b[n2][1] = this.q.k;
            this.b[n2][3] = this.q.o;
        }
        if (by >= 0) {
            for (n2 = 0; n2 < this.a + 1; ++n2) {
                this.b[n2][2] = by;
            }
        }
        switch (this.q.o) {
            case 1: {
                int[] nArray = this.b[10];
                nArray[0] = nArray[0] - this.a;
                break;
            }
            case 3: {
                int[] nArray = this.b[10];
                nArray[0] = nArray[0] + this.a;
                break;
            }
            case 2: {
                int[] nArray = this.b[10];
                nArray[1] = nArray[1] + this.a;
                break;
            }
            case 0: {
                int[] nArray = this.b[10];
                nArray[1] = nArray[1] - this.a;
            }
        }
        this.b(this.b[10][0], this.b[10][1]);
    }

    public final boolean o() {
        return this.c;
    }

    public final void a(a a2) {
        this.q = a2;
    }
}

