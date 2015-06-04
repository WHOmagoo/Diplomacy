package constants;

import java.util.Timer;
import javax.swing.JButton;

/**
 * Created by Hugh on 6/3/2015.
 */
public class RolloverButton extends JButton {
    Timer timer = new Timer();
    private boolean isAdded = false;

    public RolloverButton(String text) {
        super(text);
        setRolloverEnabled(true);
    }

    public void added() {
        isAdded = true;
        timer.schedule(new ButtonRollover(this), 0, 5);
    }

    public boolean isAdded() {
        return isAdded;
    }

    public void removed() {
        isAdded = false;
        timer.cancel();
    }
}
