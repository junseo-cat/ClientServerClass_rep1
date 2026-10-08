import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    private static ServerIF server;

    public static void main(String[] args) throws NotBoundException {
        try {
            server = (ServerIF) Naming.lookup("Server");
            System.out.println("Server's answer : " + server.getData());

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}