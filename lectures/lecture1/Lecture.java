/**
 * @author Grant Clark
 */

 // Single Line Comments
 /*
 Multiple Line comments
 here like this...
 */

 // Identifiers
 /*
    - Can start with an underscore, $, or letter, and 
    can contain letters, digits, or underscore.

    e.g. var1, _temp, $total

    - Case-sentitive
 */

 // Primitive Values (some)
 /*
    (integers)
    - byte (1 byte)
    - short (2 bytes)
    - int (4 bytes)
    - long (8 bytes)
    ---
    (floating point)
    - float (4 bytes)
    - double (8 bytes)
 */

 //   Similar to C++, java is strictly typed
 /*   
    Define variables with a type.
    int x;
    int y;
    int a,b;
 */
public class Lecture {
    public static void function1() {
        System.out.println("Hello World!");

        int x = 10;
        double y = x;

        // x = y;       // ERROR - possible losses due to incompatible types.
        x = (int) y;    // No error
    }

    public static void function2() {
        Object x = new Object();
        Object y = x; // y and x refer to the same object
        System.out.println(x==y); // prints "true"
        y = new Object(); // y refers to a new object
        System.out.println(x==y); // prints "false"
        x = y; // y and x refer to the same object. Object x from before gets cleaned up by java garbage collection.
    }

    public static void main(String[] args) {
        //function1();
        function2();
    }
}