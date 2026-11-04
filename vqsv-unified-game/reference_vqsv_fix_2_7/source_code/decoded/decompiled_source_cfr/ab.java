/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class ab {
    private static ab c;
    private Hashtable d = new Hashtable();
    private Vector e = new Vector();
    private Vector f = new Vector();
    public ao a;
    public y b = new y();

    private ab() {
    }

    public static ab a() {
        if (c == null) {
            c = new ab();
        }
        return c;
    }

    public final void b() {
        if (c != null) {
            ab ab2 = c;
            Enumeration enumeration = ab2.d.elements();
            while (enumeration.hasMoreElements()) {
                ((ao)enumeration.nextElement()).c();
            }
            ab2.d.clear();
            ab2.e.removeAllElements();
        }
        this.a = null;
    }

    public final void a(Graphics graphics) {
        if (this.e != null) {
            for (int i2 = 0; i2 < this.e.size(); ++i2) {
                ((ao)this.e.elementAt(i2)).a(graphics);
            }
        }
        graphics.setClip(0, 0, (int)an.w(), (int)an.x());
    }

    public final void c() {
        if (this.a != null) {
            this.a.b();
        }
    }

    public final void a(String string, int n2, i i2) {
        ao ao2 = (ao)this.d.get(string);
        if (ao2 != null) {
            if (!string.equals("/data/ui/dialog.ui")) {
                this.d.remove(string);
            }
            this.e.removeElement(ao2);
            if (!string.equals("/data/ui/dialog.ui")) {
                ao2.c();
                ao2 = null;
            }
            if (string.equals("/data/ui/dialog.ui")) {
                this.e.addElement(ao2);
                this.a = ao2;
                this.f.addElement(string);
            }
        }
        if (ao2 == null) {
            ao2 = new ao(i2);
            ao2.a(this.b);
            ao2.a(string, n2);
            this.d.put(string, ao2);
            this.e.addElement(ao2);
            this.a = ao2;
            this.f.addElement(string);
        }
    }

    public final void a(String string) {
        ao ao2 = (ao)this.d.get(string);
        if (ao2 != null) {
            if (this.a.equals(ao2)) {
                this.a = null;
            }
            if (!string.equals("/data/ui/dialog.ui")) {
                this.d.remove(string);
            }
            this.e.removeElement(ao2);
            this.f.removeElement(string);
            if (!string.equals("/data/ui/dialog.ui")) {
                ao2.c();
            }
        }
        if (this.d.size() > 0 && this.e.size() > 0) {
            this.a = (ao)this.e.lastElement();
        }
    }

    public final boolean b(String string) {
        return this.f.size() > 0 && this.f.lastElement().equals(string);
    }

    public final boolean c(String string) {
        return this.f.size() > 0 && this.f.contains(string);
    }

    public final ao d(String string) {
        return (ao)this.d.get(string);
    }

    public static boolean a(ao ao2, int n2) {
        return ((af)ao2.a((int)1)).h().m.a().b(n2);
    }

    public final boolean d() {
        return ((af)this.a.a((int)1)).h().m.a().e() && ((af)this.a.a((int)1)).h().m.a().f();
    }
}

