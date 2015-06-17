/**
 * AttackInput.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

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

    /**
     * The constructor for this class
     *
     * @param banner the banner to add this object to
     */
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

    /**
     * The action performed when an item has been selected.
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e){
        firstAction(banner, this);
        attack.setCountryToAttack((Country) getSelectedItem());
        Submit submit = new Submit(banner);
        lastAction(banner, submit);
    }

    /**
     * @return returns that order that has been created by this input
     */
    public Order getOrder() {
        return attack;
    }

    /**
     * Sets the order that is associated with this input.
     *
     * @param order the order to set this order as
     * @throws ClassCastException if the order set is not an instance of Attack
     */
    public void setOrder(Order order) throws ClassCastException{
        attack = (Attack) order;
    }
}