/*
 * Decompiled with CFR 0.152.
 */
import java.util.Vector;

public final class h {
    private static Vector a = new Vector();

    public static void a(Throwable throwable, String string) {
        String[] stringArray = new String[]{"", ""};
        if (string != null) {
            stringArray[0] = string;
            System.out.println(stringArray[0]);
        } else {
            stringArray[0] = "";
            System.out.println(stringArray[0]);
        }
        stringArray[1] = throwable != null ? throwable.toString() : "";
        a.addElement(stringArray);
    }
}

