/**
 * @author Grant Clark
 */

public class Course {
    private String code;
    private String name;
    private int currentEnroll;
    private Student[] students;

    public Course() {
        code = "";
        name = "";
        currentEnroll = 0;
        students = new Student[50];
    }

    public Course(String code, String name, int currentEnroll) {
        this.code = code;
        this.name = name;
        this.currentEnroll = currentEnroll;
        students = new Student[50];
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getCurrentEnroll() { return currentEnroll; }
    public void setCurrentEnroll(int currentEnroll) { this.currentEnroll = currentEnroll; }
    public Student[] getStudents() { return students; }
    public void setStudents(Student[] students) { this.students = students; }

    // Adds a student to the course. The student array order will need to be maintained
    // lexicographically by last name. In the event of a tie, the student's perm will be used.
    public void addStudent(Student s) throws StudentLimitException, DuplicateStudentException {
        if (currentEnroll == students.length)
            throw new StudentLimitException("ERROR: Student limit reached.");

        // Get the perm information from the user.
        s.setPerm(ClassRosterUI.getPermFromUser());

        // Check if the perm information given is duplicate.
        for (int i = 0; i < currentEnroll; i++) {
            if (students[i].getPerm() == s.getPerm())
                throw new DuplicateStudentException("ERROR: This student has already been added.");
        }

        // Set lastName and firstName fields for the student after prior exceptions have had potential to be thrown.
        ClassRosterUI.addStudentInfoFromUser(s);

        // Add student to the array.
        for (int i = 0; i < currentEnroll; i++) {
            int comparison = s.getLastName().compareToIgnoreCase(students[i].getLastName());
            if (comparison < 0) {
                // Push all current students in array by one.
                for (int j = currentEnroll; j > i; j--) {
                    students[j] = students[j-1];
                }
                students[i] = s;
                currentEnroll++;
                return;
            } else if (comparison == 0) {
                // Push all the current students in array by one.
                for (int j = currentEnroll; j > i + 1; j--) {
                    students[j] = students[j-1];
                }
                // Check which student had the lesser perm value.
                if (students[i].getPerm() < s.getPerm())
                    students[i+1] = s;
                else
                    students[i] = s;

                currentEnroll++;
                return;
            }
        }
        // If the student doesn't fit anywhere between other students lexicographically, then add to the end.
        students[currentEnroll] = s;
        currentEnroll++;
    }

    // Removes a student from the course based on their PERM number.
    public void removeStudent(int studentId) throws EmptyStudentListException, StudentNotFoundException {
        if (currentEnroll == 0)
            throw new EmptyStudentListException("ERROR: No students to delete.");
        for (int i = 0; i < currentEnroll; i++) {
            if (students[i].getPerm() == studentId) {
                for (int j = i; j < currentEnroll; j++) {
                    students[j] = students[j+1];
                }
                // Make the last student from the list of students an empty student object.
                students[currentEnroll] = new Student();
                currentEnroll--;
                return;
            }
        }
        throw new StudentNotFoundException("ERROR! This student does not exist.");
    }

}
