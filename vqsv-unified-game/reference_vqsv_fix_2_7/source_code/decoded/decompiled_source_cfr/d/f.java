/*
 * Decompiled with CFR 0.152.
 */
package d;

import d.d;
import d.g;
import d.h;
import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class f {
    public int[] a;
    public Object[] b;
    public f[] c;
    public int d;
    public boolean e;
    private String h;
    public int f;
    public int g;

    public f() {
    }

    private f(DataInputStream dataInputStream, String string) {
        this.h = d.f.a(dataInputStream);
        if (this.h == null) {
            this.h = string;
        }
        dataInputStream.readInt();
        dataInputStream.readInt();
        this.f = dataInputStream.read();
        this.d = dataInputStream.read();
        int n2 = dataInputStream.read();
        this.e = (n2 & 2) != 0;
        this.g = dataInputStream.read();
        n2 = dataInputStream.readInt();
        this.a = new int[n2];
        int n3 = 0;
        while (n3 < n2) {
            this.a[n3] = dataInputStream.readInt();
            ++n3;
        }
        n3 = dataInputStream.readInt();
        this.b = new Object[n3];
        n2 = 0;
        while (n2 < n3) {
            Object object = null;
            int n4 = dataInputStream.read();
            switch (n4) {
                case 0: {
                    break;
                }
                case 1: {
                    object = dataInputStream.read() == 0 ? Boolean.FALSE : Boolean.TRUE;
                    break;
                }
                case 3: {
                    object = d.g.a(dataInputStream.readInt());
                    break;
                }
                case 4: {
                    object = d.f.a(dataInputStream);
                    break;
                }
                default: {
                    throw new IOException("unknown constant type: " + n4);
                }
            }
            this.b[n2] = object;
            ++n2;
        }
        n2 = dataInputStream.readInt();
        this.c = new f[n2];
        int n5 = 0;
        while (n5 < n2) {
            this.c[n5] = new f(dataInputStream, this.h);
            ++n5;
        }
        dataInputStream.readInt();
        dataInputStream.readInt();
        dataInputStream.readInt();
    }

    public final String toString() {
        return this.h;
    }

    private static String a(DataInputStream dataInputStream) {
        int n2 = dataInputStream.readShort();
        if (n2 == 0) {
            return "";
        }
        d.f.a(n2 < 65536, "Too long str: " + n2);
        byte[] byArray = new byte[n2];
        int n3 = 0;
        int n4 = n2;
        int n5 = 0;
        while (n5 < 100 && n4 > 0) {
            n2 = dataInputStream.read(byArray, n3, n4);
            n3 += n2;
            n4 -= n2;
            ++n5;
        }
        d.f.a(n4 == 0, "strload");
        return new String(byArray, "utf-8");
    }

    private static void a(boolean bl, String string) {
        if (!bl) {
            throw new IOException("Couldn't load bytecode:" + string);
        }
    }

    public static d a(InputStream object, h h2) {
        if (!(object instanceof DataInputStream)) {
            object = new DataInputStream((InputStream)object);
        }
        d.f.a(((FilterInputStream)(object = (DataInputStream)object)).read() == 27, "Signature 1");
        ((FilterInputStream)object).read();
        ((FilterInputStream)object).read();
        ((FilterInputStream)object).read();
        ((FilterInputStream)object).read();
        ((FilterInputStream)object).read();
        ((FilterInputStream)object).read();
        ((FilterInputStream)object).read();
        ((FilterInputStream)object).read();
        ((FilterInputStream)object).read();
        ((FilterInputStream)object).read();
        ((FilterInputStream)object).read();
        object = new f((DataInputStream)object, null);
        return new d((f)object, h2);
    }
}

