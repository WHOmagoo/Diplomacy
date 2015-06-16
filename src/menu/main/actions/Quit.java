/**
 * Quit.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package menu.main.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quit implements ActionListener {

    /**
     * This is the action performed for the quit button
     * Outputs
     * Ends the threads and closes the window
     */
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
