/*
 * Decompiled with CFR 0.152.
 */
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Vector;

public class b
extends DataInputStream {
    private String a;
    private static Vector b = new Vector();
    private static Class c;

    private b(InputStream inputStream, String string) {
        super(inputStream);
        this.a = string;
    }

    public static InputStream a(String string) {
        InputStream inputStream;
        Class<?> clazz = c;
        if (clazz == null) {
            try {
                clazz = c = Class.forName("b");
            }
            catch (ClassNotFoundException classNotFoundException) {
                throw new NoClassDefFoundError(classNotFoundException.getMessage());
            }
        }
        if ((inputStream = clazz.getResourceAsStream(string)) != null) {
            inputStream = new b(inputStream, string);
            b.addElement(inputStream);
            if (b.size() > 10) {
                System.out.println("current size: " + b.size());
                int n2 = 0;
                while (n2 < b.size()) {
                    b b2 = (b)b.elementAt(n2);
                    try {
                        if (b2.available() == 0) {
                            System.out.println("auto close1: " + b2.a);
                            b2.close();
                            --n2;
                        }
                    }
                    catch (Exception exception) {}
                    ++n2;
                }
                System.out.println("new size: " + b.size());
                if (b.size() > 10) {
                    b b3 = (b)b.elementAt(0);
                    try {
                        System.out.println("auto close2: " + b3.a);
                        b3.close();
                    }
                    catch (Exception exception) {}
                }
            }
        }
        return inputStream;
    }

    public void close() {
        b.removeElement(this);
        try {
            super.close();
            return;
        }
        catch (Exception exception) {
            System.out.println("close error");
            return;
        }
    }
}

