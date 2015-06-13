package menu.main.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import map.Map;
import menu.GameFrame;

/**
 * Created by Hugh on 6/12/2015.
 */
public class PlayGame implements ActionListener {
    GameFrame container;

    public PlayGame(GameFrame container) {
        this.container = container;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        container.removeAll();
        container.repaint();
        Map map = MapCreation.createMap();
        container.addComponentCentered(map);
    }
}
