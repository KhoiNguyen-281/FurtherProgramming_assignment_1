import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentEnrolment stE = new StudentEnrolment();
        stE.setSemesters();
        stE.createNewData("1", "S001", "Khoi", "121212");
        stE.createNewData("1", "S002", "Khoi", "121212");
        stE.createNewData("1", "S003", "Khoi", "121212");
//
//
        stE.createNewData("2", "C001", "Further", "12");
        stE.createNewData("2", "C002", "Building", "12");
        stE.createNewData("2", "C003", "Software", "12");

        stE.addSemesterCourses("C001", "2022A");
        stE.addSemesterCourses("C002", "2022A");

        stE.createEnrollment("S001", "C001", "2022A");
        stE.createEnrollment("S001", "C002", "2022A");
//
//        createNew(stE, sc);
//        String input = sc.nextLine();
//        ArrayList<Student> stuList = stE.getSemesterStudent().get("2022A");
//        showCurrentStudent(stE, stuList);
//        findStudentInSem(stE, sc);
//        findAllStudentIn1Sem(stE, sc);
        findAllCourseIn1SemInCluStu(stE, sc);
//        ArrayList<Student> stuList  = stE.getStudentsList();
//        checkStudentAvailable(sc, input, stuList);
//        ArrayList<String> courses = new ArrayList<>();
//        showSemester(stE, courses);
//
//        String choice = sc.nextLine();
//        ArrayList<Course> couList = stE.getCourseList();
////        ArrayList<Course> couListSem = stE.getCoursesOfSemester(choice);
//        ArrayList<Course> couListSem = stE.getSemesterCourses().get(choice);
////        showSemesterCourse(couList,  couListSem);
//        String input = "";
//        checkIfCourseValidToAdd(sc, input, couList, couListSem);
//        findCoursesOfSem(stE, sc);

    }

