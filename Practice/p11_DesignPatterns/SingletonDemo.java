/**
 * SingletonDemo.java — Singleton Pattern: One Instance to Rule Them All
 * ======================================================================
 * CSE215 - Programming Language II
 *
 * 💡 INTUITION: The Singleton pattern ensures a class has exactly ONE instance
 *    and provides a global point of access to it.
 *
 *    Real-world analogy: There's only ONE president of a country at a time.
 *    Everyone refers to the same person when they say "the president."
 *
 *    Use cases:
 *      - Database connection pool
 *      - Logger
 *      - Configuration manager
 *      - Cache
 *
 * Topics covered:
 *   1. Eager initialization
 *   2. Lazy initialization
 *   3. Thread-safe singleton
 *   4. Enum singleton (best approach)
 *   5. When to use / when NOT to use
 *
 * 🔗 SEE ALSO: p11_DesignPatterns/FactoryPatternDemo.java
 */
package p11_DesignPatterns;

public class SingletonDemo {

    // =====================================================================
    // 1. EAGER INITIALIZATION
    // =====================================================================

    /**
     * 💡 Instance is created when the class is loaded.
     * Simple and thread-safe (JVM handles class loading synchronization).
     *
     * ✅ Pros: Simple, inherently thread-safe
     * ❌ Cons: Instance created even if never used (wastes memory if expensive)
     */
    static class EagerSingleton {
        private static final EagerSingleton INSTANCE = new EagerSingleton();

        private EagerSingleton() {
            // Private constructor prevents external instantiation
            System.out.println("      [Eager] Instance created!");
        }

        public static EagerSingleton getInstance() {
            return INSTANCE;
        }

        public void doSomething() {
            System.out.println("      [Eager] Working...");
        }
    }

    // =====================================================================
    // 2. LAZY INITIALIZATION
    // =====================================================================

    /**
     * 💡 Instance is only created when first requested.
     *
     * ⚠️ GOTCHA: This is NOT thread-safe! Two threads could both see
     * instance == null and create two instances.
     */
    static class LazySingleton {
        private static LazySingleton instance;

        private LazySingleton() {
            System.out.println("      [Lazy] Instance created!");
        }

        public static LazySingleton getInstance() {
            if (instance == null) { // ⚠️ Race condition possible here
                instance = new LazySingleton();
            }
            return instance;
        }

        public void doSomething() {
            System.out.println("      [Lazy] Working...");
        }
    }

    // =====================================================================
    // 3. THREAD-SAFE SINGLETON (Double-Checked Locking)
    // =====================================================================

    /**
     * ✅ Thread-safe AND lazy. Uses volatile + double-checked locking.
     *
     * 📌 RULE: The volatile keyword ensures all threads see the latest value
     * of instance (prevents instruction reordering).
     */
    static class ThreadSafeSingleton {
        private static volatile ThreadSafeSingleton instance;

        private ThreadSafeSingleton() {
            System.out.println("      [ThreadSafe] Instance created!");
        }

        public static ThreadSafeSingleton getInstance() {
            if (instance == null) { // First check (no lock)
                synchronized (ThreadSafeSingleton.class) {
                    if (instance == null) { // Second check (with lock)
                        instance = new ThreadSafeSingleton();
                    }
                }
            }
            return instance;
        }

        public void doSomething() {
            System.out.println("      [ThreadSafe] Working...");
        }
    }

    // =====================================================================
    // 4. ENUM SINGLETON (Recommended!)
    // =====================================================================

    /**
     * ✅ BEST PRACTICE: According to "Effective Java" by Joshua Bloch,
     * an enum singleton is the BEST way to implement Singleton in Java.
     *
     * - Thread-safe (guaranteed by JVM)
     * - Serialization-safe (no duplicate instances on deserialization)
     * - Reflection-safe (can't create new instances via reflection)
     * - Simple and concise
     */
    enum DatabaseConnection {
        INSTANCE;

        private String url = "jdbc:mysql://localhost:3306/mydb";

