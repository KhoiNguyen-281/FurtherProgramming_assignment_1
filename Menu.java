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
        stE.createStu( "S001", "Khoi", "121212");
        stE.createStu( "S002", "Khoi", "121212");
        stE.createStu( "S003", "Khoi", "121212");
//
//
        stE.createCou("C001", "Further", "12");
        stE.createCou( "C002", "Building", "12");
        stE.createCou( "C003", "Software", "12");

        stE.addSemesterCourses("C001", "2022A");
        stE.addSemesterCourses("C002", "2022A");

        stE.createEnrollment("S001", "C001", "2022A");
        stE.createEnrollment("S001", "C002", "2022A");



        createNew(stE, sc);


    }
    /**
     * 1. Create new student, Course
     * 2. Add course to semester
     * 3. Enrol student
     * 4. Search**/
    //Method to check if user's choice is valid
    public static boolean checkChoiceMenu(StudentEnrolment stE, String choice){
        String[] choiceList = {"1","2","3","4"};
        for (String c : choiceList){
            if (c.equals(choice)){
                return false;
            }
            if (exit(choice).equals(choice)){
                return false;
            }
        }
        return true;
    }

    public static boolean checkChoiceCreate( String choice){
        String[] choiceList = {"1","2"};
        for (String c : choiceList){
            if (choice.equals(c)){
                return false;
            }
            if (exit(choice).equals(choice)){
                return false;
            }
        }
        return true;
    }


//    1. Create new student, course
    //Method to input new ID to create student, course
    //Tested
    public static String inputNewID(StudentEnrolment stE, Scanner sc, String ID, String mod){
        String valid_ID = "";
        System.out.print(mod + "ID: ");
        ID = sc.nextLine();
        if (exit(ID).equals(ID)){
            return null;
        }
        while (checkIfIDExist(stE, ID)){
            System.out.println("ID existed, try again or type 'exit' to exit");
            System.out.print("Your choice: ");
            ID = sc.nextLine();
        }
        valid_ID = ID;
        return valid_ID;
    }
    //Method to input new name to create student, course
    //Tested
    public static String inputNewName(Scanner sc, String name){
        System.out.print("Name: ");
        name = sc.nextLine();
        if (exit(name).equals(name)){
            return null;
        }
        return name;
    }
    //Method menu for create
    //Tested
    public static void createChoice(){
        System.out.println("""
                +Press '1': Create student
                +Press '2': Create course
                +Type 'exit': Exit to menu page""");
        System.out.print("Your choice: ");
    }
    //Method to create student and course
    //Tested
    public static void createNew(StudentEnrolment stE, Scanner sc){
        System.out.println("---In this function, create student and course---");
        createChoice();
        String choice = sc.nextLine();
        while(checkChoiceCreate(choice)){
            System.out.println("Invalid choice, please try again");
            System.out.print("Your choice: ");
            choice = sc.nextLine();
        }
        while (!exit(choice).equals(choice)){
            String ID = "";
            String name = "";
            String mod = "";
            if (choice.equals("1")){
                mod = "Student ";
                inputNewID(stE, sc, ID, mod);
                inputNewName(sc, name);
                System.out.print("Date of birth: ");
                String val = sc.nextLine();
                if (exit(val).equals(val)){
                    return;
                }
                stE.createStu(ID, name, val);
            }
            if (choice.equals("2")){
                mod = "Course ";
                inputNewID(stE, sc, ID,mod);
                inputNewName(sc, name);
                System.out.print("Credits: ");
                String val = sc.nextLine();
                if (exit(val).equals(val)){
                    return;
                }
                stE.createCou(ID, name, val);
            }
            createChoice();
            choice = sc.nextLine();
        }
    }
