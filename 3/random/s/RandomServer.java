// RandomServer.java
import org.omg.CORBA.*;
import org.omg.PortableServer.*;  // for POA

public class RandomServer {
  public static void main(String[] args) {
    try {
      // create & initialize ORB
      ORB orb = ORB.init(args, null);

      // get reference to root POA & activate the POAManager
      POA poa = 
        POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      poa.the_POAManager().activate();

      // create a servant
      RandomImpl rand = new RandomImpl(10);

      // get object reference from the servant
      org.omg.CORBA.Object obj = 
        poa.servant_to_reference(rand);

      // print stringified IOR
      System.out.println(orb.object_to_string(obj));

      System.out.println("Waiting for clients ... ");
      
      // wait for requests 
      orb.run();      
    }
    catch (Exception e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }
}

