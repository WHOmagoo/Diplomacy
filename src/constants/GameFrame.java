package constants;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Hugh on 5/16/2015.
 */
public class GameFrame extends JFrame {
    public GameFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage("Files\\Spain\\spainarmy.png"));
        getContentPane().setBackground(new Color(204, 132, 63));
        setTitle("Diplomacy");
        addQuit();
        addMinimize();
        setVisible(true);
    }

    private void addQuit() {
        JButton quit = new JButton(new ImageIcon("Files\\x.png"));
        quit.setLayout(null);
        quit.setBounds(getWidth() - 21, 0, 21, 21);
        quit.setRolloverEnabled(true);
        quit.setBorder(null);
        quit.setOpaque(false);
        quit.setContentAreaFilled(false);
        quit.setRolloverIcon(new ImageIcon("Files\\xrollover.png"));
        quit.addActionListener(new ActionListener() {
                                   public void actionPerformed(ActionEvent e) {
                                       System.exit(0);
                                   }
                               }
        );

        add(quit);
    }

    private void addMinimize() {

        JButton minimize = new JButton(new ImageIcon("Files\\-.png"));
        minimize.setBounds(getWidth() - 42, 0, 21, 21);
        minimize.setRolloverEnabled(true);
        minimize.setBorder(null);
        minimize.setOpaque(false);
        minimize.setContentAreaFilled(false);
        minimize.setRolloverIcon(new ImageIcon("Files\\-rollover.png"));
        minimize.addActionListener(new ActionListener() {
                                       public void actionPerformed(ActionEvent e) {
                                           setState(Frame.ICONIFIED);
                                       }
                                   }
        );

        add(minimize);
    }

    public void addComponentCentered(JComponent component) {
        int x = this.getWidth() / 2 - component.getWidth() / 2;
        int y = this.getHeight() / 2 - component.getHeight() / 2;
        component.setLocation(x, y);
        add(component);
    }

}
