package command.order;

import map.Country;

public class Attack extends Order {
    private Country attacking;

    public Attack(Country attacking){
        this.attacking = attacking;
        attackPower++;
    }

    public Attack(){
    }

    public void setCountryToAttack(Country countryToAttack){
        attacking = countryToAttack;
    }

    public String toString(){
        return attacking.toString();
    }
}
