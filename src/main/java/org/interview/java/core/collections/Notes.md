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