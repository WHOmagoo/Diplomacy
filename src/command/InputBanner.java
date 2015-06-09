package command;

import command.input.Input;
import command.order.Order;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JComponent;
import map.Country;
import map.Map;

public class InputBanner extends ArrayList<JComponent> {
    private Map associatedMap;
    private Country country;
    private int numberOfItemsShowing = 0;

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
            return new Point(57, 17);
        } else {
            int xLocation = get(size() - 2).getX() + 5 + get(size() - 2).getWidth();
            int yLocation = get(size() - 2).getY();
            return new Point(xLocation, yLocation);
        }
    }

    public synchronized void clearAll() {
        while (size() > 0) {
            Rectangle oldBounds = get(0).getBounds();
            associatedMap.remove(get(0));
            associatedMap.repaint(oldBounds);
            remove(0);
        }

        numberOfItemsShowing = 0;
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

    public Input getLastInput() {
        for (int i = size() - 1; i >= 0; i--){
            if(get(i) instanceof Input){
                return (Input) get(i);
            }
        }
        throw new NullPointerException("Contains no inputs");
    }

    public Order getOrder() {
        return getLastInput().getOrder();
    }

    public void setOrder() {

    }
}