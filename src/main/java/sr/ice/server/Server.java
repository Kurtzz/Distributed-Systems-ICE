package sr.ice.server;

import Ice.*;
import Ice.Exception;
import Slice.CategoryName;
import sr.ice.ObjectManager;
import sr.ice.impl.*;

public class Server {
    public void beServer(String[] args) {
        int status = 0;

        ObjectManager manager = new ObjectManager();

        Communicator communicator = null;

        try {
            communicator = Util.initialize(args);

            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("MyAdapter",
                    "tcp -h localhost -p 1000:udp -h localhost -p 1000");

            UserI user1 = new UserI(1, CategoryName.K1);
            UserI user2 = new UserI(2, CategoryName.K2);
            UserI user3 = new UserI(3, CategoryName.K3);
            UserI user4 = new UserI(4, CategoryName.K4);
            UserI user5 = new UserI(5, CategoryName.K5);

            manager.serialize(user1, "user1");
            manager.serialize(user2, "user2");
            manager.serialize(user3, "user3");
            manager.serialize(user4, "user4");
            manager.serialize(user5, "user5");

            ServantLocator locator1 = new ServantLocator1(adapter);
            ServantLocator locator2 = new ServantLocator2();
            ServantLocator locator3 = new ServantLocator3();
            System.out.println("## ServantLocator4 created ##");
            ServantLocator evictor = new ServantEvictor();

            adapter.addServantLocator(locator1, "K1");
            adapter.addServantLocator(locator2, "K2");
            adapter.addServantLocator(locator3, "K3");
            adapter.addDefaultServant(user4, "K4");
            adapter.addServantLocator(evictor, "K5");

            adapter.activate();
            System.out.println("Entering event processing loop...");
            communicator.waitForShutdown();
        } catch (Exception e) {
            System.err.println(e);
            status = 1;
        }

        if (communicator != null) {
            try {
                communicator.destroy();
            } catch (Exception e) {
                System.err.println(e);
                status = 1;
            }
        }
        System.exit(status);
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.beServer(args);
    }
}
