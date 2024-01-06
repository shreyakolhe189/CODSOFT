import java.util.*;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input student name
        System.out.print("Enter name of Student : ");
        String name = sc.nextLine();

        // Input number of subjects
        System.out.print("Enter number of subjects : ");
        int numberOfSubjects = sc.nextInt();

        // Input and sum of marks
        System.out.println("Enter marks of Subjects (0 to 100) : ");
        int marks[] = new int[numberOfSubjects];
        int totalMarks = 0;
        for (int i = 0; i < numberOfSubjects; i++) {
            int subjectMarks;
            do {
                System.out.print("Enter Subject marks " + (i + 1) + ": ");
                subjectMarks = sc.nextInt();

                // Validate marks are in the range of 0 to 100
                if (subjectMarks < 0 || subjectMarks > 100) {
                    System.out.println("Invalid input. Marks should be between 0 and 100. Please try again.");
                }
            } while (subjectMarks < 0 || subjectMarks > 100);

            marks[i] = subjectMarks;
            totalMarks += marks[i];
        }

        // Average percentage
        double averagePercentage = ((double) totalMarks / numberOfSubjects);

        // Grade
        String grade;
        if (averagePercentage >= 90) {
            grade = "A";
        } else if (averagePercentage >= 80) {
            grade = "B";
        } else if (averagePercentage >= 70) {
            grade = "C";
        } else if (averagePercentage >= 60) {
            grade = "D";
        } else if (averagePercentage >= 50) {
            grade = "E";
        } else {
            grade = "F";
        }

        // Display results
        System.out.println("Result of " + name + "is ");
        System.out.println("Total Marks : " + totalMarks);
        System.out.println("Average Percentage : " + averagePercentage);
        System.out.println("Grade : " + grade);
    }
}
