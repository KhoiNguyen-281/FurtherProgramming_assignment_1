import java.util.ArrayList;

public class Course {
    private String courseID;
    private String courseName;
    private String courseNumOfCre;
    private ArrayList<Student> studentList;

    public Course(String courseID, String courseName, String courseNumOfCre) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseNumOfCre = courseNumOfCre;
        this.studentList = new ArrayList<Student>();
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseNumOfCre() {
        return courseNumOfCre;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public boolean studentEnrol(Student student){
        if (studentList.contains(student)){
            System.out.println("Already exist");
            return false;
        }
        studentList.add(student);
        student.getCourseList().add(this);
        System.out.println("Enrolled successfully");
        return true;
    }

    @Override
    public String toString() {
        return "[Course name: " + courseName + ", " +
                "Course ID: " + courseID + ", " +
                "Course Credits: " + courseNumOfCre + "]" + "\n";
    }
}
