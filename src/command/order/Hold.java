package command.order;

import map.Country;

public class Hold extends Order {
    public Hold(Country countryHolding) {
        super(countryHolding);
    }

    public String toString() {
        return orderFrom + " holds";
    }
}
