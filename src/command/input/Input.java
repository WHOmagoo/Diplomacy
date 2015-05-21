package command.input;

import command.OrderType;
import constants.Scheme;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import map.Country;

public class Input extends JComboBox implements ActionListener, Comparable{
    private Country countryAssociation;

    public Input() {
        new JComboBox<Country>();
        setMaximumRowCount(36);
        setRequestFocusEnabled(false);
        setBackground(Color.ORANGE);
        setForeground(Color.BLUE);
        setFont(Scheme.FONT.getFont());
        setLocation(0, 0);
        setAutoscrolls(true);
    }

    public Input(Country countryAssociation) {
        this();
        this.countryAssociation = countryAssociation;
    }

    public Input(OrderType[] orderTypes, Country countryAssociation) {
        super();
        for(OrderType o : orderTypes){
            addItem(o);
        }
    }

    public int longestItem(){
        int longestItem = getFontMetrics(getFont()).stringWidth(getSelectedItem().toString()) + 25;
        ComboBoxModel model = getModel();
        for(int i = 0; i < model.getSize(); i++){
            int thisLength = getFontMetrics(getFont()).stringWidth(model.getElementAt(i).toString());
            if (longestItem < thisLength) {
                longestItem = thisLength;
            }
        }

        return longestItem + 4;
    }

    private Country getCountryAssociation() {
        return countryAssociation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void constants() {
    }

    @Override
    public int compareTo(Object o) throws ClassCastException{
        if(o instanceof JComponent){
            JComponent temp = (JComponent) o;
            if(temp.getY() == getY()){
                return getX() - temp.getX();
            } else return getY() - temp.getY();
        }

        throw new ClassCastException("Must compare a JComponent");
    }
}