package sr.ice.server;

import Ice.*;
import Ice.Object;
import Slice.CategoryName;
import sr.ice.ObjectManager;
import sr.ice.impl.UserI;

/**
 * Created by P on 27.04.2016.
 */
public class ServantLocator1 implements ServantLocator {
    private int id = 0;
    private final ObjectAdapter adapter;
    private final ObjectManager manager = new ObjectManager();

    public ServantLocator1(ObjectAdapter adapter) {
        System.out.println("## ServantLocator1 created ##");
        this.adapter = adapter;
    }

    @Override
    public Object locate(Current current, LocalObjectHolder localObjectHolder) throws UserException {
        System.out.println("## ServantLocator1.locate() ##");

        Object servant = adapter.find(current.id);
        System.out.println(current.id.name);

        if (servant == null) {
            System.out.println("\tServantLocator1 read servant from file.");
            servant = (Object) manager.deserialize(current.id.name);

            current.adapter.add(servant, current.id);
        }
        System.out.println("\tservantId " + id);

        return servant;
    }

    @Override
    public void finished(Current current, Object object, java.lang.Object o) throws UserException {
        System.out.println("## ServantLocator1 finished ##\n");
    }

    @Override
    public void deactivate(String s) {
        System.out.println("## ServantLocator1 deactivated ##");
    }
}
