package command.input;

import command.InputBanner;
import command.OrderType;
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
    private Support order;

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
        temp.setOrder(OrderType.getOrder(getSelectedItem()));

        try {
             lastAction(banner, temp);
        } catch (NullPointerException exception){
            System.out.println("The Item was not  the correct type");
        }
    }

    public void setOrder(Support order){
        this.order = order;
    }
}
