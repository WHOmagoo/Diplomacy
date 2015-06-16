/**
 * InputBanner.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

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

    /**
     * This is the constructor for the InputBanner object, this takes some objects and puts them out
     * in order from left to right using correct spacing and such.
     *
     * @param associatedMap     the map to add this to.
     * @param associatedCountry the last country that was clicked.
     */
    public InputBanner(Map associatedMap, Country associatedCountry){
        this.associatedMap = associatedMap;
        this.country = associatedCountry;
    }

    /**
     * The same as above only it gets the last country clicked from the map.
     *
     * @param associatedMap
     */
    public InputBanner(Map associatedMap){
        this.associatedMap = associatedMap;
        this.country = associatedMap.getLastCountryClicked();
    }

    /**
     * Sets the last item visible based on index
     * @param index the index of the last item to set visible
     */
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

    /**
     * adds a new component to the InputBanner
     *
     * @param component the component to add to the InputBanner
     * @return returns true if it was added and false if it was not.
     */
    public boolean add(JComponent component) {
        if (contains(component)) {
            int indexOf = getIndex(component);
            super.set(indexOf - 2, component);
            component.setLocation(getNextLocation());
            return true;
        } else if (super.add(component)) {
            component.setLocation((getNextLocation()));
            return true;
        }
        return false;
    }

    /**
     * @return Gets the location of where the next item should be displayed.
     */
    private Point getNextLocation() {
        if (size() == 1) {
            return new Point(57, 17);
        } else {
            int xLocation = get(size() - 2).getX() + 5 + get(size() - 2).getWidth();
            int yLocation = get(size() - 2).getY();
            return new Point(xLocation, yLocation);
        }
    }

    /**
     * Removes all of the items from the InputBanner
     */
    public void clearAll() {
        while (size() > 0) {
            Rectangle oldBounds = get(0).getBounds();
            associatedMap.remove(get(0));
            associatedMap.repaint(oldBounds);
            remove(0);
        }

        numberOfItemsShowing = 0;
    }

    /**
     * Sets the last item visible based in the JComponent item
     * @param item
     */
    public void setLastVisible(JComponent item){
        if(getIndex(item) == -1){
            add(item);
        }

        setLastVisible(getIndex(item));
    }

    /**
     * Gets the index of the given JComponent
     * @param item the item to get the index of
     * @return the integer index of the item
     */
    public int getIndex(JComponent item){
        for(int i = 0; i < size(); i++){
            if (item == get(i)){
                return i;
            }
        }

        return -1;
    }

    /**
     * @return gets the map that the input banner has been added to
     */
    public Map getMap(){
        return associatedMap;
    }

    /**
     * @return gets the country that this input banner is displaying inputs for
     */
    public Country getCountry() {
        return country;
    }

    /**
     * @return the last item that is displayed
     */
    public Input getLastInput() {
        for (int i = size() - 1; i >= 0; i--){
            if(get(i) instanceof Input){
                return (Input) get(i);
            }
        }
        throw new NullPointerException("Contains no inputs");
    }

    /**
     * @return gets the order that has been created by the InputBanner
     */
    public Order getOrder() {
        return getLastInput().getOrder();
    }
}