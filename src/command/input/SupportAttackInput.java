/**
 * SupportAttackInput.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package command.input;

import command.InputBanner;
import command.order.Order;
import command.order.Support;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import map.Country;

public class SupportAttackInput extends Input implements ActionListener{
    private DefaultComboBoxModel<Country> model = new DefaultComboBoxModel();
    private InputBanner banner;
    private Support order;

    /**
     * The constructor for this class
     *
     * @param supporting the country that is being supported.
     */
    public SupportAttackInput(Country supporting){
        super(supporting.getMap().getBanner());
        banner = supporting.getMap().getBanner();
        for(Country c : supporting.getSupportableInCommon(supporting.getMap().getLastCountryClicked())){
            model.addElement(c);
        }

        model.setSelectedItem("choose who to support the attack to");
        setModel(model);
        setSize(longestItem(), 25);
        addActionListener(this);

    }

    /**
     * The action performed on action, it will prompt users to submit the order
     *
     * @param e the object that triggered this method
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        order.setAttacking((Country) getSelectedItem());
        firstAction(banner);
        Submit submit = new Submit(banner);
        lastAction(banner, submit);
        //submit.startRollover();
    }

    /**
     * @return the order that this has edited
     */
    public Order getOrder() {
        return order;
    }

    /**
     * @param order the order that this should be editing
     */
    public void setOrder(Support order) {
        this.order = order;
    }

}
