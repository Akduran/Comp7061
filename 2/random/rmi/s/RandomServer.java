// RandomServer.java
import java.rmi.Naming;

public class RandomServer {
  public static void main(String[] args) {
    try {
      RandomImpl r = new RandomImpl(10);
      String id;
      if (args.length > 0) 
        id = "rmi://" + args[0] + "/random10";
      else
        id = "rmi://localhost/random10";
      Naming.rebind(id, r);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}

