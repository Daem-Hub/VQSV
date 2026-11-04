/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ag
 *  javax.microedition.lcdui.Font
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import game.g;
import game.h;
import game.k;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public abstract class an
extends ap
implements c,
x {
    private static short a;
    private static short b;
    private static int c;
    private static Font d;
    private static Font e;
    private static int f;
    public byte P;
    public byte Q;
    public ab R;
    public h S;
    private static boolean g;
    public static boolean T;
    public static byte U;
    public static byte V;
    private static byte[][] h;
    private static Timer i;
    private static TimerTask j;
    private static boolean k;
    public static boolean W;
    public static boolean X;
    private byte l;
    private static byte[] m;
    private ag n = null;
    private byte o;
    private byte p;
    private byte q;
    private String[] r = new String[]{"01", "02", "03", "04", "05"};
    private byte[][] s = new byte[][]{{4, 1, 0}, {2, 1, 1}, {2, 1, 2}, {2, 1, 3}, {2, 1, 4}};
    private String[][] t = new String[][]{{"K\u00edch ho\u1ea1t", "B\u1ea1n mu\u1ed1n kh\u00e1m ph\u00e1 b\u00ed m\u1eadt c\u1ee7a v\u01b0\u01a1ng qu\u1ed1c s\u1ee7ng v\u1eadt, d\u1eabn d\u1eaft th\u00fa y\u00eau chi\u1ebfn \u0111\u1ea5u, ti\u1ebfn h\u00f3a, \u1ea5p tr\u1ee9ng? Ch\u1ec9 c\u1ea7n 1 tin nh\u1eafn 15000\u0111 \u0111\u1ec3 k\u00edch ho\u1ea1t tr\u00f2 ch\u01a1i, ch\u1ec9 nh\u1eafn tin 1 l\u1ea7n cho t\u1ea5t c\u1ea3 c\u00e1c l\u01b0\u1ee3t ch\u01a1i. B\u1ea1n c\u00f3 mu\u1ed1n nh\u1eafn tin kh\u00f4ng?"}, {"T\u1ea5t tr\u00fang c\u1ea7u", "Ch\u1ec9 c\u1ea7n nh\u1eafn 1 tin nh\u1eafn 10000\u0111, b\u1ea1n s\u1ebd s\u1edf h\u1eefu 1 t\u1ea5t tr\u00fang c\u1ea7u, t\u1ef7 l\u1ec7 100% b\u1eaft \u0111\u01b0\u1ee3c s\u1ee7ng v\u1eadt? B\u1ea1n c\u00f3 mu\u1ed1n nh\u1eafn tin kh\u00f4ng?"}, {"Mua s\u1eafm kim ti\u1ec1n", "Ki\u1ebfm ti\u1ec1n v\u1ea5t v\u1ea3, v\u1eadt ph\u1ea9m \u0111\u1eaft \u0111\u1ecf? Ch\u1ec9 c\u1ea7n nh\u1eafn 1 tin nh\u1eafn 10000\u0111 b\u1ea1n s\u1ebd \u0111\u1ea1t \u0111\u01b0\u1ee3c 10000 kim ti\u1ec1n. B\u1ea1n c\u00f3 mu\u1ed1n nh\u1eafn tin kh\u00f4ng?"}, {"Mua \u0111\u1eb3ng c\u1ea5p", "Th\u0103ng c\u1ea5p ch\u1eadm ch\u1ea1p, k\u1ebb \u0111\u1ecbch l\u1ea1i qu\u00e1 m\u1ea1nh? Ch\u1ec9 c\u1ea7n 1 tin nh\u1eafn 10000\u0111, t\u1ea5t c\u1ea3 s\u1ee7ng v\u1eadt trong ba l\u00f4 c\u1ee7a b\u1ea1n \u0111\u1ec1u \u0111\u01b0\u1ee3c th\u0103ng l\u00ean 5 c\u1ea5p. B\u1ea1n c\u00f3 mu\u1ed1n nh\u1eafn tin kh\u00f4ng?"}, {"Mua s\u1eafm huy hi\u1ec7u", "Ki\u1ebfm huy hi\u1ec7u kh\u00f3 kh\u0103n? Ch\u1ec9 c\u1ea7n 1 tin nh\u1eafn 10000\u0111, b\u1ea1n s\u1ebd \u0111\u1ea1t \u0111\u01b0\u1ee3c 10 huy hi\u1ec7u. B\u1ea1n c\u00f3 mu\u1ed1n nh\u1eafn tin kh\u00f4ng?"}};
    private q u;

    public abstract void b();

    public abstract void b(Graphics var1);

    public abstract boolean d();

    public abstract void f();

    public abstract void a(byte var1);

    public final void s() {
        if (g) {
            return;
        }
        if (i == null || j == null) {
            i = new Timer();
            j = new as();
        }
        i.schedule(j, 10L, 200L);
        g = true;
    }

    protected static void t() {
        if (j != null) {
            j.cancel();
            j = null;
        }
        if (i != null) {
            i.cancel();
            i = null;
            System.gc();
        }
        g = false;
        k = true;
    }

    public static void u() {
        k = false;
    }

    public static boolean v() {
        return k;
    }

    public static void a(short i0, short i1) {
        a = i0;
        b = i1;
    }

    public static short w() {
        return a;
    }

    public static short x() {
        return b;
    }

    public static short y() {
        return (short)(a / 2);
    }

    public static short z() {
        return (short)(b / 2);
    }

    public static void A() {
        c = 66;
    }

    public static int B() {
        return c;
    }

    public static void e(int i0) {
        f = i0;
    }

    public static int C() {
        return f;
    }

    public static Font D() {
        if (d == null) {
            d = Font.getFont((int)0, (int)0, (int)8);
        }
        return d;
    }

    public static Font E() {
        if (e == null) {
            e = Font.getFont((int)0, (int)0, (int)16);
        }
        return e;
    }

    public static int F() {
        if (d == null) {
            return 18;
        }
        return d.stringWidth("S\u1ee7ng");
    }

    public static int G() {
        return d.getHeight();
    }

    public static String f(int i0) {
        if (i0 == 0) {
            return "";
        }
        return aq.d[i0];
    }

    public static String a(int i0, int[] v1) {
        if (i0 == 0) {
            return "";
        }
        int i2 = 0;
        String v3 = "";
        int i4 = an.f(i0).indexOf("%s", 0);
        if (i4 == -1) {
            return an.f(i0);
        }
        int i5 = 0;
        while (i4 != -1) {
            v3 = v3 + an.f(i0).substring(i5, i4) + v1[i2];
            ++i2;
            i5 = i4 + 2;
            i4 = an.f(i0).indexOf("%s", i5);
        }
        return v3 + an.f(i0).substring(i5);
    }

    public static String a(String v0, int[] v1) {
        if (v0.equals("")) {
            return "";
        }
        int i2 = 0;
        String v3 = "";
        int i4 = v0.indexOf("%s", 0);
        if (i4 == -1) {
            return v0;
        }
        int i5 = 0;
        while (i4 != -1) {
            v3 = v3 + v0.substring(i5, i4) + v1[i2];
            ++i2;
            i5 = i4 + 2;
            i4 = v0.indexOf("%s", i5);
        }
        return v3 + v0.substring(i5);
    }

    public static String a(int i0, String[] v1) {
        if (i0 == 0) {
            return "";
        }
        int i2 = 0;
        String v3 = "";
        int i4 = an.f(i0).indexOf("%s", 0);
        if (i4 == -1) {
            return an.f(i0);
        }
        int i5 = 0;
        while (i4 != -1) {
            v3 = v3 + an.f(i0).substring(i5, i4) + v1[i2];
            ++i2;
            i5 = i4 + 2;
            i4 = an.f(i0).indexOf("%s", i5);
        }
        return v3 + an.f(i0).substring(i5);
    }

    public static void a(Graphics v0, Image v1, String v2, int i3, int i4, int i5, int i6) {
        for (int i7 = 0; i7 < v2.length(); ++i7) {
            int i8 = v2.charAt(i7);
            if (Character.isDigit((char)i8)) {
                i8 = (char)(i8 - 48);
            } else {
                switch (i8) {
                    case 45: {
                        i8 = 10;
                        break;
                    }
                    case 43: {
                        i8 = 10;
                    }
                }
            }
            v0.drawRegion(v1, i8 * i5, 0, i5, i6, 0, i3 - ((v2.length() - 1 - (i7 << 1)) * i5 >> 1), i4, 20);
        }
    }

    public static boolean H() {
        return U != -1;
    }

    public void l() {
    }

    public void m() {
    }

    public static boolean I() {
        if (U == -1) {
            return true;
        }
        return h[U][0] == 1;
    }

    public static boolean J() {
        if (U == -1) {
            return true;
        }
        return h[U][0] == 2;
    }

    public static boolean b(int i0, int i1) {
        if (U == -1) {
            return false;
        }
        if (i1 != h[U][2]) {
            return true;
        }
        if (h[U][1] == -1) {
            return true;
        }
        return h[U][1] == i0;
    }

    public static void c(int i0, int i1) {
        if (U == -1) {
            return;
        }
        if (i1 == -1) {
            an.h[an.U][2] = 0;
        }
        an.h[an.U][i0] = (byte)i1;
    }

    public static byte K() {
        if (U == -1) {
            return -1;
        }
        return h[U][1];
    }

    public final void b(boolean i1) {
        if (this.l == 4) {
            if (i1) {
                an v1 = this;
                v1.p = (byte)(v1.p + 1);
                byte by = v1.o;
                m[by] = (byte)(m[by] + 1);
                System.out.println(" curNum = " + v1.p + " tolNum = " + v1.q);
                if (v1.p >= v1.q) {
                    switch (v1.o) {
                        case 0: {
                            X = true;
                            game.g.o().s(2000);
                            game.g.o().c(1, 5, (byte)0);
                            game.g.o().c(4, 5, (byte)0);
                            game.g.o().c(11, 2, (byte)0);
                            game.g.o().u(5);
                            game.c.a().b[game.k.a((int)9, (int)0)][5] = 3;
                            game.c.a().a[5].a((byte)3);
                            break;
                        }
                        case 1: {
                            game.g.o().c(0, 1, (byte)0);
                            break;
                        }
                        case 2: {
                            game.g.o().s(10000);
                            break;
                        }
                        case 3: {
                            game.k.G = 0;
                            if (game.k.F == null) {
                                game.k.F = new Vector();
                            }
                            if (game.k.E == null) {
                                game.k.E = new Vector();
                            }
                            game.k.F.removeAllElements();
                            game.k.E.removeAllElements();
                            for (int i2 = 0; i2 < game.g.o().A; ++i2) {
                                if (game.g.o().z[i2].s() == 50) {
                                    game.g.o().z[i2].J();
                                    continue;
                                }
                                game.g.o().z[i2].x();
                                if (game.g.o().z[i2].s() + 5 >= 50) {
                                    game.g.o().z[i2].h(50 - game.g.o().z[i2].s());
                                } else {
                                    game.g.o().z[i2].h(5);
                                }
                                game.g.o().z[i2].I();
                                if (game.g.o().z[i2].E() >= 5 || game.g.o().z[i2].E() >= game.g.o().z[i2].s() / 10 + 1) continue;
                                game.k.E.addElement(game.g.o().z[i2]);
                                game.k.F.addElement("" + i2);
                            }
                            if (game.k.E.size() <= 0) {
                                game.k.G = (byte)2;
                                break;
                            }
                            game.k.G = 1;
                            break;
                        }
                        case 4: {
                            game.g.o().u(10);
                        }
                    }
                }
                v1.d((byte)2);
                return;
            }
            this.d((byte)3);
        }
    }

    private boolean a() {
        if (this.n == null) {
            try {
                this.n = new ag((x)this);
                this.n.a("sms://");
            }
            catch (ClassNotFoundException classNotFoundException) {
                return false;
            }
        }
        switch (this.o) {
            case 0: {
                this.a((int)this.o);
                break;
            }
            case 1: {
                this.a((int)this.o);
                break;
            }
            case 2: {
                this.a((int)this.o);
                break;
            }
            case 3: {
                this.a((int)this.o);
                break;
            }
            case 4: {
                this.a((int)this.o);
            }
        }
        return true;
    }

    public final boolean c(byte i1) {
        this.o = i1;
        switch (i1) {
            case 0: {
                this.q = 1;
                break;
            }
            case 1: {
                this.q = 1;
                break;
            }
            case 2: {
                this.q = 1;
                break;
            }
            case 3: {
                this.q = 1;
                break;
            }
            case 4: {
                this.q = 1;
            }
        }
        this.p = 0;
        return true;
    }

    public final void d(byte i1) {
        while (true) {
            if (i1 != 5 && i1 != 0) {
                this.S.aK();
            }
            switch (i1) {
                case 1: {
                    System.out.println(" " + an.a(513, new int[]{this.q, this.p}));
                    this.S.d(an.a(513, new int[]{this.q, this.p}));
                    break;
                }
                case 4: {
                    System.out.println(" " + an.f(514));
                    this.S.d(an.f(514));
                    break;
                }
                case 2: {
                    if (this.M()) {
                        if (this.o == 0) {
                            this.S.d(an.f(515) + an.f(633));
                            break;
                        }
                        this.S.d(an.f(515));
                        break;
                    }
                    this.S.d(an.f(516));
                    System.out.println(" " + an.f(516));
                    break;
                }
                case 3: {
                    System.out.println(" " + an.f(516));
                    this.S.d(an.f(516));
                    break;
                }
                case 5: {
                    T = false;
                    this.S.aL();
                }
            }
            this.l = i1;
            if (i1 != 5) break;
            i1 = 0;
        }
    }

    public final int L() {
        return this.o;
    }

    public final boolean M() {
        return this.p >= this.q;
    }

    public final byte N() {
        return this.l;
    }

    public final byte O() {
        return this.q;
    }

    public final void g(int i1) {
        T = true;
        if (i1 == 1) {
            this.d((byte)4);
            if (!this.a()) {
                this.d((byte)3);
                return;
            }
        } else if (i1 == 2) {
            this.d((byte)5);
        }
    }

    public final void h(int i1) {
        switch (this.l) {
            case 1: {
                this.g(i1);
                return;
            }
            case 3: {
                if (i1 != 1 && i1 != 2) break;
                this.d((byte)5);
            }
        }
    }

    private void a(int i1) {
        this.b(true);
    }

    public final void a(boolean i1) {
        this.b(i1);
    }

    static {
        g = false;
        T = false;
        U = (byte)-1;
        V = 0;
        h = new byte[7][3];
        k = false;
        X = true;
        m = new byte[]{0, 0, 0, 0, 0};
    }
}

