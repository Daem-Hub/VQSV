/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class ah
extends n {
    private e[] b;
    private short[] t;
    private byte u;
    private int v = 0;
    private int w = 0;
    private int[] x = new int[]{262, 263, 264, 265, 266, 267, 268, 299, 300, 301, 304, 306, 307, 308, 309};
    public d a = new d();

    public final void a(short[] sArray) {
        this.u = (byte)sArray[0];
        switch (this.u) {
            case 0: {
                this.t = new short[3];
                System.arraycopy(sArray, 0, this.t, 0, this.t.length);
                this.b(sArray[3], sArray[4]);
                this.b = new e[3];
                d d2 = new d();
                for (int i2 = 0; i2 < 2; ++i2) {
                    d2.a((int)sArray[5 + i2 * 3], false);
                    int[] nArray = d2.b(sArray[6 + i2 * 3], (byte)sArray[7 + i2 * 3]);
                    this.b[i2] = new e();
                    this.b[i2] = l.a(d2, (int)sArray[6 + i2 * 3], nArray, (byte)sArray[7 + i2 * 3], this.b[i2]);
                    d2.a();
                }
                this.b[2] = this.b[0].a();
                return;
            }
            case 1: {
                this.t = new short[sArray.length - 6];
                this.n = (byte)sArray[5];
                System.arraycopy(sArray, 6, this.t, 0, this.t.length);
                this.b(sArray[1], sArray[2]);
                this.b = new e[3];
                d d3 = new d();
                d3.a((int)sArray[3], false);
                int[] nArray = d3.b(sArray[4], (byte)sArray[5]);
                this.b[0] = new e();
                this.b[0] = l.a(d3, (int)sArray[4], nArray, (byte)sArray[5], this.b[0]);
                d3.a();
                this.b[1] = new e();
                this.b[1].a(aq.e[this.t[2]], 16, 16);
                this.b[2] = this.b[0].a();
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
                this.t = new short[sArray.length - 6];
                this.n = (byte)sArray[5];
                System.arraycopy(sArray, 6, this.t, 0, this.t.length);
                this.b(sArray[1], sArray[2]);
                this.b = new e[2];
                d d4 = new d();
                d4.a((int)sArray[3], false);
                int[] nArray = d4.b(sArray[4], (byte)sArray[5]);
                this.b[0] = new e();
                this.b[0] = l.a(d4, (int)sArray[4], nArray, (byte)sArray[5], this.b[0]);
                this.b[1] = this.b[0].a();
                int n2 = nArray[2] * sArray[9] / sArray[10];
                int n3 = nArray[3] * sArray[11] / sArray[12];
                this.v = (nArray[2] - n2) / 2;
                this.w = nArray[3] - n3;
                this.b[1] = l.a(this.b[1], n2, n3);
                d4.a();
                return;
            }
            case 9: {
                this.t = new short[sArray.length - 10];
                this.n = (byte)sArray[5];
                System.arraycopy(sArray, 10, this.t, 0, this.t.length);
                this.b(sArray[1], sArray[2]);
                this.b = new e[2];
                d d5 = new d();
                d5.a((int)sArray[3], false);
                int[] nArray = d5.b(sArray[4], (byte)sArray[5]);
                this.b[0] = new e();
                this.b[0] = l.a(d5, (int)sArray[4], nArray, (byte)sArray[5], this.b[0]);
                this.b[1] = this.b[0].a();
                this.b[1] = l.a(this.b[1], (int)sArray[6], sArray[7], sArray[8], sArray[9]);
                this.b[1] = l.b(this.b[1], 1, 50);
                d5.a();
                return;
            }
            case 17: {
                this.t = new short[sArray.length - 11];
                this.n = (byte)sArray[5];
                System.arraycopy(sArray, 11, this.t, 0, this.t.length);
                this.b(sArray[1], sArray[2]);
                this.b = new e[2];
                d d6 = new d();
                d6.a((int)sArray[3], false);
                int[] nArray = d6.b(sArray[4], (byte)sArray[5]);
                this.b[0] = new e();
                this.b[0] = l.a(d6, (int)sArray[4], nArray, (byte)sArray[5], this.b[0]);
                this.b[0] = l.a(this.b[0], sArray[10]);
                this.b[1] = this.b[0].a();
                this.b[1] = l.a(this.b[1], (int)sArray[6], sArray[7], sArray[8], sArray[9]);
                d6.a();
                return;
            }
            case 10: {
                this.t = new short[sArray.length - 7];
                this.n = (byte)sArray[5];
                System.arraycopy(sArray, 7, this.t, 0, this.t.length);
                this.b(sArray[1], sArray[2]);
                this.b = new e[2];
                d d7 = new d();
                d7.a((int)sArray[3], false);
                int[] nArray = d7.b(sArray[4], (byte)sArray[5]);
                this.b[0] = new e();
                this.b[0] = l.a(d7, (int)sArray[4], nArray, (byte)sArray[5], this.b[0]);
                this.b[1] = this.b[0].a();
                this.b[1] = l.b(this.b[1], sArray[6]);
                d7.a();
                return;
            }
            case 16: {
                this.t = new short[sArray.length - 6];
                this.n = (byte)sArray[5];
                System.arraycopy(sArray, 6, this.t, 0, this.t.length);
                this.b(sArray[1], sArray[2]);
                this.b = new e[1];
                d d8 = new d();
                d8.a((int)sArray[3], false);
                int[] nArray = d8.b(sArray[4], (byte)sArray[5]);
                this.b[0] = new e();
                this.b[0] = l.a(d8, (int)sArray[4], nArray, (byte)sArray[5], this.b[0]);
                this.t[1] = (short)(this.b[0].c / this.t[2]);
                d8.a();
                return;
            }
            case 8: {
                this.t = new short[sArray.length - 6];
                this.n = (byte)sArray[5];
                System.arraycopy(sArray, 6, this.t, 0, this.t.length);
                this.b(sArray[1], sArray[2]);
                this.b = new e[2];
                d d9 = new d();
                d9.a((int)sArray[3], false);
                int[] nArray = d9.b(sArray[4], (byte)sArray[5]);
                this.b[0] = new e();
                this.b[0] = l.a(d9, (int)sArray[4], nArray, (byte)sArray[5], this.b[0]);
                d9.a();
                this.b[1] = this.b[0].a();
                if (this.t[4] == 1) {
                    this.b[1] = l.b(l.a(this.b[1], this.t[2]), 1, 50);
                    this.b[1].d += this.t[3];
                    this.b[1].e += this.t[4];
                }
                return;
            }
            case 11: 
            case 14: {
                this.t = new short[sArray.length - 7 - (sArray[6] - 1 << 2)];
                this.n = (byte)sArray[5];
                System.arraycopy(sArray, 7 + (sArray[6] - 1 << 2), this.t, 0, this.t.length);
                this.b(sArray[1], sArray[2]);
                this.b = new e[sArray[6]];
                d d10 = new d();
                d10.a((int)sArray[3], false);
                int[] nArray = d10.b(sArray[4], (byte)sArray[5]);
                this.b[0] = new e();
                this.b[0] = l.a(d10, (int)sArray[4], nArray, (byte)sArray[5], this.b[0]);
                if (sArray[0] == 11) {
                    for (int i3 = 1; i3 < this.b.length; ++i3) {
                        this.b[i3] = this.b[0].a();
                        this.b[i3] = l.a(this.b[i3], (int)sArray[7 + (i3 - 1 << 2)], sArray[8 + (i3 - 1 << 2)], sArray[9 + (i3 - 1 << 2)], sArray[10 + (i3 - 1 << 2)]);
                    }
                } else {
                    for (int i4 = 1; i4 < this.b.length; ++i4) {
                        this.b[i4] = this.b[0].a();
                        this.b[i4] = l.b(this.b[i4], sArray[7 + (i4 - 1 << 2)], sArray[8 + (i4 - 1 << 2)]);
                    }
                }
                d10.a();
                return;
            }
            case 12: {
                int n4;
                this.n = (byte)sArray[5];
                this.t = new short[sArray.length - 9];
                System.arraycopy(sArray, 9, this.t, 0, this.t.length);
                this.b(sArray[1], sArray[2]);
                this.b = new e[sArray[6]];
                d d11 = new d();
                d11.a((int)sArray[3], false);
                int[] nArray = d11.b(sArray[4], (byte)sArray[5]);
                this.b[0] = new e();
                this.b[0] = l.a(d11, (int)sArray[4], nArray, (byte)sArray[5], this.b[0]);
                for (n4 = 1; n4 < this.b.length; ++n4) {
                    this.b[n4] = this.b[0].a();
                }
                for (n4 = 0; n4 < this.b.length; ++n4) {
                    this.b[n4] = l.b(this.b[n4], sArray[n4 + 7]);
                }
                d11.a();
                return;
            }
            case 13: {
                int n5;
                this.n = (byte)sArray[5];
                this.t = new short[sArray.length - 7 - sArray[6]];
                System.arraycopy(sArray, 7 + sArray[6], this.t, 0, this.t.length);
                this.b(sArray[1], sArray[2]);
                this.b = new e[sArray[6]];
                d d12 = new d();
                d12.a((int)sArray[3], false);
                int[] nArray = d12.b(sArray[4], (byte)sArray[5]);
                this.b[0] = new e();
                this.b[0] = l.a(d12, (int)sArray[4], nArray, (byte)sArray[5], this.b[0]);
                for (n5 = 1; n5 < this.b.length; ++n5) {
                    this.b[n5] = this.b[0].a();
                }
                for (n5 = 0; n5 < this.b.length; ++n5) {
                    this.b[n5] = l.b(this.b[n5], sArray[n5 + 7]);
                }
                return;
            }
            case 15: {
                this.n = (byte)sArray[5];
                this.t = new short[sArray.length - 7 - (sArray[6] - 1 << 2)];
                System.arraycopy(sArray, 7 + (sArray[6] - 1 << 2), this.t, 0, this.t.length);
                this.b(sArray[1], sArray[2]);
                this.b = new e[sArray[6]];
                d d13 = new d();
                d13.a((int)sArray[3], false);
                int[] nArray = d13.b(sArray[4], (byte)sArray[5]);
                this.b[0] = new e();
                this.b[0] = l.a(d13, (int)sArray[4], nArray, (byte)sArray[5], this.b[0]);
                for (int i5 = 1; i5 < this.b.length; ++i5) {
                    this.b[i5] = this.b[0].a();
                    this.b[i5] = l.a(this.b[i5], (int)sArray[7 + (i5 - 1 << 2)], sArray[8 + (i5 - 1 << 2)], sArray[9 + (i5 - 1 << 2)], sArray[10 + (i5 - 1 << 2)]);
                }
                d13.a();
                return;
            }
        }
        this.n = (byte)sArray[2];
        this.a.a(this.x[this.u - 20], false);
        this.a.a((byte)sArray[1], (byte)0, true);
    }

    private void f() {
        if (this.b != null) {
            for (int i2 = 0; i2 < this.b.length; ++i2) {
                this.b[i2].a = null;
                this.b[i2] = null;
            }
            this.b = null;
        }
        if (this.t != null) {
            this.t = null;
        }
    }

    public final void a() {
        this.a(true);
        this.b(true);
    }

    public final void b() {
        this.a(false);
        this.b(false);
    }

    public final boolean c() {
        return this.u == 8 && this.e;
    }

    public final boolean d() {
        return this.a.e();
    }

    public final boolean a(int n2) {
        return this.a.b(n2);
    }

    public final boolean e() {
        if (!this.e) {
            return false;
        }
        switch (this.u) {
            case 0: {
                if (this.t[1] < this.t[2] / 5) {
                    this.b[2] = this.b[0].a();
                    this.b[2] = this.t[1] % 2 == 1 ? l.b(l.a(this.b[2], 6), 5, 1) : l.b(this.b[2], 2, 1);
                } else if (this.t[1] < (this.t[2] << 2) / 5) {
                    this.b[2] = this.t[1] % 4 == 1 || this.t[1] % 4 == 2 ? this.b[0].a() : this.b[1].a();
                    this.b[2] = this.t[1] % 2 == 1 ? l.b(l.a(this.b[2], 8), 8, 1) : l.b(l.a(this.b[2], 4), 4, 1);
                } else {
                    this.b[2] = this.b[1].a();
                    this.b[2] = this.t[1] % 2 == 1 ? l.b(l.a(this.b[2], 6), 5, 1) : l.b(this.b[2], 2, 1);
                }
                if (this.t[1] < this.t[2]) {
                    this.t[1] = (short)(this.t[1] + 1);
                    break;
                }
                this.b();
                this.f();
                return false;
            }
            case 1: {
                switch (this.t[4]) {
                    case 0: {
                        int[] nArray = new int[4];
                        for (int i2 = 0; i2 < this.b[1].b; ++i2) {
                            int n2;
                            for (n2 = 0; n2 < 4; ++n2) {
                                nArray[n2] = this.b[1].a[i2 + n2 * this.b[1].b];
                            }
                            for (n2 = 0; n2 < this.b[1].c - 4; ++n2) {
                                this.b[1].a[i2 + n2 * this.b[1].b] = this.b[1].a[i2 + (n2 + 4) * this.b[1].b];
                            }
                            for (n2 = 0; n2 < 4; ++n2) {
                                this.b[1].a[i2 + (n2 + this.b[1].c - 4) * this.b[1].b] = nArray[n2];
                            }
                        }
                        break;
                    }
                    case 1: {
                        int[] nArray = new int[4];
                        for (int i3 = 0; i3 < this.b[1].b; ++i3) {
                            int n3;
                            for (n3 = 0; n3 < 4; ++n3) {
                                nArray[n3] = this.b[1].a[i3 + (this.b[1].c - 4 + n3) * this.b[1].b];
                            }
                            for (n3 = this.b[1].c - 1; n3 > 3; --n3) {
                                this.b[1].a[i3 + n3 * this.b[1].b] = this.b[1].a[i3 + (n3 - 4) * this.b[1].b];
                            }
                            for (n3 = 0; n3 < 4; ++n3) {
                                this.b[1].a[i3 + n3 * this.b[1].b] = nArray[n3];
                            }
                        }
                        break;
                    }
                    case 2: {
                        int[] nArray = new int[4];
                        for (int i4 = 0; i4 < this.b[1].c; ++i4) {
                            int n4;
                            for (n4 = 0; n4 < 4; ++n4) {
                                nArray[n4] = this.b[1].a[i4 * this.b[1].c + n4];
                            }
                            for (n4 = 0; n4 < this.b[1].b - 4; ++n4) {
                                this.b[1].a[i4 * this.b[1].c + n4] = this.b[1].a[i4 * this.b[1].c + n4 + 4];
                            }
                            for (n4 = 0; n4 < 4; ++n4) {
                                this.b[1].a[i4 * this.b[1].c + n4 + this.b[1].b - 4] = nArray[n4];
                            }
                        }
                        break;
                    }
                    case 3: {
                        int[] nArray = new int[4];
                        for (int i5 = 0; i5 < this.b[1].c; ++i5) {
                            int n5;
                            for (n5 = 0; n5 < 4; ++n5) {
                                nArray[n5] = this.b[1].a[i5 * this.b[1].c + this.b[1].b - 4 + n5];
                            }
                            for (n5 = this.b[1].b - 1; n5 > 3; --n5) {
                                this.b[1].a[i5 * this.b[1].c + n5] = this.b[1].a[i5 * this.b[1].c + n5 - 4];
                            }
                            for (n5 = 0; n5 < 4; ++n5) {
                                this.b[1].a[i5 * this.b[1].c + n5] = nArray[n5];
                            }
                        }
                        break;
                    }
                }
                this.b[2] = this.b[0].a();
                this.b[2] = l.a(this.b[2], this.b[1], (byte)this.t[3]);
                if (this.t[0] < this.t[1]) {
                    this.t[0] = (short)(this.t[0] + 1);
                    break;
                }
                this.b();
                this.f();
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
                if (this.t[0] < this.t[1]) {
                    this.t[0] = (short)(this.t[0] + 1);
                    break;
                }
                this.b();
                this.f();
                return false;
            }
            case 8: {
                if (this.t[0] < this.t[1] / this.t[3] * this.t[2]) {
                    if (this.t[4] == 1) {
                        this.b[1] = this.b[0].a();
                    }
                    this.b[1] = l.b(l.a(this.b[1], this.t[5 + (this.t[2] - 1) * 3]), 1, 50);
                    this.b[1].d += this.t[6 + (this.t[2] - 1) * 3];
                    this.b[1].e += this.t[7 + (this.t[2] - 1) * 3];
                } else {
                    this.t[2] = (short)(this.t[2] + 1);
                }
                if (this.t[0] < this.t[1]) {
                    this.t[0] = (short)(this.t[0] + 1);
                    break;
                }
                this.b();
                this.f();
                return false;
            }
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: {
                if (this.t[2] < this.t[3]) {
                    this.t[2] = (short)(this.t[2] + 1);
                    break;
                }
                this.t[2] = 0;
                this.t[0] = (short)(this.t[0] + 1);
                if (this.t[0] < this.t[1]) break;
                this.t[0] = 0;
                this.b();
                this.f();
                return false;
            }
            default: {
                this.a.d();
            }
        }
        return true;
    }

    public final void a(Graphics graphics) {
        if (!this.f || !this.g) {
            return;
        }
        switch (this.u) {
            case 0: {
                graphics.drawRGB(this.b[2].a, 0, this.b[2].b, this.i + this.b[2].d, this.j + this.b[2].e, this.b[2].b, this.b[2].c, true);
                return;
            }
            case 1: {
                graphics.drawRGB(this.b[2].a, 0, this.b[2].b, this.i + this.b[2].d, this.j + this.b[2].e, this.b[2].b, this.b[2].c, true);
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
                if (this.t[0] / this.t[2] % 2 == 0) {
                    graphics.drawRGB(this.b[1].a, 0, this.b[1].b, this.i + this.b[0].d + this.v, this.j + this.b[0].e + this.w, this.b[1].b, this.b[1].c, true);
                    return;
                }
                graphics.drawRGB(this.b[0].a, 0, this.b[0].b, this.i + this.b[0].d, this.j + this.b[0].e, this.b[0].b, this.b[0].c, true);
                return;
            }
            case 9: 
            case 10: {
                graphics.drawRGB(this.b[0].a, 0, this.b[0].b, this.i + this.b[0].d, this.j + this.b[0].e, this.b[0].b, this.b[0].c, true);
                if (this.t[0] / this.t[2] % 2 != 0) break;
                graphics.drawRGB(this.b[1].a, 0, this.b[1].b, this.i + this.b[1].d, this.j + this.b[1].e, this.b[1].b, this.b[1].c, true);
                return;
            }
            case 17: {
                graphics.drawRGB(this.b[0].a, 0, this.b[0].b, this.i + this.b[0].d, this.j + this.b[0].e + this.t[3], this.b[0].b, this.b[0].c, true);
                if (this.t[0] / this.t[2] % 2 != 0) break;
                graphics.drawRGB(this.b[1].a, 0, this.b[1].b, this.i + this.b[1].d, this.j + this.b[1].e + this.t[3], this.b[1].b, this.b[1].c, true);
                return;
            }
            case 16: {
                for (int i2 = 0; i2 < this.t[2]; ++i2) {
                    for (int i3 = 0; i3 < this.b[0].b * this.t[0]; ++i3) {
                        if (this.b[0].a[i2 * this.t[1] * this.b[0].b + i3] == 0xFFFFFF || this.b[0].a[i2 * this.t[1] * this.b[0].b + i3] == 0) continue;
                        this.b[0].a[i2 * this.t[1] * this.b[0].b + i3] = this.b[0].a[i2 * this.t[1] * this.b[0].b + i3] & 0xFFFFFF;
                    }
                }
                graphics.drawRGB(this.b[0].a, 0, this.b[0].b, this.i + this.b[0].d, this.j + this.b[0].e, this.b[0].b, this.b[0].c, true);
                return;
            }
            case 8: {
                graphics.drawRGB(this.b[1].a, 0, this.b[1].b, this.i + this.b[1].d, this.j + this.b[1].e, this.b[1].b, this.b[1].c, true);
                return;
            }
            case 11: 
            case 14: {
                graphics.setColor(0xFF00FF);
                for (int i4 = 1; i4 < this.b.length; ++i4) {
                    if (this.n == 1) {
                        graphics.drawRGB(this.b[i4].a, 0, this.b[i4].b, this.i + this.b[i4].d - this.t[4 + (this.t[0] * (this.b.length - 1) << 1) + (i4 - 1 << 1)], this.j + this.b[i4].e + this.t[4 + (this.t[0] * (this.b.length - 1) << 1) + (i4 - 1 << 1) + 1], this.b[i4].b, this.b[i4].c, true);
                        continue;
                    }
                    graphics.drawRGB(this.b[i4].a, 0, this.b[i4].b, this.i + this.b[i4].d + this.t[4 + (this.t[0] * (this.b.length - 1) << 1) + (i4 - 1 << 1)], this.j + this.b[i4].e + this.t[4 + (this.t[0] * (this.b.length - 1) << 1) + (i4 - 1 << 1) + 1], this.b[i4].b, this.b[i4].c, true);
                }
                return;
            }
            case 12: {
                if (this.n == 1) {
                    graphics.drawRGB(this.b[1].a, 0, this.b[1].b, this.i + this.b[1].d - (this.t[4 + (this.t[1] << 1) + (this.t[0] << 1)] + this.t[4 + (this.t[0] << 1)]), this.j + this.b[1].e - this.t[4 + (this.t[1] << 1) + (this.t[0] << 1) + 1] + this.t[4 + (this.t[0] << 1) + 1], this.b[1].b, this.b[1].c, true);
                    graphics.drawRGB(this.b[0].a, 0, this.b[0].b, this.i + this.b[0].d - this.t[4 + (this.t[0] << 1)], this.j + this.b[0].e + this.t[4 + (this.t[0] << 1) + 1], this.b[0].b, this.b[0].c, true);
                    return;
                }
                graphics.drawRGB(this.b[1].a, 0, this.b[1].b, this.i + this.b[1].d + this.t[4 + (this.t[1] << 1) + (this.t[0] << 1)] + this.t[4 + (this.t[0] << 1)], this.j + this.b[1].e - this.t[4 + (this.t[1] << 1) + (this.t[0] << 1) + 1] + this.t[4 + (this.t[0] << 1) + 1], this.b[1].b, this.b[1].c, true);
                graphics.drawRGB(this.b[0].a, 0, this.b[0].b, this.i + this.b[0].d + this.t[4 + (this.t[0] << 1)], this.j + this.b[0].e + this.t[4 + (this.t[0] << 1) + 1], this.b[0].b, this.b[0].c, true);
                return;
            }
            case 13: {
                for (int i5 = 0; i5 < this.b.length; ++i5) {
                    if (this.n == 1) {
                        graphics.drawRGB(this.b[i5].a, 0, this.b[i5].b, this.i + this.b[i5].d - this.t[4 + (this.t[0] * this.b.length << 1) + (i5 << 1)], this.j + this.b[i5].e + this.t[4 + (this.t[0] * this.b.length << 1) + (i5 << 1) + 1], this.b[i5].b, this.b[i5].c, true);
                        continue;
                    }
                    graphics.drawRGB(this.b[i5].a, 0, this.b[i5].b, this.i + this.b[i5].d + this.t[4 + (this.t[0] * this.b.length << 1) + (i5 << 1)], this.j + this.b[i5].e + this.t[4 + (this.t[0] * this.b.length << 1) + (i5 << 1) + 1], this.b[i5].b, this.b[i5].c, true);
                }
                return;
            }
            case 15: {
                int n2 = 4 + this.t[0] * 3;
                if (this.n == 1) {
                    graphics.drawRGB(this.b[this.t[n2]].a, 0, this.b[this.t[n2]].b, this.i + this.b[this.t[n2]].d - this.t[n2 + 1], this.j + this.b[this.t[n2]].e + this.t[n2 + 2], this.b[this.t[n2]].b, this.b[this.t[n2]].c, true);
                    return;
                }
                graphics.drawRGB(this.b[this.t[n2]].a, 0, this.b[this.t[n2]].b, this.i + this.b[this.t[n2]].d + this.t[n2 + 1], this.j + this.b[this.t[n2]].e + this.t[n2 + 2], this.b[this.t[n2]].b, this.b[this.t[n2]].c, true);
                return;
            }
            default: {
                this.a.a(graphics, this.i, this.j, this.n);
            }
        }
    }
}

