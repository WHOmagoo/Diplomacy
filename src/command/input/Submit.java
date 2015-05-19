package command.input;

import constants.Scheme;
import javax.swing.JButton;

/**
 * Created by Hugh on 5/18/2015.
 */
public class Submit extends JButton {
    public Submit() {
        super("Submit");
        setFont(Scheme.FONT.getFont());
        setSize(getFontMetrics(Scheme.FONT.getFont()).stringWidth(getText()) + 25, 25);
        setHorizontalAlignment(CENTER);
        setBackground(Scheme.BACKGROUND.getColor());
        setForeground(Scheme.FOREGROUND.getColor());
    }

    public Submit(int x, int y) {
        this();
        setLocation(x, y);
    }
}
