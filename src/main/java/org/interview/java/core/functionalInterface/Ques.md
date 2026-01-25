
# ❓ Functional Interfaces – Interview Questions & Answers

---

## 1. Basic

### Q1. What is a functional interface in Java? Give an example.
**Answer:**  
- A **functional interface** is an interface with **exactly one abstract method** (also called a *Single Abstract Method* or *SAM*).  
- It can have multiple **default** or **static** methods.  
- Functional interfaces are the foundation for **lambda expressions** and **method references** in Java.

**Example:**
```java
@FunctionalInterface
interface MyFunctional {
    void execute();
}

public class Test {
    public static void main(String[] args) {
        MyFunctional f = () -> System.out.println("Running...");
        f.execute();
    }
}
````

---

### Q2. Why is the `@FunctionalInterface` annotation recommended? What happens if you don’t use it?

**Answer:**

* `@FunctionalInterface` is not mandatory, but it is recommended.
* It ensures at **compile time** that the interface has exactly one abstract method.
* If you mistakenly add another abstract method, the compiler throws an error.
* Without the annotation, the interface may still act as a functional interface (if it has only one abstract method), but you lose the safeguard.

---

### Q3. Can a functional interface have multiple default or static methods? Why or why not?

**Answer:**

* Yes ✅, a functional interface can have multiple **default** and **static** methods.
* The restriction is only on **abstract methods** (must be exactly one).
* Default and static methods act as **helper methods** for code reuse (e.g., logging, utility functions).

**Example:**

```java
@FunctionalInterface
interface Logger {
    void log(String msg); // single abstract method

    default void info(String msg) {
        System.out.println("[INFO] " + msg);
    }

    static void error(String msg) {
        System.out.println("[ERROR] " + msg);
    }
}
```

---

## 2. Lambda & Method References

### Q4. How are lambda expressions related to functional interfaces? Explain with an example.

**Answer:**

* A **lambda expression** provides the implementation of a **functional interface’s single abstract method**.
* Lambdas make code **concise** by reducing boilerplate of anonymous classes.

**Example (without lambda):**

```java
Runnable r = new Runnable() {
    @Override
    public void run() {
        System.out.println("Running...");
    }
};
```

**With lambda:**

```java
Runnable r = () -> System.out.println("Running...");
```

---

### Q5. What are method references (`::`) and how do they tie in with functional interfaces?

**Answer:**

* **Method references** are shorthand for lambdas that call an existing method.
* They work wherever a functional interface is expected.

**Example:**

```java
@FunctionalInterface
interface Printer {
    void print(String msg);
}

public class Test {
    public static void main(String[] args) {
        Printer printer = System.out::println; // method reference
        printer.print("Hello World!");
    }
}
```

---

## 3. Predefined Functional Interfaces

### Q6. Differentiate between `Predicate`, `Function`, `Consumer`, and `Supplier`. Give use cases for each.

**Answer:**

| Interface       | Method              | Description                  | Example Use Case          |
| --------------- | ------------------- | ---------------------------- | ------------------------- |
| `Predicate<T>`  | `boolean test(T t)` | Takes input, returns boolean | Filtering in streams      |
| `Function<T,R>` | `R apply(T t)`      | Takes input, returns output  | Convert String → Integer  |
| `Consumer<T>`   | `void accept(T t)`  | Takes input, no return       | Printing/logging          |
| `Supplier<T>`   | `T get()`           | No input, returns output     | Generating random numbers |

**Example:**

```java
Predicate<String> startsWithA = s -> s.startsWith("A");
Function<String, Integer> strLength = s -> s.length();
Consumer<String> printer = s -> System.out.println(s);
Supplier<Double> randomSupplier = () -> Math.random();
```

---

### Q7. What is the difference between `UnaryOperator` and `Function`? Between `BinaryOperator` and `BiFunction`?

**Answer:**

* **UnaryOperator<T>** → A specialization of `Function<T,T>`. Input and output are the **same type**.
* **BinaryOperator<T>** → A specialization of `BiFunction<T,T,T>`. Takes two inputs of the same type and returns the same type.

**Example:**

```java
UnaryOperator<Integer> square = x -> x * x; // Function<Integer, Integer>
BinaryOperator<Integer> sum = (a, b) -> a + b; // BiFunction<Integer,Integer,Integer>
```

---

## 4. Advanced/Tricky

### Q8. Why are methods inherited from `Object` (like `equals` or `toString`) not counted toward the single abstract method rule?

**Answer:**

* All interfaces in Java implicitly inherit `Object` methods.
* If these were counted, no interface could qualify as functional.
* Only **newly declared abstract methods** matter when deciding if an interface is functional.

---

### Q9. What is **SAM Conversion**? How does the compiler translate a lambda into a functional interface instance?

**Answer:**

* **SAM Conversion** = Single Abstract Method Conversion.
* It is the process where the compiler converts a **lambda expression** or **method reference** into an instance of a functional interface.
* At compile time, the lambda is converted into an object that implements the functional interface.

**Example:**

```java
Runnable r = () -> System.out.println("Hi");
// Compiler converts to an object of Runnable implementing run()
```

---

### Q10. Suppose you add a second abstract method to a functional interface that is already being used in lambda expressions. What will happen? Why?

**Answer:**

* If `@FunctionalInterface` is used → Compile-time error.
* Even without the annotation → Code using lambdas won’t compile, because the compiler cannot decide which method the lambda is implementing.

**Example:**

```java
@FunctionalInterface
interface MyInterface {
    void run();  // abstract
    // void stop();  // uncommenting this breaks compilation
}
```

---

# 📝 Final Summary

* A **Functional Interface** has exactly **one abstract method** (SAM).
* They are the foundation for **lambdas** and **method references**.
* Can have multiple **default** and **static** methods.
* Predefined ones exist in `java.util.function` like `Predicate`, `Function`, `Consumer`, `Supplier`.
* Specializations include `UnaryOperator` and `BinaryOperator`.
* Compiler uses **SAM Conversion** to map lambdas to functional interface instances.

```
