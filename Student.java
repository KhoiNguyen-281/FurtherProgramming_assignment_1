import java.util.ArrayList;
import java.util.Date;

public class Student {
    private String studentID;
    private String studentName;
    private Date studentBD;


    public Student(){
    }

    public Student(String studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentBD = null;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public Date getStudentBD() { return studentBD; }

    public void setStudentID(String studentID) { this.studentID = studentID; }

    public void setStudentName(String studentName) { this.studentName = studentName; }

    public void setStudentBD(Date studentBD) { this.studentBD = studentBD; }

    @Override
    public String toString() {
        return "[Student Name: " + studentName + ", " +
                "Student ID: " + studentID + "]" + "\n";
    }
}
