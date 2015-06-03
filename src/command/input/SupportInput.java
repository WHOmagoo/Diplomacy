package command.input;

import command.Info;
import command.InputBanner;
import command.order.Order;
import command.order.Support;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import map.Country;

/**
 * Created by Hugh on 5/18/2015.
 */
public class SupportInput extends Input implements ActionListener{
    private DefaultComboBoxModel model;
    private InputBanner banner;
    private Support order;

    public SupportInput(){

    }

    public SupportInput(InputBanner banner){
        super(banner);
        this.banner = banner;
        model = new DefaultComboBoxModel();
        model.setSelectedItem("choose who to support");

        for (Country support : banner.getCountry().getSupportableCountries()) {
            model.addElement(support);
        }

        setModel(model);
        setSize(longestItem(), 25);
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        super.firstAction(banner);
        Info supportAttackInfo = new Info("attack on");
        order.setSupporting((Country) getSelectedItem());
        banner.add(supportAttackInfo);
        SupportAttackInput supportAttack = new SupportAttackInput((Country) getSelectedItem());
        lastAction(banner, supportAttack);
    }

    public void setOrder(Order order) throws ClassCastException{
        this.order = (Support) order;
    }
}
