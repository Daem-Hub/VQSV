/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
package c;

import a.e;
import c.f;
import c.i;
import c.l;
import javax.microedition.lcdui.Graphics;

public final class b
implements f {
    private int c = -1;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private byte[][] i = null;
    private i j = new i();
    private int k = -1;
    private byte l;
    private l m = null;
    private f[] n = new f[60];
    public l a = null;
    public l b = null;
    private boolean o = true;

    public b() {
        this.l = (byte)9;
    }

    public final void a(boolean bl) {
        this.o = bl;
    }

    public final void a(byte[][] byArray) {
        this.i = byArray;
    }

    public final byte[][] a() {
        return this.i;
    }

    public final void a(Graphics graphics, boolean bl, boolean bl2, f f2, int[] nArray) {
        if (!this.o) {
            return;
        }
        for (int i2 = 0; i2 < this.n.length && this.n[i2] != null; ++i2) {
            if (this.n[i2].g() != null) {
                boolean bl3 = false;
                int n2 = a.e.a(nArray);
                if (n2 > 0) {
                    b b2 = this;
                    if (nArray[n2 - 1] == b2.c) {
                        bl3 = true;
                    }
                }
                this.n[i2].g().a(graphics, this.n[i2].b(), bl3, nArray, bl2, f2);
                continue;
            }
            this.n[i2].a(graphics, bl, bl2, f2, nArray);
        }
    }

    public final void a(boolean bl, boolean bl2, f f2, int[] nArray) {
        for (int i2 = 0; i2 < this.n.length && this.n[i2] != null; ++i2) {
            if (this.n[i2].g() != null) {
                if (a.e.a(nArray) > 0) {
                    b b2 = this;
                    int cfr_ignored_0 = b2.c;
                }
                this.n[i2].g().a(this.n[i2].b(), nArray, bl2, f2);
                continue;
            }
            this.n[i2].a(bl, bl2, f2, nArray);
        }
    }

    public final int b() {
        return this.c;
    }

    public final void a(int n2) {
        this.c = n2;
    }

    public final int c() {
        return this.d;
    }

    public final void a(int n2, f f2) {
        this.d = n2;
        this.a(f2);
    }

    public final int d() {
        return this.e;
    }

    public final void b(int n2, f f2) {
        this.e = n2;
        this.a(f2);
    }

    public final int e() {
        return this.f;
    }

    public final void c(int n2, f f2) {
        this.f = n2;
        this.a(f2);
    }

    public final int f() {
        return this.g;
    }

    public final void d(int n2, f f2) {
        this.g = n2;
        this.a(f2);
    }

    public final l g() {
        return this.m;
    }

    public final void a(l l2) {
        this.m = l2;
    }

    public final f[] h() {
        return this.n;
    }

    public final i i() {
        return this.j;
    }

    public final void a(i i2) {
        this.j = i2;
    }

    public final int j() {
        return this.h;
    }

    public final void b(int n2) {
        this.h = n2;
    }

    public final int k() {
        return this.k;
    }

    public final void c(int n2) {
        this.k = n2;
    }

    public final void a(f f2) {
        if (f2 != null) {
            if (this.k > 0 && this.l != 9) {
                f f3 = a.e.a(f2, this.k);
                switch (this.l) {
                    case 4: {
                        this.d = f3.c();
                        this.e = f3.d();
                        this.f = f3.e();
                        this.g = f3.f();
                        break;
                    }
                    case 3: {
                        this.d = f3.c();
                        this.e = f3.d() + (f3.f() - this.g) / 2;
                        this.g = f3.f();
                        break;
                    }
                    case 5: {
                        this.d = f3.c() + (f3.e() - this.f);
                        this.e = f3.d() + (f3.f() - this.g) / 2;
                        this.g = f3.f();
                        break;
                    }
                    case 6: {
                        this.d = f3.c();
                        this.e = f3.d() + (f3.f() - this.g);
                        break;
                    }
                    case 8: {
                        this.d = f3.c() + (f3.e() - this.f);
                        this.e = f3.d() + (f3.f() - this.g);
                        break;
                    }
                    case 7: {
                        this.d = f3.c() + (f3.e() - this.f) / 2;
                        this.e = f3.d() + (f3.f() - this.g);
                        this.f = f3.e();
                        break;
                    }
                    case 0: {
                        this.d = f3.c();
                        this.e = f3.d();
                        break;
                    }
                    case 2: {
                        this.d = f3.c() + (f3.e() - this.f);
                        this.e = f3.d();
                        break;
                    }
                    case 1: {
                        this.d = f3.c() + (f3.e() - this.f) / 2;
                        this.e = f3.d();
                        this.f = f3.e();
                    }
                }
            }
            if (this.n != null) {
                for (int i2 = 0; i2 < this.n.length && this.n[i2] != null; ++i2) {
                    this.n[i2].a(f2);
                }
            }
        }
    }

    public final void l() {
        if (this.b != null) {
            this.b.a();
            this.b = null;
        }
        if (this.a != null) {
            this.a.a();
            this.a = null;
        }
        if (this.n != null) {
            for (int i2 = 0; i2 < this.n.length; ++i2) {
                if (this.n[i2] != null) {
                    this.n[i2].l();
                }
                this.n[i2] = null;
            }
            this.n = null;
        }
        if (this.m != null) {
            this.m = null;
        }
        if (this.i != null) {
            this.i = null;
        }
        if (this.j != null) {
            this.j.c();
            this.j = null;
        }
    }
}

