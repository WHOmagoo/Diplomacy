package constants;

import java.util.Timer;
import javax.swing.JButton;

public class RolloverButton extends JButton {
    transient Timer timer = new Timer();
    transient private boolean isAdded = false;

    public RolloverButton(String text) {
        super(text);
        setRolloverEnabled(true);
    }

    public void added() {
        isAdded = true;
        try {
            timer.schedule(new ButtonRollover(this), 0, 5);
        } catch (IllegalStateException e) {
            System.out.println(e);
        }
    }

    public boolean isAdded() {
        return isAdded;
    }

    public void removed() {
        isAdded = false;
        timer.cancel();
    }
}
