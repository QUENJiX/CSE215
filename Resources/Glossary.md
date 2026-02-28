# ⭐ CSE215 — Java & OOP Glossary

Comprehensive definitions of every key term covered in the course.
📌 Cross-referenced with Practice/ demos | Terms listed A → Z

---

## A

**Abstract Class**
A class that cannot be instantiated and may contain abstract methods. Used as a base class. Think of it as a *partial blueprint* — some methods are finished, others left for subclasses.
```java
abstract class Shape {
    abstract double area();       // subclass MUST implement
    void describe() { ... }      // concrete method — inherited as-is
}
```
📌 Can have constructors, fields, and concrete methods (unlike interfaces pre-Java 8).
🔗 See `Practice/p03_Interfaces/AbstractClassDemo.java`

**Access Modifier**
Keywords that set accessibility: `public`, `protected`, `private`, (default/package-private).

| Modifier    | Same Class | Same Package | Subclass | World |
| ----------- | ---------- | ------------ | -------- | ----- |
| `public`    | ✅          | ✅            | ✅        | ✅     |
| `protected` | ✅          | ✅            | ✅        | ❌     |
| *(default)* | ✅          | ✅            | ❌        | ❌     |
| `private`   | ✅          | ❌            | ❌        | ❌     |

**Abstraction**
Hiding complex implementation details and showing only necessary features. Achieved via abstract classes and interfaces.

**Anonymous Class**
A class defined and instantiated in one expression, with no name. Useful for one-off implementations.
```java
Comparator<String> comp = new Comparator<String>() {
    @Override public int compare(String a, String b) { return a.length() - b.length(); }
};
```
⚠️ `this` inside an anonymous class refers to the anonymous class itself, not the enclosing class.
🔗 See `Practice/p09_InnerClasses/InnerClassesDemo.java`

**ArrayList**
A resizable array implementation of the List interface. `get()` = O(1), `add()` = O(1) amortized, `remove()` = O(n).
🔗 See `Practice/p05_Collections/ArrayListDemo.java`

**Atomic Classes**
Thread-safe wrappers (`AtomicInteger`, `AtomicLong`, etc.) that support lock-free operations like `incrementAndGet()`.
🔗 See `Practice/p10_Concurrency/ThreadBasicsDemo.java`

**Autoboxing / Unboxing**
Automatic conversion between primitives and their wrapper classes.
```java
Integer x = 5;      // autoboxing:   int → Integer
int y = x;          // unboxing:     Integer → int
```
⚠️ Integer cache: `Integer.valueOf(127) == Integer.valueOf(127)` is `true`, but `128 == 128` is `false`!

---

## B

**Boolean**
Primitive data type with two values: `true` or `false`.

**Break**
Statement that exits the current loop or switch block.

**Builder Pattern**
Creational design pattern that constructs complex objects step-by-step using a fluent API.
```java
User user = new User.Builder("Alice").age(25).email("a@b.com").build();
```
🔗 See `Practice/p11_DesignPatterns/BuilderPatternDemo.java`

**BiFunction<T, U, R>**
Functional interface: takes two arguments of types T and U, returns R.

---

## C

**Callable<V>**
Like `Runnable` but can return a value and throw checked exceptions. Used with `ExecutorService.submit()`.
```java
Callable<Integer> task = () -> { return 42; };
Future<Integer> future = executor.submit(task);
```
🔗 See `Practice/p10_Concurrency/ThreadBasicsDemo.java`

**Class**
A blueprint for creating objects. Defines attributes (fields) and behaviors (methods).

**Clone**
Creates a copy of an object. Implement `Cloneable` and override `clone()`.
- **Shallow copy**: copies field values (references still point to same objects)
- **Deep copy**: recursively copies all referenced objects
🔗 See `Practice/p02_OOP/ObjectClassMethodsDemo.java`

**Collections Utility Class**
`java.util.Collections` — static methods: `sort()`, `binarySearch()`, `min()`, `max()`, `frequency()`, `unmodifiableList()`, `synchronizedList()`, etc.
🔗 See `Practice/p05_Collections/CollectionsUtilityDemo.java`

