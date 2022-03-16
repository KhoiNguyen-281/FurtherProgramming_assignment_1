import java.util.ArrayList;

public class Student {
    private String studentID;
    private String studentName;
    private String studentBD;
    private ArrayList<Course> courseList;

    public Student(String studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentBD = null;
        this.courseList = new ArrayList<Course>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    @Override
    public String toString() {
        return "[Student Name: " + studentName + ", " +
                "Student ID: " + studentID + "]" + "\n";
    }
}
