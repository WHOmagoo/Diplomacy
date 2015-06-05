package command.order;

import map.Country;

public class Move extends Order{
    private Country movingTo;

    public Move(Country orderFrom, Country movingTo) {
        super(orderFrom);
        this.movingTo = movingTo;
    }

    public Move(Country orderFrom) {
        super(orderFrom);
    }

    public void setMovingTo(Country movingTo) {
        this.movingTo = movingTo;
    }

    public String toString() {
        return orderFrom + " moves to " + movingTo;
    }
}
