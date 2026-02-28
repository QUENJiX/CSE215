/**
 * CollectionsUtilityDemo.java — java.util.Collections Utility Class & Unmodifiable Views
 * ========================================================================================
 * CSE215 - Programming Language II
 *
 * 💡 INTUITION: Collections (with an 's') is a UTILITY CLASS full of static helper methods
 *    for working with Collection objects. Don't confuse it with Collection (the interface)!
 *
 *    Collections  = utility class (like Math or Arrays) — has static methods
 *    Collection   = interface (parent of List, Set, Queue)
 *
 * Topics covered:
 *   1. Sorting (sort, reverseOrder, shuffle)
 *   2. Searching (binarySearch, min, max, frequency)
 *   3. Transforming (reverse, rotate, swap, fill, replaceAll)
 *   4. Creating special collections (singleton, nCopies, emptyList, unmodifiable)
 *   5. Thread-safe wrappers (synchronized collections)
 *   6. List.of, Map.of, Set.of — Immutable factory methods (Java 9+)
 *
 * 🔗 SEE ALSO: p05_Collections/ArrayListDemo.java, p05_Collections/SetDemo.java
 */
package p05_Collections;

import java.util.*;

public class CollectionsUtilityDemo {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   COLLECTIONS UTILITY DEMO                   ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // =====================================================================
        // SECTION 1: SORTING
        // =====================================================================
        System.out.println("=== 1. SORTING ===");

        List<Integer> numbers = new ArrayList<>(Arrays.asList(42, 17, 85, 3, 56, 28));
        System.out.println("Original:  " + numbers);

        Collections.sort(numbers); // Natural order (ascending)
        System.out.println("Sorted:    " + numbers);

        Collections.sort(numbers, Comparator.reverseOrder());
        System.out.println("Reversed:  " + numbers);

        Collections.shuffle(numbers); // Random order
        System.out.println("Shuffled:  " + numbers);

        Collections.shuffle(numbers, new Random(42)); // Reproducible shuffle (seed)
        System.out.println("Shuffled (seed=42): " + numbers);

        // =====================================================================
        // SECTION 2: SEARCHING & AGGREGATION
        // =====================================================================
        System.out.println("\n=== 2. SEARCHING & AGGREGATION ===");

        List<Integer> sorted = new ArrayList<>(Arrays.asList(3, 17, 28, 42, 56, 85));

        // binarySearch — list MUST be sorted!
        int index = Collections.binarySearch(sorted, 42);
        System.out.println("binarySearch(42): index " + index);

        int notFound = Collections.binarySearch(sorted, 50);
        System.out.println("binarySearch(50): " + notFound + " (negative = not found, insertion point)");

        // Min, Max
        System.out.println("min: " + Collections.min(sorted));
        System.out.println("max: " + Collections.max(sorted));

        // Min with custom comparator
        List<String> words = Arrays.asList("banana", "apple", "cherry", "date");
        String shortest = Collections.min(words, Comparator.comparingInt(String::length));
        System.out.println("Shortest word: " + shortest);

        // Frequency
        List<String> items = Arrays.asList("A", "B", "A", "C", "A", "B");
        System.out.println("\nList: " + items);
        System.out.println("frequency('A'): " + Collections.frequency(items, "A"));
        System.out.println("frequency('B'): " + Collections.frequency(items, "B"));

        // disjoint — true if two collections have NO common elements
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<Integer> list3 = Arrays.asList(3, 4, 5);
        System.out.println("\ndisjoint({1,2,3}, {4,5,6}): " + Collections.disjoint(list1, list2)); // true
        System.out.println("disjoint({1,2,3}, {3,4,5}): " + Collections.disjoint(list1, list3)); // false

        // =====================================================================
        // SECTION 3: TRANSFORMING
        // =====================================================================
        System.out.println("\n=== 3. TRANSFORMING ===");

        List<Integer> data = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("Original: " + data);

        Collections.reverse(data);
        System.out.println("Reversed: " + data);

