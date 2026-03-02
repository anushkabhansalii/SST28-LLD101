package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Global metrics registry properly implemented as a Singleton.
 *
 * It is:
 * - Lazy-initialized using double-checked locking.
 * - Thread-safe for accessing counters.
 * - Protected against Reflection-based construction.
 * - Protected against Serialization breaking the singleton.
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // volatile is necessary for correct double-checked locking behavior
    private static volatile MetricsRegistry INSTANCE;

    // ConcurrentHashMap provides thread-safe access without full synchronization on
    // the whole class
    private final Map<String, Long> counters = new ConcurrentHashMap<>();

    private MetricsRegistry() {
        // Protect against reflection attacks if the instance already exists
        if (INSTANCE != null) {
            throw new IllegalStateException("Instance already exists! Use getInstance() instead.");
        }
    }

    public static MetricsRegistry getInstance() {
        if (INSTANCE == null) {
            synchronized (MetricsRegistry.class) {
                // Secondary check inside synchronized block
                if (INSTANCE == null) {
                    INSTANCE = new MetricsRegistry();
                }
            }
        }
        return INSTANCE;
    }

    public void setCount(String key, long value) {
        counters.put(key, value);
    }

    public void increment(String key) {
        // compute is an atomic operation in ConcurrentHashMap
        counters.compute(key, (k, v) -> (v == null) ? 1L : v + 1L);
    }

    public long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new ConcurrentHashMap<>(counters));
    }

    // Preserve singleton on deserialization
    @Serial
    protected Object readResolve() {
        return getInstance();
    }
}
