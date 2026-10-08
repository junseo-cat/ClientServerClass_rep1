import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class Client {
    private static ServerIF server;

    public static void main(String[] args) throws NotBoundException, IOException {
        try {
            server = (ServerIF) Naming.lookup("Server");
            //System.out.println("Server's answer : " + server.getData());

            BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                printMenu(server.getMenu());

                System.out.print("Input > ");
                String command = objReader.readLine().trim();

                if (command.equalsIgnoreCase("X")) {
                    System.out.println("Program Exit");
                    break;
                }

                List<String> response = server.sendOrder(command);

                printResponse(response);
            }


        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    private static void printStarLine() {
        System.out.println(
                "****************************************"
        );
    }

    private static void printMenu(List<String> menu) {
        printStarLine();
        for (String line : menu) {
            System.out.println(line);
        }
        printStarLine();
    }

    private static void printResponse(List<String> response) {
        printStarLine();
        for (String line : response) {
            System.out.println(line);
        }
        printStarLine();
    }

}