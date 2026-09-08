import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class getClient {

	public static void main(String[] args) {
		ServerIF server;
		String myname = "getClient";
		
		try {
			server = (ServerIF)Naming.lookup("Server1");
			
			System.out.println(server.getWd(myname));
			
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
