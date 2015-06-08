package command;

import constants.Scheme;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Info extends JLabel {
    public Info(String text) {
        //new JTextArea(text);
        super(text, SwingConstants.CENTER);
        setFont(Scheme.getFont());
        setOpaque(true);
        setSize(getFontMetrics(Scheme.getFont()).stringWidth(getText()) + 13, 25);
        setBackground(Scheme.getBackgroundColor());
        setForeground(Scheme.getForegroundColor());
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }
}
