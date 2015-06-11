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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class Map extends JLabel implements Serializable {
    ExecuteOrders executeOrders = new ExecuteOrders(this);
    private ArrayList<Country> countries = new ArrayList<Country>();
    transient private JLabel text = new JLabel();
    transient private Country lastCountryClicked;
    transient private InputBanner banner;

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
        add(executeOrders);
    }

    public void setMapGraphic(ImageIcon map) {
        setIcon(map);
    }

    public void setMapText(ImageIcon text) {
        this.text.setIcon(text);
        this.text.setSize(1024, 768);
        this.text.setLocation(0, 0);
        this.text.setLayout(null);
        add(this.text, 0);
    }

    public void clearOldInput(){
        try {
            banner.clearAll();
        } catch (NullPointerException npe){
        }
        banner = new InputBanner(this);
    }

    public void setLastVisible(JComponent j){
        banner.setLastVisible(j);
    }

    public void addToInputBanner(JComponent component){
        banner.add(component);
    }

    public InputBanner getBanner(){
        return banner;
    }

    public Country getCountry(String nameOfCountry) throws NullPointerException {
        for (Country country : countries) {
            if (country.getName().equalsIgnoreCase(nameOfCountry)) {
                return country;
            }
        }

        throw new NullPointerException("The specified country does not exist");
    }

    public String toString() {
        String temp = new String();
        for (Country c : countries) {
            temp += "Country: " + c + "\n\tBorders: " + c.getBorders() + "\n\tSecond Degree Borders: " + c.getSecondDegreeBorders() + "\n";
        }

        return temp;
    }

    public Country getLastCountryClicked(){
        return lastCountryClicked;
    }

    public void setLastCountryClicked(Country countryClicked) {
        lastCountryClicked = countryClicked;
    }

    @Deprecated //This is intended for bug testing new maps
    public void verifyBorders() {
        for (Country country : countries) {
            for (Country borderCountry : country.getBorders()) {
                if (!borderCountry.contains(country)) {
                    System.out.println("Country of issue: " + borderCountry + " does not contain " + country);
                }

            }
        }
    }

    public void updateGraphics() {
        for (Country c : countries) {
            c.refreshGraphics();
        }
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void addAllCountries() {
        for (Country c : countries) {
            add(c, -1);
        }
    }

    public void removeAllCountries() {
        for (Country c : countries) {
            remove(c);
        }
    }

    public void updateOrderTotal() {
        int counter = 0;
        for (Country c : countries) {
            if (c.getOrder() != null) {
                counter++;
            }
        }
    }

    @Deprecated //Only inteded for bug testing
    public void printOrders() {
        for (Country c : countries) {
            if (c.getOrder() != null) { //&& c.getOrder().isValid() == Boolean.TRUE) {
                System.out.print(c.getOrder());
                if (!(c.getOrder() instanceof Hold)) {
                    System.out.print(" Succeeded:  " + c.getOrder().succeeds());
                }
                System.out.println();
            }
        }
    }

    public void add(JComponent component) {
        super.add(component);
        if (component instanceof RolloverButton) {
            ((RolloverButton) component).added();
        }
    }

    public void add(JComponent component, int i) {
        super.add(component, i);
        if (component instanceof RolloverButton) {
            ((RolloverButton) component).added();
        }
    }

    public void remove(JComponent component) {
        super.remove(component);
        if (component instanceof RolloverButton) {
            ((RolloverButton) component).removed();
        }
    }

    public void setCountryOccupied(String name){
        try {
            getCountry(name).setOccupiedBy(Team.EGYPT, UnitType.ARMY);
            updateGraphics();
            getCountry(name).setEnabled(true);
        } catch (NullPointerException e){
            throw new Error("Wrong country added");
        }
    }

    @Deprecated //This is only intended for bug testing
    public void setSomeOccupied() {
        int i = 0;
        for (Country c : countries) {
            if (c.getTileType() != TileType.Water) {
                if (i % 3 == 0)
                    c.setOccupiedBy(Team.EGYPT, UnitType.ARMY);
            } else {
                if (i % 3 == 0)
                    c.setOccupiedBy(Team.BRITAIN, UnitType.NAVY);
            }

            if (i % 3 == 0)
                c.setEnabled(true);
            i++;
        }

        updateGraphics();
    }

/*    public void relocatePrompts(ArrayList<Country> needMove) {
        banner.clearAll();
        RelocateInput relocate = new RelocateInput(0, needMove);
    }*/

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
                throw new Error("Trying to move an unmoveable tile - " + c);
            }
        }
    }

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

            //while (Math.abs(x - movingTo.getX()) > .25 && Math.abs(y - movingTo.getY()) > .25) {
            /*x += (movingTo.getX() - countryToMove.getX()) / constant;
            y += (movingTo.getY() - countryToMove.getY()) / constant;*/
            //Use this later for an exponential movement formula.
        }

        movingTo.setOccupiedBy(countryToMove.getTeam(), countryToMove.getUnitType());
        countryToMove.setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countryToMove.resetAfterMove();
        movingTo.refreshGraphics();
    }

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

    public void removeExecuteOrders() {
        remove(executeOrders);
        repaint(executeOrders.getBounds());
    }

    public void relocatePrompt(Country c) {
        banner.clearAll();
        banner = new InputBanner(this, c);
        RelocateInput relocate = new RelocateInput(banner, c);
        banner.add(new Info(c + " relocates to"));
        banner.add(relocate);
        banner.setLastVisible(relocate);
    }

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

    public void clearAll() {
        for(Country c : countries){
            c.setOccupiedBy(Team.NULL, UnitType.EMPTY);
            c.refreshGraphics();
        }
    }

    public void setCountryOccupied(String liverpool, Team britain, UnitType navy) {
        Country c = getCountry(liverpool);
        c.setOccupiedBy(britain, navy);
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(countries);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.readObject();
    }

    public void refreshTeamValues() {
        for (Team team : Team.values()) {
            team.recalculateCountriesControlled(countries);
        }
    }
}
