package command;

import command.input.AddUnit;
import command.input.RemoveUnit;
import command.order.*;
import constants.Team;
import java.util.ArrayList;
import java.util.Collections;
import map.Country;
import map.Map;
import map.ScoringCountry;

public class OrderResolver extends ArrayList<Order> {
    private ArrayList<Order> orders = new ArrayList<Order>();
    private ArrayList<Country> countries = new ArrayList<Country>();

    public OrderResolver(ArrayList<Country> countries) {
        this.countries = countries;
        orders = new ArrayList<Order>();
        for (Country c : this.countries) {
            if (c.getOrder() == null) {
                c.setOrder(new Hold(c));
            } else if (!(c.getOrder() instanceof Hold)) {
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
                    System.out.println(order + " was not validated");
//                  throw new Error(order + " was not validated");
                }
            }

            calculateDefensePowers();
            calculateAttackPowers();
            identifyAttackBounces();
            identifyMoveBounces();
            failBounced();
            calculateMoves();
            moveUnits();
            resetCountries();
            printCommands();
        }
    }

    private void resetCountries() {
        for (Country c : countries) {
            c.resetOrder();
        }
    }

    private ArrayList<Order> getUnCanceledOrders(ArrayList<Order> orders) {
        ArrayList<Order> uncanceledOrders = new ArrayList<Order>();
        for(Order order : orders){
            if (!(order instanceof Hold)) {
                if (!isCanceled(order)) {
                    order.setValid();
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
                if (attack.getAttacking() == order.orderFrom() && attack.isValid() != Boolean.FALSE) {
                        return true;
                }
            }
        }
        return false;
    }

    private ArrayList<Order> cancelSomeOrders(ArrayList<Order> executableOrders) throws Error {
        ArrayList<Order> invalidOrders = new ArrayList<>();
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
            if (order instanceof Defend) {
                if (((Defend) order).increaseDefense()) {
                    order.setSucceeded(true);
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
                if (attack.overpowers() && attack.isValid() == Boolean.TRUE) {
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

        ArrayList<Move> move1 = moves;
        ArrayList<Move> move2 = new ArrayList<Move>();

        while (!move1.equals(move2)) {
            move2 = getSuccessfulMoves(move1);
            move1 = getSuccessfulMoves(move2);
            Collections.sort(move2);
            Collections.sort(moves);
        }
    }

    private ArrayList<Move> getSuccessfulMoves(ArrayList<Move> moves) {
        for (Move move : moves) {
            if (!move.isBounced()) {
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
                    } else if (move.getMovingTo().getOrder().succeeds()) {
                        move.setSucceeded(true);
                    }
                }
            }
        }

        ArrayList<Move> stillNeedVerification = new ArrayList<Move>();

        for (Move move : moves) {
            if (!move.succeeds()) {
                stillNeedVerification.add(move);
            }
        }
        return stillNeedVerification;
    }

    public boolean moveLoopIsValid(Move move) {
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

    private void moveUnits() {
        Map map = orders.get(0).orderFrom().getMap();
        ArrayList<Country> movesFirst = new ArrayList<Country>();
        ArrayList<Country> movesSecond = new ArrayList<Country>();
        ArrayList<Country> movesThird = new ArrayList<Country>();
        ArrayList<Country> movesFourth = new ArrayList<Country>();
        ArrayList<Country> movesFifth = new ArrayList<Country>();
        ArrayList<Country> movesSixth = new ArrayList<Country>();
        ArrayList<Country> movesSeventh = new ArrayList<Country>();

        for (Order o : orders) {
            if (o.succeeds()) {
                if (o instanceof Attack) {
                    if (((Attack) o).getAttacking() instanceof ScoringCountry) {
                        movesFirst.add(o.orderFrom());
                    } else {
                        movesFifth.add(((Attack) o).getAttacking());
                        movesSixth.add(o.orderFrom());
                    }
                } else if (o instanceof Move) {
                    if (!((Move) o).getMovingTo().isOccupied()) {
                        movesSecond.add(o.orderFrom());
                    } else if ((((Move) o).getMovingTo().getOrder() instanceof Attack)) {
                        if (((Attack) ((Move) o).getMovingTo().getOrder()).getAttacking() instanceof ScoringCountry) {
                            movesSecond.add(o.orderFrom());
                        } else /*Moving to Attack on Not Scoring Country*/ {
                            movesSeventh.add(o.orderFrom());
                        }
                    } else if (((Move) o).isMoveLooped()) {
                        movesThird.add(o.orderFrom());
                    } else if (((Move) o).getMovingTo().getOrder() instanceof Move) {
                        movesFourth.add(o.orderFrom());
                    }
                }
            }
        }

        map.moveUnits(movesFirst);
        map.moveUnits(movesSecond);
        this.moveLoopedUnits(movesThird);
        this.moveMovingToMove(movesFourth);
        this.relocateUnits(movesFifth);
        map.moveUnits(movesSixth);
        map.moveUnits(movesSeventh);

        refreshTeamScores();

        for (Team team : Team.values()) {
            if (team != Team.NULL) {
                for (int i = 0; i < team.getUnitsToRemove(); i++) {
                    RemoveUnit remove = new RemoveUnit(map.getBanner(), team);
                    //map.getBanner().setLastVisible(remove);
                    while (remove.isStillInputting()) {
                    }
                }
            }
        }

        for (Team team : Team.values()) {
            if (team != Team.NULL) {
                for (int i = 0; i < team.getUnitsToAdd(); i++) {
                    AddUnit add = new AddUnit(map.getBanner(), team);
                    while (add.isStillInputting()) {
                    }
                }
                team.resetAddAndRmove();
            }
        }
    }

    private void refreshTeamScores() {
        for (Team team : Team.values()) {
            team.recalculateCountriesControlled(countries);
            team.refreshUnitTotal(countries);
        }
    }

    private void moveLoopedUnits(ArrayList<Country> loopedCountries) {
        while (loopedCountries.size() > 0) {
            Map map = loopedCountries.get(0).getMap();
            ArrayList<Country> loopedMoves = new ArrayList<Country>();
            Country original = loopedCountries.get(0);
            Country lastAdded = loopedCountries.get(0);

            while (!loopedMoves.contains(original)) {
                for (Country c : loopedCountries) {
                    if (((Move) c.getOrder()).getMovingTo() == lastAdded) {
                        loopedMoves.add(c);
                        lastAdded = c;
                        break;
                    }
                }
            }

            for (Country country : loopedMoves) {
                loopedCountries.remove(country);
            }

            map.slideMultipleTiles(loopedMoves);
        }
    }

    private void relocateUnits(ArrayList<Country> unitsToRelocate) {
        final ArrayList<Country> allUnitsToRelocate = unitsToRelocate;

        while (unitsToRelocate.size() > 0) {
            for (Country c : unitsToRelocate) {
                if (c.isOccupied()) {
                    c.getMap().relocatePrompt(c);
                    while (c.getMap().isStillRelocating()) {
                    }
                }
            }
            unitsToRelocate = wasMoveBounced(unitsToRelocate);
            for (Country c : allUnitsToRelocate) {
                if (!unitsToRelocate.contains(c)) {
                    if (c.getOrder() instanceof Move) {
                        c.getMap().slideTileTo(c, ((Move) c.getOrder()).getMovingTo());
                    }
                }
            }
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

    public ArrayList<Country> wasMoveBounced(ArrayList<Country> countriesToRelocate) {
        ArrayList<Country> moveWasBounced = new ArrayList<Country>();
        for (Country c : countriesToRelocate) {
            if (c.isOccupied()) {
                for (Country c2 : countriesToRelocate) {
                    if (c != c2) {
                        if (c.getOrder() instanceof Move && c2.getOrder() instanceof Move) {
                            if (((Move) c.getOrder()).getMovingTo() == ((Move) c2.getOrder()).getMovingTo()) {
                                moveWasBounced.add(c);
                            }
                        }
                    }
                }
            }
        }
        return moveWasBounced;

    }

    private void moveMovingToMove(ArrayList<Country> movingToAnotherMove) {
        ArrayList<Country> temp;
        do {
            temp = new ArrayList<Country>();
            for (Country c : movingToAnotherMove) {
                if (c.getOrder() instanceof Move) {
                    Move move = (Move) c.getOrder();
                    if (!move.getMovingTo().isOccupied()) {
                        temp.add(c);
                    }
                }
            }
            for (Country c : temp) {
                movingToAnotherMove.remove(c);
            }

            updateGraphics(temp);
        } while (temp.size() > 0);
    }

    public void updateGraphics(ArrayList<Country> countries) {
        if (orders.size() > 0) {
            orders.get(0).orderFrom().getMap().moveUnits(countries);
        }
    }
}