import javax.swing.*;
import java.awt.*;

/**
 * Created by Hugh on 5/12/2015.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        JFrame j = new JFrame();
        j.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        j.setSize(1000, 500);
        j.setLayout(null);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setSize(125, 55);
        p.setLocation(0, 0);
        p.setBackground(Color.ORANGE);
        p.setVisible(true);

        JTextArea t = new JTextArea("Wurd");
        t.setBounds(125, 55, 125, 55);
        t.setBackground(Color.BLACK);
        t.setVisible(true);

        JComponent[] objs = new JComponent[]{
                p, t
        };
        j.add(p, 0);
        j.add(t, 0);

        j.setVisible(true);
        Thread.sleep(1000);
        p.setVisible(false);
        Thread.sleep(1000);
        p.setVisible(true);


    }
}
