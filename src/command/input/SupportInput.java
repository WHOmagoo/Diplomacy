/**
 * SupportInput.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package command.input;

import command.Info;
import command.InputBanner;
import command.order.Order;
import command.order.Support;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import map.Country;

public class SupportInput extends Input implements ActionListener{
    private DefaultComboBoxModel model;
    private InputBanner banner;
    private Support order;

    /**
     * The constructor for this class
     *
     * @param banner the banner to add this to
     */
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

    /**
     * The action performed on action, this should prompt users for the next input
     *
     * @param e the object that triggered this
     */
    public void actionPerformed(ActionEvent e){
        super.firstAction(banner);
        Info supportAttackInfo = new Info("attack on");
        order.setSupporting((Country) getSelectedItem());
        banner.add(supportAttackInfo);
        SupportAttackInput supportAttack = new SupportAttackInput((Country) getSelectedItem());
        supportAttack.setOrder(order);
        lastAction(banner, supportAttack);
    }

    /**
     * @return the order that this has edited
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets the order that this should be editing
     *
     * @param order sets the order that this should Input should edit
     * @throws ClassCastException
     */
    public void setOrder(Order order) throws ClassCastException{
        this.order = (Support) order;
    }
}
