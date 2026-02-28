/**
 * ClassExample.java — Classes, Constructors, Encapsulation, and Static Members
 * ===============================================================================
 * CSE215 - Programming Language II
 *
 * 💡 INTUITION: A class is a BLUEPRINT, an object is a HOUSE built from that blueprint.
 *    Multiple houses (objects) can be built from the same blueprint (class),
 *    each with its own state (field values) but shared behavior (methods).
 *
 * Topics covered:
 *   1. Instance variables (fields)
 *   2. Constructors (default, parameterized, copy)
 *   3. Getters/Setters with validation (encapsulation)
 *   4. Static members (shared across all instances)
 *   5. toString() override
 *
 * 📌 RULE: Encapsulation = private fields + public getters/setters.
 *    This protects data integrity (validation in setters) and hides implementation.
 *
 * 🔗 SEE ALSO: p02_OOP/InheritanceDemo.java (extending classes)
 * 🔗 SEE ALSO: p02_OOP/StaticAndFinalDemo.java (deep-dive on static/final)
 * 🔗 SEE ALSO: p02_OOP/ObjectClassMethodsDemo.java (equals, hashCode, clone)
 */
package p02_OOP;

// Main class to run the demo
public class ClassExample {
    public static void main(String[] args) {
        // Creating objects using different constructors
        Student student1 = new Student(); // Default constructor
        Student student2 = new Student("Alice", 20, "CS101"); // Parameterized constructor

        // Using setter methods
        student1.setName("Bob");
        student1.setAge(22);
        student1.setStudentId("CS102");

        // Using getter methods
        System.out.println("=== Student 1 ===");
        student1.displayInfo();

        System.out.println("\n=== Student 2 ===");
        student2.displayInfo();

        // Demonstrating encapsulation - validation in setter
        System.out.println("\n=== Testing Validation ===");
        student1.setAge(-5); // Invalid age, will be rejected
        System.out.println("Age after invalid input: " + student1.getAge());

        // Using static members
        System.out.println("\n=== Static Members ===");
        System.out.println("Total students created: " + Student.getStudentCount());
        System.out.println("University name: " + Student.UNIVERSITY_NAME);
    }
}

/**
 * Student class demonstrating OOP fundamentals:
 * - Fields (instance variables)
 * - Constructors (default and parameterized)
 * - Getters and Setters (encapsulation)
 * - Static members
 * - Instance methods
 */
class Student {
    // === Instance Variables (Fields) ===
    private String name;
    private int age;
    private String studentId;

    // === Static Variables ===
    private static int studentCount = 0; // Shared across all instances
    public static final String UNIVERSITY_NAME = "Tech University"; // Constant

    // === Constructors ===

    // Default constructor
    public Student() {
        this.name = "Unknown";
        this.age = 0;
        this.studentId = "N/A";
        studentCount++;
    }

    // Parameterized constructor
    public Student(String name, int age, String studentId) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
        studentCount++;
    }

    // Copy constructor
    public Student(Student other) {
        this.name = other.name;
        this.age = other.age;
        this.studentId = other.studentId;
        studentCount++;
    }

    // === Getters (Accessor Methods) ===

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getStudentId() {
        return studentId;
    }

    // === Setters (Mutator Methods) with Validation ===

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) { // Validation to prevent empty names
            this.name = name;
        }
    }

    public void setAge(int age) {
        if (age >= 0 && age <= 150) { // Validation
            this.age = age;
        } else {
            System.out.println("Invalid age: " + age + ". Age must be between 0 and 150.");
        }
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    // === Static Methods ===

    public static int getStudentCount() {
        return studentCount;
    }

    // === Instance Methods ===

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Student ID: " + studentId);
        System.out.println("University: " + UNIVERSITY_NAME);
    }

    // toString method - standard way to represent object as string
    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", studentId='" + studentId + "'}";
    }
}
