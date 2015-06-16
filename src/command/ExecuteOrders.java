/**
 * ExecuteOrders.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package command;

import constants.RolloverButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import map.Country;
import map.Map;

public class ExecuteOrders extends RolloverButton implements ActionListener, Runnable {
    Thread ted = new Thread(this);
    private Map map;

    /**
     * The constructor for an ExecuteOrder, pre sets everything it needs.
     *
     * @param map - the map to add the button to and it gets the orders from the map.
     */
    public ExecuteOrders(Map map) {
        super("Execute Orders");
        this.map = map;
        setLocation(57, 726);
        setSize(getFontMetrics(getFont()).stringWidth(getText()) + 13, 25);
        addActionListener(this);
    }

    /**
     * The action that this button performs
     *
     * @param e the event that triggered it.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!ted.isAlive()) {
            map.getBanner().clearAll();
            ted = new Thread(this);
            ted.start();
        }
    }

    /**
     * The run method for this class, the actionPerformed runs this method so it doesn't lock up the
     * rollover effects.
     */
    @Override
    public void run() {
        setEnabled(false);
        for (Country c : map.getCountries()) {
            c.setEnabled(false);
        }

        OrderResolver resolver = new OrderResolver(map.getCountries());
        resolver.resolve();

        map.updateGraphics();
        setEnabled(true);
    }
}
