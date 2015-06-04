package command;

import command.order.Attack;
import command.order.Order;
import java.util.ArrayList;
import map.Country;

public class OrderResolver extends ArrayList<Order> {

    public OrderResolver(ArrayList<Country> countries) {
        for (Country c : countries) {
            if (c.getOrder() != null) {
                add(c.getOrder());
            }
        }

        ArrayList<Order> freeOrdersCalculated1 = getFreeOrders();
        while (!allIsValid()) {
            ArrayList<Order> freeOrdersCalculated2 = new ArrayList<Order>();
            while (!freeOrdersCalculated1.equals(freeOrdersCalculated2)) {
                freeOrdersCalculated1 = getFreeOrders();
                freeOrdersCalculated2 = getFreeOrders();
            }
            cancelSomeOrders(freeOrdersCalculated1);
        }

        System.out.println("Done!");
    }

    public ArrayList<Order> getFreeOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        for (Order order : this) {
            if (!order.isAttacked()) {
                for (Order otherOrder : this) {
                    if (order.isCanceledBy(otherOrder)) {
                        order.addAttackedBy(otherOrder.getOrderFrom());
                    }
                }
            }
            if (!order.isAttacked()) {
                order.setValid();
                orders.add(order);
            }
            }

        return orders;
    }

    private void cancelSomeOrders(ArrayList<Order> executableOrders) {
        for (Order order : executableOrders) {
            if (order instanceof Attack) {
                if (order.isValid() == true) {
                    ((Attack) order).cancelOtherOrder();
                } else {
                    System.out.println("Wrong cancel");
                }
            }

        }
    }

    private void printCanceledOrders() {
        for (Order o : this) {
            try {
                System.out.println(o + " is canceled by " + o.getAttackedBy());
            } catch (Exception e) {
            }
        }
    }

    private boolean allIsValid() {
        for (Order o : this) {
            if (o.isValid() == null) {
                return false;
            }
        }

        return true;
    }
}
