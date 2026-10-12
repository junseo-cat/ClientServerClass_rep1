
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StudentList {
    protected ArrayList<Student> vStudent;

    public StudentList(String sStudentFileName) throws FileNotFoundException, IOException {
        BufferedReader objStudentFile = new BufferedReader(new FileReader(sStudentFileName));
        this.vStudent = new ArrayList<Student>();
        while (objStudentFile.ready()) {
            String stuInfo = objStudentFile.readLine();
            if (!stuInfo.equals("")) {
                this.vStudent.add(new Student(stuInfo));
            }
        }
        objStudentFile.close();
    }

    public ArrayList<Student> getAllStudentRecords() {
        return this.vStudent;
    }
    public boolean addStudentRecords(String inputInfo) {
        if(this.vStudent.add(new Student(inputInfo))) return true;
        else return false;
    }
    public boolean deleteStudentRecords(String studentId) {
        for (int i = 0; i < this.vStudent.size(); i++) {
            Student Student = (Student) this.vStudent.get(i);
            if (Student.match(studentId)) {
                if (this.vStudent.remove(Student)) return true;
                else return false;
            }
        }
        return false;
    }

    public boolean isRegisteredStudent(String sSID) {
        for (int i = 0; i < this.vStudent.size(); i++) {
            Student objStudent = (Student) this.vStudent.get(i);
            if (objStudent.match(sSID)) {
                return true;
            }
        }
        return false;
    }
}
