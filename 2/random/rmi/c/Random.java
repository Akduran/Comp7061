// Random.java
import java.rmi.*;

public interface Random extends Remote { 
  public int get() throws RemoteException;  
}

