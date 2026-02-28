# ⭐ CSE215 — Exam Preparation Guide

A structured study guide covering **every topic** in the course — from basics to advanced.
Organized chronologically by study week. Cross-referenced with Practice/ demos.

---

## Study Checklist by Topic

### Week 1-2: Java Basics
- [ ] Primitive data types (8 types) and their ranges
- [ ] Variables, constants, naming conventions
- [ ] Operators (arithmetic, relational, logical, assignment)
- [ ] Type casting (implicit/widening vs explicit/narrowing)
- [ ] Overflow and floating-point gotchas (`0.1 + 0.2 != 0.3`)
- [ ] Wrapper classes and autoboxing/unboxing
- [ ] Integer cache trap (`==` for Integer 127 vs 128)
- [ ] String class: immutability, pool, `==` vs `.equals()`
- [ ] StringBuilder for performance
- [ ] String formatting with `printf` / `String.format`
- [ ] Regular expressions basics (`Pattern`, `Matcher`)
- [ ] Arrays: 1D, 2D, jagged arrays, `Arrays` utility class
- [ ] Control flow (if-else, switch, enhanced switch expressions)
- [ ] Loops (for, while, do-while, for-each, labeled breaks)
- [ ] Methods: parameters, return types, overloading, varargs
- [ ] Pass-by-value semantics (even for objects!)
- [ ] Recursion: base case, recursive case, memoization
🔗 Practice: `p01_Basics/`

### Week 3-4: Classes & Objects
- [ ] Class structure (fields, methods, constructors)
- [ ] Object creation with `new` keyword
- [ ] Access modifiers (public, private, protected, default)
- [ ] Encapsulation (getters/setters)
- [ ] `this` keyword usage (field disambiguation, constructor chaining)
- [ ] Static fields, methods, and initializer blocks
- [ ] `final` variables, methods, and classes
- [ ] Method overloading
- [ ] Object class methods: `toString()`, `equals()`, `hashCode()`, `clone()`
- [ ] equals/hashCode contract
- [ ] Shallow vs deep copy
- [ ] Enums: constants, fields, methods, abstract methods, EnumSet/EnumMap
🔗 Practice: `p02_OOP/`

### Week 5-6: Inheritance & Polymorphism
- [ ] Inheritance with `extends` (IS-A relationship)
- [ ] `super` keyword (constructor, method calls)
- [ ] Method overriding vs overloading
- [ ] `@Override` annotation
- [ ] Polymorphism (compile-time vs runtime / dynamic dispatch)
- [ ] Upcasting and downcasting
- [ ] `instanceof` operator
- [ ] `final` preventing override or extension
- [ ] Composition vs Inheritance ("has-a" vs "is-a")
🔗 Practice: `p02_OOP/InheritanceDemo.java`, `PolymorphismDemo.java`

### Week 7-8: Abstraction & Interfaces
- [ ] Abstract classes and methods (template pattern)
- [ ] When to use abstract class vs interface
- [ ] Interface declaration and implementation
- [ ] Default and static methods in interfaces (Java 8+)
- [ ] Private methods in interfaces (Java 9+)
- [ ] Multiple interface implementation
- [ ] Interface as a type
- [ ] Functional interfaces (`@FunctionalInterface`)
- [ ] Built-in functional interfaces: `Predicate`, `Function`, `Consumer`, `Supplier`
- [ ] `Comparable` vs `Comparator`
- [ ] `Comparator.comparing()`, chaining, `nullsFirst`/`nullsLast`
🔗 Practice: `p03_Interfaces/`

### Week 9: Exception Handling
- [ ] Exception hierarchy: `Throwable` → `Error` / `Exception` → `RuntimeException`
- [ ] Checked vs unchecked exceptions
- [ ] try-catch-finally structure
- [ ] Multi-catch: `catch (IOException | SQLException e)`
- [ ] throw vs throws
- [ ] Custom exceptions (extending `Exception` or `RuntimeException`)
- [ ] try-with-resources (`AutoCloseable`)
- [ ] Exception chaining (cause)
🔗 Practice: `p04_Exceptions/`

