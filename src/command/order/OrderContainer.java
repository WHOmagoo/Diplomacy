package command.order;

import java.util.ArrayList;
import java.util.Collections;

public class OrderContainer extends ArrayList<Order> {
    private static final long serialVersionUID = 65534L;

    public OrderContainer(ArrayList<Order> orders) {
        addAll(orders);
        Collections.sort(this);
    }

}