**Comparable<T>**
Interface for defining *natural ordering*. Implement `compareTo(T o)` — returns negative, zero, or positive.
```java
class Student implements Comparable<Student> {
    public int compareTo(Student o) { return this.gpa - o.gpa; }
}
```
🔗 See `Practice/p03_Interfaces/ComparableComparatorDemo.java`

**Comparator<T>**
Interface for defining *custom orderings*. Can create multiple different orderings for the same class.
```java
Comparator<Student> byName = Comparator.comparing(Student::getName);
```

**Compiler**
Translates source code (.java) into bytecode (.class).

**ConcurrentHashMap**
Thread-safe HashMap that allows concurrent reads and segment-level locking for writes.
🔗 See `Practice/p10_Concurrency/ThreadBasicsDemo.java`

**Consumer<T>**
Functional interface: takes one argument, returns nothing. `accept(T t)`.

**Constructor**
Special method called when creating an object. Same name as the class, no return type.
```java
public Person(String name) {
    this.name = name;
}
```

**Continue**
Statement that skips the current iteration and continues with the next.

**Casting**
Converting one data type to another.
- **Widening** (implicit): `int` → `long` → `float` → `double` (safe, automatic)
- **Narrowing** (explicit): `double` → `int` (may lose data, requires `(Type)`)

---

## D

**Deadlock**
A situation where two or more threads are blocked forever, each waiting for the other's lock.

**Default Constructor**
Constructor with no parameters, provided automatically if no constructor is defined. ⚠️ Gone the moment you write *any* constructor.

**Default Method**
A method with a body in an interface (Java 8+). Allows adding methods without breaking existing implementations.
```java
interface Greeter {
    default void greet() { System.out.println("Hello!"); }
}
```

**Dependency**
When one class uses another class.

**Deque (Double-Ended Queue)**
Interface supporting insertion/removal at both ends. `ArrayDeque` is the standard implementation. Java recommends `Deque` as a stack instead of `Stack` class.
🔗 See `Practice/p05_Collections/QueueAndStackDemo.java`

**Design Patterns**
Proven solutions to recurring software design problems. Categories: Creational, Structural, Behavioral.
🔗 See `Practice/p11_DesignPatterns/`

---

## E

**Effectively Final**
A variable that is never reassigned after initialization. Required for variables captured by lambdas and anonymous classes.
```java
int x = 10;  // effectively final — never reassigned
Runnable r = () -> System.out.println(x);  // OK
```

**Encapsulation**
Bundling data and methods, hiding internal state. Achieved using private fields and public getters/setters.

**Enum**
A special class representing a fixed set of constants. Can have fields, methods, and constructors.
```java
enum Planet { MERCURY(3.303e+23), VENUS(4.869e+24); /* ... */ }
```
📌 Enum constructors are always `private`. Enums are inherently `final` and `Serializable`.
🔗 See `Practice/p02_OOP/EnumDemo.java`

**EnumSet / EnumMap**
Specialized, high-performance Set/Map implementations designed for enum keys.

**equals()**
Method to check logical equality. Must override together with `hashCode()`.
📌 Contract: reflexive, symmetric, transitive, consistent, `x.equals(null)` = false.
🔗 See `Practice/p02_OOP/ObjectClassMethodsDemo.java`

**Exception**
An event that disrupts normal program flow. Handled using try-catch.
- **Checked**: must be caught or declared (`IOException`, `SQLException`)
- **Unchecked (Runtime)**: optional to catch (`NullPointerException`, `ArrayIndexOutOfBoundsException`)
🔗 See `Practice/p04_Exceptions/`

**ExecutorService**
A higher-level replacement for `Thread`. Manages a pool of threads.
```java
ExecutorService executor = Executors.newFixedThreadPool(4);
executor.submit(() -> doWork());
executor.shutdown();
```

**Extends**
Keyword for inheritance. `class Dog extends Animal`

