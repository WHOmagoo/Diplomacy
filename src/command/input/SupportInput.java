package command.input;

import javax.swing.DefaultComboBoxModel;
import map.Country;

/**
 * Created by Hugh on 5/18/2015.
 */
public class SupportInput extends Input {
    private DefaultComboBoxModel model;

    SupportInput(Country country){
        super(country);
        model = new DefaultComboBoxModel();
        model.setSelectedItem("choose who to support");

        for (Country support : country.getSupportableCountries()) {
            model.addElement(support);
        }
        setModel(model);
        setSize(longestItem(), 25);
    }
}
