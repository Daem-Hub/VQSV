/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import game.a;
import game.g;
import game.k;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class t {
    private j a;
    private Vector b;
    private Vector c = new Vector();
    private Vector d;
    private ai e;

    public t() {
        this.b = new Vector();
        this.d = new Vector();
    }

    public final void a(j j2) {
        this.a = j2;
    }

    public final void a(n n2) {
        switch (n2.s) {
            case 0: {
                this.b.addElement(n2);
                return;
            }
            case 1: {
                this.c.addElement(n2);
                return;
            }
            case 2: {
                this.d.addElement(n2);
            }
        }
    }

    public final void b(n n2) {
        switch (n2.s) {
            case 0: {
                this.b.removeElement(n2);
                return;
            }
            case 1: {
                this.c.removeElement(n2);
                return;
            }
            case 2: {
                this.d.removeElement(n2);
            }
        }
    }

    public final void c(n n2) {
        this.b(n2);
        this.d.addElement(n2);
    }

    public final void a(ai ai2) {
        this.e = ai2;
    }

    public final void a() {
        this.a = null;
        this.e = null;
        this.c.removeAllElements();
        this.b.removeAllElements();
        this.d.removeAllElements();
    }

    public final void b() {
        int n2;
        this.e.d();
        this.a.a(this.e.i, this.e.j);
        this.a.c();
        for (n2 = 0; n2 < this.b.size(); ++n2) {
            ((f)this.b.elementAt(n2)).a();
        }
        for (n2 = 0; n2 < this.c.size(); ++n2) {
            for (int i2 = 0; i2 < this.c.size() - n2 - 1; ++i2) {
                n n3 = (n)this.c.elementAt(i2);
                n n4 = (n)this.c.elementAt(i2 + 1);
                if (n3.j <= n4.j) continue;
                this.c.setElementAt(n4, i2);
                this.c.setElementAt(n3, i2 + 1);
            }
        }
        for (n2 = 0; n2 < this.c.size(); ++n2) {
            f f2 = (f)this.c.elementAt(n2);
            f2.a();
            if (!(f2 instanceof a)) continue;
            if (((a)f2).G != null && ((a)f2).G.i()) {
                ((a)f2).G.a();
                continue;
            }
            if (((a)f2).H == null || !((a)f2).H.i()) continue;
            ((a)f2).H.a();
        }
        for (n2 = 0; n2 < this.d.size(); ++n2) {
            ((f)this.d.elementAt(n2)).a();
        }
    }

    public final void a(Graphics graphics) {
        int n2;
        int n3;
        this.a.a(graphics, 1, 1);
        this.a.a(graphics, 2, 1);
        for (n3 = 0; n3 < this.d.size(); ++n3) {
            if (!((f)this.d.elementAt(n3)).k()) continue;
            ((f)this.d.elementAt(n3)).a(graphics, this.a.a, this.a.b);
        }
        n3 = 0;
        try {
            if (k.a().c.P[2] == 2) {
                n3 = 1;
                for (n2 = 0; n2 < this.c.size(); ++n2) {
                    n3 = 2;
                    n3 = 2;
                    if (((f)this.c.elementAt(n2)).k()) {
                        if ((f)this.c.elementAt(n2) instanceof g) continue;
                        n3 = 3;
                        ((f)this.c.elementAt(n2)).a(graphics, this.a.a, this.a.b);
                    }
                    if ((f)this.c.elementAt(n2) instanceof a && ((a)this.c.elementAt((int)n2)).v == 14) {
                        n3 = 4;
                        ((a)this.c.elementAt(n2)).b(graphics, this.a.a, this.a.b);
                    }
                    n3 = 501;
                    if (!((f)this.c.elementAt(n2) instanceof a)) continue;
                    if (((a)this.c.elementAt((int)n2)).G != null && ((a)this.c.elementAt((int)n2)).G.j()) {
                        n3 = 5;
                        ((a)this.c.elementAt((int)n2)).G.a(graphics, this.a.a, this.a.b);
                        continue;
                    }
                    if (((a)this.c.elementAt((int)n2)).H == null || !((a)this.c.elementAt((int)n2)).H.j()) continue;
                    n3 = 6;
                    ((a)this.c.elementAt((int)n2)).H.a(graphics, this.a.a, this.a.b);
                }
                n3 = 7;
                n2 = j.a().b(g.o().i, g.o().j);
                n3 = 8;
                if (n2 != 1 && k.a().c.b != null && k.a().c.j()) {
                    n3 = 9;
                    k.a().c.b.a(graphics, this.a.a, this.a.b);
                }
                n3 = 10;
                k.a().c.a(graphics, this.a.a, this.a.b);
            } else {
                for (n2 = 0; n2 < this.c.size(); ++n2) {
                    n3 = 110;
                    n3 = 11;
                    if (((f)this.c.elementAt(n2)).k()) {
                        n3 = 12;
                        if (((f)this.c.elementAt((int)n2)).b != null && ((f)this.c.elementAt(n2)).j()) {
                            n3 = 13;
                            ((f)this.c.elementAt((int)n2)).b.a(graphics, this.a.a, this.a.b);
                        }
                        n3 = 14;
                        ((f)this.c.elementAt(n2)).a(graphics, this.a.a, this.a.b);
                    }
                    if ((f)this.c.elementAt(n2) instanceof a && ((a)this.c.elementAt((int)n2)).v == 14) {
                        n3 = 15;
                        ((a)this.c.elementAt(n2)).b(graphics, this.a.a, this.a.b);
                    }
                    n3 = 16;
                    if (!((f)this.c.elementAt(n2) instanceof a) || !((a)this.c.elementAt(n2)).k()) continue;
                    n3 = 17;
                    if (((a)this.c.elementAt((int)n2)).G != null && ((a)this.c.elementAt((int)n2)).G.j()) {
                        n3 = 18;
                        ((a)this.c.elementAt((int)n2)).G.a(graphics, this.a.a, this.a.b);
                        continue;
                    }
                    if (((a)this.c.elementAt((int)n2)).H == null || !((a)this.c.elementAt((int)n2)).H.j()) continue;
                    n3 = 19;
                    ((a)this.c.elementAt((int)n2)).H.a(graphics, this.a.a, this.a.b);
                }
            }
        }
        catch (Exception exception) {
            h.a(exception, "" + n3);
        }
        this.a.a(graphics, 3, 1);
        for (n2 = 0; n2 < this.b.size(); ++n2) {
            f f2 = (f)this.b.elementAt(n2);
            if (!f2.k()) continue;
            f2.a(graphics, this.a.a, this.a.b);
        }
    }

    public final void b(Graphics graphics) {
        int n2;
        k.a();
        k.a(graphics, 0, 0, an.w(), an.x());
        this.a.a(graphics, 1, 1);
        this.a.a(graphics, 2, 1);
        for (n2 = 0; n2 < this.d.size(); ++n2) {
            if (!((f)this.d.elementAt(n2)).k() || !(this.d.elementAt(n2) instanceof a) || ((a)this.d.elementAt((int)n2)).v != 0) continue;
            ((f)this.d.elementAt(n2)).a(graphics, this.a.a, this.a.b);
        }
        for (n2 = 0; n2 < this.c.size(); ++n2) {
            if (!((f)this.c.elementAt(n2)).k() || !(this.c.elementAt(n2) instanceof a) || ((a)this.c.elementAt((int)n2)).v != 0) continue;
            ((f)this.c.elementAt(n2)).a(graphics, this.a.a, this.a.b);
        }
        this.a.a(graphics, 3, 1);
        for (n2 = 0; n2 < this.b.size(); ++n2) {
            if (!((f)this.b.elementAt(n2)).k() || !(this.b.elementAt(n2) instanceof a) || ((a)this.b.elementAt((int)n2)).v != 0) continue;
            ((f)this.b.elementAt(n2)).a(graphics, this.a.a, this.a.b);
        }
        for (n2 = 0; n2 < an.w() / aq.f.getWidth(); ++n2) {
            for (int i2 = 0; i2 < an.x() / aq.f.getHeight(); ++i2) {
                graphics.drawImage(aq.f, n2 * aq.f.getWidth(), i2 * aq.f.getHeight(), 20);
            }
        }
    }
}