### Week 10-11: Collections & Generics
- [ ] Collection hierarchy: `Collection` → `List`, `Set`, `Queue`
- [ ] `ArrayList` vs `LinkedList` (performance trade-offs)
- [ ] `HashSet`, `LinkedHashSet`, `TreeSet` (NavigableSet)
- [ ] Set operations: union, intersection, difference
- [ ] `HashMap`, `LinkedHashMap`, `TreeMap` (NavigableMap)
- [ ] `Queue`, `PriorityQueue` (min-heap / max-heap)
- [ ] `Deque` / `ArrayDeque` (stack + queue)
- [ ] `Collections` utility: sort, binarySearch, unmodifiable, synchronized
- [ ] `List.of()`, `Map.of()`, `Set.of()` (immutable factories)
- [ ] Iterating: for-each, Iterator, `forEach()` with lambda
- [ ] Generic classes, methods, and interfaces
- [ ] Type parameters (`T`, `E`, `K`, `V`)
- [ ] Bounded type parameters (`<T extends Comparable<T>>`)
- [ ] Wildcards (`?`, `? extends T`, `? super T`) — PECS rule
- [ ] Type erasure
🔗 Practice: `p05_Collections/`

### Week 12: File I/O & Serialization
- [ ] FileReader/FileWriter (old I/O)
- [ ] BufferedReader/BufferedWriter
- [ ] NIO: `Files.readAllLines()`, `Files.write()`, `Files.lines()` (stream)
- [ ] try-with-resources for file handling
- [ ] Serialization: `Serializable`, `ObjectOutputStream`, `ObjectInputStream`
- [ ] `transient` keyword
- [ ] `serialVersionUID`
- [ ] Custom serialization (`writeObject` / `readObject`)
🔗 Practice: `p06_FileIO/`

### Week 13: Lambdas & Streams
- [ ] Lambda syntax: `(params) -> expression` or `(params) -> { statements; }`
- [ ] Variable capture and effectively-final rule
- [ ] Method references: four types (static, bound, unbound, constructor)
- [ ] Stream creation: `.stream()`, `Stream.of()`, `IntStream.range()`
- [ ] Intermediate operations: `filter`, `map`, `flatMap`, `sorted`, `distinct`, `limit`, `skip`, `peek`
- [ ] Terminal operations: `collect`, `forEach`, `reduce`, `count`, `min`, `max`, `anyMatch`, `findFirst`, `toArray`
- [ ] Collectors: `toList`, `toSet`, `toMap`, `joining`, `groupingBy`, `partitioningBy`, `summarizingInt`
- [ ] Primitive streams: `IntStream`, `DoubleStream`, `LongStream`
- [ ] Parallel streams (and when NOT to use them)
🔗 Practice: `p08_LambdasAndStreams/`

### Week 14: Advanced Topics
- [ ] Inner classes: static nested, inner, local, anonymous
- [ ] Anonymous class vs lambda (`this` reference difference)
- [ ] Thread basics: `Thread`, `Runnable`, `Callable`, `Future`
- [ ] Race conditions and synchronization (`synchronized`, `AtomicInteger`)
- [ ] `ExecutorService` and thread pools
- [ ] `ConcurrentHashMap`
- [ ] Design patterns: Singleton, Factory, Observer, Strategy, Builder
🔗 Practice: `p09_InnerClasses/`, `p10_Concurrency/`, `p11_DesignPatterns/`

---

## Key Concepts to Know

### Four Pillars of OOP

| Pillar            | Definition                    | Java Implementation              |
| ----------------- | ----------------------------- | -------------------------------- |
| **Encapsulation** | Hide internal state           | Private fields + getters/setters |
| **Inheritance**   | Reuse code from parent class  | `extends` keyword                |
| **Polymorphism**  | One interface, multiple forms | Method overriding                |
| **Abstraction**   | Hide complexity               | Abstract classes, interfaces     |

