import javax.swing.*;

public class Input extends JComponent implements CommandControl {

    public Input() {
    }

    @Override
    public void remove() {
        super.setVisible(false);
    }

    @Override
    public void add() {
        super.setVisible(true);
    }
}
