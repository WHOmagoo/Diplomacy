package map;

import command.ExecuteOrders;
import command.InputBanner;
import constants.RolloverButton;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class Map extends JLabel {
    private ArrayList<Country> countries = new ArrayList<Country>();
    private JLabel text = new JLabel();
    private Country lastCountryClicked;
    private InputBanner banner;
    private ExecuteOrders executeOrders = new ExecuteOrders(this);

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
        //executeOrders.setRolloverEnabled(true);
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
            if (country.getName() == nameOfCountry) {
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

    public void verifyBorders() {
        for (Country country : countries) {
            for (Country borderCountry : country.getBorders()) {
                if (!borderCountry.contains(country)) {
                    System.out.println("Country of issue: " + borderCountry + " does not contain " + country);
                }

            }
        }
    }

    public void refreshAllCountries() {
        for (Country c : countries) {
            c.refreshGraphics();
            c.setVisible();
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

        if (counter == 13) {
            executeOrders.setEnabled(false);
        } else {
            executeOrders.setEnabled(true);
        }
    }

    public void printOrders() {
        for (Country c : countries) {
            if (c.getOrder() != null) {
                System.out.println(c.getOrder());
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
}
