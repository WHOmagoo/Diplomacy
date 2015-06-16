/**
 * PlayGame.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package menu.main.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import map.Map;
import menu.GameFrame;
import menu.main.MapCreation;

/**
 * Created by Hugh on 6/12/2015.
 */
public class PlayGame implements ActionListener {
    GameFrame container;

    /**
     * This is the ActionListener class for the PlayGame button.
     * Parameters
     * container - the GameFrame to clear and then add the Map to.
     */
    public PlayGame(GameFrame container) {
        this.container = container;
    }

    /**
     * This is the action performed for the buttons that have added it.
     * Parameters
     * e - the ActionEvent that triggered this method call
     * Outputs
     * Creates a new Map from MapCreation.createMap() and displays it to the GameFrame
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        container.removeAll();
        container.repaint();
        Map map = MapCreation.createMap();
        container.addComponentCentered(map);
    }
}
