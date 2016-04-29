package sr.ice.server;

import Ice.*;
import Ice.Object;
import Slice.CategoryName;
import sr.ice.impl.UserI;

/**
 * Created by P on 27.04.2016.
 */
public class ServantLocator2 implements ServantLocator {
    private long id = 0;

    public ServantLocator2() {
        System.out.println("## ServantLocator2 created ##");
    }

    @Override
    public Object locate(Current current, LocalObjectHolder cookie) throws UserException {
        System.out.println("## ServantLocator2.locate() ##");
        System.out.println("\tservantId " + id);
        return new UserI(id++, CategoryName.K2);
    }

    @Override
    public void finished(Current current, Object object, java.lang.Object o) throws UserException {
        System.out.println("## ServantLocator2 finished ##\n");
    }

    @Override
    public void deactivate(String s) {
        System.out.println("## ServantLocator2 deactivated ##");
    }
}
