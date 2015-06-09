package command;

import constants.ButtonRollover;
import constants.RolloverButton;
import constants.Scheme;
import map.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

public class ExecuteOrders extends RolloverButton implements ActionListener, Runnable {
    volatile Thread ted = new Thread(this);
    private volatile Map map;

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
        OrderResolver resolver = new OrderResolver(map.getCountries());
        resolver.resolve();
    }
}
