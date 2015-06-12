import constants.GameFrame;
import java.io.IOException;
import map.Map;

public class Main {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        GameFrame frame = new GameFrame();
        Map map = MapCreation.createMap();
        frame.addComponentCentered(map);
        frame.setVisible(true);
    }

    /*public static Map writeMap(Map map) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("Save files\\test.dat")));
            try {
                out.writeObject(map);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error writing");
            }
            out.close();
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
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Save files\\test.dat"));
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

    }*/
}
