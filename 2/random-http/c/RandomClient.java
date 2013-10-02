// RandomClient.java
import java.rmi.Naming;

public class RandomClient {
  public static void main(String[] args) {
    String id;
    try {
      if (System.getSecurityManager() == null)
        System.setSecurityManager(new SecurityManager());
      if (args.length > 0) 
        id = "rmi://" + args[0] + "/random10";
      else 
        id = "rmi://localhost/random10";
      Random r = (Random) Naming.lookup(id);
      for (int i = 0; i < 5; i++)
        System.out.println(r.get());
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}

