/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Image
 */
package a.b;

import a.e;
import b.a;
import game.l;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Image;

public class c {
    public static short[][] a;
    public static short[][] b;
    public static short[][][] c;
    public static String[] d;
    private static a[] g;
    public static int[][] e;
    public static Image f;
    private static Image[] h;
    private static byte[] i;
    private static int j;

    public static void a() {
        Object object = null;
        a = a.e.a(a.e.a("/data/script/sprite.mid"));
        object = "/data/mod/modInfo.mid";
        try {
            "".getClass();
            object = b.a((String)object);
            DataInputStream dataInputStream = new DataInputStream((InputStream)object);
            int n2 = dataInputStream.readByte();
            b = new short[n2][];
            for (int i2 = 0; i2 < n2; ++i2) {
                int n3 = dataInputStream.readByte();
                a.b.c.b[i2] = new short[n3];
                for (int i3 = 0; i3 < n3; ++i3) {
                    short s;
                    a.b.c.b[i2][i3] = s = dataInputStream.readShort();
                }
            }
            dataInputStream.close();
            ((InputStream)object).close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        a.b.c.c("/data/script/chs.mid");
        a.b.c.a("/data/script/npcDialog.mid");
        a.b.c.c();
        a.b.c.d("/data/event/worldEvt.mid");
        a.b.c.b("/data/script/db.mid");
        f = a.e.b("/data/tex/", "bk");
    }

    private static void a(String stringArray) {
        stringArray = a.e.c(a.e.a((String)stringArray));
        l.aa = new String[stringArray.length];
        for (int i2 = 0; i2 < stringArray.length; ++i2) {
            System.arraycopy(stringArray[i2], 0, l.aa, i2, stringArray[i2].length);
        }
    }

    public static short a(byte by, short s, byte by2) {
        return c[by][s][by2];
    }

    private static void b(String object) {
        object = a.e.a((String)object);
        c = new short[9][][];
        for (int i2 = 0; i2 < 9; ++i2) {
            a.b.c.c[i2] = a.e.a((InputStream)object);
        }
    }

    private static void c(String stringArray) {
        stringArray = a.e.c(a.e.a((String)stringArray));
        d = new String[stringArray.length];
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < stringArray.length; ++i2) {
            stringBuffer.delete(0, stringBuffer.length());
            for (int i3 = 0; i3 < stringArray[i2].length; ++i3) {
                stringBuffer.append(stringArray[i2][i3]);
            }
            a.b.c.d[i2] = stringBuffer.toString();
        }
    }

    private static void c() {
        e = new int[4][];
        for (int i2 = 0; i2 < 4; ++i2) {
            a.b.c.e[i2] = a.e.a(a.e.b("/data/tex/", "tex_" + i2));
        }
    }

    private static void d(String object) {
        try {
            short s;
            int n2;
            "".getClass();
            object = b.a((String)object);
            DataInputStream dataInputStream = new DataInputStream((InputStream)object);
            byte by = dataInputStream.readShort();
            String[] stringArray = null;
            if (by > 0) {
                stringArray = new String[by];
                for (n2 = 0; n2 < by; ++n2) {
                    int n3 = dataInputStream.readByte();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i2 = 0; i2 < n3; ++i2) {
                        stringBuffer.append((char)(dataInputStream.read() << 8 | dataInputStream.read()));
                    }
                    stringArray[n2] = stringBuffer.toString();
                }
            }
            if ((s = (short)dataInputStream.readByte()) > 0) {
                g = new a[s];
                for (n2 = 0; n2 < s; n2 = (int)((byte)(n2 + 1))) {
                    a.b.c.g[n2] = new a();
                    g[n2].a(dataInputStream, (byte)n2, -1, stringArray);
                }
            }
            dataInputStream.close();
            ((InputStream)object).close();
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public static void a(int n2) {
        j = 50000;
        h = new Image[50000];
        i = new byte[j];
    }

    public static Image b(int n2) {
        if (h[n2] == null) {
            a.b.c.h[n2] = a.e.b("/data/img/", "img_" + n2);
        }
        int n3 = n2;
        i[n3] = (byte)(i[n3] + 1);
        return h[n2];
    }

    public static void c(int n2) {
        int n3 = n2;
        i[n3] = (byte)(i[n3] - 1);
        if (i[n2] <= 0) {
            a.b.c.i[n2] = 0;
        }
    }

    public static boolean d(int n2) {
        int n3 = n2;
        i[n3] = (byte)(i[n3] - 1);
        if (i[n2] <= 0) {
            a.b.c.i[n2] = 0;
            a.b.c.h[n2] = null;
            return true;
        }
        return false;
    }

    public static void b() {
        for (int i2 = 0; i2 < j; ++i2) {
            a.b.c.i[i2] = 0;
            a.b.c.h[i2] = null;
        }
    }
}

