/*
 * Decompiled with CFR 0.152.
 */
package a;

import a.a;
import a.b;
import a.c;
import a.d;
import a.e;
import a.f;
import a.h;
import a.i;
import java.io.PrintStream;
import java.util.Random;

public final class g {
    public i a;
    public PrintStream b;

    public g() {
        this(System.out);
    }

    private g(PrintStream object) {
        new Random();
        this.b = object;
        object = this;
        this.a = new i((g)object, new h(0));
        ((g)object).a.a.a((Object)"_G", (Object)((g)object).a.a);
        a.a.a((g)object);
    }

    public final int a(i i2, int n2) {
        return this.c(i2, n2);
    }

    private int c(i i2, int n2) {
        int n3 = i2.f - n2 - 1;
        Object object = i2.e[n3];
        if (object == null) {
            throw new RuntimeException("call nil");
        }
        if (object instanceof b) {
            return g.a(i2, (b)object, n3 + 1, n3, n2);
        }
        if (!(object instanceof d)) {
            throw new RuntimeException("call a non-func");
        }
        i2.a((d)object, n3 + 1, n3, n2, false, false).b();
        this.a(i2);
        n2 = i2.f - n3;
        i2.c = "";
        return n2;
    }

    private static int a(i i2, b b2, int n2, int n3, int n4) {
        int n5 = 0;
        c c2 = i2.a(null, n2, n3, n4, false, false);
        i2.a();
        try {
            n5 = b2.a(c2, n4);
        }
        catch (ClassCastException classCastException) {
            classCastException.printStackTrace();
            System.out.println("func");
        }
        int n6 = 0;
        n6 = c2.a() - n5;
        c2.a(n6, -1, n5);
        c2.c(n5 - 1);
        return n5;
    }

