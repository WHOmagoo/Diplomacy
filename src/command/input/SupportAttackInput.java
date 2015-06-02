package command.input;

import command.InputBanner;
import command.order.Order;
import map.Country;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SupportAttackInput extends Input implements ActionListener{
    private DefaultComboBoxModel model = new DefaultComboBoxModel();
    private InputBanner banner;
    private Order order;

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
        Submit submit = new Submit(banner);
        lastAction(banner, submit);
        submit.startRollover();
    }

    public void setOrder(Order order){
        this.order = order;
    }

}
