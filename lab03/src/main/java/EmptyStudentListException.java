/**
 * @author Grant Clark
 */

public class EmptyStudentListException extends StudentException {
    public EmptyStudentListException() { super(); }
    public EmptyStudentListException(String message) { super(message); }
}
