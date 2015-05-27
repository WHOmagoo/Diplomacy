package command.input;

import map.Country;

import javax.swing.DefaultComboBoxModel;

/**
 * Created by Hugh on 5/18/2015.
 */
public class AttackInput extends Input {
    private DefaultComboBoxModel model;

    public AttackInput(Country associatedCountry){
        super(associatedCountry);
        model = new DefaultComboBoxModel();
        model.setSelectedItem("choose who to Attack");

        for (Country support : associatedCountry.getAttackableCountries()) {
            model.addElement(support);
        }
        setModel(model);
        setSize(longestItem(), 25);

    }
}
