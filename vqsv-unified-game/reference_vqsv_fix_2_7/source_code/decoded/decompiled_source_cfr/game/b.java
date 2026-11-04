/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
package game;

import a.a;
import a.a.f;
import a.a.g;
import a.a.h;
import a.b.d;
import javax.microedition.lcdui.Graphics;

public final class b
extends a {
    private g l;
    private h m = null;
    private static b n = null;
    private d o = null;
    private a.b.b p = new a.b.b();
    private boolean q = false;
    private byte r = 0;
    public boolean k = false;

    public static b B() {
        if (n == null) {
            n = new b();
        }
        return n;
    }

    public b() {
        this.o = new d();
        this.m = new h();
        this.l = new g();
    }

    public final void C() {
        this.q = false;
    }

    public final void a() {
        if (!this.q) {
            return;
        }
        switch (this.r) {
            case 0: {
                int n2 = 0;
                g g2 = this.l;
                this.l.a(g2.d[n2]);
                if (this.l.k - this.o.b <= game.b.h()) break;
                this.k = true;
                break;
            }
            case 2: {
                int n3 = 0;
                g g3 = this.l;
                this.l.a(g3.d[n3]);
                if (this.l.k - this.o.b >= 0) break;
                this.k = true;
            }
        }
        this.m.b();
    }

    public final void a(Graphics graphics) {
        this.m.a(graphics);
    }

    public final boolean b() {
        b b2 = this;
        this.l.d = new short[3];
        int n2 = 5;
        int n3 = 0;
        g g2 = b2.l;
        g2.d[n3] = n2;
        b2.l.a(343, false);
        b2.d(b2.r);
        b2.l.c();
        b2.m.a(b2.l);
        b2 = this;
        b2.o.a(101);
        b2.m.a(b2.o);
        b2 = this;
        b2.p.a(b2.l, game.b.g(), game.b.h(), true);
        b2.m.a(b2.p);
        b2.m.b();
        return true;
    }

    public final void c() {
        this.l = null;
        this.m = null;
        n = null;
        this.o = null;
        this.p = null;
    }

    public final void a(byte by) {
        this.b = this.a;
        switch (by) {
            case 1: {
                a.a.f.a().c(0, 13);
                a.a.f.a().a(5, 1, game.b.g(), 30, 30);
                break;
            }
            case 2: {
                b b2 = this;
                this.k = false;
                b2.q = true;
                break;
            }
            case 3: {
                this.k = false;
                a.a.f.a().c(0, 12);
                a.a.f.a().a(5, 1, game.b.g(), 30, 30);
            }
        }
        this.a = by;
    }

    public final void d(byte n2) {
        this.r = n2;
        int n3 = n2;
        g g2 = this.l;
        this.l.o = n3;
        short s = (short)(game.b.g() >> 1);
        n3 = 0;
        switch (n2) {
            case 0: {
                s = (short)(game.b.g() >> 1);
                n3 = 10;
                break;
            }
            case 2: {
                s = (short)(game.b.g() >> 1);
                n3 = (short)(game.b.h() - 10);
            }
        }
        this.l.b(s, n3);
        this.l.a((byte)n2, (byte)-1, false);
    }
}

