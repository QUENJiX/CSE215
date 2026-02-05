/**
 * InheritanceDemo.java - Demonstrates inheritance and method overriding
 * CSE215 - Programming Language II
 */
package p02_OOP;

public class InheritanceDemo {
    public static void main(String[] args) {
        // Creating objects of different types
        Animal genericAnimal = new Animal("Generic Animal", 5);
        Dog dog = new Dog("Buddy", 3, "Golden Retriever");
        Cat cat = new Cat("Whiskers", 2, true);

        System.out.println("=== Animal Sounds ===");
        genericAnimal.makeSound();
        dog.makeSound();
        cat.makeSound();

        System.out.println("\n=== Animal Info ===");
        genericAnimal.displayInfo();
        System.out.println();
        dog.displayInfo();
        System.out.println();
        cat.displayInfo();

        System.out.println("\n=== Dog-Specific Behavior ===");
        dog.fetch();
        dog.wagTail();

        System.out.println("\n=== Cat-Specific Behavior ===");
        cat.scratch();

        // Demonstrating polymorphism with inheritance
        System.out.println("\n=== Polymorphism Demo ===");
        Animal[] animals = { genericAnimal, dog, cat };
        for (Animal animal : animals) {
            System.out.print(animal.getName() + " says: ");
            animal.makeSound();
        }

        // Using 'instanceof' to check type
        System.out.println("\n=== Type Checking with instanceof ===");
        for (Animal animal : animals) {
            if (animal instanceof Dog) {
                System.out.println(animal.getName() + " is a Dog");
                ((Dog) animal).fetch(); // Downcasting
            } else if (animal instanceof Cat) {
                System.out.println(animal.getName() + " is a Cat");
            } else {
                System.out.println(animal.getName() + " is a generic Animal");
            }
        }
    }
}

/**
 * Base class (Parent/Superclass)
 */
class Animal {
    // Protected - accessible in subclasses
    protected String name;
    protected int age;

    // Constructor
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Methods that can be overridden
    public void makeSound() {
        System.out.println("Some generic animal sound...");
    }

    public void eat() {
        System.out.println(name + " is eating.");
    }

    public void sleep() {
        System.out.println(name + " is sleeping.");
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age + " years");
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

/**
 * Derived class (Child/Subclass) - Dog extends Animal
 */
class Dog extends Animal {
    private String breed;

    // Constructor - must call parent constructor
    public Dog(String name, int age, String breed) {
        super(name, age); // Call parent constructor
        this.breed = breed;
    }

    // Method overriding - providing specific implementation
    @Override
    public void makeSound() {
        System.out.println("Woof! Woof!");
    }

    // Overriding displayInfo to include breed
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call parent method
        System.out.println("Breed: " + breed);
        System.out.println("Type: Dog");
    }

    // Dog-specific methods
    public void fetch() {
        System.out.println(name + " is fetching the ball!");
    }

    public void wagTail() {
        System.out.println(name + " is wagging tail happily!");
    }

    public String getBreed() {
        return breed;
    }
}

/**
 * Another derived class - Cat extends Animal
 */
class Cat extends Animal {
    private boolean isIndoor;

    public Cat(String name, int age, boolean isIndoor) {
        super(name, age);
        this.isIndoor = isIndoor;
    }

    @Override
    public void makeSound() {
        System.out.println("Meow! Meow!");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Indoor cat: " + (isIndoor ? "Yes" : "No"));
        System.out.println("Type: Cat");
    }

    // Cat-specific methods
    public void scratch() {
        System.out.println(name + " is scratching the furniture!");
    }

    public void purr() {
        System.out.println(name + " is purring contentedly.");
    }

    public boolean isIndoor() {
        return isIndoor;
    }
}
