## 1. Heap Structure & Metaspace
The JVM memory is broadly divided into **Heap** (object storage) and **Non-Heap** (metadata, code, threads).

### **A. Young Generation (Young Gen)**
* **Eden Space:** Where 99% of new objects are allocated.
* **Survivor Spaces (S0 & S1):** Objects that survive a Minor GC move here. They flip roles ("From" / "To") during collection.
* **Tuning:** `-Xmn` sets the size. A larger Young Gen reduces Minor GC frequency but increases pause time per GC.

### **B. Old Generation (Tenured)**
* **Tenuring Threshold:** Objects that survive multiple GC cycles (default usually 15) move here.
* **Large Objects:** Also stores large objects that don't fit in Eden (Humongous objects in G1GC).
* **Critical Note:** Major GCs (Full GCs) occur here and are generally **Stop-The-World (STW)** events.

### **C. Metaspace (Java 8+)**
* **Replacement for PermGen:** It stores class metadata, static variables (pointers), and method bytecode.
* **Key Difference:** PermGen was part of the Heap and had a fixed size. Metaspace uses **Native Memory** (OS memory) and can grow automatically unless capped via `-XX:MaxMetaspaceSize`.

---

## 2. Garbage Collection: G1GC vs. ZGC
At 6+ years of experience, you are expected to know *why* you would choose one over the other in an architectural review.

| Feature | **G1GC (Garbage First)** | **ZGC (Z Garbage Collector)** |
| :--- | :--- | :--- |
| **Philosophy** | **Throughput & Predictable Pauses.** Breaks heap into equal-sized "Regions." | **Ultra-Low Latency.** Scalable to multi-terabyte heaps. |
| **Mechanism** | Prioritizes regions with the most garbage (**Mixed GC**). Uses "Remembered Sets" to track references. | Uses **Colored Pointers** and **Load Barriers** to perform GC concurrently with app threads. |
| **STW Pauses** | Yes, but targets a user-defined limit (e.g., 200ms). | **< 1ms** (sub-millisecond in newer versions). |
| **Use Case** | General purpose, default for Java 9+. Good for heaps 4GB - 32GB. | Massive heaps (16TB+) or strict latency SLAs (gaming, HFT). |
| **Tuning** | `-XX:MaxGCPauseMillis=<time>` (Soft target) | `-XX:+UseZGC` (Generational ZGC added in Java 21). |

---

## 3. Stack vs. Heap Memory

### **Stack Memory**
* **Scope:** Thread-specific. Contains **Stack Frames** (one per method call).
* **Content:** Primitives (`int`, `boolean`), Local Variables, and **References** to objects in the Heap.
* **Exception:** `StackOverflowError` (e.g., infinite recursion).
* **Characteristics:** Fast access (LIFO), contiguous memory, strictly limited size.

### **Heap Memory**
* **Scope:** Shared across all threads.
* **Content:** Actual Objects (`new Student()`), Class instances, Arrays.
* **Exception:** `OutOfMemoryError: Java heap space`.
* **Characteristics:** Slower access, requires synchronization for thread safety, non-contiguous.

---

## 4. String Pool & Immutability Internals

### **String Constant Pool (SCP)**
* A special area in the Heap (moved from PermGen in Java 7).
* Stores unique String literals to save memory.
* `String s = "Hello"` checks the SCP first. If "Hello" exists, it returns the existing reference.

### **Immutability**
* Strings are immutable because the underlying `byte[]` (or `char[]` pre-Java 9) is `final` and private.
* **Why?** Allows caching of the **HashCode** (calculated once) and ensures thread safety without synchronization.

### **`intern()` Mechanism**
* Forces a String object created via `new String("abc")` to be checked against the SCP.
* If the literal exists, it returns the pool reference; otherwise, it adds it to the pool.

---

## 5. Memory Leaks: Detection & Prevention

A memory leak in Java occurs when objects are no longer needed but are referenced by valid objects (e.g., static fields), preventing GC.

### **Common Causes**
1.  **Static Collections:** `static List` that grows indefinitely.
2.  **Unclosed Resources:** Database connections, InputStreams not in `try-with-resources`.
3.  **Listeners/Callbacks:** Registering a listener/observer but failing to deregister it.
4.  **ThreadLocals:** In app servers (Tomcat), ThreadLocals not cleaned up can leak classloaders.

### **Detection: Heap Dump Analysis**
* **Tools:** Eclipse MAT (Memory Analyzer Tool), VisualVM, JProfiler.
* **The Workflow:**
    1.  Take a Heap Dump: `jmap -dump:format=b,file=dump.hprof <pid>`
    2.  Identify **Dominator Tree** (largest objects).
    3.  Look for **GC Roots** keeping the object alive.
    4.  Differentiate **Shallow Heap** (object size) vs. **Retained Heap** (size of object + everything it keeps alive).

---

## 6. Senior Theory Questions (7 YOE Level)
*These assess architectural understanding rather than syntax.*

1.  **Metaspace vs. PermGen:** Why specifically did Java 8 move static variables and string constants out of PermGen? How does this impact our JVM startup flags for a monolithic legacy application?
2.  **G1GC Internals:** Explain the concept of "Humongous Allocations" in G1GC. Why are they problematic for performance, and how does the G1GC algorithm handle them differently than normal Old Gen objects?
3.  **Reference Types:** We have a cache that is causing OOM errors. Which type of Reference (`Weak`, `Soft`, or `Phantom`) would you use to refactor this cache, and why is `WeakHashMap` not always the correct answer?
4.  **Tuning:** You have a low-latency application suffering from frequent "Stop-The-World" pauses. You observe high "Promotion Failed" errors in the logs. What does this indicate about the Young/Old Gen ratio or the Survivor spaces?
5.  **String Internals:** Since Java 9, Strings use `Compact Strings` (byte array + encoding flag). How does this change the memory footprint compared to the char array implementation in Java 8, specifically for non-Latin languages?
6.  **Concurrency & Visibility (JMM):** Explain the "Happens-Before" relationship. If Variable A is volatile, and Thread 1 writes to A, does Thread 2 see the updates to *other* non-volatile variables written by Thread 1 before the write to A?
7.  **ZGC Barriers:** How do ZGC's "Load Barriers" allow for concurrent compaction without stopping application threads?
8.  **Allocation:** Explain TLAB (Thread Local Allocation Buffers). How do they reduce contention during object creation in the Eden space?
9.  **Classloaders:** How can a Classloader leak cause a `Metaspace` OOM in a hot-deploy environment (like Tomcat or Spring Boot DevTools)?
10. **Finalization:** Why has `finalize()` been deprecated, and how do `Cleaner` or `PhantomReference` provide a safer alternative for resource cleanup?

---