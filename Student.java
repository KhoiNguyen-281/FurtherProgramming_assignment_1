import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

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
        String finalID = "id";
        String finalName = "name";
        String finalDoB = "dob";
        String finalBD = "date of birth";
        String finalDb = "birthday";
        if (finalID.equalsIgnoreCase(field)){
            setStudentID(input);
        } if (finalName.equalsIgnoreCase(field)){
            setStudentName(input);
        } if (finalDoB.equalsIgnoreCase(field)
                || finalBD.equalsIgnoreCase(field)
                || finalDb.equalsIgnoreCase(field)){
            setStudentBD(input);
        }
    }

    @Override
    public String toString() {
        return
                "ID: " + studentID +
                ", Name: " + studentName +
                ", BD: " + studentBD + "\n" ;
    }
}
