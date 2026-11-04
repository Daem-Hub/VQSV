/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import game.k;
import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class j {
    private static j e;
    private static final int[] f;
    private int g = -1;
    private int h = -1;
    private short i = (short)-1;
    private short j = (short)-1;
    private short k = (short)-1;
    private short l = (short)-1;
    private int m = -1;
    private byte n = (byte)-1;
    private byte o = (byte)-1;
    private Image[] p = null;
    public int a;
    public int b;
    private int q;
    private int r;
    public int c;
    public int d;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private byte y;
    private short[][] z;
    private short[][][] A;
    private byte[] B = null;
    private static Image C;
    private static Graphics D;
    private boolean E = true;

    public static j a() {
        if (e == null) {
            e = new j();
        }
        return e;
    }

    public j() {
        this.h = an.x();
        this.g = an.w();
    }

    public final void b() {
        int n2;
        if (this.p != null) {
            for (n2 = 0; n2 < this.p.length; ++n2) {
                this.p[n2] = null;
            }
            this.p = null;
        }
        for (n2 = 0; n2 < aq.b[this.n].length; ++n2) {
            am.c(aq.b[this.n][n2]);
        }
        C = null;
        D = null;
        j j2 = this;
        this.A = null;
        j2.B = null;
        j2.z = null;
    }

    public final void a(int n2) {
        this.m = n2;
        j j2 = this;
        try {
            j2.getClass();
            InputStream inputStream = aj.a("/data/map/map_" + j2.m + ".mid");
            inputStream = new DataInputStream(inputStream);
            byte by = ((DataInputStream)inputStream).readByte();
            j2.o = j2.n;
            j2.n = ((DataInputStream)inputStream).readByte();
            j2.d();
            j2.i = by == 1 ? (short)((DataInputStream)inputStream).readByte() : ((DataInputStream)inputStream).readShort();
            j2.j = by == 1 ? (short)((DataInputStream)inputStream).readByte() : ((DataInputStream)inputStream).readShort();
            j2.l = j2.k = (short)((DataInputStream)inputStream).readByte();
            j2.c = j2.i * j2.k;
            j2.d = j2.j * j2.l;
            j2.y = ((DataInputStream)inputStream).readByte();
            j2.B = new byte[j2.y];
            j2.A = new short[j2.y][][];
            for (int i2 = 0; i2 < j2.y; ++i2) {
                int n3;
                int n4;
                byte by2 = ((DataInputStream)inputStream).readByte();
                j2.B[i2] = ((DataInputStream)inputStream).readByte();
                short s2 = ((DataInputStream)inputStream).readShort();
                if (j2.B[by2] == 0 || j2.B[by2] == 1) {
                    j2.A[by2] = new short[j2.i][j2.j];
                    for (n4 = 0; n4 < j2.i; ++n4) {
                        for (n3 = 0; n3 < j2.j; ++n3) {
                            j2.A[by2][n4][n3] = -1;
                        }
                    }
                } else {
                    j2.A[by2] = new short[s2][4];
                }
                for (n4 = 0; n4 < s2; ++n4) {
                    short s3;
                    if (by == 1) {
                        n3 = ((DataInputStream)inputStream).readByte();
                        s3 = ((DataInputStream)inputStream).readByte();
                    } else {
                        n3 = ((DataInputStream)inputStream).readShort();
                        s3 = ((DataInputStream)inputStream).readShort();
                    }
                    short s4 = ((DataInputStream)inputStream).readShort();
                    if (j2.B[i2] == 1) {
                        j2.A[i2][n3][s3] = s4;
                        continue;
                    }
                    if (j2.B[by2] == 0) {
                        j2.A[i2][n3][s3] = (short)(s4 & 0xFFF);
                        continue;
                    }
                    j2.A[i2][n4][1] = n3;
                    j2.A[i2][n4][2] = s3;
                    j2.A[i2][n4][0] = (short)(s4 & 0xFFF);
                    j2.A[i2][n4][3] = (short)((s4 & 0x7000) >> 12);
                }
            }
            ((FilterInputStream)inputStream).close();
        }
        catch (Exception exception) {
            Object var2_4 = null;
            exception.printStackTrace();
        }
        if (C == null) {
            C = Image.createImage((int)this.g, (int)this.h);
            D = C.getGraphics();
        }
        this.E = true;
    }

    private void d() {
        int n2;
        if (this.p != null) {
            for (n2 = 0; n2 < this.p.length; ++n2) {
                for (int i2 = 0; i2 < aq.b[this.n].length; ++i2) {
                    if (aq.b[this.o][n2] != aq.b[this.n][i2]) continue;
                    am.b(aq.b[this.o][n2]);
                    this.p[n2] = null;
                    break;
                }
                if (this.p[n2] == null) continue;
                am.c(aq.b[this.o][n2]);
                this.p[n2] = null;
            }
            this.p = null;
        }
        this.p = new Image[aq.b[this.n].length];
        for (n2 = 0; n2 < this.p.length; ++n2) {
            this.p[n2] = am.a(aq.b[this.n][n2]);
        }
        try {
            "".getClass();
            InputStream inputStream = aj.a("/data/mod/mod_" + this.n + ".mid");
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            int n3 = dataInputStream.readShort();
            this.z = new short[n3][5];
            for (int i3 = 0; i3 < n3; ++i3) {
                this.z[i3][0] = dataInputStream.readByte();
                this.z[i3][1] = dataInputStream.readShort();
                this.z[i3][2] = dataInputStream.readShort();
                this.z[i3][3] = dataInputStream.readShort();
                this.z[i3][4] = dataInputStream.readShort();
            }
            dataInputStream.close();
            inputStream.close();
            return;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
    }

    public final void c() {
        if (this.k == 0) {
            return;
        }
        this.t = this.b / this.l;
        this.s = this.a / this.k;
        this.v = (this.b + this.h) / this.l;
        if ((this.b + this.h) % this.l != 0) {
            ++this.v;
        }
        if (this.v > this.j) {
            this.v = this.j;
        }
        this.u = (this.a + this.g) / this.k;
        if ((this.a + this.g) % this.k != 0) {
            ++this.u;
        }
        if (this.u > this.i) {
            this.u = this.i;
        }
        this.x = this.h / this.l + 1;
        this.w = this.g / this.k + 1;
        if (this.t + this.x >= this.j) {
            this.x = this.j - 1 - this.t;
        }
        if (this.w + this.s >= this.i) {
            this.w = this.i - 1 - this.s;
        }
    }

    private void a(Graphics graphics, int n2, int n3, int n4, int n5, int n6) {
        game.k.a();
        game.k.a(graphics, n3, n4, n5, n6);
        switch (this.B[n2]) {
            case 0: {
                this.b(graphics, n2, n3, n4, n5, n6);
                return;
            }
            case 1: {
                this.c(graphics, n2, n3, n4, n5, n6);
            }
        }
    }

    public final void a(Graphics object, int n2, int n3) {
        switch (this.B[n2]) {
            case 0: 
            case 1: {
                n3 = n2;
                Graphics graphics = object;
                object = this;
                if (((j)object).E) {
                    int n4 = ((j)object).a / ((j)object).k < 0 ? 0 : ((j)object).a / ((j)object).k;
                    int n5 = ((j)object).b / ((j)object).l < 0 ? 0 : ((j)object).b / ((j)object).l;
                    int n6 = (((j)object).a + ((j)object).g) / ((j)object).k + 1 > ((j)object).i ? ((j)object).i : (((j)object).a + ((j)object).g) / ((j)object).k + 1;
                    int n7 = (((j)object).b + ((j)object).h) / ((j)object).l + 1 > ((j)object).j ? ((j)object).j : (((j)object).b + ((j)object).h) / ((j)object).l + 1;
                    game.k.a();
                    game.k.a(D, 0, 0, ((j)object).g, ((j)object).h);
                    switch (((j)object).B[n3]) {
                        case 0: {
                            super.b(D, n3, n4, n5, n6, n7);
                            break;
                        }
                        case 1: {
                            super.c(D, n3, n4, n5, n6, n7);
                        }
                    }
                    ((j)object).E = false;
                } else if (((j)object).q != ((j)object).a || ((j)object).r != ((j)object).b) {
                    int n8 = 0;
                    int n9 = 0;
                    if (((j)object).a > ((j)object).q) {
                        n8 = ((j)object).q - ((j)object).a;
                    } else if (((j)object).a < ((j)object).q) {
                        n8 = ((j)object).q - ((j)object).a;
                    }
                    if (((j)object).b > ((j)object).r) {
                        n9 = ((j)object).r - ((j)object).b;
                    } else if (((j)object).b < ((j)object).r) {
                        n9 = ((j)object).r - ((j)object).b;
                    }
                    D.copyArea(0, 0, ((j)object).g, ((j)object).h, n8, n9, 20);
                    if (((j)object).a > ((j)object).q) {
                        int n10 = (((j)object).q + ((j)object).g) / ((j)object).k;
                        super.a(D, n3, n10, ((j)object).t, ((j)object).u, ((j)object).v);
                    } else if (((j)object).a < ((j)object).q) {
                        n8 = ((j)object).q / ((j)object).k + 1;
                        super.a(D, n3, ((j)object).s, ((j)object).t, n8, ((j)object).v);
                    }
                    if (((j)object).b > ((j)object).r) {
                        int n11 = (((j)object).r + ((j)object).h) / ((j)object).l;
                        super.a(D, n3, ((j)object).s, n11, ((j)object).u, ((j)object).v);
                    } else if (((j)object).b < ((j)object).r) {
                        n8 = ((j)object).r / ((j)object).l + 1;
                        super.a(D, n3, ((j)object).s, ((j)object).t, ((j)object).u, n8);
                    }
                }
                graphics.drawImage(C, 0, 0, 20);
                ((j)object).q = ((j)object).a;
                ((j)object).r = ((j)object).b;
                return;
            }
            case 2: 
            case 3: 
            case 4: {
                n3 = n2;
                Graphics graphics = object;
                object = this;
                for (int i2 = 0; i2 < ((j)object).A[n3].length; ++i2) {
                    if (((j)object).A[n3][i2][2] < 0 || !ae.a(((j)object).A[n3][i2][1], ((j)object).A[n3][i2][2], ((j)object).z[((j)object).A[n3][i2][0]][3], ((j)object).z[((j)object).A[n3][i2][0]][4], ((j)object).s, ((j)object).t, ((j)object).w + 1, ((j)object).x + 1, ((j)object).A[n3][i2][3])) continue;
                    graphics.drawRegion(((j)object).p[((j)object).z[((j)object).A[n3][i2][0]][0]], (int)((j)object).z[((j)object).A[n3][i2][0]][1], (int)((j)object).z[((j)object).A[n3][i2][0]][2], (int)((j)object).z[((j)object).A[n3][i2][0]][3], (int)((j)object).z[((j)object).A[n3][i2][0]][4], f[((j)object).A[n3][i2][3]], (((j)object).A[n3][i2][1] - ((j)object).s) * ((j)object).k - ((j)object).a % ((j)object).k, (((j)object).A[n3][i2][2] - ((j)object).t) * ((j)object).l - ((j)object).b % ((j)object).l, 20);
                }
                break;
            }
        }
    }

    private void b(Graphics graphics, int n2, int n3, int n4, int n5, int n6) {
        while (n3 < n5) {
            for (int i2 = n4; i2 < n6; ++i2) {
                short s2 = this.A[n2][n3][i2];
                if (s2 == -1) continue;
                graphics.drawRegion(this.p[0], (int)this.z[s2][1], (int)this.z[s2][2], (int)this.z[s2][3], (int)this.z[s2][4], 0, n3 * this.k - this.a, i2 * this.l - this.b, 20);
            }
            ++n3;
        }
    }

    private void c(Graphics graphics, int n2, int n3, int n4, int n5, int n6) {
        while (n3 < n5) {
            for (int i2 = n4; i2 < n6; ++i2) {
                short s2 = this.A[n2][n3][i2];
                if (s2 == -1) continue;
                short s3 = (short)(s2 & 0xFFF);
                s2 = (short)f[(s2 & 0x7000) >> 12];
                graphics.drawRegion(this.p[this.z[s3][0]], (int)this.z[s3][1], (int)this.z[s3][2], (int)this.z[s3][3], (int)this.z[s3][4], (int)s2, n3 * this.k - this.a, i2 * this.l - this.b, 20);
            }
            ++n3;
        }
    }

    public final void a(int n2, int n3) {
        this.a = n2 - this.g / 2;
        this.b = n3 - this.h / 2;
        if (this.a + this.g >= this.i * this.k) {
            this.a = this.i * this.k - this.g;
        }
        if (this.a <= 0) {
            this.a = 0;
        }
        if (this.b + this.h >= this.j * this.l) {
            this.b = this.j * this.l - this.h;
        }
        if (this.b <= 0) {
            this.b = 0;
        }
    }

    public final byte b(int n2, int n3) {
        if (this.A == null || this.A[0] == null) {
            return -1;
        }
        int n4 = n2 / this.k;
        int n5 = n3 / this.l;
        if (this.c(n2, n3)) {
            return 1;
        }
        if (n5 < 0) {
            n5 = 0;
        }
        if (n4 < 0) {
            n4 = 0;
        }
        if (n4 > this.i) {
            n4 = this.i;
        }
        if (n5 > this.j) {
            n5 = this.j;
        }
        return (byte)this.A[0][n4][n5];
    }

    public final boolean c(int n2, int n3) {
        return n2 <= 0 || n2 >= this.c || n3 <= 0 || n3 >= this.d;
    }

    static {
        int[] nArray = new int[]{0, 270, 180, 90, 8192, 8462, 8372, 8282};
        f = new int[]{0, 5, 3, 6, 2, 4, 1, 7};
    }
}

