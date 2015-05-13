import java.util.ArrayList;

public class Map extends ArrayList {
    ArrayList<Country> countries;

    public Map(ArrayList<Country> countries) {
        super.addAll(countries);
    }
}
