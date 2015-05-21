package command.input;

import command.OrderType;
import constants.Scheme;
import map.Country;
import map.Map;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Input extends JComboBox implements ActionListener, Comparable{

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

    public Input(ArrayList objects) {
        super();
        Collections.sort(objects);
        for (java.lang.Object c : objects) {//See if this works as expected
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

    //TODO implement this correctly.
    public Input(Map map, Country c) {
        super();
        //for(Country c : );
        map.add(this);
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