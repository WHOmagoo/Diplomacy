package command.order;

import map.Country;

/**
 * Created by Hugh on 5/30/2015.
 */
public class Support extends Order {
    private Country supporting;
    private Country attacking;

    public Support(){

    }

    public Support(Country supporting, Country attacking){
        this.supporting = supporting;
        this.attacking = attacking;
    }

    public void setSupporting(Country countryToSupport){
        this.supporting = countryToSupport;
    }

    public void setAttacking(Country countryToAttack){
        this.attacking = countryToAttack;
    }
}
