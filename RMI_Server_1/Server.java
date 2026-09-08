import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.*;

public class Server extends UnicastRemoteObject implements ServerIF {
	
	protected Server() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private List<String> wordList = new ArrayList<>();
	
	public static void main (String[] args) {
		try {
			Server server = new Server();
			Naming.bind("Server1", server);
			System.out.println("Server1 is ready !");
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void addWd(String word) {
		wordList.add(word);
		System.out.println("Added word : " + word);
	}
	public String getWd(String clientName) {
		System.out.println("["+clientName+"] read the list");
		return wordList.toString();
	}
}
