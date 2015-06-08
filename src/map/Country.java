package map;

import command.Info;
import command.OrderType;
import command.input.OrderInput;
import command.order.Hold;
import command.order.Order;
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
    private Order order = new Hold(this);
    private boolean needsRelocation = false;
    private Country countryMovingTo = null;

    private Country() {
        setSize(40, 40);
        setOpaque(true);
        setBorder(border);
        setContentAreaFilled(false);
        addActionListener(this);
    }

    public Country(String name, Point location, TileType tileType) {
        this();
        this.name = name;
        this.tileType = tileType;
        originalLocation = location;
        setLocation(location);
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
                country != this &&
                country.isOccupied()) {
                if (getSupportableInCommon(country).size() > 0) {
                    occupiedSecondBorders.add(country);
                }
            }
            //TODO check ahead of time if there is a common country that is accessible by both units.
        }

        Collections.sort(occupiedSecondBorders);

        return occupiedSecondBorders;
    }

    public ArrayList<Country> getSupportableInCommon(Country otherCountry){
        ArrayList<Country> temp = new ArrayList<Country>();
        for(Country c : otherCountry.getAttackableCountries()){
            if(getAttackableCountries().contains(c)){
                temp.add(c);
            }
        }

        return temp;
    }

    public Border getBorders() {
        return borders;
    }

    public void setBorders(Border borders) {
        this.borders = borders;
    }

    public ArrayList<Country> getAttackableCountries(){
        ArrayList<Country> attackableCountries = new ArrayList<Country>();
        for (Country otherCountry : getBorders()) {
            if (isCorrectTypes(otherCountry)) {
                attackableCountries.add(otherCountry);
            }
        }

        Collections.sort(attackableCountries);
        return attackableCountries;
    }

    public ArrayList<Country> getWaterBorders(){
        ArrayList<Country> temp = new ArrayList<Country>();
        for(Country c : borders){
            if(c.getTileType() == TileType.Water){
                temp.add(c);
            }
        }

        return temp;
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
        if (this.team == Team.NULL || unitType == UnitType.EMPTY) {
            return false;
        } else {
            return true;
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

    public void setMapAssociation(Map map) {
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
        setDisabledIcon(getIcon());
        if (team == Team.NULL) {
            setEnabled(false);
        }
    }

    public void resetPosition() {
        super.setLocation(originalLocation);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mapAssociation.setLastCountryClicked((Country) e.getSource());
        mapAssociation.clearOldInput();
        Info infoCountry = new Info(getName());
        infoCountry.validate();

        OrderInput orderInput = new OrderInput(mapAssociation, OrderType.values());
        orderInput.validate();

        mapAssociation.addToInputBanner(infoCountry);
        mapAssociation.addToInputBanner(orderInput);

        mapAssociation.setLastVisible(orderInput);
    }

    @Override
    public int compareTo(Object country) throws IllegalArgumentException {
        try {
            Country other = (Country) country;
            if(getTileType() != TileType.Water && other.getTileType() == TileType.Water) {
                return -1;
            } else if (getTileType() == other.getTileType()) {
                return this.name.compareTo(other.getName());
            } else{
                return 1;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Must compare two countries");
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public Map getMap() {
        return mapAssociation;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        mapAssociation.updateOrderTotal();
        if (this == order.orderFrom()) {
            this.order = order;
        } else {
            throw new IllegalArgumentException("The wrong order has been added to " + this +
                    "\n" + order);
        }
    }

    //This is only for testing and is intended to show the location of all of the units.
    public void setVisible() {
        setIcon(Team.BALKANS.getIcon(UnitType.ARMY));
        setEnabled(true);
        setVisible(true);
    }

    public void removeOrder() {
        order = null;
    }

    public void resetForNewTurn() {
        removeOrder();
        needsRelocation = false;
        setLocation(originalLocation);
    }

    public void setNeedsRelocation(boolean needsRelocation) {
        this.needsRelocation = needsRelocation;
    }

    public boolean needsRelocation() {
        return needsRelocation;
    }

    public Country getMovingTo() {
        return countryMovingTo;
    }

    public Team getTeam() {
        return team;
    }

    public ArrayList<Country> getRelocateableNeighbors() {
        ArrayList<Country> relocateableTo = new ArrayList<Country>();
        ArrayList<Country> countriesLookedAt = new ArrayList<Country>();

        for (Country c : getAttackableCountries()) {
            if (!c.isOccupied()) {
                relocateableTo.add(c);
            }
            countriesLookedAt.add(c);
        }

        while (relocateableTo.size() == 0) {
            for (Country c : countriesLookedAt) {
                for (Country possibleMoveTo : c.getBorders()) {
                    if (possibleMoveTo.isCorrectTypes(this) && !possibleMoveTo.isOccupied()) {
                        relocateableTo.add(possibleMoveTo);
                    }
                    countriesLookedAt.add(possibleMoveTo);
                }
            }
        }

        Collections.sort(relocateableTo);
        return relocateableTo;
    }

    public boolean isCorrectTypes(Country countryAttacking) {
        if (borders.contains(countryAttacking)) {
            if (tileType == TileType.Costal) {
                if (unitType == UnitType.NAVY) {
                    if (countryAttacking.getTileType() == TileType.Costal) {
                        if (countryAttacking == mapAssociation.getCountry("Edinburgh")
                                && this == mapAssociation.getCountry("Wales")) {
                            return false;
                        } else if (countryAttacking == mapAssociation.getCountry("Wales")
                                && this == mapAssociation.getCountry("Edinburgh")) {
                            return false;
                        } else {
                            return countryAttacking.hasWaterInCommon(this);
                        }
                    } else {
                        return countryAttacking.getTileType() == TileType.Water;
                    }
                } else {
                    return countryAttacking.getTileType() == TileType.Landlocked
                            || countryAttacking.getTileType() == TileType.Costal;
                }
            } else if (tileType == countryAttacking.getTileType()) {
                return true;
            } else if (tileType == TileType.Water) {
                return countryAttacking.getTileType() == TileType.Costal;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean hasWaterInCommon(Country otherCountry) {
        for (Country c : borders) {
            if (c.tileType == TileType.Water) {
                if (otherCountry.getBorders().contains(c)) {
                    return true;
                }
            }
        }

        return false;
    }
}