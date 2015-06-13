package command.input;

import command.InputBanner;
import constants.RolloverButton;
import constants.Scheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Submit extends RolloverButton implements ActionListener {
    private InputBanner banner;

    public Submit(InputBanner banner) {
        super("Submit");
        this.banner = banner;
        setSize(getFontMetrics(Scheme.getFont()).stringWidth(getText()) + 13, 25);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            banner.getCountry().setOrder(banner.getOrder());
        } catch (NullPointerException n) {
        }
        banner.clearAll();
    }
}
