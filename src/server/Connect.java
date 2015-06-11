package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by Hugh on 6/10/2015.
 */
public class Connect {
    static Socket port;
    static ObjectInputStream messageIn;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        port = new Socket("localhost", 25565);
        messageIn = new ObjectInputStream(port.getInputStream());
        System.out.println(messageIn.readObject().toString());
    }
}
