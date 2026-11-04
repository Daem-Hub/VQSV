/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Font
 *  javax.microedition.lcdui.Graphics
 */
package c;

import a.a;
import a.e;
import c.d;
import c.g;
import c.k;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public final class i {
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
    public g i;
    public int j;
    public int k;
    public int l;
    public g m;
    private k r = null;
    private String s = "";
    private boolean[] t = new boolean[]{false, false};

    public i() {
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

    private static void a(Graphics graphics, k k2, int n2) {
        if (graphics == null || n2 >> 24 == 0) {
            return;
        }
        graphics.setColor(n2);
        graphics.fillRect(k2.a, k2.b, k2.c, k2.d);
    }

    public final void a(k k2) {
        this.r = k2;
    }

    public final void a() {
        this.n[1] = -this.r.d;
        this.n[0] = -this.r.c / 2;
        this.t[1] = false;
        this.t[0] = false;
    }

    public final boolean b() {
        return this.t[1];
    }

    private void a(Graphics graphics, k k2, String string, int n2, int n3, boolean bl, byte by, d d2, byte by2) {
        if (graphics == null || n2 >> 24 == 0) {
            return;
        }
        if (string.startsWith("#P") && string.length() > 2) {
            boolean bl2 = false;
            int n4 = 0;
            try {
                n4 = Integer.parseInt(string.substring(2).trim());
            }
            catch (Exception exception) {
                bl2 = true;
            }
            if (!bl2 && n4 >= 0 && n4 <= 100) {
                int n5 = n4 * k2.c / 100;
                graphics.setColor(n2);
                graphics.fillRect(k2.a + 1, k2.b + 1, n5 - 1, k2.d - 1);
                return;
            }
        }
        Font font = Font.getFont((int)0, (int)0, (int)8);
        if (!this.s.equals(string)) {
            this.n[1] = -k2.d;
            this.n[0] = -k2.c / 2;
        }
        this.s = string;
        a.e.a(graphics, string, n2, k2.a, k2.b, font.getHeight(), k2.c, k2.d, font, bl, n3, this.n, this.o, by, d2, by2, this.t);
    }

    private static void b(Graphics graphics, k k2, int n2) {
        if (graphics == null || n2 >> 24 == 0) {
            return;
        }
        graphics.setColor(n2);
        graphics.drawRect(k2.a, k2.b, k2.c, k2.d);
    }

    public final void a(Graphics graphics, int n2, int n3, int n4, int n5, boolean bl, byte by, byte by2, d d2) {
        if (this.p != bl || this.q == 0) {
            this.q = -1;
            this.p = bl;
        }
        k k2 = new k(n2, n3, n4, n5);
        i i2 = this;
        this.r = k2;
        graphics.setClip(0, 0, (int)a.a.g(), (int)a.a.h());
        if (bl) {
            c.i.a(graphics, this.r, this.e);
            c.i.b(graphics, this.r, this.f);
            if (this.i != null) {
                this.i.a(graphics, this.r, this.c);
            }
            this.a(graphics, this.r, this.a, this.g, this.b, this.d, by, d2, by2);
            return;
        }
        c.i.a(graphics, this.r, this.j);
        c.i.b(graphics, this.r, this.k);
        if (this.m != null) {
            this.m.a(graphics, this.r, this.c);
        }
        this.a(graphics, this.r, this.a, this.l, this.b, this.d, by, d2, by2);
    }

    public final void c() {
        if (this.i != null) {
            this.i.c();
            this.i = null;
        }
        if (this.m != null) {
            this.m.c();
            this.m = null;
        }
    }
}

