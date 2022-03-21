public class Enrolment {
    private Student student;
    private String semester;
    private Course course;

    public Enrolment(Student student, String semester, Course course) {
        this.student = student;
        this.semester = semester;
        this.course = course;
    }

    public String getStuID() {
        String stuID = student.getStudentID();
        return stuID;
    }

    public String getSemester() {
        return semester;
    }

    public String getCouID() {
        String couID = course.getCourseID();
        return couID;
    }

    @Override
    public String toString() {
        return "Enrolment{" +
                "student=" + getStuID() +
                ", semester='" + semester + '\'' +
                ", course=" + getCouID() +
                '}';
    }
}
