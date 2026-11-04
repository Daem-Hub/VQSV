/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
package game;

import a.a.g;
import a.b.c;
import game.a;
import game.d;
import game.e;
import game.f;
import game.h;
import game.j;
import game.k;
import game.n;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class l
extends a.a {
    private static l ac;
    public a.b.d k;
    private a.b.b ad;
    public a.a.h l;
    public j m;
    public h[] n;
    public Vector o;
    private int ae;
    public int p;
    public int q;
    private int af;
    public short r;
    public short s;
    public int t;
    public String u;
    public static int[] v;
    public static Image w;
    private static Image ag;
    public static a.a.b x;
    private static a.b.e[] ah;
    private static byte[][][] ai;
    private static short[][][] aj;
    private static boolean[][] ak;
    private static String[] al;
    private static short[][] am;
    private static Vector an;
    private static Vector ao;
    private static Vector ap;
    private static Vector aq;
    public g y;
    private g ar;
    public g z;
    public static int A;
    public static byte B;
    public static byte C;
    public static byte D;
    public static short E;
    public static short F;
    public static byte G;
    public static boolean H;
    public static int I;
    public static int J;
    public static int K;
    public static int L;
    public static int M;
    private static byte[] as;
    private int[] at;
    private int[] au;
    private short[] av;
    private static String[] aw;
    public static boolean N;
    public static Vector O;
    public static Vector P;
    public static byte Q;
    public static Vector R;
    public static byte S;
    public static boolean T;
    protected static boolean U;
    public static byte[] V;
    private Image[] ax;
    public a.a.e W;
    private static byte[][] ay;
    public static byte X;
    public static boolean Y;
    private static byte[] az;
    private static byte aA;
    public e Z;
    public static String[] aa;
    private int aB;
    private String aC;
    private byte aD;
    private byte aE;
    private byte aF;
    private byte aG;
    private int aH;
    private int aI;
    private int aJ;
    private int aK;
    private boolean aL;
    private g aM;
    private g[] aN;
    private int aO;
    private int[] aP;
    private int[] aQ;
    private int[] aR;
    private int[][] aS;
    private int[] aT;
    public static byte ab;
    private boolean aU;
    private boolean[] aV;
    private byte[] aW;
    private byte aX;
    private byte aY;
    private boolean aZ;

    public static l B() {
        if (ac == null) {
            ac = new l();
        }
        return ac;
    }

    public l() {
        game.d.a();
        this.o = new Vector();
        this.p = 0;
        this.q = 0;
        this.af = 0;
        this.r = (short)224;
        this.s = (short)496;
        this.t = -1;
        this.u = "G\u1ed7 th\u00f4";
        this.y = null;
        this.ar = null;
        this.z = null;
        this.at = new int[]{21, 35, 50, 0, 45};
        this.au = new int[]{9, 0, 20, 3, 9, 1, 17, 1, 9, 2, 9, 4, 9, 6, 86, 5, 9, 6, 58, 6, 9, 5, 21, 2, 9, 4, 3, 0};
        this.av = new short[]{1, 5, 0, 616, 3, 6, 0, 617, 4, 0, 0, 618, 5, 2, 0, 619, 6, 0, 1, 620};
        this.W = null;
        this.aB = 0;
        this.aC = "Ngo\u1ea1i tr\u1eeb ti\u1ebfn h\u00f3a, s\u1ee7ng v\u1eadt c\u00f2n c\u00f3 th\u1ec3 d\u1ecb ho\u00e1, d\u1ecb ho\u00e1 sau s\u1ee7ng v\u1eadt \u0111em c\u00e0ng c\u1ee5 t\u00ednh c\u00f4ng k\u00edch. M\u1eb7t kh\u00e1c t\u1eebng ch\u1ee7 th\u00e0nh li\u00ean minh hu\u1ea5n luy\u1ec7n s\u01b0 c\u0169ng s\u1ebd cung c\u1ea5p ti\u1ebfn h\u00f3a c\u00f9ng d\u1ecb ho\u00e1 ph\u1ee5c v\u1ee5, ng\u01b0\u01a1i c\u00f3 th\u1ec3 th\u01b0\u1eddng \u0111i xem.";
        this.aL = false;
        this.aM = null;
        this.aN = null;
        this.aO = 8;
        this.aP = new int[]{2, 1, 73, 158, 3, 3, 216, 165, 4, 5, 161, 338, 5, 3, 111, 385, 5, 5, 112, 124, 6, 1, 140, 100, 7, 2, 48, 58};
        this.aQ = new int[]{1, 5, 265, 113, 3, 6, 281, 192, 4, 0, 24, 144, 5, 2, 88, 175, 6, 0, 55, 190};
        this.aR = new int[]{16735795, 5708544, 5693667, 28273, 7796622, 1924393, 16774529, 7760896, 3291479, 10268671, 2038828, 13341951, 4443391, 0xFFFFFF, 1862959, 13886935};
        this.aS = new int[][]{{0, 0, 1, 0, 386, 5, 5, 5, 0, 1, 1, 387, 5, 5, 0, 5, 1, 2, 388, 5, 5, 0, 10, 1, 3, 389, 5, 5, 5, 10, 1, 4, 390, 5, 5, 10, 10, 1, 5, 391, 5, 5, 10, 15, 1, 6, 392, 5, 5, 10, 20, -1, -1, 518, 5, 5}, {0, 0, -1, -1, 517, 5, 5, 0, 5, 2, 0, 393, 5, 5, 0, 10, 2, 1, 394, 5, 5, 0, 15, 2, 2, 395, 5, 5, 5, 10, 2, 3, 396, 5, 5, 5, 5, 2, 4, 397, 5, 5, 5, 15, 2, 5, 398, 5, 5, 5, 20, 2, 6, 399, 5, 5, 5, 25, 2, 7, 400, 5, 5}, {15, 0, -1, -1, 518, 5, 5, 15, 5, 3, 0, 401, 5, 5, 15, 10, 3, 1, 402, 5, 5, 10, 10, 3, 2, 403, 5, 5, 10, 15, 3, 3, 404, 5, 5, 5, 15, 3, 4, 405, 5, 5, 0, 15, 3, 5, 406, 5, 5, 15, 15, 3, 6, 407, 5, 5, 15, 20, 3, 7, 408, 5, 5}, {0, 15, 4, 0, 409, 5, 5, 5, 15, 4, 1, 410, 5, 5, 10, 15, 4, 5, 414, 5, 5, 10, 20, 4, 6, 415, 5, 5, 15, 20, 4, 7, 416, 5, 5, 20, 20, 4, 8, 417, 5, 5, 15, 15, 4, 9, 418, 5, 5, 20, 15, 4, 10, 419, 5, 5, 15, 10, 4, 11, 420, 5, 5, 15, 5, 4, 12, 421, 5, 5, 0, 10, 4, 2, 411, 5, 5, 5, 10, 4, 3, 412, 5, 5, 10, 10, 4, 4, 413, 5, 5, 15, 0, -1, -1, 524, 5, 5}, {10, 5, 5, 0, 422, 5, 5, 5, 5, 5, 1, 423, 5, 5, 0, 5, 5, 2, 424, 5, 5, 5, 0, 5, 3, 425, 5, 5, 15, 5, 5, 4, 426, 5, 5, 20, 5, 5, 5, 427, 5, 5, 18, 0, 5, 6, 428, 5, 5, 10, 10, -1, -1, 522, 5, 5}, {0, 5, 6, 0, 429, 5, 5, 0, 0, 6, 1, 430, 5, 5}, {5, 15, 7, 0, 431, 5, 5, 5, 10, 7, 1, 432, 5, 5, 5, 5, 7, 2, 433, 5, 5, 0, 5, 7, 3, 434, 5, 5, 0, 0, 7, 4, 435, 5, 5, 0, 10, 7, 5, 436, 5, 5, 0, 15, 7, 6, 437, 5, 5, 10, 5, 7, 7, 438, 5, 5, 10, 0, 7, 8, 439, 5, 5, 15, 0, 7, 9, 440, 5, 5, 15, 5, 7, 10, 441, 5, 5, 10, 10, 7, 11, 442, 5, 5, 10, 15, 7, 12, 443, 5, 5}, {5, 10, 8, 0, 444, 5, 5, 5, 15, 8, 1, 445, 5, 5, 0, 15, 8, 2, 446, 5, 5, 0, 10, 8, 3, 447, 5, 5, 0, 5, 8, 4, 448, 5, 5, 5, 5, 8, 5, 449, 5, 5, 5, 0, 8, 6, 450, 5, 5}};
        this.aT = new int[]{3, 5, 2, 6, 4, 5, 5, 5, 5, 3, 1, 2, 4, 4, 2, 4};
        this.aU = false;
        this.aV = new boolean[]{false, false, false, false, false, false, false};
        this.aW = new byte[]{10, 15, 20, 30, 40, 50, 100};
        this.aZ = false;
        this.k = a.b.d.a();
        this.ad = a.b.b.a();
        this.l = new a.a.h();
    }

    public final boolean C() {
        return am[v[this.p] + this.q][2] != -1;
    }

    public static byte a(byte by, byte by2) {
        if (aA != -1) {
            for (int i2 = 0; i2 < aA; ++i2) {
                if (az[i2 * 3] != by) continue;
                switch (by2) {
                    case 0: {
                        return az[i2 * 3 + 1];
                    }
                    case 1: {
                        return az[i2 * 3 + 2];
                    }
                }
            }
        }
        return -1;
    }

    public final boolean b() {
        try {
            int n2;
            Object object;
            int n3;
            int n4;
            this.d();
            if (ah == null) {
                ah = new a.b.e[10];
            }
            for (int i2 = 0; i2 < ah.length; ++i2) {
                if (ah[i2] != null) continue;
                game.l.ah[i2] = new a.b.e(aw[i2], 1);
            }
            this.Z = game.e.B();
            this.Z.a(this);
            this.m = game.j.p();
            game.l.V[1] = -1;
            game.l.V[0] = -1;
            if (game.e.k != 0) {
                a.b.c.b();
            }
            if (ai == null) {
                ai = new byte[127][][];
                aj = new short[127][][];
                ak = new boolean[127][2];
            }
            if (!this.m.z) {
                if (h) {
                    this.R();
                    this.T();
                }
                game.l.V();
            }
            this.a(this.p, this.q, "/data/event/");
            a.a.f.b();
            Object object2 = this;
            am = a.e.a(a.e.a("/data/script/petArea.mid"));
            if (((l)object2).C()) {
                int[] nArray = new int[am[v[((l)object2).p] + ((l)object2).q].length - 5];
                for (n4 = 0; n4 < nArray.length; ++n4) {
                    nArray[n4] = am[v[((l)object2).p] + ((l)object2).q][n4 + 5];
                }
                block10: for (n4 = 0; n4 < nArray.length / 4; ++n4) {
                    object2 = new int[4];
                    switch (nArray[(n4 << 2) + 1]) {
                        case 0: {
                            System.arraycopy(nArray, n4 << 2, object2, 0, ((Object)object2).length);
                            an.addElement(object2);
                            continue block10;
                        }
                        case 1: {
                            System.arraycopy(nArray, n4 << 2, object2, 0, ((Object)object2).length);
                            ao.addElement(object2);
                            continue block10;
                        }
                        case 2: {
                            System.arraycopy(nArray, n4 << 2, object2, 0, ((Object)object2).length);
                            ap.addElement(object2);
                            continue block10;
                        }
                        case 4: {
                            System.arraycopy(nArray, n4 << 2, object2, 0, ((Object)object2).length);
                            aq.addElement(object2);
                        }
                    }
                }
            }
            object2 = this;
            InputStream inputStream = a.e.a("/data/script/media.mid");
            ay = a.e.b(inputStream);
            byte by = ay[v[n4 = ((l)object2).p] + (n3 = ((l)object2).q)][0];
            Y = by == -1 ? false : ((l)object2).Z.n[v[n4 = ((l)object2).p] + (n3 = ((l)object2).q)][by] != 3;
            n3 = ((l)object2).q;
            n4 = ((l)object2).p;
            X = ay[v[n4] + n3][1];
            n3 = ((l)object2).q;
            n4 = ((l)object2).p;
            int cfr_ignored_0 = v[n4] + n3;
            aA = ay[game.l.e(((l)object2).p, ((l)object2).q)][3];
            if (aA == -1) {
                if (!((l)object2).C()) {
                    byte[] byArray = new byte[1];
                    object = byArray;
                    byArray[0] = X;
                } else {
                    byte[] byArray = new byte[2];
                    object = byArray;
                    byArray[0] = X;
                    object[1] = X;
                }
            } else {
                if (!((l)object2).C()) {
                    object = new byte[(aA << 1) + 1];
                    for (n4 = 0; n4 < aA; ++n4) {
                        object[n4 << 1] = ay[game.l.e(((l)object2).p, ((l)object2).q)][4 + n4 * 3 + 1];
                        object[(n4 << 1) + 1] = ay[game.l.e(((l)object2).p, ((l)object2).q)][4 + n4 * 3 + 2];
                    }
                    object[game.l.aA << 1] = X;
                } else {
                    object = new byte[(aA << 1) + 2];
                    for (n4 = 0; n4 < aA; ++n4) {
                        object[n4 << 1] = ay[game.l.e(((l)object2).p, ((l)object2).q)][4 + n4 * 3 + 1];
                        object[(n4 << 1) + 1] = ay[game.l.e(((l)object2).p, ((l)object2).q)][4 + n4 * 3 + 2];
                    }
                    object[game.l.aA << 1] = X;
                    object[(game.l.aA << 1) + 1] = 4;
                }
                az = new byte[aA * 3];
                System.arraycopy(ay[game.l.e(((l)object2).p, ((l)object2).q)], 4, az, 0, aA * 3);
            }
            if (((l)object2).W == null) {
                ((l)object2).W = new a.a.e(7, -1, 0, "/data/sound/");
            }
            ((l)object2).W.a((byte[])object);
            ((l)object2).W.b(game.f.B().r);
            object2 = this;
            object = a.e.a("/data/script/petRide.mid");
            ((l)object2).m.R = a.e.b((InputStream)object)[v[((l)object2).p] + ((l)object2).q];
            if (((l)object2).m.u >= 0 && !((l)object2).m.g(((l)object2).m.u)) {
                ((l)object2).m.t();
            }
            ((l)object2).aD = ((l)object2).m.R[4];
            ((l)object2).aE = (byte)-1;
            for (n2 = 0; n2 < ((l)object2).aP.length / 4; n2 = (int)((byte)(n2 + 1))) {
                if (((l)object2).p != ((l)object2).aP[n2 << 2] || ((l)object2).q != ((l)object2).aP[(n2 << 2) + 1]) continue;
                ((l)object2).aE = (byte)n2;
                break;
            }
            ((l)object2).aF = (byte)-1;
            for (n2 = 0; n2 < ((l)object2).aQ.length / 4; n2 = (int)((byte)(n2 + 1))) {
                if (((l)object2).p != ((l)object2).aQ[n2 << 2] || ((l)object2).q != ((l)object2).aQ[(n2 << 2) + 1]) continue;
                ((l)object2).aF = (byte)n2;
                break;
            }
            object2 = this;
            w = null;
            short[][] sArray = a.e.a(a.e.a("/data/script/backPic.mid"));
            for (n4 = 0; n4 < sArray.length; ++n4) {
                if (sArray[n4][0] == ((l)object2).p && sArray[n4][1] == ((l)object2).q) {
                    if (sArray[n4][2] == 0) {
                        w = a.e.b("/data/img/", "img_" + sArray[n4][3]);
                        break;
                    }
                    if (sArray[n4][2] != 1) break;
                    a.a.b(sArray[n4][3] << 16 | sArray[n4][4] << 8 | sArray[n4][5]);
                    break;
                }
                a.a.b(2996676);
            }
            ag = a.e.b("/data/tex/", "gold");
            a.e.b("/data/img/", "img_10023");
            if (this.m.x > 0) {
                this.e(true);
            }
            object2 = this;
            this.u = game.l.c(384 + v[((l)object2).p] + ((l)object2).q);
            ((l)object2).k.a(((l)object2).ae);
            ((l)object2).k.a(0, 0);
            ((l)object2).l.a(((l)object2).k);
            this.ae();
            if (H) {
                game.e.r = false;
                this.ad();
                object2 = this;
                ((l)object2).ad.a(((l)object2).m, L, M, true);
                ((l)object2).l.a(((l)object2).ad);
                ((l)object2).l.b();
            } else {
                if (!this.m.z) {
                    object2 = new short[]{this.r, this.s, G, 4, 4, 8, 40, 100, 0};
                    this.m.a((short[])object2);
                    this.m.H();
                }
                if (I == -1) {
                    this.ad.a(J, K, L, M, true);
                    this.l.a(this.ad);
                    this.l.b();
                } else {
                    this.ad.a(this.n[I], L, M, true);
                    this.l.a(this.ad);
                    this.l.b();
                }
                this.m.a(false);
                H = true;
            }
            this.P();
            if (this.p == 3 && this.q == 7) {
                if (this.m.y > 0) {
                    this.m.y = 0;
                    this.m.b(0);
                }
                this.ax = new Image[4];
                for (int i3 = 0; i3 < this.ax.length; ++i3) {
                    this.ax[i3] = a.e.b("/data/tex/", "down" + i3);
                }
                this.m.t();
                this.m.h(0);
            }
            if (this.p == 5 && this.q == 6 || this.p == 4 && (this.q == 3 || this.q == 4)) {
                if (this.m.C[0][0] == 2) {
                    a.a.f.a().a((byte)0, this.ad.m(), this.ad.n() - this.at[this.m.u + 1], game.l.g(), game.l.h(), 110, 110);
                } else {
                    a.a.f.a().a((byte)0, this.ad.m(), this.ad.n() - this.at[this.m.u + 1], game.l.g(), game.l.h(), 50, 50);
                }
            } else {
                a.a.f.a().a((byte)-1);
            }
            this.d = game.k.a();
            this.d.a(this);
            this.c = c.j.a();
            this.Z.G();
            this.Z.a();
            T = true;
            this.a((byte)0);
            if (game.e.k == 2) {
                game.e.k = 0;
            }
            if (!Y) {
                this.W.a(X, 1);
            }
            game.l.e();
        }
        catch (Exception exception) {
            a.a.a.a(exception, "init");
        }
        return true;
    }

    private void P() {
        for (int i2 = 0; i2 < this.n.length; ++i2) {
            this.n[i2].f();
            this.l.a(this.n[i2]);
        }
    }

    public final void a(byte by, int n2, int n3, g g2) {
        if (this.z == null) {
            this.z = new g();
            this.z.a(259, false);
            this.z.a.a((byte)13, (byte)-1);
            this.l.a(this.z);
            g g3 = this.z;
            g3.a.c();
            this.z.t = 0;
            this.z.c();
        }
        this.z.b(n2, n3);
        g g4 = g2;
        g g5 = this.z;
        this.z.q = g4;
    }

    public final void D() {
        if (this.z != null) {
            this.l.b(this.z);
            this.z = null;
        }
    }

    public final void l(int n2) {
        if (this.y == null) {
            this.y = new g();
            this.y.a(n2, false);
            g g2 = this.y;
            g2.a.c();
            this.y.t = 1;
        }
    }

    public final void a(g g2) {
        if (this.y == null) {
            return;
        }
        this.ar = g2;
        g g3 = g2;
        g g4 = this.y;
        this.y.q = g3;
        g4 = g2;
        this.y.e(g4.a.g());
        this.y.c();
        this.l.a(this.y);
        if (w != null) {
            this.y.a(true);
            return;
        }
        this.y.a(false);
    }

    public final void E() {
        if (this.y != null) {
            this.l.b(this.y);
            this.y = null;
        }
    }

    private boolean a(j j2) {
        try {
            int n2;
            int n3;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            if (this.p == 9) {
                dataOutputStream.writeShort(this.d.m[(this.q << 2) + 2]);
                dataOutputStream.writeShort(this.d.m[(this.q << 2) + 3]);
                dataOutputStream.writeByte(2);
            } else if (this.p == 3 && this.q == 7) {
                dataOutputStream.writeShort(240);
                dataOutputStream.writeShort(40);
                dataOutputStream.writeByte(0);
            } else {
                dataOutputStream.writeShort(j2.j);
                dataOutputStream.writeShort(j2.k);
                dataOutputStream.writeByte(j2.o);
            }
            int n4 = 0;
            while (n4 < j2.C.length) {
                n3 = 0;
                while (n3 < j2.C[n4].length) {
                    dataOutputStream.writeByte(j2.C[n4][n3]);
                    ++n3;
                }
                ++n4;
            }
            n4 = 0;
            while (n4 < j2.Q.length) {
                dataOutputStream.writeByte(j2.Q[n4]);
                ++n4;
            }
            n4 = 0;
            while (n4 < j2.D.length) {
                n3 = 0;
                while (n3 < j2.D[n4].length) {
                    dataOutputStream.writeByte(j2.D[n4][n3]);
                    ++n3;
                }
                ++n4;
            }
            n4 = 0;
            while (n4 < j2.F.length) {
                dataOutputStream.writeByte(j2.F[n4]);
                ++n4;
            }
            n4 = 0;
            while (n4 < j2.E.length) {
                n3 = 0;
                while (n3 < j2.E[n4].length) {
                    dataOutputStream.writeByte(this.m.E[n4][n3]);
                    ++n3;
                }
                ++n4;
            }
            dataOutputStream.writeByte(this.m.I);
            dataOutputStream.writeByte(this.m.H);
            dataOutputStream.writeByte(this.m.G);
            dataOutputStream.writeByte(this.m.J);
            n4 = 0;
            while (n4 < this.m.S.length) {
                dataOutputStream.writeByte(this.m.S[n4]);
                ++n4;
            }
            if (!this.H()) {
                return false;
            }
            if (!this.aa()) {
                return false;
            }
            if (!this.Y()) {
                return false;
            }
            dataOutputStream.writeInt(j2.M.size());
            n4 = 0;
            while (n4 < j2.M.size()) {
                int[] nArray = (int[])j2.M.elementAt(n4);
                dataOutputStream.writeInt(nArray.length);
                n2 = 0;
                while (n2 < nArray.length) {
                    dataOutputStream.writeInt(nArray[n2]);
                    ++n2;
                }
                ++n4;
            }
            dataOutputStream.writeInt(j2.N.size());
            n4 = 0;
            while (n4 < j2.N.size()) {
                int[] nArray = (int[])j2.N.elementAt(n4);
                dataOutputStream.writeInt(nArray.length);
                n2 = 0;
                while (n2 < nArray.length) {
                    dataOutputStream.writeInt(nArray[n2]);
                    ++n2;
                }
                ++n4;
            }
            dataOutputStream.writeInt(j2.O.size());
            n4 = 0;
            while (n4 < j2.O.size()) {
                int[] nArray = (int[])j2.O.elementAt(n4);
                dataOutputStream.writeInt(nArray.length);
                n2 = 0;
                while (n2 < nArray.length) {
                    dataOutputStream.writeInt(nArray[n2]);
                    ++n2;
                }
                ++n4;
            }
            n4 = 0;
            while (n4 < j2.U.length) {
                dataOutputStream.writeBoolean(j2.U[n4]);
                ++n4;
            }
            if (!this.W()) {
                return false;
            }
            if (P == null) {
                P = new Vector();
            }
            dataOutputStream.writeByte(P.size());
            n4 = 0;
            while (n4 < P.size()) {
                String string = (String)P.elementAt(n4);
                dataOutputStream.writeByte(a.e.d(string));
                ++n4;
            }
            n4 = 0;
            while (n4 < this.aV.length) {
                dataOutputStream.writeBoolean(this.aV[n4]);
                ++n4;
            }
            if (this.y == null) {
                dataOutputStream.writeByte(-1);
            } else {
                dataOutputStream.writeByte(this.y.a.a);
            }
            dataOutputStream.write(this.m.v);
            dataOutputStream.writeInt(A);
            dataOutputStream.writeBoolean(U);
            long l2 = game.f.B().n + game.f.B().o - game.f.B().p;
            dataOutputStream.writeLong(l2);
            dataOutputStream.writeByte(this.m.u);
            dataOutputStream.writeInt(j2.P.size());
            n4 = 0;
            while (n4 < j2.P.size()) {
                int[] nArray = (int[])j2.P.elementAt(n4);
                dataOutputStream.writeInt(nArray.length);
                int n5 = 0;
                while (n5 < nArray.length) {
                    dataOutputStream.writeInt(nArray[n5]);
                    ++n5;
                }
                ++n4;
            }
            ah[0].a(byteArrayOutputStream);
            byteArrayOutputStream.close();
            dataOutputStream.close();
        }
        catch (Exception exception) {
            System.out.println(" savePlayer ex = " + exception);
            return false;
        }
        return true;
    }

    private boolean b(j j2) {
        try {
            int n2;
            int[] nArray;
            int n3;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(ah[0].a(0));
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            this.r = dataInputStream.readShort();
            this.s = dataInputStream.readShort();
            int n4 = dataInputStream.readByte();
            int n5 = 0;
            while (n5 < j2.C.length) {
                n3 = 0;
                while (n3 < j2.C[n5].length) {
                    j2.C[n5][n3] = dataInputStream.readByte();
                    ++n3;
                }
                ++n5;
            }
            n5 = 0;
            while (n5 < j2.Q.length) {
                j2.Q[n5] = dataInputStream.readByte();
                ++n5;
            }
            n5 = 0;
            while (n5 < j2.D.length) {
                n3 = 0;
                while (n3 < j2.D[n5].length) {
                    j2.D[n5][n3] = dataInputStream.readByte();
                    ++n3;
                }
                ++n5;
            }
            n5 = 0;
            while (n5 < j2.F.length) {
                j2.F[n5] = dataInputStream.readByte();
                ++n5;
            }
            n5 = 0;
            while (n5 < j2.E.length) {
                n3 = 0;
                while (n3 < j2.E[n5].length) {
                    this.m.E[n5][n3] = dataInputStream.readByte();
                    ++n3;
                }
                ++n5;
            }
            this.m.I = dataInputStream.readByte();
            this.m.H = dataInputStream.readByte();
            this.m.G = dataInputStream.readByte();
            this.m.J = dataInputStream.readByte();
            n5 = 0;
            while (n5 < this.m.S.length) {
                this.m.S[n5] = dataInputStream.readByte();
                ++n5;
            }
            this.ac();
            this.ab();
            this.Z();
            n5 = dataInputStream.readInt();
            j2.M.removeAllElements();
            n3 = 0;
            while (n3 < n5) {
                nArray = new int[dataInputStream.readInt()];
                n2 = 0;
                while (n2 < nArray.length) {
                    nArray[n2] = dataInputStream.readInt();
                    ++n2;
                }
                j2.M.addElement(nArray);
                ++n3;
            }
            n5 = dataInputStream.readInt();
            j2.N.removeAllElements();
            n3 = 0;
            while (n3 < n5) {
                nArray = new int[dataInputStream.readInt()];
                n2 = 0;
                while (n2 < nArray.length) {
                    nArray[n2] = dataInputStream.readInt();
                    ++n2;
                }
                j2.N.addElement(nArray);
                ++n3;
            }
            n5 = dataInputStream.readInt();
            j2.O.removeAllElements();
            n3 = 0;
            while (n3 < n5) {
                nArray = new int[dataInputStream.readInt()];
                n2 = 0;
                while (n2 < nArray.length) {
                    nArray[n2] = dataInputStream.readInt();
                    ++n2;
                }
                j2.O.addElement(nArray);
                ++n3;
            }
            n3 = 0;
            while (n3 < j2.U.length) {
                j2.U[n3] = dataInputStream.readBoolean();
                ++n3;
            }
            this.X();
            if (O == null) {
                O = new Vector();
            }
            O.removeAllElements();
            byte by = dataInputStream.readByte();
            n5 = by;
            nArray = new int[by];
            n3 = 0;
            while (n3 < n5) {
                nArray[n3] = dataInputStream.readByte();
                if (this.m.A[nArray[n3]] != null) {
                    this.m.A[nArray[n3]].x();
                    O.addElement(this.m.A[nArray[n3]]);
                }
                ++n3;
            }
            n3 = 0;
            while (n3 < this.aV.length) {
                this.aV[n3] = dataInputStream.readBoolean();
                ++n3;
            }
            byte by2 = dataInputStream.readByte();
            n3 = by2;
            if (by2 != -1) {
                this.l(n3);
            }
            j2.v = dataInputStream.readByte();
            A = dataInputStream.readInt();
            U = dataInputStream.readBoolean();
            game.f.B().n += dataInputStream.readLong();
            j2.u = dataInputStream.readByte();
            short[] sArray = new short[9];
            sArray[0] = this.r;
            sArray[1] = this.s;
            sArray[2] = (short)n4;
            sArray[3] = 4;
            sArray[4] = 4;
            sArray[5] = 8;
            sArray[6] = 40;
            sArray[7] = 100;
            j2.a(sArray);
            n3 = dataInputStream.readInt();
            if (n3 > 0) {
                j2.P.removeAllElements();
                n4 = 0;
                while (n4 < n3) {
                    nArray = new int[dataInputStream.readInt()];
                    n5 = 0;
                    while (n5 < nArray.length) {
                        nArray[n5] = dataInputStream.readInt();
                        ++n5;
                    }
                    j2.P.addElement(nArray);
                    ++n4;
                }
            }
            byteArrayInputStream.close();
            dataInputStream.close();
        }
        catch (Exception exception) {
            System.out.println(" loadPlayer ex = " + exception);
            return false;
        }
        return true;
    }

    private boolean Q() {
        try {
            int n2;
            int n3;
            int n4;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(this.p);
            dataOutputStream.writeInt(this.q);
            for (n4 = 0; n4 < ai.length; ++n4) {
                if (ai[n4] == null) {
                    dataOutputStream.writeShort(-1);
                    continue;
                }
                dataOutputStream.writeShort(ai[n4].length);
                for (n3 = 0; n3 < ai[n4].length; ++n3) {
                    if (ai[n4][n3] == null) {
                        dataOutputStream.writeByte(-1);
                        continue;
                    }
                    dataOutputStream.writeByte(ai[n4][n3].length);
                    for (n2 = 0; n2 < ai[n4][n3].length; ++n2) {
                        dataOutputStream.writeByte(ai[n4][n3][n2]);
                    }
                }
            }
            for (n4 = 0; n4 < aj.length; ++n4) {
                if (aj[n4] == null) {
                    dataOutputStream.writeShort(-1);
                    continue;
                }
                dataOutputStream.writeShort(aj[n4].length);
                for (n3 = 0; n3 < aj[n4].length; ++n3) {
                    if (aj[n4][n3] == null) {
                        dataOutputStream.writeByte(-1);
                        continue;
                    }
                    dataOutputStream.writeByte(aj[n4][n3].length);
                    for (n2 = 0; n2 < aj[n4][n3].length; ++n2) {
                        dataOutputStream.writeShort(aj[n4][n3][n2]);
                    }
                }
            }
            ah[1].a(byteArrayOutputStream);
            byteArrayOutputStream.close();
            dataOutputStream.close();
        }
        catch (IOException iOException) {
            return false;
        }
        return true;
    }

    private boolean R() {
        try {
            int n2;
            int n3;
            int n4;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(ah[1].a(0));
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            this.p = dataInputStream.readInt();
            this.q = dataInputStream.readInt();
            for (n4 = 0; n4 < ai.length; ++n4) {
                n3 = dataInputStream.readShort();
                if (n3 == -1) {
                    game.l.ai[n4] = null;
                    continue;
                }
                game.l.ai[n4] = new byte[n3][];
                for (n2 = 0; n2 < ai[n4].length; ++n2) {
                    byte by = dataInputStream.readByte();
                    n3 = by;
                    if (by == -1) {
                        game.l.ai[n4][n2] = null;
                        continue;
                    }
                    game.l.ai[n4][n2] = new byte[n3];
                    for (n3 = 0; n3 < ai[n4][n2].length; ++n3) {
                        game.l.ai[n4][n2][n3] = dataInputStream.readByte();
                    }
                }
            }
            for (n4 = 0; n4 < aj.length; ++n4) {
                n3 = dataInputStream.readShort();
                if (n3 == -1) {
                    game.l.aj[n4] = null;
                    continue;
                }
                game.l.aj[n4] = new short[n3][];
                for (n2 = 0; n2 < aj[n4].length; ++n2) {
                    byte by = dataInputStream.readByte();
                    n3 = by;
                    if (by == -1) {
                        game.l.aj[n4][n2] = null;
                        continue;
                    }
                    game.l.aj[n4][n2] = new short[n3];
                    for (n3 = 0; n3 < aj[n4][n2].length; ++n3) {
                        game.l.aj[n4][n2][n3] = dataInputStream.readShort();
                    }
                }
            }
            byteArrayInputStream.close();
            dataInputStream.close();
        }
        catch (IOException iOException) {
            System.out.println(" sceneId ex = " + iOException);
            return false;
        }
        return true;
    }

    private boolean S() {
        try {
            int n2;
            int n3;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            for (n3 = 0; n3 < this.Z.n.length; ++n3) {
                if (this.Z.n[n3] == null) {
                    dataOutputStream.writeByte(-1);
                    continue;
                }
                dataOutputStream.writeByte(this.Z.n[n3].length);
                for (n2 = 0; n2 < this.Z.n[n3].length; ++n2) {
                    dataOutputStream.writeByte(this.Z.n[n3][n2]);
                }
            }
            dataOutputStream.writeByte(game.e.G);
            dataOutputStream.writeByte(game.e.H);
            for (n3 = 0; n3 < game.e.H; ++n3) {
                dataOutputStream.writeShort(game.e.F[n3][0]);
                dataOutputStream.writeShort(game.e.F[n3][1]);
            }
            int[] nArray = this.Z.I();
            if (nArray == null) {
                dataOutputStream.writeByte(-1);
            } else {
                dataOutputStream.writeByte(nArray.length);
                for (n2 = 0; n2 < nArray.length; ++n2) {
                    dataOutputStream.writeInt(nArray[n2]);
                }
                dataOutputStream.writeByte(this.Z.C);
            }
            ah[2].a(byteArrayOutputStream);
            byteArrayOutputStream.close();
            dataOutputStream.close();
        }
        catch (IOException iOException) {
            return false;
        }
        return true;
    }

    private boolean T() {
        try {
            int n2;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(ah[2].a(0));
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            for (n2 = 0; n2 < this.Z.n.length; ++n2) {
                int n3 = dataInputStream.readByte();
                if (n3 == -1) {
                    this.Z.n[n2] = null;
                    continue;
                }
                this.Z.n[n2] = new byte[n3];
                for (n3 = 0; n3 < this.Z.n[n2].length; ++n3) {
                    this.Z.n[n2][n3] = dataInputStream.readByte();
                }
            }
            game.e.G = dataInputStream.readByte();
            game.e.H = dataInputStream.readByte();
            for (n2 = 0; n2 < game.e.H; ++n2) {
                game.e.F[n2][0] = dataInputStream.readShort();
                game.e.F[n2][1] = dataInputStream.readShort();
            }
            byte by = dataInputStream.readByte();
            n2 = by;
            if (by != -1) {
                int n4;
                int[] nArray = new int[n2];
                int[] nArray2 = this.Z.H();
                for (n4 = 0; n4 < n2; ++n4) {
                    nArray[n4] = dataInputStream.readInt();
                }
                this.Z.a(nArray);
                n4 = 0;
                if (nArray2[0] > nArray[0] || nArray2[1] > nArray[1] || nArray2[2] > nArray[2] || nArray2[3] - nArray[3] >= 20) {
                    n4 = 1;
                }
                this.Z.C = dataInputStream.readByte();
                if (n4 != 0) {
                    this.Z.C = 0;
                }
            }
            H = true;
            byteArrayInputStream.close();
            dataInputStream.close();
        }
        catch (IOException iOException) {
            return false;
        }
        return true;
    }

    public static boolean F() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            h = true;
            dataOutputStream.writeBoolean(h);
            dataOutputStream.writeBoolean(H);
            dataOutputStream.writeBoolean(N);
            dataOutputStream.writeByte(Q);
            ah[3].a(byteArrayOutputStream);
            byteArrayOutputStream.close();
            dataOutputStream.close();
        }
        catch (IOException iOException) {
            return false;
        }
        return true;
    }

    public static boolean G() {
        if (ah[3] == null) {
            game.l.ah[3] = new a.b.e(aw[3], 1);
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(ah[3].a(0));
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            h = dataInputStream.readBoolean();
            H = dataInputStream.readBoolean();
            N = dataInputStream.readBoolean();
            Q = dataInputStream.readByte();
            byteArrayInputStream.close();
            dataInputStream.close();
            return true;
        }
        catch (IOException iOException) {
            h = false;
            System.out.println(" isHaveSms = " + h);
            return false;
        }
    }

    private static boolean U() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeBoolean(i);
            ah[4].a(byteArrayOutputStream);
            byteArrayOutputStream.close();
            dataOutputStream.close();
        }
        catch (IOException iOException) {
            return false;
        }
        return true;
    }

    private static boolean V() {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(ah[4].a(0));
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            i = dataInputStream.readBoolean();
            byteArrayInputStream.close();
            dataInputStream.close();
            return true;
        }
        catch (IOException iOException) {
            return false;
        }
    }

    private boolean W() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(this.m.F());
            dataOutputStream.writeInt(this.m.G());
            ah[6].a(byteArrayOutputStream);
            byteArrayOutputStream.close();
            dataOutputStream.close();
        }
        catch (IOException iOException) {
            return false;
        }
        return true;
    }

    private boolean X() {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(ah[6].a(0));
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            this.m.t(0);
            this.m.w(0);
            this.m.s(dataInputStream.readInt());
            this.m.v(dataInputStream.readInt());
            byteArrayInputStream.close();
            dataInputStream.close();
            return true;
        }
        catch (IOException iOException) {
            return false;
        }
    }

    private boolean Y() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(this.m.L.size());
            for (int i2 = 0; i2 < this.m.L.size(); ++i2) {
                int[] nArray = (int[])this.m.L.elementAt(i2);
                dataOutputStream.writeInt(nArray.length);
                for (int i3 = 0; i3 < nArray.length; ++i3) {
                    dataOutputStream.writeInt(nArray[i3]);
                }
            }
            ah[9].a(byteArrayOutputStream);
            byteArrayOutputStream.close();
            dataOutputStream.close();
        }
        catch (IOException iOException) {
            return false;
        }
        return true;
    }

    private boolean Z() {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(ah[9].a(0));
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            int n2 = dataInputStream.readInt();
            this.m.L.removeAllElements();
            for (int i2 = 0; i2 < n2; ++i2) {
                int[] nArray = new int[dataInputStream.readInt()];
                for (int i3 = 0; i3 < nArray.length; ++i3) {
                    nArray[i3] = dataInputStream.readInt();
                }
                this.m.L.addElement(nArray);
            }
            byteArrayInputStream.close();
            dataInputStream.close();
            return true;
        }
        catch (IOException iOException) {
            return false;
        }
    }

    private boolean aa() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(this.m.K.size());
            for (int i2 = 0; i2 < this.m.K.size(); ++i2) {
                int[] nArray = (int[])this.m.K.elementAt(i2);
                dataOutputStream.writeInt(nArray.length);
                for (int i3 = 0; i3 < nArray.length; ++i3) {
                    dataOutputStream.writeInt(nArray[i3]);
                }
            }
            ah[8].a(byteArrayOutputStream);
            byteArrayOutputStream.close();
            dataOutputStream.close();
        }
        catch (IOException iOException) {
            return false;
        }
        return true;
    }

    private boolean ab() {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(ah[8].a(0));
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            int n2 = dataInputStream.readInt();
            this.m.K.removeAllElements();
            for (int i2 = 0; i2 < n2; ++i2) {
                int[] nArray = new int[dataInputStream.readInt()];
                for (int i3 = 0; i3 < nArray.length; ++i3) {
                    nArray[i3] = dataInputStream.readInt();
                }
                this.m.K.addElement(nArray);
            }
            byteArrayInputStream.close();
            dataInputStream.close();
            return true;
        }
        catch (IOException iOException) {
            return false;
        }
    }

    public final boolean H() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeByte(this.m.B);
            for (int i2 = 0; i2 < this.m.B; ++i2) {
                int[] nArray = this.m.A[i2].Q();
                dataOutputStream.writeInt(nArray.length);
                for (int i3 = 0; i3 < nArray.length; ++i3) {
                    dataOutputStream.writeInt(nArray[i3]);
                }
            }
            ah[7].a(byteArrayOutputStream);
            byteArrayOutputStream.close();
            dataOutputStream.close();
        }
        catch (IOException iOException) {
            return false;
        }
        return true;
    }

    private boolean ac() {
        try {
            int n2;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(ah[7].a(0));
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            byte by = dataInputStream.readByte();
            for (n2 = 0; n2 < this.m.B; ++n2) {
                this.m.A[n2] = null;
            }
            this.m.B = 0;
            for (n2 = 0; n2 < by; ++n2) {
                int[] nArray = new int[dataInputStream.readInt()];
                for (int i2 = 0; i2 < nArray.length; ++i2) {
                    nArray[i2] = dataInputStream.readInt();
                }
                this.m.a(nArray);
            }
            byteArrayInputStream.close();
            dataInputStream.close();
            return true;
        }
        catch (IOException iOException) {
            return false;
        }
    }

    public final boolean I() {
        if (!this.a(this.m)) {
            return false;
        }
        if (!this.Q()) {
            return false;
        }
        if (!this.S()) {
            return false;
        }
        if (!game.l.F()) {
            return false;
        }
        return game.l.U();
    }

    public final boolean J() {
        if (!game.l.U()) {
            return false;
        }
        if (!this.W()) {
            return false;
        }
        return this.Y();
    }

    public static void K() {
        ai = null;
        aj = null;
        for (int i2 = 0; i2 < 10; ++i2) {
            if (i2 == 4 || ah[i2] == null) continue;
            ah[i2].a();
            game.l.ah[i2] = null;
        }
        ah = null;
    }

    public final boolean L() {
        this.Z.a(this);
        this.d.a(this);
        game.e.r = false;
        T = true;
        this.a((byte)0);
        if (Y) {
            this.W.a(game.l.a(this.Z.m, (byte)0), 1);
        } else {
            this.W.a(X, 1);
        }
        this.c.a("/data/ui/battle.ui");
        return true;
    }

    private void ad() {
        if (!this.m.z) {
            if (h) {
                this.b(this.m);
            } else {
                short[] sArray = new short[]{this.r, this.s, G, 4, 4, 8, 40, 100, 0};
                this.m.a(sArray);
            }
        } else if (this.t >= 0) {
            int n2 = 2;
            j j2 = this.m;
            int n3 = this.n[this.t].j - this.n[this.t].j % j2.d[n2];
            n2 = 2;
            j2 = this.m;
            int n4 = this.n[this.t].k - this.n[this.t].k % j2.d[n2];
            this.m.b(n3, n4);
            this.m.b.b(n3, n4);
            this.m.a((byte)0, this.n[this.t].D);
            if (this.n[this.t].a.a == 222) {
                this.m.a(24);
            } else {
                this.m.a(32);
            }
        } else {
            short[] sArray = new short[]{this.r, this.s, G, 4, 4, 8, 40, 100, 0};
            this.m.a(sArray);
        }
        this.l.a(this.m);
        this.m.C();
        this.m.c();
        this.a((g)this.m);
        if (w != null) {
            this.m.a(false);
            return;
        }
        this.m.a(true);
    }

    private void ae() {
        for (int i2 = 0; i2 < this.n.length; ++i2) {
            if (this.n[i2].u != 0 || this.n[i2].w != 14) continue;
            h h2 = this.n[i2];
            this.n[i2].B = 0;
            while (true) {
                h h3 = h2;
                boolean bl = false;
                int n2 = 16 * (h2.B + 1);
                byte by = h3.a.g();
                h3 = h2;
                byte by2 = 0;
                switch (by) {
                    case 2: {
                        by2 = a.b.d.a().a(0, h3.j, h3.k - n2);
                        break;
                    }
                    case 0: {
                        by2 = a.b.d.a().a(0, h3.j, h3.k + n2);
                        break;
                    }
                    case 3: {
                        by2 = a.b.d.a().a(0, h3.j - n2, h3.k);
                        break;
                    }
                    case 1: {
                        by2 = a.b.d.a().a(0, h3.j + n2, h3.k);
                    }
                }
                if (!(by2 == 0)) break;
                ++h2.B;
            }
            h2.C = h2.B;
            h2.B = 0;
        }
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(int n2, int n3, String object) {
        object = (String)object + "scene_" + n2 + ".mid";
        try {
            int n4;
            int n5;
            int n6;
            "".getClass();
            object = b.a((String)object);
            DataInputStream dataInputStream = new DataInputStream((InputStream)object);
            int n7 = dataInputStream.readShort();
            short[] sArray = new short[n7];
            for (n6 = 0; n6 < n7; ++n6) {
                sArray[n6] = dataInputStream.readShort();
            }
            n6 = 0;
            for (n7 = 0; n7 < n3; n6 += sArray[n7], ++n7) {
            }
            dataInputStream.skipBytes(n6);
            n7 = dataInputStream.readShort();
            Object var6_12 = null;
            if (n7 > 0) {
                String[] stringArray = new String[n7];
                for (n6 = 0; n6 < n7; ++n6) {
                    int n8 = dataInputStream.readShort();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (n5 = 0; n5 < n8; ++n5) {
                        stringBuffer.append((char)(dataInputStream.read() << 8 | dataInputStream.read() & 0xFF));
                    }
                    stringArray[n6] = stringBuffer.toString();
                }
            }
            n6 = dataInputStream.readByte();
            Object object2 = new StringBuffer();
            for (n4 = 0; n4 < n6; ++n4) {
                ((StringBuffer)object2).append((char)(dataInputStream.read() << 8 | dataInputStream.read() & 0xFF));
            }
            this.ae = dataInputStream.readShort();
            al = null;
            dataInputStream.readShort();
            n5 = dataInputStream.readShort();
            boolean[] blArray = new boolean[2];
            int n9 = n3;
            n4 = n2;
            if (ai[v[n4] + n9] == null) {
                n9 = n3;
                n4 = n2;
                game.l.ai[game.l.v[n4] + n9] = new byte[n5][3];
                blArray[0] = true;
            }
            if (this.p == 9) {
                n9 = n3;
                n4 = n2;
                game.l.aj[game.l.v[n4] + n9] = null;
            }
            if (aj[v[n4 = n2] + (n9 = n3)] == null) {
                n9 = n3;
                n4 = n2;
                game.l.aj[game.l.v[n4] + n9] = new short[n5][2];
                blArray[1] = true;
            }
            if (n5 > 0) {
                this.n = new h[n5];
                block15: for (n6 = 0; n6 < n5; ++n6) {
                    try {
                        this.n[n6] = new h();
                        short[] sArray2 = new short[dataInputStream.readShort()];
                        object2 = sArray2;
                        sArray2[0] = dataInputStream.readByte();
                        object2[1] = dataInputStream.readShort();
                        object2[2] = dataInputStream.readShort();
                        object2[3] = dataInputStream.readShort();
                        object2[4] = dataInputStream.readShort();
                        object2[5] = (short)dataInputStream.readByte();
                        object2[6] = (short)dataInputStream.readByte();
                        switch (object2[0]) {
                            case 1: {
                                object2[7] = (short)dataInputStream.readByte();
                                object2[8] = dataInputStream.readShort();
                                object2[9] = dataInputStream.readShort();
                                object2[10] = dataInputStream.readShort();
                                if (blArray[0] && object2[6] == 3) {
                                    n9 = n3;
                                    int n10 = n2;
                                    game.l.ai[game.l.v[n10] + n9][n6][0] = (byte)object2[2];
                                    n9 = n3;
                                    n10 = n2;
                                    game.l.ai[game.l.v[n10] + n9][n6][1] = (byte)object2[5];
                                }
                                if (object2[6] == 3) {
                                    n9 = n3;
                                    int n11 = n2;
                                    object2[2] = (short)ai[v[n11] + n9][n6][0];
                                    n9 = n3;
                                    n11 = n2;
                                    object2[5] = (short)ai[v[n11] + n9][n6][1];
                                }
                                this.n[n6].a((short[])object2, n6);
                                continue block15;
                            }
                            case 2: {
                                object2[7] = dataInputStream.readShort();
                                if (object2[7] == true) {
                                    object2[8] = (short)dataInputStream.readByte();
                                    object2[9] = (short)dataInputStream.readByte();
                                    object2[10] = (short)dataInputStream.readByte();
                                    object2[11] = (short)dataInputStream.readByte();
                                    object2[12] = (short)dataInputStream.readByte();
                                }
                                this.n[n6].a((short[])object2, n6);
                                continue block15;
                            }
                            case 0: {
                                int n12;
                                object2[7] = (short)dataInputStream.readByte();
                                object2[8] = (short)dataInputStream.readByte();
                                object2[9] = (short)dataInputStream.readByte();
                                object2[10] = (short)dataInputStream.readByte();
                                object2[11] = dataInputStream.readShort();
                                object2[12] = dataInputStream.readShort();
                                if (blArray[0]) {
                                    n9 = n3;
                                    n12 = n2;
                                    game.l.ai[game.l.v[n12] + n9][n6][0] = (byte)object2[2];
                                    n9 = n3;
                                    n12 = n2;
                                    game.l.ai[game.l.v[n12] + n9][n6][1] = (byte)object2[5];
                                } else {
                                    if (object2[6] != 7 && object2[6] != 6) {
                                        n9 = n3;
                                        int n13 = n2;
                                        object2[2] = (short)ai[v[n13] + n9][n6][0];
                                    }
                                    n9 = n3;
                                    n12 = n2;
                                    object2[5] = (short)ai[v[n12] + n9][n6][1];
                                }
                                if (blArray[1]) {
                                    n9 = n3;
                                    n12 = n2;
                                    game.l.aj[game.l.v[n12] + n9][n6][0] = (short)object2[3];
                                    n9 = n3;
                                    n12 = n2;
                                    game.l.aj[game.l.v[n12] + n9][n6][1] = (short)object2[4];
                                } else {
                                    n9 = n3;
                                    n12 = n2;
                                    object2[3] = aj[v[n12] + n9][n6][0];
                                    n9 = n3;
                                    n12 = n2;
                                    object2[4] = aj[v[n12] + n9][n6][1];
                                }
                                this.n[n6].a((short[])object2, n6);
                                if (!blArray[0]) {
                                    if (object2[6] != true) break;
                                    if (object2[6] != 7 && object2[6] != 6) {
                                        n9 = n3;
                                        n12 = n2;
                                        object2[2] = (short)ai[v[n12] + n9][n6][0];
                                    }
                                    n9 = n3;
                                    n12 = n2;
                                    n9 = ai[v[n12] + n9][n6][2];
                                    h h2 = this.n[n6];
                                    this.n[n6].o = (byte)n9;
                                    this.n[n6].a((byte)object2[2]);
                                    continue block15;
                                }
                                n9 = n3;
                                n12 = n2;
                                game.l.ai[game.l.v[n12] + n9][n6][2] = this.n[n6].o;
                                continue block15;
                            }
                            case 3: {
                                int n14;
                                object2[7] = (short)dataInputStream.readByte();
                                object2[8] = (short)dataInputStream.readByte();
                                object2[9] = (short)dataInputStream.readByte();
                                object2[10] = dataInputStream.readShort();
                                object2[11] = dataInputStream.readShort();
                                if (blArray[0]) {
                                    n9 = n3;
                                    n14 = n2;
                                    game.l.ai[game.l.v[n14] + n9][n6][0] = (byte)object2[2];
                                } else {
                                    n9 = n3;
                                    n14 = n2;
                                    object2[2] = (short)ai[v[n14] + n9][n6][0];
                                }
                                this.n[n6].a((short[])object2, n6);
                            }
                        }
                        continue;
                    }
                    catch (Exception exception) {
                        System.out.println(" k = " + n6 + " e = " + exception);
                    }
                }
                short s = dataInputStream.readShort();
                n6 = s;
                al = new String[s];
                for (int i2 = 0; i2 < n6; ++i2) {
                    StringBuffer stringBuffer = new StringBuffer();
                    n3 = dataInputStream.readByte();
                    for (int i3 = 0; i3 < n3; ++i3) {
                        stringBuffer.append((char)(dataInputStream.readByte() << 8 | dataInputStream.readByte() & 0xFF));
                    }
                    game.l.al[i2] = stringBuffer.toString();
                }
            }
            this.aB = 1;
            short s = dataInputStream.readShort();
            this.aB = 2;
            if (s > 0) {
                void var6_14;
                this.Z.a(dataInputStream, this.p, this.q, s, (String[])var6_14);
            }
            dataInputStream.close();
            ((InputStream)object).close();
            return;
        }
        catch (Exception exception) {
            System.out.println(" initRoom = " + exception + " bug = " + this.aB);
            return;
        }
    }

    public final void c() {
        this.l.a();
        this.k.b();
        if (this.n != null) {
            for (int i2 = 0; i2 < this.n.length; ++i2) {
                h h2 = this.n[i2];
                h2.e();
                if (h2.b != null) {
                    h2.b.a.b();
                    h2.b = null;
                }
                h2.a.b();
                h2.a = null;
                if (h2.H != null) {
                    h2.H.a.b();
                    h2.H = null;
                }
                if (h2.I != null) {
                    h2.I.a.b();
                    h2.I = null;
                }
                h2.J = (short)-1;
                this.n[i2] = null;
            }
            this.n = null;
        }
        w = null;
        ag = null;
        am = null;
        ay = null;
        this.o.removeAllElements();
        an.removeAllElements();
        ap.removeAllElements();
        aq.removeAllElements();
        ao.removeAllElements();
        if (R != null) {
            R.removeAllElements();
            R = null;
        }
        this.c.b();
        this.Z.c();
        E = (short)-1;
        a.a.f.c();
    }

    public final void a(byte by) {
        this.b = this.a;
        switch (by) {
            case 0: {
                game.l.u();
                if (!game.e.r) {
                    if (T) {
                        this.d.c();
                    } else {
                        this.d.d();
                    }
                }
                this.m.a((byte)0, this.m.o);
                break;
            }
            case 1: {
                this.d.j = 1;
                this.d.F();
                break;
            }
            case 2: {
                if (E != -1 && this.n[E] != null && this.n[game.l.E].a.a == 24) {
                    this.d.a(4, (byte)0);
                    break;
                }
                if (E == -1 || this.n[E] == null || this.n[game.l.E].a.a != 20) break;
                this.d.a(3, (byte)2);
                break;
            }
            case 32: {
                this.d.j = (byte)3;
                this.d.a(3, (byte)2);
                break;
            }
            case 26: {
                this.d.j = (byte)2;
                this.d.a(4, (byte)0);
                break;
            }
            case 3: {
                this.d.O();
                break;
            }
            case 4: {
                break;
            }
            case 5: {
                this.d.ag();
                break;
            }
            case 6: {
                this.d.k();
                break;
            }
            case 7: {
                this.d.c = 0;
                this.d.Z();
                break;
            }
            case 8: {
                this.d.ab();
                break;
            }
            case 9: {
                this.d.Q();
                break;
            }
            case 10: {
                this.d.U();
                break;
            }
            case 22: {
                this.d.K();
                this.d.a("C\u00f3 l\u01b0u d\u1eef li\u1ec7u kh\u00f4ng?");
                break;
            }
            case 11: {
                this.d.S();
                break;
            }
            case 12: {
                this.d.W();
                break;
            }
            case 13: {
                this.d.m();
                break;
            }
            case 14: {
                this.d.aC();
                break;
            }
            case 16: {
                this.d.D();
                break;
            }
            case 15: {
                this.d.B();
                break;
            }
            case 17: {
                this.d.l = false;
            }
            case 18: 
            case 19: {
                this.d.c = 0;
                this.d.Z();
                break;
            }
            case 20: {
                this.d.x();
                break;
            }
            case 21: {
                this.d.z();
                break;
            }
            case 23: {
                if (this.b == 7) {
                    this.d.a("", this.aC, -1, -1);
                } else if (this.n != null) {
                    if (this.n[game.l.E].a.a == 68) {
                        this.d.a(al[this.n[game.l.E].z], "Mu\u1ed1n l\u00ean thuy\u1ec1n \u0111i \u0111\u00e2u?", 1, -1);
                    } else if (this.n[game.l.E].y < 0) {
                        this.d.a(al[this.n[game.l.E].z], aa[0], 1, -1);
                    } else {
                        this.d.a(al[this.n[game.l.E].z], aa[this.n[game.l.E].y], 1, -1);
                    }
                }
                this.m.a((byte)0, this.m.o);
                break;
            }
            case 27: {
                this.d.aS();
                break;
            }
            case 31: {
                this.d.f = 0;
                Object object = this;
                byte by2 = 0;
                ((l)object).ai();
                if (((l)object).aY >= ((l)object).aX) {
                    by2 = 1;
                }
                this.aZ = by2;
                if (this.aZ) {
                    if (this.aX == this.aW.length - 1) {
                        this.d.a(al[this.n[game.l.E].z], game.l.c(613), 1, -1);
                        break;
                    }
                    if (this.aX == this.aW.length - 2) {
                        this.d.a(al[this.n[game.l.E].z], game.l.c(612), 1, -1);
                        break;
                    }
                    object = new int[]{this.aW[this.aX], this.aW[this.aX + 1]};
                    this.d.a(al[this.n[game.l.E].z], game.l.a(611, (int[])object), 1, -1);
                    break;
                }
                if (this.aX < this.aW.length) {
                    by2 = this.aW[this.aX];
                    int n2 = 614;
                    n2 = a.a.c(614).indexOf("%s");
                    this.d.a(al[this.n[game.l.E].z], n2 == -1 ? a.a.c(614) : a.a.c(614).substring(0, n2) + by2 + a.a.c(614).substring(n2 + 2), 1, -1);
                    break;
                }
                this.d.a(al[this.n[game.l.E].z], game.l.c(615), 1, -1);
                break;
            }
            case 24: {
                this.d.h();
                break;
            }
            case 29: {
                a.a.f.a().c(0, 2);
                break;
            }
            case 30: {
                this.d.aQ();
                break;
            }
            case 100: {
                this.d.d(0);
                break;
            }
            case 101: {
                this.d.aJ();
                break;
            }
            case 102: {
                this.d.aL();
                break;
            }
            case 104: {
                this.d.aK();
                break;
            }
            case 25: {
                this.d.au();
                break;
            }
            case 28: {
                byte by3;
                for (by3 = 0; by3 < this.av.length / 4 && (this.av[by3 << 2] != this.p || this.av[(by3 << 2) + 1] != this.q); by3 = (byte)((byte)(by3 + 1))) {
                }
                this.d.a(by3, (int)this.av[(by3 << 2) + 2], (int)this.av[(by3 << 2) + 3]);
            }
        }
        this.d.g = true;
        this.a = by;
        this.z();
    }

    /*
     * Unable to fully structure code
     */
    public final void a() {
        if (!this.j) {
            return;
        }
        this.A();
        switch (this.a) {
            case 0: {
                this.af();
                break;
            }
            case 1: {
                this.d.G();
                break;
            }
            case 2: {
                if (game.l.E != -1 && this.n[game.l.E] != null && this.n[game.l.E].a.a == 24 || this.Z.o == 0) {
                    this.d.a((byte)4, (byte)0);
                    break;
                }
                if ((game.l.E == -1 || this.n[game.l.E] == null || this.n[game.l.E].a.a != 20) && this.Z.o != 1) break;
                this.d.a((byte)3, (byte)2);
                break;
            }
            case 32: {
                this.d.a((byte)3, (byte)2);
                break;
            }
            case 26: {
                this.d.a((byte)4, (byte)0);
                break;
            }
            case 3: {
                this.d.P();
                break;
            }
            case 4: {
                if (this.aL) {
                    if (this.aH == this.aJ && this.aI == this.aK) {
                        this.aL = false;
                    }
                    if ((var1_1 = a.e.a(this.aH, this.aI, this.aJ, this.aK)) < this.aO) {
                        this.aH = this.aJ;
                        this.aI = this.aK;
                    } else {
                        this.aH += (this.aJ - this.aH) * this.aO / var1_1;
                        this.aI += (this.aK - this.aI) * this.aO / var1_1;
                    }
                }
                if (!this.aL) {
                    if (this.i(16400)) {
                        if (this.aH < 0) {
                            this.aH += this.aO;
                        }
                    } else if (this.i(32832)) {
                        if (this.aH + (this.aT[this.aD << 1] << 4) * 5 > game.l.g()) {
                            this.aH -= this.aO;
                        }
                    } else if (this.i(4100)) {
                        if (this.aI < 0) {
                            this.aI += this.aO;
                        }
                    } else if (this.i(8448)) {
                        if (this.aI + (this.aT[(this.aD << 1) + 1] << 3) * 5 > game.l.h() - 30) {
                            this.aI -= this.aO;
                        }
                    } else if (this.g(262145) || this.a(0, 270, (int)game.l.g(), 30)) {
                        game.a.B().m = null;
                        this.a((byte)0);
                    }
                }
                this.aM.a();
                for (var1_1 = 0; var1_1 < this.aN.length; ++var1_1) {
                    this.o(var1_1);
                    this.aN[var1_1].a();
                }
                break;
            }
            case 5: {
                this.d.ah();
                break;
            }
            case 6: {
                this.d.l();
                break;
            }
            case 7: {
                this.d.aa();
                this.q();
                break;
            }
            case 8: {
                this.d.af();
                break;
            }
            case 9: {
                this.d.R();
                break;
            }
            case 10: {
                this.d.V();
                break;
            }
            case 22: {
                this.d.N();
                break;
            }
            case 11: {
                this.d.T();
                break;
            }
            case 12: {
                this.d.X();
                break;
            }
            case 13: {
                this.d.n();
                break;
            }
            case 14: {
                this.d.aD();
                break;
            }
            case 16: {
                this.d.E();
                break;
            }
            case 15: {
                this.d.C();
                break;
            }
            case 17: {
                this.d.ac();
                break;
            }
            case 18: {
                this.d.ad();
                break;
            }
            case 19: {
                this.d.ae();
                break;
            }
            case 20: {
                this.d.y();
                break;
            }
            case 21: {
                this.d.A();
                break;
            }
            case 23: {
                var1_2 = this;
                if (!var1_2.d.d(game.l.D, game.l.C) || !var1_2.g(196640)) ** GOTO lbl147
                if (a.e.b >= a.e.b()) ** GOTO lbl127
                a.e.c();
                var1_2.d.b(a.e.b);
                ** GOTO lbl147
lbl127:
                // 1 sources

                var1_2.d.aF();
                if (var1_2.b == 7) ** GOTO lbl146
                if (var1_2.n[game.l.E].a.a <= 85) {
                    var3_4 = var1_2.n[game.l.E].n;
                    var2_5 = var1_2.n[game.l.E];
                    var1_2.n[game.l.E].o = var3_4;
                }
                var1_2.n[game.l.E].a((byte)0);
                var1_2.m.a((byte)0, var1_2.m.o);
                if (var1_2.n[game.l.E].a.a == 24 || var1_2.n[game.l.E].a.a == 20) {
                    var1_2.a((byte)1);
                } else if (var1_2.n[game.l.E].a.a == 25) {
                    var1_2.a((byte)16);
                } else if (var1_2.n[game.l.E].a.a == 68) {
                    var1_2.a((byte)28);
                } else {
                    if (game.l.E != -1) {
                        game.l.B().a((byte)13, game.l.B().n[game.l.E].j, game.l.B().n[game.l.E].k - 40, game.l.B().n[game.l.E]);
                    }
lbl146:
                    // 4 sources

                    var1_2.a((byte)0);
                }
lbl147:
                // 6 sources

                var1_2.l.b();
                break;
            }
            case 31: {
                this.ag();
                break;
            }
            case 27: {
                this.d.aT();
                break;
            }
            case 24: {
                this.d.i();
                break;
            }
            case 28: {
                this.d.aP();
                break;
            }
            case 29: {
                a.a.f.a().d();
                if (!a.a.f.a().d) break;
                a.a.f.a().a = -1;
                game.f.B().a((byte)23);
                break;
            }
            case 30: {
                this.d.aR();
                break;
            }
            case 100: 
            case 101: 
            case 102: 
            case 104: {
                this.d.aO();
                break;
            }
            case 25: {
                this.d.av();
            }
        }
        if (this.a == 0 && !this.Z.F() && game.l.S == 0 && game.l.R != null && game.l.R.size() > 0) {
            if (this.af >= game.l.R.size()) {
                game.l.R.removeAllElements();
                this.af = 0;
                game.l.S = 1;
            } else if (this.d.aA()) {
                var1_3 = (int[])game.l.R.elementAt(this.af);
                var2_5 = "Ti\u1ebfn h\u00f3a";
                if (a.b.c.c[0][a.b.c.a((byte)0, (short)var1_3[0], (byte)19)][2] == 3) {
                    var2_5 = "D\u1ecb ho\u00e1";
                }
                if (!game.l.U && game.l.V[0] != -1) {
                    if (this.af == game.l.R.size() - 1) {
                        this.d.H();
                        this.d.a("Nh\u1ea5n #2" + game.l.c(var1_3[1]) + "#0 \u0111\u1ea1t t\u1edbi c\u00f3 th\u1ec3" + (String)var2_5 + " \u0111i\u1ec1u ki\u1ec7n", "Nh\u1ea5n n\u00fat 5 ti\u1ebfp t\u1ee5c");
                    } else {
                        this.d.b("#2" + game.l.c(var1_3[1]) + "#0 c\u00f3 th\u1ec3" + (String)var2_5);
                    }
                } else {
                    this.d.b("#2" + game.l.c(var1_3[1]) + "#0 c\u00f3 th\u1ec3" + (String)var2_5);
                }
                ++this.af;
            }
        }
        this.c.c();
        if (game.l.x != null) {
            game.l.x.d();
        }
    }

    public static void b(Graphics graphics) {
        if (w != null) {
            int n2 = w.getWidth();
            for (int i2 = 0; i2 < a.a.g() / n2; ++i2) {
                graphics.drawImage(w, i2 * n2, 0, 20);
            }
            return;
        }
        graphics.setColor(game.l.l());
        graphics.fillRect(0, 0, (int)game.l.g(), (int)game.l.h());
    }

    private void o(int n2) {
        switch (n2) {
            case 0: {
                if (this.aI >= 0) {
                    this.aN[n2].d();
                    return;
                }
                this.aN[n2].c();
                return;
            }
            case 1: {
                if (this.aI + (this.aT[(this.aD << 1) + 1] << 3) * 5 <= game.l.h()) {
                    this.aN[n2].d();
                    return;
                }
                this.aN[n2].c();
                return;
            }
            case 2: {
                if (this.aH >= 0) {
                    this.aN[n2].d();
                    return;
                }
                this.aN[n2].c();
                return;
            }
            case 3: {
                if (this.aH + (this.aT[this.aD << 1] << 4) * 5 <= game.l.g()) {
                    this.aN[n2].d();
                    return;
                }
                this.aN[n2].c();
            }
        }
    }

    public final void a(Graphics graphics) {
        if (this.a == 4) {
            int n2;
            String[] stringArray;
            Graphics graphics2 = graphics;
            l l2 = this;
            graphics2.drawImage(game.a.B().m, 0, 0, 20);
            int[] nArray = l2.aS[l2.aD];
            int n3 = nArray.length;
            int n4 = l2.aG * 7;
            int n5 = 0;
            while (n5 < n3) {
                if (l2.p == nArray[n5 + 2] && l2.q == nArray[n5 + 3]) {
                    graphics2.setColor(188, 122, 255);
                } else {
                    graphics2.setColor(l2.aR[l2.aD << 1]);
                }
                graphics2.fillRoundRect(l2.aH + (nArray[n5] << 4), l2.aI + (nArray[n5 + 1] << 3), nArray[n5 + 5] << 4, nArray[n5 + 6] << 3, 12, 12);
                graphics2.setColor(0);
                graphics2.drawRoundRect(l2.aH + (nArray[n5] << 4), l2.aI + (nArray[n5 + 1] << 3), nArray[n5 + 5] << 4, nArray[n5 + 6] << 3, 12, 12);
                if (n5 == n4) {
                    l2.aM.b(l2.aH + (nArray[n5] << 4) + 16 * nArray[n5 + 5] / 2, l2.aI + (nArray[n5 + 1] << 3) + 8 * nArray[n5 + 6] / 2 + 20);
                    l2.aM.a(graphics2, 0, 0);
                }
                stringArray = game.n.a(a.a.c(nArray[n5 + 4]), nArray[n5 + 5] << 4);
                int n6 = stringArray.length;
                n2 = 0;
                while (n2 < n6) {
                    a.e.a(graphics2, stringArray[n2], l2.aR[(l2.aD << 1) + 1], l2.aH + (nArray[n5] << 4) + 16 * nArray[n5 + 5] / 2, l2.aI + (nArray[n5 + 1] << 3) + 8 * nArray[n5 + 6] / 2 + (n2 - n6 / 2) * (game.n.a + 1), 17, 17, l2.c.b, -1);
                    ++n2;
                }
                n5 += 7;
            }
            graphics2.setColor(65280);
            n3 = l2.o.size();
            a.b.d d2 = a.b.d.a();
            n2 = 0;
            while (n2 < n3) {
                String[] stringArray2;
                stringArray = stringArray2 = (String[])l2.o.elementAt(n2);
                n5 = (stringArray2.j * nArray[n4 + 5] << 4) / d2.c + (nArray[n4] << 4) + l2.aH;
                stringArray = stringArray2;
                int n7 = (stringArray2.k * nArray[n4 + 6] << 3) / d2.d + (nArray[n4 + 1] << 3) + l2.aI;
                if (stringArray2.i() == 0 || ((h)l2.o.elementAt(n2)).i() == 1) {
                    graphics2.fillRect(n5, n7 - 2, 9, 3);
                } else {
                    graphics2.fillRect(n5, n7 - 5, 3, 9);
                }
                ++n2;
            }
            if (l2.aE != -1) {
                n5 = (l2.aP[(l2.aE << 2) + 2] * nArray[n4 + 5] << 4) / d2.c + (nArray[n4] << 4) + l2.aH;
                int n8 = (l2.aP[(l2.aE << 2) + 3] * nArray[n4 + 6] << 3) / d2.d + (nArray[n4 + 1] << 3) + l2.aI;
                graphics2.setColor(0xFF0000);
                graphics2.fillRect(n5, n8, 6, 6);
            }
            if (l2.aF != -1) {
                n5 = (l2.aQ[(l2.aF << 2) + 2] * nArray[n4 + 5] << 4) / d2.c + (nArray[n4] << 4) + l2.aH;
                int n9 = (l2.aQ[(l2.aF << 2) + 3] * nArray[n4 + 6] << 3) / d2.d + (nArray[n4 + 1] << 3) + l2.aI;
                graphics2.setColor(2758133);
                graphics2.fillRect(n5, n9, 6, 6);
            }
            n2 = 0;
            while (n2 < l2.aN.length) {
                l2.aN[n2].a(graphics2, 0, 0);
                ++n2;
            }
            graphics2.setColor(1862801);
            graphics2.fillRect(0, a.a.h() - 30, (int)a.a.g(), 30);
            graphics2.setColor(65280);
            graphics2.fillRect(15, a.a.h() - 22, 16, 16);
            game.n.a(graphics2, "C\u1eeda ra v\u00e0o", 35, a.a.h() - 18);
            graphics2.setColor(2758133);
            graphics2.fillRect(90, a.a.h() - 22, 16, 16);
            game.n.a(graphics2, "B\u1ebfn t\u00e0u", 110, a.a.h() - 18);
            graphics2.setColor(0xFF0000);
            graphics2.fillRect(150, a.a.h() - 22, 16, 16);
            game.n.a(graphics2, "C\u1eeda \u0110\u1ea1o qu\u00e1n", 168, a.a.h() - 18);
            return;
        }
        if (this.a == 0 || this.a == 23 || this.d.g) {
            game.l.b(graphics);
            a.a.f.a().b(graphics);
            this.l.a(graphics);
            a.a.f.a().c(graphics);
            if (this.d.g) {
                this.d.g = false;
            }
        }
        if (a.a.f.a().h != -1) {
            a.b.b b2;
            if (this.ad.q instanceof j) {
                a.b.b b3 = this.ad;
                b2 = b3;
                b2 = this.ad;
                a.a.f.a().b(b3.j - a.b.d.a().a, b2.k - a.b.d.a().b - this.at[this.m.u + 1]);
            } else {
                a.b.b b4 = this.ad;
                b2 = b4;
                b2 = this.ad;
                a.a.f.a().b(b4.j - a.b.d.a().a, b2.k - a.b.d.a().b - 20);
            }
            a.a.f.a().a(graphics, 0, 0);
        }
        game.e.b(graphics);
        this.c.a(graphics);
        if (x != null) {
            x.a(graphics, 0, 0);
        }
        Graphics graphics3 = graphics;
        l l3 = this;
        for (int i2 = 0; i2 < l3.m.W.size(); ++i2) {
            int[] nArray = (int[])l3.m.W.elementAt(i2);
            a.e.a(graphics3, "+" + nArray[0], 16704699, nArray[1] + 12 - l3.k.a, nArray[2] - nArray[3] - l3.k.b, 17, 17, l3.c.b, 2);
            graphics3.drawImage(ag, nArray[1] - l3.k.a - 6, nArray[2] - nArray[3] - l3.k.b, 20);
        }
        if (!this.d.j() && !game.l.p()) {
            this.Z.a(graphics);
        }
        if (this.p == 3 && this.q == 7 && this.a == 0) {
            if (this.Z.A > 0) {
                if (this.ax != null) {
                    graphics.drawImage(this.ax[this.Z.A - 1], game.l.g() >> 1, game.l.h() >> 1, 3);
                    return;
                }
            } else if (game.f.B().l != 0L) {
                graphics.setColor(896);
                graphics.setFont(game.l.n());
                graphics.drawString(game.l.a(game.f.B().l - game.f.B().k)[0], 10, 40, 20);
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    private void af() {
        block45: {
            block50: {
                block49: {
                    block48: {
                        block47: {
                            block46: {
                                if (this.Z.F() || this.m.i() >= 5 || this.d.j() || !this.d.J()) break block45;
                                if (this.i(4100)) {
                                    this.m.a((byte)1, (byte)2);
                                } else if (this.i(8448)) {
                                    this.m.a((byte)1, (byte)0);
                                } else if (this.i(16400)) {
                                    this.m.a((byte)1, (byte)3);
                                } else if (this.i(32832)) {
                                    this.m.a((byte)1, (byte)1);
                                }
                                if (this.g(65568)) {
                                    if (game.l.E != -1) {
                                        this.m.a((byte)0, this.m.o);
                                        if (game.e.s) {
                                            game.e.t = true;
                                            game.e.s = false;
                                        } else {
                                            if (this.n[game.l.E].a.a <= 85) {
                                                this.n[game.l.E].n = this.n[game.l.E].o;
                                                switch (this.m.o) {
                                                    case 0: {
                                                        var2_1 = 2;
                                                        var1_2 = this.n[game.l.E];
                                                        this.n[game.l.E].o = (byte)var2_1;
                                                        break;
                                                    }
                                                    case 2: {
                                                        var2_1 = 0;
                                                        var1_2 = this.n[game.l.E];
                                                        this.n[game.l.E].o = (byte)var2_1;
                                                        break;
                                                    }
                                                    case 3: {
                                                        var2_1 = 1;
                                                        var1_2 = this.n[game.l.E];
                                                        this.n[game.l.E].o = (byte)var2_1;
                                                        break;
                                                    }
                                                    case 1: {
                                                        var2_1 = 3;
                                                        var1_2 = this.n[game.l.E];
                                                        this.n[game.l.E].o = (byte)var2_1;
                                                    }
                                                }
                                                this.n[game.l.E].a((byte)0);
                                            }
                                            if (this.n[game.l.E].a.a == 17) {
                                                this.d.d = 0;
                                                this.a((byte)27);
                                            } else {
                                                this.a((byte)23);
                                            }
                                        }
                                        game.l.B().D();
                                    } else if ((h)this.m.q != null && ((h)this.m.q).u == 3) {
                                        this.m.w();
                                    } else {
                                        this.m.x();
                                    }
                                }
                                if (this.h(61780)) {
                                    this.m.a((byte)0, this.m.o);
                                }
                                if (!this.g(262144)) break block46;
                                this.r();
                                this.d.b = 0;
                                this.a((byte)6);
                                break block45;
                            }
                            if (!this.g(131072)) break block47;
                            this.d.b = 0;
                            this.a((byte)13);
                            break block45;
                        }
                        if (!this.g(1)) break block48;
                        var1_2 = this;
                        var2_1 = 1;
                        for (var3_5 = 0; var3_5 < game.l.as.length; ++var3_5) {
                            if (game.l.as[var3_5] != var1_2.p) continue;
                            var2_1 = 0;
                            break;
                        }
                        if (var2_1 != 0) {
                            for (var3_5 = 0; var3_5 < var1_2.aS[var1_2.aD].length / 7; var3_5 = (byte)((byte)(var3_5 + 1))) {
                                if (var1_2.aS[var1_2.aD][var3_5 * 7 + 2] != var1_2.p || var1_2.aS[var1_2.aD][var3_5 * 7 + 3] != var1_2.q) continue;
                                var1_2.aG = var3_5;
                                break;
                            }
                            var1_2.aJ = (game.l.g() >> 1) - (var1_2.aS[var1_2.aD][var1_2.aG * 7] << 4) - 40;
                            var1_2.aK = (game.l.h() >> 1) - (var1_2.aS[var1_2.aD][var1_2.aG * 7 + 1] << 3) - 20;
                            var1_2.aL = true;
                            if (var1_2.aM == null) {
                                var1_2.aM = new g();
                                var1_2.aM.a(0, false);
                                var1_2.aM.a((byte)3, (byte)-1, false);
                                var1_2.aM.c();
                            }
                            if (var1_2.aN == null) {
                                var1_2.aN = new g[4];
                                for (var3_5 = 0; var3_5 < var1_2.aN.length; var3_5 = (byte)(var3_5 + 1)) {
                                    var1_2.aN[var3_5] = new g();
                                    var1_2.aN[var3_5].a(223, false);
                                    if (var3_5 <= 1) {
                                        var1_2.aN[var3_5].b(game.l.g() >> 1, 20 + var3_5 * (game.l.h() - 20));
                                    } else {
                                        var1_2.aN[var3_5].b(10 + var3_5 % 2 * (game.l.g() - 20), game.l.h() >> 1);
                                    }
                                    var1_2.aN[var3_5].a(var3_5, (byte)-1, false);
                                    super.o(var3_5);
                                }
                            }
                            game.a.B().m = Image.createImage((int)a.a.g(), (int)a.a.h());
                            var3_6 = game.a.B().m.getGraphics();
                            var1_2.m.a((byte)0, var1_2.m.o);
                            var1_2.l.b(var3_6);
                            var1_2.a((byte)4);
                        } else {
                            var1_2.d.b("Khu n\u00e0y kh\u00f4ng c\u00f3 b\u1ea3n \u0111\u1ed3");
                        }
                        break block45;
                    }
                    if (!this.g(2)) break block49;
                    this.d.b = 0;
                    this.a((byte)10);
                    break block45;
                }
                if (!this.g(8)) break block50;
                this.d.b = 1;
                this.a((byte)10);
                break block45;
            }
            if (!this.g(512)) break block45;
            var1_2 = this;
            if (var1_2.p == 3 && var1_2.q == 7) ** GOTO lbl-1000
            if (var1_2.m.u >= 0 && var1_2.Z.x) {
                if (var1_2.m.s()) {
                    var1_2.m.t();
                }
                v0 = false;
            } else if (var1_2.Z.x) {
                var1_2.a((byte)5);
                v0 = true;
            } else lbl-1000:
            // 2 sources

            {
                v0 = false;
            }
        }
        this.m.r();
        for (var1_3 = 0; var1_3 < this.n.length; ++var1_3) {
            this.n[var1_3].q();
        }
        if (this.y != null && this.y.j()) {
            this.y.a(this.ar.a, this.y.a);
        }
        this.m.p = this.m.o;
        this.l.b();
        this.M();
        if (game.l.Q == 1 && game.l.i) {
            this.a((byte)25);
        }
        if (!this.Z.F() && !this.d.J() && !game.l.U && game.l.V[0] != -1 && this.g(65568)) {
            game.l.f = (byte)4;
            game.l.U = true;
            this.d.c = 0;
            this.a((byte)7);
            this.d.I();
        }
        if (!this.d.j() && game.l.B == 0 && this.O()) {
            this.d.b("C\u00f3 th\u1ec3 ti\u1ebfn h\u00e0nh s\u1ea3n xu\u1ea5t tr\u01b0\u0301ng su\u0309ng v\u00e2\u0323t");
            game.l.B = 1;
        }
        this.Z.C();
        this.d.e();
        var1_4 = this;
        for (var2_1 = 0; var2_1 < var1_4.m.W.size(); ++var2_1) {
            v1 = (int[])var1_4.m.W.elementAt(var2_1);
            var3_7 = v1;
            v1[3] = v1[3] + 5;
            if (var3_7[3] <= 30) continue;
            var1_4.m.W.removeElementAt(var2_1);
            --var2_1;
        }
        if (!this.d.j()) {
            this.Z.a();
            this.q();
        }
    }

    private void ag() {
        if (this.d.d(D, C) && !this.d.j() && this.g(196640)) {
            if (a.e.b < a.e.b()) {
                a.e.c();
                this.d.b(a.e.b);
            } else {
                this.d.aF();
                if (this.n[game.l.E].a.a <= 85) {
                    byte by = this.n[game.l.E].n;
                    h h2 = this.n[E];
                    this.n[E].o = by;
                }
                this.n[E].a((byte)0);
                this.m.a((byte)0, this.m.o);
                this.d.f = 1;
                if (this.aZ) {
                    this.aV[this.aX] = true;
                    if (this.aX < this.aW.length - 1) {
                        this.m.v(1);
                        this.d.b("\u0110\u1ea1t \u0111\u01b0\u1ee3c 1 huy hi\u1ec7u");
                    } else if (this.m.b((byte)7, (byte)0) == 0) {
                        this.d.b("\u0110\u1ea1t \u0111\u01b0\u1ee3c ho\u00e0ng kim huy hi\u1ec7u");
                        this.m.b((byte)7, (byte)0, (byte)2);
                        game.e.G = (byte)(game.e.E.length / 2);
                    }
                }
            }
        }
        this.d.f();
        if (this.d.f == 1 && this.d.aA()) {
            this.d.d = 0;
            this.a((byte)27);
        }
        this.l.b();
    }

    private boolean a(int[] nArray) {
        int n2 = -1;
        if (nArray[2] != -1) {
            n2 = a.e.b(nArray[2], nArray[3]);
        }
        if (!this.C()) {
            return false;
        }
        int n3 = a.e.b(am[v[this.p] + this.q][3], am[v[this.p] + this.q][4]);
        this.m.a((byte)a.b.c.c[0][nArray[0]][1], nArray[0], (byte)1);
        game.a.B().a(new int[][]{{nArray[0], n3, n2}});
        return true;
    }

    public final void m(int n2) {
        int n3;
        int n4;
        for (int i2 = 0; i2 < (am[v[n4 = this.p] + (n3 = this.q)].length - 5) / 4; ++i2) {
            n4 = this.p;
            n3 = this.q;
            if (n2 != am[v[n4] + n3][5 + (i2 << 2) + 4]) continue;
            int[] nArray = new int[4];
            n3 = this.q;
            n4 = this.p;
            System.arraycopy(am[v[n4] + n3], 5 + (i2 << 2), nArray, 0, nArray.length);
            this.a(nArray);
            this.ah();
            return;
        }
    }

    public final void e(boolean bl) {
        int n2;
        int n3;
        if (this.aU == bl) {
            return;
        }
        if (!this.C()) {
            return;
        }
        this.aU = bl;
        for (int i2 = 0; i2 < (am[v[n3 = this.p] + (n2 = this.q)].length - 5) / 4; ++i2) {
            n2 = this.q;
            n3 = this.p;
            n3 = am[v[n3] + n2][5 + (i2 << 2) + 4];
            if (bl) {
                this.n[n3].c();
                continue;
            }
            this.n[n3].d();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public final void M() {
        block17: {
            boolean bl;
            block16: {
                if (!this.m.E()) break block17;
                l l2 = this;
                byte by = a.b.d.a().a(0, game.j.p().j, game.j.p().k);
                int[] nArray = null;
                ab = by;
                switch (by) {
                    case 0: {
                        if (an.size() <= 0) {
                            bl = false;
                            break block16;
                        } else {
                            nArray = (int[])an.elementAt(a.e.a(an.size()));
                            break;
                        }
                    }
                    case 1: {
                        if (ao.size() <= 0) {
                            bl = false;
                            break block16;
                        } else {
                            nArray = (int[])ao.elementAt(a.e.a(ao.size()));
                            break;
                        }
                    }
                    case 2: {
                        if (ap.size() <= 0) {
                            bl = false;
                            break block16;
                        } else {
                            nArray = (int[])ap.elementAt(a.e.a(ap.size()));
                            break;
                        }
                    }
                    case 4: {
                        if (aq.size() <= 0) {
                            bl = false;
                            break block16;
                        } else {
                            nArray = (int[])aq.elementAt(a.e.a(aq.size()));
                            break;
                        }
                    }
                    case 3: {
                        bl = false;
                        break block16;
                    }
                }
                bl = l2.a(nArray);
            }
            if (!bl) {
                this.m.w = this.m.D();
                return;
            }
            game.e.v = this.p != 3 || this.q != 7;
            this.ah();
        }
    }

    public final void N() {
        game.a.B().m = Image.createImage((int)a.a.g(), (int)a.a.h());
        Graphics graphics = game.a.B().m.getGraphics();
        this.l.b(graphics);
    }

    private void ah() {
        a a2 = game.a.B();
        game.a.B().getClass();
        a2.k = 0;
        game.a.B().l = 0;
        this.N();
        this.m.a((byte)0, this.m.o);
        this.m.w = this.m.D();
        game.f.B().a((byte)12);
        this.W.a(4, 1);
    }

    public static int e(int n2, int n3) {
        return v[n2] + n3;
    }

    public final void a(int n2, int n3, byte by, boolean bl) {
        int n4 = this.p;
        int n5 = this.q;
        if (ai[v[n4] + n5] != null && ai[v[n4 = this.p] + (n5 = this.q)][n2] != null) {
            n5 = this.q;
            n4 = this.p;
            game.l.ak[game.l.v[n4] + n5][0] = bl;
            n5 = this.q;
            n4 = this.p;
            game.l.ai[game.l.v[n4] + n5][n2][n3] = by;
        }
    }

    public final void a(int n2, int n3, int n4) {
        int n5 = this.p;
        int n6 = this.q;
        if (aj[v[n5] + n6] != null && aj[v[n5 = this.p] + (n6 = this.q)][n2] != null) {
            n6 = this.q;
            n5 = this.p;
            game.l.aj[game.l.v[n5] + n6][n2][n3] = (short)n4;
        }
    }

    public final boolean n(int n2) {
        for (int i2 = 0; i2 < this.au.length / 4; ++i2) {
            if (this.au[i2 << 2] != this.p || this.au[(i2 << 2) + 1] != this.q || n2 != this.au[(i2 << 2) + 2] || this.m.C[this.au[(i2 << 2) + 3]][0] != 2) continue;
            return true;
        }
        return false;
    }

    public final boolean O() {
        return this.m.J == 0 && A >= 10 || this.m.J > 0 && A >= 30;
    }

    private void ai() {
        int n2;
        this.aX = (byte)this.aV.length;
        for (n2 = 0; n2 < this.aV.length; ++n2) {
            if (this.aV[n2]) continue;
            this.aX = (byte)n2;
            break;
        }
        n2 = 0;
        for (int i2 = this.aW.length - 1; i2 >= 0; --i2) {
            if (this.m.G < this.aW[i2]) continue;
            this.aY = (byte)i2;
            n2 = 1;
            break;
        }
        if (n2 == 0) {
            this.aY = (byte)-1;
        }
    }

    public final void q() {
        switch (f) {
            case 1: {
                if (g == 0) {
                    game.l.b(0, 1);
                    if (i) {
                        game.l.b(1, 1);
                    } else {
                        game.l.b(1, 0);
                    }
                    g = (byte)(g + 1);
                    this.a((byte)6);
                    return;
                }
                if (g == 1) {
                    g = (byte)(g + 1);
                    this.d.c("H\u00e3y l\u1ef1a ch\u1ecdn #2S\u1ee7ng v\u1eadt");
                    return;
                }
                if (g == 3) {
                    game.l.b(1, 0);
                    String string = game.l.c(a.b.c.c[0][this.m.A[a.a.d(1)].r()][0]);
                    g = (byte)(g + 1);
                    this.d.c("H\u00e3y l\u1ef1a ch\u1ecdn #2" + string);
                    return;
                }
                if (g == 4) {
                    if (!this.d.aB() || !a.a.a(this.d.b, 0)) break;
                    g = (byte)(g + 1);
                    this.d.c("H\u00e3y nh\u1ea5n #2n\u00fat 5");
                    return;
                }
                if (g == 6) {
                    g = (byte)(g + 1);
                    this.d.c("H\u00e3y l\u1ef1a ch\u1ecdn #2V\u1eadt ph\u1ea9m trang s\u1ee9c");
                    return;
                }
                if (g == 8) {
                    game.l.b(1, 0);
                    g = (byte)(g + 1);
                    this.d.c("Nh\u1ea5n #2n\u00fat 5#1 trang th\u01b0\u1ee3ng v\u1eadt ph\u1ea9m trang s\u1ee9c");
                    return;
                }
                if (g != 10) break;
                this.d.c("Nh\u1ea5n #2n\u00fat m\u1ec1m ph\u1ea3i#0 \u0111\u1ec3 quay l\u1ea1i");
                game.l.b(1, -1);
                game.l.b(0, 2);
                g = (byte)(g + 1);
                return;
            }
            case 3: {
                if (g == 0) {
                    g = (byte)(g + 1);
                    game.l.b(1, 0);
                    game.l.b(0, 1);
                    this.a((byte)1);
                    return;
                }
                if (g == 1) {
                    if (!game.l.a(this.d.b, 0)) break;
                    g = (byte)(g + 1);
                    this.d.c("H\u00e3y nh\u1ea5n v\u00e0o m\u1ee5c #2Mua s\u1eafm");
                    return;
                }
                if (g == 3) {
                    g = (byte)(g + 1);
                    this.d.c("Tr\u01b0\u1edbc ti\u00ean h\u00e3y mua #2H\u1ed3ng s\u1eafc \u1ed1c bi\u1ec3n#1");
                    return;
                }
                if (g == 4) {
                    if (!this.d.aB()) break;
                    game.l.b(1, 1);
                    g = (byte)(g + 1);
                    return;
                }
                if (g == 5) {
                    if (!game.l.a(this.d.b, 0)) break;
                    g = (byte)(g + 1);
                    this.d.c("Nh\u1ea5n #2n\u00fat 5#1 mua s\u1eafm");
                    return;
                }
                if (g != 7) break;
                this.d.c("H\u00e3y nh\u1ea5n #2n\u00fat m\u1ec1m ph\u1ea3i#1 \u0111\u1ec3 quay l\u1ea1i");
                game.l.b(1, -1);
                game.l.b(0, 2);
                g = (byte)(g + 1);
                return;
            }
            case 4: {
                if (g == 0) {
                    game.l.b(0, 1);
                    for (int i2 = 0; i2 < this.m.B; ++i2) {
                        if (this.m.A[i2].t() != V[0] || this.m.A[i2].r() != V[1]) continue;
                        game.l.b(1, i2);
                        break;
                    }
                    g = (byte)(g + 1);
                    String string = game.l.c(a.b.c.a((byte)0, (short)this.m.A[a.a.d(1)].r(), (byte)0));
                    this.d.c("H\u00e3y l\u1ef1a ch\u1ecdn #2" + string + "#0 ti\u1ebfn h\u00e0nh ti\u1ebfn h\u00f3a");
                    return;
                }
                if (g == 1) {
                    if (!game.l.a(this.d.b, 0) || !this.d.aB()) break;
                    g = (byte)(g + 1);
                    this.d.c("H\u00e3y nh\u1ea5n #2n\u00fat 5#0 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                    return;
                }
                if (g == 3) {
                    if (!game.l.a(this.d.c, 0)) break;
                    g = (byte)(g + 1);
                    this.d.c("Nh\u1ea5n #2n\u00fat 5#0 \u0111\u1ec3 v\u00e0o m\u1ee5c Ti\u1ebfn h\u00f3a");
                    return;
                }
                if (g != 5) break;
                g = (byte)(g + 1);
                this.d.c("Nh\u1ea5n #2n\u00fat m\u1ec1m tr\u00e1i#0 \u0111\u1ec3 Ti\u1ebfn h\u00f3a");
                return;
            }
            case 6: {
                if (g == 0) {
                    g = (byte)(g + 1);
                    game.l.b(0, 1);
                    if (i) {
                        game.l.b(1, 2);
                    } else {
                        game.l.b(1, 1);
                    }
                    this.a((byte)6);
                    return;
                }
                if (g == 1) {
                    g = (byte)(g + 1);
                    this.d.c("H\u00e3y l\u1ef1a ch\u1ecdn #2Ba l\u00f4#0");
                    return;
                }
                if (g == 2) {
                    if (!this.d.aB() || !game.l.a(this.d.b, 0)) break;
                    this.d.c("Nh\u1ea5n #2n\u00fat m\u1ec1m tr\u00e1i#0 v\u00e0o Tuy\u1ec3n h\u1ea1ng");
                    g = (byte)(g + 1);
                    return;
                }
                if (g == 4) {
                    g = (byte)(g + 1);
                    this.d.c("H\u00e3y s\u1eed d\u1ee5ng #2Gia t\u1ed1c d\u01b0\u1ee3c#0");
                    return;
                }
                if (g == 5) {
                    if (!this.d.aB() || !game.l.a(this.d.h, 0)) break;
                    g = (byte)(g + 1);
                    this.d.c("Nh\u1ea5n #2n\u00fat m\u1ec1m tr\u00e1i#0 s\u1eed d\u1ee5ng");
                    return;
                }
                if (g == 7) {
                    g = (byte)(g + 1);
                    game.l.b(0, 3);
                    game.l.b(2, 1);
                    game.l.b(1, 3);
                    this.d.c("H\u00e3y l\u1ef1a ch\u1ecdn #2\u0110\u1eb7c th\u00f9 \u0111\u1ea1o c\u1ee5#0 \u1ea5p tr\u1ee9ng tr\u01b0\u0301ng su\u0309ng v\u00e2\u0323t");
                    return;
                }
                if (g == 9) {
                    if (!this.d.aB() || !game.l.a(this.d.h, 0)) break;
                    game.l.b(0, 1);
                    this.d.c("Nh\u1ea5n #2n\u00fat m\u1ec1m tr\u00e1i#0 \u0111\u1ec3 \u1ea4p tr\u1ee9ng");
                    g = (byte)(g + 1);
                    return;
                }
                if (g != 11) break;
                g = (byte)(g + 1);
                game.l.b(0, 2);
                game.l.b(1, -1);
                this.d.c("Nh\u1ea5n #2n\u00fat m\u1ec1m ph\u1ea3i#0 \u0111\u1ec3 quay l\u1ea1i");
            }
        }
    }

    public final void r() {
        switch (f) {
            case 1: {
                if (g == 2 || g == 7) {
                    g = (byte)(g + 1);
                    return;
                }
                if (g == 5) {
                    game.l.b(1, 2);
                    g = (byte)(g + 1);
                    return;
                }
                if (g != 9) break;
                g = (byte)(g + 1);
                int n2 = this.q;
                int n3 = this.p;
                this.Z.n[game.l.v[n3] + n2][this.Z.E()] = 3;
                if (this.Z.l == null) break;
                this.Z.l[this.Z.E()].a((byte)3);
                return;
            }
            case 3: {
                if (g != 6 && g != 2) break;
                g = (byte)(g + 1);
                return;
            }
            case 4: {
                if (g == 2) {
                    game.l.b(1, 5);
                    g = (byte)(g + 1);
                    return;
                }
                if (g == 4) {
                    g = (byte)(g + 1);
                    return;
                }
                if (g != 6) break;
                this.d.c("Nh\u1ea5n #2n\u00fat m\u1ec1m ph\u1ea3i#0 \u0111\u1ec3 quay l\u1ea1i");
                game.l.b(1, -1);
                game.l.b(0, 2);
                g = (byte)(g + 1);
                return;
            }
            case 6: {
                if (g == 3) {
                    for (int i2 = 0; i2 < this.m.K.size() + this.m.L.size(); ++i2) {
                        if (i2 < this.m.L.size()) continue;
                        if (this.m.K.size() <= 0) break;
                        if (((int[])this.m.K.elementAt(i2 - this.m.L.size()))[0] != 14) continue;
                        game.l.b(1, i2);
                        break;
                    }
                    g = (byte)(g + 1);
                    return;
                }
                if (g == 6 || g == 10) {
                    g = (byte)(g + 1);
                    return;
                }
                if (g != 8 || !game.l.a(this.d.b, 1)) break;
                this.d.c("H\u00e3y l\u1ef1a ch\u1ecdn #2Tr\u01b0\u0301ng su\u0309ng v\u00e2\u0323t#0 \u0111\u1ec3 \u1ea5p tr\u1ee9ng");
                game.l.b(2, 0);
                game.l.b(1, 0);
                g = (byte)(g + 1);
            }
        }
    }

    protected static String[] a(long l2) {
        String[] stringArray = new String[2];
        String[] stringArray2 = stringArray;
        stringArray2[0] = "0'00\"000";
        stringArray[1] = "0'00\"000";
        long l3 = l2 % 1000L;
        long l4 = (l2 /= 1000L) % 60L;
        long l5 = (l2 /= 60L) % 60L;
        long l6 = l2 / 60L;
        String string = l3 < 10L ? "00" + l3 : (l3 < 100L ? "0" + l3 : "" + l3);
        String string2 = l4 < 10L ? "0" + l4 + "\"" : l4 + "\"";
        String string3 = l2 + "'";
        stringArray2[0] = string3 + string2 + string;
        String string4 = l6 + "'";
        string3 = l5 + "\"";
        string2 = l4 < 10L ? "0" + l4 : "" + l4;
        stringArray2[1] = string4 + string3 + string2;
        return stringArray2;
    }

    static {
        v = new int[]{0, 2, 9, 17, 25, 38, 45, 47, 60, 67, 75, 90};
        w = null;
        ag = null;
        x = null;
        ah = new a.b.e[10];
        ai = null;
        aj = null;
        ak = null;
        an = new Vector();
        ao = new Vector();
        ap = new Vector();
        aq = new Vector();
        A = 0;
        B = 0;
        E = (short)-1;
        F = (short)-1;
        G = 0;
        H = false;
        I = -1;
        J = 0;
        K = 0;
        L = game.l.g();
        M = game.l.h();
        as = new byte[]{9, 10, 11};
        aw = new String[]{"PK6_RMS_ACTOR", "PK6_RMS_WORLD", "PK6_RMS_EVENT", "PK6_RMS_RMS", "PK6_RMS_SMS", "PK6_RMS_CNTSMS", "PK6_RMS_GOLD", "PK6_RMS_POKPET", "PK6_RMS_CONITEM", "PK6_RMS_PETBALL"};
        N = false;
        O = null;
        R = null;
        S = 0;
        T = false;
        U = false;
        V = new byte[2];
        ay = null;
        X = (byte)-1;
        az = null;
        aA = (byte)-1;
        ab = (byte)-1;
    }
}

