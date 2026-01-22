/**
 * Lab01 Starter - Use this as a template
 * TODO: Complete the exercises in separate files
 */
public class Lab01Starter {
    public static void main(String[] args) {
        System.out.println("=== CSE215 Lab 01 ===");
        System.out.println("Welcome to Java programming!");
        System.out.println();
        
        // --- Exercise 1: Hello World ---
        // Create HelloWorld.java and print "Hello, CSE215!"
        
        // --- Exercise 2: Personal Info ---
        // Create PersonalInfo.java with your details
        
        // --- Exercise 3: Simple Calculator ---
        // Demonstrate arithmetic operations
        int num1 = 15;
        int num2 = 4;
        
        System.out.println("Calculator Demo:");
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        // TODO: Add all operations
        
        // --- Exercise 4: Data Types ---
        // Demonstrate all primitive types
        byte byteVar = 100;
        short shortVar = 10000;
        int intVar = 100000;
        long longVar = 10000000000L;
        float floatVar = 3.14f;
        double doubleVar = 3.14159265359;
        char charVar = 'A';
        boolean boolVar = true;
        
        System.out.println("\nData Types Demo:");
        System.out.println("byte: " + byteVar);
        System.out.println("short: " + shortVar);
        System.out.println("int: " + intVar);
        System.out.println("long: " + longVar);
        System.out.println("float: " + floatVar);
        System.out.println("double: " + doubleVar);
        System.out.println("char: " + charVar);
        System.out.println("boolean: " + boolVar);
        
        // String operations
        String greeting = "Hello, Java!";
        System.out.println("\nString: " + greeting);
        System.out.println("Length: " + greeting.length());
        System.out.println("Uppercase: " + greeting.toUpperCase());
        System.out.println("Substring(0,5): " + greeting.substring(0, 5));
    }
}