### Access Modifiers

| Modifier    | Same Class | Same Package | Subclass | Other |
| ----------- | ---------- | ------------ | -------- | ----- |
| `public`    | ✓          | ✓            | ✓        | ✓     |
| `protected` | ✓          | ✓            | ✓        | ✗     |
| (default)   | ✓          | ✓            | ✗        | ✗     |
| `private`   | ✓          | ✗            | ✗        | ✗     |

### Method Overloading vs Overriding

| Feature          | Overloading  | Overriding              |
| ---------------- | ------------ | ----------------------- |
| Same name        | ✓            | ✓                       |
| Same parameters  | ✗            | ✓                       |
| Same return type | Not required | Required (or covariant) |
| Inheritance      | Not required | Required                |
| Polymorphism     | Compile-time | Runtime                 |
| `@Override`      | No           | Yes                     |

---

## Practice Questions

### Multiple Choice (Sample)

**1. Which is NOT a primitive type in Java?**
- a) int
- b) double
- c) String ✓
- d) boolean

**2. What is the output?**
```java
int x = 5;
System.out.println(x++);
```
- a) 5 ✓
- b) 6
- c) Error
- d) 4

**3. Which keyword prevents a class from being inherited?**
- a) static
- b) abstract
- c) final ✓
- d) private

---

### Short Answer (Sample)

**1. Explain the difference between `==` and `.equals()` for Strings.**

> `==` compares object references (memory addresses), while `.equals()` compares the actual content of the strings. For String comparison, always use `.equals()`.

**2. What is the output?**
```java
class Parent {
    void show() { System.out.println("Parent"); }
}
class Child extends Parent {
    void show() { System.out.println("Child"); }
}
Parent p = new Child();
p.show();
```
> Output: "Child" (runtime polymorphism - the actual object type determines which method runs)

**3. Why do we use interfaces?**

> Interfaces define a contract that classes must follow. They enable:
> - Multiple inheritance of type
> - Loose coupling
> - Polymorphism across unrelated classes
> - Abstraction of behavior

---

### Coding Questions (Sample)

**1. Create a class hierarchy:**
- Abstract class `Shape` with abstract method `area()`
- Classes `Circle` and `Rectangle` extending `Shape`
- Calculate and print areas

**2. Implement a `Stack` class using ArrayList:**
- Methods: `push()`, `pop()`, `peek()`, `isEmpty()`
- Handle empty stack with custom exception

**3. File Processing:**
- Read student data from CSV file
- Calculate average grade
- Write results to output file

**4. Streams & Lambdas:**
```java
// Given a List<Student> where Student has name, department, gpa:
// a) Find the average GPA of CS students
// b) Get a list of names of students with GPA > 3.5, sorted alphabetically
// c) Group students by department, showing count per department
```
<details>
<summary>Solution</summary>

```java
// a)
double avgGpa = students.stream()
    .filter(s -> s.getDepartment().equals("CS"))
    .mapToDouble(Student::getGpa)
    .average().orElse(0.0);

// b)
List<String> honors = students.stream()
    .filter(s -> s.getGpa() > 3.5)
    .map(Student::getName)
    .sorted()
    .collect(Collectors.toList());

// c)
Map<String, Long> countByDept = students.stream()
    .collect(Collectors.groupingBy(Student::getDepartment, Collectors.counting()));
```
</details>

**5. Thread Safety:**
```java
// A BankAccount class has a balance field.
// Two threads deposit $100 each, 1000 times.
// Expected final balance: $200,000
// Why might it be less? Fix it using TWO different approaches.
```
<details>
<summary>Solution</summary>

Race condition: read-modify-write is not atomic.

Approach 1 — synchronized:
```java
synchronized void deposit(double amount) { balance += amount; }
```

Approach 2 — AtomicInteger (for int-based balance):
```java
AtomicInteger balance = new AtomicInteger(0);
void deposit(int amount) { balance.addAndGet(amount); }
```
</details>

