/**
 * Input.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package command.input;

import command.InputBanner;
import command.order.Order;
import constants.Scheme;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import map.Country;

public class Input extends JComboBox implements ActionListener, Comparable{
    private InputBanner banner;
    private Order order;

    /**
     * The default constructor for this class
     */
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

    /**
     * Another constructor
     *
     * @param banner the banner to add this to
     */
    public Input(InputBanner banner){
        this();
        this.banner = banner;

    }

    /**
     * Gets the length of the longest item.
     *
     * @return integer of the longest item
     */
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

    /**
     * Compares this to other JComponents based on the location of them. Going from left to right
     * and then down like lines on a book
     *
     * @param o the other object to compare to
     * @return -1 if it is less 0 if they are equal, 1 if it is more
     * @throws ClassCastException
     */
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

    /**
     * This is the first action that should be preformed at the beginning of each action. It will
     * resize the object and then set this one as the last visible
     *
     * @param banner the banner that it has been added to
     * @param item   the item that is to be evaluated
     */
    public void firstAction(InputBanner banner, Input item){
        setSize(longestItem(), getHeight());
        revalidate();
        banner.setLastVisible(item);
    }

    /**
     * This is the same as above only it defaults the input item to this
     * @param banner the banner that the item has been added to
     */
    public void firstAction(InputBanner banner){
        setSize(longestItem(), getHeight());
        revalidate();
        banner.setLastVisible(this);
    }

    /**
     * The last action that should be preformed on button push, it should add the new input.
     * @param banner the banner that this has been added to.
     * @param newInput the new input to show
     */
    public void lastAction(InputBanner banner, JComponent newInput){
        if(newInput != null) {
            banner.setLastVisible(newInput);
            this.revalidate();
        } else{
            throw new NullPointerException("The item was null");
        }
    }

    /**
     * @return the InputBanner that this has been added to
     */
    public InputBanner getBanner(){
        return banner;
    }

    /**
     * Sets the input banner that this should be added to
     * @param banner
     */
    public void setBanner(InputBanner banner) {
        this.banner = banner;
    }

    /**
     * @return the order that this object has created
     */
    public Order getOrder() {
        throw new NullPointerException("Error wrong one " + this);
        //System.out.println("we got the wrong one");
        //return order;
    }

    /**
     * @param order sets the order that this should Input should edit
     */
    public void setOrder(Order order){
        this.order = order;
    }
}