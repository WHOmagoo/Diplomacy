

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

    /**
     * This is the constructor.
     * Parameters
     * menu - the MainMenu item to add the notification to
     */
    public Unimplemented(MainMenu menu) {
        this.menu = menu;
    }

    /**
     * This is the method that runs when a button is clicked and has added this
     * class as ActionListener. It starts a new thread to add the notification.
     * Parameters
     * e - the ActionEvent that triggered it
     * Outputs
     * displays a notification that the button that added it has not yet been implemented.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Thread display = new Thread(this);
        display.start();
    }

    /**
     * This is the run method for the thread created above, here the notification
     * is shown and waits almost 5 seconds before removing it.
     * Outputs
     * displays a notification that the button that added it has not yet been implemented.
     */
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
