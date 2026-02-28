# ⭐ CSE215 — Common Java Errors & Debugging Guide

A comprehensive reference for common Java errors and how to fix them.
Covers compile-time, runtime, and advanced topic errors.

---

## Compilation Errors

### 1. `cannot find symbol`

**Cause:** Variable, method, or class not defined or misspelled.

```java
// ❌ Error
System.out.println(mesage);  // 'mesage' not defined

// ✅ Fix
String message = "Hello";
System.out.println(message);
```

**Checklist:**
- Check spelling (case-sensitive!)
- Is the variable declared?
- Is it in scope?
- Did you import the class?

---

### 2. `';' expected`

**Cause:** Missing semicolon at end of statement.

```java
// ❌ Error
int x = 5   // Missing semicolon

// ✅ Fix
int x = 5;
```

---

### 3. `class X is public, should be declared in a file named X.java`

**Cause:** Class name doesn't match filename.

```java
// File: Hello.java
// ❌ Error
public class HelloWorld { }

// ✅ Fix: Rename file to HelloWorld.java OR rename class to Hello
public class Hello { }
```

---

### 4. `incompatible types`

**Cause:** Assigning wrong type without proper casting.

```java
// ❌ Error
int x = 3.14;  // double to int

// ✅ Fix
int x = (int) 3.14;  // Explicit cast
// OR
double x = 3.14;     // Use correct type
```

---

### 5. `missing return statement`

**Cause:** Method with return type doesn't return in all paths.

```java
// ❌ Error
public int getNumber(boolean condition) {
    if (condition) {
        return 5;
    }
    // No return if condition is false!
}

// ✅ Fix
public int getNumber(boolean condition) {
    if (condition) {
        return 5;
    }
    return 0;  // Default return
}
```

---

## Runtime Errors (Exceptions)

### 1. `NullPointerException`

**Cause:** Calling method on `null` reference.

```java
// ❌ Error
String s = null;
System.out.println(s.length());  // NullPointerException

// ✅ Fix - Check for null
if (s != null) {
    System.out.println(s.length());
}

// ✅ Better - Use Optional
Optional.ofNullable(s).ifPresent(str -> System.out.println(str.length()));
```

**Prevention Tips:**
- Initialize variables when declaring
- Check for null before using
- Use Optional for values that might be absent

---

### 2. `ArrayIndexOutOfBoundsException`

**Cause:** Accessing array index that doesn't exist.

```java
// ❌ Error
int[] arr = {1, 2, 3};
System.out.println(arr[5]);  // Index 5 doesn't exist (valid: 0-2)

// ✅ Fix
if (index >= 0 && index < arr.length) {
    System.out.println(arr[index]);
}
```

**Remember:** Arrays are 0-indexed. Last valid index = `length - 1`

---

### 3. `ArithmeticException: / by zero`

**Cause:** Dividing by zero.

```java
// ❌ Error
int result = 10 / 0;

// ✅ Fix
int divisor = 0;
if (divisor != 0) {
    int result = 10 / divisor;
} else {
    System.out.println("Cannot divide by zero");
}
```

---

### 4. `ClassCastException`

**Cause:** Invalid type casting.

```java
// ❌ Error
Object obj = "Hello";
Integer num = (Integer) obj;  // String cannot be cast to Integer

// ✅ Fix - Check type first
if (obj instanceof Integer) {
    Integer num = (Integer) obj;
}
```

---

### 5. `NumberFormatException`

**Cause:** Parsing non-numeric string to number.

```java
// ❌ Error
int num = Integer.parseInt("abc");

// ✅ Fix
try {
    int num = Integer.parseInt(input);
} catch (NumberFormatException e) {
    System.out.println("Invalid number format");
}
```

---

### 6. `StringIndexOutOfBoundsException`

**Cause:** Accessing invalid string index.

```java
// ❌ Error
String s = "Hello";
char c = s.charAt(10);  // Only indices 0-4 valid

// ✅ Fix
if (index >= 0 && index < s.length()) {
    char c = s.charAt(index);
}
```

---

### 7. `ConcurrentModificationException`

**Cause:** Modifying collection while iterating.

```java
// ❌ Error
List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
for (String s : list) {
    if (s.equals("b")) {
        list.remove(s);  // ConcurrentModificationException
    }
}

// ✅ Fix - Use Iterator
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    if (it.next().equals("b")) {
        it.remove();  // Safe removal
    }
}

// ✅ Or - Use removeIf (Java 8+)
list.removeIf(s -> s.equals("b"));
```

---

## Debugging Tips

### 1. Read Error Messages Carefully
```
Exception in thread "main" java.lang.NullPointerException
    at MyClass.myMethod(MyClass.java:15)
    at Main.main(Main.java:8)
```
- **Exception type:** NullPointerException
- **Location:** MyClass.java, line 15
- **Called from:** Main.java, line 8

### 2. Use Print Statements
```java
System.out.println("DEBUG: x = " + x);
System.out.println("DEBUG: Entering method processData()");
```

### 3. Use Debugger
- Set breakpoints
- Step through code line by line
- Inspect variable values
- Watch expressions

