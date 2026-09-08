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
	
	private List<String> wd = new ArrayList<>();
	
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
	
	public int add(int a, int b) {
		int c = a+b;
		System.out.println("Server send : "+c);
		return c;
	}
	
	
	public void addWd(String n) {
		wd.add(n);
		System.out.println("added the list : "+n);
	}
	public String getWd(String name) {
		String a;
		a = wd.toString();
		System.out.println("["+name+"] read the list");
		return a;
	}
}
