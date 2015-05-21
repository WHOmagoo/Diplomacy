package command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public enum OrderType {
    ATTACK(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("I did it");
        }
    }),
    HOLD,
    MOVE,
    SUPPORT;

    private ActionListener al;

    OrderType(ActionListener actionListener) {

    }

    OrderType() {

    }
}