import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Date;
import java.time.Year;

public class StudentEnrolment{
    private static StudentEnrolment studentE = null;
    private Enrolment enrolment;
    private String semStr;
    private ArrayList<Student> studentsList;
    private ArrayList<Course> courseList;
    private ArrayList<String> semesters;
    private HashMap<String, ArrayList<Course>> semesterCourses;
    private ArrayList<Enrolment> enrolmentList;
    private HashMap<String, ArrayList<Student>> studentInCourse;

    public StudentEnrolment() {
        this.semesterCourses = new HashMap<String, ArrayList<Course>>();
        this.enrolmentList = new ArrayList<Enrolment>();
        this.semesters = new ArrayList<String>();
        this.studentsList = new ArrayList<Student>();
        this.courseList = new ArrayList<Course>();
        this.semStr = null;
        this.studentInCourse = new HashMap<String, ArrayList<Student>>();
    }

    public void resetEnrolment(){
        studentE = null;
    }

    public HashMap<String, ArrayList<Student>> getStudentInCourse() {
        return studentInCourse;
    }

    public static StudentEnrolment getStudentE() {
        return studentE;
    }

    public Enrolment getEnrolment() {
        return enrolment;
    }

    public ArrayList<String> getSemesters() {
        return semesters;
    }

    public ArrayList<Enrolment> getEnrolmentList() {
        return enrolmentList;
    }

    public HashMap<String, ArrayList<Course>> getSemesterCourses() {
        return semesterCourses;
    }

    public ArrayList<Student> getStudentsList() {
        return studentsList;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
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

    public String getSemStr() {
        return semStr;
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

    public HashMap<String, ArrayList<Course>> addSemesterCourses(String semStr, Course course){
        boolean notFound = true;
        int indexCourse = 0;
        for (int i = 0; i< courseList.size(); i++){
            Course temp = courseList.get(i);
            if(temp.equals(course)){
                notFound = false;
                indexCourse = i;
                break;
            }
        }
        if (semesters.contains(semStr)){
            if (notFound){
                System.out.println("This course is not available.");
            }
            ArrayList<Course> semCourses = semesterCourses.get(semStr);
            boolean inSem = false;
            for (Course semCours : semCourses) {
                if (semCours.equals(course)) {
                    System.out.println("This course is already in system.");
                    break;
                }
            }
            semCourses.add(courseList.get(indexCourse));
            semesterCourses.put(semStr, semCourses);
            return semesterCourses;
        }
        System.out.println("This semester is not available");
        return semesterCourses;
    }

    public void addStudentToCourse(Course course, Student student){
        boolean notFound = true;
        int indexStudent = 0;
        for (int i = 0; i < studentsList.size(); i++){
            Student stuTemp = studentsList.get(i);
            if (stuTemp.equals(student)){
                notFound = false;
                indexStudent = i;
                break;
            }
        }
        if (courseList.contains(course)){
            if(notFound){
                System.out.println("Can not find student.");
            }
            ArrayList<Student> stuCou = studentInCourse.get(course.getCourseName());
            boolean inCou = false;
            for (Student stu : stuCou){
                if(stuCou.equals(student)){
                    System.out.println("This student is already in the list.");
                }
                break;
            }
            stuCou.add(studentsList.get(indexStudent));
            studentInCourse.put(course.getCourseName(), stuCou);
        }
        System.out.println("This course is not available");
    }

    public void find(String mod, String input){
        if (mod.equals("student") || mod.equals("Student") || mod.equals("STUDENT")) {
            for (Student value : studentsList) {
                if (value.getStudentName().equals(input)) {
                    System.out.println(value);

                }
                if (value.getStudentID().equals(input)) {
                    System.out.println(value);
                }
            }
        }
        if (mod.equals("course") || mod.equals("Course") || mod.equals("COURSE")) {
            for (Course value : courseList) {
                if (value.getCourseName().equals(input)){
                    System.out.println(value);
                }
                if (value.getCourseID().equals(input)) {
                    System.out.println(value);
                }
            }
        }
        System.out.println("Cannot");
    }

    public boolean findCourse(String input){
        for (Course value : courseList) {
            if (value.getCourseName().equals(input)) {
                System.out.println(value);
                return true;
            }
            if (value.getCourseID().equals(input)) {
                System.out.println(value);
                return true;
            }
        }
        return false;
    }

    public boolean delCourse(Student student, Course course){
        String id =  student.getStudentID();
        String name =  student.getStudentName();
        if (student.getCoursesList().contains(course.getCourseID())){
            student.getCoursesList().remove(course);
            System.out.println("Removed course successfully.");
            return true;
        }
        System.out.println("Course not found.");
        return false;
    }

}
