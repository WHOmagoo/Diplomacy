package command.order;

import map.Country;

public class Order {
    int attackPower = 0;
    int defensePower = 1;
    Country orderFrom;
    Country orderCanceledBy = null;

    public Order(Country orderFrom) {
        this.orderFrom = orderFrom;
    }

    public Country getOrderFrom() {
        return orderFrom;
    }

    public Country canceledBy() {
        return orderCanceledBy;
    }

    public void setCanceledBy(Country c) {
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
    }

    public void setValid() {

    }

    public boolean isValid() {
        if (orderCanceledBy == null) {
            return true;
        } else {
            return false;
        }
    }
}