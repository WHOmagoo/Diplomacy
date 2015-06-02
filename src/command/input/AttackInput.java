package command.input;

import command.InputBanner;
import command.order.Attack;
import command.order.Order;
import map.Country;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Hugh on 5/18/2015.
 */
public class AttackInput extends Input implements ActionListener{
    private InputBanner banner;
    private Attack attack;

    public AttackInput(){
    }

    public AttackInput(InputBanner banner){
        super(banner);
        this.banner = banner;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.setSelectedItem("choose who to Attack");

        for (Country support : banner.getMap().getLastCountryClicked().getAttackableCountries()) {
            model.addElement(support);
        }
        setModel(model);
        setSize(longestItem(), 25);
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        firstAction(banner, this);
        attack.setCountryToAttack((Country) getSelectedItem());
        Submit submit = new Submit(banner);
        lastAction(banner, submit);
        submit.startRollover();
    }

    public void setOrder(Order order) throws ClassCastException{
        attack = (Attack) order;
    }
}
