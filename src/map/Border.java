/**
 * Border.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Border extends ArrayList<Country> implements Serializable {
    private Country country;

    /**
     * This is the default constructor for any sub classes.
     */
    public Border(Country c) {
        country = c;
    }

    /**
     * This is the constructor that should be used for a new Border object
     * Parameters
     * country - the country that the borders belong to
     * borders - an ArrayList of countries that border the country
     */
    public Border(Country country, ArrayList<Country> borders) throws IllegalArgumentException {
        this(country);
        if (borders.contains(country)) {
            throw new IllegalArgumentException("The Country must not be bordered by itself.\nCountry of issue: " + country);
        }
        Collections.sort(borders);
        super.addAll(borders);
    }

    /**
     * This is the constructor that should be used for a new Border object
     * Parameters
     * country - the country that the borders belong to
     * borders - an array of countries that border the country
     */
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

    /**
     * returns
     *   Country - the country that this border object belongs to.
     */
    public Country getCountry() {
        return country;
    }

    private void writeObject(ObjectOutputStream out) throws IOException, ClassNotFoundException {
        for (Country c : this) {
            out.writeUTF(c.toString());
        }
        out.writeUTF("\0");
        out.write(null);

    }

    private void readObject(ObjectInputStream in) {
        while (true) {
        }
    }
}