/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class af
implements w {
    private int d = -1;
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 1;
    private k j;
    private int k = -1;
    private byte l;
    private z m = null;
    public byte a = (byte)-1;
    public byte b = (byte)-1;
    y c;
    private boolean n = true;

    public af() {
        this.j = new k();
        this.l = (byte)9;
    }

    public final void l() {
        ak ak2 = new ak(this.e, this.f, this.g, this.h);
        this.j.a(ak2);
        this.j.a();
    }

    public final void a(Graphics graphics, boolean bl, boolean bl2, w w2, int[] nArray) {
        if (!this.n) {
            return;
        }
        if (this.j != null) {
            this.j.a(graphics, this.e, this.f, this.g, this.h, bl, this.a, this.b, this.c);
        }
    }

    public final void a(boolean bl, boolean bl2, w w2, int[] nArray) {
        if (this.j != null) {
            bl2 = bl;
            k k2 = this.j;
            if (bl2) {
                if (k2.i != null) {
                    k2.i.c();
                    return;
                }
            } else if (k2.m != null) {
                k2.m.c();
            }
        }
    }

    public final void a(boolean bl) {
        this.n = bl;
    }

    public final int a() {
        return this.d;
    }

    public final void a(int n2) {
        this.d = n2;
    }

    public final int b() {
        return this.e;
    }

    public final void a(int n2, w w2) {
        this.e = n2;
        this.a(w2);
    }

    public final int c() {
        return this.f;
    }

    public final void b(int n2, w w2) {
        this.f = n2;
        this.a(w2);
    }

    public final int d() {
        return this.g;
    }

    public final void c(int n2, w w2) {
        this.g = n2;
        this.a(w2);
    }

    public final int e() {
        return this.h;
    }

    public final void d(int n2, w w2) {
        this.h = n2;
        this.a(w2);
    }

    public final z f() {
        return this.m;
    }

    public final void a(z z2) {
        this.m = z2;
    }

    public final w[] g() {
        return null;
    }

    public final k h() {
        return this.j;
    }

    public final void a(k k2) {
        this.j = k2;
    }

    public final int i() {
        return this.i;
    }

    public final void b(int n2) {
        this.i = n2;
    }

    public final int j() {
        return this.k;
    }

    public final void c(int n2) {
        this.k = n2;
    }

    public final void a(w w2) {
        if (w2 != null && this.k > 0 && this.l != 9) {
            w2 = ae.a(w2, this.k);
            switch (this.l) {
                case 4: {
                    this.e = w2.b();
                    this.f = w2.c();
                    this.g = w2.d();
                    this.h = w2.e();
                    return;
                }
                case 3: {
                    this.e = w2.b();
                    this.f = w2.c() + (w2.e() - this.h) / 2;
                    this.h = w2.e();
                    return;
                }
                case 5: {
                    this.e = w2.b() + (w2.d() - this.g);
                    this.f = w2.c() + (w2.e() - this.h) / 2;
                    this.h = w2.e();
                    return;
                }
                case 6: {
                    this.e = w2.b();
                    this.f = w2.c() + (w2.e() - this.h);
                    return;
                }
                case 8: {
                    this.e = w2.b() + (w2.d() - this.g);
                    this.f = w2.c() + (w2.e() - this.h);
                    return;
                }
                case 7: {
                    this.e = w2.b() + (w2.d() - this.g) / 2;
                    this.f = w2.c() + (w2.e() - this.h);
                    this.g = w2.d();
                    return;
                }
                case 0: {
                    this.e = w2.b();
                    this.f = w2.c();
                    return;
                }
                case 2: {
                    this.e = w2.b() + (w2.d() - this.g);
                    this.f = w2.c();
                    return;
                }
                case 1: {
                    this.e = w2.b() + (w2.d() - this.g) / 2;
                    this.f = w2.c();
                    this.g = w2.d();
                }
            }
        }
    }

    public final void k() {
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

