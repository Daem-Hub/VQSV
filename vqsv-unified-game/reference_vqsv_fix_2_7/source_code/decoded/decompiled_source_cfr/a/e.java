/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Font
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
package a;

import a.a.a;
import a.f;
import c.d;
import game.n;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class e {
    private static int c = 5;
    private static int d = 300;
    private static int[] e = null;
    private static Random f;
    private static int[][] g;
    private static int[] h;
    private static int[] i;
    private static char[] j;
    private static int k;
    public static String[] a;
    private static int l;
    public static int b;
    private static int m;

    public static short[] a(short[] sArray, short[] sArray2) {
        int n2 = 0;
        if (sArray != null) {
            n2 = sArray.length;
        }
        short[] sArray3 = new short[n2 + sArray2.length];
        if (sArray != null) {
            System.arraycopy(sArray, 0, sArray3, 0, sArray.length);
        }
        System.arraycopy(sArray2, 0, sArray3, n2, sArray2.length);
        return sArray3;
    }

    public static void a(byte[] byArray, String object, int n2) {
        try {
            if (((String)object).substring(0, 1).endsWith("/")) {
                new Object().getClass();
                object = b.a((String)object);
            } else {
                new Object().getClass();
                object = b.a("/" + (String)object);
            }
            object = new DataInputStream((InputStream)object);
            ((FilterInputStream)object).skip(0L);
            ((DataInputStream)object).read(byArray, 0, byArray.length);
            ((FilterInputStream)object).close();
            return;
        }
        catch (Exception exception) {
            System.out.println("GameInf err at getStream()  :  " + exception);
            return;
        }
    }

    public static short[] a(byte[] byArray, int[] nArray) {
        short s = a.e.f(byArray, nArray);
        int n2 = a.e.f(byArray, nArray);
        if (s == 0) {
            return null;
        }
        short[] sArray = new short[s * n2];
        for (n2 = 0; n2 < sArray.length; ++n2) {
            byte by = byArray[nArray[0]];
            nArray[0] = nArray[0] + 1;
            byte by2 = byArray[nArray[0]];
            nArray[0] = nArray[0] + 1;
            sArray[n2] = (short)(by << 8 | by2 & 0xFF);
        }
        return sArray;
    }

    public static short[][] b(byte[] byArray, int[] nArray) {
        int n2 = a.e.f(byArray, nArray);
        short s = a.e.f(byArray, nArray);
        if (n2 == 0) {
            return null;
        }
        short[][] sArrayArray = new short[n2][];
        for (int i2 = 0; i2 < n2; ++i2) {
            int n3 = a.e.f(byArray, nArray);
            sArrayArray[i2] = new short[n3 * s];
            for (n3 = 0; n3 < sArrayArray[i2].length; ++n3) {
                byte by = byArray[nArray[0]];
                nArray[0] = nArray[0] + 1;
                byte by2 = byArray[nArray[0]];
                nArray[0] = nArray[0] + 1;
                sArrayArray[i2][n3] = (short)(by << 8 | by2 & 0xFF);
            }
        }
        return sArrayArray;
    }

    private static short f(byte[] byArray, int[] nArray) {
        int n2 = nArray[0];
        nArray[0] = n2 + 1;
        int n3 = nArray[0];
        nArray[0] = n3 + 1;
        return (short)((byArray[n2] & 0xFF) << 8 | byArray[n3] & 0xFF);
    }

    public static InputStream a(String string) {
        try {
            "".getClass();
            return b.a(string);
        }
        catch (Exception exception) {
            return null;
        }
    }

    public static short[][] a(InputStream inputStream) {
        short[][] sArrayArray = null;
        if (inputStream == null) {
            return null;
        }
        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            int n2 = dataInputStream.readShort();
            if (n2 >= 0) {
                sArrayArray = new short[n2][];
            }
            for (int i2 = 0; i2 < sArrayArray.length; ++i2) {
                n2 = dataInputStream.readShort();
                sArrayArray[i2] = new short[n2];
                for (n2 = 0; n2 < sArrayArray[i2].length; ++n2) {
                    sArrayArray[i2][n2] = dataInputStream.readShort();
                }
            }
        }
        catch (Exception exception) {}
        return sArrayArray;
    }

    public static byte[][] b(InputStream inputStream) {
        byte[][] byArrayArray = null;
        if (inputStream == null) {
            return null;
        }
        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            int n2 = dataInputStream.readShort();
            if (n2 >= 0) {
                byArrayArray = new byte[n2][];
            }
            for (int i2 = 0; i2 < byArrayArray.length; ++i2) {
                n2 = dataInputStream.readShort();
                byArrayArray[i2] = new byte[n2];
                for (n2 = 0; n2 < byArrayArray[i2].length; ++n2) {
                    byArrayArray[i2][n2] = dataInputStream.readByte();
                }
            }
        }
        catch (Exception exception) {}
        return byArrayArray;
    }

    public static String[][] c(InputStream object) {
        String[][] stringArrayArray = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            object = new DataInputStream((InputStream)object);
            int n2 = ((DataInputStream)object).readShort();
            if (n2 == 0) {
                return null;
            }
            if (n2 > 0) {
                stringArrayArray = new String[n2][];
            }
            int n3 = 0;
            while (n3 < stringArrayArray.length) {
                n2 = ((DataInputStream)object).readShort();
                stringArrayArray[n3] = new String[n2];
                n2 = 0;
                while (n2 < stringArrayArray[n3].length) {
                    int n4 = ((DataInputStream)object).readUnsignedByte();
                    if (n4 == 255) {
                        n4 = ((DataInputStream)object).readShort();
                    }
                    int n5 = 0;
                    while (n5 < n4) {
                        stringBuffer.append((char)((DataInputStream)object).readShort());
                        ++n5;
                    }
                    stringArrayArray[n3][n2] = stringBuffer.toString();
                    stringBuffer = new StringBuffer();
                    ++n2;
                }
                ++n3;
            }
        }
        catch (Exception exception) {
            object = exception;
            exception.printStackTrace();
        }
        return stringArrayArray;
    }

    public static int a(int n2, int n3, int n4, int n5) {
        n2 = (n2 - n4) * (n2 - n4) + (n3 - n5) * (n3 - n5);
        n3 = 0;
        for (n4 = 0x40000000; n4 > 0; n4 >>= 2) {
            if (n2 >= n3 + n4) {
                n2 -= n3 + n4;
                n3 = (n3 >> 1) + n4;
                continue;
            }
            n3 >>= 1;
        }
        return n3;
    }

    public static int a(int n2, int n3) {
        int n4;
        int n5;
        if (e == null) {
            e = new int[d / c + 1];
            n5 = c * c;
            for (n4 = e.length - 1; n4 >= 0; --n4) {
                a.e.e[n4] = n4 * n4 * n5;
            }
        }
        if (n2 < 0) {
            return -1;
        }
        if (n2 <= e[30]) {
            if (n2 <= e[15]) {
                if (n2 <= e[5]) {
                    for (n5 = 1; n5 <= 5 && n2 > e[n5]; ++n5) {
                    }
                } else if (n2 <= e[10]) {
                    for (n5 = 6; n5 <= 10 && n2 > e[n5]; ++n5) {
                    }
                } else {
                    for (n5 = 11; n5 <= 15 && n2 > e[n5]; ++n5) {
                    }
                }
            } else if (n2 <= e[20]) {
                for (n5 = 16; n5 <= 20 && n2 > e[n5]; ++n5) {
                }
            } else if (n2 <= e[25]) {
                for (n5 = 21; n5 <= 25 && n2 > e[n5]; ++n5) {
                }
            } else {
                for (n5 = 26; n5 <= 30 && n2 > e[n5]; ++n5) {
                }
            }
        } else if (n2 <= e[45]) {
            if (n2 <= e[35]) {
                for (n5 = 31; n5 <= 35 && n2 > e[n5]; ++n5) {
                }
            } else if (n2 <= e[40]) {
                for (n5 = 36; n5 <= 40 && n2 > e[n5]; ++n5) {
                }
            } else {
                for (n5 = 41; n5 <= 45 && n2 > e[n5]; ++n5) {
                }
            }
        } else if (n2 <= e[50]) {
            for (n5 = 46; n5 <= 50 && n2 > e[n5]; ++n5) {
            }
        } else if (n2 <= e[55]) {
            for (n5 = 51; n5 <= 55 && n2 > e[n5]; ++n5) {
            }
        } else {
            for (n5 = 56; n5 <= 60 && n2 > e[n5]; ++n5) {
            }
        }
        for (n4 = (n5 *= c) - c + 1; n4 <= n5; ++n4) {
            if (n4 * n4 < n2) continue;
            if (n3 == 0) {
                return n4;
            }
            if (n3 != 1) continue;
            return n4 - 1;
        }
        return -1;
    }

    public static int a(int n2) {
        if (f == null) {
            f = new Random(System.currentTimeMillis());
        }
        return f.nextInt(n2);
    }

    public static int b(int n2) {
        if (f == null) {
            f = new Random(System.currentTimeMillis());
        }
        return -2 + f.nextInt(4);
    }

    public static int b(int n2, int n3) {
        if (f == null) {
            f = new Random(System.currentTimeMillis());
        }
        return (f.nextInt() >>> 1) % (n3 - n2 + 1) + n2;
    }

    public static int a(int[] nArray, int n2) {
        int n3;
        for (n3 = 0; n3 < nArray.length && n2 < nArray[n3]; ++n3) {
        }
        return n3;
    }

    public static boolean a(int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10) {
        if (n10 % 2 != 0) {
            n10 = n4;
            n4 = n5;
            n5 = n10;
        }
        return n2 + n4 >= n6 && n6 + n8 >= n2 && n3 + n5 > n7 && n7 + n9 > n3;
    }

    public static boolean a(int n2, int n3, int n4, int n5, int n6, int n7) {
        return n2 >= n4 && n2 <= n4 + n6 && n3 >= n5 && n3 <= n5 + 40;
    }

    public static boolean a(int n2, int n3, int n4, int n5, short[] sArray, short[] sArray2) {
        return n2 + sArray[0] + sArray[2] >= n4 + sArray2[0] && n2 + sArray[0] <= n4 + sArray2[0] + sArray2[2] && n3 + sArray[1] <= n5 + sArray2[1] + sArray2[3] && n3 + sArray[1] + sArray[3] >= n5 + sArray2[1];
    }

    public static boolean a(int n2, int n3, int n4, int n5, int n6, int n7, short[] sArray) {
        return n2 + n4 >= n6 + sArray[0] && n2 <= n6 + sArray[0] + sArray[2] && n3 <= n7 + sArray[1] + sArray[3] && n3 + n5 >= n7 + sArray[1];
    }

    public static boolean a(int n2, int n3, int n4, int n5, short[] sArray) {
        return n2 >= n4 + sArray[0] && n2 <= n4 + sArray[0] + sArray[2] && n3 <= n5 + sArray[1] + sArray[3] && n3 >= n5 + sArray[1];
    }

    public static Image a(String string, String string2) {
        Image image;
        try {
            image = Image.createImage((String)(string + string2 + ".png"));
        }
        catch (Exception exception) {
            a.a.a.a(exception, string + string2 + ".png error!!");
            return null;
        }
        return image;
    }

    public static Image b(String object, String string) {
        byte[] byArray = a.e.g((String)object + string + ".mid");
        object = byArray;
        return Image.createImage((byte[])byArray, (int)0, (int)((Object)object).length);
    }

    private static byte[] f(String string) {
        byte[] byArray = null;
        try {
            int n2;
            int n3 = 0;
            int n4 = 0;
            "".getClass();
            InputStream inputStream = b.a(string);
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(a.e.a(-1991225785L, 4));
            byteArrayOutputStream.write(a.e.a(218765834L, 4));
            byteArrayOutputStream.write(a.e.a(13L, 4));
            byteArrayOutputStream.write(a.e.a(1229472850L, 4));
            byteArrayOutputStream.write(a.e.a((long)dataInputStream.readInt(), 4));
            byteArrayOutputStream.write(a.e.a((long)dataInputStream.readInt(), 4));
            byteArrayOutputStream.write(dataInputStream.readByte());
            byte by = dataInputStream.readByte();
            g = new int[20][2];
            byteArrayOutputStream.write(by);
            byteArrayOutputStream.write(a.e.a(0L, 3));
            byteArrayOutputStream.write(a.e.a(0L, 4));
            a.e.g[0][0] = 8;
            a.e.g[0][1] = 13;
            int n5 = 8 + (g[0][1] + 12);
            ++n4;
            if (by == 3) {
                n3 = dataInputStream.readInt();
                byteArrayOutputStream.write(a.e.a((long)n3, 4));
                byteArrayOutputStream.write(a.e.a(1347179589L, 4));
                byte[] byArray2 = new byte[n3];
                for (n2 = 0; n2 < byArray2.length; ++n2) {
                    byArray2[n2] = (byte)dataInputStream.read();
                }
                byteArrayOutputStream.write(byArray2);
                byteArrayOutputStream.write(a.e.a(0L, 4));
                a.e.g[1][0] = n5;
                a.e.g[1][1] = n3;
                n5 += g[1][1] + 12;
                ++n4;
                n3 = dataInputStream.readInt();
                if (n3 == 1951551059) {
                    int n6 = dataInputStream.read();
                    if (n6 == 0) {
                        n6 = 256;
                    }
                    byteArrayOutputStream.write(a.e.a((long)n6, 4));
                    byteArrayOutputStream.write(a.e.a(1951551059L, 4));
                    n2 = dataInputStream.read();
                    for (n3 = 0; n3 < n6; ++n3) {
                        if (n3 == n2) {
                            byteArrayOutputStream.write(0);
                            continue;
                        }
                        byteArrayOutputStream.write(255);
                    }
                    byteArrayOutputStream.write(a.e.a(0L, 4));
                    a.e.g[2][0] = n5;
                    a.e.g[2][1] = n6;
                    n5 += g[2][1] + 12;
                    ++n4;
                    n3 = dataInputStream.readInt();
                } else {
                    a.e.g[2][0] = n5;
                    a.e.g[2][1] = n3;
                    ++n4;
                }
            } else if (by == 6) {
                n3 = dataInputStream.readInt();
            }
            byteArrayOutputStream.write(a.e.a((long)n3, 4));
            byteArrayOutputStream.write(a.e.a(1229209940L, 4));
            byte[] byArray3 = new byte[n3];
            for (n2 = 0; n2 < byArray3.length; ++n2) {
                byArray3[n2] = (byte)dataInputStream.read();
            }
            byteArrayOutputStream.write(byArray3);
            byteArrayOutputStream.write(a.e.a(0L, 4));
            a.e.g[n4][0] = n5;
            a.e.g[n4][1] = n3;
            ++n4;
            byteArrayOutputStream.write(a.e.a(0L, 4));
            byteArrayOutputStream.write(a.e.a(1229278788L, 4));
            byteArrayOutputStream.write(a.e.a(-1371381630L, 4));
            byArray = byteArrayOutputStream.toByteArray();
            inputStream.close();
            byteArrayOutputStream.close();
            for (n2 = 0; n2 < n4; ++n2) {
                n5 = g[n2][1];
                n3 = g[n2][0];
                byArray3 = byArray;
                int n7 = n5 + 4;
                int n8 = n3 + 4;
                int n9 = ~a.e.a(byArray, n8, n7);
                byArray3[n3 + 8 + n5] = n9 >> 24;
                byArray3[n3 + 8 + n5 + 1] = (byte)(n9 >> 16);
                byArray3[n3 + 8 + n5 + 2] = (byte)(n9 >> 8);
                byArray3[n3 + 8 + n5 + 3] = (byte)n9;
            }
        }
        catch (Exception exception) {}
        return byArray;
    }

    private static byte[] a(long l2, int n2) {
        byte[] byArray = new byte[n2];
        --n2;
        int n3 = 0;
        while (n2 >= 0) {
            byArray[n3] = (byte)(l2 >> (n2 << 3) & 0xFFL);
            --n2;
            ++n3;
        }
        return byArray;
    }

    private static int a(byte[] byArray, int n2, int n3) {
        int n4;
        int n5 = -1;
        if (h == null) {
            h = new int[256];
            for (int i2 = 0; i2 < 256; ++i2) {
                n4 = i2;
                for (int i3 = 0; i3 < 8; ++i3) {
                    if ((n4 & 1) == 1) {
                        n4 = 0xEDB88320 ^ n4 >>> 1;
                        continue;
                    }
                    n4 >>>= 1;
                }
                a.e.h[i2] = n4;
            }
        }
        for (n4 = n2; n4 < n3 + n2; ++n4) {
            n5 = h[(n5 ^ byArray[n4]) & 0xFF] ^ n5 >>> 8;
        }
        return n5;
    }

    public static int[] a(Image image) {
        int n2 = image.getWidth();
        int n3 = image.getHeight();
        int[] nArray = new int[n2 * n3];
        image.getRGB(nArray, 0, n2, 0, 0, n2, n3);
        return nArray;
    }

    public static Image a(int[] nArray, int n2, int n3, boolean bl) {
        return Image.createRGBImage((int[])nArray, (int)n2, (int)n3, (boolean)true);
    }

    public static boolean a() {
        return false;
    }

    public static int a(int[] nArray) {
        int n2 = 0;
        for (int i2 = 0; i2 < nArray.length; ++i2) {
            if (nArray[i2] != -1) continue;
            n2 = i2;
            break;
        }
        return n2;
    }

    public static c.f a(c.f f2, int n2) {
        if (f2.b() == n2) {
            return f2;
        }
        for (int i2 = 0; i2 < f2.h().length && f2.h()[i2] != null; ++i2) {
            if (f2.h()[i2].j() == 0) {
                c.f f3 = a.e.a((c.b)f2.h()[i2], n2);
                if (f3 == null) continue;
                return f3;
            }
            if (f2.h()[i2].b() != n2) continue;
            return f2.h()[i2];
        }
        return null;
    }

    public static int[] c(int n2) {
        int[] nArray = new int[n2];
        for (int i2 = 0; i2 < n2; ++i2) {
            nArray[i2] = -1;
        }
        return nArray;
    }

    public static void a(int[] nArray, int n2, int n3) {
        int n4;
        if (0 >= nArray.length) {
            return;
        }
        n2 = 0;
        for (n4 = 0; n4 < nArray.length; ++n4) {
            if (nArray[n4] != -1) continue;
            n2 = n4;
            break;
        }
        for (n4 = n2 + 0 - 1; n4 >= 0; --n4) {
            if (n4 + 0 + 1 >= nArray.length) continue;
            nArray[n4 + 1] = nArray[n4];
        }
        nArray[0] = n3;
    }

    public static void b(byte[] byArray, String object, int n2) {
        try {
            n2 = 0;
            for (int i2 = 0; i2 < ((String)object).length(); ++i2) {
                if (((String)object).charAt(i2) == '/') continue;
                n2 = i2;
                break;
            }
            String string = "/" + ((String)object).substring(n2);
            new Object().getClass();
            object = b.a(string);
            object = new DataInputStream((InputStream)object);
            ((FilterInputStream)object).skip(0L);
            ((DataInputStream)object).read(byArray, 0, byArray.length);
            ((FilterInputStream)object).close();
            return;
        }
        catch (Exception exception) {
            System.out.println("GameInf err at getStream()  :  " + exception);
            return;
        }
    }

    public static byte c(byte[] byArray, int[] nArray) {
        int n2 = nArray[0];
        nArray[0] = n2 + 1;
        return byArray[n2];
    }

    public static short d(byte[] byArray, int[] nArray) {
        int n2 = nArray[0];
        nArray[0] = n2 + 1;
        int n3 = nArray[0];
        nArray[0] = n3 + 1;
        return (short)((byArray[n2] & 0xFF) << 8 | byArray[n3] & 0xFF);
    }

    public static int e(byte[] byArray, int[] nArray) {
        int n2 = nArray[0];
        nArray[0] = n2 + 1;
        int n3 = nArray[0];
        nArray[0] = n3 + 1;
        int n4 = nArray[0];
        nArray[0] = n4 + 1;
        int n5 = nArray[0];
        nArray[0] = n5 + 1;
        return (byArray[n2] & 0xFF) << 24 | (byArray[n3] & 0xFF) << 16 | (byArray[n4] & 0xFF) << 8 | byArray[n5] & 0xFF;
    }

    public static String a(byte[] byArray) {
        char[] cArray = new char[byArray.length >> 1];
        for (int i2 = 0; i2 < byArray.length; i2 += 2) {
            cArray[i2 >> 1] = (char)(byArray[i2] << 8 | byArray[i2 + 1] & 0xFF);
        }
        return new String(cArray);
    }

    public static void a(c.f f2, int n2, int n3, c.f f3) {
        f2.a(f2.c() + n2, f3);
        f2.b(f2.d() + n3, f3);
        if (f2.j() != 1 && f2.j() == 0) {
            for (int i2 = 0; i2 < f2.h().length && f2.h()[i2] != null; ++i2) {
                a.e.a(f2.h()[i2], n2, n3, f3);
            }
        }
    }

    public static void a(Graphics graphics, String string, int n2, int n3, int n4, int n5, int n6, int n7, Font object, boolean bl, int n8, int[] nArray, int n9, byte by, d d2, byte by2, boolean[] blArray) {
        if (by != -1 && d2 != null) {
            n5 = n.a;
        }
        int[] nArray2 = new int[1 + i.length];
        int[] nArray3 = nArray2;
        nArray2[0] = n2;
        for (n2 = 1; n2 < nArray3.length; ++n2) {
            nArray3[n2] = i[n2 - 1];
        }
        f f2 = a.e.a(string, n5, n6, bl, object, by, d2);
        int n10 = 0;
        if (f2.d > 0) {
            if (bl) {
                if (f2.a[f2.d - 1][3] + n5 > n7) {
                    blArray[0] = true;
                    if (f2.a[f2.d - 1][3] + n5 > nArray[1]) {
                        nArray[1] = nArray[1] + n9;
                    } else {
                        nArray[1] = -n7;
                        blArray[1] = true;
                    }
                } else {
                    blArray[1] = true;
                    blArray[0] = true;
                    nArray[1] = 0;
                }
            } else {
                n10 = object.stringWidth(string);
                if (by != -1 && d2 != null) {
                    object = string;
                    n10 = n.a((String)object);
                }
                if (n10 > n6) {
                    blArray[1] = true;
                    if (n10 > nArray[0]) {
                        nArray[0] = nArray[0] + n9;
                        if (nArray[0] >= n10 - n6) {
                            blArray[0] = true;
                        }
                    } else {
                        nArray[0] = -n6;
                    }
                } else {
                    blArray[0] = true;
                    blArray[1] = true;
                    nArray[0] = 0;
                }
            }
        }
        for (int i2 = 0; i2 < f2.d; ++i2) {
            if (bl) {
                graphics.clipRect(n3, n4, n6, n7);
                a.e.a(graphics, string.substring(f2.a[i2][0], f2.a[i2][1]), nArray3[f2.a[i2][4]], n3 + f2.a[i2][2], n4 + f2.a[i2][3] - nArray[1], 20, d2);
                graphics.clipRect(0, 0, (int)a.a.g(), (int)a.a.h());
                continue;
            }
            n9 = 0;
            int n11 = 0;
            switch (n8) {
                case 4: {
                    n9 = n10 > n6 ? n3 : n3 + (n6 - (f2.c + f2.b)) / 2;
                    n11 = n4 + (n7 - n5) / 2;
                    break;
                }
                case 3: {
                    n9 = n3;
                    n11 = n4 + (n7 - n5) / 2;
                    break;
                }
                case 5: {
                    n9 = n3 + (n6 - (f2.c + f2.b));
                    n11 = n4 + (n7 - n5) / 2;
                    break;
                }
                case 6: {
                    n9 = n3;
                    n11 = n4 + (n7 - n5);
                    break;
                }
                case 8: {
                    n9 = n3 + (n6 - (f2.c + f2.b));
                    n11 = n4 + (n7 - n5);
                    break;
                }
                case 7: {
                    n9 = n10 > n6 ? n3 : n3 + (n6 - (f2.c + f2.b)) / 2;
                    n11 = n4 + (n7 - n5);
                    break;
                }
                case 0: {
                    n9 = n3;
                    n11 = n4;
                    break;
                }
                case 2: {
                    n9 = n3 + (n6 - (f2.c + f2.b));
                    n11 = n4;
                    break;
                }
                case 1: {
                    n9 = n3 + (n6 - (f2.c + f2.b)) / 2;
                    n11 = n4;
                }
            }
            graphics.clipRect(n3, n4, n6, n7);
            a.e.a(graphics, string.substring(f2.a[i2][0], f2.a[i2][1]), nArray3[f2.a[i2][4]], n9 + f2.a[i2][2] - nArray[0], n11 + f2.a[i2][3], 20, d2);
            graphics.clipRect(0, 0, (int)a.a.g(), (int)a.a.h());
        }
    }

    private static f a(String string, int n2, int n3, boolean bl, Font font, byte by, d d2) {
        f f2 = new f();
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = string.length() - 1;
        int n9 = n3 - n.b;
        int n10 = 0;
        while (n10 <= n8) {
            block15: {
                block17: {
                    block16: {
                        int n11;
                        char c2 = string.charAt(n10);
                        if (c2 == '#' && n10 != n8) {
                            if (string.charAt(n10 + 1) == 'n') {
                                f2.a[f2.d++] = new int[]{n4, n10, n5, n6, n7};
                                n5 = 0;
                                n6 += n2 + 1;
                                n4 = n10 + 2;
                            } else if (string.charAt(n10 + 1) >= '0' && string.charAt(n10 + 1) <= '7') {
                                f2.c += f2.b;
                                f2.a[f2.d++] = new int[]{n4, n10, n5, n6, n7};
                                n11 = font.charsWidth(string.substring(n4, n10).toCharArray(), 0, n10 - n4);
                                if (by != -1 && d2 != null) {
                                    n11 = c.d.a(string.substring(n4, n10), 0, n10 - n4);
                                }
                                n5 += n11;
                                n7 = string.charAt(n10 + 1) - 48;
                                n4 = n10 + 2;
                            }
                        } else if (n10 - n4 >= 0) {
                            f2.b = n11 = c.d.a(string.substring(n4, n10 + 1), 0, n10 - n4 + 1);
                            if (bl && (n5 + n11 >= n3 || c2 == ' ' && n5 + n11 >= n9)) {
                                f2.a[f2.d++] = new int[]{n4, n10, n5, n6, n7};
                                n5 = 0;
                                n6 += n2 + 1;
                                n4 = n10;
                            }
                        }
                        if (n10 != n8) break block15;
                        if (n10 != n4) break block16;
                        n11 = 0;
                        while (n11 < j.length) {
                            if (string.charAt(n10) == j[n11]) {
                                f2.a[f2.d - 1][1] = n10 + 1;
                                break;
                            }
                            ++n11;
                        }
                        if (n11 < j.length) break block15;
                        break block17;
                    }
                    if (n10 + 1 - n4 <= 0) break block15;
                }
                f2.a[f2.d++] = new int[]{n4, n10 + 1, n5, n6, n7};
            }
            ++n10;
        }
        return f2;
    }

    private static void a(Graphics graphics, String string, int n2, int n3, int n4, int n5, d d2) {
        c.d.a(string, n3, n4, 20, n2, graphics);
    }

    public static void a(Graphics graphics, String string, int n2, int n3, int n4, int n5, int n6, d d2, int n7) {
        switch (n7) {
            case 0: {
                if (d2 != null) {
                    c.d.a(string, n3 - 1, n4, 17, 8607289, graphics);
                    c.d.a(string, n3 + 1, n4, 17, 8607289, graphics);
                    break;
                }
                graphics.setColor(8607289);
                graphics.drawString(string, n3 - 1, n4, 17);
                graphics.drawString(string, n3 + 1, n4, 17);
                break;
            }
            case 1: {
                if (d2 != null) {
                    c.d.a(string, n3, n4 - 1, 17, 8607289, graphics);
                    c.d.a(string, n3, n4 + 1, 17, 8607289, graphics);
                    break;
                }
                graphics.setColor(8607289);
                graphics.drawString(string, n3, n4 - 1, 17);
                graphics.drawString(string, n3, n4 + 1, 17);
                break;
            }
            case 2: {
                if (d2 != null) {
                    c.d.a(string, n3, n4 - 1, 17, 8607289, graphics);
                    c.d.a(string, n3, n4 + 1, 17, 8607289, graphics);
                    c.d.a(string, n3 - 1, n4, 17, 8607289, graphics);
                    c.d.a(string, n3 + 1, n4, 17, 8607289, graphics);
                    break;
                }
                graphics.setColor(8607289);
                graphics.drawString(string, n3, n4 - 1, 17);
                graphics.drawString(string, n3, n4 + 1, 17);
                graphics.drawString(string, n3 - 1, n4, 17);
                graphics.drawString(string, n3 + 1, n4, 17);
            }
        }
        if (d2 != null) {
            c.d.a(string, n3, n4, 17, n2, graphics);
            return;
        }
        graphics.setColor(n2);
        graphics.drawString(string, n3, n4, 17);
    }

    public static void a(String string, int n2, int n3, Font stringArray, boolean bl, byte object, d d2) {
        Object object2 = a.e.a(string, n2, n3, true, (Font)stringArray, (byte)-1, d2);
        n3 = ((f)object2).d;
        object2 = ((f)object2).a;
        b = 1;
        m = 0;
        stringArray = new String[50];
        bl = false;
        object = object2[0][3];
        Object object3 = 0;
        for (int i2 = 0; i2 < n3; ++i2) {
            if (object != object2[i2][3]) {
                object = object2[i2][3];
                stringArray[(int)(bl++ ? 1 : 0)] = string.substring((int)object3, (int)object2[i2][0]);
                object3 = object2[i2][0];
            }
            if (i2 != n3 - 1) continue;
            stringArray[(int)(bl++ ? 1 : 0)] = string.substring((int)object3, (int)object2[i2][1]);
            break;
        }
        String[] stringArray2 = new String[bl];
        System.arraycopy(stringArray, 0, stringArray2, 0, stringArray2.length);
        a = stringArray2;
    }

    public static void d(int n2) {
        k = n2 / a.a.o();
    }

    public static String e(int n2) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = k * m; i2 < (n2 * k > a.length ? a.length : n2 * k); ++i2) {
            stringBuffer.append(a[i2]);
        }
        m = n2;
        return stringBuffer.toString();
    }

    public static int b() {
        if (a.length % k == 0) {
            return a.length / k;
        }
        return a.length / k + 1;
    }

    public static void c() {
        ++b;
    }

    public static String[] a(String objectArray, char c2) {
        Vector<String> vector = new Vector<String>();
        int n2 = 0;
        int n3 = 0;
        while (n2 != -1 && (n2 = objectArray.trim().indexOf(c2, n3)) != -1) {
            String string = objectArray.trim().substring(n3, n2);
            n3 = n2 + 1;
            vector.addElement(string);
        }
        vector.addElement(objectArray.substring(n3));
        objectArray = new String[vector.size()];
        vector.copyInto(objectArray);
        return objectArray;
    }

    public static int b(String string) {
        return Integer.parseInt(string);
    }

    public static short c(String string) {
        return Short.parseShort(string);
    }

    public static byte d(String string) {
        return Byte.parseByte(string);
    }

    public static byte[] e(String stringArray) {
        stringArray = a.e.a((String)stringArray, ',');
        byte[] byArray = new byte[stringArray.length];
        for (int i2 = 0; i2 < byArray.length; ++i2) {
            String string = stringArray[i2];
            byArray[i2] = Byte.parseByte(string);
        }
        return byArray;
    }

    public static long[] a(long l2) {
        long[] lArray = new long[3];
        long[] lArray2 = lArray;
        lArray[0] = l2 / 3600000L;
        lArray2[1] = l2 / 60000L;
        lArray2[2] = l2 / 1000L;
        return lArray2;
    }

    static {
        g = new int[20][2];
        i = new int[]{1862801, 15673612, 12067264, 11689977, 16359727, 0xFF0000, 255};
        j = new char[]{'\uff0c', '\u3002', '\uff1f', '\uff01', '\uff1a', '\uff1b', '\u2019', '\u201d', '\u3001'};
        k = 0;
        l = 0;
        b = 1;
        m = 0;
    }

    private static byte[] g(String object) {
        try {
            int n2;
            if (((String)object).indexOf("img_") < 0) {
                return a.e.f((String)object);
            }
            byte[] byArray = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            "".getClass();
            object = b.a((String)object);
            while ((n2 = ((InputStream)object).read(byArray)) > 0) {
                byteArrayOutputStream.write(byArray, 0, n2);
            }
            return byteArrayOutputStream.toByteArray();
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            return null;
        }
    }
}

