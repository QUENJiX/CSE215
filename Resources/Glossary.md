# Java & OOP Glossary

Quick definitions of key terms for CSE215.

---

## A

**Abstract Class**
A class that cannot be instantiated and may contain abstract methods. Used as a base class.
```java
abstract class Shape {
    abstract double area();
}
```

**Access Modifier**
Keywords that set accessibility: `public`, `protected`, `private`, (default/package-private).

**Abstraction**
Hiding complex implementation details and showing only necessary features.

**ArrayList**
A resizable array implementation of the List interface.

---

## B

**Boolean**
Primitive data type with two values: `true` or `false`.

**Break**
Statement that exits the current loop or switch block.

---

## C

**Class**
A blueprint for creating objects. Defines attributes (fields) and behaviors (methods).

**Compiler**
Translates source code (.java) into bytecode (.class).

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
- Implicit: automatic (widening)
- Explicit: manual using `(Type)` (narrowing)

---

## D

**Default Constructor**
Constructor with no parameters, provided automatically if no constructor is defined.

**Dependency**
When one class uses another class.

---

## E

**Encapsulation**
Bundling data and methods, hiding internal state. Achieved using private fields and public getters/setters.

**Exception**
An event that disrupts normal program flow. Handled using try-catch.

**Extends**
Keyword for inheritance. `class Dog extends Animal`

---

## F

**Field**
A variable declared in a class (instance variable or class variable).

**Final**
- Final variable: constant, cannot be changed
- Final method: cannot be overridden
- Final class: cannot be extended

**For-each Loop**
Enhanced for loop for iterating collections: `for (Type item : collection)`

---

## G

**Garbage Collection**
Automatic memory management. JVM reclaims unused objects.

**Generics**
Type parameters for classes and methods: `ArrayList<String>`

**Getter**
Method that returns the value of a private field.
```java
public String getName() { return name; }
```

---

## H

**HashMap**
Key-value pair collection. Fast lookup by key.

**Heap**
Memory area where objects are stored.

---

## I

**Immutable**
Object whose state cannot be changed after creation (e.g., String).

**Implements**
Keyword for implementing interfaces: `class Dog implements Animal`

**Import**
Statement to use classes from other packages: `import java.util.ArrayList;`

**Inheritance**
Mechanism where a class inherits fields and methods from another class.

**Instance**
A specific occurrence of a class (an object).

**Interface**
Contract defining methods a class must implement. Contains only abstract methods (pre-Java 8).
```java
interface Drawable {
    void draw();
}
```

**instanceof**
Operator to check if an object is an instance of a class/interface.

---

## J

**JDK (Java Development Kit)**
Development environment including compiler, JRE, and tools.

**JRE (Java Runtime Environment)**
Environment for running Java programs.

**JVM (Java Virtual Machine)**
Executes Java bytecode. Provides platform independence.

---

## L

**Lambda Expression**
Concise way to represent anonymous functions (Java 8+).
```java
list.forEach(item -> System.out.println(item));
```

---

## M

**Method**
A function defined in a class. Performs an action or returns a value.

**Method Overloading**
Multiple methods with the same name but different parameters (compile-time polymorphism).

**Method Overriding**
Subclass provides specific implementation of a superclass method (runtime polymorphism).

**Modifier**
Keywords that change characteristics: `static`, `final`, `abstract`, etc.

---

## N

**new**
Operator to create a new object: `new Person()`

**null**
Represents the absence of a value. Reference types can be null.

---

## O

**Object**
Instance of a class. Has state (fields) and behavior (methods).

**OOP (Object-Oriented Programming)**
Programming paradigm based on objects. Four pillars: Encapsulation, Inheritance, Polymorphism, Abstraction.

**Overloading**
See Method Overloading.

**Overriding**
See Method Overriding.

---

## P

**Package**
Namespace for organizing classes: `package com.example.myapp;`

**Polymorphism**
Ability of objects to take multiple forms. Parent reference can hold child objects.

**Primitive Types**
Basic data types: `byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean`

**Private**
Access modifier. Accessible only within the same class.

**Protected**
Access modifier. Accessible within package and subclasses.

**Public**
Access modifier. Accessible from anywhere.

---

## R

**Reference Type**
Non-primitive types (objects). Stores memory address, not value.

**Return**
Statement that exits a method and optionally returns a value.

**Runtime**
When the program is executing.

---

## S

**Setter**
Method that sets the value of a private field.
```java
public void setName(String name) { this.name = name; }
```

**Static**
Belongs to the class, not instances. Shared across all objects.

**String**
Immutable sequence of characters. Reference type, not primitive.

**Subclass**
Class that inherits from another class (child class).

**Super**
Keyword to access parent class members.
```java
super.method();  // Call parent method
super();         // Call parent constructor
```

**Superclass**
Class being inherited from (parent class).

---

## T

**this**
Reference to the current object.

**throw**
Statement to throw an exception: `throw new Exception("Error");`

**throws**
Declares that a method may throw an exception.

**try-catch**
Structure for handling exceptions.

**Type**
Classification of data (int, String, etc.).

---

## V

**Variable**
Named storage location for data.

**void**
Return type indicating no value is returned.

---

## W

**Wrapper Class**
Object representation of primitives: `Integer`, `Double`, `Boolean`, etc.

**While Loop**
Loop that continues while condition is true.
```java
while (condition) {
    // code
}
```
