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

    public ArrayList<Student> getStudentsList(Course course) {
        if (studentInCourse.containsKey(course.getCourseName())){
            studentsList = studentInCourse.get(course.getCourseName());
            return studentsList;
        }
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

    public HashMap addSemesterCourses(String semStr, Course course){
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
            for (Course semCours : semCourses) {
                if (semCours.equals(course)) {
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

    public HashMap addStudentToCourse(String inputCou, String inputStu){
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
        for (Course couTemp : courseList) {
            if (couTemp.getCourseID().equals(inputCou) || couTemp.getCourseName().equals(inputCou)) {
                if (notFound) {
                    System.out.println("Can not find student.");
                }
                ArrayList<Student> stuCou = studentInCourse.get(couTemp.getCourseName());
                for (Student stu : stuCou) {
                    if (stu.getStudentName().equals(inputStu) || stu.getStudentID().equals(inputStu)) {
                        System.out.println("This student is already in the list.");
                        return studentInCourse;
                    }
                    break;
                }
                stuCou.add(studentsList.get(indexStudent));
                studentInCourse.put(couTemp.getCourseName(), stuCou);
                System.out.println("Added student to course successfully");
                return studentInCourse;
            }
            System.out.println("This course is not available");
            return studentInCourse;
        }
        return null;
    }

    public boolean find(String inputMod, String inputField) {
        if (inputMod.equals("Student") || inputMod.equals("student") || inputMod.equals("STUDENT")){
            for (Student stuTemp : studentsList){
                if(stuTemp.getStudentID().equals(inputField) || stuTemp.getStudentName().equals(inputField)){
                    System.out.println(stuTemp);
                    return true;
                }
                System.out.println("Cannot find student");
                return false;
            }
        }
        if (inputMod.equals("Course") || inputMod.equals("course") || inputMod.equals("COURSE")){
            for (Course couTemp : courseList){
                if(couTemp.getCourseName().equals(inputField) || couTemp.getCourseID().equals(inputField)){
                    System.out.println(couTemp);
                    return true;
                }
                System.out.println("Cannot find course");
                return false;
            }
        }
        return false;
    }

    public boolean dropCourse(String inputCou, String inputStu){
        String couName = null;
        for( Course couTemp : courseList){
            if (couTemp.getCourseName().equals(inputCou)){
                couName =  inputCou;
                break;
            }
        }
        if (studentInCourse.containsKey(couName)){
            // Need answer
            ArrayList<Student> stuTempList = studentInCourse.get(couName);
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
}
