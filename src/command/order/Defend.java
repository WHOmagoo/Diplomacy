package command.order;

import java.io.IOException;
import java.io.ObjectStreamException;
import map.Country;
import server.StringToCountry;

public class Defend extends Order {
    private static final long serialVersionUID = 65537L;
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

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeUTF(defending.toString());
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        defending = StringToCountry.getCountry(in.readUTF());
    }

    private void readObjectNoData() throws ObjectStreamException {
        System.out.println("This shouldn't have been called because it was not handled");
    }
}