        Collections.rotate(data, 2); // Rotate right by 2
        System.out.println("Rotated right by 2: " + data);

        Collections.swap(data, 0, 4); // Swap indices 0 and 4
        System.out.println("Swapped [0] and [4]: " + data);

        Collections.fill(data, 0); // Fill all with 0
        System.out.println("Filled with 0: " + data);

        List<String> phrases = new ArrayList<>(Arrays.asList("hello world", "hey world", "hi world"));
        Collections.replaceAll(phrases, "hey world", "hello earth");
        System.out.println("replaceAll: " + phrases);

        // =====================================================================
        // SECTION 4: SPECIAL COLLECTIONS
        // =====================================================================
        System.out.println("\n=== 4. SPECIAL COLLECTIONS ===");

        // singleton — immutable single-element collection
        Set<String> single = Collections.singleton("only one");
        System.out.println("Singleton: " + single);

        // nCopies — immutable list of n copies
        List<String> copies = Collections.nCopies(5, "Hello");
        System.out.println("nCopies(5): " + copies);

        // emptyList/emptySet/emptyMap — immutable empty collections
        List<String> empty = Collections.emptyList();
        System.out.println("emptyList: " + empty + " (size=" + empty.size() + ")");

        // =====================================================================
        // SECTION 5: UNMODIFIABLE & IMMUTABLE COLLECTIONS
        // =====================================================================
        System.out.println("\n=== 5. UNMODIFIABLE & IMMUTABLE ===");

        /**
         * 💡 INTUITION:
         * Unmodifiable = a READ-ONLY VIEW of a mutable collection
         * Changes to the original STILL show through!
         *
         * Immutable (Java 9+) = truly unchangeable, no backing mutable collection
         *
         * ⚠️ GOTCHA: unmodifiableList wraps the original — if you modify the original,
         * the unmodifiable view also changes! Use List.copyOf() for true immutability.
         */

        // Unmodifiable view
        List<String> mutable = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> unmodifiable = Collections.unmodifiableList(mutable);
        System.out.println("Unmodifiable: " + unmodifiable);

        // unmodifiable.add("D"); // ❌ Throws UnsupportedOperationException!

        mutable.add("D"); // ⚠️ But modifying the original...
        System.out.println("After adding to original: " + unmodifiable); // Shows D!

        // Java 9+ immutable factories
        System.out.println("\n--- Java 9+ Immutable Factories ---");
        List<String> immutableList = List.of("X", "Y", "Z");
        Set<Integer> immutableSet = Set.of(1, 2, 3, 4, 5);
        Map<String, Integer> immutableMap = Map.of("A", 1, "B", 2);

        System.out.println("List.of: " + immutableList);
        System.out.println("Set.of:  " + immutableSet);
        System.out.println("Map.of:  " + immutableMap);

        // List.copyOf — truly immutable deep copy (Java 10+)
        List<String> original = new ArrayList<>(Arrays.asList("P", "Q", "R"));
        List<String> immutableCopy = List.copyOf(original);
        original.add("S");
        System.out.println("\nOriginal after add: " + original);
        System.out.println("copyOf unchanged:   " + immutableCopy); // ✅ Not affected

        // =====================================================================
        // SECTION 6: THREAD-SAFE WRAPPERS
        // =====================================================================
        System.out.println("\n=== 6. THREAD-SAFE WRAPPERS ===");

        /**
         * 💡 Collections.synchronized* wraps a collection with synchronized methods.
         * Not as efficient as ConcurrentHashMap etc., but simple to use.
         */
        List<String> syncList = Collections.synchronizedList(new ArrayList<>());
        Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());
        Set<String> syncSet = Collections.synchronizedSet(new HashSet<>());

        syncList.add("Thread-safe!");
        System.out.println("Synchronized list: " + syncList);
        System.out.println("(For serious concurrency, use java.util.concurrent classes instead)");

        System.out.println("\n✅ All demos completed successfully!");
    }
}