**6. Design Patterns:**
- Implement a Singleton `Logger` class (thread-safe)
- Implement a Factory that creates `Notification` objects (Email, SMS, Push) based on a type string
- Implement a Strategy pattern for sorting a list (by name, by age, by GPA) using lambdas

---

## Exam Tips

### Before the Exam
1. Review lecture notes and practice code
2. Do practice problems without looking at solutions
3. Understand concepts, don't just memorize
4. Get enough sleep the night before

### During the Exam
1. Read questions carefully
2. Start with questions you know
3. Manage time - don't get stuck
4. For code questions:
   - Write pseudocode first if helpful
   - Use proper indentation
   - Include necessary imports
5. Review answers if time permits

### Common Mistakes to Avoid
- Forgetting semicolons
- Using `=` instead of `==`
- Not handling null values
- Incorrect loop bounds (off-by-one errors)
- Forgetting to call `super()` in constructor
- Using `==` instead of `.equals()` for String comparison
- Modifying a collection while iterating (use `Iterator.remove()` or streams)
- Forgetting `break` in switch cases (unless using enhanced switch)
- Not overriding `hashCode()` when overriding `equals()`
- Using raw types instead of generics (`List` instead of `List<String>`)
- Catching `Exception` instead of specific exceptions
- Assuming parallel streams are always faster

---

## Quick Concept Cards

### Lambda → Method Reference Conversion
```
x -> Math.abs(x)         →  Math::abs          (static)
x -> x.toLowerCase()     →  String::toLowerCase (unbound)
x -> obj.process(x)      →  obj::process        (bound)
() -> new ArrayList<>()   →  ArrayList::new      (constructor)
```

### Stream Pipeline Template
```
collection.stream()             // 1. Source
    .filter(x -> condition)     // 2. Intermediate (lazy)
    .map(x -> transform)        // 3. Intermediate (lazy)
    .collect(Collectors.toList()) // 4. Terminal (triggers execution)
```

### equals/hashCode Contract
```
a.equals(b) == true  →  a.hashCode() == b.hashCode()   MUST be true
a.hashCode() == b.hashCode()  →  a.equals(b)           NOT guaranteed
```

### Exception Decision Tree
```
Can the caller recover?
├── Yes → Checked exception (extend Exception)
└── No  → Unchecked exception (extend RuntimeException)

Is it a programming bug?
├── Yes → RuntimeException (NullPointer, ArrayIndexOutOfBounds)
└── No  → Checked exception (IOException, SQLException)
```

### Big-O Quick Reference
| Operation    | ArrayList | LinkedList | HashSet | TreeSet  | HashMap | TreeMap  |
| ------------ | --------- | ---------- | ------- | -------- | ------- | -------- |
| get/contains | O(1)/O(n) | O(n)       | O(1)    | O(log n) | O(1)    | O(log n) |
| add/put      | O(1)*     | O(1)       | O(1)    | O(log n) | O(1)    | O(log n) |
| remove       | O(n)      | O(1)**     | O(1)    | O(log n) | O(1)    | O(log n) |

\* amortized, ** if you have the node reference
- Not closing resources (use try-with-resources)

---

## Quick Reference Card

```
// Class Structure
public class ClassName extends Parent implements Interface {
    private Type field;
    
    public ClassName() { }  // Constructor
    
    public Type getField() { return field; }
    public void setField(Type f) { this.field = f; }
}

// Inheritance
class Child extends Parent {
    @Override
    void method() { super.method(); }
}

// Interface
interface Doable { void doIt(); }
class Worker implements Doable {
    public void doIt() { }
}

// Exception
try {
    // risky code
} catch (SpecificException e) {
    // handle
} finally {
    // cleanup
}

// Collections
List<String> list = new ArrayList<>();
Map<String, Integer> map = new HashMap<>();
```

---

Good luck on your exam! 🎓
