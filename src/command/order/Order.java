package command.order;

import map.Country;

public class Order {
    int attackPower = 0;
    int defensePower = 1;
    Country orderFrom;

    public Order(Country orderFrom) {
        this.orderFrom = orderFrom;
    }

    public Country getOrderFrom() {
        return orderFrom;
    }
}