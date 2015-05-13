import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Country extends JButton implements ActionListener, Comparable{
    private String name;
    private Border borders;
    private int occupiedBy = 0;
    private Point originalLocaion;
    private String random;

    public Country(String name, Point location){
        this.name = name;
        originalLocaion = location;
        super.setLocation(location);
        super.setSize(40, 40);
        super.setOpaque(true);
        super.setBorder(null);
        super.setContentAreaFilled(false);
        super.addActionListener(this);
    }

    public Country(String name) {
        this.name = name;
        super.setSize(40, 40);
        super.setOpaque(true);
        super.setBorder(null);
        super.setContentAreaFilled(false);
        super.addActionListener(this);
    }

    public void setOccupiedBy(int occupiedBy){
        this.occupiedBy = occupiedBy;
    }

    public String getName(){
        return name;
    }

    public void resetPosition(){
        super.setLocation(originalLocaion);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    public String toString() {
        return "Country: " + name + ", Borders: " + borders
                + "Original Location: " + originalLocaion;
    }

    @Override
    public int compareTo(Object country) {
        Country c;
        try {
            c = (Country) country;
        } catch (Exception e){
            throw new IllegalArgumentException("Must compare two countries");
        }

        return this.name.compareTo(c.getName());
    }

    public Border getBorders() {
        return borders;
    }

    public void setBorders(Country[] countries) {
        borders = new Border(this, countries);
    }
}
