/**
 * OrderInput.java
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
import command.order.Hold;
import command.order.Order;
import command.order.Support;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import map.Country;
import map.Map;

public class OrderInput extends Input implements ActionListener {
    private DefaultComboBoxModel<OrderType> elements = new DefaultComboBoxModel<OrderType>();
    private Country countryAssociation;
    private InputBanner banner;
    private Order order;

    /**
     * The constructor for this class
     *
     * @param map            the map that this is associated with
     * @param possibleOrders the orders that this unit may execute
     */
    public OrderInput(Map map, OrderType... possibleOrders) {
        super();
        banner = map.getBanner();
        countryAssociation = map.getLastCountryClicked();

        for (OrderType order : possibleOrders) {
            elements.addElement(order);
        }

        elements.setSelectedItem("Choose an command");
        setModel(elements);

        setSize(longestItem(), 25);
        addActionListener(this);
    }

    /**
     * The action performed for this object, will prompt for the next input
     *
     * @param e the object that triggered this
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        firstAction(banner);
        Input temp = OrderType.getInput(getSelectedItem(), banner);

        try {
            temp.setOrder(OrderType.getOrder(getSelectedItem(), banner.getCountry()));
            lastAction(banner, temp);
        } catch (NullPointerException exception){
            Submit submit = new Submit(banner);
            order = new Hold(banner.getCountry());
            banner.getCountry().setOrder(order);
            lastAction(banner, submit);
        }
    }

    /**
     * @return the order that this input has made.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * @param order sets the order that this class will be adding on to
     */
    public void setOrder(Support order){
        this.order = order;
    }
}