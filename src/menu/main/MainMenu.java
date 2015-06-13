/**
 * MainMenu.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package menu.main;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import menu.GameFrame;
import menu.main.actions.PlayGame;
import menu.main.actions.Quit;
import menu.main.actions.Unimplemented;

public class MainMenu extends JPanel {

    /**
     * The constructor for this class
     * Parameters
     *   container - this is to add objects to it on a button push and set things to the height
     *               of the GameFrame
     */
    public MainMenu(GameFrame container) {
        new JPanel();
        setSize(1024, container.getHeight());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(null);

        JPanel buttonHolder = new JPanel();
        buttonHolder.setBackground(null);
        buttonHolder.setLayout(new GridLayout(2, 2, 10, 10));
        buttonHolder.setMaximumSize(new Dimension(400, 110));
        buttonHolder.setMinimumSize(new Dimension(300, 60));
        buttonHolder.add(new MenuButton("New Single Player Game", new PlayGame(container)));
        buttonHolder.add(new MenuButton("Load Single Player Game", new Unimplemented(this)));
        buttonHolder.add(new MenuButton("Join Server", new Unimplemented(this)));
        buttonHolder.add(new MenuButton("Host Server", new Unimplemented(this)));

        MenuButton quit = new MenuButton("Quit", new Quit());
        quit.setMaximumSize(new Dimension(400, 50));

        add(Box.createVerticalGlue());
        add(new Diplomacy());
        add(Box.createVerticalGlue());
        add(buttonHolder);
        add(Box.createVerticalStrut(15));
        add(quit);
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
    }
}
