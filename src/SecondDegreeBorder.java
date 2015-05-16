import java.util.ArrayList;

/**
 * Created by Hugh on 5/15/2015.
 */
public class SecondDegreeBorder extends Border {

    public SecondDegreeBorder(Country country, Border border) {
        super(country);
        ArrayList<Country> temp = new ArrayList<>();
        for (Country c : border) {
            temp.add(c);
        }
    }

    public void add(Border borders) {
        for (Country c : borders) {
            if (!contains(c)) {
                super.add(c);
            }
        }
    }
}
