package server;

import command.order.Order;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Hugh on 6/11/2015.
 */
public class ServerConnection implements Runnable {
    ObjectOutputStream sendMessage;
    ObjectInputStream receiveMessage;
    Socket socket;
    int id;
    private volatile boolean ordersEntered = false;

    public ServerConnection(ServerSocket serverSocket) throws IOException {
        System.out.println("Waiting for connection");
        socket = serverSocket.accept();
        System.out.println("User connected");
        try {
            receiveMessage = new ObjectInputStream(socket.getInputStream());
            System.out.println("Reading id");
            id = receiveMessage.readInt();
            System.out.println("Id read, it is " + id);
            sendMessage = new ObjectOutputStream(socket.getOutputStream());
            sendMessage.writeInt(id);
            sendMessage.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread checkForOrders = new Thread(this);
        checkForOrders.run();
    }

    @Override
    public void run() {
        try {
            ArrayList<Order> orders = (ArrayList<Order>) receiveMessage.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }
}