/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class d {
    private static int[] c = new int[]{0, 5, 3, 6, 2, 4, 1, 7};
    private static int[] d = new int[]{2, 4, 1, 7, 0, 5, 3, 6};
    private static int[] e = new int[]{3, 6, 0, 5, 1, 7, 2, 4};
    private static int[] f = new int[]{1, 7, 2, 4, 3, 6, 0, 5};
    private static int[] g = new int[]{0, 270, 180, 90, 8192, 8462, 8372, 8282};
    private static int[] h = new int[]{8192, 8462, 8372, 8282, 0, 270, 180, 90};
    private static int[] i = new int[]{180, 90, 0, 270, 8372, 8282, 8192, 8462};
    private static int[] j = new int[]{8372, 8282, 8192, 8462, 180, 90, 0, 270};
    private Image[] k;
    private o l;
    private int[] m;
    public int a;
    private int n;
    private int o;
    private byte p;
    private int q;
    private int r;
    protected byte b;
    private boolean s = true;

    public final boolean a(int n2, boolean bl) {
        this.m = new int[aq.a[n2].length - 1];
        this.k = new Image[aq.a[n2].length - 1];
        for (int i2 = 0; i2 < this.k.length; ++i2) {
            this.m[i2] = aq.a[n2][i2 + 1];
            this.k[i2] = am.a(this.m[i2]);
        }
        this.a = n2;
        this.l = aa.a(aq.a[n2][0]);
        this.l.g = bl;
        this.c(0);
        return true;
    }

    public final void a() {
        if (this.m != null) {
            for (int i2 = 0; i2 < this.m.length; ++i2) {
                if (this.k != null) {
                    this.k[i2] = null;
                }
                am.b(this.m[i2]);
            }
        }
        this.m = null;
        if (this.a != 257) {
            this.l = null;
            aa.b(this.a);
        }
    }

    public final void b() {
        if (this.m == null) {
            return;
        }
        for (int i2 = 0; i2 < this.m.length; ++i2) {
            if (this.k != null) {
                this.k[i2] = null;
            }
            am.c(this.m[i2]);
        }
        this.m = null;
        this.l = null;
        aa.c(this.a);
    }

    public final void a(int n2, int n3, boolean bl) {
        this.k[n2] = null;
        am.c(this.m[n2]);
        this.m[n2] = n3;
        this.k[n2] = am.a(this.m[n2]);
    }

    public static void c() {
    }

    public final void a(int n2) {
        for (int i2 = 0; i2 < this.k.length; ++i2) {
            if (n2 == 1) {
                this.k[i2] = l.a(am.a(this.m[i2]));
                continue;
            }
            this.b();
        }
        if (n2 == 0) {
            this.a(this.a, false);
        }
    }

    public final boolean a(byte by, byte by2, boolean bl) {
        if (this.b != by || bl) {
            this.b = by;
            this.c(0);
        } else {
            this.b = by;
        }
        this.p = by2;
        return true;
    }

    public final void a(byte by, byte by2) {
        this.b = by;
        this.p = (byte)-1;
    }

    private void c(int n2) {
        this.q = n2;
        if (this.l.f == null) {
            return;
        }
        if (this.l.g) {
            this.n = this.l.f[this.b][this.q << 2];
            this.r = this.l.f[this.b].length / 4;
        } else {
            this.n = this.l.f[this.b][this.q << 1];
            this.r = this.l.f[this.b].length / 2;
        }
        this.o = 0;
        if (this.n > 0) {
            --this.n;
            return;
        }
        if (this.o > 0) {
            --this.o;
        }
    }

    public final boolean d() {
        if (this.n > 0) {
            --this.n;
        } else if (this.o > 0) {
            --this.o;
        } else {
            ++this.q;
            if (this.q >= this.r) {
                if (this.p >= 0) {
                    this.a(this.p, (byte)-1, true);
                } else if (this.p == -2) {
                    --this.q;
                    this.c(this.q);
                } else if (this.p == -1) {
                    this.c(0);
                }
                return true;
            }
            this.c(this.q);
        }
        return false;
    }

    public final boolean e() {
        return this.q >= this.r - 1;
    }

    public final boolean f() {
        return this.n == 0;
    }

    public final boolean b(int n2) {
        return this.q == n2;
    }

    public final byte g() {
        return this.b;
    }

    public final int h() {
        return this.q;
    }

    public final int[] a(int n2, byte by) {
        if (n2 >= 0 && n2 < this.l.f.length) {
            int[] nArray = this.b(this.l.f[n2][1], (byte)0);
            int n3 = nArray[0];
            int n4 = nArray[0] + nArray[2];
            int n5 = nArray[1];
            int n6 = nArray[1] + nArray[3];
            if (this.l.g) {
                for (int i2 = 1; i2 != this.l.f[n2].length / 4; ++i2) {
                    nArray = this.b(this.l.f[n2][(i2 << 2) + 1], (byte)0);
                    if (nArray == null) continue;
                    if (n3 > nArray[0]) {
                        n3 = nArray[0];
                    }
                    if (n4 < nArray[0] + nArray[2]) {
                        n4 = nArray[0] + nArray[2];
                    }
                    if (n5 > nArray[1]) {
                        n5 = nArray[1];
                    }
                    if (n6 >= nArray[1] + nArray[3]) continue;
                    n6 = nArray[1] + nArray[3];
                }
            } else {
                for (int i3 = 1; i3 != this.l.f[n2].length / 2; ++i3) {
                    nArray = this.b(this.l.f[n2][(i3 << 1) + 1], (byte)0);
                    if (nArray == null) continue;
                    if (n3 > nArray[0]) {
                        n3 = nArray[0];
                    }
                    if (n4 < nArray[0] + nArray[2]) {
                        n4 = nArray[0] + nArray[2];
                    }
                    if (n5 > nArray[1]) {
                        n5 = nArray[1];
                    }
                    if (n6 >= nArray[1] + nArray[3]) continue;
                    n6 = nArray[1] + nArray[3];
                }
            }
            return new int[]{n3, n5, n4 - n3, n6 - n5};
        }
        return null;
    }

    public final int[] b(int n2, byte by) {
        int n3;
        if (this.l.e[n2].length <= 0) {
            return null;
        }
        short s2 = this.l.e[n2][0];
        int n4 = this.l.e[n2][1];
        int n5 = this.l.e[n2][2];
        for (int i2 = 0; i2 < this.l.e[n2].length; i2 += 4) {
            s2 = this.l.e[n2][i2];
            if (this.l.e[n2][i2 + 1] < n4) {
                n4 = this.l.e[n2][i2 + 1];
            }
            if (this.l.e[n2][i2 + 2] >= n5) continue;
            n5 = this.l.e[n2][i2 + 2];
        }
        int[] nArray = new int[2];
        nArray = this.a(0, (int)s2, n2, n4, n5, nArray);
        int n6 = nArray[0];
        int n7 = nArray[1];
        for (n3 = 0; n3 < this.l.e[n2].length; n3 += 4) {
            s2 = this.l.e[n2][n3];
            if ((nArray = this.a(n3, (int)s2, n2, n4, n5, nArray))[0] > n6) {
                n6 = nArray[0];
            }
            if (nArray[1] <= n7) continue;
            n7 = nArray[1];
        }
        switch (by) {
            case 4: {
                s2 = this.l.e[n2][0];
                n5 = this.l.e[n2][3] % 2 == 1 ? -this.l.e[n2][2] - this.l.b[s2 * 5 + 3] : -this.l.e[n2][2] - this.l.b[s2 * 5 + 4];
                for (n3 = 0; n3 < this.l.e[n2].length; n3 += 4) {
                    s2 = this.l.e[n2][n3];
                    if (this.l.e[n2][n3 + 3] % 2 == 1) {
                        if (-this.l.e[n2][n3 + 2] - this.l.b[s2 * 5 + 3] >= n5) continue;
                        n5 = -this.l.e[n2][n3 + 2] - this.l.b[s2 * 5 + 3];
                        continue;
                    }
                    if (-this.l.e[n2][n3 + 2] - this.l.b[s2 * 5 + 4] >= n5) continue;
                    n5 = -this.l.e[n2][n3 + 2] - this.l.b[s2 * 5 + 4];
                }
                break;
            }
            case 1: {
                s2 = this.l.e[n2][0];
                n4 = this.l.e[n2][3] % 2 == 1 ? -this.l.e[n2][1] - this.l.b[s2 * 5 + 4] : -this.l.e[n2][1] - this.l.b[s2 * 5 + 3];
                for (n3 = 0; n3 < this.l.e[n2].length; n3 += 4) {
                    s2 = this.l.e[n2][n3];
                    if (this.l.e[n2][n3 + 3] % 2 == 1) {
                        if (-this.l.e[n2][n3 + 1] - this.l.b[s2 * 5 + 4] >= n4) continue;
                        n4 = -this.l.e[n2][n3 + 1] - this.l.b[s2 * 5 + 4];
                        continue;
                    }
                    if (-this.l.e[n2][n3 + 1] - this.l.b[s2 * 5 + 3] >= n4) continue;
                    n4 = -this.l.e[n2][n3 + 1] - this.l.b[s2 * 5 + 3];
                }
                break;
            }
            case 3: {
                s2 = this.l.e[n2][0];
                if (this.l.e[n2][3] % 2 == 1) {
                    n4 = -this.l.e[n2][1] - this.l.b[s2 * 5 + 4];
                    n5 = -this.l.e[n2][2] - this.l.b[s2 * 5 + 3];
                } else {
                    n4 = -this.l.e[n2][1] - this.l.b[s2 * 5 + 3];
                    n5 = -this.l.e[n2][2] - this.l.b[s2 * 5 + 4];
                }
                for (n3 = 0; n3 < this.l.e[n2].length; n3 += 4) {
                    s2 = this.l.e[n2][n3];
                    if (this.l.e[n2][n3 + 3] % 2 == 1) {
                        if (-this.l.e[n2][n3 + 1] - this.l.b[s2 * 5 + 4] < n4) {
                            n4 = -this.l.e[n2][n3 + 1] - this.l.b[s2 * 5 + 4];
                        }
                        if (-this.l.e[n2][n3 + 2] - this.l.b[s2 * 5 + 3] >= n5) continue;
                        n5 = -this.l.e[n2][n3 + 2] - this.l.b[s2 * 5 + 3];
                        continue;
                    }
                    if (-this.l.e[n2][n3 + 1] - this.l.b[s2 * 5 + 3] < n4) {
                        n4 = -this.l.e[n2][n3 + 1] - this.l.b[s2 * 5 + 3];
                    }
                    if (-this.l.e[n2][n3 + 2] - this.l.b[s2 * 5 + 4] >= n5) continue;
                    n5 = -this.l.e[n2][n3 + 2] - this.l.b[s2 * 5 + 4];
                }
                break;
            }
        }
        return new int[]{n4, n5, n6, n7};
    }

    private int[] a(int n2, int n3, int n4, int n5, int n6, int[] nArray) {
        if (this.l.e[n4][n2 + 3] % 2 == 1) {
            nArray[1] = this.l.e[n4][n2 + 2] - n6 + this.l.b[n3 * 5 + 3];
            nArray[0] = this.l.e[n4][n2 + 1] - n5 + this.l.b[n3 * 5 + 4];
        } else {
            nArray[0] = this.l.e[n4][n2 + 1] - n5 + this.l.b[n3 * 5 + 3];
            nArray[1] = this.l.e[n4][n2 + 2] - n6 + this.l.b[n3 * 5 + 4];
        }
        return nArray;
    }

    public final void a(Graphics graphics, int n2, int n3, byte by) {
        if (this.l.g) {
            this.a(graphics, this.l.f[this.b][(this.q << 2) + 1], n2, n3, by);
            return;
        }
        this.a(graphics, this.l.f[this.b][(this.q << 1) + 1], n2, n3, by);
    }

    public final void a(Graphics graphics, int n2, int n3, int n4, byte by) {
        if (this.l.e[n2].length <= 0) {
            return;
        }
        switch (by) {
            case 0: {
                for (by = 0; by < this.l.e[n2].length; by = (byte)(by + 4)) {
                    this.a(graphics, (int)this.l.e[n2][by], n3 + this.l.e[n2][by + 1], n4 + this.l.e[n2][by + 2], this.s ? c[this.l.e[n2][by + 3]] : g[this.l.e[n2][by + 3]], 20);
                }
                return;
            }
            case 1: {
                for (by = 0; by < this.l.e[n2].length; by = (byte)(by + 4)) {
                    if (this.l.e[n2][by + 3] % 2 == 1) {
                        this.a(graphics, (int)this.l.e[n2][by], n3 - this.l.e[n2][by + 1] - this.l.b[this.l.e[n2][by] * 5 + 4], n4 + this.l.e[n2][by + 2], this.s ? d[this.l.e[n2][by + 3]] : h[this.l.e[n2][by + 3]], 20);
                        continue;
                    }
                    this.a(graphics, (int)this.l.e[n2][by], n3 - this.l.e[n2][by + 1] - this.l.b[this.l.e[n2][by] * 5 + 3], n4 + this.l.e[n2][by + 2], this.s ? d[this.l.e[n2][by + 3]] : h[this.l.e[n2][by + 3]], 20);
                }
                return;
            }
            case 3: {
                for (by = 0; by < this.l.e[n2].length; by = (byte)(by + 4)) {
                    if (this.l.e[n2][by + 3] % 2 == 1) {
                        this.a(graphics, (int)this.l.e[n2][by], n3 - this.l.e[n2][by + 1] - this.l.b[this.l.e[n2][by] * 5 + 4], n4 - this.l.e[n2][by + 2] - this.l.b[this.l.e[n2][by] * 5 + 3], this.s ? e[this.l.e[n2][by + 3]] : i[this.l.e[n2][by + 3]], 20);
                        continue;
                    }
                    this.a(graphics, (int)this.l.e[n2][by], n3 - this.l.e[n2][by + 1] - this.l.b[this.l.e[n2][by] * 5 + 3], n4 - this.l.e[n2][by + 2] - this.l.b[this.l.e[n2][by] * 5 + 4], this.s ? e[this.l.e[n2][by + 3]] : i[this.l.e[n2][by + 3]], 20);
                }
                return;
            }
            case 4: {
                for (by = 0; by < this.l.e[n2].length; by = (byte)(by + 4)) {
                    if (this.l.e[n2][by + 3] % 2 == 1) {
                        this.a(graphics, (int)this.l.e[n2][by], n3 + this.l.e[n2][by + 1], n4 - this.l.e[n2][by + 2] - this.l.b[this.l.e[n2][by] * 5 + 3], this.s ? f[this.l.e[n2][by + 3]] : j[this.l.e[n2][by + 3]], 20);
                        continue;
                    }
                    this.a(graphics, (int)this.l.e[n2][by], n3 + this.l.e[n2][by + 1], n4 - this.l.e[n2][by + 2] - this.l.b[this.l.e[n2][by] * 5 + 4], this.s ? f[this.l.e[n2][by + 3]] : j[this.l.e[n2][by + 3]], 20);
                }
                break;
            }
        }
    }

    private void a(Graphics graphics, int n2, int n3, int n4, int n5, int n6) {
        graphics.drawRegion(this.k[this.l.b[n2 * 5]], (int)this.l.b[n2 * 5 + 1], (int)this.l.b[n2 * 5 + 2], (int)this.l.b[n2 * 5 + 3], (int)this.l.b[n2 * 5 + 4], n5, n3, n4, 20);
    }

    public final boolean i() {
        return this.l.c == null;
    }

    public final short[] j() {
        if (this.l.c == null) {
            return null;
        }
        if (this.l.g) {
            return this.l.c[this.l.f[this.b][(this.q << 2) + 1]];
        }
        return this.l.c[this.l.f[this.b][(this.q << 1) + 1]];
    }

    public final short[] k() {
        if (this.l.d == null) {
            return null;
        }
        if (this.l.g) {
            return this.l.d[this.l.f[this.b][(this.q << 2) + 1]];
        }
        return this.l.d[this.l.f[this.b][(this.q << 1) + 1]];
    }
}

