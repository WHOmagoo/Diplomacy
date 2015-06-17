/**
 * AddUnit.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package command.input;

import command.Info;
import command.InputBanner;
import constants.Team;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import map.Country;
import map.ScoringCountry;

public class AddUnit extends Input implements ActionListener {
    InputBanner banner;
    Country country;
    Team team;
    AddUnitType addUnit = null;

    /**
     * The default constructor for this class
     *
     * @param banner the banner to add this to
     * @param team   the team the needs to add units
     */
    public AddUnit(InputBanner banner, Team team) {
        super(banner);
        this.banner = banner;
        this.team = team;
        country = banner.getCountry();

        DefaultComboBoxModel<Country> model = new DefaultComboBoxModel<Country>();
        for (Country c : banner.getMap().getCountries()) {
            if (!c.isOccupied()) {
                if (c instanceof ScoringCountry) {
                    if (((ScoringCountry) c).getTeamControls() == team) {
                        model.addElement(c);
                    }
                }
            }
        }

        String infoText = team.toString();
        if (model.getSize() == 1) {
            infoText += " choose where to add unit";
        }
        Info teamAdd = new Info(infoText);
        banner.add(teamAdd);

        model.setSelectedItem(" choose a location to add a unit");
        setModel(model);
        setSize(longestItem(), 25);

        addActionListener(this);
        banner.setLastVisible(this);

        if (model.getSize() == 1) {
            setSelectedItem(model.getElementAt(0));
        }
    }

    /**
     * The action performed after action
     *
     * @param e the object that triggered this
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        firstAction(banner, this);
        addUnit = new AddUnitType(banner, team, (Country) getSelectedItem());
    }

    /**
     * @return Will return true if it is still inputting, otherwise will return false
     */
    public boolean isStillInputting() {
        try {
            return addUnit.isStillInputting();
        } catch (NullPointerException n) {
            return true;
        }
    }
}
