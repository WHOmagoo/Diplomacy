package map;

import constants.Team;

import java.awt.Point;

public class ScoringCountry extends Country {
    private Team teamControls = Team.NULL;

    public ScoringCountry(String name, Point location, TileType tileType){
        super(name, location, tileType);
    }

    public void setTeamControls(Team teamControls){
        this.teamControls = teamControls;
    }

    public Team getTeamControls(){
        return teamControls;
    }
}
