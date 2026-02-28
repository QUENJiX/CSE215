/**
 * BuilderPatternDemo.java — Builder Pattern: Step-by-Step Object Construction
 * ==============================================================================
 * CSE215 - Programming Language II
 *
 * 💡 INTUITION: The Builder pattern is like ordering a custom sandwich.
 *    Instead of one massive constructor with 10 parameters:
 *      new Sandwich("wheat", "turkey", true, false, true, "mayo", null, "large", false, true)
 *
 *    You build it step by step:
 *      new SandwichBuilder().bread("wheat").meat("turkey").size("large").toasted(true).build()
 *
 *    Benefits:
 *      - Readable: each parameter is named
 *      - Flexible: set only what you need, skip optional parameters
 *      - Immutable: the built object can be made immutable
 *      - Valid: validate in build() before creating the object
 *
 * Topics covered:
 *   1. The problem with many constructor parameters
 *   2. Classic Builder pattern
 *   3. Fluent Builder (method chaining)
 *   4. Builder with validation
 *   5. Java's built-in builders (StringBuilder, Stream.Builder)
 *
 * 🔗 SEE ALSO: p02_OOP/ClassExample.java (constructors)
 * 🔗 SEE ALSO: p11_DesignPatterns/SingletonDemo.java
 */
package p11_DesignPatterns;

import java.util.*;

public class BuilderPatternDemo {

    // =====================================================================
    // THE PROBLEM: Telescoping Constructor Anti-Pattern
    // =====================================================================

    /**
     * ❌ BAD PRACTICE: "Telescoping constructors" — multiple constructors
     * with increasing parameters. Hard to read, easy to mix up.
     *
     * new User("Alice", "alice@mail.com", 25, null, null, true, false, "US")
     * // Which null is which? What does true mean? What does false mean?
     */
    static class UserBad {
        String name, email, phone, address;
        int age;
        boolean newsletter, premium;
        String country;

        // Constructor with ALL parameters — confusing!
        UserBad(String name, String email, int age, String phone,
                String address, boolean newsletter, boolean premium, String country) {
            this.name = name;
            this.email = email;
            this.age = age;
            this.phone = phone;
            this.address = address;
            this.newsletter = newsletter;
            this.premium = premium;
            this.country = country;
        }
    }

    // =====================================================================
    // THE SOLUTION: Builder Pattern
    // =====================================================================

    /**
     * ✅ BEST PRACTICE: Use a Builder for objects with many parameters,
     * especially when many are optional.
     */
    static class User {
        // Fields are final (immutable after construction)
        private final String name;
        private final String email;
        private final int age;
        private final String phone; // Optional
        private final String address; // Optional
        private final boolean newsletter; // Optional (default: false)
        private final boolean premium; // Optional (default: false)
        private final String country; // Optional

        // Private constructor — only Builder can create User
        private User(Builder builder) {
            this.name = builder.name;
            this.email = builder.email;
            this.age = builder.age;
            this.phone = builder.phone;
            this.address = builder.address;
            this.newsletter = builder.newsletter;
            this.premium = builder.premium;
            this.country = builder.country;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("User{name='").append(name).append("', email='").append(email)
                    .append("', age=").append(age);
            if (phone != null)
                sb.append(", phone='").append(phone).append("'");
            if (address != null)
                sb.append(", address='").append(address).append("'");
            if (newsletter)
                sb.append(", newsletter=true");
            if (premium)
                sb.append(", PREMIUM");
            if (country != null)
                sb.append(", country='").append(country).append("'");
            sb.append('}');
            return sb.toString();
        }

        // ── The Builder (static inner class) ──
        static class Builder {
            // Required parameters
            private final String name;
            private final String email;

            // Optional parameters with defaults
            private int age = 0;
            private String phone = null;
            private String address = null;
            private boolean newsletter = false;
            private boolean premium = false;
            private String country = null;

            /**
             * 📌 Required parameters go in the Builder constructor.
             * Optional parameters have setter methods that return 'this'.
             */
            Builder(String name, String email) {
                this.name = name;
                this.email = email;
            }

            // Each setter returns 'this' for fluent chaining
            Builder age(int age) {
                this.age = age;
                return this;
            }

            Builder phone(String phone) {
                this.phone = phone;
                return this;
            }

            Builder address(String address) {
                this.address = address;
                return this;
            }

            Builder newsletter(boolean newsletter) {
                this.newsletter = newsletter;
                return this;
            }

            Builder premium(boolean premium) {
                this.premium = premium;
                return this;
            }

            Builder country(String country) {
                this.country = country;
                return this;
            }

            /**
             * 📌 build() is where you do validation before creating the object.
             */
            User build() {
                // Validation
                if (name == null || name.isBlank()) {
                    throw new IllegalStateException("Name is required");
                }
                if (email == null || !email.contains("@")) {
                    throw new IllegalStateException("Valid email is required");
                }
                if (age < 0 || age > 150) {
                    throw new IllegalStateException("Age must be 0-150");
                }
                return new User(this);
            }
        }
    }

    // =====================================================================
    // EXAMPLE 2: HTTP Request Builder (real-world inspired)
    // =====================================================================

    static class HttpRequest {
        private final String method;
        private final String url;
        private final Map<String, String> headers;
        private final String body;
        private final int timeout;

        private HttpRequest(Builder builder) {
            this.method = builder.method;
            this.url = builder.url;
            this.headers = Collections.unmodifiableMap(builder.headers);
            this.body = builder.body;
            this.timeout = builder.timeout;
        }

