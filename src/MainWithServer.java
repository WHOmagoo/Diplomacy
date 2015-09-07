import java.io.IOException;
import menu.GameFrame;
import menu.main.MainMenu;
import server.ConnectToServer;

/**
 * Created by Hugh on 9/6/2015.
 */
public class MainWithServer {
    public static GameFrame frame;

    public static void main(String[] args) {
        connectToServer();
        loadGame();
    }

    public static void connectToServer() {
        try {
            ConnectToServer b = new ConnectToServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadGame() {
        frame = new GameFrame();
        MainMenu mainMenu = new MainMenu(frame);
        frame.addComponentCentered(mainMenu);
        frame.setVisible(true);
    }

}
