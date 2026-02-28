/**
 * EnumDemo.java — Enums: From Simple Constants to Powerful State Machines
 * ========================================================================
 * CSE215 - Programming Language II
 *
 * 💡 INTUITION: An enum is a special class that represents a FIXED SET of constants.
 *    Think of it as a dropdown menu — only these specific values are allowed.
 *
 *    Before enums, people used int constants (public static final int RED = 0;)
 *    which was error-prone and type-unsafe. Enums fix all of that.
 *
 * Topics covered:
 *   1. Basic enum declaration and usage
 *   2. Enum methods (name, ordinal, values, valueOf)
 *   3. Enum with fields, constructors, and methods
 *   4. Enum with abstract methods (each constant behaves differently)
 *   5. Enum implementing interfaces
 *   6. EnumSet and EnumMap
 *   7. Enum in switch statements
 *   8. Enum as singleton pattern
 *
 * 🔗 SEE ALSO: p02_OOP/StaticAndFinalDemo.java, p11_DesignPatterns/SingletonDemo.java
 */
package p02_OOP;

import java.util.EnumMap;
import java.util.EnumSet;

public class EnumDemo {

    // =========================================================================
    // SECTION 1: BASIC ENUM
    // =========================================================================

    /** Simplest possible enum — just a list of named constants */
    enum Direction {
        NORTH, SOUTH, EAST, WEST
    }

    // =========================================================================
    // SECTION 2: ENUM WITH FIELDS AND METHODS
    // =========================================================================

    /**
     * 💡 INTUITION: Enums can have fields, constructors, and methods — just like
     * classes!
     * The constructor runs for EACH constant when the enum is loaded.
     *
     * 📌 RULE: Enum constructors are ALWAYS private (even without writing
     * 'private').
     * You CAN'T create new enum instances with 'new'.
     */
    enum Planet {
        MERCURY(3.303e+23, 2.4397e6),
        VENUS(4.869e+24, 6.0518e6),
        EARTH(5.976e+24, 6.37814e6),
        MARS(6.421e+23, 3.3972e6),
        JUPITER(1.9e+27, 7.1492e7),
        SATURN(5.688e+26, 6.0268e7),
        URANUS(8.686e+25, 2.5559e7),
        NEPTUNE(1.024e+26, 2.4746e7);

        private final double mass; // in kg
        private final double radius; // in meters
        static final double G = 6.67300E-11; // gravitational constant

        // Constructor — called once per constant
        Planet(double mass, double radius) {
            this.mass = mass;
            this.radius = radius;
        }

        // Methods
        public double surfaceGravity() {
            return G * mass / (radius * radius);
        }

        public double surfaceWeight(double earthWeight) {
            return earthWeight * surfaceGravity() / EARTH.surfaceGravity();
        }
    }

    // =========================================================================
    // SECTION 3: ENUM WITH ABSTRACT METHODS
    // =========================================================================

    /**
     * 💡 INTUITION: Each enum constant can have its OWN implementation of a method.
     * This is like having a mini-strategy pattern built into the enum.
     */
    enum Operation {
        ADD("+") {
            @Override
            public double apply(double a, double b) {
                return a + b;
            }
        },
        SUBTRACT("-") {
            @Override
            public double apply(double a, double b) {
                return a - b;
            }
        },
        MULTIPLY("*") {
            @Override
            public double apply(double a, double b) {
                return a * b;
            }
        },
        DIVIDE("/") {
            @Override
            public double apply(double a, double b) {
                if (b == 0)
                    throw new ArithmeticException("Division by zero");
                return a / b;
            }
        };

        private final String symbol;

        Operation(String symbol) {
            this.symbol = symbol;
        }

        public abstract double apply(double a, double b);

        @Override
        public String toString() {
            return symbol;
        }
    }

    // =========================================================================
    // SECTION 4: ENUM IMPLEMENTING INTERFACE
    // =========================================================================

    interface Describable {
        String getDescription();
    }

    enum Season implements Describable {
        SPRING("Warm and blooming"),
        SUMMER("Hot and sunny"),
        AUTUMN("Cool and colorful"),
        WINTER("Cold and snowy");

        private final String description;

