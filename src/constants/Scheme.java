package constants;

import java.awt.Color;
import java.awt.Font;

public enum Scheme {
    FOREGROUND (Color.BLUE), BACKGROUND(Color.ORANGE), FONT(new Font("Dialog", Font.BOLD, 12));

    Color color;
    Font font;

    Scheme(Color color) {
        this.color = color;
    }

    Scheme(Font font){
        this.font = font;
    }

    public Color getColor() {
        return color;
    }

    public Font getFont() {
        return font;
    }
}
