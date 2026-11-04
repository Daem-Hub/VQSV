/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
package c;

import c.a;
import c.c;
import c.d;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class j {
    private static j c;
    private Hashtable d = new Hashtable();
    private Vector e = new Vector();
    private Vector f = new Vector();
    public c a;
    public d b = new d();

    private j() {
    }

    public static j a() {
        if (c == null) {
            c = new j();
        }
        return c;
    }

    public final void b() {
        if (c != null) {
            j j2 = c;
            Enumeration enumeration = j2.d.elements();
            while (enumeration.hasMoreElements()) {
                ((c)enumeration.nextElement()).c();
            }
            j2.d.clear();
            j2.e.removeAllElements();
        }
        this.a = null;
        System.gc();
    }

    public final void a(Graphics graphics) {
        if (this.e != null) {
            for (int i2 = 0; i2 < this.e.size(); ++i2) {
                ((c)this.e.elementAt(i2)).a(graphics);
            }
        }
        graphics.setClip(0, 0, (int)a.a.g(), (int)a.a.h());
    }

    public final void c() {
        if (this.a != null) {
            this.a.b();
        }
    }

    public final void a(String string, int n2, a a2) {
        c c2 = (c)this.d.get(string);
        if (c2 != null) {
            if (!string.equals("/data/ui/dialog.ui")) {
                this.d.remove(string);
            }
            this.e.removeElement(c2);
            if (!string.equals("/data/ui/dialog.ui")) {
                c2.c();
                c2 = null;
            }
            if (string.equals("/data/ui/dialog.ui")) {
                this.e.addElement(c2);
                this.a = c2;
                this.f.addElement(string);
            }
        }
        if (c2 == null) {
            c2 = new c(a2);
            c2.a(this.b);
            c2.a(string, n2, false);
            this.d.put(string, c2);
            this.e.addElement(c2);
            this.a = c2;
            this.f.addElement(string);
        }
    }

    public final void a(String string) {
        c c2 = (c)this.d.get(string);
        if (c2 != null) {
            if (this.a.equals(c2)) {
                this.a = null;
            }
            if (!string.equals("/data/ui/dialog.ui")) {
                this.d.remove(string);
            }
            this.e.removeElement(c2);
            this.f.removeElement(string);
            if (!string.equals("/data/ui/dialog.ui")) {
                c2.c();
            }
        }
        if (this.d.size() > 0 && this.e.size() > 0) {
            this.a = (c)this.e.lastElement();
        }
    }

    public final boolean b(String string) {
        return this.f.size() > 0 && this.f.lastElement().equals(string);
    }

    public final boolean c(String string) {
        return this.f.size() > 0 && this.f.contains(string);
    }

    public final c d(String string) {
        return (c)this.d.get(string);
    }
}

