/**
 * DefenseInput.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

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

    /**
     * This is the constructor for the class
     *
     * @param banner the banner to add the input to
     */
    public DefenseInput(InputBanner banner){
        this.banner = banner;
        DefaultComboBoxModel<Country> model = new DefaultComboBoxModel();
        model.setSelectedItem("choose who to " + OrderType.DEFEND);

        for (Country support : banner.getCountry().getOccupiedNeighbors()) {
            model.addElement(support);
        }

        setModel(model);
        setSize(longestItem(), 25);
        addActionListener(this);
    }

    /**
     * The action that is performed on action
     *
     * @param e the object that triggered this
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        firstAction(banner);
        order.setDefending((Country) getSelectedItem());
        Submit submit = new Submit(banner);
        lastAction(banner, submit);
        //submit.startRollover();

    }

    /**
     * @return the order that this has created
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets the order to add onto
     *
     * @param order the order to add onto.
     * @throws ClassCastException if the order was not a Defense order
     */
    public void setOrder(Order order) throws ClassCastException{
        this.order = (Defend) order;
    }
}
