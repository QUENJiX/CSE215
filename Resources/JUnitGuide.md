# JUnit Testing Guide

A quick introduction to unit testing in Java with JUnit 5.

---

## What is Unit Testing?

Unit testing verifies that individual units of code (methods, classes) work correctly in isolation.

**Benefits:**
- Catch bugs early
- Document expected behavior
- Enable safe refactoring
- Improve code design

---

## Setup

### Maven (pom.xml)
```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
</dependency>
```

### Gradle (build.gradle)
```groovy
testImplementation 'org.junit.jupiter:junit-jupiter:5.10.0'
```

---

## Basic Test Structure

```java
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    @DisplayName("Addition of two positive numbers")
    void testAddition() {
        int result = calculator.add(2, 3);
        assertEquals(5, result);
    }
    
    @Test
    void testSubtraction() {
        assertEquals(7, calculator.subtract(10, 3));
    }
    
    @AfterEach
    void tearDown() {
        calculator = null;
    }
}
```

---

## Common Assertions

```java
// Equality
assertEquals(expected, actual);
assertEquals(expected, actual, "Custom error message");

// Boolean
assertTrue(condition);
assertFalse(condition);

// Null checks
assertNull(object);
assertNotNull(object);

// Same reference
assertSame(expected, actual);
assertNotSame(expected, actual);

// Arrays
assertArrayEquals(expectedArray, actualArray);

// Exceptions
assertThrows(IllegalArgumentException.class, () -> {
    calculator.divide(10, 0);
});

// Timeout
assertTimeout(Duration.ofSeconds(2), () -> {
    // code that should finish in 2 seconds
});

// Group assertions
assertAll("person",
    () -> assertEquals("John", person.getName()),
    () -> assertEquals(25, person.getAge()),
    () -> assertNotNull(person.getAddress())
);
```

---

## Lifecycle Annotations

```java
class TestLifecycle {
    
    @BeforeAll
    static void initAll() {
        // Runs once before all tests
        System.out.println("Starting tests...");
    }
    
    @BeforeEach
    void init() {
        // Runs before each test
        System.out.println("Setting up test...");
    }
    
    @Test
    void testOne() {
        // Test code
    }
    
    @Test
    void testTwo() {
        // Test code
    }
    
    @AfterEach
    void tearDown() {
        // Runs after each test
        System.out.println("Cleaning up...");
    }
    
    @AfterAll
    static void tearDownAll() {
        // Runs once after all tests
        System.out.println("All tests complete.");
    }
}
```

---

## Parameterized Tests

Test same logic with different inputs:

```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

class ParameterizedTests {
    
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void testPositiveNumbers(int num) {
        assertTrue(num > 0);
    }
    
    @ParameterizedTest
    @CsvSource({
        "1, 2, 3",
        "5, 5, 10",
        "-1, 1, 0"
    })
    void testAddition(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }
    
    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void testIsBlank(String input, boolean expected) {
        assertEquals(expected, input == null || input.isBlank());
    }
    
    static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
            Arguments.of(null, true),
            Arguments.of("", true),
            Arguments.of("  ", true),
            Arguments.of("hello", false)
        );
    }
}
```

---

## Complete Example

**Class to test:**
```java
public class BankAccount {
    private double balance;
    
    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = initialBalance;
    }
    
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        balance += amount;
    }
    
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal must be positive");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds");
        }
        balance -= amount;
    }
    
    public double getBalance() {
        return balance;
    }
}
```

**Test class:**
```java
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    
    private BankAccount account;
    
    @BeforeEach
    void setUp() {
        account = new BankAccount(100.0);
    }
    
    @Test
    @DisplayName("Initial balance is set correctly")
    void testInitialBalance() {
        assertEquals(100.0, account.getBalance());
    }
    
    @Test
    @DisplayName("Negative initial balance throws exception")
    void testNegativeInitialBalance() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount(-50);
        });
    }
    
    @Test
    @DisplayName("Deposit increases balance")
    void testDeposit() {
        account.deposit(50);
        assertEquals(150.0, account.getBalance());
    }
    
    @Test
    @DisplayName("Zero deposit throws exception")
    void testInvalidDeposit() {
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(0);
        });
    }
    
    @Test
    @DisplayName("Withdrawal decreases balance")
    void testWithdraw() {
        account.withdraw(30);
        assertEquals(70.0, account.getBalance());
    }
    
    @Test
    @DisplayName("Overdraft throws exception")
    void testOverdraft() {
        assertThrows(IllegalStateException.class, () -> {
            account.withdraw(200);
        });
    }
    
    @Test
    @DisplayName("Multiple transactions work correctly")
    void testMultipleTransactions() {
        account.deposit(50);
        account.withdraw(30);
        account.deposit(20);
        assertEquals(140.0, account.getBalance());
    }
}
```

---

## Best Practices

1. **One assertion per test** (when possible)
2. **Use descriptive test names**
3. **Follow AAA pattern:** Arrange, Act, Assert
4. **Test edge cases:** null, empty, boundary values
5. **Keep tests independent** - no shared state
6. **Don't test private methods directly**
7. **Run tests frequently** during development

---

## Running Tests

**Command line (Maven):**
```bash
mvn test
```

**Command line (Gradle):**
```bash
gradle test
```

**VS Code:** Click "Run Test" above test methods
