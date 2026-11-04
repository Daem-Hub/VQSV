/*
 * Decompiled with CFR 0.152.
 */
package a;

import a.d;

public abstract class c
implements d {
    protected boolean j;
    private d a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h = -1;
    private int i = -1;

    private static int a(int n2) {
        switch (n2) {
            case 48: 
            case 109: {
                return 1;
            }
            case 49: 
            case 114: {
                return 2;
            }
            case 50: 
            case 116: {
                return 4;
            }
            case 51: 
            case 121: {
                return 8;
            }
            case 52: 
            case 102: {
                return 16;
            }
            case 53: 
            case 103: {
                return 32;
            }
            case 54: 
            case 104: {
                return 64;
            }
            case 55: 
            case 118: {
                return 128;
            }
            case 56: 
            case 98: {
                return 256;
            }
            case 57: 
            case 110: {
                return 512;
            }
            case 42: 
            case 106: {
                return 1024;
            }
            case 35: 
            case 117: {
                return 2048;
            }
            case -1: {
                return 4096;
            }
            case -2: {
                return 8192;
            }
            case -3: {
                return 16384;
            }
            case -4: {
                return 32768;
            }
            case -5: {
                return 65536;
            }
            case -21: 
            case -6: {
                return 131072;
            }
            case -22: 
            case -7: {
                return 262144;
            }
        }
        return 0;
    }

    public final boolean g(int n2) {
        return (this.f & n2) != 0;
    }

    public final boolean a(int n2, int n3, int n4, int n5) {
        if (this.h < n2 || this.h > n2 + n4 || this.i < n3 || this.i > n3 + n5) {
            return false;
        }
        this.h = -1;
        this.i = -1;
        return true;
    }

    public final boolean h(int n2) {
        return (this.g & 0xF154) != 0;
    }

    public final boolean i(int n2) {
        return (this.e & n2) != 0;
    }

    public final void z() {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        if (this.a != null) {
            this.a.z();
        }
    }

    protected final void A() {
        this.e = this.b;
        this.f = this.c;
        this.g = this.d;
        this.c = 0;
        this.d = 0;
    }

    public final void j(int n2) {
        int n3 = a.c.a(n2);
        this.c |= n3;
        this.b |= n3;
        if (this.a != null) {
            this.a.j(n2);
        }
    }

    public final void k(int n2) {
        int n3 = a.c.a(n2);
        this.d |= n3;
        this.b &= ~n3;
        if (this.a != null) {
            this.a.k(n2);
        }
    }

    public final void c(int n2, int n3) {
        if (this.a != null) {
            this.a.c(n2, n3);
        }
    }

    public final void d(int n2, int n3) {
        this.h = n2;
        this.i = n3;
        if (this.a != null) {
            this.a.d(n2, n3);
        }
    }

    public void d(boolean bl) {
        this.z();
        this.j = bl;
    }

    protected final void a(d d2) {
        if (this.a != null) {
            this.a.d(false);
            this.a = null;
        }
        if (d2 != null) {
            d2.d(true);
            this.a = d2;
        }
    }
}

