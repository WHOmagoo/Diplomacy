package command.input;

import command.InputBanner;
import command.OrderType;
import command.order.Defend;
import command.order.Order;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import map.Country;

public class DefenseInput extends Input implements ActionListener{
    private InputBanner banner;
    private Defend order;

    public DefenseInput(){
    }

    public DefenseInput(InputBanner banner){
        this.banner = banner;
        DefaultComboBoxModel<Country> model = new DefaultComboBoxModel();
        model.setSelectedItem("choose who to " + OrderType.DEFEND);

        for (Country support : banner.getCountry().getAttackableCountries()) {
            model.addElement(support);
        }

        setModel(model);
        setSize(longestItem(), 25);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) throws NullPointerException{
        firstAction(banner);
        order.setDefending((Country) getSelectedItem());
        Submit submit = new Submit(banner);
        lastAction(banner, submit);
        //submit.startRollover();

    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) throws ClassCastException{
        this.order = (Defend) order;
    }
}
