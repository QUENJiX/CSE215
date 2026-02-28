# Java Practice Package — Complete Reference & Study Guide

> **CSE215 - Programming Language II**  
> A comprehensive, topic-by-topic Java learning resource.  
> Every file is independently runnable. Every concept explained with intuitive comments.

---

## How to Compile & Run

```bash
# Navigate to the Practice folder
cd Practice

# Compile a single file (from inside Practice/)
javac p01_Basics/HelloWorld.java

# Run it (use the fully qualified class name with package)
java p01_Basics.HelloWorld

# Compile ALL files at once
javac p01_Basics/*.java p02_OOP/*.java p03_Interfaces/*.java p04_Exceptions/*.java p05_Collections/*.java p06_FileIO/*.java p07_Applications/*.java p08_LambdasAndStreams/*.java p09_InnerClasses/*.java p10_Concurrency/*.java p11_DesignPatterns/*.java

# Or compile everything with a find command (Git Bash / Linux / Mac)
find . -name "*.java" | xargs javac
```

> **Tip:** Each file has its own `main()` method. Pick any file and run it standalone.

---

## Package Index — Learning Path

### 📦 p01_Basics — Java Foundations
| File                           | Topics Covered                                                     |
| ------------------------------ | ------------------------------------------------------------------ |
| `HelloWorld.java`              | First program, main method anatomy, `System.out`                   |
| `DataTypesDemo.java`           | 8 primitives, type casting (widening/narrowing), autoboxing        |
| `ControlFlowDemo.java`         | if/else, switch (traditional + enhanced), loops, break/continue    |
| `MethodsAndRecursionDemo.java` | Methods, parameters, return types, overloading, varargs, recursion |
| `StringDeepDiveDemo.java`      | String immutability, StringBuilder, String pool, regex basics      |
| `ArraysDeepDiveDemo.java`      | 1D/2D arrays, Arrays utility, copying, sorting, common patterns    |

### 📦 p02_OOP — Object-Oriented Programming
| File                          | Topics Covered                                                             |
| ----------------------------- | -------------------------------------------------------------------------- |
| `ClassExample.java`           | Classes, constructors, getters/setters, encapsulation, `this`              |
| `InheritanceDemo.java`        | `extends`, `super`, method overriding, `instanceof`, upcasting/downcasting |
| `PolymorphismDemo.java`       | Compile-time (overloading) vs runtime (overriding) polymorphism            |
| `StaticAndFinalDemo.java`     | `static` fields/methods/blocks, `final` variables/methods/classes          |
| `ObjectClassMethodsDemo.java` | `equals()`, `hashCode()`, `toString()`, `clone()`, contract rules          |
| `EnumDemo.java`               | Enums, fields, methods, constructors, EnumSet, real-world usage            |

### 📦 p03_Interfaces — Abstraction & Contracts
| File                            | Topics Covered                                                             |
| ------------------------------- | -------------------------------------------------------------------------- |
| `AbstractClassDemo.java`        | Abstract classes, concrete methods, when to use vs interfaces              |
| `InterfaceExample.java`         | Interfaces, multiple implementation, `default`/`static` methods            |
| `FunctionalInterfaceDemo.java`  | `@FunctionalInterface`, built-in (Predicate, Function, Consumer, Supplier) |
| `ComparableComparatorDemo.java` | Natural ordering vs custom ordering, multi-field sorting                   |

### 📦 p04_Exceptions — Error Handling
| File                       | Topics Covered                                                          |
| -------------------------- | ----------------------------------------------------------------------- |
| `TryCatchDemo.java`        | try/catch/finally, multi-catch, try-with-resources, exception hierarchy |
| `CustomExceptionDemo.java` | Custom exceptions, exception chaining, best practices                   |

