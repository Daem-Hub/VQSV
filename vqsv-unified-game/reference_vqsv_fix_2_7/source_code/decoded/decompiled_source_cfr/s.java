/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.io.DataInputStream;
import java.util.Hashtable;
import javax.microedition.lcdui.Graphics;

public final class s {
    public static byte a;
    private static byte[][] c;
    private static int[] d;
    private static int[] e;
    private static Hashtable f;
    public static int b;
    private static String g;
    private static int h;
    private static char i;
    private static int j;

    static {
        if (c == null) {
            f = new Hashtable();
            DataInputStream dataInputStream = new DataInputStream("".getClass().getResourceAsStream("/font.bin"));
            try {
                String string = dataInputStream.readUTF();
                byte by = a = dataInputStream.readByte();
                int n2 = string.length();
                d = new int[n2];
                e = new int[n2];
                int n3 = 0;
                int n4 = 0;
                while (n4 < n2) {
                    s.d[n4] = dataInputStream.readByte();
                    s.e[n4] = n3;
                    n3 += d[n4];
                    f.put(new Integer(string.charAt(n4)), new Integer(n4));
                    ++n4;
                }
                c = new byte[by][n3];
                n4 = 7;
                byte by2 = 0;
                n2 = 0;
                while (n2 < by) {
                    int n5 = 0;
                    while (n5 < n3) {
                        if (++n4 >= 8) {
                            n4 = 0;
                            by2 = dataInputStream.readByte();
                        }
                        if (by2 & true) {
                            s.c[n2][n5] = 1;
                        }
                        by2 = (byte)(by2 >> 1);
                        ++n5;
                    }
                    ++n2;
                }
                dataInputStream.close();
            }
            catch (Exception exception) {
                Exception exception2 = exception;
                exception.printStackTrace();
            }
        }
        b = s.a("nhung1");
    }

    public static int a(String string) {
        if (string == g) {
            return h;
        }
        g = string;
        h = s.a(string, 0, string.length() - 1);
        return h;
    }

    public static int a(String string, int n2, int n3) {
        int n4 = 0;
        while (n2 < n3) {
            Integer n5 = new Integer(string.charAt(n2));
            try {
                n4 += d[(Integer)f.get(n5)];
            }
            catch (Exception exception) {}
            ++n2;
        }
        return n4;
    }

    public static int a(char c2) {
        if (c2 == i) {
            return j;
        }
        try {
            i = c2;
            j = d[(Integer)f.get(new Integer(c2))];
            return j;
        }
        catch (Exception exception) {
            j = 0;
            return 0;
        }
    }

    public static final void a(Graphics graphics, String string, int n2, int n3) {
        int n4 = string.length();
        int n5 = 0;
        while (n5 < n4) {
            n2 += s.a(graphics, string.charAt(n5), n2, n3);
            ++n5;
        }
    }

    public static final void a(Graphics graphics, String string, int n2, int n3, int n4) {
        int n5 = n2;
        int n6 = n3;
        if ((n4 & 1) != 0) {
            n5 = n2 - s.a(string) / 2;
        } else if ((n4 & 8) != 0) {
            n5 = n2 - s.a(string);
        }
        if ((n4 & 2) != 0) {
            n6 = n3 - a / 2;
        } else if ((n4 & 0x20) != 0) {
            n6 = n3 - a;
        }
        n2 = string.length();
        n3 = 0;
        while (n3 < n2) {
            n5 += s.a(graphics, string.charAt(n3), n5, n6);
            ++n3;
        }
    }

    public static final int a(Graphics graphics, char c2, int n2, int n3) {
        Integer n4 = new Integer(c2);
        try {
            int n5 = (Integer)f.get(n4);
            int n6 = d[n5];
            n5 = e[n5];
            int n7 = a;
            int n8 = 0;
            while (n8 < n7) {
                byte[] byArray = c[n8];
                int n9 = 0;
                while (n9 < n6) {
                    if (byArray[n9 + n5] != 0) {
                        graphics.drawLine(n2 + n9, n3 + n8, n2 + n9, n3 + n8);
                    }
                    ++n9;
                }
                ++n8;
            }
            return n6;
        }
        catch (Exception exception) {
            System.out.println("c1=" + String.valueOf(c2));
            return 0;
        }
    }

    public static String[] a(String string, int n2) {
        String[] stringArray = new String[50];
        int n3 = string.length();
        int n4 = n2 - b;
        int n5 = 0;
        int n6 = 0;
        StringBuffer stringBuffer = new StringBuffer();
        int n7 = 0;
        while (n7 < n3) {
            char c2 = string.charAt(n7);
            int n8 = s.a(c2);
            if ((n5 += n8) > n2 || c2 == ' ' && n5 > n4) {
                stringArray[n6++] = stringBuffer.toString();
                stringBuffer = new StringBuffer();
                if (c2 != ' ') {
                    n5 = n8;
                    stringBuffer.append(c2);
                } else {
                    n5 = 0;
                }
            } else {
                stringBuffer.append(c2);
            }
            ++n7;
        }
        if (stringBuffer.length() > 0) {
            stringArray[n6++] = stringBuffer.toString();
        }
        String[] stringArray2 = new String[n6];
        System.arraycopy(stringArray, 0, stringArray2, 0, n6);
        return stringArray2;
    }
}