    private final void a(i i2) {
        c c2 = i2.b();
        d d2 = c2.b;
        f f2 = d2.a;
        int[] nArray = f2.a;
        int n2 = c2.e;
        while (true) {
            try {
                while (true) {
                    int n3 = nArray[c2.c++];
                    int n4 = n3 & 0x3F;
                    int n5 = n3 >>> 6 & 0xFF;
                    switch (n4) {
                        case 0: {
                            int n6 = n3 >>> 23 & 0x1FF;
                            c2.a(n5, c2.a(n6));
                            break;
                        }
                        case 1: {
                            int n7 = n3 >>> 14;
                            c2.a(n5, f2.b[n7]);
                            break;
                        }
                        case 2: {
                            int n8 = n3 >>> 23 & 0x1FF;
                            n3 = n3 >>> 14 & 0x1FF;
                            c2.a(n5, n8 == 0 ? Boolean.FALSE : Boolean.TRUE);
                            if (n3 == 0) break;
                            ++c2.c;
                            break;
                        }
                        case 3: {
                            int n9 = n3 >>> 23 & 0x1FF;
                            c2.a(n5, n9);
                            break;
                        }
                        case 4: {
                            int n10 = n3 >>> 23 & 0x1FF;
                            c2.a(n5, d2.c[n10].a());
                            break;
                        }
                        case 5: {
                            int n11 = n3 >>> 14;
                            c2.a(n5, g.b(d2.b, f2.b[n11]));
                            break;
                        }
                        case 6: {
                            int n12 = n3 >>> 23 & 0x1FF;
                            n3 = n3 >>> 14 & 0x1FF;
                            Object object = c2.a(n12);
                            Object object2 = g.a(c2, n3, f2);
                            c2.a(n5, g.b(object, object2));
                            break;
                        }
                        case 7: {
                            int n13 = n3 >>> 14;
                            Object object = c2.a(n5);
                            Object object3 = f2.b[n13];
                            g.a(d2.b, object3, object);
                            break;
                        }
                        case 8: {
                            int n14 = n3 >>> 23 & 0x1FF;
                            d2.c[n14].a(c2.a(n5));
                            break;
                        }
                        case 9: {
                            int n15 = n3 >>> 23 & 0x1FF;
                            n3 = n3 >>> 14 & 0x1FF;
                            Object object = c2.a(n5);
                            Object object4 = g.a(c2, n15, f2);
                            Object object5 = g.a(c2, n3, f2);
                            g.a(object, object4, object5);
                            break;
                        }
                        case 10: {
                            h h2 = new h(0);
                            c2.a(n5, h2);
                            break;
                        }
                        case 11: {
                            int n16 = n3 >>> 23 & 0x1FF;
                            n3 = n3 >>> 14 & 0x1FF;
                            Object object = g.a(c2, n3, f2);
                            Object object6 = c2.a(n16);
                            Object object7 = g.b(object6, object);
                            c2.a(n5, object7);
                            c2.a(n5 + 1, object6);
                            break;
                        }
                        case 12: 
                        case 13: 
                        case 14: 
                        case 15: 
                        case 16: 
                        case 17: {
                            int n17 = n3 >>> 23 & 0x1FF;
                            n3 = n3 >>> 14 & 0x1FF;
                            Object object = g.a(c2, n17, f2);
                            Object object8 = g.a(c2, n3, f2);
                            Integer n18 = null;
                            Integer n19 = null;
                            Integer n20 = a.a.b(object);
                            if (n20 != null && (n18 = a.a.b(object8)) != null) {
                                n19 = g.a(n20, n18, n4);
                            }
                            c2.a(n5, n19);
                            break;
                        }
                        case 19: {
                            int n21 = n3 >>> 23 & 0x1FF;
                            Object object = c2.a(n21);
                            c2.a(n5, g.a(!g.b(object)));
                            break;
                        }
                        case 20: {
                            int n22 = n3 >>> 23 & 0x1FF;
                            Object object = c2.a(n22);
                            Integer n23 = object instanceof h ? g.a(((h)object).a()) : (object instanceof String ? g.a(((String)object).length()) : null);
                            c2.a(n5, n23);
                            break;
                        }
                        case 21: {
                            int n24 = n3 >>> 23 & 0x1FF;
                            n3 = n3 >>> 14 & 0x1FF;
                            int n25 = n24;
                            int n26 = n3;
                            Object object = c2.a(n26);
                            --n26;
                            while (n25 <= n26) {
                                String string = a.a.a(object);
                                if (object != null) {
                                    n3 = 0;
                                    n24 = n26;
                                    while (n25 <= n24) {
                                        Object object9 = c2.a(n24);
                                        --n24;
                                        if (a.a.a(object9) == null) break;
                                        ++n3;
                                    }
                                    if (n3 > 0) {
                                        StringBuffer stringBuffer = new StringBuffer();
                                        n24 = n26 - n3 + 1;
                                        while (n24 <= n26) {
                                            stringBuffer.append(a.a.a(c2.a(n24)));
                                            ++n24;
                                        }
                                        stringBuffer.append(string);
                                        object = stringBuffer.toString();
                                        n26 -= n3;
                                    }
                                }
                                if (n25 > n26) continue;
                                c2.a(n26);
                                --n26;
                            }
                            c2.a(n5, object);
                            break;
                        }
                        case 22: {
                            c2.c += g.b(n3);
                            break;
                        }
                        case 23: 
                        case 24: 
                        case 25: {
                            int n27 = n3 >>> 23 & 0x1FF;
                            n3 = n3 >>> 14 & 0x1FF;
                            Object object = g.a(c2, n27, f2);
                            Object object10 = g.a(c2, n3, f2);
                            if (object instanceof Integer && object10 instanceof Integer) {
                                int n28 = g.a(object);
                                int n29 = g.a(object10);
                                if (n4 == 23) {
                                    if (n28 == n29 != (n5 == 0)) break;
                                    ++c2.c;
                                    break;
                                }
                                if (n4 == 24) {
                                    if (n28 < n29 != (n5 == 0)) break;
                                    ++c2.c;
                                    break;
                                }
                                if (n28 <= n29 != (n5 == 0)) break;
                                ++c2.c;
                                break;
                            }
                            if (object instanceof String && object10 instanceof String) {
                                if (n4 == 23) {
                                    if (object.equals(object10) != (n5 == 0)) break;
                                    ++c2.c;
                                    break;
                                }
                                String string = (String)object;
                                String string2 = (String)object10;
                                n3 = string.compareTo(string2);
                                if (n4 == 24) {
                                    if (n3 < 0 != (n5 == 0)) break;
                                    ++c2.c;
                                    break;
                                }
                                if (n3 <= 0 != (n5 == 0)) break;
                                ++c2.c;
                                break;
                            }
                            boolean bl = false;
                            if (object == object10) {
                                bl = true;
                            } else if (n4 == 23) {
                                bl = g.c(object, object10);
                            } else {
                                a.a.a(String.valueOf(String.valueOf(n4)) + " not defined for operand");
                            }
                            if (bl != (n5 == 0)) break;
                            ++c2.c;
                            break;
                        }
                        case 26: {
                            n3 = n3 >>> 14 & 0x1FF;
                            if (g.b(c2.a(n5)) != (n3 == 0)) break;
                            ++c2.c;
                            break;
                        }
                        case 27: {
                            int n30 = n3 >>> 23 & 0x1FF;
                            n3 = n3 >>> 14 & 0x1FF;
                            Object object = c2.a(n30);
                            if (g.b(object) != (n3 == 0)) {
                                c2.a(n5, object);
                                break;
                            }
                            ++c2.c;
                            break;
                        }
                        case 28: {
                            int n31 = n3 >>> 23 & 0x1FF;
                            n3 = n3 >>> 14 & 0x1FF;
                            int n32 = n31 - 1;
                            if (n32 != -1) {
                                c2.c(n5 + n32 + 1);
                            } else {
                                n32 = c2.a() - n5 - 1;
                            }
                            c2.i = n3 != 0;
                            int n33 = c2.d;
                            int n34 = n33 + n5;
                            int n35 = n34 + 1;
                            Object object = c2.a(n5);
                            if (object == null) {
                                a.a.a(object != null, "call nil");
                            }
                            if (object instanceof d) {
                                c c3 = i2.a((d)object, n35, n34, n32, true, c2.h);
                                c3.b();
                                c2 = c3;
                                d2 = c3.b;
                                f2 = d2.a;
                                nArray = f2.a;
                                n2 = c2.e;
                                break;
                            }
                            if (object instanceof b) {
                                g.a(i2, (b)object, n33 + n5 + 1, n33 + n5, n32);
                                c2 = i2.b();
                                if (c2.d()) {
                                    return;
                                }
                                if (c2.b != null) {
                                    d2 = c2.b;
                                    f2 = d2.a;
                                    nArray = f2.a;
                                    n2 = c2.e;
                                }
                                if (!c2.i) break;
                                c2.c(f2.g);
                                break;
                            }
                            throw new RuntimeException("Call non-func: " + object);
                        }
                        case 29: {
                            int n36 = c2.d;
                            i2.b(n36);
                            int n37 = (n3 >>> 23 & 0x1FF) - 1;
                            if (n37 == -1) {
                                n37 = c2.a() - n5 - 1;
                            }
                            c2.i = false;
                            Object object = c2.a(n5);
                            if (object == null) {
                                a.a.a(object != null, "Tried to call nil");
                            }
                            int n38 = n2 + 1;
                            i2.a(n36 + n5, n2, n37 + 1);
                            i2.a(n2 + n37 + 1);
                            if (object instanceof d) {
                                c2.d = n38;
                                c2.f = n37;
                                c2.b = (d)object;
                                c2.b();
                            } else {
                                if (!(object instanceof b)) {
                                    a.a.a("Tried to call a non-function: " + object);
                                }
                                i i3 = i2;
                                g.a(i2, (b)object, n38, n2, n37);
                                c2 = i2.b();
                                i3.a();
                                if (i3 != i2) {
                                    if (i3.c() && i2.b == i3) {
                                        i2.b = i3.b;
                                        i3.b = null;
                                        i2.b.b().a(Boolean.TRUE);
                                    }
                                    if ((c2 = i2.b()).d()) {
                                        return;
                                    }
                                } else {
                                    if (!c2.g) {
                                        return;
                                    }
                                    c2 = i2.b();
                                    if (c2.i) {
                                        c2.c(c2.b.a.g);
                                    }
                                }
                            }
                            d2 = c2.b;
                            f2 = d2.a;
                            nArray = f2.a;
                            n2 = c2.e;
                            break;
                        }
                        case 30: {
                            int n39 = (n3 >>> 23 & 0x1FF) - 1;
                            int n40 = c2.d;
                            i2.b(n40);
                            if (n39 == -1) {
                                n39 = c2.a() - n5;
                            }
                            i2.a(c2.d + n5, n2, n39);
                            i2.a(n2 + n39);
                            if (c2.g) {
                                i2.a();
                                c2 = i2.b();
                                if (c2.b != null) {
                                    d2 = c2.b;
                                    f2 = d2.a;
                                    nArray = f2.a;
                                    n2 = c2.e;
                                }
                                if (!c2.i) break;
                                c2.c(f2.g);
                                break;
                            }
                            i2.a();
                            return;
                        }
                        case 32: {
                            int n41 = g.b(n3);
                            int n42 = g.a(c2.a(n5));
                            int n43 = g.a(c2.a(n5 + 2));
                            c2.a(n5, g.a(n42 - n43));
                            c2.c += n41;
                            break;
                        }
                        case 31: {
                            int n44 = g.a(c2.a(n5));
                            int n45 = g.a(c2.a(n5 + 1));
                            int n46 = g.a(c2.a(n5 + 2));
                            Integer n47 = g.a(n44 += n46);
                            c2.a(n5, n47);
                            if (n46 > 0 ? n44 <= n45 : n44 >= n45) {
                                int n48 = g.b(n3);
                                c2.c += n48;
                                c2.a(n5 + 3, n47);
                                break;
                            }
                            c2.b(n5);
                            break;
                        }
                        case 33: {
                            n3 = n3 >>> 14 & 0x1FF;
                            c2.c(n5 + 6);
                            c2.a(n5, n5 + 3, 3);
                            this.a(i2, 2);
                            c2.b(n5 + 3 + n3);
                            c2.c();
                            Object object = c2.a(n5 + 3);
                            if (object != null) {
                                c2.a(n5 + 2, object);
                                break;
                            }
                            ++c2.c;
                            break;
                        }
                        case 34: {
                            int n49 = n3 >>> 23 & 0x1FF;
                            n3 = n3 >>> 14 & 0x1FF;
                            if (n49 == 0) {
                                n49 = c2.a() - n5 - 1;
                            }
                            if (n3 == 0) {
                                n3 = nArray[c2.c++];
                            }
                            int n50 = (n3 - 1) * 50;
                            h h3 = (h)c2.a(n5);
                            int n51 = 1;
                            while (n51 <= n49) {
                                Integer n52 = g.a(n50 + n51);
                                Object object = c2.a(n5 + n51);
                                h3.a((Object)n52, object);
                                ++n51;
                            }
                            break;
                        }
                        case 35: {
                            c2.d(n5);
                            break;
                        }
                        case 36: {
                            int n53 = n3 >>> 14;
                            f f3 = f2.c[n53];
                            d d3 = new d(f3, d2.b);
                            c2.a(n5, d3);
                            int n54 = f3.f;
                            int n55 = 0;
                            while (n55 < n54) {
                                n3 = nArray[c2.c++];
                                n4 = n3 & 0x3F;
                                n53 = n3 >>> 23 & 0x1FF;
                                switch (n4) {
                                    case 0: {
                                        d3.c[n55] = c2.e(n53);
                                        break;
                                    }
                                    case 4: {
                                        d3.c[n55] = d2.c[n53];
                                    }
                                }
                                ++n55;
                            }
                            break;
                        }
                        case 37: {
                            int n56 = (n3 >>> 23 & 0x1FF) - 1;
                            c2.b(n5, n56);
                        }
                    }
                }
            }
            catch (RuntimeException runtimeException) {
                RuntimeException runtimeException2 = runtimeException;
                runtimeException.printStackTrace();
                while (!i2.b().e()) {
                    i2.a();
                }
                boolean bl = true;
                do {
                    if ((c2 = i2.b()) == null) {
                        i i4 = i2.b;
                        if (i4 == null) break;
                        i2.b = null;
                        c c4 = i4.b();
                        c4.a(Boolean.FALSE);
                        c4.a(runtimeException2.getMessage());
                        c4.a(i2.c);
                        i2.g.a = i4;
                        i2 = i4;
                        c2 = i2.b();
                        d2 = c2.b;
                        f2 = d2.a;
                        nArray = f2.a;
                        n2 = c2.e;
                        bl = false;
                        break;
                    }
                    i2.a();
                } while (c2.g);
                if (c2 == null) continue;
                c2.d(0);
                if (!bl) continue;
                throw runtimeException2;
            }
            break;
        }
    }

