import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.time.Year;

public class StudentEnrolment{
    private static StudentEnrolment studentE = null;
    private Enrolment enrolment;
    private String semStr;
    private ArrayList<Student> studentsList;
    private ArrayList<Course> courseList;
    private ArrayList<String> semesters;
    private ArrayList<Enrolment> enrolmentList;
    private HashMap<String, ArrayList<Course>> semesterCourses;
    private HashMap<String, ArrayList<Student>> studentInCourse;

    public StudentEnrolment() {
        this.semesterCourses = new HashMap<String, ArrayList<Course>>();
        this.enrolmentList = new ArrayList<>();
        this.semesters = new ArrayList<String>();
        this.studentsList = new ArrayList<Student>();
        this.courseList = new ArrayList<Course>();
        this.semStr = null;
        this.studentInCourse = new HashMap<String, ArrayList<Student>>();
    }
    public void resetEnrolment(){
        studentE = null;
    }

    public static StudentEnrolment getStudentE() {
        return studentE;
    }

    public Enrolment getEnrolment() {
        return enrolment;
    }

    public String getSemStr() {
        return semStr;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public ArrayList<String> getSemesters() {
        return semesters;
    }

    public ArrayList<Enrolment> getEnrolmentList() {
        return enrolmentList;
    }

    public ArrayList<Student> getStudentsList() {
        return studentsList;
    }

    public HashMap<String, ArrayList<Course>> getSemesterCourses() {
        return semesterCourses;
    }

    public HashMap<String, ArrayList<Student>> getStudentInCourse() {
        return studentInCourse;
    }

    public static StudentEnrolment getInstance(){
        if (studentE == null){
            studentE = new StudentEnrolment();
        }
        return studentE;
    }

    public void setSemStr(String semester) {
        int year = Year.now().getValue();
        this.semStr = String.valueOf(year) + semester;
    }

    public ArrayList<String> addSemester(String semester){
        setSemStr(semester);
        if (semesters.contains(semStr)){
            System.out.println("This semester is already added.");
            return semesters;
        }
        semesters.add(semStr);
        ArrayList<Course> semCourses = new ArrayList<Course>();
        semesterCourses.put(semStr, semCourses);
        System.out.println("Added semester successfully");
        return semesters;
    }

    public ArrayList<Course> addCourseList(Course course){
        if (courseList.contains(course)){
            System.out.println("This course is already in the list");
            return courseList;
        }
        courseList.add(course);
        ArrayList<Student> stuInCourse = new ArrayList<Student>();
        studentInCourse.put(course.getCourseName(), stuInCourse);
        return courseList;
    }

    public ArrayList<Student> addStudentList(Student student){
        if (studentsList.contains(student)){
            System.out.println("This student is already in the system.");
            return studentsList;
        }
        studentsList.add(student);
        return studentsList;
    }

    public HashMap<String, ArrayList<Course>> addSemesterCourses(String semStr, String course){
        boolean notFound = true;
        int indexCourse = 0;
        for (int i = 0; i< courseList.size(); i++){
            Course temp = courseList.get(i);
            if(temp.getCourseName().equals(course)){
                notFound = false;
                indexCourse = i;
                break;
            }
        }
        if (semesters.contains(semStr)){
            if (notFound){
                System.out.println("This semester is not in the system.");
            }
            ArrayList<Course> semCourses = semesterCourses.get(semStr);
            for (Course semCours : semCourses) {
                if (semCours.getCourseName().equals(course)) {
                    System.out.println("This course is already in system.");
                    break;
                }

            }
            semCourses.add(courseList.get(indexCourse));
            semesterCourses.put(semStr, semCourses);
            System.out.println("Added course to semester successfully");
            return semesterCourses;
        }
        System.out.println("This semester is not available");
        return semesterCourses;
    }

    public HashMap<String, ArrayList<Student>> addEnrolment(String semStr, String inputCou, String inputStu){
        if (semesterCourses.containsKey(semStr)){
            boolean notFound = true;
            int indexStudent = 0;
            for (int i = 0; i < studentsList.size(); i++){
                Student stuTemp = studentsList.get(i);
                if (stuTemp.getStudentID().equals(inputStu) || stuTemp.getStudentName().equals(inputStu)){
                    notFound = false;
                    indexStudent = i;
                    break;
                }
            }
            for (Course couTemp : semesterCourses.get(semStr)) {
                if (couTemp.getCourseID().equals(inputCou) || couTemp.getCourseName().equals(inputCou)) {
                    if (notFound) {
                        System.out.println("Course is not available.");
                    }
                    ArrayList<Student> stuCou = studentInCourse.get(couTemp.getCourseName());
                    for (Student stu : stuCou) {
                        String stuName = stu.getStudentName();
                        String stuID = stu.getStudentID();
                        if (stuName.equals(inputStu) || stuID.equals(inputStu)) {
                            System.out.println("This student is already in the list.");
                            break;
                        }
                    }
                    stuCou.add(studentsList.get(indexStudent));
                    studentsList.get(indexStudent).getCoursesList().add(couTemp);
                    studentInCourse.put(couTemp.getCourseName(), stuCou);
                    System.out.println("Added student to course successfully");
                    enrolment = new Enrolment(semStr, inputCou, inputStu);
                    enrolmentList.add(enrolment);
                    return studentInCourse;
                }
                System.out.println("This course is not available");
            }
            System.out.println("Invalid semester.");
        }
        return null;
    }

    public void findAll(String inputMod){
        if (inputMod.equals("Student") || inputMod.equals("STUDENT") || inputMod.equals("student")){
            for (Student student : studentsList){
                System.out.println("ID: " + student.getStudentID() + "\n" +
                        "Name: " + student.getStudentName() + "\n" +
                        "Enrolled course: " + student.getCoursesList() + "\n" + "\n");
            }
        }
        if (inputMod.equals("Course") || inputMod.equals("course") || inputMod.equals("COURSE")) {
            String printCourse = "";
            String courseName = null;
            for (Course course : courseList){
                printCourse += "ID: " + course.getCourseID() + "\n" +
                        "Name: " + course.getCourseName() + "\n" +
                        "Credits: " + course.getCourseNumOfCre() + "\n" +
                        "Enrolled students: " + "\n";
                courseName = course.getCourseName();
                ArrayList<Student> students = studentInCourse.get(courseName);
                ArrayList<String> studentInCou = new ArrayList<>();
                String studentString = "";
                for (Student student : students){
                    studentString += student.getStudentID() + " " + student.getStudentName() + ", ";
                }
                studentInCou.add(studentString);
                printCourse += studentInCou + "\n" + "\n";
            }
            System.out.println(printCourse);
        }
    }

    public void findOne(String inputMod, String input){
        if(inputMod.equals("Student") || inputMod.equals("student") || inputMod.equals("STUDENT")){
            for (Student student : studentsList){
                if (student.getStudentName().equals(input) || student.getStudentID().equals(input) ){
                    System.out.println("ID: " + student.getStudentID() + "\n" +
                            "Name: " + student.getStudentName() + "\n" +
                            "DoB: " + student.getStudentBD() + "\n" +
                            "Enrolled course: " + student.getCoursesList());
                }
            }
        }
        if(inputMod.equals("Course") || inputMod.equals("course") || inputMod.equals("COURSE")){
            String printCourse =  "";
            String courseName = null;
            for (Course course : courseList){
                if (course.getCourseName().equals(input) || course.getCourseID().equals(input)){
                    printCourse += "ID: " + course.getCourseID() + "\n" +
                            "Name: " + course.getCourseName() + "\n" +
                            "Credits: " + course.getCourseNumOfCre() + "\n" +
                            "Enrolled students: " + "\n";
                    courseName = course.getCourseName();
                    ArrayList<Student> students = studentInCourse.get(courseName);
                    ArrayList<String> studentInCou = new ArrayList<>();
                    String studentString = "";
                    for (Student student : students){
                        studentString += student.getStudentID() + " " + student.getStudentName() + ", ";
                    }
                    studentInCou.add(studentString);
                    printCourse += studentInCou + "\n" + "\n";
                }
            }
            System.out.println(printCourse);
        }
    }

    public boolean dropCourse(String inputCou, String inputStu){
        if (studentInCourse.containsKey(inputCou)){
            // Need answer
            ArrayList<Student> stuTempList = studentInCourse.get(inputCou);
            for (Student stuTemp : stuTempList){
                if (stuTemp.getStudentName().equals(inputStu) || stuTemp.getStudentID().equals(inputStu)){
                    stuTempList.remove(stuTemp);
                    System.out.println("remove student successfully");
                    return true;
                }
                System.out.println("Cannot find student");
                return false;
            }
            System.out.println("Cannot find course");
            return false;
        }
        return false;
    }

    public void updateStuCou(String mod, String ID, String field, String change) {
        if(mod.equals("Student") || mod.equals("student") || mod.equals("STUDENT")){
            for(Student student : studentsList){
                if (student.getStudentID().equals(ID)){
                    student.update(field, change);
                }
            }
        }
        if(mod.equals("course") || mod.equals("Course") || mod.equals("COURSE")){
            for (Course course : courseList){
                if(course.getCourseID().equals(ID)){
                    course.update(field, change);
                }
            }
        }
    }

}
