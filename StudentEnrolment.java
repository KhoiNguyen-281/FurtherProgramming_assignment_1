import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.time.Year;

public class StudentEnrolment{
    private static StudentEnrolment studentE = null;
    private Enrolment enrolment;
    private ArrayList<Student> studentsList;
    private ArrayList<Course> courseList;
    private ArrayList<String> semesters;
    private ArrayList<Enrolment> enrolmentList;
    private HashMap<String, ArrayList<Course>> semesterCourses;
    private HashMap<Course, ArrayList<Student>> studentInCourse;
    private HashMap<Student, HashMap<String, ArrayList<Course>>> studentEnrol;
    private HashMap<String, ArrayList<Course>> semCouOfStu;

    public HashMap<Student, HashMap<String , ArrayList<Course>>> getStudentEnrol() {
        return studentEnrol;
    }

    public StudentEnrolment() {
        this.semesterCourses = new HashMap<>();
        this.enrolmentList = new ArrayList<>();
        this.semesters = new ArrayList<>();
        this.studentsList = new ArrayList<>();
        this.courseList = new ArrayList<>();
        this.studentInCourse = new HashMap();
        this.studentEnrol = new HashMap<>();
        this.semCouOfStu = new HashMap<>();
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

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public ArrayList<String> getSemesters() {
        setSemesters();
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

    public HashMap<Course, ArrayList<Student>> getStudentInCourse() {
        return studentInCourse;
    }

    public HashMap<String, ArrayList<Course>> getSemCouOfStuMap() {
        return semCouOfStu;
    }

    public static StudentEnrolment getInstance(){
        if (studentE == null){
            studentE = new StudentEnrolment();
        }
        return studentE;
    }

    public void setSemesters(){
        String[] semesterList = {"A", "B", "C"};
        int year = Year.now().getValue();
        for (String sem : semesterList) {
            String semStr = year + sem;
            semesters.add(semStr);
            ArrayList<Course> semCourses = new ArrayList<>();
            semesterCourses.put(semStr, semCourses);
            ArrayList<Course> stuCourse = new ArrayList<>();
            semCouOfStu.put(semStr, stuCourse);
        }
    }

    public ArrayList<Course> addCourseList(Course course){
        courseList.add(course);
        System.out.println("Added course successfully");
        ArrayList<Student> stuInCourse = new ArrayList<>();
        studentInCourse.put(course, stuInCourse);
        return courseList;
    }

    public ArrayList<Student> addStudentList(Student student){
        studentsList.add(student);
        System.out.println("Added student successfully");
        return studentsList;
    }

    public boolean create(String mod, String ID, String name, String value){
        if(mod.equals("Student")){
            for (Student stuTemp : studentsList)
                if (stuTemp.getStudentID().equals(ID)) {
                    System.out.println("Student existed");
                    return false;
                }
                Student student = new Student(ID, name, value);
                addStudentList(student);
                return true;
        }
        if(mod.equals("Course")){
            for (Course couTemp : courseList)
                if(couTemp.getCourseID().equals(ID)){
                    System.out.println("Course existed" );
                    return false;
                }
                Course course = new Course(ID, name, Integer.parseInt(value));
                addCourseList(course);
                return true;
        }
        System.out.println("Error, please check input again");
        return false;
    }

    public boolean addSemesterCourses(String semStr, String inputCou){
        if (semesters.contains(semStr)){
            for (Course couTemp : courseList)
                if (couTemp.getCourseID().equals(inputCou) || couTemp.getCourseName().equals(inputCou)){
                    ArrayList<Course> semCourses = semesterCourses.get(semStr);
                    for (Course couTemp2 : semCourses)
                        if (couTemp2.getCourseName().equals(inputCou) || couTemp2.getCourseID().equals(inputCou)) {
                            System.out.println("This course is already in system.");
                            return false;
                        }
                    semCourses.add(couTemp);
                    semesterCourses.put(semStr, semCourses);
                    System.out.println("Added course to semester successfully");
                    return true;
                }
                System.out.println("Course is not available");
                return false;
        }
        System.out.println("Invalid semester.");
        return false;
    }

    public ArrayList<Enrolment> addEnrolmentList(Enrolment enrolment){

        enrolmentList.add(enrolment);
        System.out.println("Add enrolment successfully");
        return enrolmentList;
    }

    public boolean addEnrollment(String inputStu, String inputCou , String semStr){
        for (Student stuTemp : studentsList)
            if (stuTemp.getStudentID().equals(inputStu)){
                if (semesters.contains(semStr)){
                    for (Course couTemp : semesterCourses.get(semStr))
                        if (couTemp.getCourseID().equals(inputCou)){
                            ArrayList<Course> couListTemp = semCouOfStu.get(semStr);
                            for (Course couTemp2 : couListTemp)
                                if ( couTemp2.getCourseID().equals(inputCou) ){
                                    System.out.println("Enrolment existed");
                                    return false;
                                }
                            couListTemp.add(couTemp);
                            semCouOfStu.put(semStr, couListTemp);
                            studentEnrol.put(stuTemp, semCouOfStu);
                            couTemp.getStudentsList().add(stuTemp);
                            studentInCourse.put(couTemp, couTemp.getStudentsList());
                            enrolment = new Enrolment(stuTemp, couTemp, semStr);
                            for (Enrolment enTemp : enrolmentList)
                                if (enTemp.getStudent().equals(enrolment.getStudent()) &&
                                        enTemp.getCourse().equals(enrolment.getCourse()) &&
                                        enTemp.getSemester().equals(enrolment.getSemester())){
                                    System.out.println("Enrollment existed");
                                }
                            addEnrolmentList(enrolment);
                            return true;
                        }
                    System.out.println("Course is not available");
                    return false;
                }
                System.out.println("Semester is not available");
                return false;
            }
        System.out.println("Student is not available");
        return false;
    }

    public void findAll(String inputMod){
        if(inputMod.equals("Student")){
            String outPutStu = "";
            for (Student stuTemp : studentsList){
                outPutStu += "ID: " + stuTemp.getStudentID() + "\n" +
                        "Name: " + stuTemp.getStudentName() + "\n" +
                        "Date of birth: " + stuTemp.getStudentBD() + "\n" +
                        "Enrolled courses: " + "\n";
                if(studentEnrol.containsKey(stuTemp)){
                    HashMap<String, ArrayList<Course>> semCouTemp = studentEnrol.get(stuTemp);
                    Set<String> semString = semCouTemp.keySet();
                    for (String key : semString) {
                        ArrayList<Course> couTempList = semCouTemp.get(key);
                        outPutStu += key + " " + couTempList + "\n";
                    }
                    System.out.println(outPutStu);
                }
            }
        }
        if (inputMod.equals("Course")){
            String outPutCou = "";
            for (Course couTemp : courseList){
                outPutCou += "ID: " + couTemp.getCourseID() + "\n" +
                        "Name: " + couTemp.getCourseName() + "\n" +
                        "Credits: " + couTemp.getCourseNumOfCre() + "\n";
            }
            System.out.println(outPutCou);
        }
    }

    public boolean findOne(String inputMod, String input){
        if(inputMod.equals("Student")){
            String outPutStu = "";
            for (Student stuTemp : studentsList){
                if (stuTemp.getStudentID().equals(input) ){
                    outPutStu += "ID: " + stuTemp.getStudentID() + "\n" +
                            "Name: " + stuTemp.getStudentName() + "\n" +
                            "Date of birth: " + stuTemp.getStudentBD() + "\n" +
                            "Enrolled courses: " + "\n";
                    if(studentEnrol.containsKey(stuTemp)){
                        HashMap<String, ArrayList<Course>> semCouTemp = studentEnrol.get(stuTemp);
                        Set<String> semString = semCouTemp.keySet();
                        for (String key : semString) {
                            ArrayList<Course> couTempList = semCouTemp.get(key);
                            outPutStu += key + " " + couTempList + "\n";
                        }
                        System.out.println(outPutStu);
                        return true;
                    }

                }
                System.out.println("Cannot find student");
                return false;
            }
        }
        if(inputMod.equals("Course") || inputMod.equals("course") || inputMod.equals("COURSE")){
            String courseName = null;
            String outPutCou = "";
                for (Course couTemp : courseList)
                    if(couTemp.getCourseID().equals(input)) {
                        outPutCou += "ID: " + couTemp.getCourseID() + "\n" +
                                "Name: " + couTemp.getCourseName() + "\n" +
                                "Credits: " + couTemp.getCourseNumOfCre() + "\n";
                    }
                System.out.println(outPutCou);
            }
        System.out.println("Cannot find course");
        return false;
    }

    public boolean dropCourse(String inputCou, String inputStu, String inputSem){
        for (Student stuTemp : studentsList)
            if(stuTemp.getStudentID().equals(inputStu)){
                HashMap<String, ArrayList<Course>> semCouTemp = studentEnrol.get(stuTemp);
                for (String semStr : semesters){
                    if (semStr.equals(inputSem)){
                        ArrayList<Course> couListTemp = semCouTemp.get(semStr);
                        for (Course couTemp : couListTemp)
                            if (couTemp.getCourseID().equals(inputCou)){
                                couListTemp.remove(couTemp);
                                System.out.println("Drop course successfully");
                                return true;
                            }
                        System.out.println("Cannot find course.");
                        return false;
                    }
                    System.out.println("Semester is invalid");
                    return false;
                }
            }
        System.out.println("Cannot find student");
        return false;
    }

    public boolean update(String mod, String ID, String field, String change) {
        if(mod.equals("Student") || mod.equals("student") || mod.equals("STUDENT")){
            for(Student student : studentsList)
                if (student.getStudentID().equals(ID)){
                    student.update(field, change);
                    System.out.println("Update successfully");
                    return true;
                }
                System.out.println("Invalid student ID");
                return false;
        }
        if(mod.equals("course") || mod.equals("Course") || mod.equals("COURSE")){
            for (Course course : courseList)
                if(course.getCourseID().equals(ID)){
                    course.update(field, change);
                    System.out.println("Update successfully");
                    return true;
                }
                System.out.println("Invalid course ID");
                return false;
        }
        System.out.println("Error, please check input again");
        return false;
    }


}
