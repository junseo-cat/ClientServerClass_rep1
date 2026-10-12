import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Client {
    private static ServerIF server;

    public static void main(String[] args) throws NotBoundException, IOException {
        try {
            server = (ServerIF) Naming.lookup("Server");
            //System.out.println("Server's answer : " + server.getData());

            BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                printMenu();

                System.out.print("Input > ");
                String command = objReader.readLine().trim();

                switch(command) {
                    case "x":
                        return;
                    case "1":
                        showList(server.getAllStudentData());
                        break;
                    case "2":
                        showList(server.getAllCourseData());
                        break;
                    case "3":
                        addStudent(objReader);
                        break;
                    case "4":
                        deleteStudent(objReader);
                        break;
                    case "5":
                        addCourse(objReader);
                        break;
                    case "6":
                        deleteCourse(objReader);
                        break;
                    default:
                        System.out.println("invalid Choice!");
                }

                //---------------------------
//                if (command.equalsIgnoreCase("X")) {
//                    System.out.println("Program Exit");
//                    break;
//                } 
//                else if (command.equals("1")) {
//                	
//                }
                //List<String> response = server.sendOrder(command);
                //printResponse(response);
            }


        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private static void addStudent(BufferedReader objReader) throws IOException, RemoteException {
        System.out.print("Student ID : "); String sId = objReader.readLine().trim();
        System.out.print("Student Name : "); String sName = objReader.readLine().trim();
        System.out.print("Student Department : "); String sDpmt = objReader.readLine().trim();
        System.out.print("Student CompletedCourses : "); String sCompCourse = objReader.readLine().trim();
        String newStudent = sId+" "+sName+" "+sDpmt+" "+sCompCourse;
        if (server.addStudent(newStudent)) System.out.println("Add SUCCESS");
        else System.out.println("Add Failed");
    }
    private static void deleteStudent(BufferedReader objReader) throws IOException, RemoteException {
        System.out.print("Student ID : "); String sId = objReader.readLine().trim();
        if (server.deleteStudent(sId)) System.out.println("Delete SUCCESS");
        else System.out.println("Delete Failed");
    }
    private static void addCourse(BufferedReader objReader) throws IOException, RemoteException {
        System.out.print("Course ID : "); String cId = objReader.readLine().trim();
        System.out.print("Professor Name : "); String pfName = objReader.readLine().trim();
        System.out.print("Course Name : "); String cName = objReader.readLine().trim();
        System.out.print("Before Courses : "); String cBefCourse = objReader.readLine().trim();
        String newCourse = cId+" "+pfName+" "+cName+" "+cBefCourse;
        if (server.addStudent(newCourse)) System.out.println("Add SUCCESS");
        else System.out.println("Add Failed");
    }
    private static void deleteCourse(BufferedReader objReader) throws IOException, RemoteException {
        System.out.print("Course ID : "); String cId = objReader.readLine().trim();
        if (server.deleteCourse(cId)) System.out.println("Delete SUCCESS");
        else System.out.println("Delete Failed");
    }



    private static void printStarLine() {
        System.out.println(
                "****************************************"
        );
    }

    private static void printMenu() {
        printStarLine();
        System.out.println("1. List Student");
        System.out.println("2. List Course");
        System.out.println("3. Add Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Add Course");
        System.out.println("6. Delete Course");

        System.out.println("x. Program Exit");
        printStarLine();
    }

    //ArrayList<?> (교수님)
    //showList(List<String> dataList) { (나의 어드밴스드과제중 리스트<스트링>으로 전부 통일 때매 
    private static void showList(ArrayList<?> dataList) {
        String list = "";
        for (int i=0; i<dataList.size(); i++) {
            list += dataList.get(i) + "\n";
        }
        System.out.println(list);
    }



}