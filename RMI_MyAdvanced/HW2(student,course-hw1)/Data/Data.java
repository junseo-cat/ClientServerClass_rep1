import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data extends UnicastRemoteObject implements DataIF {

    protected static StudentList studentList;
    protected static CourseList courseList;

    protected static Map<String, ArrayList<String>> registeredCourses;

    private static final long serialVersionUID = 1L;
    //private static final long seriolVersionUID = 1L; //지난번 영상에는 없었는데 2번째 시간 영상부터 생김

    protected Data() throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getStudentList() throws RemoteException {
        List<String> result = new ArrayList<String>();
        for (Student student : studentList.getAllStudentRecords()) {
            result.add(student.toString());
        }
        return result;
    }

    @Override
    public List<String> getCourseList() throws RemoteException {
        List<String> result = new ArrayList<String>();
        for (Course course : courseList.getAllCourseRecords()) {
            result.add(course.toString());
        }
        return result;
    }

    @Override
    public List<String> getRegisteredCourses(String studentId) throws RemoteException {
        List<String> result = new ArrayList<String>();

        ArrayList<String> courseIds =
                registeredCourses.get(studentId);

        if (courseIds == null) {
            return result;
        }

        for (String courseId : courseIds) {

            for (Course course :
                    courseList.getAllCourseRecords()) {

                if (course.match(courseId)) {
                    result.add(course.toString());
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public List<String> getRegisteredStudents(String courseId)
            throws RemoteException {

        List<String> result = new ArrayList<String>();

        // 학생별 현재 신청 과목들을 전부 확인
        for (Map.Entry<String, ArrayList<String>> entry : registeredCourses.entrySet()) {
            String studentId = entry.getKey();
            ArrayList<String> courseIds = entry.getValue();

            // 해당 학생이 이 과목을 신청했는지 확인
            if (courseIds.contains(courseId)) {
                // studentId에 해당하는 Student 찾아 리턴
                for (Student student : studentList.getAllStudentRecords()) {
                    if (student.match(studentId)) {
                        result.add(student.toString());
                        break;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public List<String> getCompletedCourses(String studentId)
            throws RemoteException {

        List<String> result = new ArrayList<String>();

        // 해당 학생 검색
        for (Student student : studentList.getAllStudentRecords()) {
            if (student.match(studentId)) {
                ArrayList<String> completedCourseIds = student.getCompletedCourses();

                // 완료한 Course ID 각각을 실제 Course 정보로 변환
                for (String courseId : completedCourseIds) {
                    for (Course course : courseList.getAllCourseRecords()) {
                        if (course.match(courseId)) {
                            result.add(course.toString());
                            break;
                        }
                    }
                }
                break;
            }
        }
        return result;
    }

    @Override
    public boolean isRegisteredStudent(String studentId) throws RemoteException {
        return studentList.isRegisteredStudent(studentId);
    }

    @Override
    public boolean isRegisteredCourse(String courseId) throws RemoteException {
        return courseList.isRegisteredCourse(courseId);
    }

    @Override
    public boolean hasCompletedCourse(String studentId, String courseId) throws RemoteException {
        for (Student student : studentList.getAllStudentRecords()) {
            if (student.match(studentId)) {
                return student.getCompletedCourses().contains(courseId);
            }
        }
        return false;
    }

    @Override
    public boolean isAlreadyRegistered(String studentId, String courseId) throws RemoteException {
        ArrayList<String> courseIds = registeredCourses.get(studentId);

        if (courseIds == null) {
            return false;
        }

        return courseIds.contains(courseId);
    }

    @Override
    public boolean registerCourse(String studentId, String courseId) throws RemoteException {
        ArrayList<String> courseIds = registeredCourses.get(studentId);

        // 이 학생이 아직 없다면
        if (courseIds == null) {
            courseIds = new ArrayList<String>();
            registeredCourses.put(studentId, courseIds);
        }

        //기존에 추가 또는 (위에서 새로만들어지면서) 추가
        courseIds.add(courseId);
        return true;
    }

//	@Override
//	public String getData() throws RemoteException {
////		Student student1 = studentList.getAllStudentRecords().get(0);
////		return student1.toString();
//
//		Course course1 = courseList.getAllCourseRecords().get(0);
//		return course1.toString();
//	}



}
