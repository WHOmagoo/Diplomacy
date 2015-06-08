package command.order;

import map.Country;

public class Move extends Order{
    private volatile Country movingTo;
    private boolean moveLooped = false;

    public Move(Country orderFrom, Country movingTo) {
        super(orderFrom);
        this.movingTo = movingTo;
    }

    public Move(Country orderFrom) {
        super(orderFrom);
    }

    public String toString() {
        return orderFrom + " moves to " + movingTo;
    }

    public Country getMovingTo() {
        return movingTo;
    }

    public void setMovingTo(Country movingTo) {
        this.movingTo = movingTo;
    }

    public boolean isMoveLooped() {
        return moveLooped;
    }

    public void setMoveLooped() {
        moveLooped = true;
    }
}
