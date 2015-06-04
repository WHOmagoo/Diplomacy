package command.order;

import map.Country;

public class Attack extends Order {
    private Country attacking;

    public Attack(Country orderFrom, Country attacking) {
        super(orderFrom);
        this.attacking = attacking;
        attackPower++;
    }

    public Attack(Country orderFrom) {
        super(orderFrom);
    }

    public void setCountryToAttack(Country countryToAttack){
        attacking = countryToAttack;
    }

    public String toString(){
        return orderFrom + " attacks " + attacking;
    }
}
