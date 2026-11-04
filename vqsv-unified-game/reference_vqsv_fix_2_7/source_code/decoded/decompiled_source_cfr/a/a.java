/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Display
 *  javax.microedition.lcdui.Displayable
 *  javax.microedition.lcdui.Font
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 *  javax.microedition.midlet.MIDlet
 */
package a;

import a.b;
import a.b.c;
import game.GameMIDLet;
import game.e;
import game.j;
import game.k;
import game.l;
import game.m;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;

public abstract class a
extends a.c
implements e.a.a.a.b.a,
e.b.a.a.a {
    private static short k;
    private static short l;
    private static int m;
    private static Font n;
    private static Font o;
    private static int p;
    public byte a;
    public byte b;
    public c.j c;
    public k d;
    private static boolean q;
    public static boolean e;
    public static byte f;
    public static byte g;
    private static byte[][] r;
    private static Timer s;
    private static TimerTask t;
    private static boolean u;
    public static boolean h;
    public static boolean i;
    private byte v;
    private static byte[] w;
    private e.b.a.a.b x = null;
    private byte y;
    private byte z;
    private byte A;
    private String[] B = new String[]{"01", "02", "03", "04", "05"};
    private byte[][] C = new byte[][]{{4, 1, 0}, {2, 1, 1}, {2, 1, 2}, {2, 1, 3}, {2, 1, 4}};
    private String[][] D = new String[][]{{"K\u00edch ho\u1ea1t tr\u00f2 ch\u01a1i", "B\u1ea1n mu\u1ed1n kh\u00e1m ph\u00e1 b\u00ed m\u1eadt c\u1ee7a v\u01b0\u01a1ng qu\u1ed1c s\u1ee7ng v\u1eadt, d\u1eabn d\u1eaft th\u00fa y\u00eau chi\u1ebfn \u0111\u1ea5u, ti\u1ebfn h\u00f3a, \u1ea5p tr\u1ee9ng? Ch\u1ec9 c\u1ea7n 1 tin nh\u1eafn 15000\u0111 \u0111\u1ec3 k\u00edch ho\u1ea1t tr\u00f2 ch\u01a1i, ch\u1ec9 nh\u1eafn tin 1 l\u1ea7n cho t\u1ea5t c\u1ea3 c\u00e1c l\u01b0\u1ee3t ch\u01a1i. B\u1ea1n c\u00f3 mu\u1ed1n nh\u1eafn tin kh\u00f4ng?"}, {"T\u1ea5t tr\u00fang c\u1ea7u", "Ch\u1ec9 c\u1ea7n nh\u1eafn 1 tin nh\u1eafn 10000\u0111, b\u1ea1n s\u1ebd s\u1edf h\u1eefu 1 t\u1ea5t tr\u00fang c\u1ea7u, t\u1ef7 l\u1ec7 100% b\u1eaft \u0111\u01b0\u1ee3c s\u1ee7ng v\u1eadt? B\u1ea1n c\u00f3 mu\u1ed1n nh\u1eafn tin kh\u00f4ng?"}, {"Mua kim ti\u1ec1n", "Ki\u1ebfm ti\u1ec1n v\u1ea5t v\u1ea3, v\u1eadt ph\u1ea9m \u0111\u1eaft \u0111\u1ecf? Ch\u1ec9 c\u1ea7n nh\u1eafn 1 tin nh\u1eafn 10000\u0111 b\u1ea1n s\u1ebd \u0111\u1ea1t \u0111\u01b0\u1ee3c 10000 kim ti\u1ec1n. B\u1ea1n c\u00f3 mu\u1ed1n nh\u1eafn tin kh\u00f4ng?"}, {"Mua \u0111\u1eb3ng c\u1ea5p", "Th\u0103ng c\u1ea5p ch\u1eadm ch\u1ea1p, k\u1ebb \u0111\u1ecbch l\u1ea1i qu\u00e1 m\u1ea1nh? Ch\u1ec9 c\u1ea7n 1 tin nh\u1eafn 10000\u0111, t\u1ea5t c\u1ea3 s\u1ee7ng v\u1eadt trong ba l\u00f4 c\u1ee7a b\u1ea1n \u0111\u1ec1u \u0111\u01b0\u1ee3c th\u0103ng l\u00ean 5 c\u1ea5p. B\u1ea1n c\u00f3 mu\u1ed1n nh\u1eafn tin kh\u00f4ng?"}, {"Mua huy hi\u1ec7u", "Ki\u1ebfm huy hi\u1ec7u kh\u00f3 kh\u0103n? Ch\u1ec9 c\u1ea7n 1 tin nh\u1eafn 10000\u0111, b\u1ea1n s\u1ebd \u0111\u1ea1t \u0111\u01b0\u1ee3c 10 huy hi\u1ec7u. B\u1ea1n c\u00f3 mu\u1ed1n nh\u1eafn tin kh\u00f4ng?"}};
    private e.a.a.a.b.b E;

    public abstract void a();

    public abstract void a(Graphics var1);

    public abstract boolean b();

    public abstract void c();

    public abstract void a(byte var1);

    public final void d() {
        if (q) {
            return;
        }
        if (s == null || t == null) {
            s = new Timer();
            t = new b();
        }
        s.schedule(t, 10L, 200L);
        q = true;
    }

    protected static void e() {
        if (t != null) {
            t.cancel();
            t = null;
        }
        if (s != null) {
            s.cancel();
            s = null;
            System.gc();
        }
        q = false;
        u = true;
    }

    public static void a(boolean bl) {
        u = false;
    }

    public static boolean f() {
        return u;
    }

    public static void a(short s, short s2) {
        k = s;
        l = s2;
    }

    public static short g() {
        return k;
    }

    public static short h() {
        return l;
    }

    public static short i() {
        return (short)(k / 2);
    }

    public static short j() {
        return (short)(l / 2);
    }

    public static void a(int n2) {
        m = 66;
    }

    public static int k() {
        return m;
    }

    public static void b(int n2) {
        p = n2;
    }

    public static int l() {
        return p;
    }

    public static Font m() {
        if (n == null) {
            n = Font.getFont((int)0, (int)0, (int)8);
        }
        return n;
    }

    public static Font n() {
        if (o == null) {
            o = Font.getFont((int)0, (int)0, (int)16);
        }
        return o;
    }

    public static int o() {
        return n.getHeight();
    }

    public static String c(int n2) {
        if (n2 == 0) {
            return "";
        }
        return a.b.c.d[n2];
    }

    public static String a(int n2, int[] nArray) {
        if (n2 == 0) {
            return "";
        }
        int n3 = 0;
        String string = "";
        int n4 = a.a.c(n2).indexOf("%s", 0);
        if (n4 == -1) {
            return a.a.c(n2);
        }
        int n5 = 0;
        while (n4 != -1) {
            string = string + a.a.c(n2).substring(n5, n4) + nArray[n3];
            ++n3;
            n5 = n4 + 2;
            n4 = a.a.c(n2).indexOf("%s", n5);
        }
        return string + a.a.c(n2).substring(n5);
    }

    public static String a(String string, int[] nArray) {
        if (string.equals("")) {
            return "";
        }
        int n2 = 0;
        String string2 = "";
        int n3 = string.indexOf("%s", 0);
        if (n3 == -1) {
            return string;
        }
        int n4 = 0;
        while (n3 != -1) {
            string2 = string2 + string.substring(n4, n3) + nArray[n2];
            ++n2;
            n4 = n3 + 2;
            n3 = string.indexOf("%s", n4);
        }
        return string2 + string.substring(n4);
    }

    public static String a(int n2, String[] stringArray) {
        if (n2 == 0) {
            return "";
        }
        int n3 = 0;
        String string = "";
        int n4 = a.a.c(n2).indexOf("%s", 0);
        if (n4 == -1) {
            return a.a.c(n2);
        }
        int n5 = 0;
        while (n4 != -1) {
            string = string + a.a.c(n2).substring(n5, n4) + stringArray[n3];
            ++n3;
            n5 = n4 + 2;
            n4 = a.a.c(n2).indexOf("%s", n5);
        }
        return string + a.a.c(n2).substring(n5);
    }

    public static void a(Graphics graphics, Image image, String string, int n2, int n3, int n4, int n5, int n6) {
        for (n6 = 0; n6 < string.length(); ++n6) {
            int n7 = string.charAt(n6);
            if (Character.isDigit((char)n7)) {
                n7 = (char)(n7 - 48);
            } else {
                switch (n7) {
                    case 45: {
                        n7 = 10;
                        break;
                    }
                    case 43: {
                        n7 = 10;
                    }
                }
            }
            graphics.drawRegion(image, n7 * n4, 0, n4, n5, 0, n2 - ((string.length() - 1 - (n6 << 1)) * n4 >> 1), n3, 20);
        }
    }

    public static boolean p() {
        return f != -1;
    }

    public void q() {
    }

    public void r() {
    }

    public static boolean s() {
        if (f == -1) {
            return true;
        }
        return r[f][0] == 1;
    }

    public static boolean t() {
        if (f == -1) {
            return true;
        }
        return r[f][0] == 2;
    }

    public static boolean a(int n2, int n3) {
        if (f == -1) {
            return false;
        }
        if (n3 != r[f][2]) {
            return true;
        }
        if (r[f][1] == -1) {
            return true;
        }
        return r[f][1] == n2;
    }

    public static void b(int n2, int n3) {
        if (f == -1) {
            return;
        }
        if (n3 == -1) {
            a.a.r[a.a.f][2] = 0;
        }
        a.a.r[a.a.f][n2] = (byte)n3;
    }

    public static byte d(int n2) {
        if (f == -1) {
            return -1;
        }
        return r[f][1];
    }

    public static void u() {
        a.a.b(1, -1);
        a.a.b(0, 0);
        f = (byte)-1;
        g = 0;
    }

    public final void b(boolean bl) {
        if (this.v == 4) {
            if (bl) {
                a a2 = this;
                a2.z = (byte)(a2.z + 1);
                byte by = a2.y;
                w[by] = (byte)(w[by] + 1);
                System.out.println(" curNum = " + a2.z + " tolNum = " + a2.A);
                if (a2.z >= a2.A) {
                    switch (a2.y) {
                        case 0: {
                            i = true;
                            game.j.p().s(2000);
                            game.j.p().c(1, 5, (byte)0);
                            game.j.p().c(4, 5, (byte)0);
                            game.j.p().c(11, 2, (byte)0);
                            game.j.p().v(5);
                            game.e.B().n[game.l.e((int)9, (int)0)][5] = 3;
                            game.e.B().l[5].a((byte)3);
                            break;
                        }
                        case 1: {
                            game.j.p().c(0, 1, (byte)0);
                            break;
                        }
                        case 2: {
                            game.j.p().s(10000);
                            break;
                        }
                        case 3: {
                            game.l.Q = 0;
                            if (game.l.P == null) {
                                game.l.P = new Vector();
                            }
                            if (game.l.O == null) {
                                game.l.O = new Vector();
                            }
                            game.l.P.removeAllElements();
                            game.l.O.removeAllElements();
                            for (int i2 = 0; i2 < game.j.p().B; ++i2) {
                                if (game.j.p().A[i2].t() == 50) {
                                    game.j.p().A[i2].K();
                                    continue;
                                }
                                game.j.p().A[i2].y();
                                if (game.j.p().A[i2].t() + 5 >= 50) {
                                    game.j.p().A[i2].h(50 - game.j.p().A[i2].t());
                                } else {
                                    game.j.p().A[i2].h(5);
                                }
                                game.j.p().A[i2].J();
                                if (game.j.p().A[i2].F() >= 5 || game.j.p().A[i2].F() >= game.j.p().A[i2].t() / 10 + 1) continue;
                                game.l.O.addElement(game.j.p().A[i2]);
                                game.l.P.addElement("" + i2);
                            }
                            if (game.l.O.size() <= 0) {
                                game.l.Q = (byte)2;
                                break;
                            }
                            game.l.Q = 1;
                            break;
                        }
                        case 4: {
                            game.j.p().v(10);
                        }
                    }
                }
                a2.c((byte)2);
                return;
            }
            this.c((byte)3);
        }
    }

    private boolean B() {
        if (this.x == null) {
            try {
                this.x = new e.b.a.a.b(this);
                this.x.a("sms://");
            }
            catch (ClassNotFoundException classNotFoundException) {
                return false;
            }
        }
        switch (this.y) {
            case 0: {
                this.l(this.y);
                break;
            }
            case 1: {
                this.l(this.y);
                break;
            }
            case 2: {
                this.l(this.y);
                break;
            }
            case 3: {
                this.l(this.y);
                break;
            }
            case 4: {
                this.l(this.y);
            }
        }
        return true;
    }

    public final boolean b(byte by) {
        this.y = by;
        switch (by) {
            case 0: {
                this.A = 1;
                break;
            }
            case 1: {
                this.A = 1;
                break;
            }
            case 2: {
                this.A = 1;
                break;
            }
            case 3: {
                this.A = 1;
                break;
            }
            case 4: {
                this.A = 1;
            }
        }
        this.z = 0;
        return true;
    }

    public final void c(byte by) {
        while (true) {
            if (by != 5 && by != 0) {
                this.d.aM();
            }
            switch (by) {
                case 1: {
                    System.out.println(" " + a.a.a(513, new int[]{this.A, this.z}));
                    this.d.d(a.a.a(513, new int[]{this.A, this.z}));
                    break;
                }
                case 4: {
                    System.out.println(" " + a.a.c(514));
                    this.d.d(a.a.c(514));
                    break;
                }
                case 2: {
                    if (this.w()) {
                        if (this.y == 0) {
                            this.d.d(a.a.c(515) + a.a.c(633));
                            break;
                        }
                        this.d.d(a.a.c(515));
                        break;
                    }
                    this.d.d(a.a.c(516));
                    System.out.println(" " + a.a.c(516));
                    break;
                }
                case 3: {
                    System.out.println(" " + a.a.c(516));
                    this.d.d(a.a.c(516));
                    break;
                }
                case 5: {
                    e = false;
                    this.d.aN();
                }
            }
            this.v = by;
            if (by != 5) break;
            by = 0;
        }
    }

    public final int v() {
        return this.y;
    }

    public final boolean w() {
        return this.z >= this.A;
    }

    public final byte x() {
        return this.v;
    }

    public final byte y() {
        return this.A;
    }

    public final void e(int n2) {
        e = true;
        if (n2 == 1) {
            this.c((byte)4);
            if (!this.B()) {
                this.c((byte)3);
                return;
            }
        } else if (n2 == 2) {
            this.c((byte)5);
        }
    }

    public final void f(int n2) {
        switch (this.v) {
            case 1: {
                this.e(n2);
                return;
            }
            case 3: {
                if (n2 != 1 && n2 != 2) break;
                this.c((byte)5);
            }
        }
    }

    private void l(int n2) {
        a.a(GameMIDLet.a);
        a.a(n2);
        this.C[n2][0] = 1;
        this.E = new e.a.a.a.b.b(GameMIDLet.a, (Displayable)game.m.a, "", "", this.B[n2], null, this.C[n2][0], this.D[n2][0], a.c[n2], "");
        Display.getDisplay((MIDlet)GameMIDLet.a).setCurrent((Displayable)this.E);
        this.E.a(this);
    }

    public final void c(boolean bl) {
        this.b(bl);
    }

    static {
        q = false;
        e = false;
        f = (byte)-1;
        g = 0;
        r = new byte[7][3];
        u = false;
        i = false;
        w = new byte[]{0, 0, 0, 0, 0};
    }
}

