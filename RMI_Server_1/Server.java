import java.net.MalformedURLException;

import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.*;

public class Server extends UnicastRemoteObject implements ServerIF {

    private static DataIF data; //client에서 서버를 받은거처럼 서버에서 데이터(베이스)를 받기
    private static final long serialVersionUID = 1L;
    //private static final long seriolVersionUID = 1L; //지난번 영상에는 없었는데 2번째 시간 영상부터 생김

    protected Server() throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
    }

    private List<String> wordList = new ArrayList<>();

    public static void main (String[] args) throws NotBoundException {
        try {
            Server server = new Server();
            Naming.rebind("Server", server);
            System.out.println("Server is ready !");

            data = (DataIF) Naming.lookup("Data");

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemoteException e) {
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

    //hw2
    public String getData() throws RemoteException {
        return data.getData();
    }
}
