/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Font
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public final class k {
    public String a = "";
    private int[] n;
    private int o;
    public int b;
    public int c;
    public boolean d;
    public int e;
    public int f;
    public int g;
    private boolean p = false;
    private int q = 0;
    public byte h = (byte)-1;
    public m i;
    public int j;
    public int k;
    public int l;
    public m m;
    private ak r = null;
    private String s = "";
    private Font t = Font.getFont((int)0, (int)0, (int)8);
    private boolean[] u = new boolean[]{false, false};

    public k() {
        this.n = new int[2];
        this.n[0] = 0;
        this.n[1] = 0;
        this.o = 2;
        this.b = 4;
        this.c = 4;
        this.d = false;
        this.e = 0xFFFFFF;
        this.f = 0xFFFFFF;
        this.g = 0xFFFFFF;
        this.i = null;
        this.j = 0xFFFFFF;
        this.k = 0xFFFFFF;
        this.l = 0xFFFFFF;
        this.m = null;
        this.p = false;
        this.h = (byte)-1;
        this.q = 0;
    }

    private static void a(Graphics graphics, ak ak2, int n2) {
        if (graphics == null || n2 >> 24 == 0) {
            return;
        }
        graphics.setColor(n2);
        graphics.fillRect(ak2.a, ak2.b, ak2.c, ak2.d);
    }

    public final void a(ak ak2) {
        this.r = ak2;
    }

    public final void a() {
        this.n[1] = -this.r.d;
        this.n[0] = -this.r.c / 2;
        this.u[1] = false;
        this.u[0] = false;
    }

    public final boolean b() {
        return this.u[0] && this.u[1];
    }

    private void a(Graphics graphics, ak ak2, String string, int n2, int n3, boolean bl, byte by, y y2, byte by2) {
        if (graphics == null || n2 >> 24 == 0) {
            return;
        }
        if (string.startsWith("#P") && string.length() > 2) {
            int n4 = Integer.parseInt(string.substring(2).trim()) * ak2.c / 100;
            graphics.setColor(n2);
            graphics.fillRect(ak2.a + 1, ak2.b + 1, n4 - 1, ak2.d - 1);
            return;
        }
        if (!this.s.equals(string)) {
            this.n[1] = -ak2.d;
            this.n[0] = -ak2.c / 2;
        }
        this.s = string;
        ae.a(graphics, string, n2, ak2.a, ak2.b, this.t.getHeight(), ak2.c, ak2.d, this.t, bl, n3, this.n, this.o, by, y2, this.u);
    }

    private static void b(Graphics graphics, ak ak2, int n2) {
        if (graphics == null || n2 >> 24 == 0) {
            return;
        }
        graphics.setColor(n2);
        graphics.drawRect(ak2.a, ak2.b, ak2.c, ak2.d);
    }

    public final void a(Graphics graphics, int n2, int n3, int n4, int n5, boolean bl, byte by, byte by2, y y2) {
        ak ak2;
        if (this.p != bl || this.q == 0) {
            this.q = -1;
            this.p = bl;
        }
        this.r = ak2 = new ak(n2, n3, n4, n5);
        graphics.setClip(0, 0, (int)an.w(), (int)an.x());
        if (bl) {
            k.a(graphics, this.r, this.e);
            k.b(graphics, this.r, this.f);
            if (this.i != null) {
                this.i.a(graphics, this.r, this.c);
            }
            this.a(graphics, this.r, this.a, this.g, this.b, this.d, by, y2, by2);
            return;
        }
        k.a(graphics, this.r, this.j);
        k.b(graphics, this.r, this.k);
        if (this.m != null) {
            this.m.a(graphics, this.r, this.c);
        }
        this.a(graphics, this.r, this.a, this.l, this.b, this.d, by, y2, by2);
    }

    public final void c() {
        if (this.i != null) {
            this.i.d();
            this.i = null;
        }
        if (this.m != null) {
            this.m.d();
            this.m = null;
        }
    }
}

