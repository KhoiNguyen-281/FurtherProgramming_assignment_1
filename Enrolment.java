public class Enrolment {
    private Student student;
    private Course course;
    private String semester;

    public Enrolment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public Enrolment(){

    }

    public Student getStudent() {
        return student;
    }

    public String getSemester() {
        return semester;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "Enrolment: " +
                "student: " + student +
                ", course: " + course +
                ", semester: " + semester +
                "\n";
    }
}
