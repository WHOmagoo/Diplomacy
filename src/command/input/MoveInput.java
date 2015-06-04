package command.input;

import command.InputBanner;
import command.order.Move;
import command.order.Order;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import map.Country;

public class MoveInput extends Input implements ActionListener{
    private InputBanner banner = null;
    private Move order;

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
        firstAction(banner);
        order.setMovingTo((Country) getSelectedItem());
        Submit submit = new Submit(banner);
        lastAction(banner, submit);
        //submit.startRollover();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) throws ClassCastException{
        this.order = (Move) order;
    }
}
