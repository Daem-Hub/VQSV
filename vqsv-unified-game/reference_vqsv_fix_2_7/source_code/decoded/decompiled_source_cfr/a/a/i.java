/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.media.Player
 *  javax.microedition.media.PlayerListener
 */
package a.a;

import a.a.e;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;

final class i
implements PlayerListener {
    private final e a;

    i(e e2) {
        this.a = e2;
    }

    public final void playerUpdate(Player player, String string, Object object) {
        if (e.a(this.a)[0][1] != 0) {
            if (string.equals("deviceUnavailable")) {
                try {
                    player.stop();
                    return;
                }
                catch (Exception exception) {
                    return;
                }
            }
            if (string.equals("deviceAvailable") || string.equals("volumeChanged")) {
                try {
                    player.start();
                    return;
                }
                catch (Exception exception) {}
            }
        }
    }
}

