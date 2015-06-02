package command.order;

import map.Country;

public class Move extends Order{
    private final int attackPower = 0;
    private Country movingTo;

    public Move(){
    }

    public Move(Country movingTo){
        this.movingTo = movingTo;
    }

    public void setMovingTo(Country movingTo) {
        this.movingTo = movingTo;
    }
}
