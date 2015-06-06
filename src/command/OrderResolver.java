package command;

import command.order.*;
import java.util.ArrayList;
import java.util.Collections;
import map.Country;

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

        catchAttackLoop();
        catchMoveLoop();

        for (Order order : orders) {
            if (order.isValid() == null) {
                System.out.println(order + " is invalid by default");
                order.setInvalid();
            }
        }

        calculateDefensePowers();
        calculateAttackPowers();
        identifyAttackBounces();
        identifyMoveBounces();
        failBounced();
        calculateMoves();
        printCommands();

    }

    private static void calculateDefensePowers() {
        for (Order order : orders) {
            if (order.isValid() == Boolean.TRUE) {
                if (order instanceof Defend) {
                    ((Defend) order).increaseDefense();
                }
            }
        }
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
                    if (!Boolean.FALSE.equals(attack.isValid())) {
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
                if (Boolean.TRUE.equals(order.isValid())) {
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

    private static void catchAttackLoop() {
        for(Order order : orders){
            if (order instanceof Attack) {
                Attack attack = (Attack) order;
                Country originalAttack = attack.getOrderFrom();
                Country countyAttacking = attack.getAttacking();
                while (!(countyAttacking.getOrder() instanceof Attack)) {
                    if (originalAttack == countyAttacking && attack.isValid() == null) {
                        originalAttack.getOrder().setValid();
                        break;
                    } else {
                        countyAttacking = ((Move) countyAttacking.getOrder()).getMovingTo();
                    }
                }
            }
        }
    }

    private static void catchMoveLoop() {
        for (Order order : orders) {
            if (order instanceof Move) {
                Move move = (Move) order;
                Country originalMove = move.getOrderFrom();
                Country movingTo = move.getMovingTo();
                while (!(movingTo.getOrder() instanceof Move)) {
                    if (originalMove == movingTo && move.isValid() == null) { //This may cause an error
                        originalMove.getOrder().setValid();
                        ((Move) originalMove.getOrder()).setMoveLooped();
                        break;
                    } else {
                        movingTo = ((Move) movingTo.getOrder()).getMovingTo();
                    }
                }
            }
        }
    }

    private static void calculateAttackPowers() {
        for (Order order : orders) {
            if (Boolean.TRUE == order.isValid()) {
                if (order instanceof Support) {
                    ((Support) order).increaseAttackPower();
                }
            }
        }

        //This is to see if an attack overpowers a country.
        for (Order order : orders) {
            if (order instanceof Attack) {
                Attack attack = (Attack) order;
                if (attack.overpowers()) {
                    attack.setSucceeded(true);
                }
            }
        }

    }

    private static void identifyAttackBounces() {
        for (Order order : orders) {
            if (order instanceof Attack) {
                Attack attack = (Attack) order;
                if (attack.succeeds()) {
                    for (Country otherCountry : attack.getAttacking().getBorders()) {
                        if (otherCountry.getOrder() instanceof Attack) {
                            if (otherCountry.getOrder().isValid() == Boolean.TRUE) {
                                Attack otherAttack = (Attack) otherCountry.getOrder();
                                if (otherAttack.getAttacking() == attack.getAttacking()) {
                                    if (otherAttack.getAttackPower() >= attack.getAttackPower()) {
                                        attack.setBounced(true);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void identifyMoveBounces() {
        for (Order order : orders) {
            if (order instanceof Move) {
                Move move = (Move) order;
                if (move.isValid()) {
                    if (move.getMovingTo().getOrder() instanceof Attack) {
                        if (!move.getMovingTo().getOrder().succeeds()) {
                            move.setBounced(true);
                        }
                    } else {
                        for (Country c : move.getMovingTo().getBorders()) {
                            if (c.getOrder().succeeds()) {
                                if (c.getOrder() instanceof Attack) {
                                    if (((Attack) c.getOrder()).getAttacking() == move.getMovingTo()) {
                                        move.setBounced(true);
                                        break;
                                    }
                                }
                            } else if (c.getOrder() instanceof Move) {
                                if (c.getOrder().isValid()) {
                                    if (((Move) c.getOrder()).getMovingTo() == move.getMovingTo()) {
                                        move.setBounced(true);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void failBounced() {
        for (Order order : orders) {
            if (order.isBounced()) {
                order.setSucceeded(false);
            }
        }
    }

    public static void calculateMoves() {
        ArrayList<Move> moves = new ArrayList<Move>();

        for (Order order : orders) {
            if (order instanceof Move) {
                if (!order.isBounced() && order.isValid()) {
                    moves.add((Move) order);
                }
            }
        }

        for (Move move : moves) {
            if (!move.getMovingTo().isOccupied()) {
                move.setSucceeded(true);
            } else if (move.getMovingTo().getOrder() instanceof Attack) {
                if (move.getMovingTo().getOrder().succeeds()) {
                    move.setSucceeded(true);
                }
            } else if (move.getMovingTo().getOrder() instanceof Move) {
                if (((Move) move.getMovingTo().getOrder()).isMoveLooped()) {
                    if (moveLoopIsValid(move)) {
                        move.setSucceeded(true);
                    }
                }
            }

            if (move.succeeds()) {
                moves.remove(move);
            }
        }
    }

    private static boolean moveLoopIsValid(Move move) {
        Country original = move.getOrderFrom();
        Country other = move.getMovingTo();
        while (original != other) {
            if (other.getOrder() instanceof Move) {
                if (other.getOrder().isValid()) {
                    other = ((Move) other.getOrder()).getMovingTo();
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
    }

    private static void printCommands() {
        System.out.println("Printing Orders");
        for (Order order : orders) {
            System.out.println("Command: " + order + " - " + order.isValid());
        }
    }

    @Deprecated
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