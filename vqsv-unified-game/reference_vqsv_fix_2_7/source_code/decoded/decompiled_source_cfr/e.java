/*
 * Decompiled with CFR 0.152.
 */
public final class e {
    public int[] a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;

    public final void a(int[] nArray, int n2, int n3) {
        this.a = nArray;
        this.b = n2;
        this.c = n3;
        this.f = nArray.length;
    }

    public final e a() {
        e e2 = new e();
        new e().a = new int[this.a.length];
        System.arraycopy(this.a, 0, e2.a, 0, e2.a.length);
        e2.b = this.b;
        e2.c = this.c;
        e2.f = this.a.length;
        e2.d = this.d;
        e2.e = this.e;
        return e2;
    }
}

