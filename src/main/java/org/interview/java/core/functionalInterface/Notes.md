
# 📌 Functional Interfaces in Java – In-Depth Overview

---

## 1. Definition
A **functional interface** is an interface that contains **exactly one abstract method (SAM – Single Abstract Method)**.  
- It can have multiple **default** or **static** methods.  
- It can also have **methods from `java.lang.Object`** (like `toString`, `equals`, `hashCode`).  

👉 **Purpose**: Functional interfaces are the foundation of **lambda expressions** and **method references** introduced in Java 8.

### 🔑 Quick Revision
- ✅ Exactly 1 abstract method  
- ✅ Can have default + static methods  
- ✅ Object class methods don’t count  

---

## 2. Annotation – `@FunctionalInterface`
- Declares an interface as a functional interface.  
- Optional, but recommended to avoid mistakes.  
- Compiler error if >1 abstract method exists.  

```java
@FunctionalInterface
interface MyFunctional {
    void execute();
}
````

### 🔑 Quick Revision

* ✅ Not mandatory, but safe to use
* ✅ Guarantees "only one abstract method"

---

## 3. Predefined Functional Interfaces

Java provides many **built-in functional interfaces** in `java.util.function`.

| Interface             | Method Signature      | Use Case Example   |
| --------------------- | --------------------- | ------------------ |
| **Predicate<T>**      | `boolean test(T t)`   | Filtering          |
| **Function\<T,R>**    | `R apply(T t)`        | Transform input    |
| **Consumer<T>**       | `void accept(T t)`    | Consumes input     |
| **Supplier<T>**       | `T get()`             | Generates value    |
| **UnaryOperator<T>**  | `T apply(T t)`        | Modify same type   |
| **BinaryOperator<T>** | `T apply(T t1, T t2)` | Combine two inputs |

### 🔑 Quick Revision

* Predicate → true/false
* Function → input → output
* Consumer → takes input, no return
* Supplier → no input, gives output
* UnaryOperator → one input → same type output
* BinaryOperator → two inputs → same type output

---

## 4. Relation with Lambda Expressions

Functional interfaces are the **target types** for lambda expressions.

```java
@FunctionalInterface
interface Calculator {
    int add(int a, int b);
}

public class Test {
    public static void main(String[] args) {
        Calculator calc = (a, b) -> a + b;
        System.out.println(calc.add(5, 3)); // 8
    }
}
```

### 🔑 Quick Revision

* ✅ Lambda = Implementation of functional interface
* ✅ Syntax: `(params) -> expression`
* ✅ Reduces boilerplate

---

## 5. Method References

You can replace lambdas with **method references**.

```java
@FunctionalInterface
interface Printer {
    void print(String msg);
}

public class Test {
    public static void main(String[] args) {
        Printer printer = System.out::println;
        printer.print("Hello World!");
    }
}
```

### 🔑 Quick Revision

* ✅ `ClassName::methodName`
* ✅ Shorter than lambda if method exists
* ✅ Common with `System.out::println`

---

## 6. Rules & Constraints

* Only **1 abstract method** allowed.
* Can have multiple **default** and **static** methods.
* `Object` methods don’t count.

```java
@FunctionalInterface
interface MyInterface {
    void run();  // abstract method
    
    default void show() {
        System.out.println("Default method");
    }

    static void log() {
        System.out.println("Static method");
    }
}
```

### 🔑 Quick Revision

* ✅ 1 abstract method
* ✅ Default + static allowed
* ✅ `Object` methods ignored

---

## 7. Why Functional Interfaces?

Before Java 8 (boilerplate):

```java
Runnable r = new Runnable() {
    @Override
    public void run() {
        System.out.println("Running...");
    }
};
```

With Java 8 (lambda):

```java
Runnable r = () -> System.out.println("Running...");
```

### 🔑 Quick Revision

* ✅ Pre-Java 8 → anonymous inner classes
* ✅ Java 8+ → lambdas = concise

---

## 8. Custom Functional Interfaces

You can create your own.

```java
@FunctionalInterface
interface Transformer<T, R> {
    R transform(T input);
}

public class Test {
    public static void main(String[] args) {
        Transformer<String, Integer> strLength = s -> s.length();
        System.out.println(strLength.transform("ChatGPT")); // 7
    }
}
```

### 🔑 Quick Revision

* ✅ Define `@FunctionalInterface`
* ✅ Generic support possible
* ✅ Implement via lambda

---

## 9. Advanced Use Cases

### (a) With Streams

```java
List<String> names = List.of("Alice", "Bob", "Charlie");

names.stream()
     .filter(name -> name.startsWith("A"))  // Predicate
     .map(String::toUpperCase)              // Function
     .forEach(System.out::println);         // Consumer
```

### (b) Chaining Functions

```java
Function<Integer, Integer> doubleIt = x -> x * 2;
Function<Integer, Integer> squareIt = x -> x * x;

Function<Integer, Integer> combined = doubleIt.andThen(squareIt);
System.out.println(combined.apply(3)); // (3*2)^2 = 36
```

### 🔑 Quick Revision

* ✅ Streams → Predicate, Function, Consumer
* ✅ `andThen` → chain functions
* ✅ `compose` → reverse chaining

---

## 10. Interview-Level Insights

* **Why Single Abstract Method?** → Target type for lambdas.
* **Difference: Functional vs Normal Interface?** → Functional = 1 abstract method. Normal = many.
* **If `@FunctionalInterface` not used?** → Works but unsafe.
* **Are default methods abstract?** → No.
* **What is SAM Conversion?** → Mapping lambda → functional interface instance.

### 🔑 Quick Revision

* ✅ "SAM" = Single Abstract Method
* ✅ Lambdas need functional interface
* ✅ Default/static don’t count
* ✅ `@FunctionalInterface` prevents errors

---

# 📝 Final Summary

* **Functional Interface** = Interface with **1 abstract method**.
* Foundation for **lambdas** & **method references**.
* Many built-in ones in `java.util.function`.
* Supports **default** & **static methods**.
* Used heavily with **Streams & functional programming**.

### 🔑 One-Liner Cheatsheet

* Predicate → boolean condition
* Function → input → output
* Consumer → consumes input
* Supplier → supplies data
* UnaryOperator → one input → same type
* BinaryOperator → two inputs → same type

```