### 4. Rubber Duck Debugging
Explain your code out loud (to a rubber duck or colleague). Often you'll find the bug while explaining.

---

## Quick Reference

| Error                             | Likely Cause                                 |
| --------------------------------- | -------------------------------------------- |
| `cannot find symbol`              | Typo, missing import, scope issue            |
| `NullPointerException`            | Calling method on null                       |
| `ArrayIndexOutOfBounds`           | Invalid array index                          |
| `ClassCastException`              | Wrong type cast                              |
| `NumberFormatException`           | Invalid string to number                     |
| `StackOverflowError`              | Infinite recursion                           |
| `OutOfMemoryError`                | Memory leak or huge data                     |
| `ConcurrentModificationException` | Modifying collection during iteration        |
| `UnsupportedOperationException`   | Modifying immutable collection (`List.of()`) |
| `IllegalStateException`           | Stream already consumed / builder misuse     |

---

## Advanced Errors

### 8. `UnsupportedOperationException`

**Cause:** Trying to modify an unmodifiable collection.

```java
// ❌ Error
List<String> list = List.of("a", "b", "c");
list.add("d");  // UnsupportedOperationException!

// Also happens with:
List<String> fixed = Arrays.asList("a", "b");
fixed.add("c");  // UnsupportedOperationException!

// ✅ Fix – wrap in a mutable list
List<String> mutable = new ArrayList<>(List.of("a", "b", "c"));
mutable.add("d");  // OK
```

---

### 9. `IllegalStateException: stream has already been operated upon`

**Cause:** Reusing a consumed stream.

```java
// ❌ Error
Stream<String> stream = list.stream();
stream.forEach(System.out::println);
stream.count();  // IllegalStateException!

// ✅ Fix – create a new stream each time
list.stream().forEach(System.out::println);
long count = list.stream().count();
```

📌 Streams are single-use pipelines. Once a terminal operation runs, the stream is gone.

---

### 10. Race Condition (no exception, just wrong results)

**Cause:** Multiple threads accessing shared data without synchronization.

```java
// ❌ Bug – counter may not reach 200000
class Counter {
    int count = 0;
    void increment() { count++; }  // NOT atomic!
}
// Two threads each call increment() 100000 times → result < 200000

// ✅ Fix Option 1 – synchronized
synchronized void increment() { count++; }

// ✅ Fix Option 2 – AtomicInteger
AtomicInteger count = new AtomicInteger(0);
void increment() { count.incrementAndGet(); }
```

🔗 See `Practice/p10_Concurrency/ThreadBasicsDemo.java`

---

### 11. Lambda / Effectively-Final Error

**Cause:** Lambda captures a variable that is later reassigned.

```java
// ❌ Error: "local variables referenced from a lambda must be final or effectively final"
int x = 10;
x = 20;  // reassigned!
Runnable r = () -> System.out.println(x);  // Compile error

// ✅ Fix – don't reassign (or use an array/AtomicInteger wrapper)
final int x = 20;
Runnable r = () -> System.out.println(x);  // OK

// ✅ Workaround for mutable state
int[] holder = {10};
Runnable r = () -> System.out.println(holder[0]);
holder[0] = 20;  // Arrays are reference types – OK
```

---

### 12. Forgetting `hashCode()` when overriding `equals()`

**Cause:** Objects compare as equal but hash differently → broken HashMap/HashSet behavior.

```java
// ❌ Bug – Student found in list, but NOT in HashSet
class Student {
    String name;
    @Override
    public boolean equals(Object o) {
        return name.equals(((Student) o).name);
    }
    // Missing hashCode()!
}

Set<Student> set = new HashSet<>();
set.add(new Student("Alice"));
set.contains(new Student("Alice"));  // false! Different hashCode

// ✅ Fix – ALWAYS override both
@Override
public int hashCode() {
    return Objects.hash(name);
}
```

📌 Rule: if `a.equals(b)` then `a.hashCode() == b.hashCode()`. Always override both or neither.

---

### 13. `NotSerializableException`

**Cause:** Trying to serialize a class that doesn't implement `Serializable`, or a non-serializable field in a serializable class.

```java
// ❌ Error
class Person {  // Missing implements Serializable
    String name;
}
ObjectOutputStream oos = new ObjectOutputStream(fos);
oos.writeObject(new Person());  // NotSerializableException

// ✅ Fix
class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    transient Socket connection;  // non-serializable field → mark transient
}
```

---

### 14. Infinite Stream

**Cause:** Using `Stream.generate()` or `Stream.iterate()` without `limit()`.

```java
// ❌ Hangs forever
Stream.generate(Math::random).forEach(System.out::println);

// ✅ Fix
Stream.generate(Math::random).limit(10).forEach(System.out::println);
```

---

## Debugging Levels

```
Level 1: Print Debugging
    System.out.println("DEBUG: x = " + x);

Level 2: Logging (better for real apps)
    Logger.getLogger("MyApp").info("Processing item: " + item);

Level 3: IDE Debugger
    • Set breakpoints
    • Step through code (F5/F6/F7)
    • Inspect variables
    • Evaluate expressions
    • Conditional breakpoints

Level 4: Rubber Duck Debugging
    Explain your code out loud — you'll often find the bug while explaining.
```
