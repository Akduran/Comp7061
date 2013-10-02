import org.omg.CORBA.*;
import org.omg.CosNaming.*;  // for Naming Service

public class RandomClient {
  public static void main(String[] args) {
    try {
      ORB orb = ORB.init(args, null);

      // get reference to root naming context
      org.omg.CORBA.Object ns =
        orb.resolve_initial_references("NameService");
      NamingContextExt nc = NamingContextExtHelper.narrow(ns);

      // lookup name
      String name = "Random10";
      org.omg.CORBA.Object obj = nc.resolve_str(name);
      Random rand = RandomHelper.narrow(obj);

      for (int i = 0; i < 5; i++)
        System.out.println(rand.get());
    }
    catch (Exception e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }
}

