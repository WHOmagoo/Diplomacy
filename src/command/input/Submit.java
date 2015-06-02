package command.input;

import command.InputBanner;
import constants.Scheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Submit extends JButton implements ActionListener{
    private Timer timer;
    private final TimerTask rollOver = new TimerTask() {
        @Override
        public void run() {
            if(isShowing()) {
                if (getModel().isRollover()) {
                    setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
                } else {
                    setBorder(BorderFactory.createLineBorder(Color.GRAY));
                }
            } else{
                timer.cancel();
            }
        }
    };

    private InputBanner banner;

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
    }

    public void startRollover(){
        timer = new Timer();
        timer.schedule(rollOver, 0, 5);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.cancel();
        System.out.println(banner.getLastInput().getOrder()); //TODO make this not null
        banner.clearAll();
    }
}
