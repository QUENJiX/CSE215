/**
 * ControlFlowDemo.java — If/Else, Switch, Loops, Break/Continue
 * ================================================================
 * CSE215 - Programming Language II
 *
 * 💡 INTUITION: Control flow determines WHICH code runs and HOW MANY times.
 *    - Conditionals (if/switch): choose a path
 *    - Loops (for/while/do-while): repeat a path
 *    - Break/Continue: escape or skip within loops
 *
 * 📌 RULE: Enhanced switch (Java 14+) uses -> arrows and returns values.
 *    No break needed, no fall-through bugs.
 *
 * 🔗 SEE ALSO: p01_Basics/DataTypesDemo.java (operators used in conditions)
 * 🔗 SEE ALSO: p01_Basics/MethodsAndRecursionDemo.java (methods + recursion)
 */
package p01_Basics;

import java.util.Scanner;

public class ControlFlowDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // === If-Else Statements ===
        System.out.println("=== If-Else Demo ===");
        int score = 85;

        if (score >= 90) {
            System.out.println("Grade: A");
        } else if (score >= 80) {
            System.out.println("Grade: B");
        } else if (score >= 70) {
            System.out.println("Grade: C");
        } else if (score >= 60) {
            System.out.println("Grade: D");
        } else {
            System.out.println("Grade: F");
        }

        // Ternary operator (shorthand if-else)
        String result = (score >= 60) ? "Pass" : "Fail";
        System.out.println("Result: " + result);

        // === Switch Statement ===
        System.out.println("\n=== Switch Demo ===");
        int dayNumber = 3;

        // Traditional switch
        String dayName;
        switch (dayNumber) {
            case 1:
                dayName = "Monday";
                break;
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            case 4:
                dayName = "Thursday";
                break;
            case 5:
                dayName = "Friday";
                break;
            case 6:
            case 7:
                dayName = "Weekend";
                break;
            default:
                dayName = "Invalid day";
        }
        System.out.println("Day " + dayNumber + " is " + dayName);

        // Enhanced switch (Java 14+)
        String day = switch (dayNumber) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6, 7 -> "Weekend";
            default -> "Invalid";
        };
        System.out.println("Enhanced switch result: " + day);

        // === Loops ===

        // For loop
        System.out.println("\n=== For Loop ===");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Iteration " + i);
        }

        // While loop
        System.out.println("\n=== While Loop ===");
        int count = 1;
        while (count <= 3) {
            System.out.println("Count: " + count);
            count++;
        }

        // Do-While loop (executes at least once)
        System.out.println("\n=== Do-While Loop ===");
        int num = 1;
        do {
            System.out.println("Number: " + num);
            num++;
        } while (num <= 3);

        // === Loop Control ===
        System.out.println("\n=== Break and Continue ===");

        // Break - exits the loop
        System.out.println("Break example:");
        for (int i = 1; i <= 10; i++) {
            if (i == 5) {
                System.out.println("Breaking at " + i);
                break;
            }
            System.out.println("i = " + i);
        }

        // Continue - skips current iteration
        System.out.println("\nContinue example (skip even numbers):");
        for (int i = 1; i <= 6; i++) {
            if (i % 2 == 0) {
                continue; // Skip even numbers
            }
            System.out.println("Odd: " + i);
        }

        // === Nested Loops ===
        System.out.println("\n=== Nested Loops (Multiplication Table) ===");
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                System.out.print(i * j + "\t");
            }
            System.out.println();
        }

        scanner.close();
    }
}
