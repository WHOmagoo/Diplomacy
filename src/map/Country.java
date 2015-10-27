/**
 * Country.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

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
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class Country extends JButton implements ActionListener, Comparable, Serializable {
    public static final long serialVersionUID = 8139L;
    private String name;
    private Border borders; //TODO implement Serialized
    private transient SecondDegreeBorder secondDegreeBorders;
    private Team team = Team.NULL;
    private UnitType unitType = UnitType.EMPTY;
    private TileType tileType;
    private Point originalLocation;
    private transient Map mapAssociation;
    private Order order = new Hold(this);

    Country() {
        setSize(40, 40);
        setOpaque(true);
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentAreaFilled(false);
        addActionListener(this);
    }
    /**
     * This is the default constructor for the Country object.
     * Parameters
     * name - the name of the country
     * location - the location of the country on the x,y plane
     * tileType - used to determin if this is a water, land or coastal teritory.
     */
    public Country(String name, Point location, TileType tileType) {
        this();
        this.name = name;
        this.tileType = tileType;
        originalLocation = location;
        setLocation(location);
    }

    /**
     * Returns the countries that this county may support.
     */
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
        }

        Collections.sort(occupiedSecondBorders);
        return occupiedSecondBorders;
    }

    /**
     * Gets the countries that are in common with the this country and another county
     * Parameters
     * otherCountry - the other country to compare borders in common with
     * Returns - the countries that are in common between two countries
     */
    public ArrayList<Country> getSupportableInCommon(Country otherCountry){
        ArrayList<Country> temp = new ArrayList<Country>();
        for(Country c : otherCountry.getAttackableCountries()){
            if(getAttackableCountries().contains(c)){
                temp.add(c);
            }
        }

        Collections.sort(temp);

        return temp;
    }

    /**
     * Returns the borders of this country
     */
    public Border getBorders() {
        return borders;
    }

    /**
     * Sets the borders of this territory, the countries that border this country.
     * Parameters
     * borders - the Border object countries that border this one.
     */
    public void setBorders(Border borders) {
        this.borders = borders;
    }

    /**
     * Returns the borders of this country and the borders of all the countries that border it.
     */
    public SecondDegreeBorder getSecondDegreeBorders() {
        return secondDegreeBorders;
    }

    /**
     * Returns the name of this country
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the TileType of this country (Landlocked, Coastal, Water)
     */
    public TileType getTileType() {
        return tileType;
    }

    /**
     * Returns the unit type that occupies this country.
     */
    public UnitType getUnitType() {
        return unitType;
    }

    /**
     * This gets the territories that the unit that occupies this country may move to, it must be a
     * ScoringCountry that is controlled by the same team or a non ScoringCountry as well as have
     * be the correct TileType
     * Returns - an ArrayList of the countries that it may move to.
     */
    public ArrayList<Country> getMovableTo() {
        ArrayList<Country> attackableCountries = new ArrayList<Country>();
        for (Country otherCountry : getBorders()) {
            if (isCorrectTypes(otherCountry)) {
                if (otherCountry instanceof ScoringCountry) {
                    if (((ScoringCountry) otherCountry).getTeamControls() == team) {
                        attackableCountries.add(otherCountry);
                    }
                } else {
                    attackableCountries.add(otherCountry);
                }
            }
        }

        Collections.sort(attackableCountries);
        return attackableCountries;
    }

    /**
     * Gets the countries that the unit that currently occupies this country may attack, it could be
     * any instance of country but must not be occupied by another unit of the same team.
     * Returns - an ArrayList of the countries it may attack
     */
    public ArrayList<Country> getAttackableCountries() {
        ArrayList<Country> attackableCountries = new ArrayList<Country>();
        for (Country otherCountry : borders) {
            if (otherCountry.getTeam() != team && isCorrectTypes(otherCountry)) {
                attackableCountries.add(otherCountry);
            }
        }

        return attackableCountries;
    }

    /**
     * Gets the countries that a unit may relocate to after it has been successfully attacked
     * Returns
     * ArrayList - of the countries it may relocate to.
     */
    public ArrayList<Country> getRelocateableToNeighbors() {
        ArrayList<Country> relocateableTo = new ArrayList<Country>();
        ArrayList<Country> countriesLookedAt = new ArrayList<Country>();

        for (Country c : getMovableTo()) {
            if (!c.isOccupied() && c.isCorrectTypes(this)) {
                relocateableTo.add(c);
            }
            countriesLookedAt.add(c);
        }

        /**
         * This while loop is here in case that there are no directly adjacent territories to
         *   relocate to. It will continue to search the borders of the countries already searched
         *   through for an unoccupied country.
         */
        while (relocateableTo.size() == 0) {
            ArrayList<Country> temp = new ArrayList<Country>();
            for (Country c : countriesLookedAt) {
                for (Country possibleMoveTo : c.getBorders()) {
                    if (!possibleMoveTo.isOccupied()) {
                        if (!relocateableTo.contains(possibleMoveTo)) {
                            if (isCorrectTypes(possibleMoveTo)) {
                                relocateableTo.add(possibleMoveTo);
                            }
                        }
                    }
                    temp.add(possibleMoveTo);
                }
            }
            countriesLookedAt.addAll(temp);
        }

        Collections.sort(relocateableTo);
        return relocateableTo;
    }

    /**
     * Returns
     * ArrayList - of the borders of this country that are occupied
     */
    public ArrayList<Country> getOccupiedNeighbors() {
        ArrayList<Country> occupiedNeighbors = new ArrayList<Country>();
        for (Country c : borders) {
            if (c.isOccupied() && isCorrectTypes(c)) {
                occupiedNeighbors.add(c);
            }
        }

        return occupiedNeighbors;
    }

    /**
     * Returns
     * Map - the map that this country has been added to.
     */
    public Map getMap() {
        return mapAssociation;
    }

    /**
     * Returns
     * boolean - if this country is occupied or not.
     */
    public boolean isOccupied() {
        if (this.team.equals(Team.NULL) || unitType.equals(UnitType.EMPTY)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Parameters
     * country - the country that may border this country
     * Returns
     * boolean - declaring if country does or doesn't border this country
     */
    public boolean borders(Country country) {
        for (Country c : borders) {
            if (c == country) {
                return true;
            }
        }

        return false;
    }

    /**
     * This method determines if the other country is of the correct TileType for attacking or moving
     * to. If the unit of this country is a Navy the the otherCountry's TileType must be water or
     * Coastal that are directly next to each other, so a navy may not go across land to another
     * Coastal territory (example: Bilboa to Barcelona). If the UnitType is an army, the other
     * country must not be a Water territory.
     * Parameters
     * otherCountry - the country to compare to this country
     * Returns
     * boolean - true if it is the correct type, false if it isn't
     */
    public boolean isCorrectTypes(Country otherCountry) {
        if (borders.contains(otherCountry)) {
            if (tileType == TileType.Coastal) {
                if (unitType == UnitType.NAVY) {
                    if (otherCountry.getTileType() == TileType.Coastal) {
                        /**
                         * This if business is to fix a problem of Edinburgh and Wales that have
                         *   water in common but still aren't legal for a navy to move to.
                         */
                        if (otherCountry == mapAssociation.getCountry("Edinburgh")
                                && this == mapAssociation.getCountry("Wales")) {
                            return false;
                        } else if (otherCountry == mapAssociation.getCountry("Wales")
                                && this == mapAssociation.getCountry("Edinburgh")) {
                            return false;
                        } else {
                            return otherCountry.hasWaterInCommon(this);
                        }
                    } else {
                        return otherCountry.getTileType() == TileType.Water;
                    }
                } else {
                    return otherCountry.getTileType() == TileType.Landlocked
                            || otherCountry.getTileType() == TileType.Coastal;
                }
            } else if (tileType == otherCountry.getTileType()) {
                return true;
            } else if (tileType == TileType.Water) {
                return otherCountry.getTileType() == TileType.Coastal;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * This method is used to determine if two Coastal tiles are legal for a navy to move to,
     * this works for all possibilities except when a Water territory borders three or more
     * countries that also border each other, the only case in my Map is with Edinburgh and Wales
     * Parameters
     * otherCountry - the other country in question.
     * Returns
     * boolean - if the two countries have a water tile in common.
     */
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

    /**
     * Sets the map that this has been added to.
     */
    public void setMapAssociation(Map map) {
        mapAssociation = map;
    }

    /**
     * Calculates if this country is Coastal or Landlocked after the borders have been set and sets
     * the tile type of this country to Coastal if it has a border that is a water, this is a
     * lot easier than manually typing it up itself.
     */
    public void calculateCoastal() {
        for (Country c : borders) {
            if (c.getTileType() == TileType.Water && tileType == TileType.Landlocked) {
                tileType = TileType.Coastal;
                break;
            }
        }
    }

    /**
     * Gets the borders of the bordering countries and adds it to the field secondDegreeBorders, this
     * is supposed to be used after all of the borders for each country have been set.
     */
    public void calculateSecondDegreeBorders() throws NullPointerException {
        if (borders == null) {
            throw new NullPointerException("The borders have not yet been set");
        }
        secondDegreeBorders = new SecondDegreeBorder(this, borders);
    }

    /**
     * Returns
     * Order - the Order of this country
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets the order of this country for this turn, overrides the previous order if an order is
     * reentered.
     * Parameters
     * order - the order that this country is doing.
     */
    public void setOrder(Order order) {
        mapAssociation.updateOrderTotal();
        if (this == order.orderFrom() && isOccupied()) {
            this.order = order;
        } else {
            throw new IllegalArgumentException("The wrong order has been added to " + this +
                    "\n" + order);
        }
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

    /**
     * Updates the graphics of the button to reflect the unit the occupies it, will disable the
     * button if it is unoccupied.
     */
    public void refreshGraphics() {
        setIcon(team.getIcon(unitType));
        setRolloverIcon(team.getRolloverIcon(unitType));
        setPressedIcon(team.getPressedIcon(unitType));
        setDisabledIcon(getIcon());
        setEnabled(isOccupied());
    }

    /**
     * Resets the position of the country to its original location, this is used after it has been
     * moved on screen
     */
    public void resetPosition() {
        super.setLocation(originalLocation);
    }

    /**
     * Resets the button after it has been moved and its unit has been relocated
     */
    public void resetAfterMove() {
        resetPosition();
        refreshGraphics();
    }

    /**
     * Resets the order of this country
     */
    public void resetOrder() {
        order = new Hold(this);
    }

    /**
     * Returns
     * Team - that currently occupies this country.
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Overrides the compareTo method according to documentation
     *
     * @param country
     * @return int
     * @throws IllegalArgumentException
     */
    @Override
    public int compareTo(Object country) throws IllegalArgumentException {
        try {
            Country other = (Country) country;
            if (other.getTileType() == TileType.Water) {
                if (tileType == TileType.Water) {
                    return name.compareTo(other.getName());
                } else {
                    return -1;
                }
            } else {
                return name.compareTo(other.getName());
            }
        } catch (Exception e) {
            return this.compareTo(country);
            //throw new IllegalArgumentException("Must compare two countries");
        }
    }

    /**
     * Overrides the toString method according to documentation
     *
     * @return String
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Overrides the actionPerformed method according to documentation, this method brings up the
     * input banner for this country and clears the old one.
     *
     * @param e
     */
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
    }


    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(name);
        out.writeObject(tileType);
        out.writeObject(team);
        out.writeObject(unitType);
        out.writeObject(borders);
        out.writeObject(originalLocation);
        out.write(null);
        out.close();
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        tileType = (TileType) in.readObject();
        team = (Team) in.readObject();
        unitType = (UnitType) in.readObject();
        borders = (Border) in.readObject();
        originalLocation = (Point) in.readObject();
        in.close();
    }
}