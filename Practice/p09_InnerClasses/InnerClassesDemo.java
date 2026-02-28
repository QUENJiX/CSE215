/**
 * InnerClassesDemo.java — All Types of Inner and Nested Classes
 * ================================================================
 * CSE215 - Programming Language II
 *
 * 💡 INTUITION: Java allows classes inside other classes. Each type
 *    serves a different purpose:
 *
 *    ┌─────────────────────────────────────────────────────────────┐
 *    │  Outer Class                                                │
 *    │  ┌──────────────────────────────────────────────────┐      │
 *    │  │ Static Nested Class                               │      │
 *    │  │ • No access to outer's instance members           │      │
 *    │  │ • Like a regular class, just organized inside     │      │
 *    │  └──────────────────────────────────────────────────┘      │
 *    │  ┌──────────────────────────────────────────────────┐      │
 *    │  │ Inner Class (non-static)                          │      │
 *    │  │ • Has access to ALL outer members (even private)  │      │
 *    │  │ • Requires an outer instance to exist             │      │
 *    │  └──────────────────────────────────────────────────┘      │
 *    │  method() {                                                 │
 *    │    ┌──────────────────────────────────────┐               │
 *    │    │ Local Class (inside method)           │               │
 *    │    │ • Visible only inside technique       │               │
 *    │    └──────────────────────────────────────┘               │
 *    │    ┌──────────────────────────────────────┐               │
 *    │    │ Anonymous Class (no name)             │               │
 *    │    │ • One-shot implementation             │               │
 *    │    └──────────────────────────────────────┘               │
 *    │  }                                                          │
 *    └─────────────────────────────────────────────────────────────┘
 *
 * Topics covered:
 *   1. Static nested classes
 *   2. Inner (non-static) classes
 *   3. Local classes (inside methods)
 *   4. Anonymous classes
 *   5. When to use which
 *
 * 🔗 SEE ALSO: p03_Interfaces/InterfaceExample.java
 * 🔗 SEE ALSO: p08_LambdasAndStreams/LambdaExpressionsDemo.java (lambdas replaced many anonymous classes)
 */
package p09_InnerClasses;

import java.util.*;

public class InnerClassesDemo {

    // =====================================================================
    // OUTER CLASS STATE
    // =====================================================================
    private String outerField = "I am the outer field";
    private static String staticOuterField = "I am static outer";

    // =====================================================================
    // 1. STATIC NESTED CLASS
    // =====================================================================

    /**
     * 📌 RULE: A static nested class is like a regular top-level class
     * that is just packaged inside another class for organization.
     *
     * - Can access STATIC members of outer class (even private)
     * - CANNOT access instance members of outer class
     * - Created without an outer instance: new Outer.StaticNested()
     *
     * ✅ BEST PRACTICE: Use static nested classes for helper classes
     * that logically belong to the outer class but don't need
     * access to its instance state.
     *
     * Real-world example: Map.Entry is a static nested interface inside Map.
     */
    static class Address {
        private String street;
        private String city;

        Address(String street, String city) {
            this.street = street;
            this.city = city;
            // ✅ Can access static outer members
            System.out.println("    (Accessing static outer: " + staticOuterField + ")");
            // ❌ Cannot do: System.out.println(outerField); // Compile error!
        }

        @Override
        public String toString() {
            return street + ", " + city;
        }
    }

    // Another example: Builder pattern uses static nested class
    static class Builder {
        private String name;
        private int age;

        Builder name(String name) {
            this.name = name;
            return this;
        }

        Builder age(int age) {
            this.age = age;
            return this;
        }

        String build() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }

    // =====================================================================
    // 2. INNER CLASS (non-static)
    // =====================================================================

