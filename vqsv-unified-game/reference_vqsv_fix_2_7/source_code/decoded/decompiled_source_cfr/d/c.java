/*
 * Decompiled with CFR 0.152.
 */
package d;

import d.d;
import d.i;
import d.j;

public final class c {
    public i a;
    public d b;
    public int c;
    public int d;
    int e;
    public int f;
    boolean g;
    public boolean h;
    boolean i;

    public c(i i2) {
        this.a = i2;
    }

    public final void a(int n2, Object object) {
        this.a.e[this.d + n2] = object;
    }

    public final Object a(int n2) {
        return this.a.e[this.d + n2];
    }

    public final int a(Object object) {
        int n2 = this.a();
        this.c(n2 + 1);
        this.a(n2, object);
        return 1;
    }

    public final void a(int n2, int n3, int n4) {
        this.a.a(this.d + n2, this.d + n3, n4);
    }

    public final void a(int n2, int n3) {
        while (n2 <= n3) {
            this.a.e[this.d + n2] = null;
            ++n2;
        }
    }

    public final void b(int n2) {
        if (this.a() < n2) {
            this.c(n2);
        }
        this.a(n2, this.a() - 1);
    }

    public final void c(int n2) {
        this.a.a(this.d + n2);
    }

    public final void d(int n2) {
        this.a.b(this.d + n2);
    }

    public final j e(int n2) {
        j j2;
        int n3 = this.d + n2;
        i i2 = this.a;
        int n4 = i2.d.size();
        while (--n4 >= 0) {
            j2 = (j)i2.d.elementAt(n4);
            if (j2.b == n3) {
                return j2;
            }
            if (j2.b < n3) break;
        }
        j2 = new j();
        new j().a = i2;
        j2.b = n3;
        i2.d.insertElementAt(j2, n4 + 1);
        return j2;
    }

    public final int a() {
        return this.a.f - this.d;
    }

    public final void b() {
        if (this.e()) {
            this.c = 0;
            if (this.b.a.e) {
                this.d += this.f;
                this.c(this.b.a.g);
                int n2 = Math.min(this.f, this.b.a.d);
                this.a(-this.f, 0, n2);
                return;
            }
            this.c(this.b.a.g);
        }
    }

    public final void c() {
        if (this.e()) {
            this.c(this.b.a.g);
        }
    }

    public final void b(int n2, int n3) {
        int n4 = this.b.a.d;
        int n5 = this.f - n4;
        if (n5 < 0) {
            n5 = 0;
        }
        if (n3 == -1) {
            n3 = n5;
            this.c(n2 + n3);
        }
        if (n5 > n3) {
            n5 = n3;
        }
        this.a(-this.f + n4, n2, n5);
        if (n3 - n5 > 0) {
            this.a(n2 + n5, n2 + n3 - 1);
        }
    }

    public final boolean d() {
        return !this.e();
    }

    public final boolean e() {
        return this.b != null;
    }
}

