import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Country extends JButton implements ActionListener, Comparable{
    private String name;
    private Border borders;
    private SecondDegreeBorder secondDegreeBorders;
    private Team team = Team.NULL;
    private UnitType unitType = UnitType.EMPTY;
    private TileTypes tileType;
    private Point originalLoctaion;

    private Country() {
        setSize(40, 40);
        setOpaque(true);
        setBorder(null);
        setContentAreaFilled(false);
        addActionListener(this);
    }

    public Country(String name, Point location, TileTypes tileType) {
        this();
        this.name = name;
        this.tileType = tileType;
        originalLoctaion = location;
        setLocation(location);
    }

    public Country(String name, int locationX, int locationY) {
        this();
        this.name = name;
        originalLoctaion = new Point(locationX, locationY);
        setLocation(locationX, locationY);
    }

    public Country(String name, Point location){
        this();
        this.name = name;
        originalLoctaion = location;
        setLocation(location);
    }

    @Deprecated
    public Country(String name) {
        this();
        this.name = name;
    }

    public void setTeam(Team team) {
        this.team = team;
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

    @Deprecated
    public void setBorders(Country[] countries) {
        borders = new Border(this, countries);
    }

    public void setBorders(Border borders) {
        this.borders = borders;
    }

    public void calculateSecondDegreeBorders() {
        if (borders == null) {
            throw new NullPointerException("The borders have not yet been set");
        }
        secondDegreeBorders = new SecondDegreeBorder(this, borders);
    }

    public SecondDegreeBorder getSecondDegreeBorders() {
        return secondDegreeBorders;
    }

    public boolean isOccupied() {
        if (this.team != Team.NULL) {
            return true;
        } else {
            return false;
        }
    }

    public void calculateCostal() {
        for (Country c : borders) {
            if (c.getTileType() == TileTypes.Water && tileType == TileTypes.Landlocked) {
                tileType = TileTypes.Costal;
                break;
            }
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

    public ArrayList<Country> getOccupiedNeighbors() {
        ArrayList<Country> occupiedNeighbors = new ArrayList();
        for (Country country : borders) {
            if (country.isOccupied()) {
                occupiedNeighbors.add(country);
            }
        }

        return occupiedNeighbors;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public void refreshGraphics() {
        setIcon(team.getIcon(unitType));
        setRolloverIcon(team.getRolloverIcon(unitType));
        setSelectedIcon(team.getSelectedIcon(unitType));
        repaint();
    }
}
