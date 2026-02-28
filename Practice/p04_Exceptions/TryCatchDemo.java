/**
 * TryCatchDemo.java — Exception Handling: try/catch/finally, Multi-catch, try-with-resources
 * ============================================================================================
 * CSE215 - Programming Language II
 *
 * 💡 INTUITION: Exceptions are Java's way of saying "something went wrong."
 *    Instead of returning error codes, Java THROWS an exception object
 *    that propagates up the call stack until someone CATCHes it.
 *
 *    Exception Hierarchy:
 *    Throwable
 *    ├── Error (serious, don't catch: OutOfMemoryError, StackOverflowError)
 *    └── Exception
 *        ├── RuntimeException (unchecked: NullPointer, ArrayIndexOutOfBounds, ClassCast)
 *        └── Checked exceptions (must handle: IOException, SQLException, FileNotFound)
 *
 * 📌 RULE: Checked exceptions MUST be caught or declared (throws).
 *    Unchecked exceptions (RuntimeException) are optional to catch.
 *
 * 🔗 SEE ALSO: p04_Exceptions/CustomExceptionDemo.java (creating your own exceptions)
 */
package p04_Exceptions;

import java.util.Scanner;
import java.util.InputMismatchException;

public class TryCatchDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Exception Handling Demo ===\n");

        // === Basic try-catch ===
        System.out.println("--- Basic Try-Catch ---");
        try {
            int result = 10 / 0; // ArithmeticException
            System.out.println("Result: " + result); // Never reached
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero!");
            System.out.println("Exception message: " + e.getMessage());
        }

        // === Multiple catch blocks ===
        System.out.println("\n--- Multiple Catch Blocks ---");
        try {
            int[] numbers = { 1, 2, 3 };
            System.out.println(numbers[10]); // ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Array index out of bounds!");
        } catch (Exception e) {
            System.out.println("Error: General exception occurred");
        }

        // === Try-catch-finally ===
        System.out.println("\n--- Try-Catch-Finally ---");
        Scanner localScanner = null;
        try {
            localScanner = new Scanner("test input");
            String text = localScanner.next();
            System.out.println("Read: " + text);
        } catch (Exception e) {
            System.out.println("Error reading input");
        } finally {
            // Finally block ALWAYS executes
            if (localScanner != null) {
                localScanner.close();
            }
            System.out.println("Finally block executed (cleanup done)");
        }

        // === Try-with-resources (Java 7+) ===
        System.out.println("\n--- Try-With-Resources ---");
        try (Scanner autoScanner = new Scanner("auto close test")) {
            System.out.println("Read: " + autoScanner.next());
            // autoScanner is automatically closed after this block
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Scanner was auto-closed");

        // === Catching multiple exceptions in one catch ===
        System.out.println("\n--- Multi-Catch (Java 7+) ---");
        try {
            String text = null;
            System.out.println(text.length()); // NullPointerException
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught: " + e.getClass().getSimpleName());
        }

        // === Nested try-catch ===
        System.out.println("\n--- Nested Try-Catch ---");
        try {
            System.out.println("Outer try block");
            try {
                int[] arr = new int[5];
                arr[10] = 50; // Exception here
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Inner catch: Array index error");
            }
            System.out.println("Continuing outer try block");
        } catch (Exception e) {
            System.out.println("Outer catch block");
        }

        // === Exception information ===
        System.out.println("\n--- Exception Information ---");
        try {
            String s = null;
            s.toUpperCase();
        } catch (NullPointerException e) {
            System.out.println("Exception type: " + e.getClass().getName());
            System.out.println("Message: " + e.getMessage());
            System.out.println("Stack trace:");
            e.printStackTrace();
        }

        // === Practical example: Input validation ===
        System.out.println("\n--- Practical: Input Validation ---");
        int validNumber = getValidInteger("42"); // Valid
        System.out.println("Valid input: " + validNumber);

        validNumber = getValidInteger("not a number"); // Invalid
        System.out.println("Invalid input returned: " + validNumber);

        scanner.close();
    }

    /**
     * Demonstrates exception handling for input validation
     */
    public static int getValidInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("'" + input + "' is not a valid integer");
            return 0; // Return default value
        }
    }
}

/*
 * ==================================
 * EXCEPTION HIERARCHY (Key Classes)
 * ==================================
 * 
 * Throwable
 * ├── Error (serious problems, don't catch)
 * │ ├── OutOfMemoryError
 * │ └── StackOverflowError
 * │
 * └── Exception
 * ├── RuntimeException (unchecked)
 * │ ├── NullPointerException
 * │ ├── ArrayIndexOutOfBoundsException
 * │ ├── ArithmeticException
 * │ ├── IllegalArgumentException
 * │ └── NumberFormatException
 * │
 * └── Checked Exceptions (must be caught or declared)
 * ├── IOException
 * ├── FileNotFoundException
 * └── SQLException
 */
