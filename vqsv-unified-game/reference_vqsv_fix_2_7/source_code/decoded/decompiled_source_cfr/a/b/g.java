/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
package a.b;

import a.a.d;
import a.b.c;
import a.b.f;
import a.b.h;
import game.e;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class g {
    private static int[] c = new int[]{0, 5, 3, 6, 2, 4, 1, 7};
    private static int[] d = new int[]{2, 4, 1, 7, 0, 5, 3, 6};
    private static int[] e = new int[]{3, 6, 0, 5, 1, 7, 2, 4};
    private static int[] f = new int[]{1, 7, 2, 4, 3, 6, 0, 5};
    private Image[] g;
    private h h;
    private int[] i;
    public int a;
    private int j;
    private int k;
    private byte l;
    private int m;
    private int n;
    protected byte b;
    private boolean o = true;

    public final boolean a(int n2, boolean bl) {
        this.i = new int[a.b.c.a[n2].length - 1];
        this.g = new Image[a.b.c.a[n2].length - 1];
        for (int i2 = 0; i2 < this.g.length; ++i2) {
            this.i[i2] = a.b.c.a[n2][i2 + 1];
            this.g[i2] = a.b.c.b(this.i[i2]);
        }
        this.a = n2;
        this.h = a.b.f.b(a.b.c.a[n2][0]);
        this.h.g = bl;
        this.c(0);
        return true;
    }

    public final void a() {
        for (int i2 = 0; i2 < this.i.length; ++i2) {
            this.g[i2] = null;
            a.b.c.c(this.i[i2]);
        }
        this.i = null;
        this.h = null;
        a.b.f.c(this.a);
    }

    public final void b() {
        if (this.i == null) {
            return;
        }
        for (int i2 = 0; i2 < this.i.length; ++i2) {
            this.g[i2] = null;
            a.b.c.d(this.i[i2]);
        }
        this.i = null;
        this.h = null;
        a.b.f.d(this.a);
    }

    public final void a(int n2, int n3, boolean bl) {
        this.g[n2] = null;
        if (bl) {
            a.b.c.d(this.i[n2]);
        } else {
            a.b.c.c(this.i[n2]);
        }
        this.i[n2] = n3;
        this.g[n2] = a.b.c.b(this.i[n2]);
    }

    public final void c() {
        if (game.e.k == 1) {
            for (int i2 = 0; i2 < this.g.length; ++i2) {
                this.g[i2] = a.a.d.a(a.b.c.b(this.i[i2]));
            }
        }
    }

    public final void d() {
        for (int i2 = 0; i2 < this.g.length; ++i2) {
            if (game.e.k == 1) {
                this.g[i2] = a.a.d.a(a.b.c.b(this.i[i2]));
                continue;
            }
            this.b();
        }
        if (game.e.k == 0) {
            this.a(this.a, false);
        }
    }

    public final void a(int n2) {
        for (int i2 = 0; i2 < this.g.length; ++i2) {
            if (n2 == 1) {
                this.g[i2] = a.a.d.a(a.b.c.b(this.i[i2]), 100);
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
        this.l = by2;
        return true;
    }

    public final void a(byte by, byte by2) {
        this.b = by;
        this.l = by2;
    }

    private void c(int n2) {
        this.m = n2;
        if (this.h.f == null) {
            return;
        }
        if (this.h.g) {
            this.j = this.h.f[this.b][this.m << 2];
            this.n = this.h.f[this.b].length / 4;
        } else {
            this.j = this.h.f[this.b][this.m << 1];
            this.n = this.h.f[this.b].length / 2;
        }
        this.k = 0;
        if (this.j > 0) {
            --this.j;
            return;
        }
        if (this.k > 0) {
            --this.k;
        }
    }

    public final boolean e() {
        if (!this.o) {
            return false;
        }
        if (this.j > 0) {
            --this.j;
        } else if (this.k > 0) {
            --this.k;
        } else {
            ++this.m;
            if (this.m >= this.n) {
                if (this.l >= 0) {
                    this.a(this.l, (byte)-1, true);
                } else if (this.l == -2) {
                    --this.m;
                    this.c(this.m);
                } else if (this.l == -1) {
                    this.c(0);
                }
                return true;
            }
            this.c(this.m);
        }
        return false;
    }

    public final boolean f() {
        return this.m >= this.n - 1;
    }

    public final boolean b(int n2) {
        return this.m == n2;
    }

    public final byte g() {
        return this.b;
    }

    public final int h() {
        return this.m;
    }

    public final int[] a(int n2, byte by) {
        if (n2 >= 0 && n2 < this.h.f.length) {
            int[] nArray = this.b(this.h.f[n2][1], by);
            int n3 = nArray[0];
            int n4 = nArray[0] + nArray[2];
            int n5 = nArray[1];
            int n6 = nArray[1] + nArray[3];
            if (this.h.g) {
                for (int i2 = 1; i2 != this.h.f[n2].length / 4; ++i2) {
                    nArray = this.b(this.h.f[n2][(i2 << 2) + 1], by);
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
                for (int i3 = 1; i3 != this.h.f[n2].length / 2; ++i3) {
                    nArray = this.b(this.h.f[n2][(i3 << 1) + 1], by);
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
        if (this.h.e[n2].length <= 0) {
            return null;
        }
        short s = this.h.e[n2][0];
        int n4 = this.h.e[n2][1];
        int n5 = this.h.e[n2][2];
        for (int i2 = 0; i2 < this.h.e[n2].length; i2 += 4) {
            s = this.h.e[n2][i2];
            if (this.h.e[n2][i2 + 1] < n4) {
                n4 = this.h.e[n2][i2 + 1];
            }
            if (this.h.e[n2][i2 + 2] >= n5) continue;
            n5 = this.h.e[n2][i2 + 2];
        }
        int[] nArray = new int[2];
        nArray = this.a(0, (int)s, n2, n4, n5, nArray);
        int n6 = nArray[0];
        int n7 = nArray[1];
        for (n3 = 0; n3 < this.h.e[n2].length; n3 += 4) {
            s = this.h.e[n2][n3];
            if ((nArray = this.a(n3, (int)s, n2, n4, n5, nArray))[0] > n6) {
                n6 = nArray[0];
            }
            if (nArray[1] <= n7) continue;
            n7 = nArray[1];
        }
        switch (by) {
            case 4: {
                s = this.h.e[n2][0];
                n5 = this.h.e[n2][3] % 2 == 1 ? -this.h.e[n2][2] - this.h.b[s * 5 + 3] : -this.h.e[n2][2] - this.h.b[s * 5 + 4];
                for (n3 = 0; n3 < this.h.e[n2].length; n3 += 4) {
                    s = this.h.e[n2][n3];
                    if (this.h.e[n2][n3 + 3] % 2 == 1) {
                        if (-this.h.e[n2][n3 + 2] - this.h.b[s * 5 + 3] >= n5) continue;
                        n5 = -this.h.e[n2][n3 + 2] - this.h.b[s * 5 + 3];
                        continue;
                    }
                    if (-this.h.e[n2][n3 + 2] - this.h.b[s * 5 + 4] >= n5) continue;
                    n5 = -this.h.e[n2][n3 + 2] - this.h.b[s * 5 + 4];
                }
                break;
            }
            case 1: {
                s = this.h.e[n2][0];
                n4 = this.h.e[n2][3] % 2 == 1 ? -this.h.e[n2][1] - this.h.b[s * 5 + 4] : -this.h.e[n2][1] - this.h.b[s * 5 + 3];
                for (n3 = 0; n3 < this.h.e[n2].length; n3 += 4) {
                    s = this.h.e[n2][n3];
                    if (this.h.e[n2][n3 + 3] % 2 == 1) {
                        if (-this.h.e[n2][n3 + 1] - this.h.b[s * 5 + 4] >= n4) continue;
                        n4 = -this.h.e[n2][n3 + 1] - this.h.b[s * 5 + 4];
                        continue;
                    }
                    if (-this.h.e[n2][n3 + 1] - this.h.b[s * 5 + 3] >= n4) continue;
                    n4 = -this.h.e[n2][n3 + 1] - this.h.b[s * 5 + 3];
                }
                break;
            }
            case 3: {
                s = this.h.e[n2][0];
                if (this.h.e[n2][3] % 2 == 1) {
                    n4 = -this.h.e[n2][1] - this.h.b[s * 5 + 4];
                    n5 = -this.h.e[n2][2] - this.h.b[s * 5 + 3];
                } else {
                    n4 = -this.h.e[n2][1] - this.h.b[s * 5 + 3];
                    n5 = -this.h.e[n2][2] - this.h.b[s * 5 + 4];
                }
                for (n3 = 0; n3 < this.h.e[n2].length; n3 += 4) {
                    s = this.h.e[n2][n3];
                    if (this.h.e[n2][n3 + 3] % 2 == 1) {
                        if (-this.h.e[n2][n3 + 1] - this.h.b[s * 5 + 4] < n4) {
                            n4 = -this.h.e[n2][n3 + 1] - this.h.b[s * 5 + 4];
                        }
                        if (-this.h.e[n2][n3 + 2] - this.h.b[s * 5 + 3] >= n5) continue;
                        n5 = -this.h.e[n2][n3 + 2] - this.h.b[s * 5 + 3];
                        continue;
                    }
                    if (-this.h.e[n2][n3 + 1] - this.h.b[s * 5 + 3] < n4) {
                        n4 = -this.h.e[n2][n3 + 1] - this.h.b[s * 5 + 3];
                    }
                    if (-this.h.e[n2][n3 + 2] - this.h.b[s * 5 + 4] >= n5) continue;
                    n5 = -this.h.e[n2][n3 + 2] - this.h.b[s * 5 + 4];
                }
                break;
            }
        }
        return new int[]{n4, n5, n6, n7};
    }

    private int[] a(int n2, int n3, int n4, int n5, int n6, int[] nArray) {
        if (this.h.e[n4][n2 + 3] % 2 == 1) {
            nArray[1] = this.h.e[n4][n2 + 2] - n6 + this.h.b[n3 * 5 + 3];
            nArray[0] = this.h.e[n4][n2 + 1] - n5 + this.h.b[n3 * 5 + 4];
        } else {
            nArray[0] = this.h.e[n4][n2 + 1] - n5 + this.h.b[n3 * 5 + 3];
            nArray[1] = this.h.e[n4][n2 + 2] - n6 + this.h.b[n3 * 5 + 4];
        }
        return nArray;
    }

    public final void a(Graphics graphics, int n2, int n3, byte by) {
        if (this.h.g) {
            this.a(graphics, (int)this.h.f[this.b][(this.m << 2) + 1], n2, n3, by, 20);
            return;
        }
        this.a(graphics, (int)this.h.f[this.b][(this.m << 1) + 1], n2, n3, by, 20);
    }

    public final void a(Graphics graphics, int n2, int n3, int n4, byte by, int n5) {
        if (this.h.e[n2].length <= 0) {
            return;
        }
        switch (by) {
            case 0: {
                for (by = 0; by < this.h.e[n2].length; by = (byte)(by + 4)) {
                    this.a(graphics, (int)this.h.e[n2][by], n3 + this.h.e[n2][by + 1], n4 + this.h.e[n2][by + 2], c[this.h.e[n2][by + 3]], 20);
                }
                return;
            }
            case 1: {
                for (by = 0; by < this.h.e[n2].length; by = (byte)(by + 4)) {
                    if (this.h.e[n2][by + 3] % 2 == 1) {
                        this.a(graphics, (int)this.h.e[n2][by], n3 - this.h.e[n2][by + 1] - this.h.b[this.h.e[n2][by] * 5 + 4], n4 + this.h.e[n2][by + 2], d[this.h.e[n2][by + 3]], 20);
                        continue;
                    }
                    this.a(graphics, (int)this.h.e[n2][by], n3 - this.h.e[n2][by + 1] - this.h.b[this.h.e[n2][by] * 5 + 3], n4 + this.h.e[n2][by + 2], d[this.h.e[n2][by + 3]], 20);
                }
                return;
            }
            case 3: {
                for (by = 0; by < this.h.e[n2].length; by = (byte)(by + 4)) {
                    if (this.h.e[n2][by + 3] % 2 == 1) {
                        this.a(graphics, (int)this.h.e[n2][by], n3 - this.h.e[n2][by + 1] - this.h.b[this.h.e[n2][by] * 5 + 4], n4 - this.h.e[n2][by + 2] - this.h.b[this.h.e[n2][by] * 5 + 3], e[this.h.e[n2][by + 3]], 20);
                        continue;
                    }
                    this.a(graphics, (int)this.h.e[n2][by], n3 - this.h.e[n2][by + 1] - this.h.b[this.h.e[n2][by] * 5 + 3], n4 - this.h.e[n2][by + 2] - this.h.b[this.h.e[n2][by] * 5 + 4], e[this.h.e[n2][by + 3]], 20);
                }
                return;
            }
            case 4: {
                for (by = 0; by < this.h.e[n2].length; by = (byte)(by + 4)) {
                    if (this.h.e[n2][by + 3] % 2 == 1) {
                        this.a(graphics, (int)this.h.e[n2][by], n3 + this.h.e[n2][by + 1], n4 - this.h.e[n2][by + 2] - this.h.b[this.h.e[n2][by] * 5 + 3], f[this.h.e[n2][by + 3]], 20);
                        continue;
                    }
                    this.a(graphics, (int)this.h.e[n2][by], n3 + this.h.e[n2][by + 1], n4 - this.h.e[n2][by + 2] - this.h.b[this.h.e[n2][by] * 5 + 4], f[this.h.e[n2][by + 3]], 20);
                }
                break;
            }
        }
    }

    private void a(Graphics graphics, int n2, int n3, int n4, int n5, int n6) {
        graphics.drawRegion(this.g[this.h.b[n2 * 5]], (int)this.h.b[n2 * 5 + 1], (int)this.h.b[n2 * 5 + 2], (int)this.h.b[n2 * 5 + 3], (int)this.h.b[n2 * 5 + 4], n5, n3, n4, n6);
    }

    public final boolean i() {
        return this.h == null || this.h.c == null;
    }

    public final short[] j() {
        if (this.h.c == null) {
            return null;
        }
        if (this.h.g) {
            return this.h.c[this.h.f[this.b][(this.m << 2) + 1]];
        }
        return this.h.c[this.h.f[this.b][(this.m << 1) + 1]];
    }

    public final short[] k() {
        if (this.h.d == null) {
            return null;
        }
        if (this.h.g) {
            return this.h.d[this.h.f[this.b][(this.m << 2) + 1]];
        }
        return this.h.d[this.h.f[this.b][(this.m << 1) + 1]];
    }
}

