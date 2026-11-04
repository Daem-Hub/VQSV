/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
package a.a;

import a.a.c;
import a.a.d;
import a.b.a;
import a.b.g;
import javax.microedition.lcdui.Graphics;

public final class b
extends a {
    private c[] c;
    private short[] u;
    public byte a;
    private int v = 0;
    private int w = 0;
    private int[] x = new int[]{262, 263, 264, 265, 266, 267, 268, 299, 300, 301, 304, 306, 307, 308, 309};
    public g b = new g();

    public final void a(short[] sArray) {
        this.a = (byte)sArray[0];
        switch (this.a) {
            case 0: {
                this.u = new short[3];
                System.arraycopy(sArray, 0, this.u, 0, this.u.length);
                this.b(sArray[3], (int)sArray[4]);
                this.c = new c[3];
                g g2 = new g();
                for (int i2 = 0; i2 < 2; ++i2) {
                    g2.a((int)sArray[5 + i2 * 3], false);
                    int[] nArray = g2.b(sArray[6 + i2 * 3], (byte)sArray[7 + i2 * 3]);
                    this.c[i2] = new c();
                    this.c[i2] = a.a.d.a(g2, (int)sArray[6 + i2 * 3], nArray, (byte)sArray[7 + i2 * 3], this.c[i2]);
                    g2.a();
                }
                this.c[2] = this.c[0].a();
                return;
            }
            case 1: {
                this.u = new short[sArray.length - 6];
                this.o = (byte)sArray[5];
                System.arraycopy(sArray, 6, this.u, 0, this.u.length);
                this.b(sArray[1], (int)sArray[2]);
                this.c = new c[3];
                g g3 = new g();
                g3.a((int)sArray[3], false);
                int[] nArray = g3.b(sArray[4], (byte)sArray[5]);
                this.c[0] = new c();
                this.c[0] = a.a.d.a(g3, (int)sArray[4], nArray, (byte)sArray[5], this.c[0]);
                g3.a();
                this.c[1] = new c();
                this.c[1].a(a.b.c.e[this.u[2]], 16, 16);
                this.c[2] = this.c[0].a();
                return;
            }
            case 2: {
                return;
            }
            case 3: {
                return;
            }
            case 4: {
                return;
            }
            case 5: {
                return;
            }
            case 6: {
                return;
            }
            case 7: {
                this.u = new short[sArray.length - 6];
                this.o = (byte)sArray[5];
                System.arraycopy(sArray, 6, this.u, 0, this.u.length);
                this.b(sArray[1], (int)sArray[2]);
                this.c = new c[2];
                g g4 = new g();
                g4.a((int)sArray[3], false);
                int[] nArray = g4.b(sArray[4], (byte)sArray[5]);
                this.c[0] = new c();
                this.c[0] = a.a.d.a(g4, (int)sArray[4], nArray, (byte)sArray[5], this.c[0]);
                this.c[1] = this.c[0].a();
                int n2 = nArray[2] * sArray[9] / sArray[10];
                int n3 = nArray[3] * sArray[11] / sArray[12];
                this.v = (nArray[2] - n2) / 2;
                this.w = nArray[3] - n3;
                this.c[1] = a.a.d.a(this.c[1], n2, n3);
                g4.a();
                return;
            }
            case 9: {
                this.u = new short[sArray.length - 10];
                this.o = (byte)sArray[5];
                System.arraycopy(sArray, 10, this.u, 0, this.u.length);
                this.b(sArray[1], (int)sArray[2]);
                this.c = new c[2];
                g g5 = new g();
                g5.a((int)sArray[3], false);
                int[] nArray = g5.b(sArray[4], (byte)sArray[5]);
                this.c[0] = new c();
                this.c[0] = a.a.d.a(g5, (int)sArray[4], nArray, (byte)sArray[5], this.c[0]);
                this.c[1] = this.c[0].a();
                this.c[1] = a.a.d.a(this.c[1], (int)sArray[6], sArray[7], sArray[8], sArray[9]);
                this.c[1] = a.a.d.b(this.c[1], 1, 50);
                g5.a();
                return;
            }
            case 17: {
                this.u = new short[sArray.length - 11];
                this.o = (byte)sArray[5];
                System.arraycopy(sArray, 11, this.u, 0, this.u.length);
                this.b(sArray[1], (int)sArray[2]);
                this.c = new c[2];
                g g6 = new g();
                g6.a((int)sArray[3], false);
                int[] nArray = g6.b(sArray[4], (byte)sArray[5]);
                this.c[0] = new c();
                this.c[0] = a.a.d.a(g6, (int)sArray[4], nArray, (byte)sArray[5], this.c[0]);
                this.c[0] = a.a.d.a(this.c[0], (int)sArray[10]);
                this.c[1] = this.c[0].a();
                this.c[1] = a.a.d.a(this.c[1], (int)sArray[6], sArray[7], sArray[8], sArray[9]);
                g6.a();
                return;
            }
            case 10: {
                this.u = new short[sArray.length - 7];
                this.o = (byte)sArray[5];
                System.arraycopy(sArray, 7, this.u, 0, this.u.length);
                this.b(sArray[1], (int)sArray[2]);
                this.c = new c[2];
                g g7 = new g();
                g7.a((int)sArray[3], false);
                int[] nArray = g7.b(sArray[4], (byte)sArray[5]);
                this.c[0] = new c();
                this.c[0] = a.a.d.a(g7, (int)sArray[4], nArray, (byte)sArray[5], this.c[0]);
                this.c[1] = this.c[0].a();
                this.c[1] = a.a.d.b(this.c[1], sArray[6]);
                g7.a();
                return;
            }
            case 16: {
                this.u = new short[sArray.length - 6];
                this.o = (byte)sArray[5];
                System.arraycopy(sArray, 6, this.u, 0, this.u.length);
                this.b(sArray[1], (int)sArray[2]);
                this.c = new c[1];
                g g8 = new g();
                g8.a((int)sArray[3], false);
                int[] nArray = g8.b(sArray[4], (byte)sArray[5]);
                this.c[0] = new c();
                this.c[0] = a.a.d.a(g8, (int)sArray[4], nArray, (byte)sArray[5], this.c[0]);
                this.u[1] = (short)(this.c[0].c / this.u[2]);
                g8.a();
                return;
            }
            case 8: {
                this.u = new short[sArray.length - 6];
                this.o = (byte)sArray[5];
                System.arraycopy(sArray, 6, this.u, 0, this.u.length);
                this.b(sArray[1], (int)sArray[2]);
                this.c = new c[2];
                g g9 = new g();
                g9.a((int)sArray[3], false);
                int[] nArray = g9.b(sArray[4], (byte)sArray[5]);
                this.c[0] = new c();
                this.c[0] = a.a.d.a(g9, (int)sArray[4], nArray, (byte)sArray[5], this.c[0]);
                g9.a();
                this.c[1] = this.c[0].a();
                if (this.u[4] == 1) {
                    this.c[1] = a.a.d.b(a.a.d.a(this.c[1], (int)this.u[2]), 1, 50);
                    this.c[1].d += this.u[3];
                    this.c[1].e += this.u[4];
                }
                return;
            }
            case 11: 
            case 14: {
                this.u = new short[sArray.length - 7 - (sArray[6] - 1 << 2)];
                this.o = (byte)sArray[5];
                System.arraycopy(sArray, 7 + (sArray[6] - 1 << 2), this.u, 0, this.u.length);
                this.b(sArray[1], (int)sArray[2]);
                this.c = new c[sArray[6]];
                g g10 = new g();
                g10.a((int)sArray[3], false);
                int[] nArray = g10.b(sArray[4], (byte)sArray[5]);
                this.c[0] = new c();
                this.c[0] = a.a.d.a(g10, (int)sArray[4], nArray, (byte)sArray[5], this.c[0]);
                if (sArray[0] == 11) {
                    for (int i3 = 1; i3 < this.c.length; ++i3) {
                        this.c[i3] = this.c[0].a();
                        this.c[i3] = a.a.d.a(this.c[i3], (int)sArray[7 + (i3 - 1 << 2)], sArray[8 + (i3 - 1 << 2)], sArray[9 + (i3 - 1 << 2)], sArray[10 + (i3 - 1 << 2)]);
                    }
                } else {
                    for (int i4 = 1; i4 < this.c.length; ++i4) {
                        this.c[i4] = this.c[0].a();
                        this.c[i4] = a.a.d.b(this.c[i4], sArray[7 + (i4 - 1 << 2)], sArray[8 + (i4 - 1 << 2)]);
                    }
                }
                g10.a();
                return;
            }
            case 12: {
                int n4;
                this.o = (byte)sArray[5];
                this.u = new short[sArray.length - 9];
                System.arraycopy(sArray, 9, this.u, 0, this.u.length);
                this.b(sArray[1], (int)sArray[2]);
                this.c = new c[sArray[6]];
                g g11 = new g();
                g11.a((int)sArray[3], false);
                int[] nArray = g11.b(sArray[4], (byte)sArray[5]);
                this.c[0] = new c();
                this.c[0] = a.a.d.a(g11, (int)sArray[4], nArray, (byte)sArray[5], this.c[0]);
                for (n4 = 1; n4 < this.c.length; ++n4) {
                    this.c[n4] = this.c[0].a();
                }
                for (n4 = 0; n4 < this.c.length; ++n4) {
                    this.c[n4] = a.a.d.b(this.c[n4], sArray[n4 + 7]);
                }
                g11.a();
                return;
            }
            case 13: {
                int n5;
                this.o = (byte)sArray[5];
                this.u = new short[sArray.length - 7 - sArray[6]];
                System.arraycopy(sArray, 7 + sArray[6], this.u, 0, this.u.length);
                this.b(sArray[1], (int)sArray[2]);
                this.c = new c[sArray[6]];
                g g12 = new g();
                g12.a((int)sArray[3], false);
                int[] nArray = g12.b(sArray[4], (byte)sArray[5]);
                this.c[0] = new c();
                this.c[0] = a.a.d.a(g12, (int)sArray[4], nArray, (byte)sArray[5], this.c[0]);
                for (n5 = 1; n5 < this.c.length; ++n5) {
                    this.c[n5] = this.c[0].a();
                }
                for (n5 = 0; n5 < this.c.length; ++n5) {
                    this.c[n5] = a.a.d.b(this.c[n5], sArray[n5 + 7]);
                }
                return;
            }
            case 15: {
                this.o = (byte)sArray[5];
                this.u = new short[sArray.length - 7 - (sArray[6] - 1 << 2)];
                System.arraycopy(sArray, 7 + (sArray[6] - 1 << 2), this.u, 0, this.u.length);
                this.b(sArray[1], (int)sArray[2]);
                this.c = new c[sArray[6]];
                g g13 = new g();
                g13.a((int)sArray[3], false);
                int[] nArray = g13.b(sArray[4], (byte)sArray[5]);
                this.c[0] = new c();
                this.c[0] = a.a.d.a(g13, (int)sArray[4], nArray, (byte)sArray[5], this.c[0]);
                for (int i5 = 1; i5 < this.c.length; ++i5) {
                    this.c[i5] = this.c[0].a();
                    this.c[i5] = a.a.d.a(this.c[i5], (int)sArray[7 + (i5 - 1 << 2)], sArray[8 + (i5 - 1 << 2)], sArray[9 + (i5 - 1 << 2)], sArray[10 + (i5 - 1 << 2)]);
                }
                g13.a();
                return;
            }
        }
        this.o = (byte)sArray[2];
        this.b.a(this.x[this.a - 20], false);
        this.b.a((byte)sArray[1], (byte)0, true);
    }

    private void e() {
        if (this.c != null) {
            for (int i2 = 0; i2 < this.c.length; ++i2) {
                c c2 = this.c[i2];
                this.c[i2].a = null;
                this.c[i2] = null;
            }
            this.c = null;
        }
        if (this.u != null) {
            this.u = null;
        }
    }

    public final void a() {
        this.b(true);
        this.c(true);
    }

    public final void b() {
        this.b(false);
        this.c(false);
    }

    public final boolean a(byte by) {
        return this.a == 8 && this.f;
    }

    public final boolean c() {
        return this.b.f();
    }

    public final boolean a(int n2) {
        return this.b.b(n2);
    }

    public final boolean d() {
        if (!this.f) {
            return false;
        }
        switch (this.a) {
            case 0: {
                if (this.u[1] < this.u[2] / 5) {
                    this.c[2] = this.c[0].a();
                    this.c[2] = this.u[1] % 2 == 1 ? a.a.d.b(a.a.d.a(this.c[2], 6), 5, 1) : a.a.d.b(this.c[2], 2, 1);
                } else if (this.u[1] < (this.u[2] << 2) / 5) {
                    this.c[2] = this.u[1] % 4 == 1 || this.u[1] % 4 == 2 ? this.c[0].a() : this.c[1].a();
                    this.c[2] = this.u[1] % 2 == 1 ? a.a.d.b(a.a.d.a(this.c[2], 8), 8, 1) : a.a.d.b(a.a.d.a(this.c[2], 4), 4, 1);
                } else {
                    this.c[2] = this.c[1].a();
                    this.c[2] = this.u[1] % 2 == 1 ? a.a.d.b(a.a.d.a(this.c[2], 6), 5, 1) : a.a.d.b(this.c[2], 2, 1);
                }
                if (this.u[1] < this.u[2]) {
                    this.u[1] = (short)(this.u[1] + 1);
                    break;
                }
                this.b();
                this.e();
                return false;
            }
            case 1: {
                switch (this.u[4]) {
                    case 0: {
                        int[] nArray = new int[4];
                        for (int i2 = 0; i2 < this.c[1].b; ++i2) {
                            int n2;
                            for (n2 = 0; n2 < 4; ++n2) {
                                nArray[n2] = this.c[1].a[i2 + n2 * this.c[1].b];
                            }
                            for (n2 = 0; n2 < this.c[1].c - 4; ++n2) {
                                this.c[1].a[i2 + n2 * this.c[1].b] = this.c[1].a[i2 + (n2 + 4) * this.c[1].b];
                            }
                            for (n2 = 0; n2 < 4; ++n2) {
                                this.c[1].a[i2 + (n2 + this.c[1].c - 4) * this.c[1].b] = nArray[n2];
                            }
                        }
                        break;
                    }
                    case 1: {
                        int[] nArray = new int[4];
                        for (int i3 = 0; i3 < this.c[1].b; ++i3) {
                            int n3;
                            for (n3 = 0; n3 < 4; ++n3) {
                                nArray[n3] = this.c[1].a[i3 + (this.c[1].c - 4 + n3) * this.c[1].b];
                            }
                            for (n3 = this.c[1].c - 1; n3 > 3; --n3) {
                                this.c[1].a[i3 + n3 * this.c[1].b] = this.c[1].a[i3 + (n3 - 4) * this.c[1].b];
                            }
                            for (n3 = 0; n3 < 4; ++n3) {
                                this.c[1].a[i3 + n3 * this.c[1].b] = nArray[n3];
                            }
                        }
                        break;
                    }
                    case 2: {
                        int[] nArray = new int[4];
                        for (int i4 = 0; i4 < this.c[1].c; ++i4) {
                            int n4;
                            for (n4 = 0; n4 < 4; ++n4) {
                                nArray[n4] = this.c[1].a[i4 * this.c[1].c + n4];
                            }
                            for (n4 = 0; n4 < this.c[1].b - 4; ++n4) {
                                this.c[1].a[i4 * this.c[1].c + n4] = this.c[1].a[i4 * this.c[1].c + n4 + 4];
                            }
                            for (n4 = 0; n4 < 4; ++n4) {
                                this.c[1].a[i4 * this.c[1].c + n4 + this.c[1].b - 4] = nArray[n4];
                            }
                        }
                        break;
                    }
                    case 3: {
                        int[] nArray = new int[4];
                        for (int i5 = 0; i5 < this.c[1].c; ++i5) {
                            int n5;
                            for (n5 = 0; n5 < 4; ++n5) {
                                nArray[n5] = this.c[1].a[i5 * this.c[1].c + this.c[1].b - 4 + n5];
                            }
                            for (n5 = this.c[1].b - 1; n5 > 3; --n5) {
                                this.c[1].a[i5 * this.c[1].c + n5] = this.c[1].a[i5 * this.c[1].c + n5 - 4];
                            }
                            for (n5 = 0; n5 < 4; ++n5) {
                                this.c[1].a[i5 * this.c[1].c + n5] = nArray[n5];
                            }
                        }
                        break;
                    }
                }
                this.c[2] = this.c[0].a();
                this.c[2] = a.a.d.a(this.c[2], this.c[1], (byte)this.u[3]);
                if (this.u[0] < this.u[1]) {
                    this.u[0] = (short)(this.u[0] + 1);
                    break;
                }
                this.b();
                this.e();
                return false;
            }
            case 2: {
                break;
            }
            case 3: {
                break;
            }
            case 4: {
                break;
            }
            case 5: {
                break;
            }
            case 6: {
                break;
            }
            case 7: 
            case 9: 
            case 10: 
            case 16: 
            case 17: {
                if (this.u[0] < this.u[1]) {
                    this.u[0] = (short)(this.u[0] + 1);
                    break;
                }
                this.b();
                this.e();
                return false;
            }
            case 8: {
                if (this.u[0] < this.u[1] / this.u[3] * this.u[2]) {
                    if (this.u[4] == 1) {
                        this.c[1] = this.c[0].a();
                    }
                    this.c[1] = a.a.d.b(a.a.d.a(this.c[1], (int)this.u[5 + (this.u[2] - 1) * 3]), 1, 50);
                    this.c[1].d += this.u[6 + (this.u[2] - 1) * 3];
                    this.c[1].e += this.u[7 + (this.u[2] - 1) * 3];
                } else {
                    this.u[2] = (short)(this.u[2] + 1);
                }
                if (this.u[0] < this.u[1]) {
                    this.u[0] = (short)(this.u[0] + 1);
                    break;
                }
                this.b();
                this.e();
                return false;
            }
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: {
                if (this.u[2] < this.u[3]) {
                    this.u[2] = (short)(this.u[2] + 1);
                    break;
                }
                this.u[2] = 0;
                this.u[0] = (short)(this.u[0] + 1);
                if (this.u[0] < this.u[1]) break;
                this.u[0] = 0;
                this.b();
                this.e();
                return false;
            }
            default: {
                this.b.e();
            }
        }
        return true;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        if (!this.g || !this.h) {
            return;
        }
        switch (this.a) {
            case 0: {
                graphics.drawRGB(this.c[2].a, 0, this.c[2].b, this.j + this.c[2].d, this.k + this.c[2].e, this.c[2].b, this.c[2].c, true);
                return;
            }
            case 1: {
                graphics.drawRGB(this.c[2].a, 0, this.c[2].b, this.j + this.c[2].d, this.k + this.c[2].e, this.c[2].b, this.c[2].c, true);
                return;
            }
            case 2: {
                return;
            }
            case 3: {
                return;
            }
            case 4: {
                return;
            }
            case 5: {
                return;
            }
            case 6: {
                return;
            }
            case 7: {
                if (this.u[0] / this.u[2] % 2 == 0) {
                    graphics.drawRGB(this.c[1].a, 0, this.c[1].b, this.j + this.c[0].d + this.v, this.k + this.c[0].e + this.w, this.c[1].b, this.c[1].c, true);
                    return;
                }
                graphics.drawRGB(this.c[0].a, 0, this.c[0].b, this.j + this.c[0].d, this.k + this.c[0].e, this.c[0].b, this.c[0].c, true);
                return;
            }
            case 9: 
            case 10: {
                graphics.drawRGB(this.c[0].a, 0, this.c[0].b, this.j + this.c[0].d, this.k + this.c[0].e, this.c[0].b, this.c[0].c, true);
                if (this.u[0] / this.u[2] % 2 != 0) break;
                graphics.drawRGB(this.c[1].a, 0, this.c[1].b, this.j + this.c[1].d, this.k + this.c[1].e, this.c[1].b, this.c[1].c, true);
                return;
            }
            case 17: {
                graphics.drawRGB(this.c[0].a, 0, this.c[0].b, this.j + this.c[0].d, this.k + this.c[0].e + this.u[3], this.c[0].b, this.c[0].c, true);
                if (this.u[0] / this.u[2] % 2 != 0) break;
                graphics.drawRGB(this.c[1].a, 0, this.c[1].b, this.j + this.c[1].d, this.k + this.c[1].e + this.u[3], this.c[1].b, this.c[1].c, true);
                return;
            }
            case 16: {
                for (n2 = 0; n2 < this.u[2]; ++n2) {
                    for (n3 = 0; n3 < this.c[0].b * this.u[0]; ++n3) {
                        if (this.c[0].a[n2 * this.u[1] * this.c[0].b + n3] == 0xFFFFFF || this.c[0].a[n2 * this.u[1] * this.c[0].b + n3] == 0) continue;
                        this.c[0].a[n2 * this.u[1] * this.c[0].b + n3] = this.c[0].a[n2 * this.u[1] * this.c[0].b + n3] & 0xFFFFFF;
                    }
                }
                graphics.drawRGB(this.c[0].a, 0, this.c[0].b, this.j + this.c[0].d, this.k + this.c[0].e, this.c[0].b, this.c[0].c, true);
                return;
            }
            case 8: {
                graphics.drawRGB(this.c[1].a, 0, this.c[1].b, this.j + this.c[1].d, this.k + this.c[1].e, this.c[1].b, this.c[1].c, true);
                return;
            }
            case 11: 
            case 14: {
                graphics.setColor(0xFF00FF);
                for (n2 = 1; n2 < this.c.length; ++n2) {
                    if (this.o == 1) {
                        graphics.drawRGB(this.c[n2].a, 0, this.c[n2].b, this.j + this.c[n2].d - this.u[4 + (this.u[0] * (this.c.length - 1) << 1) + (n2 - 1 << 1)], this.k + this.c[n2].e + this.u[4 + (this.u[0] * (this.c.length - 1) << 1) + (n2 - 1 << 1) + 1], this.c[n2].b, this.c[n2].c, true);
                        continue;
                    }
                    graphics.drawRGB(this.c[n2].a, 0, this.c[n2].b, this.j + this.c[n2].d + this.u[4 + (this.u[0] * (this.c.length - 1) << 1) + (n2 - 1 << 1)], this.k + this.c[n2].e + this.u[4 + (this.u[0] * (this.c.length - 1) << 1) + (n2 - 1 << 1) + 1], this.c[n2].b, this.c[n2].c, true);
                }
                return;
            }
            case 12: {
                if (this.o == 1) {
                    graphics.drawRGB(this.c[1].a, 0, this.c[1].b, this.j + this.c[1].d - (this.u[4 + (this.u[1] << 1) + (this.u[0] << 1)] + this.u[4 + (this.u[0] << 1)]), this.k + this.c[1].e - this.u[4 + (this.u[1] << 1) + (this.u[0] << 1) + 1] + this.u[4 + (this.u[0] << 1) + 1], this.c[1].b, this.c[1].c, true);
                    graphics.drawRGB(this.c[0].a, 0, this.c[0].b, this.j + this.c[0].d - this.u[4 + (this.u[0] << 1)], this.k + this.c[0].e + this.u[4 + (this.u[0] << 1) + 1], this.c[0].b, this.c[0].c, true);
                    return;
                }
                graphics.drawRGB(this.c[1].a, 0, this.c[1].b, this.j + this.c[1].d + this.u[4 + (this.u[1] << 1) + (this.u[0] << 1)] + this.u[4 + (this.u[0] << 1)], this.k + this.c[1].e - this.u[4 + (this.u[1] << 1) + (this.u[0] << 1) + 1] + this.u[4 + (this.u[0] << 1) + 1], this.c[1].b, this.c[1].c, true);
                graphics.drawRGB(this.c[0].a, 0, this.c[0].b, this.j + this.c[0].d + this.u[4 + (this.u[0] << 1)], this.k + this.c[0].e + this.u[4 + (this.u[0] << 1) + 1], this.c[0].b, this.c[0].c, true);
                return;
            }
            case 13: {
                for (n2 = 0; n2 < this.c.length; ++n2) {
                    if (this.o == 1) {
                        graphics.drawRGB(this.c[n2].a, 0, this.c[n2].b, this.j + this.c[n2].d - this.u[4 + (this.u[0] * this.c.length << 1) + (n2 << 1)], this.k + this.c[n2].e + this.u[4 + (this.u[0] * this.c.length << 1) + (n2 << 1) + 1], this.c[n2].b, this.c[n2].c, true);
                        continue;
                    }
                    graphics.drawRGB(this.c[n2].a, 0, this.c[n2].b, this.j + this.c[n2].d + this.u[4 + (this.u[0] * this.c.length << 1) + (n2 << 1)], this.k + this.c[n2].e + this.u[4 + (this.u[0] * this.c.length << 1) + (n2 << 1) + 1], this.c[n2].b, this.c[n2].c, true);
                }
                return;
            }
            case 15: {
                n2 = 4 + this.u[0] * 3;
                if (this.o == 1) {
                    graphics.drawRGB(this.c[this.u[n2]].a, 0, this.c[this.u[n2]].b, this.j + this.c[this.u[n2]].d - this.u[n2 + 1], this.k + this.c[this.u[n2]].e + this.u[n2 + 2], this.c[this.u[n2]].b, this.c[this.u[n2]].c, true);
                    return;
                }
                graphics.drawRGB(this.c[this.u[n2]].a, 0, this.c[this.u[n2]].b, this.j + this.c[this.u[n2]].d + this.u[n2 + 1], this.k + this.c[this.u[n2]].e + this.u[n2 + 2], this.c[this.u[n2]].b, this.c[this.u[n2]].c, true);
                return;
            }
            default: {
                this.b.a(graphics, this.j, this.k, this.o);
            }
        }
    }
}

