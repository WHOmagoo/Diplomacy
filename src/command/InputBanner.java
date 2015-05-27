package command;

import map.Country;
import map.Map;

import javax.swing.JComponent;
import java.awt.Point;
import java.util.ArrayList;

public class InputBanner extends ArrayList<JComponent> {
    private Map associatedMap;
    private Country associatedCountry;
    private int numberOfItemsShowing = 0;

    public InputBanner(Map associatedMap, Country associatedCountry){
        this.associatedMap = associatedMap;
        this.associatedCountry = associatedCountry;
    }

    public InputBanner(Map associatedMap){
        this.associatedMap = associatedMap;
    }

    public void setAssociatedCountry(Country associatedCountry){
        this.associatedCountry = associatedCountry;
    }

    public void setLastVisible(int index) {
        if (numberOfItemsShowing < index) {
            for (int i = numberOfItemsShowing; i <= index; i++) {
                associatedMap.add(get(i), -1);
                get(i).revalidate();
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

}
