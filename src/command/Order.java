package command;//TODO create new sub classes for each type of order, like an attack or support class.

import map.Country;

public class Order {
    private Country orderFrom;
    private Country orderTo;
    private Country supporting;
    private OrderType orderType;

    public Order(Country orderFrom, Country orderTo, OrderType orderType) throws IllegalArgumentException {
        if(orderType != OrderType.ATTACK && orderType != OrderType.MOVE) {
            throw new IllegalArgumentException("This must use an attack or a move command");
        }
            this.orderFrom = orderFrom;
            this.orderTo = orderTo;
            this.orderType = orderType;
    }

    public Order(Country orderFrom, Country supporting, Country orderTo, OrderType orderType) throws IllegalArgumentException {
        if(orderType != OrderType.SUPPORT){
            throw new IllegalArgumentException("This must use a support command");
        }
        this.orderFrom = orderFrom;
        this.supporting = supporting;
        this.orderTo = orderTo;
        this.orderType = orderType;
    }

    public Order(OrderType orderType){
        if(orderType != OrderType.HOLD){
            throw new IllegalArgumentException("This must use a hold command");
        }
        this.orderType = orderType;
    }

    public Country getCountryOfOrigin(){
        return orderFrom;
    }

    public void clear(){
        orderFrom = null;
        orderTo = null;
        supporting = null;
        orderType = null;
    }
}