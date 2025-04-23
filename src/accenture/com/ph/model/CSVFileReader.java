package accenture.com.ph.model;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*******************************
 * Noelle Joshua N. Fadol
 * Cloud Elite Bootcamp - Wave 5
 * Java Case Study
 *******************************/

public class CSVFileReader implements FileOperation {

    public ArrayList<StudentGrade> readFile(String filePath) {
        ArrayList<StudentGrade> studentList = new ArrayList<>();
        Map<String, ArrayList<Integer>> studentGradesMap = new HashMap<>();       //storing student values efficiently using hashmap
        Map<String, String> studentNamesMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) { //try-with-resources to auto-close
            String line;   // skips the header line
            br.readLine();  // Read the header, don't process it

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                String studentId = tokens[0];
                String studentName = tokens[1];
                int grade = Integer.parseInt(tokens[3]);

                // collect student names and grades
                studentNamesMap.putIfAbsent(studentId, studentName); // store name only once
                studentGradesMap.putIfAbsent(studentId, new ArrayList<>());
                studentGradesMap.get(studentId).add(grade);
            }

            // after reading all lines, create StudentGrade objects
            for (String studentId : studentGradesMap.keySet()) {
                String name = studentNamesMap.get(studentId);
                ArrayList<Integer> gradesList = studentGradesMap.get(studentId);

                // Convert ArrayList<Integer> to int[]
                int[] grades = new int[gradesList.size()];
                for (int i = 0; i < gradesList.size(); i++) {
                    grades[i] = gradesList.get(i);
                }

                // Create StudentGrade object with converted grades
                StudentGrade student = new StudentGrade(studentId, name, grades);
                studentList.add(student);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return studentList;
    }
}