    /**
     * 📌 RULE: An inner class has a hidden reference to an instance of
     * the enclosing class. It can access ALL members of the outer
     * class, including private ones.
     *
     * - Requires an outer instance: outerObj.new Inner()
     * - Has access to BOTH static and instance members of outer
     * - Cannot declare static members (except constants)
     *
     * ⚠️ GOTCHA: Each inner class instance holds a reference to the outer
     * instance, which can prevent garbage collection (memory leak risk).
     *
     * Real-world example: Iterator implementations inside collection classes.
     */
    class Engine {
        private int horsepower;

        Engine(int hp) {
            this.horsepower = hp;
        }

        void describe() {
            // ✅ Can access outer instance field directly
            System.out.println("    Engine (" + horsepower + "hp) belongs to context: " + outerField);
            // ✅ Can access outer static field
            System.out.println("    Static context: " + staticOuterField);
        }

        // Accessing outer 'this' explicitly
        void showOuterThis() {
            // InnerClassesDemo.this refers to the enclosing instance
            System.out.println("    Outer this: " + InnerClassesDemo.this.outerField);
        }
    }

    // Practical example: custom Iterator
    private int[] data = { 10, 20, 30, 40, 50 };

    class DataIterator implements Iterator<Integer> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            // ✅ Accessing outer class's 'data' array directly
            return index < data.length;
        }

        @Override
        public Integer next() {
            return data[index++];
        }
    }

    // =====================================================================
    // MAIN METHOD — demonstrates all types
    // =====================================================================
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   INNER CLASSES DEMO                         ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // =================================================================
        // 1. Static Nested Class
        // =================================================================
        System.out.println("=== 1. STATIC NESTED CLASS ===");

        // ✅ Created without an outer instance
        Address addr = new InnerClassesDemo.Address("123 Main St", "Springfield");
        System.out.println("Address: " + addr);

        // Builder pattern example
        String person = new Builder().name("Alice").age(25).build();
        System.out.println("Built: " + person);

        // =================================================================
        // 2. Inner (non-static) Class
        // =================================================================
        System.out.println("\n=== 2. INNER CLASS ===");

        // ⚠️ Must create outer instance first, then create inner through it
        InnerClassesDemo outer = new InnerClassesDemo();
        Engine engine = outer.new Engine(200);
        engine.describe();
        engine.showOuterThis();

        // Iterator example — inner class accesses outer's data array
        System.out.println("\n  Custom Iterator:");
        DataIterator iter = outer.new DataIterator();
        while (iter.hasNext()) {
            System.out.print("    " + iter.next());
        }
        System.out.println();

        // =================================================================
        // 3. Local Class (inside a method)
        // =================================================================
        System.out.println("\n=== 3. LOCAL CLASS (inside method) ===");

        /**
         * 📌 RULE: A local class is defined inside a method body.
         * - Visible only within that method
         * - Can access outer class members AND local variables
         * (but local variables must be effectively final)
         * - Useful for one-time helper logic
         */

        final String format = ">> "; // Must be effectively final

        class Formatter {
            String format(String text) {
                return format + text.toUpperCase() + " <<";
            }
        }

        Formatter fmt = new Formatter();
        System.out.println(fmt.format("hello local class"));
        System.out.println(fmt.format("another message"));

        // More practical example: custom comparator as local class
        class LengthComparator implements Comparator<String> {
            @Override
            public int compare(String a, String b) {
                return Integer.compare(a.length(), b.length());
            }
        }

        List<String> words = new ArrayList<>(List.of("banana", "fig", "cherry", "date"));
        words.sort(new LengthComparator());
        System.out.println("Sorted by length: " + words);

        // =================================================================
        // 4. Anonymous Class
        // =================================================================
        System.out.println("\n=== 4. ANONYMOUS CLASS ===");

        /**
         * 📌 RULE: An anonymous class is a class without a name, defined
         * and instantiated in a single expression. It implements an
         * interface or extends a class inline.
         *
         * Syntax:
         * new InterfaceOrClass() {
         * // override methods here
         * };
         *
         * 💡 Before Java 8 lambdas, this was the primary way to create
         * one-off implementations. Now, for single-method interfaces
         * (functional interfaces), prefer lambdas.
         */

        // Anonymous class implementing Comparator
        Comparator<String> reverseComp = new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a); // Reverse order
            }
        };

        List<String> fruits = new ArrayList<>(List.of("banana", "apple", "cherry"));
        fruits.sort(reverseComp);
        System.out.println("Reverse sorted: " + fruits);

        // Anonymous class implementing Runnable
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("Running anonymous Runnable!");
            }
        };
        task.run();

        // Anonymous class extending a class
        abstract class Greeting {
            abstract String greet(String name);
        }

        Greeting formalGreeting = new Greeting() {
            @Override
            String greet(String name) {
                return "Good day, " + name + ". How do you do?";
            }
        };
        System.out.println(formalGreeting.greet("Professor"));

        // Anonymous class with multiple methods (can't use lambda for this!)
        System.out.println("\n--- Multi-method anonymous class (lambda can't do this) ---");

        abstract class Animal {
            abstract String sound();

            abstract String type();
        }

        Animal dog = new Animal() {
            @Override
            String sound() {
                return "Woof!";
            }

            @Override
            String type() {
                return "Dog";
            }
        };
        System.out.println(dog.type() + " says: " + dog.sound());

        // =================================================================
        // 5. Anonymous vs Lambda comparison
        // =================================================================
        System.out.println("\n=== 5. ANONYMOUS CLASS vs LAMBDA ===");

        /**
         * ✅ USE LAMBDA when:
         * - Interface has exactly ONE abstract method (functional interface)
         * - You don't need 'this' to refer to the anonymous instance
         * - Clean, concise code is the goal
         *
         * ✅ USE ANONYMOUS CLASS when:
         * - Interface has MULTIPLE methods to implement
         * - You're extending a concrete/abstract CLASS (not just interface)
         * - You need 'this' to refer to the anonymous instance itself
         * - You need to maintain state (fields in the anonymous class)
         */

        // Lambda (functional interface = 1 method)
        Comparator<String> lambdaComp = (a, b) -> a.length() - b.length();

        // Anonymous class (same thing, but more verbose)
        Comparator<String> anonComp = new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.length() - b.length();
            }
        };

        System.out.println("Lambda result: " + lambdaComp.compare("hi", "hello"));
        System.out.println("Anon result:   " + anonComp.compare("hi", "hello"));

        // ⚠️ GOTCHA: 'this' means different things!
        System.out.println("\n--- 'this' difference ---");
        Runnable lambdaThis = () -> {
            // 'this' refers to the ENCLOSING class (InnerClassesDemo instance or main)
            System.out.println("Lambda: 'this' is the enclosing scope");
        };

        Runnable anonThis = new Runnable() {
            @Override
            public void run() {
                // 'this' refers to the anonymous class instance itself
                System.out.println("Anonymous: 'this' is " + this.getClass().getSimpleName());
            }
        };

        lambdaThis.run();
        anonThis.run();

        System.out.println("\n✅ All demos completed successfully!");
    }
}

/*
 * ╔═══════════════════════════════════════════════════════════════════╗
 * ║ INNER CLASS TYPES COMPARISON ║
 * ╠═══════════════════════════════════════════════════════════════════╣
 * ║ Type │ Access to Outer │ Needs Outer │ Use Case ║
 * ║─────────────────┼─────────────────┼─────────────┼────────────────║
 * ║ Static nested │ static only │ No │ Helper/utility ║
 * ║ Inner │ all (+ private) │ Yes │ Tight coupling ║
 * ║ Local │ all + eff.final │ If non-stat │ One method use ║
 * ║ Anonymous │ all + eff.final │ If non-stat │ One-shot impl ║
 * ╠═══════════════════════════════════════════════════════════════════╣
 * ║ 📌 Prefer static nested unless you NEED access to outer state. ║
 * ║ 📌 Prefer lambdas over anonymous classes for functional ifaces. ║
 * ╚═══════════════════════════════════════════════════════════════════╝
 */
