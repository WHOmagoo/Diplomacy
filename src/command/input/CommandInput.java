package command.input;

import command.OrderType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class CommandInput extends Input implements ActionListener {
    public CommandInput(ArrayList<OrderType> possibleOrders) {
        super();
        Collections.sort(possibleOrders);
        for (OrderType order : possibleOrders) {
            addItem(order);
        }
        //setSelectedItem();
        setSize(getFontMetrics(getFont()).stringWidth(getSelectedItem().toString() + 25), 25);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
        System.out.println(getSelectedItem());

    }
}
