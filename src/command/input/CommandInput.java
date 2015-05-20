package command.input;

import command.OrderType;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        DefaultListSelectionModel jon = new DefaultListSelectionModel();
        jon.setSelectionInterval(2,3);
        setModel(elements);
        BasicComboBoxRenderer j = new BasicComboBoxRenderer();
        //setRenderer(this);


        setSize(longestItem(), 25);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setSize(longestItem(), 25);
        revalidate();
    }
}
