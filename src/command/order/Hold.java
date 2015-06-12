package command.order;

import map.Country;

public class Hold extends Order {
    private static final long serialVersionUID = 65535L;
    public Hold(Country countryHolding) {
        super(countryHolding);
    }

    public String toString() {
        return orderFrom + " holds";
    }
}
