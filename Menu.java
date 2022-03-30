import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args){
        menu();
    }
    public static void menu(){
        StudentEnrolment stE  = new StudentEnrolment();
        stE.setSemesters();
        // create student and course object for testing purpose.
        stE.create("Course", "C001", "Further programming", "12");
        stE.create("Course", "C002", "Building IT System", "12");
        stE.create("Course", "C003", "Programming 1", "12");

        stE.create("Student", "S001", "Khoi Nguyen", "28/01/2000");
        stE.create("Student", "S002", "Minh Vu", "12/01/1998");
        stE.create("Student", "S003", "Tuan Nguyen", "26/07/1997");

        //Add course to semester for testing purpose
        stE.addSemesterCourses("2022A","C001");
        stE.addSemesterCourses("2022A","C002");
        stE.addSemesterCourses("2022B","C002");
        stE.addSemesterCourses("2022B","C001");

        //Add enrollment for testing purpose
        stE.createEnrollment("S001", "C001", "2022A");
        stE.createEnrollment("S001", "C002", "2022A");
        stE.createEnrollment("S002", "C001", "2022A");

        //Main while loop to get user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Press '1' to start, '2' to end.");
        int choice1 = sc.nextInt();
        while(choice1 == 1){
            System.out.println("Select feature:");
            System.out.println("1. Create (Student, Course)" + "\n" +
                    "2. Add course to semesters" + "\n" +
                    "3. Create enrollment for student" + "\n" +
                    "4. Update (course or student)" + "\n" +
                    "5. Search (Student or course)" + "\n" +
                    "6. Show all (Student or course)" + "\n" +
                    "7. Exit");
            System.out.print("Your choice: ");
            int choice2 = sc.nextInt();
            String exit = "";
            while (!exit.equals("e")) {
                if (choice2 == 1) {
                    String mod = null;
                    System.out.println("'1' Student, '2' Course");
                    System.out.print("Choice: ");
                    int choice = sc.nextInt();
                    sc.nextLine();
                    System.out.print("ID: ");
                    String ID = sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    String var = null;
                    if (choice == 1) {
                        mod = "Student";
                        System.out.print("Date of birth (dd/mm/yyyy) : ");
                        var = sc.nextLine();
                        stE.create(mod, ID, name, var);
                    }
                    if (choice == 2) {
                        mod = "Course";
                        System.out.print("Number of credits: ");
                        var = sc.nextLine();
                        stE.create(mod, ID, name, var);
                    }
                }
                if (choice2 == 2) {
                    System.out.println("Available semesters: " + stE.getSemesters());
                    System.out.println("Available courses: " + stE.getCourseList());
                    System.out.print("Semester (format 2022X ): ");
                    String semester = sc.next();
                    sc.nextLine();
                    System.out.print("Course ID or name: ");
                    String couTemp = sc.nextLine();
                    stE.addSemesterCourses(semester, couTemp);
                }
                if (choice2 == 3) {
                    System.out.print("Enter semester: ");
                    String semStr = sc.next();
                    sc.nextLine();
                    System.out.println("Available courses: " + stE.getCoursesOfSemester(semStr));
                    System.out.println("Enter student ID and course ID or course name to enroll");
                    System.out.print("Student ID: ");
                    String stuID = sc.nextLine();
                    System.out.print("Course ID or name: ");
                    String couTemp = sc.nextLine();
                    stE.createEnrollment(stuID, couTemp, semStr);
                }
                if (choice2 == 4) {
                    String mod = null;
                    System.out.println("'1' to choose student, '2' to choose course");
                    System.out.print("Choice: ");
                    int choice = sc.nextInt();
                    sc.nextLine();
                    System.out.print("ID: ");
                    String ID = sc.nextLine();
                    String field = null;
                    String change = null;
                    if(choice ==  1){
                        mod = "Student";
                        System.out.print("Field (name or date of birth): ");
                        field = sc.nextLine();
                        System.out.print("Changed content: ");
                        change = sc.nextLine();
                        stE.update(mod, ID, field, change);
                    }
                    if(choice ==  2){
                        mod = "Course";
                        System.out.print("Field (name or credits): ");
                        field = sc.nextLine();
                        System.out.print("Changed content: ");
                        change =sc.nextLine();
                        stE.update(mod, ID, field, change);
                    }
                }
//                if (choice2 == 5) {
//                    System.out.println("Enter student ID, Course ID and semester:");
//                    sc.nextLine();
//                    System.out.print("Course ID: ");
//                    String couID = sc.nextLine();
//                    System.out.print("Student ID: ");
//                    String stuID  = sc.nextLine();
//                    System.out.print("Semester format (2022X):  ");
//                    String sem = sc.nextLine();
//                    stE.dropCourse(couID, stuID, sem);
//                }
                if (choice2 == 5) {
                    System.out.println("'1' to search student,'2' to search course");
                    int choice = sc.nextInt();
                    sc.nextLine();
                    System.out.print("ID: ");
                    String ID = sc.nextLine();
                    String mod = null;
                    if (choice ==  1){
                        mod = "Student";
                        stE.findOne(mod, ID);
                        System.out.println("press 'y' if you want to add or delete, 'e' to return to search");
                        System.out.print("Choice: ");
                        String choice3 = sc.nextLine();
                        while (!choice3.equals("e")){
                            System.out.println("'1' to delete, '2' to add");
                            String choiceStu = sc.nextLine();
                            if (choiceStu.equals("1")){
                                System.out.println("Enter course ID of semester you want to delete");
                                System.out.print("Course ID: ");
                                String couID  = sc.nextLine();
                                System.out.print("Semester: ");
                                String sem = sc.nextLine();
                                stE.dropCourse(ID, couID, sem);
                                stE.findOne(mod, ID);
                            }
                            if (choiceStu.equals("2")){
                                System.out.print("Semester: ");
                                String sem = sc.nextLine();
                                System.out.println("Available courses: " + stE.getCoursesOfSemester(sem));
                                System.out.println("Enter course ID and semester to add");
                                System.out.print("Course ID: ");
                                String couID = sc.nextLine();
                                stE.createEnrollment(ID, couID, sem);
                                stE.findOne(mod, ID);
                            }
                            System.out.println("press 'e' to exit, any key to continue");
                            choice3 = sc.nextLine();
                        }
                    }
                    if (choice ==  2){
                        mod = "Course";
                        stE.findOne(mod, ID);
                    }
                }
                if (choice2 == 6) {
                    System.out.println("'1' to show students, '2' to show courses");
                    int choice = sc.nextInt();
                    sc.nextLine();
                    String mod = null;
                    if (choice == 1){
                        mod = "Student";
                        stE.findAll(mod);
                    }
                    if (choice == 2){
                        mod = "Course";
                        stE.findAll(mod);
                    }
                }
                if (choice2 == 7){
                    break;
                }
                System.out.println("Press any key to continue, 'e' to exit");
                exit = sc.nextLine();
            }
            System.out.println("Press '1' to start, '2' to end.");
            choice1 = sc.nextInt();
        }
        System.out.println("Thank you for using the system");
    }
}