//


    //Declare exit method() o exit
    //Tested
    public static String exit(String choice){
        String finalExit = "exit";
        finalExit.equalsIgnoreCase(choice);
        return finalExit;
    }

    //Declare check methods to check user in put

    //Check if input semester is valid to enroll, add course, find, delete
    //Tested
    public static boolean checkSemValid(StudentEnrolment stE, String sem){
        for (String semTemp : stE.getSemesters()){
            if(semTemp.equals(sem)){
                System.out.println("Semester valid, continue");
                return false;
            }
            if (exit(sem).equals(sem)){
                System.out.println("Exit");
                return false;
            }
        }
        return true;
    }
    //Show available semesters
    //Tested
    public static void availableSem(StudentEnrolment stE){
        String validSem = "";
        for (String semTemp : stE.getSemesters()){
            validSem += semTemp + " ";
        }
        System.out.println("Available semesters: ");
        System.out.println(validSem);
    }
    //Method to input semesters to find, update, enrol, drop course, update
    //Tested
    public static String inputSemester(StudentEnrolment stE, Scanner sc, String sem){
        String validSem = "";
        while (checkSemValid(stE, sem)){
            System.out.println("Semester is not exist in the system, please try again, 'exit' to exit");
            System.out.print("Your input: ");
            sem =  sc.nextLine();
        }
        validSem = sem;
        return validSem;
    }
    //Check if ID is exist in semester (student) to enrol, find, update student, update course, drop course;
    //Tested
    public static boolean checkIfIDExist(StudentEnrolment stE, String ID){
        for (Student stuTemp : stE.getStudentsList()){
            if (stuTemp.getStudentID().equals(ID)){;
                return true;
            }
            if (exit(ID).equals(ID)){
                System.out.println("Exit");
                return false;
            }
            System.out.println("ID valid, continue");
            return false;
        }
        for (Course couTemp : stE.getCourseList()){
            if (couTemp.getCourseID().equals(ID)){
                return true;
            }
            if (exit(ID).equals(ID)){
                System.out.println("Exit");
                return false;
            }
            System.out.println("ID valid, continue");
            return false;
        }
        return false;
    }


    //Check if ID is exist in lists (student, course)
    //Tested
    public static boolean checkStuInSem(StudentEnrolment stE, String ID, String sem){
        for (Student stuTemp : stE.getSemesterStudent().get(sem)){
            if (stuTemp.getStudentID().equals(ID)){
                return false;
            }
            if (exit(ID).equals(ID)){
                System.out.println("Exit");
                return false;
            }
        }
        return true;
    }

    //Method to input existed ID to find, enrol, update
    //Tested
    public static String inputExistedID(StudentEnrolment stE, Scanner sc, String ID, String sem){
        String validID = "";
        while (checkStuInSem(stE, ID, sem)){
            System.out.println("Cannot find student with this ID, please try again, 'exit' to exit");
            System.out.print("Your choice: ");
            ID = sc.nextLine();
        }
        validID = ID;
        return validID;
    }
    //Check if course is existed to add to semester
    //Tested
    public static boolean checkCourInList(StudentEnrolment stE, String cou){
        for (Course couTemp : stE.getCourseList()){
            if (couTemp.getCourseID().equals(cou) || couTemp.getCourseName().equals(cou)){
                System.out.println("Course available, continue");
                return false;
            }
            if (exit(cou).equals(cou)){
                System.out.println("Exit");
                return false;
            }
        }
        return true;
    }
    //Input course information to add to semester
    //Tested
    public static String inputCou(StudentEnrolment stE, Scanner sc, String cou){
        String validCOurse =  "";
        while (checkCourInList(stE, cou)){
            System.out.println("Cannot find course, please try again, 'exit' to exit");
            System.out.print("Your choice: ");
            cou =  sc.nextLine();
        }
        validCOurse =  cou;
        return validCOurse;
    }
    //Show available course in semester to enrol
    //Tested
    public static void availableCourseInSem(StudentEnrolment stE, String sem){
        String available = "";
        for (Course couTemp : stE.getSemesterCourses().get(sem)){
            available += couTemp;
        }
        System.out.println("Available course in this sem: ");
        System.out.println(available);
    }

    //Show available course in course's list
    //Tested
    public static void availableCourseInList(StudentEnrolment stE){
        String available = "";
        for (Course couTemp : stE.getCourseList()){
            available += couTemp;
        }
        System.out.println("Available course: ");
        System.out.println(available);
    }

    //Check if course is existed to enroll student, update course, drop course, find student;
    //Tested
    public static boolean checkCourInSem(StudentEnrolment stE, String cou, String sem){
        for (Course couTemp : stE.getSemesterCourses().get(sem)){
            if (couTemp.getCourseID().equals(cou) || couTemp.getCourseName().equals(cou)){
                System.out.println("Course valid, continue");
                return false;
            }
            if (exit(cou).equals(cou)){
                System.out.println("Exit");
                return false;
            }
        }
        return true;
    }

    //Input semester's course information to enrol
    //Tested
    public static String inputCouInSem(StudentEnrolment stE, Scanner sc, String cou, String sem){
        String validCourse = "";
        while (checkCourInSem(stE, cou, sem)){
            System.out.println("Cannot find course, please try again, 'exit' to exit");
            System.out.print("Your choice: ");
            cou = sc.nextLine();
        }
        validCourse = cou;
        return validCourse;
    }

    public static boolean checkIfStuContainCourSem(StudentEnrolment stE, String stu, String cou, String sem){
        boolean invalid = true;
        int indexStu = 0;
        ArrayList<Student> stuListTemp = stE.getSemesterStudent().get(sem);
        for (int i = 0; i < stuListTemp.size(); i++){
            if (stuListTemp.get(i).equals(stu)){
                indexStu = i;
                invalid = false;
                break;
            }
        }
        for (Course couTemp : stuListTemp.get(indexStu).getCoursesList()){
            if (couTemp.getCourseID().equals(cou) || couTemp.getCourseName().equals(cou)){
                invalid = false;
                break;
            }
        }
        return invalid;
    }






