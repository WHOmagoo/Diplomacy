package command.input;

import command.OrderType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

public class CommandInput extends Input implements ActionListener {
    private DefaultComboBoxModel<OrderType> elements = new DefaultComboBoxModel<OrderType>();

    public CommandInput(ArrayList<OrderType> possibleOrders) {
        this((OrderType[]) possibleOrders.toArray());
    }

    public CommandInput(OrderType[] possibleOrders){
        super();
        for (OrderType order : possibleOrders) {
            elements.addElement(order);
        }
        elements.setSelectedItem("Choose an Item");
        setModel(elements);

        setSize(longestItem() + 25, 25);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setSize(longestItem() + 25, 25);
        revalidate();
    }
}
