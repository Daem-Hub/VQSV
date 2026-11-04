/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.rms.RecordStore
 *  javax.microedition.rms.RecordStoreException
 *  javax.microedition.rms.RecordStoreNotOpenException
 */
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import javax.microedition.lcdui.Graphics;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

public final class ao {
    private w[] a;
    private int b;
    private al c;
    private int[] d;
    private int[] e;
    private int[] f;
    private y g;
    private i h;

    public ao(i i2) {
        this.a = new w[200];
        this.b = 0;
        this.d = new int[]{0, 1, 2, 3, 5, 6, 7, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
        this.e = null;
        this.f = null;
        this.h = i2;
        this.d();
    }

    private void d() {
        this.c = new al();
        this.c.a(0);
        this.c.c(-1);
        this.e = ae.b(100);
        this.f();
    }

    public final al a() {
        return this.c;
    }

    public final void a(y y2) {
        this.g = y2;
    }

    public final void a(String object, int n2) {
        this.d();
        byte[] byArray = new byte[20000];
        ae.b(byArray, (String)object);
        object = new int[]{0};
        ae.d(byArray, (int[])object);
        ae.d(byArray, (int[])object);
        short s2 = ae.c(byArray, (int[])object);
        this.c.b(s2);
        s2 = ae.d(byArray, (int[])object);
        this.c.a(s2);
        s2 = ae.d(byArray, (int[])object);
        this.c.a(s2, this.c);
        s2 = ae.d(byArray, (int[])object);
        this.c.b(s2, this.c);
        s2 = ae.d(byArray, (int[])object);
        this.c.c(s2, this.c);
        s2 = ae.d(byArray, (int[])object);
        this.c.d(s2, this.c);
        this.a[this.b] = this.c;
        this.b = 1;
        this.a(byArray, (int[])object, this.c, n2, false);
        object = this;
        this.e = ae.b(50);
        super.a(((ao)object).c, -1);
    }

    private void a(byte[] byArray, int[] nArray, al al2, int n2, boolean bl) {
        int n3;
        int n4;
        int n5 = ae.c(byArray, nArray);
        if (n5 > 0) {
            byte[][] byArray2 = new byte[n5][4];
            for (n4 = 0; n4 < n5; ++n4) {
                byArray2[n4][0] = ae.c(byArray, nArray);
                byArray2[n4][1] = ae.c(byArray, nArray);
                byArray2[n4][2] = ae.c(byArray, nArray);
                byArray2[n4][3] = ae.c(byArray, nArray);
            }
            al2.a(byArray2);
        }
        int n6 = ae.c(byArray, nArray);
        for (n4 = 0; n4 < n6; ++n4) {
            int n7;
            int n8;
            int n9;
            n5 = ae.c(byArray, nArray);
            z z2 = new z(n5);
            new z(n5).g = ae.c(byArray, nArray) != 0;
            z2.d = ae.d(byArray, nArray);
            z2.a = ae.d(byArray, nArray);
            z2.h = ae.c(byArray, nArray);
            z2.i = ae.c(byArray, nArray);
            for (n9 = 0; n9 < z2.a; ++n9) {
                n8 = ae.d(byArray, nArray);
                z2.b[n9] = n8;
                n7 = ae.d(byArray, nArray);
                byte[] byArray3 = new byte[n7];
                for (int i2 = 0; i2 < n7; ++i2) {
                    byArray3[i2] = ae.c(byArray, nArray);
                }
                z2.j.addElement(ae.a(byArray3));
            }
            z2.c = new int[z2.d + this.d.length][][];
            for (n9 = 0; n9 < z2.c.length; ++n9) {
                z2.c[n9] = new int[0][];
            }
            n9 = ae.d(byArray, nArray);
            for (n8 = 0; n8 < n9; ++n8) {
                n7 = ae.d(byArray, nArray);
                int n10 = ae.d(byArray, nArray);
                int[][] nArrayArray = new int[n10][];
                for (n3 = 0; n3 < n10; ++n3) {
                    nArrayArray[n3] = new int[5];
                    nArrayArray[n3][0] = ae.d(byArray, nArray);
                    nArrayArray[n3][1] = ae.d(byArray, nArray);
                    nArrayArray[n3][2] = ae.d(byArray, nArray);
                    nArrayArray[n3][3] = ae.d(byArray, nArray);
                    nArrayArray[n3][4] = ae.d(byArray, nArray);
                }
                z2.c[n7] = nArrayArray;
            }
            if (n5 == 0) {
                al2.a = z2;
                continue;
            }
            al2.b = z2;
        }
        n4 = ae.d(byArray, nArray);
        for (n5 = 0; n5 < n4; ++n5) {
            short s2;
            int n11;
            w w2;
            int n12 = ae.c(byArray, nArray);
            if (n12 == 0) {
                w2 = new al();
                ((al)w2).b(n12);
                ((al)w2).a(ae.d(byArray, nArray));
                ((al)w2).a(ae.d(byArray, nArray), this.c);
                ((al)w2).b(ae.d(byArray, nArray), this.c);
                ((al)w2).c(ae.d(byArray, nArray), this.c);
                ((al)w2).d(ae.d(byArray, nArray), this.c);
                ((al)w2).c(al2.a());
                if (al2.a != null) {
                    for (int i3 = 0; i3 < al2.a.b.length; ++i3) {
                        if (al2.a.b[i3] != ((al)w2).a()) continue;
                        ((al)w2).a(al2.a);
                        break;
                    }
                }
                if (al2.b != null) {
                    for (int i4 = 0; i4 < al2.b.b.length; ++i4) {
                        if (al2.b.b[i4] != ((al)w2).a()) continue;
                        ((al)w2).a(al2.b);
                        break;
                    }
                }
                this.a[this.b] = w2;
                ++this.b;
                al2.g()[n5] = w2;
                this.a(byArray, nArray, (al)al2.g()[n5], n2, bl);
                continue;
            }
            if (n12 == 1) {
                w2 = new af();
                ((af)w2).b(n12);
                ((af)w2).a(ae.d(byArray, nArray));
                ((af)w2).a(ae.d(byArray, nArray), this.c);
                ((af)w2).b(ae.d(byArray, nArray), this.c);
                ((af)w2).c(ae.d(byArray, nArray), this.c);
                ((af)w2).d(ae.d(byArray, nArray), this.c);
                ((af)w2).l();
                ((af)w2).c = this.g;
                int n13 = ae.d(byArray, nArray);
                byte[] byArray4 = new byte[n13];
                for (n11 = 0; n11 < n13; ++n11) {
                    byArray4[n11] = ae.c(byArray, nArray);
                }
                ((af)w2).h().a = ae.a(byArray4);
                ((af)w2).h().b = ae.c(byArray, nArray);
                ((af)w2).h().c = ae.c(byArray, nArray);
                ((af)w2).h().d = ae.c(byArray, nArray) != 0;
                ((af)w2).h().e = ae.e(byArray, nArray);
                ((af)w2).h().f = ae.e(byArray, nArray);
                ((af)w2).h().g = ae.e(byArray, nArray);
                n11 = ae.d(byArray, nArray);
                s2 = ae.c(byArray, nArray);
                if (n11 < 0) {
                    ((af)w2).h().i = null;
                } else {
                    ((af)w2).h().i = new m();
                    ((af)w2).h().i.a = s2;
                    ((af)w2).h().i.a(n11);
                }
                ((af)w2).h().j = ae.e(byArray, nArray);
                ((af)w2).h().k = ae.e(byArray, nArray);
                ((af)w2).h().l = ae.e(byArray, nArray);
                n3 = ae.d(byArray, nArray);
                n6 = ae.c(byArray, nArray);
                if (n3 < 0) {
                    ((af)w2).h().m = null;
                } else {
                    ((af)w2).h().m = new m();
                    ((af)w2).h().m.a(n3);
                    ((af)w2).h().m.a = n6;
                }
                ((af)w2).h().h = ae.c(byArray, nArray);
                if (((af)w2).h().i != null) {
                    ((af)w2).h().i.a(n2, bl, ((af)w2).h().h);
                }
                if (((af)w2).h().m != null) {
                    ((af)w2).h().m.a(n2, bl, ((af)w2).h().h);
                }
                ((af)w2).c(al2.a());
                if (al2.a != null) {
                    for (n12 = 0; n12 < al2.a.b.length; ++n12) {
                        if (al2.a.b[n12] != ((af)w2).a()) continue;
                        ((af)w2).a(al2.a);
                        break;
                    }
                }
                if (al2.b != null) {
                    for (n12 = 0; n12 < al2.b.b.length; ++n12) {
                        if (al2.b.b[n12] != ((af)w2).a()) continue;
                        ((af)w2).a(al2.b);
                        break;
                    }
                }
                this.a[this.b] = w2;
                ++this.b;
                al2.g()[n5] = w2;
                ((af)w2).a = ae.c(byArray, nArray);
                ((af)w2).b = ae.c(byArray, nArray);
                continue;
            }
            if (n12 != 2) continue;
            w2 = new ac();
            ((ac)w2).q(ae.d(byArray, nArray));
            ((ac)w2).a(ae.d(byArray, nArray), this.c);
            ((ac)w2).b(ae.d(byArray, nArray), this.c);
            ((ac)w2).a((int)ae.c(byArray, nArray));
            ((ac)w2).b(ae.c(byArray, nArray));
            ((ac)w2).c(ae.c(byArray, nArray));
            ((ac)w2).d(ae.c(byArray, nArray));
            ((ac)w2).e(ae.c(byArray, nArray));
            ((ac)w2).f(ae.c(byArray, nArray));
            ((ac)w2).g(ae.c(byArray, nArray));
            ((ac)w2).h(ae.c(byArray, nArray));
            ((ac)w2).k(ae.c(byArray, nArray));
            ((ac)w2).l(ae.c(byArray, nArray));
            ((ac)w2).m(ae.c(byArray, nArray));
            ((ac)w2).n(ae.c(byArray, nArray));
            ((ac)w2).o(ae.c(byArray, nArray));
            ((ac)w2).p(ae.c(byArray, nArray));
            ((ac)w2).i(ae.c(byArray, nArray));
            ((ac)w2).j(ae.c(byArray, nArray));
            ((ac)w2).a = ae.e(byArray, nArray);
            short s3 = ae.d(byArray, nArray);
            int n14 = ae.c(byArray, nArray);
            if (s3 < 0) {
                ((ac)w2).b = null;
            } else {
                ((ac)w2).b = new m();
                ((ac)w2).b.a(s3);
                ((ac)w2).b.a = n14;
                ((ac)w2).b.a(n2, bl, (byte)n14);
            }
            s3 = ae.d(byArray, nArray);
            n14 = ae.c(byArray, nArray);
            if (s3 < 0) {
                ((ac)w2).c = null;
            } else {
                ((ac)w2).c = new m();
                ((ac)w2).c.a(s3);
                ((ac)w2).c.a = n14;
                ((ac)w2).c.a(n2, bl, (byte)n14);
            }
            ((ac)w2).r(ae.d(byArray, nArray));
            byte by = ae.c(byArray, nArray);
            n11 = by;
            if (by == 0) {
                ((ac)w2).e = ((ac)w2).n();
            } else if (n11 == 1) {
                n12 = ((ac)w2).m();
                n6 = ((ac)w2).l();
                a[] aArray = new a[n6 * n12];
                for (n14 = 0; n14 != n6 * n12; ++n14) {
                    aArray[n14] = new a();
                }
                ((ac)w2).e = aArray;
                s2 = ae.d(byArray, nArray);
                for (n3 = 0; n3 < s2; ++n3) {
                    n6 = ae.d(byArray, nArray);
                    n12 = ae.d(byArray, nArray);
                    byte by2 = ae.c(byArray, nArray);
                    n14 = ae.d(byArray, nArray);
                    n11 = ae.d(byArray, nArray);
                    short s4 = ae.d(byArray, nArray);
                    short s5 = ae.d(byArray, nArray);
                    ((ac)w2).e[n6] = new a(n12, by2, n14, n11, s4, s5);
                }
            }
            ((ac)w2).s(al2.a());
            this.a[this.b] = w2;
            ++this.b;
            al2.g()[n5] = w2;
        }
    }

    private ak a(w w2) {
        if (w2.i() != 0) {
            return new ak(w2.b(), w2.c(), w2.d(), w2.e());
        }
        al al2 = (al)w2;
        if (al2.g() == null || al2.g()[0] == null) {
            return new ak(w2.b(), w2.c(), w2.d(), w2.e());
        }
        int n2 = this.a((w)al2.g()[0]).a;
        int n3 = this.a((w)al2.g()[0]).a + this.a((w)al2.g()[0]).c;
        int n4 = this.a((w)al2.g()[0]).b;
        int n5 = this.a((w)al2.g()[0]).b + this.a((w)al2.g()[0]).d;
        for (int i2 = 0; i2 < al2.g().length && al2.g()[i2] != null; ++i2) {
            ak ak2 = this.a(al2.g()[i2]);
            if (n2 > ak2.a) {
                n2 = ak2.a;
            }
            if (n3 < ak2.a + ak2.c) {
                n3 = ak2.a + ak2.c;
            }
            if (n4 > ak2.b) {
                n4 = ak2.b;
            }
            if (n5 >= ak2.b + ak2.d) continue;
            n5 = ak2.b + ak2.d;
        }
        return new ak(n2, n4, n3 - n2, n5 - n4);
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    public final void a(Graphics var1_1) {
        var3_2 = this.c;
        var2_3 = var1_1 /* !! */ ;
        var1_1 /* !! */  = this;
        var6_4 = var3_2;
        var5_6 = var2_3;
        var4_8 = var1_1 /* !! */ ;
        if (var6_4.a == null && var6_4.b == null) ** GOTO lbl-1000
        if (var6_4.a == null && var6_4.b != null) {
            v0 = var6_4.b;
        } else if (var6_4.a != null && var6_4.b == null) {
            v0 = var6_4.a;
        } else lbl-1000:
        // 2 sources

        {
            v0 = var6_4 = null;
        }
        if (v0 != null && var6_4.d < var6_4.a) {
            v1 = var7_10 = var4_8;
            var4_8 = var6_4;
            var6_4 = v1;
            var8_11 = 0;
            var9_12 = 0;
            var10_13 = 0;
            var11_14 = 0;
            if (var4_8.b[0] != -1) {
                var12_15 = super.a(var7_10.a(var4_8.b[0]));
                var8_11 = var12_15.a;
                var9_12 = var12_15.a + var12_15.c;
                var10_13 = var12_15.b;
                var11_14 = var12_15.b + var12_15.d;
                for (var13_16 = 1; var13_16 != var4_8.d; ++var13_16) {
                    var12_15 = super.a(var7_10.a(var4_8.b[var13_16]));
                    if (var8_11 > var12_15.a) {
                        var8_11 = var12_15.a;
                    }
                    if (var9_12 < var12_15.a + var12_15.c) {
                        var9_12 = var12_15.a + var12_15.c;
                    }
                    if (var10_13 > var12_15.b) {
                        var10_13 = var12_15.b;
                    }
                    if (var11_14 >= var12_15.b + var12_15.d) continue;
                    var11_14 = var12_15.b + var12_15.d;
                }
            }
            new ak(var8_11, var10_13, var9_12 - var8_11, var11_14 - var10_13);
            var4_8 = new ak();
            var6_4 = new ak();
            var5_6.setColor(255, 255, 255);
            var5_6.fillRect(var4_8.a, var4_8.b, var4_8.c, var4_8.d);
            var5_6.setColor(245, 222, 179);
            var5_6.drawRect(var4_8.a, var4_8.b, var4_8.c, var4_8.d);
            var5_6.setColor(95, 158, 160);
            var5_6.fillRect(var6_4.a, var6_4.b, var6_4.c, var6_4.d);
        }
        for (var4_9 = 0; var4_9 < var3_2.g().length && var3_2.g()[var4_9] != null; ++var4_9) {
            if (var3_2.g()[var4_9].f() != null) {
                var5_7 = false;
                var6_5 = ae.a(var1_1 /* !! */ .f);
                if (var6_5 > 0 && var1_1 /* !! */ .f[var6_5 - 1] == var3_2.a()) {
                    var5_7 = true;
                }
                var3_2.g()[var4_9].f().a(var2_3, var3_2.g()[var4_9].a(), var5_7, var1_1 /* !! */ .f, true, var1_1 /* !! */ .c);
                continue;
            }
            var3_2.g()[var4_9].a(var2_3, false, true, var1_1 /* !! */ .c, var1_1 /* !! */ .f);
        }
    }

    public final void b() {
        al al2 = this.c;
        ao ao2 = this;
        for (int i2 = 0; i2 < al2.g().length && al2.g()[i2] != null; ++i2) {
            if (al2.g()[i2].f() != null) {
                al2.g()[i2].f().a(al2.g()[i2].a(), ao2.f, true, ao2.c);
                continue;
            }
            al2.g()[i2].a(false, true, ao2.c, ao2.f);
        }
    }

    public final w a(int n2) {
        return ae.a(this.c, n2);
    }

    public final boolean b(int n2) {
        boolean bl;
        w w2;
        int n3 = 0;
        int[] nArray = this.e;
        w w3 = this.c;
        ao ao2 = this;
        while (true) {
            int n4;
            if ((n4 = ae.a(nArray)) == 0) {
                w2 = w3;
                break;
            }
            if (n3 == n4 - 1) {
                w2 = w3.g()[nArray[n3]];
                break;
            }
            w w4 = w3.g()[nArray[n3]];
            ++n3;
            w3 = w4;
        }
        al al2 = w2;
        if (w2.i() == 2) {
            w3 = (ac)((Object)al2);
            int n5 = n2;
            ao ao3 = this;
            boolean bl2 = false;
            switch (n5) {
                case 0: {
                    bl2 = ((ac)w3).a((byte)0);
                    int[] nArray2 = new int[]{-1, -1, -1, -1};
                    ao3.h.a(new int[]{-1, -1, 0});
                    break;
                }
                case 1: {
                    bl2 = ((ac)w3).a((byte)1);
                    int[] nArray3 = new int[]{-1, -1, -1, -1};
                    ao3.h.a(new int[]{-1, -1, 1});
                    break;
                }
                case 2: {
                    bl2 = ((ac)w3).a((byte)2);
                    int[] nArray4 = new int[]{-1, -1, -1, -1};
                    ao3.h.a(new int[]{-1, -1, 2});
                    break;
                }
                case 3: {
                    bl2 = ((ac)w3).a((byte)3);
                    int[] nArray5 = new int[]{-1, -1, -1, -1};
                    ao3.h.a(new int[]{-1, -1, 3});
                    break;
                }
                case 5: {
                    int[] nArray6 = new int[]{-1, -1, -1, -1};
                    ao3.h.a(new int[]{-1, -1, 4});
                    bl2 = true;
                    break;
                }
                case 7: {
                    bl2 = ao3.e();
                    if (bl2) {
                        ((ac)w3).d = false;
                        int[] nArray7 = new int[]{-1, -1, -1, -1};
                        ao3.h.a(new int[]{-1, -1, 7});
                        break;
                    }
                    int[] nArray8 = new int[]{-1, -1, -1, -1};
                    ao3.h.a(new int[]{-1, -1, 5});
                }
            }
            bl = bl2;
        } else {
            w3 = al2;
            int n6 = n2;
            ao ao4 = this;
            boolean bl3 = false;
            n3 = 0;
            Object object = ((al)w3).l() != null ? ((al)w3).l() : (Object)new byte[][]{{0, 0, 1, -1}, {1, 1, 1, -1}, {2, 2, 1, -1}, {3, 3, 1, -1}, {5, 4, -1, -1}, {7, 5, -1, -1}};
            int[] nArray9 = new int[3];
            nArray9[1] = ((al)w3).b != null ? ((al)w3).b.f : -1;
            nArray9[0] = ((al)w3).a != null ? ((al)w3).a.f : -1;
            for (int i2 = 0; i2 < ((byte[][])object).length; ++i2) {
                if (object[i2][0] != n6) continue;
                n3 = 1;
                bl3 = false;
                switch (object[i2][1]) {
                    case 0: {
                        nArray9[2] = 0;
                        if (((al)w3).a != null) {
                            if (object[i2][3] != -1 && ((al)w3).a.f % (object[i2][3] + 1) == 0) {
                                ((al)w3).a.a(object[i2][3], ao4.c);
                            } else {
                                ((al)w3).a.b(object[i2][2], ao4.c);
                            }
                            bl3 = true;
                            nArray9[0] = ((al)w3).a.f;
                            int[] nArray10 = new int[]{-1, -1, -1, -1};
                            ao4.h.a(nArray9);
                            break;
                        }
                        int[] nArray11 = new int[]{-1, -1, -1, -1};
                        ao4.h.a(nArray9);
                        break;
                    }
                    case 1: {
                        nArray9[2] = 1;
                        if (((al)w3).a != null) {
                            if (object[i2][3] != -1 && (((al)w3).a.f + 1) % (object[i2][3] + 1) == 0) {
                                ((al)w3).a.b(object[i2][3], ao4.c);
                            } else {
                                ((al)w3).a.a(object[i2][2], ao4.c);
                            }
                            bl3 = true;
                            nArray9[0] = ((al)w3).a.f;
                            int[] nArray12 = new int[]{-1, -1, -1, -1};
                            ao4.h.a(nArray9);
                            break;
                        }
                        int[] nArray13 = new int[]{-1, -1, -1, -1};
                        ao4.h.a(nArray9);
                        break;
                    }
                    case 2: {
                        nArray9[2] = 2;
                        if (((al)w3).b != null) {
                            if (object[i2][3] != -1 && ((al)w3).b.f % (object[i2][3] + 1) == 0) {
                                ((al)w3).b.a(object[i2][3], ao4.c);
                            } else {
                                ((al)w3).b.b(object[i2][2], ao4.c);
                            }
                            bl3 = true;
                            nArray9[1] = ((al)w3).b.f;
                            int[] nArray14 = new int[]{-1, -1, -1, -1};
                            ao4.h.a(nArray9);
                            break;
                        }
                        int[] nArray15 = new int[]{-1, -1, -1, -1};
                        ao4.h.a(nArray9);
                        break;
                    }
                    case 3: {
                        nArray9[2] = 3;
                        if (((al)w3).b != null) {
                            if (object[i2][3] != -1 && (((al)w3).b.f + 1) % (object[i2][3] + 1) == 0) {
                                ((al)w3).b.b(object[i2][3], ao4.c);
                            } else {
                                ((al)w3).b.a(object[i2][2], ao4.c);
                            }
                            bl3 = true;
                            nArray9[1] = ((al)w3).b.f;
                            int[] nArray16 = new int[]{-1, -1, -1, -1};
                            ao4.h.a(nArray9);
                            break;
                        }
                        int[] nArray17 = new int[]{-1, -1, -1, -1};
                        ao4.h.a(nArray9);
                        break;
                    }
                    case 4: {
                        if (((al)w3).b != null) {
                            bl3 = ((al)w3).b.f >= ae.a(((al)w3).b.b) ? ao4.a(w3, ((al)w3).b.b[((al)w3).b.f - ((al)w3).b.e]) : ao4.a(w3, ((al)w3).b.b[((al)w3).b.f]);
                        }
                        if (!bl3 && ((al)w3).a != null) {
                            bl3 = ((al)w3).a.f >= ae.a(((al)w3).a.b) ? ao4.a(w3, ((al)w3).a.b[((al)w3).a.f - ((al)w3).a.e]) : ao4.a(w3, ((al)w3).a.b[((al)w3).a.f]);
                        }
                        if (bl3) {
                            nArray9[2] = 6;
                            int[] nArray18 = new int[]{-1, -1, -1, -1};
                            ao4.h.a(nArray9);
                            break;
                        }
                        nArray9[2] = 4;
                        int[] nArray19 = new int[]{-1, -1, -1, -1};
                        ao4.h.a(nArray9);
                        break;
                    }
                    case 5: {
                        bl3 = ao4.e();
                        if (bl3) {
                            nArray9[2] = 7;
                            int[] nArray20 = new int[]{-1, -1, -1, -1};
                            ao4.h.a(nArray9);
                            break;
                        }
                        nArray9[2] = 5;
                        int[] nArray21 = new int[]{-1, -1, -1, -1};
                        ao4.h.a(nArray9);
                    }
                }
                if (bl3) break;
            }
            if (n3 == 0) {
                switch (n6) {
                    case 14: 
                    case 15: 
                    case 16: 
                    case 17: 
                    case 18: 
                    case 19: 
                    case 20: 
                    case 21: 
                    case 22: 
                    case 23: {
                        nArray9[2] = n6 - 6;
                        int[] nArray22 = new int[]{-1, -1, -1, -1};
                        ao4.h.a(nArray9);
                    }
                }
            }
            bl = bl3;
        }
        return bl;
    }

    private boolean e() {
        boolean bl = false;
        int n2 = -1;
        for (int i2 = ae.a(this.f) - 2; i2 >= 0; --i2) {
            w w2 = ae.a(this.c, this.f[i2]);
            if (((al)w2).a == null && ((al)w2).b == null) continue;
            bl = true;
            n2 = w2.a();
            break;
        }
        if (bl) {
            this.e = this.c(n2);
            if (this.e == null) {
                this.e = ae.b(50);
            }
            this.f();
        }
        return bl;
    }

    private boolean a(w w2, int n2) {
        if (w2.i() == 1) {
            return false;
        }
        int n3 = this.a(w2, n2, true);
        if (n3 == -1) {
            return false;
        }
        this.e = this.c(n3);
        if (this.e == null) {
            this.e = ae.b(50);
        }
        this.f();
        return true;
    }

    private void f() {
        this.f = ae.b(50);
        w w2 = this.c;
        int n2 = 0;
        ++n2;
        this.f[0] = w2.a();
        for (int i2 = 0; i2 < this.e.length && this.e[i2] != -1; ++i2) {
            w2 = w2.g()[this.e[i2]];
            this.f[n2++] = w2.a();
        }
    }

    private int a(w w2, int n2, boolean bl) {
        if (w2.i() == 2 && n2 == -1) {
            if (bl) {
                ((ac)w2).d = true;
            }
            return w2.a();
        }
        if ((((al)w2).a != null || ((al)w2).b != null) && n2 == -1) {
            return w2.a();
        }
        for (int i2 = 0; i2 < w2.g().length && w2.g()[i2] != null; ++i2) {
            int n3;
            if (w2.g()[i2].i() == 1 || n2 != -1 && w2.g()[i2].a() != n2 || (n3 = this.a(w2.g()[i2], -1, bl)) == -1) continue;
            return n3;
        }
        return -1;
    }

    private int[] c(int n2) {
        int[] nArray = ae.b(50);
        w w2 = ae.a(this.c, n2);
        while (w2.j() != -1) {
            w w3 = ae.a(this.c, w2.j());
            for (int i2 = 0; i2 < w3.g().length && w3.g()[i2] != null; ++i2) {
                if (w3.g()[i2].a() != w2.a()) continue;
                ae.b(nArray, i2);
                break;
            }
            w2 = ae.a(this.c, w2.j());
        }
        return nArray;
    }

    public final void c() {
        this.c.k();
        this.d = null;
        this.e = null;
        this.f = null;
        this.h = null;
        this.a = null;
    }

    public ao() {
    }

    private static RecordStore a(String string, String string2, String string3, String charSequence, int n2) {
        RecordStore recordStore = null;
        try {
            if (string3 == null || string3.length() == 0) {
                string3 = "00";
            }
            if (charSequence != null) {
                ((String)charSequence).length();
            }
            charSequence = new StringBuffer();
            ((StringBuffer)charSequence).append("dcn").append(string).append(string2).append(string3).append(n2);
            recordStore = RecordStore.openRecordStore((String)((StringBuffer)charSequence).toString(), (boolean)true, (int)1, (boolean)true);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return recordStore;
    }

    public static void a(v object, String string, String object2, String object3, String string2, int n2) {
        if ((string = ao.a(string, (String)object2, (String)object3, string2, n2)) != null) {
            try {
                try {
                    object2 = new ByteArrayOutputStream();
                    object3 = new DataOutputStream((OutputStream)object2);
                    ((DataOutputStream)object3).writeInt(((v)object).i());
                    if (((v)object).i() < n2) {
                        ((DataOutputStream)object3).writeInt(((v)object).d());
                        ((DataOutputStream)object3).writeUTF(((v)object).c());
                        ((DataOutputStream)object3).writeUTF(((v)object).b());
                        ((DataOutputStream)object3).writeUTF(((v)object).g());
                        ((DataOutputStream)object3).writeInt(((v)object).a());
                        ((DataOutputStream)object3).writeUTF(((v)object).f());
                        ((DataOutputStream)object3).writeUTF(((v)object).e());
                        ((DataOutputStream)object3).writeBoolean(((v)object).h());
                        ((DataOutputStream)object3).writeInt(((v)object).j());
                        ((DataOutputStream)object3).writeLong(((v)object).k());
                    }
                    object = ((ByteArrayOutputStream)object2).toByteArray();
                    if (string.getNumRecords() == 0) {
                        string.addRecord((byte[])object, 0, ((Object)object).length);
                    } else {
                        string.setRecord(1, (byte[])object, 0, ((Object)object).length);
                    }
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                    try {
                        string.closeRecordStore();
                        return;
                    }
                    catch (RecordStoreNotOpenException recordStoreNotOpenException) {
                        recordStoreNotOpenException.printStackTrace();
                        return;
                    }
                    catch (RecordStoreException recordStoreException) {
                        recordStoreException.printStackTrace();
                        return;
                    }
                }
            }
            catch (Throwable throwable) {
                try {
                    string.closeRecordStore();
                }
                catch (RecordStoreNotOpenException recordStoreNotOpenException) {
                    recordStoreNotOpenException.printStackTrace();
                }
                catch (RecordStoreException recordStoreException) {
                    recordStoreException.printStackTrace();
                }
                throw throwable;
            }
            try {
                string.closeRecordStore();
                return;
            }
            catch (RecordStoreNotOpenException recordStoreNotOpenException) {
                recordStoreNotOpenException.printStackTrace();
                return;
            }
            catch (RecordStoreException recordStoreException) {
                recordStoreException.printStackTrace();
            }
        }
    }
}

