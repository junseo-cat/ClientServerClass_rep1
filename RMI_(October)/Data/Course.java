import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String courseId ;
    protected String profName;
    protected String courseName;
    protected ArrayList<String> beforeCourses;

    public Course (String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
        this.courseId = stringTokenizer.nextToken();
        this.profName = stringTokenizer.nextToken();
        this.courseName = stringTokenizer.nextToken();

        this.beforeCourses = new ArrayList<String>();
        while (stringTokenizer.hasMoreTokens()) {
            this.beforeCourses.add(stringTokenizer.nextToken());
        }
    }

    public boolean match(String cId) {
        return this.courseId.equals(cId);
    }
    public String getName() {
        return this.courseName;
    }
    public ArrayList<String> getBeforeCourse() {
        return this.beforeCourses;
    }
    public String toString() {
        String stringReturn = String.format("%s    %-10s%-40s",this.courseId, this.profName, this.courseName);
        for (int i = 0; i < this.beforeCourses.size(); i++) {
            stringReturn = stringReturn + "    " + this.beforeCourses.get(i).toString();
        }
        return stringReturn;
    }
}
