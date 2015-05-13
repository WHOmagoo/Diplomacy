import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ros_hrmcgough on 4/29/2015.
 */
public class Border extends ArrayList<Country> {
    private Country country;
    private String random;

    public Border(Country country, ArrayList<Country> borders){
        Collections.sort(borders);
        super.addAll(borders);
        this.country = country;

    }

    public Border(Country country, Country[] borders) {
        for (Country s : borders) {
            if (s == country) {
                throw new IllegalArgumentException("Country must not be borderd by itself");
            } else {
                super.add(s);
            }
        }

        Collections.sort(this);
        this.country = country;

    }

    public Country getCountry(int i) {
        return super.get(i);
    }
}