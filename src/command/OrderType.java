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

    public Order getOrder() throws NullPointerException{
        return orderType;
    }

    public Input[] getInputs(){
        return inputs;
    }
}