Why String is Immutable in Java?

Security: Prevents modification of sensitive strings (like file paths, DB URLs).

Caching (String Pool): Same literal reused → saves memory.

Thread-safety: Multiple threads can safely share strings.

HashCode caching: Since value never changes, hashCode is cached → fast lookups in HashMap.

✅ Summary

Mutable: Can change state (e.g., StringBuilder, ArrayList).

Immutable: Cannot change state, new object created on modification (e.g., String, Integer).

Immutability is safer and thread-friendly, while mutability is faster for frequent updates.

👉 Do you want me to also create a diagram (memory-level flow for String vs StringBuilder) to visualize how immutability works?

You said:
Give me 15 questions on hashset and hashmaps
ChatGPT said:

Perfect 👍 — here are 15 well-structured interview questions on HashMap & HashSet ranging from basic → advanced. These are the kinds of questions that come up in real interviews (from service companies to FAANG).

🔹 15 Interview Questions on HashMap & HashSet
✅ Basic Level

What is the difference between HashMap and HashSet?

(Storage, uniqueness, null handling, internal implementation)

How does HashMap work internally in Java?

(hashCode → index → bucket → equals → collision handling)

Can HashMap have null keys and values? How many?

(1 null key, multiple null values)

What is the time complexity of put() and get() in HashMap?

(Average O(1), Worst O(n) / O(log n) in Java 8+)

Why is String a good key for HashMap?

(Immutable, cached hashCode, reliable equals/hashCode contract)

✅ Intermediate Level

What is load factor in HashMap? Why is the default 0.75?

(Trade-off between memory usage & lookup performance)

How does HashSet ensure uniqueness of elements?

(Uses HashMap internally → keys must be unique)

Difference between HashMap and ConcurrentHashMap?

(Thread-safety, null handling, locking mechanism)

What happens if you override equals() but not hashCode() in a HashMap key?

(Contract violation → duplicates may exist unexpectedly)

What is rehashing in HashMap?

(Resizing + redistributing entries when load factor exceeded)

✅ Advanced Level

Explain collision handling in HashMap (Java 7 vs Java 8).

(Linked list in Java 7, Linked list + Red-Black tree in Java 8)

Why was tree-based bucket introduced in Java 8 HashMap?

(To avoid O(n) lookup in case of too many collisions → worst case O(log n))

What are the differences between HashMap and Hashtable?

(Synchronization, nulls, performance, legacy nature)

Why should HashMap keys be immutable?

(Because if key value changes, hashCode changes → leads to data loss / lookup failure)

What happens internally when two different keys have the same hashCode()?

(They go into same bucket → compared using equals() → if equal, overwrite; else coexist in bucket list/tree)