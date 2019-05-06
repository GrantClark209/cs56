import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

/**
 * @author Grant Clark
 */

public class ClassRosterTester {

    // Component Under Test (cut)
    private RosterManager cut;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        cut = new RosterManager();
    }

    @Test
    public void addCourseTest() throws DuplicateCourseException, CourseLimitException {
        Course c = new Course();
        cut.addCourse(c);
    }

    @Test
    public void addCourseTest_CourseLimitException() throws DuplicateCourseException, CourseLimitException {
        thrown.expect(CourseLimitException.class);

        // Adds Course1 -> Course10
        for (int i = 1; i < 11; i++) {
            Course c = new Course();
            c.setCode("Course" + i);
            cut.addCourse(c);
        }

        // Adding more than max courses throws CourseLimitException
        Course c = new Course();
        c.setCode("Course11");
        cut.addCourse(c);
    }

    @Test
    public void addCourseTest_DuplicateCourseException() throws DuplicateCourseException, CourseLimitException {
        thrown.expect(DuplicateCourseException.class);

        Course c1 = new Course();
        Course c2 = new Course();
        c1.setCode("IdenticalCourseCode");
        c2.setCode("IdenticalCourseCode");

        // Adding two of the same course throws DuplicateCourseException
        cut.addCourse(c1);
        cut.addCourse(c2);
    }

    @Test
    public void deleteCourseTest() throws CourseNotFoundException, EmptyCourseListException {
        int expectedCourseSize = 2;

        // Add courses
        Course c1 = new Course();
        Course c2 = new Course();
        Course c3 = new Course();
        c1.setCode("Course1");
        c2.setCode("Course2");
        c3.setCode("Course3");

        // Inject course list
        Course[] inputCourses = {c1, c2, c3};
        cut.setCourses(inputCourses);

        cut.deleteCourse("Course2");

        // Get back out the new course list
        Course[] courses = cut.getCourses();

        assertEquals("Expected the course array size to be: " + expectedCourseSize, expectedCourseSize, courses.length);
        assertEquals("Expected the first course to have code: " + c1.getCode() , c1.getCode(), courses[0].getCode());
        assertEquals("Expected the second course to have code: " + c3.getCode(), c3.getCode(), courses[1].getCode());
    }

    @Test
    public void deleteCourseTest_CourseNotFoundException() throws CourseNotFoundException, EmptyCourseListException {
        thrown.expect(CourseNotFoundException.class);

        Course c = new Course();
        c.setCode("ValidCourseCode");

        // Inject course list
        Course[] inputCourses = {c};
        cut.setCourses(inputCourses);

        // Deleting a course that does not exist throws CourseNotFoundException
        cut.deleteCourse("InvalidCourseCode");
    }

    @Test
    public void deleteCourseTest_EmptyCourseListException() throws CourseNotFoundException, EmptyCourseListException {
        thrown.expect(EmptyCourseListException.class);

        cut.deleteCourse("InvalidCourseCode_WithNoCoursesInArray");
    }

    @Test
    public void addStudentTest() throws DuplicateCourseException, CourseLimitException, CourseNotFoundException,
            DuplicateStudentException, StudentLimitException {
        Course c = new Course();
        c.setCode("CourseCode");
        cut.addCourse(c);

        Student s = new Student(12345678, "lastName", "firstName");

        cut.addStudent("CourseCode", s);
    }

    @Test
    public void addStudentTest_MaintainLexOrder() throws DuplicateCourseException, CourseLimitException {
        Course c = new Course();
        c.setCode("CourseCode");
        cut.addCourse(c);
    }

    @Test
    public void addStudentTest_MaintainPermOrder() {

    }

    @Test
    public void addStudentTest_StudentLimitException() {

    }

    @Test
    public void addStudentTest_DuplicateStudentException() {

    }

    @Test
    public void addStudentTest_CourseNotFoundException() {

    }

    @Test
    public void deleteStudentTest() {

    }

    @Test
    public void deleteStudentTest_MaintainLexOrder() {

    }

    @Test
    public void deleteStudentTest_MaintainPermOrder() {

    }

    @Test
    public void deleteStudentTest_EmptyStudentListException() {

    }

    @Test
    public void deleteStudentTest_StudentNotFoundException() {

    }

    @Test
    public void deleteStudentTest_CourseNotFoundException() {

    }
}
