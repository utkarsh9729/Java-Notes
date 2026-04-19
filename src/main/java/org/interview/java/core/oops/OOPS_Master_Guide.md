# 🏛️ Object-Oriented Programming (OOP) Master Guide

> A comprehensive guide to OOP principles, patterns, and interview questions in Java.

---

## 1. The Four Pillars of OOP

### 🔹 Abstraction
Abstraction is the process of hiding implementation details and showing only the essential features of an object.
- **Abstract Classes**: Used when classes share **state + behavior**. Can have abstract and concrete methods.
- **Interfaces**: Used when classes share only a **contract**. Since Java 8, can have `default` and `static` methods.

### 🔹 Encapsulation
Encapsulation is the binding of data (fields) and behavior (methods) into a single unit (class) while controlling access via modifiers.
- **Data Hiding**: Keeping fields `private` and exposing them through public methods.
- **Defensive Copying**: Returning copies of mutable objects to prevent external state corruption.

### 🔹 Inheritance
Enables a class (subclass) to acquire properties and methods of another class (superclass).
- Models **is-a** relationships.
- **Constructor Chaining**: Subclass constructors implicitly call `super()` unless another constructor is explicitly called.
- **Diamond Problem**: Java avoids this for classes (single inheritance only) but handles it in interfaces via explicit overrides for default method conflicts.

### 🔹 Polymorphism
One name, many forms.
- **Compile-time (Overloading)**: Multiple methods with the same name but different parameters. Resolved by the compiler.
- **Runtime (Overriding)**: Subclass provides a specific implementation of a method defined in the superclass. Resolved at runtime via **Dynamic Method Dispatch**.

---

## 2. SOLID Principles

1. **S**ingle Responsibility: A class should have one, and only one, reason to change.
2. **O**pen/Closed: Software entities should be open for extension but closed for modification.
3. **L**iskov Substitution: Subtypes must be substitutable for their base types without breaking the program.
4. **I**nterface Segregation: Clients should not be forced to depend on methods they do not use.
5. **D**ependency Inversion: Depend on abstractions, not concretions.

---

## 3. Critical Interview Questions

### Q: Why is `Composition` preferred over `Inheritance`?
Inheritance is a tight coupling (white-box reuse). Composition is a loose coupling (black-box reuse) that is more flexible at runtime and avoids the "fragile base class" problem.

### Q: Can you override a static method?
No. Static methods belong to the class, not the instance. Defining a static method with the same signature in a subclass is called **Method Hiding**, not overriding.

### Q: What is the "Fragile Base Class" problem?
A situation where seemingly safe changes to a base class break the functionality of subclasses because the subclasses depend on the internal implementation details of the base class.

### Q: Covariant Return Types?
Java allows an overriding method to return a subtype of the type returned by the overridden method. This is useful for returning specific subtypes in specialized classes.

---

## 4. Patterns & Design Guidance
- **Template Method**: Defines the skeleton of an algorithm in a base class, deferring some steps to subclasses.
- **Sealed Classes (Java 17+)**: Restricts which classes can extend or implement a class or interface, providing better control over the hierarchy.
- **PECS (Producer Extends, Consumer Super)**: Guidelines for using wildcards in Generics to ensure type safety while maintaining flexibility.
