public class SecondDegreeBorder extends Border {

    public SecondDegreeBorder(Country country, Border regularBorders) {
        for (Country c : regularBorders) {
            for (Country secondBorder : c.getBorders()) {
                if (!contains(secondBorder)) {
                    add(secondBorder);
                }
            }
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
