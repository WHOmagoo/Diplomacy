package command.input;

import command.Info;
import command.InputBanner;
import command.OrderResolver;
import command.order.Move;
import command.order.Order;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import map.Country;

public class RelocateInput extends Input implements ActionListener {
    Country countryAssociation;
    int locationOfThisCountry;
    private Move relocatingTo;
    private InputBanner banner;
    private ArrayList<Country> countriesToRelocate;

    public RelocateInput(int locationOfCountryToRelocate, ArrayList<Country> countriesToRelocate) {
        super();
        locationOfThisCountry = locationOfCountryToRelocate;
        countryAssociation = countriesToRelocate.get(locationOfCountryToRelocate);
        this.countriesToRelocate = countriesToRelocate;
        DefaultComboBoxModel<Country> model = new DefaultComboBoxModel<Country>();

        for (Country moveTo : countryAssociation.getRelocateableNeighbors()) {
            model.addElement(moveTo);
        }
        model.setSelectedItem("choose where to relocate to");

        setModel(model);
        setSize(longestItem(), 25);
        addActionListener(this);
        this.banner = new InputBanner(countryAssociation.getMap(), countryAssociation);
        banner.add(new Info(countryAssociation + " relocates to"));
        banner.add(this);
        banner.setLastVisible(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        firstAction(banner);
        relocatingTo = new Move(countryAssociation, (Country) getSelectedItem());
        Submit submit = new Submit(banner);
        lastAction(banner, submit);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (locationOfThisCountry < countriesToRelocate.size() - 1) {
                    RelocateInput relocateInput = new RelocateInput(locationOfThisCountry + 1, countriesToRelocate);
                } else {
                    countryAssociation.setOrder(relocatingTo);
                    OrderResolver.resolveOrders(countriesToRelocate);
                    ArrayList<Country> stillNeedRelocation = new ArrayList<Country>();
                    for (Country c : countriesToRelocate) {
                        if (!c.getOrder().succeeds()) {
                            stillNeedRelocation.add(c);
                        }
                    }

                    if (stillNeedRelocation.size() > 0) {
                        RelocateInput relocateInput = new RelocateInput(0, stillNeedRelocation);
                    } else {
                        countryAssociation.getMap().moveUnits();
                        countryAssociation.getMap().updateGraphics();
                    }
                }
            }
        });

    }

    public Order getOrder() {
        return relocatingTo;
    }
}
