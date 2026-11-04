/*
 * Decompiled with CFR 0.152.
 */
package b;

import b.b;
import java.io.DataInputStream;
import java.util.Vector;

public final class a {
    private b[] a;
    private byte b;
    private Vector c;
    private byte d;
    private byte e;
    private int f;

    public final void a(DataInputStream object, byte by, int n2, String[] stringArray) {
        this.b = by;
        this.f = n2;
        by = (byte)((DataInputStream)object).readShort();
        this.a = new b[by];
        this.c = new Vector();
        for (n2 = 0; n2 < by; ++n2) {
            this.a[n2] = new b();
            this.a[n2].a((DataInputStream)object, stringArray);
            this.c.addElement(this.a[n2]);
        }
        by = 0;
        object = this;
        this.e = by;
    }

    public final byte a() {
        return this.e;
    }

    public final void a(byte by) {
        this.e = by;
    }

    public final byte b() {
        return this.b;
    }

    public final void b(byte by) {
        this.d = by;
    }

    public final b c() {
        if (this.d >= this.c.size()) {
            return null;
        }
        return (b)this.c.elementAt(this.d);
    }

    public final b d() {
        return (b)this.c.firstElement();
    }

    public final void e() {
        this.c();
        this.d = (byte)(this.d + 1);
        if (this.d >= this.c.size()) {
            this.d = 0;
        }
    }

    public final int[] f() {
        if (this.f == -1) {
            return null;
        }
        int[] nArray = new int[2];
        int[] nArray2 = nArray;
        nArray[0] = this.f >> 8 & 0xFF;
        nArray2[1] = this.f & 0xFF;
        return nArray2;
    }
}

