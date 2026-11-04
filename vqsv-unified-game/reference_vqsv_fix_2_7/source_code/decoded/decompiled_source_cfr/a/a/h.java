/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
package a.a;

import a.a;
import a.a.g;
import a.b.b;
import a.b.c;
import a.b.d;
import game.j;
import game.l;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class h {
    private d a;
    private Vector b;
    private Vector c = new Vector();
    private Vector d;
    private b e;

    public h() {
        this.b = new Vector();
        this.d = new Vector();
    }

    public final void a(d d2) {
        this.a = d2;
    }

    public final void a(a.b.a a2) {
        switch (a2.t) {
            case 0: {
                this.b.addElement(a2);
                return;
            }
            case 1: {
                this.c.addElement(a2);
                return;
            }
            case 2: {
                this.d.addElement(a2);
            }
        }
    }

    public final void b(a.b.a a2) {
        switch (a2.t) {
            case 0: {
                this.b.removeElement(a2);
                return;
            }
            case 1: {
                this.c.removeElement(a2);
                return;
            }
            case 2: {
                this.d.removeElement(a2);
            }
        }
    }

    public final void a(a.b.a a2, int n2) {
        this.b(a2);
        this.d.addElement(a2);
    }

    public final void a(b b2) {
        this.e = b2;
    }

    public final void a() {
        this.a = null;
        this.e = null;
        this.c.removeAllElements();
        this.b.removeAllElements();
        this.d.removeAllElements();
    }

    public final void b() {
        Object e2;
        int n2;
        int n3;
        this.e.c();
        this.a.a(this.e.j, this.e.k);
        this.a.d();
        for (n3 = 0; n3 < this.b.size(); ++n3) {
            for (n2 = 0; n2 < this.b.size() - n3 - 1; ++n2) {
                if (((a.b.a)this.b.elementAt((int)n2)).k <= ((a.b.a)this.b.elementAt((int)(n2 + 1))).k) continue;
                e2 = this.b.elementAt(n2);
                this.b.setElementAt(this.b.elementAt(n2 + 1), n2);
                this.b.setElementAt(e2, n2 + 1);
            }
        }
        for (n3 = 0; n3 < this.b.size(); ++n3) {
            ((g)this.b.elementAt(n3)).a();
        }
        for (n3 = 0; n3 < this.c.size(); ++n3) {
            for (n2 = 0; n2 < this.c.size() - n3 - 1; ++n2) {
                if (((a.b.a)this.c.elementAt((int)n2)).k <= ((a.b.a)this.c.elementAt((int)(n2 + 1))).k) continue;
                e2 = this.c.elementAt(n2);
                this.c.setElementAt(this.c.elementAt(n2 + 1), n2);
                this.c.setElementAt(e2, n2 + 1);
            }
        }
        for (n3 = 0; n3 < this.c.size(); ++n3) {
            ((g)this.c.elementAt(n3)).a();
            if (!(this.c.elementAt(n3) instanceof game.h)) continue;
            if (((game.h)this.c.elementAt((int)n3)).H != null && ((game.h)this.c.elementAt((int)n3)).H.j()) {
                ((game.h)this.c.elementAt((int)n3)).H.a();
                continue;
            }
            if (((game.h)this.c.elementAt((int)n3)).I == null || !((game.h)this.c.elementAt((int)n3)).I.j()) continue;
            ((game.h)this.c.elementAt((int)n3)).I.a();
        }
        for (n3 = 0; n3 < this.d.size(); ++n3) {
            for (n2 = 0; n2 < this.d.size() - n3 - 1; ++n2) {
                if (((a.b.a)this.d.elementAt((int)n2)).k <= ((a.b.a)this.d.elementAt((int)(n2 + 1))).k) continue;
                e2 = this.d.elementAt(n2);
                this.d.setElementAt(this.d.elementAt(n2 + 1), n2);
                this.d.setElementAt(e2, n2 + 1);
            }
        }
        for (n3 = 0; n3 < this.d.size(); ++n3) {
            ((g)this.d.elementAt(n3)).a();
        }
    }

    public final void a(Graphics graphics) {
        int n2;
        for (n2 = 0; n2 < this.c.size(); ++n2) {
            ((g)this.c.elementAt(n2)).b(graphics, this.a.a, this.a.b);
        }
        this.a.a(graphics, 1);
        this.a.a(graphics, 2);
        for (n2 = 0; n2 < this.d.size(); ++n2) {
            if (!((g)this.d.elementAt(n2)).l()) continue;
            ((g)this.d.elementAt(n2)).a(graphics, this.a.a, this.a.b);
        }
        if (l.B().m.Q[2] == 2) {
            for (n2 = 0; n2 < this.c.size(); ++n2) {
                if (((g)this.c.elementAt(n2)).l()) {
                    if (this.c.elementAt(n2) instanceof j) continue;
                    ((g)this.c.elementAt(n2)).a(graphics, this.a.a, this.a.b);
                }
                if (this.c.elementAt(n2) instanceof game.h && ((game.h)this.c.elementAt((int)n2)).w == 14) {
                    ((game.h)this.c.elementAt(n2)).c(graphics, this.a.a, this.a.b);
                }
                if (!(this.c.elementAt(n2) instanceof game.h)) continue;
                if (((game.h)this.c.elementAt((int)n2)).H != null && ((game.h)this.c.elementAt((int)n2)).H.k()) {
                    ((game.h)this.c.elementAt((int)n2)).H.a(graphics, this.a.a, this.a.b);
                    continue;
                }
                if (((game.h)this.c.elementAt((int)n2)).I == null || !((game.h)this.c.elementAt((int)n2)).I.k()) continue;
                ((game.h)this.c.elementAt((int)n2)).I.a(graphics, this.a.a, this.a.b);
            }
            if (a.b.d.a().a(0, j.p().j, j.p().k) != 1 && l.B().m.b != null && l.B().m.k()) {
                l.B().m.b.a(graphics, this.a.a, this.a.b);
            }
            l.B().m.a(graphics, this.a.a, this.a.b);
        } else {
            for (n2 = 0; n2 < this.c.size(); ++n2) {
                if (((g)this.c.elementAt(n2)).l()) {
                    if (((g)this.c.elementAt((int)n2)).b != null && ((g)this.c.elementAt(n2)).k()) {
                        ((g)this.c.elementAt((int)n2)).b.a(graphics, this.a.a, this.a.b);
                    }
                    ((g)this.c.elementAt(n2)).a(graphics, this.a.a, this.a.b);
                }
                if (this.c.elementAt(n2) instanceof game.h && ((game.h)this.c.elementAt((int)n2)).w == 14) {
                    ((game.h)this.c.elementAt(n2)).c(graphics, this.a.a, this.a.b);
                }
                if (!(this.c.elementAt(n2) instanceof game.h)) continue;
                if (((game.h)this.c.elementAt((int)n2)).H != null && ((game.h)this.c.elementAt((int)n2)).H.k()) {
                    ((game.h)this.c.elementAt((int)n2)).H.a(graphics, this.a.a, this.a.b);
                    continue;
                }
                if (((game.h)this.c.elementAt((int)n2)).I == null || !((game.h)this.c.elementAt((int)n2)).I.k()) continue;
                ((game.h)this.c.elementAt((int)n2)).I.a(graphics, this.a.a, this.a.b);
            }
        }
        this.a.a(graphics, 3);
        for (n2 = 0; n2 < this.b.size(); ++n2) {
            if (!((g)this.b.elementAt(n2)).l()) continue;
            ((g)this.b.elementAt(n2)).a(graphics, this.a.a, this.a.b);
        }
    }

    public final void b(Graphics graphics) {
        int n2;
        l.B();
        l.b(graphics);
        this.a.a(graphics, 1);
        this.a.a(graphics, 2);
        for (n2 = 0; n2 < this.d.size(); ++n2) {
            if (!((g)this.d.elementAt(n2)).l() || !(this.d.elementAt(n2) instanceof game.h) || ((game.h)this.d.elementAt((int)n2)).w != 0) continue;
            ((g)this.d.elementAt(n2)).a(graphics, this.a.a, this.a.b);
        }
        for (n2 = 0; n2 < this.c.size(); ++n2) {
            if (!((g)this.c.elementAt(n2)).l() || !(this.c.elementAt(n2) instanceof game.h) || ((game.h)this.c.elementAt((int)n2)).w != 0) continue;
            ((g)this.c.elementAt(n2)).a(graphics, this.a.a, this.a.b);
        }
        this.a.a(graphics, 3);
        for (n2 = 0; n2 < this.b.size(); ++n2) {
            if (!((g)this.b.elementAt(n2)).l() || !(this.b.elementAt(n2) instanceof game.h) || ((game.h)this.b.elementAt((int)n2)).w != 0) continue;
            ((g)this.b.elementAt(n2)).a(graphics, this.a.a, this.a.b);
        }
        for (n2 = 0; n2 < a.a.g() / a.b.c.f.getWidth(); ++n2) {
            for (int i2 = 0; i2 < a.a.h() / a.b.c.f.getHeight(); ++i2) {
                graphics.drawImage(a.b.c.f, n2 * a.b.c.f.getWidth(), i2 * a.b.c.f.getHeight(), 20);
            }
        }
    }
}

