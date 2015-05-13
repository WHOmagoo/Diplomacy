import javax.swing.*;

public class Input extends JComboBox implements CommandControl {
    @Override
    public void remove() {
        super.setVisible(false);
    }

    @Override
    public void add() {
        super.setVisible(true);
    }
}
