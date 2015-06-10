package command.order;

import map.Country;

public class Order implements Comparable{
    int defensePower;
    Country orderFrom;
    Boolean valid;
    boolean succeeded;
    boolean isBounced;

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
        } else if (valid != Boolean.TRUE && !succeeded) {
            this.succeeded = succeeded;
        } else {
            System.out.println("Invalid set to successful" + this);
            throw new Error("Wankers");
        }
        /*} else {
            //May change this to a println if it is the wrong type.
            throw new NullPointerException("Cannot set this order as successful with false validity.\n"
                    + this);
        }*/
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

}