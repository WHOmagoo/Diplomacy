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
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class Map extends JLabel {
    ExecuteOrders executeOrders = new ExecuteOrders(this);
    private volatile ArrayList<Country> countries = new ArrayList<Country>();
    private JLabel text = new JLabel();
    private Country lastCountryClicked;
    private InputBanner banner;

    public Map(ArrayList<Country> countries) {
        this.countries.addAll(countries);
        Collections.sort(this.countries);
        for (Country c : countries) {
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

    public void moveUnits(ArrayList<Country> countriesToMove) {
        for (Country c : countriesToMove) {
            Order order = c.getOrder();
            if (order instanceof Move) {
                Country movingTo = ((Move) order).getMovingTo();
                if (!movingTo.isOccupied()) {
                    movingTo.setOccupiedBy(c.getTeam(), c.getUnitType());
                    c.setOccupiedBy(Team.NULL, UnitType.EMPTY);
                    c.resetForNewTurn();
                } else {
                    throw new Error("Moving to occupied area " + order);
                }
            } else if (order instanceof Attack) {
                Country movingTo = ((Attack) order).getAttacking();
                if (!movingTo.isOccupied()) {
                    ((Attack) order).getAttacking().setOccupiedBy(c.getTeam(), c.getUnitType());
                    c.setOccupiedBy(Team.NULL, UnitType.EMPTY);
                    c.resetForNewTurn();
                } else {
                    throw new Error("Moving to occupied area " + order);
                }
            }
        }

        updateGraphics();
    }

    public void moveUnits() {
        moveUnits(countries);
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
            return ((RelocateInput) banner.getLastInput()).isStillRelocating();
        } else {
            System.out.println("wrong ;(");
            return false;
        }
    }
}
