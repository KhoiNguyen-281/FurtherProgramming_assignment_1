import java.util.ArrayList;
import java.util.Date;

public class Student implements Management {
    private String studentID;
    private String studentName;
    private String studentBD;
    private ArrayList<Course> coursesList;


    public Student(){
    }

    public Student(String studentID, String studentName, String studentBD) {
        this.studentBD = studentBD;
        this.studentID = studentID;
        this.studentName = studentName;
        this.coursesList = new ArrayList<Course>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentBD() { return studentBD; }

    public ArrayList<Course> getCoursesList() {
        return coursesList;
    }

    public void setStudentID(String studentID) { this.studentID = studentID; }

    public void setStudentName(String studentName) { this.studentName = studentName; }

    public void setStudentBD(String studentBD) { this.studentBD = studentBD; }


    @Override
    public void update(String field, String input){
        if (field.equals("ID")){
            setStudentID(input);
        } if (field.equals("Name")){
            setStudentName(input);
        } if (field.equals("DoB")){
            setStudentBD(input);
        }
    }

    @Override
    public void getOne() {

    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentBD='" + studentBD + '\'' +
                ", coursesList=" + coursesList +
                '}';
    }
}
