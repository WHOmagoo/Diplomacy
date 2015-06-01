package command.input;

import command.InputBanner;
import command.OrderType;
import command.order.Order;
import constants.Scheme;
import map.Country;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Submit extends JButton implements ActionListener{
    Timer timer;
    InputBanner banner;

    public Submit(InputBanner banner) {
        super("Submit");
        this.banner = banner;
        setFont(Scheme.FONT.getFont());
        setSize(getFontMetrics(Scheme.FONT.getFont()).stringWidth(getText()) + 13, 25);
        setHorizontalAlignment(CENTER);
        setBackground(Scheme.BACKGROUND.getColor());
        setForeground(Scheme.FOREGROUND.getColor());
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setFocusPainted(false);
        setRolloverEnabled(true);
        addActionListener(this);
        TimerTask ran = new TimerTask() {
            @Override
            public void run() {
                if (getModel().isRollover()) {
                    setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
                } else {
                    setBorder(BorderFactory.createLineBorder(Color.GRAY));
                }
            }
        };
        timer = new Timer();
        timer.schedule(ran, 0, 5);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.purge();
        OrderType orderType = null;
        Country attacking = null;
        Country supporting = null;
        Country defending = null;
        Order order;
        //TODO this should probably be identified by a variables inside of the input classes.
        for(JComponent item : banner){
            if (item instanceof OrderInput) {
                orderType = (OrderType) ((Input) item).getSelectedItem();
            } else if(item instanceof AttackInput || item instanceof MoveInput){
                attacking = (Country) ((AttackInput) item).getSelectedItem();
            } else if (item instanceof DefenseInput){
                defending = (Country) ((DefenseInput) item).getSelectedItem();
            } else if(item instanceof SupportAttackInput){
                attacking = (Country) ((SupportAttackInput) item).getSelectedItem();
            }
        }
        banner.clearAll();
    }
}
