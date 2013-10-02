import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;

public class RandomServer {
  public static void main(String[] args) {
    try {
      ORB orb = ORB.init(args, null);

      POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      poa.the_POAManager().activate();

      RandomImpl rand = new RandomImpl(10);
      org.omg.CORBA.Object obj = poa.servant_to_reference(rand);
      Random r = RandomHelper.narrow(obj);

      // get reference to root naming context
      org.omg.CORBA.Object ns =
          orb.resolve_initial_references("NameService");
      NamingContextExt nc = NamingContextExtHelper.narrow(ns);

      // bind the Object Reference in Naming
      String name = "Random10";
      NameComponent path[] = nc.to_name(name);
      nc.rebind(path, r);

      System.out.println("Waiting for clients ... ");
      orb.run();
    }
    catch (Exception e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }
}

