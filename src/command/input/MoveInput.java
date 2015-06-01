package command.input;

import command.InputBanner;
import map.Country;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoveInput extends Input implements ActionListener{
    private InputBanner banner = null;

    public MoveInput(){
    }

    public MoveInput(InputBanner banner){
        this.banner = banner;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.setSelectedItem("choose where to move");

        for (Country support : banner.getCountry().getAttackableCountries()) {
            model.addElement(support);
        }

        setModel(model);
        setSize(longestItem(), 25);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) throws NullPointerException{
        if(banner == null){
            throw new NullPointerException("Banner has not yet been initialized");
        }
        firstAction(banner);
        lastAction(banner, new Submit(banner));
    }
}
