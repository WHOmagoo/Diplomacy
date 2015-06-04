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

    public Country getAttacking() {
        return attacking;
    }

    public boolean isAttacking(Country c) {
        return attacking == c;
    }

    public String toString(){
        return orderFrom + " attacks " + attacking;
    }

    public boolean cancels(Attack otherAttack) {
        return otherAttack.getOrderFrom() == attacking;
    }
}
