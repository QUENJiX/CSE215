/**
 * SerializationDemo.java — Java Serialization: Persisting Objects to Bytes
 * =========================================================================
 * CSE215 - Programming Language II
 *
 * 💡 INTUITION: Serialization converts a Java object into a byte stream
 *    so it can be saved to a file, sent over a network, or stored in a database.
 *    Deserialization reverses the process — bytes back to an object.
 *
 *    Think of it like freeze-drying food:
 *      Serialize:   Fresh meal → Freeze-dried packet (portable, storable)
 *      Deserialize: Freeze-dried packet + water → Fresh meal (restored)
 *
 * Topics covered:
 *   1. Serializable interface (marker interface)
 *   2. ObjectOutputStream / ObjectInputStream
 *   3. transient keyword (excluding fields)
 *   4. serialVersionUID (version control)
 *   5. Custom serialization (writeObject/readObject)
 *   6. Serializing collections
 *
 * ⚠️ GOTCHA: Java serialization has known security concerns for untrusted data.
 *    For production systems, consider JSON (Jackson/Gson) or Protocol Buffers.
 *    This demo teaches the concept — the principle applies to all formats.
 *
 * 🔗 SEE ALSO: p06_FileIO/FileReadWrite.java (text-based I/O)
 */
package p06_FileIO;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SerializationDemo {

    // =====================================================================
    // 1. Basic Serializable class
    // =====================================================================

    /**
     * 📌 RULE: A class must implement java.io.Serializable to be serialized.
     * Serializable is a MARKER INTERFACE — no methods to implement.
     * It just tells Java "this class is safe to convert to bytes."
     *
     * ⚠️ GOTCHA: ALL fields must also be Serializable (or transient).
     * Primitives and String are serializable by default.
     */
    static class Employee implements Serializable {
        /**
         * 📌 serialVersionUID: A version number for the class.
         * If you change the class (add/remove fields), update this number.
         * If it doesn't match during deserialization → InvalidClassException.
         *
         * ✅ BEST PRACTICE: Always declare serialVersionUID explicitly.
         * Without it, Java auto-generates one that changes with any class change.
         */
        private static final long serialVersionUID = 1L;

        private String name;
        private int age;
        private double salary;

        /**
         * 📌 transient: Marks a field to be EXCLUDED from serialization.
         * Use for: passwords, cached data, derived values, sensitive info.
         * After deserialization, transient fields get DEFAULT values
         * (null for objects, 0 for numbers, false for boolean).
         */
        private transient String password;

        // static fields are NEVER serialized (they belong to the class, not the object)
        private static int employeeCount = 0;

        Employee(String name, int age, double salary, String password) {
            this.name = name;
            this.age = age;
            this.salary = salary;
            this.password = password;
            employeeCount++;
        }

        @Override
        public String toString() {
            return String.format("Employee{name='%s', age=%d, salary=%.2f, password='%s'}",
                    name, age, salary, password);
        }
    }

    // =====================================================================
    // 2. Complex object with nested Serializable
    // =====================================================================

    static class Address implements Serializable {
        private static final long serialVersionUID = 1L;
        private String street;
        private String city;

        Address(String street, String city) {
            this.street = street;
            this.city = city;
        }

        @Override
        public String toString() {
            return street + ", " + city;
        }
    }

    static class Person implements Serializable {
        private static final long serialVersionUID = 1L;
        private String name;
        private Address address; // Must also be Serializable!
        private List<String> hobbies; // ArrayList is Serializable

        Person(String name, Address address, List<String> hobbies) {
            this.name = name;
            this.address = address;
            this.hobbies = new ArrayList<>(hobbies);
        }

        @Override
        public String toString() {
            return String.format("Person{name='%s', address=%s, hobbies=%s}", name, address, hobbies);
        }
    }

    // =====================================================================
    // 3. Custom serialization
    // =====================================================================

    /**
     * 💡 You can customize serialization by defining these private methods:
     * - writeObject(ObjectOutputStream) — custom write logic
     * - readObject(ObjectInputStream) — custom read logic
     *
     * Use case: encrypt sensitive data, handle version migrations,
     * reconstruct transient fields, etc.
     */
    static class SecureConfig implements Serializable {
        private static final long serialVersionUID = 2L;
        private String appName;
        private transient String apiKey; // Won't serialize normally

        SecureConfig(String appName, String apiKey) {
            this.appName = appName;
            this.apiKey = apiKey;
        }

        // Custom serialization: obfuscate the API key
        private void writeObject(ObjectOutputStream out) throws IOException {
            out.defaultWriteObject(); // Write non-transient fields normally
            // Write apiKey with simple encoding (NOT real encryption — demo only)
            String encoded = Base64.getEncoder().encodeToString(apiKey.getBytes());
            out.writeObject(encoded);
        }

        // Custom deserialization: decode the API key
        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject(); // Read non-transient fields normally
            String encoded = (String) in.readObject();
            this.apiKey = new String(Base64.getDecoder().decode(encoded));
        }

        @Override
        public String toString() {
            return String.format("SecureConfig{appName='%s', apiKey='%s'}", appName, apiKey);
        }
    }

    // =====================================================================
    // HELPER METHODS
    // =====================================================================

    /** Serialize an object to a file */
    static <T extends Serializable> void serialize(T object, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(object);
        }
    }

    /** Deserialize an object from a file */
    @SuppressWarnings("unchecked")
    static <T> T deserialize(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (T) ois.readObject();
        }
    }

    // =====================================================================
    // MAIN
    // =====================================================================
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   SERIALIZATION DEMO                         ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        String tempDir = System.getProperty("java.io.tmpdir");

        // =================================================================
        // 1. Basic Serialization
        // =================================================================
        System.out.println("=== 1. BASIC SERIALIZATION ===");

        String empFile = tempDir + "employee.ser";
        Employee emp = new Employee("Alice", 28, 75000, "secret123");
        System.out.println("Before:  " + emp);

        try {
            // Serialize
            serialize(emp, empFile);
            System.out.println("Saved to: " + empFile);

            // Deserialize
            Employee loaded = deserialize(empFile);
            System.out.println("Loaded:  " + loaded);

            System.out.println("\n⚠️ Notice: password is 'null' — transient fields are NOT saved!");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // =================================================================
        // 2. Complex Object Serialization
        // =================================================================
        System.out.println("\n=== 2. COMPLEX OBJECTS ===");

        String personFile = tempDir + "person.ser";
        Person person = new Person("Bob",
                new Address("123 Main St", "Boston"),
                List.of("Reading", "Coding", "Hiking"));

        System.out.println("Before: " + person);

        try {
            serialize(person, personFile);
            Person loaded = deserialize(personFile);
            System.out.println("Loaded: " + loaded);
            System.out.println("✅ Nested objects and collections preserved!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // =================================================================
        // 3. Serializing Collections
        // =================================================================
        System.out.println("\n=== 3. SERIALIZING COLLECTIONS ===");

        String listFile = tempDir + "employees.ser";
        ArrayList<Employee> team = new ArrayList<>(List.of(
                new Employee("Charlie", 35, 95000, "pass1"),
                new Employee("Diana", 29, 80000, "pass2"),
                new Employee("Eve", 32, 88000, "pass3")));

        System.out.println("Before (" + team.size() + " employees):");
        team.forEach(e -> System.out.println("  " + e));

        try {
            serialize(team, listFile);

            @SuppressWarnings("unchecked")
            ArrayList<Employee> loaded = deserialize(listFile);
            System.out.println("Loaded (" + loaded.size() + " employees):");
            loaded.forEach(e -> System.out.println("  " + e));
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // =================================================================
        // 4. Custom Serialization
        // =================================================================
        System.out.println("\n=== 4. CUSTOM SERIALIZATION ===");

        String configFile = tempDir + "config.ser";
        SecureConfig config = new SecureConfig("MyApp", "sk-12345-ABCDE");
        System.out.println("Before: " + config);

        try {
            serialize(config, configFile);
            SecureConfig loaded = deserialize(configFile);
            System.out.println("Loaded: " + loaded);
            System.out.println("✅ API key survived serialization via custom write/read!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Cleanup temp files
        try {
            Files.deleteIfExists(Path.of(empFile));
            Files.deleteIfExists(Path.of(personFile));
            Files.deleteIfExists(Path.of(listFile));
            Files.deleteIfExists(Path.of(configFile));
            System.out.println("\n🧹 Cleaned up temporary files.");
        } catch (IOException ignored) {
        }

        System.out.println("\n✅ All demos completed successfully!");
    }
}

/*
 * ╔═══════════════════════════════════════════════════════════════════╗
 * ║ SERIALIZATION CHEAT SHEET ║
 * ╠═══════════════════════════════════════════════════════════════════╣
 * ║ To make a class serializable: ║
 * ║ 1. implements Serializable ║
 * ║ 2. Add serialVersionUID ║
 * ║ 3. Mark non-serializable fields as transient ║
 * ║ ║
 * ║ Keywords/Concepts: ║
 * ║ Serializable → marker interface, no methods ║
 * ║ transient → field excluded from serialization ║
 * ║ serialVersionUID → version control for the class ║
 * ║ ObjectOutputStream → writes objects to bytes ║
 * ║ ObjectInputStream → reads objects from bytes ║
 * ║ ║
 * ║ ⚠️ NOT serialized: static fields, transient fields ║
 * ║ ⚠️ All referenced objects must also be Serializable ║
 * ║ ✅ Modern alternative: JSON with Jackson/Gson libraries ║
 * ╚═══════════════════════════════════════════════════════════════════╝
 */
