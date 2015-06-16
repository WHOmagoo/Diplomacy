/**
 * OrderType.java
 * Assignment: Final Project
 * Purpose: This was a culminating project that should
 * show our knowledge of writing java code.
 *
 * @version 06/13/15
 * @author Hugh McGough
 */

package command;

import command.input.*;
import command.order.*;
import map.Country;

public enum OrderType {
    ATTACK,
    DEFEND,
    HOLD,
    MOVE,
    SUPPORT;

    public static Order getOrder(Object selectedItem, Country orderFrom) throws NullPointerException, ClassCastException {
        switch ((OrderType) selectedItem) {
            case ATTACK:
                return new Attack(orderFrom);
            case DEFEND:
                return new Defend(orderFrom);
            case HOLD: return null;
            case MOVE:
                return new Move(orderFrom);
            case SUPPORT:
                return new Support(orderFrom);
        }

        throw new NullPointerException("The time could not be found");
    }

    public static Input getInput(Object selectedItem, InputBanner banner) throws NullPointerException, ClassCastException{
        switch ((OrderType) selectedItem) {
            case ATTACK: return new AttackInput(banner);
            case DEFEND: return new DefenseInput(banner);
            case HOLD: return null;
            case MOVE: return new MoveInput(banner);
            case SUPPORT: return new SupportInput(banner);
        }

        throw new NullPointerException("Error");
    }
}