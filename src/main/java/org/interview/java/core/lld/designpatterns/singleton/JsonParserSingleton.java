package org.interview.java.core.lld.designpatterns.singleton;

/**
 * Demonstrates the Thread-Safe Singleton pattern using Double-Checked Locking.
 */
public class JsonParserSingleton {

    // 1. Use 'volatile' to ensure visibility and prevent instruction reordering
    private static volatile JsonParserSingleton instance;

    // 2. Private constructor to prevent instantiation from outside
    private JsonParserSingleton() {
        // Initialize resources here
        System.out.println("JsonParserSingleton initialized.");
    }

    /**
     * Returns the singleton instance using double-checked locking.
     */
    public static JsonParserSingleton getInstance() {
        // First check: avoids synchronization overhead if instance is already initialized
        if (instance == null) {
            synchronized (JsonParserSingleton.class) {
                // Second check: ensures only one thread initializes the instance
                if (instance == null) {
                    instance = new JsonParserSingleton();
                }
            }
        }
        return instance;
    }

    public void parse(String json) {
        System.out.println("Parsing JSON: " + json);
    }
}
