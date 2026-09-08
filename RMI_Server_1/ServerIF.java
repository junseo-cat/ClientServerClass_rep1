import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerIF extends Remote {
	void addWd(String word) throws RemoteException;
	String getWd(String clientName) throws RemoteException;
}