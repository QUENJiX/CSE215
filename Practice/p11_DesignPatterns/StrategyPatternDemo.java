/**
 * StrategyPatternDemo.java — Strategy Pattern: Interchangeable Algorithms
 * =========================================================================
 * CSE215 - Programming Language II
 *
 * 💡 INTUITION: The Strategy pattern lets you swap algorithms at runtime
 *    without changing the code that uses them.
 *
 *    Real-world analogy: A GPS app with routing strategies.
 *      - Same destination, different strategies:
 *        "Fastest route" / "Shortest route" / "Avoid tolls"
 *      - The navigation logic doesn't change — only the routing algorithm.
 *
 *    Before Strategy: giant if-else/switch deciding behavior
 *    After Strategy:  plug in any algorithm that fits the interface
 *
 * Topics covered:
 *   1. Strategy interface + context class
 *   2. Multiple concrete strategies
 *   3. Strategy with lambda expressions
 *   4. Real-world example: Payment processing, Sorting strategies
 *
 * 🔗 SEE ALSO: p03_Interfaces/InterfaceExample.java
 * 🔗 SEE ALSO: p08_LambdasAndStreams/LambdaExpressionsDemo.java
 */
package p11_DesignPatterns;

import java.util.*;
import java.util.stream.*;

public class StrategyPatternDemo {

    // =====================================================================
    // 1. CLASSIC STRATEGY: Payment Processing
    // =====================================================================

    // Strategy interface
    interface PaymentStrategy {
        void pay(double amount);

        String getName();
    }

    // Concrete strategies
    static class CreditCardPayment implements PaymentStrategy {
        private String cardNumber;

        CreditCardPayment(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        @Override
        public void pay(double amount) {
            String masked = "****-" + cardNumber.substring(cardNumber.length() - 4);
            System.out.printf("    💳 Paid $%.2f with Credit Card %s%n", amount, masked);
        }

        @Override
        public String getName() {
            return "Credit Card";
        }
    }

    static class PayPalPayment implements PaymentStrategy {
        private String email;

        PayPalPayment(String email) {
            this.email = email;
        }

        @Override
        public void pay(double amount) {
            System.out.printf("    🅿️  Paid $%.2f via PayPal (%s)%n", amount, email);
        }

        @Override
        public String getName() {
            return "PayPal";
        }
    }

    static class CryptoPayment implements PaymentStrategy {
        private String wallet;

        CryptoPayment(String wallet) {
            this.wallet = wallet;
        }

        @Override
        public void pay(double amount) {
            System.out.printf("    ₿  Paid $%.2f with Crypto (wallet: %s...)%n",
                    amount, wallet.substring(0, 8));
        }

        @Override
        public String getName() {
            return "Crypto";
        }
    }

    // Context class — uses a strategy
    static class ShoppingCart {
        private List<String> items = new ArrayList<>();
        private List<Double> prices = new ArrayList<>();
        private PaymentStrategy paymentStrategy;

        void addItem(String item, double price) {
            items.add(item);
            prices.add(price);
        }

        /**
         * 📌 The strategy can be changed at any time!
         * This is the key benefit — swap behavior without changing the cart.
         */
        void setPaymentStrategy(PaymentStrategy strategy) {
            this.paymentStrategy = strategy;
        }

        void checkout() {
            double total = prices.stream().mapToDouble(Double::doubleValue).sum();
            System.out.println("  Items: " + items);
            System.out.printf("  Total: $%.2f%n", total);

            if (paymentStrategy == null) {
                System.out.println("  ❌ No payment method selected!");
                return;
            }
            System.out.println("  Payment via: " + paymentStrategy.getName());
            paymentStrategy.pay(total);
        }
    }

    // =====================================================================
    // 2. STRATEGY WITH LAMBDAS: Text Formatting
    // =====================================================================

    /**
     * 💡 Since Strategy interfaces are often functional interfaces
     * (one method), you can use LAMBDAS instead of concrete classes.
     * This makes the pattern much more concise in modern Java.
     */
    interface TextFormatter {
        String format(String text);
    }

    static class TextEditor {
        private TextFormatter formatter;

        void setFormatter(TextFormatter formatter) {
            this.formatter = formatter;
        }

        String publish(String text) {
            if (formatter == null)
                return text;
            return formatter.format(text);
        }
    }

    // =====================================================================
    // 3. STRATEGY FOR VALIDATION
    // =====================================================================

    interface ValidationStrategy {
        boolean validate(String input);
    }

    static class FormField {
        private String name;
        private String value;
        private ValidationStrategy validator;

        FormField(String name, String value, ValidationStrategy validator) {
            this.name = name;
            this.value = value;
            this.validator = validator;
        }

        boolean isValid() {
            boolean valid = validator.validate(value);
            System.out.printf("    %-15s = %-20s → %s%n", name, value, valid ? "✅" : "❌");
            return valid;
        }
    }

