package command;

import constants.ButtonRollover;
import constants.RolloverButton;
import constants.Scheme;
import map.Country;
import map.Map;

import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

public class ExecuteOrders extends RolloverButton implements ActionListener {
    private Map map;

    public ExecuteOrders(Map map) {
        super("Execute Orders");
        this.map = map;
        setLocation(57, 726);
        setSize(getFontMetrics(getFont()).stringWidth(getText()) + 13, 25);
        addActionListener(this);
        setFont(Scheme.FONT.getFont());
        setHorizontalAlignment(CENTER);
        setBackground(Scheme.BACKGROUND.getColor());
        setForeground(Scheme.FOREGROUND.getColor());
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
        //printOrders();
        OrderResolver.resolveOrders(map.getCountries());
    }

    //This is intended for bug testing only
    public void printOrders() {
        for (Country c : map.getCountries()) {
            if (c.getOrder() != null) {
                System.out.println(c.getOrder());
            }
        }
    }
}
