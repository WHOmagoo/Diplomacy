package server;

import command.order.Order;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Hugh on 9/4/2015.
 */
public class ConnectToServer {
    private static ObjectOutputStream sendMessage;
    private static ObjectInputStream receiveMessage;
    private static Socket socket;

    public static void ConnectToServer() throws IOException {
        try {
            socket = new Socket("localhost", 25565);
            try {
                sendMessage = new ObjectOutputStream(socket.getOutputStream());

                sendMessage.writeInt(2012);
                sendMessage.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Could not find server on selected port");
        }
    }

    public static boolean sendOrder(Order order) {
        try {
            sendMessage.writeObject(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
