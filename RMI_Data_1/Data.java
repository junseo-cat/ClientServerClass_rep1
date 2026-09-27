import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Data extends UnicastRemoteObject implements DataIF {

    private static final long serialVersionUID = 1L;
    //private static final long seriolVersionUID = 1L; //지난번 영상에는 없었는데 2번째 시간 영상부터 생김

    protected Data() throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        Data data;
        try {
            data = new Data();
            Naming.rebind("Data", data);
            System.out.println("Data is ready");
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public String getData() throws RemoteException {
        return "testString(Data) is here!";
    }

}
