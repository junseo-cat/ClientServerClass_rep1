import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerIF extends Remote {
	int add(int a, int b) throws RemoteException;
	void addWd(String n) throws RemoteException;
	String getWd(String name) throws RemoteException;
}