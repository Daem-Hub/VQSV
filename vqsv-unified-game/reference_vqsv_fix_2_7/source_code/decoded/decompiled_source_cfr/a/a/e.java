/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.media.Manager
 *  javax.microedition.media.Player
 *  javax.microedition.media.PlayerListener
 *  javax.microedition.media.control.VolumeControl
 */
package a.a;

import a.a.i;
import java.io.InputStream;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import javax.microedition.media.control.VolumeControl;

public final class e {
    private byte[] a = new byte[]{0, 20, 40, 60, 80, 100};
    private String b = "";
    private byte[][] c = new byte[][]{{2, 2, 0}, {(byte)(this.a.length - 1), (byte)(this.a.length - 1), 1}, {-1, -1, -1}, {0, 0, 0}};
    private int d = 0;
    private int e = 0;
    private Player[] f;
    private short g;
    private Player[] h;
    private static final String[] i = new String[]{"audio/x-tone-seq", "audio/midi", "audio/x-wav", "audio/amr"};
    private VolumeControl j = null;
    private byte k = 0;
    private PlayerListener l;
    private PlayerListener m = this.l = new i(this);

    public e(int n2, int n3, byte by, String string) {
        if (this.f == null) {
            this.g = (short)7;
            this.f = new Player[7];
        }
        this.k = 0;
        this.b = string;
    }

    public final void a() {
        int n2;
        if (this.f != null) {
            for (n2 = 0; n2 < this.f.length; ++n2) {
                this.f[n2] = null;
            }
        }
        if (this.h != null) {
            for (n2 = 0; n2 < this.h.length; ++n2) {
                this.h[n2] = null;
            }
        }
        this.f = null;
        this.h = null;
    }

    private static Player a(String object, int n2) {
        try {
            object.getClass();
            object = b.a((String)object);
            Player player = Manager.createPlayer((InputStream)object, (String)i[n2]);
            player.realize();
            player.prefetch();
            player.setLoopCount(-1);
            ((InputStream)object).close();
            return player;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public final void a(byte[] byArray) {
        for (int i2 = 0; i2 < byArray.length; ++i2) {
            if (byArray[i2] == -1 || this.f[byArray[i2]] != null) continue;
            this.f[byArray[i2]] = a.a.e.a(this.b + byArray[i2] + ".mid", 1);
        }
    }

    private void a(Player player, int n2, int n3, boolean bl, boolean bl2) {
        if (player != null) {
            try {
                if (this.c[0][n2] != 0) {
                    if (this.k == 1) {
                        if (bl2) {
                            player.addPlayerListener(this.l);
                        } else {
                            player.addPlayerListener(this.m);
                        }
                    }
                    player.prefetch();
                    if (player.getState() == 300) {
                        int n4 = n3;
                        player.setLoopCount(n4);
                        if (!bl) {
                            player.setMediaTime(0L);
                        }
                        this.a(player, (int)this.a[this.c[0][n2]]);
                        player.start();
                        return;
                    }
                } else {
                    player.stop();
                }
                return;
            }
            catch (Exception exception) {}
        }
    }

    private void a(Player player, boolean bl) {
        if (player != null) {
            try {
                player.stop();
                if (this.k == 1) {
                    if (bl) {
                        player.removePlayerListener(this.l);
                        return;
                    }
                    player.removePlayerListener(this.m);
                }
                return;
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private void a(Player player, int n2) {
        try {
            this.j = (VolumeControl)player.getControl("VolumeControl");
            if (n2 == 0) {
                this.a(1);
                return;
            }
            this.j.setLevel(n2);
            if (a.a.e.a(player)) {
                player.start();
            }
            return;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
    }

    public final void a(byte by) {
        if (this.f != null) {
            for (by = 0; by < this.f.length; by = (byte)(by + 1)) {
                Player player = this.f[by];
                if (player == null) continue;
                player.deallocate();
                player.close();
            }
            return;
        }
    }

    private static boolean a(Player player) {
        try {
            player.prefetch();
            if (player.getState() == 300) {
                return true;
            }
        }
        catch (Exception exception) {}
        return false;
    }

    public final void b(byte by) {
        if (by >= this.a.length) {
            by = (byte)(this.a.length - 1);
        }
        this.c[2][1] = (byte)this.d;
        this.c[0][1] = by;
        this.c[0][0] = by;
        if (this.f != null && this.f[this.d] != null) {
            this.a(this.f[this.d], (int)this.a[this.c[0][1]]);
        }
        if (this.h != null) {
            for (by = 0; by < this.h.length; by = (byte)(by + 1)) {
                if (this.h[by] == null) continue;
                this.a(this.h[by], (int)this.a[this.c[0][0]]);
            }
        }
    }

    public final void a(int n2, int n3) {
        this.e = this.d;
        this.d = n2;
        if (n2 >= 0 && n2 < this.g) {
            if (this.c[2][1] == -1 || n2 != this.c[2][1]) {
                int n4 = 1;
                e e2 = this;
                if (e2.c[0][n4] == 0) {
                    return;
                }
                this.a(1);
                if (this.e != n2 && this.f[this.e] != null) {
                    this.f[this.e] = null;
                }
                if (this.f[n2] == null) {
                    this.f[n2] = a.a.e.a(this.b + n2 + ".mid", 1);
                }
                int n5 = 1;
                int n6 = -1;
                n4 = n2;
                e2 = this;
                e2.a(n5);
                e2.c[2][n5] = (byte)n4;
                e2.c[3][n5] = (byte)n6;
                this.a(this.f[n2], 1, -1, false, true);
                return;
            }
        } else if (n2 == -2 || n2 == -1) {
            this.a(1);
            return;
        }
    }

    private void a(int n2) {
        switch (n2) {
            case 0: {
                if (this.c[2][0] < 0) break;
                this.a(this.h[this.c[2][0]], false);
                return;
            }
            case 1: {
                if (this.c[2][1] < 0) break;
                this.a(this.f[this.c[2][1]], true);
            }
        }
    }

    public final void b() {
        this.a(0);
        this.a(1);
    }

    public final void c() {
        e e2 = this;
        if (e2.c[2][1] != -1) {
            e2.a(e2.f[e2.c[2][1]], 1, e2.c[3][1], e2.k == 0, true);
        }
        if (e2.c[2][0] != -1) {
            e2.a(e2.h[e2.c[2][0]], 0, e2.c[3][0], e2.k == 0, false);
        }
    }

    static byte[][] a(e e2) {
        return e2.c;
    }
}