//    1. Create new student, course
    //Check if input ID is valid to create new object
    //Tested
    public static void checkIfIDExist(StudentEnrolment stE, Scanner sc, String choice) {
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
                    break;
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
                    break;
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
    public static void inputNewData(Scanner sc, String name, String val, String choice) {
        System.out.print("Name: ");
        name = sc.nextLine();
        if (name.equalsIgnoreCase("exit")) {
            return;
        }
        if (choice.equals("1")) {
            System.out.print("Date of birth: ");
            val = sc.nextLine();
            if (val.equalsIgnoreCase("exit")) {
                return;
            }
        }
        if (choice.equals("2")) {
            System.out.print("Number of credits: ");
            val = sc.nextLine();
            if (val.equalsIgnoreCase("exit")) {
                return;
            }
        }
    }

    //Method check if user choice is valid
    //Tested
    public static void checkChoice(Scanner sc, String choice) {
        if (choice.equalsIgnoreCase("exit")) {
            return;
        }
        while (!choice.equals("1")) {
            if (choice.equals("2")) {
                break;
            }
            System.out.println("Invalid input, please try again or type 'exit' to back");
            System.out.print("Input: ");
            choice = sc.nextLine();
            if (choice.equalsIgnoreCase("exit")) {
                return;
            }
        }
    }

    //Method to create student and course
    //Tested
    public static void createNew(StudentEnrolment stE, Scanner sc) {
        System.out.println("---Create student and course---");
        System.out.println("""
                +Press '1': Create student
                +Press '2': Create course
                +Type 'exit': Exit to menu page""");
        System.out.print("Input: ");
        String choice = sc.nextLine();
        checkChoice(sc, choice);
        while (!choice.equalsIgnoreCase("exit")) {
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
//----------------------------------------------------------------------------------------
//   2. Find course of semester
    //Option for user to choose
    public static void choice() {
        System.out.println("""
                +Press '1': Add course to semester
                +Press '2': Remove course of semester
                +Type 'exit': Back to select semester""");
        System.out.print("Your choice: ");
    }
    //Method to show all available semesters in the system
    //Tested
    public static void showSemester(StudentEnrolment stE, ArrayList<String> semesterList) {
        String aSem = "";
        for (String sem : stE.getSemesters()) {
            aSem += sem + " ";
            semesterList.add(sem);
        }
        System.out.println("Available semester: ");
        System.out.println(aSem);
    }
    //Method to show all course in semester and available course in course list
    //Tested
    public static void showSemesterCourse(ArrayList<Course> couSem) {
        String couInSem = "";
        for (Course couTemp : couSem) {
            couInSem += couTemp;
        }
        System.out.println("Available course in this semester: ");
        System.out.println(couInSem);

    }
    //Method to show all available course to add
    //Tested
    public static void showCourseList(ArrayList<Course> couList) {
        String couInList = "";
        for (Course couTemp2 : couList) {
            couInList += couTemp2;
        }
        System.out.println("Available course in system: ");
        System.out.println(couInList);
    }

    //Method to check if user choice is valid
    //Tested
    public static void checkChoiceCou(Scanner sc, String choice) {
        while (!choice.equalsIgnoreCase("exit")) {
            if (choice.equals("2")) {
                break;
            }
            if (choice.equalsIgnoreCase("1")) {
                return;
            }
            System.out.println("Invalid input, please try again, 'exit' to back to select semesters");
            System.out.print("Input: ");
            choice = sc.nextLine();
        }
    }

    //Method to check if course is in semester or not
    //Tested
    public static void checkCourseInSem(StudentEnrolment stE, String input, String sem, ArrayList<Course> couListSem) {
        for (Course couTemp : couListSem) {
            if (couTemp.getCourseID().equalsIgnoreCase(input)) {
                System.out.println("Course is existed");
                return;
            }
        }
        stE.addSemesterCourses(input, sem);
    }

    //Method to check if course is available to add
    //Tested
    public static void checkIfCourseValidToAdd(Scanner sc, String input, ArrayList<Course> couList) {
        while (!input.equalsIgnoreCase("exit")) {
            for (Course cou : couList) {
                if (cou.getCourseID().equalsIgnoreCase(input) || cou.getCourseName().equalsIgnoreCase(input)) {
                    return;
                }
            }
            System.out.println("Course is not available, please try again or 'exit' to exit");
            System.out.print("Course ID or name: ");
            input = sc.nextLine();
        }

    }

    //Method to remove course from semester;
    //Tested
    public static void removeCourse(StudentEnrolment stE, Scanner sc, String input, String sem, ArrayList<Course> couInSem) {
        while (!input.equalsIgnoreCase("exit")) {
            for (Course couTemp : couInSem) {
                if (couTemp.getCourseID().equalsIgnoreCase(input)) {
                    stE.removeCourseSem(input, sem);
                    return;
                }
            }
            System.out.println("Course is not available, please try again or 'exit' to exit");
            System.out.print("Course ID or name: ");
            input = sc.nextLine();
        }
    }

    //Main method to add or remove course from semester
    //Tested
    public static void viewCoursesOfSem(StudentEnrolment stE, Scanner sc) {
        System.out.println("---Populate courses of semester---");
        ArrayList<String> semesters = new ArrayList<>();
        showSemester(stE, semesters);
        System.out.println("Input semester you want to see, 'exit' to exit");
        System.out.print("Semester: ");
        String inputSem = sc.nextLine();
        if (inputSem.equalsIgnoreCase("exit")) {
            return;
        }
        for (String sem : semesters) {
            while (!inputSem.equalsIgnoreCase("exit")) {
                if (inputSem.equalsIgnoreCase(sem)) {
                    System.out.println("Valid semester, continue");
                    inputSem = sem;
                    break;
                }
                System.out.println("This semester is not in the system, please input again or type 'exit' to exit");
                System.out.print("Input: ");
                inputSem = sc.nextLine();
            }
            break;
        }
        while (!inputSem.equalsIgnoreCase("exit")) {
            ArrayList<Course> couList = stE.getCourseList();
            ArrayList<Course> couListSem = stE.getSemesterCourses().get(inputSem);
            showSemesterCourse(couListSem);
            showCourseList(couList);
            choice();
            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("exit")) {
                return;
            }
            checkChoiceCou(sc, choice);
            while (!choice.equalsIgnoreCase("exit")) {
                String input2 = "";
                if (choice.equals("1")) {
                    System.out.println("Input course ID or name to add to this semester, 'exit' to exit");
                    System.out.print("Course ID or name: ");
                    input2 = sc.nextLine();
                    checkIfCourseValidToAdd(sc, input2, couList);
                    checkCourseInSem(stE, input2, inputSem, couListSem);

                }
                if (choice.equals("2")) {
                    System.out.println("Input course ID or name to remove, 'exit' to exit");
                    System.out.print("Course ID or name: ");
                    input2 = sc.nextLine();
                    removeCourse(stE, sc, input2, inputSem, couListSem);
                }
                choice();
                choice = sc.nextLine();
                if (choice.equalsIgnoreCase("exit")) {
                    return;
                }
                checkChoiceCou(sc, choice);
            }
        }
    }
//-----------------------------------------------------------------------------------------
//  3. Find 1 student in sem
    //Method to show all available student in semester
    //Tested
    public static void showCurrentStudent(StudentEnrolment stE, ArrayList<Student> stuList) {
        String availableStu = "";
        for (Student stuTemp : stuList) {
            availableStu += stuTemp;
        }
        System.out.println("Available student: ");
        System.out.print(availableStu);
    }
    //Method to check if student is available in system
    //Tested
    public static void checkStudentAvailable(Scanner sc, String input, ArrayList<Student> studentList) {
        while (!input.equalsIgnoreCase("exit")) {
            for (Student stuTemp : studentList) {
                if (stuTemp.getStudentID().equalsIgnoreCase(input)) {
                    System.out.println("Valid student: ");
                    return;
                }
            }
            System.out.println("Cannot find student, please try again or 'exit' to exit");
            System.out.print("Input: ");
            input = sc.nextLine();
        }
    }
    //Method to check if course is available in semester
    //Tested
    public static void checkCouAvailable(Scanner sc, String input, ArrayList<Course> couListSem) {
        while (!input.equalsIgnoreCase("exit")) {
            for (Course cou : couListSem) {
                if (cou.getCourseID().equalsIgnoreCase(input) || cou.getCourseName().equalsIgnoreCase(input)) {
                    return;
                }
            }
            System.out.println("Course is not in the semester, please try again or 'exit' to exit");
            System.out.print("Course ID or name: ");
            input = sc.nextLine();
        }
    }
    //Method to check if student has enrolled course
    //Tested
    public static void checkStuHaveCourse(StudentEnrolment stE, String input, String stuID, String sem, ArrayList<Course> coursOfStu) {
        for (Course couTemp : coursOfStu) {
            if (couTemp.getCourseID().equalsIgnoreCase(input) || couTemp.getCourseName().equalsIgnoreCase(input)) {
                System.out.println("Student has already enrolled this course");
                return;
            }
        }
        stE.createEnrollment(stuID, input, sem);
    }
    //Method to show option after finding student
    //Tested
    public static void choiceOfStu() {
        System.out.println("""
                +Press '1': Enroll new course
                +Press '2': Drop course
                +Press '3': Update student information
                +Type 'exit': Exit to menu""");
        System.out.print("Your choice: ");
    }
    //Method to check if user choice is valid
    //Tested
    public static void checkStuChoice(Scanner sc, String choice) {
        String[] choiceList = {"1", "2", "3"};
        while (!choice.equalsIgnoreCase("exit")) {
            for (String s : choiceList) {
                if (choice.equalsIgnoreCase(s)) {
                    return;
                }
            }
            System.out.println("Invalid input, please try again, 'exit' to exit");
            System.out.print("Your choice: ");
            choice = sc.nextLine();
        }
    }
    //Method to check if input data is valid to enrol
    //Tested
    public static void enrolNewCourse(StudentEnrolment stE,
                                      Scanner sc,String cou ,String stuID, String sem, ArrayList<Student> stuInSemList,
                                      ArrayList<Course> couInSemList) {


        for (Student stuTemp : stE.getSemesterStudent().get(sem)) {
            if (stuTemp.getStudentID().equalsIgnoreCase(stuID)) {
                ArrayList<Course> couOfStu = stuTemp.getCoursesList();
                checkStuHaveCourse(stE, cou, stuID, sem, couOfStu);
                stE.createEnrollment(stuID, cou, sem);
                return;
            }
        }
    }
    //Method to check if input data is valid to drop course
    //Tested
    public static void dropCourse(StudentEnrolment stE, Scanner sc, String stuID, String sem) {
        System.out.println("Input course ID or name to drop, 'exit' to exit");
        System.out.print("Course ID or name: ");
        String cou = sc.nextLine();
        if (cou.equalsIgnoreCase("exit")) {
            return;
        }
        for (Student stuTemp : stE.getSemesterStudent().get(sem)) {
            if (stuTemp.getStudentID().equalsIgnoreCase(stuID)) {
                ArrayList<Course> couOfStu = stuTemp.getCoursesList();
                checkStuHaveCourse(stE, cou, stuID, sem, couOfStu);
                stE.dropCourse(stuID, cou, sem);
                return;
            }
        }
    }
    //Method to check if input is valid to update student information
    //Tested
    public static void upDate(StudentEnrolment stE, Scanner sc, String ID, String field){
        while (!field.equalsIgnoreCase("exit")){
            if (field.equalsIgnoreCase("ID")){
                break;
            }
            if (field.equalsIgnoreCase("Name")){
                break;
            }

                if (field.equalsIgnoreCase("Date of birth")){
                    break;
                }


                if (field.equalsIgnoreCase("Number or credits")){
                    break;
                }
            System.out.println("Invalid input, please try again or 'exit' to exit");
            System.out.print("Input: ");
            field = sc.nextLine();
        }
        System.out.print("New data: ");
        String change = sc.nextLine();

            stE.updateStudent(ID, field, change);

    }
    //Main method to find student and perform tasks
    //Tested
    public static void findStudentInSem(StudentEnrolment stE, Scanner sc) {
        if (stE.getStudentsList().isEmpty()) {
            System.out.println("Student list is empty, please create student first");
            return;
        }
        System.out.println("---Find student function---");
        ArrayList<String> semesters = new ArrayList<>();
        ArrayList<Student> studentList = stE.getStudentsList();
        showSemester(stE, semesters);
        System.out.println("PLease input student ID and semester you want to find, 'exit' to exit");
        System.out.print("Student ID: ");
        String inputStu = sc.nextLine();
        if (inputStu.equalsIgnoreCase("exit")) {
            return;
        }
        checkStudentAvailable(sc, inputStu, studentList);
        System.out.print("Semester: ");
        String inputSem = sc.nextLine();
        if (inputSem.equalsIgnoreCase("exit")) {
            return;
        }
        for (String sem : semesters) {
            while (!inputSem.equalsIgnoreCase("exit")) {
                if (inputSem.equalsIgnoreCase(sem)) {
                    System.out.println("Valid semester, continue");
                    inputSem = sem;
                    break;
                }
                System.out.println("This semester is not in the system, please input again or type 'exit' to exit");
                System.out.print("Input: ");
                inputSem = sc.nextLine();
            }
            break;
        }
        while (!inputSem.equalsIgnoreCase("exit")) {
            ArrayList<Student> stuInSemList = stE.getSemesterStudent().get(inputSem);
//            ArrayList<Course> couInSemList = stE.getSemesterCourses().get(inputSem);
            //Show all student and enrolled course in semester
//            showSemesterCourse(couInSemList);
            stE.findOneStu(inputStu, inputSem);
            choiceOfStu();
            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("exit")) {
                return;
            }
            checkStuChoice(sc, choice);
            while (!choice.equalsIgnoreCase("exit")) {
                if (choice.equals("1")) {
                    ArrayList<Course> couInSemList = stE.getSemesterCourses().get(inputSem);
                    System.out.println("Input course ID or name to enrol");
                    System.out.print("Course ID or name: ");
                    String cou = sc.nextLine();
                    if (cou.equalsIgnoreCase("exit")) {
                        return;
                    }
                    checkCouAvailable(sc, cou, couInSemList);
                    enrolNewCourse(stE, sc, cou, inputStu, inputSem, stuInSemList, couInSemList);
                }
                if (choice.equals("2")) {
                    dropCourse(stE, sc, inputStu, inputSem);
                }
                if (choice.equals("3")){
                    System.out.println("Input field and new data to update, 'exit' to exit");
                    System.out.print("Field (ID, Name, Date of birth): ");
                    String field = sc.nextLine();
                    if (field.equalsIgnoreCase("exit")){
                        return;
                    }
                    upDate(stE, sc, inputStu, field);
                }
                choiceOfStu();
                choice = sc.nextLine();
                if (choice.equalsIgnoreCase("exit")) {
                    return;
                }
                checkStuChoice(sc, choice);
            }
        }

    }
//-----------------------------------------------------------------------------------------
//   4. Find all student in 1 semester
    //Tested
    public static void findAllStudentIn1Sem(StudentEnrolment stE, Scanner sc){
        System.out.println("---View all student in 1 semester---");
        ArrayList<String> semesters = new ArrayList<>();
        showSemester(stE, semesters);
        System.out.println("Input semester to view all student");
        System.out.print("Semester: ");
        String inputSem =  sc.nextLine();
        if (inputSem.equalsIgnoreCase("exit")){
            return;
        }
        for (String s : semesters){
            while (!inputSem.equalsIgnoreCase("exit")){
                if (s.equalsIgnoreCase(inputSem)){
                    inputSem = s;
                    break;
                }
                System.out.println("Cannot find this semester, please try again or 'exit' to exit");
                inputSem = sc.nextLine();
            }
            break;
        }
        stE.findAllStuSem(inputSem);

        System.out.println("Do you want to perform tasks ? (y/n)");
        String yN = sc.nextLine();
        if(yN.equalsIgnoreCase("n")){
            return;
        }
        while (!yN.equalsIgnoreCase("n")){
            if (yN.equalsIgnoreCase("y")){
                break;
            }
            System.out.println("Invalid input, please try again, 'exit' to exit");
            System.out.print("Your choice: ");
            yN = sc.nextLine();
        }
        choiceOfStu();
        String choice =  sc.nextLine();
        if (choice.equalsIgnoreCase("exit")){
            return;
        }
        checkStuChoice(sc, choice);
        while (!choice.equalsIgnoreCase("exit")){
            ArrayList<Student> studentList =  stE.getStudentsList();
            System.out.println("Input student ID, 'exit' to exit");
            System.out.print("Student ID: ");
            String stuID = sc.nextLine();
            checkStudentAvailable(sc, stuID, studentList);
            if (choice.equals("1")){
                ArrayList<Course> couInSemList = stE.getSemesterCourses().get(inputSem);
                System.out.println("Input course ID or name to enroll");
                System.out.print("Course ID or name: ");
                String cou = sc.nextLine();
                if (cou.equalsIgnoreCase("exit")) {
                    return;
                }
                checkCouAvailable(sc, cou, couInSemList);
                enrolNewCourse(stE, sc,cou, stuID, inputSem, studentList, couInSemList);
            }
            if (choice.equals("2")) {
                dropCourse(stE, sc, stuID, inputSem);
            }
            if (choice.equals("3")){
                System.out.println("Input field and new data to update, 'exit' to exit");
                System.out.print("Field (ID, Name, Date of birth): ");
                String field = sc.nextLine();
                if (field.equalsIgnoreCase("exit")){
                    return;
                }
                upDate(stE, sc, stuID, field);
            }
            choiceOfStu();
            choice = sc.nextLine();
            if (choice.equalsIgnoreCase("exit")) {
                return;
            }
            checkStuChoice(sc, choice);
            }
        }
//-----------------------------------------------------------------------------------------
//   5. Find all course in 1 semester
    //Method to show course task after showing
    //Tested
    public static void choiceOfCouIn1Sem(){
        System.out.println("""
                +Press '1': Enroll student to course
                +Press '2': Update course information
                +Press '3': Remove student from course
                +Type 'exit': Exit""");
        System.out.print("Your choice: ");
    }
    //Method to update course information
    //Tested
    public static void upDateCou(StudentEnrolment stE, Scanner sc, String cou, String sem, String field){
        while (!field.equalsIgnoreCase("exit")){
            if (field.equalsIgnoreCase("ID")){
                break;
            }
            if (field.equalsIgnoreCase("Name")){
                break;
            }
            if (field.equalsIgnoreCase("Number of credits")){
                break;
            }
            System.out.println("Field is not exist, please try again, 'exit' to exit");
            System.out.print("Input: ");
            field = sc.nextLine();
        }
        System.out.print("New data: ");
        String change = sc.nextLine();
        stE.updateCourseInfo(cou, sem, field, change);
    }
    //Main method to find all course in 1 sem
    //Tested
    public static void findAllCourseIn1SemInCluStu(StudentEnrolment stE, Scanner sc){
        System.out.println("---Find all course in semester function---");
        ArrayList<String> semesters =  new ArrayList<>();
        showSemester(stE, semesters);
        System.out.println("Input semester to view");
        System.out.print("Semester (2022A): ");
        String inputSem = sc.nextLine();
        if (inputSem.equalsIgnoreCase("exit")){
            return;
        }
        for (String s : semesters){
            while (!inputSem.equalsIgnoreCase("exit")){
                if (s.equalsIgnoreCase(inputSem)){
                    break;
                }
                System.out.println("Cannot find semester, please try again, 'exit' to exit");
                System.out.print("Input: ");
                inputSem = sc.nextLine();
            }
            break;
        }
        if (stE.getCourseList().isEmpty()) {
            System.out.println("Course list is empty, please create course first");
            return;
        }
        stE.findAllCouSem(inputSem);
        System.out.println("Do you want to perform tasks ? (y/n)");
        String yN = sc.nextLine();
        if(yN.equalsIgnoreCase("n")){
            return;
        }
        while (!yN.equalsIgnoreCase("n")){
            if (yN.equalsIgnoreCase("y")){
                break;
            }
            System.out.println("Invalid input, please try again, 'exit' to exit");
            System.out.print("Your choice: ");
            yN = sc.nextLine();
        }
        while (!yN.equalsIgnoreCase("exit")){
            ArrayList<Course> couInSemList = stE.getSemesterCourses().get(inputSem);
            System.out.println("Input course ID or name to perform tasks");
            System.out.print("Course: ");
            String cou =  sc.nextLine();
            if (cou.equalsIgnoreCase("exit")){
                return;
            }
            checkCouAvailable(sc,cou, couInSemList);
            choiceOfCouIn1Sem();
            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("exit")){
                return;
            }
            checkStuChoice(sc, choice);
            while (!choice.equalsIgnoreCase("exit")){
                String stuID = "";
                if (choice.equals("1")){
                    ArrayList<Student> studentList = stE.getStudentsList();
                    System.out.println("Input student ID to enroll, 'exit' to exit");
                    System.out.print("Student ID: ");
                    stuID = sc.nextLine();
                    checkStudentAvailable(sc, stuID, studentList);
                    enrolNewCourse(stE, sc,cou, stuID, inputSem, studentList,couInSemList);
                }
                if (choice.equals("2")){
                    System.out.println("Input field and new data to update, 'exit' to exit");
                    System.out.print("Field (ID, Name, Number of credits): ");
                    String field = sc.nextLine();
                    if (field.equalsIgnoreCase("exit")){
                        return;
                    }
                    upDateCou(stE, sc, cou, inputSem, field);
                }
                if (choice.equals("3")){
                    System.out.println("Input student ID to remove, 'exit' to exit");
                    System.out.print("Student ID: ");
                    stuID = sc.nextLine();
                    stE.removeStu(cou, stuID, inputSem);
                }
                choiceOfCouIn1Sem();
                choice = sc.nextLine();
                if (choice.equalsIgnoreCase("exit")) {
                    return;
                }
                checkStuChoice(sc, choice);
            }
        }
    }
//-----------------------------------------------------------------------------------------
}