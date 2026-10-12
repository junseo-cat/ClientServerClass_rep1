import java.net.MalformedURLException;

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
    }

    public static void main (String[] args) throws NotBoundException {
        try {
            Server server = new Server();

            data = (DataIF) Naming.lookup("Data");

            Naming.rebind("Server", server);
            System.out.println("Server is ready !");

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


//	public String getData() throws RemoteException {
//		return data.getData();
//	}

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

    //-----------------------

    @Override
    public List<String> getMenu() throws RemoteException {
        List<String> menu = new ArrayList<String>();

        menu.add("[ Order Numbers ]");
        menu.add("1. Student List");
        menu.add("   입력 : 1");

        menu.add("2. Course List");
        menu.add("   입력 : 2");

        menu.add("3. 학생 별 수강 신청 과목 리스트");
        menu.add("   입력 : 3 [Student ID]");

        menu.add("4. 수강과목 별 학생 리스트");
        menu.add("   입력 : 4 [Course ID]");

        menu.add("5. 학생 별 수강 완료 과목 리스트");
        menu.add("   입력 : 5 [Student ID]");

        menu.add("6. 수강 신청");
        menu.add("   입력 : 6 [Student ID] [Course ID]");

        menu.add("X. Exit");

        return menu;
    }



	/*
	@Override
	public List<String> sendOrder(String command) throws RemoteException {
		List<String> response = new ArrayList<String>();

	    if (command == null || command.trim().equals("")) {
	        response.add("명령을 입력해주세요.");
	        return response;
	    }

	    StringTokenizer stringTokenizer = new StringTokenizer(command);
	    String order = stringTokenizer.nextToken();

	    switch (order) {

		    case "1":
		        // 1번은 추가 입력이 없어야 함
		        if (stringTokenizer.hasMoreTokens()) {
		            response.add("입력 형식 : 1");
		            return response;
		        }

		        return data.getStudentList();


		    case "2":
		        if (stringTokenizer.hasMoreTokens()) {
		            response.add("입력 형식 : 2");
		            return response;
		        }

		        return data.getCourseList();



		    //학생별 현재 수강과목 :studentId
		    case "3":
		        // Student ID가 없으면 오류
		        if (!stringTokenizer.hasMoreTokens()) {
		            response.add(
		                    "입력 형식 : 3 [Student ID]");
		            return response;
		        }

		        String studentId =
		                stringTokenizer.nextToken();

		        // Student ID 뒤에 또 값이 있으면 잘못된 입력
		        if (stringTokenizer.hasMoreTokens()) {
		            response.add(
		                    "입력 형식 : 3 [Student ID]");
		            return response;
		        }

		        if (!data.isRegisteredStudent(studentId)) {
		            response.add(
		                    "존재하지 않는 Student ID입니다.");
		            return response;
		        }

		        List<String> courses =
		                data.getRegisteredCourses(studentId);

		        if (courses.isEmpty()) {
		            response.add(
		                    "현재 신청한 수강과목이 없습니다.");
		            return response;
		        }
		        return courses;




		    //과목별 수강 학생 :courseId
		    case "4":
		        if (!stringTokenizer.hasMoreTokens()) {
		            response.add(
		                    "입력 형식 : 4 [Course ID]");
		            return response;
		        }

		        String courseId = stringTokenizer.nextToken();

		        if (stringTokenizer.hasMoreTokens()) {
		            response.add(
		                    "입력 형식 : 4 [Course ID]");
		            return response;
		        }

		        if (!data.isRegisteredCourse(courseId)) {
		            response.add(
		                    "존재하지 않는 Course ID입니다.");
		            return response;
		        }

		        List<String> students =
		                data.getRegisteredStudents(courseId);

		        if (students.isEmpty()) {
		            response.add(
		                    "현재 이 과목을 신청한 학생이 없습니다.");
		            return response;
		        }

		        return students;



		    //학생별 완료 과목 :studentId
		    case "5":
		        if (!stringTokenizer.hasMoreTokens()) {
		            response.add(
		                    "입력 형식 : 5 [Student ID]");
		            return response;
		        }

		        String completedStudentId = stringTokenizer.nextToken();

		        if (stringTokenizer.hasMoreTokens()) {
		            response.add(
		                    "입력 형식 : 5 [Student ID]");
		            return response;
		        }

		        if (!data.isRegisteredStudent(completedStudentId)) {
		            response.add(
		                    "존재하지 않는 Student ID입니다.");
		            return response;
		        }

		        List<String> completedCourses = data.getCompletedCourses(completedStudentId);

		        if (completedCourses.isEmpty()) {
		            response.add(
		                    "수강 완료한 과목이 없습니다.");
		            return response;
		        }

		        return completedCourses;



		    //수강신청 :studentId, courseId
		    case "6":
		        if (!stringTokenizer.hasMoreTokens()) {
		            response.add(
		                    "입력 형식 : 6 [Student ID] [Course ID]");
		            return response;
		        }

		        String registerStudentId = stringTokenizer.nextToken();

		        // Course ID 검사
		        if (!stringTokenizer.hasMoreTokens()) {
		            response.add(
		                    "입력 형식 : 6 [Student ID] [Course ID]");
		            return response;
		        }

		        String registerCourseId = stringTokenizer.nextToken();

		        // 인자가 2개보다 많으면 오류
		        if (stringTokenizer.hasMoreTokens()) {
		            response.add(
		                    "입력 형식 : 6 [Student ID] [Course ID]");
		            return response;
		        }

		        // 학생 존재 확인
		        if (!data.isRegisteredStudent(registerStudentId)) {
		            response.add(
		                    "존재하지 않는 Student ID입니다.");
		            return response;
		        }

		        // 과목 존재 확인
		        if (!data.isRegisteredCourse(registerCourseId)) {
		            response.add(
		                    "존재하지 않는 Course ID입니다.");
		            return response;
		        }

		        // 이미 완료한 과목인지 확인
		        if (data.hasCompletedCourse(registerStudentId, registerCourseId)) {
		            response.add(
		                    "이미 수강 완료한 과목입니다.");
		            return response;
		        }

		        // 현재 이미 신청한 과목인지 확인
		        if (data.isAlreadyRegistered(registerStudentId, registerCourseId)) {
		            response.add(
		                    "이미 수강 신청한 과목입니다.");
		            return response;
		        }

		        // 실제 등록
		        if (data.registerCourse(registerStudentId, registerCourseId)) {
		            response.add("수강 신청이 완료되었습니다.");
		            return response;
		        }

		        response.add("수강 신청에 실패했습니다.");
		        return response;


		    default:
		        response.add("Invalid Order");
		        return response;
	    }
	}
	*/

}
