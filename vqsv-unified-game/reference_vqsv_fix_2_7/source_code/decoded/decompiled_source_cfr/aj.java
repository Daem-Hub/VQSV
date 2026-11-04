/*
 * Decompiled with CFR 0.152.
 */
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Vector;

public class aj
extends DataInputStream {
    private String a;
    private static Vector b = new Vector();
    private static Class c;

    private aj(InputStream inputStream, String string) {
        super(inputStream);
        this.a = string;
    }

    public static InputStream a(String string) {
        InputStream inputStream;
        Class<?> clazz = c;
        if (clazz == null) {
            try {
                clazz = c = Class.forName("aj");
            }
            catch (ClassNotFoundException classNotFoundException) {
                throw new NoClassDefFoundError(classNotFoundException.getMessage());
            }
        }
        if ((inputStream = clazz.getResourceAsStream(string)) != null) {
            inputStream = new aj(inputStream, string);
            b.addElement(inputStream);
            if (b.size() > 10) {
                System.out.println("current size: " + b.size());
                int n2 = 0;
                while (n2 < b.size()) {
                    aj aj2 = (aj)b.elementAt(n2);
                    try {
                        if (aj2.available() == 0) {
                            System.out.println("auto close1: " + aj2.a);
                            aj2.close();
                            --n2;
                        }
                    }
                    catch (Exception exception) {}
                    ++n2;
                }
                System.out.println("new size: " + b.size());
                if (b.size() > 10) {
                    aj aj3 = (aj)b.elementAt(0);
                    try {
                        System.out.println("auto close2: " + aj3.a);
                        aj3.close();
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