        void send() {
            System.out.println("    " + method + " " + url);
            headers.forEach((k, v) -> System.out.println("      " + k + ": " + v));
            if (body != null)
                System.out.println("      Body: " + body);
            System.out.println("      Timeout: " + timeout + "ms");
        }

        static class Builder {
            private final String url;
            private String method = "GET";
            private Map<String, String> headers = new LinkedHashMap<>();
            private String body = null;
            private int timeout = 5000;

            Builder(String url) {
                this.url = url;
            }

            Builder method(String method) {
                this.method = method;
                return this;
            }

            Builder header(String key, String value) {
                headers.put(key, value);
                return this;
            }

            Builder body(String body) {
                this.body = body;
                return this;
            }

            Builder timeout(int ms) {
                this.timeout = ms;
                return this;
            }

            HttpRequest build() {
                if (url == null || url.isBlank())
                    throw new IllegalStateException("URL required");
                return new HttpRequest(this);
            }
        }
    }

    // =====================================================================
    // MAIN
    // =====================================================================
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   BUILDER PATTERN DEMO                       ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // =================================================================
        // 1. The Problem
        // =================================================================
        System.out.println("=== 1. THE PROBLEM (Telescoping Constructor) ===");
        // Hard to read — what do the nulls and booleans mean?
        UserBad bad = new UserBad("Alice", "alice@mail.com", 25, null, null, true, false, "US");
        System.out.println(
                "  Hard to read: new UserBad(\"Alice\", \"alice@mail.com\", 25, null, null, true, false, \"US\")");
        System.out.println("  Which null is which? What does true mean?\n");

        // =================================================================
        // 2. The Solution: Builder
        // =================================================================
        System.out.println("=== 2. BUILDER PATTERN ===");

        // Minimal user (only required fields)
        User minimal = new User.Builder("Alice", "alice@mail.com")
                .build();
        System.out.println("  Minimal: " + minimal);

        // User with some optional fields
        User standard = new User.Builder("Bob", "bob@mail.com")
                .age(30)
                .country("US")
                .newsletter(true)
                .build();
        System.out.println("  Standard: " + standard);

        // Fully specified user
        User full = new User.Builder("Charlie", "charlie@mail.com")
                .age(28)
                .phone("+1-555-0123")
                .address("123 Main St, Boston")
                .newsletter(true)
                .premium(true)
                .country("US")
                .build();
        System.out.println("  Full: " + full);

        /**
         * 💡 Compare readability:
         *
         * ❌ new UserBad("Alice", "alice@mail.com", 25, null, null, true, false, "US")
         *
         * ✅ new User.Builder("Alice", "alice@mail.com")
         * .age(25)
         * .newsletter(true)
         * .country("US")
         * .build()
         *
         * Every parameter is self-documenting!
         */

        // Validation demo
        System.out.println("\n--- Validation ---");
        try {
            new User.Builder("", "bad-email").build();
        } catch (IllegalStateException e) {
            System.out.println("  Caught: " + e.getMessage());
        }

        // =================================================================
        // 3. Real-world: HTTP Request Builder
        // =================================================================
        System.out.println("\n=== 3. HTTP REQUEST BUILDER ===");

        // Simple GET request
        System.out.println("--- Simple GET ---");
        new HttpRequest.Builder("https://api.example.com/users")
                .header("Accept", "application/json")
                .build()
                .send();

        // Complex POST request
        System.out.println("\n--- Complex POST ---");
        new HttpRequest.Builder("https://api.example.com/users")
                .method("POST")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer token123")
                .body("{\"name\": \"Alice\", \"email\": \"alice@mail.com\"}")
                .timeout(10000)
                .build()
                .send();

        // =================================================================
        // 4. Java's Built-in Builders
        // =================================================================
        System.out.println("\n=== 4. JAVA'S BUILT-IN BUILDERS ===");

        // StringBuilder — the original Java builder
        String result = new StringBuilder()
                .append("Hello")
                .append(" ")
                .append("World")
                .append("!")
                .toString();
        System.out.println("  StringBuilder: " + result);

        // StringJoiner — builder for joining strings
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        sj.add("one").add("two").add("three");
        System.out.println("  StringJoiner: " + sj);

        System.out.println("\n✅ All demos completed successfully!");
    }
}

/*
 * ╔═══════════════════════════════════════════════════════════════════╗
 * ║ BUILDER PATTERN SUMMARY ║
 * ╠═══════════════════════════════════════════════════════════════════╣
 * ║ Structure: ║
 * ║ Product class → the object being built (often immutable) ║
 * ║ Builder class → static inner class with setter methods ║
 * ║ build() → validates and creates the Product ║
 * ║ ║
 * ║ When to use: ║
 * ║ ✅ Many constructor parameters (especially optional ones) ║
 * ║ ✅ Object needs to be immutable after creation ║
 * ║ ✅ Complex construction with validation ║
 * ║ ║
 * ║ When NOT to use: ║
 * ║ ❌ Few parameters (just use constructor) ║
 * ║ ❌ All parameters required (constructor is fine) ║
 * ║ ║
 * ║ 📌 Java's StringBuilder is the most famous builder. ║
 * ║ 📌 Many libraries use builders: OkHttp, Retrofit, Lombok @Builder║
 * ╚═══════════════════════════════════════════════════════════════════╝
 */
