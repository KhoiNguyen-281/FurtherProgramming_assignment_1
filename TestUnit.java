import java.time.Year;

public class TestUnit {
    public static void main(String args[]) {
        StudentEnrolment stE = new StudentEnrolment();

        Course c1 = new Course("C001", "Further Programming",12);
        Course c2 = new Course("C002", "Building IT System",12);
        Course c3 = new Course("C003", "Programming 1",12);

        Student s1 = new Student("S001", "Khoi", "28/01/2000");
        Student s2 = new Student("S002", "Hoang", "26/12/2000");
        Student s3 = new Student("S003", "Minh", "23/09/2000");

        stE.addSemester("C");
        stE.addSemester("B");
        stE.addSemester("A");


        stE.addCourseList(c1);
        stE.addCourseList(c1);
        stE.addCourseList(c2);
        stE.addCourseList(c3);

        stE.addStudentList(s1);
        stE.addStudentList(s2);
        stE.addStudentList(s3);

        stE.addSemesterCourses("2022A", c1);
        stE.addSemesterCourses("2022A", c2);
        stE.addSemesterCourses("2022A", c3);

        stE.addStudentToCourse(c1, s1);
        stE.addStudentToCourse(c1, s2);
        stE.addStudentToCourse(c1, s3);

        System.out.println(stE.getStudentInCourse());

        System.out.println(stE.getSemesterCourses());


    }
}