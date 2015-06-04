package command.order;

import map.Country;

/**
 * Created by Hugh on 5/30/2015.
 */
public class Defend extends Order {
    private Country countryBeingDefended;

    public Defend(Country orderFrom, Country countryBeingDefended) {
        super(orderFrom);
        this.countryBeingDefended = countryBeingDefended;
    }

    public Defend(Country orderFrom) {
        super(orderFrom);
    }

    public void setCountryBeingDefended(Country countryBeingDefended){
        this.countryBeingDefended = countryBeingDefended;
    }

    public String toString() {
        return orderFrom + " defends " + countryBeingDefended;
    }
}
