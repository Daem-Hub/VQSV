/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
package game;

import game.a;
import game.f;
import game.l;
import javax.microedition.lcdui.Graphics;

public final class c
extends a.a {
    private static c k = null;
    private StringBuffer l = null;
    private l m = game.l.B();
    private a n = game.a.B();
    private int[] o = new int[]{2, 7, 8, 8, 13, 7, 2, 13, 7, 8, 15, 29};
    private int[] p = new int[]{0, 2, 9, 17, 25, 38, 45, 47, 60, 67, 75, 90};
    private byte q = 0;
    private byte r = 0;
    private byte s = 0;
    private byte t = 0;
    private byte u = 0;
    private static boolean v = true;
    private String[] w = new String[]{"1: tr\u00e0ng c\u1ea3nh kh\u1ed1ng ch\u1ebf", "2: s\u1ee7ng v\u1eadt kh\u1ed1ng ch\u1ebf"};
    private String[] x = new String[]{"L\u1ef1a ch\u1ecdn tr\u00e0ng c\u1ea3nh", "L\u1ef1a ch\u1ecdn gian ph\u00f2ng", "G\u1eb7p \u0111\u1ecbch \u0111i\u1ec1u ch\u1ec9nh th\u1eed"};
    private short[] y = new short[]{0, 0, 198, 198, 151, 55, 80, 63, 118, 118, 118, 132, 112, 174, 160, 368, 136, 136, 230, 245, 167, 135, 168, 198};
    private String[] z = new String[]{"CG tr\u00e0ng c\u1ea3nh", "Th\u00f4n trang", "B\u00edch th\u1ee7y", "G\u1ed7 th\u00f4", "Ni\u00eam th\u1ed5", "H\u1eafc th\u1ea1ch", "H\u1eafc Long th\u1ea7n \u0111i\u1ec7n", "Thi\u00ean gi\u1edbi", "Vi\u1ec5n c\u1ed5", "\u0110\u1ea1o qu\u00e1n", "Th\u1ea7n th\u00fa m\u00ea cung", "Trong ph\u00f2ng"};

    public static c B() {
        if (k == null) {
            k = new c();
        }
        return k;
    }

    public final void a() {
        if (!this.j) {
            return;
        }
        this.A();
        switch (this.a) {
            case 0: {
                if (!this.g(196640)) break;
                this.a((byte)1);
                return;
            }
            case 1: {
                if (this.g(16400)) {
                    this.t = 0;
                    this.r = (byte)(this.r - 1);
                    if (this.r > 0) break;
                    this.r = 0;
                    return;
                }
                if (this.g(32832)) {
                    this.t = 0;
                    this.r = (byte)(this.r + 1);
                    if (this.r <= this.x.length - 1) break;
                    this.r = (byte)(this.x.length - 1);
                    return;
                }
                if (this.g(4100)) {
                    if (this.r == 0) {
                        this.s = (byte)(this.s - 1);
                        if (this.s > 0) break;
                        this.s = 0;
                        return;
                    }
                    if (this.r == 1) {
                        this.t = (byte)(this.t - 1);
                        if (this.t > 0) break;
                        this.t = 0;
                        return;
                    }
                    if (this.r != 2) break;
                    v = !v;
                    return;
                }
                if (this.g(8448)) {
                    if (this.r == 0) {
                        this.s = (byte)(this.s + 1);
                        if (this.s < this.o.length - 1) break;
                        this.s = (byte)(this.o.length - 1);
                        return;
                    }
                    if (this.r == 1) {
                        this.t = (byte)(this.t + 1);
                        if (this.t < this.o[this.s] - 1) break;
                        this.t = (byte)(this.o[this.s] - 1);
                        return;
                    }
                    if (this.r != 2) break;
                    v = !v;
                    return;
                }
                if (this.g(196640)) {
                    this.m.p = this.s;
                    this.m.q = this.t;
                    game.l.B().t = -1;
                    game.l.H = true;
                    h = false;
                    this.m.r = this.y[this.s << 1];
                    this.m.s = this.y[(this.s << 1) + 1];
                    this.m.c();
                    game.f.B().a((byte)9);
                    return;
                }
                if (!this.g(1024)) break;
                game.f.B().a((byte)10);
                return;
            }
            case 2: {
                if (this.g(4100)) {
                    this.u = (byte)(this.u - 1);
                    if (this.u > 0) break;
                    this.u = (byte)(a.b.c.c[0].length - 1);
                    return;
                }
                if (this.g(8448)) {
                    this.u = (byte)(this.u + 1);
                    if (this.u < a.b.c.c[0].length - 1) break;
                    this.u = 0;
                    return;
                }
                if (!this.g(196640)) break;
                this.n.c();
                this.m.M();
                game.f.B().a((byte)12);
            }
        }
    }

    public final void a(Graphics graphics) {
        graphics.setColor(0xFFFFFF);
        graphics.fillRect(0, 0, (int)game.c.g(), 100);
        switch (this.a) {
            case 0: {
                graphics.setColor(0xFF0000);
                graphics.drawString("\u0110\u00e0i \u0111i\u1ec1u khi\u1ec3n", game.c.g() >> 1, 10, 17);
                graphics.setColor(0);
                for (int i2 = 0; i2 < this.w.length; ++i2) {
                    if (i2 == 0) {
                        graphics.setColor(0xFF0000);
                    } else {
                        graphics.setColor(0);
                    }
                    graphics.drawString(this.w[i2], 10, 30 + i2 * 20, 20);
                }
                return;
            }
            case 1: {
                graphics.setColor(0xFF0000);
                graphics.drawString(this.w[0], game.c.g() >> 1, 10, 17);
                for (int i3 = 0; i3 < this.x.length; ++i3) {
                    if (this.r == i3) {
                        graphics.setColor(0xFF0000);
                    } else {
                        graphics.setColor(0);
                    }
                    graphics.drawString(this.x[i3], 30 + i3 * 80, 30, 20);
                }
                graphics.setColor(0);
                graphics.drawString("Tr\u00e0ng c\u1ea3nh: " + this.s + "  " + this.z[this.s], 10, 50, 20);
                graphics.drawString("Gian ph\u00f2ng: " + this.t + "  " + game.c.c(384 + this.p[this.s] + this.t), 10, 70, 20);
                if (v) {
                    graphics.drawString("C\u00f3 th\u1ec3 g\u1eb7p \u0111\u01b0\u1ee3c \u0111\u1ecbch", 120, 70, 20);
                    return;
                }
                graphics.drawString("Kh\u00f4ng th\u1ec3 g\u1eb7p \u0111\u1ecbch", 120, 70, 20);
                return;
            }
            case 2: {
                graphics.setColor(0xFF0000);
                graphics.drawString(this.w[1], game.c.g() >> 1, 10, 17);
                graphics.drawString("S\u1ee7ng v\u1eadt tr\u01b0\u1edbc m\u1eb7t: " + this.u + " t\u00ean: " + game.c.c(a.b.c.c[0][this.u][0]), game.c.g() >> 1, 30, 17);
            }
        }
    }

    public final boolean b() {
        if (this.l == null) {
            this.l = new StringBuffer();
        }
        return true;
    }

    public final void c() {
    }

    public final void a(byte by) {
        this.b = this.a;
        switch (by) {
            case 0: {
                break;
            }
            case 1: {
                this.s = (byte)this.m.p;
                this.t = (byte)this.m.q;
            }
        }
        this.a = by;
    }
}

