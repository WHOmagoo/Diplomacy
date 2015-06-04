package command.order;

import map.Country;

/**
 * Created by Hugh on 5/30/2015.
 */
public class Hold extends Order {
    public Hold(Country countryHolding) {
        super(countryHolding);
    }

    public String toString() {
        return orderFrom + " holds";
    }
}
