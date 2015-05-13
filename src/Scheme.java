import java.awt.*;

public enum Scheme {
    FOREGROUND (Color.BLUE), BACKGROUND(Color.ORANGE), FONT(new Font("Dialog", Font.BOLD, 12));

    Scheme(Color color) {
    }

    Scheme(Font font){

    }
}
