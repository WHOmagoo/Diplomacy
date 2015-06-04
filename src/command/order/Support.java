package command.order;

import map.Country;

/**
 * Created by Hugh on 5/30/2015.
 */
public class Support extends Order {
    private Country supporting;
    private Country attacking;

    public Support(Country orderFrom, Country supporting, Country attacking) {
        super(orderFrom);
        this.supporting = supporting;
        this.attacking = attacking;
    }

    public Support(Country orderFrom) {
        super(orderFrom);
    }

    public void setSupporting(Country countryToSupport){
        this.supporting = countryToSupport;
    }

    public void setAttacking(Country countryToAttack){
        this.attacking = countryToAttack;
    }

    @Override
    public String toString() {
        return orderFrom + " supports " + supporting + "'s attack on " + attacking;
    }
}
