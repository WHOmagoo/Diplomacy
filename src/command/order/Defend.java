package command.order;

import map.Country;

public class Defend extends Order {
    private Country defending;

    public Defend(Country orderFrom, Country countryBeingDefended) {
        super(orderFrom);
        this.defending = countryBeingDefended;
    }

    public Defend(Country orderFrom) {
        super(orderFrom);
    }

    public Country getDefending() {
        return defending;
    }

    public void setDefending(Country defending) {
        this.defending = defending;
    }

    public void increaseDefense() throws NullPointerException {
        try {
            if (valid) {
                defending.getOrder().increaseDefense(this);
            }
        } catch (NullPointerException np) {
            throw new NullPointerException("This order has not yet been validated (" + this + ")");
        }
    }

    public String toString() {
        return orderFrom + " defends " + defending;
    }

    public void reset() {
        super.reset();
        defending = null;
    }
}