---

## F

**Factory Pattern**
Creational design pattern that creates objects without exposing creation logic to the client.
🔗 See `Practice/p11_DesignPatterns/FactoryPatternDemo.java`

**Field**
A variable declared in a class (instance variable or class variable).

**Final**
- Final variable: constant, cannot be changed (but final object's *fields* can still change!)
- Final method: cannot be overridden
- Final class: cannot be extended (e.g., `String`)

**flatMap()**
Stream operation that maps each element to a stream, then flattens all streams into one.
```java
// ["hello","world"] → ['h','e','l','l','o','w','o','r','l','d']
words.stream().flatMap(w -> w.chars().mapToObj(c -> (char)c));
```

**For-each Loop**
Enhanced for loop for iterating collections: `for (Type item : collection)`

**Function<T, R>**
Functional interface: takes one argument of type T, returns R. `apply(T t)`.

**Functional Interface**
An interface with exactly one abstract method. Can be implemented with a lambda. Annotated with `@FunctionalInterface`.
🔗 See `Practice/p03_Interfaces/FunctionalInterfaceDemo.java`

**Future<V>**
Represents the result of an asynchronous computation. `get()` blocks until result is available.

---

## G

**Garbage Collection**
Automatic memory management. JVM reclaims unused objects. Cannot be forced — `System.gc()` is only a *suggestion*.

**Generics**
Type parameters for classes and methods: `ArrayList<String>`. Provides compile-time type safety.
⚠️ **Type erasure**: generics are removed at runtime — `List<String>` becomes `List` in bytecode.
🔗 See `Practice/p05_Collections/GenericClassDemo.java`

**Getter**
Method that returns the value of a private field.
```java
public String getName() { return name; }
```

**groupingBy()**
Collector that groups stream elements by a classifier function into a `Map<K, List<V>>`.
```java
Map<String, List<Student>> byDept = students.stream()
    .collect(Collectors.groupingBy(Student::getDepartment));
```

---

## H

**hashCode()**
Returns an integer hash for an object. Used by `HashMap`, `HashSet`, etc.
📌 Contract: if `a.equals(b)` then `a.hashCode() == b.hashCode()`. The reverse is not required.
🔗 See `Practice/p02_OOP/ObjectClassMethodsDemo.java`

**HashMap**
Key-value pair collection. O(1) average lookup by key. Does not maintain insertion order.
- `LinkedHashMap`: maintains insertion order
- `TreeMap`: maintains sorted key order (O(log n))
🔗 See `Practice/p05_Collections/HashMapDemo.java`

**Heap**
Memory area where objects are stored (vs. *stack* for method calls and local variables).

---

## I

**Immutable**
Object whose state cannot be changed after creation (e.g., `String`). Thread-safe by default.

**Implements**
Keyword for implementing interfaces: `class Dog implements Animal`

**Import**
Statement to use classes from other packages: `import java.util.ArrayList;`

**Inheritance**
Mechanism where a class inherits fields and methods from another class. Java supports single inheritance only.
📌 Rule: IS-A relationship must make logical sense (Dog IS-A Animal ✅, Car IS-A Engine ❌).
🔗 See `Practice/p02_OOP/InheritanceDemo.java`

**Inner Class**
A class defined inside another class. Four kinds: static nested, inner (non-static), local, anonymous.
🔗 See `Practice/p09_InnerClasses/InnerClassesDemo.java`

**Instance**
A specific occurrence of a class (an object).

**Interface**
Contract defining methods a class must implement. Since Java 8: can include `default` and `static` methods. Since Java 9: can include `private` methods.
```java
interface Drawable {
    void draw();
    default void describe() { System.out.println("I'm drawable"); }
}
```
🔗 See `Practice/p03_Interfaces/InterfaceExample.java`

**instanceof**
Operator to check if an object is an instance of a class/interface.

**IntStream / DoubleStream / LongStream**
Specialized primitive streams that avoid autoboxing overhead. Have extra methods like `sum()`, `average()`, `range()`.

---

## J

**JDK (Java Development Kit)**
Development environment including compiler, JRE, and tools.

**JRE (Java Runtime Environment)**
Environment for running Java programs.

**JVM (Java Virtual Machine)**
Executes Java bytecode. Provides platform independence ("write once, run anywhere").

---

## K

**Keyword**
Reserved word with special meaning in Java (e.g., `class`, `if`, `return`, `synchronized`). There are 67 keywords in Java.

---

## L

**Lambda Expression**
Concise way to implement a functional interface (Java 8+). Think: *anonymous function*.
```java
// Full syntax:
(String a, String b) -> { return a.length() - b.length(); }
// Shorthand:
(a, b) -> a.length() - b.length()
```
🔗 See `Practice/p08_LambdasAndStreams/LambdaExpressionsDemo.java`

**LinkedHashSet**
A HashSet that maintains insertion order.

---

## M

**Method**
A function defined in a class. Performs an action or returns a value.

**Method Overloading**
Multiple methods with the same name but different parameters (compile-time polymorphism).

**Method Overriding**
Subclass provides specific implementation of a superclass method (runtime polymorphism). Must use `@Override` annotation.

**Method Reference**
Shorthand for a lambda that just calls an existing method. Four types:
| Type             | Syntax           | Lambda Equivalent         |
| ---------------- | ---------------- | ------------------------- |
| Static           | `Math::abs`      | `x -> Math.abs(x)`        |
| Bound instance   | `str::length`    | `() -> str.length()`      |
| Unbound instance | `String::length` | `s -> s.length()`         |
| Constructor      | `ArrayList::new` | `() -> new ArrayList<>()` |
🔗 See `Practice/p08_LambdasAndStreams/MethodReferenceDemo.java`

**Modifier**
Keywords that change characteristics: `static`, `final`, `abstract`, `synchronized`, `volatile`, `transient`, etc.

---

## N

**new**
Operator to create a new object: `new Person()`

**null**
Represents the absence of a value. Reference types can be null.
⚠️ Calling any method on `null` throws `NullPointerException`.

---

## O

**Object**
Instance of a class. Has state (fields) and behavior (methods). All classes implicitly extend `java.lang.Object`.

**Object Class Methods**
Every Java class inherits from `Object`: `toString()`, `equals()`, `hashCode()`, `clone()`, `getClass()`, `finalize()`, `wait()`, `notify()`, `notifyAll()`.
🔗 See `Practice/p02_OOP/ObjectClassMethodsDemo.java`

**Observer Pattern**
Behavioral design pattern where subjects notify observers of state changes (pub/sub).
🔗 See `Practice/p11_DesignPatterns/ObserverPatternDemo.java`

**OOP (Object-Oriented Programming)**
Programming paradigm based on objects. Four pillars: Encapsulation, Inheritance, Polymorphism, Abstraction.

**Optional<T>**
Container that may or may not hold a value. Avoids `null` and `NullPointerException`.
```java
Optional<String> opt = Optional.ofNullable(name);
String result = opt.orElse("Unknown");
```

**Overloading**
See Method Overloading.

**Overriding**
See Method Overriding.

---

## P

**Package**
Namespace for organizing classes: `package com.example.myapp;`

**Parallel Stream**
A stream that processes elements concurrently using multiple threads via the Fork/Join framework.
```java
list.parallelStream().filter(x -> x > 10).collect(Collectors.toList());
```
⚠️ Not always faster — overhead of thread management can outweigh benefits for small data.

**Pass-by-Value**
Java is ALWAYS pass-by-value. For objects, the *reference* is passed by value (not the object itself).
🔗 See `Practice/p01_Basics/MethodsAndRecursionDemo.java`

**Polymorphism**
Ability of objects to take multiple forms. Parent reference can hold child objects.
- **Compile-time**: method overloading
- **Runtime**: method overriding (dynamic dispatch)
🔗 See `Practice/p02_OOP/PolymorphismDemo.java`

**Predicate<T>**
Functional interface: takes one argument, returns boolean. `test(T t)`. Supports `and()`, `or()`, `negate()`.

**Primitive Types**
Basic data types: `byte` (1B), `short` (2B), `int` (4B), `long` (8B), `float` (4B), `double` (8B), `char` (2B), `boolean`.
🔗 See `Practice/p01_Basics/DataTypesDemo.java`

**Private**
Access modifier. Accessible only within the same class.

**PriorityQueue**
A queue where elements are ordered by natural ordering or a Comparator. Not FIFO — smallest element comes out first (min-heap).
🔗 See `Practice/p05_Collections/QueueAndStackDemo.java`

**Protected**
Access modifier. Accessible within package and subclasses.

**Public**
Access modifier. Accessible from anywhere.

---

## Q

**Queue**
FIFO (First-In-First-Out) collection interface. Key methods: `offer()`, `poll()`, `peek()`.
🔗 See `Practice/p05_Collections/QueueAndStackDemo.java`

---

## R

**Race Condition**
Bug where program behavior depends on the relative timing of thread execution. Solved with synchronization.

**Record** *(Java 16+)*
Compact syntax for immutable data classes. Auto-generates constructor, getters, `equals()`, `hashCode()`, `toString()`.
```java
record Point(int x, int y) { }
```

**Recursion**
A method that calls itself. Must have a **base case** (stopping condition) and work toward it.
```java
int factorial(int n) {
    if (n <= 1) return 1;         // base case
    return n * factorial(n - 1);  // recursive case
}
```
🔗 See `Practice/p01_Basics/MethodsAndRecursionDemo.java`

**reduce()**
Terminal stream operation that combines all elements into a single result.
```java
int sum = numbers.stream().reduce(0, Integer::sum);
```

**Reference Type**
Non-primitive types (objects). Stores memory address, not value.

**Return**
Statement that exits a method and optionally returns a value.

**Runnable**
Functional interface representing a task with no return value. `void run()`.

**Runtime**
When the program is executing.

---

## S

**Serialization**
Converting an object to a byte stream for storage or transmission. Implement `Serializable`.
- `transient` keyword: excludes fields from serialization
- `serialVersionUID`: version control for serialized classes
🔗 See `Practice/p06_FileIO/SerializationDemo.java`

**Set**
Collection that contains no duplicate elements. Implementations: `HashSet` (unordered), `LinkedHashSet` (insertion order), `TreeSet` (sorted).
🔗 See `Practice/p05_Collections/SetDemo.java`

**Setter**
Method that sets the value of a private field.
```java
public void setName(String name) { this.name = name; }
```

**Singleton Pattern**
Creational design pattern ensuring a class has only one instance with a global access point.
🔗 See `Practice/p11_DesignPatterns/SingletonDemo.java`

**Static**
Belongs to the class, not instances. Shared across all objects.
- Static field: one copy per class
- Static method: can be called without an object
- Static block: runs once when class is loaded
🔗 See `Practice/p02_OOP/StaticAndFinalDemo.java`

**Strategy Pattern**
Behavioral design pattern that defines a family of interchangeable algorithms.
🔗 See `Practice/p11_DesignPatterns/StrategyPatternDemo.java`

**Stream**
A sequence of elements supporting aggregate operations (filter, map, reduce, collect). Does NOT store data — it's a *pipeline*.
📌 Streams are lazy (intermediate ops don't execute until a terminal op is called) and single-use.
🔗 See `Practice/p08_LambdasAndStreams/StreamsDemo.java`

**String**
Immutable sequence of characters. Reference type, not primitive.
- `==` compares references; `.equals()` compares content
- String pool: literals are interned for memory efficiency
- Use `StringBuilder` for many concatenations in a loop
🔗 See `Practice/p01_Basics/StringDeepDiveDemo.java`

**StringBuilder**
Mutable sequence of characters. Much faster than `String` for repeated concatenation.
```java
StringBuilder sb = new StringBuilder();
sb.append("hello").append(" ").append("world");
```

**Subclass**
Class that inherits from another class (child class).

**Super**
Keyword to access parent class members.
```java
super.method();  // Call parent method
super();         // Call parent constructor (must be first line)
```

**Superclass**
Class being inherited from (parent class).

**Supplier<T>**
Functional interface: takes no arguments, returns T. `get()`.

**synchronized**
Keyword ensuring only one thread can execute a block/method at a time.
```java
synchronized void deposit(double amount) { balance += amount; }
```
🔗 See `Practice/p10_Concurrency/ThreadBasicsDemo.java`

---

## T

**this**
Reference to the current object. Used to distinguish fields from parameters, or to call another constructor (`this()`).

**Thread**
A lightweight unit of execution. Created by extending `Thread` or implementing `Runnable`.
🔗 See `Practice/p10_Concurrency/ThreadBasicsDemo.java`

**throw**
Statement to throw an exception: `throw new Exception("Error");`

**throws**
Declares that a method may throw a checked exception.

**toString()**
Returns a string representation of an object. Override to provide meaningful output.
📌 Called automatically by `System.out.println()` and string concatenation.

**Transient**
Keyword that excludes a field from serialization.

**TreeSet**
A NavigableSet that maintains elements in sorted order. O(log n) for add/remove/contains.
Extra methods: `first()`, `last()`, `floor()`, `ceiling()`, `headSet()`, `tailSet()`, `subSet()`.

**try-catch**
Structure for handling exceptions.
```java
try {
    riskyOperation();
} catch (SpecificException e) {
    handle(e);
} finally {
    cleanup();  // ALWAYS runs
}
```

**try-with-resources**
Automatically closes resources implementing `AutoCloseable` when the block exits.
```java
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    // br is auto-closed
}
```
🔗 See `Practice/p06_FileIO/FileReadWrite.java`

**Type**
Classification of data (int, String, etc.).

**Type Erasure**
Generics are removed at compile time. `List<String>` becomes `List` in bytecode. This is why you can't do `new T()` or `instanceof List<String>`.

---

## U

**UnaryOperator<T>**
Functional interface: takes one argument, returns same type. Extends `Function<T,T>`.

---

## V

**Variable**
Named storage location for data.

**Varargs**
Variable-length argument list using `...`. Must be the last parameter.
```java
void print(String... items) { /* items is an array */ }
```
🔗 See `Practice/p01_Basics/MethodsAndRecursionDemo.java`

**void**
Return type indicating no value is returned.

**volatile**
Keyword ensuring a variable's value is always read from main memory, not a thread's cache.

---

## W

**Wrapper Class**
Object representation of primitives: `Integer`, `Double`, `Boolean`, `Character`, `Long`, `Float`, `Short`, `Byte`.
📌 Required for generics (`List<Integer>`, not `List<int>`).

**While Loop**
Loop that continues while condition is true.
```java
while (condition) {
    // code
}
```

---

## Quick Reference: Common Pairs to Know

| Concept                         | Contrast                                   |
| ------------------------------- | ------------------------------------------ |
| `==` vs `.equals()`             | Reference equality vs logical equality     |
| `String` vs `StringBuilder`     | Immutable vs mutable                       |
| `ArrayList` vs `LinkedList`     | Fast random access vs fast insert/remove   |
| `HashMap` vs `TreeMap`          | O(1) unordered vs O(log n) sorted          |
| `Comparable` vs `Comparator`    | Natural ordering vs custom ordering        |
| `Checked` vs `Unchecked`        | Must handle vs optional to handle          |
| `abstract class` vs `interface` | Partial impl + state vs pure contract      |
| `Runnable` vs `Callable`        | No return vs returns value                 |
| `synchronized` vs `Atomic`      | Lock-based vs lock-free                    |
| `Stream` vs `Collection`        | Lazy pipeline vs stored data               |
| Shallow copy vs Deep copy       | Copied references vs copied objects        |
| Autoboxing vs Unboxing          | Primitive → Wrapper vs Wrapper → Primitive |
