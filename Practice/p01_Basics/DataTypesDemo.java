/**
 * DataTypesDemo.java - Demonstrates Java primitive data types and their operations
 * CSE215 - Programming Language II
 */
package p01_Basics;

public class DataTypesDemo {
    public static void main(String[] args) {
        // === Primitive Data Types ===

        // Integer types
        byte byteVar = 127; // 1 byte: -128 to 127
        short shortVar = 32767; // 2 bytes: -32,768 to 32,767
        int intVar = 2147483647; // 4 bytes: -2^31 to 2^31-1
        long longVar = 9223372036854775807L; // 8 bytes: -2^63 to 2^63-1

        // Floating-point types
        float floatVar = 3.14159f; // 4 bytes: ~7 decimal digits
        double doubleVar = 3.141592653589793; // 8 bytes: ~15 decimal digits

        // Character and Boolean
        char charVar = 'A'; // 2 bytes: Unicode character
        boolean boolVar = true; // 1 bit: true or false

        // === Type Casting ===

        // Implicit casting (widening): smaller → larger
        int myInt = 100;
        double myDouble = myInt; // int to double automatically
        System.out.println("Implicit cast (int → double): " + myDouble);

        // Explicit casting (narrowing): larger → smaller
        double pi = 3.14159;
        int truncatedPi = (int) pi; // Must cast explicitly, loses decimal
        System.out.println("Explicit cast (double → int): " + truncatedPi);

        // === String Operations ===

        String greeting = "Hello";
        String name = "World";

        // Concatenation
        String message = greeting + ", " + name + "!";
        System.out.println(message);

        // String methods
        System.out.println("Length: " + message.length());
        System.out.println("Uppercase: " + message.toUpperCase());
        System.out.println("Substring: " + message.substring(0, 5));
        System.out.println("Contains 'World': " + message.contains("World"));

        // === Arrays ===

        // Array declaration and initialization
        int[] numbers = { 10, 20, 30, 40, 50 };
        String[] days = new String[7];
        days[0] = "Monday";
        days[1] = "Tuesday";

        // Array iteration
        System.out.println("\nArray elements:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("  numbers[" + i + "] = " + numbers[i]);
        }

        // Enhanced for loop (for-each)
        System.out.println("\nUsing for-each:");
        for (int num : numbers) {
            System.out.println("  Value: " + num);
        }

        // === Operators ===

        int a = 10, b = 3;
        System.out.println("\n=== Arithmetic Operators ===");
        System.out.println("a + b = " + (a + b));
        System.out.println("a - b = " + (a - b));
        System.out.println("a * b = " + (a * b));
        System.out.println("a / b = " + (a / b)); // Integer division
        System.out.println("a % b = " + (a % b)); // Modulus (remainder)

        System.out.println("\n=== Comparison Operators ===");
        System.out.println("a == b: " + (a == b));
        System.out.println("a != b: " + (a != b));
        System.out.println("a > b: " + (a > b));
        System.out.println("a >= b: " + (a >= b));

        System.out.println("\n=== Logical Operators ===");
        boolean x = true, y = false;
        System.out.println("x && y: " + (x && y)); // AND
        System.out.println("x || y: " + (x || y)); // OR
        System.out.println("!x: " + (!x)); // NOT
    }
}
