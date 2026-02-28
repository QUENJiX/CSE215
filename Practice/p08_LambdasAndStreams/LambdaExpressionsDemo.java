/**
 * LambdaExpressionsDemo.java — Lambda Syntax, Closures, Scoping & Patterns
 * ==========================================================================
 * CSE215 - Programming Language II
 *
 * 💡 INTUITION: A lambda is an ANONYMOUS FUNCTION — a piece of code you can
 *    pass around like a value. Instead of writing a whole class/method just to
 *    hold some behavior, you write it inline.
 *
 *    Before lambdas (Java 7-):
 *      button.addListener(new ActionListener() {
 *          @Override public void actionPerformed(ActionEvent e) {
 *              System.out.println("Clicked!");
 *          }
 *      });
 *
 *    With lambdas (Java 8+):
 *      button.addListener(e -> System.out.println("Clicked!"));
 *
 * Topics covered:
 *   1. Lambda syntax (all variations)
 *   2. Target typing (how Java infers the type)
 *   3. Variable capture and "effectively final"
 *   4. Common lambda patterns
 *   5. Lambda vs anonymous class
 *
 * 🔗 SEE ALSO: p03_Interfaces/FunctionalInterfaceDemo.java, p08_LambdasAndStreams/StreamsDemo.java
 */
package p08_LambdasAndStreams;

import java.util.*;
import java.util.function.*;

public class LambdaExpressionsDemo {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   LAMBDA EXPRESSIONS DEMO                    ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // =====================================================================
        // SECTION 1: LAMBDA SYNTAX — ALL VARIATIONS
        // =====================================================================
        System.out.println("=== 1. LAMBDA SYNTAX ===");

        /**
         * 💡 SYNTAX FORMS:
         *
         * Full form: (Type param1, Type param2) -> { statements; return value; }
         * Inferred types: (param1, param2) -> { statements; return value; }
         * Single param: param -> { statements; return value; }
         * Expression: (a, b) -> a + b // No braces, no return keyword
         * No params: () -> expression
         *
         * 📌 RULES:
         * - If one parameter and no type → parentheses optional: x -> x * 2
         * - If body is a single expression → braces and return optional
         * - If body has multiple statements → MUST use braces + return
         */

        // No parameters
        Runnable noArgs = () -> System.out.println("  No parameters lambda!");
        noArgs.run();

        // One parameter (parens optional)
        Consumer<String> oneArg = msg -> System.out.println("  One param: " + msg);
        oneArg.accept("Hello!");

        // Two parameters (parens required)
        BinaryOperator<Integer> twoArgs = (a, b) -> a + b;
        System.out.println("  Two params: " + twoArgs.apply(3, 4));

        // Multi-line body (braces + return required)
        Function<Integer, String> multiLine = n -> {
            String result;
            if (n > 0)
                result = "positive";
            else if (n < 0)
                result = "negative";
            else
                result = "zero";
            return result;
        };
        System.out.println("  Multi-line: 5 is " + multiLine.apply(5));

        // Explicit parameter types (usually unnecessary)
        BiFunction<String, Integer, String> explicitTypes = (String text, Integer count) -> text.repeat(count);
        System.out.println("  Explicit types: " + explicitTypes.apply("Ha", 3));

        // =====================================================================
        // SECTION 2: LAMBDAS WITH COLLECTIONS
        // =====================================================================
        System.out.println("\n=== 2. LAMBDAS WITH COLLECTIONS ===");

        List<String> names = new ArrayList<>(Arrays.asList(
                "Charlie", "Alice", "Eve", "Bob", "Diana"));

        // forEach with lambda
        System.out.println("--- forEach ---");
        names.forEach(name -> System.out.println("  " + name));

        // Sort with lambda comparator
        System.out.println("\n--- Sorting with lambda ---");
        names.sort((a, b) -> a.compareTo(b)); // Ascending
        System.out.println("Alphabetical: " + names);

        names.sort((a, b) -> Integer.compare(a.length(), b.length())); // By length
        System.out.println("By length: " + names);

        // removeIf with lambda predicate
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        numbers.removeIf(n -> n % 2 == 0); // Remove even numbers
        System.out.println("After removing evens: " + numbers);

        // replaceAll with lambda
        List<String> greetings = new ArrayList<>(Arrays.asList("hello", "world", "java"));
        greetings.replaceAll(s -> s.toUpperCase());
        System.out.println("After replaceAll toUpper: " + greetings);

        // Map forEach
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        scores.put("Charlie", 92);

        System.out.println("\n--- Map forEach ---");
        scores.forEach((name, score) -> System.out.println("  " + name + ": " + score));

        // =====================================================================
        // SECTION 3: VARIABLE CAPTURE (Closures)
        // =====================================================================
        System.out.println("\n=== 3. VARIABLE CAPTURE (Closures) ===");

