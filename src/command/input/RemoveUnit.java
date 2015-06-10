package command.input;

import command.Info;
import command.InputBanner;
import constants.Team;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import map.Country;
import map.UnitType;

/**
 * Created by Hugh on 6/10/2015.
 */
public class RemoveUnit extends Input implements ActionListener {
    InputBanner banner;
    Country country;
    private volatile boolean isStillInputting = true;

    public RemoveUnit(InputBanner banner, Team teamToRemoveTile) {
        super(banner);
        this.banner = banner;
        country = banner.getCountry();
        Info teamRemove = new Info(teamToRemoveTile.toString());
        banner.add(teamRemove);

        DefaultComboBoxModel<Country> model = new DefaultComboBoxModel<Country>();
        for (Country c : banner.getMap().getCountries()) {
            if (c.getTeam() == teamToRemoveTile) {
                model.addElement(c);
            }
        }

        model.setSelectedItem("choose a unit to remove");

        setModel(model);
        setSize(longestItem(), 25);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        firstAction(banner, this);
        Submit submit = new Submit(banner);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStillInputting = false;
                ((Country) getSelectedItem()).setOccupiedBy(Team.NULL, UnitType.EMPTY);
            }
        });
        lastAction(banner, submit);
    }

    public boolean isStillInputting() {
        return isStillInputting;
    }
}