    // =====================================================================
    // MAIN
    // =====================================================================
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   STRATEGY PATTERN DEMO                      ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // =================================================================
        // 1. Payment Strategy
        // =================================================================
        System.out.println("=== 1. PAYMENT STRATEGIES ===");

        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Java Book", 45.99);
        cart.addItem("USB Cable", 12.50);
        cart.addItem("Coffee", 8.99);

        // Pay with credit card
        System.out.println("--- Checkout #1: Credit Card ---");
        cart.setPaymentStrategy(new CreditCardPayment("4111222233334444"));
        cart.checkout();

        // Same cart, different payment method — swap strategy!
        System.out.println("\n--- Checkout #2: PayPal ---");
        cart.setPaymentStrategy(new PayPalPayment("alice@email.com"));
        cart.checkout();

        System.out.println("\n--- Checkout #3: Crypto ---");
        cart.setPaymentStrategy(new CryptoPayment("0xABCDEF1234567890"));
        cart.checkout();

        // =================================================================
        // 2. Lambda Strategies (Text Formatting)
        // =================================================================
        System.out.println("\n=== 2. LAMBDA STRATEGIES (Text Formatting) ===");

        TextEditor editor = new TextEditor();
        String text = "hello world from java";

        // Strategy 1: Uppercase
        editor.setFormatter(String::toUpperCase);
        System.out.println("  Upper:     " + editor.publish(text));

        // Strategy 2: Title Case
        editor.setFormatter(t -> Arrays.stream(t.split(" "))
                .map(w -> Character.toUpperCase(w.charAt(0)) + w.substring(1))
                .collect(Collectors.joining(" ")));
        System.out.println("  Title:     " + editor.publish(text));

        // Strategy 3: Slug format
        editor.setFormatter(t -> t.toLowerCase().replaceAll("\\s+", "-"));
        System.out.println("  Slug:      " + editor.publish(text));

        // Strategy 4: Reverse
        editor.setFormatter(t -> new StringBuilder(t).reverse().toString());
        System.out.println("  Reversed:  " + editor.publish(text));

        /**
         * 💡 Notice how we defined 4 different behaviors inline with lambdas,
         * without creating 4 separate classes. This is the power of combining
         * Strategy pattern with functional programming.
         */

        // =================================================================
        // 3. Validation Strategies
        // =================================================================
        System.out.println("\n=== 3. VALIDATION STRATEGIES ===");

        // Define validators as lambdas
        ValidationStrategy notEmpty = s -> s != null && !s.trim().isEmpty();
        ValidationStrategy isEmail = s -> s != null && s.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$");
        ValidationStrategy isPhone = s -> s != null && s.matches("^\\+?\\d{10,15}$");
        ValidationStrategy minLength8 = s -> s != null && s.length() >= 8;

        // Validate form fields
        List<FormField> fields = List.of(
                new FormField("Username", "alice123", notEmpty),
                new FormField("Email", "alice@email.com", isEmail),
                new FormField("Phone", "+1234567890", isPhone),
                new FormField("Password", "pass", minLength8),
                new FormField("Bad Email", "not-an-email", isEmail),
                new FormField("Empty Name", "   ", notEmpty));

        long validCount = fields.stream().filter(FormField::isValid).count();
        System.out.printf("%n  Valid fields: %d/%d%n", validCount, fields.size());

        // =================================================================
        // 4. Strategy vs If-Else (Code Comparison)
        // =================================================================
        System.out.println("\n=== 4. WHY STRATEGY? (vs if-else) ===");

        /**
         * ❌ WITHOUT Strategy pattern:
         *
         * void processPayment(String type, double amount) {
         * if (type.equals("credit")) {
         * // credit card logic...
         * } else if (type.equals("paypal")) {
         * // paypal logic...
         * } else if (type.equals("crypto")) {
         * // crypto logic...
         * }
         * // Adding new type = modifying this method (violates Open/Closed)
         * }
         *
         * ✅ WITH Strategy pattern:
         * - Each payment type is its own class implementing PaymentStrategy
         * - Adding new type = adding new class (no existing code changes)
         * - Easy to test each strategy independently
         * - Can swap strategies at runtime
         */

        System.out.println("  See source code for before/after comparison.");
        System.out.println("  Key principle: Open for extension, closed for modification.");

        System.out.println("\n✅ All demos completed successfully!");
    }
}

/*
 * ╔═══════════════════════════════════════════════════════════════════╗
 * ║ STRATEGY PATTERN SUMMARY ║
 * ╠═══════════════════════════════════════════════════════════════════╣
 * ║ Components: ║
 * ║ Strategy interface → defines the algorithm contract ║
 * ║ Concrete strategies → different implementations ║
 * ║ Context class → uses a strategy, swappable at runtime ║
 * ║ ║
 * ║ Modern Java approach: Use lambdas as strategies when the ║
 * ║ interface has a single method (functional interface). ║
 * ║ ║
 * ║ 📌 Use when: you have multiple algorithms for the same task ║
 * ║ 📌 Benefit: eliminates complex conditionals ║
 * ║ 📌 Related: Comparator is a built-in example of Strategy! ║
 * ╚═══════════════════════════════════════════════════════════════════╝
 */
