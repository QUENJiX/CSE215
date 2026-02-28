/**
 * ObjectClassMethodsDemo.java — The Object Class: equals, hashCode, toString, clone
 * ====================================================================================
 * CSE215 - Programming Language II
 *
 * 💡 INTUITION: Every Java class implicitly extends java.lang.Object.
 *    Object provides methods that EVERY class inherits.
 *    Three of them are especially important to override correctly:
 *      - toString()   → "How should this object print?"
 *      - equals()     → "When are two objects 'the same'?"
 *      - hashCode()   → "What's this object's hash?" (must agree with equals!)
 *
 * Topics covered:
 *   1. toString() — readable representation
 *   2. equals() — logical equality (vs == reference equality)
 *   3. hashCode() — the equals/hashCode contract
 *   4. clone() — copying objects (Cloneable interface)
 *   5. getClass() and instanceof
 *   6. The equals/hashCode contract explained visually
 *
 * 🔗 SEE ALSO: p05_Collections/HashMapDemo.java (why hashCode matters for HashMaps)
 */
package p02_OOP;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ObjectClassMethodsDemo {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   OBJECT CLASS METHODS DEMO                 ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // =====================================================================
        // SECTION 1: toString()
        // =====================================================================
        System.out.println("=== 1. toString() ===");

        /**
         * 💡 Default toString() returns: ClassName@hexHashCode
         * This is almost useless for debugging! Always override it.
         */

        Object obj = new Object();
        System.out.println("Default Object.toString(): " + obj.toString());

        // Our custom class with overridden toString
        Book book1 = new Book("Clean Code", "Robert Martin", 2008);
        System.out.println("Custom toString(): " + book1); // println automatically calls toString()

        // =====================================================================
        // SECTION 2: equals()
        // =====================================================================
        System.out.println("\n=== 2. equals() — LOGICAL EQUALITY ===");

        /**
         * 📌 RULE:
         * == compares REFERENCES (are these the same object in memory?)
         * equals() compares CONTENT (are these logically equivalent?)
         *
         * 💡 INTUITION:
         * Two copies of the same book are .equals() even though they're
         * physically different books (different references).
         *
         * == is asking: "Are these the SAME physical book?"
         * .equals() asks: "Do these books have the same content?"
         */

        Book book2 = new Book("Clean Code", "Robert Martin", 2008);
        Book book3 = new Book("Effective Java", "Joshua Bloch", 2018);
        Book book4 = book1; // Same reference

        System.out.println("book1 == book2:       " + (book1 == book2)); // false (different objects)
        System.out.println("book1 == book4:       " + (book1 == book4)); // true (same reference)
        System.out.println("book1.equals(book2):  " + book1.equals(book2)); // true (same content)
        System.out.println("book1.equals(book3):  " + book1.equals(book3)); // false (different content)
        System.out.println("book1.equals(null):   " + book1.equals(null)); // false (always)
        System.out.println("book1.equals(\"str\"): " + book1.equals("str")); // false (different type)

        /**
         * 📌 THE equals() CONTRACT — Any correct equals() must satisfy:
         *
         * 1. REFLEXIVE: x.equals(x) is true
         * 2. SYMMETRIC: x.equals(y) ↔ y.equals(x)
         * 3. TRANSITIVE: x.equals(y) && y.equals(z) → x.equals(z)
         * 4. CONSISTENT: multiple calls return the same result
         * 5. NULL-SAFE: x.equals(null) is false
         */

        // =====================================================================
        // SECTION 3: hashCode()
        // =====================================================================
        System.out.println("\n=== 3. hashCode() — THE EQUALS/HASHCODE CONTRACT ===");

        /**
         * 📌 THE CONTRACT:
         * If a.equals(b) is TRUE → a.hashCode() MUST equal b.hashCode()
         * If a.equals(b) is FALSE → hashCodes CAN be equal (collision) but ideally
         * shouldn't
         *
         * 💡 WHY THIS MATTERS:
         * HashMap and HashSet use hashCode() to decide which "bucket" to put an
         * object in. If equals() says two objects are the same but they have
         * different hashCodes, the HashMap BREAKS — it won't find them!
         *
         * Think of hashCode like a library section number:
         * Two copies of the same book should be in the same section.
         * Different books CAN be in the same section (that's OK — a "collision").
         * But same books in different sections = lost forever!
         */

        System.out.println("book1.hashCode(): " + book1.hashCode());
        System.out.println("book2.hashCode(): " + book2.hashCode());
        System.out.println("Equal books, same hashCode? " + (book1.hashCode() == book2.hashCode()));

        // Demonstrating why hashCode matters for HashSet
        System.out.println("\n--- HashSet requires correct hashCode ---");
        Set<Book> bookSet = new HashSet<>();
        bookSet.add(book1);
        bookSet.add(book2); // Same content as book1 — should be treated as duplicate

        System.out.println("Added book1 and book2 (same content) to HashSet");
        System.out.println("Set size: " + bookSet.size()); // Should be 1 if hashCode is correct

        // =====================================================================
        // SECTION 4: clone()
        // =====================================================================
        System.out.println("\n=== 4. clone() — COPYING OBJECTS ===");

        /**
         * 💡 SHALLOW COPY vs DEEP COPY:
         *
         * Shallow copy: copies field values as-is.
         * For primitives → copies values ✅
         * For objects → copies REFERENCES (both point to same object!) ⚠️
         *
         * Deep copy: recursively copies all referenced objects too.
         * Completely independent copy.
         */

        Book original = new Book("Design Patterns", "GoF", 1994);
        Book cloned = original.clone();

        System.out.println("Original: " + original);
        System.out.println("Cloned:   " + cloned);
        System.out.println("original == cloned:       " + (original == cloned)); // false — different objects
        System.out.println("original.equals(cloned):  " + original.equals(cloned)); // true — same content

        // =====================================================================
        // SECTION 5: getClass() and instanceof
        // =====================================================================
        System.out.println("\n=== 5. getClass() AND instanceof ===");

        System.out.println("book1.getClass():            " + book1.getClass());
        System.out.println("book1.getClass().getName():   " + book1.getClass().getName());
        System.out.println("book1.getClass().getSimpleName(): " + book1.getClass().getSimpleName());

        System.out.println("\nbook1 instanceof Book:   " + (book1 instanceof Book));
        System.out.println("book1 instanceof Object: " + (book1 instanceof Object)); // Always true!

        /**
         * ⚠️ GOTCHA: getClass() vs instanceof in equals():
         *
         * getClass() is strict: only exact same class matches
         * → new Dog().getClass() == new Animal().getClass() → false
         *
         * instanceof is lenient: includes subclasses
         * → new Dog() instanceof Animal → true
         *
         * ✅ BEST PRACTICE: Use getClass() in equals() to prevent
         * weird asymmetric equality between parent and child objects.
         */

        System.out.println("\n✅ All demos completed successfully!");
    }
}