        Season(String description) {
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    // =========================================================================
    // SECTION 5: STATUS ENUM (Real-world example)
    // =========================================================================

    enum OrderStatus {
        PENDING("Order received, awaiting processing"),
        PROCESSING("Order is being prepared"),
        SHIPPED("Order has been shipped"),
        DELIVERED("Order delivered successfully"),
        CANCELLED("Order was cancelled");

        private final String message;

        OrderStatus(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        /** Check if order can transition to the given status */
        public boolean canTransitionTo(OrderStatus next) {
            return switch (this) {
                case PENDING -> next == PROCESSING || next == CANCELLED;
                case PROCESSING -> next == SHIPPED || next == CANCELLED;
                case SHIPPED -> next == DELIVERED;
                case DELIVERED, CANCELLED -> false; // Terminal states
            };
        }
    }

    // =========================================================================
    // MAIN — Run all demos
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   ENUM DEMO                                 ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // --- Section 1: Basic enum ---
        System.out.println("=== 1. BASIC ENUM ===");
        Direction dir = Direction.NORTH;
        System.out.println("Direction: " + dir);
        System.out.println("Name:      " + dir.name()); // "NORTH" as String
        System.out.println("Ordinal:   " + dir.ordinal()); // 0 (position in declaration)

        // Iterate over all values
        System.out.println("\nAll directions:");
        for (Direction d : Direction.values()) {
            System.out.println("  " + d.name() + " (ordinal=" + d.ordinal() + ")");
        }

        // valueOf — convert String to enum
        Direction parsed = Direction.valueOf("EAST");
        System.out.println("\nvalueOf(\"EAST\"): " + parsed);
        // Direction.valueOf("NORTHEAST"); // ⚠️ Would throw IllegalArgumentException!

        // --- Section 2: Enum in switch ---
        System.out.println("\n=== 2. ENUM IN SWITCH ===");
        printDirectionAdvice(Direction.NORTH);
        printDirectionAdvice(Direction.EAST);

        // --- Section 3: Enum with fields ---
        System.out.println("\n=== 3. ENUM WITH FIELDS (Planets) ===");
        double earthWeight = 75.0; // kg
        System.out.printf("Your weight on different planets (Earth weight: %.1f kg):%n", earthWeight);
        for (Planet p : Planet.values()) {
            System.out.printf("  %-8s  %.2f kg%n", p, p.surfaceWeight(earthWeight));
        }

        // --- Section 4: Enum with abstract methods ---
        System.out.println("\n=== 4. ENUM WITH ABSTRACT METHODS (Operations) ===");
        double a = 10, b = 3;
        for (Operation op : Operation.values()) {
            System.out.printf("  %.0f %s %.0f = %.2f%n", a, op, b, op.apply(a, b));
        }

        // --- Section 5: Enum implementing interface ---
        System.out.println("\n=== 5. ENUM IMPLEMENTING INTERFACE (Seasons) ===");
        for (Season s : Season.values()) {
            System.out.printf("  %-8s → %s%n", s, s.getDescription());
        }

        // --- Section 6: Real-world status transitions ---
        System.out.println("\n=== 6. REAL-WORLD: ORDER STATUS TRANSITIONS ===");
        OrderStatus current = OrderStatus.PENDING;
        System.out.println("Current status: " + current + " — " + current.getMessage());
        System.out.println("Can transition to PROCESSING? " + current.canTransitionTo(OrderStatus.PROCESSING));
        System.out.println("Can transition to DELIVERED?  " + current.canTransitionTo(OrderStatus.DELIVERED));

        // --- Section 7: EnumSet and EnumMap ---
        System.out.println("\n=== 7. EnumSet AND EnumMap ===");

        /**
         * 💡 INTUITION:
         * EnumSet — a Set optimized for enums (uses bit vector internally — blazing
         * fast)
         * EnumMap — a Map with enum keys (uses array internally — very efficient)
         *
         * Always prefer these over HashSet/HashMap when dealing with enums.
         */

        // EnumSet — subset of enum values
        EnumSet<Direction> horizontal = EnumSet.of(Direction.EAST, Direction.WEST);
        EnumSet<Direction> vertical = EnumSet.of(Direction.NORTH, Direction.SOUTH);
        EnumSet<Direction> all = EnumSet.allOf(Direction.class);
        System.out.println("Horizontal: " + horizontal);
        System.out.println("Vertical:   " + vertical);
        System.out.println("All:        " + all);

        // EnumMap — map from enum to values
        EnumMap<Season, String> activities = new EnumMap<>(Season.class);
        activities.put(Season.SPRING, "Hiking");
        activities.put(Season.SUMMER, "Swimming");
        activities.put(Season.AUTUMN, "Apple picking");
        activities.put(Season.WINTER, "Skiing");

        activities.forEach((season, activity) -> System.out.println("  " + season + " → " + activity));

        // --- Section 8: Enum comparison ---
        System.out.println("\n=== 8. ENUM COMPARISON ===");

        /**
         * 📌 RULE: Use == to compare enums, NOT .equals()
         * Since enum constants are singletons, == is both correct and faster.
         */
        Direction d1 = Direction.NORTH;
        Direction d2 = Direction.NORTH;
        System.out.println("d1 == d2:       " + (d1 == d2)); // true ✅ (preferred)
        System.out.println("d1.equals(d2):  " + d1.equals(d2)); // true (also works, but unnecessary)

        System.out.println("\n✅ All demos completed successfully!");
    }

    /** Demonstrates enum in switch statement */
    public static void printDirectionAdvice(Direction dir) {
        String advice = switch (dir) {
            case NORTH -> "Head towards the mountains!";
            case SOUTH -> "Head towards the coast!";
            case EAST -> "Follow the sunrise!";
            case WEST -> "Chase the sunset!";
        };
        System.out.println("  " + dir + ": " + advice);
    }
}
