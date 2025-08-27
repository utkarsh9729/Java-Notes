# 🟡 Medium (Applied Concepts) – Java OOPS (Q21–Q30)

---

## 21. Why is diamond problem an issue in multiple inheritance? How does Java solve it?

- The diamond problem occurs when a class inherits from two sources that define the same method, causing ambiguity over which implementation to use.
- Java prevents this for classes by disallowing multiple inheritance of classes (a class can extend only one class).
- With interfaces (Java 8+), if two interfaces provide the same default method, the implementing class must explicitly override and resolve the conflict (e.g., `A.super.m()` or `B.super.m()`).


---

## 22. What happens when you call an overridable method from a constructor?

- If a superclass constructor calls an overridable method, the subclass’s override is invoked even before the subclass constructor body runs.
- This is risky because subclass fields may not yet be initialized, leading to subtle bugs.
- Best practice: Avoid calling overridable (non-final, non-private) methods from constructors.


---

## 23. How do sealed classes (Java 17+) enhance OOPS design?

- A sealed class restricts which classes can extend it using `permits`.
- Benefits:
    - Controlled inheritance and clear API boundaries.
    - Improves readability and maintainability of hierarchies.
    - Enables exhaustive pattern matching in `switch` (when all subtypes are known).


---

## 24. Explain fragile base class problem in inheritance.

- Changes to a base class can unintentionally break subclasses (e.g., adding a new method or changing internal behavior that subclasses relied on).
- Example: Base adds a method that accidentally conflicts with a method in a subclass, changing dispatch/binding unexpectedly.
- Mitigation: Prefer composition over inheritance, minimize inheritance depth, design for extension with clear contracts, and use final/ sealed where appropriate.

---

## 25. Can an interface extend another interface? Can an interface extend a class?

- Yes: An interface can extend one or more interfaces.
- No: An interface cannot extend a class (interfaces define contracts, not implementation).
- Note: An interface can declare static and default methods, but it still cannot inherit from a class.


---

## 26. How does the Liskov Substitution Principle (LSP) apply to Java inheritance?

- LSP: Subtypes must be substitutable for their supertypes without altering desirable properties of the program.
- In Java:
    - Do not weaken preconditions (don’t require more).
    - Do not strengthen postconditions (don’t guarantee less).
    - Don’t throw broader checked exceptions than the parent method.
- Violations often surface as surprising runtime behavior or compilation constraints around exceptions.


---

## 27. Why are arrays covariant but generics invariant in Java?

- Arrays are covariant: `String[]` is a subtype of `Object[]`. This can cause runtime errors (ArrayStoreException).
- Generics are invariant: `List<String>` is NOT a subtype of `List<Object>`. This ensures compile-time type safety.
- Reason: Arrays existed before generics; generics were designed later to improve type safety.


---

## 28. Can a subclass declare a broader checked exception than the parent method? Why not?

- No: A subclass cannot declare a broader checked exception.
- Yes: It may declare a narrower exception or unchecked exceptions.
- Rationale: Broadening breaks substitutability (LSP) because callers coded against the parent type wouldn’t be prepared to handle the broader exception.


---

## 29. Explain how polymorphism + generics work together (with PECS: Producer Extends, Consumer Super).

- Wildcards enable flexible APIs with generics while preserving type safety.
- PECS:
    - ? extends T → Producer: safe to read as T, not safe to add.
    - ? super T → Consumer: safe to add Ts, reads are at least Object.


## 30. In a real system, when prefer abstraction with interface over abstract class?

- Prefer interface when:
    - Multiple inheritance of type is needed (a class can implement many interfaces).
    - Capability-based abstraction (Comparable, Serializable).
    - You want to evolve APIs via default methods without forcing hierarchy coupling.

- Prefer abstract class when:
    - You need shared state, protected fields, or common base implementation.
    - You want to provide non-trivial default behavior and enforce a skeletal implementation (Template Method).

