

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
            if (studentsList.get(i).getStudentID().equalsIgnoreCase(inputStu)){
                indexStu = i;
                break;
            }
        }
        stuSemTemp.add(studentsList.get(indexStu));
        semesterStudent.put(semStr, stuSemTemp);
    }

    //Create objects students, courses
    //Tested
    public void createNewData(String choice, String ID, String name, String val){
        if (choice.equalsIgnoreCase("1")){
            Student student = new Student(ID, name, val);
            addStudentList(student);
            for (String semTemp : semesters)
                addStudentToSemester(semTemp, ID);
        }
        if (choice.equalsIgnoreCase("2")){
            Course course  = new Course(ID, name, Integer.parseInt(val));
            addCourseList(course);
        }
    }
    //Add course to available semester
    //Tested
    public void addSemesterCourses( String inputCou, String semStr){
        int indexCou =  0;
        for (int i = 0; i < courseList.size(); i++){
            if (courseList.get(i).getCourseName().equalsIgnoreCase(inputCou)
                    || courseList.get(i).getCourseID().equalsIgnoreCase(inputCou)){
                indexCou = i;
                break;
            }
        }
        for (String semTemp : semesters){
            if (semTemp.equalsIgnoreCase(semStr)){
                ArrayList<Course> couListTemp = semesterCourses.get(semStr);
                couListTemp.add(courseList.get(indexCou));
                semesterCourses.put(semStr, couListTemp);
                System.out.println("Add course to semester successfully." + "\n");
                return;
            }
        }
    }
    //Remove course in semester
    //Tested
    public void removeCourseSem(String inputCou, String semester){
        for (String s : semesters){
            if (s.equalsIgnoreCase(semester)){
                semester = s;
                break;
            }
        }
        ArrayList<Course> couInSem  =  semesterCourses.get(semester);
        for (Course couTemp : couInSem){
            if (couTemp.getCourseID().equalsIgnoreCase(inputCou)){
                couInSem.remove(couTemp);
                System.out.println("Remove course successfully");
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
            return enrolmentList;
        }
        enrolmentList.add(enrolment);
        System.out.println("Add enrolment successfully");
        return enrolmentList;
    }

    //Method to add student to course's studentList, student's courseList when create enrollment
    //Tested
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
        for (String s : semesters){
            if (s.equalsIgnoreCase(semStr)){
                semStr = s;
                break;
            }
        }
        for (String semTemp : semesterStudent.keySet()){
            if (semTemp.equalsIgnoreCase(semStr)){
                ArrayList<Student> stuTempList = semesterStudent.get(semStr);
                ArrayList<Course> couTempList =  semesterCourses.get(semStr);
                for (int i = 0; i < stuTempList.size(); i++){
                    if (stuTempList.get(i).getStudentID().equalsIgnoreCase(inputStu)){
                        indexStu = i;
                        break;
                    }
                }
                for (int j = 0; j < couTempList.size(); j++){
                    if (couTempList.get(j).getCourseID().equalsIgnoreCase(inputCou)
                            || couTempList.get(j).getCourseName().equalsIgnoreCase(inputCou)){
                        indexCou = j;
                        break;
                    }
                }
                break;
            }
        }
        Enrolment enrolment = new Enrolment(studentsList.get(indexStu), courseList.get(indexCou), semStr);
        addEnrolmentList(enrolment);
        addStu(studentsList.get(indexStu), courseList.get(indexCou));
    }

    //Find student with student ID, course with course ID or name
    //Tested
    public void findOneStu(String ID, String sem){
        String output = "";
        for (String s : semesters){
            if (s.equalsIgnoreCase(sem)){
                sem = s;
                break;
            }
        }
        for (Student stuTemp : semesterStudent.get(sem)) {
            if (stuTemp.getStudentID().equalsIgnoreCase(ID)) {
                output += "\n" + stuTemp.toString() +
                        "Enrolled course: " + "\n";
                for (Course couTemp : stuTemp.getCoursesList()) {
                    output += couTemp.toString();
                }
                break;
            }
        }
        System.out.println(output);
    }

    public void findOneCour(String cou, String sem){
        for (String s : semesters){
            if (s.equalsIgnoreCase(sem)){
                sem = s;
                break;
            }
        }
        String output = "";
        for (Course couTemp : semesterCourses.get(sem)) {
            if (couTemp.getCourseID().equalsIgnoreCase(cou) || couTemp.getCourseName().equalsIgnoreCase(cou)) {
                output += "\n" + couTemp.toString() +
                        "Enrolled student: " + "\n";
                for (Student stuTemp : couTemp.getStudentsList()) {
                    output += stuTemp.toString();
                    break;
                }
            }
        }
        System.out.println(output);
    }
    //Drop course, delete student enrollment with student ID, course ID or name, semester
    //Tested
    public void dropCourse(String inputStu, String inputCou, String inputSem) {
        for (String s : semesters){
            if (s.equalsIgnoreCase(inputSem)){
                inputSem = s;
                break;
            }
        }
        for (Student stuTemp : semesterStudent.get(inputSem))
        if(stuTemp.getStudentID().equalsIgnoreCase(inputStu)){
            for (Course couTemp : stuTemp.getCoursesList())
            if (couTemp.getCourseID().equalsIgnoreCase(inputCou) || couTemp.getCourseName().equalsIgnoreCase(inputCou)){
                stuTemp.getCoursesList().remove(couTemp);
                couTemp.getStudentsList().remove(stuTemp);
                System.out.println("Drop course successfully");
                return;
            }
            System.out.println("Course not found");
        }
    }

    //Update student, course with ID and name
    //Tested
    public void updateCourseInfo(String couIn, String sem,  String field, String change ) {
        for (Course couTemp : semesterCourses.get(sem))
            if (couTemp.getCourseID().equalsIgnoreCase(couIn) || couTemp.getCourseName().equalsIgnoreCase(couIn)){
                couTemp.update(field, change);
                System.out.println("Update course successfully");
                System.out.println(couTemp);
                return;
            }
        }

    public void updateStudent(String stuID, String field, String change){
        for (Student stuTemp : studentsList)
        if (stuTemp.getStudentID().equalsIgnoreCase(stuID)){
            stuTemp.update(field, change);
            System.out.println(stuTemp);
            return;
        }
    }

}
