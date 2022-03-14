import java.util.ArrayList;

public class Student {
    private String studentID;
    private String studentName;
    private String studentBD;
    private ArrayList<Course> courseList;

    public Student(String studentID, String studentName, String studentBD) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentBD = studentBD;
    }
}
