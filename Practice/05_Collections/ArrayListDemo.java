/**
 * ArrayListDemo.java - Demonstrates ArrayList and List interface
 * CSE215 - Programming Language II
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Iterator;

public class ArrayListDemo {
    public static void main(String[] args) {
        System.out.println("=== ArrayList Demo ===\n");
        
        // === Creating ArrayList ===
        // Using interface type (recommended)
        List<String> fruits = new ArrayList<>();
        
        // === Adding elements ===
        System.out.println("--- Adding Elements ---");
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Date");
        fruits.add("Elderberry");
        System.out.println("Fruits: " + fruits);
        
        // Add at specific index
        fruits.add(1, "Apricot");
        System.out.println("After adding Apricot at index 1: " + fruits);
        
        // === Accessing elements ===
        System.out.println("\n--- Accessing Elements ---");
        System.out.println("First fruit: " + fruits.get(0));
        System.out.println("Third fruit: " + fruits.get(2));
        System.out.println("Size: " + fruits.size());
        
        // === Modifying elements ===
        System.out.println("\n--- Modifying Elements ---");
        fruits.set(0, "Avocado");  // Replace element at index
        System.out.println("After replacing index 0: " + fruits);
        
        // === Checking elements ===
        System.out.println("\n--- Checking Elements ---");
        System.out.println("Contains 'Banana': " + fruits.contains("Banana"));
        System.out.println("Index of 'Cherry': " + fruits.indexOf("Cherry"));
        System.out.println("Is empty: " + fruits.isEmpty());
        
        // === Removing elements ===
        System.out.println("\n--- Removing Elements ---");
        fruits.remove("Date");  // Remove by object
        System.out.println("After removing 'Date': " + fruits);
        
        fruits.remove(0);  // Remove by index
        System.out.println("After removing index 0: " + fruits);
        
        // === Iterating over ArrayList ===
        System.out.println("\n--- Iteration Methods ---");
        
        // 1. Enhanced for loop (for-each)
        System.out.println("For-each loop:");
        for (String fruit : fruits) {
            System.out.println("  - " + fruit);
        }
        
        // 2. Traditional for loop with index
        System.out.println("\nTraditional for loop:");
        for (int i = 0; i < fruits.size(); i++) {
            System.out.println("  [" + i + "] " + fruits.get(i));
        }
        
        // 3. Iterator
        System.out.println("\nUsing Iterator:");
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            System.out.println("  > " + iterator.next());
        }
        
        // 4. forEach with lambda (Java 8+)
        System.out.println("\nUsing forEach with lambda:");
        fruits.forEach(fruit -> System.out.println("  * " + fruit));
        
        // === Sorting ===
        System.out.println("\n--- Sorting ---");
        Collections.sort(fruits);  // Natural order (alphabetical)
        System.out.println("Sorted: " + fruits);
        
        Collections.reverse(fruits);
        System.out.println("Reversed: " + fruits);
        
        // === ArrayList with Numbers ===
        System.out.println("\n=== ArrayList with Numbers ===");
        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(8);
        numbers.add(1);
        numbers.add(9);
        
        System.out.println("Original: " + numbers);
        Collections.sort(numbers);
        System.out.println("Sorted: " + numbers);
        System.out.println("Max: " + Collections.max(numbers));
        System.out.println("Min: " + Collections.min(numbers));
        
        // Sum using stream (Java 8+)
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum: " + sum);
        
        // === Additional Operations ===
        System.out.println("\n--- Additional Operations ---");
        
        // Sublist
        List<Integer> subList = numbers.subList(1, 4);
        System.out.println("Sublist (1 to 4): " + subList);
        
        // addAll - add multiple elements
        List<Integer> moreNumbers = new ArrayList<>();
        moreNumbers.add(100);
        moreNumbers.add(200);
        numbers.addAll(moreNumbers);
        System.out.println("After addAll: " + numbers);
        
        // Clear all elements
        List<Integer> temp = new ArrayList<>(numbers);  // Copy constructor
        temp.clear();
        System.out.println("After clear: " + temp);
        System.out.println("Original still has: " + numbers);
        
        // === Converting ===
        System.out.println("\n--- Conversions ---");
        
        // ArrayList to Array
        String[] fruitArray = fruits.toArray(new String[0]);
        System.out.println("As array: " + java.util.Arrays.toString(fruitArray));
        
        // Array to ArrayList
        String[] moreVeggies = {"Carrot", "Broccoli", "Spinach"};
        List<String> veggieList = new ArrayList<>(java.util.Arrays.asList(moreVeggies));
        System.out.println("From array: " + veggieList);
    }
}
