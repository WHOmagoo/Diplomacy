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

    public boolean increaseDefense() throws NullPointerException {
            if (valid) {
                defending.getOrder().increaseDefense(this);
                succeeded = true;
                return true;
            } else {
                return false;
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
