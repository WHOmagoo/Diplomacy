package map;

import java.util.ArrayList;
import java.util.Collections;

public class Border extends ArrayList<Country> {
    private Country country;

    public Border() {
    }

    public Border(Country country, ArrayList<Country> borders) throws IllegalArgumentException {
        if (borders.contains(country)) {
            throw new IllegalArgumentException("The Country must not be bordered by itself.\nCountry of issue: " + country);
        }
        Collections.sort(borders);
        super.addAll(borders);
        this.country = country;

    }

    public Border(Country country, Country[] borders) throws IllegalArgumentException {
        for (Country c : borders) {
            if (c == country) {
                throw new IllegalArgumentException("Country must not be borderd by itself.\nCountry of issue: " + country);
            } else {
                for (Country addedCountry : this) {
                    if (addedCountry == c) {
                        //throw new IllegalArgumentException("There is a duplicate country, the problem is with the country " + c);
                    }
                }
                super.add(c);
            }
        }

        Collections.sort(this);
        this.country = country;

    }

    public Country getCountry() {
        return country;
    }

    public Country getCountry(String nameOfCountry) {
        for (Country country : this) {
            if (country.getName() == nameOfCountry) {
                return country;
            }
        }

        throw new NullPointerException("The specified country does not exist as a border");
    }

}