/**
 * MoveInput.java
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

public class MoveInput extends Input implements ActionListener{
    private InputBanner banner = null;
    private Move order;

    /**
     * The constructor for this class
     *
     * @param banner the banner to add the input to
     */
    public MoveInput(InputBanner banner){
        this.banner = banner;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.setSelectedItem("choose where to move");

        for (Country moveTo : banner.getCountry().getMovableTo()) {
            if (moveTo instanceof ScoringCountry) {
                if (((ScoringCountry) moveTo).getTeamControls() == banner.getCountry().getTeam()) {
                    model.addElement(moveTo);
                }
            } else {
                model.addElement(moveTo);
            }
        }

        setModel(model);
        setSize(longestItem(), 25);
        addActionListener(this);
    }

    /**
     * The action event for this object
     *
     * @param e the object that triggered this event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        firstAction(banner);
        order.setMovingTo((Country) getSelectedItem());
        Submit submit = new Submit(banner);
        lastAction(banner, submit);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) throws ClassCastException{
        this.order = (Move) order;
    }
}
