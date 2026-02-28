/**
 * ComparableComparatorDemo.java — Sorting Objects with Comparable & Comparator
 * ==============================================================================
 * CSE215 - Programming Language II
 *
 * 💡 INTUITION:
 *   Comparable → "I know how to compare MYSELF to another object" (natural order)
 *   Comparator → "I'm an EXTERNAL judge that knows how to compare two objects" (custom order)
 *
 *   Think of it as:
 *     Comparable = students who can rank themselves by GPA (built-in)
 *     Comparator = a teacher who can rank students by name, age, or any criteria (external)
 *
 * Topics covered:
 *   1. Comparable interface (natural ordering)
 *   2. Comparator interface (custom ordering)
 *   3. Comparator factory methods (comparing, thenComparing, reversed)
 *   4. Multi-field sorting
 *   5. Sorting arrays and lists
 *
 * 🔗 SEE ALSO: p05_Collections/ArrayListDemo.java, p08_LambdasAndStreams/StreamsDemo.java
 */
package p03_Interfaces;

import java.util.*;

public class ComparableComparatorDemo {

    // =========================================================================
    // STUDENT CLASS: Implements Comparable for natural ordering by GPA
    // =========================================================================

    static class StudentRecord implements Comparable<StudentRecord> {
        private String name;
        private int age;
        private double gpa;

        public StudentRecord(String name, int age, double gpa) {
            this.name = name;
            this.age = age;
            this.gpa = gpa;
        }

        // Getters
        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public double getGpa() {
            return gpa;
        }

        /**
         * 📌 RULE — compareTo() must return:
         * negative if this < other
         * 0 if this == other
         * positive if this > other
         *
         * Natural order for students: by GPA descending (highest first)
         */
        @Override
        public int compareTo(StudentRecord other) {
            return Double.compare(other.gpa, this.gpa); // Descending by GPA
        }

        @Override
        public String toString() {
            return String.format("%-10s age=%d  GPA=%.2f", name, age, gpa);
        }
    }

    // =========================================================================
    // MAIN
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   COMPARABLE & COMPARATOR DEMO              ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        List<StudentRecord> students = new ArrayList<>(Arrays.asList(
                new StudentRecord("Alice", 20, 3.8),
                new StudentRecord("Bob", 22, 3.5),
                new StudentRecord("Charlie", 19, 3.9),
                new StudentRecord("Diana", 21, 3.5),
                new StudentRecord("Eve", 20, 3.7),
                new StudentRecord("Frank", 22, 4.0)));

        // =====================================================================
        // SECTION 1: COMPARABLE (Natural Ordering)
        // =====================================================================
        System.out.println("=== 1. COMPARABLE — Natural Order (GPA descending) ===");

        List<StudentRecord> list1 = new ArrayList<>(students);
        Collections.sort(list1); // Uses compareTo()
        list1.forEach(s -> System.out.println("  " + s));

        // =====================================================================
        // SECTION 2: COMPARATOR — Custom Ordering
        // =====================================================================
        System.out.println("\n=== 2. COMPARATOR — Custom Orders ===");

        /**
         * 💡 THREE WAYS to create a Comparator:
         *
         * 1. Anonymous class (old way)
         * 2. Lambda expression (recommended)
         * 3. Comparator.comparing() factory method (most readable)
         */

        // --- Sort by name (alphabetical) ---
        System.out.println("\n--- By Name (A-Z) ---");
        List<StudentRecord> byName = new ArrayList<>(students);

        // Way 1: Anonymous class (verbose — old style)
        // Comparator<StudentRecord> nameComparator = new Comparator<StudentRecord>() {
        // @Override
        // public int compare(StudentRecord a, StudentRecord b) {
        // return a.getName().compareTo(b.getName());
        // }
        // };

        // Way 2: Lambda (cleaner)
        // byName.sort((a, b) -> a.getName().compareTo(b.getName()));

        // Way 3: Comparator.comparing (most readable ✅)
        byName.sort(Comparator.comparing(StudentRecord::getName));
        byName.forEach(s -> System.out.println("  " + s));

        // --- Sort by age ---
        System.out.println("\n--- By Age (youngest first) ---");
        List<StudentRecord> byAge = new ArrayList<>(students);
        byAge.sort(Comparator.comparingInt(StudentRecord::getAge));
        byAge.forEach(s -> System.out.println("  " + s));

