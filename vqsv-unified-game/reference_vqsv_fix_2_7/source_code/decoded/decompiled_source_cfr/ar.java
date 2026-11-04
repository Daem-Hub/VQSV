/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.rms.RecordStore
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import javax.microedition.rms.RecordStore;

public final class ar {
    private String c;
    private RecordStore d;
    private int e;
    private int f = 1;
    public boolean a;
    public byte[] b;

    public ar(String string) {
        this.c = string;
        if (this.c() == 0) {
            this.a(1);
            return;
        }
        if (this.c() != 1) {
            this.b();
            this.a(1);
        }
    }

    private void a(int n2) {
        try {
            byte[] byArray = new byte[]{0};
            for (int i2 = 0; i2 <= 0; ++i2) {
                int n3 = byArray.length;
                byte[] byArray2 = byArray;
                ar ar2 = this;
                ar2.a(ar2.f, byArray2, 0, n3, 1);
            }
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    private int c() {
        this.a(this.f, null, 0, 0, 0);
        return this.e;
    }

    public final byte[] a() {
        this.b = null;
        Object object = this.a(1, null, 0, 0, 3);
        if (object != null && ((byte[])object).length >= 3 && object[1] == 44 && object[2] == 79) {
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream((byte[])object);
                DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                object = dataInputStream;
                dataInputStream.readByte();
                object.readShort();
                int n2 = object.readInt();
                byte[] byArray = new byte[n2];
                object.read(byArray);
                if (object.available() > 0) {
                    this.b = new byte[object.available()];
                    object.read(this.b);
                }
                this.d();
                return byArray;
            }
            catch (Exception exception) {
                Exception exception2 = exception;
                exception.printStackTrace();
                return null;
            }
        }
        byte[] byArray = new byte[((byte[])object).length - 1];
        System.arraycopy(object, 1, byArray, 0, byArray.length);
        this.d();
        return byArray;
    }

    public final void a(ByteArrayOutputStream object) {
        byte[] byArray = ((ByteArrayOutputStream)object).toByteArray();
        if (!this.a) {
            object = new byte[byArray.length + 1];
            System.arraycopy(byArray, 0, object, 1, byArray.length);
            object[0] = true;
            int n2 = ((Object)object).length;
            this.a(this.f, (byte[])object, 0, n2, 4);
            this.d();
            return;
        }
        this.b = byArray;
    }

    private void d() {
        this.a(0, null, 0, 0, 6);
    }

    public final void b() {
        this.a(0, null, 0, 0, 7);
    }

    private byte[] a(int n2, byte[] byArray, int n3, int n4, int n5) {
        try {
            if (n5 == 7 || n5 == 6) {
                if (this.d != null) {
                    this.d.closeRecordStore();
                    this.d = null;
                }
                if (n5 == 7) {
                    RecordStore.deleteRecordStore((String)this.c);
                }
                return null;
            }
            if (this.d == null) {
                this.d = RecordStore.openRecordStore((String)this.c, (boolean)true);
            }
            switch (n5) {
                case 0: {
                    this.e = this.d.getNumRecords();
                    break;
                }
                case 1: {
                    this.d.addRecord(byArray, 0, n4);
                    break;
                }
                case 2: {
                    this.d.deleteRecord(n2);
                    break;
                }
                case 3: {
                    return this.d.getRecord(n2);
                }
                case 4: {
                    this.d.setRecord(n2, byArray, 0, n4);
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public final void a(byte[] byArray) {
        if (this.b == null) {
            return;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeByte(1);
            dataOutputStream.writeByte(44);
            dataOutputStream.writeByte(79);
            dataOutputStream.writeInt(this.b.length);
            ((OutputStream)dataOutputStream).write(this.b);
            ((OutputStream)dataOutputStream).write(byArray);
            dataOutputStream.flush();
            this.b = null;
            this.a(this.f, byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size(), 4);
            this.d();
            return;
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            return;
        }
    }
}

