package constants;

import java.awt.Color;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class ButtonRollover extends TimerTask {
    private RolloverButton jButton;
    private Border oldBorder;

    public ButtonRollover(RolloverButton jButton) {
        this.jButton = jButton;
        oldBorder = jButton.getBorder();
    }

    @Override
    public void run() {
        if (jButton.getModel().isRollover()) {
            jButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        } else {
            jButton.setBorder(oldBorder);
        }
    }
}