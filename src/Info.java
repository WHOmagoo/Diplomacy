import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Info extends JLabel {
    public Info(String text) {
        //new JTextArea(text);
        super(text, SwingConstants.CENTER);
        setFont(Scheme.FONT.getFont());
        setOpaque(true);
        setSize(getFontMetrics(Scheme.FONT.getFont()).stringWidth(getText()) + 13, 25);
        setBackground(Scheme.BACKGROUND.getColor());
        setForeground(Scheme.FOREGROUND.getColor());
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }
}
