/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
package c;

import game.n;
import javax.microedition.lcdui.Graphics;

public final class d {
    public static void a(String string, int n2, int n3, int n4, int n5, Graphics graphics) {
        graphics.setColor(n5);
        n.b(graphics, string, n2, n3, n4);
    }

    public static int a(String string, int n2, int n3) {
        return n.a(string, 0, n3 + 0);
    }
}

