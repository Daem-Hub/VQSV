/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
package a.a;

import a.a;
import a.e;
import game.l;
import javax.microedition.lcdui.Graphics;

public class g
extends a.b.a {
    public a.b.g a = new a.b.g();
    public g b = null;
    protected boolean c;

    public boolean a(int n2, boolean bl) {
        return this.a.a(n2, bl);
    }

    public final void a(int n2, int n3, boolean bl) {
        this.a.a(n2, n3, true);
    }

    public final boolean a(byte by, byte by2, boolean bl) {
        return this.a.a(by, by2, bl);
    }

    public final void a(byte by, byte by2, byte by3) {
        this.a.a(by, (byte)-1);
        by2 = by3;
        g g2 = this;
        this.o = by2;
    }

    public final boolean a() {
        if (!this.g) {
            return false;
        }
        return this.a.e();
    }

    public final boolean b() {
        return this.a.f();
    }

    public final void a(Graphics graphics, int n2, int n3) {
        if (!this.g) {
            return;
        }
        if (this.o == 3) {
            this.a.a(graphics, this.j - n2, this.k - n3, (byte)1);
            return;
        }
        this.a.a(graphics, this.j - n2, this.k - n3, (byte)0);
    }

    public final void b(Graphics graphics, int n2, int n3) {
        if (!this.c) {
            return;
        }
        if (this.o == 3) {
            this.a.a(graphics, this.j - n2, this.k - n3, (byte)3);
            return;
        }
        this.a.a(graphics, this.j - n2, this.k - n3, (byte)4);
    }

    public void c() {
        this.b(true);
        this.c(true);
        this.d(true);
    }

    public void d() {
        this.b(false);
        this.c(false);
        this.d(false);
    }

    public final void e() {
        if (this.b != null) {
            this.b.d();
        }
    }

    public final void a(boolean bl) {
        this.c = bl;
    }

    public void a(int n2) {
        switch (this.o) {
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
            this.b.b(this.j, this.k);
        }
    }

    public final void a(int n2, int n3) {
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
            this.b.b(this.j, this.k);
        }
    }

    public final void f() {
        if (this.a.i()) {
            this.d(true);
            return;
        }
        if (a.e.a(game.l.B().k.a, game.l.B().k.b, (int)a.a.g(), (int)a.a.h(), this.j, this.k, this.a.j())) {
            this.d(true);
            return;
        }
        this.d(false);
    }

    public final void g() {
        this.a.c();
    }

    public final void b(int n2) {
        this.a.a(n2);
    }
}

