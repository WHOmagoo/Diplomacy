package command.input;

import command.InputBanner;
import map.Country;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SupportAttackInput extends Input implements ActionListener{
    private DefaultComboBoxModel model = new DefaultComboBoxModel();
    private InputBanner banner;

    public SupportAttackInput(){

    }

    public SupportAttackInput(Country supporting){
        super(supporting.getMap().getBanner());
        banner = supporting.getMap().getBanner();
        for(Country c : supporting.getSupportableInCommon(supporting.getMap().getLastCountryClicked())){
            model.addElement(c);
        }

        model.setSelectedItem("choose who to support the attack to");
        setModel(model);
        setSize(longestItem(), 25);
        addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        firstAction(banner);
        lastAction(banner, new Submit(banner));
    }

}
