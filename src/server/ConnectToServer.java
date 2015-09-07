package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Hugh on 9/4/2015.
 */
public class ConnectToServer {
    private ObjectOutputStream sendMessage;
    private ObjectInputStream receiveMessage;
    private Socket socket;

    public ConnectToServer() throws IOException {
        try {
            socket = new Socket("localhost", 25565);
        } catch (IOException e) {
            System.out.println("Could not find server on selected port");
        }
        try {
            sendMessage = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        sendMessage.writeInt(2012);
        sendMessage.flush();
    }

}
