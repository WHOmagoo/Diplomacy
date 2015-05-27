package map;

import command.InputBanner;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.Collections;

public class Map extends JLabel {
    private ArrayList<Country> countries = new ArrayList<Country>();
    private JLabel text = new JLabel();
    private Country lastCountryClicked;
    private InputBanner banner = new InputBanner(this);

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

    public void setLastCountryClicked(Country countryClicked){
        lastCountryClicked = countryClicked;
    }

    public void clearOldInput(){
        try {
            banner.clearAll();
            banner = new InputBanner(this);
        } catch (NullPointerException npe){
            System.out.println("error " + npe);
        }
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
}
