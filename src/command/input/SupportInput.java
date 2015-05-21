package command.input;

import map.Country;

/**
 * Created by Hugh on 5/18/2015.
 */
public class SupportInput extends Input {
    SupportInput(Country country){
        for(Country border : country.getOccupiedNeighbors()){

        }
    }
}
