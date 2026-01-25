# Java Collections — Interface Notes (Cheat-Sheet)

> Covers **interfaces, contracts, hierarchy, complexity, ordering, null rules, fail-fast, mutability, and common pitfalls**. Focuses on `java.util` (non-concurrent) + a brief note on concurrent counterparts.

---

## 0) Big Picture

- **Iterable** → **Collection** → { **List**, **Set**, **Queue**/**Deque** }
- **Map** is a **sibling hierarchy** (not a `Collection`).
- Algorithms live in `Collections` (static helpers) and `Arrays`.

### ASCII Hierarchy (interfaces & canonical impls)

        Iterable
        └─ Collection
        ├─ List
        │ ├─ ArrayList
        │ ├─ LinkedList (also Deque)
        │ └─ Vector → Stack (legacy)
        ├─ Set
        │ ├─ HashSet → LinkedHashSet
        │ └─ SortedSet → NavigableSet → TreeSet
        └─ Queue
        ├─ Deque
        │ ├─ ArrayDeque
        │ └─ LinkedList
        └─ PriorityQueue

        Map (not a subinterface of Collection)
        ├─ HashMap → LinkedHashMap
        └─ SortedMap → NavigableMap → TreeMap
        (legacy: Hashtable)


---

## 1) Root Contracts

### `Iterable<E>`
- Provides `Iterator<E> iterator()`.
- Enhanced-for loop (`for (E e : iterable)`) relies on this.
- **Iterator** contract: `hasNext()`, `next()`, `remove()` (optional), and *fail-fast* behavior for most JCF iterators.

### `Collection<E>`
- Basic ops: `size`, `isEmpty`, `contains`, `add`, `remove`, `clear`, `toArray`, `iterator`, `stream`.
- **Bulk ops**: `addAll`, `removeAll`, `retainAll`, `containsAll`.
- **Optional operations**: Some collections are unmodifiable; calling mutators throws `UnsupportedOperationException`.

---

## 2) Linear Collections

### `List<E>`
- **Ordered**, **index-based**, **duplicates allowed**, **null allowed** (except in some specialized impls).
- Positional methods: `get`, `set`, `add(index, e)`, `remove(index)`, `listIterator`.
- **Equality**: element-wise in order.
- **Typical impls**:
    - `ArrayList`: O(1) amortized `add`, O(1) `get`, O(n) middle insert/remove.
    - `LinkedList`: O(1) add/remove at ends, O(n) index access; also implements `Deque`.

### When to pick
- **Random access heavy** → `ArrayList`
- **Frequent middle insertions/removals** and **list as queue/deque** → `LinkedList` (though often `ArrayDeque` is better for pure deque).

---

## 3) Sets

### `Set<E>`
- **No duplicates** (by `equals`), **no positional index**.
- Equality: **same elements** (order irrelevant).

#### `HashSet<E>`
- Backed by `HashMap`.
- Average O(1) for `add`, `contains`, `remove`.
- **Requires stable `hashCode`/`equals`** (see §9).

#### `LinkedHashSet<E>`
- Hash semantics + **insertion order** iteration.
- Slight overhead vs `HashSet`.

#### `SortedSet<E>` / `NavigableSet<E>`
- Sorted by **natural order** (`Comparable`) or a supplied **`Comparator`**.
- `NavigableSet` adds `lower`, `floor`, `ceiling`, `higher`, `pollFirst/Last`, and view methods.
- Canonical impl: **`TreeSet`** (balanced Red-Black tree).
    - O(log n) `add`/`contains`/`remove`.
    - **No `null`** elements when natural/comparator cannot handle it (TreeSet generally disallows `null`).

---

## 4) Queues & Deques

### `Queue<E>`
- **FIFO** semantics (by default).
- Dual method families:
    - Insert: `add`/`offer`
    - Remove: `remove`/`poll`
    - Examine: `element`/`peek`
    - (`offer`/`poll`/`peek` are non-exception variants.)

#### `PriorityQueue<E>`
- **Heap-based**; orders by priority (natural or `Comparator`).
- O(log n) insertion and removal of head.
- **Iteration order is not sorted**, only the head is the least/greatest by priority.
- Allows a single `null`? **No, `null` elements are disallowed.**

### `Deque<E>` (double-ended queue)
- Methods for both ends: `addFirst/Last`, `offerFirst/Last`, `pollFirst/Last`, `peekFirst/Last`, `push`/`pop`.
- Canonical impls:
    - `ArrayDeque`: resizable circular array; **preferred** for stacks/queues over `Stack`/`LinkedList`.
    - `LinkedList`: node-based; also a `List`.

---

## 5) Maps (key–value)

> Not a `Collection`, but core to the framework.

### `Map<K,V>`
- Unique **keys**; values may duplicate.
- Key ops: `put`, `get`, `remove`, `containsKey`, `keySet`, `values`, `entrySet`, `compute*`, `merge`, `putIfAbsent`, `replace*`.
- **Equality**: equal iff same key–value pairs.

#### `HashMap<K,V>`
- Average O(1) `put`/`get`/`remove`.
- **Iteration order unspecified**.
- Since Java 8: bins may treeify for long collision chains.

#### `LinkedHashMap<K,V>`
- Predictable iteration: **insertion-order** or **access-order** (LRU-style with `removeEldestEntry` override).

#### `SortedMap<K,V>` / `NavigableMap<K,V>`
- Sorted keys. Canonical impl: **`TreeMap`** (RB-tree).
- O(log n) `put`/`get`/`remove`.
- `NavigableMap` adds `lowerEntry`, `floorEntry`, `ceilingEntry`, `higherEntry`, `pollFirst/LastEntry`, `descendingMap`, `subMap` with inclusive flags.

#### Legacy: `Hashtable`
- Synchronized, no `null` keys/values. Prefer `HashMap` + external sync or `ConcurrentHashMap`.

---

## 6) Views, Ranges, and Backed Collections

- **Backed views** reflect changes on the underlying collection:
    - `subList(from, to)` on `List`
    - `subSet`, `headSet`, `tailSet` on `SortedSet`/`NavigableSet`
    - `keySet`, `values`, `entrySet` on `Map`
- Ranged views in navigable types accept **inclusive** flags (e.g., `subSet(a,true,b,false)`).
- **Concurrent modification** of a view and its backing collection can throw `ConcurrentModificationException` if iterating.

---

## 7) Mutability Spectrum

- **Unmodifiable wrappers**: `Collections.unmodifiableList/Set/Map/...`
- **Immutable factories (Java 9+)**: `List.of(...)`, `Set.of(...)`, `Map.of(...)`, `Map.ofEntries(...)`
    - Fixed size, disallow `null`s, preserve encounter order for lists.
- **Copying**: `List.copyOf`, `Set.copyOf`, `Map.copyOf` (immutable copies; reject `null`).

---

## 8) Performance & Big-O (typical)

| Interface | Impl             | Add | Contains/Get | Remove | Notes |
|---|---|---:|---:|---:|---|
| List      | ArrayList        | amort. O(1) tail | O(1) index | O(n) middle | Resizes; fast random access |
| List      | LinkedList       | O(1) ends | O(n) index | O(1) ends | Also Deque |
| Set       | HashSet          | O(1) avg | O(1) avg | O(1) avg | Quality hash needed |
| Set       | LinkedHashSet    | O(1) avg | O(1) avg | O(1) avg | Preserves insertion order |
| Set       | TreeSet          | O(log n) | O(log n) | O(log n) | Sorted/navigable |
| Queue     | ArrayDeque       | O(1) amort. ends | — | O(1) ends | Prefer over `Stack` |
| Queue     | PriorityQueue    | O(log n) offer | — | O(log n) poll | Heap; not globally sorted |
| Map       | HashMap          | O(1) avg | O(1) avg | O(1) avg | Unordered |
| Map       | LinkedHashMap    | O(1) avg | O(1) avg | O(1) avg | Insertion/access order |
| Map       | TreeMap          | O(log n) | O(log n) | O(log n) | Sorted/navigable |

*Actual performance depends on load factor, comparator cost, GC, etc.*

---

## 9) Equality, Ordering, and `hashCode`

- **Sets/Maps** rely on:
    - Consistent **`equals`/`hashCode`** for keys/elements.
    - If an element’s fields used in `equals`/`hashCode` mutate **after insertion**, the collection may become corrupted (unfindable entries).
- **Sorted collections** (`TreeSet`/`TreeMap`) rely on **`compareTo`/`Comparator`**:
    - Comparator must be **consistent with equals** *or* you accept “distinct but equal by comparator” semantics.
    - Must be **antisymmetric, transitive, total**; avoid inconsistent comparators.

---

## 10) Null-handling (common cases)

- `HashMap`, `HashSet`, `ArrayList`: allow `null` (1 `null` key in `HashMap`).
- `LinkedHashMap/Set`: same as hash counterparts.
- `TreeMap/TreeSet`: generally **disallow `null`** keys/elements because ordering is undefined.
- `PriorityQueue`: **no `null`** elements.
- `Hashtable`, `ConcurrentHashMap`: **no `null`** keys/values.

---

## 11) Iteration & Fail-Fast

- Most JCF iterators are **fail-fast**: structural modification outside the iterator during iteration → `ConcurrentModificationException`.
- Safe removal during iteration: use `Iterator.remove()` (or `ListIterator`’s `add`/`set` when supported).

---

## 12) Thread-Safety (quick map)

- **Not thread-safe by default**: `ArrayList`, `HashMap`, `HashSet`, `TreeMap`, etc.
- Wrappers: `Collections.synchronizedList/Set/Map`.
- **Concurrent alternatives** (in `java.util.concurrent`):
    - `ConcurrentHashMap`, `CopyOnWriteArrayList/Set`, `ConcurrentLinkedQueue/Deque`, `LinkedBlockingQueue`, `ArrayBlockingQueue`, `PriorityBlockingQueue`, `ConcurrentSkipListMap/Set` (sorted).
- **Iteration** over concurrent collections is **weakly consistent** (no `ConcurrentModificationException` and may not reflect all updates).

---

## 13) Utility Methods You’ll Actually Use

- **Sorting**:
    - Lists: `list.sort(Comparator)` or `Collections.sort(list)`.
- **Binary search**: `Collections.binarySearch(list, key, comparator)` (list must be sorted).
- **Shuffling/Rotating/Swapping**: `Collections.shuffle/rotate/swap`.
- **Frequency**: `Collections.frequency(collection, o)`.
- **Disjoint**: `Collections.disjoint(a, b)`.
- **Min/Max**: `Collections.min/max(collection, comparator?)`.
- **Unmodifiable Views**: `Collections.unmodifiableXxx`.
- **Checked (type-safe) Views**: `Collections.checkedList/Set/Map` (runtime type checks).

---

## 14) Typical Patterns

- **LRU cache (bounded)**:
  ```java
  Map<K,V> cache = new LinkedHashMap<>(16, 0.75f, true) {
    protected boolean removeEldestEntry(Map.Entry<K,V> e) { return size() > 1000; }
  };
Top-K elements:

```java

PriorityQueue<E> pq = new PriorityQueue<>(Comparator.naturalOrder()); // min-heap
```
// maintain size K; poll() to drop smallest
Distinct while preserving order:

```java

Collection<E> distinct = new LinkedHashSet<>(original);
```
Range queries:

```java

NavigableMap<K,V> map = new TreeMap<>();

// ...
map.subMap(lo, true, hi, false); // [lo, hi)
```
15) Common Pitfalls
Mutating key fields after inserting into HashMap/HashSet.
        
        Assuming HashMap iteration order is stable (use LinkedHashMap if needed).
        
        Using PriorityQueue and expecting full sorted traversal.
        
        Forgetting that subList/subSet are backed views (modifying one affects the other).
        
        Passing null into structures that forbid it (e.g., TreeMap, PriorityQueue).
        
        Relying on fail-fast as a correctness mechanism (it’s a best-effort bug detector).

16) Mini Reference (What to choose?)
        Need fast membership / no order → HashSet
        
        Need no dups, preserve insertion order → LinkedHashSet
        
        Need sorted elements → TreeSet
        
        Need index access / random reads → ArrayList
        
        Need deque/stack/queue (single-threaded) → ArrayDeque
        
        Need priority processing → PriorityQueue
        
        Need key→value map (fast) → HashMap
        
        Need map with predictable iteration order → LinkedHashMap
        
        Need sorted map → TreeMap

17) Micro Examples
    ```java
    // 1) Distinct while preserving order
    List<Integer> nums = List.of(3,1,2,3,2,4);
    List<Integer> distinct = new ArrayList<>(new LinkedHashSet<>(nums)); // [3,1,2,4]
```java
// 2) Top-3 largest
PriorityQueue<Integer> minK = new PriorityQueue<>(3); // min-heap
for (int x : nums) { minK.offer(x); if (minK.size() > 3) minK.poll(); }
List<Integer> top3 = new ArrayList<>(minK); // contains 3 largest (unsorted)
```

```java
// 3) Range query on NavigableMap
NavigableMap<Integer,String> m = new TreeMap<>();
m.put(10,"a"); m.put(20,"b"); m.put(30,"c");
SortedMap<Integer,String> r = m.subMap(10, true, 30, false); // {10=a, 20=b}
```
### Quick Glossary
Fail-fast: iterator throws on concurrent structural modification.

Weakly-consistent: iterator may reflect some/all/none of concurrent changes, but won’t throw.

Backed view: changes reflect in both view and source.

Stable hash: equals and hashCode consistent and immutable while stored.


Here is the full internal implementation of Java HashMap in Markdown format.

```markdown
# Java HashMap Internal Implementation (Deep Dive)

## 1. The High-Level Architecture
At its core, a `HashMap` is an **Array of Buckets**.
* **The Array:** `transient Node<K,V>[] table;`
* **The Bucket:** Each slot in the array acts as a "bucket." Initially, it is null. When data is added, it holds the head of a Linked List (or a Red-Black Tree in Java 8+).



## 2. The Node Structure
Before Java 8, there was only `Entry`. In Java 8, this became `Node`.

```java
// Simplified view of the static inner class
static class Node<K,V> implements Map.Entry<K,V> {
    final int hash;    // Cached hash code (for performance)
    final K key;
    V value;
    Node<K,V> next;    // Pointer to next node (Linked List)
}

```

**New in Java 8:** If a collision chain grows too long, the bucket converts to a `TreeNode` (Red-Black Tree structure).

```java
static class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
    TreeNode<K,V> parent;  // Red-Black tree links
    TreeNode<K,V> left;
    TreeNode<K,V> right;
    TreeNode<K,V> prev;    // needed to unlink next upon deletion
    boolean red;
}

```

## 3. The Hashing Logic (Crucial for Seniors)

This is where the magic happens. HashMap does *not* use the raw `hashCode()` directly.

### Step A: The "Perturbation" Function (Mixing)

Ideally, hash codes are well-distributed. In reality, many objects have poor hash codes (e.g., all Float keys ending in .0 might have similar lower bits).
To fix this, Java applies a secondary hash function:

```java
// Java 8 Implementation
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}

```

* **Why XOR `^` with `>>> 16`?**
  It shifts the "high bits" (the left side of the 32-bit integer) down and XORs them with the "low bits."
* **Reason:** The table index is calculated using only the *lower bits*. If we didn't do this mixing, all keys that differ only in the high bits would collide. This simple math spreads the entropy to the lower bits.



### Step B: Index Calculation

```java
int index = (n - 1) & hash;

```

* **Why `(n-1) & hash` instead of `hash % n`?**
* Bitwise AND (`&`) is significantly faster than the Modulo (`%`) operator at the CPU level.
* **Condition:** This trick **only** works if `n` (table capacity) is a **power of 2**. This is why HashMap enforces capacity as .



## 4. The `put(K key, V value)` Lifecycle

1. **Hash Calculation:** Compute `hash(key)`.
2. **Table Check:** If the table is empty, initialize it (resize).
3. **Index:** Calculate `i = (n - 1) & hash`.
4. **No Collision:** If `table[i] == null`, create a new `Node` and place it there.
5. **Collision (The Complex Part):**
* **Check Head:** Does the first node match the key? (Checks `hash` equality first, then `==` or `equals()`). If yes, overwrite value.
* **Is it a Tree?** If `table[i]` is a `TreeNode`, use Red-Black Tree insertion rules ().
* **Is it a List?** Iterate the Linked List.
* Append the new node to the end.
* **Treeification Check:** After appending, if the bin count exceeds `TREEIFY_THRESHOLD` (default 8), call `treeifyBin()`.
* *Note:* It only converts to a tree if the total array capacity is also  `MIN_TREEIFY_CAPACITY` (64). If capacity < 64, it resizes the array instead of treeifying.






6. **Update Size:** Increment `modCount` (for fail-fast iterators) and `size`.
7. **Resize:** If `size > threshold` (capacity * loadFactor), call `resize()`.

## 5. Handling Collisions: The Java 8 Revolution

* **Java 7:** Always used a Linked List.
* *Attack Vector:* A malicious user could send thousands of requests with keys that hash to the same bucket (Hash DoS Attack). The HashMap degrades to a Linked List (), crashing the CPU.


* **Java 8:** Uses **Red-Black Trees**.
* When a bucket has  nodes, it becomes a Tree ().
* When a bucket shrinks to  nodes (during deletion/resize), it "untreeifies" back to a Linked List () to save memory (TreeNodes are ~2x larger than standard Nodes).



## 6. Resizing (Rehashing) Mechanics

When the map fills up, it must double in size. This is an expensive operation.

1. **New Array:** Create a new array with `newCap = oldCap << 1` (double size).
2. **Redistribution (The Optimization):**
   In Java 7, every entry needed its hash recalculated to find the new index.
   In Java 8, they used a clever bit-manipulation trick.
* Since the capacity doubles (e.g., 16 to 32), the index only changes based on **one specific bit** in the hash (the bit at the new power-of-2 position).
* The code checks: `(e.hash & oldCap) == 0`.
* **If 0:** The element stays at the **same index**.
* **If 1:** The element moves to **index + oldCap**.


* **Benefit:** No need to recompute hash codes; mostly pointer manipulation.



## 7. Concurrency Issues

You must know *why* HashMap is not thread-safe.

1. **Race Condition (Java 7):** If two threads resize the map simultaneously, the Linked List could form a **circular reference** (A->B->A). The next `get()` operation would result in an **infinite loop**, pinning the CPU at 100%.
2. **Data Loss (Java 8):** The infinite loop issue was fixed in Java 8 (by inserting at the tail instead of the head), but race conditions now result in **lost updates** (one thread overwriting another's insertion) or corrupted size counters.

## 8. Summary of Constants

| Constant | Value | Purpose |
| --- | --- | --- |
| `DEFAULT_INITIAL_CAPACITY` | 16 | Must be power of 2. |
| `DEFAULT_LOAD_FACTOR` | 0.75f | Trade-off between time and space. |
| `TREEIFY_THRESHOLD` | 8 | Bucket converts to Tree if count > 8. |
| `UNTREEIFY_THRESHOLD` | 6 | Bucket converts back to List if count < 6. |
| `MIN_TREEIFY_CAPACITY` | 64 | Table must be at least this big to allow Trees. |

```

```