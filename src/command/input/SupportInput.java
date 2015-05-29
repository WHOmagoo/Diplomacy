package command.input;

import command.InputBanner;
import map.Country;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Hugh on 5/18/2015.
 */
public class SupportInput extends Input implements ActionListener{
    private DefaultComboBoxModel model;
    private InputBanner banner;

    SupportInput(Country country){
        super(country);
        banner = country.getMap().getBanner();
        model = new DefaultComboBoxModel();
        model.setSelectedItem("choose who to support");

        for (Country support : country.getSupportableCountries()) {
            model.addElement(support);
        }

        setModel(model);
        setSize(longestItem(), 25);
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        super.firstAction(banner);
        SupportAttackInput supportAttack = new SupportAttackInput((Country) getSelectedItem());
        lastAction(banner, supportAttack);
    }
}
