public class Enrolment {
    private String stuID;
    private String semester;
    private String couID;

    public Enrolment(String stuID, String semester, String couID) {
        this.stuID = stuID;
        this.semester = semester;
        this.couID = couID;
    }

    public String getStuID() { return stuID; }

    public String getSemester() { return semester; }

    public String getCouID() { return couID; }

    @Override
    public String toString() {
        return "Enrolment: " +
                "stuID: " + stuID + '\'' +
                ", semester: " + semester + '\'' +
                ", couID: " + couID + '\'';
    }
}
