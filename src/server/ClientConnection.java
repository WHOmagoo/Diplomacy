package server;

import command.order.Order;
import command.order.OrderContainer;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Hugh on 6/10/2015.
 */
public class ClientConnection implements Runnable {
    Socket port;
    ObjectOutputStream messageOut;
    ObjectInputStream messageIn;
    int id;

    public ClientConnection() {
        try {
            Scanner ipIn = new Scanner(new File("ip.txt"));
            port = new Socket(ipIn.nextLine(), 25565);
            messageOut = new ObjectOutputStream(port.getOutputStream());
            id = ipIn.nextInt();
            messageOut.writeInt(id);
            messageOut.flush();
            System.out.println("Written id");
            messageIn = new ObjectInputStream(port.getInputStream());
            System.out.println("linking up");
            System.out.println(messageIn.readInt());
            System.out.println("linked up");
            Thread checkForOrders = new Thread(this);
            checkForOrders.start();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            ArrayList<Order> allOrders = (ArrayList<Order>) messageIn.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeOrders(OrderContainer orders) {
        try {
            messageOut.writeObject(orders);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void writeOrders(Order order) {
        try {
            messageOut.writeObject(order);
            messageOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
