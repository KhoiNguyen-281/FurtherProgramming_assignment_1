

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
        ArrayList<Course> semCou = null;
        for (String keySem : semesterCourses.keySet())
        if (keySem.equals(sem)){
            semCou = semesterCourses.get(sem);
            return semCou;
        }
        return semCou;
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
    public boolean addStudentToSemester(String semStr, String inputStu){
        if (semesters.contains(semStr)){
            for (Student stuTemp : studentsList)
                if (stuTemp.getStudentID().equals(inputStu)){
                    ArrayList<Student> semStudents = semesterStudent.get(semStr);
                    for (Student stuTemp2 : semStudents)
                        if (stuTemp2.getStudentID().equals(inputStu)) {
                            return false;
                        }
                    semStudents.add(stuTemp);
                    semesterStudent.put(semStr, semStudents);
                    return true;
                }
            return false;
        }
        return false;
    }

    //Create objects students, courses
    //Tested
    public boolean create(String mod, String ID, String name, String value){
        if(mod.equals("Student")){
            for (Student stuTemp : studentsList)
                if (stuTemp.getStudentID().equals(ID)) {
                    System.out.println("Student existed");
                    return false;
                }
                Student student = new Student(ID, name, value);
                addStudentList(student);
                for (String sem : semesterStudent.keySet()){
                    addStudentToSemester(sem, ID);
                }
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

    //Add course to available semester
    //Tested
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



    //Add enrollments to enrollment list
    //Tested
    public ArrayList<Enrolment> addEnrolmentList(Enrolment enrolment){
        for(Enrolment enrolTemp : enrolmentList)
        if(enrolTemp.getStudent().getStudentID().equals(enrolment.getStudent().getStudentID()) &&
        enrolTemp.getCourse().getCourseID().equals(enrolment.getCourse().getCourseID()) &&
        enrolTemp.getSemester().equals(enrolment.getSemester())){
            System.out.println("Enrollment existed");
            return enrolmentList;
        }
        enrolmentList.add(enrolment);
        System.out.println("Add enrolment successfully");
        return enrolmentList;
    }

    public boolean addStu(Student stu, Course cou){
        for (Student stuTemp : cou.getStudentsList())
            if (stuTemp.getStudentID().equals(stu.getStudentID())){
                return false;
            }
        cou.getStudentsList().add(stu);
        stu.getCoursesList().add(cou);
        return true;
    }


    //Enroll students to courses in semesters, add to enrollment list
    //Tested
    public boolean createEnrollment(String inputStu, String inputCou, String semStr){
        for (Student stuTemp : semesterStudent.get(semStr))
        if (stuTemp.getStudentID().equals(inputStu)){
            if(semesterCourses.containsKey(semStr)){
                ArrayList<Course> couListTemp = semesterCourses.get(semStr);
                for (Course couTemp : couListTemp) {
                    if (couTemp.getCourseID().equals(inputCou) || couTemp.getCourseName().equals(inputCou)) {
                        Enrolment enrolment = new Enrolment(stuTemp, couTemp, semStr);
                        addEnrolmentList(enrolment);
                        addStu(stuTemp, couTemp);
                        return true;
                    }
                }
            }
            System.out.println("Semester is not available");
            return false;
        }
        System.out.println("Invalid student ID");
        return false;
    }

    //Find all students, courses in semester.
    //Tested
    public void findAll(String inputMod , String sem){
        String output = "";
        if(inputMod.equals("Student")){
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
        if(inputMod.equals("Course")){
            for (Course couTemp : semesterCourses.get(sem)){
                output += "\n" + "Semester: " + sem + "\n"
                        + couTemp.toString() +
                        "Enrollment history: " + "\n";
                for (Student stuTemp : couTemp.getStudentsList()) {
                    output += stuTemp.toString();
                }
            }
            System.out.println(output);
        }
    }

    //Find student with student ID, course with course ID
    //Tested
    public void findOne(String inputMod, String ID, String sem){
        String output = "";
        if (inputMod.equals("Student")){
            for (Student stuTemp : semesterStudent.get(sem)) {
                if (stuTemp.getStudentID().equals(ID)) {
                    output += "\n" + stuTemp.toString() +
                            "Enrolled course: " + "\n";
                    for (Course couTemp : semesterCourses.get(sem)) {
                        output += couTemp.toString();
                    }
                }
            }
            System.out.println(output);
        }
        if(inputMod.equals("Course")){
            for (Course couTemp : semesterCourses.get(sem)) {
                if (couTemp.getCourseID().equals(ID)) {
                    output += "\n" + couTemp.toString() +
                            "Enrolled student: " + "\n";
                    for (Student stuTemp : semesterStudent.get(sem)) {
                        output += stuTemp.toString();
                    }
                }
            }
            System.out.println(output);
        }
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
            }
        }
    }

    //Update student, course with ID
    //Tested
    public void updateCourse(String stuID, String sem, String couIn, String field, String change ) {
        for (Student stuTemp : semesterStudent.get(sem))
        if(stuTemp.getStudentID().equals(stuID)){
            for (Course couTemp : stuTemp.getCoursesList())
            if (couTemp.getCourseID().equals(couIn) || couTemp.getCourseName().equals(couIn)){
                couTemp.update(field, change);
                System.out.println("Update course successfully");
                System.out.println(couTemp);
            }
        }
    }

    public void updateStudent(String stuID, String mod, String field, String change){
        for (Student stuTemp : studentsList)
        if (stuTemp.getStudentID().equals(stuID)){
            if (mod.equals("Update")){
                stuTemp.update(field, change);
                System.out.println(stuTemp);
                return;
            }
            if (mod.equals("Remove")){
                studentsList.remove(stuTemp);
                System.out.println(studentsList);
                return;
            }
        }
    }

}
