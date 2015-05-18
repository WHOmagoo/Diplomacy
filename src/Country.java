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
    private TileType tileType;
    private Point originalLocation;
    private javax.swing.border.Border border = null;
    private Map mapAssociation;
    private Info info;

    private Country() {
        setSize(40, 40);
        setOpaque(true);
        setBorder(border);
        setContentAreaFilled(false);
        addActionListener(this);
        setDisabledIcon(null);
    }

    public Country(String name, Point location, TileType tileType) {
        this();
        this.name = name;
        this.tileType = tileType;
        originalLocation = location;
        setLocation(location);
    }

    public Country(String name, int locationX, int locationY) {
        this();
        this.name = name;
        originalLocation = new Point(locationX, locationY);
        setLocation(locationX, locationY);
    }

    public Country(String name, Point location){
        this();
        this.name = name;
        originalLocation = location;
        setLocation(location);
    }

    public void setOccupiedBy(Team team, UnitType unitType) {
        if (team == Team.NULL && unitType != UnitType.EMPTY) {
            throw new IllegalArgumentException("When setting team to null, unit type must also be null");
        }

        if (unitType == UnitType.EMPTY && team != Team.NULL) {
            throw new IllegalArgumentException("When setting unit type to null, team must also be null");
        }

        if (unitType == UnitType.NAVY && tileType == TileType.Landlocked) {
            throw new IllegalArgumentException("A navy is not allowed in a landlocked area");
        }

        if (unitType == UnitType.ARMY && tileType == TileType.Water) {
            throw new IllegalArgumentException("An army is not allowed in the water");
        }

        this.team = team;
        this.unitType = unitType;
    }


    public String getName(){
        return name;
    }

    public void resetPosition(){
        super.setLocation(originalLocation);
    }

    public Info getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return name;
        /*
        return "Country: " + name + ", Borders: " + borders
                + "Original Location: " + originalLocation;*/
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
            if (c.getTileType() == TileType.Water && tileType == TileType.Landlocked) {
                tileType = TileType.Costal;
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

    public TileType getTileType() {
        return tileType;
    }

    public ArrayList<Country> getOccupiedNeighbors() {
        ArrayList<Country> occupiedNeighbors = new ArrayList<Country>();
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

    public void refreshGraphics() {
        setIcon(team.getIcon(unitType));
        setRolloverIcon(team.getRolloverIcon(unitType));
        setPressedIcon(team.getPressedIcon(unitType));
        if (team == Team.NULL) {
            setEnabled(false);
        }
    }

    public javax.swing.border.Border getBorder() {
        return border;
    }

    public void setMapAssociation(Map map) {
        mapAssociation = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action");
        info = new Info("Yes!");
        info.setLocation(0, 0);
        mapAssociation.add(info);
        mapAssociation.repaint(info.getBounds());
        Runnable runner = new Runnable() {
            @Override
            public void run() {
                while (1 == 1) {
                    try {
                        Thread.sleep(4);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    info.setLocation(info.getX() + 1, info.getY() + 1);
                    mapAssociation.validate();
                    if (info.getX() > getWidth() && info.getY() > getHeight()) {
                        mapAssociation.remove(info);
                    }
                }
            }
        };
        Thread ted = new Thread(runner);
        ted.start();

    }
}