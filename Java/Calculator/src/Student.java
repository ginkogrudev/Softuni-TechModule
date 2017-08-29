public class  Student {

    private String name;
    private double grade;
    private int credits;

    public Student() {
    }

    public Student(String name, double grade, int credits) {
        this.name = name;
        this.grade = grade;
        this.credits = credits;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
