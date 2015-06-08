package command.order;

import map.Country;

public class Order implements Comparable{
    int defensePower = 1;
    Country orderFrom;
    Boolean valid = null;
    boolean succeeded = false;
    boolean isBounced = false;

    public Order(Country orderFrom) {
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
        try {
            if (valid) {
                if (defenseCommand.getDefending() == orderFrom) {
                    if (defenseCommand.getDefending().contains(orderFrom)) {
                        defensePower++;
                    }
                }
            }
        } catch (NullPointerException np) {
            throw new NullPointerException("This order has not yet been validated (" + this + ")");
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
        this.succeeded = succeeded;
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

}