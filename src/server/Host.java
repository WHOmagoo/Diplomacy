package server;

import java.awt.Point;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import map.Country;
import map.TileType;

/**
 * Created by Hugh on 6/10/2015.
 */
public class Host {
    static ServerSocket socketGetter;
    static Socket sock;
    static ObjectOutputStream sendMessage;

    public static void main(String[] args) throws IOException {
        System.out.println("Starting Server");
        socketGetter = new ServerSocket(25565);
        System.out.println("Waiting for connection");
        sock = socketGetter.accept();
        sendMessage = new ObjectOutputStream(sock.getOutputStream());
        sendMessage.writeObject(new Country("BOb", new Point(50, 55), TileType.Water));
        System.out.println("Message Sent");
    }
}
