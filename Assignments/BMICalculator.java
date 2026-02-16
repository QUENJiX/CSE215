package Assignments;

import java.util.Scanner;

public class BMICalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter weight in pounds: ");
        double weightPounds = input.nextDouble();

        System.out.print("Enter height in inches: ");
        double heightInches = input.nextDouble();

        double kilogramsPerPound = 0.45359237;
        double metersPerInch = 0.0254;
        double weightKg = weightPounds * kilogramsPerPound;
        double heightMeters = heightInches * metersPerInch;
        double bmi = weightKg / (heightMeters * heightMeters);

        System.out.println("BMI is " + bmi);
        if (bmi < 18.5) {
            System.out.println("Underweight");
        } else if (bmi < 25.0) {
            System.out.println("Normal");
        } else if (bmi < 30.0) {
            System.out.println("Overweight");
        } else {
            System.out.println("Obese");
        }
        input.close();
    }
}