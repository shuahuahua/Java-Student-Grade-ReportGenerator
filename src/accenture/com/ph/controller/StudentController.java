package accenture.com.ph.controller;

import accenture.com.ph.model.*;
import accenture.com.ph.view.StudentView;

import java.util.ArrayList;

/*******************************
 * Noelle Joshua N. Fadol
 * Cloud Elite Bootcamp - Wave 5
 * Java Case Study
 *******************************/

public class StudentController {
    private StudentView view = new StudentView();
    private ArrayList<StudentGrade> students;

    public StudentController() {    //reads CSV file
        FileOperation reader = new CSVFileReader();
        this.students = reader.readFile("bini_grades.csv");
    }

    public void run() {
        String command;
        do {
            view.printMenu();    //prints menu on console
            command = view.getCommand();   //gets user input (L,R,Q)

            switch (command.toLowerCase()) {
                case "l":
                    char[] message = "Searching for all student records....\nListing all records....".toCharArray();
                    for (char msg : message) {
                        try {
                            Thread.sleep(100);
                            System.out.print(msg);
                        } catch (InterruptedException interruptedException) {
                            System.err.println(interruptedException.getMessage());
                        }
                    }
                    System.out.println("\nRecords have been listed successfully.\n");
                    view.listStudents(students);
                    break;

                case "r":
                    String id = view.promptStudentId();
                    try {
                        StudentGrade found = searchStudentById(id);
                        found.generateStudentReport("output/");

                        String studentName = found.getStudentName(); //getting student name based from studentID input for dynamic message
                        String dynamicMessage = "Please wait....\nRecord found....\nGenerating a report card for " + studentName + "....";
                        char[] message1 = dynamicMessage.toCharArray();

                        for (char msg : message1) {
                            try {
                                Thread.sleep(100);
                                System.out.print(msg);
                            } catch (InterruptedException interruptedException) {
                                System.err.println(interruptedException.getMessage());
                            }
                        }

                        System.out.println("\nReport card generated successfully.\n");
                    } catch (Exception e) {
                        System.err.println("Student with ID " + id + " not found.");
                    }
                    break;

                case "q":
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.err.println("Invalid command. Please try again.\n");
            }
        } while (!command.equalsIgnoreCase("q"));   //loops until user quits
        view.closeScanner();
    }

    private StudentGrade searchStudentById(String id) throws Exception {   //exception if ID input is not available
        for (StudentGrade student : students) {
            if (student.getStudentId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        throw new Exception("Student not found");
    }

    public static void main(String[] args) {
        new StudentController().run();
    }
}
