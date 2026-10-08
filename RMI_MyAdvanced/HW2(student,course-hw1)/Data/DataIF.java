import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DataIF extends Remote {
    //String getData() throws RemoteException;

    List<String> getStudentList() throws RemoteException;

    List<String> getCourseList() throws RemoteException;

    List<String> getRegisteredCourses(String studentId) throws RemoteException;

    List<String> getRegisteredStudents(String courseId) throws RemoteException;

    List<String> getCompletedCourses(String studentId) throws RemoteException;

    boolean isRegisteredStudent(String studentId) throws RemoteException;

    boolean isRegisteredCourse(String courseId) throws RemoteException;

    boolean hasCompletedCourse(String studentId, String courseId) throws RemoteException;

    boolean isAlreadyRegistered(String studentId, String courseId) throws RemoteException;

    boolean registerCourse(String studentId, String courseId) throws RemoteException;
}