### 📦 p05_Collections — Data Structures
| File                          | Topics Covered                                                       |
| ----------------------------- | -------------------------------------------------------------------- |
| `ArrayListDemo.java`          | ArrayList, List interface, iteration, sorting, conversion            |
| `HashMapDemo.java`            | HashMap, LinkedHashMap, TreeMap, compute methods, word counter       |
| `GenericClassDemo.java`       | Generic classes, methods, bounded types, wildcards, type erasure     |
| `SetDemo.java`                | HashSet, TreeSet, LinkedHashSet, set operations (union/intersection) |
| `QueueAndStackDemo.java`      | Queue, Deque, PriorityQueue, Stack, BFS pattern                      |
| `CollectionsUtilityDemo.java` | Collections class, unmodifiable views, synchronized, frequency       |

### 📦 p06_FileIO — File Operations
| File                     | Topics Covered                                                |
| ------------------------ | ------------------------------------------------------------- |
| `FileReadWrite.java`     | FileWriter, BufferedReader, NIO Files, binary I/O             |
| `SerializationDemo.java` | Serializable, ObjectOutputStream, transient, serialVersionUID |

### 📦 p07_Applications — Complete Programs
| File                           | Topics Covered                                                 |
| ------------------------------ | -------------------------------------------------------------- |
| `Calculator.java`              | Expression parsing, switch expressions                         |
| `GuessingGame.java`            | Random, loops, user input                                      |
| `StudentManagementSystem.java` | Full OOP app: CRUD, collections, file persistence, menu-driven |

### 📦 p08_LambdasAndStreams — Modern Java (8+)
| File                         | Topics Covered                                                           |
| ---------------------------- | ------------------------------------------------------------------------ |
| `LambdaExpressionsDemo.java` | Lambda syntax, closures, functional interfaces, event patterns           |
| `StreamsDemo.java`           | Stream pipeline, intermediate/terminal ops, collectors, parallel streams |
| `MethodReferenceDemo.java`   | Static, instance, arbitrary object, constructor references               |

### 📦 p09_InnerClasses — Nested Types
| File                    | Topics Covered                                                   |
| ----------------------- | ---------------------------------------------------------------- |
| `InnerClassesDemo.java` | Static nested, inner, local, anonymous classes, when to use each |

### 📦 p10_Concurrency — Multithreading Basics
| File                    | Topics Covered                                                    |
| ----------------------- | ----------------------------------------------------------------- |
| `ThreadBasicsDemo.java` | Thread, Runnable, synchronization, ExecutorService, thread safety |

### 📦 p11_DesignPatterns — Common Patterns
| File                       | Topics Covered                              |
| -------------------------- | ------------------------------------------- |
| `SingletonDemo.java`       | Eager, lazy, thread-safe, enum singleton    |
| `FactoryPatternDemo.java`  | Simple factory, factory method, benefits    |
| `ObserverPatternDemo.java` | Event-driven design, publisher-subscriber   |
| `StrategyPatternDemo.java` | Swappable algorithms, open/closed principle |
| `BuilderPatternDemo.java`  | Fluent API, immutable object construction   |

---

## Suggested Study Order

```
Week 1-2:  p01_Basics      → Build your foundation
Week 3-4:  p02_OOP         → Learn to think in objects
Week 5-6:  p03_Interfaces  → Master abstraction
Week 7:    p04_Exceptions  → Handle errors gracefully
Week 8-9:  p05_Collections → Know your data structures
Week 10:   p06_FileIO      → Persist data
Week 11:   p08_Lambdas     → Write modern Java
Week 12:   p09-p11         → Advanced topics
Anytime:   p07_Applications → See full programs in action
```

---

## Conventions Used

- **💡 INTUITION:** comments explain the "why" behind the code
- **⚠️ GOTCHA:** marks common mistakes and pitfalls
- **📌 RULE:** states important rules to remember
- **🔗 SEE ALSO:** cross-references related demos
- **✅ BEST PRACTICE:** recommended approach
- **❌ BAD PRACTICE:** anti-patterns to avoid

---

*Last Updated: February 2026*
