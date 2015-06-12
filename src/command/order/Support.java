package command.order;

import java.io.IOException;
import java.io.ObjectStreamException;
import map.Country;
import server.StringToCountry;

public class Support extends Order {
    private static final long serialVersionUID = 65538L;
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

    public Country getSupporting() {
        return supporting;
    }

    public void setSupporting(Country countryToSupport){
        this.supporting = countryToSupport;
    }

    public Country getAttacking() {
        return attacking;
    }

    public void setAttacking(Country countryToAttack){
        this.attacking = countryToAttack;
    }

    public boolean increaseAttackPower() throws NullPointerException {
        if (valid) {
            if (supporting.getOrder() instanceof Attack) {
                Attack temp = (Attack) supporting.getOrder();
                if (temp.getAttacking() == attacking) {
                    temp.addAttackPower(this);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return orderFrom + " supports " + supporting + "'s attack on " + attacking;
    }

    public void reset() {
        super.reset();
        supporting = null;
        attacking = null;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeUTF(supporting.toString());
        out.writeUTF(attacking.toString());
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        supporting = StringToCountry.getCountry(in.readUTF());
        attacking = StringToCountry.getCountry(in.readUTF());
    }

    private void readObjectNoData() throws ObjectStreamException {
        System.out.println("This shouldn't have been called because it was not handled");
    }
}
