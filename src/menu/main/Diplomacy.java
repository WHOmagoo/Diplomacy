package menu.main;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Diplomacy extends JLabel {
    /**
     * The constructor for this class
     * Outputs
     *    Creates a JLabel with a specific picture in it
     */
    public Diplomacy() {
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
