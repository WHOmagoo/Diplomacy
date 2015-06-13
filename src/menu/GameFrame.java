/**
 * GameFrame.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import menu.main.actions.Quit;

public class GameFrame extends JFrame {

    public GameFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage("files\\Spain\\spainarmy.png"));
        getContentPane().setBackground(new Color(204, 132, 63));
        setTitle("Diplomacy");
        addQuit();
        addMinimize();
    }

    //Adds the quit button to the JFrame which does not need to be accessible by other classes.
    private void addQuit() {
        JButton quit = new JButton(new ImageIcon("files\\x.png"));
        quit.setLayout(null);
        quit.setBounds(getWidth() - 21, 0, 21, 21);
        quit.setRolloverEnabled(true);
        quit.setBorder(null);
        quit.setOpaque(false);
        quit.setContentAreaFilled(false);
        quit.setRolloverIcon(new ImageIcon("files\\xrollover.png"));
        quit.addActionListener(new Quit());
        add(quit, 0);
    }

    //Adds the minimize button to the JFrame which does not need to be accessible by other classes.
    private void addMinimize() {

        JButton minimize = new JButton(new ImageIcon("files\\-.png"));
        minimize.setBounds(getWidth() - 42, 0, 21, 21);
        minimize.setRolloverEnabled(true);
        minimize.setBorder(null);
        minimize.setOpaque(false);
        minimize.setContentAreaFilled(false);
        minimize.setRolloverIcon(new ImageIcon("files\\-rollover.png"));
        minimize.addActionListener(new ActionListener() {
                                       public void actionPerformed(ActionEvent e) {
                                           setState(Frame.ICONIFIED);
                                       }
                                   }
        );

        add(minimize, 0);
    }

    /**
     * Adds a JComponent that is centered on the screen vertically and horizontally
     * Parameters
     * component - the JComponent to add
     */
    public void addComponentCentered(JComponent component) {
        int x = this.getWidth() / 2 - component.getWidth() / 2;
        int y = this.getHeight() / 2 - component.getHeight() / 2;
        component.setLocation(x, y);
        add(component);
    }

    /**
     * Removes everything from the JFrame except the exit and minimize buttons which should always
     * be on the JFrame
     */
    public void removeAll() {
        for (int i = 2; i < getContentPane().getComponentCount(); i++) {
            getContentPane().remove(i);
        }
    }
}
