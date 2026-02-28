# Java Quick Reference — CSE215

> Complete quick reference covering basics through advanced topics.  
> For in-depth examples, see the `Practice/` package (each topic has runnable demos).

## Data Types
| Type    | Size    | Range                 | Default  |
| ------- | ------- | --------------------- | -------- |
| byte    | 1 byte  | -128 to 127           | 0        |
| short   | 2 bytes | -32,768 to 32,767     | 0        |
| int     | 4 bytes | -2^31 to 2^31 - 1     | 0        |
| long    | 8 bytes | -2^63 to 2^63 - 1     | 0L       |
| float   | 4 bytes | ~7 decimal digits     | 0.0f     |
| double  | 8 bytes | ~15 decimal digits    | 0.0      |
| char    | 2 bytes | 0 to 65,535 (Unicode) | '\u0000' |
| boolean | JVM     | true or false         | false    |

**⚠️ Pitfalls:** Integer overflow wraps silently. `0.1 + 0.2 ≠ 0.3` (floating-point). Use `BigDecimal` for money.

---

## OOP Concepts

### Class Structure
```java
public class ClassName {
    // Fields (attributes)
    private int field;
    
    // Constructor
    public ClassName(int value) {
        this.field = value;
    }
    
    // Methods
    public int getField() {
        return field;
    }
}
```

### Inheritance
```java
public class Child extends Parent {
    public Child() {
        super();  // Call parent constructor
    }
    
    @Override
    public void method() {
        super.method();  // Call parent method
    }
}
```

### Interface
```java
public interface Drawable {
    void draw();  // Abstract method
    
    default void show() {  // Default method (Java 8+)
        System.out.println("Showing");
    }
}

public class Circle implements Drawable {
    @Override
    public void draw() {
        // Implementation
    }
}
```

### Abstract Class
```java
public abstract class Shape {
    protected String name;
    
    public Shape(String name) {
        this.name = name;
    }
    
    public abstract double area();  // Must be implemented
    
    public void display() {  // Can have concrete methods
        System.out.println("Shape: " + name);
    }
}
```

### Access Modifiers
| Modifier  | Class | Package | Subclass | World |
| --------- | ----- | ------- | -------- | ----- |
| public    | ✓     | ✓       | ✓        | ✓     |
| protected | ✓     | ✓       | ✓        | ✗     |
| default   | ✓     | ✓       | ✗        | ✗     |
| private   | ✓     | ✗       | ✗        | ✗     |

---

## Control Flow

### If-Else
```java
if (condition) {
    // code
} else if (otherCondition) {
    // code
} else {
    // code
}

// Ternary
String result = (x > 0) ? "positive" : "non-positive";
```

### Switch (Enhanced - Java 14+)
```java
String day = switch (dayNum) {
    case 1 -> "Monday";
    case 2 -> "Tuesday";
    case 6, 7 -> "Weekend";
    default -> "Unknown";
};
```

### Loops
```java
// For loop
for (int i = 0; i < n; i++) { }

// Enhanced for (for-each)
for (String item : collection) { }

// While
while (condition) { }

// Do-while
do { } while (condition);
```

---

## Common Operations

### Strings
```java
String s = "Hello, World!";
s.length();              // 13
s.charAt(0);             // 'H'
s.substring(0, 5);       // "Hello"
s.toLowerCase();         // "hello, world!"
s.toUpperCase();         // "HELLO, WORLD!"
s.contains("World");     // true
s.indexOf("o");          // 4
s.replace("World", "Java"); // "Hello, Java!"
s.split(", ");           // ["Hello", "World!"]
s.trim();                // Remove whitespace
s.equals("Hello");       // false (content comparison)
s.equalsIgnoreCase("HELLO, WORLD!"); // true
```

### Arrays
```java
int[] arr = new int[5];
int[] arr2 = {1, 2, 3, 4, 5};
arr.length;              // 5 (property, not method)
Arrays.sort(arr);        // Sort in place
Arrays.toString(arr);    // "[1, 2, 3, 4, 5]"
Arrays.copyOf(arr, 10);  // Copy with new length
```

### ArrayList
```java
ArrayList<String> list = new ArrayList<>();
list.add("item");
list.add(0, "first");    // Insert at index
list.get(0);
list.set(0, "newValue");
list.remove(0);
list.remove("item");
list.size();
list.isEmpty();
list.contains("item");
list.clear();
```

### HashMap
```java
Map<String, Integer> map = new HashMap<>();
map.put("key", 1);
map.get("key");          // 1
map.getOrDefault("x", 0); // 0 (default if not found)
map.containsKey("key");  // true
map.containsValue(1);    // true
map.remove("key");
map.size();
map.keySet();            // Set of keys
map.values();            // Collection of values
map.entrySet();          // Set of entries

// Iterate
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}
```

---

## Exception Handling
```java
try {
    // risky code
} catch (SpecificException e) {
    e.getMessage();
    e.printStackTrace();
} catch (Exception e) {
    // catch-all
} finally {
    // always executes
}

// Try-with-resources (auto-close)
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line = br.readLine();
}

// Throwing exceptions
throw new IllegalArgumentException("Invalid input");

// Declaring exceptions
public void method() throws IOException {
    // code that might throw IOException
}
```

---

## File I/O

### Reading
```java
// BufferedReader
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
}

// NIO (Java 7+)
List<String> lines = Files.readAllLines(Path.of("file.txt"));
String content = Files.readString(Path.of("file.txt"));
```

### Writing
```java
// BufferedWriter
try (BufferedWriter bw = new BufferedWriter(new FileWriter("file.txt"))) {
    bw.write("Hello");
    bw.newLine();
}

// NIO (Java 7+)
Files.write(Path.of("file.txt"), "content".getBytes());
Files.writeString(Path.of("file.txt"), "content");
```

