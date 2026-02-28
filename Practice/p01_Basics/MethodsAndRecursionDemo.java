/**
 * MethodsAndRecursionDemo.java — Methods, Parameters, Overloading, Varargs & Recursion
 * =====================================================================================
 * CSE215 - Programming Language II
 *
 * 💡 INTUITION: Methods are reusable blocks of code — like recipes.
 *    You define them once and "call" them whenever you need that behavior.
 *    This is the backbone of writing clean, maintainable, DRY (Don't Repeat Yourself) code.
 *
 * Topics covered:
 *   1. Method anatomy (return type, name, parameters, body)
 *   2. Pass-by-value semantics (primitives vs references)
 *   3. Method overloading (same name, different signatures)
 *   4. Varargs (variable-length arguments)
 *   5. Recursion (a method calling itself)
 *   6. Recursion vs Iteration comparison
 *
 * 🔗 SEE ALSO: p02_OOP/StaticAndFinalDemo.java for static vs instance methods
 */
package p01_Basics;

import java.util.Arrays;

public class MethodsAndRecursionDemo {

    // =========================================================================
    // SECTION 1: METHOD ANATOMY
    // =========================================================================

    /**
     * 📌 RULE — A method signature = name + parameter types.
     *
     * accessModifier returnType methodName(parameterType paramName) {
     * // body
     * return value; // (if returnType is not void)
     * }
     */

    // Method that takes parameters and returns a value
    public static int add(int a, int b) {
        return a + b;
    }

