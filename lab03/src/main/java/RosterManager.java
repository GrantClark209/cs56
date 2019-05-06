/**
 * @author Grant Clark
 */

public class RosterManager {

    private Course[] courses;
    private int numberOfCourses;

    public Course[] getCourses() { return courses; }
    public void setCourses(Course[] courses) { this.courses = courses; }
    public int getNumberOfCourses() { return numberOfCourses; }
    public void setNumberOfCourses(int numberOfCourses) { this.numberOfCourses = numberOfCourses; }

    public RosterManager() {
        courses = new Course[10];
        numberOfCourses = 0;
    }

    // Runs the main loop of the program. This prints the menu, accepts user input,
    // and handles each of the user commands.
    // Handles the custom Exceptions and prints the messages to the user.
    public void run() {
        boolean done = false;

        System.out.println("Welcome to Class Roster Manager!");
        System.out.println("Select an action based on the following menu:");

        while (!done) {
            ClassRosterUI.printMenu();
            String command = ClassRosterUI.getCommand();
            try {
                String courseCode;
                switch (command) {
                    case "ac":
                        Course course = ClassRosterUI.getCourseFromUser();
                        addCourse(course);
                        break;
                    case "dc":
                        courseCode = ClassRosterUI.getCourseCodeFromUser(command);
                        deleteCourse(courseCode);
                        break;
                    case "as":
                        Student student = new Student();
                        courseCode = ClassRosterUI.getCourseCodeFromUser(command);
                        addStudent(courseCode, student);
                        break;
                    case "ds":
                        courseCode = ClassRosterUI.getCourseCodeFromUser(command);
                        // id = -1 indicated to deleteStudent to get the student PERM after trying to see if
                        // any other exceptions occur.
                        deleteStudent(-1, courseCode);
                        break;
                    case "p":
                        printRoster();
                        break;
                    case "q":
                        ClassRosterUI.close();
                        done = true;
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid PERM.");
            } catch (StudentException | CourseException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Adds a course to the array of courses.
    public void addCourse(Course c) throws DuplicateCourseException, CourseLimitException {
        if (numberOfCourses == courses.length)
            throw new CourseLimitException("ERROR: Course limit reached.");
        for (int i = 0; i < numberOfCourses; i++) {
            if (c.getCode().equals(courses[i].getCode()))
                throw new DuplicateCourseException("ERROR: This course has already been added.");
        }
        courses[numberOfCourses] = c;
        numberOfCourses++;
    }

    // Deletes a course from the array of courses.
    public void deleteCourse(String courseCode) throws CourseNotFoundException, EmptyCourseListException {
        if (numberOfCourses == 0)
            throw new EmptyCourseListException("ERROR: No courses exist to delete.");
        for (int i = 0; i < numberOfCourses; i++) {
            if (courseCode.equals(courses[i].getCode())) {
                // Delete course and move remaining courses.
                for (int j = i; j < numberOfCourses - 1; j++) {
                    courses[j] = courses[j+1];
                }
                // Make the last course from the list of courses an empty course object.
                courses[numberOfCourses] = new Course();
                numberOfCourses--;
                return;
            }
        }
        throw new CourseNotFoundException("ERROR: This course does not exist.");

    }

    // Adds a student to an already existing courseCode.
    public void addStudent(String courseCode, Student s) throws StudentLimitException, DuplicateStudentException,
            CourseNotFoundException {
        for (int i = 0; i < numberOfCourses; i++) {
            if (courseCode.equals(courses[i].getCode())) {
                courses[i].addStudent(s);
                return;
            }
        }
        throw new CourseNotFoundException("ERROR: This course does not exist.");
    }

    // Deletes a student to an already existing courseCode.
    public void deleteStudent(int id, String courseCode) throws EmptyStudentListException, StudentNotFoundException,
            CourseNotFoundException {
        for (int i = 0; i < numberOfCourses; i++) {
            if (courseCode.equals(courses[i].getCode())) {
                if (id < 0)
                    id = ClassRosterUI.getPermFromUser();
                courses[i].removeStudent(id);
                return;
            }
        }
        throw new CourseNotFoundException("ERROR: This course does not exist.");
    }

    // Prints the information for all courses and their enrolled students.
    public void printRoster() {
        System.out.println("********************");
        for (int i = 0; i < numberOfCourses; i++) {
            Course c = courses[i];
            int enrolled = c.getCurrentEnroll();
            Student[] students = c.getStudents();

            System.out.printf("%s : %s%n", c.getCode(), c.getName());
            System.out.printf("Enrolled: %d%n", enrolled);
            for (int j = 0; j < enrolled; j++) {
                int perm = students[j].getPerm();
                String firstName = students[j].getFirstName();
                String lastName = students[j].getLastName();

                System.out.printf("\t%d | %s, %s%n", perm, lastName, firstName);
            }
        }
        System.out.println("********************");
    }

}
