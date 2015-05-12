import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by ros_hrmcgough on 4/29/2015.
 */
public class Border {
    private ArrayList<Country> borders;
    private Country country;
    private String random;

    public Border(Country country, ArrayList<Country> borders){
        Collections.sort(borders);
        this.borders = borders;
        this.country = country;

    }

    public void setRandom(String s){
        random = s;
    }

    public String getRandom(){
        return random;
    }

    public Country getCountry(int i) {
        return borders.get(i);
    }

    public String toString(){
        return borders.toString();
    }
}