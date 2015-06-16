/**
 * Order.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package command.order;

import java.io.Serializable;
import map.Country;

public class Order implements Comparable, Serializable {
    private static final long serialVersionUID = 65535L;
    int defensePower;
    Country orderFrom;
    Boolean valid;
    boolean succeeded;
    boolean isBounced;

    /**
     * The constructor for an order object
     *
     * @param orderFrom where the order is from
     */
    public Order(Country orderFrom) {
        defensePower = 1;
        valid = null;
        succeeded = false;
        isBounced = false;
        this.orderFrom = orderFrom;
    }

    /**
     * @return the country where this order is from
     */
    public Country orderFrom() {
        return orderFrom;
    }

    /**
     * Sets this object as valid (the order can be executed)
     */
    public void setValid() {
        valid = true;
    }

    /**
     * Sets this object as invalid (the order can't be executed)
     */
    public void setInvalid() {
        valid = false;
    }

    /**
     * @param aBoolean of weather or not this order is valid
     */
    public void setValid(Boolean aBoolean) {
        valid = aBoolean;
    }

    /**
     * @return the validity of this order
     */
    public Boolean isValid() {
        return valid;
    }

    /**
     * Increases the defense power if the correct order is added.
     * @param defenseCommand the command that raises this defense power
     */
    void increaseDefense(Defend defenseCommand) {
        if (defenseCommand.getDefending() == orderFrom) {
            if (orderFrom.borders(defenseCommand.orderFrom())) {
                defensePower++;
            }
        }
    }

    /**
     * Compares this order to another sorting them in alphabetical order based on the name of the
     * country that this order is from
     * @param obj the object to compare to
     * @return returns an integer value of -1 if this comes before obj in ordering, 1 if it comes
     *  after and 0 if they are equal.
     */
    @Override
    public int compareTo(Object obj) {
        if (obj instanceof Order) {
            Order o = (Order) obj;
            return o.orderFrom().compareTo(this.orderFrom);
        } else{
            throw new ClassCastException(obj + " was of the wrong type");
        }
    }

    /**
     * @param succeeded true if the order succeeded (Attack successfully or defend another country.)
     *                  else false
     */
    public void setSucceeded(boolean succeeded) {
        if (valid == Boolean.TRUE && succeeded) {
            this.succeeded = succeeded;
        } else if (!succeeded) {
            this.succeeded = succeeded;
        } else {
            throw new Error("Invalid set to successful " + this);
        }
    }

    /**
     * @return true if this order succeeded else false
     */
    public boolean succeeds() {
        return succeeded;
    }

    /**
     * @return the defense power of this country (how many units are defending this country)
     */
    public int getDefensePower(){
        return defensePower;
    }

    /**
     * Bounced is when two countries are trying to go to the same spot, they bounce and get their
     * orders canceled
     * @return true if this order has been bounced else false
     */
    public boolean isBounced() {
        return isBounced;
    }

    /**
     * @param aBoolean sets whether this has been bounced or not
     */
    public void setBounced(boolean aBoolean) {
        isBounced = aBoolean;
    }

    /**
     * @return the string representation of this object
     */
    @Override
    public String toString() {
        return orderFrom + " holds";
    }

    /**
     * Resets the fields of this object except for orderFrom
     */
    public void reset() {
        valid = null;
        succeeded = false;
        isBounced = false;
        defensePower = 1;
    }

/**
*   private void writeObject(java.io.ObjectOutputStream out) throws IOException {
*       out.writeUTF(orderFrom.toString());
*       out.writeObject(null);
*   }
*
*   private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
*       orderFrom = StringToCountry.getCountry((String) in.readObject());
*   }
*
*   private void readObjectNoData() throws ObjectStreamException {
*       System.out.println("This shouldn't have been called because it was not handled");
*   }
*/
}