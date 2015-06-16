/**
 * Attack.java
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

public class Attack extends Order implements Serializable {
    private static final long serialVersionUID = 65536L;
    int attackPower = 1;
    private Country attacking;
    private boolean isAttackLooped = false;

    /**
     * Creates an attack object and stores where the order originates from
     *
     * @param orderFrom where the order comes from
     */
    public Attack(Country orderFrom) {
        super(orderFrom);
    }

    /**
     * @param countryToAttack the country that this order is attacking
     */
    public void setCountryToAttack(Country countryToAttack){
        attacking = countryToAttack;
    }

    /**
     * @return the country that this order is attacking
     */
    public Country getAttacking() {
        return attacking;
    }

    /**
     * @return the string representation of this object
     */
    public String toString(){
        return orderFrom + " attacks " + attacking;
    }

    /**
     * Will add to the attackPower if the supporting order is attacking the same country
     *
     * @param orderSupporting the supporting order that adds to the attackPower
     */
    public void addAttackPower(Support orderSupporting) {
        if (orderSupporting.getAttacking() == attacking) {
            if (orderSupporting.getSupporting() == orderFrom) {
                if (orderSupporting.getAttacking().borders(orderSupporting.orderFrom())) {
                    attackPower++;
                }
            }
        }
    }

    /**
     * @return gets the attack power of this country
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * @return true if this attackPower is greater than the defense power of the attacking country,
     *      otherwise returns false
     */
    public boolean overpowers() {
        if (attacking.isOccupied()) {
            if (attackPower > attacking.getOrder().getDefensePower()) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     * An attack loop is when two or more countries attack each other in a circle
     * @return will return true if the field attackField has been set to true otherwise false.
     */
    public boolean isAttackLooped() {
        return isAttackLooped;
    }

    /**
     * @param aBoolean weather or not this order has been attack looped
     */
    public void setAttackLooped(Boolean aBoolean) {
        isAttackLooped = aBoolean;
    }

/**
*   private void writeObject(java.io.ObjectOutputStream out) throws IOException {
*       out.writeUTF(attacking.toString());
*   }
*
*   private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
*       attacking = StringToCountry.getCountry(in.readUTF());
*   }
*
*   private void readObjectNoData() throws ObjectStreamException {
*       System.out.println("This shouldn't have been called because it was not handled");
*   }
*/
}