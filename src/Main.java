/**
 * Main.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

import menu.GameFrame;
import menu.main.MainMenu;

public class Main {
    public static GameFrame frame;

    //This is my main method, it is intended to start everything,
    public static void main(String[] args) {
        frame = new GameFrame();
        MainMenu mainMenu = new MainMenu(frame);
        frame.addComponentCentered(mainMenu);
        frame.setVisible(true);
    }

/**
 * To write an object to file use ObjectOutputStream and FileOutputStream,
 *  I will use this later for saving your game.
 */
}
