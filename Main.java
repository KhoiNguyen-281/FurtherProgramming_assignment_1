import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StudentEnrolment stE = new StudentEnrolment();
        ArrayList<Course> couList = stE.getCourseList();
        ArrayList<Student> stuList = stE.getStudentsList();
        ArrayList<String> semesters = stE.getSemesters();
        HashMap<String, ArrayList<Course>> couInSem = stE.getSemesterCourses();
        //Create for testing purpose
        stE.setSemesters();
        stE.createNewData("1", "S001", "Khoi", "121212");
        stE.createNewData("1", "S002", "Khoi", "121212");
        stE.createNewData("1", "S003", "Khoi", "121212");

        stE.createNewData("2", "C001", "Further", "12");
        stE.createNewData("2", "C002", "Building", "12");
        stE.createNewData("2", "C003", "Software", "12");

        stE.addSemesterCourses("C001", "2022A");
        stE.addSemesterCourses("C002", "2022A");

        stE.createEnrollment("S001", "C001", "2022A");
        stE.createEnrollment("S001", "C002", "2022A");

        Menu newMenu = new Menu();
        newMenu.mainMenu(stE, sc, semesters, stuList, couList, couInSem);
    }
}
