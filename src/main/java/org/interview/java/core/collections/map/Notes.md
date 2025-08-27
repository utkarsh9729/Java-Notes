# 🌟 In-depth Overview of Hashing, HashMap, and HashSet in Java

---

## 🔹 1. Hashing Fundamentals
Hashing is the **core concept** behind both `HashMap` and `HashSet`.

### 📌 Hashing Workflow
1. **Key → HashCode**  
   Each object in Java inherits `hashCode()` from `Object`.
   ```java
   int hashCode = key.hashCode();
   ```

2. **HashCode → Index (Bucket)**  
   HashMap doesn’t use `hashCode` directly; it **mixes bits** for better distribution.
   ```java
   int index = (n - 1) & hashCode; // where n = array size
   ```

3. **Collision Handling**
    - If two keys map to same index → **collision**.
    - Java resolves via **chaining**: each bucket has a **linked list** (pre-Java 8) or **linked list/tree** (Java 8+).

4. **Load Factor & Resizing**
    - Default capacity = **16**, load factor = **0.75**.
    - If `size > capacity * loadFactor` → table is **resized (doubled)** and all elements are **rehashed**.
    - Resizing = costly `O(n)`, but amortized cost still **O(1)**.

---

## 🔹 2. HashMap – Deep Dive
`HashMap<K,V>` is the **workhorse of Java Collections**.

### 📌 Internal Structure
- Backed by an **array of Node<K,V> buckets**.
- Each `Node` stores:
  ```java
  static class Node<K,V> {
      final int hash;
      final K key;
      V value;
      Node<K,V> next; // linked list (or tree node)
  }
  ```

### 📌 Steps for `put(K key, V value)`
1. Compute hash → index.
2. If bucket empty → place new node.
3. If bucket not empty:
    - Compare `hash` + `equals()` of keys.
    - If same key → **replace value**.
    - Else → append node in linked list (or tree if > 8 nodes).

### 📌 Steps for `get(K key)`
1. Compute hash → index.
2. Traverse list/tree in bucket.
3. Return value if key found; else return `null`.

### 📌 Java 8 Optimization
- If bucket size > **8**, linked list converts to **Red-Black Tree**.
- Lookup becomes **O(log n)** instead of **O(n)** in worst case.

---

## 🔹 3. HashSet – Deep Dive
`HashSet<E>` is essentially a **wrapper over HashMap**.

### 📌 Internal Implementation
```java
public class HashSet<E> extends AbstractSet<E> {
    private transient HashMap<E,Object> map;
    private static final Object PRESENT = new Object();
}
```
- When you call `set.add("Java")`, internally:
  ```java
  map.put("Java", PRESENT);
  ```
- Values are dummy (`PRESENT`) — only keys matter.

### 📌 Characteristics
- Stores **unique elements** only (no duplicates).
- Allows **one null** element.
- **Order not preserved** (use `LinkedHashSet` for insertion order).

---

## 🔹 4. HashMap vs HashSet
| Aspect            | HashMap                                | HashSet                        |
|-------------------|----------------------------------------|--------------------------------|
| Stores            | Key-Value pairs                        | Only keys (via HashMap keys)   |
| Duplicates        | Keys unique, values can duplicate      | No duplicates allowed          |
| Nulls             | 1 null key, many null values           | 1 null element                 |
| Internal Impl.    | Array + LinkedList/Tree (Node<K,V>)    | Uses HashMap<K,Object>         |
| Access            | get(key), put(key, value)              | add(e), contains(e)            |

---

## 🔹 5. Hashing Contracts in Java
To ensure correctness, **hashCode() and equals() must follow contract**:

1. If `a.equals(b)` → `a.hashCode() == b.hashCode()`
2. If `a.hashCode() == b.hashCode()` → not necessarily equal (collision possible).
3. Both must be **consistent**.

### 📌 Pitfall Example
```java
class Person {
   String name;
   Person(String n){ name = n; }
   // forgot to override hashCode()
   @Override
   public boolean equals(Object o) {
       return o instanceof Person && name.equals(((Person)o).name);
   }
}
```
- `new Person("Utkarsh")` and `new Person("Utkarsh")` are equal (`equals`),  
  but hashCodes differ → HashSet stores **duplicates**!

✅ Always override both **equals()** and **hashCode()**.

---

## 🔹 6. Performance
- **Average complexity:**
    - `put`, `get`, `remove` → **O(1)**.
- **Worst-case complexity:**
    - If many collisions:
        - Java 7 (linked list): **O(n)**.
        - Java 8+ (tree): **O(log n)**.

---

## 🔹 7. Common Interview Qs (HashMap/HashSet)
1. Internal working of `HashMap` (Java 7 vs Java 8).
2. Why is load factor default `0.75`?
3. How does `hashCode()` and `equals()` affect HashMap?
4. Why is HashMap not thread-safe?
5. Difference between `HashMap`, `ConcurrentHashMap`, `Hashtable`.
6. Can HashMap store duplicate keys? (No, overwrites).
7. How does HashSet check uniqueness? (via HashMap keys).
8. What happens if all objects return same hashCode()? (all go into one bucket → tree/list).
9. Why Tree instead of LinkedList in Java 8 buckets? (to prevent O(n) worst case).
10. Why is HashMap key immutable (best practice)? (hashCode stability).

---

## 🔹 8. Best Practices
- Always override **hashCode() + equals()** for custom objects.
- Prefer **immutable keys** (like `String`, `Integer`).
- If high concurrency needed → use `ConcurrentHashMap`.
- If predictable iteration order needed → use `LinkedHashMap`.
- For sorted order → use `TreeMap` / `TreeSet`.

---

# ✅ Summary
- **Hashing** = mapping keys to buckets using `hashCode()`.
- **HashMap** = key-value pairs, high-performance O(1) operations.
- **HashSet** = unique values, built on top of HashMap.
- **Contracts (hashCode & equals)** are critical to correctness.
- Java 8 introduced **Tree nodes in buckets** for performance guarantees.  
