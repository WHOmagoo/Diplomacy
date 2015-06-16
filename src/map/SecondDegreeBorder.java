package map;

public class SecondDegreeBorder extends Border {

    /**
     * This is the constructor for this class, this will take the regular borders of a country and
     * add the borders of the regular borders to this object.
     *
     * @param country        the country to set the SecondDegreeBorders
     * @param regularBorders the borders of country.
     */
    public SecondDegreeBorder(Country country, Border regularBorders) {
        super(country);
        for (Country c : regularBorders) {
            for (Country secondBorder : c.getBorders()) {
                if (!contains(secondBorder) && secondBorder != country) {
                    add(secondBorder);
                }
            }
        }
    }
}