package menu.main.actions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import menu.main.MainMenu;
import menu.main.MenuButton;

public class Unimplemented implements ActionListener, Runnable {
    MainMenu menu;

    public Unimplemented(MainMenu menu) {
        this.menu = menu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Thread display = new Thread(this);
        display.start();
    }

    @Override
    public void run() {
        MenuButton disclaimer = new MenuButton("So far this has been unimplemented");
        disclaimer.setMaximumSize(new Dimension(400, 50));
        disclaimer.setRolloverEnabled(false);
        disclaimer.setBackground(Color.RED);
        disclaimer.setForeground(Color.BLACK);
        disclaimer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        menu.add(disclaimer);
        menu.revalidate();
        try {
            Thread.sleep(4567);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        menu.remove(disclaimer);
        menu.revalidate();
        menu.repaint();
    }
}
