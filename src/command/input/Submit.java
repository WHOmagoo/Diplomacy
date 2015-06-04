package command.input;

import command.InputBanner;
import constants.RolloverButton;
import constants.Scheme;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;

public class Submit extends RolloverButton implements ActionListener {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        banner.getCountry().setOrder(banner.getOrder());
        banner.clearAll();
    }
}
