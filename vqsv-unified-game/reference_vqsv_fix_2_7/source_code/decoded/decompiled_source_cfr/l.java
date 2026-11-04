/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Image
 */
import javax.microedition.lcdui.Image;

public final class l {
    private static byte a = 1;
    private static byte b = (byte)2;

    public static e a(Image image, e e2) {
        int n2 = image.getWidth();
        int n3 = image.getHeight();
        int[] nArray = new int[n2 * n3];
        image.getRGB(nArray, 0, n2, 0, 0, n2, n3);
        for (int i2 = 0; i2 < nArray.length; ++i2) {
            if (nArray[i2] != -1 && nArray[i2] != -16777216) continue;
            nArray[i2] = 0xFFFFFF;
        }
        e2.a(nArray, n2, n3);
        return e2;
    }

    public static e a(d d2, int n2, int[] nArray, byte by, e e2) {
        Image image = Image.createImage((int)nArray[2], (int)nArray[3]);
        image.getGraphics().setColor(0);
        image.getGraphics().fillRect(0, 0, nArray[2], nArray[3]);
        d2.a(image.getGraphics(), n2, -nArray[0], -nArray[1], by);
        e2.d = nArray[0];
        e2.e = nArray[1];
        return l.a(image, e2);
    }

    public static e a(e e2, int n2, int n3) {
        int[] nArray = new int[n2 * n3];
        for (int i2 = 0; i2 < n3; ++i2) {
            for (int i3 = 0; i3 < n2; ++i3) {
                int n4 = i3 * e2.b / n2;
                int n5 = i2 * e2.c / n3;
                nArray[i3 + i2 * n2] = e2.a[n4 + n5 * e2.b];
            }
        }
        e2.a(nArray, n2, n3);
        e2.d = e2.d * n2 / e2.b / 10;
        e2.e = e2.e * n2 / e2.b / 10;
        return e2;
    }

    public static e a(e e2, int n2) {
        int n3 = e2.b * n2 / 10;
        int n4 = e2.c * n2 / 10;
        int[] nArray = new int[n3 * n4];
        for (int i2 = 0; i2 < n4; ++i2) {
            for (int i3 = 0; i3 < n3; ++i3) {
                int n5 = i3 * e2.b / n3;
                int n6 = i2 * e2.c / n4;
                nArray[i3 + i2 * n3] = e2.a[n5 + n6 * e2.b];
            }
        }
        e2.a(nArray, n3, n4);
        e2.d = e2.d * n2 / 10;
        e2.e = e2.e * n2 / 10;
        return e2;
    }

    public static e b(e e2, int n2, int n3) {
        for (int i2 = 0; i2 < e2.c; ++i2) {
            for (int i3 = 0; i3 < e2.b; ++i3) {
                int n4 = e2.a[i2 * e2.b + i3];
                int n5 = n4 >> 24;
                int n6 = n4 >> 16 & 0xFF;
                int n7 = n4 >> 8 & 0xFF;
                n4 &= 0xFF;
                n6 = n6 * n2 + n3;
                n7 = n7 * n2 + n3;
                n4 = n4 * n2 + n3;
                if (n6 > 255) {
                    n6 = 255;
                } else if (n6 < 0) {
                    n6 = 0;
                }
                if (n7 > 255) {
                    n7 = 255;
                } else if (n7 < 0) {
                    n7 = 0;
                }
                if (n4 > 255) {
                    n4 = 255;
                } else if (n4 < 0) {
                    n4 = 0;
                }
                e2.a[i2 * e2.b + i3] = n5 << 24 | n6 << 16 | n7 << 8 | n4;
            }
        }
        return e2;
    }

    public static e b(e e2, int n2) {
        if (n2 < 0 || n2 > 255) {
            return e2;
        }
        for (int i2 = 0; i2 < e2.f; ++i2) {
            if (e2.a[i2] == 0xFFFFFF || e2.a[i2] == 0) continue;
            e2.a[i2] = e2.a[i2] == -16777216 ? 0 : n2 << 24 | e2.a[i2] & 0xFFFFFF;
        }
        return e2;
    }

    public static Image a(Image image) {
        e e2 = new e();
        int n2 = image.getWidth();
        int n3 = image.getHeight();
        Image image2 = Image.createImage((int)n2, (int)n3);
        image2.getGraphics().setColor(0);
        image2.getGraphics().fillRect(0, 0, n2, n3);
        image2.getGraphics().drawImage(image, 0, 0, 20);
        e2.a(ae.a(image2), n2, n3);
        l.b(e2, 100);
        image = ae.a(e2.a, e2.b, e2.c);
        e2.a = null;
        return image;
    }

    public static e a(e e2, int n2, int n3, int n4, int n5) {
        for (int i2 = 0; i2 < e2.a.length; ++i2) {
            if (e2.a[i2] == 0xFFFFFF) continue;
            e2.a[i2] = n2 < 0 || n2 > 255 ? n3 << 16 | n4 << 8 | n5 : n2 << 24 | n3 << 16 | n4 << 8 | n5;
        }
        return e2;
    }

    public static e a(e e2, e e3, byte by) {
        int n2 = 0;
        int n3 = 0;
        if (by == 0) {
            e2 = l.b(e2, 5, 5);
        }
        for (int i2 = 0; i2 < e2.c; ++i2) {
            for (int i3 = 0; i3 < e2.b; ++i3) {
                int n4 = e2.a[i2 * e2.b + i3];
                int n5 = e3.a[n3 * e3.b + n2];
                if (n4 >> 24 != 0) {
                    if (by == 0) {
                        e2.a[i2 * e2.b + i3] = n4 & n5;
                    } else if (by == a) {
                        e2.a[i2 * e2.b + i3] = n4 | n5;
                    } else if (by == b) {
                        e2.a[i2 * e2.b + i3] = n5;
                    }
                }
                if (n2 < e3.b - 1) {
                    ++n2;
                    continue;
                }
                n2 = 0;
            }
            n3 = n3 < e3.c - 1 ? ++n3 : 0;
            n2 = 0;
        }
        return e2;
    }
}

