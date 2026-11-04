/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import game.k;
import javax.microedition.lcdui.Graphics;

public class f
extends n {
    public d a = new d();
    public f b = null;

    public boolean a(int n2, boolean bl) {
        return this.a.a(n2, bl);
    }

    public final void a(int n2, int n3) {
        this.a.a(n2, n3, true);
    }

    public final boolean a(byte by, byte by2, boolean bl) {
        return this.a.a(by, by2, bl);
    }

    public final void a(byte by, byte by2) {
        this.a.a(by, (byte)-1);
        this.n = by = by2;
    }

    public final boolean a() {
        if (!this.f) {
            return false;
        }
        return this.a.d();
    }

    public final boolean b() {
        return this.a.e();
    }

    public final void a(Graphics graphics, int n2, int n3) {
        if (!this.f) {
            return;
        }
        if (this.n == 3) {
            this.a.a(graphics, this.i - n2, this.j - n3, (byte)1);
            return;
        }
        this.a.a(graphics, this.i - n2, this.j - n3, (byte)0);
    }

    public void c() {
        this.a(true);
        this.b(true);
        this.c(true);
    }

    public void d() {
        this.a(false);
        this.b(false);
        this.c(false);
    }

    public final void e() {
        if (this.b != null) {
            this.b.d();
        }
    }

    public void a(int n2) {
        switch (this.n) {
            case 3: {
                this.d(-n2);
                break;
            }
            case 1: {
                this.d(n2);
                break;
            }
            case 2: {
                this.e(-n2);
                break;
            }
            case 0: {
                this.e(n2);
            }
        }
        if (this.b != null) {
            this.b.b(this.i, this.j);
        }
    }

    public final void b(int n2) {
        switch (n2) {
            case 3: {
                this.d(-4);
                break;
            }
            case 1: {
                this.d(4);
                break;
            }
            case 2: {
                this.e(-4);
                break;
            }
            case 0: {
                this.e(4);
            }
        }
        if (this.b != null) {
            this.b.b(this.i, this.j);
        }
    }

    public final void f() {
        if (!this.f) {
            return;
        }
        if (this.a.i()) {
            this.c(true);
            return;
        }
        if (ae.a(game.k.a().a.a, game.k.a().a.b, (int)an.w(), (int)an.x(), this.i, this.j, this.a.j())) {
            this.c(true);
            return;
        }
        this.c(false);
    }

    public final void c(int n2) {
        this.a.a(n2);
    }
}

