/**
 * RolloverButton.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package constants;

import java.awt.Color;
import java.awt.Insets;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class RolloverButton extends JButton implements Runnable {
    Timer timer = new Timer();
    private Border oldBorder;
    private RolloverButton button;

    /**
     * This is the default constructor for the class.
     *
     * @param text the text for the button to have.
     */
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

    /**
     * This method should be called once the button has been added to a JComponent, it starts the
     * timer to check if the mouse is hovering over the button and will cancel it once the JButton
     * is no longer visible.
     */
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

    /**
     * This cancels the timer and should be called once removed from the JComponent.
     */
    public void removed() {
        timer.cancel();
    }

    /**
     * This is the method that runs every 30 milliseconds or so and it checks if the mouse is
     * hovering over the button.
     */
    @Override
    public void run() {
    }
}
