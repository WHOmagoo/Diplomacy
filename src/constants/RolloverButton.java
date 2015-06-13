package constants;

import java.awt.Color;
import java.awt.Insets;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class RolloverButton extends JButton implements Runnable {
    transient Timer timer = new Timer();
    private Border oldBorder;
    private RolloverButton button;

    public RolloverButton(String text) {
        super(text);
        button = this;
        setHorizontalAlignment(CENTER);
        setBackground(Scheme.getBackgroundColor());
        setForeground(Scheme.getForegroundColor());
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setFocusPainted(false);
        setFont(Scheme.getFont());
        setMargin(new Insets(0, 0, 0, 0));
        oldBorder = getBorder();
    }

    public void added() {
        try {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (button.getModel().isRollover()) {
                        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
                    } else {
                        button.setBorder(oldBorder);
                    }
                }
            }, 0, 33);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void removed() {
        timer.cancel();
    }

    @Override
    public void run() {
    }
}
