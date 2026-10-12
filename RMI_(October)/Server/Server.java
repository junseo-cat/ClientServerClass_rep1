import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.*;

public class Server extends UnicastRemoteObject implements ServerIF {

    private static DataIF data;

    private static final long serialVersionUID = 1L;

    protected Server() throws RemoteException {
        super();
    }

    public static void main (String[] args) throws NotBoundException {
        try {
            Server server = new Server();

            data = (DataIF) Naming.lookup("Data");

            Naming.rebind("Server", server);
            System.out.println("Server is ready !");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }



    @Override
    public ArrayList<Student> getAllStudentData() throws RemoteException {
        return data.getStudentList();
    }
    @Override
    public ArrayList<Course> getAllCourseData() throws RemoteException {
        return data.getCourseList();
    }
    @Override
    public boolean addStudent(String studentInfo) throws RemoteException {
        if (data.addStudentData(studentInfo)) return true;
        else return false;
    }
    @Override
    public boolean deleteStudent(String studentId) throws RemoteException {
        if (data.deleteStudentData(studentId)) return true;
        else return false;
    }
    @Override
    public boolean addCourse(String courseInfo) throws RemoteException {
        if (data.addCourseData(courseInfo)) return true;
        else return false;
    }
    @Override
    public boolean deleteCourse(String courseId) throws RemoteException {
        if (data.deleteCourseData(courseId)) return true;
        else return false;
    }


}
