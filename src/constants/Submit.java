package constants;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;

/**
 * Created by Hugh on 5/15/2015.
 */
public class Submit extends JButton {
    public Submit() {
        new JButton("Submit");
        setText("Submit");
        setBackground(Scheme.BACKGROUND.getColor());
        setForeground(Scheme.FOREGROUND.getColor());
        setSelectedIcon(null);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setSize(getFontMetrics(Scheme.FONT.getFont()).stringWidth(getText()) + 5, 25);
    }
}
