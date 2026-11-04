/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Display
 *  javax.microedition.lcdui.Displayable
 *  javax.microedition.midlet.MIDlet
 */
package game;

import game.m;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

public class GameMIDLet
extends MIDlet {
    private Display b;
    private m c;
    public static GameMIDLet a;

    public GameMIDLet() {
        a = this;
        this.b = Display.getDisplay((MIDlet)this);
        this.c = m.a(this);
        this.b.setCurrent((Displayable)this.c);
    }

    public void startApp() {
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean bl) {
        this.c = null;
        System.gc();
        if (bl) {
            this.notifyDestroyed();
        }
    }
}

