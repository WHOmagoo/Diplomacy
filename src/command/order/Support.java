/**
 * Support.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package command.order;

import map.Country;

public class Support extends Order {
    private static final long serialVersionUID = 65538L;
    private Country supporting;
    private Country attacking;

    /**
     * The constructor for the support class
     *
     * @param orderFrom the country from where this order originates from.
     */
    public Support(Country orderFrom) {
        super(orderFrom);
    }

    /**
     * @return the country that this order is supporting
     */
    public Country getSupporting() {
        return supporting;
    }

    /**
     * @param countryToSupport sets the country that this country is supporting the this input
     */
    public void setSupporting(Country countryToSupport){
        this.supporting = countryToSupport;
    }

    /**
     * @return the country that this order is attacking
     */
    public Country getAttacking() {
        return attacking;
    }

    /**
     * @param countryToAttack sets the country that this order is attacking
     */
    public void setAttacking(Country countryToAttack){
        this.attacking = countryToAttack;
    }

    /**
     * Will attempt to increase the attack power of the country it is supporting.
     *
     * @return boolean, true if attack power was increased otherwise false.
     */
    public boolean increaseAttackPower() {
        if (valid) {
            if (supporting.getOrder() instanceof Attack) {
                Attack temp = (Attack) supporting.getOrder();
                if (temp.getAttacking() == attacking) {
                    temp.addAttackPower(this);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return a string version of this object
     */
    @Override
    public String toString() {
        return orderFrom + " supports " + supporting + "'s attack on " + attacking;
    }

    /**
     * resets this order to nothing, will keep the country that this order is from.
     */
    public void reset() {
        super.reset();
        supporting = null;
        attacking = null;
    }

/**
 * This is intended for later use when the server has been implemented.
*   private void writeObject(java.io.ObjectOutputStream out) throws IOException {
*       out.writeUTF(supporting.toString());
*       out.writeUTF(attacking.toString());
*   }
*
*   private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
*       supporting = StringToCountry.getCountry(in.readUTF());
*       attacking = StringToCountry.getCountry(in.readUTF());
*   }
*
*   private void readObjectNoData() throws ObjectStreamException {
*       System.out.println("This shouldn't have been called because it was not handled");
*   }
*/
}
