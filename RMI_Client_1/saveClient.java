import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import java.util.Scanner;

public class saveClient {

	public static void main(String[] args) {
		ServerIF server;
		String myname = "saveClient";
		Scanner sc = new Scanner(System.in);
		String stopWd = "0";
		
		try {
			server = (ServerIF)Naming.lookup("Server1");
			
			while (true) {
				String wd = sc.next();
				if (wd.equals(stopWd)) break;
				if (wd != null) {
					server.addWd(wd);
					System.out.println("current List "+server.getWd(myname));
				} 
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
