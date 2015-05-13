public class Order {
    private Country orderFrom;
    private Country orderTo;
    private Country supporting;
    private OrderTypes orderType;

    public Order(Country orderFrom, Country orderTo, OrderTypes orderType){
        if(orderType != OrderTypes.ATTACK && orderType != OrderTypes.MOVE) {
            throw new IllegalArgumentException("This must use an attack or a move command");
        }
            this.orderFrom = orderFrom;
            this.orderTo = orderTo;
            this.orderType = orderType;
    }

    public Order(Country orderFrom, Country supporting, Country orderTo, OrderTypes orderType){
        if(orderType != OrderTypes.SUPPORT){
            throw new IllegalArgumentException("This must use a support command");
        }
        this.orderFrom = orderFrom;
        this.supporting = supporting;
        this.orderTo = orderTo;
        this.orderType = orderType;
    }

    public Order(OrderTypes orderType){
        if(orderType != OrderTypes.HOLD){
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
