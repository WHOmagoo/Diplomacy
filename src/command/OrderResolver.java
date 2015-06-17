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

public class OrderResolver {
    private ArrayList<Order> orders = new ArrayList<Order>();
    private ArrayList<Country> countries = new ArrayList<Country>();

    /**
     * The constructor for this class, sets the countries that should be evaluated for orders
     *
     * @param countries the countries to be evaluated
     */
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

    /**
     * Calls all of the methods to resolve the orders.
     */
    public void resolve() {
        if (orders.size() > 0) {
            ArrayList<Order> invalidOrders1 = cancelSomeOrders(getUnCanceledOrders(orders));
            ArrayList<Order> invalidOrders2 = cancelSomeOrders(getUnCanceledOrders(invalidOrders1));
            Collections.sort(invalidOrders1);
            Collections.sort(invalidOrders2);

            /**
             * This will go in a loop until getUncanceledOrders does not return any new orders.
             */
            while (!invalidOrders1.equals(invalidOrders2)) {
                invalidOrders1 = cancelSomeOrders(getUnCanceledOrders(invalidOrders2));
                invalidOrders2 = cancelSomeOrders(getUnCanceledOrders(invalidOrders1));
                Collections.sort(invalidOrders1);
                Collections.sort(invalidOrders2);
            }

            catchAttackLoop();
            catchMoveLoop();

            for (Order order : orders) {
                assert order.isValid() == true : order + " was not validated";
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
        } else {
            System.out.println("|======================|");
            System.out.println("|No orders were entered|");
            System.out.println("|======================|");
        }
    }

    /**
     * Resets all of the countries orders for the next turn.
     */
    private void resetCountries() {
        for (Country c : countries) {
            c.resetOrder();
        }
    }

    /**
     * Gets all of the orders which have not been attacked or are attacked by orders that have
     * already been verified as canceled.
     *
     * @param orders the list of orders that are not yet been considered valid
     * @return
     */
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

    /**
     * Will look through all of the orders to see if any of the other orders cancel it, an order is
     * canceled by being attacked by an attack with its validity of true or unknown (null).
     *
     * @param order the order that is in question
     * @return true if it is canceled by an attack false otherwise
     */
    private boolean isCanceled(Order order) {
        for (Order otherOrder : orders) {
            if (otherOrder instanceof Attack) {
                Attack attack = (Attack) otherOrder;
                if (attack.getAttacking() == order.orderFrom() && attack.isValid() != Boolean.FALSE) {
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * Cancels the orders that have been attacked by uncanceled orders.
     *
     * @param executableOrders An ArrayList of orders that are not canceled
     * @return returns the list of orders that have still not yet been validated
     */
    private ArrayList<Order> cancelSomeOrders(ArrayList<Order> executableOrders) {
        ArrayList<Order> invalidOrders = new ArrayList<>();
        for (Order order : executableOrders) {
            if (order instanceof Attack) {
                assert order.isValid() == Boolean.TRUE : "Tried to use " + order + " to cancel an attack but it was not validated";
                if (Boolean.TRUE == order.isValid()) {
                    if (((Attack) order).getAttacking().getOrder() != null) {
                        ((Attack) order).getAttacking().getOrder().setInvalid();
                    }
                }
            }
        }

        for(Order order : orders){
            if (order.isValid() == null) {
                invalidOrders.add(order);
            }
        }
        return invalidOrders;
    }

    /**
     * Will cancel attacks if 2 or more countries attack each other. This scenario is not caught by
     * the getUncanceled orders because all the countries in the loop are considered as canceled
     * because it has been attacked by an attack of unknown validity. This sets them as invalid and
     * also marks that they attacked in a circle.
     */
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

    /**
     * This is the same as the catchAttackLoop scenario except that it sets the move orders as valid
     * because they don't cancel each others moves like the attacks do.
     */
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

    /**
     * Calculates the defense powers by adding to the defense power 1 point for each valid defend
     * move. Will also set the defend moves as succeeded if the defense power was raised.
     */
    private void calculateDefensePowers() {
        for (Order order : orders) {
            if (order instanceof Defend) {
                if (((Defend) order).increaseDefense()) {
                    order.setSucceeded(true);
                }
            }
        }
    }

    /**
     * This takes the support orders and attempts to add to the attack power 1 point per support
     * helping the attack, will also set the order as succeeded if the support successfully
     * increased the attack power of the country it was supporting, after that it takes all of the
     * attack orders and see if the attack power is greater than the defense power of the country it
     * is attacking.
     */
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

    /**
     * Will go through all of the Attack orders and see if there are multiple countries that have
     * attacked the same country, if they both attack with the same power then they are both bounced
     * (Order is canceled) otherwise the country with the lesser attack power is canceled.
     */
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

    /**
     * This identifies all the move orders that are moving to where another unit is moving to, a
     * move can be bounced by another successful move order to the same Country or another move to
     * the same country.
     */
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

    /**
     * This fails all of the moves that bounced by setting them as succeeded to false
     */
    private void failBounced() {
        for (Order order : orders) {
            if (order.isBounced()) {
                order.setSucceeded(false);
            }
        }
    }

    /**
     * This goes through all of the move commands and will only set the move orders that are not
     * bounced and that are moving to an unoccupied area to successful, or a move moving to another
     * successful move.
     */
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

        /**
         * This loop will iterate through all of the moves and reevaluate the move orders and will
         * set some as successful if the conditions are met, once no more moves can be marked as
         * successful, the two ArrayLists are equal and it exits.
         */
        while (!move1.equals(move2)) {
            move2 = getSuccessfulMoves(move1);
            move1 = getSuccessfulMoves(move2);
            Collections.sort(move2);
            Collections.sort(moves);
        }
    }

    /**
     * This will go through the input and will mark the move orders that are moving to empty
     * countries or moving to successful moving countries, and return the move orders that have not
     * yet been set as successful.
     * @param moves the move orders that have not yet been set to successful
     * @return the move orders that have not yet been evaluated.
     */
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

    /**
     * This will check to make sure that all of the move orders that were moveLooped are still valid
     * and that one's order was not canceled.
     * @param move the move order that is to be evaluated
     * @return a boolean true if it is still valid otherwise false
     */
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

    /**
     * This will move the units on the map to their new locations.
     */
    private void moveUnits() {
        Map map = orders.get(0).orderFrom().getMap();

        //The first to move are orders that attack an empty country, or a scoringCountry
        ArrayList<Country> movesFirst = new ArrayList<Country>();

        /*The second to move is orders that move to an empty country, or a country that will be
            moved above*/
        ArrayList<Country> movesSecond = new ArrayList<Country>();

        //The third is to move orders that are moveLooped
        ArrayList<Country> movesThird = new ArrayList<Country>();

        //The fourth is to move orders that are moving to countries that have move orders
        ArrayList<Country> movesFourth = new ArrayList<Country>();

        /*The fifth is to relocate countries that have been successfully attacked but occupy a non
            point scoring country*/
        ArrayList<Country> movesFifth = new ArrayList<Country>();

        /*The sixth is to move countries that have successfully attacked a non ScoringCountry
        occupied country.*/
        ArrayList<Country> movesSixth = new ArrayList<Country>();

        /*The seventh is to move countries that have move orders to countries that have successfully
            attacked a non ScoringCountry.*/
        ArrayList<Country> movesSeventh = new ArrayList<Country>();

        //Iterates through all of the orders and sorts them into the proper ArrayList
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

        //Moves the units through their respective methods
        map.moveUnits(movesFirst);
        map.moveUnits(movesSecond);
        this.moveLoopedUnits(movesThird);
        this.moveMovingToMove(movesFourth);
        this.relocateUnits(movesFifth);
        map.moveUnits(movesSixth);
        map.moveUnits(movesSeventh);

        refreshTeamScores();

        //Prompts users to remove units if applicable
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

        //Prompts users to select where to add their new units if applicable
        for (Team team : Team.values()) {
            if (team != Team.NULL) {
                for (int i = 0; i < team.getUnitsToAdd(); i++) {
                    AddUnit add = new AddUnit(map.getBanner(), team);
                    while (add.isStillInputting()) {
                    }
                }
                team.resetAddAndRemove();
            }
        }
    }

    /**
     * Finds out if a team needs to add or remove units after the units have been moved.
     */
    private void refreshTeamScores() {
        for (Team team : Team.values()) {
            team.recalculateCountriesControlled(countries);
            team.refreshUnitTotal(countries);
        }
    }

    /**
     * This moves the units that were marked as moveLooped, it uses a special method from the map
     * class to move them.
     * @param loopedCountries the countries that are moveLooped
     */
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

    /**
     * This is where the stuff happens to prompt the user to relocate their unit when it was
     * attacked
     * @param unitsToRelocate the units that have been attacked and need relocation
     */
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


    /**
     * This method is to print the commands and is not intended for use in the final product
     */
    private void printCommands() {
        System.out.println("========================");
        System.out.println("========================");
        System.out.println("Printing Orders");
        System.out.println("---------------");
        orders.get(0).orderFrom().getMap().printOrders();
        System.out.println("========================");
        System.out.println("========================");
    }

    /**
     * Will take in an ArrayList of countries that have been relocated and then it will evaluate if
     * two countries moves were bounced, it returns the countries that still need to be relocated
     * @param countriesToRelocate The countries that have entered relocation orders
     * @return an ArrayList of countries that still need to be relocated because of an order bounce
     */
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

    /**
     * This is the method that moves the units that are moving to countries that are occupied by
     * successful move orders. It starts with the order that is moving to an unoccupied area and
     * keeps going until all units have been moved
     * @param movingToAnotherMove an ArrayList of countries that still need to be moved
     */
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
            if (temp.size() > 0) {
                temp.get(0).getMap().moveUnits(temp);
            }
        } while (temp.size() > 0);
    }
}