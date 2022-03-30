

import java.util.*;

import java.time.Year;


public class StudentEnrolment{
    private Enrolment enrolment;
    private final ArrayList<Student> studentsList;
    private final ArrayList<Course> courseList;
    private final ArrayList<String> semesters;
    private final ArrayList<Enrolment> enrolmentList;
    private final HashMap<String, ArrayList<Course>> semesterCourses;
    private HashMap<String, HashMap<String, String>> semesterStudent;

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

    //Setter


    public void setEnrolment(Enrolment enrolment) {
        this.enrolment = enrolment;
    }


    public void setSemesterStudent(HashMap<String, HashMap<String, String>> semesterStudent) {
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
            HashMap<Student, Course> couOfStu =  new HashMap<>();
            semesterCourses.put(semStr, semCourses);

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

    //Enroll students to courses in semesters, add to enrollment list
    //Tested
    public boolean createEnrollment(String inputStu, String inputCou, String semStr){
        for (Student stuTemp : studentsList)
        if (stuTemp.getStudentID().equals(inputStu)){
            if(semesterCourses.containsKey(semStr)){
                ArrayList<Course> couListTemp = semesterCourses.get(semStr);
                for (Course couTemp : couListTemp) {
                    if (couTemp.getCourseID().equals(inputCou)) {
                        Enrolment enrolment = new Enrolment(stuTemp, couTemp, semStr);
                        addEnrolmentList(enrolment);
                        System.out.println("Add enrollment successfully");
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

    //Find all students in student list, courses in course list
    //Tested
    public void findAll(String inputMod){
        String output = "";
        if(inputMod.equals("Student")){
            for (Student stuTemp : studentsList){
                output += "ID: " + stuTemp.getStudentID() + "\n" +
                        "Name: " + stuTemp.getStudentName() + "\n" +
                        "Date of birth: " + stuTemp.getStudentBD() + "\n" +
                        "Enrollment history: " + "\n";
                for (Enrolment enrolTemp : enrolmentList)
                    if(enrolTemp.getStudent().getStudentID().equals(stuTemp.getStudentID())) {
                        output += enrolTemp.getSemester() + " " +
                                enrolTemp.getCourse().getCourseID() + " " +
                                enrolTemp.getCourse().getCourseName() + "\n";

                    }
                output += "\n";
            }
            System.out.println(output);
        }
        if(inputMod.equals("Course")){
            for (Course couTemp : courseList){
                output += "\n" + "ID: " + couTemp.getCourseID() + "\n" +
                        "Name: " + couTemp.getCourseName() + "\n" +
                        "Number of credits: " + couTemp.getCourseNumOfCre() + "\n" +
                        "Enrolled students: " +"\n";
                for (String semStrTemp : semesterCourses.keySet()) {
                    ArrayList<Course> couListTemp = semesterCourses.get(semStrTemp);
                    for (Course couTemp2 : couListTemp)
                    if (couTemp2.getCourseID().equals(couTemp.getCourseID())) {
                        for (Enrolment enrolTemp : enrolmentList)
                        if(enrolTemp.getCourse().getCourseID().equals(couTemp.getCourseID())
                                && enrolTemp.getSemester().equals(semStrTemp)) {
                            output +=  semStrTemp + " " + enrolTemp.getStudent().getStudentID() +
                                    " " + enrolTemp.getStudent().getStudentName() + "\n";
                        }
                    }
                }
            }
            System.out.println(output);
        }
    }

    //Find student with student ID, course with course ID
    //Tested
    public void findOne(String inputMod, String ID){
        String output = "";
        boolean isIdExist = false;
        if (inputMod.equals("Student")){
            for (Student stuTemp : studentsList) {
                if (stuTemp.getStudentID().equals(ID)) {
                    isIdExist = true;
                    output += "\n" + "ID: " + stuTemp.getStudentID() + "\n" +
                            "Name: " + stuTemp.getStudentName() + "\n" +
                            "Date of birth: " + stuTemp.getStudentBD() + "\n" +
                            "Enrollment history: " + "\n";
                    for (Enrolment enrolTemp : enrolmentList) {
                        if (enrolTemp.getStudent().getStudentID().equals(stuTemp.getStudentID())) {
                            output += enrolTemp.getSemester() + " " +
                                    enrolTemp.getCourse().getCourseID() + " " +
                                    enrolTemp.getCourse().getCourseName() + "\n";
                        }
                    }
                    break;
                } else {
                    isIdExist = false;
                }
            }
            if (isIdExist){
                System.out.println(output);
            } else {
                System.out.println("Not found");
            }
        }
        if(inputMod.equals("Course")){
            for (Course couTemp : courseList){
                if (couTemp.getCourseID().equals(ID)){
                    isIdExist = true;
                    output += "\n" + "ID: " + couTemp.getCourseID() + "\n" +
                            "Name: " + couTemp.getCourseName() + "\n" +
                            "Number of credits: " + couTemp.getCourseNumOfCre() + "\n" +
                            "Enrolled students: " +"\n";
                    for (String semStrTemp : semesterCourses.keySet()) {
                        ArrayList<Course> couListTemp = semesterCourses.get(semStrTemp);
                        for (Course couTemp2 : couListTemp)
                        if (couTemp2.getCourseID().equals(couTemp.getCourseID())) {
                            for (Enrolment enrolTemp : enrolmentList)
                            if(enrolTemp.getCourse().getCourseID().equals(couTemp.getCourseID())
                                    && enrolTemp.getSemester().equals(semStrTemp)) {
                                output +=  semStrTemp + " " + enrolTemp.getStudent().getStudentID() +
                                        " " + enrolTemp.getStudent().getStudentName() + "\n";
                            }
                        }
                    }
                    break;
                }else {
                    isIdExist = false;
                }
            }
            if (isIdExist){
                System.out.println(output);
            } else {
                System.out.println("Not found");
            }
        }
    }

    //Drop course, delete student enrollment with student ID, course ID or name, semester
    //Tested
    public void dropCourse(String inputStu, String inputCou, String inputSem) {
        int indexEnrol =  0;
        boolean isValid = false;
        for(int i = 0; i < enrolmentList.size(); i++){
            String stuID = enrolmentList.get(i).getStudent().getStudentID();
            String couID = enrolmentList.get(i).getCourse().getCourseID();
            String sem  = enrolmentList.get(i).getSemester();
            if (stuID.equals(inputStu) && couID.equals(inputCou) && sem.equals(inputSem)){
                indexEnrol = i;
                isValid = true;
                break;
            }
            else {
                isValid = false;
            }
        }
        if(isValid){
            enrolmentList.remove(enrolmentList.get(indexEnrol));
            System.out.println("Drop course successfully");
        } else {
            System.out.println("Invalid input");
        }
    }

    //Update student, course with ID
    //Tested
    public boolean update(String mod, String ID, String field, String change) {
        if(mod.equals("Student")){
            for(Student student : studentsList)
                if (student.getStudentID().equals(ID)){
                    System.out.println(student);
                    student.update(field, change);
                    System.out.println("Update successfully");
                    System.out.println(student);
                    return true;
                }
                System.out.println("Invalid student ID");
                return false;
        }
        if(mod.equals("course")){
            for (Course course : courseList)
                if(course.getCourseID().equals(ID)){
                    System.out.println(course);
                    course.update(field, change);
                    System.out.println("Update successfully");
                    System.out.println(course);
                    return true;
                }
                System.out.println("Invalid course ID");
                return false;
        }
        System.out.println("Error, please check input again");
        return false;
    }

    //Delete student in student list with ID, course in course list and semester's course with ID
    //Tested
    public boolean delete(String mod, String ID){
        if (mod.equals("Student")){
            for (Student stuTemp : studentsList)
            if (stuTemp.getStudentID().equals(ID)){
                studentsList.remove(stuTemp);
                System.out.println("Delete student successfully");
                return true;
            }
            System.out.println("Cannot find student");
        }
        if (mod.equals("Course")){
            for (Course couTemp : courseList)
            if (couTemp.getCourseID().equals(ID)){
                for (String keySem : semesterCourses.keySet()){
                    ArrayList<Course> couListTemp = semesterCourses.get(keySem);
                    for (Course couTemp2 : couListTemp)
                    if (couTemp2.getCourseID().equals(ID)){
                        couListTemp.remove(couTemp2);
                        courseList.remove(couTemp);
                        System.out.println("Delete course successfully");
                        return true;
                    }
                }
            }
            System.out.println("Cannot find course");
            return false;
        }
        return false;
    }
}
