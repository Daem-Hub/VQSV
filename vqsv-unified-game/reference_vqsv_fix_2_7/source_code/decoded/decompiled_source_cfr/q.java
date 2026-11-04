/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Canvas
 *  javax.microedition.lcdui.Display
 *  javax.microedition.lcdui.Displayable
 *  javax.microedition.lcdui.Font
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.midlet.MIDlet
 */
import a.f;
import a.g;
import a.h;
import java.io.InputStream;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

public final class q
extends Canvas
implements Runnable {
    private static int a = 240;
    private static int b = 320;
    private int[][] c = new int[4][4];
    private static final String[] d = new String[]{"Nokia", "Motorokr", "Motorola", "SonyEricsson", "Samsung", "j2me", "SunMicrosystems_wtk", "MX6", "MotoA668"};
    private String e = null;
    private v f;
    private String g = null;
    private MIDlet h = null;
    private Displayable i;
    private int j = 0;
    private int k = 0;
    private String l = "G\u1eedi tin";
    private String m = "Ph\u1ea3n h\u1ed3i";
    private String n = null;
    private String o = null;
    private String p = null;
    private String q = null;
    private int r = 0;
    private String s = null;
    private String t = null;
    private String u = "\u0110\u1eb7t h\u00e0ng";
    private c v = null;
    private int w = -1;

    public q(MIDlet object, Displayable object2, String string, String string2, String string3, int n2, String string4, String string5, String string6) {
        this.setFullScreenMode(true);
        try {
            this.h = object;
            this.i = object2;
            this.n = string;
            this.o = string2;
            if (string3 == null || string3.length() == 0) {
                string3 = "00";
            }
            object2 = "000";
            this.p = string3;
            this.q = object2;
            this.r = n2;
            this.s = string4;
            this.t = string5;
            this.a((MIDlet)object);
            a = this.getWidth();
            b = this.getHeight();
            this.a();
            object = this;
            this.l = "G\u1eedi tin";
            ((q)object).m = "Ph\u1ea3n h\u1ed3i";
            object2 = new v();
            ((v)object2).c(u.a);
            ((v)object2).d(u.b);
            ((v)object2).a(2);
            ((v)object2).c(0);
            ((v)object2).e("eeee");
            ((v)object2).b(1);
            ((v)object2).a("");
            ((v)object2).b("");
            ((v)object2).a(false);
            ((q)object).f = object2;
            return;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
    }

    protected final void sizeChanged(int n2, int n3) {
        super.sizeChanged(n2, n3);
        a = this.getWidth();
        b = this.getHeight();
        this.a();
    }

    private void a() {
        Font font = Font.getFont((int)0, (int)1, (int)0);
        int n2 = font.getHeight() + 4;
        int n3 = z.a("G\u1eedi tin", font) + 4;
        this.c[0][0] = 2;
        this.c[0][1] = b - n2;
        this.c[0][2] = n3;
        this.c[0][3] = b;
        this.c[1][0] = a - n3;
        this.c[1][1] = b - n2;
        this.c[1][2] = a;
        this.c[1][3] = b;
        this.c[2][0] = a / 2 - (n3 <<= 1);
        this.c[2][1] = b - n2;
        this.c[2][2] = a / 2;
        this.c[2][3] = b;
        this.c[3][0] = a / 2;
        this.c[3][1] = b - n2;
        this.c[3][2] = a / 2 + n3;
        this.c[3][3] = b;
    }

    private void a(MIDlet mIDlet) {
        String string = null;
        try {
            string = mIDlet.getAppProperty("Platform");
            if (string == null) {
                string = System.getProperty("microedition.platform");
            }
        }
        catch (Exception exception) {}
        if (string != null) {
            string = string.toLowerCase();
            String string2 = null;
            int n2 = 0;
            while (n2 < d.length) {
                string2 = d[n2].toLowerCase();
                if (string.length() >= string2.length() && string.startsWith(string2)) break;
                ++n2;
            }
            this.e = string2;
        }
    }

    private void b() {
        if (this.f.j() == 0) {
            if (this.f != null) {
                this.a(this.t);
            }
            return;
        }
        this.c();
    }

    private void c() {
        if (this.f.h()) {
            this.a("H\u00e3y nh\u1ea5n x\u00e1c nh\u1eadn \u0111\u1ec3 g\u1eedi tin nh\u1eafn");
            return;
        }
        int n2 = this.f.a();
        if (this.f.e() != null && this.f.e().length() != 0) {
            ++n2;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("B\u1ea1n \u0111\u00e3 g\u1eedi " + this.f.j() + " tin nh\u1eafn, c\u00f2n " + (n2 - this.f.j()) + " tin");
        this.a(stringBuffer.toString());
    }

    public final void run() {
        if (this.f.f() != null && this.f.e().length() != 0 && this.f.e() != null && this.f.e().length() != 0 && (this.f.k() == 0L || this.f.h() && this.f.j() % 2 == 0)) {
            this.f.a(System.currentTimeMillis());
            this.f.d(this.f.j() + 1);
        }
        g g2 = new g();
        Object object = g2;
        object = g2.a.a;
        ((h)object).a((Object)"ca", (Object)new r(this, 0));
        ((h)object).a((Object)"gi", (Object)new r(this, 1));
        ((h)object).a((Object)"gs", (Object)new r(this, 2));
        ((h)object).a((Object)"gg", (Object)new r(this, 3));
        ((h)object).a((Object)"gj", (Object)new r(this, 4));
        ((h)object).a((Object)"yc", (Object)new r(this, 5));
        ((h)object).a((Object)"m", (Object)new r(this, 6));
        ((h)object).a((Object)"n", (Object)new r(this, 7));
        ((h)object).a((Object)"as", (Object)new r(this, 8));
        ((h)object).a((Object)"_fc", (Object)new r(this, 9));
        ((h)object).a((Object)"_fb", (Object)new r(this, 10));
        ((h)object).a((Object)"ts", (Object)new r(this, 11));
        ((h)object).a((Object)"aa", (Object)new r(this, 12));
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/data/event/scene_13.mib");
            object = g2;
            object = a.f.a(inputStream, ((g)object).a.a);
            inputStream.close();
            g2.a(object, null);
            return;
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            return;
        }
    }

    private void a(String string) {
        this.j = 0;
        this.g = string;
        this.repaint();
    }

    private void d() {
        if (this.v != null) {
            this.v.a(this.f.i() >= this.r);
        }
    }

    /*
     * Unable to fully structure code
     */
    protected final void keyPressed(int var1_1) {
        var2_4 = var1_1;
        var1_2 = this;
        if (!"Motorola".equals(var1_2.e)) ** GOTO lbl-1000
        switch (var2_4) {
            case -21: 
            case 21: {
                v0 = -6;
                break;
            }
            case -22: 
            case 22: {
                v0 = -7;
                break;
            }
            case -6: {
                v0 = 56;
                break;
            }
            default: lbl-1000:
            // 2 sources

            {
                v0 = var2_4;
            }
        }
        var1_3 = v0;
        switch (v0) {
            case -202: 
            case -21: 
            case -6: 
            case 21: {
                this.g();
                break;
            }
            case -203: 
            case -22: 
            case -7: 
            case 22: {
                this.h();
                break;
            }
            case 50: {
                this.e();
                break;
            }
            case 56: {
                this.f();
                break;
            }
            default: {
                System.out.println(var1_3);
            }
        }
        switch (this.getGameAction(var1_3)) {
            case 1: {
                this.e();
                return;
            }
            case 6: {
                this.f();
                return;
            }
        }
    }

    private boolean a(int n2, int n3, int n4) {
        return n2 > this.c[n4][0] && n2 < this.c[n4][2] && n3 > this.c[n4][1] && n3 < this.c[n4][3];
    }

    protected final void pointerPressed(int n2, int n3) {
        if (this.a(n2, n3, 0)) {
            this.g();
            return;
        }
        if (this.a(n2, n3, 1)) {
            this.h();
            return;
        }
        if (this.a(n2, n3, 2)) {
            if (this.w == 0 || this.w == 1) {
                this.e();
                return;
            }
        } else if (this.a(n2, n3, 3) && (this.w == 0 || this.w == 2)) {
            this.f();
        }
    }

    protected final void keyRepeated(int n2) {
        super.keyRepeated(n2);
        this.keyPressed(n2);
    }

    private void e() {
        --this.j;
        if (this.j <= 0) {
            this.j = 0;
        }
        this.repaint();
    }

    private void f() {
        if (this.k + 5 > 0) {
            ++this.j;
        }
        this.repaint();
    }

    private void g() {
        if ("G\u1eedi tin".equals(this.l) && this.f.a() > 0) {
            this.l = "";
            this.m = "";
            new Thread(this).start();
            return;
        }
        if ("X\u00e1c nh\u1eadn".equals(this.l)) {
            this.l = "G\u1eedi tin";
            this.m = "Quay l\u1ea1i";
            this.b();
        }
    }

    private void h() {
        System.out.println("payed=" + this.f.i());
        System.out.println("count=" + this.r);
        if ("Quay l\u1ea1i".equals(this.m)) {
            if (this.f.i() < this.r) {
                this.l = "X\u00e1c nh\u1eadn";
                this.m = "Tho\u00e1t";
                this.a("B\u1ea1n ch\u01b0a tr\u1ea3 ti\u1ec1n xong, \u0111\u1ec1 ngh\u1ecb ti\u1ebfp t\u1ee5c tr\u1ea3 ti\u1ec1n.");
                return;
            }
            this.d();
            Display.getDisplay((MIDlet)this.h).setCurrent(this.i);
            return;
        }
        if ("Tho\u00e1t".equals(this.m)) {
            this.d();
            Display.getDisplay((MIDlet)this.h).setCurrent(this.i);
        }
    }

    public final void paint(Graphics graphics) {
        Font.getDefaultFont();
        Font font = Font.getFont((int)0, (int)1, (int)0);
        graphics.setColor(4423868);
        graphics.fillRect(0, 0, a, b);
        graphics.setColor(255, 102, 0);
        graphics.fillRect(0, 0, a, 30);
        graphics.setColor(0xFFFFFF);
        int n2 = (30 - font.getHeight()) / 2;
        graphics.drawString(this.u, 2, n2, 0);
        graphics.setFont(font);
        int n3 = font.getHeight() + 8;
        int[] nArray = z.a(graphics, this.g, font, a, b - n3, this.j, 35);
        this.k = nArray[1];
        graphics.setColor(255, 102, 0);
        int n4 = b - n3;
        graphics.fillRect(0, n4, a, n3);
        graphics.setColor(0xFFFFFF);
        n4 = b - (n3 + font.getHeight()) / 2;
        graphics.drawString(this.l, 2, n4, 0);
        int n5 = a - z.a(this.m, font) - 2 - 2;
        graphics.drawString(this.m, n5, n4, 0);
        n5 = b - n3 / 2;
        this.w = -1;
        if (this.j > 0 && this.k > 0) {
            this.w = 0;
            n4 = n5 + 5;
            graphics.fillTriangle(a / 2 - 10 - 3, n4, a / 2 - 10 + 3, n4, a / 2 - 10, n4 - 11);
            graphics.fillTriangle(a / 2 + 10 - 3, n5 -= 5, a / 2 + 10 + 3, n5, a / 2 + 10, n5 + 11);
            return;
        }
        if (this.j > 0) {
            this.w = 1;
            graphics.fillTriangle(a / 2 - 10 - 3, n5 += 5, a / 2 - 10 + 3, n5, a / 2 - 10, n5 - 11);
            return;
        }
        if (this.k > 0) {
            this.w = 2;
            graphics.fillTriangle(a / 2 + 10 - 3, n5 -= 5, a / 2 + 10 + 3, n5, a / 2 + 10, n5 + 11);
        }
    }

    public final void a(c c2) {
        this.v = c2;
    }

    public final int a(int n2, a.c c2, int n3) {
        switch (n2) {
            case 0: {
                return 0;
            }
            case 1: {
                if (n3 == 0) {
                    c2.a(new Integer(this.f.i()));
                    return 1;
                }
                this.f.c((Integer)c2.a(0));
                return 0;
            }
            case 2: {
                c2.a(new Integer(this.r));
                return 1;
            }
            case 3: {
                c2.a(new Integer(this.f.d()));
                return 1;
            }
            case 4: {
                if (n3 == 0) {
                    c2.a(new Integer(this.f.j()));
                    return 1;
                }
                this.f.d((Integer)c2.a(0));
                return 0;
            }
            case 5: {
                ao.a(this.f, this.n, this.o, this.p, this.q, this.r);
                return 0;
            }
            case 6: {
                this.l = (String)c2.a(0);
                return 0;
            }
            case 7: {
                this.m = (String)c2.a(0);
                return 0;
            }
            case 8: {
                this.a((String)c2.a(0));
                return 0;
            }
            case 9: {
                c2.a(u.a);
                return 1;
            }
            case 10: {
                c2.a(u.b);
                return 1;
            }
            case 11: {
                c2.a(this.s);
                return 1;
            }
            case 12: {
                ((Boolean)c2.a(0)).booleanValue();
                this.c();
                return 0;
            }
        }
        return 0;
    }
}

