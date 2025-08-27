# Internal Working of `ArrayList` in Java

---

## 1) Basics
- `ArrayList` is a **resizable array implementation** of the `List` interface.
- It maintains a **dynamic array** internally (`Object[] elementData`).
- Provides:
    - **Random access** in O(1) via indexing.
    - **Insertion/Deletion**:
        - At end → amortized O(1).
        - At middle → O(n) (due to shifting).

---

## 2) Internal Structure

```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
    
    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA = {};

    transient Object[] elementData; // underlying array buffer
    private int size; // number of elements actually stored
}
```
    elementData: backing array (stores elements).
    
    size: current number of elements (logical size).
    
    Capacity: length of elementData.

## 3) Constructors
```java
// Default: empty array, grows on first add
ArrayList<String> list = new ArrayList<>();

// With initial capacity
ArrayList<String> list = new ArrayList<>(20);

// From another collection
ArrayList<String> list = new ArrayList<>(collection);
```
    Default constructor creates an empty array (not size 10 immediately).
    
    On first add(), it grows to DEFAULT_CAPACITY (10).

## 4) Adding Elements
Method:
```java
public boolean add(E e) {
    ensureCapacityInternal(size + 1); // check + grow if needed
    elementData[size++] = e;
    return true;
}
```
    Step 1: Check capacity with ensureCapacityInternal().
    
    Step 2: If not enough space → call grow().
    
    Step 3: Place new element at elementData[size].
    
    Step 4: Increment size.

##  5) Growth Mechanism
```java
private void grow(int minCapacity) {
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1); // 1.5x
    if (newCapacity < minCapacity)
        newCapacity = minCapacity;
    elementData = Arrays.copyOf(elementData, newCapacity);
}
```
    When full, capacity increases by 50% (oldCapacity * 1.5).
    
    Uses Arrays.copyOf() → creates new array, copies old elements.
    
    Expensive operation, but amortized across many inserts.

##  6) Removing Elements
By index:
```java

public E remove(int index) {
    rangeCheck(index);
    E oldValue = elementData(index);
    int numMoved = size - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index, numMoved);
    elementData[--size] = null; // free memory
    return oldValue;
}
```
    Validates index.
    
    Shifts remaining elements left by System.arraycopy().
    
    Clears last reference (avoid memory leak).
    
    Complexity: O(n - index).

## 7) Accessing Elements
```java
public E get(int index) {
    rangeCheck(index);
    return elementData(index);
}
```
    Simple array indexing.
    
    Complexity: O(1).

## 8) Capacity vs Size
size = number of elements stored.

capacity = length of backing array.

You can manage capacity:

```java
list.ensureCapacity(100); // pre-allocate
list.trimToSize();        // shrink to actual size
```
## 9) Iterators
    ArrayList provides:
    
    Iterator (fail-fast).
    
    ListIterator (bidirectional).
    
    Fail-fast:
    If modified structurally while iterating (except via iterator methods) → throws ConcurrentModificationException.

🔑 Summary of Time Complexity
            | Operation |	Time Complexity |
    
            get(index)	O(1)
            set(index, val)	O(1)
            add(e) (amortized)	O(1)
            add(index, e)	O(n)
            remove(index)	O(n)
            contains(e)	O(n)
            iterator()	O(1)

10) Key Points
        Backed by Object[] array.
        
        Resizes by 1.5x growth when full.
        
        Fast random access, slow insert/delete in middle.
        
        Not thread-safe → use Collections.synchronizedList() or CopyOnWriteArrayList in concurrent use.
        
        null values are allowed.


