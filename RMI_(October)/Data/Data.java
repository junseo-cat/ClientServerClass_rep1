import java.io.FileNotFoundException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Data extends UnicastRemoteObject implements DataIF {

    protected static StudentList studentList;
    protected static CourseList courseList;

    protected static Map<String, ArrayList<String>> registeredCourses;

    private static final long serialVersionUID = 1L;

    protected Data() throws RemoteException {
        super();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Data data;
        try {
            data = new Data();

            studentList = new StudentList("Students.txt");
            System.out.println("Data <Students> is ready");
            courseList = new CourseList("Courses.txt");
            System.out.println("Data <Courses> is ready");

            registeredCourses = new HashMap<String, ArrayList<String>>();
            System.out.println("Data <registeredCourses> is ready");

            Naming.rebind("Data", data);
            System.out.println("Data Naming is ready");

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Student> getStudentList() throws RemoteException {
        return studentList.getAllStudentRecords();
    }
    @Override
    public ArrayList<Course> getCourseList() throws RemoteException {
        return courseList.getAllCourseRecords();
    }

    @Override
    public boolean addStudentData(String studentInfo) throws RemoteException {
        if (studentList.addStudentRecords(studentInfo)) return true;
        else return false;
    }
    @Override
    public boolean deleteStudentData(String studentId) throws RemoteException {
        if (studentList.deleteStudentRecords(studentId)) return true;
        else return false;
    }
    @Override
    public boolean addCourseData(String courseInfo) throws RemoteException {
        if (courseList.addCourseRecords(courseInfo)) return true;
        else return false;
    }
    @Override
    public boolean deleteCourseData(String courseId) throws RemoteException {
        if (courseList.deleteCourseRecords(courseId)) return true;
        else return false;
    }


}
