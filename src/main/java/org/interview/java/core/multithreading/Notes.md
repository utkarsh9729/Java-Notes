# 🧵 Multithreading & Concurrency in Java

> Comprehensive notes on thread management, synchronization, and modern concurrency utilities.

---

## 1. Thread Basics
- **Thread Lifecycle**: `NEW`, `RUNNABLE`, `BLOCKED`, `WAITING`, `TIMED_WAITING`, `TERMINATED`.
- **Creation Mechanisms**:
    - **Extending `Thread`**: Simple, but limits inheritance.
    - **Implementing `Runnable`**: Better separation of concerns, allows inheritance.
    - **Implementing `Callable<V>`**: Allows returning a value and throwing checked exceptions.

---

## 2. Synchronization Mechanisms

### 🔹 `synchronized` Keyword
- Ensures **Mutual Exclusion** (only one thread accesses the critical section).
- Can be applied to **instance methods**, **static methods**, or **code blocks**.
- Built-in monitor locking (Intrinsic Lock).

### 🔹 `volatile` Keyword
- Ensures **Visibility** of variables across threads.
- Prevents the JVM from caching a variable locally in a thread's CPU cache; forces reads/writes directly to main memory.
- Does **not** provide atomicity for compound operations (e.g., `i++`).

### 🔹 Locks (from `java.util.concurrent.locks`)
- **`ReentrantLock`**: More flexible than `synchronized`. Provides fairness options and `tryLock()` for non-blocking attempts.
- **`ReadWriteLock`**: Optimizes performance for read-heavy resources (multiple concurrent readers, single exclusive writer).

---

## 3. Atomic Classes
- `AtomicInteger`, `AtomicLong`, `AtomicBoolean`, `AtomicReference`.
- Use **CAS (Compare-And-Swap)** CPU instructions to perform lock-free thread-safe updates.

---

## 4. Modern Concurrency: Executors & CompletableFuture

### 🔹 Executor Framework
Avoid creating `new Thread()` manually in high-volume apps. Use **Thread Pools**:
- `Executors.newFixedThreadPool(n)`
- `Executors.newCachedThreadPool()`
- `Executors.newScheduledThreadPool(n)`

### 🔹 `CompletableFuture` (Java 8+)
- Enables **Asynchronous, non-blocking** programming.
- Supports pipeline chaining (`thenApply`, `thenCombine`, `exceptionally`).

---

## 5. Critical Interview Questions

### Q: Difference between `sleep()` and `wait()`?
- `sleep()` is a `Thread` static method; it does **not** release locks.
- `wait()` is an `Object` instance method; it **releases** the monitor lock and waits to be notified.

### Q: What is a "Race Condition"?
- Occurs when two or more threads access shared data concurrently and try to change it at the same time. The final result depends on the timing of execution.

### Q: What are "Deadlocks"?
- A situation where two or more threads are blocked forever, waiting for each other to release locks.
- **Prevention**: Acquire locks in a consistent order, use `tryLock()` with timeouts.

### Q: What are "Virtual Threads" (Java 21+)?
- Lightweight threads managed by the JVM rather than the OS. Designed to scale I/O-bound tasks to millions of concurrent operations with minimal overhead.
