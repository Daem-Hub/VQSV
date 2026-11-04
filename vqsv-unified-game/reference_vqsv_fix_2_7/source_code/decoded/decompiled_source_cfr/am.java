/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Image
 */
import javax.microedition.lcdui.Image;

public final class am {
    private static Image[] a;
    private static byte[] b;
    private static int c;

    public static void a() {
        c = 50000;
        a = new Image[50000];
        b = new byte[c];
    }

    public static Image a(int n2) {
        if (a[n2] == null) {
            am.a[n2] = ae.b("/data/img/", "img_" + n2);
        }
        int n3 = n2;
        b[n3] = (byte)(b[n3] + 1);
        return a[n2];
    }

    public static void b(int n2) {
        int n3 = n2;
        b[n3] = (byte)(b[n3] - 1);
        if (b[n2] <= 0) {
            am.b[n2] = 0;
        }
    }

    public static boolean c(int n2) {
        if (n2 == -1) {
            return true;
        }
        int n3 = n2;
        b[n3] = (byte)(b[n3] - 1);
        if (b[n2] <= 0) {
            am.b[n2] = 0;
            am.a[n2] = null;
            return true;
        }
        return false;
    }
}

