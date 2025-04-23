package accenture.com.ph.view;

import accenture.com.ph.model.StudentGrade;

import java.util.List;
import java.util.Scanner;

/*******************************
 * Noelle Joshua N. Fadol
 * Cloud Elite Bootcamp - Wave 5
 * Java Case Study
 *******************************/

public class StudentView {
    private Scanner scanner = new Scanner(System.in);

    public void printMenu() {
        System.out.println("\n===== STUDENT REPORT CARD MENU =====");
        System.out.println("[L] - List All Records");
        System.out.println("[R] - Report Card Generate");
        System.out.println("[Q] - Quit");
        System.out.print("Enter command of choice: ");
    }

    public String getCommand() {
        return scanner.nextLine().trim();
    }

    public void listStudents(List<StudentGrade> students) {
        System.out.printf("%-15s %-20s %-10s\n", "STUDENT ID", "NAME", "AVERAGE");   //spaces for aesthetics
        for (StudentGrade student : students) {
            System.out.printf("%-15s %-20s %-10.2f\n",
                    student.getStudentId(), student.getStudentName(), student.getAverage());
        }
    }

    public String promptStudentId() {
        System.out.print("Enter Student ID: ");
        return scanner.nextLine().trim();
    }

    public void showNotFound(String id) {
        System.out.println("Student ID does not exist: " + id);
    }
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }

}
