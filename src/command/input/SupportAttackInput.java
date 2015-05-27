package command.input;

import map.Country;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SupportAttackInput extends Input implements ActionListener{
    private DefaultComboBoxModel model = new DefaultComboBoxModel();

    public SupportAttackInput(Country supporting){
        super();
        for(Country c : supporting.getSupportableInCommon(supporting.getMap().getLastCountryClicked())){
            model.addElement(c);
        }
    }

}
