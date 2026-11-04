/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
package a.b;

import a.a;
import a.b.c;
import a.e;
import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class d {
    private static d e;
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
    public int c;
    public int d;
    private int q;
    private int r;
    private int s;
    private int t;
    private byte u;
    private short[][] v;
    private short[][][] w;
    private byte[] x = null;

    public static d a() {
        if (e == null) {
            e = new d();
        }
        return e;
    }

    public d() {
        this.h = a.a.h();
        this.g = a.a.g();
    }

    public final void b() {
        this.w = null;
        this.x = null;
        this.v = null;
    }

    public final void a(int n2) {
        this.m = n2;
        d d2 = this;
        try {
            d2.getClass();
            InputStream inputStream = b.a("/data/map/map_" + d2.m + ".mid");
            inputStream = new DataInputStream(inputStream);
            byte by = ((DataInputStream)inputStream).readByte();
            d2.o = d2.n;
            d2.n = ((DataInputStream)inputStream).readByte();
            d2.e();
            d2.i = by == 1 ? (short)((DataInputStream)inputStream).readByte() : ((DataInputStream)inputStream).readShort();
            d2.j = by == 1 ? (short)((DataInputStream)inputStream).readByte() : ((DataInputStream)inputStream).readShort();
            d2.l = d2.k = (short)((DataInputStream)inputStream).readByte();
            d2.c = d2.i * d2.k;
            d2.d = d2.j * d2.l;
            d2.u = ((DataInputStream)inputStream).readByte();
            d2.x = new byte[d2.u];
            d2.w = new short[d2.u][][];
            for (int i2 = 0; i2 < d2.u; ++i2) {
                int n3;
                int n4;
                byte by2 = ((DataInputStream)inputStream).readByte();
                d2.x[i2] = ((DataInputStream)inputStream).readByte();
                short s = ((DataInputStream)inputStream).readShort();
                if (d2.x[by2] == 0 || d2.x[by2] == 1) {
                    d2.w[by2] = new short[d2.i][d2.j];
                    for (n4 = 0; n4 < d2.i; ++n4) {
                        for (n3 = 0; n3 < d2.j; ++n3) {
                            d2.w[by2][n4][n3] = -1;
                        }
                    }
                } else {
                    d2.w[by2] = new short[s][4];
                }
                for (n4 = 0; n4 < s; ++n4) {
                    short s2;
                    if (by == 1) {
                        n3 = ((DataInputStream)inputStream).readByte();
                        s2 = ((DataInputStream)inputStream).readByte();
                    } else {
                        n3 = ((DataInputStream)inputStream).readShort();
                        s2 = ((DataInputStream)inputStream).readShort();
                    }
                    short s3 = ((DataInputStream)inputStream).readShort();
                    if (d2.x[i2] == 1) {
                        d2.w[i2][n3][s2] = s3;
                        continue;
                    }
                    if (d2.x[by2] == 0) {
                        d2.w[i2][n3][s2] = (short)(s3 & 0xFFF);
                        continue;
                    }
                    d2.w[i2][n4][1] = n3;
                    d2.w[i2][n4][2] = s2;
                    d2.w[i2][n4][0] = (short)(s3 & 0xFFF);
                    d2.w[i2][n4][3] = (short)((s3 & 0x7000) >> 12);
                }
            }
            ((FilterInputStream)inputStream).close();
            return;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
    }

    private void e() {
        int n2;
        if (this.p != null) {
            if (game.e.k != 0) {
                for (n2 = 0; n2 < this.p.length; ++n2) {
                    if (this.p[n2] == null) continue;
                    this.p[n2] = null;
                }
                this.p = null;
            } else {
                for (n2 = 0; n2 < this.p.length; ++n2) {
                    for (int i2 = 0; i2 < a.b.c.b[this.n].length; ++i2) {
                        if (a.b.c.b[this.o][n2] != a.b.c.b[this.n][i2]) continue;
                        a.b.c.c(a.b.c.b[this.o][n2]);
                        this.p[n2] = null;
                        break;
                    }
                    if (this.p[n2] == null) continue;
                    a.b.c.d(a.b.c.b[this.o][n2]);
                    this.p[n2] = null;
                }
                this.p = null;
            }
        }
        this.p = new Image[a.b.c.b[this.n].length];
        for (n2 = 0; n2 < this.p.length; ++n2) {
            this.p[n2] = game.e.k == 1 ? a.a.d.a(a.b.c.b(a.b.c.b[this.n][n2])) : a.b.c.b(a.b.c.b[this.n][n2]);
        }
        try {
            "".getClass();
            InputStream inputStream = b.a("/data/mod/mod_" + this.n + ".mid");
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            int n3 = dataInputStream.readShort();
            this.v = new short[n3][5];
            for (int i3 = 0; i3 < n3; ++i3) {
                this.v[i3][0] = dataInputStream.readByte();
                this.v[i3][1] = dataInputStream.readShort();
                this.v[i3][2] = dataInputStream.readShort();
                this.v[i3][3] = dataInputStream.readShort();
                this.v[i3][4] = dataInputStream.readShort();
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
        int n2;
        for (n2 = 0; n2 < this.p.length; ++n2) {
            if (game.e.k == 1) {
                this.p[n2] = a.a.d.a(a.b.c.b(a.b.c.b[this.n][n2]));
                continue;
            }
            a.b.c.d(a.b.c.b[this.n][n2]);
        }
        for (n2 = 0; n2 < this.p.length; ++n2) {
            if (game.e.k != 0) continue;
            this.p[n2] = a.b.c.b(a.b.c.b[this.n][n2]);
        }
    }

    public final void d() {
        if (this.k == 0) {
            return;
        }
        this.r = this.b / this.l;
        this.q = this.a / this.k;
        this.t = this.h / this.l + 1;
        this.s = this.g / this.k + 1;
        if (this.r + this.t >= this.j) {
            this.t = this.j - 1 - this.r;
        }
        if (this.s + this.q >= this.i) {
            this.s = this.i - 1 - this.q;
        }
    }

    public final void a(Graphics object, int n2) {
        switch (this.x[n2]) {
            case 0: {
                int n3 = n2;
                Graphics graphics = object;
                object = this;
                for (int i2 = 0; i2 <= object.s; ++i2) {
                    for (int i3 = 0; i3 <= object.t; ++i3) {
                        short s = object.w[n3][object.q + i2][object.r + i3];
                        if (s == -1) continue;
                        graphics.drawRegion(object.p[0], (int)object.v[s][1], (int)object.v[s][2], (int)object.v[s][3], (int)object.v[s][4], 0, i2 * object.k - object.a % object.k, i3 * object.l - object.b % object.l, 20);
                    }
                }
                return;
            }
            case 1: {
                int n4 = n2;
                Graphics graphics = object;
                object = this;
                for (int i4 = 0; i4 <= object.s; ++i4) {
                    for (int i5 = 0; i5 <= object.t; ++i5) {
                        short s = object.w[n4][object.q + i4][object.r + i5];
                        if (s == -1) continue;
                        short s2 = (short)(s & 0xFFF);
                        s = (short)f[(s & 0x7000) >> 12];
                        graphics.drawRegion(object.p[object.v[s2][0]], (int)object.v[s2][1], (int)object.v[s2][2], (int)object.v[s2][3], (int)object.v[s2][4], (int)s, i4 * object.k - object.a % object.k, i5 * object.l - object.b % object.l, 20);
                    }
                }
                return;
            }
            case 2: 
            case 3: 
            case 4: {
                int n5 = n2;
                Graphics graphics = object;
                object = this;
                for (int i6 = 0; i6 < object.w[n5].length; ++i6) {
                    if (object.w[n5][i6][2] < 0 || !a.e.a(object.w[n5][i6][1], object.w[n5][i6][2], (int)object.v[object.w[n5][i6][0]][3], (int)object.v[object.w[n5][i6][0]][4], object.q, object.r, object.s + 1, object.t + 1, (int)object.w[n5][i6][3])) continue;
                    graphics.drawRegion(object.p[object.v[object.w[n5][i6][0]][0]], (int)object.v[object.w[n5][i6][0]][1], (int)object.v[object.w[n5][i6][0]][2], (int)object.v[object.w[n5][i6][0]][3], (int)object.v[object.w[n5][i6][0]][4], f[object.w[n5][i6][3]], (object.w[n5][i6][1] - object.q) * object.k - object.a % object.k, (object.w[n5][i6][2] - object.r) * object.l - object.b % object.l, 20);
                }
                break;
            }
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

    public final byte a(int n2, int n3, int n4) {
        if (this.w == null || this.w[0] == null) {
            return -1;
        }
        n2 = n3 / this.k;
        int n5 = n4 / this.l;
        if (this.b(n3, n4)) {
            return 1;
        }
        if (n5 < 0) {
            n5 = 0;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 > this.i) {
            n2 = this.i;
        }
        if (n5 > this.j) {
            n5 = this.j;
        }
        return (byte)this.w[0][n2][n5];
    }

    public final boolean b(int n2, int n3) {
        return n2 <= 0 || n2 >= this.c || n3 <= 0 || n3 >= this.d;
    }

    static {
        f = new int[]{0, 5, 3, 6, 2, 4, 1, 7};
    }
}

