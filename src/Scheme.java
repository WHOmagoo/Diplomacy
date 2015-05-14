import java.awt.*;

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

    Color getColor(){
        return color;
    }

    Font getFont(){
        return font;
    }
}
