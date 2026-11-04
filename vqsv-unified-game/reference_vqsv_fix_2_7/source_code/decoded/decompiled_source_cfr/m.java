/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class m {
    private d b = null;
    private byte c = 0;
    private short d = (short)-1;
    public byte a = (byte)4;

    public final void a(int n2, boolean bl, byte by) {
        if (n2 != -1) {
            this.b = new d();
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

    public final d a() {
        return this.b;
    }

    public final void a(int n2) {
        this.d = (short)n2;
    }

    public final void b() {
        this.c = 1;
    }

    public m() {
        this.d = (short)-1;
        this.a = (byte)4;
    }

    public final void a(Graphics graphics, ak ak2, int n2) {
        if (this.b != null) {
            Object object;
            boolean bl = false;
            m m2 = this;
            if (m2.d == -1) {
                object = null;
            } else {
                int[] nArray = new int[4];
                switch (m2.a) {
                    case 3: {
                        nArray = m2.b.a((int)m2.d, (byte)0);
                        break;
                    }
                    case 2: {
                        nArray = m2.b.b(m2.d, (byte)0);
                    }
                }
                object = m2 = new ak(nArray[0], nArray[1], nArray[2], nArray[3]);
            }
            if (object == null) {
                return;
            }
            int n3 = ak2.a;
            int n4 = ak2.b;
            switch (n2) {
                case 4: {
                    n3 = ak2.a + (ak2.c - ((ak)((Object)m2)).c) / 2 - ((ak)((Object)m2)).a;
                    n4 = ak2.b + (ak2.d - ((ak)((Object)m2)).d) / 2 - ((ak)((Object)m2)).b;
                    break;
                }
                case 3: {
                    n3 = ak2.a - ((ak)((Object)m2)).a;
                    n4 = ak2.b + (ak2.d - ((ak)((Object)m2)).d) / 2 - ((ak)((Object)m2)).b;
                    break;
                }
                case 5: {
                    n3 = ak2.a + (ak2.c - ((ak)((Object)m2)).c) - ((ak)((Object)m2)).a;
                    n4 = ak2.b + (ak2.d - ((ak)((Object)m2)).d) / 2 - ((ak)((Object)m2)).b;
                    break;
                }
                case 6: {
                    n3 = ak2.a - ((ak)((Object)m2)).a;
                    n4 = ak2.b + (ak2.d - ((ak)((Object)m2)).d) - ((ak)((Object)m2)).b;
                    break;
                }
                case 8: {
                    n3 = ak2.a + (ak2.c - ((ak)((Object)m2)).c) - ((ak)((Object)m2)).a;
                    n4 = ak2.b + (ak2.d - ((ak)((Object)m2)).d) - ((ak)((Object)m2)).b;
                    break;
                }
                case 7: {
                    n3 = ak2.a + (ak2.c - ((ak)((Object)m2)).c) / 2 - ((ak)((Object)m2)).a;
                    n4 = ak2.b + (ak2.d - ((ak)((Object)m2)).d) - ((ak)((Object)m2)).b;
                    break;
                }
                case 0: {
                    n3 = ak2.a - ((ak)((Object)m2)).a;
                    n4 = ak2.b - ((ak)((Object)m2)).b;
                    break;
                }
                case 2: {
                    n3 = ak2.a + (ak2.c - ((ak)((Object)m2)).c) - ((ak)((Object)m2)).a;
                    n4 = ak2.b - ((ak)((Object)m2)).b;
                    break;
                }
                case 1: {
                    n3 = ak2.a + (ak2.c - ((ak)((Object)m2)).c) / 2 - ((ak)((Object)m2)).a;
                    n4 = ak2.b - ((ak)((Object)m2)).b;
                }
            }
            if (this.a == 3) {
                this.b.a(graphics, n3, n4, this.c);
                return;
            }
            if (this.a == 2) {
                this.b.a(graphics, this.d, n3, n4, (byte)0);
            }
        }
    }

    public final void c() {
        if (this.a == 3) {
            this.b.d();
        }
    }

    public final void d() {
        if (this.b != null) {
            this.b.a();
            this.b = null;
        }
    }
}