    private static final Object a(c c2, int n2, f f2) {
        int n3 = n2 - 256;
        if (n3 < 0) {
            return c2.a(n2);
        }
        return f2.b[n3];
    }

    private static final int b(int n2) {
        return (n2 >>> 14) - 131071;
    }

    private static final Integer a(Integer n2, Integer n3, int n4) {
        int n5 = n2;
        int n6 = n3;
        int n7 = 0;
        switch (n4) {
            case 12: {
                n7 = n5 + n6;
                break;
            }
            case 13: {
                n7 = n5 - n6;
                break;
            }
            case 14: {
                n7 = n5 * n6;
                break;
            }
            case 15: {
                n7 = n5 / n6;
                break;
            }
            case 16: {
                n7 = n5 % n6;
            }
        }
        return g.a(n7);
    }

    public final Object a(Object object, Object object2) {
        int n2 = this.a.f;
        this.a.a(n2 + 1 + 3);
        object2 = this.a.e;
        this.a.e[n2] = object;
        object2[n2 + 1] = null;
        object2[n2 + 2] = null;
        object2[n2 + 3] = null;
        object = this.a;
        int n3 = this.c((i)object, 3);
        object2 = null;
        if (n3 > 0) {
            object2 = this.a.e[n2];
        }
        this.a.a(n2);
        return object2;
    }

