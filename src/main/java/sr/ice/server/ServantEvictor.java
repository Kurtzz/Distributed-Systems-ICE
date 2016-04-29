package sr.ice.server;

import Ice.Current;
import Ice.LocalObjectHolder;
import Ice.Object;
import Slice.CategoryName;
import sr.ice.ObjectManager;
import sr.ice.evictor.EvictorBase;
import sr.ice.impl.*;

/**
 * Created by P on 27.04.2016.
 */
public class ServantEvictor extends EvictorBase {
    private static final int N = 3;
    private static final String PREFIX = "evict";

    private ObjectManager manager = new ObjectManager();

    public ServantEvictor() {
        super(N);
        System.out.println("## ServantEvictor created ##");
    }

    @Override
    public Object add(Current current, LocalObjectHolder cookie) {
        System.out.println("## ServantEvictor add(" + PREFIX + current.id.name + ") #");

        Object object = (Object) manager.deserialize(PREFIX + current.id.name);

        if (object == null) {
            object = new UserI(0, CategoryName.K5);
            System.out.println("\tServantEvictor create new servant");
        } else {
            System.out.println("\tServantEvictor read servant from file");
        }

        cookie.value = current;
        return object;
    }

    @Override
    public void evict(Object servant, java.lang.Object cookie) {
        Current current = (Current) cookie;
        System.out.println("## ServantEvictor evict(user" + PREFIX + current.id.name + ") #");

        manager.serialize(servant, PREFIX + current.id.name);
        System.out.println("\tServantEvictor write servant to file");
    }
}
