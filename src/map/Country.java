package map;

import command.Info;
import command.InputBanner;
import command.OrderType;
import command.input.CommandInput;
import command.input.Input;
import constants.Team;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;

public class Country extends JButton implements ActionListener, Comparable {
    private String name;
    private Border borders;
    private SecondDegreeBorder secondDegreeBorders;
    private Team team = Team.NULL;
    private UnitType unitType = UnitType.EMPTY;
    private TileType tileType;
    private Point originalLocation;
    private javax.swing.border.Border border = null;
    private Map mapAssociation;
    private InputBanner inputBanner;
    //private ArrayList<JComponent> inputs = new ArrayList<JComponent>(); //TODO make an object for this

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

    public void setMapAssociation(Map map) {
        inputBanner = new InputBanner(map, this);
        mapAssociation = map;
    }

    public void setOccupiedBy(Team team, UnitType unitType) throws IllegalArgumentException {
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

    public ArrayList<Country> getOccupiedNeighbors() {
        ArrayList<Country> occupiedNeighbors = new ArrayList<Country>();
        for (Country country : borders) {
            if (country.isOccupied()) {
                occupiedNeighbors.add(country);
            }
        }

        return occupiedNeighbors;
    }

    public ArrayList<Country> getSupportableCountries() {
        ArrayList<Country> occupiedSecondBorders = new ArrayList<Country>();
        for(Country country : secondDegreeBorders) {
            if (!occupiedSecondBorders.contains(country) &&
                ((unitType == UnitType.NAVY && country.getTileType() != TileType.Landlocked) ||
                (unitType == UnitType.ARMY && country.getTileType() != TileType.Water)) &&
                country != this &&
                isOccupied()) {
                occupiedSecondBorders.add(country);
            }
        }


        Collections.sort(occupiedSecondBorders);

        return occupiedSecondBorders;
    }

    public Border getBorders() {
        return borders;
    }

    public void setBorders(Border borders) {
        this.borders = borders;
    }

    public javax.swing.border.Border getBorder() {
        return border;
    }

    public SecondDegreeBorder getSecondDegreeBorders() {
        return secondDegreeBorders;
    }

    public String getName() {
        return name;
    }

    public TileType getTileType() {
        return tileType;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public boolean isOccupied() {
        if (this.team != Team.NULL) {
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

    public void add(Input input) {
        inputBanner.add(input);
        input.revalidate();
        mapAssociation.add(input, -1);
        mapAssociation.repaint(input.getBounds());
    }

    public void calculateCoastal() {
        for (Country c : borders) {
            if (c.getTileType() == TileType.Water && tileType == TileType.Landlocked) {
                tileType = TileType.Costal;
                break;
            }
        }
    }

    public void calculateSecondDegreeBorders() throws NullPointerException {
        if (borders == null) {
            throw new NullPointerException("The borders have not yet been set");
        }
        secondDegreeBorders = new SecondDegreeBorder(this, borders);
    }

    public void refreshGraphics() {
        setIcon(team.getIcon(unitType));
        setRolloverIcon(team.getRolloverIcon(unitType));
        setPressedIcon(team.getPressedIcon(unitType));
        if (team == Team.NULL) {
            setEnabled(false);
        }
    }

    public void resetPosition() {
        super.setLocation(originalLocation);
    }

    public InputBanner getInputBanner() {
        return inputBanner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mapAssociation.clearOldInput();
        mapAssociation.setLastCountryClicked((Country) e.getSource());

        inputBanner = new InputBanner(mapAssociation, this);
        Info infoCountry = new Info(getName());
        inputBanner.add(infoCountry);
        infoCountry.validate();

        CommandInput commandInput = new CommandInput(OrderType.values(), this);
        inputBanner.add(commandInput);
        commandInput.validate();

        inputBanner.setLastVisible(commandInput);
    }

    @Override
    public int compareTo(Object country) throws IllegalArgumentException {
        try {
            return this.name.compareTo(((Country) country).getName());
        } catch (Exception e) {
            throw new IllegalArgumentException("Must compare two countries");
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public InputBanner getInputs() {
        return inputBanner;
    }

    public Map getMap() {
        return mapAssociation;
    }
}