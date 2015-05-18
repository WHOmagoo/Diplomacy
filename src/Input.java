import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JComboBox;

public class Input extends JComboBox implements ActionListener {
    ;

    public Input() {
        new JComboBox<OrderTypes>();
        for (OrderTypes o : OrderTypes.values()) {
            addItem(o);
        }

        constants();
    }

    public Input(ArrayList<Country> countries) {
        new JComboBox<Country>();
        Collections.sort(countries);
        for (Country c : countries) {
            addItem(c);
        }

        constants();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void constants() {
        setMaximumRowCount(36);
        setRequestFocusEnabled(false);
        setBackground(Color.ORANGE);
        setForeground(Color.BLUE);
        setSize(getFontMetrics(getFont()).stringWidth(getSelectedItem().toString()) + 25, 25);
        setLocation(0, 0);
        setAutoscrolls(true);
    }
}
