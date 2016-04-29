package sr.ice;

import java.io.*;
import Ice.Object;

/**
 * Created by P on 27.04.2016.
 */
public class ObjectManager {
    private static final String repoLocation = "src/resources/";

    private File resource;

    public ObjectManager() {
        this.resource = new File(repoLocation);
    }

    public void serialize(Object object, String name) {
        try {
            FileOutputStream fileOut = new FileOutputStream(getFileName(name));
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(object);
            objectOut.close();
            fileOut.close();

            System.out.println("Object " + name + " serialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public java.lang.Object deserialize(String name) {
        Object object = null;

        try {
            FileInputStream fileIn = new FileInputStream(getFileName(name));
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            object = (Object) objectIn.readObject();
            objectIn.close();
            fileIn.close();
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return object;
    }

    private File getFileName(String key) {
        return new File(resource, key);
    }
}
