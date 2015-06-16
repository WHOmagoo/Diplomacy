/**
 * Hold.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package command.order;

import map.Country;

public class Hold extends Order {
    private static final long serialVersionUID = 65535L;

    /**
     * This creates a hold object for the country.
     *
     * @param countryHolding the country that is holding
     */
    public Hold(Country countryHolding) {
        super(countryHolding);
    }

    /**
     * @return the string representation of this object
     */
    public String toString() {
        return orderFrom + " holds";
    }
}
