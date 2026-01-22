# OOP Design Patterns in Java

A quick reference for commonly used design patterns with Java examples.

---

## 1. Singleton Pattern

**Purpose:** Ensure only one instance of a class exists.

**When to use:**
- Database connections
- Configuration managers
- Logging

```java
public class Singleton {
    private static Singleton instance;
    
    private Singleton() { }  // Private constructor
    
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

// Thread-safe version
public class ThreadSafeSingleton {
    private static volatile ThreadSafeSingleton instance;
    
    private ThreadSafeSingleton() { }
    
    public static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}
```

---

## 2. Factory Pattern

**Purpose:** Create objects without exposing creation logic.

**When to use:**
- When object creation is complex
- When the type of object needed depends on input
- When you want to decouple object creation from usage

```java
// Product interface
interface Animal {
    void speak();
}

// Concrete products
class Dog implements Animal {
    public void speak() { System.out.println("Woof!"); }
}

class Cat implements Animal {
    public void speak() { System.out.println("Meow!"); }
}

// Factory
class AnimalFactory {
    public static Animal createAnimal(String type) {
        return switch (type.toLowerCase()) {
            case "dog" -> new Dog();
            case "cat" -> new Cat();
            default -> throw new IllegalArgumentException("Unknown animal: " + type);
        };
    }
}

// Usage
Animal pet = AnimalFactory.createAnimal("dog");
pet.speak();  // Output: Woof!
```

---

## 3. Builder Pattern

**Purpose:** Construct complex objects step by step.

**When to use:**
- Object has many optional parameters
- Object construction requires multiple steps
- You want immutable objects

```java
public class Pizza {
    private final String size;
    private final boolean cheese;
    private final boolean pepperoni;
    private final boolean mushrooms;
    
    private Pizza(Builder builder) {
        this.size = builder.size;
        this.cheese = builder.cheese;
        this.pepperoni = builder.pepperoni;
        this.mushrooms = builder.mushrooms;
    }
    
    public static class Builder {
        private final String size;  // Required
        private boolean cheese = false;
        private boolean pepperoni = false;
        private boolean mushrooms = false;
        
        public Builder(String size) {
            this.size = size;
        }
        
        public Builder cheese(boolean val) {
            cheese = val;
            return this;
        }
        
        public Builder pepperoni(boolean val) {
            pepperoni = val;
            return this;
        }
        
        public Builder mushrooms(boolean val) {
            mushrooms = val;
            return this;
        }
        
        public Pizza build() {
            return new Pizza(this);
        }
    }
}

// Usage - fluent API
Pizza pizza = new Pizza.Builder("Large")
    .cheese(true)
    .pepperoni(true)
    .build();
```

---

## 4. Observer Pattern

**Purpose:** One-to-many dependency where changes notify all dependents.

**When to use:**
- Event handling systems
- GUI components
- Push notifications

```java
import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(String message);
}

// Subject (Observable)
class NewsAgency {
    private List<Observer> observers = new ArrayList<>();
    private String news;
    
    public void addObserver(Observer o) {
        observers.add(o);
    }
    
    public void removeObserver(Observer o) {
        observers.remove(o);
    }
    
    public void setNews(String news) {
        this.news = news;
        notifyObservers();
    }
    
    private void notifyObservers() {
        for (Observer o : observers) {
            o.update(news);
        }
    }
}

// Concrete Observer
class NewsChannel implements Observer {
    private String name;
    
    public NewsChannel(String name) {
        this.name = name;
    }
    
    @Override
    public void update(String news) {
        System.out.println(name + " received: " + news);
    }
}

// Usage
NewsAgency agency = new NewsAgency();
agency.addObserver(new NewsChannel("CNN"));
agency.addObserver(new NewsChannel("BBC"));
agency.setNews("Breaking News!");
// Output:
// CNN received: Breaking News!
// BBC received: Breaking News!
```

---

## 5. Strategy Pattern

**Purpose:** Define a family of algorithms and make them interchangeable.

**When to use:**
- Multiple algorithms for same task
- Need to switch algorithms at runtime
- Avoid complex conditionals

```java
// Strategy interface
interface PaymentStrategy {
    void pay(double amount);
}

// Concrete strategies
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " with credit card ****" + 
            cardNumber.substring(cardNumber.length() - 4));
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;
    
    public PayPalPayment(String email) {
        this.email = email;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " via PayPal (" + email + ")");
    }
}

// Context
class ShoppingCart {
    private PaymentStrategy paymentMethod;
    
    public void setPaymentMethod(PaymentStrategy method) {
        this.paymentMethod = method;
    }
    
    public void checkout(double amount) {
        paymentMethod.pay(amount);
    }
}

// Usage
ShoppingCart cart = new ShoppingCart();
cart.setPaymentMethod(new CreditCardPayment("1234567890123456"));
cart.checkout(100.00);

cart.setPaymentMethod(new PayPalPayment("user@email.com"));
cart.checkout(50.00);
```

---

## Pattern Selection Guide

| Pattern | Use When |
|---------|----------|
| **Singleton** | Need exactly one instance globally |
| **Factory** | Object creation logic should be hidden |
| **Builder** | Object has many parameters (4+) |
| **Observer** | Objects need to react to changes |
| **Strategy** | Need interchangeable algorithms |

---

## Further Reading
- *Head First Design Patterns* by Freeman & Robson
- *Design Patterns* by Gang of Four
- [Refactoring Guru](https://refactoring.guru/design-patterns)
