package command.input;

import command.InputBanner;
import command.order.Order;
import constants.Scheme;
import map.Country;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Input extends JComboBox implements ActionListener, Comparable{
    private volatile InputBanner banner;
    private Order order;

    public Input() {
        new JComboBox<Country>();
        setMaximumRowCount(36);
        setRequestFocusEnabled(false);
        setBackground(Color.ORANGE);
        setForeground(Color.BLUE);
        setFont(Scheme.getFont());
        setLocation(0, 0);
        setAutoscrolls(true);
    }

    public Input(InputBanner banner){
        this();
        this.banner = banner;

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

    public void firstAction(InputBanner banner, Input item){
        setSize(longestItem(), getHeight());
        revalidate();
        banner.setLastVisible(item);
    }

    public void firstAction(InputBanner banner){
        setSize(longestItem(), getHeight());
        revalidate();
        banner.setLastVisible(this);
    }

    public void lastAction(InputBanner banner, JComponent newInput){
        if(newInput != null) {
            banner.setLastVisible(newInput);
            this.revalidate();
        } else{
            throw new NullPointerException("The item was null");
        }
    }

    public InputBanner getBanner(){
        return banner;
    }

    public void setBanner(InputBanner banner) {
        this.banner = banner;
    }

    public Order getOrder() {
        System.out.println("we got the wrong one");
        return order;
    }

    public void setOrder(Order order){
        this.order = order;
        System.out.println("This should only be used in emergencies");
    }
}