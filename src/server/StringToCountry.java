package server;

import map.Country;
import map.Map;

public class StringToCountry {
    private static Map map = null;

    public static Country getCountry(String nameOfCountry) {
        if (map != null) {
            return map.getCountry(nameOfCountry);
        } else {
            throw new NullPointerException("Map has not yet been set");
        }
    }

    public static void setMap(Map mapToSet) {
        map = mapToSet;
    }
}
