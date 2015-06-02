package command;

import command.input.*;
import command.order.*;

public enum OrderType {
    ATTACK (new Attack(), new Input[]{new AttackInput()}),
    DEFEND (new Defend(), new Input[]{new DefenseInput()}),
    HOLD (new Hold(), null),
    MOVE (new Move(), new Input[]{new MoveInput()}),
    SUPPORT (new Support(), new Input[]{new SupportInput(), new SupportAttackInput()});

    private Order orderType;
    private Input[] inputs;

    OrderType(Order order, Input[] inputs){
        orderType = order;
        this.inputs = inputs;
    }

    public static Order getOrder(Object selectedItem) throws NullPointerException, ClassCastException{
        switch ((OrderType) selectedItem) {
            case ATTACK: return new Attack();
            case DEFEND: return new Defend();
            case HOLD: return null;
            case MOVE: return new Move();
            case SUPPORT: return new Support();
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