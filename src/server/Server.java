package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

/**
 * Created by Hugh on 6/10/2015.
 */
public class Server {
    static ServerSocket serverSocket;
    static ArrayList<ServerConnection> connections = new ArrayList<ServerConnection>();

    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(25565);
        for (int i = 0; i < 10; i++) {
            ServerConnection connection = new ServerConnection(serverSocket);
            if (!connectionContains(connection)) {
                connections.add(connection);
            } else {
                System.out.println("Two connection with same id");
            }
        }
    }

    public static boolean connectionContains(ServerConnection newConnection) {
        for (ServerConnection connection : connections) {
            if (connection.getId() == newConnection.getId()) {
                return true;
            }
        }

        return false;
    }
}