        /**
         * 💡 INTUITION: Lambdas can "capture" (use) variables from their surrounding
         * scope.
         * BUT the captured variable must be EFFECTIVELY FINAL — meaning its value
         * never changes after assignment.
         *
         * ⚠️ WHY? Because the lambda might execute later (e.g., in another thread).
         * If the variable could change, it would cause unpredictable behavior.
         *
         * String prefix = "Hello";
         * Consumer<String> greeter = name -> System.out.println(prefix + " " + name);
         * // prefix = "Hi"; ← ❌ COMPILE ERROR: prefix must be effectively final
         */

        String prefix = ">>>"; // Effectively final (never reassigned)
        int multiplier = 3; // Effectively final

        Consumer<String> prefixed = s -> System.out.println(prefix + " " + s);
        prefixed.accept("This uses a captured variable");

        Function<Integer, Integer> tripler = n -> n * multiplier;
        System.out.println("tripler(7): " + tripler.apply(7));

        // Workaround for mutable state: use a single-element array or AtomicInteger
        final int[] counter = { 0 }; // Array reference is final, but contents can change
        List.of("a", "b", "c").forEach(item -> counter[0]++);
        System.out.println("Counter: " + counter[0]);

        // =====================================================================
        // SECTION 4: LAMBDA AS METHOD PARAMETER
        // =====================================================================
        System.out.println("\n=== 4. LAMBDA AS METHOD PARAMETER ===");

        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Passing different lambdas to the same method
        System.out.println("Even numbers: " + filterList(data, n -> n % 2 == 0));
        System.out.println("Greater than 5: " + filterList(data, n -> n > 5));
        System.out.println("Divisible by 3: " + filterList(data, n -> n % 3 == 0));

        // Passing different transformations
        System.out.println("Doubled: " + transformList(data, n -> n * 2));
        System.out.println("Squared: " + transformList(data, n -> n * n));
        System.out.println("Negated: " + transformList(data, n -> -n));

        // =====================================================================
        // SECTION 5: LAMBDA vs ANONYMOUS CLASS
        // =====================================================================
        System.out.println("\n=== 5. LAMBDA vs ANONYMOUS CLASS ===");

        /**
         * Lambda advantages over anonymous classes:
         * ✅ More concise (less boilerplate)
         * ✅ 'this' refers to enclosing class (not the lambda itself)
         * ✅ Slightly better performance (no extra .class file generated)
         *
         * When you MUST use anonymous class instead:
         * ❌ Interface has multiple abstract methods (not functional)
         * ❌ You need to reference 'this' as the anonymous class itself
         * ❌ You're implementing an abstract CLASS (not interface)
         */

        // Anonymous class — verbose
        Comparator<String> anonComparator = new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.length() - b.length();
            }
        };

        // Lambda — concise and equivalent
        Comparator<String> lambdaComparator = (a, b) -> a.length() - b.length();

        // Method reference — even more concise (see MethodReferenceDemo.java)
        Comparator<String> methodRefComparator = Comparator.comparingInt(String::length);

        List<String> test = new ArrayList<>(Arrays.asList("Elephant", "Cat", "Dog", "Hippopotamus"));
        test.sort(lambdaComparator);
        System.out.println("Sorted by length: " + test);

        // =====================================================================
        // SECTION 6: COMMON LAMBDA PATTERNS
        // =====================================================================
        System.out.println("\n=== 6. COMMON PATTERNS ===");

        // Event handler pattern
        System.out.println("--- Event Handler Pattern ---");
        performAction("Click", event -> System.out.println("  Handling click event: " + event));
        performAction("Hover", event -> System.out.println("  Handling hover event: " + event));

        // Factory pattern with Supplier
        System.out.println("\n--- Factory Pattern ---");
        List<String> fromFactory = createAndFill(5, () -> "Item-" + UUID.randomUUID().toString().substring(0, 4));
        System.out.println("Factory list: " + fromFactory);

        // Conditional execution
        System.out.println("\n--- Conditional Execution ---");
        executeIf(true, () -> System.out.println("  Condition was true!"));
        executeIf(false, () -> System.out.println("  This won't print."));

        System.out.println("\n✅ All demos completed successfully!");
    }

    // =========================================================================
    // HELPER METHODS
    // =========================================================================

    /** Filter a list using a Predicate lambda */
    public static <T> List<T> filterList(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item))
                result.add(item);
        }
        return result;
    }

    /** Transform a list using a Function lambda */
    public static <T, R> List<R> transformList(List<T> list, Function<T, R> transformer) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(transformer.apply(item));
        }
        return result;
    }

    /** Simulate event handling with a Consumer */
    public static void performAction(String event, Consumer<String> handler) {
        handler.accept(event);
    }

    /** Create a list of n items using a Supplier factory */
    public static <T> List<T> createAndFill(int n, Supplier<T> factory) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < n; i++)
            list.add(factory.get());
        return list;
    }

    /** Execute a Runnable only if condition is true */
    public static void executeIf(boolean condition, Runnable action) {
        if (condition)
            action.run();
    }
}
