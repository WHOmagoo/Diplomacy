/**
 * Created by Hugh on 5/12/2015.
 */

//TODO Use a ObjectOutputStream and ObjectInputStream to read and write files (ObjectOutputStream.writeObject(object)).
public class Test {

    public static void main(String[] args) {
        Map map = CountryCreation.createMap();
        System.out.println(map);
        /*JFrame j = new JFrame();
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

        JComboBox<Country> combo = new JComboBox<Country>();
        combo.addItem(new Country("Strouser"));
        combo.addItem(new Country("Bober rober"));
        combo.addItem(new Country("Ronald REgan"));
        combo.setLocation(250,250);
        combo.setSize(225, 33);

        JComponent[] objs = new JComponent[]{
                p, t
        };
        j.add(p, -1);
        j.add(t, -1);
        j.add(combo, 0);

        //j.setVisible(true);
        Thread.sleep(1000);
        p.setVisible(false);
        Thread.sleep(1000);
        p.setVisible(true);*/


    }
}
