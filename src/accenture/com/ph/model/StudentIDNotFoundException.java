package accenture.com.ph.model;

/*******************************
 * Noelle Joshua N. Fadol
 * Cloud Elite Bootcamp - Wave 5
 * Java Case Study
 *******************************/

public class StudentIDNotFoundException extends Exception {
    public StudentIDNotFoundException(String studentId) {
        super("Student ID does not exist: " + studentId);
    }
}
