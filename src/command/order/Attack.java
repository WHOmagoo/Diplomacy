package command.order;

import java.io.IOException;
import map.Country;

public class Attack extends Order {
    int attackPower = 1;
    private Country attacking;
    private boolean isAttackLooped = false;

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
                if (orderSupporting.getAttacking().contains(orderSupporting.orderFrom())) {
                    attackPower++;
                }
            }
        }
    }

    public int getAttackPower() {
        return attackPower;
    }

    public boolean cancels(Order order) {
        return order.orderFrom() == attacking;
    }

    public boolean overpowers() {
        if (attacking.isOccupied()) {
            if (attackPower > attacking.getOrder().getDefensePower()) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean isAttackLooped() {
        return isAttackLooped;
    }

    public void setAttackLooped(Boolean aBoolean) {
        isAttackLooped = aBoolean;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(orderFrom);
        out.writeObject(attacking);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        orderFrom = (Country) in.readObject();
        attacking = (Country) in.readObject();
    }
}