import java.util.Scanner;

public class gradeCalculator {
    public static void main(String[] args) {
        int grade;
        char letterGrade;

        Scanner input = new Scanner(System.in);
        System.out.print("Enter your grade: ");
        grade = input.nextInt();

        boolean isValid = isValidGrade(grade);

        while (!isValid) {
            System.out.println("Invalid! Grade must be between 0 and 100.");
            System.out.print("Enter your grade: ");
            grade = input.nextInt();
            isValid = isValidGrade(grade);
        }

        while (isValid) {
            if (grade >= 90)
                letterGrade = 'A';
            else if (grade >= 80)
                letterGrade = 'B';
            else if (grade >= 70)
                letterGrade = 'C';
            else if (grade >= 60)
                letterGrade = 'D';
            else
                letterGrade = 'F';

            System.out.println("Your letter grade is: " + letterGrade);
            System.out.print("Enter your grade: ");
            grade = input.nextInt();
            isValid = isValidGrade(grade);
            if (!isValid) {
                System.out.println("Invalid! Grade must be between 0 and 100.");
            }
        }
        input.close();
    }

    public static boolean isValidGrade(int grade) {
        return (grade >= 0) && (grade <= 100);
    }
}