import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DataIF extends Remote {
    String getData() throws RemoteException;
}
