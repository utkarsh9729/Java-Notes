## OOPS IN JAVA 

### Key Features of OOP in Java:
 -  Structures code into logical units (classes and objects)
-  Keeps related data and methods together (encapsulation)
-  Makes code modular, reusable and scalable
-  Prevents unauthorized access to data
-  Follows the DRY (Don’t Repeat Yourself) principle



#### ABSTRACTION
 -> What is Abstraction?

    Abstraction is the process of hiding the implementation details and showing only the essential features of an object.

In Java, abstraction is achieved in two ways:

- Abstract classes

- Interfaces

#### Abstract Class
Declared with the abstract keyword.

Can have:

- Abstract methods (without implementation).

- Concrete methods (with implementation). 
- Cannot be instantiated (you can’t create objects directly). 
- Supports constructors, instance variables, non-static methods.

#### Interfaces

What is an Interface?

- A blueprint of a class that contains abstract methods (and since Java 8, also default & static methods).

- Supports multiple inheritance (a class can implement multiple interfaces).

- Cannot have constructors or instance fields (before Java 8).

- Used when you want to specify a contract without caring about state.

### INTERFACE VS ABSTRACT CLASS
| Feature                  | Abstract Class                                          | Interface                                                                 |
| ------------------------ | ------------------------------------------------------- | ------------------------------------------------------------------------- |
| **Methods**              | Can have abstract + concrete                            | Before Java 8 → only abstract, After Java 8 → abstract + default + static |
| **Fields**               | Can have instance variables, `final`, `non-final`       | Only `public static final` (constants)                                    |
| **Constructors**         | Allowed                                                 | Not allowed                                                               |
| **Multiple Inheritance** | Not possible (a class can extend only 1 abstract class) | Possible (a class can implement multiple interfaces)                      |
| **Access Modifiers**     | Methods can be `public/protected`                       | Methods are always `public abstract` (unless default/static/private)      |
| **Use Case**             | When classes share **state + behavior**                 | When classes need to share only a **contract/behavior**                   |

### What is Data Hiding?

- Data Hiding means restricting direct access to the internal state (fields/variables) of a class.

- Instead of exposing variables directly, we control access via methods (getters, setters, or business methods).

- Achieved using access modifiers (private, protected, public, default).

👉 Purpose:

- Protects data integrity.

- Prevents external classes from putting an object into an invalid or inconsistent state.

- Enforces controlled access through APIs.

### What is Encapsulation?

- Encapsulation = binding data (fields/variables) and methods (functions/behavior) into a single unit (class), and controlling access to that data using access modifiers.


### Inheritance 

Inheritance lets a class (subclass/child) reuse and specialize code from another class (superclass/parent).
It models is-a relationships (e.g., Car is a Vehicle) and enables polymorphism (treat different subclasses uniformly via a parent type).

Typical goals:

-   Reuse shared state/behavior

-  Define stable APIs in the base; let subclasses customize specifics 
- Enable substitution (List of Animal can hold Dog, Cat, etc.)


What Is Inherited (and what isn’t)

##### Inherited

- public / protected members

- package-private members (only if subclass is in same package)

- default/static interface methods (via interface inheritance)

##### Not inherited

- Constructors

-  members

- Static initializers/blocks (still run, but not “inherited”)

- Fields are not polymorphic (they can be hidden, not overridden)



# Inheritance in Java – Deep Dive

## What & Why
**Inheritance** lets a class (**subclass/child**) reuse and specialize code from another class (**superclass/parent**).  
It models **is-a** relationships (e.g., `Car is a Vehicle`) and enables **polymorphism** (treat different subclasses uniformly via a parent type).

**Goals:**
- Reuse shared state/behavior
- Define stable APIs in the base; let subclasses customize specifics
- Enable substitution (`List` of `Animal` can hold `Dog`, `Cat`, etc.)

---

