package constants;

import java.util.Timer;
import javax.swing.JButton;

public class RolloverButton extends JButton {
    transient Timer timer = new Timer();

    public RolloverButton(String text) {
        super(text);
        setRolloverEnabled(true);
    }

    public void added() {
        try {
            timer.schedule(new ButtonRollover(this), 0, 5);
        } catch (IllegalStateException e) {
            System.out.println(e);
        }
    }

    public void removed() {
        timer.cancel();
    }
}
