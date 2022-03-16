public class StudentEnrolment {
    private String semester;

    public static void main(String args[]){
        Course c1 = new Course("c001", "Further Programming", "12");
        Course c2 = new Course("c002", "Building IT System", "12");

        Student s1 = new Student("s001", "Khoi");
        Student s2 = new Student("s002", "Minh");
        Student s3 = new Student("s003", "Hoang");

        c1.studentEnrol(s1);
        c1.studentEnrol(s2);
        c1.studentEnrol(s3);
        c1.studentEnrol(s1);
        c2.studentEnrol(s1);

        System.out.println(c1.getStudentList());
        System.out.println(s1.getCourseList());
    }
}
