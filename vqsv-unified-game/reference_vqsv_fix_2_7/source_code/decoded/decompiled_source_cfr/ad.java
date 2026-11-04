/*
 * Decompiled with CFR 0.152.
 */
import java.io.DataInputStream;

public final class ad {
    private short a;
    private short[] b;
    private String[] c;

    public final void a(DataInputStream dataInputStream, String[] stringArray) {
        int n2;
        this.a = dataInputStream.readShort();
        byte by = dataInputStream.readByte();
        int n3 = dataInputStream.readByte();
        this.b = new short[n3];
        for (n2 = 0; n2 < n3; ++n2) {
            this.b[n2] = dataInputStream.readShort();
        }
        if (stringArray != null) {
            this.c = new String[by - n3];
            for (n2 = 0; n2 < by - n3; ++n2) {
                short s2 = dataInputStream.readShort();
                this.c[n2] = stringArray[s2];
            }
        }
    }

    public final short a() {
        return this.a;
    }

    public final short[] b() {
        return this.b;
    }

    public final String[] c() {
        return this.c;
    }
}

