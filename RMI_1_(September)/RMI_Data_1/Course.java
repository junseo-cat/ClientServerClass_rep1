import java.io.Serializable;
import java.util.StringTokenizer;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String courseId ;
    protected String profName;
    protected String courseName;
    protected String beforeCourse;

    public Course (String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
        this.courseId = stringTokenizer.nextToken();
        this.profName = stringTokenizer.nextToken();
        this.courseName = stringTokenizer.nextToken();

        this.beforeCourse = null;
        String lastToken = null;
        while (stringTokenizer.hasMoreTokens()) {
            lastToken = stringTokenizer.nextToken();
        }
        if (lastToken!=null) {
            this.beforeCourse = lastToken;
        }
    }

    public boolean match(String cId) {
        return this.courseId.equals(cId);
    }
    public String getName() {
        return this.courseName;
    }
    public String getBeforeCourse() {
        return this.beforeCourse;
    }
    public String toString() {
        String stringReturn = this.courseId + " " + this.profName + " " + this.courseName;
        if (this.beforeCourse != null) {
            stringReturn = stringReturn + " " + this.beforeCourse;
        }
        return stringReturn;
    }
}
