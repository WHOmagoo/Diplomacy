package command.order;

import map.Country;

public class Move extends Order{
    private Country movingTo;
    private boolean moveLooped;

    public Move(Country orderFrom, Country movingTo) {
        super(orderFrom);
        this.movingTo = movingTo;
        moveLooped = false;
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

    public void reset() {
        super.reset();
        movingTo = null;
        moveLooped = false;
    }
}
