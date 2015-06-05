package command;

import command.order.Attack;
import command.order.Defend;
import command.order.Order;
import command.order.Support;
import java.util.ArrayList;
import java.util.Collections;
import map.Country;

public class OrderResolver extends ArrayList<Order> {
    private static final Boolean trueBoolean = new Boolean(true);
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

        for (Order order : orders) {
            if (order.isValid() == null) {
                order.setInvalid();
            }
        }

        calculateAttackPowers();
        printCommands();

    }

    private static ArrayList<Order> getUnCanceledOrders(ArrayList<Order> orders){
        ArrayList<Order> uncanceledOrders = new ArrayList<Order>();
        for(Order order : orders){
            if(!isCanceled(order)){
                uncanceledOrders.add(order);
            }
        }
        return uncanceledOrders;
    }

    private static boolean isCanceled(Order order){
        for(Order o : orders){
            if(o instanceof Attack){
                Attack attack = (Attack) o;
                if (attack.getAttacking() == order.getOrderFrom()) {
                    if (!new Boolean(false).equals(attack.isValid())) {
                        return true;
                    }
                }
            }
        }
        order.setValid();
        return false;
    }

    private static ArrayList<Order> cancelSomeOrders(ArrayList<Order> executableOrders) {
        ArrayList<Order> invalidOrders = new ArrayList<Order>();
        for (Order order : executableOrders) {
            if (order instanceof Attack) {
                if (new Boolean(true).equals(order.isValid())) {
                    if (((Attack) order).getAttacking().getOrder() == null) {
                    } else {
                        ((Attack) order).getAttacking().getOrder().setInvalid();
                    }
                } else {
                    System.out.println("Wrong cancel" + order);
                }
            } else {
                System.out.println("Wrong order type");
            }
        }

        for(Order order : orders){
            if (order.isValid() == null) {
                invalidOrders.add(order);
            }
        }

        return invalidOrders;
    }

    private static void printCommands(){
        System.out.println("Printing Orders");
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

    private static void calculateAttackPowers() {
        for (Order order : orders) {
            if (trueBoolean.equals(order.isValid())) {
                if (order instanceof Support) {
                    Support support = (Support) order;
                    support.increaseAttackPower();
                } else if (order instanceof Defend) {
                    Defend defend = (Defend) order;
                    defend.increaseDefense();
                }
            }
        }

        //This is to see if an attack overpowers a country.
        for (Order order : orders) {
            if (trueBoolean.equals(order.isValid())) {
                if (order instanceof Attack) {

                }
            }
        }

    }
}
