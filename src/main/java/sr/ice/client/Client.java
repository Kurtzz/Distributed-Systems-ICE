package sr.ice.client;

import Ice.LocalException;
import Slice.UserPrx;
import Slice.UserPrxHelper;

/**
 * Created by P on 27.04.2016.
 */
public class Client {
    public static void main(String[] args)
    {
        int status = 0;
        Ice.Communicator communicator = null;

        try {
            communicator = Ice.Util.initialize(args);

            String line, category, user, proxy;
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

            System.out.println("Choose category..");
            System.out.print("==> ");
            System.out.flush();
            category = in.readLine();

            System.out.println("Choose user..");
            System.out.print("==> ");
            System.out.flush();
            user = in.readLine();

            proxy = "K" + category + "/user" + user;
            Ice.ObjectPrx base = communicator.stringToProxy(proxy + ":tcp -h localhost -p 1000:udp -h localhost -p 1000");

            UserPrx userPrx = UserPrxHelper.checkedCast(base);
            if (userPrx == null) {
                throw new Error("Invalid proxy");
            }

            do {
                System.out.print("\t==> ");
                System.out.flush();
                line = in.readLine();

                if (line.equals("getId")) {
                    System.out.println("\t\tRESULT UserId = " + userPrx.getId());
                }
                else if (line.equals("getCategory")) {
                    System.out.println("\t\tRESULT UserCategory = " + userPrx.getCategory());
                }
                else if (line.equals("creationalTime")) {
                    System.out.println("\t\tRESULT User created at " + userPrx.getCreationTime());
                }
            } while (!line.equals("exit"));

        } catch (LocalException e) {
            e.printStackTrace();
            status = 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            status = 1;
        }
        if (communicator != null) {
            try {
                communicator.destroy();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                status = 1;
            }
        }
        System.exit(status);
    }
}
