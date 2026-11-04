/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Image
 */
package a.a;

import a.a.c;
import a.b.g;
import a.e;
import javax.microedition.lcdui.Image;

public final class d {
    private static byte a = 0;
    private static byte b = 1;
    private static byte c = (byte)2;

    public static c a(Image image, c c2) {
        int n2 = image.getWidth();
        int n3 = image.getHeight();
        int[] nArray = new int[n2 * n3];
        image.getRGB(nArray, 0, n2, 0, 0, n2, n3);
        for (int i2 = 0; i2 < nArray.length; ++i2) {
            if (nArray[i2] != -1 && nArray[i2] != -16777216) continue;
            nArray[i2] = 0xFFFFFF;
        }
        c2.a(nArray, n2, n3);
        return c2;
    }

    public static c a(g g2, int n2, int[] nArray, byte by, c c2) {
        Image image = Image.createImage((int)nArray[2], (int)nArray[3]);
        image.getGraphics().setColor(0);
        image.getGraphics().fillRect(0, 0, nArray[2], nArray[3]);
        g2.a(image.getGraphics(), n2, -nArray[0], -nArray[1], by, 20);
        c2.d = nArray[0];
        c2.e = nArray[1];
        return d.a(image, c2);
    }

    public static c a(c c2, int n2, int n3) {
        int[] nArray = new int[n2 * n3];
        for (int i2 = 0; i2 < n3; ++i2) {
            for (int i3 = 0; i3 < n2; ++i3) {
                int n4 = i3 * c2.b / n2;
                int n5 = i2 * c2.c / n3;
                nArray[i3 + i2 * n2] = c2.a[n4 + n5 * c2.b];
            }
        }
        c2.a(nArray, n2, n3);
        c2.d = c2.d * n2 / c2.b / 10;
        c2.e = c2.e * n2 / c2.b / 10;
        return c2;
    }

    public static c a(c c2, int n2) {
        int n3 = c2.b * n2 / 10;
        int n4 = c2.c * n2 / 10;
        int[] nArray = new int[n3 * n4];
        for (int i2 = 0; i2 < n4; ++i2) {
            for (int i3 = 0; i3 < n3; ++i3) {
                int n5 = i3 * c2.b / n3;
                int n6 = i2 * c2.c / n4;
                nArray[i3 + i2 * n3] = c2.a[n5 + n6 * c2.b];
            }
        }
        c2.a(nArray, n3, n4);
        c2.d = c2.d * n2 / 10;
        c2.e = c2.e * n2 / 10;
        return c2;
    }

    public static c b(c c2, int n2, int n3) {
        for (int i2 = 0; i2 < c2.c; ++i2) {
            for (int i3 = 0; i3 < c2.b; ++i3) {
                int n4 = c2.a[i2 * c2.b + i3];
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
                c2.a[i2 * c2.b + i3] = n5 << 24 | n6 << 16 | n7 << 8 | n4;
            }
        }
        return c2;
    }

    public static c b(c c2, int n2) {
        if (n2 < 0 || n2 > 255) {
            return c2;
        }
        for (int i2 = 0; i2 < c2.f; ++i2) {
            if (c2.a[i2] == 0xFFFFFF || c2.a[i2] == 0) continue;
            c2.a[i2] = c2.a[i2] == -16777216 ? 0 : n2 << 24 | c2.a[i2] & 0xFFFFFF;
        }
        return c2;
    }

    public static Image a(Image image, int n2) {
        c c2 = new c();
        int n3 = image.getWidth();
        int n4 = image.getHeight();
        Image image2 = Image.createImage((int)n3, (int)n4);
        image2.getGraphics().setColor(0);
        image2.getGraphics().fillRect(0, 0, n3, n4);
        image2.getGraphics().drawImage(image, 0, 0, 20);
        c2.a(e.a(image2), n3, n4);
        d.b(c2, 100);
        image = e.a(c2.a, c2.b, c2.c, true);
        v0.a = null;
        return image;
    }

    public static c a(c c2, int n2, int n3, int n4, int n5) {
        for (int i2 = 0; i2 < c2.a.length; ++i2) {
            if (c2.a[i2] == 0xFFFFFF) continue;
            c2.a[i2] = n2 < 0 || n2 > 255 ? n3 << 16 | n4 << 8 | n5 : n2 << 24 | n3 << 16 | n4 << 8 | n5;
        }
        return c2;
    }

    public static c a(c c2, c c3, byte by) {
        int n2 = 0;
        int n3 = 0;
        if (by == 0) {
            c2 = d.b(c2, 5, 5);
        }
        for (int i2 = 0; i2 < c2.c; ++i2) {
            for (int i3 = 0; i3 < c2.b; ++i3) {
                int n4 = c2.a[i2 * c2.b + i3];
                int n5 = c3.a[n3 * c3.b + n2];
                if (n4 >> 24 != 0) {
                    if (by == 0) {
                        c2.a[i2 * c2.b + i3] = n4 & n5;
                    } else if (by == b) {
                        c2.a[i2 * c2.b + i3] = n4 | n5;
                    } else if (by == c) {
                        c2.a[i2 * c2.b + i3] = n5;
                    }
                }
                if (n2 < c3.b - 1) {
                    ++n2;
                    continue;
                }
                n2 = 0;
            }
            n3 = n3 < c3.c - 1 ? ++n3 : 0;
            n2 = 0;
        }
        return c2;
    }

    public static Image a(Image image) {
        c c2 = new c();
        c2.a(e.a(image), image.getWidth(), image.getHeight());
        for (int i2 = 0; i2 < c2.c; ++i2) {
            for (int i3 = 0; i3 < c2.b; ++i3) {
                int n2 = c2.a[i2 * c2.b + i3];
                int n3 = n2 >> 24;
                int n4 = n2 >> 16 & 0xFF;
                int n5 = n2 >> 8 & 0xFF;
                n2 &= 0xFF;
                n4 = n2 = (int)(0.299 * (double)n4 + 0.587 * (double)n5 + 0.114 * (double)n2);
                n5 = n2;
                if (c2.a[i2 * c2.b + i3] == -1 || c2.a[i2 * c2.b + i3] == -16777216) {
                    int n6 = i2 * c2.b + i3;
                    c2.a[n6] = c2.a[n6] & 0xFFFFFF;
                    continue;
                }
                c2.a[i2 * c2.b + i3] = n3 << 24 | n4 << 16 | n5 << 8 | n2;
            }
        }
        image = e.a(c2.a, c2.b, c2.c, true);
        c c3 = c2;
        c2.a = null;
        return image;
    }
}

