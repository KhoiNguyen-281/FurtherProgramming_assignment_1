import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StudentEnrolment stE = new StudentEnrolment();
        stE.setSemesters();
        stE.createNewData( "1", "S001", "Khoi", "121212");
        stE.createNewData( "1","S002", "Khoi", "121212");
        stE.createNewData( "1","S003", "Khoi", "121212");
//
//
        stE.createNewData("2","C001", "Further", "12");
        stE.createNewData( "2","C002", "Building", "12");
        stE.createNewData( "2","C003", "Software", "12");

        stE.addSemesterCourses("C001", "2022A");
        stE.addSemesterCourses("C002", "2022A");

        stE.createEnrollment("S001", "C001", "2022A");
        stE.createEnrollment("S001", "C002", "2022A");
//
        createNew(stE, sc);

    }

//    1. Create new student, course
    //Check if input ID is valid to create new object
    //Tested
    public static void checkIfIDExist(StudentEnrolment stE, Scanner sc, String choice){
        if (choice.equals("1") || choice.equals("2")) {
            ArrayList<Student> stuTempList = stE.getStudentsList();
            ArrayList<Course> couTempList = stE.getCourseList();
            System.out.print("ID: ");
            String ID = sc.nextLine();
            if (ID.equalsIgnoreCase("exit")) {
                return;
            }
            if (choice.equals("1")) {
                for (Student student : stuTempList) {
                    while (student.getStudentID().equalsIgnoreCase(ID)) {
                        System.out.println("ID is existed, please input another, or 'exit' to exit");
                        System.out.print("ID: ");
                        ID = sc.nextLine();
                        if (ID.equalsIgnoreCase("exit")) {
                            return;
                        }
                    }
                }
            }
            if (choice.equals("2")) {
                for (Course course : couTempList) {
                    while (course.getCourseID().equalsIgnoreCase(ID)) {
                        System.out.println("ID is existed, please input another, or 'exit' to exit");
                        System.out.print("ID: ");
                        ID = sc.nextLine();
                        if (ID.equalsIgnoreCase("exit")) {
                            return;
                        }

                    }
                }
            }
            System.out.println("ID valid, continue");
            String name = "";
            String val = "";
            inputNewData(sc, name, val, choice);
            stE.createNewData(choice, ID, name, val);
        }

    }
    //Method for input new data
    //Tested
    public static void inputNewData(Scanner sc, String name, String val, String choice){
        System.out.print("Name: ");
        name = sc.nextLine();
        if (name.equalsIgnoreCase("exit")){
            return;
        }
        if (choice.equals("1")){
            System.out.print("Date of birth: ");
            val = sc.nextLine();
            if (val.equalsIgnoreCase("exit")){
                return;
            }
        }
        if (choice.equals("2")){
            System.out.print("Number of credits: ");
            val = sc.nextLine();
            if (val.equalsIgnoreCase("exit")){
                return;
            }
        }
    }
    //Method check if user choice is valid
    //Tested
    public static void checkChoice(Scanner sc, String choice){
        if (choice.equalsIgnoreCase("exit")){
            return;
        }
        while (!choice.equals("1")){
            if (choice.equals("2")){
                break;
            }
            System.out.println("Invalid input, please try again or type 'exit' to back");
            System.out.print("Input: ");
            choice = sc.nextLine();
            if (choice.equalsIgnoreCase("exit")){
                return;
            }
        }
    }
    //Method to create student and course
    //Tested
    public static void createNew(StudentEnrolment stE, Scanner sc){
        System.out.println("---Create student and course---");
        System.out.println("""
                +Press '1': Create student
                +Press '2': Create course
                +Type 'exit': Exit to menu page""");
        System.out.print("Input: ");
        String choice = sc.nextLine();
        checkChoice(sc, choice);
        while (!choice.equalsIgnoreCase("exit")){
            checkIfIDExist(stE, sc, choice);
            System.out.println("""
                +Press '1': Create student
                +Press '2': Create course
                +Type 'exit': Exit to menu page""");
            System.out.print("Your choice: ");
            choice = sc.nextLine();
            checkChoice(sc, choice);
        }
    }
