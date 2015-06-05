package command.order;

import map.Country;

public class Order implements Comparable{
    int defensePower = 1;
    Country orderFrom;
    Boolean valid = null;
    boolean succeded = false;

    public Order(Country orderFrom) {
        this.orderFrom = orderFrom;
    }

    public Country getOrderFrom() {
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
            return o.getOrderFrom().compareTo(this.orderFrom);
        } else{
            throw new ClassCastException("Wrong type");
        }
    }

    void setSucceded() {
        succeded = true;
    }

    public boolean succeded() {
        return succeded;
    }
}