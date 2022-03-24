import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Course implements Management{
    private String courseID;
    private String courseName;
    private int courseNumOfCre;
    private ArrayList<Student> studentsList;

    public Course(String courseID, String courseName, int courseNumOfCre) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseNumOfCre = courseNumOfCre;
        this.studentsList = new ArrayList<Student>();

    }

    public String getCourseID() { return courseID; }

    public String getCourseName() { return courseName; }

    public int getCourseNumOfCre() { return courseNumOfCre; }

    public ArrayList<Student> getStudentsList() {
        return studentsList;
    }

    public void setCourseID(String courseID) { this.courseID = courseID; }

    public void setCourseName(String courseName) { this.courseName = courseName; }

    public void setCourseNumOfCre(int courseNumOfCre) { this.courseNumOfCre = courseNumOfCre; }

    public void setStudentsList(ArrayList<Student> studentsList) {
        this.studentsList = studentsList;
    }


    @Override
    public String toString() {
        return
                "ID='" + courseID + '\'' +
                ", Name='" + courseName + '\'' +
                ", Credits=" + courseNumOfCre ;
    }

    @Override
    public void update(String field, String input) {
        if(field.equals("id") || field.equals("ID") || field.equals("Id")){
            setCourseID(input);
        } if (field.equals("name") || field.equals("NAME") || field.equals("Name")){
            setCourseName(input);
        } if (field.equals("credits") ||
                field.equals("Number of credits") ||
                field.equals("Credits") ||
                field.equals("NoC") ||
                field.equals("noc") ||
                field.equals("NOC")){
            setCourseNumOfCre(Integer.parseInt(input));
        }
    }

}
