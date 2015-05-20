package Country;

import Country.Country;

public class SecondDegreeBorder extends Country.Border {

    public SecondDegreeBorder(Country country, Country.Border regularBorders) {
        for (Country c : regularBorders) {
            for (Country secondBorder : c.getBorders()) {
                if (!contains(secondBorder)) {
                    add(secondBorder);
                }
            }
        }
    }

    public void add(Country.Border borders) {
        for (Country c : borders) {
            if (!contains(c)) {
                super.add(c);
            }
        }
    }
}
