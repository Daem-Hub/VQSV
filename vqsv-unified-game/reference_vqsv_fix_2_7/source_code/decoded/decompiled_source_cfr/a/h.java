/*
 * Decompiled with CFR 0.152.
 */
package a;

import a.a;
import a.b;
import a.g;
import java.lang.ref.WeakReference;

public class h {
    private Object[] a;
    private Object[] b;
    private int[] c;
    private int d;
    private boolean e;
    private boolean f;

    public void a(Object object, Object object2) {
        h.g(object);
        int n2 = this.c(object);
        int n3 = this.a(object, n2);
        if (n3 < 0) {
            n3 = this.b(object, n2);
        }
        Object object3 = object2;
        int n4 = n3;
        object = this;
        if (((h)object).f) {
            object3 = h.e(object3);
        }
        ((h)object).b[n4] = object3;
    }

    public Object a(Object object) {
        h.g(object);
        int n2 = this.c(object);
        int n3 = this.a(object, n2);
        if (n3 >= 0) {
            return this.d(n3);
        }
        return null;
    }

    public Object a(int n2) {
        return this.a(g.a(n2));
    }

    public Object b(Object object) {
        int n2 = 0;
        if (object != null) {
            int n3 = this.c(object);
            n2 = 1 + this.a(object, n3);
            a.a.a(n2 > 0, "invalid key to 'next'");
        }
        while (n2 != this.a.length) {
            Object object2 = this.c(n2);
            if (object2 != null && this.d(n2) != null) {
                return object2;
            }
            ++n2;
        }
        return null;
    }

    public int a() {
        int n2 = this.a.length;
        int n3 = 0;
        while (n3 < n2) {
            int n4 = n2 + n3 + 1 >> 1;
            Integer n5 = g.a(n4);
            if (this.a(n5) == null) {
                n2 = n4 - 1;
                continue;
            }
            n3 = n4;
        }
        return n3;
    }

    private static int b(int n2) {
        if (n2 < 4) {
            return 4;
        }
        --n2;
        n2 |= n2 >> 1;
        n2 |= n2 >> 2;
        n2 |= n2 >> 4;
        n2 |= n2 >> 8;
        return (n2 | n2 >> 16) + 1;
    }

    private h() {
        h h2 = this;
        int n2 = h.b(4);
        h2.a = new Object[n2];
        h2.b = new Object[n2];
        h2.c = new int[n2];
        h2.d = n2;
    }

    public h(byte by) {
        this();
    }

    private int c(Object object) {
        int n2 = this.a.length;
        return (object instanceof Integer ? ((Integer)(object = (Integer)object) == 0 ? 0 : ((Integer)object).hashCode()) : (object instanceof String ? object.hashCode() : System.identityHashCode(object))) & n2 - 1;
    }

    private static Object d(Object object) {
        if (!h.f(object)) {
            return object;
        }
        return ((WeakReference)object).get();
    }

    private static Object e(Object object) {
        if (!h.f(object)) {
            return object;
        }
        return new WeakReference<Object>(object);
    }

    private static boolean f(Object object) {
        return object != null && !(object instanceof String) && !(object instanceof Integer) && !(object instanceof Boolean) && !(object instanceof b);
    }

    private Object c(int n2) {
        Object object = this.a[n2];
        if (this.e) {
            return h.d(object);
        }
        return object;
    }

    private void a(int n2, Object object) {
        if (this.e) {
            object = h.e(object);
        }
        this.a[n2] = object;
    }

    private Object d(int n2) {
        Object object = this.b[n2];
        if (this.f) {
            return h.d(object);
        }
        return object;
    }

    private int a(Object object, int n2) {
        Object object2 = this.c(n2);
        if (object2 == null) {
            return -1;
        }
        if (object instanceof Integer) {
            int n3 = (Integer)object;
            while (true) {
                int n4;
                if (object2 instanceof Integer && n3 == (n4 = ((Integer)object2).intValue())) {
                    return n2;
                }
                if ((n2 = this.c[n2]) == -1) {
                    return -1;
                }
                object2 = this.c(n2);
            }
        }
        if (object instanceof String) {
            while (true) {
                if (object.equals(object2)) {
                    return n2;
                }
                if ((n2 = this.c[n2]) == -1) {
                    return -1;
                }
                object2 = this.c(n2);
            }
        }
        while (object != object2) {
            if ((n2 = this.c[n2]) == -1) {
                return -1;
            }
            object2 = this.c(n2);
        }
        return n2;
    }

    /*
     * Unable to fully structure code
     */
    private int b(Object var1_1, int var2_3) {
        while (true) {
            if ((var3_6 = this.c(var2_3)) == null) {
                this.a(var2_3, var1_1);
                this.c[var2_3] = -1;
                return var2_3;
            }
            try {
                while (this.c(--this.d) != null) {
                }
                break;
            }
            catch (ArrayIndexOutOfBoundsException v0) {
                var2_4 = this;
                var3_5 = var2_4.e;
                var4_8 = var2_4.f;
                var2_4.a(false, false);
                var5_9 = var2_4.a;
                var6_10 = var2_4.b;
                var7_11 = var5_9.length;
                var8_13 = 0;
                var9_15 = var7_11;
                ** while (var9_15-- > 0)
            }
lbl-1000:
            // 1 sources

            {
                if (var2_4.a[var9_15] == null || var2_4.b[var9_15] == null) continue;
                ++var8_13;
                continue;
            }
lbl24:
            // 1 sources

            var8_13 = 2 * h.b(var8_13);
            var2_4.a = new Object[var8_13];
            var2_4.b = new Object[var8_13];
            var2_4.c = new int[var8_13];
            var2_4.d = var8_13;
            var9_15 = var7_11;
            while (var9_15-- > 0) {
                var7_12 = var5_9[var9_15];
                if (var7_12 == null || (var8_14 = var6_10[var9_15]) == null) continue;
                var2_4.a(var7_12, var8_14);
            }
            var2_4.a(var3_5, var4_8);
            var2_3 = this.c(var1_1);
        }
        var3_7 = this.c(var3_6);
        if (var3_7 == var2_3) {
            this.a(this.d, var1_1);
            this.c[this.d] = this.c[var2_3];
            this.c[var2_3] = this.d;
            return this.c[var2_3];
        }
        this.a[this.d] = this.a[var2_3];
        this.b[this.d] = this.b[var2_3];
        this.c[this.d] = this.c[var2_3];
        this.a(var2_3, var1_1);
        this.c[var2_3] = -1;
        var1_2 = var3_7;
        while (true) {
            if ((var3_7 = this.c[var1_2]) == var2_3) break;
            var1_2 = var3_7;
        }
        this.c[var1_2] = this.d;
        return var2_3;
    }

    private static void g(Object object) {
        a.a.a(object != null, "table index is nil");
    }

    public void a(boolean bl, boolean bl2) {
        if (bl != this.e) {
            this.a(this.a, bl);
            this.e = bl;
        }
        if (bl2 != this.f) {
            this.a(this.b, bl2);
            this.f = bl2;
        }
    }

    private void a(Object[] objectArray, boolean bl) {
        int n2 = objectArray.length - 1;
        while (n2 >= 0) {
            Object object = objectArray[n2];
            objectArray[n2] = bl ? h.e(object) : h.d(object);
            --n2;
        }
    }
}

