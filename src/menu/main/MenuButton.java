/**
 * MenuButton.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package menu.main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MenuButton extends JButton {

    /**The constructor
    * Parameters
    *   text - a string to add to the button
    */
    public MenuButton(String text) {
        new JButton();
        setText(text);
        setEnabled(true);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setAlignmentY(Component.TOP_ALIGNMENT);
        setMinimumSize(new Dimension(100, 25));
        setMaximumSize(new Dimension(200, 50));
        setPreferredSize(new Dimension(200, 50));
        setHorizontalAlignment(CENTER);
        setFocusPainted(false);
    }

    /**Another constructor
    * Parameters
    *   text - a string to add to the button
    *   actionPerformed - an ActionListener to associate with the button
    */
    public MenuButton(String text, ActionListener actionPerformed) {
        this(text);
        addActionListener(actionPerformed);
    }
}
