package command;

import command.input.Input;
import command.order.Order;
import map.Country;
import map.Map;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InputBanner extends ArrayList<JComponent> {
    private Map associatedMap;
    private Country country;
    private int numberOfItemsShowing = 0;
    private Order orderBiulder;

    public InputBanner(Map associatedMap, Country associatedCountry){
        this.associatedMap = associatedMap;
        this.country = associatedCountry;
    }

    public InputBanner(Map associatedMap){
        this.associatedMap = associatedMap;
        this.country = associatedMap.getLastCountryClicked();
    }

    public void setLastVisible(int index) {
        if (numberOfItemsShowing < index) {
            for (int i = numberOfItemsShowing; i <= index; i++) {
                associatedMap.add(get(i), -1);
                get(i).validate();
                associatedMap.repaint(get(i).getBounds());
            }
        } else {
            for (int i = size() - 1; i > index; i--) {
                associatedMap.remove(get(i));
                get(i).revalidate();
                associatedMap.repaint(get(i).getBounds());
                remove(i);
            }
        }
        numberOfItemsShowing = index;
    }

    private JComponent getPreviousItem(JComponent component){
        //TODO delete later.
        for(int i = 0; i < size(); i++){
            if (component == get(i)){
                return get(i - 1);
            }
        }

        throw new NullPointerException("The item was not found");
    }

    public boolean add(JComponent e){
        if (contains(e)) {
            int indexOf = getIndex(e);
            super.set(indexOf - 2, e);
            e.setLocation(getNextLocation());
            return true;
        } else if(super.add(e)){
            e.setLocation((getNextLocation()));
            return true;
        }
        return false;
    }

    private Point getNextLocation() {
        if (size() == 1) {
            return new Point(0, 0);
        } else {
            int xLocation = get(size() - 2).getX() + 5 + get(size() - 2).getWidth();
            int yLocation = get(size() - 1).getY();
            return new Point(xLocation, yLocation);
        }
    }

    public void clearAll() {
        while (size() != 0) {
            associatedMap.remove(get(0));
            associatedMap.repaint(get(0).getBounds());
            remove(0);
        }
    }

    public void setLastVisible(JComponent item){
        if(getIndex(item) == -1){
            add(item);
        }

        setLastVisible(getIndex(item));
    }

    public int getIndex(JComponent item){
        for(int i = 0; i < size(); i++){
            if (item == get(i)){
                return i;
            }
        }

        return -1;
    }

    public Input getFirstNotVisibile(Input[] inputs){
        for(Input input : inputs){
            if(!contains(input)){
                return input;
            }
        }

        return null;
    }

    public Map getMap(){
        return associatedMap;
    }

    public Country getCountry() {
        return country;
    }

    public void setOrderType(OrderType orderType){
        orderBiulder = orderType.getOrder();
    }
}
