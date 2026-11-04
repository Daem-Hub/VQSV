/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
package c;

import c.e;
import c.f;
import c.g;
import c.i;
import c.k;
import c.l;
import javax.microedition.lcdui.Graphics;

public final class m
implements f {
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private int m = 0;
    private int n = 0;
    private int o = 0;
    private int p = 0;
    private int q = 0;
    private int r = 0;
    private int s = 0;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x = 0;
    public int a;
    public g b;
    public g c = null;
    public boolean d = false;
    private boolean y = true;
    private int z = -1;
    private int A = 2;
    private int B = -1;
    e[] e = new e[0];

    public final void a(int n2) {
        this.h = n2;
    }

    public final void b(int n2) {
        this.i = n2;
    }

    public final void c(int n2) {
        this.j = n2;
    }

    public final void d(int n2) {
        this.k = n2;
    }

    public final int a() {
        return this.l;
    }

    public final void e(int n2) {
        this.l = n2;
    }

    public final int m() {
        return this.m;
    }

    public final void f(int n2) {
        this.m = n2;
    }

    public final void g(int n2) {
        if (n2 <= 0 || n2 > this.l) {
            this.p = this.l;
            return;
        }
        this.p = n2;
    }

    public final void h(int n2) {
        if (n2 <= 0 || n2 > this.m) {
            this.r = this.m;
            return;
        }
        this.r = n2;
    }

    public final void i(int n2) {
        this.q = n2;
    }

    public final void j(int n2) {
        this.s = n2;
    }

    private int o() {
        return this.s + this.r - 1;
    }

    private int p() {
        return this.q + this.p - 1;
    }

    public final void k(int n2) {
        this.n = n2;
    }

    public final void l(int n2) {
        this.o = n2;
    }

    public final void m(int n2) {
        this.t = n2;
    }

    public final void n(int n2) {
        this.v = n2;
    }

    public final void o(int n2) {
        this.u = n2;
    }

    public final void p(int n2) {
        this.w = n2;
    }

    public final e[] n() {
        int n2;
        int n3;
        int n4;
        int n5;
        e e2;
        Object object;
        int n6;
        m m2 = this;
        m2 = this;
        int n7 = m2.m;
        int n8 = m2.l;
        e[] eArray = new e[n8 * n7];
        for (n6 = 0; n6 != n8 * n7; ++n6) {
            object = new e(n6 - n8, n6 + n8, n6 - 1, n6 + 1);
            eArray[n6] = object;
        }
        e[] eArray2 = eArray;
        m m3 = this;
        m m4 = m3;
        m4 = this;
        int n9 = m4.m;
        int n10 = m3.l;
        e[] eArray3 = eArray2;
        m4 = this;
        for (n6 = 0; n6 != n9; ++n6) {
            object = new e(-1, 0, 0, 0, 0, 0, 0);
            e2 = new e(-1, 0, 0, 0, 0, 0, 0);
            int n11 = -1;
            n5 = -1;
            for (n4 = 0; n4 != n10; ++n4) {
                if (((e)object).a() == 0 && eArray3[n6 * n10 + n4].a() == 1) {
                    object = eArray3[n6 * n10 + n4];
                    n11 = n6 * n10 + n4;
                }
                if (e2.a() != 0 || eArray3[(n6 + 1) * n10 - n4 - 1].a() != 1) continue;
                e2 = eArray3[(n6 + 1) * n10 - n4 - 1];
                n5 = (n6 + 1) * n10 - n4 - 1;
            }
            if (((e)object).a() != 1) continue;
            if (m4.t == 0) {
                ((e)object).c(n11);
                e2.d(n5);
                continue;
            }
            e[] eArray4 = object;
            object = eArray3;
            n4 = -1;
            for (n3 = 0; n3 != ((e[])object).length; ++n3) {
                n2 = n11 - n3 - 1;
                if (n2 < 0) {
                    n2 += n10 * n9;
                }
                if (n4 != -1 || ((e)object[n2]).a() != 1) continue;
                n4 = n2;
            }
            eArray4.c(n4);
            n11 = n5;
            object = eArray3;
            n4 = -1;
            for (n3 = 0; n3 != ((Object)object).length; ++n3) {
                n2 = n11 + n3 + 1;
                if (n2 >= n10 * n9) {
                    n2 -= n10 * n9;
                }
                if (n4 != -1 || ((e)object[n2]).a() != 1) continue;
                n4 = n2;
            }
            e2.d(n4);
        }
        m m5 = this;
        m4 = m5;
        m4 = this;
        n9 = m4.m;
        n10 = m5.l;
        eArray3 = eArray2;
        m4 = this;
        for (n6 = 0; n6 != n10; ++n6) {
            int n12;
            int n13;
            object = new e(-1, 0, 0, 0, 0, 0, 0);
            e2 = new e(-1, 0, 0, 0, 0, 0, 0);
            int n14 = -1;
            n5 = -1;
            for (n4 = 0; n4 != n9; ++n4) {
                if (((e)object).a() == 0 && eArray3[n6 + n10 * n4].a() == 1) {
                    object = eArray3[n6 + n10 * n4];
                    n14 = n6 + n10 * n4;
                }
                if (e2.a() != 0 || eArray3[n6 + n10 * (n9 - n4 - 1)].a() != 1) continue;
                e2 = eArray3[n6 + n10 * (n9 - n4 - 1)];
                n5 = n6 + n10 * (n9 - n4 - 1);
            }
            if (((e)object).a() != 1) continue;
            if (m4.v == 0) {
                ((e)object).a(n14);
                e2.b(n5);
                continue;
            }
            Object object2 = object;
            n4 = n14;
            e[] eArray5 = eArray3;
            object = m4;
            n3 = -1;
            n2 = n4 % n10;
            for (n13 = 0; n13 != n9; ++n13) {
                n12 = n4 / n10 - 1 - n13;
                if (n12 < 0) {
                    n12 += n9;
                }
                if (n3 != -1 || eArray5[n12 * ((m)object).l + n2].a() != 1) continue;
                n3 = n12 * ((m)object).l + n2;
            }
            ((e)object2).a(n3);
            n4 = n5;
            eArray5 = eArray3;
            object = m4;
            n3 = -1;
            n2 = n4 % n10;
            for (n13 = 0; n13 != n9; ++n13) {
                n12 = n4 / n10 + 1 + n13;
                if (n12 >= n9) {
                    n12 -= n9;
                }
                if (n3 != -1 || eArray5[n12 * ((m)object).l + n2].a() != 1) continue;
                n3 = n12 * ((m)object).l + n2;
            }
            e2.b(n3);
        }
        return eArray2;
    }

    public static e[] a(int n2, int n3) {
        e[] eArray = new e[n2 * n3];
        for (int i2 = 0; i2 != n2 * n3; ++i2) {
            eArray[i2] = new e();
        }
        return eArray;
    }

    public final boolean a(byte by) {
        boolean bl = false;
        m m2 = this;
        int n2 = m2.x;
        if (by == 0) {
            if (this.e[this.x].a() == 1 && n2 != this.e[this.x].b()) {
                n2 = this.e[this.x].b();
                bl = true;
            }
        } else if (by == 1) {
            if (this.e[this.x].a() == 1 && n2 != this.e[this.x].c()) {
                n2 = this.e[this.x].c();
                bl = true;
            }
        } else if (by == 2) {
            if (this.e[this.x].a() == 1 && n2 != this.e[this.x].d()) {
                n2 = this.e[this.x].d();
                bl = true;
            }
        } else if (by == 3 && this.e[this.x].a() == 1 && n2 != this.e[this.x].e()) {
            n2 = this.e[this.x].e();
            bl = true;
        }
        this.r(n2);
        return bl;
    }

    public final void a(Graphics graphics, boolean bl, boolean n2, f f2, int[] nArray) {
        if (!this.y) {
            return;
        }
        if (graphics != null) {
            graphics.setColor(this.a);
            graphics.fillRect(this.f, this.g, this.e(), this.f());
            k k2 = new k(this.f, this.g, this.e(), this.f());
            if (this.b != null && graphics != null) {
                this.b.a(graphics, k2, 0);
            }
            n2 = this.o();
            int n3 = this.p();
            for (int i2 = this.s; i2 <= n2; ++i2) {
                for (int i3 = this.q; i3 <= n3; ++i3) {
                    k2 = new k(this.f + this.j + (i3 - this.q) * (this.j + this.h), this.g + this.k + (i2 - this.s) * (this.k + this.i), this.h, this.i);
                    g g2 = this.e[i2 * this.l + i3].a;
                    if (g2 == null || graphics == null) continue;
                    g2.a(graphics, k2, 0);
                }
            }
            k2 = new k(this.f + this.j + (this.x % this.l - this.q) * (this.j + this.h) + this.n, this.g + this.k + (this.x / this.l - this.s) * (this.k + this.i) + this.o, this.e(), this.f());
            if (this.d && this.c != null && graphics != null) {
                this.c.a(graphics, k2, 0);
            }
        }
    }

    public final void a(boolean n2, boolean bl, f f2, int[] nArray) {
        if (this.b != null) {
            this.b.b();
        }
        for (n2 = 0; n2 < this.e.length; ++n2) {
            if (this.e[n2].a == null) continue;
            this.e[n2].a.b();
        }
        if (this.d && this.c != null) {
            this.c.b();
        }
    }

    public final int b() {
        return this.z;
    }

    public final void q(int n2) {
        this.z = n2;
    }

    public final int c() {
        return this.f;
    }

    public final void a(int n2, f f2) {
        this.f = n2;
    }

    public final int d() {
        return this.g;
    }

    public final void b(int n2, f f2) {
        this.g = n2;
    }

    public final int e() {
        if (this.p > 0 && this.p <= this.l) {
            return (this.h + this.j) * this.p + this.j;
        }
        return (this.h + this.j) * this.l + this.j;
    }

    public final void c(int n2, f f2) {
    }

    public final int f() {
        if (this.r > 0 && this.r <= this.m) {
            return (this.i + this.k) * this.r + this.k;
        }
        return (this.i + this.k) * this.m + this.k;
    }

    public final void d(int n2, f f2) {
    }

    public final void r(int n2) {
        if (this.e != null && n2 > -2 && n2 < this.e.length) {
            m m2;
            this.x = n2;
            m m3 = m2 = this;
            int n3 = m2.x / m3.l;
            if (m3.w == 1) {
                m3.s = n3 / m3.r * m3.r;
            } else if (n3 < m3.s) {
                m3.s = n3;
            } else if (n3 > m3.o()) {
                v0.s = n3 - m3.r + 1;
            }
            m3 = m2;
            n3 = m3.x % m3.l;
            if (m3.u == 1) {
                m3.q = n3 / m3.p * m3.p;
                return;
            }
            if (n3 < m3.q) {
                m3.q = n3;
                return;
            }
            if (n3 > m3.p()) {
                v1.q = n3 - m3.p + 1;
            }
        }
    }

    public final l g() {
        return null;
    }

    public final f[] h() {
        return null;
    }

    public final i i() {
        return null;
    }

    public final void a(i i2) {
    }

    public final int j() {
        return this.A;
    }

    public final int k() {
        return this.B;
    }

    public final void s(int n2) {
        this.B = n2;
    }

    public final void a(f f2) {
    }

    public final void l() {
        if (this.c != null) {
            this.c.c();
            this.c = null;
        }
        if (this.b != null) {
            this.b.c();
            this.b = null;
        }
        if (this.e != null) {
            for (int i2 = 0; i2 < this.e.length; ++i2) {
                if (this.e[i2] == null) continue;
                e e2 = this.e[i2];
                e2.a.c();
                this.e[i2] = null;
            }
            this.e = null;
        }
    }

    public final void a(boolean bl) {
        this.y = bl;
    }
}

