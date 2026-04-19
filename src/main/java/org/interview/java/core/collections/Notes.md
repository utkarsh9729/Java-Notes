# 📚 Java Collections Framework — Comprehensive Notes

> This guide covers interfaces, implementation details, time complexity, and common interview patterns for the `java.util` collections.

---

## 1. Hierarchy Overview

- **Iterable** → **Collection** → { **List**, **Set**, **Queue** / **Deque** }
- **Map** is a **sibling hierarchy** (not a `Collection`).

### Canonical Implementations
- **List**: `ArrayList`, `LinkedList`, `Vector` (Legacy)
- **Set**: `HashSet`, `LinkedHashSet`, `TreeSet` (Sorted)
- **Queue/Deque**: `PriorityQueue`, `ArrayDeque`, `LinkedList`
- **Map**: `HashMap`, `LinkedHashMap`, `TreeMap` (Sorted), `Hashtable` (Legacy)

---

## 2. Linear Collections

### `List<E>` (Ordered, Index-based, Duplicates allowed)
- **ArrayList**: Backed by a dynamic array. O(1) random access, amortized O(1) tail insertion. Best for read-heavy apps.
- **LinkedList**: Doubly-linked list. O(1) insertions at ends, O(n) random access. Useful for frequent middle insertions (if using iterator).

### `Deque<E>` (Double-ended queue)
- **ArrayDeque**: Circular array. **Preferred over Stack and LinkedList** for stack/queue operations due to better memory locality and performance.

---

## 3. Sets (No Duplicates)

- **HashSet**: O(1) operations. Unordered. Relies on `hashCode()` and `equals()`.
- **LinkedHashSet**: O(1) operations. Preserves **insertion order**.
- **TreeSet**: O(log n) operations. **Sorted** according to natural order or a custom `Comparator`. Disallows `null` (usually).

---

## 4. Maps (Key-Value pairs)

- **HashMap**: O(1) operations. Java 8+ uses **Red-Black Trees** for buckets with high collisions (>8 nodes), reducing worst-case from O(n) to O(log n).
- **LinkedHashMap**: Preserves **insertion order** or **access order** (perfect for LRU caches).
- **TreeMap**: Sorted keys. O(log n) operations.

---

## 5. Performance Cheat Sheet

| Implementation | Add | Get | Remove | Contains | Notes |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **ArrayList** | O(1)* | O(1) | O(n) | O(n) | *Amortized O(1) at tail |
| **LinkedList** | O(1) | O(n) | O(1)* | O(n) | *If at ends; else O(n) |
| **HashSet** | O(1) | - | O(1) | O(1) | Unordered |
| **TreeSet** | O(log n) | - | O(log n) | O(log n) | Sorted |
| **HashMap** | O(1) | O(1) | O(1) | O(1)* | *containsKey |
| **TreeMap** | O(log n) | O(log n) | O(log n) | O(log n) | Sorted Keys |

---

## 6. Critical Concepts

### Fail-Fast vs Weakly Consistent
- **Fail-Fast**: Iterators throw `ConcurrentModificationException` if the collection is structurally modified during iteration (e.g., `ArrayList`, `HashMap`).
- **Weakly Consistent**: Iterators allow concurrent modifications without throwing exceptions (e.g., `ConcurrentHashMap`, `CopyOnWriteArrayList`).

### `hashCode` & `equals` Contract
1. If `o1.equals(o2)` is true, then `o1.hashCode() == o2.hashCode()` **must** be true.
2. If `o1.hashCode() == o2.hashCode()` is true, `o1.equals(o2)` **might** be false (Collision).
3. **Always override both** together for custom keys in Maps/Sets.

### Backed Views
- `subList`, `subMap`, and `keySet` are **views**, not copies. Modifying the view modifies the original collection, and vice versa. Structural changes to the original can invalidate the view.

---

## 7. Common Pitfalls
1. **Mutating Key Fields**: Changing fields used in `hashCode` after putting an object in a `HashMap` makes it unretrievable.
2. **PriorityQueue Iteration**: Iterating over a `PriorityQueue` does **not** yield elements in sorted order; only `poll()` or `peek()` guarantees the head is the priority element.
3. **Passing Null**: `TreeMap`, `TreeSet`, and `PriorityQueue` generally throw `NullPointerException` on `null` elements/keys.
4. **Using `Stack`**: `java.util.Stack` is legacy and synchronized. Use `ArrayDeque` for stack operations.
