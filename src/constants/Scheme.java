/**
 * Scheme.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package constants;

import java.awt.Color;
import java.awt.Font;

public class Scheme {

    /**
     * @return the foreground color that should be used by the other JComponents
     */
    public static Color getForegroundColor() {
        return Color.BLUE;
    }

    /**
     * @return the background color that should be used by the JComponents
     */
    public static Color getBackgroundColor() {
        return Color.ORANGE;
    }

    /**
     * @return the font that should be used by the JComponents
     */
    public static Font getFont() {
        return new Font("Dialog", Font.BOLD, 12);
    }
}
