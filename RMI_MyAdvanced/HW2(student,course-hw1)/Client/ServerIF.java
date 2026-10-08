import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerIF extends Remote {
    List<String> getMenu() throws RemoteException;
    List<String> sendOrder(String command) throws RemoteException;

    //String getData() throws RemoteException;
}