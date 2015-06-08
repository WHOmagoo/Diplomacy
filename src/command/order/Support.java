package command.order;

import map.Country;

public class Support extends Order {
    private Country supporting;
    private Country attacking;

    public Support(Country orderFrom, Country supporting, Country attacking) {
        super(orderFrom);
        this.supporting = supporting;
        this.attacking = attacking;
    }

    public Support(Country orderFrom) {
        super(orderFrom);
    }

    public Country getSupporting() {
        return supporting;
    }

    public void setSupporting(Country countryToSupport){
        this.supporting = countryToSupport;
    }

    public Country getAttacking() {
        return attacking;
    }

    public void setAttacking(Country countryToAttack){
        this.attacking = countryToAttack;
    }

    public boolean increaseAttackPower() throws NullPointerException {
        if (valid) {
            if (supporting.getOrder() instanceof Attack) {
                Attack temp = (Attack) supporting.getOrder();
                if (temp.getAttacking() == attacking) {
                    temp.addAttackPower(this);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return orderFrom + " supports " + supporting + "'s attack on " + attacking;
    }
}
