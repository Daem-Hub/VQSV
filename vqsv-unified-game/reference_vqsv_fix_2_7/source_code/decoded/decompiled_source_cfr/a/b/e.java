/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.rms.RecordStore
 */
package a.b;

import java.io.ByteArrayOutputStream;
import javax.microedition.rms.RecordStore;

public final class e {
    private String a;
    private RecordStore b;
    private int c;
    private int d = 1;

    public e(String string, int n2) {
        this.a = string;
        if (this.b() == 0) {
            this.b(1);
            return;
        }
        if (this.b() != 1) {
            this.a();
            this.b(1);
        }
    }

    private void b(int n2) {
        try {
            byte[] byArray = new byte[]{0};
            for (int i2 = 0; i2 < n2; ++i2) {
                int n3 = byArray.length;
                byte[] byArray2 = byArray;
                e e2 = this;
                e2.a(e2.d, byArray2, 0, n3, 1);
            }
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    private int b() {
        this.a(this.d, null, 0, 0, 0);
        return this.c;
    }

    public final byte[] a(int n2) {
        byte[] byArray = this.a(1, null, 0, 0, 3);
        byte[] byArray2 = new byte[byArray.length - 1];
        System.arraycopy(byArray, 1, byArray2, 0, byArray2.length);
        this.c();
        return byArray2;
    }

    public final void a(ByteArrayOutputStream object) {
        byte[] byArray = ((ByteArrayOutputStream)object).toByteArray();
        object = byArray;
        byte[] byArray2 = new byte[byArray.length + 1];
        System.arraycopy(object, 0, byArray2, 1, ((Object)object).length);
        byArray2[0] = 1;
        int n2 = byArray2.length;
        object = this;
        ((e)object).a(((e)object).d, byArray2, 0, n2, 4);
        this.c();
    }

    private void c() {
        this.a(0, null, 0, 0, 6);
    }

    public final void a() {
        this.a(0, null, 0, 0, 7);
    }

    private byte[] a(int n2, byte[] byArray, int n3, int n4, int n5) {
        try {
            if (n5 == 7 || n5 == 6) {
                if (this.b != null) {
                    this.b.closeRecordStore();
                    this.b = null;
                }
                if (n5 == 7) {
                    RecordStore.deleteRecordStore((String)this.a);
                }
                return null;
            }
            if (this.b == null) {
                this.b = RecordStore.openRecordStore((String)this.a, (boolean)true);
            }
            switch (n5) {
                case 0: {
                    this.c = this.b.getNumRecords();
                    break;
                }
                case 1: {
                    this.b.addRecord(byArray, 0, n4);
                    break;
                }
                case 2: {
                    this.b.deleteRecord(n2);
                    break;
                }
                case 3: {
                    return this.b.getRecord(n2);
                }
                case 4: {
                    this.b.setRecord(n2, byArray, 0, n4);
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}

