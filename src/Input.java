import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Input extends JComboBox implements ActionListener {
    DefaultComboBoxModel model = new DefaultComboBoxModel();

    public Input() {
        for (OrderTypes o : OrderTypes.values()) {
            model.addElement(o);
        }
    }

    public Input(ArrayList<Country> countries) {
        Collections.sort(countries);
        for (Country c : countries) {
            if (c.isOccupied()) {
                model.addElement(c);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
