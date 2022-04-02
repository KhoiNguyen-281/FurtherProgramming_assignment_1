

import javax.swing.*;
import java.util.*;

import java.time.Year;


public class StudentEnrolment{
    private Enrolment enrolment;
    private final ArrayList<Student> studentsList;
    private final ArrayList<Course> courseList;
    private final ArrayList<String> semesters;
    private final ArrayList<Enrolment> enrolmentList;
    private final HashMap<String, ArrayList<Course>> semesterCourses;
    private HashMap<String, ArrayList<Student>> semesterStudent;

    //Constructor
    public StudentEnrolment() {
        this.semesterCourses = new HashMap<>();
        this.enrolmentList = new ArrayList<>();
        this.semesters = new ArrayList<>();
        this.studentsList = new ArrayList<>();
        this.courseList = new ArrayList<>();
        this.semesterStudent = new HashMap<>();
    }

    //Getter
    public Enrolment getEnrolment() {
        return enrolment;
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

    public HashMap<String, ArrayList<Student>> getSemesterStudent() {
        return semesterStudent;
    }

    //Setter
    public void setEnrolment(Enrolment enrolment) {
        this.enrolment = enrolment;
    }

    public void setSemesterStudent(HashMap<String, ArrayList<Student>> semesterStudent) {
        this.semesterStudent = semesterStudent;
    }

    //Create semester and add to semester list
    //Tested
    public void setSemesters(){
        String[] semesterList = {"A", "B", "C"};
        int year = Year.now().getValue();
        for (String sem : semesterList) {
            String semStr = year + sem;
            semesters.add(semStr);
            ArrayList<Course> semCourses = new ArrayList<>();
            ArrayList<Student> semStudents = new ArrayList<>();
            semesterCourses.put(semStr, semCourses);
            semesterStudent.put(semStr, semStudents);
        }
    }

    //Initialized methods
    public ArrayList<Course> getCoursesOfSemester(String sem){
        return semesterCourses.get(sem);
    }

    //Add courses to course list
    //Tested
    public ArrayList<Course> addCourseList(Course course){
        courseList.add(course);
        System.out.println("Added course successfully");
        return courseList;
    }

    //Add students to student list
    //Tested
    public ArrayList<Student> addStudentList(Student student){
        studentsList.add(student);
        System.out.println("Added student successfully");
        return studentsList;
    }

    //Add students to semester
    //Tested
    public void addStudentToSemester(String semStr, String inputStu){
        int indexStu = 0;
        ArrayList<Student> stuSemTemp =  semesterStudent.get(semStr);
        for (int i = 0; i < studentsList.size(); i++){
            if (studentsList.get(i).getStudentID().equals(inputStu)){
                indexStu = i;
                break;
            }
        }
        stuSemTemp.add(studentsList.get(indexStu));
        semesterStudent.put(semStr, stuSemTemp);
//        if (semesters.contains(semStr)){
//            for (Student stuTemp : studentsList)
//                if (stuTemp.getStudentID().equals(inputStu)){
//                    ArrayList<Student> semStudents = semesterStudent.get(semStr);
//                    for (Student stuTemp2 : semStudents)
//                        if (stuTemp2.getStudentID().equals(inputStu)) {
//                            return false;
//                        }
//                    semStudents.add(stuTemp);
//                    semesterStudent.put(semStr, semStudents);
//                    return true;
//                }
//            return false;
//        }
//        return false;
    }

    //Create objects students, courses
    //Tested
//    public boolean create(String mod, String ID, String name, String value){
//        if(mod.equals("Student")){
//            for (Student stuTemp : studentsList)
//                if (stuTemp.getStudentID().equals(ID)) {
//                    System.out.println("Student existed");
//                    return false;
//                }
//                Student student = new Student(ID, name, value);
//                addStudentList(student);
//                for (String sem : semesterStudent.keySet()){
//                    addStudentToSemester(sem, ID);
//                }
//                return true;
//        }
//        if(mod.equals("Course")){
//            for (Course couTemp : courseList)
//                if(couTemp.getCourseID().equals(ID)){
//                    System.out.println("Course existed" );
//                    return false;
//                }
//                Course course = new Course(ID, name, Integer.parseInt(value));
//                addCourseList(course);
//                return true;
//        }
//        System.out.println("Error, please check input again");
//        return false;
//    }
    public void createStu(String ID, String name, String val){
        Student student = new Student(ID, name, val);
        addStudentList(student);
        for (String semTemp : semesters)
        addStudentToSemester(semTemp, ID);
    }

    public void createCou(String ID, String name, String val){
        Course course  = new Course(ID, name, Integer.parseInt(val));
        addCourseList(course);
    }

    //Add course to available semester
    //Tested
    public void addSemesterCourses( String inputCou, String semStr){
        int indexCou =  0;
        for (int i = 0; i < courseList.size(); i++){
            if (courseList.get(i).getCourseName().equals(inputCou)
                    || courseList.get(i).getCourseID().equals(inputCou)){
                indexCou = i;
                break;
            }
        }
        for (String semTemp : semesters){
            if (semTemp.equals(semStr)){
                ArrayList<Course> couListTemp = semesterCourses.get(semStr);
                for (Course couTemp : couListTemp){
                    if (couTemp.getCourseID().equals(inputCou)){
                        System.out.println("System works");
                        return;
                    }
                    break;
                }
                couListTemp.add(courseList.get(indexCou));
                semesterCourses.put(semStr, couListTemp);
                System.out.println("Add course to semester successfully." + "\n");
                return;
            }
        }
    }


    //Add enrollments to enrollment list
    //Tested
    public ArrayList<Enrolment> addEnrolmentList(Enrolment enrolment){
        for(Enrolment enrolTemp : enrolmentList)
        if(enrolTemp.getStudent().equals(enrolment.getStudent()) &&
        enrolTemp.getCourse().equals(enrolment.getCourse()) &&
        enrolTemp.getSemester().equals(enrolment.getSemester())){
            System.out.println("Enrollment existed");
            return enrolmentList;
        }
        enrolmentList.add(enrolment);
        System.out.println("Add enrolment successfully");
        return enrolmentList;
    }

    public boolean addStu(Student stu, Course cou){
        cou.getStudentsList().add(stu);
        stu.getCoursesList().add(cou);
        return true;
    }


    //Enroll students to courses in semesters, add to enrollment list
    //Tested
    public void createEnrollment(String inputStu, String inputCou, String semStr){
        int indexStu = 0;
        int indexCou = 0;
        for (String semTemp : semesterStudent.keySet()){
            if (semTemp.equals(semStr)){
                ArrayList<Student> stuTempList = semesterStudent.get(semStr);
                ArrayList<Course> couTempList =  semesterCourses.get(semStr);
                for (int i = 0; i < stuTempList.size(); i++){
                    if (stuTempList.get(i).getStudentID().equals(inputStu)){
                        indexStu = i;
                        break;
                    }
                }
                for (int j = 0; j < couTempList.size(); j++){
                    if (couTempList.get(j).getCourseID().equals(inputCou)
                            || couTempList.get(j).getCourseName().equals(inputCou)){
                        indexCou = j;
                        break;
                    }
                }
                break;
            }
        }
        Enrolment enrolment = new Enrolment(studentsList.get(indexStu), courseList.get(indexCou), semStr);
        addEnrolmentList(enrolment);
    }

    //Find all students, courses in semester.
    //Tested
    public void findAllStuSem(String sem){
        String output = "";
        for (Student stuTemp : semesterStudent.get(sem)){
            output += "\n" + "Semester: " + sem + "\n"
                    + stuTemp.toString() +
                    "Enrollment history: " + "\n";
            for (Course couTemp : stuTemp.getCoursesList()) {
                output += couTemp.toString();
            }
        }
        System.out.println(output);
    }

    public void findAllCouSem(String sem){
        String output = "";
        for (Course couTemp : semesterCourses.get(sem)){
            output += "\n" + "Semester: " + sem + "\n"
                    + couTemp.toString() +
                    "Enrolled students: " + "\n";
            for (Student stuTemp : couTemp.getStudentsList()) {
                output += stuTemp.toString();
            }
        }
        System.out.println(output);
    }

    //Find student with student ID, course with course ID
    //Tested
    public void findOneStu(String ID, String sem){
        String output = "";
        for (Student stuTemp : semesterStudent.get(sem)) {
            if (stuTemp.getStudentID().equals(ID)) {
                output += "\n" + stuTemp.toString() +
                        "Enrolled course: " + "\n";
                for (Course couTemp : semesterCourses.get(sem)) {
                    output += couTemp.toString();
                }
                break;
            }
            System.out.println("Student not found");
            break;
        }
        System.out.println(output);
    }

    public void findOneCour(String cou, String sem){
        String output = "";
        for (Course couTemp : semesterCourses.get(sem)) {
            if (couTemp.getCourseID().equals(cou) || couTemp.getCourseName().equals(cou)) {
                output += "\n" + couTemp.toString() +
                        "Enrolled student: " + "\n";
                for (Student stuTemp : semesterStudent.get(sem)) {
                    output += stuTemp.toString();
                    break;
                }
            }
            System.out.println("Student not found");
            break;
        }
        System.out.println(output);
    }
    //Drop course, delete student enrollment with student ID, course ID or name, semester
    //Tested
    public void dropCourse(String inputStu, String inputCou, String inputSem) {
        for (Student stuTemp : semesterStudent.get(inputSem))
        if(stuTemp.getStudentID().equals(inputStu)){
            for (Course couTemp : stuTemp.getCoursesList())
            if (couTemp.getCourseID().equals(inputCou) || couTemp.getCourseName().equals(inputCou)){
                stuTemp.getCoursesList().remove(couTemp);
                System.out.println("Drop course successfully");
                return;
            }
            System.out.println("Course not found");
        }
    }

    //Update student, course with ID
    //Tested
    public void updateCourseInfo(String stuID, String sem, String couIn, String field, String change ) {
        for (Student stuTemp : semesterStudent.get(sem))
        if(stuTemp.getStudentID().equals(stuID)){
            for (Course couTemp : stuTemp.getCoursesList())
            if (couTemp.getCourseID().equals(couIn) || couTemp.getCourseName().equals(couIn)){
                couTemp.update(field, change);
                System.out.println("Update course successfully");
                System.out.println(couTemp);
                return;
            }
        }
    }

    public void updateStudent(String stuID, String field, String change){
        for (Student stuTemp : studentsList)
        if (stuTemp.getStudentID().equals(stuID)){
            stuTemp.update(field, change);
            System.out.println(stuTemp);
            return;
        }
    }
    public void removeStu(String stuID){
        for (Student stuTemp : studentsList)
        if(stuTemp.getStudentID().equals(stuID)) {
            studentsList.remove(stuTemp);
            System.out.println("Student remove successfully");
        }
    }


}
