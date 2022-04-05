import java.util.ArrayList;

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

    public ArrayList<Course> getCoursesList() {
        return coursesList;
    }

    public void setStudentID(String studentID) { this.studentID = studentID; }

    public void setStudentName(String studentName) { this.studentName = studentName; }

    public void setStudentBD(String studentBD) { this.studentBD = studentBD; }



    @Override
    public void update(String field, String input){

        if (field.equalsIgnoreCase("ID")){
            setStudentID(input);
        } if (field.equalsIgnoreCase("Name")){
            setStudentName(input);
        } if (field.equalsIgnoreCase("Date of birth")){
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
