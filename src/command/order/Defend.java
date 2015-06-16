/**
 * Defend.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package command.order;

import map.Country;

public class Defend extends Order {
    private static final long serialVersionUID = 65537L;
    private Country defending;

    /**
     * The constructor for defend
     *
     * @param orderFrom the country from which this originates.
     */
    public Defend(Country orderFrom) {
        super(orderFrom);
    }

    /**
     * @return the country that this order is defending
     */
    public Country getDefending() {
        return defending;
    }

    /**
     * @param defending the country that this order is defending
     */
    public void setDefending(Country defending) {
        this.defending = defending;
    }

    /**
     * Increases the defense of the country it defends.
     *
     * @return true if it sucessfully increased the defense power otherwise false
     */
    public boolean increaseDefense() {
            if (valid) {
                defending.getOrder().increaseDefense(this);
                succeeded = true;
                return true;
            } else {
                return false;
            }
    }

    /**
     * @return the string representation of this object.
     */
    public String toString() {
        return orderFrom + " defends " + defending;
    }

    /**
     * will reset the fields of this object, except for the order from.
     */
    public void reset() {
        super.reset();
        defending = null;
    }

/**
*   private void writeObject(java.io.ObjectOutputStream out) throws IOException {
*       out.writeUTF(defending.toString());
*   }
*
*   private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
*       defending = StringToCountry.getCountry(in.readUTF());
*   }
*
*   private void readObjectNoData() throws ObjectStreamException {
*       System.out.println("This shouldn't have been called because it was not handled");
*   }
*/
}
