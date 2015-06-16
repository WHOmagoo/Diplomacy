package command.input;

import command.InputBanner;
import command.order.Order;
import command.order.Support;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import map.Country;

public class SupportAttackInput extends Input implements ActionListener{
    private DefaultComboBoxModel<Country> model = new DefaultComboBoxModel();
    private InputBanner banner;
    private Support order;

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
        order.setAttacking((Country) getSelectedItem());
        firstAction(banner);
        Submit submit = new Submit(banner);
        lastAction(banner, submit);
        //submit.startRollover();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Support order) {
        this.order = order;
    }

}
