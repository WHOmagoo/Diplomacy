package command.order;

public class Order {
    int attackPower = 0;
    int defensePower = 1;

    public Order(){
    }

    /*public Order(Country orderFrom, Country orderTo, OrderType orderType) throws IllegalArgumentException {
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
    }*/
}