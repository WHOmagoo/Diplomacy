/**
 * RelocateInput.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package command.input;

import command.InputBanner;
import command.order.Move;
import command.order.Order;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import map.Country;
import map.ScoringCountry;

public class RelocateInput extends Input implements ActionListener {
    Country countryAssociation;
    volatile boolean isStillRelocating = true;
    private Move relocatingTo;

    /**
     * The constructor for this class
     *
     * @param banner the banner to add this to
     * @param c      the country that is relocating
     */
    public RelocateInput(InputBanner banner, Country c) {
        super();
        setBanner(banner);
        countryAssociation = banner.getCountry();
        this.countryAssociation = c;
        DefaultComboBoxModel<Country> model = new DefaultComboBoxModel<Country>();

        for (Country moveTo : countryAssociation.getRelocateableToNeighbors()) {
            if (moveTo instanceof ScoringCountry) {
                if (((ScoringCountry) moveTo).getTeamControls() == c.getTeam()) {
                    model.addElement(moveTo);
                }
            } else {
                model.addElement(moveTo);
            }
        }
        model.setSelectedItem("choose where to relocate to");

        setModel(model);
        setSize(longestItem(), 25);
        addActionListener(this);
        //this.banner = new InputBanner(countryAssociation.getMap(), countryAssociation);
    }

    /**
     * The action performed on action
     *
     * @param e the object that triggered this
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        firstAction(getBanner());
        relocatingTo = new Move(countryAssociation);
        relocatingTo.setMovingTo((Country) getSelectedItem());
        Submit submit = new Submit(getBanner());
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                relocatingTo.setMovingTo((Country) getSelectedItem());
                countryAssociation.setOrder(relocatingTo);
                isStillRelocating = false;
            }
        });
        lastAction(getBanner(), submit);
    }

    /**
     * @return the order that this has created
     */
    public Order getOrder() {
        return relocatingTo;
    }

    /**
     * @return returns true if the prompt has not had its order submitted, otherwise false.
     */
    public boolean isStillRelocating() {
        return isStillRelocating;
    }
}
