/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class al
implements w {
    private int c = -1;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private byte[][] i = null;
    private k j = new k();
    private int k = -1;
    private byte l;
    private z m = null;
    private w[] n = new w[60];
    public z a = null;
    public z b = null;
    private boolean o = true;

    public al() {
        this.l = (byte)9;
    }

    public final void a(boolean bl) {
        this.o = bl;
    }

    public final void a(byte[][] byArray) {
        this.i = byArray;
    }

    public final byte[][] l() {
        return this.i;
    }

    public final void a(Graphics graphics, boolean bl, boolean bl2, w w2, int[] nArray) {
        if (!this.o) {
            return;
        }
        for (int i2 = 0; i2 < this.n.length && this.n[i2] != null; ++i2) {
            if (this.n[i2].f() != null) {
                boolean bl3 = false;
                int n2 = ae.a(nArray);
                if (n2 > 0 && nArray[n2 - 1] == this.c) {
                    bl3 = true;
                }
                this.n[i2].f().a(graphics, this.n[i2].a(), bl3, nArray, bl2, w2);
                continue;
            }
            this.n[i2].a(graphics, bl, bl2, w2, nArray);
        }
    }

    public final void a(boolean bl, boolean bl2, w w2, int[] nArray) {
        for (int i2 = 0; i2 < this.n.length && this.n[i2] != null; ++i2) {
            if (this.n[i2].f() != null) {
                this.n[i2].f().a(this.n[i2].a(), nArray, bl2, w2);
                continue;
            }
            this.n[i2].a(bl, bl2, w2, nArray);
        }
    }

    public final int a() {
        return this.c;
    }

    public final void a(int n2) {
        this.c = n2;
    }

    public final int b() {
        return this.d;
    }

    public final void a(int n2, w w2) {
        this.d = n2;
        this.a(w2);
    }

    public final int c() {
        return this.e;
    }

    public final void b(int n2, w w2) {
        this.e = n2;
        this.a(w2);
    }

    public final int d() {
        return this.f;
    }

    public final void c(int n2, w w2) {
        this.f = n2;
        this.a(w2);
    }

    public final int e() {
        return this.g;
    }

    public final void d(int n2, w w2) {
        this.g = n2;
        this.a(w2);
    }

    public final z f() {
        return this.m;
    }

    public final void a(z z2) {
        this.m = z2;
    }

    public final w[] g() {
        return this.n;
    }

    public final k h() {
        return this.j;
    }

    public final void a(k k2) {
        this.j = k2;
    }

    public final int i() {
        return this.h;
    }

    public final void b(int n2) {
        this.h = n2;
    }

    public final int j() {
        return this.k;
    }

    public final void c(int n2) {
        this.k = n2;
    }

    public final void a(w w2) {
        if (w2 != null) {
            if (this.k > 0 && this.l != 9) {
                w w3 = ae.a(w2, this.k);
                switch (this.l) {
                    case 4: {
                        this.d = w3.b();
                        this.e = w3.c();
                        this.f = w3.d();
                        this.g = w3.e();
                        break;
                    }
                    case 3: {
                        this.d = w3.b();
                        this.e = w3.c() + (w3.e() - this.g) / 2;
                        this.g = w3.e();
                        break;
                    }
                    case 5: {
                        this.d = w3.b() + (w3.d() - this.f);
                        this.e = w3.c() + (w3.e() - this.g) / 2;
                        this.g = w3.e();
                        break;
                    }
                    case 6: {
                        this.d = w3.b();
                        this.e = w3.c() + (w3.e() - this.g);
                        break;
                    }
                    case 8: {
                        this.d = w3.b() + (w3.d() - this.f);
                        this.e = w3.c() + (w3.e() - this.g);
                        break;
                    }
                    case 7: {
                        this.d = w3.b() + (w3.d() - this.f) / 2;
                        this.e = w3.c() + (w3.e() - this.g);
                        this.f = w3.d();
                        break;
                    }
                    case 0: {
                        this.d = w3.b();
                        this.e = w3.c();
                        break;
                    }
                    case 2: {
                        this.d = w3.b() + (w3.d() - this.f);
                        this.e = w3.c();
                        break;
                    }
                    case 1: {
                        this.d = w3.b() + (w3.d() - this.f) / 2;
                        this.e = w3.c();
                        this.f = w3.d();
                    }
                }
            }
            if (this.n != null) {
                for (int i2 = 0; i2 < this.n.length && this.n[i2] != null; ++i2) {
                    this.n[i2].a(w2);
                }
            }
        }
    }

    public final void k() {
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
                    this.n[i2].k();
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

