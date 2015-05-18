import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Input extends JComboBox implements ActionListener {
    ;

    public Input() {
        new JComboBox();
        setSelectedItem("RObb");
        setMaximumRowCount(36);
        setRequestFocusEnabled(false);
        setBackground(Color.ORANGE);
        setForeground(Color.BLUE);
        setSize(getFontMetrics(getFont()).stringWidth(getSelectedItem().toString()) + 25, 25);
        setLocation(0, 0);
        setAutoscrolls(true);
        System.out.println("Creating");
    }

    public Input(ArrayList<Country> countries) {
        super();
        Collections.sort(countries);
        for (Country c : countries) {
            addItem(c);
        }

        constants();
    }

    public Input(OrderType[] orderTypes){
        super();
        for(OrderType o : orderTypes){
            addItem(o);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void constants() {
    }
}