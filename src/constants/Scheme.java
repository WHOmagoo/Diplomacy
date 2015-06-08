package constants;

import java.awt.Color;
import java.awt.Font;

public enum Scheme {
    ;

    public static Color getForegroundColor() {
        return Color.BLUE;
    }

    public static Color getBackgroundColor() {
        return Color.ORANGE;
    }

    public static Font getFont() {
        return new Font("Dialog", Font.BOLD, 12);
    }
}
