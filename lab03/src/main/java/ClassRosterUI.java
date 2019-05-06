import java.util.Scanner;

/**
 * @author Grant Clark
 */

public class ClassRosterUI {

    private static Scanner input = new Scanner(System.in);

    public static void printMenu() {
        System.out.println("----------");
        System.out.println("ac: Add Course");
        System.out.println("dc: Drop Course");
        System.out.println("as: Add Student");
        System.out.println("ds: Drop Student");
        System.out.println(" p: Print ClassRoster");
        System.out.println(" q: Quit Program");
        System.out.println("----------");
    }

    public static String getCommand() {
        // Local variable declarations/initialization.
        String[] validCommands = {"ac", "dc", "as", "ds", "p", "q"};
        boolean isValid = false;
        String command = "";

        System.out.print("Enter Command: ");

        // While the command is NOT valid, keep looping for a valid command.
        while (!isValid) {
            // Check the validCommands array if the entered command is valid.
            command = input.nextLine().trim();
            for (String validCommand : validCommands) {
                if (command.equals(validCommand))
                    isValid = true;
            }

            // If the command is not located in the array, print error message before
            // getting the next command.
            if (!isValid)
                System.out.print("Invalid Command! Please enter a valid command: ");
        }

        return command;
    }

    // Get the course information when adding a new course.
    public static Course getCourseFromUser() {
        Course course = new Course();

        System.out.print("Enter Course Code: ");
        course.setCode(input.nextLine());
        System.out.print("Enter Course Name: ");
        course.setName(input.nextLine());

        return course;
    }

    // Get the course code when adding a student to a course or trying to remove a course.
    public static String getCourseCodeFromUser(String command) {
        String commandObj = "";
        if (command.equals("dc")) {
            commandObj = "Course";
        } else if (command.equals("as") || command.equals("ds")) {
            commandObj = "Student";
        }

        System.out.printf("Enter Course Code for %s: ", commandObj);
        String courseCode = input.nextLine();

        return courseCode;
    }

    // Get the student information when adding a student to a course.
    public static Student getStudentFromUser() throws NumberFormatException {
        Student student = new Student();

        System.out.print("Enter PERM: ");
        student.setPerm(Integer.parseInt(input.nextLine()));
        System.out.print("Enter last name: ");
        student.setLastName(input.nextLine());
        System.out.print("Enter first name: ");
        student.setFirstName(input.nextLine());

        return student;
    }

    // Get the first and last name of the student from the user.
    public static void addStudentInfoFromUser(Student s) {
        System.out.print("Enter last name: ");
        s.setLastName(input.nextLine());
        System.out.print("Enter first name: ");
        s.setFirstName(input.nextLine());
    }

    // Get the PERM number when trying to remove a student from a course.
    public static int getPermFromUser() throws NumberFormatException {
        System.out.print("Enter PERM: ");
        int perm = Integer.parseInt(input.nextLine());

        return perm;
    }

    public static void close() {
        input.close();
    }
}
