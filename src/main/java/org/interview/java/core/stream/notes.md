
## 1. What is a Stream?
A **Stream** in Java is a sequence of elements supporting **functional-style operations**.  
It was introduced in **Java 8** under the `java.util.stream` package.

👉 Key Idea:  
- It is **not a data structure** (like `List`, `Set`, or `Map`).  
- It is a **view** or **pipeline** of data from a source (Collection, array, I/O, etc.).  
- Operations on streams are **declarative** (focus on "what" not "how").  

Example:  
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
numbers.stream()
       .filter(n -> n % 2 == 0)
       .map(n -> n * n)
       .forEach(System.out::println); 
// Output: 4, 16
````

---

## 2. Stream vs Collection

| Aspect      | Collection                     | Stream                                    |
| ----------- | ------------------------------ | ----------------------------------------- |
| Nature      | Stores data (in memory)        | Views data (pipeline of computation)      |
| Iteration   | External iteration (for-loops) | Internal iteration (handled by framework) |
| Reusability | Reusable                       | Not reusable (once consumed, closed)      |
| Laziness    | Eager                          | Lazy (executes only when needed)          |
| Parallelism | Manual handling (threads)      | Built-in support with `parallelStream()`  |

---

## 3. Stream Pipeline (3 Stages)

Every Stream operation goes through a **pipeline**:

1. **Source**: Where the data comes from (Collection, Array, Generator, File, I/O).

   ```java
   Stream<String> stream = List.of("a", "b", "c").stream();
   ```

2. **Intermediate Operations**: Transform the stream (lazy). Always return a **new Stream**.
   Examples: `filter()`, `map()`, `sorted()`, `distinct()`, `limit()`, `skip()`, `flatMap()`.

3. **Terminal Operations**: Trigger execution and produce a result.
   Examples: `forEach()`, `collect()`, `reduce()`, `count()`, `findFirst()`, `anyMatch()`.

⚡ **Lazy Evaluation**: Intermediate operations are not executed until a terminal operation is called.

---

## 4. Key Operations

### a) `filter(Predicate<T>)`

Filters elements based on condition.

```java
numbers.stream()
       .filter(n -> n > 10)
       .forEach(System.out::println);
```

### b) `map(Function<T,R>)`

Transforms elements.

```java
words.stream()
     .map(String::toUpperCase)
     .forEach(System.out::println);
```

### c) `flatMap(Function<T,Stream<R>>)`

Flattens nested structures.

```java
List<List<String>> list = Arrays.asList(
    Arrays.asList("a","b"),
    Arrays.asList("c","d")
);
list.stream()
    .flatMap(Collection::stream)
    .forEach(System.out::println);
// a b c d
```

### d) `reduce()`

Aggregates values.

```java
int sum = numbers.stream()
                 .reduce(0, Integer::sum);
```

### e) `collect()`

Terminal operation to collect into List/Set/Map.

```java
List<String> result = words.stream()
                           .filter(w -> w.length() > 3)
                           .collect(Collectors.toList());
```

---

## 5. Primitive Streams

To avoid boxing/unboxing overhead, Java provides:

* `IntStream`
* `LongStream`
* `DoubleStream`

Example:

```java
int sum = IntStream.range(1, 5).sum(); // 1+2+3+4 = 10
```

---

## 6. Parallel Streams

* Streams can run **in parallel** using **ForkJoinPool** (common pool).
* Useful for CPU-heavy tasks on large datasets.

```java
list.parallelStream()
    .filter(x -> x % 2 == 0)
    .forEach(System.out::println);
```

⚠️ Pitfalls:

* Order of elements may be lost.
* Overhead of parallelism might be worse for small datasets.
* Stateful operations (`limit`, `findFirst`) may give different results.

---

## 7. Short-Circuiting

Some operations terminate early:

* `limit(n)` → stops after `n` elements
* `anyMatch()` → stops once condition is satisfied
* `findFirst()` → returns first match and stops

Example:

```java
Stream.of(1, 2, 3, 4, 5)
      .filter(n -> n > 2)
      .findFirst()
      .ifPresent(System.out::println); // 3
```

---

## 8. Collectors API

`Collectors` provide advanced reductions:

* `toList()`, `toSet()`, `toMap()`
* `groupingBy()`
* `partitioningBy()`
* `joining()`
* `summarizingInt()`

Example: Group by length of words

```java
Map<Integer, List<String>> map =
    words.stream().collect(Collectors.groupingBy(String::length));
```

---

## 9. Best Practices

✅ Use streams for **readable, functional-style transformations**.
✅ Prefer **parallel streams** only when dealing with **large data** + **CPU-bound tasks**.
✅ Avoid **stateful lambdas** (like modifying external variables).
✅ For primitive types, prefer **IntStream/LongStream** over boxed streams.
✅ Remember: **Streams are one-time-use**.

---

## 10. Real-World Examples

### Example 1: Find Top 3 Salaries

```java
List<Employee> employees = ...;

List<Employee> top3 = employees.stream()
    .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
    .limit(3)
    .collect(Collectors.toList());
```

### Example 2: Word Frequency

```java
String text = "apple banana apple orange banana apple";

Map<String, Long> freq = Arrays.stream(text.split(" "))
    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
```

---

# ✅ Summary

* **Streams = declarative, functional-style pipeline for processing data**.
* **Intermediate ops (lazy)** → filter, map, flatMap, sorted, distinct.
* **Terminal ops (eager)** → collect, reduce, forEach, findFirst, count.
* **Parallel streams** enable concurrency but must be used carefully.
* **Collectors** give powerful grouping/partitioning capabilities.
* Designed to improve **readability, maintainability, and parallel processing**.

```
