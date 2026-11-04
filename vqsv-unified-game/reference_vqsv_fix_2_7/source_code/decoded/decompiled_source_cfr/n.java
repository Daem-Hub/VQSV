/*
 * Decompiled with CFR 0.152.
 */
public class n {
    public short[] c;
    public short[] d;
    protected boolean e;
    protected boolean f;
    protected boolean g;
    protected byte h;
    public int i;
    public int j;
    public int k;
    public int l;
    public byte m;
    public byte n;
    public byte o;
    public n p;
    public int q;
    public int r;
    public int s;
    private short a = (short)10;
    private int[][] b;
    private boolean t = false;

    public final void a(byte by, short s2) {
        this.d[by] = s2;
    }

    public final short a(byte by) {
        return this.d[1];
    }

    public void g() {
        for (int n2 = 0; n2 < this.c.length; n2 = (int)((byte)(n2 + 1))) {
            int n3 = n2;
            short s2 = this.c[n3];
            n3 = n2;
            this.d[n3] = s2;
        }
    }

    public final byte h() {
        return this.h;
    }

    public final void a(boolean bl) {
        this.e = bl;
    }

    public final boolean i() {
        return this.e;
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

    public void b(int n2, int n3) {
        this.i = n2;
        this.j = n3;
    }

    public final void b(byte by) {
        this.n = by;
    }

    public final int l() {
        return this.i;
    }

    public final int m() {
        return this.j;
    }

    public final void d(int n2) {
        this.i += n2;
    }

    public final void e(int n2) {
        this.j += n2;
    }

    public void b(int n2) {
        this.i += n2;
        this.j += 4;
    }

    public final boolean a(int n2, int n3, int n4) {
        if (this.i == n3 && this.j == n4) {
            return true;
        }
        int n5 = ae.a(this.i, this.j, n3, n4);
        if (n5 < n2) {
            this.i = n3;
            this.j = n4;
        } else {
            this.d((n3 - this.i) * n2 / n5);
            this.e((n4 - this.j) * n2 / n5);
        }
        return false;
    }

    public final void a(d d2, d d3) {
        if (!this.t) {
            return;
        }
        if (this.p.h == 0) {
            return;
        }
        this.b[0][0] = this.p.i;
        this.b[0][1] = this.p.j;
        this.b[0][2] = d2.b;
        this.b[0][3] = this.p.n;
        for (int i2 = this.a; i2 > 0; --i2) {
            byte by;
            this.b[i2][0] = this.b[i2 - 1][0];
            this.b[i2][1] = this.b[i2 - 1][1];
            this.b[i2][2] = this.b[i2 - 1][2];
            this.b[i2][3] = this.b[i2 - 1][3];
            if (i2 % this.a != 0) continue;
            this.b(this.b[i2][0], this.b[i2][1]);
            if (this.b[i2][3] == 3) {
                d3.a((byte)this.b[i2][2], (byte)1, false);
            } else {
                d3.a((byte)this.b[i2][2], (byte)this.b[i2][3], false);
            }
            this.n = by = (byte)this.b[i2][3];
        }
    }

    public final void c(byte by) {
        int n2;
        this.t = true;
        this.b = new int[this.a + 1][4];
        for (n2 = 0; n2 < this.a + 1; ++n2) {
            this.b[n2][0] = this.p.i;
            this.b[n2][1] = this.p.j;
            this.b[n2][3] = this.p.n;
        }
        if (by >= 0) {
            for (n2 = 0; n2 < this.a + 1; ++n2) {
                this.b[n2][2] = by;
            }
        }
        switch (this.p.n) {
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

    public final boolean n() {
        return this.t;
    }

    public final void a(n n2) {
        this.p = n2;
    }
}

