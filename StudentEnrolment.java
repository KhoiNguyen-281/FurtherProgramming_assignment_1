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
    private HashMap<String, ArrayList<Course>> semesterCourses;
    private HashMap<String, ArrayList<String>> semCourse;
    private HashSet<Enrolment> enrolmentList;
    private HashMap<String, ArrayList<Student>> studentInCourse;

    public StudentEnrolment() {
        this.semesterCourses = new HashMap<String, ArrayList<Course>>();
        this.enrolmentList = new HashSet<Enrolment>();
        this.semesters = new ArrayList<String>();
        this.studentsList = new ArrayList<Student>();
        this.courseList = new ArrayList<Course>();
        this.semStr = null;
        this.studentInCourse = new HashMap<String, ArrayList<Student>>();
        this.semCourse = new HashMap<String, ArrayList<String>>();
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

    public ArrayList<Student> getStudentsList() {
        return studentsList;
    }

    public HashSet<Enrolment> getEnrolmentList() {
        return enrolmentList;
    }

    public HashMap<String, ArrayList<Course>> getSemesterCourses() {
        return semesterCourses;
    }

    public HashMap<String, ArrayList<String>> getSemCourse() {
        return semCourse;
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

    public HashMap<String, ArrayList<Student>> addStudentToCourse(String inputCou, String inputStu){
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
                    System.out.println("Course is not available.");
                }
                ArrayList<Student> stuCou = studentInCourse.get(couTemp.getCourseName());
                for (Student stu : studentsList) {
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
                return studentInCourse;
            }
            System.out.println("This course is not available");
            return studentInCourse;
        }
        return null;
    }

    public HashSet<Enrolment> addEnrolment(String inputStu, String semStr, String inputCou){
        /* Check if semester in the system
         * check if course in the semester
         * check if student in the list*/
        if (semesterCourses.containsKey(semStr)){
            String stu = null;
            String cou = null;
            String stu_2 = null;
            String cou_2 = null;
            ArrayList<Course> couListTemp = semesterCourses.get(semStr);
            for (Course couTemp : couListTemp){
                if (couTemp.getCourseName().equals(inputCou) || couTemp.getCourseID().equals(inputCou)){
                    cou = couTemp.getCourseID() + " " + couTemp.getCourseName();
                    cou_2 = couTemp.getCourseName();
                    break;
                }
                System.out.println("Course not found");
            }
            for (Student stuTemp : studentsList){
                if (stuTemp.getStudentID().equals(inputStu) || stuTemp.getStudentName().equals(inputStu)){
                    stu = stuTemp.getStudentID() + " " + stuTemp.getStudentName();
                    stu_2 = stuTemp.getStudentName();
                    break;
                }
                System.out.println("Cannot find student");
            }
            enrolment = new Enrolment(stu, semStr, cou);
            enrolmentList.add(enrolment);
            System.out.println("Add enrolment successfully.");
//            addStudentToCourse(cou_2, stu_2);
            return enrolmentList;
        }
        System.out.println("Semester is not in the system");
        return enrolmentList;
    }

    public void findAll(String inputMod){
        if (inputMod.equals("Student") || inputMod.equals("STUDENT") || inputMod.equals("student")){
            for (Student student : studentsList){
                System.out.println("ID: " + student.getStudentID() + "\n" +
                        "Name: " + student.getStudentName() + "\n" +
                        "Enrolled course: " + student.getCoursesList());
            }

        }
        if (inputMod.equals("Course") || inputMod.equals("course") || inputMod.equals("COURSE")) {
            for (Course course : courseList){
               if (studentInCourse.containsKey(course.getCourseName())){
                   ArrayList<Student> stuTemp = studentInCourse.get(course.getCourseName());
                   System.out.println(stuTemp);
               }
            }
//            int indexStudent = 0;
//            String sName  =  null;
//            String sID  =  null;
//            ArrayList<Student> stuTemp = studentInCourse.get(courseList.get(indexCourse).getCourseName());
//            for(int i = 0; i < studentsList.size(); i++) {
//                indexStudent = i;
//
//            }
//            System.out.println("ID: " + courseList.get(indexCourse).getCourseID() + "\n" +
//                    "Name: " + courseList.get(indexCourse).getCourseName() + "\n" +
//                    "Number of credits: " +  courseList.get(indexCourse).getCourseNumOfCre() + "\n" +
//                    "Enrolled student: " + sID + " " + sName + "\n");
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
}
