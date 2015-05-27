package command.input;

import map.Country;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Hugh on 5/18/2015.
 */
public class SupportInput extends Input implements ActionListener{
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
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        SupportAttackInput sai = new SupportAttackInput((Country) getSelectedItem());
    }
}
