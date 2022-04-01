import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class TestUnit {
    public static void main(String args[]) {
        StudentEnrolment stE = new StudentEnrolment();
        Scanner sc = new Scanner(System.in);
        //Test create semester method
        stE.setSemesters();
        System.out.println(stE.getSemesters());

        //Test create() method (course)
        stE.create("Course", "C001", "Further programming", "12");
        stE.create("Course", "C002", "Building IT System", "12");
        System.out.println(stE.getCourseList());

        //Test create() method (student)
        stE.create("Student", "S001", "Khoi", "28/01/2000");
        stE.create("Student", "S002", "Khoa", "20/10/2002");
        stE.create("Student", "S003", "Khang", "20/10/2002");
        System.out.println(stE.getStudentsList());

        //Test add course to semester
        enrol(stE, sc);
        stE.addSemesterCourses("C001", "2022A");
        stE.addSemesterCourses("C001", "2022A");
        stE.addSemesterCourses("C001", "2022B");
//        stE.addSemesterCourses("2022A", "C002");
//        stE.addSemesterCourses("2022C", "C003");
        System.out.println(stE.getSemesterCourses());


//        Test add student to course



////        stE.createEnrollment("S002", "C001", "2022B");


        stE.createEnrollment("S001", "C001", "2022A");
        stE.createEnrollment("S001", "C001", "2022A");
        stE.createEnrollment("S001", "C002", "2022A");
        stE.createEnrollment("S001", "C001", "2022B");
        stE.createEnrollment("S002", "C001", "2022B");
        System.out.println(stE.getEnrolmentList());

//        Test update method

//
//        //Test findAll() method (Course)
//        stE.findAll("Course", "2022B");
//        stE.findAll("Course", "2022A");
//
//        //Test findAll() method (Student)
//        stE.findAll("Student", "2022A");
//        stE.findAll("Student", "2022B");

//        Test findOne() method(Student)
//        stE.create("Student", "S002", "Khoa", "20/10/2002");
//        stE.createEnrollment("S002", "C001", "2022B");
//        stE.findOne("Student", "S004");
//        stE.findOne("Student", "S002");
//
//        //Test findOne() method(Course)
//        stE.findOne("Course", "C002");
//        stE.findOne("Course", "C004");
//
//        //Test delete() method(Student)
//        stE.delete("Student", "S003");
//        stE.delete("Student", "S004");
//        System.out.println(stE.getStudentsList());
////
////        Test delete() method(Course)
//        stE.delete("Course","C002");
//        stE.delete("Course","C004");
//        System.out.println(stE.getCourseList());
//        System.out.println(stE.getSemesterCourses());
//
//        //Test dropCourse() method
//        stE.dropCourse("C001", "S001", "2022B");
//        stE.dropCourse("C002", "S001", "2022A");
//        stE.dropCourse("C001", "S004", "2022A");
////        System.out.println(stE.getEnrolmentList());
//
//        //Write test
//        stE.writeCsv();
    }

    public static void enrol(StudentEnrolment stE, Scanner sc){
        System.out.println("asd");
        String couOD = sc.nextLine();
        System.out.println("asd");
        String sem = sc.nextLine();
        ArrayList<Course> temp = stE.getSemesterCourses().get(sem);
        for (Course couTemp : temp){
            if (couTemp.getCourseID().equals(couOD)){
                System.out.println("Cant do shit");
                return;
            }
            stE.addSemesterCourses(couOD, sem);
        }
    }
}