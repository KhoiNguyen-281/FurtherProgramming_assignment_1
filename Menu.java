import java.util.Scanner;

public class Menu {
    public static void main(String[] args){
        menu();
    }
    public static void menu(){
        StudentEnrolment stE  = new StudentEnrolment();
        Scanner sc = new Scanner(System.in);
        System.out.println("Press '1' to start, '2' to end.");
        int choice1 = sc.nextInt();
        while(choice1 == 1){
            System.out.println("Select feature:" + "\n");
            System.out.println("1. Create (Student, Course)" + "\n" +
                    "2. Add course to semesters" + "\n" +
                    "3. Enrol student to courses" + "\n" +
                    "4. Update (course or student)" + "\n" +
                    "5. Drop course" + "\n" +
                    "6. Search (Student or course)");
            int choice2 = sc.nextInt();
                if (choice2 == 1){
                    String mod = null;
                    System.out.println("'1' Student, '2' Course");
                    int temp = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter attributes:");
                    String ID = sc.nextLine();
                    String name = sc.nextLine();
                    String var = sc.nextLine();
                    if (temp == 1){
                        mod = "Student";
                        stE.create(mod, ID, name, var);
                        System.out.println(stE.getStudentsList());
                    }
                    if (temp == 2){
                        mod = "Course";
                        stE.create(mod, ID, name, var);
                    }
                }
            System.out.println("Press '1' to start again, '2' to end");
            choice1 = sc.nextInt();
        }
        System.out.println("Thank you for using the system");
    }

}
