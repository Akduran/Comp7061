// RandomImpl.java
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class RandomImpl extends UnicastRemoteObject 
                        implements Random {
  private int _max;

  public RandomImpl(int max) throws RemoteException { 
    _max = max; 
  }

  public int get() throws RemoteException { 
    return (int) (Math.random() * _max); 
  }
}

