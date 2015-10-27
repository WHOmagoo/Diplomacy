/**
 * Map.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package map;

import command.ExecuteOrders;
import command.Info;
import command.InputBanner;
import command.input.RelocateInput;
import command.order.Attack;
import command.order.Hold;
import command.order.Move;
import command.order.Order;
import constants.RolloverButton;
import constants.Team;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class Map extends JLabel implements Serializable {
    public static final long serialVersionUID = 8140L;
    private ArrayList<Country> countries = new ArrayList<Country>();
    private transient JLabel text = new JLabel();
    private transient Country lastCountryClicked;
    private transient InputBanner banner;

    /**
     * This is the constructor for the Map class, it just needs to know the countries that it needs
     * to display.
     *
     * @param countries - the countries that are to be added to this map.
     */
    public Map(ArrayList<Country> countries) {
        this.countries.addAll(countries);
        Collections.sort(this.countries);
        for (Country c : this.countries) {
            c.calculateCoastal();
            c.setMapAssociation(this);
            c.calculateSecondDegreeBorders();
        }

        setLayout(null);
        setSize(1024, 768);
        ExecuteOrders executeOrders = new ExecuteOrders(this);
        add(executeOrders);
    }

    /**
     * @param map - sets the graphics for this map to this the ImageIcon
     */
    public void setMapGraphic(ImageIcon map) {
        setIcon(map);
    }

    /**
     * @param text - sets the graphics for the text of this country to this ImageIcon
     */
    public void setMapText(ImageIcon text) {
        this.text.setIcon(text);
        this.text.setSize(1024, 768);
        this.text.setLocation(0, 0);
        this.text.setLayout(null);
        add(this.text, 0);
    }

    /**
     * Clears the old input from the input banner
     */
    public void clearOldInput(){

        Runnable run = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                banner.clearAll();
            }
        };
        Thread thread = new Thread(run);

        try {
            //TODO fix this System.out.println(banner.size());
            if (banner.size() != 0) {
                banner.clearAll();
                Info info = new Info("Order not entered");
                info.setLocation(0, 0);
                add(info);
                repaint(info.getBounds());
                //banner.setLastVisible(info);
                //TODO fix this System.out.println("order was not entered");
                thread.start();
            } else {
                banner.clearAll();
            }
        } catch (NullPointerException npe){
        }

        banner = new InputBanner(this);
    }

    /**
     * Adds a component to the input banner
     *
     * @param component - the component to add to the input banner
     */
    public void addToInputBanner(JComponent component){
        banner.add(component);
        banner.setLastVisible(component);
    }

    /**
     * @return the InputBanner that this map has
     */
    public InputBanner getBanner(){
        return banner;
    }

    /**
     *
     * @param nameOfCountry  - a String of the name of the country to get.
     * @return Country - a Country object that has the same name as nameOfCountry
     * @throws NullPointerException if a country does not exist with the name that was given.
     */
    public Country getCountry(String nameOfCountry) throws NullPointerException {
        for (Country country : countries) {
            if (country.getName().equalsIgnoreCase(nameOfCountry)) {
                return country;
            }
        }

        throw new NullPointerException("The specified country does not exist (" + nameOfCountry + ")");
    }

    /**
     * Converts this object to a string according to documentation
     *
     * @return a String of the countries it contains and the borders each country has.
     */
    @Override
    public String toString() {
        String temp = new String();
        for (Country c : countries) {
            temp += "Country: " + c + "\n\tBorders: " + c.getBorders() + "\n\tSecond Degree Borders: " + c.getSecondDegreeBorders() + "\n";
        }

        return temp;
    }

    /**
     * @return the last country that was clicked on the map
     */
    public Country getLastCountryClicked(){
        return lastCountryClicked;
    }

    /**
     * Sets the last country that was clicked on the map.
     * @param countryClicked - The las country that was clicked.
     */
    public void setLastCountryClicked(Country countryClicked) {
        lastCountryClicked = countryClicked;
    }

    /**
     * //This is intended for bug testing new maps
     */
    public void verifyBorders() {
        for (Country country : countries) {
            for (Country borderCountry : country.getBorders()) {
                if (!borderCountry.borders(country)) {
                    System.out.println("Country of issue: " + borderCountry + " does not contain " + country);
                }

            }
        }
    }

    /**
     * Calls the refreshGraphics methods for each country in this class
     */
    public void updateGraphics() {
        for (Country c : countries) {
            c.refreshGraphics();
        }
    }

    /**
     * @return ArrayList of the countries that this class has
     */
    public ArrayList<Country> getCountries() {
        return countries;
    }

    /**
     * adds all of the countries to the Map visually.
     */
    public void addAllCountries() {
        for (Country c : countries) {
            add(c, -1);
        }
    }

    /**
     * Removes all of the countries from the Map visually
     */
    public void removeAllCountries() {
        for (Country c : countries) {
            remove(c);
        }
    }

    /**
     * This updates the total orders the have been entered, this is intended for use when I have
     *  implemented orders being entered by team instead of any team anytime. Not really used in
     *  this project quite yet.
     */
    public void updateOrderTotal() {
        int counter = 0;
        for (Country c : countries) {
            if (c.getOrder() != null || !(c.getOrder() instanceof Hold)) {
                counter++;
            }
        }
    }

    //Only intended for bug testing, prints out all the orders of all the countries.
    public void printOrders() {
        for (Country c : countries) {
            if (c.getOrder() != null) {
                System.out.print(c.getOrder());
                if (!(c.getOrder() instanceof Hold)) {
                    System.out.print(" Succeeded - " + c.getOrder().succeeds());
                }
                System.out.println();
            }
        }
    }

    /**
     * Overrides the add method from JPanel in order to notify a RolloverButton that it has been
     *  added and to start its thread to check for a rollover. Otherwise works the same.
     * @param component the component to be added
     */
    public void add(JComponent component) {
        super.add(component);
        if (component instanceof RolloverButton) {
            ((RolloverButton) component).added();
        }
    }

    /**
     * Same as the add() method only using the integer value to utilize simple layering available in
     *  JComponent.
     * @param component the component to be added
     * @param i index location to display this above or behind other objects
     */
    public void add(JComponent component, int i) {
        super.add(component, i);
        if (component instanceof RolloverButton) {
            ((RolloverButton) component).added();
        }
    }

    /**
     * The same as the super method only it signals a RolloverButton to stop its thread checking for
     *  a rollover
     * @param component the component to remove
     */
    public void remove(JComponent component) {
        super.remove(component);
        if (component instanceof RolloverButton) {
            ((RolloverButton) component).removed();
        }
    }

    /**
     * This method iterates through the JButtons and calls the move method for each of them.
     * @param countriesToMove - An ArrayList of countries to move
     * @throws Error - will throw an error if attempting to move a country to an occupied area if it
     *      is not an instance of ScoringCountry, or if trying to move a country that's order is not
     *      an instance of Attack or Move
     */
    public void moveUnits(ArrayList<Country> countriesToMove) throws Error {
        for (Country c : countriesToMove) {
            Country movingTo = null;
            Order order = c.getOrder();
            if (order instanceof Move) {
                movingTo = ((Move) order).getMovingTo();
                if (!movingTo.isOccupied()) {
                    slideTileTo(c, movingTo);
                } else {
                    throw new Error("Moving to occupied area " + order);
                }
            } else if (order instanceof Attack) {
                movingTo = ((Attack) order).getAttacking();
                if (!movingTo.isOccupied()) {
                    slideTileTo(c, movingTo);
                } else {
                    if (movingTo instanceof ScoringCountry) {
                        slideTileTo(c, movingTo);
                    } else {
                        throw new Error("Attack moves to occupied non point scoring area");
                    }
                }
            } else {
                throw new Error("Trying to move an unmovable tile - " + c);
            }
        }
    }

    /**
     * Slides the country to a new area, right now it moves linearly but there is commented out part
     * that would move it faster at the beginning and slower at the end which does look a
     * little nicer but sometimes it will go forever without stopping.
     * @param countryToMove - the country to move
     * @param movingTo - where the counry will be moving to
     */
    public void slideTileTo(Country countryToMove, Country movingTo) {
        final double constant = 50;
        final int originalX = countryToMove.getX();
        final int originalY = countryToMove.getY();
        final float x = (float) ((movingTo.getX() - countryToMove.getX()) / constant);
        final float y = (float) ((movingTo.getY() - countryToMove.getY() ) / constant);
        for(int i = 0; i <= constant; i++){
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
            }

            int newX = Math.round(x * i) + originalX;
            int newY = Math.round(y * i) + originalY;
            countryToMove.setLocation(newX, newY);

            /**
             * Use this later for an exponential movement formula
             *  to calculate the new x and y positions.
             *
             * while (Math.abs(x - movingTo.getX()) > 1 && Math.abs(y - movingTo.getY()) > 1) {
             *      x += (movingTo.getX() - countryToMove.getX()) / constant;
             *      y += (movingTo.getY() - countryToMove.getY()) / constant;
             * }
             */
        }

        movingTo.setOccupiedBy(countryToMove.getTeam(), countryToMove.getUnitType());
        countryToMove.setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countryToMove.resetAfterMove();
        movingTo.refreshGraphics();
    }

    /**
     * This converts the ArrayList of countries into an Array and calls the other
     *  slideMultipleTiles() method
     * @param countriesToMove an ArrayList of countries to move.
     * @throws Error will be thrown when it receives an error
     *          from the other slideMultipleTiles() method
     */
    public void slideMultipleTiles(ArrayList<Country> countriesToMove) throws Error {
        if (countriesToMove.size() > 0) {
            Object[] objectsToConvert = countriesToMove.toArray();
            Country[] countries = new Country[objectsToConvert.length];

            for (int i = 0; i < objectsToConvert.length; i++) {
                countries[i] = (Country) objectsToConvert[i];
            }

            slideMultipleTiles(countries);
        }
    }

    /**
     * This slides multiple countries at once and is intended for use when there is a move loop,
     *  all of them move simultaneously and this handles the moving of a tile to an occupied
     *  territory by removing a unit and storing it then setting all of the other ones and
     *  setting the last one by the stored value.
     * @param countriesToMove an Array of countries to move, or added by commas
     * @throws Error when moving units that aren't MoveLooped or moving
     *          countries that aren't even moving
     */
    public void slideMultipleTiles(Country... countriesToMove) throws Error {
        if (countriesToMove.length > 1) {
            final double constant = 50;
            int[] originalX = new int[countriesToMove.length];
            int[] originalY = new int[countriesToMove.length];
            final float[] x = new float[countriesToMove.length];
            final float[] y = new float[countriesToMove.length];

            for (int i = 0; i < countriesToMove.length; i++) {
                Country c = countriesToMove[i];
                if (c.getOrder() instanceof Move) {
                    Move move = (Move) c.getOrder();
                    if (move.isMoveLooped()) {
                        Country countryMovingTo = ((Move) countriesToMove[i].getOrder()).getMovingTo();
                        originalX[i] = countriesToMove[i].getX();
                        originalY[i] = countriesToMove[i].getY();
                        x[i] = (float) ((countryMovingTo.getX() - countriesToMove[i].getX()) / constant);
                        y[i] = (float) ((countryMovingTo.getY() - countriesToMove[i].getY()) / constant);
                    } else throw new Error("You may only move countries that are move looped\n"
                            + "Here's what is wrong " + move);
                } else throw new Error("You may only move countries that are moved\n"
                        + "Here's what is wrong " + c.getOrder());
            }

            for (int iterations = 0; iterations <= constant; iterations++) {
                try {
                    Thread.sleep(12);
                } catch (InterruptedException e) {
                }
                for (int countryMoving = 0; countryMoving < countriesToMove.length; countryMoving++) {
                    int newX = Math.round(x[countryMoving] * iterations) + originalX[countryMoving];
                    int newY = Math.round(y[countryMoving] * iterations) + originalY[countryMoving];
                    countriesToMove[countryMoving].setLocation(newX, newY);
                }
            }

            Country cached = countriesToMove[0];
            Country cachedMovingTo = ((Move) cached.getOrder()).getMovingTo();
            Team cachedTeam = cached.getTeam();
            UnitType cachedUnitType = cached.getUnitType();
            cached.setOccupiedBy(Team.NULL, UnitType.EMPTY);
            cached.resetAfterMove();

            ArrayList<Country> notYetRelocated = new ArrayList<Country>();
            for (int i = 1; i < countriesToMove.length; i++) {
                notYetRelocated.add(countriesToMove[i]);
            }

            do {
                for (Country c : notYetRelocated) {
                    Move move = (Move) c.getOrder();
                    Country orderFrom = move.orderFrom();
                    if (!move.getMovingTo().isOccupied()) {
                        move.getMovingTo().setOccupiedBy(orderFrom.getTeam(), orderFrom.getUnitType());
                        orderFrom.setOccupiedBy(Team.NULL, UnitType.EMPTY);
                        orderFrom.resetAfterMove();
                        notYetRelocated.remove(orderFrom);
                        break;
                    }
                }

            } while (notYetRelocated.size() > 0);
            cachedMovingTo.setOccupiedBy(cachedTeam, cachedUnitType);
            for (Country c : countriesToMove) {
                c.refreshGraphics();
            }
        }
    }

    public void relocatePrompt(Country c) {
        banner.clearAll();
        banner = new InputBanner(this, c);
        RelocateInput relocate = new RelocateInput(banner, c);
        banner.add(new Info(c + " relocates to"));
        banner.add(relocate);
        banner.setLastVisible(relocate);
    }

    /**
     * This method will determine if a user is still inputting its relocation command when
     *  it has been attacked
     * @return boolean true if the user is still relocating, false otherwise
     */
    public boolean isStillRelocating() {
        if (banner.getLastInput() instanceof RelocateInput) {
            try {
                return ((RelocateInput) banner.getLastInput()).isStillRelocating();
            } catch (IndexOutOfBoundsException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * This will reset every country to be occupied by no one and refresh the graphics
     */
    public void clearAll() {
        for(Country c : countries){
            c.setOccupiedBy(Team.NULL, UnitType.EMPTY);
            c.refreshGraphics();
        }
    }

    /**
     * This method will recalculate how many countries each team owns.
     */
    public void refreshTeamValues() {
        for (Team team : Team.values()) {
            team.recalculateCountriesControlled(countries);
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException, ClassNotFoundException {
        out.writeLong(serialVersionUID);
        out.writeObject(countries);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.readLong();
        countries = (ArrayList<Country>) in.readObject();
    }
}
