import java.util.ArrayList;

public class Course implements Management{
    private String courseID;
    private String courseName;
    private int courseNumOfCre;
    private ArrayList<Student> studentsList;

    public Course() {
    }

    public Course(String courseID, String courseName, int courseNumOfCre) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseNumOfCre = courseNumOfCre;
        this.studentsList = new ArrayList<Student>();

    }

    public String getCourseID() { return courseID; }

    public String getCourseName() { return courseName; }

    public ArrayList<Student> getStudentsList() {
        return studentsList;
    }

    public void setCourseID(String courseID) { this.courseID = courseID; }

    public void setCourseName(String courseName) { this.courseName = courseName; }

    public void setCourseNumOfCre(int courseNumOfCre) { this.courseNumOfCre = courseNumOfCre; }


    @Override
    public String toString() {
        return
                "ID: " + courseID +
                ", Name: " + courseName +
                ", Credit: " + courseNumOfCre + "\n";
    }

    @Override
    public void update(String field, String input) {
        if(field.equalsIgnoreCase("ID")){
            setCourseID(input);
        } if (field.equalsIgnoreCase("Name")){
            setCourseName(input);
        } if (field.equalsIgnoreCase("Number of credits")){
            setCourseNumOfCre(Integer.parseInt(input));
        }
    }

}
