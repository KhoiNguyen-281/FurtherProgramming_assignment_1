import java.util.ArrayList;

interface StudentEnrolmentManagement{
    boolean addStudent(Course course, Student student);

}


public class StudentEnrolment{
    private String semester;
    private Course course;
    private Student student;
    private ArrayList<Student> studentsList;
    private ArrayList<Course> coursesList;

}
