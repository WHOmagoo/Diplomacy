package command.input;

import command.Info;
import command.InputBanner;
import constants.Team;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import map.Country;
import map.ScoringCountry;

/**
 * Created by Hugh on 6/10/2015.
 */
public class AddUnit extends Input implements ActionListener {
    InputBanner banner;
    Country country;
    Team team;
    addUnitType addUnit = null;

    public AddUnit(InputBanner banner, Team team) {
        super(banner);
        this.banner = banner;
        this.team = team;
        country = banner.getCountry();
        Info teamAdd = new Info(team.toString());
        banner.add(teamAdd);

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

        if (model.getSize() > 1) {
            model.setSelectedItem("choose a location to add a unit");
        }

        setModel(model);
        setSize(longestItem(), 25);

        addActionListener(this);

        if (model.getSize() == 1) {
            banner.setLastVisible(this);
            setSelectedIndex(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        firstAction(banner, this);
        addUnit = new addUnitType(banner, team, (Country) getSelectedItem());
        if (addUnit.getModel().getSize() != 1) {
            lastAction(banner, addUnit);
        }
    }

    public boolean isStillInputting() {
        try {
            return addUnit.isStillInputting();
        } catch (NullPointerException n) {
            return true;
        }
    }
}
