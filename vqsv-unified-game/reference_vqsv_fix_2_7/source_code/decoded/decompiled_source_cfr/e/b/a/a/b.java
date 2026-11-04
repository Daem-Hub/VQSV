/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.io.Connector
 *  javax.wireless.messaging.Message
 *  javax.wireless.messaging.MessageConnection
 *  javax.wireless.messaging.TextMessage
 */
package e.b.a.a;

import e.b.a.a.a;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.wireless.messaging.Message;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

public final class b
implements Runnable {
    private a a = null;
    private String b;
    private boolean c = false;
    private Thread d = null;

    public b() {
        Class.forName("javax.wireless.messaging.Message");
        this.a = null;
        if (this.d == null) {
            this.d = new Thread(this);
            this.d.start();
        }
    }

    public b(a a2) {
        Class.forName("javax.wireless.messaging.Message");
        this.a = a2;
        if (this.d == null) {
            this.d = new Thread(this);
            this.d.start();
        }
    }

    public final synchronized void a(String string) {
        this.b = string;
    }

    public final void run() {
        while (true) {
            b b2 = this;
            synchronized (b2) {
                block22: {
                    try {
                        this.wait();
                    }
                    catch (Exception exception) {}
                    int n2 = ((String)null).length();
                    String string = n2 >= 3 && ((String)null).substring(0, 3) == "+86" ? ((String)null).substring(3) : (n2 > 0 && ((String)null).substring(0, 1) == "+" ? ((String)null).substring(1) : null);
                    MessageConnection messageConnection = null;
                    try {
                        block21: {
                            string = this.b + string;
                            try {
                                messageConnection = (MessageConnection)Connector.open((String)string);
                                TextMessage textMessage = (TextMessage)messageConnection.newMessage("text");
                                textMessage.setAddress(string);
                                textMessage.setPayloadText(null);
                                messageConnection.send((Message)textMessage);
                                if (messageConnection == null) break block21;
                            }
                            catch (Exception exception) {
                                System.out.println(exception.getMessage());
                                break block22;
                            }
                            finally {
                                if (messageConnection != null) {
                                    messageConnection.close();
                                }
                                if (this.a != null) {
                                    this.a.b(false);
                                }
                            }
                            messageConnection.close();
                        }
                        if (this.a != null) {
                            this.a.b(true);
                        }
                    }
                    catch (IOException iOException) {
                        System.out.println(iOException.getMessage());
                    }
                    catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    finally {
                        this.c = false;
                    }
                }
            }
        }
    }
}

