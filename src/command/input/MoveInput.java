package command.input;

import command.InputBanner;
import map.Country;

import javax.swing.DefaultComboBoxModel;

public class MoveInput extends Input{
    private InputBanner banner;
    private DefaultComboBoxModel model;

    public MoveInput(Country country){
        super(country);
        banner = country.getMap().getBanner();
        model = new DefaultComboBoxModel();
        model.setSelectedItem("choose where to move");

        for (Country support : country.getAttackableCountries()) {
            model.addElement(support);
        }

        setModel(model);
        setSize(longestItem(), 25);
        addActionListener(this);

    }
}
