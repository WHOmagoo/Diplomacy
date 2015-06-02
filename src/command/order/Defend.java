package command.order;

import map.Country;

/**
 * Created by Hugh on 5/30/2015.
 */
public class Defend extends Order {
    private Country countryBeingDefended;

    public Defend(){

    }

    public Defend(Country countryBeingDefended){
        this.countryBeingDefended = countryBeingDefended;
    }

    public void setCountryBeingDefended(Country countryBeingDefended){
        this.countryBeingDefended = countryBeingDefended;
    }
}
