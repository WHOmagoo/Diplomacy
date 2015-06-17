/**
 * Submit.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package command.input;

import command.InputBanner;
import constants.RolloverButton;
import constants.Scheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Submit extends RolloverButton implements ActionListener {
    private InputBanner banner;

    /**
     * creates a new submit button
     *
     * @param banner the banner to add this to
     */
    public Submit(InputBanner banner) {
        super("Submit");
        this.banner = banner;
        setSize(getFontMetrics(Scheme.getFont()).stringWidth(getText()) + 13, 25);
        addActionListener(this);
    }

    /**
     * The action performed on button push, this sets the countries order to whatever has been
     * created by the prior inputs
     *
     * @param e the object that fired this event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            banner.getCountry().setOrder(banner.getOrder());
        } catch (NullPointerException n) {
        }
        banner.clearAll();
    }
}
