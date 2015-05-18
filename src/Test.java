import java.io.*;

//TODO Use a ObjectOutputStream and ObjectInputStream to read and write files (ObjectOutputStream.writeObject(object)).
public class Test {

    public static void main(String[] args) {

        GameFrame frame = new GameFrame();
        Map map = writeMap();
        //Map map = readMap();

        frame.addComponentCentered(map);
        frame.repaint();
    }

    public static Map writeMap() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("..\\Save Files\\test.dat")));
            Map map = MapCreation.createMap();
            try {
                out.writeObject(map);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error writing");
            } finally {
                out.close();
            }
            return map;
        } catch (FileNotFoundException e) {
            System.out.println("The File could not be found");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The file is being accesed by another file");

        }

        return null;
    }

    public static Map readMap() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("..\\Save Files\\test.dat"));
            try {
                Map map = (Map) in.readObject();
                in.close();
                return map;
            } catch (ClassNotFoundException e) {
                System.out.println("Wrong class type");
                e.printStackTrace();
            } finally {
                in.close();
            }
        } catch (IOException e) {
            System.out.println("Loading bad class");
            e.printStackTrace();
        }

        return null;

    }
}
