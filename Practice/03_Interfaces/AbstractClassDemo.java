/**
 * AbstractClassDemo.java - Demonstrates abstract classes vs interfaces
 * CSE215 - Programming Language II
 */

public class AbstractClassDemo {
    public static void main(String[] args) {
        System.out.println("=== Abstract Class Demo ===\n");
        
        // Cannot instantiate abstract class directly:
        // Vehicle v = new Vehicle(); // ERROR!
        
        // But we can use abstract class as reference type
        Vehicle car = new Car("Tesla Model 3", 4);
        Vehicle motorcycle = new Motorcycle("Harley Davidson", true);
        Vehicle bicycle = new Bicycle("Trek Mountain", 21);
        
        // Polymorphic behavior
        Vehicle[] vehicles = {car, motorcycle, bicycle};
        
        System.out.println("--- Starting all vehicles ---");
        for (Vehicle v : vehicles) {
            v.start();
        }
        
        System.out.println("\n--- Vehicle descriptions ---");
        for (Vehicle v : vehicles) {
            v.describe();
            System.out.println("Fuel type: " + v.getFuelType());
            System.out.println();
        }
        
        System.out.println("--- Stopping all vehicles ---");
        for (Vehicle v : vehicles) {
            v.stop();  // Uses concrete method from abstract class
        }
        
        // Demonstrating difference: Abstract class can have state
        System.out.println("\n=== Abstract Class with State ===");
        System.out.println("Total vehicles created: " + Vehicle.getVehicleCount());
    }
}

/**
 * Abstract class - can have:
 * - Abstract methods (must be implemented by subclasses)
 * - Concrete methods (can be inherited or overridden)
 * - Instance variables (state)
 * - Constructors
 * - Static members
 */
abstract class Vehicle {
    // Instance variables (abstract classes can have state)
    protected String name;
    protected boolean isRunning;
    
    // Static variable
    private static int vehicleCount = 0;
    
    // Constructor (abstract classes can have constructors)
    public Vehicle(String name) {
        this.name = name;
        this.isRunning = false;
        vehicleCount++;
    }
    
    // Abstract methods - MUST be implemented by subclasses
    public abstract void start();
    public abstract String getFuelType();
    
    // Concrete method - can be used or overridden by subclasses
    public void stop() {
        isRunning = false;
        System.out.println(name + " has stopped.");
    }
    
    // Concrete method with common behavior
    public void describe() {
        System.out.println("Vehicle: " + name);
        System.out.println("Status: " + (isRunning ? "Running" : "Stopped"));
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public boolean isRunning() {
        return isRunning;
    }
    
    public static int getVehicleCount() {
        return vehicleCount;
    }
}

/**
 * Concrete class - must implement all abstract methods
 */
class Car extends Vehicle {
    private int numberOfDoors;
    
    public Car(String name, int numberOfDoors) {
        super(name);  // Call abstract class constructor
        this.numberOfDoors = numberOfDoors;
    }
    
    // Must implement abstract method
    @Override
    public void start() {
        isRunning = true;
        System.out.println(name + " engine started. Vroom!");
    }
    
    @Override
    public String getFuelType() {
        return "Electric/Gasoline";
    }
    
    // Override concrete method from parent
    @Override
    public void describe() {
        super.describe();  // Call parent's describe
        System.out.println("Type: Car with " + numberOfDoors + " doors");
    }
}

/**
 * Another concrete implementation
 */
class Motorcycle extends Vehicle {
    private boolean hasSidecar;
    
    public Motorcycle(String name, boolean hasSidecar) {
        super(name);
        this.hasSidecar = hasSidecar;
    }
    
    @Override
    public void start() {
        isRunning = true;
        System.out.println(name + " engine roars to life!");
    }
    
    @Override
    public String getFuelType() {
        return "Gasoline";
    }
    
    @Override
    public void describe() {
        super.describe();
        System.out.println("Type: Motorcycle" + (hasSidecar ? " with sidecar" : ""));
    }
}

/**
 * Third concrete implementation
 */
class Bicycle extends Vehicle {
    private int gears;
    
    public Bicycle(String name, int gears) {
        super(name);
        this.gears = gears;
    }
    
    @Override
    public void start() {
        isRunning = true;
        System.out.println(name + " pedaling begins!");
    }
    
    @Override
    public String getFuelType() {
        return "Human Power";
    }
    
    @Override
    public void describe() {
        super.describe();
        System.out.println("Type: Bicycle with " + gears + " gears");
    }
    
    @Override
    public void stop() {
        super.stop();
        System.out.println("  (Applying brakes)");
    }
}

/*
 * ============================================
 * ABSTRACT CLASS vs INTERFACE - Key Differences
 * ============================================
 * 
 * ABSTRACT CLASS:
 * - Can have instance variables (state)
 * - Can have constructors
 * - Can have concrete methods with implementation
 * - Single inheritance only (extends one class)
 * - Use when classes share common state/behavior
 * 
 * INTERFACE:
 * - Cannot have instance variables (only constants)
 * - Cannot have constructors
 * - Methods are abstract by default (Java 8+ can have default methods)
 * - Multiple inheritance (implements multiple interfaces)
 * - Use to define a contract/capability
 * 
 * WHEN TO USE WHICH:
 * - Use ABSTRACT CLASS when:
 *   - Subclasses share common code
 *   - You need to define non-static, non-final fields
 *   - You want to provide a common base implementation
 * 
 * - Use INTERFACE when:
 *   - Unrelated classes need to implement same methods
 *   - You want to specify behavior without implementation
 *   - You need multiple inheritance of type
 */