    public static final Object b(Object object, Object object2) {
        int n2;
        Object object3;
        if (!(object instanceof h)) {
            if (object instanceof String && object2 instanceof Integer) {
                int n3 = (Integer)object2;
                return ((String)object).substring(n3 - 1, n3);
            }
            return null;
        }
        if (object2 instanceof Integer && (object3 = ((h)object).a(n2 = ((Integer)object2).intValue())) != null) {
            return object3;
        }
        return ((h)object).a(object2);
    }

    private static void a(Object object, Object object2, Object object3) {
        try {
            ((h)object).a(object2, object3);
            return;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            System.out.println(object2);
            System.out.println(object3);
            System.out.println(object);
            return;
        }
    }

    public final int b(i object, int n2) {
        c c2;
        Throwable throwable;
        Object object2;
        i i2 = object;
        Object object3 = i2.b();
        i2.c = "";
        int n3 = i2.f - n2 - 1;
        try {
            int n4 = this.c((i)object, n2);
            int n5 = n3 + n4 + 1;
            i2.a(n5);
            i2.a(n3, n3 + 1, n4);
            i2.e[n3] = Boolean.TRUE;
            return n4 + 1;
        }
        catch (e e2) {
            object2 = e2;
            throwable = e2;
            object = null;
            ((Throwable)object2).printStackTrace();
        }
        catch (Throwable throwable2) {
            object2 = throwable2;
            throwable = throwable2;
            object = ((Throwable)object2).getMessage();
            ((Throwable)object2).printStackTrace();
        }
        a.a.a(true, "Thread changed in pcall");
        if (object3 != null) {
            ((c)object3).d(0);
        }
        object2 = object3;
        object3 = i2;
        while ((c2 = ((i)object3).b()) != null && c2 != object2) {
            ((i)object3).a();
        }
        if (object instanceof String) {
            object = (String)object;
        }
        i2.a(n3 + 4);
        i2.e[n3] = Boolean.FALSE;
        i2.e[n3 + 1] = object;
        i2.e[n3 + 2] = i2.c;
        i2.e[n3 + 3] = throwable;
        i2.c = "";
        return 4;
    }

    public static boolean c(Object object, Object object2) {
        if (object == null || object2 == null) {
            return object == object2;
        }
        if (object instanceof Integer && object2 instanceof Integer) {
            object = (Integer)object;
            object2 = (Integer)object2;
            return ((Integer)object).intValue() == ((Integer)object2).intValue();
        }
        return object == object2;
    }

    private static int a(Object object) {
        return (Integer)object;
    }

    public static Integer a(int n2) {
        return new Integer(n2);
    }

    private static boolean b(Object object) {
        return object != null && object != Boolean.FALSE;
    }

    private static Boolean a(boolean bl) {
        if (bl) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}

