package command.input;

import command.InputBanner;
import command.OrderType;
import command.order.Hold;
import command.order.Order;
import command.order.Support;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import map.Country;
import map.Map;

public class OrderInput extends Input implements ActionListener {
    private DefaultComboBoxModel<OrderType> elements = new DefaultComboBoxModel<OrderType>();
    private Country countryAssociation;
    private InputBanner banner;
    private Order order;

    public OrderInput(ArrayList<OrderType> possibleOrders, Map map) {
        this((OrderType[]) possibleOrders.toArray(), map);
    }

    public OrderInput(OrderType[] possibleOrders, Map map){
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

    @Override
    public void actionPerformed(ActionEvent e) {
        firstAction(banner);
        Input temp = OrderType.getInput(getSelectedItem(), banner);

        try {
            temp.setOrder(OrderType.getOrder(getSelectedItem(), banner.getCountry()));
            lastAction(banner, temp);
        } catch (NullPointerException exception){
            Submit submit = new Submit(banner);
            //submit.startRollover();
            order = new Hold(banner.getCountry());
            banner.getCountry().setOrder(order);
            lastAction(banner, submit);
            //System.out.println("The Item was not  the correct type");
        }
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Support order){
        this.order = order;
    }
}
