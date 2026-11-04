/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.midlet.MIDlet
 */
import game.GameMIDLet;
import java.io.DataInputStream;
import java.io.FilterInputStream;
import javax.microedition.midlet.MIDlet;

public final class u {
    public static String a;
    public static String b;
    private static String[] d;
    private static String[] e;
    public static String[] c;
    private static int[] f;
    private static boolean g;

    static {
        g = false;
    }

    public static final void a(int n2) {
        if (d == null || e == null) {
            return;
        }
        if (n2 >= e.length) {
            return;
        }
        String string = GameMIDLet.a.getAppProperty("uid");
        if (string == null) {
            string = "0";
        }
        string = u.a(e[n2], "%1", string);
        String string2 = GameMIDLet.a.getAppProperty("Term");
        if (string2 == null) {
            string2 = "";
        }
        string = u.a(string, "%2", string2);
        string2 = GameMIDLet.a.getAppProperty("RefCode");
        if (string2 == null) {
            string2 = "";
        }
        string = u.a(string, "%cp", string2);
        string = string.trim();
        string = u.a(string, "  ", " ");
        a = d[n2];
        b = string;
    }

    private static byte a(byte by) {
        return (byte)(by <= 57 ? by - 48 : by + 10 - 97);
    }

    private static byte[] a(byte[] byArray) {
        int n2 = byArray.length;
        int n3 = n2 / 2;
        byte[] byArray2 = new byte[n3];
        int n4 = 0;
        int n5 = 0;
        while (n4 < n2) {
            int n6 = n5;
            byArray2[n6] = (byte)(byArray2[n6] | u.a(byArray[n4]));
            int n7 = n5++;
            byArray2[n7] = (byte)(byArray2[n7] | u.a(byArray[n4 + 1]) << 4);
            n4 += 2;
        }
        return byArray2;
    }

    private static String a(String string, String string2, String string3) {
        StringBuffer stringBuffer = new StringBuffer();
        int n2 = string.indexOf(string2);
        int n3 = 0;
        int n4 = string2.length();
        while (n2 != -1) {
            stringBuffer.append(string.substring(n3, n2)).append(string3);
            n3 = n2 + n4;
            n2 = string.indexOf(string2, n3);
        }
        stringBuffer.append(string.substring(n3, string.length()));
        return stringBuffer.toString();
    }

    private static void a(byte[] byArray, byte[] byArray2) {
        int n2 = byArray.length;
        int n3 = byArray2.length;
        int n4 = 0;
        while (n4 < n2) {
            int n5 = n3 - n4 % 3;
            int n6 = 0;
            while (n6 < n5) {
                byArray[n4] = (byte)(byArray2[n6] ^ byArray[n4]);
                ++n6;
            }
            ++n4;
        }
    }

    private static String b(byte[] byArray) {
        String string;
        try {
            string = new String(byArray, "utf-8");
        }
        catch (Exception exception) {
            try {
                string = new String(byArray, "UTF-8");
            }
            catch (Exception exception2) {
                string = new String(byArray);
            }
        }
        return string;
    }

    private static String[] a(String string, String string2) {
        int n2;
        if (string == null) {
            System.out.println(" split. aStr == null");
            return null;
        }
        if (string.length() <= 0 || string2 == null) {
            return new String[]{string};
        }
        if (string2.length() <= 0) {
            return new String[]{string};
        }
        String[] stringArray = null;
        String string3 = string;
        int[] nArray = new int[string3.length()];
        int[] nArray2 = new int[string3.length()];
        int n3 = 0;
        boolean bl = false;
        int n4 = 0;
        int n5 = 0;
        do {
            n2 = 0;
            n5 = n4 + string2.length();
            if (n5 <= string3.length() && string3.substring(n4, n5).equals(string2)) {
                n2 = 1;
            }
            if (n2 != 0) {
                if (bl) {
                    bl = false;
                    nArray2[n3++] = n4;
                }
                n4 = n5;
                continue;
            }
            if (!bl) {
                bl = true;
                nArray[n3] = n4;
            }
            ++n4;
        } while (n4 < string3.length());
        if (bl) {
            nArray2[n3++] = string3.length();
        }
        if (n3 > 0) {
            stringArray = new String[n3];
            n2 = 0;
            while (n2 < n3) {
                stringArray[n2] = string.substring(nArray[n2], nArray2[n2]);
                ++n2;
            }
        }
        return stringArray;
    }

    public static void a(MIDlet mIDlet) {
        Object object;
        int n2;
        Object object2;
        if (g) {
            return;
        }
        g = true;
        Object object3 = mIDlet.getAppProperty("sr");
        boolean bl = true;
        String[] stringArray = null;
        if (object3 == null) {
            try {
                DataInputStream dataInputStream = new DataInputStream("".getClass().getResourceAsStream("/l2.bin"));
                object2 = dataInputStream;
                n2 = dataInputStream.available();
                object3 = new byte[n2];
                ((DataInputStream)object2).read((byte[])object3);
                ((FilterInputStream)object2).close();
                object = u.b((byte[])object3);
                stringArray = u.a((String)object, String.valueOf('\n'));
                object3 = stringArray[0].trim().getBytes();
            }
            catch (Exception exception) {
                Exception exception2 = exception;
                exception.printStackTrace();
                return;
            }
            bl = false;
        } else {
            object3 = ((String)object3).getBytes();
        }
        object2 = new byte[6];
        n2 = ((Object)object3).length;
        System.arraycopy(object3, 0, object2, 0, 3);
        System.arraycopy(object3, n2 - 3, object2, 3, 3);
        object = new byte[n2 - 6];
        System.arraycopy(object3, 3, object, 0, n2 - 6);
        byte[] byArray = u.a((byte[])object);
        object = byArray;
        u.a(byArray, (byte[])object2);
        object3 = new byte[]{1, 2, 5, 7, 4};
        n2 = ((Object)object).length - 5;
        int n3 = 0;
        while (n3 < n2) {
            Object object4 = object3;
            int n4 = n3 % 5;
            object4[n4] = (byte)(object4[n4] ^ object[n3]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 5) {
            if (object3[n3] != object[n2 + n3]) {
                return;
            }
            ++n3;
        }
        object3 = object;
        object = new byte[n2];
        System.arraycopy(object3, 0, object, 0, n2);
        object3 = u.b((byte[])object);
        String[] stringArray2 = u.a((String)object3, "|");
        n2 = Integer.parseInt(stringArray2[0]);
        d = new String[n2];
        e = new String[n2];
        f = new int[n2];
        int n5 = 0;
        while (n5 < n2) {
            u.d[n5] = stringArray2[n5 + 1];
            u.f[n5] = Integer.parseInt(stringArray2[n5 + n2 + 1]);
            u.e[n5] = stringArray2[n5 + n2 + n2 + 1];
            ++n5;
        }
        c = new String[n2];
        if (bl) {
            n5 = 0;
            while (n5 < n2) {
                u.c[n5] = mIDlet.getAppProperty("sr" + (n5 + 1));
                ++n5;
            }
            return;
        }
        n5 = 0;
        while (n5 < n2) {
            u.c[n5] = stringArray[n5 + 1].trim();
            ++n5;
        }
    }
}

