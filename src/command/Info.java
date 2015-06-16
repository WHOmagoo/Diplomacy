/**
 * Info.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package command;

import constants.Scheme;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Info extends JLabel {
    /**
     * This is just a JLabel with some text on it, it automatically sizes to the string width and
     * sets the background.
     *
     * @param text the text to display on the JLabel
     */
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