        // --- Sort by age DESCENDING ---
        System.out.println("\n--- By Age (oldest first) — .reversed() ---");
        List<StudentRecord> byAgeDesc = new ArrayList<>(students);
        byAgeDesc.sort(Comparator.comparingInt(StudentRecord::getAge).reversed());
        byAgeDesc.forEach(s -> System.out.println("  " + s));

        // =====================================================================
        // SECTION 3: MULTI-FIELD SORTING with thenComparing()
        // =====================================================================
        System.out.println("\n=== 3. MULTI-FIELD SORTING ===");

        /**
         * 💡 INTUITION: thenComparing() is the tiebreaker.
         * "Sort by GPA descending, and if two students have the same GPA, sort by
         * name."
         */

        System.out.println("--- By GPA desc, then by name asc (tiebreaker) ---");
        List<StudentRecord> multiSort = new ArrayList<>(students);
        multiSort.sort(
                Comparator.comparingDouble(StudentRecord::getGpa).reversed()
                        .thenComparing(StudentRecord::getName));
        multiSort.forEach(s -> System.out.println("  " + s));

        System.out.println("\n--- By Age asc, then by GPA desc, then by Name ---");
        List<StudentRecord> tripleSort = new ArrayList<>(students);
        tripleSort.sort(
                Comparator.comparingInt(StudentRecord::getAge)
                        .thenComparing(Comparator.comparingDouble(StudentRecord::getGpa).reversed())
                        .thenComparing(StudentRecord::getName));
        tripleSort.forEach(s -> System.out.println("  " + s));

        // =====================================================================
        // SECTION 4: SORTING ARRAYS
        // =====================================================================
        System.out.println("\n=== 4. SORTING ARRAYS ===");

        String[] names = { "Charlie", "Alice", "Eve", "Bob", "Diana" };
        System.out.println("Original: " + Arrays.toString(names));

        Arrays.sort(names); // Natural order (alphabetical)
        System.out.println("Sorted:   " + Arrays.toString(names));

        Arrays.sort(names, Comparator.reverseOrder()); // Reverse
        System.out.println("Reversed: " + Arrays.toString(names));

        Arrays.sort(names, Comparator.comparingInt(String::length)); // By length
        System.out.println("By length: " + Arrays.toString(names));

        // =====================================================================
        // SECTION 5: USEFUL COMPARATOR UTILITIES
        // =====================================================================
        System.out.println("\n=== 5. COMPARATOR UTILITIES ===");

        // nullsFirst / nullsLast — handle null values gracefully
        List<String> withNulls = new ArrayList<>(Arrays.asList("Banana", null, "Apple", null, "Cherry"));
        withNulls.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
        System.out.println("nullsFirst: " + withNulls);

        withNulls.sort(Comparator.nullsLast(Comparator.naturalOrder()));
        System.out.println("nullsLast:  " + withNulls);

        // Find min/max using Comparator
        StudentRecord youngest = Collections.min(students,
                Comparator.comparingInt(StudentRecord::getAge));
        StudentRecord highest = Collections.max(students,
                Comparator.comparingDouble(StudentRecord::getGpa));
        System.out.println("\nYoungest: " + youngest);
        System.out.println("Highest GPA: " + highest);

        System.out.println("\n✅ All demos completed successfully!");
    }
}

/*
 * ╔══════════════════════════════════════════════════════════════════╗
 * ║ COMPARABLE vs COMPARATOR — Quick Reference ║
 * ╠══════════════════════════════════════════════════════════════════╣
 * ║ Feature │ Comparable │ Comparator ║
 * ╠══════════════════════════════════════════════════════════════════╣
 * ║ Package │ java.lang │ java.util ║
 * ║ Method │ compareTo(T) │ compare(T, T) ║
 * ║ Usage │ class implements it │ external object ║
 * ║ Sort call │ Collections.sort(l) │ list.sort(comparator) ║
 * ║ # orderings │ ONE (natural order) │ MANY (any criteria) ║
 * ║ Modifies class? │ YES (implements) │ NO (separate) ║
 * ╠══════════════════════════════════════════════════════════════════╣
 * ║ ✅ BEST PRACTICE: ║
 * ║ Use Comparable for the ONE "obvious" ordering (e.g., by ID) ║
 * ║ Use Comparator for all other custom orderings ║
 * ╚══════════════════════════════════════════════════════════════════╝
 */
