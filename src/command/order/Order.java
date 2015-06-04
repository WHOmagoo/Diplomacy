package command.order;

import map.Country;

import java.util.ArrayList;

public class Order implements Comparable{
    int attackPower = 0;
    int defensePower = 1;
    Country orderFrom;
    Country orderCanceledBy = null;
    ArrayList<Country> attackedBy = new ArrayList<Country>();
    boolean valid = false;

    public Order(Country orderFrom) {
        this.orderFrom = orderFrom;
    }

    public Country getOrderFrom() {
        return orderFrom;
    }


    /*public void setCanceledBy(Country c) {
        if (c.getOrder() instanceof Attack) {
            Attack attackCanceling = (Attack) c.getOrder();
            if (attackCanceling.getAttacking() == orderFrom) {
                orderCanceledBy = c;
            } else {
                System.out.println("Wrong order cnaceled");
            }
        } else {
            //TODO make this throw an error later when methods have been set better.
            System.out.println("Wrong order canceled by");
        }
    }*/

    public void addAttackedBy(Country c) {
        attackedBy.add(c);
    }

    public ArrayList<Country> getAttackedBy() {
        return attackedBy;
    }

    public boolean isAttacked() {
        if (attackedBy.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

   /* public boolean isCanceled() {
        if (orderCanceledBy == null) {
            return false;
        } else {
            return true;
        }
    }*/

    /*public boolean isCanceledBy(Order order) {
        if (order instanceof Attack) {
            Attack temp = (Attack) order;
            if (temp.cancels(this)) {
                if (!temp.isCanceled()) {
                    return true;
                }
            }
        }
        return false;
    }*/

    public void setValid() {
        valid = true;
    }

    public void setInvalid() {
        valid = false;
    }

    public void setValid(Boolean aBoolean) {
        valid = aBoolean;
    }

    public boolean isValid() {
        return valid;
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
}