## Basic Syntax & Example
```java
abstract class Vehicle {
    protected final String vin;
    Vehicle(String vin) { this.vin = vin; }
    public void start() { System.out.println("Vehicle starting"); }   // concrete
    public abstract void drive();                                     // abstract
}

class Car extends Vehicle {
    Car(String vin) { super(vin); }                                   // constructor chain
    @Override public void drive() { System.out.println("Car driving"); }
    public void openTrunk() { /* ... */ }
}

Vehicle v = new Car("X1");
v.start();   // Vehicle starting
v.drive();   // Car driving  (runtime dispatch)
```

---

## What Is Inherited (and what isn’t)
**Inherited**
- `public` / `protected` members
- package-private members (only if subclass is in same package)
- `default`/`static` interface methods (via interface inheritance)

**Not inherited**
- **Constructors**
- **Private** members
- **Static** initializers/blocks (still run, but not “inherited”)
- **Fields are not polymorphic** (they can be *hidden*, not overridden)

---

## Constructor Chaining & Initialization Order
1. **Static** fields/blocks (super → sub, once per class)
2. Each `new`:
    - Super **instance fields** → super **constructor**
    - Sub **instance fields** → sub **constructor**

```java
class A {
    A() { f(); }                    
    void f() { System.out.println("A.f"); }
}
class B extends A {
    int x = 42;
    @Override void f() { System.out.println("B.f x=" + x); }
}
// new B() -> A() runs first, calls f() -> B.f sees x still 0 (not yet initialized)
```

**Rule:** Don’t call overridable methods from constructors.

---

## Overriding, Hiding, and Dispatch
**Overriding (instance methods)**
- Same signature; return type may be **covariant**
- Cannot reduce visibility
- Checked exceptions can be narrowed
- Use `@Override`

**Hiding (fields & static methods)**
```java
class P { static void m() {} int v = 1; }
class C extends P { static void m() {} int v = 2; }

P p = new C();
p.m();            // P.m
System.out.println(p.v); // 1
```

**Dynamic dispatch applies only to instance methods.**

---

## Access Modifiers Nuances
- `private`: not inherited
- package-private: visible only in same package
- `protected`: visible in subclass (but with restrictions if different package)

---

## Casting & `instanceof`
```java
Vehicle v = getVehicle();
if (v instanceof Car c) {       
    c.openTrunk();
}
```
- Upcast safe
- Downcast explicit, may throw `ClassCastException`

---

## Multiple Inheritance & Diamond
- Classes: **single inheritance only**
- Interfaces: multiple inheritance possible

```java
interface A { default void ping(){ System.out.println("A"); } }
interface B { default void ping(){ System.out.println("B"); } }
class C implements A, B {
    @Override public void ping() { A.super.ping(); }
}
```

---

## `final`, `abstract`, `sealed`
- `final class` → cannot be subclassed
- `final method` → cannot be overridden
- `abstract` class/method → must be implemented
- `sealed` (Java 17+) → restricts inheritance

```java
public sealed class Shape permits Circle, Rectangle {}
public final class Circle extends Shape {}
public non-sealed class Rectangle extends Shape {}
```

---

## Generics & Inheritance Gotchas
- Generics are **invariant**
- Use wildcards: `? extends T`, `? super T`
- Arrays are covariant → risk `ArrayStoreException`

---

## Design Guidance
- Prefer composition over inheritance
- Keep base classes small and stable
- Follow LSP
- Avoid `protected` fields; prefer `private` + hooks
- Don’t expose `this` during construction

**Patterns**
- Template Method
- Skeletal Implementation (`AbstractList`)

---

## Real-World Examples
- **Servlet API** (`HttpServlet`)
- **Spring** base entities, skeletal impls
- **JUnit** older versions with test base classes

---

## Common Pitfalls
- Overridable methods in constructors
- Fragile base classes
- Violating LSP
- Field hiding confusion
- Wrong use instead of composition

---

## Quick Quiz
1. Can a private method be overridden? **No**
2. Can you widen method visibility? **Yes**
3. Can override throw broader checked exceptions? **No**
4. Are static methods polymorphic? **No**
