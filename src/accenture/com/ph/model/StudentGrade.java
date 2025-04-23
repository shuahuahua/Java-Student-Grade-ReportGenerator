package accenture.com.ph.model;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*******************************
 * Noelle Joshua N. Fadol
 * Cloud Elite Bootcamp - Wave 5
 * Java Case Study
 *******************************/

public class StudentGrade implements Serializable {
    private String studentId;
    private String studentName;
    private int[] grades;
    private double average;
    private static final String[] subjects = {
            "Filipino", "English", "Math", "Mapeh", "Science", "Araling Panlipunan"
    };

    public StudentGrade() {}

    public StudentGrade(String studentId, String studentName, int[] grades) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.grades = grades;
        computeAverage();
    }

    public void computeAverage() {
        int sum = 0;
        for (int grade : grades) sum += grade;
        this.average = (double) sum / grades.length;
    }

    public void generateStudentReport(String ignoredDirectoryPath) {   // use current directory in saving output file
        String basePath = System.getProperty("user.dir");
        File outputDir = new File(basePath, "Generated Report Cards");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = LocalDateTime.now().format(formatter);

        String[] nameParts = studentName.toLowerCase().trim().split(" ");
        String lastName = nameParts.length > 1 ? nameParts[1] : nameParts[0];
        String fileName = lastName + "_" + nameParts[0] + "_" + timestamp + ".txt";

        File file = new File(outputDir, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Student Report Card\n");
            writer.write("====================\n");
            writer.write(String.format("Student ID: %s\n", studentId));
            writer.write(String.format("Name: %s\n\n", studentName));

            for (int i = 0; i < subjects.length; i++) {
                writer.write(String.format("%-35s %d\n", subjects[i], grades[i]));
            }

            writer.write(String.format("\n%-35s %.2f\n", "Average Grade", average));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public int[] getGrades() {
        return grades;
    }

    public double getAverage() {
        return average;
    }
}
