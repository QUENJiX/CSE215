/**
 * StringDeepDiveDemo.java — Strings: Immutability, Pool, StringBuilder, Regex & More
 * ====================================================================================
 * CSE215 - Programming Language II
 *
 * 💡 INTUITION: Strings in Java are NOT like in most other languages.
 *    They are IMMUTABLE — once created, a String object can NEVER be changed.
 *    Every "modification" actually creates a NEW String object.
 *
 * Topics covered:
 *   1. String immutability & the String pool
 *   2. String comparison (== vs .equals())
 *   3. Essential String methods (comprehensive)
 *   4. StringBuilder & StringBuffer
 *   5. String formatting (printf, String.format)
 *   6. Regular expressions basics
 *   7. Common interview patterns (reverse, palindrome, anagram)
 *
 * 🔗 SEE ALSO: p01_Basics/DataTypesDemo.java, p05_Collections/ArrayListDemo.java
 */
package p01_Basics;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringDeepDiveDemo {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   STRING DEEP DIVE DEMO                     ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // =====================================================================
        // SECTION 1: STRING IMMUTABILITY & THE STRING POOL
        // =====================================================================
        System.out.println("=== 1. IMMUTABILITY & STRING POOL ===");

        /**
         * 💡 INTUITION — String Pool (a.k.a. intern pool)
         *
         * Java maintains a special memory area called the "String Pool"
         * in the heap. When you write a string literal like "Hello",
         * Java checks the pool first:
         * - If "Hello" exists → reuse it (no new object)
         * - If not → create it in the pool
         *
         * This saves memory when the same string appears many times.
         *
         * HEAP MEMORY:
         * ┌──────────────────────────────────────┐
         * │ │
         * │ String Pool: ┌─────────┐ │
         * │ │ "Hello" │ ← s1, s2 both point here
         * │ └─────────┘ │
         * │ │
         * │ Regular Heap: ┌─────────┐ │
         * │ │ "Hello" │ ← s3 (new String)
         * │ └─────────┘ │
         * └──────────────────────────────────────┘
         */

        String s1 = "Hello"; // Created in String Pool
        String s2 = "Hello"; // Reuses SAME object from pool
        String s3 = new String("Hello"); // Forces NEW object on heap (outside pool)

        System.out.println("s1 == s2:      " + (s1 == s2)); // true (same pool object)
        System.out.println("s1 == s3:      " + (s1 == s3)); // false (different objects!)
        System.out.println("s1.equals(s3): " + s1.equals(s3)); // true (same content)

        // intern() forces a string into the pool
        String s4 = s3.intern();
        System.out.println("s1 == s3.intern(): " + (s1 == s4)); // true

        /**
         * 📌 RULE: ALWAYS use .equals() to compare String CONTENT.
         * == compares references (memory addresses), not content.
         *
         * ⚠️ GOTCHA: This is the #1 Java beginner mistake.
         */

        // Immutability demonstration
        String original = "Hello";
        String modified = original.concat(" World"); // Creates a NEW string
        System.out.println("\noriginal: \"" + original + "\"  (unchanged!)");
        System.out.println("modified: \"" + modified + "\"  (new object)");

        // =====================================================================
        // SECTION 2: STRING COMPARISON (all the ways)
        // =====================================================================
        System.out.println("\n=== 2. STRING COMPARISON ===");

        String a = "Java";
        String b = "java";

        System.out.println("\"Java\".equals(\"java\"):           " + a.equals(b)); // false
        System.out.println("\"Java\".equalsIgnoreCase(\"java\"): " + a.equalsIgnoreCase(b)); // true
        System.out.println("\"Java\".compareTo(\"java\"):        " + a.compareTo(b)); // negative (J < j in Unicode)
        System.out.println("\"Java\".compareToIgnoreCase(\"java\"): " + a.compareToIgnoreCase(b)); // 0

        // =====================================================================
        // SECTION 3: ESSENTIAL STRING METHODS (Comprehensive Reference)
        // =====================================================================
        System.out.println("\n=== 3. ESSENTIAL STRING METHODS ===");

        String text = "  Hello, World! Welcome to Java Programming.  ";

        // --- Length and emptiness ---
        System.out.println("--- Length & Emptiness ---");
        System.out.println("length():    " + text.length());
        System.out.println("isEmpty():   " + text.isEmpty()); // false
        System.out.println("isBlank():   " + "   ".isBlank()); // true (Java 11+, checks whitespace)
        System.out.println("isEmpty() on \"\": " + "".isEmpty()); // true

        // --- Trimming whitespace ---
        System.out.println("\n--- Trimming ---");
        System.out.println("trim():  \"" + text.trim() + "\""); // Removes leading/trailing whitespace
        System.out.println("strip(): \"" + text.strip() + "\""); // Java 11+, Unicode-aware trim

        String trimmed = text.trim();

        // --- Searching ---
        System.out.println("\n--- Searching ---");
        System.out.println("charAt(0):      '" + trimmed.charAt(0) + "'");
        System.out.println("indexOf('o'):   " + trimmed.indexOf('o'));
        System.out.println("lastIndexOf('o'): " + trimmed.lastIndexOf('o'));
        System.out.println("contains(\"World\"): " + trimmed.contains("World"));
        System.out.println("startsWith(\"Hello\"): " + trimmed.startsWith("Hello"));
        System.out.println("endsWith(\".\"):   " + trimmed.endsWith("."));

        // --- Extracting substrings ---
        System.out.println("\n--- Substrings ---");
        System.out.println("substring(7):    \"" + trimmed.substring(7) + "\""); // From index 7 to end
        System.out.println("substring(7,12): \"" + trimmed.substring(7, 12) + "\""); // From 7 to 11 (exclusive end)

        // --- Case conversion ---
        System.out.println("\n--- Case Conversion ---");
        System.out.println("toUpperCase(): " + trimmed.toUpperCase());
        System.out.println("toLowerCase(): " + trimmed.toLowerCase());

        // --- Replacing ---
        System.out.println("\n--- Replacing ---");
        System.out.println("replace('o','0'):        " + trimmed.replace('o', '0'));
        System.out.println("replace(\"World\",\"Java\"): " + trimmed.replace("World", "Java"));
        System.out.println("replaceAll(\"[aeiou]\",\"*\"): " + trimmed.replaceAll("[aeiou]", "*")); // regex!
        System.out.println("replaceFirst(\"o\",\"0\"):  " + trimmed.replaceFirst("o", "0")); // first only

        // --- Splitting ---
        System.out.println("\n--- Splitting ---");
        String csv = "apple,banana,cherry,date";
        String[] fruits = csv.split(",");
        System.out.println("split(\",\"): " + Arrays.toString(fruits));

        String sentence = "The   quick   brown   fox";
        String[] words = sentence.split("\\s+"); // \\s+ = one or more whitespace
        System.out.println("split(\"\\\\s+\"): " + Arrays.toString(words));

        // --- Joining ---
        System.out.println("\n--- Joining ---");
        String joined = String.join(" | ", fruits);
        System.out.println("String.join(\" | \", fruits): " + joined);

        // --- char array conversion ---
        System.out.println("\n--- Char Array ---");
        char[] chars = "Hello".toCharArray();
        System.out.println("toCharArray(): " + Arrays.toString(chars));
        String fromChars = new String(chars);
        System.out.println("new String(chars): " + fromChars);

        // --- valueOf (converting other types to String) ---
        System.out.println("\n--- valueOf ---");
        System.out.println("String.valueOf(42):    \"" + String.valueOf(42) + "\"");
        System.out.println("String.valueOf(3.14):  \"" + String.valueOf(3.14) + "\"");
        System.out.println("String.valueOf(true):  \"" + String.valueOf(true) + "\"");

        // =====================================================================
        // SECTION 4: STRINGBUILDER & STRINGBUFFER
        // =====================================================================
        System.out.println("\n=== 4. STRINGBUILDER ===");

        /**
         * 💡 INTUITION — Since String is immutable, concatenating in a loop
         * creates a NEW String object every iteration → very slow for many concats!
         *
         * ❌ BAD (creates ~1000 String objects):
         * String s = "";
         * for (int i = 0; i < 1000; i++) s += i; // O(n²) !
         *
         * ✅ GOOD (modifies one buffer in place):
         * StringBuilder sb = new StringBuilder();
         * for (int i = 0; i < 1000; i++) sb.append(i); // O(n)
         *
         * 📌 RULE:
         * StringBuilder = fast, NOT thread-safe (use in single-threaded code)
         * StringBuffer = slower, thread-safe (use in multi-threaded code)
         * In practice: almost always use StringBuilder.
         */

        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" "); // Append
        sb.append("World");
        sb.insert(5, ","); // Insert at index
        sb.replace(7, 12, "Java"); // Replace range
        sb.delete(5, 6); // Delete range
        sb.reverse(); // Reverse in place

        System.out.println("Result: " + sb.toString());

        sb.reverse(); // Reverse back
        System.out.println("Reversed back: " + sb);

        // Performance comparison
        System.out.println("\n--- Performance: String concat vs StringBuilder ---");
        int iterations = 50000;

        long start = System.currentTimeMillis();
        StringBuilder fast = new StringBuilder();
        for (int i = 0; i < iterations; i++)
            fast.append("x");
        long sbTime = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        String slow = "";
        for (int i = 0; i < iterations; i++)
            slow += "x"; // ⚠️ Creates 50,000 objects!
        long strTime = System.currentTimeMillis() - start;

        System.out.println("StringBuilder: " + sbTime + "ms");
        System.out.println("String concat: " + strTime + "ms");
        System.out.println("StringBuilder is ~" + (strTime == 0 ? "∞" : (strTime / Math.max(sbTime, 1))) + "x faster");

        // =====================================================================
        // SECTION 5: STRING FORMATTING
        // =====================================================================
        System.out.println("\n=== 5. STRING FORMATTING ===");

        /**
         * Format specifiers:
         * %s = String %d = int/long %f = float/double
         * %c = char %b = boolean %n = newline (platform-specific)
         * %10s = right-align in 10 chars %-10s = left-align in 10 chars
         * %.2f = 2 decimal places %05d = zero-padded to 5 digits
         */

        String name = "Alice";
        int age = 25;
        double gpa = 3.856;

        // String.format returns a formatted String
        String formatted = String.format("Name: %-10s | Age: %3d | GPA: %.2f", name, age, gpa);
        System.out.println(formatted);

        // printf prints directly (same format specifiers)
        System.out.printf("Name: %-10s | Age: %3d | GPA: %.2f%n", "Bob", 30, 3.214);

        // Padding and alignment
        System.out.println("\n--- Table Formatting ---");
        System.out.printf("%-15s %5s %8s%n", "Student", "Age", "GPA");
        System.out.printf("%-15s %5d %8.2f%n", "Alice", 25, 3.85);
        System.out.printf("%-15s %5d %8.2f%n", "Bob", 30, 3.21);
        System.out.printf("%-15s %5d %8.2f%n", "Charlie", 22, 3.99);

        // =====================================================================
        // SECTION 6: REGULAR EXPRESSIONS (Regex) BASICS
        // =====================================================================
        System.out.println("\n=== 6. REGEX BASICS ===");

        /**
         * 💡 INTUITION — Regex is a mini-language for pattern matching in text.
         *
         * Common patterns:
         * . = any single character
         * \d = digit [0-9] \D = non-digit
         * \w = word char [a-zA-Z0-9_] \W = non-word char
         * \s = whitespace \S = non-whitespace
         * [abc] = character class (a, b, or c)
         * ^ = start of string $ = end of string
         * * = 0 or more + = 1 or more
         * ? = 0 or 1 {n,m} = n to m times
         *
         * ⚠️ GOTCHA: In Java strings, backslash must be doubled!
         * \d in regex → "\\d" in Java code
         */

        // matches() — checks if ENTIRE string matches pattern
        System.out.println("--- matches() ---");
        System.out.println("\"12345\".matches(\"\\\\d+\"): " + "12345".matches("\\d+"));
        System.out.println("\"abc123\".matches(\"\\\\d+\"): " + "abc123".matches("\\d+"));

        // Email validation (simplified)
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        System.out.println("\"user@email.com\" valid: " + "user@email.com".matches(emailRegex));
        System.out.println("\"not-an-email\" valid:   " + "not-an-email".matches(emailRegex));

        // Pattern and Matcher for advanced use
        System.out.println("\n--- Pattern & Matcher (find all matches) ---");
        String input = "Call 555-1234 or 555-5678 for info. Also try 555-9999.";
        Pattern phonePattern = Pattern.compile("\\d{3}-\\d{4}");
        Matcher matcher = phonePattern.matcher(input);

        System.out.println("Phone numbers found:");
        while (matcher.find()) {
            System.out.println("  " + matcher.group() + " at index " + matcher.start());
        }

        // =====================================================================
        // SECTION 7: COMMON STRING PATTERNS (Interview Favorites)
        // =====================================================================
        System.out.println("\n=== 7. COMMON STRING PATTERNS ===");

        // Palindrome check
        System.out.println("--- Palindrome Check ---");
        System.out.println("isPalindrome(\"racecar\"): " + isPalindrome("racecar"));
        System.out.println("isPalindrome(\"hello\"):   " + isPalindrome("hello"));
        System.out.println("isPalindrome(\"A man a plan a canal Panama\"): "
                + isPalindrome("A man a plan a canal Panama"));

        // Anagram check
        System.out.println("\n--- Anagram Check ---");
        System.out.println("isAnagram(\"listen\", \"silent\"): " + isAnagram("listen", "silent"));
        System.out.println("isAnagram(\"hello\", \"world\"):   " + isAnagram("hello", "world"));

        // Character frequency
        System.out.println("\n--- Character Frequency ---");
        printCharFrequency("mississippi");

        System.out.println("\n✅ All demos completed successfully!");
    }

    // --- Helper methods ---

    /**
     * Checks if a string is a palindrome (ignoring case and non-alphanumeric chars)
     */
    public static boolean isPalindrome(String s) {
        // Clean: remove non-alphanumeric, lowercase
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        // Compare with its reverse
        return cleaned.equals(new StringBuilder(cleaned).reverse().toString());
    }

    /** Checks if two strings are anagrams (same letters, different order) */
    public static boolean isAnagram(String a, String b) {
        char[] charsA = a.toLowerCase().toCharArray();
        char[] charsB = b.toLowerCase().toCharArray();
        Arrays.sort(charsA);
        Arrays.sort(charsB);
        return Arrays.equals(charsA, charsB);
    }

    /** Prints frequency of each character in a string */
    public static void printCharFrequency(String s) {
        int[] freq = new int[128]; // ASCII characters
        for (char c : s.toCharArray()) {
            freq[c]++;
        }
        System.out.print("  \"" + s + "\" → ");
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                System.out.print((char) i + ":" + freq[i] + " ");
            }
        }
        System.out.println();
    }
}
