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
        upDateStu(stE, sc, "S001");
//        upDateCourse(stE, sc, "S001", "2022A");
//        dropCourse(stE, sc, "S001", "2022A");
//        find(stE, sc);
//        String choice = sc.nextLine();
//        checkChoice(sc, choice);
//        checkChoiceTask(sc, choice);

//        taskPerform(stE, sc, "S001", "2022A" );
    }
    public static void menu(Scanner sc){
        System.out.println("""
                Welcome!!
                - Press '1': Create new student or new course
                - Press '2': Add courses to semesters
                - Press '3': Enroll students to course
                - Press '4': View students or course
                - Press 'e': exits"""

        );
        System.out.print("Your choice: ");
        String choice = sc.nextLine();
        while (!choice.equals("e")){
            System.out.println("""
                            - Press '1': Create new student
                            - Press '2': Create new course
                            - Press '3': Add courses to semesters
                            - Press '4': Enroll students to course
                            - Press '5': View students or course
                            - Press 'e': exits"""

            );
            System.out.print("Your choice: ");
            choice = sc.nextLine();
        }
    }

//    public static void create(StudentEnrolment stE, Scanner sc){
//        System.out.println("""
//                - Press '1': Create student
//                - Press '2': Create course""");
//        System.out.print("Your choice: ");
//        String choice = sc.nextLine();
//        boolean b = true;
//        while (checkChoiceTask(sc, choice)){
//            if (choice.equals("1")){
//                createStudent(stE, sc);
//            }
//            if (choice.equals("2")){
//               createCourse(stE, sc);
//            }
////            System.out.println("""
////                    - Press '1': Continue create student
////                    - Press '2': Create course
////                    - Press any: Exit to menu""");
////            choice = sc.nextLine();
//        }
//    }

    public static void createStudent(StudentEnrolment stE, Scanner sc){
            String mod = "Student";
            System.out.print("ID: ");
            String ID = sc.nextLine();
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Date of birth (dd/mm/yyyy): ");
            String val = sc.nextLine();
            stE.create(mod, ID, name, val);
    }

    public static void createCourse(StudentEnrolment stE, Scanner sc){
            String mod = "Course";
            System.out.print("ID: ");
            String ID = sc.nextLine();
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Credits: ");
            String val = sc.nextLine();
            stE.create(mod, ID, name, val);
    }
    public static void availableCoursSem(StudentEnrolment stE){
        System.out.println("Available semester: ");
        stE.setSemesters();
        for (String sem : stE.getSemesters()){
            System.out.print(sem + " ");
        }
        System.out.println( "\n" + "Available courses: ");
        for (Course couTemp : stE.getCourseList()){
            System.out.print(couTemp.toString());
        }
    }

    public static void addCourseToSemester(StudentEnrolment stE, Scanner sc){
        String choice = "c";
        availableCoursSem(stE);
        while (choice.equals("c")){
            if (stE.getCourseList().isEmpty()){
                System.out.println("Please create course first");
                break;
            }
            System.out.println("Please input semester and course ID or name to add.");
            System.out.print("Semester: ");
            String sem = sc.nextLine();
            while (!stE.getSemesters().contains(sem)){
                System.out.println("Semester is not in the system");
                System.out.print("Semester: ");
                sem = sc.nextLine();
            }
            System.out.print("Course: ");
            String course =  sc.nextLine();
            for (Course couTemp : stE.getCourseList()){
                while(!couTemp.getCourseID().equals(course)){
                    if (couTemp.getCourseName().equals(course)){
                        break;
                    }
                    System.out.println("Cannot find course");
                    System.out.print("Course: ");
                    course =  sc.nextLine();
                }
                break;
            }
            stE.addSemesterCourses(sem, course);
            System.out.println("""
                    - Press 'c': Continue
                    - Press nay: Exist""");
            System.out.print("Your choice: ");
            choice = sc.nextLine();
        }

    }
    public static void enrollStudent(StudentEnrolment stE, Scanner sc, String stuID, String sem, String choice){
        stE.setSemesters();
        System.out.println("Available semester: ");
        HashMap<String, ArrayList<Course>> semCouTemp =  stE.getSemesterCourses();
        for (String semC : semCouTemp.keySet()){
            ArrayList<Course> couTemp = semCouTemp.get(semC);
            if (!couTemp.isEmpty()){
                System.out.print(semC + " ");
                System.out.println("\n" + "Available courses: ");
                for (Course couTemp2 : couTemp){
                    System.out.print(couTemp2.toString());
                }
            }
        }
        while (choice.equals("c")){
            if (semCouTemp.isEmpty()){
                System.out.println("Please add courses to semesters first");
                break;
            }
            if (stE.getStudentsList().isEmpty()){
                System.out.println("Please create student first");
                break;
            }
            System.out.println("Input student ID, semester and course name or ID to enroll.");
            System.out.print("Student ID: ");
            stuID = sc.nextLine();
            for (Student stuTemp : stE.getSemesterStudent().get(sem)){
                while (!stuTemp.getStudentID().equals(stuID)){
                    System.out.println("Cannot find student, please try again");
                    System.out.print("Student ID: ");
                    stuID = sc.nextLine();
                }
                break;
            }
            System.out.print("Semester: ");
            sem = sc.nextLine();
            while (!stE.getSemesterCourses().containsValue(sem)){
                System.out.println("Semester is not in the system, please try again");
                System.out.print("Semester: ");
                sem = sc.nextLine();
            }
            System.out.print("Course: ");
            String cou = sc.nextLine();
            for (Course couTemp : stE.getSemesterCourses().get(sem)){
                while(!couTemp.getCourseName().equals(cou)){
                    if (couTemp.getCourseID().equals(cou)){
                        break;
                    }
                    System.out.println("Cannot find course, please try again");
                    System.out.print("Course: ");
                    cou = sc.nextLine();
                }
                break;
            }
            stE.createEnrollment(stuID, cou, sem);
            System.out.println("""
                    - Press 'c': Continue
                    - Press any: Exist""");
            System.out.print("Your choice: ");
            choice = sc.nextLine();
        }
    }

    public static void find(StudentEnrolment stE, Scanner sc){
        System.out.println("Input semester for program to be ran or 'exit' to exit");
        System.out.print("Semester format (2022A): ");
        String sem = sc.nextLine();
        while (!sem.equals("exit")){
            if (sem.equals("exit")){
                break;
            }
            while (!stE.getSemesters().contains(sem)){
                System.out.println("Invalid semesters");
                System.out.println("Please choose again");
                sem = sc.nextLine();
                if (sem.equals("exit")){
                    return;
                }
            }
            System.out.println("""
                    - Press '1': Show all'
                    - Press '2': Show one""");
            System.out.print("Your choice: ");
            String allOrOne = sc.nextLine();
            checkAllorOne(sc, allOrOne);
            if (allOrOne.equals("1")){
                findAll(stE, sc, sem);
            }
            if (allOrOne.equals("2")){
                findOne(stE, sc, sem);
            }
            System.out.println("Input semester for program to be ran or 'exit' to exit");
            System.out.print("Semester: ");
            sem = sc.nextLine();
        }
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

    public static void findAll(StudentEnrolment stE, Scanner sc, String sem){
        System.out.println("""
                - Press '1': Find student
                - Press '2': Find course""");
        System.out.print("Your choice: ");
        String choice = sc.nextLine();
        checkChoice(sc, choice);
        if (choice.equals("1")){
            findAllStu(stE, sem);
        }
        if (choice.equals("2")){
            findAllCou(stE, sem);
        }
    }

    public static void findAllCou(StudentEnrolment stE, String sem){
        String mod = "Course";
        stE.findAll(mod, sem);
    }

    public static void findAllStu(StudentEnrolment stE,String sem){
        String mod = "Student";
        stE.findAll(mod, sem);
    }

    public static void findOne(StudentEnrolment stE, Scanner sc, String sem){
        System.out.println("""
                - Press '1': Find student
                - Press '2': Find course""");
        System.out.print("Your choice: ");
        String choice = sc.nextLine();
        checkChoice(sc, choice);
        if (choice.equals("1")){
            findOneStu(stE, sc, sem);
        }
        if (choice.equals("2")){
            findOneCou(stE, sc, sem);
        }
    }

    public static void findOneStu(StudentEnrolment stE, Scanner sc,  String sem){
        String mod = "Student";
        System.out.println("Student ID: ");
        String stuID = sc.nextLine();
        for (Student stuTemp : stE.getSemesterStudent().get(sem)){
            while (!stuTemp.getStudentID().equals(stuID)){
                System.out.println("Cannot find student, please try again");
                System.out.println("Student ID: ");
                stuID = sc.nextLine();
            }
            break;
        }
        stE.findOne(mod, stuID, sem);
        taskPerform(stE, sc,  stuID, sem);
    }

    public static void findOneCou(StudentEnrolment stE, Scanner sc, String sem){
            String mod = "Course";
            System.out.print("Course ID or Name: ");
            String cou = sc.nextLine();
            for (Course couTemp : stE.getSemesterCourses().get(sem)){
                while(!couTemp.getCourseID().equals(cou)){
                    if (couTemp.getCourseName().equals(cou)){
                        break;
                    }
                    System.out.println("Cannot find course, please try again");
                    System.out.print("Course ID or Name: ");
                    cou = sc.nextLine();
                }
                break;
            }
            stE.findOne(mod, cou, sem);

    }



    public static void taskPerform(StudentEnrolment stE, Scanner sc,  String stuID, String sem ){
        System.out.println("""
                - Press '1': Perform tasks
                - Press any: Exit to search""");
        System.out.print("Your choice: ");
        String choice = sc.nextLine();
        if (!choice.equals("1")){
            return;
        }
        taskMenu();
        String option = sc.nextLine();
        checkOption(sc, option);
        while (!option.equals("exit")){
            if(option.equals("1")){
                upDateCourse(stE, sc, stuID, sem);
            }
            if (option.equals("2")){
                dropCourse(stE, sc, stuID, sem);
            }
            if (option.equals("3")){
                enrollStudent(stE, sc, stuID, sem, option);
            }
            if (option.equals("4")){
                upDateStu(stE, sc, stuID);
            }
            if (option.equals("5")){
                removeStu(stE, stuID);
            }
            taskMenu();
            option = sc.nextLine();
            checkOption(sc, option);
        }
    }

//tested
    public static void upDateCourse(StudentEnrolment stE, Scanner sc, String stuID, String sem){
        String choice = "c";
        while (!choice.equals("exit")){
            System.out.println("Input course ID or name, field of data and new data,'exit' to exit");
            System.out.print("Course: ");
            String cou = sc.nextLine();
            if(exit(cou)){
                return;
            }
            for (Enrolment enrolTemp : stE.getEnrolmentList()){
                if (enrolTemp.getStudent().getStudentID().equals(stuID)
                        && enrolTemp.getSemester().equals(sem)){
                    while (!enrolTemp.getCourse().getCourseName().equals(cou)){
                        if (enrolTemp.getCourse().getCourseID().equals(cou)){
                            break;
                        }
                        System.out.println("Cannot find course, please try again");
                        System.out.print("Course: ");
                        cou = sc.nextLine();
                        if (exit(cou)){
                            return;
                        }
                    }
                }
                break;
            }
            System.out.print("Field (ID, Name, Credits): ");
            String field =  sc.nextLine();
            while (!field.equals("ID")){
                if (field.equals("Credits") || field.equals("Name")){
                    break;
                }
                System.out.println("Field is not exist");
                System.out.print("Field (ID, Name, Credits): ");
                field =  sc.nextLine();
                if (exit(field)){
                    return;
                }
            }
            System.out.print("New data: ");
            String data = sc.nextLine();
            stE.updateCourse(stuID, sem, cou, field,data);
            choice = sc.nextLine();
            checkChoiceTask(sc, choice);
//            }
        }
    }

//    tested
    public static void dropCourse(StudentEnrolment stE, Scanner sc, String stuID, String sem){
        String choice = "c";
        while (choice.equals("c")){
            if (exit(choice)) {
                return;
            }
            System.out.println("Enter course ID or name to drop, 'exit' to exit");
            System.out.print("Course: ");
            String cou = sc.nextLine();
            if (exit(cou)) {
                return;
            }
            for (Enrolment enrolTemp : stE.getEnrolmentList()) {
                if (enrolTemp.getStudent().getStudentID().equals(stuID)
                        && enrolTemp.getSemester().equals(sem)) {
                    while (!enrolTemp.getCourse().getCourseName().equals(cou)) {
                        if (exit(cou)) {
                            return;
                        }
                        if (enrolTemp.getCourse().getCourseID().equals(cou)) {
                            break;
                        }
                        System.out.println("Cannot find course, please try again or type 'exit' to exit");
                        System.out.print("Course: ");
                        cou = sc.nextLine();
                        if (exit(cou)) {
                            return;
                        }
                    }
                }
                break;
            }
            stE.dropCourse(stuID, cou, sem);
            choice = sc.nextLine();
            checkChoiceTask(sc, choice);
            }
        }

//tested
    public static void upDateStu(StudentEnrolment stE, Scanner sc, String stuID){
        String choice = "c";
        while (choice.equals("c")) {
            String mod = "Update";
            System.out.println("Enter student ID, Name or DoB and new data to update, 'exit' to exit");
            System.out.print("Field (ID, Name, DoB): ");
            String field = sc.nextLine();
            if (exit(field)) {
                return;
            }
            while (!field.equals("ID")) {
                if (field.equals("Name") || field.equals("DoB")) {
                    break;
                }
                System.out.println("Field is not exist, please try again, 'exit' to exit");
                System.out.print("Field (ID, Name, DoB): ");
                field = sc.nextLine();
                if (exit(field)) {
                    return;
                }
            }
            System.out.print("New data: ");
            String data = sc.nextLine();
            if (exit(data)) {
                return;
            }
            stE.updateStudent(stuID, mod, field, data);
            choice = sc.nextLine();
            checkChoiceTask(sc, choice);
        }
    }

//tested
    public static void removeStu(StudentEnrolment stE, String stuID){
        String mod = "Remove";
        stE.updateStudent(stuID, mod, null, null);
        System.out.println("Removed student successfully");
        System.out.println("Exited");
    }

//tested
    public static boolean exit(String choice){
        if (choice.equals("exit")){
            System.out.println("Exited");
            return true;
        }
        return false;
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