    // Method that returns nothing (void)
    public static void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }

    // Method with no parameters
    public static double getPi() {
        return Math.PI;
    }

    // =========================================================================
    // SECTION 2: PASS-BY-VALUE (This is THE most misunderstood Java concept)
    // =========================================================================

    /**
     * 💡 INTUITION — Java is ALWAYS pass-by-value. Period.
     *
     * For primitives: the VALUE is copied → changes inside method don't affect
     * caller.
     * For objects: the REFERENCE (address) is copied → you CAN modify the object's
     * fields, but you CANNOT make the caller's variable point elsewhere.
     *
     * Think of it like giving someone a COPY of your house address:
     * - They can go to your house and rearrange furniture (modify fields) ✅
     * - They can scribble a new address on THEIR copy (reassign reference) ❌
     * doesn't affect yours
     */

    public static void tryToChangePrimitive(int x) {
        x = 999; // This modifies the LOCAL copy only
    }

    public static void modifyArray(int[] arr) {
        arr[0] = 999; // This DOES change the original — same object, different reference copy
    }

    public static void tryToReassignArray(int[] arr) {
        arr = new int[] { -1, -1, -1 }; // This points the LOCAL copy to a new array
        // The caller's reference is UNCHANGED
    }

    // =========================================================================
    // SECTION 3: METHOD OVERLOADING (Compile-time Polymorphism)
    // =========================================================================

    /**
     * 📌 RULE — Overloading = same method name, different parameter lists.
     * The compiler picks the right version based on argument types at compile time.
     *
     * ⚠️ GOTCHA: Return type alone does NOT distinguish overloaded methods.
     * int foo() and String foo() — WON'T compile together!
     */

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static int multiply(int a, int b, int c) {
        return a * b * c;
    }

    // =========================================================================
    // SECTION 4: VARARGS (Variable-Length Arguments)
    // =========================================================================

    /**
     * 💡 INTUITION — Varargs let a method accept 0 or more arguments of the same
     * type.
     * Under the hood, Java converts them into an array.
     *
     * 📌 RULE: Varargs must be the LAST parameter, and only ONE varargs per method.
     *
     * void foo(String label, int... numbers) ✅
     * void foo(int... a, int... b) ❌ compile error
     */

    public static int sum(int... numbers) {
        // 'numbers' is actually an int[] here
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        return total;
    }

    public static String joinWords(String separator, String... words) {
        return String.join(separator, words);
    }

    // =========================================================================
    // SECTION 5: RECURSION
    // =========================================================================

    /**
     * 💡 INTUITION — Recursion = a method that calls ITSELF.
     * Every recursive method needs:
     * 1. BASE CASE — when to STOP (prevents infinite recursion)
     * 2. RECURSIVE CASE — the method calls itself with a SMALLER problem
     *
     * Think of recursion like Russian nesting dolls:
     * You open a doll, find a smaller one inside, keep opening until you reach the
     * tiny one (base case).
     */

    // Classic: Factorial n! = n × (n-1) × ... × 1
    // 5! = 5 × 4! = 5 × 4 × 3! = ... = 5 × 4 × 3 × 2 × 1 = 120
    public static long factorial(int n) {
        if (n <= 1)
            return 1; // Base case
        return n * factorial(n - 1); // Recursive case: shrink the problem
    }

    // Classic: Fibonacci fib(n) = fib(n-1) + fib(n-2)
    // 0, 1, 1, 2, 3, 5, 8, 13, 21, ...
    //
    // ⚠️ GOTCHA: This naive version is O(2^n) — exponentially slow!
    // For large n, use memoization or iteration instead.
    public static int fibonacci(int n) {
        if (n <= 0)
            return 0; // Base case 1
        if (n == 1)
            return 1; // Base case 2
        return fibonacci(n - 1) + fibonacci(n - 2); // Two recursive calls
    }

    // Better: Fibonacci with memoization (top-down DP)
    // 💡 INTUITION: "Remember" results we've already computed to avoid
    // re-computing.
    public static long fibMemo(int n, long[] memo) {
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;
        if (memo[n] != 0)
            return memo[n]; // Already computed? Return it!
        memo[n] = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
        return memo[n];
    }

    // Recursion: Power base^exp
    public static double power(double base, int exp) {
        if (exp == 0)
            return 1; // Anything^0 = 1
        if (exp < 0)
            return 1.0 / power(base, -exp); // Negative exponent
        return base * power(base, exp - 1);
    }

    // Recursion: Reverse a string
    // "Hello" → reverse("ello") + "H" → ... → "olleH"
    public static String reverse(String str) {
        if (str.length() <= 1)
            return str; // Base case
        return reverse(str.substring(1)) + str.charAt(0);
    }

    // Recursion: Binary search (finding element in a SORTED array)
    // 💡 Each call halves the search space → O(log n) — very fast!
    public static int binarySearch(int[] arr, int target, int low, int high) {
        if (low > high)
            return -1; // Base case: not found

        int mid = low + (high - low) / 2; // Avoid integer overflow vs (low+high)/2
        if (arr[mid] == target)
            return mid; // Found it!
        else if (arr[mid] < target)
            return binarySearch(arr, target, mid + 1, high); // Search right half
        else
            return binarySearch(arr, target, low, mid - 1); // Search left half
    }

    // =========================================================================
    // SECTION 6: RECURSION vs ITERATION
    // =========================================================================

    // Iterative factorial for comparison
    public static long factorialIterative(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // Iterative fibonacci for comparison
    public static long fibonacciIterative(int n) {
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;
        long prev2 = 0, prev1 = 1;
        for (int i = 2; i <= n; i++) {
            long current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }

    // =========================================================================
    // MAIN — Run all demos
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   METHODS & RECURSION DEMO                  ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // --- Section 1: Basic methods ---
        System.out.println("=== 1. METHOD BASICS ===");
        System.out.println("add(3, 4) = " + add(3, 4));
        greet("Java Learner");
        System.out.println("getPi() = " + getPi());

        // --- Section 2: Pass-by-value ---
        System.out.println("\n=== 2. PASS-BY-VALUE ===");

        int x = 10;
        tryToChangePrimitive(x);
        System.out.println("After tryToChangePrimitive(x): x = " + x + "  (unchanged — primitive copied)");

        int[] arr = { 1, 2, 3 };
        modifyArray(arr);
        System.out.println("After modifyArray(arr): " + Arrays.toString(arr) + "  (changed — same object)");

        int[] arr2 = { 1, 2, 3 };
        tryToReassignArray(arr2);
        System.out.println("After tryToReassignArray(arr2): " + Arrays.toString(arr2)
                + "  (unchanged — reference copy reassigned, not original)");

        // --- Section 3: Overloading ---
        System.out.println("\n=== 3. METHOD OVERLOADING ===");
        System.out.println("multiply(3, 4)       = " + multiply(3, 4));
        System.out.println("multiply(2.5, 3.0)   = " + multiply(2.5, 3.0));
        System.out.println("multiply(2, 3, 4)    = " + multiply(2, 3, 4));

        // --- Section 4: Varargs ---
        System.out.println("\n=== 4. VARARGS ===");
        System.out.println("sum()            = " + sum());
        System.out.println("sum(1)           = " + sum(1));
        System.out.println("sum(1, 2, 3)     = " + sum(1, 2, 3));
        System.out.println("sum(1,2,3,4,5)   = " + sum(1, 2, 3, 4, 5));
        System.out.println("joinWords(\"-\", \"Java\", \"is\", \"awesome\") = "
                + joinWords("-", "Java", "is", "awesome"));

        // --- Section 5: Recursion ---
        System.out.println("\n=== 5. RECURSION ===");
        System.out.println("factorial(5) = " + factorial(5));
        System.out.println("factorial(10) = " + factorial(10));

        System.out.println("\nFibonacci sequence (first 10):");
        for (int i = 0; i < 10; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();

        // Memoized fibonacci for larger values
        int n = 40;
        long[] memo = new long[n + 1];
        System.out.println("fibMemo(" + n + ") = " + fibMemo(n, memo));

        System.out.println("\npower(2, 10) = " + power(2, 10));
        System.out.println("power(3, -2) = " + power(3, -2));

        System.out.println("\nreverse(\"Hello World\") = " + reverse("Hello World"));

        int[] sorted = { 2, 5, 8, 12, 16, 23, 38, 56, 72, 91 };
        System.out.println("\nbinarySearch for 23 in " + Arrays.toString(sorted));
        int index = binarySearch(sorted, 23, 0, sorted.length - 1);
        System.out.println("Found at index: " + index);

        // --- Section 6: Recursion vs Iteration ---
        System.out.println("\n=== 6. RECURSION vs ITERATION ===");
        System.out.println("Factorial 15 (recursive):  " + factorial(15));
        System.out.println("Factorial 15 (iterative):  " + factorialIterative(15));
        System.out.println("Fibonacci 40 (iterative):  " + fibonacciIterative(40));
        System.out.println("Fibonacci 40 (memoized):   " + fibMemo(40, new long[41]));

        /*
         * ✅ BEST PRACTICE — When to choose recursion vs iteration:
         *
         * USE RECURSION when:
         * - The problem is naturally recursive (trees, graphs, divide-and-conquer)
         * - Code clarity matters more than raw speed
         * - The recursion depth is bounded (won't cause StackOverflow)
         *
         * USE ITERATION when:
         * - Performance is critical (no function-call overhead)
         * - The recursive version would be very deep (risk of StackOverflowError)
         * - A simple loop does the job clearly
         *
         * ⚠️ GOTCHA: Java does NOT optimize tail recursion (unlike Scala/Kotlin).
         * Deep recursion in Java WILL cause StackOverflowError.
         * Default stack size is ~512KB-1MB → roughly 5,000-10,000 frames.
         */

        System.out.println("\n✅ All demos completed successfully!");
    }
}
