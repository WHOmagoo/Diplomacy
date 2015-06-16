/**
 * ScoringCountry.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */


package map;

import constants.Team;
import java.awt.Point;

public class ScoringCountry extends Country {
    private Team teamControls = Team.NULL;
    private Team oldTeamControls = Team.NULL;

    /**
     * This is the constructor for a ScoringCountry, it just calls the super constructor but it is
     * type ScoringCountry
     *
     * @param name     the name of the country
     * @param location the location of the country on the x/y plane on the screen
     * @param tileType what type of tile it is.
     */
    public ScoringCountry(String name, Point location, TileType tileType){
        super(name, location, tileType);
    }

    /**
     * calls the super method but also sets which team controls this ScoringCountry which does not
     * get reset when the country becomes not occupied.
     *
     * @param team     the team that now occupies the country
     * @param unitType the unitType that now occupies this country
     */
    @Override
    public void setOccupiedBy(Team team, UnitType unitType) {
        super.setOccupiedBy(team, unitType);
        setTeamControls(team);
    }

    /**
     * @return the country that controls this ScoringCountry
     */
    public Team getTeamControls(){
        return teamControls;
    }

    /**
     * @param teamControls the team that now controls this ScoringCountry
     */
    private void setTeamControls(Team teamControls) {
        if (teamControls != Team.NULL) {
            oldTeamControls = this.teamControls;
            this.teamControls = teamControls;
        }
    }
}
