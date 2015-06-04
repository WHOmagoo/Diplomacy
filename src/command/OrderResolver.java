package command;

import command.order.Attack;
import command.order.Order;
import map.Country;

import java.util.ArrayList;
import java.util.Collections;

public class OrderResolver extends ArrayList<Order> {
    private static ArrayList<Order> orders = new ArrayList<Order>();

    public static void resolveOrders(ArrayList<Country> countries) {

        for (Country c : countries) {
            if (c.getOrder() != null) {
                orders.add(c.getOrder());
            }
        }

        ArrayList<Order> invalidOrders1 = cancelSomeOrders(getUnCanceledOrders(orders));
        ArrayList<Order> invalidOrders2 = cancelSomeOrders(getUnCanceledOrders(invalidOrders1));

        while (!invalidOrders1.equals(invalidOrders2)) {
            invalidOrders1 = cancelSomeOrders(getUnCanceledOrders(invalidOrders2));
            invalidOrders2 = cancelSomeOrders(getUnCanceledOrders(invalidOrders1));
            Collections.sort(invalidOrders1);
            Collections.sort(invalidOrders2);
        }
        printCommands();

    }

    private static ArrayList<Order> getUnCanceledOrders(ArrayList<Order> orders){
        ArrayList<Order> uncanceledOrders = new ArrayList<Order>();
        for(Order order : orders){
            if(!isCanceled(order)){
                order.setValid();
                uncanceledOrders.add(order);
            }
        }

        return uncanceledOrders;
    }

    private static boolean isCanceled(Order order){
        for(Order o : orders){
            if(o instanceof Attack){
                Attack attack = (Attack) o;
                if(attack.getAttacking().getOrder() == order){
                    return true;
                }
            }
        }
        return false;
    }

    private static ArrayList<Order> cancelSomeOrders(ArrayList<Order> executableOrders) {
        ArrayList<Order> invalidOrders = new ArrayList<Order>();
        for (Order order : executableOrders) {
            if (order instanceof Attack) {
                if (order.isValid() == true) {
                    try {
                        ((Attack) order).getAttacking().getOrder().setInvalid();
                    } catch (NullPointerException nullPointer){
                    }
                } else {
                    System.out.println("Wrong cancel");
                }
            } else {
                System.out.println("Wrong order type");
            }

        }

        for(Order order : orders){
            if(!order.isValid()){
                invalidOrders.add(order);
            }
        }

        return invalidOrders;
    }

    private static void printCanceledOrders() {
        for (Order o : orders) {
            try {
                System.out.println(o + " is canceled by " + o.getAttackedBy());
            } catch (Exception e) {
            }
        }
    }

    private static void printCommands(){
        for(Order order : orders){
            System.out.println("Command: " + order + " - " + order.isValid());
        }
    }

    private static boolean allIsValidated() {
        System.out.println("Here's the order resolution");
        for (Order o : orders) {
            if (!o.isValid()) {
                return false;
            }
        }

        return true;
    }
}
