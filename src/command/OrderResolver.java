package command;

import command.order.*;
import map.Country;
import map.Map;

import java.util.ArrayList;
import java.util.Collections;

public class OrderResolver extends ArrayList<Order> {
    private ArrayList<Order> orders = new ArrayList<Order>();

    public OrderResolver(ArrayList<Country> countries) {
        orders = new ArrayList<Order>();
        for (Country c : countries) {
            if (c.getOrder() == null) {
                c.setOrder(new Hold(c));
            }

            if (!(c.getOrder() instanceof Hold)) {
                orders.add(c.getOrder());
            }
        }

    }

    public void resolve() {
        if (orders.size() > 0) {
            ArrayList<Order> invalidOrders1 = cancelSomeOrders(getUnCanceledOrders(orders));
            ArrayList<Order> invalidOrders2 = cancelSomeOrders(getUnCanceledOrders(invalidOrders1));
            Collections.sort(invalidOrders1);
            Collections.sort(invalidOrders2);

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
                    throw new Error(order + " was not validated");
                }
            }

            calculateDefensePowers();
            calculateAttackPowers();
            identifyAttackBounces();
            identifyMoveBounces();
            failBounced();
            calculateMoves();
            labelAttackedForMove();

            ArrayList<Country> temp = new ArrayList<Country>();
            for (Order o : orders) {
                if (o instanceof Move || o instanceof Attack) {
                    if (o.succeeds()) {
                        temp.add(o.orderFrom());
                    }
                }
            }
            orders.get(0).orderFrom().getMap().moveUnits(temp);
        }
    }

    private ArrayList<Order> getUnCanceledOrders(ArrayList<Order> orders) {
        ArrayList<Order> uncanceledOrders = new ArrayList<Order>();
        for(Order order : orders){
            if (!(order instanceof Hold)) {
                if (!isCanceled(order)) {
                    uncanceledOrders.add(order);
                }
            }
        }
        return uncanceledOrders;
    }

    private boolean isCanceled(Order order) {
        for(Order o : orders){
            if(o instanceof Attack){
                Attack attack = (Attack) o;
                if (attack.getAttacking() == order.orderFrom()) {
                        return true;
                }
            }
        }
        order.setValid();
        return false;
    }

    private ArrayList<Order> cancelSomeOrders(ArrayList<Order> executableOrders) throws Error {
        ArrayList<Order> invalidOrders = new ArrayList<Order>();
        for (Order order : executableOrders) {
            if (order instanceof Attack) {
                if (Boolean.TRUE == order.isValid()) {
                    if (((Attack) order).getAttacking().getOrder() == null) {
                    } else {
                        ((Attack) order).getAttacking().getOrder().setInvalid();
                    }
                } else {
                    throw new Error("Tried to cancel commands using a falsely valid attack");
                }
            } else {
            }
        }

        for(Order order : orders){
            if (order.isValid() == null) {
                invalidOrders.add(order);
            }
        }

        return invalidOrders;
    }

    private void catchAttackLoop() {
        for(Order order : orders){
            if (order instanceof Attack) {
                Attack attack = (Attack) order;
                Country originalAttack = attack.orderFrom();
                Country countyAttacking = attack.getAttacking();
                while (countyAttacking.getOrder() instanceof Attack && countyAttacking.getOrder().isValid() == null) {
                    if (originalAttack == countyAttacking) {
                        attack.setAttackLooped(true);
                        break;
                    } else {
                        countyAttacking = ((Attack) countyAttacking.getOrder()).getAttacking();
                    }
                }
            }
        }

        //This cancels the orders that are attack looped
        for (Order order : orders) {
            if (order instanceof Attack) {
                if (((Attack) order).isAttackLooped()) {
                    order.setInvalid();
                    order.setSucceeded(false);
                }
            }
        }
    }

    private void catchMoveLoop() {
        for (Order order : orders) {
            if (order instanceof Move) {
                Move move = (Move) order;
                Country originalMove = move.orderFrom();
                Country movingTo = move.getMovingTo();
                ArrayList<Country> countriesLookedAt = new ArrayList<Country>();
                countriesLookedAt.add(originalMove);
                while (movingTo.getOrder() instanceof Move) {
                    if (countriesLookedAt.contains(movingTo)) {
                        if (movingTo == originalMove) {
                            move.setValid();
                            move.setMoveLooped();
                        }
                        break;
                    } else {
                        countriesLookedAt.add(movingTo);
                        movingTo = ((Move) movingTo.getOrder()).getMovingTo();
                    }
                }
            }
        }
    }

    private void calculateDefensePowers() {
        for (Order order : orders) {
            if (order.isValid() == Boolean.TRUE) {
                if (order instanceof Defend) {
                    ((Defend) order).increaseDefense();
                }
            }
        }
    }

    private void calculateAttackPowers() {
        for (Order order : orders) {
            if (Boolean.TRUE == order.isValid()) {
                if (order instanceof Support) {
                    if (((Support) order).increaseAttackPower()) {
                        order.setSucceeded(true);
                    }
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

    private void identifyAttackBounces() {
        for (Order order : orders) {
            if (order instanceof Attack) {
                Attack attack = (Attack) order;
                if (attack.succeeds()) {
                    for (Country c : attack.getAttacking().getBorders()) {
                        if (c.getOrder() instanceof Attack && c != attack.orderFrom()) {
                            if (c.getOrder().isValid() == Boolean.TRUE) {
                                Attack otherAttack = (Attack) c.getOrder();
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

    private void identifyMoveBounces() {
        for (Order order : orders) {
            if (order instanceof Move) {
                Move move = (Move) order;
                if (move.isValid() == Boolean.TRUE) {
                    if (move.getMovingTo().getOrder() instanceof Attack) {
                        if (!move.getMovingTo().getOrder().succeeds()) {
                            move.setBounced(true);
                        }
                    } else {
                        for (Country c : move.getMovingTo().getBorders()) {
                            if (c.isOccupied() && c != move.orderFrom()) {
                                if (c.getOrder().isValid() == Boolean.TRUE) {
                                    if (c.getOrder() instanceof Attack) {
                                        if (((Attack) c.getOrder()).getAttacking() == move.getMovingTo()) {
                                            move.setBounced(true);
                                            break;
                                        }
                                    } else if (c.getOrder() instanceof Move) {
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
    }

    private void failBounced() {
        for (Order order : orders) {
            if (order.isBounced()) {
                order.setSucceeded(false);
            }
        }
    }

    private void calculateMoves() {
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
        }
    }

    private boolean moveLoopIsValid(Move move) {
        Country original = move.orderFrom();
        Country other = move.getMovingTo();
        while (original != other && other.getOrder() instanceof Move) {
            if (other.getOrder() instanceof Move) {
                if (other.getOrder().isValid() && !other.getOrder().isBounced()) {
                    other = ((Move) other.getOrder()).getMovingTo();
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        if (!(other.getOrder() instanceof Move)) {
            return false;
        } else {
            return true;
        }
    }

    private void labelAttackedForMove() {
        ArrayList<Country> movesFirst = new ArrayList<Country>();
        ArrayList<Country> movesSecond = new ArrayList<Country>();
        ArrayList<Country> movesThird = new ArrayList<Country>();
        for (Order o : orders) {
            if (o instanceof Attack) {
                if (o.succeeds()) {
                    if (((Attack) o).getAttacking().isOccupied()) {
                        movesThird.add(((Attack) o).getAttacking());
                    } else {
                        movesFirst.add(o.orderFrom());
                    }
                }
            } else {
                if (o instanceof Move) {
                    if (o.succeeds()) {
                        movesSecond.add(o.orderFrom());
                    }
                }
            }
        }

        updateGraphics(movesFirst);
        updateGraphics(movesSecond);
        for (Country c : movesThird) {
            Map map = c.getMap();
            map.relocatePrompt(c);
            while (map.isStillRelocating()) {
            }
        }
        while(movesBounce(movesThird)){
            for(Country c : movesThird){
                if(!c.getOrder().succeeds()){
                    Map map = c.getMap();
                    map.relocatePrompt(c);
                    while (map.isStillRelocating()){

                    }
                }
            }
        }

        if (movesThird.size() > 0) {
            OrderResolver resolver = new OrderResolver(movesThird);
            resolver.resolve();
        }
    }

    @Deprecated //This is only intended for bug testing
    private void printCommands() {
        System.out.println("Printing Orders");
        for (Order order : orders) {
            order.orderFrom().getMap().printOrders();
            break;
        }
    }

    public boolean movesBounce(ArrayList<Country> countriesToRelocate) {
        boolean movesBounce = false;
        for (Country c : countriesToRelocate) {
            for (Country c2 : countriesToRelocate) {
                if (c != c2) {
                    if (c.getOrder() instanceof Move && c2.getOrder() instanceof Move) {
                        if (((Move) c.getOrder()).getMovingTo() == ((Move) c2.getOrder()).getMovingTo()) {
                            c.getOrder().setSucceeded(false);
                            movesBounce = true;
                        }
                    }
                }
            }
        }

        return movesBounce;
    }

    public void updateGraphics(ArrayList<Country> countries) {
        if (orders.size() > 0) {
            orders.get(0).orderFrom().getMap().moveUnits(countries);
        }
    }
}