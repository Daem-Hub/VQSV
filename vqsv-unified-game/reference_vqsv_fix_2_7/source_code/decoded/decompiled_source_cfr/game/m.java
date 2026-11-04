/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Canvas
 *  javax.microedition.lcdui.Graphics
 */
package game;

import a.a;
import a.d;
import a.e;
import game.GameMIDLet;
import game.f;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public final class m
extends Canvas
implements Runnable {
    private static m b = null;
    private static GameMIDLet c;
    private static d d;
    private f e;
    public static m a;
    private long f = 0L;
    private long g = 0L;
    private long h = 0L;
    private int i = 0;

    public static m a(GameMIDLet gameMIDLet) {
        if (b == null) {
            b = new m(gameMIDLet);
        }
        return b;
    }

    public static m a() {
        return b;
    }

    private m(GameMIDLet object) {
        a = this;
        this.setFullScreenMode(true);
        c = object;
        a.a.a(66);
        a.a.a((short)this.getWidth(), (short)this.getHeight());
        this.e = game.f.B();
        this.e.C();
        object = this.e;
        if (d != null) {
            d.d(false);
            d = null;
        }
        if (object != null) {
            object.d(true);
            d = object;
        }
        new Thread(this).start();
    }

    protected final void hideNotify() {
        if (!a.a.e && this.e.D() > 1) {
            this.e.E();
        }
    }

    protected final void paint(Graphics graphics) {
        if (this.e.D() > 1) {
            this.e.a(graphics);
        }
    }

    public final void run() {
        while (this.e.D() > 1) {
            this.f = System.currentTimeMillis();
            this.e.a();
            this.repaint();
            this.serviceRepaints();
            this.g = System.currentTimeMillis();
            this.h = this.g - this.f;
            if (this.h > (long)a.a.k()) {
                this.h = a.a.k();
            }
            try {
                Thread.sleep((long)a.a.k() - this.h);
            }
            catch (InterruptedException interruptedException) {}
        }
        c.destroyApp(true);
    }

    protected final void keyPressed(int n2) {
        if (this.e != null) {
            this.e.j(n2);
        }
    }

    protected final void keyReleased(int n2) {
        if (this.e != null) {
            this.e.k(n2);
        }
    }

    protected final void pointerPressed(int n2, int n3) {
        this.i = 0;
        if (this.e.D() == 13) {
            if (a.e.a(n2, n3, 38, 225, 50, 40)) {
                this.i = -6;
            } else if (a.e.a(n2, n3, 150, 225, 50, 40)) {
                this.i = -7;
            }
        } else if (a.e.a(n2, n3, 0, 280, 40, 40)) {
            this.i = -6;
        } else if (a.e.a(n2, n3, 200, 280, 40, 40)) {
            this.i = -7;
        }
        this.keyPressed(this.i);
        if (this.e != null) {
            this.e.c(n2, n3);
        }
    }

    protected final void pointerReleased(int n2, int n3) {
        this.keyReleased(this.i);
        if (this.e != null) {
            this.e.d(n2, n3);
        }
    }
}

