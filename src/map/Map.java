package map;

import command.ExecuteOrders;
import command.Info;
import command.InputBanner;
import command.input.RelocateInput;
import command.order.Attack;
import command.order.Move;
import command.order.Order;
import constants.RolloverButton;
import constants.Team;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Map extends JLabel {
    ExecuteOrders executeOrders = new ExecuteOrders(this);
    private ArrayList<Country> countries = new ArrayList<Country>();
    private JLabel text = new JLabel();
    private Country lastCountryClicked;
    private InputBanner banner;

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
            if (c.getOrder() != null && c.getOrder().isValid() == Boolean.TRUE) {
                System.out.println(c.getOrder() + " - " + c.getOrder().succeeds());
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

    public void removeOldOrders() {
        for (Country c : countries) {
            c.removeOrder();
        }
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
                    movingTo.setOccupiedBy(c.getTeam(), c.getUnitType());
                } else {
                    throw new Error("Moving to occupied area " + order);
                }
            } else if (order instanceof Attack) {
                movingTo = ((Attack) order).getAttacking();
                if (!movingTo.isOccupied()) {
                    slideTileTo(c, movingTo);
                    ((Attack) order).getAttacking().setOccupiedBy(c.getTeam(), c.getUnitType());
                } else {
                    throw new Error("Moving to occupied area " + order);
                }
            } else {
                throw new Error("Trying to move an unmoveable tile - " + c);
            }

            movingTo.setOccupiedBy(c.getTeam(), c.getUnitType());
            c.setOccupiedBy(Team.NULL, UnitType.EMPTY);
            c.resetForNewTurn();
            movingTo.refreshGraphics();
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
    }

    public void slideMultipleTiles(ArrayList<Country> countriesToMove) throws Error {
        Country[] countries = (Country[]) countriesToMove.toArray();
        slideMultipleTiles(countries);
    }

    public void slideMultipleTiles(Country... countriesToMove) throws Error {
        double[] x = new double[countriesToMove.length];
        double[] y = new double[x.length];
        Point[] countriesMovingTo = new Point[y.length];
        for (int i = 0; i < countriesToMove.length; i++) {
            Country c = countriesToMove[i];
            if (c.getOrder() instanceof Move) {
                Move move = (Move) c.getOrder();
                if (move.isMoveLooped()) {
                    x[i] = c.getX() + .0;
                    y[i] = c.getY() + .0;
                    countriesMovingTo[i] = move.getMovingTo().getLocation();
                } else throw new Error("You may only move countries that are move looped"
                        + "Here's what is wrong " + move);
            } else throw new Error("You may only move countries that are moved\n"
                    + "Here's what is wrong " + c.getOrder());
        }

        while (Math.abs(x[0] - countriesMovingTo[0].getX()) > 1) {
            try {
                Thread.sleep(12);
            } catch (InterruptedException e) {
            }
            for (int i = 0; i < countriesToMove.length; i++) {
                x[i] += (countriesMovingTo[i].getX() - countriesToMove[i].getX()) / 40.0;
                y[i] += (countriesMovingTo[i].getY() - countriesToMove[i].getY()) / 40.0;

                countriesToMove[i].setLocation((int) Math.round(x[i]), (int) Math.round(y[i]));
            }
        }

        for (Country c : countriesToMove) {
            c.resetForNewTurn();
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
}
