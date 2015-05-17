import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class Map extends JPanel {
    ArrayList<Country> countries = new ArrayList<Country>();

    public Map(ArrayList<Country> countries) {
        this.countries.addAll(countries);
        Collections.sort(this.countries);

        setLayout(null);
        setSize(1024, 768);

    }

    public void calculateSecondDegreeBorders() {
        for (Country c : countries) {
            c.calculateSecondDegreeBorders();
        }
    }

    public void setMapGraphics(ImageIcon map) {
        add(mapGraphicsConstants(map), -1);
    }

    public void setMapText(ImageIcon text) {
        add(mapGraphicsConstants(text), 0);
    }

    private JLabel mapGraphicsConstants(ImageIcon imageIcon) {
        JLabel temp = new JLabel(imageIcon);
        temp.setSize(1024, 768);
        temp.setLocation(0, 0);
        temp.setLayout(null);
        return temp;

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
            temp += "Country: " + c + "\n\tBorders: " + c.getBorders() + "\n\tSecond Degree Borders: " + c.getSecondDegreeBorders() + "\n";
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
