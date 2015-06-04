package command.input;

import command.InputBanner;
import command.order.Attack;
import command.order.Order;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import map.Country;

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
    }

    public Order getOrder() {
        return attack;
    }

    public void setOrder(Order order) throws ClassCastException{
        attack = (Attack) order;
    }
}
