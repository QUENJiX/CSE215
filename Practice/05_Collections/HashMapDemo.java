/**
 * HashMapDemo.java - Demonstrates HashMap and Map interface
 * CSE215 - Programming Language II
 */
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.Set;
import java.util.Collection;

public class HashMapDemo {
    public static void main(String[] args) {
        System.out.println("=== HashMap Demo ===\n");
        
        // === Creating HashMap ===
        // Map<KeyType, ValueType>
        Map<String, Integer> ages = new HashMap<>();
        
        // === Adding entries (put) ===
        System.out.println("--- Adding Entries ---");
        ages.put("Alice", 25);
        ages.put("Bob", 30);
        ages.put("Charlie", 28);
        ages.put("Diana", 22);
        System.out.println("Map: " + ages);
        
        // put returns previous value if key exists
        Integer previousAge = ages.put("Alice", 26);
        System.out.println("Previous age of Alice: " + previousAge);
        System.out.println("Updated map: " + ages);
        
        // putIfAbsent - only adds if key doesn't exist
        ages.putIfAbsent("Eve", 35);
        ages.putIfAbsent("Alice", 99);  // Won't change Alice's age
        System.out.println("After putIfAbsent: " + ages);
        
        // === Accessing values ===
        System.out.println("\n--- Accessing Values ---");
        System.out.println("Bob's age: " + ages.get("Bob"));
        System.out.println("Unknown person: " + ages.get("Unknown"));  // Returns null
        
        // getOrDefault - returns default if key not found
        int frankAge = ages.getOrDefault("Frank", 0);
        System.out.println("Frank's age (default 0): " + frankAge);
        
        // === Checking entries ===
        System.out.println("\n--- Checking Entries ---");
        System.out.println("Contains key 'Alice': " + ages.containsKey("Alice"));
        System.out.println("Contains key 'Zack': " + ages.containsKey("Zack"));
        System.out.println("Contains value 30: " + ages.containsValue(30));
        System.out.println("Size: " + ages.size());
        System.out.println("Is empty: " + ages.isEmpty());
        
        // === Removing entries ===
        System.out.println("\n--- Removing Entries ---");
        ages.remove("Diana");
        System.out.println("After removing Diana: " + ages);
        
        // Conditional remove
        boolean removed = ages.remove("Bob", 25);  // Only removes if value matches
        System.out.println("Removed Bob with age 25: " + removed);
        
        ages.remove("Bob", 30);  // This will work
        System.out.println("After removing Bob: " + ages);
        
        // === Iterating over HashMap ===
        System.out.println("\n--- Iteration Methods ---");
        
        // Re-populate for iteration demos
        ages.put("Bob", 30);
        ages.put("Diana", 22);
        
        // 1. Iterating over entries (most common)
        System.out.println("Iterating over entries:");
        for (Map.Entry<String, Integer> entry : ages.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
        
        // 2. Iterating over keys only
        System.out.println("\nIterating over keys:");
        Set<String> keys = ages.keySet();
        for (String name : keys) {
            System.out.println("  Key: " + name);
        }
        
        // 3. Iterating over values only
        System.out.println("\nIterating over values:");
        Collection<Integer> values = ages.values();
        for (Integer age : values) {
            System.out.println("  Value: " + age);
        }
        
        // 4. forEach with lambda (Java 8+)
        System.out.println("\nUsing forEach with lambda:");
        ages.forEach((name, age) -> System.out.println("  " + name + " is " + age + " years old"));
        
        // === Compute methods (Java 8+) ===
        System.out.println("\n--- Compute Methods ---");
        
        // compute - transform value
        ages.compute("Alice", (key, val) -> val + 1);  // Increment Alice's age
        System.out.println("After incrementing Alice: " + ages.get("Alice"));
        
        // computeIfAbsent - add if absent
        ages.computeIfAbsent("Frank", key -> 40);
        System.out.println("After computeIfAbsent: " + ages);
        
        // === Map Variations ===
        System.out.println("\n=== Map Implementations ===");
        
        // LinkedHashMap - maintains insertion order
        System.out.println("\n--- LinkedHashMap (maintains order) ---");
        Map<String, String> linkedMap = new LinkedHashMap<>();
        linkedMap.put("First", "A");
        linkedMap.put("Second", "B");
        linkedMap.put("Third", "C");
        System.out.println("LinkedHashMap: " + linkedMap);
        
        // TreeMap - sorted by keys
        System.out.println("\n--- TreeMap (sorted keys) ---");
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("Zebra", 1);
        treeMap.put("Apple", 2);
        treeMap.put("Mango", 3);
        System.out.println("TreeMap: " + treeMap);  // Sorted alphabetically
        
        // === Practical Example: Word Counter ===
        System.out.println("\n=== Practical Example: Word Counter ===");
        String text = "the quick brown fox jumps over the lazy dog the fox";
        Map<String, Integer> wordCount = new HashMap<>();
        
        for (String word : text.split(" ")) {
            wordCount.merge(word, 1, Integer::sum);  // Increment count
        }
        
        System.out.println("Word frequencies:");
        wordCount.forEach((word, count) -> 
            System.out.println("  '" + word + "' appears " + count + " time(s)")
        );
        
        // Find most frequent word
        String mostFrequent = wordCount.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("N/A");
        System.out.println("Most frequent word: " + mostFrequent);
    }
}