---

## Generics
```java
// Generic class
public class Box<T> {
    private T content;
    public void set(T content) { this.content = content; }
    public T get() { return content; }
}

// Generic method
public <T> T getFirst(T[] array) {
    return array[0];
}

// Bounded types
public <T extends Number> double sum(List<T> numbers) { }

// Wildcards
List<?> anyList;           // Any type
List<? extends Number> nums; // Number or subclass
List<? super Integer> ints;  // Integer or superclass
```

---

## Useful Shortcuts
| Shortcut | Expansion                                |
| -------- | ---------------------------------------- |
| `sout`   | `System.out.println()`                   |
| `psvm`   | `public static void main(String[] args)` |
| `fori`   | `for (int i = 0; i < ; i++) { }`         |
| `iter`   | Enhanced for loop                        |

---

## Lambda Expressions (Java 8+)
```java
// Syntax variations
Runnable r = () -> System.out.println("hi");
Consumer<String> c = s -> System.out.println(s);
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
Comparator<String> comp = (a, b) -> a.length() - b.length();

// With collections
list.forEach(System.out::println);
list.removeIf(s -> s.isEmpty());
list.sort(Comparator.comparing(String::length));
```

### Built-in Functional Interfaces
| Interface           | Method       | Signature     |
| ------------------- | ------------ | ------------- |
| `Predicate<T>`      | `test(T)`    | `T → boolean` |
| `Function<T,R>`     | `apply(T)`   | `T → R`       |
| `Consumer<T>`       | `accept(T)`  | `T → void`    |
| `Supplier<T>`       | `get()`      | `() → T`      |
| `UnaryOperator<T>`  | `apply(T)`   | `T → T`       |
| `BinaryOperator<T>` | `apply(T,T)` | `(T,T) → T`   |

### Method References
| Type             | Syntax                | Lambda Equivalent            |
| ---------------- | --------------------- | ---------------------------- |
| Static           | `Integer::parseInt`   | `s -> Integer.parseInt(s)`   |
| Bound instance   | `System.out::println` | `s -> System.out.println(s)` |
| Unbound instance | `String::toUpperCase` | `s -> s.toUpperCase()`       |
| Constructor      | `ArrayList::new`      | `() -> new ArrayList<>()`    |

---

## Streams API (Java 8+)
```java
// Pipeline: source → intermediate ops → terminal op
List<String> result = list.stream()
    .filter(s -> s.length() > 3)       // keep matching
    .map(String::toUpperCase)           // transform
    .sorted()                           // sort
    .distinct()                         // remove duplicates
    .limit(10)                          // take first 10
    .collect(Collectors.toList());      // gather results

// Terminal operations
long count = stream.count();
Optional<T> first = stream.findFirst();
boolean any = stream.anyMatch(predicate);
T reduced = stream.reduce(identity, accumulator);

// Collectors
Collectors.toList()
Collectors.toSet()
Collectors.toMap(keyFn, valueFn)
Collectors.groupingBy(classifier)
Collectors.partitioningBy(predicate)
Collectors.joining(", ")
```

---

## Enums
```java
enum Season {
    SPRING, SUMMER, FALL, WINTER;
}

// Enum with fields
enum Planet {
    EARTH(5.97e24, 6.37e6);
    private final double mass, radius;
    Planet(double m, double r) { mass = m; radius = r; }
}

// Useful methods
Season.values()            // All constants
Season.valueOf("SPRING")   // String → enum
season.name()              // Enum → String
season.ordinal()           // Position (0-based)
```

---

## Concurrency Basics
```java
// Create thread
Thread t = new Thread(() -> System.out.println("Running"));
t.start();   // ✅ creates new thread
// t.run();  // ❌ runs in current thread!
t.join();    // Wait for thread to finish

// ExecutorService (preferred)
ExecutorService pool = Executors.newFixedThreadPool(4);
pool.submit(() -> { /* task */ });
Future<Integer> future = pool.submit(() -> 42);
int result = future.get(); // blocks until ready
pool.shutdown();

// Synchronized
synchronized (lockObject) { /* thread-safe block */ }

// Atomic (for simple counters)
AtomicInteger counter = new AtomicInteger(0);
counter.incrementAndGet();
```

---

## Common Imports
```java
import java.util.*;                    // Collections, Arrays, Scanner, etc.
import java.util.stream.*;            // Stream, Collectors
import java.util.function.*;          // Predicate, Function, Consumer, etc.
import java.io.*;                     // File I/O
import java.nio.file.*;               // NIO (Path, Files)
import java.util.concurrent.*;        // ExecutorService, Future
import java.util.concurrent.atomic.*; // AtomicInteger
```

---

## Common Gotchas
| Trap                         | Explanation                                             |
| ---------------------------- | ------------------------------------------------------- |
| `==` on objects              | Compares references, not values. Use `.equals()`        |
| `==` on `Integer > 127`      | Integer cache only covers -128 to 127                   |
| String `==` vs `.equals()`   | Pool may make `==` work sometimes, but never rely on it |
| `0.1 + 0.2`                  | Not exactly `0.3` (floating-point)                      |
| Array `toString()`           | Prints hash, not contents. Use `Arrays.toString()`      |
| `List.of()` is immutable     | Can't add/remove. Use `new ArrayList<>(List.of(...))`   |
| Forgetting `break` in switch | Fall-through. Use enhanced switch (`->`) instead        |
| `thread.run()`               | Doesn't create a new thread! Use `thread.start()`       |

> 📚 **See also:** `Practice/README.md` for the complete learning path and runnable demos.
