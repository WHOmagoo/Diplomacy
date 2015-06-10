package command.input;

import command.InputBanner;
import constants.Team;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import map.Country;
import map.TileType;
import map.UnitType;

/**
 * Created by Hugh on 6/10/2015.
 */
public class addUnitType extends Input implements ActionListener {
    InputBanner banner;
    Country locationAdding;
    Team team;
    private volatile boolean isStillInputting = true;

    public addUnitType(InputBanner banner, Team team, Country locationPicked) {
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
    }

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

    public boolean isStillInputting() {
        return isStillInputting;
    }
}