// =========================================================================
// BOOK CLASS — Demonstrates proper equals, hashCode, toString, clone
// =========================================================================

/**
 * A properly implemented class with all Object methods overridden correctly.
 * Use this as a TEMPLATE for your own classes!
 */
class Book implements Cloneable {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    // --- GETTERS ---
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    // =========================================================================
    // toString — Always override! Makes debugging SO much easier.
    // =========================================================================
    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author + "', year=" + year + "}";
    }

    // =========================================================================
    // equals — Step-by-step template you can follow for ANY class
    // =========================================================================
    @Override
    public boolean equals(Object obj) {
        // Step 1: Check if same reference
        if (this == obj)
            return true;

        // Step 2: Check if null or different class
        if (obj == null || getClass() != obj.getClass())
            return false;

        // Step 3: Cast to our type (safe after step 2)
        Book other = (Book) obj;

        // Step 4: Compare all significant fields
        return year == other.year // primitive: use ==
                && Objects.equals(title, other.title) // object: use Objects.equals (null-safe)
                && Objects.equals(author, other.author);
    }

    // =========================================================================
    // hashCode — MUST override whenever you override equals!
    // =========================================================================
    @Override
    public int hashCode() {
        // Objects.hash is the easiest correct implementation
        return Objects.hash(title, author, year);
    }

    // =========================================================================
    // clone — Returns a copy of this object
    // =========================================================================
    @Override
    public Book clone() {
        try {
            return (Book) super.clone(); // Shallow copy (OK for immutable fields like String)
        } catch (CloneNotSupportedException e) {
            // Won't happen since we implement Cloneable, but required by compiler
            throw new AssertionError("Clone not supported", e);
        }
    }
}

/*
 * ╔══════════════════════════════════════════════════════════════════╗
 * ║ TEMPLATE: Implementing equals() and hashCode() Correctly ║
 * ╠══════════════════════════════════════════════════════════════════╣
 * ║ ║
 * ║ @Override ║
 * ║ public boolean equals(Object obj) { ║
 * ║ if (this == obj) return true; // same ref ║
 * ║ if (obj == null) return false; // null ║
 * ║ if (getClass() != obj.getClass()) return false; // type ║
 * ║ MyClass other = (MyClass) obj; // cast ║
 * ║ return field1 == other.field1 // compare ║
 * ║ && Objects.equals(field2, other.field2); ║
 * ║ } ║
 * ║ ║
 * ║ @Override ║
 * ║ public int hashCode() { ║
 * ║ return Objects.hash(field1, field2); ║
 * ║ } ║
 * ║ ║
 * ╚══════════════════════════════════════════════════════════════════╝
 */
