/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
package c;

import a.e;
import c.d;
import c.f;
import c.i;
import c.k;
import c.l;
import javax.microedition.lcdui.Graphics;

public final class h
implements f {
    private int d = -1;
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 1;
    private i j;
    private int k = -1;
    private byte l;
    private l m = null;
    private f[] n = null;
    public byte a = (byte)-1;
    public byte b = (byte)-1;
    d c;
    private boolean o = true;

    public h() {
        this.j = new i();
        this.l = (byte)9;
    }

    public final void a() {
        k k2 = new k(this.e, this.f, this.g, this.h);
        this.j.a(k2);
        this.j.a();
    }

    public final void a(Graphics graphics, boolean bl, boolean bl2, f f2, int[] nArray) {
        if (!this.o) {
            return;
        }
        if (this.j != null) {
            this.j.a(graphics, this.e, this.f, this.g, this.h, bl, this.a, this.b, this.c);
        }
    }

    public final void a(boolean bl, boolean bl2, f f2, int[] nArray) {
        if (this.j != null) {
            bl2 = bl;
            i i2 = this.j;
            if (bl2) {
                if (i2.i != null) {
                    i2.i.b();
                    return;
                }
            } else if (i2.m != null) {
                i2.m.b();
            }
        }
    }

    public final void a(boolean bl) {
        this.o = bl;
    }

    public final int b() {
        return this.d;
    }

    public final void a(int n2) {
        this.d = n2;
    }

    public final int c() {
        return this.e;
    }

    public final void a(int n2, f f2) {
        this.e = n2;
        this.a(f2);
    }

    public final int d() {
        return this.f;
    }

    public final void b(int n2, f f2) {
        this.f = n2;
        this.a(f2);
    }

    public final int e() {
        return this.g;
    }

    public final void c(int n2, f f2) {
        this.g = n2;
        this.a(f2);
    }

    public final int f() {
        return this.h;
    }

    public final void d(int n2, f f2) {
        this.h = n2;
        this.a(f2);
    }

    public final l g() {
        return this.m;
    }

    public final void a(l l2) {
        this.m = l2;
    }

    public final f[] h() {
        return null;
    }

    public final i i() {
        return this.j;
    }

    public final void a(i i2) {
        this.j = i2;
    }

    public final int j() {
        return this.i;
    }

    public final void b(int n2) {
        this.i = n2;
    }

    public final int k() {
        return this.k;
    }

    public final void c(int n2) {
        this.k = n2;
    }

    public final void a(f f2) {
        if (f2 != null && this.k > 0 && this.l != 9) {
            f2 = a.e.a(f2, this.k);
            switch (this.l) {
                case 4: {
                    this.e = f2.c();
                    this.f = f2.d();
                    this.g = f2.e();
                    this.h = f2.f();
                    return;
                }
                case 3: {
                    this.e = f2.c();
                    this.f = f2.d() + (f2.f() - this.h) / 2;
                    this.h = f2.f();
                    return;
                }
                case 5: {
                    this.e = f2.c() + (f2.e() - this.g);
                    this.f = f2.d() + (f2.f() - this.h) / 2;
                    this.h = f2.f();
                    return;
                }
                case 6: {
                    this.e = f2.c();
                    this.f = f2.d() + (f2.f() - this.h);
                    return;
                }
                case 8: {
                    this.e = f2.c() + (f2.e() - this.g);
                    this.f = f2.d() + (f2.f() - this.h);
                    return;
                }
                case 7: {
                    this.e = f2.c() + (f2.e() - this.g) / 2;
                    this.f = f2.d() + (f2.f() - this.h);
                    this.g = f2.e();
                    return;
                }
                case 0: {
                    this.e = f2.c();
                    this.f = f2.d();
                    return;
                }
                case 2: {
                    this.e = f2.c() + (f2.e() - this.g);
                    this.f = f2.d();
                    return;
                }
                case 1: {
                    this.e = f2.c() + (f2.e() - this.g) / 2;
                    this.f = f2.d();
                    this.g = f2.e();
                }
            }
        }
    }

    public final void l() {
        if (this.m != null) {
            this.m = null;
        }
        if (this.j != null) {
            this.j.c();
            this.j = null;
        }
        if (this.c != null) {
            this.c = null;
        }
    }
}

