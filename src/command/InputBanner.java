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

    public void setLastVisible(int index){
        if(numberOfItemsShowing < index){
            for(int i = numberOfItemsShowing; i < index; i++){
                associatedMap.add(get(i), -1);
                associatedMap.repaint(get(i).getBounds());
            }
        } else {
            for (int i = size(); i > index; i--) {
                associatedMap.remove(get(i));
                associatedMap.repaint(get(i).getBounds());
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
        if(add(e)){
            e.setLocation(getNextLocation());
        }

        return false;
    }

    private Point getNextLocation() {
        if (size() == 0) {
            return new Point(0, 0);
        } else {
            int xLocation = get(size() - 2).getX() + 3 + get(size() - 2).getWidth();
            int yLocation = get(size() - 2).getY();
            return new Point(xLocation, yLocation);
        }
    }

}
