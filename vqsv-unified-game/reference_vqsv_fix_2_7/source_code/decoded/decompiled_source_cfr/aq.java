/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Image
 */
import game.k;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Image;

public final class aq {
    public static short[][] a;
    public static short[][] b;
    public static short[][][] c;
    public static String[] d;
    public static int[][] e;
    public static Image f;

    public static void a() {
        Object object = "/data/script/sprite.mid";
        try {
            object = ae.a((String)object);
            a = ae.a((InputStream)object);
            ((InputStream)object).close();
        }
        catch (Exception exception) {}
        object = "/data/mod/modInfo.mid";
        try {
            "".getClass();
            object = aj.a((String)object);
            DataInputStream dataInputStream = new DataInputStream((InputStream)object);
            int n2 = dataInputStream.readByte();
            b = new short[n2][];
            for (int i2 = 0; i2 < n2; ++i2) {
                int n3 = dataInputStream.readByte();
                aq.b[i2] = new short[n3];
                for (int i3 = 0; i3 < n3; ++i3) {
                    short s2;
                    aq.b[i2][i3] = s2 = dataInputStream.readShort();
                }
            }
            dataInputStream.close();
            ((InputStream)object).close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        aq.c("/data/script/chs.mid");
        aq.a("/data/script/npcDialog.mid");
        aq.b();
        aq.b("/data/script/db.mid");
        f = ae.b("/data/tex/", "bk");
    }

    private static void a(String object) {
        try {
            object = ae.a((String)object);
            String[][] stringArray = ae.c((InputStream)object);
            k.N = new String[stringArray.length];
            for (int i2 = 0; i2 < stringArray.length; ++i2) {
                System.arraycopy(stringArray[i2], 0, k.N, i2, stringArray[i2].length);
            }
            ((InputStream)object).close();
            return;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return;
        }
    }

    public static short a(byte by, short s2, byte by2) {
        return c[by][s2][by2];
    }

    private static void b(String object) {
        try {
            object = ae.a((String)object);
            c = new short[9][][];
            for (int i2 = 0; i2 < 9; ++i2) {
                aq.c[i2] = ae.a((InputStream)object);
            }
            ((InputStream)object).close();
            return;
        }
        catch (IOException iOException) {
            return;
        }
    }

    private static void c(String object) {
        try {
            object = ae.a((String)object);
            String[][] stringArray = ae.c((InputStream)object);
            d = new String[stringArray.length];
            StringBuffer stringBuffer = new StringBuffer();
            for (int i2 = 0; i2 < stringArray.length; ++i2) {
                stringBuffer.delete(0, stringBuffer.length());
                for (int i3 = 0; i3 < stringArray[i2].length; ++i3) {
                    stringBuffer.append(stringArray[i2][i3]);
                }
                aq.d[i2] = stringBuffer.toString();
            }
            ((InputStream)object).close();
            return;
        }
        catch (IOException iOException) {
            return;
        }
    }

    private static void b() {
        e = new int[4][];
        for (int i2 = 0; i2 < 4; ++i2) {
            aq.e[i2] = ae.a(ae.b("/data/tex/", "tex_" + i2));
        }
    }
}

