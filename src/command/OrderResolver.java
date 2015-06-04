package command;

import command.order.Attack;
import command.order.Order;
import java.util.ArrayList;
import map.Country;

public class OrderResolver {
    private ArrayList<Order> orders = new ArrayList<Order>();
    private ArrayList<Country> countries;

    public OrderResolver(ArrayList<Country> countries) {
        this.countries = countries;
        for (Country c : countries) {
            orders.add(c.getOrder());
        }
        ;
        ArrayList<Order> freeOrdersCalculated1 = getFreeOrders();
        ArrayList<Order> freeOrdersCalculated2 = new ArrayList<Order>();
        while (!freeOrdersCalculated1.equals(freeOrdersCalculated2)) {
            freeOrdersCalculated1 = getFreeOrders();
            freeOrdersCalculated2 = getFreeOrders();
        }

        System.out.println("Finished");
        System.out.println(freeOrdersCalculated1);

    }

    public ArrayList<Order> getFreeOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        for (Order order : this.orders) {
            if (order instanceof Attack) {
                Attack attack = (Attack) order;
                if (attack.isValid()) {
                    for (Order otherOrder : this.orders) {
                        if (otherOrder instanceof Attack) {
                            Attack otherAttack = (Attack) otherOrder;
                            if (otherAttack.cancels(attack)) {
                                attack.setCanceledBy(otherAttack.getOrderFrom());
                                break;
                            }
                        }
                    }
                }
                if (attack.isValid() == true) {
                    orders.add(attack);
                }
            }
        }

        return orders;
    }

    private void cancelOrders(ArrayList<Order> executableOrders) {
        for (Order order : executableOrders) {

        }
    }
}
