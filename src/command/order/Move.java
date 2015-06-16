/**
 * Move.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package command.order;

import map.Country;

public class Move extends Order{
    private static final long serialVersionUID = 65538L;
    private Country movingTo;
    private boolean moveLooped;

    /**
     * The constructor for the movement of a country
     *
     * @param orderFrom the country from where this order originates from
     */
    public Move(Country orderFrom) {
        super(orderFrom);
    }

    /**
     * @return the string representation of this object
     */
    public String toString() {
        return orderFrom + " moves to " + movingTo;
    }

    /**
     * @return the country that the country is moving to
     */
    public Country getMovingTo() {
        return movingTo;
    }

    /**
     * @param movingTo the country that this object is moving to
     */
    public void setMovingTo(Country movingTo) {
        this.movingTo = movingTo;
    }

    /**
     * This is a condition where two or more units are moving to each others locations in a circle.
     * @return true if it is moveLooped otherwise false
     */
    public boolean isMoveLooped() {
        return moveLooped;
    }

    /**
     * Sets this as moveLooped when called.
     */
    public void setMoveLooped() {
        moveLooped = true;
    }

    /**
     * Resets the fields of this object except for where the order is from
     */
    public void reset() {
        super.reset();
        movingTo = null;
        moveLooped = false;
    }

/**
*   private void writeObject(java.io.ObjectOutputStream out) throws IOException {
*       out.writeUTF(movingTo.toString());
*   }
*
*   private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
*       movingTo = StringToCountry.getCountry((String) in.readObject());
*   }
*
*   private void readObjectNoData() throws ObjectStreamException {
*       System.out.println("This shouldn't have been called because it was not handled");
*   }
*/
}
