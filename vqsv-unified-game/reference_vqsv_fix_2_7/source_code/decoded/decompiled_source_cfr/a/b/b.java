/*
 * Decompiled with CFR 0.152.
 */
package a.b;

import a.b.a;

public final class b
extends a {
    private static b b;
    public byte a = 0;
    private int c;
    private int u;
    private int v;
    private boolean w = true;

    public static b a() {
        if (b == null) {
            b = new b();
        }
        return b;
    }

    public final void a(int n2) {
        this.v = n2;
    }

    public final void a(boolean bl) {
        this.w = false;
    }

    public final void a(int n2, int n3, int n4, int n5, boolean bl) {
        this.a = 0;
        this.w = bl;
        if (this.w) {
            this.j = n2;
            this.k = n3;
            return;
        }
        n4 = n3;
        n3 = n2;
        b b2 = this;
        this.r = n3;
        b2.s = n4;
    }

    public final void a(a a2, int n2, int n3, boolean bl) {
        this.a = 1;
        a a3 = a2;
        a2 = this;
        this.q = a3;
        this.w = bl;
        if (this.w) {
            this.j = this.q.j;
            this.k = this.q.k;
        }
    }

    public final boolean b() {
        return this.w;
    }

    public final void c() {
        switch (this.a) {
            case 0: {
                if (this.w || !this.a(this.v, this.r, this.s)) break;
                this.w = true;
                return;
            }
            case 1: {
                if (this.w) {
                    this.j = this.q.j;
                    this.k = this.q.k;
                    return;
                }
                if (!this.a(this.v, this.q.j, this.q.k)) break;
                this.w = true;
                return;
            }
            case 2: {
                if (this.w) {
                    return;
                }
                if (this.c > 0) {
                    --this.c;
                    return;
                }
                if (this.j == null[0]) {
                    if (this.k == null[1]) {
                        if (this.u < (null).length) {
                            ++this.u;
                            this.c = null[5];
                            return;
                        }
                        this.w = true;
                        return;
                    }
                }
                this.a((int)null[2], (int)null[0], (int)null[1]);
            }
        }
    }
}

