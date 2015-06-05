package command.order;

import map.Country;

public class Attack extends Order {
    int attackPower = 0;
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

    public String toString(){
        return orderFrom + " attacks " + attacking;
    }

    public void addAttackPower(Support orderSupporting) {
        if (orderSupporting.getAttacking() == attacking) {
            if (orderSupporting.getSupporting() == orderFrom) {
                if (orderSupporting.getAttacking().contains(orderSupporting.getOrderFrom())) {
                    attackPower++;
                }
            }
        }
    }

    public int getAttackPower() {
        return attackPower;
    }

    public boolean cancels(Order order) {
        return order.getOrderFrom() == attacking;
    }

    public boolean overpowers() {
        try {
            if (attackPower > attacking.getOrder().getDefensePower()) {
                return true;
            } else {
                return false;
            }
        } catch (NullPointerException np){
            if(attacking.isOccupied()){
                if(attackPower > 1){
                    return true;
                } else{
                    return false;
                }
            } else{
                return true;
            }
        }
    }
}