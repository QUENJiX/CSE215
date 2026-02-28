/**
 * ObserverPatternDemo.java — Observer Pattern: Event-Driven Communication
 * =========================================================================
 * CSE215 - Programming Language II
 *
 * 💡 INTUITION: The Observer pattern is like a YouTube subscription.
 *    - A channel (Subject) has subscribers (Observers)
 *    - When the channel posts a new video, ALL subscribers get notified
 *    - Subscribers can unsubscribe at any time
 *
 *    This is the foundation of event-driven programming (GUI events,
 *    message queues, reactive systems).
 *
 * Topics covered:
 *   1. Observer interface and Subject class
 *   2. Multiple observers subscribing/unsubscribing
 *   3. Practical example: Stock price tracker
 *   4. Practical example: Event system
 *
 * 🔗 SEE ALSO: p03_Interfaces/InterfaceExample.java
 * 🔗 SEE ALSO: p11_DesignPatterns/StrategyPatternDemo.java
 */
package p11_DesignPatterns;

import java.util.*;

public class ObserverPatternDemo {

    // =====================================================================
    // Observer interface
    // =====================================================================

    /**
     * 📌 All observers must implement this interface.
     * update() is called when the subject changes.
     */
    interface Observer {
        void update(String event, Object data);
    }

    // =====================================================================
    // Subject (Observable)
    // =====================================================================

    /**
     * 📌 The Subject maintains a list of observers and notifies them of changes.
     */
    static class EventEmitter {
        private final Map<String, List<Observer>> listeners = new HashMap<>();

        void subscribe(String event, Observer observer) {
            listeners.computeIfAbsent(event, k -> new ArrayList<>()).add(observer);
        }

        void unsubscribe(String event, Observer observer) {
            List<Observer> list = listeners.get(event);
            if (list != null) {
                list.remove(observer);
            }
        }

        void emit(String event, Object data) {
            List<Observer> list = listeners.get(event);
            if (list != null) {
                for (Observer observer : list) {
                    observer.update(event, data);
                }
            }
        }
    }

    // =====================================================================
    // EXAMPLE 1: Stock Price Tracker
    // =====================================================================

    static class StockMarket extends EventEmitter {
        private final Map<String, Double> prices = new HashMap<>();

        void updatePrice(String symbol, double newPrice) {
            double oldPrice = prices.getOrDefault(symbol, 0.0);
            prices.put(symbol, newPrice);

            String direction = newPrice > oldPrice ? "📈" : newPrice < oldPrice ? "📉" : "➡️";
            emit("price-change", String.format("%s %s $%.2f → $%.2f", direction, symbol, oldPrice, newPrice));
        }
    }

    static class StockDisplay implements Observer {
        private String name;

        StockDisplay(String name) {
            this.name = name;
        }

        @Override
        public void update(String event, Object data) {
            System.out.println("    [" + name + "] " + data);
        }
    }

    // =====================================================================
    // EXAMPLE 2: Simple Event System (like GUI events)
    // =====================================================================

    static class Button extends EventEmitter {
        private String label;

        Button(String label) {
            this.label = label;
        }

        void click() {
            System.out.println("  Button '" + label + "' clicked!");
            emit("click", label);
        }

        void hover() {
            emit("hover", label);
        }
    }

    // =====================================================================
    // MAIN
    // =====================================================================
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   OBSERVER PATTERN DEMO                      ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // =================================================================
        // Example 1: Stock Market
        // =================================================================
        System.out.println("=== 1. STOCK PRICE TRACKER ===");

        StockMarket market = new StockMarket();

        StockDisplay phone = new StockDisplay("Phone App");
        StockDisplay desktop = new StockDisplay("Desktop");
        StockDisplay tv = new StockDisplay("TV Ticker");

        // Subscribe all three to price changes
        market.subscribe("price-change", phone);
        market.subscribe("price-change", desktop);
        market.subscribe("price-change", tv);

        System.out.println("All 3 displays subscribed. Updating prices...");
        market.updatePrice("AAPL", 150.00);
        market.updatePrice("GOOGL", 2800.00);

        // TV unsubscribes
        System.out.println("\n  TV unsubscribes...");
        market.unsubscribe("price-change", tv);

        market.updatePrice("AAPL", 155.50);
        System.out.println("  (Notice: only 2 displays updated)");

        // =================================================================
        // Example 2: Button Event System
        // =================================================================
        System.out.println("\n=== 2. BUTTON EVENT SYSTEM ===");

        Button saveBtn = new Button("Save");

        // Subscribe with lambda observers
        saveBtn.subscribe("click", (event, data) -> System.out.println("    💾 Saving document..."));
        saveBtn.subscribe("click", (event, data) -> System.out.println("    📝 Logging: " + data + " button " + event));
        saveBtn.subscribe("hover", (event, data) -> System.out.println("    🖱️ Tooltip: Click to save"));

        saveBtn.click();
        System.out.println();
        saveBtn.hover();

        // =================================================================
        // Example 3: Using Observer with lambda directly
        // =================================================================
        System.out.println("\n=== 3. LAMBDA OBSERVERS ===");

        EventEmitter logger = new EventEmitter();

        // Different observers for different severity levels
        logger.subscribe("info", (event, data) -> System.out.println("    ℹ️  INFO: " + data));
        logger.subscribe("warn", (event, data) -> System.out.println("    ⚠️  WARN: " + data));
        logger.subscribe("error", (event, data) -> System.out.println("    🔴 ERROR: " + data));

        // All-events observer
        Observer auditLog = (event, data) -> System.out.println("    📋 AUDIT [" + event.toUpperCase() + "]: " + data);
        logger.subscribe("info", auditLog);
        logger.subscribe("warn", auditLog);
        logger.subscribe("error", auditLog);

        logger.emit("info", "Application started");
        logger.emit("warn", "Memory usage high");
        logger.emit("error", "Database connection failed");

        System.out.println("\n✅ All demos completed successfully!");
    }
}

/*
 * ╔═══════════════════════════════════════════════════════════════════╗
 * ║ OBSERVER PATTERN SUMMARY ║
 * ╠═══════════════════════════════════════════════════════════════════╣
 * ║ Subject (Observable): ║
 * ║ - Maintains list of observers ║
 * ║ - subscribe(observer), unsubscribe(observer), notify() ║
 * ║ ║
 * ║ Observer: ║
 * ║ - Implements update(event, data) ║
 * ║ - Gets called when subject changes ║
 * ║ ║
 * ║ 📌 Used in: GUI events, MVC pattern, message systems ║
 * ║ 📌 Java has: java.beans.PropertyChangeListener (built-in) ║
 * ║ 📌 Modern: Use lambdas as observers for concise code ║
 * ╚═══════════════════════════════════════════════════════════════════╝
 */
