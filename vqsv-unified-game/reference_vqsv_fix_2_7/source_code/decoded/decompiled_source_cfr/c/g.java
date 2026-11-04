/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
package c;

import c.k;
import javax.microedition.lcdui.Graphics;

public final class g {
    private a.b.g b = null;
    private byte c = 0;
    private short d = (short)-1;
    public byte a = (byte)4;

    public final void a(int n2, boolean bl, byte by) {
        if (n2 != -1) {
            this.b = new a.b.g();
            this.b.a(n2, bl);
            switch (this.a) {
                case 3: {
                    this.b.a((byte)this.d, by, true);
                }
            }
        }
    }

    public final void a(byte by, byte by2) {
        this.d = by;
        this.b.a(by, by2, true);
    }

    public final a.b.g a() {
        return this.b;
    }

    public final void a(int n2) {
        this.d = (short)n2;
    }

    public final void a(byte by) {
        this.c = 1;
    }

    public g() {
        this.d = (short)-1;
        this.a = (byte)4;
    }

    public final void a(Graphics graphics, k k2, int n2) {
        if (this.b != null) {
            Object object;
            boolean bl = false;
            g g2 = this;
            if (g2.d == -1) {
                object = null;
            } else {
                int[] nArray = new int[4];
                switch (g2.a) {
                    case 3: {
                        nArray = g2.b.a((int)g2.d, (byte)0);
                        break;
                    }
                    case 2: {
                        nArray = g2.b.b(g2.d, (byte)0);
                    }
                }
                object = g2 = new k(nArray[0], nArray[1], nArray[2], nArray[3]);
            }
            if (object == null) {
                return;
            }
            int n3 = k2.a;
            int n4 = k2.b;
            switch (n2) {
                case 4: {
                    n3 = k2.a + (k2.c - ((k)((Object)g2)).c) / 2 - ((k)((Object)g2)).a;
                    n4 = k2.b + (k2.d - ((k)((Object)g2)).d) / 2 - ((k)((Object)g2)).b;
                    break;
                }
                case 3: {
                    n3 = k2.a - ((k)((Object)g2)).a;
                    n4 = k2.b + (k2.d - ((k)((Object)g2)).d) / 2 - ((k)((Object)g2)).b;
                    break;
                }
                case 5: {
                    n3 = k2.a + (k2.c - ((k)((Object)g2)).c) - ((k)((Object)g2)).a;
                    n4 = k2.b + (k2.d - ((k)((Object)g2)).d) / 2 - ((k)((Object)g2)).b;
                    break;
                }
                case 6: {
                    n3 = k2.a - ((k)((Object)g2)).a;
                    n4 = k2.b + (k2.d - ((k)((Object)g2)).d) - ((k)((Object)g2)).b;
                    break;
                }
                case 8: {
                    n3 = k2.a + (k2.c - ((k)((Object)g2)).c) - ((k)((Object)g2)).a;
                    n4 = k2.b + (k2.d - ((k)((Object)g2)).d) - ((k)((Object)g2)).b;
                    break;
                }
                case 7: {
                    n3 = k2.a + (k2.c - ((k)((Object)g2)).c) / 2 - ((k)((Object)g2)).a;
                    n4 = k2.b + (k2.d - ((k)((Object)g2)).d) - ((k)((Object)g2)).b;
                    break;
                }
                case 0: {
                    n3 = k2.a - ((k)((Object)g2)).a;
                    n4 = k2.b - ((k)((Object)g2)).b;
                    break;
                }
                case 2: {
                    n3 = k2.a + (k2.c - ((k)((Object)g2)).c) - ((k)((Object)g2)).a;
                    n4 = k2.b - ((k)((Object)g2)).b;
                    break;
                }
                case 1: {
                    n3 = k2.a + (k2.c - ((k)((Object)g2)).c) / 2 - ((k)((Object)g2)).a;
                    n4 = k2.b - ((k)((Object)g2)).b;
                }
            }
            if (this.a == 3) {
                this.b.a(graphics, n3, n4, this.c);
                return;
            }
            if (this.a == 2) {
                this.b.a(graphics, (int)this.d, n3, n4, (byte)0, 20);
            }
        }
    }

    public final void b() {
        if (this.a == 3) {
            this.b.e();
        }
    }

    public final void c() {
        if (this.b != null) {
            this.b.b();
            this.b = null;
        }
    }
}

