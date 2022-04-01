import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.*;

public class Menu {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StudentEnrolment stE = new StudentEnrolment();
        stE.setSemesters();
        stE.create("Student", "S001", "Khoi", "121212");
        stE.create("Student", "S002", "Khoi", "121212");
        stE.create("Student", "S003", "Khoi", "121212");


        stE.create("Course", "C001", "Further", "12");
        stE.create("Course", "C002", "Building", "12");
        stE.create("Course", "C003", "Software", "12");

        stE.addSemesterCourses("2022A", "C001");
        stE.addSemesterCourses("2022A", "C002");

        stE.createEnrollment("S001", "C001", "2022A");
        stE.createEnrollment("S001", "C002", "2022A");
//        String ID = sc.nextLine();
//        checkIfIDExist(stE, sc, ID);
//        System.out.println(checkIfIDExist(stE, "S001") == false);
        String ID = sc.nextLine();
        inputNewID(stE, sc, ID);

    }
    //Declare exit method() o exit
    //Tested
    public static boolean exit(String choice){
        String exit = "exit";
        if (exit.equalsIgnoreCase(choice)){
            System.out.println("Exited");
            return false;
        }
        return true;
    }

    //Declare check methods to check user in put
    //Check if ID is exist in lists (student, course)
    //Tested
    public static boolean checkIfIDExist(StudentEnrolment stE, String ID){
        boolean invalid = false;
        for (Student stuTemp : stE.getStudentsList()){
            if (stuTemp.getStudentID().equals(ID)){;
                invalid = true;
                return invalid;
            }
        }

        for (Course couTemp : stE.getCourseList()){
            if (couTemp.getCourseID().equals(ID)){
                invalid = true;
                return invalid;
            }
        }
        return invalid;
    }
    //Check if ID is exist in semester (student)
    public static boolean checkStuInSem(StudentEnrolment stE, String ID, String sem){
        boolean invalid = true;
        for (Student stuTemp : stE.getSemesterStudent().get(sem)){
            if (stuTemp.getStudentID().equals(ID)){
                invalid = false;
                return invalid;
            }
        }
        return invalid;
    }
    public static boolean checkSemValid(StudentEnrolment stE, String sem){
        boolean invalid = true;
        for (String semTemp : stE.getSemesters()){
            if(semTemp.equals(sem)){
                invalid = false;
                return invalid;
            }
        }
        return invalid;
    }
    public static boolean checkCourInList(StudentEnrolment stE, String cou){
        boolean valid = false;
        for (Course couTemp : stE.getCourseList()){
            if (couTemp.getCourseID().equals(cou) || couTemp.getCourseName().equals(cou)){
                valid = true;
                return valid;
            }
        }
        return valid;
    }
    public static boolean checkCourInSem(StudentEnrolment stE, String cou, String sem){
        boolean invalid = true;
        for (Course couTemp : stE.getSemesterCourses().get(sem)){
            if (couTemp.getCourseID().equals(cou) || couTemp.getCourseName().equals(cou)){
                invalid = false;
                return invalid;
            }
        }
        return invalid;
    }
//    public static boolean checkIfStuContainCour(StudentEnrolment stE, String stu, String cou){
//        boolean invalid = true;
//        for (stE.ge)
//        return invalid;
//    }

    //Declare methods to return value if valid
    //Tested
    public static String inputNewID(StudentEnrolment stE, Scanner sc, String ID){
        String valid_ID = "";
        while (checkIfIDExist(stE, ID)){
            System.out.println("ID existed, try again or type 'exit' to exit");
            System.out.print("Your choice: ");
            ID = sc.nextLine();
            exit(ID);
        }
        valid_ID = ID;
        return valid_ID;
    }


    public static void checkAllorOne(Scanner sc, String allOrOne){
        while (!allOrOne.equals("1")){
            if (allOrOne.equals("2")){
                break;
            }
            System.out.println("Invalid input, please try again, 'exit' to exit");
            System.out.print("Your choice: ");
            allOrOne = sc.nextLine();
            if (allOrOne.equals("exit")){
                break;
            }
        }
    }

    public static void checkChoice(Scanner sc, String choice){
        while (!choice.equals("1")){
            if (choice.equals("2")){
                break;
            }
            System.out.println("Invalid input, please try again, 'exit' to exit");
            System.out.print("Your choice: ");
            choice = sc.nextLine();
            if (exit(choice)){
                break;
            }
        }
    }




//    tested
    public static void checkChoiceTask(Scanner sc, String choice) {
        while (!choice.equals("c")){
            if (exit(choice)){
                break;
            }
            System.out.println("Invalid input, please try again, 'exit' to exit");
            System.out.print("Your choice: ");
            choice = sc.nextLine();
        }

    }
    public static boolean checkOption(Scanner sc, String option){
        boolean b = false;
        while (!option.equals("1")){
            if (option.equals("2")){
                b = true;
                return b;
            }
            if (option.equals("3")){
                b = true;
                return b;
            }
            if (option.equals("4")){
                b = true;
                return b;
            }
            if (option.equals("5")){
                b = true;
                return b;
            }
            exit(option);
            System.out.println("Invalid option, please try again, 'exit' to exit");
            System.out.print("Your choice: ");
            option = sc.nextLine();
        }
        return b;
    }

    public static void taskMenu(){
        System.out.println("""
                    - Press '1': Update enrolled course
                    - Press '2': Drop course
                    - Press '3': Add course
                    - Press '4': Update student information
                    - Press '5': Delete student
                    - Press 'exit': Exit""");
        System.out.print("Your choice: ");
    }
}
