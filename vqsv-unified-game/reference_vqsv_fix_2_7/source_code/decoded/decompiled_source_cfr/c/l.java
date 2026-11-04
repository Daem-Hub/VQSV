/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
package c;

import a.e;
import c.f;
import c.i;
import c.k;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class l {
    public int a = 0;
    public int[] b = null;
    public int[][][] c;
    public int d = 0;
    private k[] p;
    public int e = 0;
    public int f = 0;
    public boolean g = true;
    public int h = 0;
    public int i = 0;
    public int j;
    public int k = 0;
    public int l = 10;
    public int m = 2;
    private int q = -1;
    public int[][] n = null;
    private int r = -1;
    private Vector s = new Vector();
    public Vector o = new Vector();

    public l(int n2) {
        this.b = a.e.c(50);
        this.p = new k[20];
        this.j = n2;
        this.q = -1;
    }

    public final void a(int n2, f f2) {
        if (this.a <= 1) {
            this.f = 0;
            this.e = 0;
            return;
        }
        if (this.g) {
            if (this.h == 0) {
                this.f += n2;
                if (this.f >= this.a) {
                    this.f %= this.a;
                    if (this.f >= this.e + this.d || this.f < this.e) {
                        this.e = this.f;
                    }
                    this.a(f2);
                    return;
                }
                if (this.f >= this.e + this.d) {
                    this.e += n2;
                    if (this.e + this.d >= this.a) {
                        this.e = this.a - this.d;
                    }
                    this.a(f2);
                    return;
                }
            } else if (this.h == 1) {
                this.f += n2;
                if (this.f >= this.a) {
                    this.f %= this.a;
                }
                this.e = this.f - this.i < 0 ? this.a + (this.f - this.i) : this.f - this.i;
                this.a(f2);
                return;
            }
        } else {
            this.f += n2;
            if (this.f >= this.a) {
                this.f = this.a - 1;
                if (this.a >= this.d) {
                    this.e = this.a - this.d;
                }
                this.a(f2);
                return;
            }
            if (this.f >= this.e + this.d) {
                this.e += n2;
                this.a(f2);
            }
        }
    }

    public final void b(int n2, f f2) {
        if (this.a <= 1) {
            this.f = 0;
            this.e = 0;
            return;
        }
        if (this.g) {
            if (this.h == 0) {
                this.f -= n2;
                if (this.f < 0) {
                    this.f = this.a + this.f % this.a;
                    if (this.f >= this.e + this.d || this.f < this.e) {
                        this.e = this.a - this.d - (this.a - this.f - 1);
                    }
                    this.a(f2);
                    return;
                }
                if (this.f < this.e) {
                    this.e = this.f;
                    this.a(f2);
                    return;
                }
            } else if (this.h == 1) {
                this.f -= n2;
                if (this.f < 0) {
                    this.f = this.a + this.f % this.a;
                }
                this.e = this.f - this.i < 0 ? this.a + (this.f - this.i) : this.f - this.i;
                this.a(f2);
                return;
            }
        } else {
            this.f -= n2;
            if (this.f < 0) {
                this.f = 0;
                this.e = 0;
                this.a(f2);
                return;
            }
            if (this.f < this.e) {
                this.e -= n2;
                this.a(f2);
            }
        }
    }

    private void a(f f2) {
        if (this.q == 1) {
            if (this.r == 1) {
                for (int i2 = 0; i2 < this.d; ++i2) {
                    int n2 = (null).length > ((String[])this.s.elementAt((this.e + i2) % this.a)).length ? ((String[])this.s.elementAt((this.e + i2) % this.a)).length : (null).length;
                    for (int i3 = 0; i3 < n2; ++i3) {
                        a.e.a((f)f2, (int)null[i3]).i().a = ((String[])this.s.elementAt((this.e + i2) % this.a))[i3];
                    }
                }
                return;
            }
            if (this.r == 2) {
                for (int i4 = 0; i4 < this.d; ++i4) {
                    int n3 = (null).length > ((i[])this.s.elementAt((this.e + i4) % this.a)).length ? ((i[])this.s.elementAt((this.e + i4) % this.a)).length : (null).length;
                    for (int i5 = 0; i5 < n3; ++i5) {
                        a.e.a(f2, (int)null[i5]).a(((i[])this.s.elementAt((this.e + i4) % this.a))[i5]);
                    }
                }
            }
        }
    }

    public final void a(Graphics graphics, int n2, boolean bl, int[] nArray, boolean bl2, f f2) {
        int n3;
        if (this.p != null) {
            this.p = new k[20];
            for (n3 = 0; n3 < this.d; ++n3) {
                k k2;
                f f3 = a.e.a(f2, this.b[n3]);
                this.p[n3] = k2 = new k(f3.c(), f3.d(), f3.e(), f3.f());
            }
        }
        n3 = -1;
        for (int i2 = 0; i2 < this.b.length && this.b[i2] != -1; ++i2) {
            if (this.b[i2] != n2) continue;
            n3 = i2;
            break;
        }
        int[] nArray2 = a.e.c(50);
        if (this.q == 1) {
            for (int i3 = 0; i3 < this.d; ++i3) {
                nArray2[i3] = i3;
            }
        } else if (this.q == -1) {
            for (int i4 = 0; i4 < this.d; ++i4) {
                nArray2[i4] = (this.e + i4) % this.b.length;
            }
        }
        for (int i5 = 0; i5 < nArray2.length && nArray2[i5] != -1; ++i5) {
            if (n3 != nArray2[i5]) continue;
            f f4 = a.e.a(f2, n2);
            int n4 = f4.c() - this.p[i5].a;
            int n5 = f4.d() - this.p[i5].b;
            int n6 = f4.e();
            int n7 = f4.f();
            a.e.a(f4, -n4, -n5, f2);
            f4.c(this.p[i5].c, f2);
            f4.d(this.p[i5].d, f2);
            if (bl && this.a > 0) {
                if (this.q == 1) {
                    if (this.f == (this.e + nArray2[i5]) % this.a) {
                        f4.a(graphics, true, bl2, f2, nArray);
                    } else {
                        f4.a(graphics, false, bl2, f2, nArray);
                    }
                } else if (this.f == nArray2[i5]) {
                    f4.a(graphics, true, bl2, f2, nArray);
                } else {
                    f4.a(graphics, false, bl2, f2, nArray);
                }
            } else {
                f4.a(graphics, false, bl2, f2, nArray);
            }
            a.e.a(f4, n4, n5, f2);
            f4.c(n6, f2);
            f4.d(n7, f2);
        }
    }

    public final void a(int n2, int[] nArray, boolean bl, f f2) {
        int n3;
        if (this.p != null) {
            this.p = new k[20];
            for (n3 = 0; n3 < this.d; ++n3) {
                k k2;
                f f3 = a.e.a(f2, this.b[n3]);
                this.p[n3] = k2 = new k(f3.c(), f3.d(), f3.e(), f3.f());
            }
        }
        n3 = -1;
        for (int i2 = 0; i2 < this.b.length && this.b[i2] != -1; ++i2) {
            if (this.b[i2] != n2) continue;
            n3 = i2;
            break;
        }
        int[] nArray2 = a.e.c(50);
        if (this.q == 1) {
            for (int i3 = 0; i3 < this.d; ++i3) {
                nArray2[i3] = i3;
            }
        } else if (this.q == -1) {
            for (int i4 = 0; i4 < this.d; ++i4) {
                nArray2[i4] = (this.e + i4) % this.b.length;
            }
        }
        for (int i5 = 0; i5 < nArray2.length && nArray2[i5] != -1; ++i5) {
            if (n3 != nArray2[i5]) continue;
            f f4 = a.e.a(f2, n2);
            int n4 = f4.c() - this.p[i5].a;
            int n5 = f4.d() - this.p[i5].b;
            int n6 = f4.e();
            int n7 = f4.f();
            a.e.a(f4, -n4, -n5, f2);
            f4.c(this.p[i5].c, f2);
            f4.d(this.p[i5].d, f2);
            f4.a(bl, bl, f2, nArray);
            a.e.a(f4, n4, n5, f2);
            f4.c(n6, f2);
            f4.d(n7, f2);
        }
    }

    public final void a() {
        if (this.o != null) {
            this.o = null;
        }
        if (this.b != null) {
            this.b = null;
        }
        if (this.c != null) {
            this.c = null;
        }
        if (this.s != null) {
            this.s = null;
        }
        if (this.p != null) {
            this.p = null;
        }
    }

    public final void a(int n2) {
        if (n2 != 1 && n2 != -1) {
            this.q = -1;
            return;
        }
        this.q = n2;
    }
}

