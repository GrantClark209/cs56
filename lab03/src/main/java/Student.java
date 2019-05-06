/**
 * @author Grant Clark
 */

public class Student {
    private int perm;
    private String firstName;
    private String lastName;

    public Student() {
        perm = 0;
        firstName = "";
        lastName = "";
    }
    public Student(int perm, String firstName, String lastName) {
        this.perm = perm;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getPerm() { return perm; }
    public void setPerm(int perm) { this.perm = perm; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
}
