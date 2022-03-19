import java.util.ArrayList;

public class Course {
    private String courseID;
    private String courseName;
    private int courseNumOfCre;

    public Course(String courseID, String courseName, int courseNumOfCre) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseNumOfCre = courseNumOfCre;
    }

    public String getCourseID() { return courseID; }

    public String getCourseName() { return courseName; }

    public int getCourseNumOfCre() { return courseNumOfCre; }

    public void setCourseID(String courseID) { this.courseID = courseID; }

    public void setCourseName(String courseName) { this.courseName = courseName; }

    public void setCourseNumOfCre(int courseNumOfCre) { this.courseNumOfCre = courseNumOfCre; }

    @Override
    public String toString() {
        return "[Course name: " + courseName + ", " +
                "Course ID: " + courseID + ", " +
                "Course Credits: " + courseNumOfCre + "]" + "\n";
    }
}
