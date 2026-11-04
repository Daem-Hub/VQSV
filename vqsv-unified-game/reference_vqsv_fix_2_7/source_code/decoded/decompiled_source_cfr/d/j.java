/*
 * Decompiled with CFR 0.152.
 */
package d;

import d.i;

public final class j {
    public i a;
    public int b;
    public Object c;

    public final Object a() {
        if (this.a == null) {
            return this.c;
        }
        return this.a.e[this.b];
    }

    public final void a(Object object) {
        if (this.a == null) {
            this.c = object;
            return;
        }
        this.a.e[this.b] = object;
    }
}

