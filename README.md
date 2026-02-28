# CSE215 - Programming Language II (Java)

An organized course folder for learning Java and Object-Oriented Programming.

---

## Course Structure

```
CSE215/
├── Lectures/          # Lecture notes, syllabus, and templates
├── Assignments/       # Homework assignments
├── Labs/              # Hands-on lab exercises
├── Projects/          # Course projects
├── Practice/          # ⭐ Comprehensive Java practice (35+ runnable demos)
│   ├── Main.java      # Entry point — run for package index
│   ├── p01_Basics/    # Data types, control flow, methods, strings, arrays
│   ├── p02_OOP/       # Classes, inheritance, polymorphism, enums, static/final
│   ├── p03_Interfaces/# Interfaces, abstract classes, functional interfaces
│   ├── p04_Exceptions/# Exception handling, custom exceptions
│   ├── p05_Collections/# ArrayList, HashMap, Set, Queue, Stack, generics
│   ├── p06_FileIO/    # File read/write, serialization
│   ├── p07_Applications/# Calculator, guessing game, student management system
│   ├── p08_LambdasAndStreams/ # Lambdas, Streams API, method references
│   ├── p09_InnerClasses/     # Static nested, inner, local, anonymous classes
│   ├── p10_Concurrency/      # Threads, synchronization, ExecutorService
│   └── p11_DesignPatterns/   # Singleton, Factory, Observer, Strategy, Builder
│
└── Resources/         # Reference materials and study guides
```

---

## Getting Started

1. Ensure you have **JDK 17+** installed
2. Verify installation:
   ```bash
   java --version
   javac --version
   ```
3. Recommended IDE: VS Code with Java Extension Pack

---

## Compilation & Execution

```bash
# Compile a Java file
javac FileName.java

# Run the compiled class
java FileName
```

---

## Quick Course Index

### Core Materials
| Resource                         | Description                         |
| -------------------------------- | ----------------------------------- |
| [Syllabus](Lectures/Syllabus.md) | Course overview, topics, assessment |
| [Lectures](Lectures/README.md)   | Lecture notes and templates         |
| [Practice](Practice/README.md)   | Organized example code              |
| [Labs](Labs/README.md)           | Hands-on exercises                  |

### Study Resources
| Resource                                              | Description                        |
| ----------------------------------------------------- | ---------------------------------- |
| [Java Cheat Sheet](Resources/JavaCheatSheet.md)       | Quick syntax reference             |
| [OOP Design Patterns](Resources/OOPDesignPatterns.md) | Common patterns with examples      |
| [Common Errors](Resources/CommonErrors.md)            | Debugging guide                    |
| [JUnit Guide](Resources/JUnitGuide.md)                | Unit testing basics                |
| [Glossary](Resources/Glossary.md)                     | Java/OOP terminology               |
| [Exam Prep](Resources/ExamPrep.md)                    | Study guide and practice questions |

### Templates
| Template                                                   | Use For              |
| ---------------------------------------------------------- | -------------------- |
| [Assignment Template](Assignments/AssignmentTemplate.md)   | Homework submissions |
| [Project Template](Projects/ProjectTemplate.md)            | Course projects      |
| [Lecture Notes Template](Lectures/LectureNotesTemplate.md) | Taking notes         |

---

## Course Topics

- [x] Java Basics (types, operators, control flow, methods)
- [x] Strings and Arrays (deep-dive)
- [x] Classes and Objects (encapsulation, static, final)
- [x] Inheritance and Polymorphism
- [x] Abstraction and Interfaces
- [x] Functional Interfaces and Lambdas
- [x] Enums and Inner Classes
- [x] Exception Handling (built-in + custom)
- [x] Collections Framework (List, Set, Map, Queue)
- [x] Generics and Wildcards
- [x] Streams API and Method References
- [x] File I/O and Serialization
- [x] Multithreading and Concurrency
- [x] Design Patterns (Singleton, Factory, Observer, Strategy, Builder)

---

## Learning Path

```
Week 1-2    Week 3-4      Week 5-6        Week 7-8
   │           │             │               │
   ▼           ▼             ▼               ▼
┌──────┐   ┌──────┐     ┌─────────┐    ┌──────────┐
│Basics│──▶│ OOP  │────▶│Inherit- │───▶│Interfaces│
│      │   │      │     │  ance   │    │Abstract  │
└──────┘   └──────┘     └─────────┘    └──────────┘
                                             │
              ┌──────────────────────────────┘
              ▼
         Week 9-10       Week 11-12      Week 13-14
            │               │                │
            ▼               ▼                ▼
      ┌──────────┐    ┌──────────┐    ┌──────────┐
      │Exception │───▶│Collection│───▶│ Lambdas  │
      │ Handling │    │ & FileIO │    │ Streams  │
      └──────────┘    └──────────┘    │ Patterns │
                                      └──────────┘
```

---

## Tips for Success

1. **Practice daily** - Code every day, even for 30 minutes
2. **Type, don't copy** - Typing code helps you learn
3. **Read error messages** - They tell you what's wrong
4. **Use the debugger** - Step through code to understand flow
5. **Experiment** - Modify examples and see what happens
6. **Ask questions** - Use office hours and study groups

---

*Last Updated: January 2026*
