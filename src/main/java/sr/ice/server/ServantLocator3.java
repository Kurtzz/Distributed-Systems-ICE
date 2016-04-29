package sr.ice.server;

import Slice.CategoryName;
import Ice.*;
import Ice.Object;
import sr.ice.impl.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by P on 27.04.2016.
 */
public class ServantLocator3 implements ServantLocator {
    private static final int N = 5;
    private List<UserI> servants;
    private int currentServant = 0;

    public ServantLocator3() {
        System.out.println("## ServantLocator3 created ##");
        servants = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            servants.add(new UserI(i, CategoryName.K3));
        }
    }

    public Object locate(Current current, LocalObjectHolder cookie) throws UserException {
        System.out.println("## ServantLocator3.locate() ##");

        UserI servant = servants.get(currentServant);
        System.out.println("\tservantId " + currentServant);
        currentServant = (currentServant + 1) % servants.size();

        return servant;
    }

    @Override
    public void finished(Current current, Object object, java.lang.Object o) throws UserException {
        System.out.println("## ServantLocator3 finished ##\n");
    }

    @Override
    public void deactivate(String s) {
        System.out.println("## ServantLocator1 deactivated ##");
    }
}
