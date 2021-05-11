package ge.tsu.lecture4.exercise.student.modules;

public class NewStudentView {
    private String firstName;
    private String lastName;
    private int age;
    private double GPA;

    public NewStudentView(){}
    public NewStudentView(String firstName, String lastName, int age, double GPA) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.GPA = GPA;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }
}