//    public static void checkAllorOne(Scanner sc, String allOrOne){
//        while (!allOrOne.equals("1")){
//            if (allOrOne.equals("2")){
//                break;
//            }
//            System.out.println("Invalid input, please try again, 'exit' to exit");
//            System.out.print("Your choice: ");
//            allOrOne = sc.nextLine();
//            if (allOrOne.equals("exit")){
//                break;
//            }
//        }
//    }

//    public static void checkChoice(Scanner sc, String choice){
//        while (!choice.equals("1")){
//            if (choice.equals("2")){
//                break;
//            }
//            System.out.println("Invalid input, please try again, 'exit' to exit");
//            System.out.print("Your choice: ");
//            choice = sc.nextLine();
//            if (exit(choice)){
//                break;
//            }
//        }
//    }
//
//
//
//
////    tested
//    public static void checkChoiceTask(Scanner sc, String choice) {
//        while (!choice.equals("c")){
//            if (exit(choice)){
//                break;
//            }
//            System.out.println("Invalid input, please try again, 'exit' to exit");
//            System.out.print("Your choice: ");
//            choice = sc.nextLine();
//        }
//
//    }
//    public static boolean checkOption(Scanner sc, String option){
//        boolean b = false;
//        while (!option.equals("1")){
//            if (option.equals("2")){
//                b = true;
//                return b;
//            }
//            if (option.equals("3")){
//                b = true;
//                return b;
//            }
//            if (option.equals("4")){
//                b = true;
//                return b;
//            }
//            if (option.equals("5")){
//                b = true;
//                return b;
//            }
//            exit(option);
//            System.out.println("Invalid option, please try again, 'exit' to exit");
//            System.out.print("Your choice: ");
//            option = sc.nextLine();
//        }
//        return b;
//    }
//
//    public static void taskMenu(){
//        System.out.println("""
//                    - Press '1': Update enrolled course
//                    - Press '2': Drop course
//                    - Press '3': Add course
//                    - Press '4': Update student information
//                    - Press '5': Delete student
//                    - Press 'exit': Exit""");
//        System.out.print("Your choice: ");
//    }
}
