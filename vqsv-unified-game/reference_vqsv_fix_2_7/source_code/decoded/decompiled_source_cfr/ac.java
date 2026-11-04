/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class ac
implements w {
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
    public m b;
    public m c = null;
    public boolean d = false;
    private boolean y = true;
    private int z = -1;
    private int A = 2;
    private int B = -1;
    a[] e = new a[0];

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

    public final int l() {
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

    public final a[] n() {
        int n2;
        int n3;
        int n4;
        int n5;
        int n6;
        int n7;
        a a2;
        Object object;
        int n8;
        int n9 = this.m;
        int n10 = this.l;
        a[] aArray = new a[n10 * n9];
        for (n8 = 0; n8 != n10 * n9; ++n8) {
            object = new a(n8 - n10, n8 + n10, n8 - 1, n8 + 1);
            aArray[n8] = object;
        }
        a[] aArray2 = aArray;
        int n11 = this.m;
        int n12 = this.l;
        a[] aArray3 = aArray2;
        ac ac2 = this;
        for (n8 = 0; n8 != n11; ++n8) {
            object = new a(0);
            a2 = new a(0);
            int n13 = -1;
            n7 = -1;
            for (n6 = 0; n6 != n12; ++n6) {
                if (((a)object).a() == 0 && aArray3[n8 * n12 + n6].a() == 1) {
                    object = aArray3[n8 * n12 + n6];
                    n13 = n8 * n12 + n6;
                }
                if (a2.a() != 0 || aArray3[(n8 + 1) * n12 - n6 - 1].a() != 1) continue;
                a2 = aArray3[(n8 + 1) * n12 - n6 - 1];
                n7 = (n8 + 1) * n12 - n6 - 1;
            }
            if (((a)object).a() != 1) continue;
            if (ac2.t == 0) {
                ((a)object).c(n13);
                a2.d(n7);
                continue;
            }
            a[] aArray4 = object;
            n5 = n13;
            n6 = n11;
            n13 = n12;
            object = aArray3;
            n4 = -1;
            for (n3 = 0; n3 != ((a[])object).length; ++n3) {
                n2 = n5 - n3 - 1;
                if (n2 < 0) {
                    n2 += n13 * n6;
                }
                if (n4 != -1 || ((a)object[n2]).a() != 1) continue;
                n4 = n2;
            }
            aArray4.c(n4);
            n5 = n7;
            n6 = n11;
            n13 = n12;
            object = aArray3;
            n4 = -1;
            for (n3 = 0; n3 != ((Object)object).length; ++n3) {
                n2 = n5 + n3 + 1;
                if (n2 >= n13 * n6) {
                    n2 -= n13 * n6;
                }
                if (n4 != -1 || ((a)object[n2]).a() != 1) continue;
                n4 = n2;
            }
            a2.d(n4);
        }
        n11 = this.m;
        n12 = this.l;
        aArray3 = aArray2;
        ac2 = this;
        for (n8 = 0; n8 != n12; ++n8) {
            int n14;
            object = new a(0);
            a2 = new a(0);
            int n15 = -1;
            n7 = -1;
            for (n6 = 0; n6 != n11; ++n6) {
                if (((a)object).a() == 0 && aArray3[n8 + n12 * n6].a() == 1) {
                    object = aArray3[n8 + n12 * n6];
                    n15 = n8 + n12 * n6;
                }
                if (a2.a() != 0 || aArray3[n8 + n12 * (n11 - n6 - 1)].a() != 1) continue;
                a2 = aArray3[n8 + n12 * (n11 - n6 - 1)];
                n7 = n8 + n12 * (n11 - n6 - 1);
            }
            if (((a)object).a() != 1) continue;
            if (ac2.v == 0) {
                ((a)object).a(n15);
                a2.b(n7);
                continue;
            }
            Object object2 = object;
            int n16 = n15;
            n5 = n11;
            n6 = n12;
            a[] aArray5 = aArray3;
            object = ac2;
            n4 = -1;
            n3 = n16 % n6;
            for (n2 = 0; n2 != n5; ++n2) {
                n14 = n16 / n6 - 1 - n2;
                if (n14 < 0) {
                    n14 += n5;
                }
                if (n4 != -1 || aArray5[n14 * ((ac)object).l + n3].a() != 1) continue;
                n4 = n14 * ((ac)object).l + n3;
            }
            ((a)object2).a(n4);
            n16 = n7;
            n5 = n11;
            n6 = n12;
            aArray5 = aArray3;
            object = ac2;
            n4 = -1;
            n3 = n16 % n6;
            for (n2 = 0; n2 != n5; ++n2) {
                n14 = n16 / n6 + 1 + n2;
                if (n14 >= n5) {
                    n14 -= n5;
                }
                if (n4 != -1 || aArray5[n14 * ((ac)object).l + n3].a() != 1) continue;
                n4 = n14 * ((ac)object).l + n3;
            }
            a2.b(n4);
        }
        return aArray2;
    }

    public final boolean a(byte by) {
        boolean bl = false;
        int n2 = this.x;
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

    public final void a(Graphics graphics, boolean bl, boolean n2, w w2, int[] nArray) {
        if (!this.y) {
            return;
        }
        if (graphics != null) {
            graphics.setColor(this.a);
            graphics.fillRect(this.f, this.g, this.d(), this.e());
            ak ak2 = new ak(this.f, this.g, this.d(), this.e());
            if (this.b != null && graphics != null) {
                this.b.a(graphics, ak2, 0);
            }
            n2 = this.o();
            int n3 = this.p();
            for (int i2 = this.s; i2 <= n2; ++i2) {
                for (int i3 = this.q; i3 <= n3; ++i3) {
                    ak2 = new ak(this.f + this.j + (i3 - this.q) * (this.j + this.h), this.g + this.k + (i2 - this.s) * (this.k + this.i), this.h, this.i);
                    m m2 = this.e[i2 * this.l + i3].a;
                    if (m2 == null || graphics == null) continue;
                    m2.a(graphics, ak2, 0);
                }
            }
            ak2 = new ak(this.f + this.j + (this.x % this.l - this.q) * (this.j + this.h) + this.n, this.g + this.k + (this.x / this.l - this.s) * (this.k + this.i) + this.o, this.d(), this.e());
            if (this.d && this.c != null && graphics != null) {
                this.c.a(graphics, ak2, 0);
            }
        }
    }

    public final void a(boolean n2, boolean bl, w w2, int[] nArray) {
        if (this.b != null) {
            this.b.c();
        }
        for (n2 = 0; n2 < this.e.length; ++n2) {
            if (this.e[n2].a == null) continue;
            this.e[n2].a.c();
        }
        if (this.d && this.c != null) {
            this.c.c();
        }
    }

    public final int a() {
        return this.z;
    }

    public final void q(int n2) {
        this.z = n2;
    }

    public final int b() {
        return this.f;
    }

    public final void a(int n2, w w2) {
        this.f = n2;
    }

    public final int c() {
        return this.g;
    }

    public final void b(int n2, w w2) {
        this.g = n2;
    }

    public final int d() {
        if (this.p > 0 && this.p <= this.l) {
            return (this.h + this.j) * this.p + this.j;
        }
        return (this.h + this.j) * this.l + this.j;
    }

    public final void c(int n2, w w2) {
    }

    public final int e() {
        if (this.r > 0 && this.r <= this.m) {
            return (this.i + this.k) * this.r + this.k;
        }
        return (this.i + this.k) * this.m + this.k;
    }

    public final void d(int n2, w w2) {
    }

    public final void r(int n2) {
        if (this.e != null && n2 > -2 && n2 < this.e.length) {
            ac ac2;
            this.x = n2;
            ac ac3 = ac2 = this;
            int n3 = ac2.x / ac3.l;
            if (ac3.w == 1) {
                ac3.s = n3 / ac3.r * ac3.r;
            } else if (n3 < ac3.s) {
                ac3.s = n3;
            } else if (n3 > ac3.o()) {
                v0.s = n3 - ac3.r + 1;
            }
            ac3 = ac2;
            n3 = ac3.x % ac3.l;
            if (ac3.u == 1) {
                ac3.q = n3 / ac3.p * ac3.p;
                return;
            }
            if (n3 < ac3.q) {
                ac3.q = n3;
                return;
            }
            if (n3 > ac3.p()) {
                v1.q = n3 - ac3.p + 1;
            }
        }
    }

    public final z f() {
        return null;
    }

    public final w[] g() {
        return null;
    }

    public final k h() {
        return null;
    }

    public final void a(k k2) {
    }

    public final int i() {
        return this.A;
    }

    public final int j() {
        return this.B;
    }

    public final void s(int n2) {
        this.B = n2;
    }

    public final void a(w w2) {
    }

    public final void k() {
        if (this.c != null) {
            this.c.d();
            this.c = null;
        }
        if (this.b != null) {
            this.b.d();
            this.b = null;
        }
        if (this.e != null) {
            for (int i2 = 0; i2 < this.e.length; ++i2) {
                if (this.e[i2] == null) continue;
                this.e[i2].a.d();
                this.e[i2] = null;
            }
            this.e = null;
        }
    }

    public final void a(boolean bl) {
        this.y = bl;
    }
}

