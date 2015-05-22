package command.input;

import command.OrderType;
import map.Country;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CommandInput extends Input implements ActionListener {
    private DefaultComboBoxModel<OrderType> elements = new DefaultComboBoxModel<OrderType>();
    private Country countryAssociation;

    public CommandInput(ArrayList<OrderType> possibleOrders, Country countryCalled) {
        this((OrderType[]) possibleOrders.toArray(), countryCalled);
    }

    public CommandInput(OrderType[] possibleOrders, Country countryCalled){
        super();
        countryAssociation = countryCalled;

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
        Input temp = null;
        if(getSelectedItem() == OrderType.ATTACK){

        } else if(getSelectedItem() == OrderType.HOLD){

        } else if(getSelectedItem() == OrderType.MOVE){

        } else if(getSelectedItem() == OrderType.SUPPORT){
            temp = new SupportInput(countryAssociation);
        }

        setSize(longestItem(), 25);
        countryAssociation.add(temp);
        revalidate();
    }
}
