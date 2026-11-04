/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Font
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.rms.RecordStore
 *  javax.microedition.rms.RecordStoreException
 *  javax.microedition.rms.RecordStoreNotOpenException
 */
package a.a;

import e.a.a.a.a.a;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

public class c {
    public int[] a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;

    public final void a(int[] nArray, int n2, int n3) {
        this.a = nArray;
        this.b = n2;
        this.c = n3;
        this.f = nArray.length;
    }

    public final c a() {
        c c2 = new c();
        new c().a = new int[this.a.length];
        System.arraycopy(this.a, 0, c2.a, 0, c2.a.length);
        c2.b = this.b;
        c2.c = this.c;
        c2.f = this.a.length;
        c2.d = this.d;
        c2.e = this.e;
        return c2;
    }

    private static RecordStore a(String string, String string2, String string3, String charSequence, int n2) {
        RecordStore recordStore = null;
        try {
            if (string3 == null || string3.length() == 0) {
                string3 = "00";
            }
            if (charSequence != null) {
                ((String)charSequence).length();
            }
            charSequence = new StringBuffer();
            ((StringBuffer)charSequence).append("dcn").append(string).append(string2).append(string3).append(n2);
            recordStore = RecordStore.openRecordStore((String)((StringBuffer)charSequence).toString(), (boolean)true, (int)1, (boolean)true);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return recordStore;
    }

    public static void a(a object, String string, String object2, String object3, String string2, int n2) {
        if ((string = a.a.c.a(string, (String)object2, (String)object3, string2, n2)) != null) {
            try {
                try {
                    object2 = new ByteArrayOutputStream();
                    object3 = new DataOutputStream((OutputStream)object2);
                    ((DataOutputStream)object3).writeInt(((a)object).i());
                    if (((a)object).i() < n2) {
                        ((DataOutputStream)object3).writeInt(((a)object).d());
                        ((DataOutputStream)object3).writeUTF(((a)object).c());
                        ((DataOutputStream)object3).writeUTF(((a)object).b());
                        ((DataOutputStream)object3).writeUTF(((a)object).g());
                        ((DataOutputStream)object3).writeInt(((a)object).a());
                        ((DataOutputStream)object3).writeUTF(((a)object).f());
                        ((DataOutputStream)object3).writeUTF(((a)object).e());
                        ((DataOutputStream)object3).writeBoolean(((a)object).h());
                        ((DataOutputStream)object3).writeInt(((a)object).j());
                        ((DataOutputStream)object3).writeLong(((a)object).k());
                    }
                    object = ((ByteArrayOutputStream)object2).toByteArray();
                    if (string.getNumRecords() == 0) {
                        string.addRecord((byte[])object, 0, ((Object)object).length);
                    } else {
                        string.setRecord(1, (byte[])object, 0, ((Object)object).length);
                    }
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                    try {
                        string.closeRecordStore();
                        return;
                    }
                    catch (RecordStoreNotOpenException recordStoreNotOpenException) {
                        recordStoreNotOpenException.printStackTrace();
                        return;
                    }
                    catch (RecordStoreException recordStoreException) {
                        recordStoreException.printStackTrace();
                        return;
                    }
                }
            }
            catch (Throwable throwable) {
                try {
                    string.closeRecordStore();
                }
                catch (RecordStoreNotOpenException recordStoreNotOpenException) {
                    recordStoreNotOpenException.printStackTrace();
                }
                catch (RecordStoreException recordStoreException) {
                    recordStoreException.printStackTrace();
                }
                throw throwable;
            }
            try {
                string.closeRecordStore();
                return;
            }
            catch (RecordStoreNotOpenException recordStoreNotOpenException) {
                recordStoreNotOpenException.printStackTrace();
                return;
            }
            catch (RecordStoreException recordStoreException) {
                recordStoreException.printStackTrace();
            }
        }
    }

    public static int a(String string, Font font) {
        int n2 = 0;
        int n3 = 0;
        while (n3 < string.length()) {
            char c2 = string.charAt(n3);
            n2 += font.charWidth(c2);
            ++n3;
        }
        return n2;
    }

    public static int[] a(Graphics graphics, String string, Font font, int n2, int n3, int n4, int n5, int n6, int n7) {
        int n8 = 0;
        int n9 = 0;
        while (true) {
            int n10;
            String string2;
            block6: {
                int n11 = n2;
                Font font2 = font;
                string2 = string;
                int n12 = 0;
                int n13 = 0;
                while (n13 < string2.length()) {
                    char c2 = string2.charAt(n13);
                    if (c2 == '\n') {
                        n10 = n13 + 1;
                        break block6;
                    }
                    if ((n12 += font2.charWidth(c2)) > n11) {
                        n10 = n13;
                        break block6;
                    }
                    ++n13;
                }
                n10 = n7 = 0;
            }
            if (n10 == 0) break;
            string2 = string.charAt(n7 - 1) == '\n' ? string.substring(0, n7 - 1) : string.substring(0, n7);
            if (n8 >= n4 && n6 < n3) {
                graphics.drawString(string2, 5, n6, 0);
                n6 = n6 + font.getHeight() + 5;
            }
            if (n6 >= n3) {
                ++n9;
            }
            ++n8;
            string = string.substring(n7, string.length());
        }
        graphics.drawString(string, 5, n6, 0);
        return new int[]{n6, n9};
    }
}

