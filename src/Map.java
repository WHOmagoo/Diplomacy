import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class Map extends JPanel {
    ArrayList<Country> countries = new ArrayList<Country>();

    public Map(ArrayList<Country> countries) {
        this.countries.addAll(countries);
        Collections.sort(this.countries);
    }

    public void calculateSecondDegreeBorders() {
        for (Country c : countries) {
            c.calculateSecondDegreeBorders();
        }
    }


    public Country getCountry(String nameOfCountry) {
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
            temp += "Coutnry: " + c + "\n\t Borders: " + c.getBorders().toString() + "\n\n";
        }

        return temp;
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
}
