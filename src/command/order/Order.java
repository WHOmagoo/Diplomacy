package command.order;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import map.Country;
import server.StringToCountry;

public class Order implements Comparable, Serializable {
    private static final long serialVersionUID = 65535L;
    transient int defensePower;
    transient Country orderFrom;
    transient Boolean valid;
    transient boolean succeeded;
    transient boolean isBounced;

    public Order(Country orderFrom) {
        defensePower = 1;
        valid = null;
        succeeded = false;
        isBounced = false;
        this.orderFrom = orderFrom;
    }

    public Country orderFrom() {
        return orderFrom;
    }

    public void setValid() {
        valid = true;
    }

    public void setInvalid() {
        valid = false;
    }

    public void setValid(Boolean aBoolean) {
        valid = aBoolean;
    }

    public Boolean isValid() {
        return valid;
    }

    void increaseDefense(Defend defenseCommand) {
        if (defenseCommand.getDefending() == orderFrom) {
            if (orderFrom.contains(defenseCommand.orderFrom())) {
                defensePower++;
            }
        }
    }

    @Override
    public int compareTo(Object obj) {
        if (obj instanceof Order) {
            Order o = (Order) obj;
            return o.orderFrom().compareTo(this.orderFrom);
        } else{
            throw new ClassCastException("Wrong type");
        }
    }

    public void setSucceeded(boolean succeeded) {
        if (valid == Boolean.TRUE && succeeded) {
            this.succeeded = succeeded;
        } else if (!succeeded) {
            this.succeeded = succeeded;
        } else {
            System.out.println("Invalid set to successful" + this);
            throw new Error("Wrong");
        }
    }

    public boolean succeeds() {
        return succeeded;
    }

    public int getDefensePower(){
        return defensePower;
    }

    public boolean isBounced() {
        return isBounced;
    }

    public void setBounced(boolean aBoolean) {
        isBounced = aBoolean;
    }

    @Override
    public String toString() {
        return orderFrom + " holds";
    }

    public void reset() {
        valid = null;
        succeeded = false;
        isBounced = false;
        defensePower = 1;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeUTF(orderFrom.toString());
        out.writeObject(null);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        orderFrom = StringToCountry.getCountry((String) in.readObject());
    }

    private void readObjectNoData() throws ObjectStreamException {
        System.out.println("This shouldn't have been called because it was not handled");
    }
}