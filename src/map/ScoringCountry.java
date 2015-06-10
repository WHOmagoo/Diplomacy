package map;

import constants.Team;
import java.awt.Point;

public class ScoringCountry extends Country {
    private Team teamControls = Team.NULL;
    private Team oldTeamControls = Team.NULL;

    public ScoringCountry(String name, Point location, TileType tileType){
        super(name, location, tileType);
    }

    @Override
    public void setOccupiedBy(Team team, UnitType unitType) {
        super.setOccupiedBy(team, unitType);
        setTeamControls(team);
    }

    public Team getTeamControls(){
        return teamControls;
    }

    public void setTeamControls(Team teamControls) {
        if (teamControls != Team.NULL) {
            oldTeamControls = this.teamControls;
            this.teamControls = teamControls;
        }
    }

    public Team getOldTeamControls() {
        return oldTeamControls;
    }

    @Override
    public void resetAfterMove() {
        super.resetAfterMove();
        oldTeamControls = teamControls;
    }

    public boolean occupiedByNewTeam() {
        return oldTeamControls != teamControls;
    }
}
