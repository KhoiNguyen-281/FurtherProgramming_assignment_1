public class Enrolment {
    private String student;
    private String semester;
    private String course;

    public Enrolment(String student, String semester, String course) {
        this.student = student;
        this.semester = semester;
        this.course = course;
    }


    public String getStudent() {
        return student;
    }

    public String getSemester() {
        return semester;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "Enrolment{" +
                "student: " + student +
                ", semester: '" + semester + '\'' +
                ", course: " + course +
                '}';
    }
}
