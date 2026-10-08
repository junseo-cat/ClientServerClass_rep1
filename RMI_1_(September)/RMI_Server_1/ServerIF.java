import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerIF extends Remote {

    String getData() throws RemoteException;
}