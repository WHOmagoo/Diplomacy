package command.order;

import java.io.IOException;
import java.io.ObjectStreamException;
import map.Country;
import server.StringToCountry;

public class Move extends Order{
    private static final long serialVersionUID = 65538L;
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

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeUTF(movingTo.toString());
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        movingTo = StringToCountry.getCountry((String) in.readObject());
    }

    private void readObjectNoData() throws ObjectStreamException {
        System.out.println("This shouldn't have been called because it was not handled");
    }
}
