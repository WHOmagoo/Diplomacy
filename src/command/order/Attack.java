package command.order;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import map.Country;
import server.StringToCountry;

public class Attack extends Order implements Serializable {
    private static final long serialVersionUID = 65536L;
    transient int attackPower = 1;
    transient private Country attacking;
    transient private boolean isAttackLooped = false;

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
        out.writeUTF(attacking.toString());
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        attacking = StringToCountry.getCountry(in.readUTF());
    }

    private void readObjectNoData() throws ObjectStreamException {
        System.out.println("This shouldn't have been called because it was not handled");
    }
}