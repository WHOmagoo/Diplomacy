package command.input;

import command.InputBanner;
import command.OrderType;
import map.Country;
import map.Map;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CommandInput extends Input implements ActionListener {
    private DefaultComboBoxModel<OrderType> elements = new DefaultComboBoxModel<OrderType>();
    private Country countryAssociation;
    private InputBanner banner;

    public CommandInput(ArrayList<OrderType> possibleOrders, Map map) {
        this((OrderType[]) possibleOrders.toArray(), map);
    }

    public CommandInput(OrderType[] possibleOrders, Map map){
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
        banner.setLastVisible(this);
        Input temp = null;
        if(getSelectedItem() == OrderType.ATTACK){
            temp = new AttackInput(countryAssociation);
        } else if(getSelectedItem() == OrderType.HOLD){

        } else if(getSelectedItem() == OrderType.MOVE){

        } else if(getSelectedItem() == OrderType.SUPPORT){
            temp = new SupportInput(countryAssociation);
        }

        setSize(longestItem(), 25);
        banner.add(temp);
        revalidate();
        banner.setLastVisible(temp);
    }
}
