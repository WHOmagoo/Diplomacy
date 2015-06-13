package menu.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Diplomacy extends JLabel {
    /**
     *
     */
    public Diplomacy() {
        new JLabel();
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setIcon(new ImageIcon("Files\\Diplomacy Text.png"));
        setSize(1024, 241);
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 4, true));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setAlignmentY(Component.TOP_ALIGNMENT);
    }

    /**
     * Use this to resize an ImageIcon, may be used later.
     *   Image resized = new ImageIcon("Files\\Diplomacy Text.png").getImage();
     *   setIcon(new ImageIcon(resized.getScaledInstance(1024, 250, Image.SCALE_AREA_AVERAGING)));
     */
}