////
//
////      Add course to semester
//    //Show available semesters
//    //Tested
//    public static void availableSem(StudentEnrolment stE){
//        String validSem = "";
//        for (String semTemp : stE.getSemesters()){
//            validSem += semTemp + " ";
//        }
//        System.out.println("Available semesters: ");
//        System.out.println(validSem);
//    }
//    //Check if input semester is valid to enroll, add course, find, delete
//    //Tested
//    public static String checkSemValid(StudentEnrolment stE, Scanner sc, String sem){
//        System.out.print("Semester: ");
//        sem = sc.nextLine();
//        for (String semTemp : stE.getSemesters()){
//            while(!semTemp.equals(sem)){
//                System.out.println(semTemp.equals(sem));
//                System.out.println("Semester is not valid, please try again, 'exit' to exit");
//                System.out.print("Semester: ");
//                sem = sc.nextLine();
//                if (exit(sem).equals(sem)){
//                    return sem;
//                }
//            }
//        }
//        System.out.println("Semester valid, continue");
//        return sem;
//    }
//
//    //Check if course is existed to add to semester
//    //Tested
//    public static String checkCourInList(StudentEnrolment stE,Scanner sc, String cou){
//        availableCourseInList(stE);
//        System.out.println("Input course ID or name to add to semester, 'exit' to exit");
//        System.out.print("Course ID or name: ");
//        cou = sc.nextLine();
//        if (exit(cou).equals(cou)){
//            return cou;
//        }
//        for (Course couTemp : stE.getCourseList()){
//            while (!couTemp.getCourseID().equals(cou)){
//                if (couTemp.getCourseName().equals(cou)){
//                    break;
//                }
//                System.out.println("Cannot find course, please try again, 'exit' to exit");
//                System.out.print("Course ID or name: ");
//                cou = sc.nextLine();
//                if (exit(cou).equals(cou)){
//                    return cou;
//                }
//            }
//        }
//        return cou;
//    }
//    //Input course information to add to semester
//    //Tested
//    public static void addCourseToSemester(StudentEnrolment stE, Scanner sc){
//        System.out.println("---Add course to semester---");
//        System.out.println("""
//                +Press any: Start function
//                +Type 'exit': Exit to menu""");
//        String choice = sc.nextLine();
//        while (!exit(choice).equals(choice)){
//            availableSem(stE);
//            String sem = "";
//            checkSemValid(stE, sc, sem);
//            String cou = "";
//            while (!exit(cou).equals(cou)){
//                checkCourInList(stE, sc, cou);
//                stE.addSemesterCourses(cou, sem);
//                System.out.println("""
//                        +Press 'any': Continue adding course
//                        +Type 'exit': Exit to select semester""");
//                System.out.print("Your choice");
//                cou = sc.nextLine();
//            }
//            System.out.println("""
//                +Press any: Start function again
//                +Type 'exit': Exit to menu""");
//            choice = sc.nextLine();
//        }
//    }
//
//    //Declare check methods to check user in put
//
//
//
//
//
//
//
//    //Check if ID is exist in lists (student, course)
//    //Tested
//    public static boolean checkStuInSem(StudentEnrolment stE, String ID, String sem){
//        for (Student stuTemp : stE.getSemesterStudent().get(sem)){
//            if (stuTemp.getStudentID().equals(ID)){
//                return false;
//            }
//            if (exit(ID).equals(ID)){
//                System.out.println("Exit");
//                return false;
//            }
//        }
//        return true;
//    }
//
//    //Method to input existed ID to find, enrol, update
//    //Tested
//    public static String inputExistedID(StudentEnrolment stE, Scanner sc, String ID, String sem){
//        String validID = "";
//        while (checkStuInSem(stE, ID, sem)){
//            System.out.println("Cannot find student with this ID, please try again, 'exit' to exit");
//            System.out.print("Your choice: ");
//            ID = sc.nextLine();
//        }
//        validID = ID;
//        return validID;
//    }
//
//
//    //Show available course in semester to enrol
//    //Tested
//    public static void availableCourseInSem(StudentEnrolment stE, String sem){
//        String available = "";
//        for (Course couTemp : stE.getSemesterCourses().get(sem)){
//            available += couTemp;
//        }
//        System.out.println("Available course in this sem: ");
//        System.out.println(available);
//    }
//
//    //Show available course in course's list
//    //Tested
//    public static void availableCourseInList(StudentEnrolment stE){
//        String available = "";
//        for (Course couTemp : stE.getCourseList()){
//            available += couTemp;
//        }
//        System.out.println("Available course: ");
//        System.out.println(available);
//    }
//
//    //Check if course is existed to enroll student, update course, drop course, find student;
//    //Tested
//    public static boolean checkCourInSem(StudentEnrolment stE, String cou, String sem){
//        for (Course couTemp : stE.getSemesterCourses().get(sem)){
//            if (couTemp.getCourseID().equals(cou) || couTemp.getCourseName().equals(cou)){
//                System.out.println("Course valid, continue");
//                return false;
//            }
//            if (exit(cou).equals(cou)){
//                System.out.println("Exit");
//                return false;
//            }
//        }
//        return true;
//    }
//
//    //Input semester's course information to enrol
//    //Tested
//    public static String inputCouInSem(StudentEnrolment stE, Scanner sc, String cou, String sem){
//        String validCourse = "";
//        while (checkCourInSem(stE, cou, sem)){
//            System.out.println("Cannot find course, please try again, 'exit' to exit");
//            System.out.print("Your choice: ");
//            cou = sc.nextLine();
//        }
//        validCourse = cou;
//        return validCourse;
//    }
//
//    public static boolean checkIfStuContainCourSem(StudentEnrolment stE, String stu, String cou, String sem){
//        boolean invalid = true;
//        int indexStu = 0;
//        ArrayList<Student> stuListTemp = stE.getSemesterStudent().get(sem);
//        for (int i = 0; i < stuListTemp.size(); i++){
//            if (stuListTemp.get(i).equals(stu)){
//                indexStu = i;
//                invalid = false;
//                break;
//            }
//        }
//        for (Course couTemp : stuListTemp.get(indexStu).getCoursesList()){
//            if (couTemp.getCourseID().equals(cou) || couTemp.getCourseName().equals(cou)){
//                invalid = false;
//                break;
//            }
//        }
//        return invalid;
//    }
//
//
//
//
//
//
////    public static void checkAllorOne(Scanner sc, String allOrOne){
////        while (!allOrOne.equals("1")){
////            if (allOrOne.equals("2")){
////                break;
////            }
////            System.out.println("Invalid input, please try again, 'exit' to exit");
////            System.out.print("Your choice: ");
////            allOrOne = sc.nextLine();
////            if (allOrOne.equals("exit")){
////                break;
////            }
////        }
////    }
//
////    public static void checkChoice(Scanner sc, String choice){
////        while (!choice.equals("1")){
////            if (choice.equals("2")){
////                break;
////            }
////            System.out.println("Invalid input, please try again, 'exit' to exit");
////            System.out.print("Your choice: ");
////            choice = sc.nextLine();
////            if (exit(choice)){
////                break;
////            }
////        }
////    }
////
////
////
////
//////    tested
////    public static void checkChoiceTask(Scanner sc, String choice) {
////        while (!choice.equals("c")){
////            if (exit(choice)){
////                break;
////            }
////            System.out.println("Invalid input, please try again, 'exit' to exit");
////            System.out.print("Your choice: ");
////            choice = sc.nextLine();
////        }
////
////    }
////    public static boolean checkOption(Scanner sc, String option){
////        boolean b = false;
////        while (!option.equals("1")){
////            if (option.equals("2")){
////                b = true;
////                return b;
////            }
////            if (option.equals("3")){
////                b = true;
////                return b;
////            }
////            if (option.equals("4")){
////                b = true;
////                return b;
////            }
////            if (option.equals("5")){
////                b = true;
////                return b;
////            }
////            exit(option);
////            System.out.println("Invalid option, please try again, 'exit' to exit");
////            System.out.print("Your choice: ");
////            option = sc.nextLine();
////        }
////        return b;
////    }
////
////    public static void taskMenu(){
////        System.out.println("""
////                    - Press '1': Update enrolled course
////                    - Press '2': Drop course
////                    - Press '3': Add course
////                    - Press '4': Update student information
////                    - Press '5': Delete student
////                    - Press 'exit': Exit""");
////        System.out.print("Your choice: ");
////    }
}
