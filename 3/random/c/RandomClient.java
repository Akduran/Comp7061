// RandomClient.java
import org.omg.CORBA.*;

public class RandomClient {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Need to specify an IOR");
      System.exit(0);
    }

    try {
      // initialize ORB
      ORB orb = ORB.init(args, null);

      // assume 1st commandline argument is IOR of remote object
      // note fully-qualified name for CORBA Object class 
      org.omg.CORBA.Object obj = orb.string_to_object(args[0]);

      // cast reference to "Random" reference
      Random rand = RandomHelper.narrow(obj);

      for (int i = 0; i < 5; i++)
        System.out.println(rand.get());

      // optional (?)
      orb.shutdown(true);
      orb.destroy();
    }
    catch (Exception e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }
}

