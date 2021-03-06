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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import map.Country;
import map.Map;

public class ExecuteOrders extends RolloverButton implements ActionListener, Runnable {
    Thread ted = new Thread(this);
    private Map map;
    private ObjectOutputStream saveMap;
    private FileOutputStream file;

    /**
     * The constructor for an ExecuteOrder, pre sets everything it needs.
     *
     * @param map - the map to add the button to and it gets the orders from the map.
     */
    public ExecuteOrders(Map map) {
        super("Execute Orders");
        this.map = map;
        try {
            file = new FileOutputStream("Save.dat");
            saveMap = new ObjectOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            try {
                map.getBanner().clearAll();
            } catch (NullPointerException n) {
            }
            ted = new Thread(this);
            ted.start();
        }

        //TODO implement later;
        try {
            saveMap.writeObject(map);
        } catch (IOException e1) {
            e1.printStackTrace();
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
