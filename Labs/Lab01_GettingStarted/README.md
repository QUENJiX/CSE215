# Lab 01: Getting Started with Java

## Objectives
- Set up Java development environment
- Write, compile, and run your first Java program
- Understand basic Java syntax
- Practice using variables and data types

## Prerequisites
- JDK 17+ installed (`java --version` and `javac --version`)
- VS Code with Java Extension Pack (or preferred IDE)

---

## Exercise 1: Hello World (5 points)

Create a file named `HelloWorld.java`:

```java
public class HelloWorld {
    public static void main(String[] args) {
        // TODO: Print "Hello, CSE215!" to the console
    }
}
```

**Compile and run:**
```bash
javac HelloWorld.java
java HelloWorld
```

---

## Exercise 2: Personal Info (10 points)

Create `PersonalInfo.java` that prints:
- Your name
- Your student ID
- Your favorite programming language
- Why you're taking this course

**Sample Output:**
```
=== Personal Information ===
Name: John Doe
Student ID: 12345678
Favorite Language: Python
Why CSE215: To learn object-oriented programming
```

---

## Exercise 3: Calculator (15 points)

Create `SimpleCalculator.java` that:
1. Declares two integer variables with values of your choice
2. Performs and prints all 5 arithmetic operations (+, -, *, /, %)
3. Uses proper formatting

**Sample Output:**
```
Number 1: 15
Number 2: 4
Addition: 15 + 4 = 19
Subtraction: 15 - 4 = 11
Multiplication: 15 * 4 = 60
Division: 15 / 4 = 3
Modulus: 15 % 4 = 3
```

---

## Exercise 4: Data Types Exploration (20 points)

Create `DataTypes.java` that demonstrates:
1. All 8 primitive data types with example values
2. Type casting (both implicit and explicit)
3. String operations (concatenation, length, substring)

**Requirements:**
- Include comments explaining each data type
- Show the range or purpose of each type
- Demonstrate at least 3 string methods

---

## Submission
- Create a folder named `Lastname_Lab01`
- Include all `.java` source files
- Add a `README.md` with compilation/run instructions
- Zip and submit by the deadline

## Grading Rubric
| Exercise | Points |
|----------|--------|
| Exercise 1 | 5 |
| Exercise 2 | 10 |
| Exercise 3 | 15 |
| Exercise 4 | 20 |
| Code style & comments | 10 |
| **Total** | **60** |
