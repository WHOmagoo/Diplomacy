import java.util.ArrayList;
import java.util.Collections;

public class Map extends ArrayList<Country> {

    public Map(ArrayList<Country> countries) {
        super.addAll(countries);
        Collections.sort(this);
    }

    public void calculateSecondDegreeBorders() {
        for (Country c : this) {
            c.calculateSecondDegreeBorders();
        }
    }


    public Country getCountry(String nameOfCountry) {
        for (Country country : this) {
            if (country.getName() == nameOfCountry) {
                return country;
            }
        }

        throw new NullPointerException("The specified country does not exist");
    }

    public String toString() {
        String temp = new String();
        for (Country c : this) {
            temp += "Coutnry: " + c + "\n\t Borders: " + c.getBorders().toString() + "\n\n";
        }

        return temp;
    }
}
