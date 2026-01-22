# CSE215 Exam Preparation Guide

A structured study guide for Java & OOP examinations.

---

## Study Checklist by Topic

### Week 1-2: Java Basics
- [ ] Primitive data types (8 types) and their ranges
- [ ] Variables, constants, naming conventions
- [ ] Operators (arithmetic, relational, logical, assignment)
- [ ] Type casting (implicit vs explicit)
- [ ] String class and common methods
- [ ] Arrays (declaration, initialization, iteration)
- [ ] Control flow (if-else, switch, loops)

### Week 3-4: Classes & Objects
- [ ] Class structure (fields, methods, constructors)
- [ ] Object creation with `new` keyword
- [ ] Access modifiers (public, private, protected, default)
- [ ] Encapsulation (getters/setters)
- [ ] `this` keyword usage
- [ ] Static members (variables, methods)
- [ ] Method overloading

### Week 5-6: Inheritance & Polymorphism
- [ ] Inheritance with `extends`
- [ ] `super` keyword (constructor, method calls)
- [ ] Method overriding vs overloading
- [ ] Polymorphism (compile-time vs runtime)
- [ ] Upcasting and downcasting
- [ ] `instanceof` operator
- [ ] `final` keyword (class, method, variable)

### Week 7-8: Abstraction & Interfaces
- [ ] Abstract classes and methods
- [ ] When to use abstract class vs interface
- [ ] Interface declaration and implementation
- [ ] Default and static methods in interfaces (Java 8+)
- [ ] Multiple interface implementation
- [ ] Interface as type

### Week 9: Exception Handling
- [ ] try-catch-finally structure
- [ ] Checked vs unchecked exceptions
- [ ] throw vs throws
- [ ] Custom exceptions
- [ ] try-with-resources
- [ ] Exception hierarchy

### Week 10-11: Collections & Generics
- [ ] List, Set, Map interfaces
- [ ] ArrayList vs LinkedList
- [ ] HashMap, HashSet, TreeMap
- [ ] Iterating collections (for-each, iterator)
- [ ] Generic classes and methods
- [ ] Wildcards (?, extends, super)

### Week 12: File I/O
- [ ] FileReader/FileWriter
- [ ] BufferedReader/BufferedWriter
- [ ] Reading and writing text files
- [ ] try-with-resources for file handling
- [ ] NIO (Files class methods)

---

## Key Concepts to Know

### Four Pillars of OOP

| Pillar | Definition | Java Implementation |
|--------|------------|---------------------|
| **Encapsulation** | Hide internal state | Private fields + getters/setters |
| **Inheritance** | Reuse code from parent class | `extends` keyword |
| **Polymorphism** | One interface, multiple forms | Method overriding |
| **Abstraction** | Hide complexity | Abstract classes, interfaces |

### Access Modifiers

| Modifier | Same Class | Same Package | Subclass | Other |
|----------|------------|--------------|----------|-------|
| `public` | ✓ | ✓ | ✓ | ✓ |
| `protected` | ✓ | ✓ | ✓ | ✗ |
| (default) | ✓ | ✓ | ✗ | ✗ |
| `private` | ✓ | ✗ | ✗ | ✗ |

### Method Overloading vs Overriding

| Feature | Overloading | Overriding |
|---------|-------------|------------|
| Same name | ✓ | ✓ |
| Same parameters | ✗ | ✓ |
| Same return type | Not required | Required (or covariant) |
| Inheritance | Not required | Required |
| Polymorphism | Compile-time | Runtime |
| `@Override` | No | Yes |

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
