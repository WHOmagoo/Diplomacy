package command;

import constants.ButtonRollover;
import constants.RolloverButton;
import constants.Scheme;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import javax.swing.BorderFactory;
import map.Country;
import map.Map;

public class ExecuteOrders extends RolloverButton implements ActionListener, Runnable {
    Thread ted = new Thread(this);
    private Map map;

    public ExecuteOrders(Map map) {
        super("Execute Orders");
        this.map = map;
        setLocation(57, 726);
        setSize(getFontMetrics(getFont()).stringWidth(getText()) + 13, 25);
        addActionListener(this);
        setFont(Scheme.getFont());
        setHorizontalAlignment(CENTER);
        setBackground(Scheme.getBackgroundColor());
        setForeground(Scheme.getForegroundColor());
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setFocusPainted(false);
        setRolloverEnabled(true);
        Timer time = new Timer();
        time.schedule(new ButtonRollover(this), 0, 5);
    }

    public void setRolloverEnabled(boolean b){
        super.setRolloverEnabled(true);
        if(b){
            Timer time = new Timer();
            time.schedule(new ButtonRollover(this), 5);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!ted.isAlive()) {
            ted = new Thread(this);
            ted.start();
        }
    }

    @Override
    public void run() {
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
    }
}
