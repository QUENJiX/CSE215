/**
 * GenericClassDemo.java — Generics: Type-Safe, Reusable Code
 * ==============================================================
 * CSE215 - Programming Language II
 *
 * 💡 INTUITION: Generics let you write ONE class/method that works with ANY type,
 *    while still catching type errors at COMPILE time (not runtime).
 *
 *    Without generics: List list = new ArrayList(); list.add("hello"); int x = (int) list.get(0); // ClassCastException at RUNTIME!
 *    With generics:    List<String> list = new ArrayList<>(); // Compile error if you add non-String!
 *
 * 📌 COMMON TYPE PARAMETERS:
 *    T = Type, E = Element, K = Key, V = Value, N = Number
 *
 * ⚠️ GOTCHA (Type Erasure): Generics are a COMPILE-TIME feature.
 *    At runtime, ArrayList<String> and ArrayList<Integer> are the same class!
 *    You cannot do: new T(), instanceof T, or T.class at runtime.
 *
 * 🔗 SEE ALSO: p05_Collections/ArrayListDemo.java (generics in practice)
 * 🔗 SEE ALSO: p03_Interfaces/ComparableComparatorDemo.java (bounded types)
 */
package p05_Collections;

import java.util.ArrayList;
import java.util.List;

public class GenericClassDemo {
    public static void main(String[] args) {
        System.out.println("=== Generics Demo ===\n");

        // === Generic Class Usage ===
        System.out.println("--- Generic Box Class ---");

        // Box for String
        Box<String> stringBox = new Box<>();
        stringBox.set("Hello, Generics!");
        System.out.println("String box contains: " + stringBox.get());

        // Box for Integer
        Box<Integer> intBox = new Box<>();
        intBox.set(42);
        System.out.println("Integer box contains: " + intBox.get());

        // Box for custom object
        Box<Student> studentBox = new Box<>();
        studentBox.set(new Student("Alice", 3.8));
        System.out.println("Student box contains: " + studentBox.get());

        // === Generic Class with Multiple Type Parameters ===
        System.out.println("\n--- Pair Class (Two Type Parameters) ---");
        Pair<String, Integer> nameAge = new Pair<>("Bob", 25);
        System.out.println("Name: " + nameAge.getFirst() + ", Age: " + nameAge.getSecond());

        Pair<Integer, Double> scoreGrade = new Pair<>(95, 4.0);
        System.out.println("Score: " + scoreGrade.getFirst() + ", GPA: " + scoreGrade.getSecond());

        // === Generic Methods ===
        System.out.println("\n--- Generic Methods ---");

        // Method works with any type
        String[] names = { "Alice", "Bob", "Charlie" };
        Integer[] numbers = { 1, 2, 3, 4, 5 };
        Double[] prices = { 19.99, 29.99, 39.99 };

        System.out.println("First name: " + getFirst(names));
        System.out.println("First number: " + getFirst(numbers));
        System.out.println("First price: " + getFirst(prices));

        // Print array with generic method
        System.out.println("\nPrinting arrays:");
        printArray(names);
        printArray(numbers);

        // === Bounded Type Parameters ===
        System.out.println("\n--- Bounded Type Parameters ---");

        // Only works with Number or its subclasses
        System.out.println("Max of integers: " + findMax(10, 20, 15));
        System.out.println("Max of doubles: " + findMax(3.14, 2.71, 1.41));

        // === Wildcards ===
        System.out.println("\n--- Wildcards ---");

        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);

        List<Double> doubleList = new ArrayList<>();
        doubleList.add(1.1);
        doubleList.add(2.2);
        doubleList.add(3.3);

        List<String> stringList = new ArrayList<>();
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");

        // Upper bounded wildcard: ? extends Number
        System.out.println("Sum of integers: " + sumNumbers(intList));
        System.out.println("Sum of doubles: " + sumNumbers(doubleList));

        // Unbounded wildcard: ?
        System.out.println("\nPrinting lists:");
        printList(intList);
        printList(stringList);

        // === Generic Interface ===
        System.out.println("\n--- Generic Interface ---");
        Comparable<Student> student1 = new Student("Alice", 3.8);
        Student student2 = new Student("Bob", 3.5);
        System.out.println("Comparison: " + student1.compareTo(student2));

        // === Real-World Example: Generic Stack ===
        System.out.println("\n--- Generic Stack Example ---");
        GenericStack<String> stack = new GenericStack<>();
        stack.push("First");
        stack.push("Second");
        stack.push("Third");

        System.out.println("Stack size: " + stack.size());
        System.out.println("Top element: " + stack.peek());

        while (!stack.isEmpty()) {
            System.out.println("Popped: " + stack.pop());
        }
    }

    // === Generic Methods ===

    // Generic method with type parameter <T>
    public static <T> T getFirst(T[] array) {
        if (array.length > 0) {
            return array[0];
        }
        return null;
    }

    // Generic method to print array
    public static <T> void printArray(T[] array) {
        System.out.print("  [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    // Bounded type parameter: T must extend Number
    public static <T extends Number & Comparable<T>> T findMax(T a, T b, T c) {
        T max = a;
        if (b.compareTo(max) > 0)
            max = b;
        if (c.compareTo(max) > 0)
            max = c;
        return max;
    }

    // Upper bounded wildcard
    public static double sumNumbers(List<? extends Number> numbers) {
        double sum = 0;
        for (Number n : numbers) {
            sum += n.doubleValue();
        }
        return sum;
    }

    // Unbounded wildcard
    public static void printList(List<?> list) {
        System.out.print("  ");
        for (Object item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}

/**
 * Generic class with single type parameter
 */
class Box<T> {
    private T content;

    public void set(T content) {
        this.content = content;
    }

    public T get() {
        return content;
    }

    public boolean isEmpty() {
        return content == null;
    }
}

/**
 * Generic class with two type parameters
 */
class Pair<K, V> {
    private K first;
    private V second;

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}

/**
 * Generic Stack implementation
 */
class GenericStack<E> {
    private ArrayList<E> elements = new ArrayList<>();

    public void push(E element) {
        elements.add(element);
    }

    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return elements.remove(elements.size() - 1);
    }

    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return elements.get(elements.size() - 1);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public int size() {
        return elements.size();
    }
}

/**
 * Student class implementing Comparable
 */
class Student implements Comparable<Student> {
    private String name;
    private double gpa;

    public Student(String name, double gpa) {
        this.name = name;
        this.gpa = gpa;
    }

    @Override
    public int compareTo(Student other) {
        return Double.compare(this.gpa, other.gpa);
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', gpa=" + gpa + "}";
    }
}

/*
 * ========================================
 * GENERICS SUMMARY
 * ========================================
 * 
 * TYPE PARAMETERS:
 * - T: Type
 * - E: Element
 * - K: Key
 * - V: Value
 * - N: Number
 * 
 * WILDCARDS:
 * - <?> : Unbounded - accepts any type
 * - <? extends T> : Upper bounded - T or subclass
 * - <? super T> : Lower bounded - T or superclass
 * 
 * BENEFITS:
 * - Type safety at compile time
 * - Elimination of casts
 * - Code reusability
 */
