import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Country extends JButton implements ActionListener, Comparable{
    private String name;
    private Border borders;
    private SecondDegreeBorder secondDegreeBorders;
    private Team occupiedBy = Team.NULL;
    private TileTypes tileType;
    private Point originalLoctaion;

    public Country(String name, int locationX, int locationY) {
        this.name = name;
        originalLoctaion = new Point(locationX, locationY);
        setLocation(locationX, locationY);
        declarationConstants();
    }

    public Country(String name, Point location){
        this.name = name;
        originalLoctaion = location;
        setLocation(location);
        declarationConstants();
    }

    public Country(String name, Point location, TileTypes tileType) {
        this.name = name;
        this.tileType = tileType;
        originalLoctaion = location;
        setLocation(location);
        declarationConstants();
    }

    @Deprecated
    public Country(String name) {
        this.name = name;
        declarationConstants();
    }

    private void declarationConstants() {
        setSize(40, 40);
        setOpaque(true);
        setBorder(null);
        setContentAreaFilled(false);
        addActionListener(this);
    }

    public void setOccupiedBy(Team occupiedBy) {
        this.occupiedBy = occupiedBy;
    }

    public String getName(){
        return name;
    }

    public void resetPosition(){
        super.setLocation(originalLoctaion);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public String toString() {
        return name;
        /*
        return "Country: " + name + ", Borders: " + borders
                + "Original Location: " + originalLoctaion;*/
    }

    @Override
    public int compareTo(Object country) {
        try {
            return this.name.compareTo(((Country) country).getName());
        } catch (Exception e){
            throw new IllegalArgumentException("Must compare two countries");
        }
    }

    public Border getBorders() {
        return borders;
    }

    public void setBorders(Border borders) {
        this.borders = borders;
    }

    @Deprecated
    public void setBorders(Country[] countries) {
        borders = new Border(this, countries);
    }

    public void calculateSecondDegreeBorders() {
        if (borders == null) {
            throw new NullPointerException("The borders have not yet been set");
        }
        secondDegreeBorders = new SecondDegreeBorder(this, borders);
    }

    public boolean isOccupied() {
        if (this.occupiedBy != Team.NULL) {
            return true;
        } else {
            return false;
        }
    }

    public boolean contains(Country country) {
        for (Country c : borders) {
            if (c == country) {
                return true;
            }
        }

        return false;
    }

    public TileTypes getTileType() {
        return tileType;
    }
}
