/**
 * AddUnitType.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package command.input;

import command.InputBanner;
import constants.Team;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import map.Country;
import map.TileType;
import map.UnitType;

public class AddUnitType extends Input implements ActionListener {
    InputBanner banner;
    Country locationAdding;
    Team team;
    private volatile boolean isStillInputting = true;

    /**
     * The unit type to be added
     *
     * @param banner         the banner to display this to
     * @param team           the team that is adding a unit
     * @param locationPicked where the team is adding a unit
     */
    public AddUnitType(InputBanner banner, Team team, Country locationPicked) {
        super(banner);
        this.banner = banner;
        this.team = team;
        locationAdding = locationPicked;
        DefaultComboBoxModel<UnitType> model = new DefaultComboBoxModel<UnitType>();

        model.addElement(UnitType.ARMY);
        if (locationPicked.getTileType() == TileType.Coastal) {
            model.addElement(UnitType.NAVY);
        }

        if (model.getSize() == 1) {
            model.setSelectedItem(UnitType.ARMY);
        } else {
            model.setSelectedItem("which type of unit");
        }

        setModel(model);
        setSize(longestItem(), 25);
        addActionListener(this);
        banner.setLastVisible(this);
        if (model.getSize() == 1) {
            banner.setLastVisible(this);
            setSelectedIndex(0);
        }
    }

    /**
     * The action performed on action
     *
     * @param e the object that triggered it
     */
    public void actionPerformed(ActionEvent e) {
        firstAction(banner, this);
        Submit submit = new Submit(banner);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                locationAdding.setOccupiedBy(team, (UnitType) getSelectedItem());
                locationAdding.refreshGraphics();
                isStillInputting = false;
            }
        });
        lastAction(banner, submit);
    }

    /**
     * @return will return true if the user is still submitting their orders, otherwise false
     */
    public boolean isStillInputting() {
        return isStillInputting;
    }
}
