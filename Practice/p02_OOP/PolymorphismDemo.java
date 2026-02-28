/**
 * PolymorphismDemo.java — Compile-Time & Runtime Polymorphism
 * ==============================================================
 * CSE215 - Programming Language II
 *
 * 💡 INTUITION: Polymorphism = "many forms". One interface, multiple behaviors.
 *
 *    Two types:
 *    ┌──────────────────────────────────────────────────────┐
 *    │ COMPILE-TIME (Static)    │ RUNTIME (Dynamic)          │
 *    │ Method OVERLOADING       │ Method OVERRIDING           │
 *    │ Same name, diff params   │ Same signature, diff class  │
 *    │ Resolved at compile time │ Resolved at runtime (JVM)   │
 *    └──────────────────────────────────────────────────────┘
 *
 * 📌 RULE (Dynamic Dispatch): When you call a method on a parent reference
 *    that points to a child object, the CHILD's overridden method runs.
 *    Animal a = new Dog();  a.makeSound();  → calls Dog's makeSound()
 *
 * 🔗 SEE ALSO: p02_OOP/InheritanceDemo.java (inheritance basics)
 * 🔗 SEE ALSO: p03_Interfaces/InterfaceExample.java (interface polymorphism)
 */
package p02_OOP;

public class PolymorphismDemo {
    public static void main(String[] args) {
        // === Compile-time Polymorphism (Method Overloading) ===
        System.out.println("=== Method Overloading (Compile-time Polymorphism) ===");

        MathOperations math = new MathOperations();

        // Same method name, different parameters
        System.out.println("add(5, 3) = " + math.add(5, 3));
        System.out.println("add(5, 3, 2) = " + math.add(5, 3, 2));
        System.out.println("add(5.5, 3.3) = " + math.add(5.5, 3.3));
        System.out.println("add(\"Hello\", \"World\") = " + math.add("Hello", "World"));

        // === Runtime Polymorphism (Method Overriding) ===
        System.out.println("\n=== Method Overriding (Runtime Polymorphism) ===");

        // Parent reference can hold child objects
        Shape shape1 = new Circle(5);
        Shape shape2 = new Rectangle(4, 6);
        Shape shape3 = new Triangle(3, 4);

        // The actual method called depends on the object type (determined at runtime)
        System.out.println("\nShape areas:");
        System.out.println("Circle area: " + shape1.calculateArea());
        System.out.println("Rectangle area: " + shape2.calculateArea());
        System.out.println("Triangle area: " + shape3.calculateArea());

        // Polymorphic array
        System.out.println("\n=== Polymorphic Collection ===");
        Shape[] shapes = { shape1, shape2, shape3, new Circle(3), new Rectangle(2, 5) };

        double totalArea = 0;
        for (Shape shape : shapes) {
            shape.draw(); // Calls the appropriate draw() method
            System.out.println("  Area: " + shape.calculateArea());
            totalArea += shape.calculateArea();
        }
        System.out.println("\nTotal area of all shapes: " + String.format("%.2f", totalArea));

        // === Benefits of Polymorphism ===
        System.out.println("\n=== Practical Example: Shape Processor ===");
        ShapeProcessor processor = new ShapeProcessor();
        processor.processShape(new Circle(7));
        processor.processShape(new Rectangle(3, 4));
    }
}

/**
 * Demonstrates method overloading (compile-time polymorphism)
 */
class MathOperations {
    // Method with 2 int parameters
    public int add(int a, int b) {
        return a + b;
    }

    // Overloaded: 3 int parameters
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // Overloaded: 2 double parameters
    public double add(double a, double b) {
        return a + b;
    }

    // Overloaded: 2 String parameters
    public String add(String a, String b) {
        return a + " " + b;
    }
}

/**
 * Abstract base class for shapes
 */
abstract class Shape {
    protected String name;

    public Shape(String name) {
        this.name = name;
    }

    // Abstract method - must be implemented by subclasses
    public abstract double calculateArea();

    // Concrete method - can be overridden
    public void draw() {
        System.out.println("Drawing a " + name);
    }

    public String getName() {
        return name;
    }
}

/**
 * Circle - extends Shape
 */
class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Circle with radius " + radius);
    }
}

/**
 * Rectangle - extends Shape
 */
class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        super("Rectangle");
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle (" + width + " x " + height + ")");
    }
}

/**
 * Triangle - extends Shape
 */
class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        super("Triangle");
        this.base = base;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Triangle (base: " + base + ", height: " + height + ")");
    }
}

/**
 * Demonstrates how polymorphism enables flexible code
 */
class ShapeProcessor {
    // Works with ANY shape - polymorphism in action
    public void processShape(Shape shape) {
        System.out.println("\nProcessing: " + shape.getName());
        shape.draw();
        System.out.println("Calculated area: " + String.format("%.2f", shape.calculateArea()));
    }
}
