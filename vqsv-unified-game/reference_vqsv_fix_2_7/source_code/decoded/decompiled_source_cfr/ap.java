/*
 * Decompiled with CFR 0.152.
 */
public abstract class ap {
    protected boolean Y;
    private ap a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h = -1;
    private int i = -1;

    public void i(int n2) {
        int n3 = ap.a(n2);
        this.c |= n3;
        this.b |= n3;
        if (this.a != null) {
            this.a.i(n2);
        }
    }

    public void j(int n2) {
        int n3 = ap.a(n2);
        this.d |= n3;
        this.b &= ~n3;
        if (this.a != null) {
            this.a.j(n2);
        }
    }

    public void c(boolean bl) {
        this.P();
        this.Y = bl;
    }

    public void P() {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        if (this.a != null) {
            this.a.P();
        }
    }

    public void d(int n2, int n3) {
        this.h = n2;
        this.i = n3;
        if (this.a != null) {
            this.a.d(n2, n3);
        }
    }

    private static int a(int n2) {
        switch (n2) {
            case 48: {
                return 1;
            }
            case 49: {
                return 2;
            }
            case 50: {
                return 4;
            }
            case 51: {
                return 8;
            }
            case 52: {
                return 16;
            }
            case 53: {
                return 32;
            }
            case 54: {
                return 64;
            }
            case 55: {
                return 128;
            }
            case 56: {
                return 256;
            }
            case 57: {
                return 512;
            }
            case 42: {
                return 1024;
            }
            case 35: {
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

    public boolean k(int n2) {
        return (this.f & n2) != 0;
    }

    public boolean Q() {
        if (this.h < 40 || this.h > 85 || this.i < 228 || this.i > 248) {
            return false;
        }
        this.h = -1;
        this.i = -1;
        return true;
    }

    public boolean R() {
        return (this.g & 0xF154) != 0;
    }

    public boolean l(int n2) {
        return (this.e & n2) != 0;
    }

    protected void S() {
        this.e = this.b;
        this.f = this.c;
        this.g = this.d;
        this.c = 0;
        this.d = 0;
    }

    protected void a(ap ap2) {
        if (this.a != null) {
            this.a.c(false);
            this.a = null;
        }
        if (ap2 != null) {
            ap2.c(true);
            this.a = ap2;
        }
    }
}

