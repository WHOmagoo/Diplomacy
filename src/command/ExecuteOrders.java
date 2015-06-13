package command;

import constants.RolloverButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import map.Country;
import map.Map;

public class ExecuteOrders extends RolloverButton implements ActionListener, Runnable {
    transient Thread ted = new Thread(this);
    private Map map;

    public ExecuteOrders(Map map) {
        super("Execute Orders");
        this.map = map;
        setLocation(57, 726);
        setSize(getFontMetrics(getFont()).stringWidth(getText()) + 13, 25);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!ted.isAlive()) {
            map.getBanner().clearAll();
            ted = new Thread(this);
            ted.start();
        }
    }

    @Override
    public void run() {
        setEnabled(false);
        for (Country c : map.getCountries()) {
            c.setEnabled(false);
        }

        OrderResolver resolver = new OrderResolver(map.getCountries());
        resolver.resolve();

        for (Country c : map.getCountries()) {
            if (c.isOccupied()) {
                c.setEnabled(true);
            }
        }
        setEnabled(true);
    }
}