        public void connect() {
            System.out.println("      [EnumSingleton] Connected to " + url);
        }

        public void query(String sql) {
            System.out.println("      [EnumSingleton] Executing: " + sql);
        }
    }

    // =====================================================================
    // 5. Practical Example: Configuration Manager
    // =====================================================================

    static class ConfigManager {
        private static volatile ConfigManager instance;
        private final java.util.Map<String, String> config = new java.util.HashMap<>();

        private ConfigManager() {
            // Simulate loading config
            config.put("app.name", "MyApp");
            config.put("app.version", "1.0");
            config.put("db.host", "localhost");
            System.out.println("      [Config] Loaded configuration.");
        }

        public static ConfigManager getInstance() {
            if (instance == null) {
                synchronized (ConfigManager.class) {
                    if (instance == null) {
                        instance = new ConfigManager();
                    }
                }
            }
            return instance;
        }

        public String get(String key) {
            return config.getOrDefault(key, "NOT_FOUND");
        }

        public void set(String key, String value) {
            config.put(key, value);
        }
    }

    // =====================================================================
    // MAIN
    // =====================================================================
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   SINGLETON PATTERN DEMO                     ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // Eager
        System.out.println("=== 1. Eager Singleton ===");
        EagerSingleton e1 = EagerSingleton.getInstance();
        EagerSingleton e2 = EagerSingleton.getInstance();
        e1.doSomething();
        System.out.println("  Same instance? " + (e1 == e2)); // true

        // Lazy
        System.out.println("\n=== 2. Lazy Singleton ===");
        LazySingleton l1 = LazySingleton.getInstance();
        LazySingleton l2 = LazySingleton.getInstance();
        l1.doSomething();
        System.out.println("  Same instance? " + (l1 == l2)); // true

        // Thread-safe
        System.out.println("\n=== 3. Thread-Safe Singleton ===");
        ThreadSafeSingleton ts1 = ThreadSafeSingleton.getInstance();
        ThreadSafeSingleton ts2 = ThreadSafeSingleton.getInstance();
        ts1.doSomething();
        System.out.println("  Same instance? " + (ts1 == ts2)); // true

        // Enum singleton
        System.out.println("\n=== 4. Enum Singleton (Best!) ===");
        DatabaseConnection db = DatabaseConnection.INSTANCE;
        db.connect();
        db.query("SELECT * FROM users");
        System.out.println("  Same instance? " + (db == DatabaseConnection.INSTANCE)); // true

        // Practical: ConfigManager
        System.out.println("\n=== 5. Practical: Config Manager ===");
        ConfigManager config = ConfigManager.getInstance();
        System.out.println("  App: " + config.get("app.name") + " v" + config.get("app.version"));
        config.set("app.debug", "true");
        // Anywhere else in the app, same instance:
        System.out.println("  Debug: " + ConfigManager.getInstance().get("app.debug"));

        System.out.println("\n✅ All demos completed successfully!");
    }
}

/*
 * ╔═══════════════════════════════════════════════════════════════════╗
 * ║ SINGLETON COMPARISON ║
 * ╠═══════════════════════════════════════════════════════════════════╣
 * ║ Approach │ Thread-Safe │ Lazy │ Simple │ Recommended ║
 * ║──────────────────┼─────────────┼──────┼────────┼─────────────────║
 * ║ Eager │ ✅ │ ❌ │ ✅ │ For light objs ║
 * ║ Lazy │ ❌ │ ✅ │ ✅ │ Single-threaded ║
 * ║ Double-checked │ ✅ │ ✅ │ ❌ │ Multi-threaded ║
 * ║ Enum │ ✅ │ ❌ │ ✅ │ ✅ BEST overall ║
 * ╠═══════════════════════════════════════════════════════════════════╣
 * ║ ⚠️ Anti-pattern warning: Overusing Singleton creates tight ║
 * ║ coupling and makes testing harder. Use dependency injection ║
 * ║ when possible. ║
 * ╚═══════════════════════════════════════════════════════════════════╝
 */
