import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DataIF extends Remote {
    //String getData() throws RemoteException;

    ArrayList<Student> getStudentList() throws RemoteException;

    ArrayList<Course> getCourseList() throws RemoteException;

    boolean addStudentData(String studentInfo) throws RemoteException;
    boolean deleteStudentData(String studentId) throws RemoteException;
    boolean addCourseData(String courseInfo) throws RemoteException;
    boolean deleteCourseData(String courseId) throws RemoteException;
    //----------------
	/*
    List<String> getRegisteredCourses(String studentId) throws RemoteException;

    List<String> getRegisteredStudents(String courseId) throws RemoteException;

    List<String> getCompletedCourses(String studentId) throws RemoteException;

    boolean isRegisteredStudent(String studentId) throws RemoteException;

    boolean isRegisteredCourse(String courseId) throws RemoteException;

    boolean hasCompletedCourse(String studentId, String courseId) throws RemoteException;

    boolean isAlreadyRegistered(String studentId, String courseId) throws RemoteException;

    boolean registerCourse(String studentId, String courseId) throws RemoteException;
    */
}
