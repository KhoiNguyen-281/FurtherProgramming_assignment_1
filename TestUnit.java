public class TestUnit {
    public static void main (String[] args){
        StudentEnrolment stE = new StudentEnrolment();

        //Test create semester and add to system
        stE.setSemesters();
        System.out.println(stE.getSemesters());

        //Test create new function and add to list
        //Create student
        stE.createNewData("1", "S001", "Khoi", "29/12/2000");
        stE.createNewData("1", "S002", "Khoa", "27/06/2000");
        stE.createNewData("1", "S003", "Minh", "07/01/2000");
        stE.createNewData("1", "S004", "Duc", "28/01/2000");
        //Create course
        stE.createNewData("2", "C001", "MATH", "6");
        stE.createNewData("2", "C002", "Further programming", "12");
        stE.createNewData("2", "C003", "Building IT System", "12");

        //Test display all course in semester (before add course)
        stE.getSemesterCourses().get("2022A");

        //Test add course to semester
        stE.addSemesterCourses("C001", "2022A");
        stE.addSemesterCourses("Further programming", "2022A");
        stE.addSemesterCourses("C003", "2022A");

        //Test display all course in semester (after add course)
        stE.getSemesterCourses().get("2022A");

        //Test show student in 1 semester (before add course)
        stE.findOneStu("S001", "2022A");

        //Test show course in 1 semester (before add student)
        stE.findOneCour("C001", "2022A");

        stE.createEnrollment("S001", "C001", "2022A");
        stE.createEnrollment("S001", "Further programming", "2022A");

        //Test show student in 1 semester (after add course)
        stE.findOneStu("S001", "2022A");

        //Test show course in 1 semester (after add student)
        stE.findOneCour("C001", "2022A");
        stE.findOneCour("Further programming", "2022A");

        //Test drop course
        stE.dropCourse("S001", "C001", "2022A");

        //Test show course and student after remove student
        stE.findOneCour("C001", "2022A");
        stE.findOneStu("S001", "2022A");

        //Test update student information
        stE.updateStudent("S001", "ID", "S004");

        //Test update course information
        stE.updateCourseInfo("Further programming", "2022A", "Name", "Programming 1");
        System.out.println(stE.getSemesterCourses().get("2022A"));

        //Test remove course in semester
        stE.removeCourseSem("C002", "2022A");
        System.out.println(stE.getSemesterCourses().get("2022A"));
    }
}
