/**
 * RemoveUnit.java
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
import map.UnitType;

public class RemoveUnit extends Input implements ActionListener {
    InputBanner banner;
    Country country;
    private volatile boolean isStillInputting = true;

    /**
     * The constructor for this class
     *
     * @param banner           the banner to add this to.
     * @param teamToRemoveTile the team that is removing a tile
     */
    public RemoveUnit(InputBanner banner, Team teamToRemoveTile) {
        super(banner);
        this.banner = banner;
        country = banner.getCountry();

        DefaultComboBoxModel<Country> model = new DefaultComboBoxModel<Country>();
        for (Country c : banner.getMap().getCountries()) {
            if (c.getTeam() == teamToRemoveTile) {
                model.addElement(c);
            }
        }

        model.setSelectedItem("choose a unit to remove");

        String infoText = teamToRemoveTile.toString();
        if (model.getSize() == 1) {
            infoText += " choose a unit to remove";
        }

        Info teamRemove = new Info(infoText);
        banner.add(teamRemove);

        setModel(model);
        setSize(longestItem(), 25);
        addActionListener(this);
        banner.setLastVisible(this);
        if (model.getSize() == 1) {
            setSelectedIndex(0);
        }
    }

    /**
     * The action event that is triggered on action
     *
     * @param e the object that triggered the action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        firstAction(banner, this);
        Submit submit = new Submit(banner);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStillInputting = false;
                ((Country) getSelectedItem()).setOccupiedBy(Team.NULL, UnitType.EMPTY);
                ((Country) getSelectedItem()).refreshGraphics();
            }
        });
        lastAction(banner, submit);
    }

    /**
     * @return will return true if the order has not yet been submitted, otherwise false
     */
    public boolean isStillInputting() {
        return isStillInputting;
    }
}
